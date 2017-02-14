package com.ab.db.orm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class AbDBHelper extends SQLiteOpenHelper
{
  private Class<?>[] modelClasses;

  public AbDBHelper(Context paramContext, String paramString, SQLiteDatabase.CursorFactory paramCursorFactory, int paramInt, Class<?>[] paramArrayOfClass)
  {
    super(paramContext, paramString, paramCursorFactory, paramInt);
    this.modelClasses = paramArrayOfClass;
  }

  public void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
    AbTableHelper.createTablesByClasses(paramSQLiteDatabase, this.modelClasses);
  }

  public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    AbTableHelper.dropTablesByClasses(paramSQLiteDatabase, this.modelClasses);
    onCreate(paramSQLiteDatabase);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.db.orm.AbDBHelper
 * JD-Core Version:    0.6.2
 */