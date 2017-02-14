package cn.jpush.android.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import cn.jpush.android.util.x;

public final class i
{
  public static final String[] a = localObject2;
  private static final String[] z;
  private Context b;
  private j c;
  private SQLiteDatabase d;

  static
  {
    Object localObject1 = new String[15];
    int j = 0;
    Object localObject3 = "4\\\001\037B(W";
    int i = -1;
    Object localObject4 = localObject1;
    char[] arrayOfChar = ((String)localObject3).toCharArray();
    int k = arrayOfChar.length;
    int i1 = 0;
    int m = 0;
    int i3 = i;
    localObject3 = arrayOfChar;
    int i4 = j;
    Object localObject5 = localObject1;
    Object localObject2 = localObject4;
    int n = k;
    Object localObject6;
    int i2;
    if (k <= 1)
    {
      localObject6 = localObject1;
      localObject1 = arrayOfChar;
      i2 = i;
      label72: n = m;
      label75: localObject2 = localObject1;
      i1 = localObject2[m];
      switch (n % 5)
      {
      default:
        i = 59;
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
        break label75;
      }
      i1 = n;
      n = k;
      localObject2 = localObject4;
      localObject5 = localObject6;
      i4 = j;
      localObject3 = localObject1;
      i3 = i2;
      i2 = i3;
      localObject1 = localObject3;
      j = i4;
      localObject6 = localObject5;
      localObject4 = localObject2;
      k = n;
      m = i1;
      if (n > i1)
        break label72;
      localObject1 = new String((char[])localObject3).intern();
      switch (i3)
      {
      default:
        localObject5[i4] = localObject1;
        j = 1;
        localObject3 = "4\\\001\031^5](\016";
        i = 0;
        localObject1 = localObject2;
        localObject4 = localObject2;
        break;
      case 0:
        localObject5[i4] = localObject1;
        j = 2;
        localObject3 = "4\\\001\n_<m*\002V=";
        i = 1;
        localObject1 = localObject2;
        localObject4 = localObject2;
        break;
      case 1:
        localObject5[i4] = localObject1;
        j = 3;
        localObject3 = ",m2\004X9^0\004O1T7\bZ,[1\005";
        i = 2;
        localObject1 = localObject2;
        localObject4 = localObject2;
        break;
      case 2:
        localObject5[i4] = localObject1;
        j = 4;
        localObject3 = "4\\\001\037I1U9\016I\007F7\006^";
        i = 3;
        localObject1 = localObject2;
        localObject4 = localObject2;
        break;
      case 3:
        localObject5[i4] = localObject1;
        j = 5;
        localObject3 = "4\\\001\016C,@?";
        i = 4;
        localObject1 = localObject2;
        localObject4 = localObject2;
        break;
      case 4:
        localObject5[i4] = localObject1;
        j = 6;
        localObject3 = "4\\\001\bT-\\*";
        i = 5;
        localObject1 = localObject2;
        localObject4 = localObject2;
        break;
      case 5:
        localObject5[i4] = localObject1;
        j = 7;
        localObject3 = "4\\\001\002_";
        i = 6;
        localObject1 = localObject2;
        localObject4 = localObject2;
        break;
      case 6:
        localObject5[i4] = localObject1;
        j = 8;
        localObject3 = "4\\\001\bT-\\*U\013";
        i = 7;
        localObject1 = localObject2;
        localObject4 = localObject2;
        break;
      case 7:
        localObject5[i4] = localObject1;
        j = 9;
        localObject3 = "4\\\001\bT-\\*U\013xS0\017\0334\\\001\037I1U9\016I\007F7\006^d";
        i = 8;
        localObject1 = localObject2;
        localObject4 = localObject2;
        break;
      case 8:
        localObject5[i4] = localObject1;
        j = 10;
        localObject3 = "xS0\017\0334\\\001\037I1U9\016I\007F7\006^";
        i = 9;
        localObject1 = localObject2;
        localObject4 = localObject2;
        break;
      case 9:
        localObject5[i4] = localObject1;
        j = 11;
        localObject3 = "4\\\001\bT-\\*V";
        i = 10;
        localObject1 = localObject2;
        localObject4 = localObject2;
        break;
      case 10:
        localObject5[i4] = localObject1;
        j = 12;
        localObject3 = "xS0\017\0334\\\001\037B(W";
        i = 11;
        localObject1 = localObject2;
        localObject4 = localObject2;
        break;
      case 11:
        localObject5[i4] = localObject1;
        j = 13;
        localObject3 = "e\002";
        i = 12;
        localObject1 = localObject2;
        localObject4 = localObject2;
        break;
      case 12:
        localObject5[i4] = localObject1;
        j = 14;
        localObject3 = "4\\\001\002_e";
        i = 13;
        localObject1 = localObject2;
        localObject4 = localObject2;
        break;
      case 13:
        localObject5[i4] = localObject1;
        z = localObject2;
        localObject1 = new String[8];
        j = 0;
        localObject3 = "\007[:";
        i = 14;
        localObject4 = localObject1;
        break;
        i = 88;
        continue;
        i = 50;
        continue;
        i = 94;
        continue;
        i = 107;
      case 14:
      }
    }
    localObject5[i4] = localObject1;
    localObject2[1] = z[7];
    localObject2[2] = z[6];
    localObject2[3] = z[1];
    localObject2[4] = z[0];
    localObject2[5] = z[5];
    localObject2[6] = z[4];
    localObject2[7] = z[2];
  }

