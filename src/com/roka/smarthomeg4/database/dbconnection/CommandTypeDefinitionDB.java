package com.roka.smarthomeg4.database.dbconnection;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.roka.smarthomeg4.business.CommandTypeDefinition;
import com.roka.smarthomeg4.database.Database;

public class CommandTypeDefinitionDB {

	

	private Database myDatabase;
	private SQLiteDatabase sqLiteDatabase;

	public CommandTypeDefinitionDB(Context context) {
		// TODO Auto-generated constructor stub
		myDatabase = new Database(context);

	}

	public ArrayList<CommandTypeDefinition> getAllCommandTypeDefinition() {
		ArrayList<CommandTypeDefinition> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("CommandTypeDefinition", null, null,
				null, null, null, null);
		if (c.getCount() > 0) {
			Arr = new ArrayList<CommandTypeDefinition>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				CommandTypeDefinition data = new CommandTypeDefinition();
				data.setCommandTypeID(c.getInt(0));				
				data.setName(c.getString(1));
				Arr.add(data);
				c.moveToNext();
			}
			c.close();
			myDatabase.close();
			return Arr;

		}

		return Arr;

	}
	
	
	public ArrayList<CommandTypeDefinition> getCommandTypeDefinitionWithId(int CommandTypeID) {
		ArrayList<CommandTypeDefinition> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("CommandTypeDefinition", null, "CommandTypeID="+CommandTypeID,
				null, null, null, null);
		if (c.getCount() > 0) {
			Arr = new ArrayList<CommandTypeDefinition>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				CommandTypeDefinition data = new CommandTypeDefinition();
				data.setCommandTypeID(c.getInt(0));				
				data.setName(c.getString(1));
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
