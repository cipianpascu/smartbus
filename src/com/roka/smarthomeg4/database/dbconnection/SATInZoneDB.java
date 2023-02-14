package com.roka.smarthomeg4.database.dbconnection;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.roka.smarthomeg4.business.SATInZone;
import com.roka.smarthomeg4.database.Database;

public class SATInZoneDB {

	private Database myDatabase;
	private SQLiteDatabase sqLiteDatabase;

	public SATInZoneDB(Context context) {
		// TODO Auto-generated constructor stub
		myDatabase = new Database(context);

	}

	public ArrayList<SATInZone> getAllSATInZone() {
		ArrayList<SATInZone> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("SATInZone", null, null, null, null,
				null, null);
		if (c.getCount() > 0) {
			Arr = new ArrayList<SATInZone>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				SATInZone data = new SATInZone();
				data.setZoneID(c.getInt(0));
				data.setSubnetID(c.getInt(1));
				data.setDeviceID(c.getInt(2));
				data.setUniversalSwitchIDforOn(c.getInt(3));
				data.setUniversalSwitchStatusforOn(c.getInt(4));
				data.setUniversalSwitchIDforOff(c.getInt(5));
				data.setUniversalSwitchStatusforOff(c.getInt(6));
				data.setUniversalSwitchIDfoUp(c.getInt(7));
				data.setUniversalSwitchIDforDown(c.getInt(8));
				data.setUniversalSwitchIDforLeft(c.getInt(9));
				data.setUniversalSwitchIDforRight(c.getInt(10));
				data.setUniversalSwitchIDforOK(c.getInt(11));
				data.setUniversalSwitchIDfoMenu(c.getInt(12));
				data.setUniversalSwitchIDforFAV(c.getInt(13));
				data.setUniversalSwitchIDfor0(c.getInt(14));
				data.setUniversalSwitchIDfor1(c.getInt(15));
				data.setUniversalSwitchIDfor2(c.getInt(16));
				data.setUniversalSwitchIDfor3(c.getInt(17));
				data.setUniversalSwitchIDfor4(c.getInt(18));
				data.setUniversalSwitchIDfor5(c.getInt(19));
				data.setUniversalSwitchIDfor6(c.getInt(20));
				data.setUniversalSwitchIDfor7(c.getInt(21));
				data.setUniversalSwitchIDfor8(c.getInt(22));
				data.setUniversalSwitchIDfor9(c.getInt(23));
				data.setUniversalSwithIDForRecord(c.getInt(24));
				data.setUniversalSwithIDForStopRecord(c.getInt(25));
				data.setIRMacroNumbForSATStart(c.getInt(26));
				data.setIRMacroNumbForSATSpare1(c.getInt(27));
				data.setIRMacroNumbForSATSpare2(c.getInt(28));
				data.setIRMacroNumbForSATSpare3(c.getInt(29));
				data.setIRMacroNumbForSATSpare4(c.getInt(30));
				data.setIRMacroNumbForSATSpare5(c.getInt(31));
				Arr.add(data);
				c.moveToNext();
			}
			c.close();
			myDatabase.close();
			return Arr;

		}

		return Arr;

	}
	
	public ArrayList<SATInZone> getSATInZoneWithZoneID(int ZoneID) {
		ArrayList<SATInZone> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("SATInZone", null, "ZoneID="+ZoneID, null, null,
				null, null);
		if (c.getCount() > 0) {
			Arr = new ArrayList<SATInZone>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				SATInZone data = new SATInZone();
				data.setZoneID(c.getInt(0));
				data.setSubnetID(c.getInt(1));
				data.setDeviceID(c.getInt(2));
				data.setUniversalSwitchIDforOn(c.getInt(3));
				data.setUniversalSwitchStatusforOn(c.getInt(4));
				data.setUniversalSwitchIDforOff(c.getInt(5));
				data.setUniversalSwitchStatusforOff(c.getInt(6));
				data.setUniversalSwitchIDfoUp(c.getInt(7));
				data.setUniversalSwitchIDforDown(c.getInt(8));
				data.setUniversalSwitchIDforLeft(c.getInt(9));
				data.setUniversalSwitchIDforRight(c.getInt(10));
				data.setUniversalSwitchIDforOK(c.getInt(11));
				data.setUniversalSwitchIDfoMenu(c.getInt(12));
				data.setUniversalSwitchIDforFAV(c.getInt(13));
				data.setUniversalSwitchIDfor0(c.getInt(14));
				data.setUniversalSwitchIDfor1(c.getInt(15));
				data.setUniversalSwitchIDfor2(c.getInt(16));
				data.setUniversalSwitchIDfor3(c.getInt(17));
				data.setUniversalSwitchIDfor4(c.getInt(18));
				data.setUniversalSwitchIDfor5(c.getInt(19));
				data.setUniversalSwitchIDfor6(c.getInt(20));
				data.setUniversalSwitchIDfor7(c.getInt(21));
				data.setUniversalSwitchIDfor8(c.getInt(22));
				data.setUniversalSwitchIDfor9(c.getInt(23));
				data.setUniversalSwithIDForRecord(c.getInt(24));
				data.setUniversalSwithIDForStopRecord(c.getInt(25));
				data.setIRMacroNumbForSATStart(c.getInt(26));
				data.setIRMacroNumbForSATSpare1(c.getInt(27));
				data.setIRMacroNumbForSATSpare2(c.getInt(28));
				data.setIRMacroNumbForSATSpare3(c.getInt(29));
				data.setIRMacroNumbForSATSpare4(c.getInt(30));
				data.setIRMacroNumbForSATSpare5(c.getInt(31));
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
