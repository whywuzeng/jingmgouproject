package com.ab.db.orm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class AbSDDBHelper extends AbSDSQLiteOpenHelper
{
  private Class<?>[] modelClasses;

  public AbSDDBHelper(Context paramContext, String paramString1, String paramString2, SQLiteDatabase.CursorFactory paramCursorFactory, int paramInt, Class<?>[] paramArrayOfClass)
  {
    super(paramContext, paramString1, paramString2, null, paramInt);
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
 * Qualified Name:     com.ab.db.orm.AbSDDBHelper
 * JD-Core Version:    0.6.2
 */