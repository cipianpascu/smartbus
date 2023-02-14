package com.roka.smarthomeg4.udp_socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.Calendar;
import com.roka.smarthomeg4.My_app;
import com.roka.smarthomeg4.business.FanInZone;
import com.roka.smarthomeg4.business.LightInZone;
import android.util.Log;

public class LightSocketConnection {

	// udp数据报定义声明

	// private Handler moHandler;

	/*
	 * 复写这个类
	 */

	public String allMessage = "";
//	private LightInZone lightInZone;

	public LightSocketConnection() {
		// TODO Auto-generated constructor stub
		CreateUDPSocket();
	}

	// public DatagramSocket GetUDPSocket() {
	// return SmartSocketConnection.moUDPSocket;
	// }

	public void CreateUDPSocket() {
		try {

			if (SmartSocketConnection.moUDPSocket != null) {
				if (SmartSocketConnection.moUDPSocket.isClosed() == false) {
					SmartSocketConnection.moUDPSocket.close();
				}
				SmartSocketConnection.moUDPSocket = null;
			}
			// set udpsock and setBrodcast
			// moUDPSocket=new DatagramSocket(CONST_UDP_UDP_PORT);
			// moUDPSocket.setBroadcast(true);//ةèضأ±êض¾£¬¹م²¥ت‎¾ف±¨
			// دآأوتاسِµ½the address is already in use ½â¾ِ°ى·¨
			if (SmartSocketConnection.moUDPSocket == null) {
				SmartSocketConnection.moUDPSocket = new DatagramSocket(null);
				SmartSocketConnection.moUDPSocket.setReuseAddress(true);
				SmartSocketConnection.moUDPSocket.setBroadcast(true);// ةèضأ±êض¾£¬T
																		// OR E
																		// OR
				// R¹م²¥ت‎¾ف±¨
				// شع2.2×َسز°و±¾²»ذذ،£شع4.0°و±¾ةد؟ةذذ
				SmartSocketConnection.moUDPSocket.bind(new InetSocketAddress(
						SmartSocketConnection.CONST_UDP_UDP_PORT));
			}

		} catch (SocketException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			// Toast.makeText(getApplicationContext(), e.getMessage(),
			// Toast.LENGTH_LONG).show();
		}

	}

	public int HandleLightOnoff(LightInZone lightInZone, int intOldLevel,
			int waitSendValue) {
		int bytSuccess = 0;
		try {
			byte byteSubnetID, byteDeviceID;// 子网ID，设备ID
			int intChns, intTemp;// 是哪一路，灯亮度，


			

			intTemp = (Integer) lightInZone.getSubnetID();
			byteSubnetID = (byte) intTemp;
			Log.i("btn", "byteSubnetID = " + byteSubnetID);
			intTemp = (Integer) lightInZone.getDeviceID();
			byteDeviceID = (byte) intTemp;

			intChns = (Integer) lightInZone.getChannelNo();

			// 执行开关灯
			// pblvariables_public.mblnNeedCancelToWaitInUDPSocket = true;
			if (SingleChannelControl(byteSubnetID, byteDeviceID, intChns,
					waitSendValue, 0, false, false) == true) {
				bytSuccess = 1;
				// 并且要在参数结构体当中说明当前灯的亮度值和开关状态，判断前提--根据灯亮度值来判断是开还是关
				// mListLightParams.get(intPosition).setLigthStatus(NewLightStatu);
				// mListLightParams.get(intPosition).setcurLigthValue(
				// waitSendValue);
				Log.i("btn", "bytSuccess = 1");
			} else {
				bytSuccess = 0;
			}

			// String strFlag;
			// if (bytSuccess == 1)
			{

				// RefreshUIFromOnOffByMsg(1, intPosition, context,
				// waitSendValue,
				// layoutType, marraylistHashMap, intLightType);

			}

		} catch (Exception e) {
			// Toast.makeText(getApplicationContext(), e.getMessage(),
			// Toast.LENGTH_LONG).show();
		}

		finally {
			// pblvariables_public.mblnNeedCancelToWaitInUDPSocket = false;
		}

		return bytSuccess;
	}// end

