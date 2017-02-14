package cn.jpush.android.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build.VERSION;
import cn.jpush.android.util.x;

public final class r extends SQLiteOpenHelper
{
  private static r a;
  private static final String[] z;

  static
  {
    String[] arrayOfString = new String[14];
    int j = 0;
    Object localObject2 = "*f1\031N\022j\002\\S\rg;W\001\027}n\031";
    int i = -1;
    Object localObject1 = arrayOfString;
    char[] arrayOfChar = ((String)localObject2).toCharArray();
    int k = arrayOfChar.length;
    int i1 = 0;
    int m = 0;
    int i3 = i;
    localObject2 = arrayOfChar;
    int i4 = j;
    Object localObject3 = localObject1;
    int n = k;
    Object localObject4;
    int i2;
    if (k <= 1)
    {
      localObject4 = localObject1;
      localObject1 = arrayOfChar;
      i2 = i;
      label68: n = m;
      label71: localObject2 = localObject1;
      i1 = localObject2[m];
      switch (n % 5)
      {
      default:
        i = 33;
      case 0:
      case 1:
      case 2:
      case 3:
      }
    }
    while (true)
    {
      localObject2[m] = ((char)(i ^ i1));
      n += 1;
      if (k == 0)
      {
        m = k;
        break label71;
      }
      i1 = n;
      n = k;
      localObject3 = localObject4;
      i4 = j;
      localObject2 = localObject1;
      i3 = i2;
      i2 = i3;
      localObject1 = localObject2;
      j = i4;
      localObject4 = localObject3;
      k = n;
      m = i1;
      if (n > i1)
        break label68;
      localObject1 = new String((char[])localObject2).intern();
      switch (i3)
      {
      default:
        localObject3[i4] = localObject1;
        j = 1;
        localObject2 = "\032|;I\001\no6UD^G\022\031d&G\007mr^|1I~\no6UD";
        i = 0;
        localObject1 = arrayOfString;
        break;
      case 0:
        localObject3[i4] = localObject1;
        j = 2;
        localObject2 = "^z<\\\001\020k#oD\f}=VO^g'\031\033^";
        i = 1;
        localObject1 = arrayOfString;
        break;
      case 1:
        localObject3[i4] = localObject1;
        j = 3;
        localObject2 = "\032|;I\001\no6UD^G\022\031d&G\007mr^z7I~\no6UD";
        i = 2;
        localObject1 = arrayOfString;
        break;
      case 2:
        localObject3[i4] = localObject1;
        j = 4;
        localObject2 = "\fk$fH\0323";
        i = 3;
        localObject1 = arrayOfString;
        break;
      case 3:
        localObject3[i4] = localObject1;
        j = 5;
        localObject2 = "\nm$fU\037l8\\";
        i = 4;
        localObject1 = arrayOfString;
        break;
      case 4:
        localObject3[i4] = localObject1;
        j = 6;
        localObject2 = "\fk$fU\037l8\\";
        i = 5;
        localObject1 = arrayOfString;
        break;
      case 5:
        localObject3[i4] = localObject1;
        j = 7;
        localObject2 = "\fk$fQ\fk2PY";
        i = 6;
        localObject1 = arrayOfString;
        break;
      case 6:
        localObject3[i4] = localObject1;
        j = 8;
        localObject2 = "\fk$fE\037z5";
        i = 7;
        localObject1 = arrayOfString;
        break;
      case 7:
        localObject3[i4] = localObject1;
        j = 9;
        localObject2 = "\fk$fH\032";
        i = 8;
        localObject1 = arrayOfString;
        break;
      case 8:
        localObject3[i4] = localObject1;
        j = 10;
        localObject2 = "\nm$fE\037z5";
        i = 9;
        localObject1 = arrayOfString;
        break;
      case 9:
        localObject3[i4] = localObject1;
        j = 11;
        localObject2 = "\fk$\027E\034";
        i = 10;
        localObject1 = arrayOfString;
        break;
      case 10:
        localObject3[i4] = localObject1;
        j = 12;
        localObject2 = "";
        i = 11;
        localObject1 = arrayOfString;
        break;
      case 11:
        localObject3[i4] = localObject1;
        j = 13;
        localObject2 = "";
        i = 12;
        localObject1 = arrayOfString;
        break;
      case 12:
        localObject3[i4] = localObject1;
        z = arrayOfString;
        return;
        i = 126;
        continue;
        i = 14;
        continue;
        i = 84;
        continue;
        i = 57;
      }
    }
  }

