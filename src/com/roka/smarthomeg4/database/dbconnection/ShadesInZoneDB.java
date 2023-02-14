package com.roka.smarthomeg4.database.dbconnection;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.roka.smarthomeg4.business.ShadesInZone;
import com.roka.smarthomeg4.database.Database;

public class ShadesInZoneDB {

	

	private Database myDatabase;
	private SQLiteDatabase sqLiteDatabase;

	public ShadesInZoneDB(Context context) {
		// TODO Auto-generated constructor stub
		myDatabase = new Database(context);

	}

	public ArrayList<ShadesInZone> getAllShadesInZone() {
		ArrayList<ShadesInZone> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("ShadesInZone", null, null, null, null,
				null, "SequenceNo");
		if (c.getCount() > 0) {
			Arr = new ArrayList<ShadesInZone>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				ShadesInZone data = new ShadesInZone();
				data.setZoneID(c.getInt(0));
				data.setShadeID(c.getInt(1));
				data.setShadeName(c.getString(2));
				data.setShadeIconID(c.getInt(3));
				data.setSequenceNo(c.getInt(4));
				data.setHasStop(c.getInt(5));
				data.setHasRotate(c.getInt(6));

				Arr.add(data);
				c.moveToNext();
			}
			c.close();
			myDatabase.close();
			return Arr;

		}

		return Arr;

	}
	
	
	public ArrayList<ShadesInZone> getShadesInZoneWithZoneID(int ZoneID) {
		ArrayList<ShadesInZone> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("ShadesInZone", null, "ZoneID="+ZoneID, null, null,
				null, "SequenceNo");
		if (c.getCount() > 0) {
			Arr = new ArrayList<ShadesInZone>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				ShadesInZone data = new ShadesInZone();
				data.setZoneID(c.getInt(0));
				data.setShadeID(c.getInt(1));
				data.setShadeName(c.getString(2));
				data.setShadeIconID(c.getInt(3));
				data.setSequenceNo(c.getInt(4));
				data.setHasStop(c.getInt(5));
				data.setHasRotate(c.getInt(6));

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
