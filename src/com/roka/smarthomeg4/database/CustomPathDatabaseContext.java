package com.roka.smarthomeg4.database;

import java.io.File;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class CustomPathDatabaseContext extends ContextWrapper{
	 
    private String mDirPath;
     
    public CustomPathDatabaseContext(Context base, String dirPath) {
            super(base);
            this.mDirPath = dirPath;
    }
     
    @Override
    public File getDatabasePath(String name) 
    {
        File result = new File(mDirPath + name);

        if (!result.getParentFile().exists())
        {
            result.getParentFile().mkdirs();
        }

        return result;
    }
     
    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, CursorFactory factory)
    {
            return SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), factory);
    }
}