  private r(Context paramContext)
  {
    super(paramContext, z[11], null, 3);
  }

  public static Cursor a(Context paramContext)
  {
    try
    {
      paramContext = c(paramContext).getWritableDatabase();
      String str1 = z[6];
      String str2 = z[9];
      String str3 = z[8];
      String str4 = z[7];
      String str5 = z[9];
      paramContext = paramContext.query(str1, new String[] { str2, str3, str4 }, null, null, null, null, str5);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      x.h();
    }
    return null;
  }

  public static boolean a(Context paramContext, int paramInt)
  {
    boolean bool = false;
    try
    {
      paramInt = c(paramContext).getWritableDatabase().delete(z[6], z[4] + paramInt, null);
      if (paramInt > 0)
        bool = true;
      return bool;
    }
    catch (Exception paramContext)
    {
      x.h();
    }
    return false;
  }

  public static boolean a(Context paramContext, String paramString)
  {
    boolean bool = false;
    try
    {
      paramContext = c(paramContext).getWritableDatabase();
      ContentValues localContentValues = new ContentValues();
      localContentValues.put(z[10], paramString);
      long l = paramContext.insert(z[5], z[9], localContentValues);
      if (l > 0L)
        bool = true;
      return bool;
    }
    catch (Exception paramContext)
    {
      x.h();
    }
    return false;
  }

  public static boolean a(Context paramContext, String paramString1, String paramString2)
  {
    boolean bool = false;
    try
    {
      paramContext = c(paramContext).getWritableDatabase();
      ContentValues localContentValues = new ContentValues();
      localContentValues.put(z[7], paramString1);
      localContentValues.put(z[8], paramString2);
      long l = paramContext.insert(z[6], z[9], localContentValues);
      if (l > 0L)
        bool = true;
      return bool;
    }
    catch (Exception paramContext)
    {
      x.h();
    }
    return false;
  }

  public static Cursor b(Context paramContext)
  {
    try
    {
      paramContext = c(paramContext).getWritableDatabase();
      String str1 = z[5];
      String str2 = z[9];
      String str3 = z[10];
      String str4 = z[9];
      paramContext = paramContext.query(str1, new String[] { str2, str3 }, null, null, null, null, str4);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      x.h();
    }
    return null;
  }

  public static boolean b(Context paramContext, int paramInt)
  {
    boolean bool = false;
    try
    {
      paramInt = c(paramContext).getWritableDatabase().delete(z[5], z[4] + paramInt, null);
      if (paramInt > 0)
        bool = true;
      return bool;
    }
    catch (Exception paramContext)
    {
      x.h();
    }
    return false;
  }

  private static r c(Context paramContext)
  {
    if (a == null)
      a = new r(paramContext);
    return a;
  }

  public final void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
    x.d();
    paramSQLiteDatabase.execSQL(z[12]);
    paramSQLiteDatabase.execSQL(z[13]);
  }

  public final void onDowngrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    if (Build.VERSION.SDK_INT >= 11)
      super.onDowngrade(paramSQLiteDatabase, paramInt1, paramInt2);
    new StringBuilder(z[0]).append(paramInt1).append(z[2]).append(paramInt2).toString();
    x.c();
    paramSQLiteDatabase.execSQL(z[1]);
    paramSQLiteDatabase.execSQL(z[3]);
    onCreate(paramSQLiteDatabase);
  }

  public final void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    new StringBuilder(z[0]).append(paramInt1).append(z[2]).append(paramInt2).toString();
    x.c();
    paramSQLiteDatabase.execSQL(z[1]);
    paramSQLiteDatabase.execSQL(z[3]);
    onCreate(paramSQLiteDatabase);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.data.r
 * JD-Core Version:    0.6.2
 */