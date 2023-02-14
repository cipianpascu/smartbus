package com.roka.smarthomeg4.database;

import android.content.Context;

import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Environment;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;


public class Database {
	// public String databaseName = "sale.db";
	// public String databaseName = "b4check.db";
	public String databaseName = "Database.sqlite3";
	// public static String APKName = "XBMC Remote.apk";
	public static String XBMCAPKName = "org.xbmc.android.remote.v1.0.9.apk";
	public static String GoogleTVAPKName = "Google TV Remote 1.1.0.apk";
	 public static String databaseFolder = "/SMART-Mohamed/";
	// //�������ݿ���·��
	// public static String databasePath = "/data/B4Check/";
	// //�������ݿ���·��
	public String databaseAllPath;
	public static String ApkAllPath;
	public Context context = null;

	public static String databasePath = Environment
			.getExternalStorageDirectory()
			+ File.separator
			+ "SMART-Mohamed" + File.separator; // �������ݿ���·��
	String XBMCfileName = Environment.getExternalStorageDirectory()
			+ File.separator + "SMART-Mohamed" + File.separator
			+ "org.xbmc.android.remote.v1.0.9.apk";
	String GoogleTVfileName = Environment.getExternalStorageDirectory()
			+ File.separator + "SMART-Mohamed" + File.separator
			+ "Google TV Remote 1.1.0.apk";
//	private PackageInfo info;
//	private int version;
	SQLiteDatabase database = null; // ���ڴ���SQLite�Ĳ���
	public final static String TAG = "transDatabse";
	public final static int LOGIN_ERROR_NOUSER = -1; // �����û�������
	public final static int LOGIN_OK = 1; // ���������¼
	public final static int LOGIN_ERROR_PASSWORD = 0; // �����������

