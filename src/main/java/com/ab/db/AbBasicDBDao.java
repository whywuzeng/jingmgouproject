package com.ab.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class AbBasicDBDao
{
  public void closeCursor(Cursor paramCursor)
  {
    if (paramCursor != null)
      paramCursor.close();
  }

  public void closeDB(Cursor paramCursor, SQLiteDatabase paramSQLiteDatabase)
  {
    if (paramCursor != null)
      paramCursor.close();
    if ((paramSQLiteDatabase != null) && (paramSQLiteDatabase.isOpen()))
      paramSQLiteDatabase.close();
  }

  public int getIntColumnValue(String paramString, Cursor paramCursor)
  {
    return paramCursor.getInt(paramCursor.getColumnIndex(paramString));
  }

  public String getStringColumnValue(String paramString, Cursor paramCursor)
  {
    return paramCursor.getString(paramCursor.getColumnIndex(paramString));
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.db.AbBasicDBDao
 * JD-Core Version:    0.6.2
 */