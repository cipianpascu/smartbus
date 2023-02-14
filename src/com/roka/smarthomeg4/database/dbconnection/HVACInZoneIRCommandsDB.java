package com.roka.smarthomeg4.database.dbconnection;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.roka.smarthomeg4.business.HVACInZoneIRCommands;
import com.roka.smarthomeg4.database.Database;

public class HVACInZoneIRCommandsDB {

	
	private Database myDatabase;
	private SQLiteDatabase sqLiteDatabase;

	public HVACInZoneIRCommandsDB(Context context) {
		// TODO Auto-generated constructor stub
		myDatabase = new Database(context);

	}

	public ArrayList<HVACInZoneIRCommands> getAllHVACInZoneIRCommands() {
		ArrayList<HVACInZoneIRCommands> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("HVACInZoneIRCommands", null, null,
				null, null, null, null);
		if (c.getCount() > 0) {
			Arr = new ArrayList<HVACInZoneIRCommands>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				HVACInZoneIRCommands data = new HVACInZoneIRCommands();
				data.setZoneID(c.getInt(0));
				data.setACID(c.getInt(1));
				data.setSubnetID(c.getInt(2));
				data.setDeviceID(c.getInt(3));
				data.setUniversalSwitchIDforOn(c.getInt(4));
				data.setUnSwStatusforOn(c.getInt(5));
				data.setUnSwIDforOff(c.getInt(6));
				data.setUnSwStatusforOff(c.getInt(7));
				data.setUnSwIDforCool(c.getInt(8));
				data.setUnSwIDforHeat(c.getInt(9));
				data.setUnSwIDforFan(c.getInt(10));				
				data.setUnSwIDforModeAuto(c.getInt(11));
				data.setUnSwIDforDry(c.getInt(12));				
				data.setUnSwIDforFanLow(c.getInt(13));
				data.setUnSwIDforFanMed(c.getInt(14));				
				data.setUnSwIDforFanHigh(c.getInt(15));
				data.setUnSwIDforFanAuto(c.getInt(16));
				data.setUnSwForTemp15(c.getInt(17));
				data.setUnSwForTemp16(c.getInt(18));
				data.setUnSwForTemp17(c.getInt(19));
				data.setUnSwForTemp18(c.getInt(20));
				data.setUnSwForTemp19(c.getInt(21));
				data.setUnSwForTemp20(c.getInt(22));
				data.setUnSwForTemp21(c.getInt(23));
				data.setUnSwForTemp22(c.getInt(24));
				data.setUnSwForTemp23(c.getInt(25));
				data.setUnSwForTemp24(c.getInt(26));
				data.setUnSwForTemp25(c.getInt(27));
				data.setUnSwForTemp26(c.getInt(28));
				data.setUnSwForTemp27(c.getInt(29));
				data.setUnSwForTemp28(c.getInt(30));
				data.setUnSwForTemp29(c.getInt(31));
				data.setUnSwForTemp30(c.getInt(32));
				data.setUnSwForTemp31(c.getInt(33));				
				Arr.add(data);
				c.moveToNext();
			}
			c.close();
			myDatabase.close();
			return Arr;

		}

