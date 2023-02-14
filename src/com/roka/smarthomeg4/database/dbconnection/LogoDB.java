package com.roka.smarthomeg4.database.dbconnection;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;

import com.roka.smarthomeg4.business.Camera;
import com.roka.smarthomeg4.business.Logo;
import com.roka.smarthomeg4.database.Database;

public class LogoDB {

	private Database myDatabase;
	private SQLiteDatabase sqLiteDatabase;

	public LogoDB(Context context) {
		// TODO Auto-generated constructor stub
		myDatabase = new Database(context);

	}

	public ArrayList<Logo> getAllLogo() {
		ArrayList<Logo> ArrLogo = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("Logo", null, null, null, null, null,
				null);
		if (c.getCount() > 0) {
			ArrLogo = new ArrayList<Logo>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				Logo data = new Logo();
				data.setLogoTypeID(c.getInt(0));
				data.setLogoID(c.getInt(1));
				data.setLogoName(c.getString(2));
				
				
				ArrLogo.add(data);
				c.moveToNext();
			}
			c.close();
			myDatabase.close();
			return ArrLogo;

		}

		return ArrLogo;

	}

	
	public ArrayList<Logo> getLogoWithTypeAndID(int type,int id) {
		ArrayList<Logo> ArrLogo = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("Logo", null, "LogoTypeID="+type+" and LogoID="+id, null, null, null,
				null);
		if (c.getCount() > 0) {
			ArrLogo = new ArrayList<Logo>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				Logo data = new Logo();
				data.setLogoTypeID(c.getInt(0));
				data.setLogoID(c.getInt(1));
				data.setLogoName(c.getString(2));
				
				
				ArrLogo.add(data);
				c.moveToNext();
			}
			c.close();
			myDatabase.close();
			return ArrLogo;

		}

		return ArrLogo;

	}
	
	
	
	public ArrayList<Logo> getZoneImages() {
		ArrayList<Logo> ArrLogo = null;
		int logoid=1;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("Logo", null, "LogoTypeID=1", null, null, null,
				"LogoID");
		try {
		if (c.getCount() > 0) {
			ArrLogo = new ArrayList<Logo>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				Logo data = new Logo();
				int logotype=c.getInt(0);
				data.setLogoTypeID(logotype);
				data.setLogoID(c.getInt(1));
				data.setLogoName(c.getString(2));
				 byte[] imgByte = c.getBlob(3);
				data.setBlogData(BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length));
				
				ArrLogo.add(data);
				logoid=data.getLogoID();
				c.moveToNext();
			}
			c.close();
			

		}
		
		} catch (Exception e) {
			// TODO: handle exception
			c = sqLiteDatabase.query("Logo", null, "LogoTypeID=1 and LogoID>"+logoid, null, null, null,
					"LogoID");
			if (c.getCount() > 0) {
				
				c.moveToFirst();
				while (c.isAfterLast() == false) {
					Logo data = new Logo();
					int logotype=c.getInt(0);
					data.setLogoTypeID(logotype);
					data.setLogoID(c.getInt(1));
					data.setLogoName(c.getString(2));
					 byte[] imgByte = c.getBlob(3);
					data.setBlogData(BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length));
					
					ArrLogo.add(data);
					logoid=data.getLogoID();
					c.moveToNext();
				}
				c.close();
				

			}
		}
		myDatabase.close();
		
		return ArrLogo;

	}

//	public Bitmap getZoneImages(){
//		sqLiteDatabase = myDatabase.OpenDatabase();
//	    String qu = "select  LogoData  from Logo where LogoTypeID=4";
//	    Cursor cur = sqLiteDatabase.rawQuery(qu, null);
//
//	    if (cur.moveToFirst()){
//	        byte[] imgByte = cur.getBlob(0);
//	        cur.close();
//	    	myDatabase.close();
//	        return BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
//	    }
//	    if (cur != null && !cur.isClosed()) {
//	        cur.close();
//	    }       
//		myDatabase.close();
//	    return null ;
//	} 
//	
//	
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
	
	
	
	public void insertImg(int id , Bitmap img ) {   


	    byte[] data = getBitmapAsByteArray(img); // this is a function

//	    insertStatement_logo.bindLong(1, id);       
//	    insertStatement_logo.bindBlob(2, data);
//
//	    insertStatement_logo.executeInsert();
//	    insertStatement_logo.clearBindings() ;
//
	}
//
	 public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    bitmap.compress(CompressFormat.PNG, 0, outputStream);       
	    return outputStream.toByteArray();
	}
}
