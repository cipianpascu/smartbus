package com.roka.smarthomeg4.database.dbconnection;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.roka.smarthomeg4.business.HVACSetUp;
import com.roka.smarthomeg4.database.DBHandler;
import com.roka.smarthomeg4.database.Database;

public class HVACSetUpDB {

	
	private Database myDatabase;
	private SQLiteDatabase sqLiteDatabase;

	public HVACSetUpDB(Context context) {
		// TODO Auto-generated constructor stub
		myDatabase = new Database(context);

	}

	public ArrayList<HVACSetUp> getAllHVACSetUp() {
		ArrayList<HVACSetUp> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("HVACSetUp", null, null, null, null,
				null, null);
		if (c.getCount() > 0) {
			Arr = new ArrayList<HVACSetUp>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				HVACSetUp data = new HVACSetUp();
				data.setIsCelsiur(DBHandler.getBool(c.getInt(0)));
				data.setTempertureOfCold(c.getInt(1));
				data.setTempertureOfCool(c.getInt(2));
				data.setTempertureOfWarm(c.getInt(3));
				data.setTempertureOfHot(c.getInt(4));
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
