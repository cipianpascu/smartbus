package com.roka.smarthomeg4.database.dbconnection;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.roka.smarthomeg4.business.MacroButton;
import com.roka.smarthomeg4.database.Database;

public class MacroButtonDB {

	
	private Database myDatabase;
	private SQLiteDatabase sqLiteDatabase;

	public MacroButtonDB(Context context) {
		// TODO Auto-generated constructor stub
		myDatabase = new Database(context);

	}

	public ArrayList<MacroButton> getAllMacroButton() {
		ArrayList<MacroButton> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("MacroButton", null, null,
				null, null, null, "SequenceNO");
		if (c.getCount() > 0) {
			Arr = new ArrayList<MacroButton>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				MacroButton data = new MacroButton();
				data.setMacroID(c.getInt(0));
				data.setMacroName(c.getString(1));
				data.setMacroIconID(c.getInt(2));
				data.setSequenceNO(c.getInt(3));				
				Arr.add(data);
				c.moveToNext();
			}
			c.close();
			myDatabase.close();
			return Arr;

		}

		return Arr;

	}
	

	public ArrayList<MacroButton> getMacroButtonWithID(int MacroID) {
		ArrayList<MacroButton> Arr = null;
		sqLiteDatabase = myDatabase.OpenDatabase();
		Cursor c = sqLiteDatabase.query("MacroButton", null, "MacroID="+MacroID,
				null, null, null, "SequenceNO");
		if (c.getCount() > 0) {
			Arr = new ArrayList<MacroButton>();
			c.moveToFirst();
			while (c.isAfterLast() == false) {
				MacroButton data = new MacroButton();
				data.setMacroID(c.getInt(0));
				data.setMacroName(c.getString(1));
				data.setMacroIconID(c.getInt(2));
				data.setSequenceNO(c.getInt(3));				
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
