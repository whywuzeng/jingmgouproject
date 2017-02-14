package com.baidu.location.c;

import android.database.sqlite.SQLiteDatabase;
import com.baidu.location.Jni;
import com.baidu.location.b.b;
import com.baidu.location.b.c;
import com.baidu.location.b.f;
import com.baidu.location.b.k;
import com.baidu.location.b.m;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

final class j
  implements b
{
  private final d b;
  private final SQLiteDatabase c;
  private final a d;
  private boolean e;
  private boolean f;
  private boolean g;
  private boolean h;
  private boolean i;
  private String[] j;
  private boolean k;
  private int l;
  private int m;
  private int n;
  private double o;
  private double p;
  private double q;
  private double r;
  private double s;
  private int t;
  private boolean u = true;
  private long v = 8000L;
  private long w = 5000L;
  private long x = 5000L;
  private long y = 5000L;
  private long z = 5000L;

  j(d paramd, SQLiteDatabase paramSQLiteDatabase)
  {
    this.b = paramd;
    this.e = false;
    this.f = false;
    this.g = false;
    this.h = false;
    this.i = false;
    this.k = false;
    this.l = 6;
    this.m = 30;
    this.n = 30;
    this.o = 0.0D;
    this.p = 0.0D;
    this.q = 0.0D;
    this.r = 0.0D;
    this.s = 0.0D;
    this.t = 8;
    this.j = new String[0];
    this.c = paramSQLiteDatabase;
    this.d = new a(null);
    if ((this.c != null) && (this.c.isOpen()))
      this.c.execSQL("CREATE TABLE IF NOT EXISTS BLACK (name VARCHAR(100) PRIMARY KEY);");
    g();
  }

  int a()
  {
    return this.t;
  }

  long a(String paramString)
  {
    long l1 = 5000L;
    if (paramString.equals("2G"))
      l1 = this.v;
    do
    {
      return l1;
      if (paramString.equals("3G"))
        return this.w;
      if (paramString.equals("4G"))
        return this.x;
      if (paramString.equals("WIFI"))
        return this.y;
    }
    while (!paramString.equals("unknown"));
    return this.z;
  }

  void a(String[] paramArrayOfString)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i1 = 0;
    while (i1 < paramArrayOfString.length)
    {
      if (i1 > 0)
        localStringBuffer.append(",");
      localStringBuffer.append("(\"");
      localStringBuffer.append(paramArrayOfString[i1]);
      localStringBuffer.append("\")");
      i1 += 1;
    }
    if ((this.c != null) && (this.c.isOpen()) && (localStringBuffer.length() > 0))
      paramArrayOfString = String.format(Locale.US, "INSERT OR IGNORE INTO BLACK VALUES %s;", new Object[] { localStringBuffer.toString() });
    try
    {
      this.c.execSQL(paramArrayOfString);
      return;
    }
    catch (Exception paramArrayOfString)
    {
    }
  }

  double b()
  {
    return this.o;
  }

  // ERROR //
  boolean b(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aconst_null
    //   4: astore 6
    //   6: iconst_0
    //   7: istore_3
    //   8: iconst_0
    //   9: istore 4
    //   11: getstatic 174	java/util/Locale:US	Ljava/util/Locale;
    //   14: ldc 188
    //   16: iconst_1
    //   17: anewarray 4	java/lang/Object
    //   20: dup
    //   21: iconst_0
    //   22: aload_1
    //   23: aastore
    //   24: invokestatic 184	java/lang/String:format	(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   27: astore 7
    //   29: aload 6
    //   31: astore_1
    //   32: aload_0
    //   33: getfield 103	com/baidu/location/c/j:c	Landroid/database/sqlite/SQLiteDatabase;
    //   36: aload 7
    //   38: aconst_null
    //   39: invokevirtual 192	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   42: astore 6
    //   44: aload 6
    //   46: astore_1
    //   47: aload 6
    //   49: astore 5
    //   51: aload 6
    //   53: invokeinterface 197 1 0
    //   58: istore_2
    //   59: iload 4
    //   61: istore_3
    //   62: iload_2
    //   63: ifle +5 -> 68
    //   66: iconst_1
    //   67: istore_3
    //   68: iload_3
    //   69: istore 4
    //   71: aload 6
    //   73: ifnull +13 -> 86
    //   76: aload 6
    //   78: invokeinterface 200 1 0
    //   83: iload_3
    //   84: istore 4
    //   86: iload 4
    //   88: ifeq +3 -> 91
    //   91: iload 4
    //   93: ireturn
    //   94: astore 5
    //   96: iload_3
    //   97: istore 4
    //   99: aload_1
    //   100: ifnull -14 -> 86
    //   103: aload_1
    //   104: invokeinterface 200 1 0
    //   109: iload_3
    //   110: istore 4
    //   112: goto -26 -> 86
    //   115: astore_1
    //   116: iload_3
    //   117: istore 4
    //   119: goto -33 -> 86
    //   122: astore_1
    //   123: aload 5
    //   125: ifnull +10 -> 135
    //   128: aload 5
    //   130: invokeinterface 200 1 0
    //   135: aload_1
    //   136: athrow
    //   137: astore_1
    //   138: iload_3
    //   139: istore 4
    //   141: goto -55 -> 86
    //   144: astore 5
    //   146: goto -11 -> 135
    //
    // Exception table:
    //   from	to	target	type
    //   32	44	94	java/lang/Exception
    //   51	59	94	java/lang/Exception
    //   103	109	115	java/lang/Exception
    //   32	44	122	finally
    //   51	59	122	finally
    //   76	83	137	java/lang/Exception
    //   128	135	144	java/lang/Exception
  }

  double c()
  {
    return this.p;
  }

  double d()
  {
    return this.q;
  }

  double e()
  {
    return this.r;
  }

  double f()
  {
    return this.s;
  }

  void g()
  {
    a.a(this.d);
  }

  boolean h()
  {
    return this.e;
  }

  boolean i()
  {
    return this.g;
  }

  boolean j()
  {
    return this.h;
  }

  boolean k()
  {
    return this.f;
  }

  boolean l()
  {
    return this.k;
  }

  boolean m()
  {
    return this.u;
  }

  int n()
  {
    return this.l;
  }

  String[] o()
  {
    return this.j;
  }

  int p()
  {
    return this.n;
  }

  int q()
  {
    return this.m;
  }

  private final class a extends m
    implements f
  {
    private int c = 0;
    private long d = -1L;
    private long e = -1L;
    private boolean f = false;
    private final String g = Jni.G(String.format(Locale.US, "&ver=%s&cuid=%s&prod=%s:%s&sdk=%.2f", new Object[] { "1", c.N().bm, c.bj, c.bn, Float.valueOf(6.12F) }));

    private a()
    {
      this.c7 = new ArrayList();
    }

    private void a()
    {
      if (this.f);
      while (true)
      {
        return;
        int j = 0;
        try
        {
          File localFile = new File(j.u(j.this).d(), "ofl.config");
          if ((this.e == -1L) && (localFile.exists()))
          {
            Object localObject1 = new Scanner(localFile);
            Object localObject2 = ((Scanner)localObject1).next();
            ((Scanner)localObject1).close();
            localObject1 = new JSONObject((String)localObject2);
            j.a(j.this, ((JSONObject)localObject1).getBoolean("ol"));
            j.b(j.this, ((JSONObject)localObject1).getBoolean("fl"));
            j.c(j.this, ((JSONObject)localObject1).getBoolean("on"));
            j.d(j.this, ((JSONObject)localObject1).getBoolean("wn"));
            j.e(j.this, ((JSONObject)localObject1).getBoolean("oc"));
            this.e = ((JSONObject)localObject1).getLong("t");
            if (((JSONObject)localObject1).has("cplist"))
              j.a(j.this, ((JSONObject)localObject1).getString("cplist").split(";"));
            if (((JSONObject)localObject1).has("rgcgp"))
              j.a(j.this, ((JSONObject)localObject1).getInt("rgcgp"));
            if (((JSONObject)localObject1).has("rgcon"))
              j.f(j.this, ((JSONObject)localObject1).getBoolean("rgcon"));
            if (((JSONObject)localObject1).has("addrup"))
              j.b(j.this, ((JSONObject)localObject1).getInt("addrup"));
            if (((JSONObject)localObject1).has("poiup"))
              j.c(j.this, ((JSONObject)localObject1).getInt("poiup"));
            if (((JSONObject)localObject1).has("oflp"))
            {
              localObject2 = ((JSONObject)localObject1).getJSONObject("oflp");
              if (((JSONObject)localObject2).has("0"))
                j.a(j.this, ((JSONObject)localObject2).getDouble("0"));
              if (((JSONObject)localObject2).has("1"))
                j.b(j.this, ((JSONObject)localObject2).getDouble("1"));
              if (((JSONObject)localObject2).has("2"))
                j.c(j.this, ((JSONObject)localObject2).getDouble("2"));
              if (((JSONObject)localObject2).has("3"))
                j.d(j.this, ((JSONObject)localObject2).getDouble("3"));
              if (((JSONObject)localObject2).has("4"))
                j.e(j.this, ((JSONObject)localObject2).getDouble("4"));
            }
            if (((JSONObject)localObject1).has("onlt"))
            {
              localObject2 = ((JSONObject)localObject1).getJSONObject("onlt");
              if (((JSONObject)localObject2).has("0"))
                j.a(j.this, ((JSONObject)localObject2).getLong("0"));
              if (((JSONObject)localObject2).has("1"))
                j.b(j.this, ((JSONObject)localObject2).getLong("1"));
              if (((JSONObject)localObject2).has("2"))
                j.c(j.this, ((JSONObject)localObject2).getLong("2"));
              if (((JSONObject)localObject2).has("3"))
                j.d(j.this, ((JSONObject)localObject2).getLong("3"));
              if (((JSONObject)localObject2).has("4"))
                j.e(j.this, ((JSONObject)localObject2).getLong("4"));
            }
            if (((JSONObject)localObject1).has("minapn"))
              j.d(j.this, ((JSONObject)localObject1).getInt("minapn"));
          }
          if ((this.e == -1L) && (!localFile.exists()));
          i = j;
          if (this.e != -1L)
          {
            long l1 = this.e;
            long l2 = System.currentTimeMillis();
            i = j;
            if (l1 + 86400000L <= l2)
              i = 1;
          }
          if (((this.e != -1L) && (i == 0)) || (!b()) || (!k.jdMethod_for(j.u(j.this).jdMethod_char())))
            continue;
          this.f = true;
          ao();
          return;
        }
        catch (Exception localException)
        {
          while (true)
            int i = j;
        }
      }
    }

    private boolean b()
    {
      boolean bool = true;
      if (this.c < 2);
      while (true)
      {
        if (!bool);
        return bool;
        if (this.d + 86400000L < System.currentTimeMillis())
        {
          this.c = 0;
          this.d = -1L;
        }
        else
        {
          bool = false;
        }
      }
    }

    public void au()
    {
      this.c7.clear();
      this.c7.add(new BasicNameValuePair("qt", "conf"));
      this.c7.add(new BasicNameValuePair("req", this.g));
      this.c5 = d.ak;
    }

    public void jdMethod_int(boolean paramBoolean)
    {
      if ((paramBoolean) && (this.c6 != null));
      while (true)
      {
        try
        {
          JSONObject localJSONObject2 = new JSONObject(EntityUtils.toString(this.c6, "utf-8"));
          Object localObject1 = "1";
          long l = 0L;
          if (localJSONObject2.has("ofl"))
            l = localJSONObject2.getLong("ofl");
          if (localJSONObject2.has("ver"))
            localObject1 = localJSONObject2.getString("ver");
          if ((l & 1L) == 1L)
            j.a(j.this, true);
          if ((l & 0x2) == 2L)
            j.b(j.this, true);
          if ((l & 0x4) == 4L)
            j.c(j.this, true);
          if ((l & 0x8) == 8L)
            j.d(j.this, true);
          if ((0x10 & l) == 16L)
            j.e(j.this, true);
          if ((l & 0x20) == 32L)
            j.f(j.this, true);
          JSONObject localJSONObject1 = new JSONObject();
          if (localJSONObject2.has("cplist"))
          {
            j.a(j.this, localJSONObject2.getString("cplist").split(";"));
            localJSONObject1.put("cplist", localJSONObject2.getString("cplist"));
          }
          Object localObject2;
          if (localJSONObject2.has("bklist"))
          {
            localObject2 = localJSONObject2.getString("bklist").split(";");
            j.this.a((String[])localObject2);
          }
          if (localJSONObject2.has("para"))
          {
            localJSONObject2 = localJSONObject2.getJSONObject("para");
            if (localJSONObject2.has("rgcgp"))
              j.a(j.this, localJSONObject2.getInt("rgcgp"));
            if (localJSONObject2.has("addrup"))
              j.b(j.this, localJSONObject2.getInt("addrup"));
            if (localJSONObject2.has("poiup"))
              j.c(j.this, localJSONObject2.getInt("poiup"));
            if (localJSONObject2.has("oflp"))
            {
              localObject2 = localJSONObject2.getJSONObject("oflp");
              if (((JSONObject)localObject2).has("0"))
                j.a(j.this, ((JSONObject)localObject2).getDouble("0"));
              if (((JSONObject)localObject2).has("1"))
                j.b(j.this, ((JSONObject)localObject2).getDouble("1"));
              if (((JSONObject)localObject2).has("2"))
                j.c(j.this, ((JSONObject)localObject2).getDouble("2"));
              if (((JSONObject)localObject2).has("3"))
                j.d(j.this, ((JSONObject)localObject2).getDouble("3"));
              if (((JSONObject)localObject2).has("4"))
                j.e(j.this, ((JSONObject)localObject2).getDouble("4"));
            }
            if (localJSONObject2.has("onlt"))
            {
              localObject2 = localJSONObject2.getJSONObject("onlt");
              if (((JSONObject)localObject2).has("0"))
                j.a(j.this, ((JSONObject)localObject2).getLong("0"));
              if (((JSONObject)localObject2).has("1"))
                j.b(j.this, ((JSONObject)localObject2).getLong("1"));
              if (((JSONObject)localObject2).has("2"))
                j.c(j.this, ((JSONObject)localObject2).getLong("2"));
              if (((JSONObject)localObject2).has("3"))
                j.d(j.this, ((JSONObject)localObject2).getLong("3"));
              if (((JSONObject)localObject2).has("4"))
                j.e(j.this, ((JSONObject)localObject2).getLong("4"));
            }
            if (localJSONObject2.has("minapn"))
              j.d(j.this, localJSONObject2.getInt("minapn"));
          }
          localJSONObject1.put("ol", j.a(j.this));
          localJSONObject1.put("fl", j.b(j.this));
          localJSONObject1.put("on", j.c(j.this));
          localJSONObject1.put("wn", j.d(j.this));
          localJSONObject1.put("oc", j.e(j.this));
          this.e = System.currentTimeMillis();
          localJSONObject1.put("t", this.e);
          localJSONObject1.put("ver", localObject1);
          localJSONObject1.put("rgcon", j.f(j.this));
          localJSONObject1.put("rgcgp", j.g(j.this));
          localObject1 = new JSONObject();
          ((JSONObject)localObject1).put("0", j.h(j.this));
          ((JSONObject)localObject1).put("1", j.i(j.this));
          ((JSONObject)localObject1).put("2", j.j(j.this));
          ((JSONObject)localObject1).put("3", j.k(j.this));
          ((JSONObject)localObject1).put("4", j.l(j.this));
          localJSONObject1.put("oflp", localObject1);
          localObject1 = new JSONObject();
          ((JSONObject)localObject1).put("0", j.m(j.this));
          ((JSONObject)localObject1).put("1", j.n(j.this));
          ((JSONObject)localObject1).put("2", j.o(j.this));
          ((JSONObject)localObject1).put("3", j.p(j.this));
          ((JSONObject)localObject1).put("4", j.q(j.this));
          localJSONObject1.put("onlt", localObject1);
          localJSONObject1.put("addrup", j.r(j.this));
          localJSONObject1.put("poiup", j.s(j.this));
          localJSONObject1.put("minapn", j.t(j.this));
          localObject1 = new File(j.u(j.this).d(), "ofl.config");
          if (!((File)localObject1).exists())
            ((File)localObject1).createNewFile();
          localObject1 = new FileWriter((File)localObject1);
          ((FileWriter)localObject1).write(localJSONObject1.toString());
          ((FileWriter)localObject1).close();
          this.f = false;
          return;
        }
        catch (Exception localException)
        {
          this.c += 1;
          this.d = System.currentTimeMillis();
          continue;
        }
        this.c += 1;
        this.d = System.currentTimeMillis();
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.c.j
 * JD-Core Version:    0.6.2
 */