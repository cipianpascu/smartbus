package com.roka.smarthomeg4.udp_socket;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;

import com.roka.smarthomeg4.business.CommandParameters;

import android.os.Handler;
import android.util.Log;

public class SmartSocketConnection {

	// CRC table
	public static final int[] mbufintCRCTable = { 0x0000, 0x1021, 0x2042,
			0x3063, 0x4084, 0x50a5, 0x60c6, 0x70e7, 0x8108, 0x9129, 0xa14a,
			0xb16b, 0xc18c, 0xd1ad, 0xe1ce, 0xf1ef, 0x1231, 0x0210, 0x3273,
			0x2252, 0x52b5, 0x4294, 0x72f7, 0x62d6, 0x9339, 0x8318, 0xb37b,
			0xa35a, 0xd3bd, 0xc39c, 0xf3ff, 0xe3de, 0x2462, 0x3443, 0x0420,
			0x1401, 0x64e6, 0x74c7, 0x44a4, 0x5485, 0xa56a, 0xb54b, 0x8528,
			0x9509, 0xe5ee, 0xf5cf, 0xc5ac, 0xd58d, 0x3653, 0x2672, 0x1611,
			0x0630, 0x76d7, 0x66f6, 0x5695, 0x46b4, 0xb75b, 0xa77a, 0x9719,
			0x8738, 0xf7df, 0xe7fe, 0xd79d, 0xc7bc, 0x48c4, 0x58e5, 0x6886,
			0x78a7, 0x0840, 0x1861, 0x2802, 0x3823, 0xc9cc, 0xd9ed, 0xe98e,
			0xf9af, 0x8948, 0x9969, 0xa90a, 0xb92b, 0x5af5, 0x4ad4, 0x7ab7,
			0x6a96, 0x1a71, 0x0a50, 0x3a33, 0x2a12, 0xdbfd, 0xcbdc, 0xfbbf,
			0xeb9e, 0x9b79, 0x8b58, 0xbb3b, 0xab1a, 0x6ca6, 0x7c87, 0x4ce4,
			0x5cc5, 0x2c22, 0x3c03, 0x0c60, 0x1c41, 0xedae, 0xfd8f, 0xcdec,
			0xddcd, 0xad2a, 0xbd0b, 0x8d68, 0x9d49, 0x7e97, 0x6eb6, 0x5ed5,
			0x4ef4, 0x3e13, 0x2e32, 0x1e51, 0x0e70, 0xff9f, 0xefbe, 0xdfdd,
			0xcffc, 0xbf1b, 0xaf3a, 0x9f59, 0x8f78, 0x9188, 0x81a9, 0xb1ca,
			0xa1eb, 0xd10c, 0xc12d, 0xf14e, 0xe16f, 0x1080, 0x00a1, 0x30c2,
			0x20e3, 0x5004, 0x4025, 0x7046, 0x6067, 0x83b9, 0x9398, 0xa3fb,
			0xb3da, 0xc33d, 0xd31c, 0xe37f, 0xf35e, 0x02b1, 0x1290, 0x22f3,
			0x32d2, 0x4235, 0x5214, 0x6277, 0x7256, 0xb5ea, 0xa5cb, 0x95a8,
			0x8589, 0xf56e, 0xe54f, 0xd52c, 0xc50d, 0x34e2, 0x24c3, 0x14a0,
			0x0481, 0x7466, 0x6447, 0x5424, 0x4405, 0xa7db, 0xb7fa, 0x8799,
			0x97b8, 0xe75f, 0xf77e, 0xc71d, 0xd73c, 0x26d3, 0x36f2, 0x0691,
			0x16b0, 0x6657, 0x7676, 0x4615, 0x5634, 0xd94c, 0xc96d, 0xf90e,
			0xe92f, 0x99c8, 0x89e9, 0xb98a, 0xa9ab, 0x5844, 0x4865, 0x7806,
			0x6827, 0x18c0, 0x08e1, 0x3882, 0x28a3, 0xcb7d, 0xdb5c, 0xeb3f,
			0xfb1e, 0x8bf9, 0x9bd8, 0xabbb, 0xbb9a, 0x4a75, 0x5a54, 0x6a37,
			0x7a16, 0x0af1, 0x1ad0, 0x2ab3, 0x3a92, 0xfd2e, 0xed0f, 0xdd6c,
			0xcd4d, 0xbdaa, 0xad8b, 0x9de8, 0x8dc9, 0x7c26, 0x6c07, 0x5c64,
			0x4c45, 0x3ca2, 0x2c83, 0x1ce0, 0x0cc1, 0xef1f, 0xff3e, 0xcf5d,
			0xdf7c, 0xaf9b, 0xbfba, 0x8fd9, 0x9ff8, 0x6e17, 0x7e36, 0x4e55,
			0x5e74, 0x2e93, 0x3eb2, 0x0ed1, 0x1ef0 };

