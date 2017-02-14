package cn.jpush.android.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import cn.jpush.android.util.x;

final class j extends SQLiteOpenHelper
{
  private static final String[] z;

  static
  {
    String[] arrayOfString = new String[3];
    Object localObject2 = "FzuPe@\bdPsIm\020eniGSp]kGDxWlKQeXjF\0209nlL\020XQmwTc%xbX|Dzi1z@q\020PdQgy_rWm}TQ\b\034}_ZAT1]jFW1_j\\\020DiD\034}_ZK_d_q\bYE`OUc\021kGD1_pD\\=]kwBt\\j^U1Xk\\UvTw\b^~E%FE}])D^NE|XU1Xk\\UvTw\b^~E%FE}])D^NT}\\Bp\021qMHe\021)D^NEwAWvTwwDx\\`\b\\~_b\b\034}_ZITunqA]t\021iG^v\021,\023";
    int i = -1;
    int j = 0;
    Object localObject1 = arrayOfString;
    localObject2 = ((String)localObject2).toCharArray();
    int k = localObject2.length;
    int m;
    Object localObject3;
    int n;
    int i1;
    label50: int i2;
    label99: label121: Object localObject4;
    int i3;
    if (k <= 1)
    {
      m = 0;
      localObject3 = localObject1;
      n = j;
      localObject1 = localObject2;
      i1 = i;
      j = m;
      while (true)
      {
        localObject2 = localObject1;
        i2 = localObject2[m];
        switch (j % 5)
        {
        default:
          i = 49;
          localObject2[m] = ((char)(i ^ i2));
          j += 1;
          if (k != 0)
            break label121;
          m = k;
        case 0:
        case 1:
        case 2:
        case 3:
        }
      }
      i = k;
      localObject4 = localObject3;
      i3 = n;
      localObject2 = localObject1;
      i2 = i1;
    }
    while (true)
    {
      i1 = i2;
      localObject1 = localObject2;
      n = i3;
      localObject3 = localObject4;
      k = i;
      m = j;
      if (i > j)
        break label50;
      localObject1 = new String((char[])localObject2).intern();
      switch (i2)
      {
      default:
        localObject4[i3] = localObject1;
        localObject2 = "oXEbYZD_rPiw^~ElNYrPqA_\037aJ";
        j = 1;
        i = 0;
        break;
      case 0:
        localObject4[i3] = localObject1;
        j = 2;
        localObject2 = "AzA\021Qir]t%av1t]acEb%\\o}^fI\\^qAVxRd\\Y~_";
        i = 1;
        break;
      case 1:
        localObject4[i3] = localObject1;
        z = arrayOfString;
        return;
        i = 5;
        break label99;
        i = 40;
        break label99;
        i = 48;
        break label99;
        i = 17;
        break label99;
        m = 0;
        i2 = i;
        i3 = j;
        localObject4 = localObject1;
        i = k;
        j = m;
      }
    }
  }

  public j(Context paramContext)
  {
    this(paramContext, z[1], null, 1);
  }

  private j(Context paramContext, String paramString, SQLiteDatabase.CursorFactory paramCursorFactory, int paramInt)
  {
    super(paramContext, paramString, null, 1);
  }

  public final void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
    try
    {
      paramSQLiteDatabase.execSQL(z[0]);
      return;
    }
    catch (Exception paramSQLiteDatabase)
    {
      x.b();
    }
  }

  public final void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    paramSQLiteDatabase.execSQL(z[2]);
    onCreate(paramSQLiteDatabase);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.data.j
 * JD-Core Version:    0.6.2
 */