	public boolean SingleChannelControl(byte byteSubnetID, byte byteDeviceID,
			int intChns, int intBrightness, int intThirdPara,
			boolean blnNeedResend, boolean blnNeedToWaitANS) {
		boolean blnSuccess = false, blnSent = false;

		try {
			byte[] arraybyteBufWithoutAA = null;
			byte byteI;

			int intOP = 0x0031;
			short shortLenOfAddtionalBuf;

			byte[] arrayAddtional = new byte[4];

			arrayAddtional[0] = (byte) intChns;
			arrayAddtional[1] = (byte) intBrightness;
			arrayAddtional[2] = (byte) 0;
			arrayAddtional[3] = (byte) 0;

			shortLenOfAddtionalBuf = (short) (arrayAddtional.length);
			// 发送2次--做一个重发机制
			for (byteI = 1; byteI <= SmartSocketConnection.CONST_MAX_TIMES_OF_SEND; byteI++) {
				// 判断UDPSocket是否关闭，
				if (SmartSocketConnection.IsSocketClose() == true) {
					return false;
				}
				/*
				 * if (pblvariables.mblnNeedCancelToWaitInUDPSocket==true) {
				 * return false; }
				 */

				blnSent = SendUDPBuffer(arrayAddtional, shortLenOfAddtionalBuf,
						intOP, byteSubnetID, byteDeviceID, false);
				if (blnSent == true) // 如果发送成功
				{
					// 与否只是做一个程序转责的一种标志哦
					if (blnNeedToWaitANS == true)// 在发送成功的条件下如果需要等待回应，就要接受数据报包
					{
						arraybyteBufWithoutAA = UDPReceive(byteSubnetID,
								byteDeviceID, intOP, true);
						allMessage += "\n";
						if (arraybyteBufWithoutAA == null) {
							if ((blnNeedResend == false)
									|| (My_app.mblnNeedCancelToWaitInUDPSocket == true)) {
								return false;
							}
							{
								break;
							}
						} else {
							blnSuccess = true;
							break;
						}
					} else {
						blnSuccess = true;
						break;
					}

				} else {
					break;
				}
			}

		} catch (Exception e) {
			// Toast.makeText(getApplicationContext(), e.getMessage(),
			// Toast.LENGTH_SHORT).show();
		} finally {

		}
		return blnSuccess;
	}

	public byte[] SingleChannelControl(byte byteSubnetID, byte byteDeviceID,

	boolean blnNeedResend, boolean blnNeedToWaitANS) {
		boolean blnSuccess = false, blnSent = false;
		byte[] arraybyteBufWithoutAA = null;
		byte status = 0;
		try {
		
			byte byteI;

			int intOP = 0x0033;
			short shortLenOfAddtionalBuf;

			byte[] arrayAddtional=null;
			

			shortLenOfAddtionalBuf =0;
			// 发送2次--做一个重发机制
			for (byteI = 1; byteI <= SmartSocketConnection.CONST_MAX_TIMES_OF_SEND; byteI++) {
				// 判断UDPSocket是否关闭，
				if (SmartSocketConnection.IsSocketClose() == true) {
					return null;
				}
				/*
				 * if (pblvariables.mblnNeedCancelToWaitInUDPSocket==true) {
				 * return false; }
				 */

				blnSent = SendUDPBuffer(arrayAddtional, shortLenOfAddtionalBuf,
						intOP, byteSubnetID, byteDeviceID, false);
				if (blnSent == true) // 如果发送成功
				{
					// 与否只是做一个程序转责的一种标志哦
					if (blnNeedToWaitANS == true)// 在发送成功的条件下如果需要等待回应，就要接受数据报包
					{
						arraybyteBufWithoutAA = UDPReceiveLightStatus(byteSubnetID,
								byteDeviceID, intOP, true);
						if (arraybyteBufWithoutAA == null) {
							if ((blnNeedResend == false)
									|| (My_app.mblnNeedCancelToWaitInUDPSocket == true)) {
								return null;
							}
							{
								break;
							}
						} else {
							blnSuccess = true;
							break;
						}
					} else {
						blnSuccess = true;
						break;
					}

				} else {
					break;
				}
			}

		} catch (Exception e) {
			// Toast.makeText(getApplicationContext(), e.getMessage(),
			// Toast.LENGTH_SHORT).show();
		} finally {

		}
		return arraybyteBufWithoutAA;
	}

