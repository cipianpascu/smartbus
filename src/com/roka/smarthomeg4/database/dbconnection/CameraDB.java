package com.roka.smarthomeg4.database.dbconnection;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.roka.smarthomeg4.business.Camera;
import com.roka.smarthomeg4.database.Database;

public class CameraDB {

	private Database myDatabase;
	private SQLiteDatabase sqLiteDatabase;

	public CameraDB(Context context) {
		// TODO Auto-generated constructor stub
		myDatabase = new Database(context);

	}

	public ArrayList<Camera> getAllCamera() {
		ArrayList<Camera> Arrcamera = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("Camera", null, null, null, null, null,
				"SequenceNo");
		if (c.getCount() > 0) {
			Arrcamera = new ArrayList<Camera>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				Camera data = new Camera();
				data.setCameraID(c.getInt(0));
				data.setName(c.getString(1));
				data.setURL(c.getString(2));
				data.setSequenceNo(c.getInt(3));
				Arrcamera.add(data);
				c.moveToNext();
			}
			c.close();
			myDatabase.close();
			return Arrcamera;

		}

		return Arrcamera;

	}

	
	public ArrayList<Camera> getAllCameraWithCamID(int CamID) {
		ArrayList<Camera> Arrcamera = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("Camera", null, "CameraID="+CamID, null, null, null,
				"SequenceNo");
		if (c.getCount() > 0) {
			Arrcamera = new ArrayList<Camera>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				Camera data = new Camera();
				data.setCameraID(c.getInt(0));
				data.setName(c.getString(1));
				data.setURL(c.getString(2));
				data.setSequenceNo(c.getInt(3));
				Arrcamera.add(data);
				c.moveToNext();
			}
			c.close();
			myDatabase.close();
			return Arrcamera;

		}

		return Arrcamera;

	}
}
