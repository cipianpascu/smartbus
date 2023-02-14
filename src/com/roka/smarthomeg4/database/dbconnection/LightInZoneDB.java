package com.roka.smarthomeg4.database.dbconnection;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.roka.smarthomeg4.business.LightInZone;
import com.roka.smarthomeg4.database.Database;

public class LightInZoneDB {

	
	
	private Database myDatabase;
	private SQLiteDatabase sqLiteDatabase;

	public LightInZoneDB(Context context) {
		// TODO Auto-generated constructor stub
		myDatabase = new Database(context);

	}

	public ArrayList<LightInZone> getAllLightInZone() {
		ArrayList<LightInZone> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("LightInZone", null, null,
				null, null, null, "SequenceNo");
		if (c.getCount() > 0) {
			Arr = new ArrayList<LightInZone>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				LightInZone data = new LightInZone();
				data.setZoneID(c.getInt(0));
				data.setLightID(c.getInt(1));
				data.setLightRemark(c.getString(2));
				data.setSubnetID(c.getInt(3));
				data.setDeviceID(c.getInt(4));
				data.setChannelNo(c.getInt(5));
				data.setCanDim(c.getInt(6));
				data.setLightTypeID(c.getInt(7));
				data.setSequenceNo(c.getInt(8));
						
				Arr.add(data);
				c.moveToNext();
			}
			c.close();
			myDatabase.close();
			return Arr;

		}

		return Arr;

	}
	
	
	public ArrayList<LightInZone> getLightInZoneWithZoneID(int ZoneID) {
		ArrayList<LightInZone> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("LightInZone", null, "ZoneID="+ZoneID,
				null, null, null, "SequenceNo");
		if (c.getCount() > 0) {
			Arr = new ArrayList<LightInZone>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				LightInZone data = new LightInZone();
				data.setZoneID(c.getInt(0));
				data.setLightID(c.getInt(1));
				data.setLightRemark(c.getString(2));
				data.setSubnetID(c.getInt(3));
				data.setDeviceID(c.getInt(4));
				data.setChannelNo(c.getInt(5));
				data.setCanDim(c.getInt(6));
				data.setLightTypeID(c.getInt(7));
				data.setSequenceNo(c.getInt(8));
						
				Arr.add(data);
				c.moveToNext();
			}
			c.close();
			myDatabase.close();
			return Arr;

		}

		return Arr;

	}
	
	

	public ArrayList<LightInZone> getLightInZoneWithLightID(int LightID) {
		ArrayList<LightInZone> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("LightInZone", null, "LightID="+LightID,
				null, null, null, "SequenceNo");
		if (c.getCount() > 0) {
			Arr = new ArrayList<LightInZone>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				LightInZone data = new LightInZone();
				data.setZoneID(c.getInt(0));
				data.setLightID(c.getInt(1));
				data.setLightRemark(c.getString(2));
				data.setSubnetID(c.getInt(3));
				data.setDeviceID(c.getInt(4));
				data.setChannelNo(c.getInt(5));
				data.setCanDim(c.getInt(6));
				data.setLightTypeID(c.getInt(7));
				data.setSequenceNo(c.getInt(8));
						
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
