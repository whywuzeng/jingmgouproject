package com.baidu.location.c;

import android.database.sqlite.SQLiteDatabase;
import com.baidu.location.Jni;
import com.baidu.location.b.b;
import com.baidu.location.b.m;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.apache.http.ParseException;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

final class q
  implements b
{
  private static final String e = String.format(Locale.US, "DELETE FROM LOG WHERE timestamp NOT IN (SELECT timestamp FROM LOG ORDER BY timestamp DESC LIMIT %d);", new Object[] { Integer.valueOf(3000) });
  private static final String f = String.format(Locale.US, "SELECT * FROM LOG ORDER BY timestamp DESC LIMIT %d;", new Object[] { Integer.valueOf(3) });
  private String b = null;
  private final SQLiteDatabase c;
  private final a d;

  q(SQLiteDatabase paramSQLiteDatabase)
  {
    this.c = paramSQLiteDatabase;
    this.d = new a(this);
    if ((this.c != null) && (this.c.isOpen()))
      this.c.execSQL("CREATE TABLE IF NOT EXISTS LOG(timestamp LONG PRIMARY KEY, log VARCHAR(4000))");
  }

  private void a(boolean paramBoolean)
  {
    String str;
    if ((paramBoolean) && (this.b != null))
    {
      str = String.format("DELETE FROM LOG WHERE timestamp in (%s);", new Object[] { this.b });
      if (this.b.length() <= 0);
    }
    try
    {
      this.c.execSQL(str);
      label46: this.b = null;
      return;
    }
    catch (Exception localException)
    {
      break label46;
    }
  }

  // ERROR //
  private String b()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aconst_null
    //   3: astore 4
    //   5: aconst_null
    //   6: astore 6
    //   8: aconst_null
    //   9: astore 5
    //   11: new 94	org/json/JSONArray
    //   14: dup
    //   15: invokespecial 95	org/json/JSONArray:<init>	()V
    //   18: astore 7
    //   20: new 97	org/json/JSONObject
    //   23: dup
    //   24: invokespecial 98	org/json/JSONObject:<init>	()V
    //   27: astore 8
    //   29: aload_0
    //   30: getfield 54	com/baidu/location/c/q:c	Landroid/database/sqlite/SQLiteDatabase;
    //   33: getstatic 45	com/baidu/location/c/q:f	Ljava/lang/String;
    //   36: aconst_null
    //   37: invokevirtual 102	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   40: astore_2
    //   41: aload 6
    //   43: astore_1
    //   44: aload_2
    //   45: ifnull +172 -> 217
    //   48: aload 5
    //   50: astore_3
    //   51: aload 6
    //   53: astore_1
    //   54: aload_2
    //   55: invokeinterface 107 1 0
    //   60: ifle +157 -> 217
    //   63: aload 5
    //   65: astore_3
    //   66: new 109	java/lang/StringBuffer
    //   69: dup
    //   70: invokespecial 110	java/lang/StringBuffer:<init>	()V
    //   73: astore 6
    //   75: aload 5
    //   77: astore_3
    //   78: aload_2
    //   79: invokeinterface 113 1 0
    //   84: pop
    //   85: aload 5
    //   87: astore_3
    //   88: aload_2
    //   89: invokeinterface 116 1 0
    //   94: ifne +90 -> 184
    //   97: aload 5
    //   99: astore_3
    //   100: aload 7
    //   102: aload_2
    //   103: iconst_1
    //   104: invokeinterface 120 2 0
    //   109: invokevirtual 124	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   112: pop
    //   113: aload 5
    //   115: astore_3
    //   116: aload 6
    //   118: invokevirtual 125	java/lang/StringBuffer:length	()I
    //   121: ifeq +14 -> 135
    //   124: aload 5
    //   126: astore_3
    //   127: aload 6
    //   129: ldc 127
    //   131: invokevirtual 131	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   134: pop
    //   135: aload 5
    //   137: astore_3
    //   138: aload 6
    //   140: aload_2
    //   141: iconst_0
    //   142: invokeinterface 135 2 0
    //   147: invokevirtual 138	java/lang/StringBuffer:append	(J)Ljava/lang/StringBuffer;
    //   150: pop
    //   151: aload 5
    //   153: astore_3
    //   154: aload_2
    //   155: invokeinterface 141 1 0
    //   160: pop
    //   161: goto -76 -> 85
    //   164: astore_1
    //   165: aload_3
    //   166: astore 4
    //   168: aload_2
    //   169: ifnull +12 -> 181
    //   172: aload_2
    //   173: invokeinterface 144 1 0
    //   178: aload_3
    //   179: astore 4
    //   181: aload 4
    //   183: areturn
    //   184: aload 5
    //   186: astore_3
    //   187: aload 8
    //   189: ldc 146
    //   191: aload 7
    //   193: invokevirtual 149	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   196: pop
    //   197: aload 5
    //   199: astore_3
    //   200: aload 8
    //   202: invokevirtual 152	org/json/JSONObject:toString	()Ljava/lang/String;
    //   205: astore_1
    //   206: aload_1
    //   207: astore_3
    //   208: aload_0
    //   209: aload 6
    //   211: invokevirtual 153	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   214: putfield 52	com/baidu/location/c/q:b	Ljava/lang/String;
    //   217: aload_1
    //   218: astore 4
    //   220: aload_2
    //   221: ifnull -40 -> 181
    //   224: aload_2
    //   225: invokeinterface 144 1 0
    //   230: aload_1
    //   231: areturn
    //   232: astore_2
    //   233: aload_1
    //   234: areturn
    //   235: astore_1
    //   236: aconst_null
    //   237: astore_2
    //   238: aload_2
    //   239: ifnull +9 -> 248
    //   242: aload_2
    //   243: invokeinterface 144 1 0
    //   248: aload_1
    //   249: athrow
    //   250: astore_1
    //   251: aload_3
    //   252: areturn
    //   253: astore_2
    //   254: goto -6 -> 248
    //   257: astore_1
    //   258: goto -20 -> 238
    //   261: astore_1
    //   262: aconst_null
    //   263: astore_2
    //   264: goto -99 -> 165
    //   267: astore_1
    //   268: aload 4
    //   270: astore_1
    //   271: goto -65 -> 206
    //
    // Exception table:
    //   from	to	target	type
    //   54	63	164	java/lang/Exception
    //   66	75	164	java/lang/Exception
    //   78	85	164	java/lang/Exception
    //   88	97	164	java/lang/Exception
    //   100	113	164	java/lang/Exception
    //   116	124	164	java/lang/Exception
    //   127	135	164	java/lang/Exception
    //   138	151	164	java/lang/Exception
    //   154	161	164	java/lang/Exception
    //   187	197	164	java/lang/Exception
    //   200	206	164	java/lang/Exception
    //   208	217	164	java/lang/Exception
    //   224	230	232	java/lang/Exception
    //   29	41	235	finally
    //   172	178	250	java/lang/Exception
    //   242	248	253	java/lang/Exception
    //   54	63	257	finally
    //   66	75	257	finally
    //   78	85	257	finally
    //   88	97	257	finally
    //   100	113	257	finally
    //   116	124	257	finally
    //   127	135	257	finally
    //   138	151	257	finally
    //   154	161	257	finally
    //   187	197	257	finally
    //   200	206	257	finally
    //   208	217	257	finally
    //   29	41	261	java/lang/Exception
    //   187	197	267	org/json/JSONException
    //   200	206	267	org/json/JSONException
  }

  void a()
  {
    a.a(this.d);
  }

  void a(String paramString)
  {
    paramString = Jni.G(paramString);
    paramString = String.format(Locale.US, "INSERT OR IGNORE INTO LOG VALUES (%d,\"%s\");", new Object[] { Long.valueOf(System.currentTimeMillis()), paramString });
    try
    {
      this.c.execSQL(paramString);
      this.c.execSQL(e);
      return;
    }
    catch (Exception paramString)
    {
    }
  }

  private class a extends m
  {
    private int c;
    private long d;
    private String e;
    private boolean f;
    private boolean g;
    private q h;

    a(q arg2)
    {
      Object localObject;
      this.h = localObject;
      this.e = null;
      this.f = false;
      this.g = false;
      this.c7 = new ArrayList();
      this.c = 0;
      this.d = -1L;
    }

    private void a()
    {
      if (!this.f)
      {
        this.e = q.a(this.h);
        if ((this.d != -1L) && (this.d + 86400000L <= System.currentTimeMillis()))
        {
          this.c = 0;
          this.d = -1L;
        }
        if ((this.e != null) && (this.c < 2))
        {
          this.f = true;
          ao();
        }
      }
    }

    public void au()
    {
      this.c7.clear();
      this.c7.add(new BasicNameValuePair("qt", "ofbh"));
      this.c7.add(new BasicNameValuePair("req", this.e));
      this.c5 = d.ak;
    }

    public void jdMethod_int(boolean paramBoolean)
    {
      this.g = false;
      if ((paramBoolean) && (this.c6 != null));
      try
      {
        JSONObject localJSONObject = new JSONObject(EntityUtils.toString(this.c6, "utf-8"));
        if ((localJSONObject != null) && (localJSONObject.has("error")) && (localJSONObject.getInt("error") == 161))
          this.g = true;
        label63: this.f = false;
        if (!this.g)
        {
          this.c += 1;
          this.d = System.currentTimeMillis();
        }
        new r(this).start();
        return;
      }
      catch (IOException localIOException)
      {
        break label63;
      }
      catch (JSONException localJSONException)
      {
        break label63;
      }
      catch (ParseException localParseException)
      {
        break label63;
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.c.q
 * JD-Core Version:    0.6.2
 */