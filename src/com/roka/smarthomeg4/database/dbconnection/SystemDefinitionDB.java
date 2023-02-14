package com.roka.smarthomeg4.database.dbconnection;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.roka.smarthomeg4.business.SystemDefinition;
import com.roka.smarthomeg4.database.Database;

public class SystemDefinitionDB {

	private Database myDatabase;
	private SQLiteDatabase sqLiteDatabase;

	public SystemDefinitionDB(Context context) {
		// TODO Auto-generated constructor stub
		myDatabase = new Database(context);

	}

	public ArrayList<SystemDefinition> getAllSystemDefinition() {
		ArrayList<SystemDefinition> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("SystemDefinition", null, null, null,
				null, null, null);
		if (c.getCount() > 0) {
			Arr = new ArrayList<SystemDefinition>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				SystemDefinition data = new SystemDefinition();
				data.setSystemID(c.getInt(0));
				data.setSystemName(c.getString(1));
				Arr.add(data);
				c.moveToNext();
			}
			c.close();
			myDatabase.close();
			return Arr;

		}

		return Arr;
	}
	
	public ArrayList<SystemDefinition> getSystemDefinitionWithSystemID(int SystemID) {
		ArrayList<SystemDefinition> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("SystemDefinition", null, "SystemID="+SystemID, null,
				null, null, null);
		if (c.getCount() > 0) {
			Arr = new ArrayList<SystemDefinition>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				SystemDefinition data = new SystemDefinition();
				data.setSystemID(c.getInt(0));
				data.setSystemName(c.getString(1));
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
