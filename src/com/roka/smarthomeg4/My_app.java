package com.roka.smarthomeg4;

import java.net.DatagramSocket;

import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import com.roka.smarthomeg4.business.Logo;
import com.roka.smarthomeg4.database.Database;
import com.roka.smarthomeg4.database.dbconnection.LogoDB;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

public class My_app extends Application {

	private DatagramSocket moUDPSocket = null;
	private static final int CONST_UDP_UDP_PORT = 6000;
	private int mintCurRoomID;
	public static boolean mblnNeedCancelToWaitInUDPSocket=false;

	private static My_app moAappInstance;

	private boolean mblnStopWaitForReadingLightStatusInRoom = false;

	public static HashMap<Integer, Logo> hasmap;
	private static final int INDEX = 17;
	private int index;
	private int playListIndex = 15;

	// private static my_app instance;
	private static Database database = null;

	public static Database getDatabase() {
		if (database == null) {
			database = new Database(moAappInstance);
			database.OpenDatabase();
		}
		return database;
	}

	public static My_app getInstance() {
		return moAappInstance;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		moAappInstance = this;

		getDatabase();

		setIndex(INDEX);
		
		
	}

	public int getPlayListIndex() {
		return playListIndex;
	}

	public void setPlayListIndx(int PLAYLISTINDEX) {
		this.playListIndex = PLAYLISTINDEX;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public My_app GetApp() {
		return moAappInstance;
	}

	public DatagramSocket GetUDPSocket() {
		try {
			if (moUDPSocket == null) {
				CreateUDPSocket();

			}

		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), e.getMessage(),
					Toast.LENGTH_LONG).show();
		}
		return moUDPSocket;

	}

	public void CreateUDPSocket() {
		try {

			if (moUDPSocket != null) {
				if (moUDPSocket.isClosed() == false) {
					moUDPSocket.close();
				}
				moUDPSocket = null;
			}
			// set udpsock and setBrodcast
			// moUDPSocket=new DatagramSocket(CONST_UDP_UDP_PORT);
			// moUDPSocket.setBroadcast(true);//设置标志，广播数据报
			// 下面是遇到the address is already in use 解决办法
			if (moUDPSocket == null) {
				moUDPSocket = new DatagramSocket(null);
				moUDPSocket.setReuseAddress(true);
				moUDPSocket.setBroadcast(true);
				moUDPSocket.bind(new InetSocketAddress(CONST_UDP_UDP_PORT));
			}

		} catch (SocketException e) {
			// TODO Auto-generated catch block

			Toast.makeText(getApplicationContext(), e.getMessage(),
					Toast.LENGTH_LONG).show();
		}

	}

	public void SetRoomID(int intRoomID) {
		mintCurRoomID = intRoomID;
	}

	public int GetRoomID() {
		return mintCurRoomID;
	}

	// light in the room begin
	public boolean GetStopWaitForReadingLightStatusInRoom() {
		return mblnStopWaitForReadingLightStatusInRoom;// false
	}

	public void SetStopWaitForReadingLightStatusInRoom(
			boolean blnStopWaitForReadingLightStatusInRoom) {
		mblnStopWaitForReadingLightStatusInRoom = blnStopWaitForReadingLightStatusInRoom;// false
	}

	// light in the room end
	protected void onDestroy() {
		try {
			CloseSocket();

		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), e.getMessage(),
					Toast.LENGTH_LONG).show();
		} finally {

		}

	}

	public void CloseSocket() {
		try {
			if (moUDPSocket != null) {
				if (moUDPSocket.isClosed() == false) {
					moUDPSocket.close();
				}
				moUDPSocket = null;
			}

		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), e.getMessage(),
					Toast.LENGTH_LONG).show();
		} finally {

		}
	}
	
	
	public static void fillImages(Context context) {
		ArrayList<Logo> arrLogos=new LogoDB(context).getZoneImages();
		hasmap=new HashMap<Integer, Logo>();
		if(arrLogos!=null){
			for (Logo logo : arrLogos) {
				My_app.hasmap.put(logo.getLogoID(), logo);
			}
		}
	}
}
