package com.roka.smarthomeg4.database;

import java.util.ArrayList;

import com.roka.smarthomeg4.R;
import com.roka.smarthomeg4.business.LightTypeDefinition;
import com.roka.smarthomeg4.business.ZAudio_Album;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class Zaudio_DB extends SQLiteOpenHelper {

	// The Android's default system path of your application database.
	// public static String DB_PATH =

	public static String DB_NAME = "zaudio.db";
	private SQLiteDatabase myDataBase;
	private final Context myContext;

	/**
	 * Constructor Takes and keeps a reference of the passed context in order to
	 * access to the application assets and resources.
	 * 
	 * @param context
	 */

	// public static DataBaseHelper db = null;

	// public static DataBaseHelper getInstance(Context c) {
	// if (db != null) {
	// return db;
	// } else {
	// db = new DataBaseHelper(c);
	// return db;
	// }
	// }

	public Zaudio_DB(Context context) {

		super(context, DB_NAME, null, 1);
		this.myContext = context;
		myDataBase = getWritableDatabase();
		close();

	}

	public SQLiteDatabase open() throws SQLiteException {
		if (myDataBase == null || !myDataBase.isOpen()) {
			try {
				myDataBase = getWritableDatabase();
			} catch (SQLiteException ex) {
				myDataBase = getReadableDatabase();
			}
		}
		return myDataBase;

	}

	@Override
	public synchronized void close() {
		//
		if (myDataBase != null && myDataBase.isOpen())
			myDataBase.close();

	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL(myContext.getResources().getString(R.string.db_song));
		db.execSQL(myContext.getResources().getString(R.string.db_albums));

		//

	}

	public void clearDB(int zone,int source ) {
		open();
		myDataBase.execSQL("delete from albums where zone="+zone+" and source="+source);
		myDataBase.execSQL("delete from songs where zone="+zone+" and source="+source);

		close();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	public void getTablesName() {
		open();
		Cursor c = myDataBase.rawQuery("SELECT name FROM" + DB_NAME
				+ " WHERE type='table'", null);

		if (c.moveToFirst()) {
			while (!c.isAfterLast()) {
				Toast.makeText(myContext, "Table Name=> " + c.getString(0),
						Toast.LENGTH_LONG).show();
				c.moveToNext();
			}
		}
		close();
	}

	public long insertAlbum(ZAudio_Album album, int zone,int source) {

		long n = 0;
		ContentValues contentValues = new ContentValues();
		contentValues.put("zone", zone);
		contentValues.put("no", album.getNo());
		contentValues.put("name", album.getName());
		contentValues.put("packageno", album.getBig_Package_No());
		contentValues.put("packagesongno", album.getBig_Package_No_song());
		contentValues.put("source", source);
		if (!myDataBase.isOpen())
			myDataBase = open();
		n = myDataBase.insert("albums", null, contentValues);
		myDataBase.close();
		return n;
	}

	public long insertSong(ZAudio_Album album, int zone,int source) {

		long n = 0;
		ContentValues contentValues = new ContentValues();
		contentValues.put("zone", zone);
		contentValues.put("no", album.getNo());
		contentValues.put("noh", album.getSongNoHigh());
		contentValues.put("nol", album.getSongNoLow());
		contentValues.put("name", album.getName());
		contentValues.put("source", source);

		if (!myDataBase.isOpen())
			myDataBase = open();
		n = myDataBase.insert("songs", null, contentValues);
		myDataBase.close();
		return n;
	}

	public ArrayList<ZAudio_Album> getAllAlbum(int zone,int source) {
		ArrayList<ZAudio_Album> Arr = null;
		myDataBase = open();
		Cursor c = myDataBase.query("albums", null, "zone="+zone+" and source="+source, null, null, null,
				null);
		if (c.getCount() > 0) {
			Arr = new ArrayList<ZAudio_Album>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				ZAudio_Album data = new ZAudio_Album();
				data.setNo(c.getInt(1));
				data.setName(c.getString(2));
				data.setBig_Package_No(c.getInt(3));
				data.setBig_Package_No_song(c.getInt(4));
				Arr.add(data);
				c.moveToNext();
			}
			c.close();
			myDataBase.close();

		}
		return Arr;
	}

	public ArrayList<ZAudio_Album> getSongWithAlbumNo(int albumNo,int zone,int source) {
		ArrayList<ZAudio_Album> Arr = null;
		myDataBase = open();
		Cursor c = myDataBase.query("songs", null, "no=" + albumNo + " and zone="+zone+" and source="+source, null, null,
				null, null);
		if (c.getCount() > 0) {
			Arr = new ArrayList<ZAudio_Album>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				ZAudio_Album data = new ZAudio_Album();
				data.setNo(c.getInt(1));
				data.setSongNoHigh(c.getInt(2));
				data.setSongNoLow(c.getInt(3));
				data.setName(c.getString(4));
				Arr.add(data);
				c.moveToNext();
			}
			c.close();
			myDataBase.close();

		}
		return Arr;
	}
}