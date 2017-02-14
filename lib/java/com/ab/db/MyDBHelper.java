package com.ab.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper
{
  private static final String DBNAME = "andbase.db";
  private static final int VERSION = 1;

  public MyDBHelper(Context paramContext)
  {
    super(paramContext, "andbase.db", null, 1);
  }

  public void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
    paramSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS FILEDOWN (_ID INTEGER PRIMARY KEY AUTOINCREMENT,ICON TEXT,NAME TEXT,DESCRIPTION TEXT, PAKAGENAME TEXT ,DOWNURL TEXT,DOWNPATH TEXT,STATE INTEGER,DOWNLENGTH INTEGER,TOTALLENGTH INTEGER,DOWNSUFFIX TEXT)");
  }

  public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS FILEDOWN");
    onCreate(paramSQLiteDatabase);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.db.MyDBHelper
 * JD-Core Version:    0.6.2
 */