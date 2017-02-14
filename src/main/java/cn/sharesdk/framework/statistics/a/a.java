package cn.sharesdk.framework.statistics.a;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class a extends SQLiteOpenHelper
{
  public a(Context paramContext)
  {
    super(paramContext.getApplicationContext(), "sharesdk.db", null, 1);
  }

  public void close()
  {
    try
    {
      super.close();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
    paramSQLiteDatabase.execSQL(" create table  message(_id integer primary key autoincrement,post_time timestamp not null, message_data text not null);");
  }

  public void onOpen(SQLiteDatabase paramSQLiteDatabase)
  {
    super.onOpen(paramSQLiteDatabase);
  }

  public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.framework.statistics.a.a
 * JD-Core Version:    0.6.2
 */