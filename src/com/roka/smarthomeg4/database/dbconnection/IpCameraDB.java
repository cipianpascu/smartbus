package com.roka.smarthomeg4.database.dbconnection;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.roka.smarthomeg4.business.IpCamera;
import com.roka.smarthomeg4.database.Database;

public class IpCameraDB {
	private Database myDatabase;
	private SQLiteDatabase sqLiteDatabase;

	public IpCameraDB(Context context) {
		// TODO Auto-generated constructor stub
		myDatabase = new Database(context);

	}

	public ArrayList<IpCamera> getAllIpCamera() {
		ArrayList<IpCamera> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("IpCamera", null, null,
				null, null, null, null);
		if (c.getCount() > 0) {
			Arr = new ArrayList<IpCamera>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				IpCamera data = new IpCamera();
				data.setCameraID(c.getInt(0));
				data.setBrand(c.getString(1));
				data.setType(c.getInt(2));
				data.setIp(c.getString(3));
				data.setPort(c.getInt(4));
				data.setMask(c.getString(5));
				data.setGateway(c.getString(6));
				data.setDns(c.getString(7));
				data.setIsP2P(c.getInt(8));
				data.setMediaPort(c.getInt(9));
				data.setSysVersions(c.getInt(10));				
				data.setAppVersions(c.getInt(11));
				data.setDhcpEnabled(c.getInt(12));				
				data.setCameraName(c.getString(13));
				data.setUIDForP2P(c.getInt(14));				
				data.setIsMainStream(c.getInt(15));
						
				Arr.add(data);
				c.moveToNext();
			}
			c.close();
			myDatabase.close();
			return Arr;

		}

		return Arr;

	}
	
	
	public ArrayList<IpCamera> getIpCameraWithCameraID(int CameraID) {
		ArrayList<IpCamera> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("IpCamera", null, "CameraID="+CameraID,
				null, null, null, null);
		if (c.getCount() > 0) {
			Arr = new ArrayList<IpCamera>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				IpCamera data = new IpCamera();
				data.setCameraID(c.getInt(0));
				data.setBrand(c.getString(1));
				data.setType(c.getInt(2));
				data.setIp(c.getString(3));
				data.setPort(c.getInt(4));
				data.setMask(c.getString(5));
				data.setGateway(c.getString(6));
				data.setDns(c.getString(7));
				data.setIsP2P(c.getInt(8));
				data.setMediaPort(c.getInt(9));
				data.setSysVersions(c.getInt(10));				
				data.setAppVersions(c.getInt(11));
				data.setDhcpEnabled(c.getInt(12));				
				data.setCameraName(c.getString(13));
				data.setUIDForP2P(c.getInt(14));				
				data.setIsMainStream(c.getInt(15));
						
				Arr.add(data);
				c.moveToNext();
			}
			c.close();
			myDatabase.close();
			return Arr;

		}

		return Arr;

	}
}
