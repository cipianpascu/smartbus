package com.roka.smarthomeg4.database.dbconnection;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.roka.smarthomeg4.business.DVDInZone;
import com.roka.smarthomeg4.database.Database;

public class DVDInZoneDB {

	
	private Database myDatabase;
	private SQLiteDatabase sqLiteDatabase;

	public DVDInZoneDB(Context context) {
		// TODO Auto-generated constructor stub
		myDatabase = new Database(context);

	}

	public ArrayList<DVDInZone> getAllDVDInZone() {
		ArrayList<DVDInZone> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("DVDInZone", null, null,
				null, null, null, null);
		if (c.getCount() > 0) {
			Arr = new ArrayList<DVDInZone>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				DVDInZone data = new DVDInZone();
				data.setZoneID(c.getInt(0));
				data.setSubnetID(c.getInt(1));
				data.setDeviceID(c.getInt(2));
				data.setUniversalSwitchIDforOn(c.getInt(3));
				data.setUniversalSwitchStatusforOn(c.getInt(4));
				data.setUniversalSwitchIDforOff(c.getInt(5));
				data.setUniversalSwitchStatusforOff(c.getInt(6));
				data.setUniversalSwitchIDfoMenu(c.getInt(7));
				data.setUniversalSwitchIDfoUp(c.getInt(8));
				data.setUniversalSwitchIDforDown(c.getInt(9));				
				data.setUniversalSwitchIDforFastForward(c.getInt(10));
				data.setUniversalSwitchIDforBackForward(c.getInt(11));				
				data.setUniversalSwitchIDforOK(c.getInt(12));
				data.setUniversalSwitchIDforPREVChapter(c.getInt(13));
				
				data.setUniversalSwitchIDforNextChapter(c.getInt(14));
				data.setUniversalSwitchIDforPlayPause(c.getInt(15));
				data.setUniversalSwithIDForRecord(c.getInt(16));
				data.setUniversalSwithIDForStopRecord(c.getInt(17));
				data.setIRMacroNumbForDVDStart(c.getInt(18));
				data.setIRMacroNumbForDVDSpare1(c.getInt(19));
				data.setIRMacroNumbForDVDSpare2(c.getInt(20));
				data.setIRMacroNumbForDVDSpare3(c.getInt(21));
				data.setIRMacroNumbForDVDSpare4(c.getInt(22));
				data.setIRMacroNumbForDVDSpare5(c.getInt(23));
				Arr.add(data);
				c.moveToNext();
			}
			c.close();
			myDatabase.close();
			return Arr;

		}

		return Arr;

	}
	
	
	public ArrayList<DVDInZone> getDVDInZoneWithZoneID(int zonID) {
		ArrayList<DVDInZone> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("DVDInZone", null, "ZoneID="+zonID,
				null, null, null, null);
		if (c.getCount() > 0) {
			Arr = new ArrayList<DVDInZone>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				DVDInZone data = new DVDInZone();
				data.setZoneID(c.getInt(0));
				data.setSubnetID(c.getInt(1));
				data.setDeviceID(c.getInt(2));
				data.setUniversalSwitchIDforOn(c.getInt(3));
				data.setUniversalSwitchStatusforOn(c.getInt(4));
				data.setUniversalSwitchIDforOff(c.getInt(5));
				data.setUniversalSwitchStatusforOff(c.getInt(6));
				data.setUniversalSwitchIDfoMenu(c.getInt(7));
				data.setUniversalSwitchIDfoUp(c.getInt(8));
				data.setUniversalSwitchIDforDown(c.getInt(9));				
				data.setUniversalSwitchIDforFastForward(c.getInt(10));
				data.setUniversalSwitchIDforBackForward(c.getInt(11));				
				data.setUniversalSwitchIDforOK(c.getInt(12));
				data.setUniversalSwitchIDforPREVChapter(c.getInt(13));
				
				data.setUniversalSwitchIDforNextChapter(c.getInt(14));
				data.setUniversalSwitchIDforPlayPause(c.getInt(15));
				data.setUniversalSwithIDForRecord(c.getInt(16));
				data.setUniversalSwithIDForStopRecord(c.getInt(17));
				data.setIRMacroNumbForDVDStart(c.getInt(18));
				data.setIRMacroNumbForDVDSpare1(c.getInt(19));
				data.setIRMacroNumbForDVDSpare2(c.getInt(20));
				data.setIRMacroNumbForDVDSpare3(c.getInt(21));
				data.setIRMacroNumbForDVDSpare4(c.getInt(22));
				data.setIRMacroNumbForDVDSpare5(c.getInt(23));
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