	public static final int CONST_UDP_UDP_PORT = 6000;//
	public static final int CONST_MAX_UPD_PACKET_LEN = 2048;//
	public static final int CONST_MAX_TIMES_OF_SEND = 2;//

	public static final short CONST_SELF_SUBNET_ID = (short) 0xBB;// 187.
	public static final short CONST_SELF_DEVICE_ID = (short) 0xBB;// 1787.
	public static final short CONST_SELF_DEVICE_TYPE_H = (short) 0xCC;// 204
	public static final short CONST_SELF_DEVICE_TYPE_L = (short) 0xCC;

	public static final int CONST_TIME_OUT_FOR_TOTAL_WAIT = 4000; // millisecond
	public static final int CONST_TIME_OUT_FOR_TOTAL_WAIT_RS232 = 4000; // millisecond
	public static final int CONST_TIME_OUT_FOR_TOTAL_WAIT_ALBUM = 5000; // millisecond
	public static final int CONST_TIME_OUT_FOR_EACH_WAIT = 1000; // millisecond

	// position of udp buffer
	public static final byte CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS = 25;
	public static final byte CONST_START_PST_OF_ADDITIONAL_DATA_WITHOUT_AA_PACKETS = 9;
	public static final byte CONST_START_PST_OF_LEN_OF_DATA_IN_FULL_PACKETS = 16;

	// command type define
	private static final int CONST_CMD_TYPE_SCENE = 0;
	private static final int CONST_CMD_TYPE_SEQUENCE = 1;
	private static final int CONST_CMD_TYPE_UNIVERSAL_SWITCH = 2;
	private static final int CONST_CMD_TYPE_INVALID = 3;
	private static final int CONST_CMD_TYPE_SINGLE_CH = 4;
	private static final int CONST_CMD_TYPE_BR_SCENE = 5;
	private static final int CONST_CMD_TYPE_BR_CH = 6;
	private static final int CONST_CMD_TYPE_CURTAIN = 7;
	private static final int CONST_CMD_TYPE_TIMER = 8;
	private static final int CONST_CMD_TYPE_GPRS = 9;
	private static final int CONST_CMD_TYPE_PANEL = 10;
	private static final int CONST_CMD_TYPE_security_mode = 11;
	private static final int CONST_CMD_TYPE_security_alarm = 12;
	private static final int CONST_CMD_TYPE_zaudio = 18;
	private static final int CONST_CMD_TYPE_LED = 30;

	public static DatagramSocket moUDPSocket;

	public static boolean IsSocketClose() {
		boolean blnIsClose = false;
		try {
			if (SmartSocketConnection.moUDPSocket == null) {
				blnIsClose = true;
			} else {
				if (SmartSocketConnection.moUDPSocket.isClosed() == true) {
					blnIsClose = true;
				}
			}

		} catch (Exception e) {

		}
		return blnIsClose;
	}

