package com.roka.smarthomeg4.udp_socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Calendar;
import java.util.Enumeration;

import com.roka.smarthomeg4.business.CommandParameters;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;


public class udp_socket extends Activity {

	/* CRCtable */
	private static final int[] mbufintCRCTable ={
		0x0000, 0x1021, 0x2042, 0x3063, 0x4084, 0x50a5, 0x60c6, 0x70e7,
		0x8108, 0x9129, 0xa14a, 0xb16b, 0xc18c, 0xd1ad, 0xe1ce, 0xf1ef,
		0x1231, 0x0210, 0x3273, 0x2252, 0x52b5, 0x4294, 0x72f7, 0x62d6,
		0x9339, 0x8318, 0xb37b, 0xa35a, 0xd3bd, 0xc39c, 0xf3ff, 0xe3de,
		0x2462, 0x3443, 0x0420, 0x1401, 0x64e6, 0x74c7, 0x44a4, 0x5485,
		0xa56a, 0xb54b, 0x8528, 0x9509, 0xe5ee, 0xf5cf, 0xc5ac, 0xd58d,
		0x3653, 0x2672, 0x1611, 0x0630, 0x76d7, 0x66f6, 0x5695, 0x46b4,
		0xb75b, 0xa77a, 0x9719, 0x8738, 0xf7df, 0xe7fe, 0xd79d, 0xc7bc,
		0x48c4, 0x58e5, 0x6886, 0x78a7, 0x0840, 0x1861, 0x2802, 0x3823,
		0xc9cc, 0xd9ed, 0xe98e, 0xf9af, 0x8948, 0x9969, 0xa90a, 0xb92b,
		0x5af5, 0x4ad4, 0x7ab7, 0x6a96, 0x1a71, 0x0a50, 0x3a33, 0x2a12,
		0xdbfd, 0xcbdc, 0xfbbf, 0xeb9e, 0x9b79, 0x8b58, 0xbb3b, 0xab1a,
		0x6ca6, 0x7c87, 0x4ce4, 0x5cc5, 0x2c22, 0x3c03, 0x0c60, 0x1c41,
		0xedae, 0xfd8f, 0xcdec, 0xddcd, 0xad2a, 0xbd0b, 0x8d68, 0x9d49,
		0x7e97, 0x6eb6, 0x5ed5, 0x4ef4, 0x3e13, 0x2e32, 0x1e51, 0x0e70,
		0xff9f, 0xefbe, 0xdfdd, 0xcffc, 0xbf1b, 0xaf3a, 0x9f59, 0x8f78,
		0x9188, 0x81a9, 0xb1ca, 0xa1eb, 0xd10c, 0xc12d, 0xf14e, 0xe16f,
		0x1080, 0x00a1, 0x30c2, 0x20e3, 0x5004, 0x4025, 0x7046, 0x6067,
		0x83b9, 0x9398, 0xa3fb, 0xb3da, 0xc33d, 0xd31c, 0xe37f, 0xf35e,
		0x02b1, 0x1290, 0x22f3, 0x32d2, 0x4235, 0x5214, 0x6277, 0x7256,
		0xb5ea, 0xa5cb, 0x95a8, 0x8589, 0xf56e, 0xe54f, 0xd52c, 0xc50d,
		0x34e2, 0x24c3, 0x14a0, 0x0481, 0x7466, 0x6447, 0x5424, 0x4405,
		0xa7db, 0xb7fa, 0x8799, 0x97b8, 0xe75f, 0xf77e, 0xc71d, 0xd73c,
		0x26d3, 0x36f2, 0x0691, 0x16b0, 0x6657, 0x7676, 0x4615, 0x5634,
		0xd94c, 0xc96d, 0xf90e, 0xe92f, 0x99c8, 0x89e9, 0xb98a, 0xa9ab,
		0x5844, 0x4865, 0x7806, 0x6827, 0x18c0, 0x08e1, 0x3882, 0x28a3,
		0xcb7d, 0xdb5c, 0xeb3f, 0xfb1e, 0x8bf9, 0x9bd8, 0xabbb, 0xbb9a,
		0x4a75, 0x5a54, 0x6a37, 0x7a16, 0x0af1, 0x1ad0, 0x2ab3, 0x3a92,
		0xfd2e, 0xed0f, 0xdd6c, 0xcd4d, 0xbdaa, 0xad8b, 0x9de8, 0x8dc9,
		0x7c26, 0x6c07, 0x5c64, 0x4c45, 0x3ca2, 0x2c83, 0x1ce0, 0x0cc1,
		0xef1f, 0xff3e, 0xcf5d, 0xdf7c, 0xaf9b, 0xbfba, 0x8fd9, 0x9ff8,
		0x6e17, 0x7e36, 0x4e55, 0x5e74, 0x2e93, 0x3eb2, 0x0ed1, 0x1ef0
	};
		
	//CONST 
	private static final int CONST_UDP_UDP_PORT=6000;
	private static final int CONST_MAX_UPD_PACKET_LEN=1024;
	private static final int CONST_MAX_TIMES_OF_SEND=2;
		
	
	private static final short CONST_SELF_SUBNET_ID=(short) 0xBB;
	private static final short CONST_SELF_DEVICE_ID=(short) 0xBB;
	private static final short CONST_SELF_DEVICE_TYPE_H=(short) 0xCC;
	private static final short CONST_SELF_DEVICE_TYPE_L=(short) 0xCC;
    
	private static final int CONST_TIME_OUT_FOR_TOTAL_WAIT=4000; //millisecond
	private static final int CONST_TIME_OUT_FOR_TOTAL_WAIT_RS232=4000; //millisecond
	private static final int CONST_TIME_OUT_FOR_TOTAL_WAIT_ALBUM=5000; //millisecond
	private static final int CONST_TIME_OUT_FOR_EACH_WAIT=1000; //millisecond
	
	//msg
	private static final byte CONST_MSG_TYPE_SHOWING_PROGRESS=0;
	private static final byte CONST_MSG_TYPE_CLOSE_PAGE=100;

	
    //command type define
    private static final int CONST_CMD_TYPE_SCENE=0;
    private static final int CONST_CMD_TYPE_SEQUENCE=1;
    private static final int CONST_CMD_TYPE_UNIVERSAL_SWITCH=2;
    //private static final int CONST_CMD_TYPE_INVALID=3;
    private static final int CONST_CMD_TYPE_SINGLE_CH=4;
    private static final int CONST_CMD_TYPE_BR_SCENE=5;
    private static final int CONST_CMD_TYPE_BR_CH=6;
    private static final int CONST_CMD_TYPE_CURTAIN=7;
    private static final int CONST_CMD_TYPE_TIMER=8;
    private static final int CONST_CMD_TYPE_GPRS=9;
    private static final int CONST_CMD_TYPE_PANEL=10;
       
    
    
    private DatagramSocket moUDPSocket;
    
//    private Handler moHandler;
	
	
	/*
	 * 
	 */
	public udp_socket(DatagramSocket oUDPSocket) {
		// TODO Auto-generated constructor stub
		moUDPSocket=oUDPSocket;
//		CreateUDPSocket();
	}
	
	
	

	public DatagramSocket GetUDPSocket()
	{
		return moUDPSocket;
	}
	
