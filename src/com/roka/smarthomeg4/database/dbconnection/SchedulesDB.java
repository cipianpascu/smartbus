package com.roka.smarthomeg4.database.dbconnection;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.roka.smarthomeg4.business.Schedules;
import com.roka.smarthomeg4.database.DBHandler;
import com.roka.smarthomeg4.database.Database;

public class SchedulesDB {
	private Database myDatabase;
	private SQLiteDatabase sqLiteDatabase;

	public SchedulesDB(Context context) {
		// TODO Auto-generated constructor stub
		myDatabase = new Database(context);

	}

	public ArrayList<Schedules> getAllSchedules() {
		ArrayList<Schedules> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("Schedules", null, null, null, null,
				null, null);
		if (c.getCount() > 0) {
			Arr = new ArrayList<Schedules>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				Schedules data = new Schedules();
				data.setScheduleID(c.getInt(0));
				data.setScheduleName(c.getString(1));
				data.setEnabledSchedule(DBHandler.getBool(c.getInt(2)));
				data.setControlledItemID(c.getInt(3));
				data.setZoneID(c.getInt(4));
				data.setFrequencyID(c.getInt(5));
				data.setWithSunday(DBHandler.getBool(c.getInt(6)));
				data.setWithMonday(DBHandler.getBool(c.getInt(7)));
				data.setWithTuesday(DBHandler.getBool(c.getInt(8)));
				data.setWithWednesday(DBHandler.getBool(c.getInt(9)));
				data.setWithThursday(DBHandler.getBool(c.getInt(10)));
				data.setWithFriday(DBHandler.getBool(c.getInt(11)));
				data.setWithSaturday(DBHandler.getBool(c.getInt(12)));
				data.setExecutionHours(c.getInt(13));
				data.setExecutionMins(c.getInt(14));
				data.setExecutionDate(c.getString(15));
				data.setHaveSound(DBHandler.getBool(c.getInt(16)));
				
				Arr.add(data);
				c.moveToNext();
			}
			c.close();
			myDatabase.close();
			return Arr;

		}

