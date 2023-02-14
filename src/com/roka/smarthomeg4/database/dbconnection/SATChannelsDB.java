package com.roka.smarthomeg4.database.dbconnection;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.roka.smarthomeg4.business.SATChannels;
import com.roka.smarthomeg4.database.Database;

public class SATChannelsDB {


		
		private Database myDatabase;
		private SQLiteDatabase sqLiteDatabase;

		public SATChannelsDB(Context context) {
			// TODO Auto-generated constructor stub
			myDatabase = new Database(context);

		}

		public ArrayList<SATChannels> getAllSATChannels() {
			ArrayList<SATChannels> Arr = null;
			sqLiteDatabase = myDatabase.OpenDatabase();
			Cursor c = sqLiteDatabase.query("SATChannels", null, null,
					null, null, null, "SequenceNo");
			if (c.getCount() > 0) {
				Arr = new ArrayList<SATChannels>();
				c.moveToFirst();
				while (c.isAfterLast() == false) {
					SATChannels data = new SATChannels();
					data.setCategoryID(c.getInt(0));
					data.setChannelID(c.getInt(1));
					data.setChannelNo(c.getInt(2));
					data.setChannelName(c.getString(3));
					data.setLogoID(c.getInt(4));
					data.setSequenceNo(c.getInt(5));
					data.setZoneID(c.getInt(6));
					
					Arr.add(data);
					c.moveToNext();
				}
				c.close();
				myDatabase.close();
				return Arr;

			}

			return Arr;

		}
		
		
		public ArrayList<SATChannels> getSATChannelsWithCategoryID(int CategoryID) {
			ArrayList<SATChannels> Arr = null;
			sqLiteDatabase = myDatabase.OpenDatabase();
			Cursor c = sqLiteDatabase.query("SATChannels", null, "CategoryID="+CategoryID,
					null, null, null, "SequenceNo");
			if (c.getCount() > 0) {
				Arr = new ArrayList<SATChannels>();
				c.moveToFirst();
				while (c.isAfterLast() == false) {
					SATChannels data = new SATChannels();
					data.setCategoryID(c.getInt(0));
					data.setChannelID(c.getInt(1));
					data.setChannelNo(c.getInt(2));
					data.setChannelName(c.getString(3));
					data.setLogoID(c.getInt(4));
					data.setSequenceNo(c.getInt(5));
					data.setZoneID(c.getInt(6));
					
					Arr.add(data);
					c.moveToNext();
				}
				c.close();
				myDatabase.close();
				return Arr;

			}

			return Arr;

		}
		
		public ArrayList<SATChannels> getSATChannelsWithChannelID(int ChannelID) {
			ArrayList<SATChannels> Arr = null;
			sqLiteDatabase = myDatabase.OpenDatabase();
			Cursor c = sqLiteDatabase.query("SATChannels", null, "ChannelID="+ChannelID,
					null, null, null, "SequenceNo");
			if (c.getCount() > 0) {
				Arr = new ArrayList<SATChannels>();
				c.moveToFirst();
				while (c.isAfterLast() == false) {
					SATChannels data = new SATChannels();
					data.setCategoryID(c.getInt(0));
					data.setChannelID(c.getInt(1));
					data.setChannelNo(c.getInt(2));
					data.setChannelName(c.getString(3));
					data.setLogoID(c.getInt(4));
					data.setSequenceNo(c.getInt(5));
					data.setZoneID(c.getInt(6));
					
					Arr.add(data);
					c.moveToNext();
				}
				c.close();
				myDatabase.close();
				return Arr;

			}

			return Arr;

		}
		
		public ArrayList<SATChannels> getSATChannelsWithChannelNo(int ChannelNo) {
			ArrayList<SATChannels> Arr = null;
			sqLiteDatabase = myDatabase.OpenDatabase();
			Cursor c = sqLiteDatabase.query("SATChannels", null, "ChannelNo="+ChannelNo,
					null, null, null, "SequenceNo");
			if (c.getCount() > 0) {
				Arr = new ArrayList<SATChannels>();
				c.moveToFirst();
				while (c.isAfterLast() == false) {
					SATChannels data = new SATChannels();
					data.setCategoryID(c.getInt(0));
					data.setChannelID(c.getInt(1));
					data.setChannelNo(c.getInt(2));
					data.setChannelName(c.getString(3));
					data.setLogoID(c.getInt(4));
					data.setSequenceNo(c.getInt(5));
					data.setZoneID(c.getInt(6));
					
					Arr.add(data);
					c.moveToNext();
				}
				c.close();
				myDatabase.close();
				return Arr;

			}

			return Arr;

		}
		
		public ArrayList<SATChannels> getSATChannelsWithZoneID(int ZoneID) {
			ArrayList<SATChannels> Arr = null;
			sqLiteDatabase = myDatabase.OpenDatabase();
			Cursor c = sqLiteDatabase.query("SATChannels", null, "ZoneID="+ZoneID,
					null, null, null, "SequenceNo");
			if (c.getCount() > 0) {
				Arr = new ArrayList<SATChannels>();
				c.moveToFirst();
				while (c.isAfterLast() == false) {
					SATChannels data = new SATChannels();
					data.setCategoryID(c.getInt(0));
					data.setChannelID(c.getInt(1));
					data.setChannelNo(c.getInt(2));
					data.setChannelName(c.getString(3));
					data.setLogoID(c.getInt(4));
					data.setSequenceNo(c.getInt(5));
					data.setZoneID(c.getInt(6));
					
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
