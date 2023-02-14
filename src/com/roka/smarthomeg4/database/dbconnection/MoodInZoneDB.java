package com.roka.smarthomeg4.database.dbconnection;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.roka.smarthomeg4.business.MoodInZone;
import com.roka.smarthomeg4.database.Database;

public class MoodInZoneDB {

	private Database myDatabase;
	private SQLiteDatabase sqLiteDatabase;

	public MoodInZoneDB(Context context) {
		// TODO Auto-generated constructor stub
		myDatabase = new Database(context);

	}

	public long insertMoodInZone(MoodInZone moodInZone) {
		//
		sqLiteDatabase = myDatabase.OpenDatabase();
		long n = 0;
		ContentValues contentValues = new ContentValues();
		contentValues.put("MoodID", moodInZone.getMoodID());
		contentValues.put("ZoneID", moodInZone.getZoneID());
		contentValues.put("MoodName", moodInZone.getMoodName());
		contentValues.put("MoodIconID", moodInZone.getMoodIconID());
		contentValues.put("SequenceNO", getNextSequence());
		contentValues.put("IsSystemMood", moodInZone.getIsSystemMood());

		if (!sqLiteDatabase.isOpen())
			sqLiteDatabase = myDatabase.OpenDatabase();
		n = sqLiteDatabase.insert("MoodInZone", null, contentValues);

		myDatabase.close();
		return n;
	}

	public int getNextId() {
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.rawQuery(
				"SELECT max(MoodID) FROM MoodInZone", null);
		c.moveToFirst();
		if (c.getCount() > 0) {
			int max = c.getInt(0);
			c.close();
			myDatabase.close();
			return max + 1;
		} else {
			c.close();
			myDatabase.close();
			return 1;
		}

	}
	
	public int getNextSequence() {
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.rawQuery(
				"SELECT max(SequenceNO) FROM MoodInZone", null);
		c.moveToFirst();
		if (c.getCount() > 0) {
			int max = c.getInt(0);
			c.close();
			myDatabase.close();
			return max + 1;
		} else {
			c.close();
			myDatabase.close();
			return 1;
		}

	}

	public ArrayList<MoodInZone> getAllMoodInZone() {
		ArrayList<MoodInZone> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("MoodInZone", null, null, null, null,
				null, null);
		if (c.getCount() > 0) {
			Arr = new ArrayList<MoodInZone>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				MoodInZone data = new MoodInZone();
				data.setMoodID(c.getInt(0));
				data.setZoneID(c.getInt(1));
				data.setMoodName(c.getString(2));
				data.setMoodIconID(c.getInt(3));
				data.setSequenceNO(c.getInt(4));
				data.setIsSystemMood(c.getInt(5));

				Arr.add(data);
				c.moveToNext();
			}
			c.close();
			myDatabase.close();
			return Arr;

		}

		return Arr;

	}

	public ArrayList<MoodInZone> getMoodInZoneWithZoneId(int ZoneID) {
		ArrayList<MoodInZone> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("MoodInZone", null, "ZoneID=" + ZoneID,
				null, null, null, null);
		if (c.getCount() > 0) {
			Arr = new ArrayList<MoodInZone>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				MoodInZone data = new MoodInZone();
				data.setMoodID(c.getInt(0));
				data.setZoneID(c.getInt(1));
				data.setMoodName(c.getString(2));
				data.setMoodIconID(c.getInt(3));
				data.setSequenceNO(c.getInt(4));
				data.setIsSystemMood(c.getInt(5));

				Arr.add(data);
				c.moveToNext();
			}
			c.close();
			myDatabase.close();
			return Arr;

		}

		return Arr;

	}

	public ArrayList<MoodInZone> getMoodInZoneWithMoodID(int MoodID) {
		ArrayList<MoodInZone> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("MoodInZone", null, "MoodID=" + MoodID,
				null, null, null, null);
		if (c.getCount() > 0) {
			Arr = new ArrayList<MoodInZone>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				MoodInZone data = new MoodInZone();
				data.setMoodID(c.getInt(0));
				data.setZoneID(c.getInt(1));
				data.setMoodName(c.getString(2));
				data.setMoodIconID(c.getInt(3));
				data.setSequenceNO(c.getInt(4));
				data.setIsSystemMood(c.getInt(5));

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