  public i(Context paramContext)
  {
    this.b = paramContext;
  }

  public static void a(Cursor paramCursor, k paramk)
  {
    if ((paramCursor == null) || (paramCursor.getCount() == 0))
    {
      x.c();
      return;
    }
    k localk = paramk;
    if (paramk == null)
      localk = new k();
    localk.a(paramCursor.getLong(1));
    localk.a(paramCursor.getInt(2));
    localk.b(paramCursor.getInt(3));
    localk.c(paramCursor.getInt(4));
    localk.a(paramCursor.getString(5));
    localk.c(paramCursor.getLong(6));
    localk.b(paramCursor.getLong(7));
    localk.toString();
    x.d();
  }

  public final long a(long paramLong1, int paramInt1, int paramInt2, int paramInt3, String paramString, long paramLong2, long paramLong3)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put(z[7], Long.valueOf(paramLong1));
    localContentValues.put(z[6], Integer.valueOf(1));
    localContentValues.put(z[1], Integer.valueOf(0));
    localContentValues.put(z[0], Integer.valueOf(0));
    localContentValues.put(z[5], paramString);
    localContentValues.put(z[4], Long.valueOf(paramLong2));
    localContentValues.put(z[2], Long.valueOf(paramLong3));
    return this.d.insert(z[3], null, localContentValues);
  }

  public final Cursor a(int paramInt, long paramLong)
  {
    Object localObject = z[11] + 1 + z[10] + "<" + paramLong;
    localObject = this.d.query(true, z[3], a, (String)localObject, null, null, null, null, null);
    if (localObject != null)
      ((Cursor)localObject).moveToFirst();
    return localObject;
  }

  public final Cursor a(long paramLong, int paramInt)
  {
    Object localObject = z[14] + paramLong + z[12] + z[13];
    localObject = this.d.query(true, z[3], a, (String)localObject, null, null, null, null, null);
    if (localObject != null)
      ((Cursor)localObject).moveToFirst();
    return localObject;
  }

  public final Cursor a(long paramLong1, long paramLong2)
  {
    Object localObject = z[9] + (300000L + paramLong1) + z[10] + ">" + paramLong1;
    localObject = this.d.query(true, z[3], a, (String)localObject, null, null, null, null, null);
    if (localObject != null)
      ((Cursor)localObject).moveToFirst();
    return localObject;
  }

  public final void a()
  {
    this.c = new j(this.b);
    try
    {
      this.d = this.c.getWritableDatabase();
      return;
    }
    catch (Exception localException)
    {
      this.d = this.c.getReadableDatabase();
    }
  }

  public final void b()
  {
    this.d.close();
  }

  public final boolean b(long paramLong1, int paramInt1, int paramInt2, int paramInt3, String paramString, long paramLong2, long paramLong3)
  {
    String str = z[14] + paramLong1;
    ContentValues localContentValues = new ContentValues();
    localContentValues.put(z[7], Long.valueOf(paramLong1));
    localContentValues.put(z[6], Integer.valueOf(paramInt1));
    localContentValues.put(z[1], Integer.valueOf(paramInt2));
    localContentValues.put(z[0], Integer.valueOf(0));
    localContentValues.put(z[5], paramString);
    localContentValues.put(z[4], Long.valueOf(paramLong2));
    localContentValues.put(z[2], Long.valueOf(paramLong3));
    return this.d.update(z[3], localContentValues, str, null) > 0;
  }

  public final boolean c()
  {
    String str = z[8];
    ContentValues localContentValues = new ContentValues();
    localContentValues.put(z[6], Integer.valueOf(0));
    localContentValues.put(z[1], Integer.valueOf(1));
    localContentValues.put(z[0], Integer.valueOf(0));
    return this.d.update(z[3], localContentValues, str, null) > 0;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.data.i
 * JD-Core Version:    0.6.2
 */