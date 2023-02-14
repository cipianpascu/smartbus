package com.roka.smarthomeg4.database.dbconnection;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.roka.smarthomeg4.business.MoodIconDefinition;
import com.roka.smarthomeg4.database.Database;

public class MoodIconDefinitionDB {

	private Database myDatabase;
	private SQLiteDatabase sqLiteDatabase;

	public MoodIconDefinitionDB(Context context) {
		// TODO Auto-generated constructor stub
		myDatabase = new Database(context);

	}

	public ArrayList<MoodIconDefinition> getAllMoodIconDefinition() {
		ArrayList<MoodIconDefinition> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("MoodIconDefinition", null, null,
				null, null, null, null);
		if (c.getCount() > 0) {
			Arr = new ArrayList<MoodIconDefinition>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				MoodIconDefinition data = new MoodIconDefinition();
				data.setMoodIconID(c.getInt(0));
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
	
	
	public ArrayList<MoodIconDefinition> getMoodIconDefinitionWithMoodIconID(int MoodIconID) {
		ArrayList<MoodIconDefinition> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("MoodIconDefinition", null, "MoodIconID="+MoodIconID,
				null, null, null, null);
		if (c.getCount() > 0) {
			Arr = new ArrayList<MoodIconDefinition>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				MoodIconDefinition data = new MoodIconDefinition();
				data.setMoodIconID(c.getInt(0));
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