		return Arr;

	}
	
	
	public ArrayList<Schedules> getSchedulesWithScheduleID(int ScheduleID) {
		ArrayList<Schedules> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("Schedules", null, "ScheduleID="+ScheduleID, null, null,
				null, null);
		if (c.getCount() > 0) {
			Arr = new ArrayList<Schedules>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				Schedules data = new Schedules();
				data.setScheduleID(c.getInt(0));
				data.setScheduleName(c.getString(1));
				data.setEnabledSchedule(DBHandler.getBool(c.getInt(2)));
				data.setControlledItemID(c.getInt(3));
				data.setZoneID(c.getInt(4));
				data.setFrequencyID(c.getInt(5));
				data.setWithSunday(DBHandler.getBool(c.getInt(6)));
				data.setWithMonday(DBHandler.getBool(c.getInt(7)));
				data.setWithTuesday(DBHandler.getBool(c.getInt(8)));
				data.setWithWednesday(DBHandler.getBool(c.getInt(9)));
				data.setWithThursday(DBHandler.getBool(c.getInt(10)));
				data.setWithFriday(DBHandler.getBool(c.getInt(11)));
				data.setWithSaturday(DBHandler.getBool(c.getInt(12)));
				data.setExecutionHours(c.getInt(13));
				data.setExecutionMins(c.getInt(14));
				data.setExecutionDate(c.getString(15));
				data.setHaveSound(DBHandler.getBool(c.getInt(16)));
				
				Arr.add(data);
				c.moveToNext();
			}
			c.close();
			myDatabase.close();
			return Arr;

		}

		return Arr;

	}
	
	
	public ArrayList<Schedules> getSchedulesWithControlledItemID(int ControlledItemID) {
		ArrayList<Schedules> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("Schedules", null, "ControlledItemID="+ControlledItemID, null, null,
				null, null);
		if (c.getCount() > 0) {
			Arr = new ArrayList<Schedules>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				Schedules data = new Schedules();
				data.setScheduleID(c.getInt(0));
				data.setScheduleName(c.getString(1));
				data.setEnabledSchedule(DBHandler.getBool(c.getInt(2)));
				data.setControlledItemID(c.getInt(3));
				data.setZoneID(c.getInt(4));
				data.setFrequencyID(c.getInt(5));
				data.setWithSunday(DBHandler.getBool(c.getInt(6)));
				data.setWithMonday(DBHandler.getBool(c.getInt(7)));
				data.setWithTuesday(DBHandler.getBool(c.getInt(8)));
				data.setWithWednesday(DBHandler.getBool(c.getInt(9)));
				data.setWithThursday(DBHandler.getBool(c.getInt(10)));
				data.setWithFriday(DBHandler.getBool(c.getInt(11)));
				data.setWithSaturday(DBHandler.getBool(c.getInt(12)));
				data.setExecutionHours(c.getInt(13));
				data.setExecutionMins(c.getInt(14));
				data.setExecutionDate(c.getString(15));
				data.setHaveSound(DBHandler.getBool(c.getInt(16)));
				
				Arr.add(data);
				c.moveToNext();
			}
			c.close();
			myDatabase.close();
			return Arr;

		}

		return Arr;

	}
	
	
	public ArrayList<Schedules> getSchedulesWithZoneID(int ZoneID) {
		ArrayList<Schedules> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("Schedules", null, "ZoneID="+ZoneID, null, null,
				null, null);
		if (c.getCount() > 0) {
			Arr = new ArrayList<Schedules>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				Schedules data = new Schedules();
				data.setScheduleID(c.getInt(0));
				data.setScheduleName(c.getString(1));
				data.setEnabledSchedule(DBHandler.getBool(c.getInt(2)));
				data.setControlledItemID(c.getInt(3));
				data.setZoneID(c.getInt(4));
				data.setFrequencyID(c.getInt(5));
				data.setWithSunday(DBHandler.getBool(c.getInt(6)));
				data.setWithMonday(DBHandler.getBool(c.getInt(7)));
				data.setWithTuesday(DBHandler.getBool(c.getInt(8)));
				data.setWithWednesday(DBHandler.getBool(c.getInt(9)));
				data.setWithThursday(DBHandler.getBool(c.getInt(10)));
				data.setWithFriday(DBHandler.getBool(c.getInt(11)));
				data.setWithSaturday(DBHandler.getBool(c.getInt(12)));
				data.setExecutionHours(c.getInt(13));
				data.setExecutionMins(c.getInt(14));
				data.setExecutionDate(c.getString(15));
				data.setHaveSound(DBHandler.getBool(c.getInt(16)));
				
				Arr.add(data);
				c.moveToNext();
			}
			c.close();
			myDatabase.close();
			return Arr;

		}

		return Arr;

	}
	
	
	public ArrayList<Schedules> getSchedulesWithFrequencyID(int FrequencyID) {
		ArrayList<Schedules> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("Schedules", null, "FrequencyID="+FrequencyID, null, null,
				null, null);
		if (c.getCount() > 0) {
			Arr = new ArrayList<Schedules>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				Schedules data = new Schedules();
				data.setScheduleID(c.getInt(0));
				data.setScheduleName(c.getString(1));
				data.setEnabledSchedule(DBHandler.getBool(c.getInt(2)));
				data.setControlledItemID(c.getInt(3));
				data.setZoneID(c.getInt(4));
				data.setFrequencyID(c.getInt(5));
				data.setWithSunday(DBHandler.getBool(c.getInt(6)));
				data.setWithMonday(DBHandler.getBool(c.getInt(7)));
				data.setWithTuesday(DBHandler.getBool(c.getInt(8)));
				data.setWithWednesday(DBHandler.getBool(c.getInt(9)));
				data.setWithThursday(DBHandler.getBool(c.getInt(10)));
				data.setWithFriday(DBHandler.getBool(c.getInt(11)));
				data.setWithSaturday(DBHandler.getBool(c.getInt(12)));
				data.setExecutionHours(c.getInt(13));
				data.setExecutionMins(c.getInt(14));
				data.setExecutionDate(c.getString(15));
				data.setHaveSound(DBHandler.getBool(c.getInt(16)));
				
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