		return Arr;

	}
	
	
	public ArrayList<HVACInZoneIRCommands> getHVACInZoneIRCommandsWithZone(int zoneId) {
		ArrayList<HVACInZoneIRCommands> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("HVACInZoneIRCommands", null, "ZoneID="+zoneId,
				null, null, null, null);
		if (c.getCount() > 0) {
			Arr = new ArrayList<HVACInZoneIRCommands>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				HVACInZoneIRCommands data = new HVACInZoneIRCommands();
				data.setZoneID(c.getInt(0));
				data.setACID(c.getInt(1));
				data.setSubnetID(c.getInt(2));
				data.setDeviceID(c.getInt(3));
				data.setUniversalSwitchIDforOn(c.getInt(4));
				data.setUnSwStatusforOn(c.getInt(5));
				data.setUnSwIDforOff(c.getInt(6));
				data.setUnSwStatusforOff(c.getInt(7));
				data.setUnSwIDforCool(c.getInt(8));
				data.setUnSwIDforHeat(c.getInt(9));
				data.setUnSwIDforFan(c.getInt(10));				
				data.setUnSwIDforModeAuto(c.getInt(11));
				data.setUnSwIDforDry(c.getInt(12));				
				data.setUnSwIDforFanLow(c.getInt(13));
				data.setUnSwIDforFanMed(c.getInt(14));				
				data.setUnSwIDforFanHigh(c.getInt(15));
				data.setUnSwIDforFanAuto(c.getInt(16));
				data.setUnSwForTemp15(c.getInt(17));
				data.setUnSwForTemp16(c.getInt(18));
				data.setUnSwForTemp17(c.getInt(19));
				data.setUnSwForTemp18(c.getInt(20));
				data.setUnSwForTemp19(c.getInt(21));
				data.setUnSwForTemp20(c.getInt(22));
				data.setUnSwForTemp21(c.getInt(23));
				data.setUnSwForTemp22(c.getInt(24));
				data.setUnSwForTemp23(c.getInt(25));
				data.setUnSwForTemp24(c.getInt(26));
				data.setUnSwForTemp25(c.getInt(27));
				data.setUnSwForTemp26(c.getInt(28));
				data.setUnSwForTemp27(c.getInt(29));
				data.setUnSwForTemp28(c.getInt(30));
				data.setUnSwForTemp29(c.getInt(31));
				data.setUnSwForTemp30(c.getInt(32));
				data.setUnSwForTemp31(c.getInt(33));				
				Arr.add(data);
				c.moveToNext();
			}
			c.close();
			myDatabase.close();
			return Arr;

		}

		return Arr;

	}
	
	public ArrayList<HVACInZoneIRCommands> getHVACInZoneIRCommandsWithACID(int ACID) {
		ArrayList<HVACInZoneIRCommands> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("HVACInZoneIRCommands", null, "ACID="+ACID,
				null, null, null, null);
		if (c.getCount() > 0) {
			Arr = new ArrayList<HVACInZoneIRCommands>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				HVACInZoneIRCommands data = new HVACInZoneIRCommands();
				data.setZoneID(c.getInt(0));
				data.setACID(c.getInt(1));
				data.setSubnetID(c.getInt(2));
				data.setDeviceID(c.getInt(3));
				data.setUniversalSwitchIDforOn(c.getInt(4));
				data.setUnSwStatusforOn(c.getInt(5));
				data.setUnSwIDforOff(c.getInt(6));
				data.setUnSwStatusforOff(c.getInt(7));
				data.setUnSwIDforCool(c.getInt(8));
				data.setUnSwIDforHeat(c.getInt(9));
				data.setUnSwIDforFan(c.getInt(10));				
				data.setUnSwIDforModeAuto(c.getInt(11));
				data.setUnSwIDforDry(c.getInt(12));				
				data.setUnSwIDforFanLow(c.getInt(13));
				data.setUnSwIDforFanMed(c.getInt(14));				
				data.setUnSwIDforFanHigh(c.getInt(15));
				data.setUnSwIDforFanAuto(c.getInt(16));
				data.setUnSwForTemp15(c.getInt(17));
				data.setUnSwForTemp16(c.getInt(18));
				data.setUnSwForTemp17(c.getInt(19));
				data.setUnSwForTemp18(c.getInt(20));
				data.setUnSwForTemp19(c.getInt(21));
				data.setUnSwForTemp20(c.getInt(22));
				data.setUnSwForTemp21(c.getInt(23));
				data.setUnSwForTemp22(c.getInt(24));
				data.setUnSwForTemp23(c.getInt(25));
				data.setUnSwForTemp24(c.getInt(26));
				data.setUnSwForTemp25(c.getInt(27));
				data.setUnSwForTemp26(c.getInt(28));
				data.setUnSwForTemp27(c.getInt(29));
				data.setUnSwForTemp28(c.getInt(30));
				data.setUnSwForTemp29(c.getInt(31));
				data.setUnSwForTemp30(c.getInt(32));
				data.setUnSwForTemp31(c.getInt(33));				
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
