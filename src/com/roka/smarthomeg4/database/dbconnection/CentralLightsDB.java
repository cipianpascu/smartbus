package com.roka.smarthomeg4.database.dbconnection;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.roka.smarthomeg4.business.CentralLights;
import com.roka.smarthomeg4.database.Database;

public class CentralLightsDB {

	
	
	private Database myDatabase;
	private SQLiteDatabase sqLiteDatabase;

	public CentralLightsDB(Context context) {
		// TODO Auto-generated constructor stub
		myDatabase = new Database(context);

	}

	public ArrayList<CentralLights> getAllCentralLights() {
		ArrayList<CentralLights> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("CentralLights", null, null,
				null, null, null, "SequenceNo");
		if (c.getCount() > 0) {
			Arr = new ArrayList<CentralLights>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				CentralLights data = new CentralLights();
				data.setFloorID(c.getInt(0));				
				data.setFloorName(c.getString(1));
				data.setSequenceNo(c.getInt(2));
				
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
