package com.roka.smarthomeg4.database.dbconnection;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.roka.smarthomeg4.business.ScheduleCommands;
import com.roka.smarthomeg4.database.Database;

public class ScheduleCommandsDB {

	
	private Database myDatabase;
	private SQLiteDatabase sqLiteDatabase;

	public ScheduleCommandsDB(Context context) {
		// TODO Auto-generated constructor stub
		myDatabase = new Database(context);

	}

	public ArrayList<ScheduleCommands> getAllScheduleCommands() {
		ArrayList<ScheduleCommands> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("ScheduleCommands", null, null,
				null, null, null, null);
		if (c.getCount() > 0) {
			Arr = new ArrayList<ScheduleCommands>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				ScheduleCommands data = new ScheduleCommands();
				data.setScheduleID(c.getInt(0));
				data.setCommandID(c.getInt(1));
				data.setFstPara(c.getInt(2));
				data.setSndPara(c.getInt(3));
				data.setThirdPara(c.getInt(4));
				data.setFourthPara(c.getInt(5));
				data.setFivthPara(c.getInt(6));
				data.setSixthPara(c.getInt(7));
				
				Arr.add(data);
				c.moveToNext();
			}
			c.close();
			myDatabase.close();
			return Arr;

		}

		return Arr;

	}
	
	
	public ArrayList<ScheduleCommands> getScheduleCommandsWithScheduleID(int ScheduleID) {
		ArrayList<ScheduleCommands> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("ScheduleCommands", null, "ScheduleID="+ScheduleID,
				null, null, null, null);
		if (c.getCount() > 0) {
			Arr = new ArrayList<ScheduleCommands>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				ScheduleCommands data = new ScheduleCommands();
				data.setScheduleID(c.getInt(0));
				data.setCommandID(c.getInt(1));
				data.setFstPara(c.getInt(2));
				data.setSndPara(c.getInt(3));
				data.setThirdPara(c.getInt(4));
				data.setFourthPara(c.getInt(5));
				data.setFivthPara(c.getInt(6));
				data.setSixthPara(c.getInt(7));
				
				Arr.add(data);
				c.moveToNext();
			}
			c.close();
			myDatabase.close();
			return Arr;

		}

		return Arr;

	}
	
	public ArrayList<ScheduleCommands> getScheduleCommandsWithCommandID(int CommandID) {
		ArrayList<ScheduleCommands> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("ScheduleCommands", null, "CommandID="+CommandID,
				null, null, null, null);
		if (c.getCount() > 0) {
			Arr = new ArrayList<ScheduleCommands>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				ScheduleCommands data = new ScheduleCommands();
				data.setScheduleID(c.getInt(0));
				data.setCommandID(c.getInt(1));
				data.setFstPara(c.getInt(2));
				data.setSndPara(c.getInt(3));
				data.setThirdPara(c.getInt(4));
				data.setFourthPara(c.getInt(5));
				data.setFivthPara(c.getInt(6));
				data.setSixthPara(c.getInt(7));
				
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
