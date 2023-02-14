package com.roka.smarthomeg4.database.dbconnection;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.roka.smarthomeg4.business.TVInZone;
import com.roka.smarthomeg4.database.Database;

public class TVInZoneDB {
	private Database myDatabase;
	private SQLiteDatabase sqLiteDatabase;

	public TVInZoneDB(Context context) {
		// TODO Auto-generated constructor stub
		myDatabase = new Database(context);

	}

	public ArrayList<TVInZone> getAllTVInZone() {
		ArrayList<TVInZone> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("TVInZone", null, null, null, null,
				null, null);
		if (c.getCount() > 0) {
			Arr = new ArrayList<TVInZone>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				TVInZone data = new TVInZone();
				data.setZoneID(c.getInt(0));
				data.setSubnetID(c.getInt(1));
				data.setDeviceID(c.getInt(2));
				data.setUniversalSwitchIDforOn(c.getInt(3));
				data.setUniversalSwitchStatusforOn(c.getInt(4));
				data.setUniversalSwitchIDforOff(c.getInt(5));
				data.setUniversalSwitchStatusforOff(c.getInt(6));
				data.setUniversalSwitchIDforCHPlus(c.getInt(7));
				data.setUniversalSwitchIDforCHMinus(c.getInt(8));
				data.setUniversalSwitchIDforVOLPlus(c.getInt(9));
				data.setUniversalSwitchIDforVOLMinus(c.getInt(10));
				data.setUniversalSwitchIDforMute(c.getInt(11));
				data.setUniversalSwitchIDforMenu(c.getInt(12));
				data.setUniversalSwitchIDforSource(c.getInt(13));
				data.setUniversalSwitchIDforOK(c.getInt(14));
				data.setUniversalSwitchIDfor0(c.getInt(15));
				data.setUniversalSwitchIDfor1(c.getInt(16));
				data.setUniversalSwitchIDfor2(c.getInt(17));
				data.setUniversalSwitchIDfor3(c.getInt(18));
				data.setUniversalSwitchIDfor4(c.getInt(19));
				data.setUniversalSwitchIDfor5(c.getInt(20));
				data.setUniversalSwitchIDfor6(c.getInt(21));
				data.setUniversalSwitchIDfor7(c.getInt(22));
				data.setUniversalSwitchIDfor8(c.getInt(23));
				data.setUniversalSwitchIDfor9(c.getInt(24));
				data.setIRMacroNumbForTVStart(c.getInt(25));
				data.setIRMacroNumbForTVSpare1(c.getInt(26));
				data.setIRMacroNumbForTVSpare2(c.getInt(27));
				data.setIRMacroNumbForTVSpare3(c.getInt(28));
				data.setIRMacroNumbForTVSpare4(c.getInt(29));
				data.setIRMacroNumbForTVSpare5(c.getInt(30));
				Arr.add(data);
				c.moveToNext();
			}
			c.close();
			myDatabase.close();
			return Arr;

		}

		return Arr;

	}

	
	public ArrayList<TVInZone> getTVInZoneWithZoneID(int ZoneID) {
		ArrayList<TVInZone> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("TVInZone", null, "ZoneID="+ZoneID, null, null,
				null, null);
		if (c.getCount() > 0) {
			Arr = new ArrayList<TVInZone>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				TVInZone data = new TVInZone();
				data.setZoneID(c.getInt(0));
				data.setSubnetID(c.getInt(1));
				data.setDeviceID(c.getInt(2));
				data.setUniversalSwitchIDforOn(c.getInt(3));
				data.setUniversalSwitchStatusforOn(c.getInt(4));
				data.setUniversalSwitchIDforOff(c.getInt(5));
				data.setUniversalSwitchStatusforOff(c.getInt(6));
				data.setUniversalSwitchIDforCHPlus(c.getInt(7));
				data.setUniversalSwitchIDforCHMinus(c.getInt(8));
				data.setUniversalSwitchIDforVOLPlus(c.getInt(9));
				data.setUniversalSwitchIDforVOLMinus(c.getInt(10));
				data.setUniversalSwitchIDforMute(c.getInt(11));
				data.setUniversalSwitchIDforMenu(c.getInt(12));
				data.setUniversalSwitchIDforSource(c.getInt(13));
				data.setUniversalSwitchIDforOK(c.getInt(14));
				data.setUniversalSwitchIDfor0(c.getInt(15));
				data.setUniversalSwitchIDfor1(c.getInt(16));
				data.setUniversalSwitchIDfor2(c.getInt(17));
				data.setUniversalSwitchIDfor3(c.getInt(18));
				data.setUniversalSwitchIDfor4(c.getInt(19));
				data.setUniversalSwitchIDfor5(c.getInt(20));
				data.setUniversalSwitchIDfor6(c.getInt(21));
				data.setUniversalSwitchIDfor7(c.getInt(22));
				data.setUniversalSwitchIDfor8(c.getInt(23));
				data.setUniversalSwitchIDfor9(c.getInt(24));
				data.setIRMacroNumbForTVStart(c.getInt(25));
				data.setIRMacroNumbForTVSpare1(c.getInt(26));
				data.setIRMacroNumbForTVSpare2(c.getInt(27));
				data.setIRMacroNumbForTVSpare3(c.getInt(28));
				data.setIRMacroNumbForTVSpare4(c.getInt(29));
				data.setIRMacroNumbForTVSpare5(c.getInt(30));
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
