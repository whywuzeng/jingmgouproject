package cn.jpush.android.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import cn.jpush.android.util.x;

public final class u
{
  public static final String[] a = localObject2;
  private static final String[] z;
  private Context b;
  private v c;
  private SQLiteDatabase d;

  static
  {
    Object localObject1 = new String[19];
    int j = 0;
    Object localObject3 = "hT\026\035,rL,\037";
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
        i = 77;
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
        localObject3 = "";
        i = 0;
        localObject1 = localObject2;
        localObject4 = localObject2;
        break;
      case 0:
        localObject5[i4] = localObject1;
        j = 2;
        localObject3 = "";
        i = 1;
        localObject1 = localObject2;
        localObject4 = localObject2;
        break;
      case 1:
        localObject5[i4] = localObject1;
        j = 3;
        localObject3 = "hT\026\017\"oA%";
        i = 2;
        localObject1 = localObject2;
        localObject4 = localObject2;
        break;
      case 2:
        localObject5[i4] = localObject1;
        j = 4;
        localObject3 = "";
        i = 3;
        localObject1 = localObject2;
        localObject4 = localObject2;
        break;
      case 3:
        localObject5[i4] = localObject1;
        j = 5;
        localObject3 = "";
        i = 4;
        localObject1 = localObject2;
        localObject4 = localObject2;
        break;
      case 4:
        localObject5[i4] = localObject1;
        j = 6;
        localObject3 = "hT\026\030\"nN=$|D\023";
        i = 5;
        localObject1 = localObject2;
        localObject4 = localObject2;
        break;
      case 5:
        localObject5[i4] = localObject1;
        j = 7;
        localObject3 = "qP<\b%DS=\0329rS=\022.h";
        i = 6;
        localObject1 = localObject2;
        localObject4 = localObject2;
        break;
      case 6:
        localObject5[i4] = localObject1;
        j = 8;
        localObject3 = "hT\026\025(o";
        i = 7;
        localObject1 = localObject2;
        localObject4 = localObject2;
        break;
      case 7:
        localObject5[i4] = localObject1;
        j = 9;
        localObject3 = "hT\026\b\"iT\026\020(b\035n";
        i = 8;
        localObject1 = localObject2;
        localObject4 = localObject2;
        break;
      case 8:
        localObject5[i4] = localObject1;
        j = 10;
        localObject3 = "hT\026\b\"nR*\036";
        i = 9;
        localObject1 = localObject2;
        localObject4 = localObject2;
        break;
      case 9:
        localObject5[i4] = localObject1;
        j = 11;
        localObject3 = "hT\026\030\"uN\026\022=";
        i = 10;
        localObject1 = localObject2;
        localObject4 = localObject2;
        break;
      case 10:
        localObject5[i4] = localObject1;
        j = 12;
        localObject3 = "hT\026\b\"iT\026\020(b";
        i = 11;
        localObject1 = localObject2;
        localObject4 = localObject2;
        break;
      case 11:
        localObject5[i4] = localObject1;
        j = 13;
        localObject3 = "hT\026\030\"nN=$|+";
        i = 12;
        localObject1 = localObject2;
        localObject4 = localObject2;
        break;
      case 12:
        localObject5[i4] = localObject1;
        j = 14;
        localObject3 = "hT\026\027\"xA%$)uS";
        i = 13;
        localObject1 = localObject2;
        localObject4 = localObject2;
        break;
      case 13:
        localObject5[i4] = localObject1;
        j = 15;
        localObject3 = "hT\026\030\"nN=$~D\021y";
        i = 14;
        localObject1 = localObject2;
        localObject4 = localObject2;
        break;
      case 14:
        localObject5[i4] = localObject1;
        j = 16;
        localObject3 = "hT\026\030\"nN=$|";
        i = 15;
        localObject1 = localObject2;
        localObject4 = localObject2;
        break;
      case 15:
        localObject5[i4] = localObject1;
        j = 17;
        localObject3 = "";
        i = 16;
        localObject1 = localObject2;
        localObject4 = localObject2;
        break;
      case 16:
        localObject5[i4] = localObject1;
        j = 18;
        localObject3 = "";
        i = 17;
        localObject1 = localObject2;
        localObject4 = localObject2;
        break;
      case 17:
        localObject5[i4] = localObject1;
        z = localObject2;
        localObject1 = new String[12];
        localObject1[0] = z[12];
        j = 1;
        localObject3 = "DI-";
        i = 18;
        localObject4 = localObject1;
        break;
        i = 27;
        continue;
        i = 32;
        continue;
        i = 73;
        continue;
        i = 123;
      case 18:
      }
    }
    localObject5[i4] = localObject1;
    localObject2[2] = z[8];
    localObject2[3] = z[11];
    localObject2[4] = z[14];
    localObject2[5] = z[10];
    localObject2[6] = z[0];
    localObject2[7] = z[3];
    localObject2[8] = z[16];
    localObject2[9] = z[6];
    localObject2[10] = z[15];
    localObject2[11] = z[13];
  }

  public u(Context paramContext)
  {
    this.b = paramContext;
  }

  public static w a(Cursor paramCursor)
  {
    if ((paramCursor == null) || (paramCursor.getCount() == 0))
    {
      x.c();
      return null;
    }
    w localw = new w();
    localw.a(paramCursor.getString(1));
    localw.b(paramCursor.getString(2));
    localw.c(paramCursor.getString(3));
    localw.d(paramCursor.getString(4));
    localw.e(paramCursor.getString(5));
    localw.a(paramCursor.getInt(6));
    localw.b(paramCursor.getInt(7));
    localw.c(paramCursor.getInt(8));
    localw.d(paramCursor.getInt(9));
    localw.e(paramCursor.getInt(10));
    localw.f(paramCursor.getInt(11));
    localw.toString();
    x.d();
    return localw;
  }

  public final int a(boolean paramBoolean)
  {
    Object localObject = z[0];
    if (paramBoolean)
      localObject = z[3];
    localObject = z[2] + (String)localObject + z[1];
    localObject = this.d.rawQuery((String)localObject, null);
    if (localObject != null)
    {
      ((Cursor)localObject).moveToFirst();
      int i = ((Cursor)localObject).getInt(0);
      ((Cursor)localObject).close();
      return i;
    }
    return 0;
  }

  public final long a(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put(z[12], paramString1);
    localContentValues.put(z[8], paramString2);
    localContentValues.put(z[11], paramString3);
    localContentValues.put(z[14], paramString4);
    localContentValues.put(z[10], paramString5);
    localContentValues.put(z[0], Integer.valueOf(paramInt1));
    localContentValues.put(z[3], Integer.valueOf(1));
    localContentValues.put(z[16], Integer.valueOf(paramInt3));
    localContentValues.put(z[6], Integer.valueOf(paramInt4));
    localContentValues.put(z[15], Integer.valueOf(paramInt5));
    localContentValues.put(z[13], Integer.valueOf(0));
    return this.d.insert(z[7], null, localContentValues);
  }

  public final void a()
  {
    this.c = new v(this.b);
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

  public final boolean a(String paramString)
  {
    paramString = z[17] + paramString + "'";
    paramString = this.d.rawQuery(paramString, null);
    int i;
    if (paramString != null)
    {
      paramString.moveToFirst();
      i = paramString.getInt(0);
      paramString.close();
    }
    return i != 0;
  }

  public final Cursor b(String paramString)
  {
    paramString = z[9] + paramString + "'";
    paramString = this.d.query(true, z[7], a, paramString, null, null, null, null, null);
    if (paramString != null)
      paramString.moveToFirst();
    return paramString;
  }

  public final void b()
  {
    this.d.close();
  }

  public final boolean b(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
  {
    String str = z[9] + paramString1 + "'";
    ContentValues localContentValues = new ContentValues();
    localContentValues.put(z[12], paramString1);
    localContentValues.put(z[8], paramString2);
    localContentValues.put(z[11], paramString3);
    localContentValues.put(z[14], paramString4);
    localContentValues.put(z[10], paramString5);
    localContentValues.put(z[0], Integer.valueOf(paramInt1));
    localContentValues.put(z[3], Integer.valueOf(paramInt2));
    localContentValues.put(z[16], Integer.valueOf(paramInt3));
    localContentValues.put(z[6], Integer.valueOf(paramInt4));
    localContentValues.put(z[15], Integer.valueOf(paramInt5));
    localContentValues.put(z[13], Integer.valueOf(paramInt6));
    return this.d.update(z[7], localContentValues, str, null) > 0;
  }

  public final void c()
  {
    String str = z[18];
    this.d.execSQL(str);
  }

  public final Cursor d()
  {
    Object localObject = z[5];
    localObject = this.d.rawQuery((String)localObject, null);
    if (localObject != null)
      ((Cursor)localObject).moveToFirst();
    return localObject;
  }

  public final Cursor e()
  {
    Object localObject = z[4];
    localObject = this.d.rawQuery((String)localObject, null);
    if (localObject != null)
      ((Cursor)localObject).moveToFirst();
    return localObject;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.data.u
 * JD-Core Version:    0.6.2
 */