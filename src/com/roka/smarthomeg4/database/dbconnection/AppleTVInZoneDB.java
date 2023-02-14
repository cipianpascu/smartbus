package com.roka.smarthomeg4.database.dbconnection;

import java.util.ArrayList;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.roka.smarthomeg4.business.AppleTVInZone;
import com.roka.smarthomeg4.database.Database;

public class AppleTVInZoneDB {

	 private Database myDatabase;
	 private SQLiteDatabase sqLiteDatabase;
	 
	 public AppleTVInZoneDB(Context context) {
		// TODO Auto-generated constructor stub
		 myDatabase=new Database(context);
	}
	 
	 
	 public ArrayList<AppleTVInZone> getAllAppelTVINZone(){		 
		 ArrayList<AppleTVInZone> appeList=null;		
			 sqLiteDatabase=myDatabase.OpenDatabase();	
			 Cursor c = sqLiteDatabase.query("AppleTVInZone", null, null, null, null, null, null);
//				c=sqLiteDatabase.rawQuery("select ZoneID from AppleTVInZone", null);
				if(c.getCount()>0){
					appeList=new ArrayList<AppleTVInZone>();
					c.moveToFirst(); 
				while (c.isAfterLast() == false) {
					AppleTVInZone data = new AppleTVInZone();
					data.setZoneID(c.getInt(0));
					data.setSubnetID(c.getInt(1));
					data.setDeviceID(c.getInt(2));
					data.setUniversalSwitchIDforOn(c.getInt(3));
					data.setUniversalSwitchStatusforOn(c.getInt(4));
					data.setUniversalSwitchIDforOff(c.getInt(5));
					data.setUniversalSwitchStatusforOff(c.getInt(6));
					data.setUniversalSwitchIDforUp(c.getInt(7));
					data.setUniversalSwitchIDforDown(c.getInt(8));
					data.setUniversalSwitchIDforLeft(c.getInt(9));
					data.setUniversalSwitchIDforRight(c.getInt(10));
					data.setUniversalSwitchIDforOK(c.getInt(11));
					data.setUniversalSwitchIDforMenu(c.getInt(12));
					data.setUniversalSwitchIDforPlayPause(c.getInt(13));
					data.setIRMacroNumbForAppleTVStart(c.getInt(14));
					data.setIRMacroNumbForAppleTVSpare1(c.getInt(15));
					data.setIRMacroNumbForAppleTVSpare2(c.getInt(16));
					data.setIRMacroNumbForAppleTVSpare3(c.getInt(17));
					data.setIRMacroNumbForAppleTVSpare4(c.getInt(18));
					data.setIRMacroNumbForAppleTVSpare5(c.getInt(19));					
					appeList.add(data);
					c.moveToNext();
				}
				c.close();
				myDatabase.close();
				return appeList;
		 
				}
		 
		 return appeList;
	 }
	 
	 public ArrayList<AppleTVInZone> getAllAppelTVINZoneWithZoneID(int zonId){		 
		 ArrayList<AppleTVInZone> appeList=null;		
			 sqLiteDatabase=myDatabase.OpenDatabase();	
			 Cursor c = sqLiteDatabase.query("AppleTVInZone", null, "ZoneID="+zonId, null, null, null, null);
//				c=sqLiteDatabase.rawQuery("select ZoneID from AppleTVInZone", null);
				if(c.getCount()>0){
					appeList=new ArrayList<AppleTVInZone>();
					c.moveToFirst(); 
				while (c.isAfterLast() == false) {
					AppleTVInZone data = new AppleTVInZone();
					data.setZoneID(c.getInt(0));
					data.setSubnetID(c.getInt(1));
					data.setDeviceID(c.getInt(2));
					data.setUniversalSwitchIDforOn(c.getInt(3));
					data.setUniversalSwitchStatusforOn(c.getInt(4));
					data.setUniversalSwitchIDforOff(c.getInt(5));
					data.setUniversalSwitchStatusforOff(c.getInt(6));
					data.setUniversalSwitchIDforUp(c.getInt(7));
					data.setUniversalSwitchIDforDown(c.getInt(8));
					data.setUniversalSwitchIDforLeft(c.getInt(9));
					data.setUniversalSwitchIDforRight(c.getInt(10));
					data.setUniversalSwitchIDforOK(c.getInt(11));
					data.setUniversalSwitchIDforMenu(c.getInt(12));
					data.setUniversalSwitchIDforPlayPause(c.getInt(13));
					data.setIRMacroNumbForAppleTVStart(c.getInt(14));
					data.setIRMacroNumbForAppleTVSpare1(c.getInt(15));
					data.setIRMacroNumbForAppleTVSpare2(c.getInt(16));
					data.setIRMacroNumbForAppleTVSpare3(c.getInt(17));
					data.setIRMacroNumbForAppleTVSpare4(c.getInt(18));
					data.setIRMacroNumbForAppleTVSpare5(c.getInt(19));					
					appeList.add(data);
					c.moveToNext();
				}
				c.close();
				myDatabase.close();
				return appeList;
		 
				}
		 
		 return appeList;
	 }
}
