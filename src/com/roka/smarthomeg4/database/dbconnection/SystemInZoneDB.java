package com.roka.smarthomeg4.database.dbconnection;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.roka.smarthomeg4.business.SystemInZone;
import com.roka.smarthomeg4.database.Database;

public class SystemInZoneDB {
	private Database myDatabase;
	private SQLiteDatabase sqLiteDatabase;

	public SystemInZoneDB(Context context) {
		// TODO Auto-generated constructor stub
		myDatabase = new Database(context);

	}

	public ArrayList<SystemInZone> getAllSystemInZone() {
		ArrayList<SystemInZone> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("SystemInZone", null, null, null,
				null, null, null);
		if (c.getCount() > 0) {
			Arr = new ArrayList<SystemInZone>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				SystemInZone data = new SystemInZone();
				data.setZoneID(c.getInt(0));
				data.setSystemID(c.getInt(1));
				
				Arr.add(data);
				c.moveToNext();
			}
			c.close();
			myDatabase.close();
			return Arr;

		}

		return Arr;
	}
	public ArrayList<SystemInZone> getSystemInZoneWithZoneID(int ZoneID) {
		ArrayList<SystemInZone> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("SystemInZone", null, "ZoneID="+ZoneID, null,
				null, null, "SystemID");
		if (c.getCount() > 0) {
			Arr = new ArrayList<SystemInZone>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				SystemInZone data = new SystemInZone();
				data.setZoneID(c.getInt(0));
				data.setSystemID(c.getInt(1));
				
				Arr.add(data);
				c.moveToNext();
			}
			c.close();
			myDatabase.close();
			return Arr;

		}

		return Arr;
	}
	
	
	public ArrayList<SystemInZone> getSystemInZoneWithZoneIDNoMood(int ZoneID) {
		ArrayList<SystemInZone> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("SystemInZone", null, "ZoneID="+ZoneID, null,
				null, null, "SystemID");
		if (c.getCount() > 0) {
			Arr = new ArrayList<SystemInZone>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				SystemInZone data = new SystemInZone();
				data.setZoneID(c.getInt(0));
				data.setSystemID(c.getInt(1));
				if(data.getSystemID()==10){
					c.moveToNext();
					continue;
				}
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