	public byte[] readStatusofChannel(LightInZone lightInZone) {
		byte[] status=null;
		try {
//			this.lightInZone=lightInZone;
			byte byteSubnetID, byteDeviceID;// 子网ID，设备ID
			int intTemp;// 是哪一路，灯亮度，

			intTemp = (Integer) lightInZone.getSubnetID();
			byteSubnetID = (byte) intTemp;
			Log.i("btn", "byteSubnetID = " + byteSubnetID);
			intTemp = (Integer) lightInZone.getDeviceID();
			byteDeviceID = (byte) intTemp;

			// pblvariables_public.mblnNeedCancelToWaitInUDPSocket = true;
//			CreateUDPSocket();
			 status = SingleChannelControl(byteSubnetID, byteDeviceID,
					false, true);
//			if (status > 0) {
//				lightInZone.setStatus(1);
//
//			} else {
//				lightInZone.setStatus(0);
//			}
//			lightInZone.setNewValue(status);

		} catch (Exception e) {
			// Toast.makeText(getApplicationContext(), e.getMessage(),
			// Toast.LENGTH_LONG).show();
		}

		finally {
			// pblvariables_public.mblnNeedCancelToWaitInUDPSocket = false;
		}
		return status;

	}// end
	
	public byte[] readStatusofChannel(FanInZone fanInZone) {
		byte[] status=null;
		try {
//			this.lightInZone=lightInZone;
			byte byteSubnetID, byteDeviceID;// 子网ID，设备ID
			int intTemp;// 是哪一路，灯亮度，

			intTemp = (Integer) fanInZone.getSubnetID();
			byteSubnetID = (byte) intTemp;
			Log.i("btn", "byteSubnetID = " + byteSubnetID);
			intTemp = (Integer) fanInZone.getDeviceID();
			byteDeviceID = (byte) intTemp;

			// pblvariables_public.mblnNeedCancelToWaitInUDPSocket = true;
//			CreateUDPSocket();
			 status = SingleChannelControl(byteSubnetID, byteDeviceID,
					false, true);
//			if (status > 0) {
//				lightInZone.setStatus(1);
//
//			} else {
//				lightInZone.setStatus(0);
//			}
//			lightInZone.setNewValue(status);

		} catch (Exception e) {
			// Toast.makeText(getApplicationContext(), e.getMessage(),
			// Toast.LENGTH_LONG).show();
		}

		finally {
			// pblvariables_public.mblnNeedCancelToWaitInUDPSocket = false;
		}
		return status;

	}// end

