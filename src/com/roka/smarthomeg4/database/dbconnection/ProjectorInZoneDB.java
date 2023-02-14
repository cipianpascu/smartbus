package com.roka.smarthomeg4.database.dbconnection;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.roka.smarthomeg4.business.ProjectorInZone;
import com.roka.smarthomeg4.database.Database;

public class ProjectorInZoneDB {

	
	private Database myDatabase;
	private SQLiteDatabase sqLiteDatabase;

	public ProjectorInZoneDB(Context context) {
		// TODO Auto-generated constructor stub
		myDatabase = new Database(context);

	}

	public ArrayList<ProjectorInZone> getAllProjectorInZone() {
		ArrayList<ProjectorInZone> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("ProjectorInZone", null, null,
				null, null, null, null);
		if (c.getCount() > 0) {
			Arr = new ArrayList<ProjectorInZone>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				ProjectorInZone data = new ProjectorInZone();
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
				data.setUniversalSwitchIDforSource(c.getInt(13));
				data.setIRMacroNumbForProjectorStart(c.getInt(14));
				data.setIRMacroNumbForProjectorSpare1(c.getInt(15));
				data.setIRMacroNumbForProjectorSpare2(c.getInt(16));
				data.setIRMacroNumbForProjectorSpare3(c.getInt(17));
				data.setIRMacroNumbForProjectorSpare4(c.getInt(18));
				data.setIRMacroNumbForProjectorSpare5(c.getInt(19));
				Arr.add(data);
				c.moveToNext();
			}
			c.close();
			myDatabase.close();
			return Arr;

		}

		return Arr;

	}

	
	
	public ArrayList<ProjectorInZone> getProjectorInZoneWithZoneID(int ZoneID) {
		ArrayList<ProjectorInZone> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("ProjectorInZone", null, "ZoneID="+ZoneID,
				null, null, null, null);
		if (c.getCount() > 0) {
			Arr = new ArrayList<ProjectorInZone>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				ProjectorInZone data = new ProjectorInZone();
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
				data.setUniversalSwitchIDforSource(c.getInt(13));
				data.setIRMacroNumbForProjectorStart(c.getInt(14));
				data.setIRMacroNumbForProjectorSpare1(c.getInt(15));
				data.setIRMacroNumbForProjectorSpare2(c.getInt(16));
				data.setIRMacroNumbForProjectorSpare3(c.getInt(17));
				data.setIRMacroNumbForProjectorSpare4(c.getInt(18));
				data.setIRMacroNumbForProjectorSpare5(c.getInt(19));
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
