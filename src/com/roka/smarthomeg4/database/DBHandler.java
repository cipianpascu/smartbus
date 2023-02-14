package com.roka.smarthomeg4.database;

public class DBHandler {

	public static boolean getBool(int i){
		 return (i== 1)? true : false;
	}
	
	public static int getBoolInt(boolean b){
		 return (b)? 1 : 0;
	}
	
	public static boolean getBool(String bool){
		 return (bool.equals("true"))? true : false;
	}
}
