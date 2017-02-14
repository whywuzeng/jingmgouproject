package com.ismartgo.beacon.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper
{
  public DBOpenHelper(Context paramContext)
  {
    super(paramContext, "beacon.db", null, 1);
  }

  public void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
    paramSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS beacon_info(_id INTEGER PRIMARY KEY AUTOINCREMENT,uuid VARCHAR, major INTEGER, minor INTEGER,scan_time TEXT,push_time TEXT,click_time TEXT,day_of_time TEXT,flag INTEGER,activity_id INTEGER,beacon_by_activity_id INTEGER,submit_status INTEGER)");
  }

  public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    paramSQLiteDatabase.execSQL("ALTER TABLE ibeaconInfos ADD COLUMN other STRING");
  }

  public static class BeaconField
  {
    public static final String ACTIVITY_ID = "activity_id";
    public static final String BEACON_BY_ACTIVITY_ID = "beacon_by_activity_id";
    public static final String CLICK_TIME = "click_time";
    public static final String DAY_OF_TIME = "day_of_time";
    public static final String DB_BEACON = "beacon.db";
    public static final String FLAG = "flag";
    public static final String MAJOR = "major";
    public static final String MINOR = "minor";
    public static final String PUSH_TIME = "push_time";
    public static final String SCAN_TIME = "scan_time";
    public static final String SUBMIT_STATUS = "submit_status";
    public static final String TAB_BEACON = "beacon_info";
    public static final String UUID = "uuid";
    public static final int VERSION = 1;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.beacon.db.DBOpenHelper
 * JD-Core Version:    0.6.2
 */