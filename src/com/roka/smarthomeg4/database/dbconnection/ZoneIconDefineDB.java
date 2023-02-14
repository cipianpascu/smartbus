package com.roka.smarthomeg4.database.dbconnection;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.roka.smarthomeg4.business.ZoneIconDefine;
import com.roka.smarthomeg4.database.Database;

public class ZoneIconDefineDB {

	

	private Database myDatabase;
	private SQLiteDatabase sqLiteDatabase;

	public ZoneIconDefineDB(Context context) {
		// TODO Auto-generated constructor stub
		myDatabase = new Database(context);

	}

	public ArrayList<ZoneIconDefine> getAllZoneIconDefine() {
		ArrayList<ZoneIconDefine> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("ZoneIconDefine", null, null, null, null,
				null, null);
		if (c.getCount() > 0) {
			Arr = new ArrayList<ZoneIconDefine>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				ZoneIconDefine data = new ZoneIconDefine();
				data.setZoneIconID(c.getInt(0));
				data.setIconName(c.getString(1));
				
				Arr.add(data);
				c.moveToNext();
			}
			c.close();
			myDatabase.close();
			return Arr;

		}

		return Arr;

	}

	
	public ArrayList<ZoneIconDefine> getZoneIconDefineWithZoneIconID(int ZoneIconID) {
		ArrayList<ZoneIconDefine> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("ZoneIconDefine", null, "ZoneIconID="+ZoneIconID, null, null,
				null, null);
		if (c.getCount() > 0) {
			Arr = new ArrayList<ZoneIconDefine>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				ZoneIconDefine data = new ZoneIconDefine();
				data.setZoneIconID(c.getInt(0));
				data.setIconName(c.getString(1));
				
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
