package com.roka.smarthomeg4.database.dbconnection;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.roka.smarthomeg4.business.Security;
import com.roka.smarthomeg4.database.Database;

public class SecurityDB {

	private Database myDatabase;
	private SQLiteDatabase sqLiteDatabase;

	public SecurityDB(Context context) {
		// TODO Auto-generated constructor stub
		myDatabase = new Database(context);

	}

	public ArrayList<Security> getAllSecurity() {
		ArrayList<Security> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("Security", null, null,
				null, null, null, null);
		if (c.getCount() > 0) {
			Arr = new ArrayList<Security>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				Security data = new Security();
				data.setSubnetID(c.getInt(0));
				data.setDeviceID(c.getInt(1));
				data.setZoneID(c.getInt(2));
				data.setZoneNameOfSecurity(c.getString(3));
				
				Arr.add(data);
				c.moveToNext();
			}
			c.close();
			myDatabase.close();
			return Arr;

		}

		return Arr;

	}
	
	
	public ArrayList<Security> getSecurityWithZoneID(int ZoneID) {
		ArrayList<Security> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("Security", null, "ZoneID="+ZoneID,
				null, null, null, null);
		if (c.getCount() > 0) {
			Arr = new ArrayList<Security>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				Security data = new Security();
				data.setSubnetID(c.getInt(0));
				data.setDeviceID(c.getInt(1));
				data.setZoneID(c.getInt(2));
				data.setZoneNameOfSecurity(c.getString(3));
				
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