	public boolean SendUDPBuffer(byte[] arrayAddtional,
			short shortLenOfAddtionalBuf, int intOP, byte byteObjSubnetID,
			byte byteObjDeviceID, boolean blnBigPack) {
		boolean blnSuccess = false;
		boolean blnNeedShowIPError = false;
		String strLocalIP;
		short shortLenOfBaseData, shortI, shortLenOfPackCRCBufWithAA, shortLenOfPackCRCBufWithoutAA, shortLenOfSend;
		DatagramPacket oDataPacket;
		byte[] bytebufSend;
		byte[] arraybyteLocalIP = new byte[4];
		byte[] arraybyteTargetIP = new byte[4];
		// byte[] arraybyteBufRec = new byte[CONST_MAX_UPD_PACKET_LEN];
		// byte[] arraybyteRec;

		// DatagramPacket oPacket = new
		// DatagramPacket(arraybyteBufRec,arraybyteBufRec.length);

		try {
			// bytebufRec=new byte[300];
			// 前提，保证有已经获得本地的 IP
			arraybyteLocalIP = SmartSocketConnection.GetLocalIP();
			Log.v("breakPoint", "SendUDPBuffer 正在获得本地IP地址");
			System.out.println("arraybyteLocalIP="
					+ arraybyteLocalIP.toString());
			//
			// if (arraybyteLocalIP != null)
			// {
			//
			// }
			// else
			// {
			// blnNeedShowIPError=true;
			//
			//
			// blnSuccess=false;
			// return blnSuccess;
			//
			// };

			arraybyteTargetIP = SmartSocketConnection
					.GetTargetIP(arraybyteLocalIP);
			Log.v("breakPoint", "SendUDPBuffer 正在获得TargetIP地址");
			Log.i("breakPoint", "TaggetID==" + arraybyteTargetIP.toString());

			// short
			// shortLenOfBaseData,shortI,shortLenOfPackCRCBufWithAA,shortLenOfPackCRCBufWithoutAA,shortLenOfSend;
			shortLenOfBaseData = 11;// 数据包长度设为11.其基础udp包长度为11个字节，这里先声明UDP包的基本长度大小
			shortLenOfPackCRCBufWithoutAA = (short) (shortLenOfBaseData + shortLenOfAddtionalBuf);
			shortLenOfPackCRCBufWithAA = (short) (shortLenOfPackCRCBufWithoutAA + 2);

			shortLenOfSend = (short) (shortLenOfPackCRCBufWithAA + 14);// 为什么加14。14个字节表示的是包头的总大小字节数
			bytebufSend = new byte[shortLenOfSend];

			byte[] arrayPackCRC = new byte[shortLenOfPackCRCBufWithoutAA];
			bytebufSend[0] = arraybyteLocalIP[0];
			bytebufSend[1] = arraybyteLocalIP[1];
			bytebufSend[2] = arraybyteLocalIP[2];
			bytebufSend[3] = arraybyteLocalIP[3];

			// UNKNOWN包头校验码，已经封装好了的
			// bytebufSend[4]=0x48; //H
			// bytebufSend[5]=0x44; //D
			// bytebufSend[6]=0x4C; //L
			// bytebufSend[7]=0x4D; //M
			// bytebufSend[8]=0x49; //I
			// bytebufSend[9]=0x52; //R
			// bytebufSend[10]=0x41; //A
			// bytebufSend[11]=0x43; //C
			// bytebufSend[12]=0x4C; //L
			// bytebufSend[13]=0x45; //E

			bytebufSend[4] = 0x53; // S
			bytebufSend[5] = 0x4D; // M
			bytebufSend[6] = 0x41; // A
			bytebufSend[7] = 0x52; // R
			bytebufSend[8] = 0x54; // T
			bytebufSend[9] = 0x43; // C
			bytebufSend[10] = 0x4C; // L
			bytebufSend[11] = 0x4F; // O
			bytebufSend[12] = 0x55; // U
			bytebufSend[13] = 0x44; // D

			bytebufSend[14] = (byte) 0xAA; //
			bytebufSend[15] = (byte) 0xAA; //

			// data size 判断大包是否有起始码
			if ((blnBigPack == true)
					|| ((shortLenOfAddtionalBuf + shortLenOfBaseData) > 80)) {
				// 如果是打包，为什么0xFF
				arrayPackCRC[0] = (byte) 0xFF;
			} else {
				arrayPackCRC[0] = (byte) shortLenOfPackCRCBufWithoutAA;
			}
			;

			// 基本数据结构
			arrayPackCRC[0] = (byte) shortLenOfPackCRCBufWithoutAA; // LEN of
																	// Data
																	// Package,包长大小
			arrayPackCRC[1] = (byte) SmartSocketConnection.CONST_SELF_SUBNET_ID; // 源subID
			arrayPackCRC[2] = (byte) SmartSocketConnection.CONST_SELF_DEVICE_ID; // 源设备ID
			arrayPackCRC[3] = (byte) SmartSocketConnection.CONST_SELF_DEVICE_TYPE_H; // 设备类型，高8位
			arrayPackCRC[4] = (byte) SmartSocketConnection.CONST_SELF_DEVICE_TYPE_L; // 设备类型，低8位
			arrayPackCRC[5] = (byte) (intOP / 256); // H bit of operation code
													// 高位操作码，
			arrayPackCRC[6] = (byte) (intOP % 256); // L bit of operation
													// code低位操作码
			arrayPackCRC[7] = byteObjSubnetID; // 目标子网ID
			arrayPackCRC[8] = byteObjDeviceID; // 目标设备ID

			// 到了Addtional Content 附加数据
			if (shortLenOfAddtionalBuf > 0)// 如果有附加数据
			{
				for (shortI = 0; shortI <= shortLenOfAddtionalBuf - 1; shortI++) {
					arrayPackCRC[9 + shortI] = arrayAddtional[shortI];
				}
			}

			if (blnBigPack == false) // 如果是小包
			{
				SmartSocketConnection.PackCRC(arrayPackCRC,
						(short) (arrayPackCRC.length - 2));
			}

			// 不管是打包还是小包.都把数据打包
			for (shortI = 0; shortI <= arrayPackCRC.length - 1; shortI++) {
				bytebufSend[shortI + 16] = arrayPackCRC[shortI];// 为什么加16,
			}

			// 实例化一个数据报包，并说明所发包，其包大小，目标ip，目标端口，指定端口号和目标IP。再通过udpsocket发送出去，就可以达到了指定的端口
			// T

			// oDataPacket = new DatagramPacket(data, length);

			oDataPacket = new DatagramPacket(bytebufSend, shortLenOfSend,
					InetAddress.getByAddress(arraybyteTargetIP),
					SmartSocketConnection.CONST_UDP_UDP_PORT);

			SmartSocketConnection.moUDPSocket.send(oDataPacket);

			Log.v("breakPoint", "正在发送UDP");
			// 我采取广播的方式出去怎么样？？？？。则是向路由器广播消息，
			blnSuccess = true;

		} catch (Exception e) {
			e.printStackTrace();
			// Test
			// Toast.makeText(getApplicationContext(), e.getMessage(),
			// Toast.LENGTH_SHORT).show();
		}
		return blnSuccess;
	}

