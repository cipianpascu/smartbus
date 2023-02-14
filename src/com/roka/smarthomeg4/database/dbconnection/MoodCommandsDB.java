package com.roka.smarthomeg4.database.dbconnection;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.roka.smarthomeg4.business.CommandParameters;
import com.roka.smarthomeg4.business.MoodCommands;
import com.roka.smarthomeg4.database.Database;

public class MoodCommandsDB {

	
	private Database myDatabase;
	private SQLiteDatabase sqLiteDatabase;

	public MoodCommandsDB(Context context) {
		// TODO Auto-generated constructor stub
		myDatabase = new Database(context);

	}

	
	
	
	public long insertMoodInZone(MoodCommands moodCommands) {
		//
		sqLiteDatabase = myDatabase.OpenDatabase();
		long n = 0;
		ContentValues contentValues = new ContentValues();
		contentValues.put("ZoneID", moodCommands.getZoneID());
		contentValues.put("MoodID", moodCommands.getMoodID());
		contentValues.put("CommandID", moodCommands.getCommandID());
		contentValues.put("SequenceNo", moodCommands.getSequenceNo());
		contentValues.put("Remark", moodCommands.getRemark());
		contentValues.put("SubnetID", moodCommands.getSubnetID());

		contentValues.put("DeviceID", moodCommands.getDeviceID());
		contentValues.put("CommandTypeID", moodCommands.getCommandTypeID());
		contentValues.put("FirstParameter", moodCommands.getFirstParameter());
		contentValues.put("SecondParameter", moodCommands.getSecondParameter());
		contentValues.put("ThirdParameter", moodCommands.getThirdParameter());
		contentValues.put("DelayMillisecondAfterSend", moodCommands.getDelayMillisecondAfterSend());
		
		
		if (!sqLiteDatabase.isOpen())
			sqLiteDatabase = myDatabase.OpenDatabase();
		n = sqLiteDatabase.insert("MoodCommands", null, contentValues);

		myDatabase.close();
		return n;
	}
	
	
	public ArrayList<CommandParameters> getAllMoodCommands() {
		ArrayList<CommandParameters> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("MoodCommands", null, null,
				null, null, null, "SequenceNo");
		if (c.getCount() > 0) {
			Arr = new ArrayList<CommandParameters>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				CommandParameters data = new CommandParameters();
				data.setZoneId(c.getInt(0));
				data.setId(c.getInt(1));
				data.setCommandID(c.getInt(2));
				data.setSequenceNo(c.getInt(3));
				data.setRemark(c.getString(4));
				data.setSubnetID((byte)c.getInt(5));
				data.setDeviceID((byte)c.getInt(6));
				data.setCommandTypeID(c.getInt(7));
				data.setFirstParameter(c.getInt(8));
				data.setSecondParameter(c.getInt(9));
				data.setThirdParameter(c.getInt(10));
				data.setDelayMillisecondAfterSend(c.getInt(11));
						
				Arr.add(data);
				c.moveToNext();
			}
			c.close();
			myDatabase.close();
			return Arr;

		}

		return Arr;

	}
	
	
	

	public ArrayList<CommandParameters> getMoodCommandsWithZoneID(int ZoneID) {
		ArrayList<CommandParameters> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("MoodCommands", null, "ZoneID="+ZoneID,
				null, null, null, "SequenceNo");
		if (c.getCount() > 0) {
			Arr = new ArrayList<CommandParameters>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				CommandParameters data = new CommandParameters();
				data.setZoneId(c.getInt(0));
				data.setId(c.getInt(1));
				data.setCommandID(c.getInt(2));
				data.setSequenceNo(c.getInt(3));
				data.setRemark(c.getString(4));
				data.setSubnetID((byte)c.getInt(5));
				data.setDeviceID((byte)c.getInt(6));
				data.setCommandTypeID(c.getInt(7));
				data.setFirstParameter(c.getInt(8));
				data.setSecondParameter(c.getInt(9));
				data.setThirdParameter(c.getInt(10));
				data.setDelayMillisecondAfterSend(c.getInt(11));
				Arr.add(data);
				c.moveToNext();
			}
			c.close();
			myDatabase.close();
			return Arr;

		}

		return Arr;

	}
	
	
	public ArrayList<CommandParameters> getMoodCommandsWithMoodID(int MoodID) {
		ArrayList<CommandParameters> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("MoodCommands", null, "MoodID="+MoodID,
				null, null, null, "SequenceNo");
		if (c.getCount() > 0) {
			Arr = new ArrayList<CommandParameters>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				CommandParameters data = new CommandParameters();
				data.setZoneId(c.getInt(0));
				data.setId(c.getInt(1));
				data.setCommandID(c.getInt(2));
				data.setSequenceNo(c.getInt(3));
				data.setRemark(c.getString(4));
				data.setSubnetID((byte)c.getInt(5));
				data.setDeviceID((byte)c.getInt(6));
				data.setCommandTypeID(c.getInt(7));
				data.setFirstParameter(c.getInt(8));
				data.setSecondParameter(c.getInt(9));
				data.setThirdParameter(c.getInt(10));
				data.setDelayMillisecondAfterSend(c.getInt(11));
						
				Arr.add(data);
				c.moveToNext();
			}
			c.close();
			myDatabase.close();
			return Arr;

		}

		return Arr;

	}
	
	
	public ArrayList<CommandParameters> getMoodCommandsWithCommandID(int CommandID) {
		ArrayList<CommandParameters> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("MoodCommands", null, "CommandID="+CommandID,
				null, null, null, "SequenceNo");
		if (c.getCount() > 0) {
			Arr = new ArrayList<CommandParameters>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				CommandParameters data = new CommandParameters();
				data.setZoneId(c.getInt(0));
				data.setId(c.getInt(1));
				data.setCommandID(c.getInt(2));
				data.setSequenceNo(c.getInt(3));
				data.setRemark(c.getString(4));
				data.setSubnetID((byte)c.getInt(5));
				data.setDeviceID((byte)c.getInt(6));
				data.setCommandTypeID(c.getInt(7));
				data.setFirstParameter(c.getInt(8));
				data.setSecondParameter(c.getInt(9));
				data.setThirdParameter(c.getInt(10));
				data.setDelayMillisecondAfterSend(c.getInt(11));
						
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
