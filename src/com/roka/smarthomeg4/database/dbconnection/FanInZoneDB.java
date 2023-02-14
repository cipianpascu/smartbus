package com.roka.smarthomeg4.database.dbconnection;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.roka.smarthomeg4.business.FanInZone;
import com.roka.smarthomeg4.database.Database;

public class FanInZoneDB {

	private Database myDatabase;
	private SQLiteDatabase sqLiteDatabase;

	public FanInZoneDB(Context context) {
		// TODO Auto-generated constructor stub
		myDatabase = new Database(context);

	}

	public ArrayList<FanInZone> getAllFanInZone() {
		ArrayList<FanInZone> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("FanInZone", null, null, null, null,
				null, "SequenceNO");
		if (c.getCount() > 0) {
			Arr = new ArrayList<FanInZone>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				FanInZone data = new FanInZone();
				data.setZoneID(c.getInt(0));
				data.setFanID(c.getInt(1));
				data.setFanName(c.getString(2));
				data.setSubnetID(c.getInt(3));
				data.setDeviceID(c.getInt(4));
				data.setChannelNO(c.getInt(5));
				data.setFanTypeID(c.getInt(6));
				data.setSequenceNO(c.getInt(7));
				data.setRemark(c.getString(8));
				data.setReserved1(c.getInt(9));
				data.setReserved2(c.getInt(10));
				data.setReserved3(c.getInt(11));
				data.setReserved4(c.getInt(12));
				data.setReserved5(c.getInt(13));
				Arr.add(data);
				c.moveToNext();
			}
			c.close();
			myDatabase.close();
			return Arr;

		}

		return Arr;

	}

	public ArrayList<FanInZone> getFanInZoneWithZone(int zoneId) {
		ArrayList<FanInZone> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("FanInZone", null, "ZoneID=" + zoneId,
				null, null, null, "SequenceNO");
		if (c.getCount() > 0) {
			Arr = new ArrayList<FanInZone>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				FanInZone data = new FanInZone();
				data.setZoneID(c.getInt(0));
				data.setFanID(c.getInt(1));
				data.setFanName(c.getString(2));
				data.setSubnetID(c.getInt(3));
				data.setDeviceID(c.getInt(4));
				data.setChannelNO(c.getInt(5));
				data.setFanTypeID(c.getInt(6));
				data.setSequenceNO(c.getInt(7));
				data.setRemark(c.getString(8));
				data.setReserved1(c.getInt(9));
				data.setReserved2(c.getInt(10));
				data.setReserved3(c.getInt(11));
				data.setReserved4(c.getInt(12));
				data.setReserved5(c.getInt(13));
				Arr.add(data);
				c.moveToNext();
			}
			c.close();
			myDatabase.close();
			return Arr;

		}

		return Arr;

	}

	public ArrayList<FanInZone> getFanInZoneWithFanID(int fandId) {
		ArrayList<FanInZone> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("FanInZone", null, "FanID=" + fandId,
				null, null, null, "SequenceNO");
		if (c.getCount() > 0) {
			Arr = new ArrayList<FanInZone>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				FanInZone data = new FanInZone();
				data.setZoneID(c.getInt(0));
				data.setFanID(c.getInt(1));
				data.setFanName(c.getString(2));
				data.setSubnetID(c.getInt(3));
				data.setDeviceID(c.getInt(4));
				data.setChannelNO(c.getInt(5));
				data.setFanTypeID(c.getInt(6));
				data.setSequenceNO(c.getInt(7));
				data.setRemark(c.getString(8));
				data.setReserved1(c.getInt(9));
				data.setReserved2(c.getInt(10));
				data.setReserved3(c.getInt(11));
				data.setReserved4(c.getInt(12));
				data.setReserved5(c.getInt(13));
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