	public static byte[] GetLocalIP() {
		// IP为4个字节
		byte[] ipAddr = new byte[4];
		String strIP;
		try {

			// 通用获得本地IP方法,
			ipAddr = null;
			for (Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf
						.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					// if (!inetAddress.isLoopbackAddress() &&
					// InetAddressUtils.isIPv4Address(inetAddress.getHostAddress()))
					// odler
					if (!inetAddress.isLoopbackAddress()
							&& !inetAddress.isLinkLocalAddress()) {
						ipAddr = inetAddress.getAddress();

						strIP = inetAddress.getHostAddress().toString();// 此方法正确
						Log.v("breakPoint", "正在打印获得的本地IP地址");
						System.out.println("正在打印获得的本地IP地址==" + strIP);
						System.out
								.println("正在打印获得的本地IP地址inetAddress.getHostAddress()=="
										+ inetAddress.getHostAddress());
						System.out.println("inetAddress.getAddress()="
								+ inetAddress.getAddress());
						return ipAddr;
					}
				}
			}
			return ipAddr;

		} catch (Exception e) {

		}
		return ipAddr;

	}

	public static byte[] GetTargetIP(byte[] arraybyteLocalIP) {
		byte[] arraybyteTargetIP = new byte[4];
		byte byteBit;

		try {
			byteBit = (byte) ((arraybyteLocalIP[0] & 0xFF) >> 5);
			if (((byteBit & 0xFF) >= 0) && ((byteBit & 0xFF) <= 3)) // IP type:A
			{
				arraybyteTargetIP[0] = arraybyteLocalIP[0];
				arraybyteTargetIP[1] = (byte) 255;
				arraybyteTargetIP[2] = (byte) 255;
				arraybyteTargetIP[3] = (byte) 255;
			} else if (((byteBit & 0xFF) >= 4) && ((byteBit & 0xFF) <= 5)) // IP
																			// Type:B
			{
				arraybyteTargetIP[0] = arraybyteLocalIP[0];
				arraybyteTargetIP[1] = arraybyteLocalIP[1];
				arraybyteTargetIP[2] = (byte) 255;
				arraybyteTargetIP[3] = (byte) 255;
			} else if (((byteBit & 0xFF) >= 6) && ((byteBit & 0xFF) <= 7)) // IP
																			// Type:C

			{
				arraybyteTargetIP[0] = arraybyteLocalIP[0];
				arraybyteTargetIP[1] = arraybyteLocalIP[1];
				arraybyteTargetIP[2] = arraybyteLocalIP[2];
				arraybyteTargetIP[3] = (byte) 255;
			} else {
				arraybyteTargetIP[0] = (byte) 255;
				arraybyteTargetIP[1] = (byte) 255;
				arraybyteTargetIP[2] = (byte) 255;
				arraybyteTargetIP[3] = (byte) 255;
			}

		} catch (Exception e) {
			// Test

		}

		Log.v("breakPoint", "正在打印目标IP地址");
		System.out.println("获得的目标IP byte[] = " + arraybyteTargetIP);
		return arraybyteTargetIP;

	}

	public static void PackCRC(byte[] arrayBuf, short shortLenOfBuf) {
		try {
			short shortCRC = 0;
			byte bytTMP = 0;
			short shortIndexOfBuf = 0;
			byte byteIndex_Of_CRCTable = 0;
			while (shortLenOfBuf != 0) {
				bytTMP = (byte) (shortCRC >> 8); // >>: right move bit
				shortCRC = (short) (shortCRC << 8); // <<: left move bit
				byteIndex_Of_CRCTable = (byte) (bytTMP ^ arrayBuf[shortIndexOfBuf]);
				shortCRC = (short) (shortCRC ^ SmartSocketConnection.mbufintCRCTable[(byteIndex_Of_CRCTable & 0xFF)]); // ^:
				// xor
				shortIndexOfBuf = (short) (shortIndexOfBuf + 1);
				shortLenOfBuf = (short) (shortLenOfBuf - 1);
			}
			;

			arrayBuf[shortIndexOfBuf] = (byte) (shortCRC >> 8);
			shortIndexOfBuf = (short) (shortIndexOfBuf + 1);
			arrayBuf[shortIndexOfBuf] = (byte) (shortCRC & 0x00FF);

		} catch (Exception e) {
			// Test
			// Toast.makeText(getApplicationContext(), e.getMessage(),
			// Toast.LENGTH_SHORT).show();
		}

	}

	public static String GetCRC(byte[] arrayBuf) {
		String strCRC2byte = "";
		try {
			byte byteH, byteL;
			short shortCRC = 0;
			byte bytTMP = 0;
			short shortIndexOfBuf = 0;
			byte byteIndex_Of_CRCTable = 0;
			int intLenOfBuf = arrayBuf.length;
			while (intLenOfBuf != 0) {
				bytTMP = (byte) (shortCRC >> 8); // >>: right move bit
				shortCRC = (short) (shortCRC << 8); // <<: left move bit
				byteIndex_Of_CRCTable = (byte) (bytTMP ^ arrayBuf[shortIndexOfBuf]);
				shortCRC = (short) (shortCRC ^ SmartSocketConnection.mbufintCRCTable[(byteIndex_Of_CRCTable & 0xFF)]); // ^:
				// xor
				shortIndexOfBuf = (short) (shortIndexOfBuf + 1);
				intLenOfBuf = intLenOfBuf - 1;
			}
			;

			byteH = (byte) (shortCRC >> 8);
			byteL = (byte) (shortCRC & 0x00FF);

			strCRC2byte = Integer.toHexString(shortCRC & 0xFFFF).toUpperCase();
			switch (strCRC2byte.length()) {
			case 1: {
				strCRC2byte = "000" + strCRC2byte;
				break;
			}

			case 2: {
				strCRC2byte = "00" + strCRC2byte;
				break;
			}

			case 3: {
				strCRC2byte = "0" + strCRC2byte;
				break;
			}

			default: {
				break;
			}
			}

		} catch (Exception e) {
			// Toast.makeText(getApplicationContext(), e.getMessage(),
			// Toast.LENGTH_SHORT).show();
		}
		return strCRC2byte;
	}

	/*
	 * 利用CRC校验码判断UDP包是否正确 Check the UDP packets is correct or not by checking
	 * CRC 检查的UDP数据包是通过检查CRC正确与否
	 */
	public static boolean CheckCRC(byte[] arrayBuf, int intlength) {
		boolean blnIsCorrenct = false;

		try {
			short shortCRC = 0;
			byte bytTMP = 0;
			short shortIndexOfBuf = 0;
			byte byteIndex_Of_CRCTable = 0;

			if (IsSocketClose() == true) {
				return false;
			}

			while (intlength != 0) {
				bytTMP = (byte) (shortCRC >> 8); // >>: right move bit
				shortCRC = (short) (shortCRC << 8); // <<: left move bit
				byteIndex_Of_CRCTable = (byte) (bytTMP ^ arrayBuf[shortIndexOfBuf]);
				shortCRC = (short) (shortCRC ^ SmartSocketConnection.mbufintCRCTable[(byteIndex_Of_CRCTable & 0xFF)]); // ^:
				// xor
				shortIndexOfBuf = (short) (shortIndexOfBuf + 1);
				intlength = (short) (intlength - 1);
			}
			;

			if (arrayBuf[shortIndexOfBuf] == (shortCRC >> 8)
					&& arrayBuf[shortIndexOfBuf + 1] == (short) (shortCRC & 0xFF)) {
				blnIsCorrenct = true;
			} else {
				blnIsCorrenct = false;
			}
			;

		} catch (Exception e) {
			// Test
			// Toast.makeText(getApplicationContext(), e.getMessage(),
			// Toast.LENGTH_SHORT).show();
		}

		return blnIsCorrenct;

	}

	public static boolean SendMutilCommands(
			ArrayList<CommandParameters> arrayCmdsPara, boolean blnNeedResend,
			boolean blnNeedWaitToAnswer, boolean blnShowProgressDialog,
			Handler oHandler) {
		int intI;
		boolean blnSuccess = false, blnTmpResult = false;
		try {
			for (intI = 0; intI < arrayCmdsPara.size(); intI++) {
				if (IsSocketClose() == true) {
					// return false;
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
							SmartSocketConnection.moUDPSocket = new DatagramSocket(
									null);
							SmartSocketConnection.moUDPSocket
									.setReuseAddress(true);
							SmartSocketConnection.moUDPSocket
									.setBroadcast(true);// ةèضأ±êض¾£¬T
														// OR E
														// OR
							// R¹م²¥ت‎¾ف±¨
							// شع2.2×َسز°و±¾²»ذذ،£شع4.0°و±¾ةد؟ةذذ
							SmartSocketConnection.moUDPSocket
									.bind(new InetSocketAddress(
											SmartSocketConnection.CONST_UDP_UDP_PORT));
						}

					} catch (SocketException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return false;

						// Toast.makeText(getApplicationContext(),
						// e.getMessage(),
						// Toast.LENGTH_LONG).show();
					}

				}

				switch (arrayCmdsPara.get(intI).CommandTypeID) {
				case CONST_CMD_TYPE_SCENE: {
					// blnTmpResult=SceneControl(arrayCmdsPara[intI].SubnetID,arrayCmdsPara[intI].DeviceID,arrayCmdsPara[intI].FirstParameter,arrayCmdsPara[intI].SecondParameter,blnNeedResend,blnNeedWaitToAnswer);
					// if (blnTmpResult == false) {
					// return false;
					// }
					;
					break;
				}

				case CONST_CMD_TYPE_SEQUENCE: {
					// blnTmpResult=SequenceControl(arrayCmdsPara[intI].SubnetID,arrayCmdsPara[intI].DeviceID,arrayCmdsPara[intI].FirstParameter,arrayCmdsPara[intI].SecondParameter,blnNeedResend,blnNeedWaitToAnswer);
					// if (blnTmpResult == false) {
					// return false;
					// }
					;
					break;
				}

				case CONST_CMD_TYPE_UNIVERSAL_SWITCH: {
					blnTmpResult = new LightSocketConnection()
							.UniversalSwitchControl(
									arrayCmdsPara.get(intI).SubnetID,
									arrayCmdsPara.get(intI).DeviceID,
									arrayCmdsPara.get(intI).FirstParameter,
									arrayCmdsPara.get(intI).SecondParameter,
									blnNeedResend, blnNeedWaitToAnswer);
					if (blnTmpResult == false) {
						return false;
					}
					;
					break;
				}

				case CONST_CMD_TYPE_SINGLE_CH: {
					blnTmpResult = new LightSocketConnection()
							.SingleChannelControl(
									arrayCmdsPara.get(intI).SubnetID,
									arrayCmdsPara.get(intI).DeviceID,
									arrayCmdsPara.get(intI).FirstParameter,
									arrayCmdsPara.get(intI).SecondParameter,
									arrayCmdsPara.get(intI).ThirdParameter,
									blnNeedResend, blnNeedWaitToAnswer);
					if (blnTmpResult == false) {
						return false;
					}
					break;
				}

				case CONST_CMD_TYPE_BR_SCENE: {
					if (arrayCmdsPara.get(intI).FirstParameter != 255) {
						arrayCmdsPara.get(intI).FirstParameter = 255;
					}
					// blnTmpResult=SceneControl(arrayCmdsPara[intI].SubnetID,arrayCmdsPara[intI].DeviceID,arrayCmdsPara[intI].FirstParameter,arrayCmdsPara[intI].SecondParameter,blnNeedResend,blnNeedWaitToAnswer);
					// if (blnTmpResult == false) {
					// return false;
					// }
					break;
				}

				case CONST_CMD_TYPE_BR_CH: {
					if (arrayCmdsPara.get(intI).FirstParameter != 255) {
						arrayCmdsPara.get(intI).FirstParameter = 255;
					}
					blnTmpResult = new LightSocketConnection()
							.SingleChannelControl(
									arrayCmdsPara.get(intI).SubnetID,
									arrayCmdsPara.get(intI).DeviceID,
									arrayCmdsPara.get(intI).FirstParameter,
									arrayCmdsPara.get(intI).SecondParameter,
									arrayCmdsPara.get(intI).ThirdParameter,
									blnNeedResend, blnNeedWaitToAnswer);
					if (blnTmpResult == false) {
						return false;
					}
					break;

				}

				case CONST_CMD_TYPE_CURTAIN: {
					// blnTmpResult=CurtainControl(arrayCmdsPara[intI].SubnetID,arrayCmdsPara[intI].DeviceID,arrayCmdsPara[intI].FirstParameter,arrayCmdsPara[intI].SecondParameter,blnNeedResend,blnNeedWaitToAnswer);
					// if (blnTmpResult == false) {
					// return false;
					// }
					break;
				}

				case CONST_CMD_TYPE_TIMER: {
					// blnTmpResult=TimerControl(arrayCmdsPara[intI].SubnetID,arrayCmdsPara[intI].DeviceID,arrayCmdsPara[intI].FirstParameter,arrayCmdsPara[intI].SecondParameter,blnNeedResend,blnNeedWaitToAnswer);
					// if (blnTmpResult == false) {
					// return false;
					// }
					break;
				}

				case CONST_CMD_TYPE_GPRS: {
					// blnTmpResult=GPRSControl(arrayCmdsPara[intI].SubnetID,arrayCmdsPara[intI].DeviceID,arrayCmdsPara[intI].FirstParameter,arrayCmdsPara[intI].SecondParameter,blnNeedResend,blnNeedWaitToAnswer);
					// if (blnTmpResult == false) {
					// return false;
					// }
					break;
				}

				case CONST_CMD_TYPE_PANEL: {
					blnTmpResult = new HVACUDPSocketConnection().panelControl(
							arrayCmdsPara.get(intI).SubnetID,
							arrayCmdsPara.get(intI).DeviceID,
							arrayCmdsPara.get(intI).FirstParameter,
							arrayCmdsPara.get(intI).SecondParameter,
							blnNeedResend, blnNeedWaitToAnswer);
					if (blnTmpResult == false) {
						return false;
					}
					break;
				}

				case CONST_CMD_TYPE_zaudio: {
					blnTmpResult = new ZAudioUDBSocket().audioControllCommand(
							(byte) arrayCmdsPara.get(intI).SubnetID,
							(byte) arrayCmdsPara.get(intI).DeviceID,
							(byte) arrayCmdsPara.get(intI).FirstParameter,
							(byte) arrayCmdsPara.get(intI).SecondParameter,
							(byte) arrayCmdsPara.get(intI).ThirdParameter,
							(byte) 0);
					if (blnTmpResult == false) {
						return false;
					}
					break;
				}
				default: // invalid
				{
					arrayCmdsPara.get(intI).DelayMillisecondAfterSend = 0;
					break;
				}

				}

				if (blnShowProgressDialog == true
						&& intI <= (arrayCmdsPara.size() - 1)) {

					oHandler.sendEmptyMessage(0);
				}

				Delay(arrayCmdsPara.get(intI).DelayMillisecondAfterSend);

			}
			if (blnShowProgressDialog == true) {

				oHandler.sendEmptyMessage(1);
			}

			blnSuccess = true;

		} catch (Exception e) {
			e.printStackTrace();
			// Toast.makeText(getApplicationContext(), e.getMessage(),
			// Toast.LENGTH_SHORT).show();

		}

		return blnSuccess;
	}

	public static boolean SendMutilCommands(
			ArrayList<CommandParameters> arrayCmdsPara, boolean blnNeedResend,
			boolean blnNeedWaitToAnswer, boolean blnShowProgressDialog,
			Handler oHandler, int value) {
		int intI;
		boolean blnSuccess = false, blnTmpResult = false;
		try {
			for (intI = 0; intI < arrayCmdsPara.size(); intI++) {
				if (IsSocketClose() == true) {
					// return false;
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
							SmartSocketConnection.moUDPSocket = new DatagramSocket(
									null);
							SmartSocketConnection.moUDPSocket
									.setReuseAddress(true);
							SmartSocketConnection.moUDPSocket
									.setBroadcast(true);// ةèضأ±êض¾£¬T
														// OR E
														// OR
							// R¹م²¥ت‎¾ف±¨
							// شع2.2×َسز°و±¾²»ذذ،£شع4.0°و±¾ةد؟ةذذ
							SmartSocketConnection.moUDPSocket
									.bind(new InetSocketAddress(
											SmartSocketConnection.CONST_UDP_UDP_PORT));
						}

					} catch (SocketException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return false;

						// Toast.makeText(getApplicationContext(),
						// e.getMessage(),
						// Toast.LENGTH_LONG).show();
					}

				}

				switch (arrayCmdsPara.get(intI).CommandTypeID) {
				case CONST_CMD_TYPE_SINGLE_CH: {
					blnTmpResult = new LightSocketConnection()
							.SingleChannelControl(
									arrayCmdsPara.get(intI).SubnetID,
									arrayCmdsPara.get(intI).DeviceID,
									arrayCmdsPara.get(intI).FirstParameter,
									value/*
											 * arrayCmdsPara.get(intI).
											 * SecondParameter
											 */,
									arrayCmdsPara.get(intI).ThirdParameter,
									blnNeedResend, blnNeedWaitToAnswer);
					if (blnTmpResult == false) {
						return false;
					}
					break;
				}
				case CONST_CMD_TYPE_BR_CH: {
					if (arrayCmdsPara.get(intI).FirstParameter != 255) {
						arrayCmdsPara.get(intI).FirstParameter = 255;
					}
					blnTmpResult = new LightSocketConnection()
							.SingleChannelControl(
									arrayCmdsPara.get(intI).SubnetID,
									arrayCmdsPara.get(intI).DeviceID,
									arrayCmdsPara.get(intI).FirstParameter,
									value/*
											 * arrayCmdsPara.get(intI).
											 * SecondParameter
											 */,
									arrayCmdsPara.get(intI).ThirdParameter,
									blnNeedResend, blnNeedWaitToAnswer);
					if (blnTmpResult == false) {
						return false;
					}
					break;

				}
				

				case CONST_CMD_TYPE_PANEL: {
					blnTmpResult = new HVACUDPSocketConnection().panelControl(
							arrayCmdsPara.get(intI).SubnetID,
							arrayCmdsPara.get(intI).DeviceID,
							arrayCmdsPara.get(intI).FirstParameter,
							value/*arrayCmdsPara.get(intI).SecondParameter*/,
							blnNeedResend, blnNeedWaitToAnswer);
					if (blnTmpResult == false) {
						return false;
					}
					break;
				}
				default: // invalid
				{
					arrayCmdsPara.get(intI).DelayMillisecondAfterSend = 0;
					break;
				}

				}

				if (blnShowProgressDialog == true
						&& intI <= (arrayCmdsPara.size() - 1)) {

					oHandler.sendEmptyMessage(0);
				}

				Delay(arrayCmdsPara.get(intI).DelayMillisecondAfterSend);

			}
			if (blnShowProgressDialog == true) {

				oHandler.sendEmptyMessage(1);
			}

			blnSuccess = true;

		} catch (Exception e) {
			e.printStackTrace();
			// Toast.makeText(getApplicationContext(), e.getMessage(),
			// Toast.LENGTH_SHORT).show();

		}

		return blnSuccess;
	}

	private static void Delay(int intMillisSecond) {
		try {
			if (intMillisSecond < CONST.CONST_MIN_DELAY_MILLISECONDS) {
				intMillisSecond = CONST.CONST_MIN_DELAY_MILLISECONDS;
			}

			if (intMillisSecond > 0) {
				Thread.sleep((intMillisSecond));
			}

		} catch (InterruptedException e) {
			// Toast.makeText(getApplicationContext(), e.getMessage(),
			// Toast.LENGTH_SHORT).show();
		}
	}
}
