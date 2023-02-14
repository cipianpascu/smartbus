package com.roka.smarthomeg4.database;

import java.io.File;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class myDB {
	//SQLite database
	private SQLiteDatabase moDB;
	private final static String CONST_DB_PATH="/SMART-BUS-Mohamed";
	private final static String CONST_DB_NAME="/Database.sqlite3";
	
	private String mstrCurDBPath;
	
	protected void onDestroy() 
	{
		CloseDatabase();
		
	}
	
	public SQLiteDatabase GetDB() 
	{
		return moDB;
	}
	
	public void CloseDatabase()
	{
		if(moDB!=null)
		{
			if (moDB.isOpen())
			{
				moDB.close();	
			}
			
		}
	}
	
	public String GetDBPath()
	{
	
		
		return mstrCurDBPath;
	}
	
	public SQLiteDatabase OpenDatabase()  
    {  
		  String strDBPath ;
		 
		  File oDir;
	      try  
	      { 
	    	 moDB=null;
	    	    	 
	    	 strDBPath = android.os.Environment.getExternalStorageDirectory().getAbsolutePath()  
	    	             + CONST_DB_PATH+CONST_DB_NAME;  
	       	 oDir = new File(strDBPath);  
	    	 if (oDir.exists()==false)  
	    	 {
	    		 
	    		 
    			 strDBPath="/sdcard"+CONST_DB_PATH+CONST_DB_NAME;
	    		 oDir = new File(strDBPath);  
		    	 if (oDir.exists()==false)  
		    	 {
		    		 strDBPath ="/flash"+CONST_DB_PATH+CONST_DB_NAME;  
		    		 oDir = new File(strDBPath);  
			    	 if (oDir.exists()==false)  
			    	 {
			    		 strDBPath = "/mnt/sdcard" + CONST_DB_PATH+CONST_DB_NAME; 
			    		 oDir = new File(strDBPath);  
			    		 if (oDir.exists()==false)  
			    		 {
			    			 strDBPath = "/LocalDisk" + CONST_DB_PATH+CONST_DB_NAME; 
			    		 }
			    	 }
		    	 } 
	 
	    		 
	    	 }

	    	 mstrCurDBPath=strDBPath;
	    	 moDB = SQLiteDatabase.openOrCreateDatabase(strDBPath, null);  
	         
	      }catch (Exception e)  
	      {  
	    	  
	      }  
	      return moDB;  
	}   
	

	

	/*
	 * execute SQL
	 */
	public boolean ExecSQL(SQLiteDatabase DB,String strSql)
	{
		boolean blnFlag = false;
	    try
		{
	    	DB.execSQL(strSql);
			blnFlag = true;
		}
		catch(Exception e)
		{
			
		}
		return blnFlag;
	}
	
	/*
	 * Query SQL
	 */
	public Cursor GetCursor(SQLiteDatabase DB,String strTableName,String[] arraystrColsName,String strWhere,
			 String[] arraystrReplacementChar,String strGroupBy,String strHaving,String strOderby)
	{
		Cursor ocursor=null;
		try
		{
			ocursor = DB.query(strTableName, arraystrColsName,
					strWhere, arraystrReplacementChar, strGroupBy, strHaving, strOderby);
		}
		catch(Exception e)
		{
			 
		}
		return ocursor;
	}
	
}
