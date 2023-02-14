package com.roka.smarthomeg4.database.dbconnection;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.roka.smarthomeg4.business.Zones;
import com.roka.smarthomeg4.database.Database;

public class ZonesDB {

	
	private Database myDatabase;
	private SQLiteDatabase sqLiteDatabase;

	public ZonesDB(Context context) {
		// TODO Auto-generated constructor stub
		myDatabase = new Database(context);

	}

	public ArrayList<Zones> getAllZones() {
		ArrayList<Zones> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("Zones", null, null, null, null,
				null, "SequenceNo");
		if (c.getCount() > 0) {
			Arr = new ArrayList<Zones>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				Zones data = new Zones();
				data.setZoneID(c.getInt(0));
				data.setZoneName(c.getString(1));
				data.setZoneIconID(c.getInt(2));
				data.setSequenceNo(c.getInt(3));
				Arr.add(data);
				c.moveToNext();
			}
			c.close();
			myDatabase.close();
			return Arr;

		}

		return Arr;

	}
	
	
	public ArrayList<Zones> getZonesWithZoneID(int ZoneID) {
		ArrayList<Zones> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("Zones", null, "ZoneID="+ZoneID, null, null,
				null, "SequenceNo");
		if (c.getCount() > 0) {
			Arr = new ArrayList<Zones>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				Zones data = new Zones();
				data.setZoneID(c.getInt(0));
				data.setZoneName(c.getString(1));
				data.setZoneIconID(c.getInt(2));
				data.setSequenceNo(c.getInt(3));
				Arr.add(data);
				c.moveToNext();
			}
			c.close();
			myDatabase.close();
			return Arr;

		}

		return Arr;

	}
	
	
	
	public Bitmap getImage(int type,int id){
		sqLiteDatabase = myDatabase.OpenDatabase();
	    String qu = "select LogoData  from Logo where LogoTypeID="+type+" and LogoID="+id ;
	    Cursor cur = sqLiteDatabase.rawQuery(qu, null);

	    if (cur.moveToFirst()){
	        byte[] imgByte = cur.getBlob(0);
	        cur.close();
	    	myDatabase.close();
	        return BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
	    }
	    if (cur != null && !cur.isClosed()) {
	        cur.close();
	    }       
		myDatabase.close();
	    return null ;
	} 
	
}
