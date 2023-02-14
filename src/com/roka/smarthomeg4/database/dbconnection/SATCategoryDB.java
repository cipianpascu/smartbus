package com.roka.smarthomeg4.database.dbconnection;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.roka.smarthomeg4.business.SATCategory;
import com.roka.smarthomeg4.database.Database;

public class SATCategoryDB {

	private Database myDatabase;
	private SQLiteDatabase sqLiteDatabase;

	public SATCategoryDB(Context context) {
		// TODO Auto-generated constructor stub
		myDatabase = new Database(context);
	}

	public ArrayList<SATCategory> getAllSATCategory() {
		ArrayList<SATCategory> appeList = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("SATCategory", null, null, null, null,
				null, "SequenceNo");
		// c=sqLiteDatabase.rawQuery("select ZoneID from AppleTVInZone", null);
		if (c.getCount() > 0) {
			appeList = new ArrayList<SATCategory>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				SATCategory data = new SATCategory();
				data.setCategoryID(c.getInt(0));
				data.setCategoryName(c.getString(1));
				data.setSequenceNo(c.getInt(2));
				data.setZoneID(c.getInt(3));

				appeList.add(data);
				c.moveToNext();
			}
			c.close();
			myDatabase.close();
			return appeList;

		}

		return appeList;
	}

	public ArrayList<SATCategory> getSATCategoryWithCategoryID(int CategoryID) {
		ArrayList<SATCategory> appeList = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("SATCategory", null, "CategoryID="+CategoryID, null, null,
				null, "SequenceNo");
		// c=sqLiteDatabase.rawQuery("select ZoneID from AppleTVInZone", null);
		if (c.getCount() > 0) {
			appeList = new ArrayList<SATCategory>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				SATCategory data = new SATCategory();
				data.setCategoryID(c.getInt(0));
				data.setCategoryName(c.getString(1));
				data.setSequenceNo(c.getInt(2));
				data.setZoneID(c.getInt(3));

				appeList.add(data);
				c.moveToNext();
			}
			c.close();
			myDatabase.close();
			return appeList;

		}

		return appeList;
	}
	
	
	public ArrayList<SATCategory> getSATCategoryWithZoneID(int ZoneID) {
		ArrayList<SATCategory> appeList = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("SATCategory", null, "ZoneID="+ZoneID, null, null,
				null, "SequenceNo");
		// c=sqLiteDatabase.rawQuery("select ZoneID from AppleTVInZone", null);
		if (c.getCount() > 0) {
			appeList = new ArrayList<SATCategory>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				SATCategory data = new SATCategory();
				data.setCategoryID(c.getInt(0));
				data.setCategoryName(c.getString(1));
				data.setSequenceNo(c.getInt(2));
				data.setZoneID(c.getInt(3));

				appeList.add(data);
				c.moveToNext();
			}
			c.close();
			myDatabase.close();
			return appeList;

		}

		return appeList;
	}
}
