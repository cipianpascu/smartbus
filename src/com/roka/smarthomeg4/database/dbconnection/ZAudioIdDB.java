package com.roka.smarthomeg4.database.dbconnection;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.roka.smarthomeg4.business.ZAudioId;
import com.roka.smarthomeg4.database.Database;

public class ZAudioIdDB {

	
	private Database myDatabase;
	private SQLiteDatabase sqLiteDatabase;

	public ZAudioIdDB(Context context) {
		// TODO Auto-generated constructor stub
		myDatabase = new Database(context);

	}

	public ArrayList<ZAudioId> getAllZAudioId() {
		ArrayList<ZAudioId> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("ZAudioId", null, null, null, null,
				null, null);
		if (c.getCount() > 0) {
			Arr = new ArrayList<ZAudioId>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				ZAudioId data = new ZAudioId();
				data.setID(c.getInt(0));
				data.setZoneName(c.getString(1));
				data.setSubnetID(c.getInt(2));
				data.setDeviceID(c.getInt(3));
				Arr.add(data);
				c.moveToNext();
			}
			c.close();
			myDatabase.close();
			return Arr;

		}

		return Arr;

	}

	

	public ArrayList<ZAudioId> getZAudioIdWithID(int ID) {
		ArrayList<ZAudioId> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("ZAudioId", null, "ID="+ID, null, null,
				null, null);
		if (c.getCount() > 0) {
			Arr = new ArrayList<ZAudioId>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				ZAudioId data = new ZAudioId();
				data.setID(c.getInt(0));
				data.setZoneName(c.getString(1));
				data.setSubnetID(c.getInt(2));
				data.setDeviceID(c.getInt(3));
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
