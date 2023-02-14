package com.roka.smarthomeg4.database.dbconnection;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.roka.smarthomeg4.business.LightTypeDefinition;
import com.roka.smarthomeg4.database.Database;

public class LightTypeDefinitionDB {
	
	private Database myDatabase;
	private SQLiteDatabase sqLiteDatabase;

	public LightTypeDefinitionDB(Context context) {
		// TODO Auto-generated constructor stub
		myDatabase = new Database(context);

	}

	public ArrayList<LightTypeDefinition> getAllLightTypeDefinition() {
		ArrayList<LightTypeDefinition> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("LightTypeDefinition", null, null,
				null, null, null, null);
		if (c.getCount() > 0) {
			Arr = new ArrayList<LightTypeDefinition>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				LightTypeDefinition data = new LightTypeDefinition();
				data.setLightTypeID(c.getInt(0));
				data.setRemark(c.getString(1));
				data.setIconNameforOn(c.getString(2));
				data.setIconNameforOff(c.getString(3));				
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
