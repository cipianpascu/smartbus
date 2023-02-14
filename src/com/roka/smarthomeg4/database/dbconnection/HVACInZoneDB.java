package com.roka.smarthomeg4.database.dbconnection;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.roka.smarthomeg4.business.HVACInZone;
import com.roka.smarthomeg4.database.Database;

public class HVACInZoneDB {

	private Database myDatabase;
	private SQLiteDatabase sqLiteDatabase;

	public HVACInZoneDB(Context context) {
		// TODO Auto-generated constructor stub
		myDatabase = new Database(context);

	}

	public ArrayList<HVACInZone> getAllHVACInZone() {
		ArrayList<HVACInZone> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("HVACInZone", null, null, null, null,
				null, "SequenceNo");
		if (c.getCount() > 0) {
			Arr = new ArrayList<HVACInZone>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				HVACInZone data = new HVACInZone();
				data.setZoneID(c.getInt(0));
				data.setSubnetID(c.getInt(1));
				data.setDeviceID(c.getInt(2));
				data.setACNumber(c.getInt(3));
				data.setACTypeID(c.getInt(4));
				data.setACSyncNo(c.getInt(5));
				data.setSequenceNo(c.getInt(6));
				data.setRemark(c.getString(7));
				Arr.add(data);
				c.moveToNext();
			}
			c.close();
			myDatabase.close();
			return Arr;

		}

		return Arr;

	}
	
	
	public ArrayList<HVACInZone> getHVACInZoneWithZone(int ZoneID) {
		ArrayList<HVACInZone> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("HVACInZone", null, "ZoneID="+ZoneID, null, null,
				null, "SequenceNo");
		if (c.getCount() > 0) {
			Arr = new ArrayList<HVACInZone>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				HVACInZone data = new HVACInZone();
				data.setZoneID(c.getInt(0));
				data.setSubnetID(c.getInt(1));
				data.setDeviceID(c.getInt(2));
				data.setACNumber(c.getInt(3));
				data.setACTypeID(c.getInt(4));
				data.setACSyncNo(c.getInt(5));
				data.setSequenceNo(c.getInt(6));
				data.setRemark(c.getString(7));
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
