package com.roka.smarthomeg4.database.dbconnection;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.roka.smarthomeg4.business.CommandParameters;
import com.roka.smarthomeg4.database.Database;

public class MacroCommandsDB {

	

	private Database myDatabase;
	private SQLiteDatabase sqLiteDatabase;

	public MacroCommandsDB(Context context) {
		// TODO Auto-generated constructor stub
		myDatabase = new Database(context);

	}

	public ArrayList<CommandParameters> getAllMacroCommands() {
		ArrayList<CommandParameters> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("MacroCommands", null, null,
				null, null, null, "SequenceNo");
		if (c.getCount() > 0) {
			Arr = new ArrayList<CommandParameters>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				CommandParameters data = new CommandParameters();
				data.setId(c.getInt(0));
				data.setSequenceNo(c.getInt(1));
				data.setRemark(c.getString(2));
				data.setSubnetID((byte)c.getInt(3));
				data.setDeviceID((byte)c.getInt(4));
				data.setCommandTypeID(c.getInt(5));
				data.setFirstParameter(c.getInt(6));
				data.setSecondParameter(c.getInt(7));
				data.setThirdParameter(c.getInt(8));
				data.setDelayMillisecondAfterSend(c.getInt(9));
						
				Arr.add(data);
				c.moveToNext();
			}
			c.close();
			myDatabase.close();
			return Arr;

		}

		return Arr;

	}
	
	
	public ArrayList<CommandParameters> getMacroCommandsWithMicroID(int MacroID) {
		ArrayList<CommandParameters> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("MacroCommands", null, "MacroID="+MacroID,
				null, null, null, "SequenceNo");
		if (c.getCount() > 0) {
			Arr = new ArrayList<CommandParameters>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				CommandParameters data = new CommandParameters();
				data.setId(c.getInt(0));
				data.setSequenceNo(c.getInt(1));
				data.setRemark(c.getString(2));
				data.setSubnetID((byte)c.getInt(3));
				data.setDeviceID((byte)c.getInt(4));
				data.setCommandTypeID(c.getInt(5));
				data.setFirstParameter(c.getInt(6));
				data.setSecondParameter(c.getInt(7));
				data.setThirdParameter(c.getInt(8));
				data.setDelayMillisecondAfterSend(c.getInt(9));
						
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