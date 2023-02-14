package com.roka.smarthomeg4.database.dbconnection;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.roka.smarthomeg4.business.ShadesCommands;
import com.roka.smarthomeg4.database.Database;

public class ShadesCommandsDB {

	
	private Database myDatabase;
	private SQLiteDatabase sqLiteDatabase;

	public ShadesCommandsDB(Context context) {
		// TODO Auto-generated constructor stub
		myDatabase = new Database(context);

	}

	public ArrayList<ShadesCommands> getAllShadesCommands() {
		ArrayList<ShadesCommands> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("ShadesCommands", null, null,
				null, null, null, "SequenceNo");
		if (c.getCount() > 0) {
			Arr = new ArrayList<ShadesCommands>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				ShadesCommands data = new ShadesCommands();
				data.setZoneID(c.getInt(0));
				data.setShadeID(c.getInt(1));
				data.setShadeControlType(c.getInt(2));
				data.setCommandID(c.getInt(3));
				data.setSequenceNo(c.getInt(4));
				data.setRemark(c.getString(5));
				data.setSubnetID(c.getInt(6));
				data.setDeviceID(c.getInt(7));
				data.setCommandTypeID(c.getInt(8));
				data.setFirstParameter(c.getInt(9));
				data.setSecondParameter(c.getInt(10));
				data.setThirdParameter(c.getInt(11));
				data.setDelayMillisecondAfterSend(c.getInt(12));
						
				Arr.add(data);
				c.moveToNext();
			}
			c.close();
			myDatabase.close();
			return Arr;

		}

		return Arr;

	}
	
	
	
	public ArrayList<ShadesCommands> getShadesCommandsWithZoneID(int ZoneID) {
		ArrayList<ShadesCommands> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("ShadesCommands", null, "ZoneID="+ZoneID,
				null, null, null, "SequenceNo");
		if (c.getCount() > 0) {
			Arr = new ArrayList<ShadesCommands>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				ShadesCommands data = new ShadesCommands();
				data.setZoneID(c.getInt(0));
				data.setShadeID(c.getInt(1));
				data.setShadeControlType(c.getInt(2));
				data.setCommandID(c.getInt(3));
				data.setSequenceNo(c.getInt(4));
				data.setRemark(c.getString(5));
				data.setSubnetID(c.getInt(6));
				data.setDeviceID(c.getInt(7));
				data.setCommandTypeID(c.getInt(8));
				data.setFirstParameter(c.getInt(9));
				data.setSecondParameter(c.getInt(10));
				data.setThirdParameter(c.getInt(11));
				data.setDelayMillisecondAfterSend(c.getInt(12));
						
				Arr.add(data);
				c.moveToNext();
			}
			c.close();
			myDatabase.close();
			return Arr;

		}

		return Arr;

	}
	
	public ArrayList<ShadesCommands> getShadesCommandsWithShadeID(int ShadeID) {
		ArrayList<ShadesCommands> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("ShadesCommands", null, "ShadeID="+ShadeID,
				null, null, null, "SequenceNo");
		if (c.getCount() > 0) {
			Arr = new ArrayList<ShadesCommands>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				ShadesCommands data = new ShadesCommands();
				data.setZoneID(c.getInt(0));
				data.setShadeID(c.getInt(1));
				data.setShadeControlType(c.getInt(2));
				data.setCommandID(c.getInt(3));
				data.setSequenceNo(c.getInt(4));
				data.setRemark(c.getString(5));
				data.setSubnetID(c.getInt(6));
				data.setDeviceID(c.getInt(7));
				data.setCommandTypeID(c.getInt(8));
				data.setFirstParameter(c.getInt(9));
				data.setSecondParameter(c.getInt(10));
				data.setThirdParameter(c.getInt(11));
				data.setDelayMillisecondAfterSend(c.getInt(12));
						
				Arr.add(data);
				c.moveToNext();
			}
			c.close();
			myDatabase.close();
			return Arr;

		}

		return Arr;

	}
	
	
	public ArrayList<ShadesCommands> getShadesCommandsWithCommandID(int CommandID) {
		ArrayList<ShadesCommands> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("ShadesCommands", null, "CommandID="+CommandID,
				null, null, null, "SequenceNo");
		if (c.getCount() > 0) {
			Arr = new ArrayList<ShadesCommands>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				ShadesCommands data = new ShadesCommands();
				data.setZoneID(c.getInt(0));
				data.setShadeID(c.getInt(1));
				data.setShadeControlType(c.getInt(2));
				data.setCommandID(c.getInt(3));
				data.setSequenceNo(c.getInt(4));
				data.setRemark(c.getString(5));
				data.setSubnetID(c.getInt(6));
				data.setDeviceID(c.getInt(7));
				data.setCommandTypeID(c.getInt(8));
				data.setFirstParameter(c.getInt(9));
				data.setSecondParameter(c.getInt(10));
				data.setThirdParameter(c.getInt(11));
				data.setDelayMillisecondAfterSend(c.getInt(12));
						
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