	public Database(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	
	public synchronized void close() {
		//
		if(database!=null)
		{
			if (database.isOpen())
			{
				database.close();	
			}
			
		}
		
	}
	
	// ����Ĭ�����ݿ⵽Ĭ��·����
	public boolean copyXBMCGOOGLETVDefaultAPK(Context context) {
		try {
			File dir = new File(databasePath);
			if (!dir.exists())
				dir.mkdirs();
			// �����/sdcard/testdbĿ¼�в�����
			// test.db�ļ������asset\dbĿ¼�и�������ļ���
			// SD����Ŀ¼��/sdcard/testdb��
			//
			File dbFile = new File(XBMCfileName);
			if (!dbFile.exists()) {
				// ��÷�װtestDatabase.db�ļ���InputStream����
				AssetManager asset = context.getAssets();
				InputStream is = asset.open(XBMCAPKName);

				dbFile.createNewFile();

				// FileOutputStream fos = new FileOutputStream(databaseAllPath);
				FileOutputStream fos = new FileOutputStream(dbFile);
				byte[] buffer = new byte[4096];
				int count = 0;
				// ��ʼ����APKName�ļ�
				while ((count = is.read(buffer)) > 0) {
					fos.write(buffer, 0, count);
				}
				fos.close();
				is.close();
				// asset.close();
			}

			File goolTVFile = new File(GoogleTVfileName);
			if (!goolTVFile.exists()) {
				// ��÷�װtestDatabase.db�ļ���InputStream����
				AssetManager asset = context.getAssets();
				InputStream is = asset.open(GoogleTVAPKName);

				goolTVFile.createNewFile();

				// FileOutputStream fos = new FileOutputStream(databaseAllPath);
				FileOutputStream fos = new FileOutputStream(goolTVFile);
				byte[] buffer = new byte[4096];
				int count = 0;
				// ��ʼ����APKName�ļ�
				while ((count = is.read(buffer)) > 0) {
					fos.write(buffer, 0, count);
				}
				fos.close();
				is.close();
				// asset.close();
			}

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// ����Ĭ�����ݿ⵽Ĭ��·����
	public boolean copyDefaultDatabase(Context context) {
		try {

			File dir = new File(databasePath);
			if (!dir.exists())
				dir.mkdirs();
			// �����/sdcard/testdbĿ¼�в�����
			// test.db�ļ������asset\dbĿ¼�и�������ļ���
			// SD����Ŀ¼��/sdcard/testdb��
			databaseAllPath = databasePath + databaseName;
			File dbFile = new File(databaseAllPath);

			if (!dbFile.exists()) {
				// ��÷�װtestDatabase.db�ļ���InputStream����
				AssetManager asset = context.getAssets();
				InputStream is = asset.open(databaseName);

				dbFile.createNewFile();

				// FileOutputStream fos = new FileOutputStream(databaseAllPath);
				FileOutputStream fos = new FileOutputStream(dbFile);
				byte[] buffer = new byte[4096];
				int count = 0;
				// ��ʼ����testDatabase.db�ļ�
				while ((count = is.read(buffer)) > 0) {
					fos.write(buffer, 0, count);
				}
				fos.close();
				is.close();
				// asset.close();
			}

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean copyAPKtoDIR(Context context) {
		if (!copyXBMCGOOGLETVDefaultAPK(context)) {
			return false;
		}
		return true;
	}

	// // �����ݿ�
	// public boolean open(Context context) {
	// // ·��������
	// databaseAllPath = databasePath + databaseName;
	//
	// if (!copyDefaultDatabase(context))
	// return false;
	//
	// try {
	// // ��openDatabase �����ݿⲢ�Ҵ������ݿ���󷵻�
	// // database = SQLiteDatabase
	// // .openDatabase(databaseAllPath, null, 0);
	// // ���ַ�ʽ�Ǵ�sd���µ����ݿ��ļ�
	// database = SQLiteDatabase.openOrCreateDatabase(databaseAllPath,
	// null);
	//
	//
	// }catch (SQLiteException e) {
	// // ��ѯ���ݲ����쳣
	// e.printStackTrace();
	// }
	//
	// if (!database.isOpen()) {
	// SQLiteDatabase.openDatabase(databaseAllPath, null, 0);
	// }
	//
	// Log.i(TAG, "open datbase " + databaseAllPath + ",isOpen="
	// + database.isOpen());
	//
	// return database.isOpen();
	// }

	public SQLiteDatabase OpenDatabase() {
		String strDBPath;
		// SQLiteDatabase moDB=null;
		File oDir;
		try {

			strDBPath = android.os.Environment.getExternalStorageDirectory()
					.getAbsolutePath() + databaseFolder + databaseName;
			oDir = new File(strDBPath);
			// һ���ļ���һ���ļ��е�ȥ�ж�
			if (oDir.exists() == false) {

				// ���ȿ�sdcard������û��
				strDBPath = "/sdcard" + databaseFolder + databaseName;
				oDir = new File(strDBPath);
				if (oDir.exists() == false) {
					// �ٿ�flash�ļ���������û��
					strDBPath = "/flash" + databaseFolder + databaseName;
					oDir = new File(strDBPath);
					if (oDir.exists() == false) {
						// �ڿ�mnt/sdcard����û��
						strDBPath = "/mnt/sdcard" + databaseFolder + databaseName;
						oDir = new File(strDBPath);
						if (oDir.exists() == false) {
							// ���û���ٿ�LocalDisk������û��
							strDBPath = "/LocalDisk" + databaseFolder
									+ databaseName;
							oDir = new File(strDBPath);
							if (oDir.exists() == false) {
								boolean copied = copyDefaultDatabase(context);
								if (copied) {
									strDBPath = databasePath + databaseName;
								}
							}
						}
					}
				}

			}

			// �����ļ�·���ĵ�����ǰ·��
			// mstrCurDBPath=strDBPath;
			// �����ݿ⡣�������򴴽�
			database = SQLiteDatabase.openOrCreateDatabase(strDBPath, null);

		} catch (Exception e) {

		}
		return database;
	}

	// ��¼��֤����
	public int login(String user, String password) {
		// String name = binertInvoice(name.getText().toString());
		// String password = binertInvoice(password.getText().toString());
		// String sname=result.getString(result.getColumnIndex("UserName"))
		// ����һ����ѯSQL��䡪��ʹ���û�����Ϊ�ؼ��ֲ�ѯ
		String sqlStr = "  select UserName ,Password from Users where UserName=\'"
				+ user + "\';";

		Cursor result = null;

		try {
			result = database.rawQuery(sqlStr, null);

			// String
			// spassword=result.getString(result.getColumnIndex("Password"));
			if (result.getCount() > 0) {
				result.moveToFirst();
				if (password.equals(result.getString(result
						.getColumnIndex("Password")))) {
					return LOGIN_OK;
				} else
					return LOGIN_ERROR_PASSWORD;

			} else
				return LOGIN_ERROR_NOUSER;

		} catch (SQLiteException e) {
			// ��ѯ���ݲ����쳣
			e.printStackTrace();
		}

		return LOGIN_OK;

	}

	public boolean checkDataBase() {
		SQLiteDatabase db = null;
		try {
			String databaseFileName = databasePath + databaseName;
			db = SQLiteDatabase.openDatabase(databaseFileName, null,
					SQLiteDatabase.OPEN_READONLY);

		} catch (SQLiteException e) {

		}

		if (db != null) {
			db.close();
		}

		return db != null ? true : false;

	}

	// �������ݿ⵽�ֻ�ָ���ļ�����
	// public void copyDataBase() throws IOException {
	// String databaseFileNames =databasePath + databaseName;
	// File dir = new File(DATABASE_PATH);
	// // �ж��ļ����Ƿ���ڣ������ھʹ���һ��
	// if (!dir.exists()) {
	// boolean created =dir.mkdir();
	// }
	//
	// // �õ����ݿ��ļ���д����
	// FileOutputStream os = new FileOutputStream(databaseFileNames);
	// InputStream is =
	// context.getResources().openRawResource(com.roka.smarthomeg4.R.raw.database);
	// // �õ����ݿ��ļ���������
	//
	// // InputStream is = context.getAssets().open("DataBase.db3");
	//
	// byte[] buffer = new byte[8192];
	// int count = 0;
	// while ((count = is.read(buffer)) > 0) {
	// os.write(buffer, 0, count);
	// os.flush();
	// }
	//
	// is.close();
	// os.close();
	// }

}
