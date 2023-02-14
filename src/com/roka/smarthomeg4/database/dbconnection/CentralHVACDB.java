package com.roka.smarthomeg4.database.dbconnection;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.roka.smarthomeg4.business.CentralHVAC;
import com.roka.smarthomeg4.database.DBHandler;
import com.roka.smarthomeg4.database.Database;

public class CentralHVACDB {

	private Database myDatabase;
	private SQLiteDatabase sqLiteDatabase;

	public CentralHVACDB(Context context) {
		// TODO Auto-generated constructor stub
		myDatabase = new Database(context);

	}

	public ArrayList<CentralHVAC> getAllCentralHVAC() {
		ArrayList<CentralHVAC> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("CentralHVAC", null, null, null, null,
				null, "SequenceNo");
		if (c.getCount() > 0) {
			Arr = new ArrayList<CentralHVAC>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				CentralHVAC data = new CentralHVAC();
				data.setFloorID(c.getInt(0));
				data.setFloorName(c.getString(1));
				data.setSequenceNo(c.getInt(2));
				data.setBlnHaveHot(DBHandler.getBool(c.getInt(3)));
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
