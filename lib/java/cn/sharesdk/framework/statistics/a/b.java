package cn.sharesdk.framework.statistics.a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.mob.tools.utils.Ln;

public class b
{
  private static b c = null;
  private Context a;
  private a b;

  private b(Context paramContext)
  {
    this.a = paramContext.getApplicationContext();
    this.b = new a(this.a);
  }

  public static b a(Context paramContext)
  {
    try
    {
      if (c == null)
        c = new b(paramContext);
      paramContext = c;
      return paramContext;
    }
    finally
    {
    }
    throw paramContext;
  }

  public int a(String paramString)
  {
    Object localObject = null;
    String str = null;
    int i = 0;
    SQLiteDatabase localSQLiteDatabase = this.b.getWritableDatabase();
    try
    {
      paramString = localSQLiteDatabase.rawQuery("select count(*) from " + paramString, null);
      str = paramString;
      localObject = paramString;
      if (paramString.moveToNext())
      {
        str = paramString;
        localObject = paramString;
        i = paramString.getInt(0);
      }
      paramString.close();
      return i;
    }
    catch (Exception paramString)
    {
      localObject = str;
      Ln.e(paramString);
      str.close();
      return 0;
    }
    finally
    {
      ((Cursor)localObject).close();
    }
    throw paramString;
  }

  // ERROR //
  public int a(String paramString1, String paramString2, String[] paramArrayOfString)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 33	cn/sharesdk/framework/statistics/a/b:b	Lcn/sharesdk/framework/statistics/a/a;
    //   4: invokevirtual 42	cn/sharesdk/framework/statistics/a/a:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   7: astore 5
    //   9: aload 5
    //   11: aload_1
    //   12: aload_2
    //   13: aload_3
    //   14: invokevirtual 84	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   17: istore 4
    //   19: ldc 86
    //   21: iconst_2
    //   22: anewarray 4	java/lang/Object
    //   25: dup
    //   26: iconst_0
    //   27: iload 4
    //   29: invokestatic 92	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   32: aastore
    //   33: dup
    //   34: iconst_1
    //   35: aload_1
    //   36: aastore
    //   37: invokestatic 96	com/mob/tools/utils/Ln:d	(Ljava/lang/Object;[Ljava/lang/Object;)I
    //   40: pop
    //   41: iload 4
    //   43: ireturn
    //   44: astore_2
    //   45: iconst_0
    //   46: istore 4
    //   48: aload_2
    //   49: ldc 98
    //   51: iconst_1
    //   52: anewarray 4	java/lang/Object
    //   55: dup
    //   56: iconst_0
    //   57: aload_1
    //   58: aastore
    //   59: invokestatic 101	com/mob/tools/utils/Ln:e	(Ljava/lang/Throwable;Ljava/lang/Object;[Ljava/lang/Object;)I
    //   62: pop
    //   63: iload 4
    //   65: ireturn
    //   66: astore_2
    //   67: goto -19 -> 48
    //
    // Exception table:
    //   from	to	target	type
    //   9	19	44	java/lang/Exception
    //   19	41	66	java/lang/Exception
  }

  public long a(String paramString, ContentValues paramContentValues)
  {
    SQLiteDatabase localSQLiteDatabase = this.b.getWritableDatabase();
    try
    {
      long l = localSQLiteDatabase.replace(paramString, null, paramContentValues);
      return l;
    }
    catch (Exception paramContentValues)
    {
      Ln.e(paramContentValues, "when insert database occur error table:%s,", new Object[] { paramString });
    }
    return -1L;
  }

  public Cursor a(String paramString1, String[] paramArrayOfString1, String paramString2, String[] paramArrayOfString2, String paramString3)
  {
    SQLiteDatabase localSQLiteDatabase = this.b.getWritableDatabase();
    Ln.d("Query table: %s", new Object[] { paramString1 });
    try
    {
      paramArrayOfString1 = localSQLiteDatabase.query(paramString1, paramArrayOfString1, paramString2, paramArrayOfString2, null, null, paramString3);
      return paramArrayOfString1;
    }
    catch (Exception paramArrayOfString1)
    {
      Ln.e(paramArrayOfString1, "when query database occur error table:%s,", new Object[] { paramString1 });
    }
    return null;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.framework.statistics.a.b
 * JD-Core Version:    0.6.2
 */