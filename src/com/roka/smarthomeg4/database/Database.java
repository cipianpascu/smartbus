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
	// //设置数据库存放路径
	// public static String databasePath = "/data/B4Check/";
	// //设置数据库存放路径
	public String databaseAllPath;
	public static String ApkAllPath;
	public Context context = null;

	public static String databasePath = Environment
			.getExternalStorageDirectory()
			+ File.separator
			+ "SMART-Mohamed" + File.separator; // 设置数据库存放路径
	String XBMCfileName = Environment.getExternalStorageDirectory()
			+ File.separator + "SMART-Mohamed" + File.separator
			+ "org.xbmc.android.remote.v1.0.9.apk";
	String GoogleTVfileName = Environment.getExternalStorageDirectory()
			+ File.separator + "SMART-Mohamed" + File.separator
			+ "Google TV Remote 1.1.0.apk";
//	private PackageInfo info;
//	private int version;
	SQLiteDatabase database = null; // 用于处理SQLite的操作
	public final static String TAG = "transDatabse";
	public final static int LOGIN_ERROR_NOUSER = -1; // 代表用户不存在
	public final static int LOGIN_OK = 1; // 代表允许登录
	public final static int LOGIN_ERROR_PASSWORD = 0; // 代表密码错误

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
	
	// 拷贝默认数据库到默认路径上
	public boolean copyXBMCGOOGLETVDefaultAPK(Context context) {
		try {
			File dir = new File(databasePath);
			if (!dir.exists())
				dir.mkdirs();
			// 如果在/sdcard/testdb目录中不存在
			// test.db文件，则从asset\db目录中复制这个文件到
			// SD卡的目录（/sdcard/testdb）
			//
			File dbFile = new File(XBMCfileName);
			if (!dbFile.exists()) {
				// 获得封装testDatabase.db文件的InputStream对象
				AssetManager asset = context.getAssets();
				InputStream is = asset.open(XBMCAPKName);

				dbFile.createNewFile();

				// FileOutputStream fos = new FileOutputStream(databaseAllPath);
				FileOutputStream fos = new FileOutputStream(dbFile);
				byte[] buffer = new byte[4096];
				int count = 0;
				// 开始复制APKName文件
				while ((count = is.read(buffer)) > 0) {
					fos.write(buffer, 0, count);
				}
				fos.close();
				is.close();
				// asset.close();
			}

			File goolTVFile = new File(GoogleTVfileName);
			if (!goolTVFile.exists()) {
				// 获得封装testDatabase.db文件的InputStream对象
				AssetManager asset = context.getAssets();
				InputStream is = asset.open(GoogleTVAPKName);

				goolTVFile.createNewFile();

				// FileOutputStream fos = new FileOutputStream(databaseAllPath);
				FileOutputStream fos = new FileOutputStream(goolTVFile);
				byte[] buffer = new byte[4096];
				int count = 0;
				// 开始复制APKName文件
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

	// 拷贝默认数据库到默认路径上
	public boolean copyDefaultDatabase(Context context) {
		try {

			File dir = new File(databasePath);
			if (!dir.exists())
				dir.mkdirs();
			// 如果在/sdcard/testdb目录中不存在
			// test.db文件，则从asset\db目录中复制这个文件到
			// SD卡的目录（/sdcard/testdb）
			databaseAllPath = databasePath + databaseName;
			File dbFile = new File(databaseAllPath);

			if (!dbFile.exists()) {
				// 获得封装testDatabase.db文件的InputStream对象
				AssetManager asset = context.getAssets();
				InputStream is = asset.open(databaseName);

				dbFile.createNewFile();

				// FileOutputStream fos = new FileOutputStream(databaseAllPath);
				FileOutputStream fos = new FileOutputStream(dbFile);
				byte[] buffer = new byte[4096];
				int count = 0;
				// 开始复制testDatabase.db文件
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

	// // 打开数据库
	// public boolean open(Context context) {
	// // 路径加名字
	// databaseAllPath = databasePath + databaseName;
	//
	// if (!copyDefaultDatabase(context))
	// return false;
	//
	// try {
	// // 由openDatabase 打开数据库并且创建数据库对象返回
	// // database = SQLiteDatabase
	// // .openDatabase(databaseAllPath, null, 0);
	// // 这种方式是打开sd卡下的数据库文件
	// database = SQLiteDatabase.openOrCreateDatabase(databaseAllPath,
	// null);
	//
	//
	// }catch (SQLiteException e) {
	// // 查询数据产生异常
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
			// 一个文件夹一个文件夹的去判断
			if (oDir.exists() == false) {

				// 首先看sdcard里面有没有
				strDBPath = "/sdcard" + databaseFolder + databaseName;
				oDir = new File(strDBPath);
				if (oDir.exists() == false) {
					// 再开flash文件夹里面有没有
					strDBPath = "/flash" + databaseFolder + databaseName;
					oDir = new File(strDBPath);
					if (oDir.exists() == false) {
						// 在看mnt/sdcard下有没有
						strDBPath = "/mnt/sdcard" + databaseFolder + databaseName;
						oDir = new File(strDBPath);
						if (oDir.exists() == false) {
							// 如果没有再看LocalDisk里面有没有
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

			// 把有文件路径的当做当前路径
			// mstrCurDBPath=strDBPath;
			// 打开数据库。不存在则创建
			database = SQLiteDatabase.openOrCreateDatabase(strDBPath, null);

		} catch (Exception e) {

		}
		return database;
	}

	// 登录验证方法
	public int login(String user, String password) {
		// String name = binertInvoice(name.getText().toString());
		// String password = binertInvoice(password.getText().toString());
		// String sname=result.getString(result.getColumnIndex("UserName"))
		// 构造一个查询SQL语句——使用用户名作为关键字查询
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
			// 查询数据产生异常
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

	// 复制数据库到手机指定文件夹下
	// public void copyDataBase() throws IOException {
	// String databaseFileNames =databasePath + databaseName;
	// File dir = new File(DATABASE_PATH);
	// // 判断文件夹是否存在，不存在就创建一个
	// if (!dir.exists()) {
	// boolean created =dir.mkdir();
	// }
	//
	// // 得到数据库文件的写入流
	// FileOutputStream os = new FileOutputStream(databaseFileNames);
	// InputStream is =
	// context.getResources().openRawResource(com.roka.smarthomeg4.R.raw.database);
	// // 得到数据库文件的数据流
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