	@Override
    public void onDestroy() 
	{
		try
		{
			if (moUDPSocket!=null)
			{
				if (moUDPSocket.isClosed()==false)
				{
					moUDPSocket.close();
				}
				moUDPSocket=null;
			}
		    super.onDestroy();
			 
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), e.getMessage(),
		  		       //   Toast.LENGTH_SHORT).show();	
		}
		 
		
	}

	public byte[] GetLocalIP()
	{	
		byte[] ipAddr = new byte[4];
		    
		try 
		{ 
			/*
		    InetAddress addr = InetAddress.getLocalHost(); 
	        ipAddr = addr.getAddress(); 
	        strIP=ipAddr[0]+"." +ipAddr[1]+"."+ipAddr[2]+"."+ipAddr[3];
	      	Log.d(CONST_CLASS_NAME, "Get Local IP"+strIP);
	      	
	      	//for testing
	      	/*ipAddr[0]=(byte) 192;
	      	ipAddr[1]=(byte) 168;
	      	ipAddr[2]=0;
	      	ipAddr[3]=(byte) 198;
	      	*/
			
			ipAddr=null;
			for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) 
			{
	            NetworkInterface intf = en.nextElement();
	            for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();)
	            {
	                InetAddress inetAddress = enumIpAddr.nextElement();
	                if (!inetAddress.isLoopbackAddress()) 
	                {
	                	ipAddr= inetAddress.getAddress();
	                	return ipAddr;
	                }
	            }
	        }
		    return ipAddr;
		      
		    
		}
	    catch (Exception e) 
		{ 
	    	//Toast.makeText(getApplicationContext(), e.getMessage(),
	  		         // Toast.LENGTH_SHORT).show();	
		}
		return ipAddr;
		
	}

	/*
	 * 
	 */
	public byte[] GetTargetIP(byte[] arraybyteLocalIP)
	{	
		byte[] arraybyteTargetIP= new byte[4];
		byte byteBit;
	       
		try 
		{ 	
			byteBit=(byte) ((arraybyteLocalIP[0] & 0xFF)>>5);
			if (((byteBit & 0xFF)>=0) && ((byteBit & 0xFF)<=3)) //IP type:A
			{
				arraybyteTargetIP[0]=arraybyteLocalIP[0];
				arraybyteTargetIP[1]=(byte) 255;
				arraybyteTargetIP[2]=(byte) 255;
				arraybyteTargetIP[3]=(byte) 255;	
			}
			else if (((byteBit & 0xFF)>=4) && ((byteBit & 0xFF)<=5)) //IP Type:B
			{
				arraybyteTargetIP[0]=arraybyteLocalIP[0];
				arraybyteTargetIP[1]=arraybyteLocalIP[1];
				arraybyteTargetIP[2]=(byte) 255;
				arraybyteTargetIP[3]=(byte) 255;	
			}
			else if (((byteBit & 0xFF)>=6) && ((byteBit & 0xFF)<=7)) //IP Type:C
			{
				arraybyteTargetIP[0]=arraybyteLocalIP[0];
				arraybyteTargetIP[1]=arraybyteLocalIP[1];
				arraybyteTargetIP[2]=arraybyteLocalIP[2];
				arraybyteTargetIP[3]=(byte) 255;	
			}
			else
			{
				arraybyteTargetIP[0]=(byte) 255;
				arraybyteTargetIP[1]=(byte) 255;
				arraybyteTargetIP[2]=(byte) 255;
        		arraybyteTargetIP[3]=(byte) 255;	
			}
				
   	      
		    
		}
	    catch (Exception e) 
		{ 
			 //Toast.makeText(getApplicationContext(), e.getMessage(),
	  		         // Toast.LENGTH_SHORT).show();	
		}
	 
	   
		return arraybyteTargetIP;
		
	}
	
	/*
	 * Get 2 CRC bytes 
	 */
	protected void PackCRC(byte[] arrayBuf,short shortLenOfBuf)
	{
		try
		{
	   	    short shortCRC=0;
	   	    byte bytTMP=0;
	   	    short shortIndexOfBuf=0;
	   	    byte byteIndex_Of_CRCTable=0;
			while (shortLenOfBuf!=0) 
			{
				bytTMP= (byte) (shortCRC >> 8) ;    //>>: right move bit                              
				shortCRC=(short) (shortCRC << 8);   //<<: left  move bit   
				byteIndex_Of_CRCTable=(byte) (bytTMP ^ arrayBuf[shortIndexOfBuf]);
				shortCRC=(short) (shortCRC ^ mbufintCRCTable[(byteIndex_Of_CRCTable & 0xFF)]);   //^: xor
				shortIndexOfBuf=(short) (shortIndexOfBuf+1);
			    shortLenOfBuf=(short) (shortLenOfBuf-1);
			};
			
			arrayBuf[shortIndexOfBuf]=(byte) (shortCRC >> 8);
			shortIndexOfBuf=(short) (shortIndexOfBuf+1);
			arrayBuf[shortIndexOfBuf]=(byte) (shortCRC & 0x00FF);
			
				
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), e.getMessage(),
	  		         // Toast.LENGTH_SHORT).show();	
		}
		
	}
	
	public static String GetCRC(byte[] arrayBuf)
	{
		String strCRC2byte="";
		try
		{
			byte byteH,byteL;
	   	    short shortCRC=0;
	   	    byte bytTMP=0;
	   	    short shortIndexOfBuf=0;
	   	    byte byteIndex_Of_CRCTable=0;
	   	    int intLenOfBuf= arrayBuf.length;
			while (intLenOfBuf!=0) 
			{
				bytTMP= (byte) (shortCRC >> 8) ;    //>>: right move bit                              
				shortCRC=(short) (shortCRC << 8);   //<<: left  move bit   
				byteIndex_Of_CRCTable=(byte) (bytTMP ^ arrayBuf[shortIndexOfBuf]);
				shortCRC=(short) (shortCRC ^ mbufintCRCTable[(byteIndex_Of_CRCTable & 0xFF)]);   //^: xor
				shortIndexOfBuf=(short) (shortIndexOfBuf+1);
				intLenOfBuf=intLenOfBuf-1;
			};
			
			
			byteH=(byte) (shortCRC >> 8);
			byteL=(byte) (shortCRC & 0x00FF);
			
			strCRC2byte=Integer.toHexString(shortCRC & 0xFFFF).toUpperCase();
			switch(strCRC2byte.length())
			{
				case 1:
				{
					strCRC2byte="000"+strCRC2byte;
					break;
				}
				
				case 2:
				{
					strCRC2byte="00"+strCRC2byte;
					break;
				}
				
				case 3:
				{
					strCRC2byte="0"+strCRC2byte;
					break;
				}
				
				default:
				{
					break;
				}
			}
			
							
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), e.getMessage(),
	  		         // Toast.LENGTH_SHORT).show();	
		}
		return strCRC2byte;
	}
	
	/*
	 * Check the UDP packets is correct or not by checking CRC 
	 */
	public boolean CheckCRC(byte[] arrayBuf,int intlength)
	{
		boolean blnIsCorrenct=false;
		
		try
		{
			short shortCRC=0;
	   	    byte bytTMP=0;
	   	    short shortIndexOfBuf=0;
	   	    byte byteIndex_Of_CRCTable=0;
	        		
			if (IsSocketClose()==true) 
			{
				return false;
			}
			
			while (intlength!=0) 
			{
				bytTMP= (byte) (shortCRC >> 8) ;    //>>: right move bit                              
				shortCRC=(short) (shortCRC << 8);   //<<: left  move bit   
				byteIndex_Of_CRCTable=(byte) (bytTMP ^ arrayBuf[shortIndexOfBuf]);
				shortCRC=(short) (shortCRC ^ mbufintCRCTable[(byteIndex_Of_CRCTable & 0xFF)]);   //^: xor
				shortIndexOfBuf=(short) (shortIndexOfBuf+1);
			    intlength=(short) (intlength-1);
			};
			
			if (arrayBuf[shortIndexOfBuf]==(shortCRC >> 8) && arrayBuf[shortIndexOfBuf+1]==(short)(shortCRC & 0xFF))
		    { 
			    blnIsCorrenct=true;
		    }
		    else
		    {
		    	blnIsCorrenct=false;
		    };
		    
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), e.getMessage(),
	  		     //     Toast.LENGTH_SHORT).show();	
		}
		    
	    return blnIsCorrenct;
		
	}
	
	private void Delay(int intMillisSecond)
	{
		try
		{
			if (intMillisSecond<CONST.CONST_MIN_DELAY_MILLISECONDS)
			{
				intMillisSecond=CONST.CONST_MIN_DELAY_MILLISECONDS;
			}
		
			if (intMillisSecond>0)
			{
				Thread.sleep((intMillisSecond));
			}
			
		}catch(InterruptedException   e)
		{
			//Toast.makeText(getApplicationContext(), e.getMessage(),
	  		         // Toast.LENGTH_SHORT).show();	
		} 
	}
	
	public boolean SendMutilCommands(CommandParameters[] arrayCmdsPara,boolean blnNeedResend,boolean blnNeedWaitToAnswer,boolean blnShowProgressDialog,Handler oHandler)
	{
		int intI;
		boolean blnSuccess=false,blnTmpResult=false;
		try
		{   
			for(intI=0;intI<arrayCmdsPara.length;intI++)
			{
				if (IsSocketClose()==true) 
				{
					return false;
				}
				
				switch(arrayCmdsPara[intI].CommandTypeID)
				{
					case CONST_CMD_TYPE_SCENE:
					{
						blnTmpResult=SceneControl(arrayCmdsPara[intI].SubnetID,arrayCmdsPara[intI].DeviceID,arrayCmdsPara[intI].FirstParameter,arrayCmdsPara[intI].SecondParameter,blnNeedResend,blnNeedWaitToAnswer);
					    if (blnTmpResult==false)
					    {
					    	return false;
					    };
						break;
					}
					
					case CONST_CMD_TYPE_SEQUENCE:
					{
						blnTmpResult=SequenceControl(arrayCmdsPara[intI].SubnetID,arrayCmdsPara[intI].DeviceID,arrayCmdsPara[intI].FirstParameter,arrayCmdsPara[intI].SecondParameter,blnNeedResend,blnNeedWaitToAnswer);
						if (blnTmpResult==false)
					    {
					    	return false;
					    };
						break;
					}
					
					case CONST_CMD_TYPE_UNIVERSAL_SWITCH:
					{
						blnTmpResult=UniversalSwitchControl(arrayCmdsPara[intI].SubnetID,arrayCmdsPara[intI].DeviceID,arrayCmdsPara[intI].FirstParameter,arrayCmdsPara[intI].SecondParameter,blnNeedResend,blnNeedWaitToAnswer);
						if (blnTmpResult==false)
					    {
					    	return false;
					    };
						break;
					}
					
					case CONST_CMD_TYPE_SINGLE_CH:
					{
						blnTmpResult=SingleChannelControl(arrayCmdsPara[intI].SubnetID,arrayCmdsPara[intI].DeviceID,arrayCmdsPara[intI].FirstParameter,arrayCmdsPara[intI].SecondParameter, arrayCmdsPara[intI].ThirdParameter,blnNeedResend,blnNeedWaitToAnswer);
						if (blnTmpResult==false)
					    {
					    	return false;
					    }
						break;
					}
							
					case CONST_CMD_TYPE_BR_SCENE:
					{
						if (arrayCmdsPara[intI].FirstParameter!=255)
						{
							arrayCmdsPara[intI].FirstParameter=255;
						}
						blnTmpResult=SceneControl(arrayCmdsPara[intI].SubnetID,arrayCmdsPara[intI].DeviceID,arrayCmdsPara[intI].FirstParameter,arrayCmdsPara[intI].SecondParameter,blnNeedResend,blnNeedWaitToAnswer);
						if (blnTmpResult==false)
					    {
					    	return false;
					    }
						break;
					}
					
					case CONST_CMD_TYPE_BR_CH:
					{
						if (arrayCmdsPara[intI].FirstParameter!=255)
						{
							arrayCmdsPara[intI].FirstParameter=255;
						}
						blnTmpResult=SingleChannelControl(arrayCmdsPara[intI].SubnetID,arrayCmdsPara[intI].DeviceID,arrayCmdsPara[intI].FirstParameter,arrayCmdsPara[intI].SecondParameter, arrayCmdsPara[intI].ThirdParameter,blnNeedResend,blnNeedWaitToAnswer);
						if (blnTmpResult==false)
					    {
					    	return false;
					    }
						break;

					}
					
					case CONST_CMD_TYPE_CURTAIN:
					{
						blnTmpResult=CurtainControl(arrayCmdsPara[intI].SubnetID,arrayCmdsPara[intI].DeviceID,arrayCmdsPara[intI].FirstParameter,arrayCmdsPara[intI].SecondParameter,blnNeedResend,blnNeedWaitToAnswer);
						if (blnTmpResult==false)
					    {
					    	return false;
					    }
						break;
					}
					
					case CONST_CMD_TYPE_TIMER:
					{
						blnTmpResult=TimerControl(arrayCmdsPara[intI].SubnetID,arrayCmdsPara[intI].DeviceID,arrayCmdsPara[intI].FirstParameter,arrayCmdsPara[intI].SecondParameter,blnNeedResend,blnNeedWaitToAnswer);
						if (blnTmpResult==false)
					    {
					    	return false;
					    }
						break;
					}
					
					case CONST_CMD_TYPE_GPRS:
					{
						blnTmpResult=GPRSControl(arrayCmdsPara[intI].SubnetID,arrayCmdsPara[intI].DeviceID,arrayCmdsPara[intI].FirstParameter,arrayCmdsPara[intI].SecondParameter,blnNeedResend,blnNeedWaitToAnswer);
						if (blnTmpResult==false)
					    {
					    	return false;
					    }
						break;
					}
					
					case CONST_CMD_TYPE_PANEL:
					{
						blnTmpResult=PanelControl(arrayCmdsPara[intI].SubnetID,arrayCmdsPara[intI].DeviceID,arrayCmdsPara[intI].FirstParameter,arrayCmdsPara[intI].SecondParameter,blnNeedResend,blnNeedWaitToAnswer);
						if (blnTmpResult==false)
					    {
					    	return false;
					    }
						break;
					}
						
					default:  //invalid
					{
						arrayCmdsPara[intI].DelayMillisecondAfterSend=0;
						break;
					}
								
				}
				
				if (blnShowProgressDialog==true)
		    	{
		    		Message oMsg= Message.obtain();
		    		oMsg.what=CONST_MSG_TYPE_SHOWING_PROGRESS;
		    		oMsg.obj = intI+1;
		    		oHandler.sendMessage(oMsg);
		    	}
				
				Delay(arrayCmdsPara[intI].DelayMillisecondAfterSend);
					
				
			}
		
			blnSuccess=true;
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), e.getMessage(),
	  		        //  Toast.LENGTH_SHORT).show();

		}

		return blnSuccess;
	}
	
	/*
	 * Send buffer by UDP Socket
	*/
	public boolean SendUDPBuffer(byte[] arrayAddtional,short shortLenOfAddtionalBuf,int intOP, byte byteObjSubnetID,byte byteObjDeviceID,boolean blnBigPack)
	{
		boolean blnSuccess =false;
		boolean blnNeedShowIPError=false;
		String strLocalIP;
		short shortLenOfBaseData,shortI,shortLenOfPackCRCBufWithAA,shortLenOfPackCRCBufWithoutAA,shortLenOfSend;
		DatagramPacket oDataPacket;
		byte[] bytebufSend;
		byte[] arraybyteLocalIP=new byte[4];
		byte[] arraybyteTargetIP=new byte[4];
		//byte[] arraybyteBufRec = new byte[CONST_MAX_UPD_PACKET_LEN];
		//byte[] arraybyteRec;
		
		//DatagramPacket oPacket = new DatagramPacket(arraybyteBufRec,arraybyteBufRec.length);
									
		try
		{
			//bytebufRec=new byte[300];       
			
			arraybyteLocalIP=GetLocalIP();
						
			if (arraybyteLocalIP  != null)
			{
			     
			}
			else
			{
				blnNeedShowIPError=true;
				
				
				blnSuccess=false;
				return blnSuccess;
				
			};
						
			arraybyteTargetIP=GetTargetIP(arraybyteLocalIP);
			
			shortLenOfBaseData=11;
			shortLenOfPackCRCBufWithoutAA=(short) (shortLenOfBaseData+shortLenOfAddtionalBuf);
			shortLenOfPackCRCBufWithAA=(short) (shortLenOfPackCRCBufWithoutAA+2);
			shortLenOfSend=(short) (shortLenOfPackCRCBufWithAA+14);
			bytebufSend=new byte[shortLenOfSend];
			byte [] arrayPackCRC=new byte[shortLenOfPackCRCBufWithoutAA];
			bytebufSend[0]=arraybyteLocalIP[0];
			bytebufSend[1]=arraybyteLocalIP[1];
			bytebufSend[2]=arraybyteLocalIP[2];
			bytebufSend[3]=arraybyteLocalIP[3];
				
			if (pblvariables.gintGenerationNo<=3)
			{
				bytebufSend[4]=0x48; //H
				bytebufSend[5]=0x44; //D
				bytebufSend[6]=0x4C; //L
				bytebufSend[7]=0x4D; //M
				bytebufSend[8]=0x49; //I
				bytebufSend[9]=0x52; //R
				bytebufSend[10]=0x41; //A
				bytebufSend[11]=0x43; //C
				bytebufSend[12]=0x4C; //L
				bytebufSend[13]=0x45; //E
			}
			else
			{
				bytebufSend[4]=0x53; //S
				bytebufSend[5]=0x4D; //M
				bytebufSend[6]=0x41; //A
				bytebufSend[7]=0x52; //R
				bytebufSend[8]=0x54; //T
				bytebufSend[9]=0x43; //C
				bytebufSend[10]=0x4C; //L
				bytebufSend[11]=0x4F; //O
				bytebufSend[12]=0x55; //U
				bytebufSend[13]=0x44; //D
			}
		
			bytebufSend[14]=(byte) 0xAA; //
			bytebufSend[15]=(byte) 0xAA; //
            
        	//data size
		   	if ((blnBigPack==true) || ((shortLenOfAddtionalBuf+shortLenOfBaseData)>80)) 
        	{
        		arrayPackCRC[0]=(byte) 0xFF;
            }
            else
            {
            	arrayPackCRC[0]=(byte) shortLenOfPackCRCBufWithoutAA; 
            };
         
            arrayPackCRC[0]=(byte) shortLenOfPackCRCBufWithoutAA; 
            arrayPackCRC[1]=(byte) CONST_SELF_SUBNET_ID; //
            arrayPackCRC[2]=(byte) CONST_SELF_DEVICE_ID; //
            arrayPackCRC[3]=(byte) CONST_SELF_DEVICE_TYPE_H; //
            arrayPackCRC[4]=(byte) CONST_SELF_DEVICE_TYPE_L; //
            arrayPackCRC[5]=(byte) (intOP/256); //H bit of operation code 
            arrayPackCRC[6]=(byte) (intOP%256); //L bit of operation code
            arrayPackCRC[7]=byteObjSubnetID; //
            arrayPackCRC[8]=byteObjDeviceID; //
            if (shortLenOfAddtionalBuf>0)
            {
            	for(shortI=0;shortI<=shortLenOfAddtionalBuf-1;shortI++)
            	{
            		arrayPackCRC[9+shortI]=arrayAddtional[shortI];
            	}
            }
        	                
        	if (blnBigPack==false) 
        	{
           	  PackCRC(arrayPackCRC,(short) (arrayPackCRC.length-2));	
        	}
        	        	
        	for(shortI=0;shortI<=arrayPackCRC.length-1;shortI++)
        	{
        		bytebufSend[shortI+16]=arrayPackCRC[shortI];
        	}
          
        	oDataPacket = new DatagramPacket(bytebufSend, shortLenOfSend,
        	     InetAddress.getByAddress(arraybyteTargetIP), CONST_UDP_UDP_PORT);
        	
        	
           	moUDPSocket.send(oDataPacket);
           	blnSuccess=true;
             	
   		
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), e.getMessage(),
	  		          //Toast.LENGTH_SHORT).show();

		}
		return blnSuccess;
	}
	
	public boolean ACControl(byte byteSubnetID,byte byteDeviceID,int intType,int intValue)
    {
		boolean blnSuccess=false;
    	try
    	{
    		int intOP=0xE3D8;
       		short shortLenOfAddtionalBuf;
    		
       		pblvariables.mblnNeedCancelToWaitInUDPSocket=true;
       		
    		byte[] arrayAddtional =new byte[2];
    		arrayAddtional[0]=(byte) intType;
    		arrayAddtional[1]=(byte) intValue;
    	    		
    		shortLenOfAddtionalBuf=(short) (arrayAddtional.length);
    		blnSuccess=SendUDPBuffer(arrayAddtional, shortLenOfAddtionalBuf, intOP, byteSubnetID, byteDeviceID, false);
   	
    		
    		
    	}catch(Exception e)
    	{ 
    		//Toast.makeText(getApplicationContext(), e.getMessage(),
	  		         // Toast.LENGTH_SHORT).show();	
    	}
    	finally
    	{
    		pblvariables.mblnNeedCancelToWaitInUDPSocket=false;
    	}
    	return blnSuccess;
    }
    
	public boolean IsSocketClose()
	{
		boolean blnIsClose=false;
		try
		{
			if (moUDPSocket==null)
			{
				blnIsClose=true;
			}
			else
			{
				if (moUDPSocket.isClosed()==true)
				{
					blnIsClose=true;
				}
			}
			
		}catch(Exception e)
		{
			
		}
		return blnIsClose;
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
    		for(byteI=1;byteI<=CONST_MAX_TIMES_OF_SEND;byteI++)
 	    	{
    			if (IsSocketClose()==true) 
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
    
    public boolean SingleChannelControl(byte byteSubnetID,byte byteDeviceID,int intChns,int intBrightness,int intThirdPara,boolean blnNeedResend,boolean blnNeedToWaitANS)
    {
    	boolean blnSuccess=false,blnSent=false;
    	
    	try
    	{
    		byte[] arraybyteBufWithoutAA=null;  
    		byte byteI;
    		
    		int intOP=0x0031;
       		short shortLenOfAddtionalBuf;
    		
    		byte[] arrayAddtional =new byte[4];
    		arrayAddtional[0]=(byte) intChns;
    		arrayAddtional[1]=(byte) intBrightness;
    		arrayAddtional[2]=(byte) 0;
    		arrayAddtional[3]=(byte) 0;
    		
    		shortLenOfAddtionalBuf=(short) (arrayAddtional.length);
    		for(byteI=1;byteI<=CONST_MAX_TIMES_OF_SEND;byteI++)
 	    	{
    			if (IsSocketClose()==true)
    			{
    				return false;
    			}
    			/*
    			if (pblvariables.mblnNeedCancelToWaitInUDPSocket==true)
    			{
    				return false;
    			}
    			*/
    			
    			blnSent=SendUDPBuffer(arrayAddtional, shortLenOfAddtionalBuf, intOP, byteSubnetID, byteDeviceID, false);
         	    if (blnSent==true)	
         	    {  
         	    	if (blnNeedToWaitANS==true)
         	    	{
         	    		arraybyteBufWithoutAA=UDPReceive(byteSubnetID, byteDeviceID, intOP,true);
           	    		if (arraybyteBufWithoutAA==null)
           	    		{
           	    			if ((blnNeedResend==false) || (pblvariables.mblnNeedCancelToWaitInUDPSocket==true))
           	    			{
           	    				return false;
           	    			}
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
    		//Toast.makeText(getApplicationContext(), e.getMessage(),
	  		         // Toast.LENGTH_SHORT).show();	
    	}
    	finally
    	{
    		
    	}
    	return blnSuccess;
    }
    
    public boolean CurtainControl(byte byteSubnetID,byte byteDeviceID,int intCurtainNo,int intCurtainStatus,boolean blnNeedResend,boolean blnNeedToWaitANS)
    {
    	boolean blnSuccess=false,blnSent=false;
    	try
    	{
    		   	
    		int intOP=0xE3E0;
    		byte byteI;
       		short shortLenOfAddtionalBuf;
       		byte[] arraybyteBufWithoutAA=null;
    		
    		byte[] arrayAddtional =new byte[2];
    		arrayAddtional[0]=(byte) intCurtainNo;
    		arrayAddtional[1]=(byte) intCurtainStatus;
       		
    		shortLenOfAddtionalBuf=(short) (arrayAddtional.length);
    		for(byteI=1;byteI<=CONST_MAX_TIMES_OF_SEND;byteI++)
 	    	{
    			if (moUDPSocket==null)
    			{
    				return false;
    			}
    			else
    			{
    				if (moUDPSocket.isClosed()==true)
    				{
    					return false;
    				}
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
    		//Toast.makeText(getApplicationContext(), e.getMessage(),
	  		         // Toast.LENGTH_SHORT).show();	
    	}
    	return blnSuccess;
    }
    
    public boolean TimerControl(byte byteSubnetID,byte byteDeviceID,int intChannelNo,int intStatus,boolean blnNeedResend,boolean blnNeedToWaitANS)
    {
    	boolean blnSuccess=false,blnSent=false;
    	try
    	{
    		   	
    		int intOP=0xF116;
       		short shortLenOfAddtionalBuf;
       		byte byteI;
       		byte[] arraybyteBufWithoutAA=null;
    		
    		byte[] arrayAddtional =new byte[2];
    		arrayAddtional[0]=(byte) intChannelNo;
    		arrayAddtional[1]=(byte) intStatus;
       		
    		shortLenOfAddtionalBuf=(short) (arrayAddtional.length);
    		for(byteI=1;byteI<=CONST_MAX_TIMES_OF_SEND;byteI++)
 	    	{
    			if (moUDPSocket==null)
    			{
    				return false;
    			}
    			else
    			{
    				if (moUDPSocket.isClosed()==true)
    				{
    					return false;
    				}
    			}
    			
    			blnSent=SendUDPBuffer(arrayAddtional, shortLenOfAddtionalBuf, intOP, byteSubnetID, byteDeviceID, false);
         	    if (blnSent==true)	
         	    {  
         	    	if (blnNeedToWaitANS==true)
         	    	{
         	    		arraybyteBufWithoutAA=UDPReceive(byteSubnetID, byteDeviceID, intOP,false);
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
    		//Toast.makeText(getApplicationContext(), e.getMessage(),
	  		         // Toast.LENGTH_SHORT).show();	
    	}
    	return blnSuccess;
    }
    
    public boolean GPRSControl(byte byteSubnetID,byte byteDeviceID,int intCMDID,int intValue,boolean blnNeedResend,boolean blnNeedToWaitANS)
    {
    	boolean blnSuccess=false,blnSent=false;
    	try
    	{
    		   	
    		int intOP=0xE3D4;
       		short shortLenOfAddtionalBuf;
       		byte byteI;
       		byte[] arraybyteBufWithoutAA=null;
    		
    		byte[] arrayAddtional =new byte[2];
    		arrayAddtional[0]=(byte) intCMDID;
    		arrayAddtional[1]=(byte) intValue;
       		
    		shortLenOfAddtionalBuf=(short) (arrayAddtional.length);
    		for(byteI=1;byteI<=CONST_MAX_TIMES_OF_SEND;byteI++)
 	    	{
    			if (moUDPSocket==null)
    			{
    				return false;
    			}
    			else
    			{
    				if (moUDPSocket.isClosed()==true)
    				{
    					return false;
    				}
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
    		//Toast.makeText(getApplicationContext(), e.getMessage(),
	  		          //Toast.LENGTH_SHORT).show();	
    	}
    	return blnSuccess;
    }
    
    public boolean PanelControl(byte byteSubnetID,byte byteDeviceID,int intCMDID,int intValue,boolean blnNeedResend,boolean blnNeedToWaitANS)
    {
    	boolean blnSuccess=false,blnSent=false;
    	try
    	{
    		   	
    		int intOP=0xE3D4;
       		short shortLenOfAddtionalBuf;
       		byte byteI;
       		byte[] arraybyteBufWithoutAA=null;
    		
    		byte[] arrayAddtional =new byte[2];
    		arrayAddtional[0]=(byte) intCMDID;
    		arrayAddtional[1]=(byte) intValue;
       		
    		shortLenOfAddtionalBuf=(short) (arrayAddtional.length);
    		for(byteI=1;byteI<=CONST_MAX_TIMES_OF_SEND;byteI++)
 	    	{
    			if (moUDPSocket==null)
    			{
    				return false;
    			}
    			else
    			{
    				if (moUDPSocket.isClosed()==true)
    				{
    					return false;
    				}
    			}
    			
    			blnSent=SendUDPBuffer(arrayAddtional, shortLenOfAddtionalBuf, intOP, byteSubnetID, byteDeviceID, false);
         	    if (blnSent==true)	
         	    {  
         	    	if (blnNeedToWaitANS==true)
         	    	{
         	    		arraybyteBufWithoutAA=UDPReceive(byteSubnetID, byteDeviceID, intOP,false); //broadcast address when feedback
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
    		//Toast.makeText(getApplicationContext(), e.getMessage(),
	  		        //  Toast.LENGTH_SHORT).show();	
    	}
    	return blnSuccess;
    }
    
    
    protected boolean SceneControl(byte byteSubnetID,byte byteDeviceID,int intZoneID,int intSceneID,boolean blnNeedResend,boolean blnNeedToWaitANS)
    {
    	boolean blnSuccess=false,blnSent=false;
    	try
    	{
    		   	
    		int intOP=0x0002;
    		byte byteI;
       		short shortLenOfAddtionalBuf;
       		byte[] arraybyteBufWithoutAA=null;
    		
    		byte[] arrayAddtional =new byte[2];
    		arrayAddtional[0]=(byte) intZoneID;
    		arrayAddtional[1]=(byte) intSceneID;
    	    		
    		shortLenOfAddtionalBuf=(short) (arrayAddtional.length);
    		for(byteI=1;byteI<=CONST_MAX_TIMES_OF_SEND;byteI++)
 	    	{
    			if (moUDPSocket==null)
    			{
    				return false;
    			}
    			else
    			{
    				if (moUDPSocket.isClosed()==true)
    				{
    					return false;
    				}
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
    		//Toast.makeText(getApplicationContext(), e.getMessage(),
	  		         // Toast.LENGTH_SHORT).show();	
    	}
    	return blnSuccess;
    }
    
    protected boolean SequenceControl(byte byteSubnetID,byte byteDeviceID,int intZoneID,int intSequenceID,boolean blnNeedResend,boolean blnNeedToWaitANS)
    {
    	boolean blnSuccess=false,blnSent=false;
    	try
    	{
    		   	
    		int intOP=0x001A;
    		byte byteI;
       		short shortLenOfAddtionalBuf;
       		byte[] arraybyteBufWithoutAA=null;
    		
    		byte[] arrayAddtional =new byte[2];
    		arrayAddtional[0]=(byte) intZoneID;
    		arrayAddtional[1]=(byte) intSequenceID;
    	    		
    		shortLenOfAddtionalBuf=(short) (arrayAddtional.length);
    		for(byteI=1;byteI<=CONST_MAX_TIMES_OF_SEND;byteI++)
 	    	{
    			if (moUDPSocket==null)
    			{
    				return false;
    			}
    			else
    			{
    				if (moUDPSocket.isClosed()==true)
    				{
    					return false;
    				}
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
    		//Toast.makeText(getApplicationContext(), e.getMessage(),
	  		        // Toast.LENGTH_SHORT).show();	
    	}
    	return blnSuccess;
    }
    
    public byte[] UDPReceive(byte byteSubnetID,byte byteDeviceID,int intOP,boolean blnNeedCheckAddressOfFeedback)
    {
    	boolean blnSuccess=false;
       	byte[] arraybyteBufRec=null;
    	byte[] arraybyteBufWithoutAA=null;
    	    	
    	try
    	{
    		boolean blnContinute;
    		long lngStartTime_of_MS;
        	long lngCurTime_of_MS;
        	
        	byte byteSrcSubnetID_of_reply;
        	byte byteSrcDeviceID_of_reply;
        	int  intOP_of_reply;
        	int intOP_H,intOP_L;
        	int intTimes=0;
        	Calendar oCal;
     
        	oCal=Calendar.getInstance();  
    		lngStartTime_of_MS=oCal.getTimeInMillis();  
    		lngCurTime_of_MS=lngStartTime_of_MS;
    		while((lngCurTime_of_MS-lngStartTime_of_MS)<=CONST_TIME_OUT_FOR_TOTAL_WAIT)
     		{
    			try
        		{
    				if (pblvariables.mblnNeedCancelToWaitInUDPSocket==true)
        			{
        				return null;
        			}
    				
    				if (IsSocketClose()==true) 
        			{
        				return null;
        			}
    	
    			     				
    				intTimes=intTimes+1;
    				byte[] arraybyteBufTEMP= new byte[CONST_MAX_UPD_PACKET_LEN];
    				DatagramPacket oPacketRec = new DatagramPacket(arraybyteBufTEMP,arraybyteBufTEMP.length);
    		    	
    				moUDPSocket.setSoTimeout(CONST_TIME_OUT_FOR_EACH_WAIT);
    				moUDPSocket.setReceiveBufferSize(CONST_MAX_UPD_PACKET_LEN);
    				moUDPSocket.receive(oPacketRec);
      				arraybyteBufRec=oPacketRec.getData();
					
      				intOP_H=(arraybyteBufRec[CONST.CONST_START_PST_OF_LEN_OF_DATA_IN_FULL_PACKETS+5] *256) & 0xFFFF;
				    intOP_L=arraybyteBufRec[CONST.CONST_START_PST_OF_LEN_OF_DATA_IN_FULL_PACKETS+6] & 0xFF;
					intOP_of_reply=intOP_H+intOP_L;
					
					if ((intOP_of_reply==(intOP+1)))
					{
						if (blnNeedCheckAddressOfFeedback==true)
						{
							byteSrcSubnetID_of_reply=arraybyteBufRec[CONST.CONST_START_PST_OF_LEN_OF_DATA_IN_FULL_PACKETS+1];
							byteSrcDeviceID_of_reply=arraybyteBufRec[CONST.CONST_START_PST_OF_LEN_OF_DATA_IN_FULL_PACKETS+2];   
							if ((byteSrcSubnetID_of_reply==byteSubnetID) & (byteSrcDeviceID_of_reply==byteDeviceID) )
							{
								blnContinute=true;
							}
							else
							{
								blnContinute=false;
							}
						}
						else
						{
							blnContinute=true;
						}
						
						if (blnContinute==true)
						{
							arraybyteBufWithoutAA=ProcessUDPPackets(arraybyteBufRec);
		    				if (arraybyteBufWithoutAA!=null)
		        			{
		    					blnSuccess=true;
		    					break;
		       				}
						}
						
   				    }

					lngCurTime_of_MS=System.currentTimeMillis();
    				if ((lngCurTime_of_MS-lngStartTime_of_MS)>CONST_TIME_OUT_FOR_TOTAL_WAIT )
    				{
    					break;
    				}
    				
    		
    				
        		}catch(Exception e)
    			{
       				lngCurTime_of_MS=System.currentTimeMillis();
    				if ((lngCurTime_of_MS-lngStartTime_of_MS)>CONST_TIME_OUT_FOR_TOTAL_WAIT )
    				{
    					break;
    				}
    		
    			}
        		
    		}
    	    //receive packets end
    		
    		
    	}catch(Exception e)
    	{ 
    		// Toast.makeText(getApplicationContext(), e.getMessage(),
	  		          //Toast.LENGTH_SHORT).show();	
    	}
    	finally
    	{
    
    	}
    	
    	if (blnSuccess==true)
    	{
    	    return arraybyteBufWithoutAA;
    	    
    	}
		else
		{
			return null;
		}
    }
    
    /*
    private boolean IsStopToWait(int intOP)
    {
    	boolean blnStopToWait=false;
    	try
    	{  
    		my_app oMyApp= ((my_app) getApplicationContext());  
    		switch(intOP)
			{
				case 0x0033: //read light status 
				{
					blnStopToWait=oMyApp.GetStopWaitForReadingLightStatusInRoom();	 	    
					break;
				}
			}
    		
    	}catch(Exception e)
    	{
    		//Toast.makeText(getApplicationContext(), e.getMessage(),
	  		       //   Toast.LENGTH_SHORT).show();	
    	}
    	finally
    	{
    		/*if (oMyApp!=null)
    		{
    			oMyApp=null;
    		}
    		*/
    	//}
    	//return blnStopToWait;
    //}

    
    public byte[] UDPReceiveForRS232(byte byteSubnetID,byte byteDeviceID,byte byteCommandType,byte bytCurZoneNo,byte byteSourceNo)
    {
    	boolean blnSuccess=false,blnSuccessToHandle=false;
    	byte[] arraybyteBufRec=null;
    	byte[] arraybyteBufWithoutAA=null;
    	
    	try
    	{
    		long lngStartTime_of_MS;
        	long lngCurTime_of_MS;
        
        	int  intOP_of_reply,intTimeout;
        	int intOP_H,intOP_L;
        	int intOPOfShouldReply=0xEF02;
        	Calendar oCal;
	
			oCal=Calendar.getInstance();  
    		lngStartTime_of_MS=oCal.getTimeInMillis();  
    		lngCurTime_of_MS=lngStartTime_of_MS;
        		
    		if (byteCommandType==CONST.CONST_MUSIC_CMD_TYPE_READ_ALBUM_INFO)
    		{
    			intTimeout=CONST_TIME_OUT_FOR_TOTAL_WAIT_ALBUM;
    		}
    		else
    		{
    			intTimeout=CONST_TIME_OUT_FOR_TOTAL_WAIT_RS232;
    		}
    		
    		while((lngCurTime_of_MS-lngStartTime_of_MS)<=intTimeout)
    		{
    			try
        		{
    				byte[] arraybyteBufTEMP= new byte[CONST_MAX_UPD_PACKET_LEN];
    				DatagramPacket oPacketRec = new DatagramPacket(arraybyteBufTEMP,arraybyteBufTEMP.length);

    				moUDPSocket.setSoTimeout(CONST_TIME_OUT_FOR_EACH_WAIT);
    				moUDPSocket.receive(oPacketRec);
    				arraybyteBufRec=oPacketRec.getData();
    				
    				if (arraybyteBufRec[CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS]==0x23) // #
    				{
    					intOP_H=(arraybyteBufRec[CONST.CONST_START_PST_OF_LEN_OF_DATA_IN_FULL_PACKETS+5] *256) & 0xFFFF;
    				    intOP_L=arraybyteBufRec[CONST.CONST_START_PST_OF_LEN_OF_DATA_IN_FULL_PACKETS+6] & 0xFF;
    					intOP_of_reply=intOP_H+intOP_L;
    					if (intOP_of_reply==intOPOfShouldReply) 
    					{
    						switch(byteCommandType)
							{
								case CONST.CONST_MUSIC_CMD_TYPE_POWER_ON:
							    {
							        blnSuccessToHandle=HandleMusicPowerOn(arraybyteBufRec,bytCurZoneNo);
							        break;
							    }
								   
							    case CONST.CONST_MUSIC_CMD_TYPE_POWER_OFF:
								{
								    blnSuccessToHandle=HandleMusicPowerOff(arraybyteBufRec,bytCurZoneNo);
								    break;
								}
								
							    case CONST.CONST_MUSIC_CMD_TYPE_READ_ZONE_STATUS:
							    {
							    	blnSuccessToHandle=HandleForReadZoneStatus(arraybyteBufRec,bytCurZoneNo);
							    	break;
							    }
							    
							    case CONST.CONST_MUSIC_CMD_TYPE_READ_PLAYING_INFO:
							    {
							    	blnSuccessToHandle=HandleForReadPlayingInfo(arraybyteBufRec,byteSourceNo);
							    	break;
							    }
							    
								default:
								{
									blnSuccessToHandle=false;
									break;
								}

							} 
    						
    						if (blnSuccessToHandle==true)
    						{
    							arraybyteBufWithoutAA=ProcessUDPPackets(arraybyteBufRec);
        						if (arraybyteBufWithoutAA!=null)
        	        			{    					
        							blnSuccess=true;
        							break;
               				    }
    						}
    						
    		    					
    					}

    				}
    	
    				lngCurTime_of_MS=System.currentTimeMillis();
    				if ((lngCurTime_of_MS-lngStartTime_of_MS)>CONST_TIME_OUT_FOR_TOTAL_WAIT_RS232 )
    				{
    					break;
    				}
        			
        		}
    			catch(Exception e)
    			{
    				lngCurTime_of_MS=System.currentTimeMillis();
    				if ((lngCurTime_of_MS-lngStartTime_of_MS)>CONST_TIME_OUT_FOR_TOTAL_WAIT_RS232 )
    				{
    					break;
    				}
    			}
        		
    		}
    	    //receive packets end
    		
    		
    	}catch(Exception e)
    	{ 

    	}
   	
    	if (blnSuccess==true)
    	{
    	    return arraybyteBufWithoutAA;
    	    
    	}
		else
		{
			return null;
		}
    }
    
    private void test(byte byteCurZoneNo)
    {
    	byte[] arraybyteBuf=new byte[50];
    	arraybyteBuf[CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS]=0x23;
    	arraybyteBuf[CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+1]=0x5A;
    	arraybyteBuf[CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+2]=0x31;
    	arraybyteBuf[CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+3]=0x2C;
    	arraybyteBuf[CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+4]=0x4F;
    	arraybyteBuf[CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+5]=0x46;
    	arraybyteBuf[CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+6]=0x46;
    	arraybyteBuf[CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+7]=0x0D;
    	arraybyteBuf[CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+8]=0x0A;
    	arraybyteBuf[CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+9]=0x67;
    	arraybyteBuf[CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+10]=0x6C;
    	
    	//14 01 0A 03 F1 EF 02 FF FF 23 5A 31 2C 4F 46 46 0D 0A 67 6C
    	
    	if (HandleMusicPowerOff(arraybyteBuf,byteCurZoneNo) ==true)
    	{
    		
    	}
    }
    
    private boolean HandleForReadZoneStatus(byte[] arraybyteBufFull,byte bytCurZoneNo)
    {
    	boolean blnCorrect=false;
    	try
    	{
    		if (arraybyteBufFull[CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+1]==0x5A) //Z
			{
			   if (bytCurZoneNo<=9)
			   {
				   if ((arraybyteBufFull[CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+2]-0x30)==bytCurZoneNo) 
				   {
					   blnCorrect=true;
				   }
				   
			   }
			   else if ((bytCurZoneNo>=10) && (bytCurZoneNo<=99))
			   {
				   byte byteTemp1,byteTemp2,byteZoneNoOfRec;
				   byteTemp1=(byte) (arraybyteBufFull[CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+2]-0x30);
				   byteTemp2=(byte) (arraybyteBufFull[CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+3]-0x30);
				   byteZoneNoOfRec=(byte) (byteTemp1*10+byteTemp2);
					   
				   if (byteZoneNoOfRec==bytCurZoneNo) 
				   {
					   blnCorrect=true;

				   } 
			   }
			}
    		
    	}catch(Exception e)
    	{
    		//Toast.makeText(getApplicationContext(), e.getMessage(),
	  		      //    Toast.LENGTH_SHORT).show();	
    	}
    	return blnCorrect;
    }
    
    private boolean HandleForReadPlayingInfo(byte[] arraybyteBufFull,byte bytCurSourceNo)
    {
    	boolean blnCorrect=false;
    	try
    	{
    		if (arraybyteBufFull[CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+1]==83) //S
			{
			   if (bytCurSourceNo<=9)
			   {
				   if ((arraybyteBufFull[CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+2]-0x30)==bytCurSourceNo) 
				   {
					   //#S1DISPINFO,DUR1945,POS0,STATUS2
					   if ((arraybyteBufFull[CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+3]==68) 
						&& (arraybyteBufFull[CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+4]==73)
						&& (arraybyteBufFull[CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+5]==83)
						&& (arraybyteBufFull[CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+6]==80)
						&& (arraybyteBufFull[CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+7]==73)
						&& (arraybyteBufFull[CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+8]==78)
						&& (arraybyteBufFull[CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+9]==70)
						&& (arraybyteBufFull[CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+10]==79))
					   {
						   blnCorrect=true;
					   }
					   
				   }
				   
			   }
			   else if ((bytCurSourceNo>=10) && (bytCurSourceNo<=99))
			   {
				   byte byteTemp1,byteTemp2,byteSourceNoOfRec;
				   byteTemp1=(byte) (arraybyteBufFull[CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+2]-0x30);
				   byteTemp2=(byte) (arraybyteBufFull[CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+3]-0x30);
				   byteSourceNoOfRec=(byte) (byteTemp1*10+byteTemp2);
					   
				   if (byteSourceNoOfRec==bytCurSourceNo) 
				   {
					   if ((arraybyteBufFull[CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+4]==68) 
								&& (arraybyteBufFull[CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+5]==73)
								&& (arraybyteBufFull[CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+6]==83)
								&& (arraybyteBufFull[CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+7]==80)
								&& (arraybyteBufFull[CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+8]==73)
								&& (arraybyteBufFull[CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+9]==78)
								&& (arraybyteBufFull[CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+10]==70)
								&& (arraybyteBufFull[CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+11]==79))
					   {
						   blnCorrect=true;
					   }

				   } 
			   }
			}
    		
    	}catch(Exception e)
    	{
    		//Toast.makeText(getApplicationContext(), e.getMessage(),
	  		      //    Toast.LENGTH_SHORT).show();	
    	}
    	return blnCorrect;
    }
    
    private boolean HandleMusicPowerOn(byte[] arraybyteBufFull,byte bytCurZoneNo)
    {
    	boolean blnCorrect=false;
    	try
    	{
    		if (arraybyteBufFull.length<(CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+6)) 
    		{
    			return blnCorrect;
    		}
    		
    		if (arraybyteBufFull[CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+1]==0x5A) //Z
			{
			   if (bytCurZoneNo<=9)
			   {
				   if ((arraybyteBufFull[CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+2]-0x30)==bytCurZoneNo) 
				   {
					   //ON
					   if ((arraybyteBufFull[CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+4]==0x4F) 
							    && (arraybyteBufFull[CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+5]==0x4E) )
					   {
						   blnCorrect=true;
					   }
				   }
				   
				   
			   }
			   else if ((bytCurZoneNo>=10) && (bytCurZoneNo<=99))
			   {
				   if (arraybyteBufFull.length<(CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+7)) 
		    	   {
		    			return blnCorrect;
		    	   }
				   
				   byte byteTemp1,byteTemp2,byteZoneNoOfRec;
				   byteTemp1=(byte) (arraybyteBufFull[CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+2]-0x30);
				   byteTemp2=(byte) (arraybyteBufFull[CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+3]-0x30);
				   byteZoneNoOfRec=(byte) (byteTemp1*10+byteTemp2);
					   
				   if (byteZoneNoOfRec==bytCurZoneNo) 
				   {
					   //ON
					   if ((arraybyteBufFull[CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+5]==0x4F) 
							    && (arraybyteBufFull[CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+6]==0x4E) )
					   {
						   blnCorrect=true;
					   }

				   } 
			   }
			}
    		
    	}catch(Exception e)
    	{
    		//Toast.makeText(getApplicationContext(), e.getMessage(),
	  		      //    Toast.LENGTH_SHORT).show();	
    	}
    	return blnCorrect;
    }
    
    private boolean HandleMusicPowerOff(byte[] arraybyteBufFull,byte bytCurZoneNo)
    {
    	boolean blnCorrect=false;
    	try
    	{
    		if (arraybyteBufFull.length<(CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+7)) 
    		{
    			return blnCorrect;
    		}
    		
    		if (arraybyteBufFull[CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+1]==0x5A) //Z
		    {
			   if (bytCurZoneNo<=9)
			   {
				   if ((arraybyteBufFull[CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+2]-0x30)==bytCurZoneNo) 
				   {
					   //OFF
					   if ((arraybyteBufFull[CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+4]==0x4F) 
							    && (arraybyteBufFull[CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+5]==0x46)
							    && (arraybyteBufFull[CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+6]==0x46))
					   {
						   blnCorrect=true;
					   }
				   }
				   
				   
			   }
			   else if ((bytCurZoneNo>=10) && (bytCurZoneNo<=99))
			   {
				   if (arraybyteBufFull.length<(CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+8)) 
		    	   {
		    			return blnCorrect;
		    	   }
				   
				   byte byteTemp1,byteTemp2,byteZoneNoOfRec;
				   byteTemp1=(byte) (arraybyteBufFull[CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+2]-0x30);
				   byteTemp2=(byte) (arraybyteBufFull[CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+3]-0x30);
				   byteZoneNoOfRec=(byte) (byteTemp1*10+byteTemp2);
					   
				   if (byteZoneNoOfRec==bytCurZoneNo) 
				   {
					   //OFF
					   if ((arraybyteBufFull[CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+5]==0x4F) 
							    && (arraybyteBufFull[CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+6]==0x46)
							    && (arraybyteBufFull[CONST.CONST_START_PST_OF_ADDITIONAL_DATA_IN_FULL_PACKETS+7]==0x46))
					   {
						   blnCorrect=true;
					   }

				   } 
			   }
			}

    	
    	}catch(Exception e)
    	{
    		//Toast.makeText(getApplicationContext(), e.getMessage(),
	  		        //  Toast.LENGTH_SHORT).show();	
    	}
    	
    	return blnCorrect;
    }
    
    //Air condition begin
    public byte[]  ReadACTempType(byte byteSubnetID,byte byteDeviceID)
    {
    	boolean blnSuccess=false;
    	byte[] arraybyteBufWithoutAA=null;
    	
    	try
    	{        	
    		int intOP=0xE120;
       		short shortLenOfAddtionalBuf;
       		
    		byte[] arrayAddtional =new byte[0];
    		shortLenOfAddtionalBuf=(short) (arrayAddtional.length);
    		blnSuccess=SendUDPBuffer(arrayAddtional, shortLenOfAddtionalBuf, intOP, byteSubnetID, byteDeviceID, false);
     	    if (blnSuccess==true)	
     	    {
     	    	arraybyteBufWithoutAA=UDPReceive(byteSubnetID, byteDeviceID, intOP,true);
     	    }
    		
    		
    		
    	}catch(Exception e)
    	{ 
    		// Toast.makeText(getApplicationContext(), e.getMessage(),
	  		       //   Toast.LENGTH_SHORT).show();	
    	}
    	return arraybyteBufWithoutAA;
    }
    
    public byte[] ReadACTempRange(byte byteSubnetID,byte byteDeviceID)
    {
    	boolean blnSuccess=false;
    	byte[] arraybyteBufWithoutAA=null;
    	try
    	{
    		   	
    		int intOP=0x1900;
       		short shortLenOfAddtionalBuf;
    		
    		byte[] arrayAddtional =new byte[0];
    	    	    		
    		shortLenOfAddtionalBuf=(short) (arrayAddtional.length);
    		blnSuccess=SendUDPBuffer(arrayAddtional, shortLenOfAddtionalBuf, intOP, byteSubnetID, byteDeviceID, false);
    		if (blnSuccess==true)	
     	    {
     	    	arraybyteBufWithoutAA=UDPReceive(byteSubnetID, byteDeviceID, intOP,true);
     	    }	
    		
    	}catch(Exception e)
    	{ 
    		 //Toast.makeText(getApplicationContext(), e.getMessage(),
	  		        //  Toast.LENGTH_SHORT).show();	
    	}
    	return arraybyteBufWithoutAA;
    }
    
    public byte[] ReadACCurrentTemp(byte byteSubnetID,byte byteDeviceID)
    {
    	boolean blnSuccess=false;
    	byte[] arraybyteBufWithoutAA=null;
    	try
    	{
    		   	
    		int intOP=0xE0EC;
       		short shortLenOfAddtionalBuf;
    		
    		byte[] arrayAddtional =new byte[0];
    	    	    		
    		shortLenOfAddtionalBuf=(short) (arrayAddtional.length);
    		blnSuccess=SendUDPBuffer(arrayAddtional, shortLenOfAddtionalBuf, intOP, byteSubnetID, byteDeviceID, false);
    		if (blnSuccess==true)	
     	    {
     	    	arraybyteBufWithoutAA=UDPReceive(byteSubnetID, byteDeviceID, intOP,true);
     	    }		
    		
    	}catch(Exception e)
    	{ 
    		// Toast.makeText(getApplicationContext(), e.getMessage(),
	  		         // Toast.LENGTH_SHORT).show();	
    	}
    	return arraybyteBufWithoutAA;
    }
    
    
    public byte[] ReadACFanSpeedAndModeTable(byte byteSubnetID,byte byteDeviceID)
    {
    	boolean blnSuccess=false;
    	byte[] arraybyteBufWithoutAA=null;
    	try
    	{
    		   	
    		int intOP=0xE124;
       		short shortLenOfAddtionalBuf;
    		
    		byte[] arrayAddtional =new byte[0];
    	    	    		
    		shortLenOfAddtionalBuf=(short) (arrayAddtional.length);
    		blnSuccess=SendUDPBuffer(arrayAddtional, shortLenOfAddtionalBuf, intOP, byteSubnetID, byteDeviceID, false);
    		if (blnSuccess==true)	
     	    {
     	    	arraybyteBufWithoutAA=UDPReceive(byteSubnetID, byteDeviceID, intOP,true);
     	    }			
    		
    	}catch(Exception e)
    	{ 
    		// Toast.makeText(getApplicationContext(), e.getMessage(),
	  		        //  Toast.LENGTH_SHORT).show();	
    	}
    	return arraybyteBufWithoutAA;
    }
    
    //Air condition end
    
    public byte[] ProcessUDPPackets(byte[] arraybyteRec)
	{
    	byte[] arraybyteBufWithoutHead=null;
    	int intLenOfPackets;
    	boolean blnIsBigPack=false;
    	
		try
		{
			if (IsSocketClose()==true) 
			{
				return null;
			}
			
			int intSizeWithoutHead=0,intI=0;
			boolean blnNeedToCheckCRC=false;
					
			intLenOfPackets=arraybyteRec.length;
		    if ((intLenOfPackets<=0) || (intLenOfPackets<27) )
	        {
	           return null;
	        }
	           
		
			if (((arraybyteRec[14] & 0xFF)==0xAA) && (((arraybyteRec[15] & 0xFF)==0xAA) || ((arraybyteRec[15] & 0xFF)==0x55)))
			{
				//do nothing
			}
			else
			{
				return null;
			}
				
			if ((arraybyteRec[16] & 0xFF)==0xFF)
			{
				blnIsBigPack=true;
			}
			else
			{
				blnIsBigPack=false;
			}

			intSizeWithoutHead=intLenOfPackets-16;
			arraybyteBufWithoutHead=new byte[intSizeWithoutHead];
			for(intI=0;intI<arraybyteBufWithoutHead.length;intI++)
			{
				arraybyteBufWithoutHead[intI]=arraybyteRec[intI+16];
			}
			
			if (blnIsBigPack==true)
			{
				blnNeedToCheckCRC=false;
			}
			else
		    {
				blnNeedToCheckCRC=true;
				
		    };
		    
			if (IsSocketClose()==true) 
			{
				return null;
			}
			
		    if (blnNeedToCheckCRC==true)
		    {
		    	if (CheckCRC(arraybyteBufWithoutHead,arraybyteBufWithoutHead.length-2)==false)
		    	{
		       		arraybyteBufWithoutHead=null;
		    	}
		    };
				    
					
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), e.getMessage(),
	  		      //    Toast.LENGTH_SHORT).show();	
		}
		
		return arraybyteBufWithoutHead;
	}
    
    //Music begin
    public byte[] MusicZonePowerControl(byte byteSubnetID,byte byteDeviceID,byte byteZoneNo,boolean blnPowerOn,boolean blnNeedResend,boolean blnNeedToWaitANS)
    {
		boolean blnSuccess=false,blnSent=false;
		byte[] arraybyteBufWithoutAA=null;
		
    	try
    	{
    		int intOP=0xEF03;
    		short shortLenOfAddtionalBuf;
       		byte byteI,bytCmdType;
       		String strCommand;
       		
       		if (blnPowerOn==true)
       		{
       			strCommand="*Z"+byteZoneNo+"ON";   
       			bytCmdType=CONST.CONST_MUSIC_CMD_TYPE_POWER_ON;
       		}
       		else
       		{
       			strCommand="*Z"+byteZoneNo+"OFF";   
       			bytCmdType=CONST.CONST_MUSIC_CMD_TYPE_POWER_OFF;
       		}
       				
    		byte[] arraybyteTemp= strCommand.getBytes("ASCII");
    		byte[] arrayAddtional =new byte[arraybyteTemp.length+1];
    		for(byteI=0;byteI<arraybyteTemp.length;byteI++)
    		{
    			arrayAddtional[byteI]=arraybyteTemp[byteI];
    		}
    		arrayAddtional[arrayAddtional.length-1]=0x0D;
		
    	
    		shortLenOfAddtionalBuf=(short) (arrayAddtional.length);
    		for(byteI=1;byteI<=CONST_MAX_TIMES_OF_SEND;byteI++)
 	    	{
    		    if (this.IsSocketClose()==true) return null;
    		    if (pblvariables.mblnNeedCancelToWaitInUDPSocket==true) return null;
    			
    			blnSent=SendUDPBuffer(arrayAddtional, shortLenOfAddtionalBuf, intOP, byteSubnetID, byteDeviceID, false);
         	    if (blnSent==true)	
         	    {  
         	    	if (blnNeedToWaitANS==true)
         	    	{
         	    		byte byteSourceNo=0;
         	    		arraybyteBufWithoutAA=UDPReceiveForRS232(byteSubnetID, byteDeviceID, bytCmdType,byteZoneNo,byteSourceNo);
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
    		
    	}
    	if (blnSuccess==true)
    	{
    		return arraybyteBufWithoutAA;
    	}
    	else
    	{
    		return null;
    	}
    	
    }
    
    //Read music zone status
    public byte[] MusicReadZoneStatus(byte byteSubnetID,byte byteDeviceID,byte byteZoneNo,boolean blnNeedResend,boolean blnNeedToWaitANS)
    {
		boolean blnSuccess=false,blnSent=false;
		byte[] arraybyteBufWithoutAA=null;
		
    	try
    	{
    		int intOP=0xEF03;
    		short shortLenOfAddtionalBuf;
       		byte byteI,bytCmdType;
       		String strCommand;
              
        	strCommand="*Z"+byteZoneNo+"STATUS?";   
       		bytCmdType=CONST.CONST_MUSIC_CMD_TYPE_READ_ZONE_STATUS;
       		
       				
    		byte[] arraybyteTemp= strCommand.getBytes("ASCII");
    		byte[] arrayAddtional =new byte[arraybyteTemp.length+1];
    		for(byteI=0;byteI<arraybyteTemp.length;byteI++)
    		{
    			arrayAddtional[byteI]=arraybyteTemp[byteI];
    		}
    		arrayAddtional[arrayAddtional.length-1]=0x0D;
		
    		shortLenOfAddtionalBuf=(short) (arrayAddtional.length);
    		for(byteI=1;byteI<=CONST_MAX_TIMES_OF_SEND;byteI++)
 	    	{
    		    if (this.IsSocketClose()==true) return null;
    		    if (pblvariables.mblnNeedCancelToWaitInUDPSocket==true) return null;
    			
    			blnSent=SendUDPBuffer(arrayAddtional, shortLenOfAddtionalBuf, intOP, byteSubnetID, byteDeviceID, false);
         	    if (blnSent==true)	
         	    {  
         	    	if (blnNeedToWaitANS==true)
         	    	{
         	    		byte byteSourceNo=0;
         	    		arraybyteBufWithoutAA=UDPReceiveForRS232(byteSubnetID, byteDeviceID, bytCmdType,byteZoneNo,byteSourceNo);
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
    		
    	}
    	if (blnSuccess==true)
    	{
    		return arraybyteBufWithoutAA;
    	}
    	else
    	{
    		return null;
    	}
    	
    }
    
    //Read music info
    public byte[] MusicReadPlayingInfo(byte byteSubnetID,byte byteDeviceID,byte byteSourceNo,boolean blnNeedResend,boolean blnNeedToWaitANS)
    {
		boolean blnSuccess=false,blnSent=false;
		byte[] arraybyteBufWithoutAA=null;
		
    	try
    	{
    		int intOP=0xEF03;
    		short shortLenOfAddtionalBuf;
       		byte byteI,bytCmdType;
       		String strCommand;
          
        	strCommand="*S"+byteSourceNo+"DISPINFO?";   
       		bytCmdType=CONST.CONST_MUSIC_CMD_TYPE_READ_PLAYING_INFO;
       	       				
    		byte[] arraybyteTemp= strCommand.getBytes("ASCII");
    		byte[] arrayAddtional =new byte[arraybyteTemp.length+1];
    		for(byteI=0;byteI<arraybyteTemp.length;byteI++)
    		{
    			arrayAddtional[byteI]=arraybyteTemp[byteI];
    		}
    		arrayAddtional[arrayAddtional.length-1]=0x0D;
		
    		shortLenOfAddtionalBuf=(short) (arrayAddtional.length);
    		for(byteI=1;byteI<=CONST_MAX_TIMES_OF_SEND;byteI++)
 	    	{
    		    if (this.IsSocketClose()==true) return null;
    		    if (pblvariables.mblnNeedCancelToWaitInUDPSocket==true) return null;
    			
    			blnSent=SendUDPBuffer(arrayAddtional, shortLenOfAddtionalBuf, intOP, byteSubnetID, byteDeviceID, false);
         	    if (blnSent==true)	
         	    {  
         	    	if (blnNeedToWaitANS==true)
         	    	{
         	    		byte byteCurZoneNo=0; //not useful
         	    		arraybyteBufWithoutAA=UDPReceiveForRS232(byteSubnetID, byteDeviceID, bytCmdType,byteCurZoneNo,byteSourceNo);
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
    		
    	}
    	if (blnSuccess==true)
    	{
    		return arraybyteBufWithoutAA;
    	}
    	else
    	{
    		return null;
    	}
    	
    }
    
   //Read music album info
    public byte[] MusicReadAlbumInfo(byte byteSubnetID,byte byteDeviceID,byte byteSourceNo,boolean blnNeedResend,boolean blnNeedToWaitANS)
    {
		boolean blnSuccess=false,blnSent=false;
		byte[] arraybyteBufWithoutAA=null;
		
    	try
    	{
    		int intOP=0xEF03;
    		short shortLenOfAddtionalBuf;
       		byte byteI,bytCmdType;
       		String strCommand;
          
        	strCommand="*S"+byteSourceNo+"DISPLINE?";   
       		bytCmdType=CONST.CONST_MUSIC_CMD_TYPE_READ_ALBUM_INFO;
       	       				
    		byte[] arraybyteTemp= strCommand.getBytes("ASCII");
    		byte[] arrayAddtional =new byte[arraybyteTemp.length+1];
    		for(byteI=0;byteI<arraybyteTemp.length;byteI++)
    		{
    			arrayAddtional[byteI]=arraybyteTemp[byteI];
    		}
    		arrayAddtional[arrayAddtional.length-1]=0x0D;
		
    		shortLenOfAddtionalBuf=(short) (arrayAddtional.length);
    		for(byteI=1;byteI<=CONST_MAX_TIMES_OF_SEND;byteI++)
 	    	{
    		    if (this.IsSocketClose()==true) return null;
    		    if (pblvariables.mblnNeedCancelToWaitInUDPSocket==true) return null;
    			
    			blnSent=SendUDPBuffer(arrayAddtional, shortLenOfAddtionalBuf, intOP, byteSubnetID, byteDeviceID, false);
         	    if (blnSent==true)	
         	    {  
         	    	if (blnNeedToWaitANS==true)
         	    	{
         	    		byte byteCurZoneNo=0; //not useful
         	    		arraybyteBufWithoutAA=UDPReceiveForRS232(byteSubnetID, byteDeviceID, bytCmdType,byteCurZoneNo,byteSourceNo);
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
    		
    	}
    	if (blnSuccess==true)
    	{
    		return arraybyteBufWithoutAA;
    	}
    	else
    	{
    		return null;
    	}
    	
    }
    
    public boolean MusicPlayPause(byte byteSubnetID,byte byteDeviceID,byte byteZoneNo)
    {
		boolean blnSuccess=false;
    	try
    	{
    		int intOP=0xEF03;
       		short shortLenOfAddtionalBuf;
       		byte byteI;
       		String strCommand;
       		
    		strCommand="*Z"+byteZoneNo+"PLAYPAUSE";   
            				
    		byte[] arraybyteTemp= strCommand.getBytes("ASCII");
    		byte[] arrayAddtional =new byte[arraybyteTemp.length+1];
    		for(byteI=0;byteI<arraybyteTemp.length;byteI++)
    		{
    			arrayAddtional[byteI]=arraybyteTemp[byteI];
    		}
    		arrayAddtional[arrayAddtional.length-1]=0x0D;
		
    		shortLenOfAddtionalBuf=(short) (arrayAddtional.length);
    		blnSuccess=SendUDPBuffer(arrayAddtional, shortLenOfAddtionalBuf, intOP, byteSubnetID, byteDeviceID, false);
   	
    		
    		
    	}catch(Exception e)
    	{ 
    		//Toast.makeText(getApplicationContext(), e.getMessage(),
	  		     //     Toast.LENGTH_SHORT).show();	
    	}
    	return blnSuccess;
    }
      
    
    public boolean MusicPrevSong(byte byteSubnetID,byte byteDeviceID,byte byteZoneNo)
    {
		boolean blnSuccess=false;
    	try
    	{
    		int intOP=0xEF03;
       		short shortLenOfAddtionalBuf;
       		byte byteI;
       		String strCommand;
       		
    		strCommand="*Z"+byteZoneNo+"PREV";   
            				
    		byte[] arraybyteTemp= strCommand.getBytes("ASCII");
    		byte[] arrayAddtional =new byte[arraybyteTemp.length+1];
    		for(byteI=0;byteI<arraybyteTemp.length;byteI++)
    		{
    			arrayAddtional[byteI]=arraybyteTemp[byteI];
    		}
    		arrayAddtional[arrayAddtional.length-1]=0x0D;
		
    		shortLenOfAddtionalBuf=(short) (arrayAddtional.length);
    		blnSuccess=SendUDPBuffer(arrayAddtional, shortLenOfAddtionalBuf, intOP, byteSubnetID, byteDeviceID, false);
   	
    		
    		
    	}catch(Exception e)
    	{ 
    		//Toast.makeText(getApplicationContext(), e.getMessage(),
	  		      //    Toast.LENGTH_SHORT).show();	
    	}
    	return blnSuccess;
    }
    
    public boolean MusicNextSong(byte byteSubnetID,byte byteDeviceID,byte byteZoneNo)
    {
		boolean blnSuccess=false;
    	try
    	{
    		int intOP=0xEF03;
       		short shortLenOfAddtionalBuf;
       		byte byteI;
       		String strCommand;
       		
    		strCommand="*Z"+byteZoneNo+"NEXT";   
            				
    		byte[] arraybyteTemp= strCommand.getBytes("ASCII");
    		byte[] arrayAddtional =new byte[arraybyteTemp.length+1];
    		for(byteI=0;byteI<arraybyteTemp.length;byteI++)
    		{
    			arrayAddtional[byteI]=arraybyteTemp[byteI];
    		}
    		arrayAddtional[arrayAddtional.length-1]=0x0D;
		
    		shortLenOfAddtionalBuf=(short) (arrayAddtional.length);
    		blnSuccess=SendUDPBuffer(arrayAddtional, shortLenOfAddtionalBuf, intOP, byteSubnetID, byteDeviceID, false);
   	
    		
    		
    	}catch(Exception e)
    	{ 
    		//Toast.makeText(getApplicationContext(), e.getMessage(),
	  		         // Toast.LENGTH_SHORT).show();	
    	}
    	return blnSuccess;
    }
    
    public boolean MusicIncrementVOL(byte byteSubnetID,byte byteDeviceID,byte byteZoneNo)
    {
		boolean blnSuccess=false;
    	try
    	{
    		int intOP=0xEF03;
       		short shortLenOfAddtionalBuf;
       		byte byteI;
       		String strCommand;
       		
    		strCommand="*Z"+byteZoneNo+"VOL+";   
            				
    		byte[] arraybyteTemp= strCommand.getBytes("ASCII");
    		byte[] arrayAddtional =new byte[arraybyteTemp.length+1];
    		for(byteI=0;byteI<arraybyteTemp.length;byteI++)
    		{
    			arrayAddtional[byteI]=arraybyteTemp[byteI];
    		}
    		arrayAddtional[arrayAddtional.length-1]=0x0D;
		
    		shortLenOfAddtionalBuf=(short) (arrayAddtional.length);
    		blnSuccess=SendUDPBuffer(arrayAddtional, shortLenOfAddtionalBuf, intOP, byteSubnetID, byteDeviceID, false);
   	
    		
    		
    	}catch(Exception e)
    	{ 
    		//Toast.makeText(getApplicationContext(), e.getMessage(),
	  		     //     Toast.LENGTH_SHORT).show();	
    	}
    	return blnSuccess;
    }

    
    public boolean MusicDncrementVOL(byte byteSubnetID,byte byteDeviceID,byte byteZoneNo)
    {
		boolean blnSuccess=false;
    	try
    	{
    		int intOP=0xEF03;
       		short shortLenOfAddtionalBuf;
       		byte byteI;
       		String strCommand;
       		
    		strCommand="*Z"+byteZoneNo+"VOL-";   
            				
    		byte[] arraybyteTemp= strCommand.getBytes("ASCII");
    		byte[] arrayAddtional =new byte[arraybyteTemp.length+1];
    		for(byteI=0;byteI<arraybyteTemp.length;byteI++)
    		{
    			arrayAddtional[byteI]=arraybyteTemp[byteI];
    		}
    		arrayAddtional[arrayAddtional.length-1]=0x0D;
		
    		shortLenOfAddtionalBuf=(short) (arrayAddtional.length);
    		blnSuccess=SendUDPBuffer(arrayAddtional, shortLenOfAddtionalBuf, intOP, byteSubnetID, byteDeviceID, false);
   	
    		
    		
    	}catch(Exception e)
    	{ 
    		//Toast.makeText(getApplicationContext(), e.getMessage(),
	  		        //  Toast.LENGTH_SHORT).show();	
    	}
    	return blnSuccess;
    }
    
    public boolean MusicSourceControl(byte byteSubnetID,byte byteDeviceID,byte byteZoneNo,byte byteSourceNo)
    {
		boolean blnSuccess=false;
    	try
    	{
    		int intOP=0xEF03;
       		short shortLenOfAddtionalBuf;
       		byte byteI;
       		String strCommand;
       		
    		strCommand="*Z"+byteZoneNo+"SRC"+byteSourceNo;   
              				
    		byte[] arraybyteTemp= strCommand.getBytes("ASCII");
    		byte[] arrayAddtional =new byte[arraybyteTemp.length+1];
    		for(byteI=0;byteI<arraybyteTemp.length;byteI++)
    		{
    			arrayAddtional[byteI]=arraybyteTemp[byteI];
    		}
    		arrayAddtional[arrayAddtional.length-1]=0x0D;
		
    		shortLenOfAddtionalBuf=(short) (arrayAddtional.length);
    		blnSuccess=SendUDPBuffer(arrayAddtional, shortLenOfAddtionalBuf, intOP, byteSubnetID, byteDeviceID, false);
   	
    		
    		
    	}catch(Exception e)
    	{ 
    		//Toast.makeText(getApplicationContext(), e.getMessage(),
	  		         // Toast.LENGTH_SHORT).show();	
    	}
    	return blnSuccess;
    }
    
    //music end
    
    
	/*
	private void SendMsgBack(byte[] arraybyteRec)
	{
		try
		{
			/*
	    	 * send message back to activity by Handler
	    	 */
	    	 /*Message msg= moHandler.obtainMessage();
             Bundle oBundle = new Bundle();
             oBundle.putByteArray("arraybyteRec", arraybyteRec);
             msg.setData(oBundle);
             moHandler.sendMessage(msg);
             
		}catch(Exception e)
		{

		}
	}
	
	*/
	/*
	UDPSocket(Handler oHandler) {
		moHandler =oHandler;
    }*/
	
    private boolean IsInLightTab()
    {
  	  boolean blnIsInLighttab=false;
  	  try
  	  {
  		    if (pblvariables.mtabHostInRoom==null)
  			{
  		
  			}
  			else
  			{
  				if (pblvariables.mtabHostInRoom.getCurrentTab()==CONST.CONST_TAB_INDEX_OF_LIGHT_IN_ROOM)
  				{
  					blnIsInLighttab=true;
  				}
  				else
  				{
  					blnIsInLighttab=false;
  				}
  			}
  		  
  	  }catch(Exception e)
  	  {
  		  
  	  }
  	  return blnIsInLighttab; 
    }
    
	//lighting begin
    public byte[]  ReadLightStatus(byte byteSubnetID,byte byteDeviceID,boolean blnResend)
    {
    	boolean blnSuccess=false;
    	byte[] arraybyteBufWithoutAA=null;
    	
    	try
    	{        	
    		int intOP=0x0033;
    		byte byteI;
       		short shortLenOfAddtionalBuf;
       		
    		byte[] arrayAddtional =new byte[0];
    		shortLenOfAddtionalBuf=(short) (arrayAddtional.length);
    		for(byteI=1;byteI<=CONST_MAX_TIMES_OF_SEND;byteI++)
 	    	{
    			if (IsSocketClose()==true)
    			{
    				return null;
    			}
    			    			    			
    			if (pblvariables.mblnNeedCancelToWaitInUDPSocket==true)
    			{
    				return null;
    			}
    			
    			if (IsInLightTab()==false) return null;
    			    			
    			
    			blnSuccess=SendUDPBuffer(arrayAddtional, shortLenOfAddtionalBuf, intOP, byteSubnetID, byteDeviceID, false);
         	    if (blnSuccess==true)	
         	    {  
       	    		arraybyteBufWithoutAA=UDPReceive(byteSubnetID, byteDeviceID, intOP,true);
       	    		if (arraybyteBufWithoutAA==null)
       	    		{
       	    			if (blnResend==false)
       	    			{
       	    				break;
       	    			}
       	    		}
       	    		else
       	    		{
       	    			break;
       	    		}
         	    }
 	    	}
    		
    		
    		
    		
    	}catch(Exception e)
    	{ 
    		// Toast.makeText(getApplicationContext(), e.getMessage(),
	  		      //    Toast.LENGTH_SHORT).show();	
    	}
    	return arraybyteBufWithoutAA;
    }
    
    //lighting end
	
	
}