	public byte[] UDPReceive(byte byteSubnetID, byte byteDeviceID, int intOP,
			boolean blnNeedCheckAddressOfFeedback) {
		boolean blnSuccess = false;
		byte[] arraybyteBufRec = null;
		byte[] arraybyteBufWithoutAA = null;

		try {
			boolean blnContinute;
			long lngStartTime_of_MS;
			long lngCurTime_of_MS;

			byte byteSrcSubnetID_of_reply;
			byte byteSrcDeviceID_of_reply;
			int intOP_of_reply;
			int intOP_H, intOP_L;
			int intTimes = 0;
			Calendar oCal;

			oCal = Calendar.getInstance();
			lngStartTime_of_MS = oCal.getTimeInMillis();
			lngCurTime_of_MS = lngStartTime_of_MS;
			while ((lngCurTime_of_MS - lngStartTime_of_MS) <= SmartSocketConnection.CONST_TIME_OUT_FOR_TOTAL_WAIT) {// CONST_TIME_OUT_FOR_TOTAL_WAIT
				// ==
				// 4000
				try {
					if (My_app.mblnNeedCancelToWaitInUDPSocket == true) {
						return null;
					}

					if (SmartSocketConnection.IsSocketClose() == true) {
						return null;
					}

					intTimes = intTimes + 1;
					byte[] arraybyteBufTEMP = new byte[SmartSocketConnection.CONST_MAX_UPD_PACKET_LEN];// 2048
					DatagramPacket oPacketRec = new DatagramPacket(
							arraybyteBufTEMP, arraybyteBufTEMP.length);

					// T不要这两项设置，设置延时为1秒钟
					SmartSocketConnection.moUDPSocket
							.setSoTimeout(SmartSocketConnection.CONST_TIME_OUT_FOR_EACH_WAIT);// 1000
					// 当设置小了时，会收不到UDP数据
					SmartSocketConnection.moUDPSocket
							.setReceiveBufferSize(SmartSocketConnection.CONST_MAX_UPD_PACKET_LEN);// 2048
					SmartSocketConnection.moUDPSocket.receive(oPacketRec);
					arraybyteBufRec = oPacketRec.getData();
					// 判断操作码，高位操作码。和低位操作吗

					intOP_H = arraybyteBufRec[SmartSocketConnection.CONST_START_PST_OF_LEN_OF_DATA_IN_FULL_PACKETS + 5] & 0xFF;
					intOP_L = arraybyteBufRec[SmartSocketConnection.CONST_START_PST_OF_LEN_OF_DATA_IN_FULL_PACKETS + 6] & 0xFF;
					intOP_of_reply = intOP_H + intOP_L;
					//

					// 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32
				//[-64,-88, 1, -56, 83, 77, 65, 82, 84, 67, 76, 79, 85, 68, -86, -86, 17, 1, -43, 19, -100, 0, 50, -1, -1, 1, -8,
					// 100, 15, 14, 0, -40, 17 ]

					// 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21
					// 22 23 24 25 26 27 28 29 30 31 32
					// c0 a8 1 c8 53 4d 41 52 54 43 4c 4f 55 44 aa aa 11 1 d5 13
					// 9c 0 32 ff ff 1 f8 64 f e 0 d8 11

//					 String all ="";
//					 for (int i = 0; i < arraybyteBufRec.length; i++) {
//					 byte a= arraybyteBufRec[i];
//					 String str =byteToHexString(a);
//					 all+=str;
//					 }
					
//					 allMessage+=all+"\n";

					if (intOP_of_reply == (intOP + 1)) {
						if (blnNeedCheckAddressOfFeedback == true) {
							byteSrcSubnetID_of_reply = arraybyteBufRec[SmartSocketConnection.CONST_START_PST_OF_LEN_OF_DATA_IN_FULL_PACKETS + 1];
							byteSrcDeviceID_of_reply = arraybyteBufRec[SmartSocketConnection.CONST_START_PST_OF_LEN_OF_DATA_IN_FULL_PACKETS + 2];
							if ((byteSrcSubnetID_of_reply == byteSubnetID)
									& (byteSrcDeviceID_of_reply == byteDeviceID)) {
								blnContinute = true;
							} else {
								blnContinute = false;
							}
						} else {
							blnContinute = true;
						}

						if (blnContinute == true) {
							arraybyteBufWithoutAA = ProcessUDPPackets(arraybyteBufRec);
							if (arraybyteBufWithoutAA != null) {
								blnSuccess = true;
								break;
							}
						}

					}

					lngCurTime_of_MS = System.currentTimeMillis();
					if ((lngCurTime_of_MS - lngStartTime_of_MS) > SmartSocketConnection.CONST_TIME_OUT_FOR_TOTAL_WAIT) {
						break;
					}

				} catch (Exception e) {
					lngCurTime_of_MS = System.currentTimeMillis();
					if ((lngCurTime_of_MS - lngStartTime_of_MS) > SmartSocketConnection.CONST_TIME_OUT_FOR_TOTAL_WAIT) {
						break;
					}

				}

			}
			// receive packets end

		} catch (Exception e) {
			// Toast.makeText(getApplicationContext(), e.getMessage(),
			// Toast.LENGTH_SHORT).show();
		} finally {

		}

		if (blnSuccess == true) {
			return arraybyteBufWithoutAA;

		} else {
			return null;
		}
	}
	
	
	public byte[] UDPReceiveLightStatus(byte byteSubnetID, byte byteDeviceID, int intOP,
			boolean blnNeedCheckAddressOfFeedback) {
		boolean blnSuccess = false;
		byte[] arraybyteBufRec = null;
		byte[] arraybyteBufWithoutAA = null;

		try {
			boolean blnContinute;
			long lngStartTime_of_MS;
			long lngCurTime_of_MS;

			byte byteSrcSubnetID_of_reply;
			byte byteSrcDeviceID_of_reply;
			int intOP_of_reply;
			int intOP_H, intOP_L;
			int intTimes = 0;
			Calendar oCal;

			oCal = Calendar.getInstance();
			lngStartTime_of_MS = oCal.getTimeInMillis();
			lngCurTime_of_MS = lngStartTime_of_MS;
			while ((lngCurTime_of_MS - lngStartTime_of_MS) <= SmartSocketConnection.CONST_TIME_OUT_FOR_TOTAL_WAIT) {// CONST_TIME_OUT_FOR_TOTAL_WAIT
				// ==
				// 4000
				try {
					if (My_app.mblnNeedCancelToWaitInUDPSocket == true) {
						return null;
					}

					if (SmartSocketConnection.IsSocketClose() == true) {
						return null;
					}

					intTimes = intTimes + 1;
					byte[] arraybyteBufTEMP = new byte[SmartSocketConnection.CONST_MAX_UPD_PACKET_LEN];// 2048
					DatagramPacket oPacketRec = new DatagramPacket(
							arraybyteBufTEMP, arraybyteBufTEMP.length);

					// T不要这两项设置，设置延时为1秒钟
					SmartSocketConnection.moUDPSocket
							.setSoTimeout(SmartSocketConnection.CONST_TIME_OUT_FOR_EACH_WAIT);// 1000
					// 当设置小了时，会收不到UDP数据
					SmartSocketConnection.moUDPSocket
							.setReceiveBufferSize(SmartSocketConnection.CONST_MAX_UPD_PACKET_LEN);// 2048
					SmartSocketConnection.moUDPSocket.receive(oPacketRec);
					arraybyteBufRec = oPacketRec.getData();
					// 判断操作码，高位操作码。和低位操作吗
					intOP_H = (arraybyteBufRec[SmartSocketConnection.CONST_START_PST_OF_LEN_OF_DATA_IN_FULL_PACKETS + 5] * 256) & 0xFFFF;
					intOP_L = arraybyteBufRec[SmartSocketConnection.CONST_START_PST_OF_LEN_OF_DATA_IN_FULL_PACKETS + 6] & 0xFF;
					intOP_of_reply = intOP_H + intOP_L;
					//

					// 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32
				//[-64,-88, 1, -56, 83, 77, 65, 82, 84, 67, 76, 79, 85, 68, -86, -86, 17, 1, -43, 19, -100, 0, 50, -1, -1, 1, -8,
					// 100, 15, 14, 0, -40, 17 ]

					// 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21
					// 22 23 24 25 26 27 28 29 30 31 32
					// c0 a8 1 c8 53 4d 41 52 54 43 4c 4f 55 44 aa aa 11 1 d5 13
					// 9c 0 32 ff ff 1 f8 64 f e 0 d8 11

//					 String all ="";
//					 for (int i = 0; i < arraybyteBufRec.length; i++) {
//					 byte a= arraybyteBufRec[i];
//					 String str =byteToHexString(a);
//					 all+=str;
//					 }
					
//					 allMessage+=all+"\n";

					if (intOP_of_reply == (intOP + 1)) {
						if (blnNeedCheckAddressOfFeedback == true) {
							byteSrcSubnetID_of_reply = arraybyteBufRec[SmartSocketConnection.CONST_START_PST_OF_LEN_OF_DATA_IN_FULL_PACKETS + 1];
							byteSrcDeviceID_of_reply = arraybyteBufRec[SmartSocketConnection.CONST_START_PST_OF_LEN_OF_DATA_IN_FULL_PACKETS + 2];
							if ((byteSrcSubnetID_of_reply == byteSubnetID)
									& (byteSrcDeviceID_of_reply == byteDeviceID)) {
								blnContinute = true;
							} else {
								blnContinute = false;
							}
						} else {
							blnContinute = true;
						}

						if (blnContinute == true) {
							arraybyteBufWithoutAA = ProcessUDPPackets(arraybyteBufRec);
							if (arraybyteBufWithoutAA != null) {
								blnSuccess = true;
								break;
							}
						}

					}

					lngCurTime_of_MS = System.currentTimeMillis();
					if ((lngCurTime_of_MS - lngStartTime_of_MS) > SmartSocketConnection.CONST_TIME_OUT_FOR_TOTAL_WAIT) {
						break;
					}

				} catch (Exception e) {
					lngCurTime_of_MS = System.currentTimeMillis();
					if ((lngCurTime_of_MS - lngStartTime_of_MS) > SmartSocketConnection.CONST_TIME_OUT_FOR_TOTAL_WAIT) {
						break;
					}

				}

			}
			// receive packets end

		} catch (Exception e) {
			// Toast.makeText(getApplicationContext(), e.getMessage(),
			// Toast.LENGTH_SHORT).show();
		} finally {

		}

		if (blnSuccess == true) {
			return arraybyteBufWithoutAA;

		} else {
			return null;
		}
	}


