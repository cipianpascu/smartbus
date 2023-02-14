package com.roka.smarthomeg4.database.dbconnection;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.roka.smarthomeg4.business.ZaudioInZone;
import com.roka.smarthomeg4.database.Database;

public class ZaudioInZoneDB {

	

	private Database myDatabase;
	private SQLiteDatabase sqLiteDatabase;

	public ZaudioInZoneDB(Context context) {
		// TODO Auto-generated constructor stub
		myDatabase = new Database(context);

	}

	public ArrayList<ZaudioInZone> getAllZaudioInZone() {
		ArrayList<ZaudioInZone> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("ZaudioInZone", null, null, null, null,
				null, null);
		if (c.getCount() > 0) {
			Arr = new ArrayList<ZaudioInZone>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				ZaudioInZone data = new ZaudioInZone();
				data.setZoneID(c.getInt(0));
				data.setSubnetID(c.getInt(1));
				data.setDeviceID(c.getInt(2));
				Arr.add(data);
				c.moveToNext();
			}
			c.close();
			myDatabase.close();
			return Arr;

		}

		return Arr;

	}
	
	public ArrayList<ZaudioInZone> getZaudioInZoneWithZoneID(int ZoneID) {
		ArrayList<ZaudioInZone> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("ZaudioInZone", null, "ZoneID="+ZoneID, null, null,
				null, null);
		if (c.getCount() > 0) {
			Arr = new ArrayList<ZaudioInZone>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				ZaudioInZone data = new ZaudioInZone();
				data.setZoneID(c.getInt(0));
				data.setSubnetID(c.getInt(1));
				data.setDeviceID(c.getInt(2));
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
