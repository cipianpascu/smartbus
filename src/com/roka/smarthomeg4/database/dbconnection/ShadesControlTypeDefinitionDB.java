package com.roka.smarthomeg4.database.dbconnection;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.roka.smarthomeg4.business.ShadesControlTypeDefinition;
import com.roka.smarthomeg4.database.Database;

public class ShadesControlTypeDefinitionDB {

	private Database myDatabase;
	private SQLiteDatabase sqLiteDatabase;

	public ShadesControlTypeDefinitionDB(Context context) {
		// TODO Auto-generated constructor stub
		myDatabase = new Database(context);

	}

	public ArrayList<ShadesControlTypeDefinition> getAllShadesControlTypeDefinition() {
		ArrayList<ShadesControlTypeDefinition> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("ShadesControlTypeDefinition", null, null,
				null, null, null, "SequenceNo");
		if (c.getCount() > 0) {
			Arr = new ArrayList<ShadesControlTypeDefinition>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				ShadesControlTypeDefinition data = new ShadesControlTypeDefinition();
				data.setControlType(c.getInt(0));
				data.setDESC(c.getString(1));
				
				Arr.add(data);
				c.moveToNext();
			}
			c.close();
			myDatabase.close();
			return Arr;

		}

		return Arr;

	}
	
	
	public ArrayList<ShadesControlTypeDefinition> getShadesControlTypeDefinitionWithControlType(int ControlType) {
		ArrayList<ShadesControlTypeDefinition> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("ShadesControlTypeDefinition", null, "ControlType="+ControlType,
				null, null, null, "SequenceNo");
		if (c.getCount() > 0) {
			Arr = new ArrayList<ShadesControlTypeDefinition>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				ShadesControlTypeDefinition data = new ShadesControlTypeDefinition();
				data.setControlType(c.getInt(0));
				data.setDESC(c.getString(1));
				
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