	public static String byteToHexString(byte bytes) {
		StringBuffer buffer = new StringBuffer();

		if (((int) bytes & 0xff) < 0x10)
			buffer.append("0");
		buffer.append(Long.toString((int) bytes & 0xff, 16));

		return buffer.toString();
	}

	public static String byteArrayToHexString(byte[] bytes) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			if (((int) bytes[i] & 0xff) < 0x10)
				buffer.append("0");
			buffer.append(Long.toString((int) bytes[i] & 0xff, 16));
		}
		return buffer.toString();
	}

	/**
	 * converts given hex string to a byte array (ex: "0D0A" => {0x0D, 0x0A,})
	 * 
	 * @param str
	 * @return
	 */
	public static final byte[] hexStringToByteArray(String str) {
		int i = 0;
		byte[] results = new byte[str.length() / 2];
		for (int k = 0; k < str.length();) {
			results[i] = (byte) (Character.digit(str.charAt(k++), 16) << 4);
			results[i] += (byte) (Character.digit(str.charAt(k++), 16));
			i++;
		}
		return results;
	}

	public byte[] ProcessUDPPackets(byte[] arraybyteRec) {
		byte[] arraybyteBufWithoutHead = null;
		int intLenOfPackets;
		boolean blnIsBigPack = false;

		try {
			if (SmartSocketConnection.IsSocketClose() == true) {
				return null;
			}

			int intSizeWithoutHead = 0, intI = 0;
			boolean blnNeedToCheckCRC = false;

			intLenOfPackets = arraybyteRec.length;
			if ((intLenOfPackets <= 0) || (intLenOfPackets < 27))// 小于27表示没有附加数据
			{
				return null;
			}

			if (((arraybyteRec[14] & 0xFF) == 0xAA)
					&& (((arraybyteRec[15] & 0xFF) == 0xAA) || ((arraybyteRec[15] & 0xFF) == 0x55))) {
				// do nothing
			} else {
				return null;
			}

			// 这样便判断包是否是打包
			if ((arraybyteRec[16] & 0xFF) == 0xFF) {
				blnIsBigPack = true;
			} else {
				blnIsBigPack = false;
			}

			intSizeWithoutHead = intLenOfPackets - 16;// 把包包头，IP，起始吗去掉，剩下的为协议基础包架构的包长开始
			arraybyteBufWithoutHead = new byte[intSizeWithoutHead];
			for (intI = 0; intI < arraybyteBufWithoutHead.length; intI++) {
				arraybyteBufWithoutHead[intI] = arraybyteRec[intI + 16];
			}

			// 是大包就不需要检查CRC校验
			if (blnIsBigPack == true) {
				blnNeedToCheckCRC = false;
			} else {
				blnNeedToCheckCRC = true;

			}
			;

			if (SmartSocketConnection.IsSocketClose() == true) {
				return null;
			}

			if (blnNeedToCheckCRC == true) {
				if (SmartSocketConnection.CheckCRC(arraybyteBufWithoutHead,
						arraybyteBufWithoutHead.length - 2) == false) {
					arraybyteBufWithoutHead = null;
				}
			}
			;

		} catch (Exception e) {
			// Toast.makeText(getApplicationContext(), e.getMessage(),
			// Toast.LENGTH_SHORT).show();
		}

		return arraybyteBufWithoutHead;
	}

	
	
	
	   public boolean UniversalSwitchControl(byte byteSubnetID,byte byteDeviceID,int intFirstParameter,int intSecondParameter,boolean blnNeedResend,boolean blnNeedToWaitANS)
	    {
	    	boolean blnSuccess=false,blnSent=false;
	    	try
	    	{
	    		   	
	    		int intOP=0xE01C;
	       		short shortLenOfAddtionalBuf;
	       		byte byteI;
	    		byte[] arraybyteBufWithoutAA=null;
	    		
	    		byte[] arrayAddtional =new byte[2];
	    		arrayAddtional[0]=(byte) intFirstParameter;
	    		arrayAddtional[1]=(byte) intSecondParameter;
	    	    		
	    		shortLenOfAddtionalBuf=(short) (arrayAddtional.length);
	    		for(byteI=1;byteI<=SmartSocketConnection.CONST_MAX_TIMES_OF_SEND;byteI++)
	 	    	{
	    			if (SmartSocketConnection.IsSocketClose()==true) 
	    			{
	    				return false;
	    			}
	    			
	    			blnSent=SendUDPBuffer(arrayAddtional, shortLenOfAddtionalBuf, intOP, byteSubnetID, byteDeviceID, false);
	         	    if (blnSent==true)	
	         	    {  
	         	    	if (blnNeedToWaitANS==true)
	         	    	{
	         	    		arraybyteBufWithoutAA=UDPReceive(byteSubnetID, byteDeviceID, intOP,true);
	           	    		if (arraybyteBufWithoutAA==null)
	           	    		{
	           	    			if (blnNeedResend==false)
	           	    			{
	           	    				break;
	           	    			}
	           	    		}
	           	    		else
	           	    		{
	           	    			blnSuccess=true;
	           	    			break;
	           	    		}
	         	    	}
	         	    	else
	         	    	{
	         	    		blnSuccess=true;
	       	    			break;
	         	    	}
	         	    	       	    		
	         	   }
	         	    else
	         	    {
	         	    	break;
	         	    }
	 	    	}
	    		
	    	}catch(Exception e)
	    	{ 
	    		// Toast.makeText(getApplicationContext(), e.getMessage(),
		  		        //  Toast.LENGTH_SHORT).show();	
	    	}
	    	return blnSuccess;
	    }
}
