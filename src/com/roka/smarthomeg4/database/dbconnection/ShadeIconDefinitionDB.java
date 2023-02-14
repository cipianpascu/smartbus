package com.roka.smarthomeg4.database.dbconnection;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.roka.smarthomeg4.business.ShadeIconDefinition;
import com.roka.smarthomeg4.database.Database;

public class ShadeIconDefinitionDB {

	private Database myDatabase;
	private SQLiteDatabase sqLiteDatabase;

	public ShadeIconDefinitionDB(Context context) {
		// TODO Auto-generated constructor stub
		myDatabase = new Database(context);

	}

	public ArrayList<ShadeIconDefinition> getAllShadeIconDefinition() {
		ArrayList<ShadeIconDefinition> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("ShadeIconDefinition", null, null,
				null, null, null, null);
		if (c.getCount() > 0) {
			Arr = new ArrayList<ShadeIconDefinition>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				ShadeIconDefinition data = new ShadeIconDefinition();
				data.setShadeIconID(c.getInt(0));
				data.setRemark(c.getString(1));
				data.setIconName(c.getString(2));
				
				
				Arr.add(data);
				c.moveToNext();
			}
			c.close();
			myDatabase.close();
			return Arr;

		}

		return Arr;

	}
	
	
	public ArrayList<ShadeIconDefinition> getShadeIconDefinitionWithShadeIconID(int ShadeIconID) {
		ArrayList<ShadeIconDefinition> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("ShadeIconDefinition", null, "ShadeIconID="+ShadeIconID,
				null, null, null, null);
		if (c.getCount() > 0) {
			Arr = new ArrayList<ShadeIconDefinition>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				ShadeIconDefinition data = new ShadeIconDefinition();
				data.setShadeIconID(c.getInt(0));
				data.setRemark(c.getString(1));
				data.setIconName(c.getString(2));
				
				
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
