package com.baidu.location.c;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.net.wifi.ScanResult;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import com.baidu.location.BDLocation;
import com.baidu.location.Jni;
import com.baidu.location.b.o;
import com.baidu.location.e.m;
import com.baidu.location.h.h;
import com.baidu.location.h.j;
import com.baidu.location.h.l;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONObject;

public final class b
  implements com.baidu.location.b.f
{
  private static final int fe = 10000;
  private static b fg = null;
  private static final String fi = Environment.getExternalStorageDirectory().getPath() + "/baidu/tempdata/";
  private static final int fj = 6;
  private static final String fo = "bdcltb09";
  private static final String fs = Environment.getExternalStorageDirectory().getPath() + "/baidu/tempdata/" + "/ls.db";
  private static final String ft = "wof";
  private static final int fv = 2000;
  private Handler ff = new Handler();
  private double fh = 0.0D;
  private double fk = 0.0D;
  private double fl = 0.0D;
  private String fm = null;
  private boolean fn = false;
  private volatile boolean fp = false;
  private double fq = 0.0D;
  private double fr = 0.0D;
  private boolean fu = false;

  private b()
  {
    try
    {
      Object localObject = new File(fi);
      File localFile = new File(fs);
      if (!((File)localObject).exists())
        ((File)localObject).mkdirs();
      if (!localFile.exists())
        localFile.createNewFile();
      if (localFile.exists())
      {
        localObject = SQLiteDatabase.openOrCreateDatabase(localFile, null);
        ((SQLiteDatabase)localObject).execSQL("CREATE TABLE IF NOT EXISTS bdcltb09(id CHAR(40) PRIMARY KEY,time DOUBLE,tag DOUBLE, type DOUBLE , ac INT);");
        ((SQLiteDatabase)localObject).execSQL("CREATE TABLE IF NOT EXISTS wof(id CHAR(15) PRIMARY KEY,mktime DOUBLE,time DOUBLE, ac INT, bc INT, cc INT);");
        ((SQLiteDatabase)localObject).setVersion(1);
        ((SQLiteDatabase)localObject).close();
      }
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public static b a0()
  {
    if (fg == null)
      fg = new b();
    return fg;
  }

  private void aZ()
  {
    boolean bool2 = true;
    SQLiteDatabase localSQLiteDatabase2;
    try
    {
      SQLiteDatabase localSQLiteDatabase1 = SQLiteDatabase.openOrCreateDatabase(fs, null);
      if (localSQLiteDatabase1 == null)
        return;
    }
    catch (Exception localException1)
    {
      while (true)
        localSQLiteDatabase2 = null;
    }
    while (true)
    {
      long l2;
      try
      {
        long l1 = DatabaseUtils.queryNumEntries(localSQLiteDatabase2, "wof");
        l2 = DatabaseUtils.queryNumEntries(localSQLiteDatabase2, "bdcltb09");
        if (l1 > 10000L)
        {
          bool1 = true;
          break label105;
          new a(null).execute(new Boolean[] { Boolean.valueOf(bool1), Boolean.valueOf(bool2) });
          localSQLiteDatabase2.close();
          return;
        }
      }
      catch (Exception localException2)
      {
        return;
      }
      boolean bool1 = false;
      label105: 
      while (l2 <= 10000L)
      {
        bool2 = false;
        break;
      }
      if (!bool1)
        if (!bool2);
    }
  }

  private void jdMethod_do(String paramString, List paramList)
  {
    Object localObject;
    if ((paramString != null) && (!paramString.equals(this.fm)))
    {
      localObject = SQLiteDatabase.openOrCreateDatabase(fs, null);
      jdMethod_if(paramString, (SQLiteDatabase)localObject);
    }
    for (paramString = (String)localObject; ; paramString = null)
    {
      localObject = paramString;
      if (paramList != null)
      {
        localObject = paramString;
        if (paramString == null)
          localObject = SQLiteDatabase.openOrCreateDatabase(fs, null);
        jdMethod_if(paramList, (SQLiteDatabase)localObject);
      }
      if ((localObject != null) && (((SQLiteDatabase)localObject).isOpen()))
        ((SQLiteDatabase)localObject).close();
      return;
    }
  }

  private void jdMethod_if(com.baidu.location.h.f paramf, BDLocation paramBDLocation, SQLiteDatabase paramSQLiteDatabase)
  {
    if ((paramBDLocation == null) || (paramBDLocation.getLocType() != 161) || ((!"wf".equals(paramBDLocation.getNetworkLocationType())) && (paramBDLocation.getRadius() >= 300.0F)))
      return;
    while (paramf.km == null);
    int n = (int)(System.currentTimeMillis() >> 28);
    System.currentTimeMillis();
    paramf = paramf.km.iterator();
    int i = 0;
    while (paramf.hasNext())
    {
      Object localObject1 = (ScanResult)paramf.next();
      if (((ScanResult)localObject1).level == 0)
        break;
      i += 1;
      if (i > 6)
        break;
      ContentValues localContentValues = new ContentValues();
      localObject1 = Jni.J(((ScanResult)localObject1).BSSID.replace(":", ""));
      try
      {
        Object localObject2 = paramSQLiteDatabase.rawQuery("select * from wof where id = \"" + (String)localObject1 + "\";", null);
        double d2;
        double d1;
        int j;
        int m;
        int k;
        if ((localObject2 != null) && (((Cursor)localObject2).moveToFirst()))
        {
          d2 = ((Cursor)localObject2).getDouble(1);
          d1 = ((Cursor)localObject2).getDouble(2);
          j = ((Cursor)localObject2).getInt(4);
          m = ((Cursor)localObject2).getInt(5);
          d2 -= 113.2349D;
          k = 1;
          d1 -= 432.12380000000002D;
          label247: if (localObject2 != null)
            ((Cursor)localObject2).close();
          if (k != 0)
            break label683;
          localContentValues.put("mktime", Double.valueOf(paramBDLocation.getLongitude() + 113.2349D));
          localContentValues.put("time", Double.valueOf(paramBDLocation.getLatitude() + 432.12380000000002D));
          localContentValues.put("bc", Integer.valueOf(1));
          localContentValues.put("cc", Integer.valueOf(1));
          localContentValues.put("ac", Integer.valueOf(n));
          localContentValues.put("id", (String)localObject1);
          paramSQLiteDatabase.insert("wof", null, localContentValues);
        }
        label680: label683: 
        while (m != 0)
        {
          localObject2 = new float[1];
          Location.distanceBetween(d1, d2, paramBDLocation.getLatitude(), paramBDLocation.getLongitude(), (float[])localObject2);
          if (localObject2[0] > 1500.0F)
          {
            k = m + 1;
            if ((k > 10) && (k > j * 3))
            {
              localContentValues.put("mktime", Double.valueOf(paramBDLocation.getLongitude() + 113.2349D));
              localContentValues.put("time", Double.valueOf(paramBDLocation.getLatitude() + 432.12380000000002D));
              localContentValues.put("bc", Integer.valueOf(1));
              localContentValues.put("cc", Integer.valueOf(1));
              localContentValues.put("ac", Integer.valueOf(n));
            }
          }
          try
          {
            while (true)
            {
              j = paramSQLiteDatabase.update("wof", localContentValues, "id = \"" + (String)localObject1 + "\"", null);
              if (j > 0)
                break;
              break;
              localContentValues.put("cc", Integer.valueOf(k));
              continue;
              d2 = (d2 * j + paramBDLocation.getLongitude()) / (j + 1);
              d1 = (d1 * j + paramBDLocation.getLatitude()) / (j + 1);
              localContentValues.put("mktime", Double.valueOf(d2 + 113.2349D));
              localContentValues.put("time", Double.valueOf(d1 + 432.12380000000002D));
              localContentValues.put("bc", Integer.valueOf(j + 1));
              localContentValues.put("ac", Integer.valueOf(n));
            }
          }
          catch (Exception localException1)
          {
          }
          m = 0;
          j = 0;
          d2 = 0.0D;
          k = 0;
          d1 = 0.0D;
          break label247;
          break;
        }
      }
      catch (Exception localException2)
      {
        break label680;
      }
    }
  }

  // ERROR //
  private void jdMethod_if(String paramString, SQLiteDatabase paramSQLiteDatabase)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aload_1
    //   3: ifnull +14 -> 17
    //   6: aload_1
    //   7: aload_0
    //   8: getfield 85	com/baidu/location/c/b:fm	Ljava/lang/String;
    //   11: invokevirtual 181	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   14: ifeq +4 -> 18
    //   17: return
    //   18: aload_0
    //   19: iconst_0
    //   20: putfield 87	com/baidu/location/c/b:fn	Z
    //   23: aload_2
    //   24: new 49	java/lang/StringBuilder
    //   27: dup
    //   28: invokespecial 52	java/lang/StringBuilder:<init>	()V
    //   31: ldc_w 359
    //   34: invokevirtual 68	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   37: aload_1
    //   38: invokevirtual 68	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   41: ldc_w 277
    //   44: invokevirtual 68	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   47: invokevirtual 73	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   50: aconst_null
    //   51: invokevirtual 281	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   54: astore_2
    //   55: aload_2
    //   56: astore_3
    //   57: aload_0
    //   58: aload_1
    //   59: putfield 85	com/baidu/location/c/b:fm	Ljava/lang/String;
    //   62: aload_2
    //   63: astore_3
    //   64: aload_2
    //   65: invokeinterface 286 1 0
    //   70: ifeq +61 -> 131
    //   73: aload_2
    //   74: astore_3
    //   75: aload_0
    //   76: aload_2
    //   77: iconst_1
    //   78: invokeinterface 290 2 0
    //   83: ldc2_w 360
    //   86: dsub
    //   87: putfield 93	com/baidu/location/c/b:fr	D
    //   90: aload_2
    //   91: astore_3
    //   92: aload_0
    //   93: aload_2
    //   94: iconst_2
    //   95: invokeinterface 290 2 0
    //   100: ldc2_w 362
    //   103: dsub
    //   104: putfield 91	com/baidu/location/c/b:fh	D
    //   107: aload_2
    //   108: astore_3
    //   109: aload_0
    //   110: aload_2
    //   111: iconst_3
    //   112: invokeinterface 290 2 0
    //   117: ldc2_w 364
    //   120: dsub
    //   121: putfield 95	com/baidu/location/c/b:fq	D
    //   124: aload_2
    //   125: astore_3
    //   126: aload_0
    //   127: iconst_1
    //   128: putfield 87	com/baidu/location/c/b:fn	Z
    //   131: aload_2
    //   132: ifnull -115 -> 17
    //   135: aload_2
    //   136: invokeinterface 299 1 0
    //   141: return
    //   142: astore_1
    //   143: return
    //   144: astore_1
    //   145: aload_3
    //   146: ifnull -129 -> 17
    //   149: aload_3
    //   150: invokeinterface 299 1 0
    //   155: return
    //   156: astore_1
    //   157: return
    //   158: astore_1
    //   159: aconst_null
    //   160: astore_2
    //   161: aload_2
    //   162: ifnull +9 -> 171
    //   165: aload_2
    //   166: invokeinterface 299 1 0
    //   171: aload_1
    //   172: athrow
    //   173: astore_2
    //   174: goto -3 -> 171
    //   177: astore_1
    //   178: goto -17 -> 161
    //
    // Exception table:
    //   from	to	target	type
    //   135	141	142	java/lang/Exception
    //   23	55	144	java/lang/Exception
    //   57	62	144	java/lang/Exception
    //   64	73	144	java/lang/Exception
    //   75	90	144	java/lang/Exception
    //   92	107	144	java/lang/Exception
    //   109	124	144	java/lang/Exception
    //   126	131	144	java/lang/Exception
    //   149	155	156	java/lang/Exception
    //   23	55	158	finally
    //   165	171	173	java/lang/Exception
    //   57	62	177	finally
    //   64	73	177	finally
    //   75	90	177	finally
    //   92	107	177	finally
    //   109	124	177	finally
    //   126	131	177	finally
  }

  private void jdMethod_if(String paramString, h paramh, SQLiteDatabase paramSQLiteDatabase)
  {
    int i = 0;
    double d2 = 0.0D;
    if ((!paramh.du()) || (!m.bb().bh()))
      return;
    System.currentTimeMillis();
    int j = (int)(System.currentTimeMillis() >> 28);
    paramh = paramh.dC();
    while (true)
    {
      try
      {
        int k;
        Object localObject;
        while (true)
        {
          paramString = new JSONObject(paramString);
          k = Integer.parseInt(paramString.getJSONObject("result").getString("error"));
          if (k != 161)
            break label327;
          paramString = paramString.getJSONObject("content");
          if (!paramString.has("clf"))
            break label372;
          localObject = paramString.getString("clf");
          if (((String)localObject).equals("0"))
          {
            localObject = paramString.getJSONObject("point");
            d2 = Double.parseDouble(((JSONObject)localObject).getString("x"));
            d1 = Double.parseDouble(((JSONObject)localObject).getString("y"));
            f = Float.parseFloat(paramString.getString("radius"));
            if (i != 0)
              break;
            paramString = new ContentValues();
            paramString.put("time", Double.valueOf(d2 + 1235.4322999999999D));
            paramString.put("tag", Float.valueOf(4326.0F + f));
            paramString.put("type", Double.valueOf(d1 + 2367.3217D));
            paramString.put("ac", Integer.valueOf(j));
            try
            {
              if (paramSQLiteDatabase.update("bdcltb09", paramString, "id = \"" + paramh + "\"", null) > 0)
                break;
              paramString.put("id", paramh);
              paramSQLiteDatabase.insert("bdcltb09", null, paramString);
              return;
            }
            catch (Exception paramString)
            {
              return;
            }
          }
        }
        paramString = ((String)localObject).split("\\|");
        d2 = Double.parseDouble(paramString[0]);
        d1 = Double.parseDouble(paramString[1]);
        f = Float.parseFloat(paramString[2]);
        continue;
        label327: if (k == 167)
        {
          paramSQLiteDatabase.delete("bdcltb09", "id = \"" + paramh + "\"", null);
          return;
        }
      }
      catch (Exception paramString)
      {
        return;
      }
      label372: i = 1;
      float f = 0.0F;
      double d1 = 0.0D;
    }
  }

  // ERROR //
  private void jdMethod_if(List paramList, SQLiteDatabase paramSQLiteDatabase)
  {
    // Byte code:
    //   0: invokestatic 233	java/lang/System:currentTimeMillis	()J
    //   3: pop2
    //   4: aload_0
    //   5: iconst_0
    //   6: putfield 89	com/baidu/location/c/b:fu	Z
    //   9: aload_1
    //   10: ifnonnull +4 -> 14
    //   13: return
    //   14: aload_2
    //   15: ifnull -2 -> 13
    //   18: aload_1
    //   19: ifnull -6 -> 13
    //   22: dconst_0
    //   23: dstore 5
    //   25: dconst_0
    //   26: dstore_3
    //   27: iconst_0
    //   28: istore 17
    //   30: iconst_0
    //   31: istore 18
    //   33: bipush 8
    //   35: newarray double
    //   37: astore 21
    //   39: iconst_0
    //   40: istore 19
    //   42: iconst_0
    //   43: istore 16
    //   45: new 451	java/lang/StringBuffer
    //   48: dup
    //   49: invokespecial 452	java/lang/StringBuffer:<init>	()V
    //   52: astore 22
    //   54: aload_1
    //   55: invokeinterface 239 1 0
    //   60: astore_1
    //   61: iconst_0
    //   62: istore 15
    //   64: aload_1
    //   65: invokeinterface 244 1 0
    //   70: ifeq +21 -> 91
    //   73: aload_1
    //   74: invokeinterface 248 1 0
    //   79: checkcast 250	android/net/wifi/ScanResult
    //   82: astore 23
    //   84: iload 15
    //   86: bipush 10
    //   88: if_icmple +158 -> 246
    //   91: aconst_null
    //   92: astore_1
    //   93: aload_2
    //   94: new 49	java/lang/StringBuilder
    //   97: dup
    //   98: invokespecial 52	java/lang/StringBuilder:<init>	()V
    //   101: ldc_w 454
    //   104: invokevirtual 68	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   107: aload 22
    //   109: invokevirtual 455	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   112: invokevirtual 68	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   115: ldc_w 457
    //   118: invokevirtual 68	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   121: invokevirtual 73	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   124: aconst_null
    //   125: invokevirtual 281	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   128: astore_2
    //   129: aload_2
    //   130: astore_1
    //   131: aload_1
    //   132: invokeinterface 286 1 0
    //   137: ifeq +323 -> 460
    //   140: iload 19
    //   142: istore 15
    //   144: iload 17
    //   146: istore 19
    //   148: dload_3
    //   149: dstore 9
    //   151: dload 5
    //   153: dstore 7
    //   155: aload_1
    //   156: invokeinterface 460 1 0
    //   161: ifne +269 -> 430
    //   164: aload_1
    //   165: iconst_1
    //   166: invokeinterface 290 2 0
    //   171: ldc2_w 295
    //   174: dsub
    //   175: dstore 13
    //   177: aload_1
    //   178: iconst_2
    //   179: invokeinterface 290 2 0
    //   184: ldc2_w 297
    //   187: dsub
    //   188: dstore 11
    //   190: aload_1
    //   191: iconst_4
    //   192: invokeinterface 294 2 0
    //   197: istore 19
    //   199: aload_1
    //   200: iconst_5
    //   201: invokeinterface 294 2 0
    //   206: istore 20
    //   208: iload 20
    //   210: bipush 8
    //   212: if_icmple +96 -> 308
    //   215: iload 20
    //   217: iload 19
    //   219: if_icmple +89 -> 308
    //   222: aload_1
    //   223: invokeinterface 463 1 0
    //   228: pop
    //   229: goto -85 -> 144
    //   232: astore_2
    //   233: aload_1
    //   234: ifnull -221 -> 13
    //   237: aload_1
    //   238: invokeinterface 299 1 0
    //   243: return
    //   244: astore_1
    //   245: return
    //   246: iload 15
    //   248: ifle +12 -> 260
    //   251: aload 22
    //   253: ldc_w 465
    //   256: invokevirtual 468	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   259: pop
    //   260: iload 15
    //   262: iconst_1
    //   263: iadd
    //   264: istore 15
    //   266: aload 23
    //   268: getfield 259	android/net/wifi/ScanResult:BSSID	Ljava/lang/String;
    //   271: ldc_w 261
    //   274: ldc_w 263
    //   277: invokevirtual 267	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   280: invokestatic 273	com/baidu/location/Jni:J	(Ljava/lang/String;)Ljava/lang/String;
    //   283: astore 23
    //   285: aload 22
    //   287: ldc_w 353
    //   290: invokevirtual 468	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   293: aload 23
    //   295: invokevirtual 468	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   298: ldc_w 353
    //   301: invokevirtual 468	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   304: pop
    //   305: goto -241 -> 64
    //   308: aload_0
    //   309: getfield 87	com/baidu/location/c/b:fn	Z
    //   312: ifeq +161 -> 473
    //   315: iconst_1
    //   316: newarray float
    //   318: astore_2
    //   319: dload 11
    //   321: dload 13
    //   323: aload_0
    //   324: getfield 95	com/baidu/location/c/b:fq	D
    //   327: aload_0
    //   328: getfield 93	com/baidu/location/c/b:fr	D
    //   331: aload_2
    //   332: invokestatic 348	android/location/Location:distanceBetween	(DDDD[F)V
    //   335: aload_2
    //   336: iconst_0
    //   337: faload
    //   338: f2d
    //   339: aload_0
    //   340: getfield 91	com/baidu/location/c/b:fh	D
    //   343: ldc2_w 469
    //   346: dadd
    //   347: dcmpl
    //   348: ifle +32 -> 380
    //   351: aload_1
    //   352: invokeinterface 463 1 0
    //   357: pop
    //   358: goto -214 -> 144
    //   361: astore 21
    //   363: aload_1
    //   364: astore_2
    //   365: aload 21
    //   367: astore_1
    //   368: aload_2
    //   369: ifnull +9 -> 378
    //   372: aload_2
    //   373: invokeinterface 299 1 0
    //   378: aload_1
    //   379: athrow
    //   380: iconst_1
    //   381: istore 18
    //   383: dload 5
    //   385: dload 13
    //   387: dadd
    //   388: dstore 5
    //   390: dload_3
    //   391: dload 11
    //   393: dadd
    //   394: dstore_3
    //   395: iload 17
    //   397: iconst_1
    //   398: iadd
    //   399: istore 17
    //   401: iload 16
    //   403: istore 19
    //   405: iload 15
    //   407: istore 16
    //   409: iload 19
    //   411: istore 15
    //   413: iload 17
    //   415: iconst_4
    //   416: if_icmple +186 -> 602
    //   419: dload 5
    //   421: dstore 7
    //   423: dload_3
    //   424: dstore 9
    //   426: iload 17
    //   428: istore 19
    //   430: iload 19
    //   432: ifle +28 -> 460
    //   435: aload_0
    //   436: iconst_1
    //   437: putfield 89	com/baidu/location/c/b:fu	Z
    //   440: aload_0
    //   441: dload 7
    //   443: iload 19
    //   445: i2d
    //   446: ddiv
    //   447: putfield 97	com/baidu/location/c/b:fl	D
    //   450: aload_0
    //   451: dload 9
    //   453: iload 19
    //   455: i2d
    //   456: ddiv
    //   457: putfield 99	com/baidu/location/c/b:fk	D
    //   460: aload_1
    //   461: ifnull -448 -> 13
    //   464: aload_1
    //   465: invokeinterface 299 1 0
    //   470: return
    //   471: astore_1
    //   472: return
    //   473: iload 18
    //   475: ifeq +193 -> 668
    //   478: iconst_1
    //   479: newarray float
    //   481: astore_2
    //   482: dload 11
    //   484: dload 13
    //   486: dload_3
    //   487: iload 17
    //   489: i2d
    //   490: ddiv
    //   491: dload 5
    //   493: iload 17
    //   495: i2d
    //   496: ddiv
    //   497: aload_2
    //   498: invokestatic 348	android/location/Location:distanceBetween	(DDDD[F)V
    //   501: aload_2
    //   502: iconst_0
    //   503: faload
    //   504: ldc_w 471
    //   507: fcmpl
    //   508: ifle +145 -> 653
    //   511: aload_1
    //   512: invokeinterface 463 1 0
    //   517: pop
    //   518: goto -374 -> 144
    //   521: iload 19
    //   523: iload 16
    //   525: if_icmpge +211 -> 736
    //   528: iconst_1
    //   529: newarray float
    //   531: astore_2
    //   532: dload 11
    //   534: dload 13
    //   536: aload 21
    //   538: iload 19
    //   540: iconst_1
    //   541: iadd
    //   542: daload
    //   543: aload 21
    //   545: iload 19
    //   547: daload
    //   548: aload_2
    //   549: invokestatic 348	android/location/Location:distanceBetween	(DDDD[F)V
    //   552: aload_2
    //   553: iconst_0
    //   554: faload
    //   555: ldc_w 471
    //   558: fcmpg
    //   559: ifge +79 -> 638
    //   562: iconst_1
    //   563: istore 18
    //   565: dload 5
    //   567: aload 21
    //   569: iload 19
    //   571: daload
    //   572: dadd
    //   573: dstore 5
    //   575: aload 21
    //   577: iload 19
    //   579: iconst_1
    //   580: iadd
    //   581: daload
    //   582: dload_3
    //   583: dadd
    //   584: dstore_3
    //   585: iload 17
    //   587: iconst_1
    //   588: iadd
    //   589: istore 20
    //   591: iload 18
    //   593: istore 17
    //   595: iload 20
    //   597: istore 18
    //   599: goto +112 -> 711
    //   602: aload_1
    //   603: invokeinterface 463 1 0
    //   608: pop
    //   609: iload 16
    //   611: istore 19
    //   613: iload 15
    //   615: istore 16
    //   617: iload 19
    //   619: istore 15
    //   621: goto -477 -> 144
    //   624: astore_2
    //   625: goto -247 -> 378
    //   628: astore_1
    //   629: aconst_null
    //   630: astore_2
    //   631: goto -263 -> 368
    //   634: astore_2
    //   635: goto -402 -> 233
    //   638: iload 17
    //   640: istore 20
    //   642: iload 18
    //   644: istore 17
    //   646: iload 20
    //   648: istore 18
    //   650: goto +61 -> 711
    //   653: iload 15
    //   655: istore 19
    //   657: iload 16
    //   659: istore 15
    //   661: iload 19
    //   663: istore 16
    //   665: goto -252 -> 413
    //   668: iload 15
    //   670: ifne +35 -> 705
    //   673: iload 16
    //   675: iconst_1
    //   676: iadd
    //   677: istore 15
    //   679: aload 21
    //   681: iload 16
    //   683: dload 13
    //   685: dastore
    //   686: aload 21
    //   688: iload 15
    //   690: dload 11
    //   692: dastore
    //   693: iconst_1
    //   694: istore 16
    //   696: iload 15
    //   698: iconst_1
    //   699: iadd
    //   700: istore 15
    //   702: goto -289 -> 413
    //   705: iconst_0
    //   706: istore 19
    //   708: goto -187 -> 521
    //   711: iload 19
    //   713: iconst_2
    //   714: iadd
    //   715: istore 20
    //   717: iload 18
    //   719: istore 19
    //   721: iload 17
    //   723: istore 18
    //   725: iload 19
    //   727: istore 17
    //   729: iload 20
    //   731: istore 19
    //   733: goto -212 -> 521
    //   736: iload 18
    //   738: ifeq +40 -> 778
    //   741: dload 5
    //   743: dload 13
    //   745: dadd
    //   746: dstore 5
    //   748: dload_3
    //   749: dload 11
    //   751: dadd
    //   752: dstore_3
    //   753: iload 17
    //   755: iconst_1
    //   756: iadd
    //   757: istore 19
    //   759: iload 15
    //   761: istore 17
    //   763: iload 16
    //   765: istore 15
    //   767: iload 17
    //   769: istore 16
    //   771: iload 19
    //   773: istore 17
    //   775: goto -362 -> 413
    //   778: iload 17
    //   780: istore 19
    //   782: dload_3
    //   783: dstore 9
    //   785: dload 5
    //   787: dstore 7
    //   789: iload 16
    //   791: bipush 8
    //   793: if_icmpge -363 -> 430
    //   796: iload 16
    //   798: iconst_1
    //   799: iadd
    //   800: istore 19
    //   802: aload 21
    //   804: iload 16
    //   806: dload 13
    //   808: dastore
    //   809: aload 21
    //   811: iload 19
    //   813: dload 11
    //   815: dastore
    //   816: iload 19
    //   818: iconst_1
    //   819: iadd
    //   820: istore 19
    //   822: iload 15
    //   824: istore 16
    //   826: iload 19
    //   828: istore 15
    //   830: goto -417 -> 413
    //
    // Exception table:
    //   from	to	target	type
    //   131	140	232	java/lang/Exception
    //   155	208	232	java/lang/Exception
    //   222	229	232	java/lang/Exception
    //   308	358	232	java/lang/Exception
    //   435	460	232	java/lang/Exception
    //   478	501	232	java/lang/Exception
    //   511	518	232	java/lang/Exception
    //   528	552	232	java/lang/Exception
    //   602	609	232	java/lang/Exception
    //   237	243	244	java/lang/Exception
    //   131	140	361	finally
    //   155	208	361	finally
    //   222	229	361	finally
    //   308	358	361	finally
    //   435	460	361	finally
    //   478	501	361	finally
    //   511	518	361	finally
    //   528	552	361	finally
    //   602	609	361	finally
    //   464	470	471	java/lang/Exception
    //   372	378	624	java/lang/Exception
    //   93	129	628	finally
    //   93	129	634	java/lang/Exception
  }

  private String jdMethod_new(boolean paramBoolean)
  {
    double d1 = 0.0D;
    double d3;
    double d2;
    boolean bool;
    int i;
    if (this.fu)
    {
      d3 = this.fl;
      d2 = this.fk;
      d1 = 246.40000000000001D;
      bool = true;
      i = 1;
    }
    while (true)
      if (i != 0)
      {
        String str;
        if (paramBoolean)
        {
          str = "{\"result\":{\"time\":\"" + com.baidu.location.b.k.ad() + "\",\"error\":\"66\"},\"content\":{\"point\":{\"x\":" + "\"%f\",\"y\":\"%f\"},\"radius\":\"%f\",\"isCellChanged\":\"%b\"}}";
          return String.format(Locale.CHINA, str, new Object[] { Double.valueOf(d3), Double.valueOf(d2), Double.valueOf(d1), Boolean.valueOf(true) });
          if (this.fn)
          {
            d3 = this.fr;
            d2 = this.fq;
            d1 = this.fh;
            bool = m.bb().bh();
            i = 1;
          }
        }
        else
        {
          str = "{\"result\":{\"time\":\"" + com.baidu.location.b.k.ad() + "\",\"error\":\"66\"},\"content\":{\"point\":{\"x\":" + "\"%f\",\"y\":\"%f\"},\"radius\":\"%f\",\"isCellChanged\":\"%b\"}}";
          return String.format(Locale.CHINA, str, new Object[] { Double.valueOf(d3), Double.valueOf(d2), Double.valueOf(d1), Boolean.valueOf(bool) });
        }
      }
      else
      {
        if (paramBoolean)
          return "{\"result\":{\"time\":\"" + com.baidu.location.b.k.ad() + "\",\"error\":\"67\"}}";
        return "{\"result\":{\"time\":\"" + com.baidu.location.b.k.ad() + "\",\"error\":\"63\"}}";
        bool = false;
        i = 0;
        d2 = 0.0D;
        d3 = 0.0D;
      }
  }

  public void a1()
  {
    this.ff.postDelayed(new f(this), 3000L);
  }

  public BDLocation jdMethod_if(String paramString, List paramList, boolean paramBoolean)
  {
    ExecutorService localExecutorService = Executors.newSingleThreadExecutor();
    paramList = (FutureTask)localExecutorService.submit(new g(this, paramString, paramList));
    try
    {
      paramString = (String)paramList.get(2000L, TimeUnit.MILLISECONDS);
      return new BDLocation(paramString);
    }
    catch (InterruptedException paramString)
    {
      while (true)
      {
        paramList.cancel(true);
        localExecutorService.shutdown();
        paramString = "{\"result\":\"null\"}";
      }
    }
    catch (ExecutionException paramString)
    {
      while (true)
      {
        paramList.cancel(true);
        localExecutorService.shutdown();
        paramString = "{\"result\":\"null\"}";
      }
    }
    catch (TimeoutException paramString)
    {
      while (true)
      {
        if (paramBoolean)
          o.aY().j("old offlineLocation Timeout Exception!");
        paramList.cancel(true);
        localExecutorService.shutdown();
        paramString = "{\"result\":\"null\"}";
      }
    }
    finally
    {
      localExecutorService.shutdown();
    }
    throw paramString;
  }

  public void jdMethod_if(String paramString, h paramh, com.baidu.location.h.f paramf, BDLocation paramBDLocation)
  {
    if ((!paramh.du()) || (!m.bb().bh()));
    for (int j = 1; ; j = 0)
    {
      if ((paramBDLocation == null) || (paramBDLocation.getLocType() != 161) || ((!"wf".equals(paramBDLocation.getNetworkLocationType())) && (paramBDLocation.getRadius() >= 300.0F)));
      for (int i = 1; ; i = 0)
      {
        if (paramf.km == null)
          i = 1;
        if ((j != 0) && (i != 0));
        while (this.fp)
          return;
        this.fp = true;
        new b(null).execute(new Object[] { paramString, paramh, paramf, paramBDLocation });
        return;
      }
    }
  }

  public BDLocation jdMethod_try(boolean paramBoolean)
  {
    Object localObject2 = com.baidu.location.h.c.a().cO();
    Object localObject1 = null;
    if (localObject2 != null)
      localObject1 = ((h)localObject2).dC();
    com.baidu.location.h.f localf = l.a().c8();
    localObject1 = jdMethod_if((String)localObject1, localf.km, true);
    if ((localObject1 != null) && (((BDLocation)localObject1).getLocType() == 66))
    {
      StringBuffer localStringBuffer = new StringBuffer(1024);
      localStringBuffer.append(String.format(Locale.CHINA, "&ofl=%f|%f|%f", new Object[] { Double.valueOf(((BDLocation)localObject1).getLatitude()), Double.valueOf(((BDLocation)localObject1).getLongitude()), Float.valueOf(((BDLocation)localObject1).getRadius()) }));
      if ((localf != null) && (localf.dn() > 0))
      {
        localStringBuffer.append("&wf=");
        localStringBuffer.append(localf.d(15));
      }
      if (localObject2 != null)
        localStringBuffer.append(((h)localObject2).dz());
      localStringBuffer.append("&uptype=oldoff");
      localStringBuffer.append(com.baidu.location.b.c.N().jdMethod_do(false));
      localStringBuffer.append(com.baidu.location.e.c.br().bu());
      localObject2 = localStringBuffer.toString();
      o.jdMethod_for(o.fd, Jni.H((String)localObject2));
    }
    return localObject1;
  }

  private class a extends AsyncTask
  {
    private a()
    {
    }

    protected Boolean a(Boolean[] paramArrayOfBoolean)
    {
      Object localObject1 = null;
      if (paramArrayOfBoolean.length != 4)
        return Boolean.valueOf(false);
      try
      {
        Object localObject2 = SQLiteDatabase.openOrCreateDatabase(b.a2(), null);
        localObject1 = localObject2;
        label25: if (localObject1 == null)
          return Boolean.valueOf(false);
        int i = (int)(System.currentTimeMillis() >> 28);
        try
        {
          localObject1.beginTransaction();
          if (paramArrayOfBoolean[0].booleanValue())
            localObject2 = "delete from wof where ac < " + (i - 35);
          try
          {
            localObject1.execSQL((String)localObject2);
            if (paramArrayOfBoolean[1].booleanValue())
              paramArrayOfBoolean = "delete from bdcltb09 where ac is NULL or ac < " + (i - 130);
          }
          catch (Exception localException1)
          {
            try
            {
              localObject1.execSQL(paramArrayOfBoolean);
              label123: localObject1.setTransactionSuccessful();
              localObject1.endTransaction();
              localObject1.close();
              label135: return Boolean.valueOf(true);
              localException1 = localException1;
            }
            catch (Exception paramArrayOfBoolean)
            {
              break label123;
            }
          }
        }
        catch (Exception paramArrayOfBoolean)
        {
          break label135;
        }
      }
      catch (Exception localException2)
      {
        break label25;
      }
    }
  }

  private class b extends AsyncTask
  {
    private b()
    {
    }

    protected Boolean a(Object[] paramArrayOfObject)
    {
      if (paramArrayOfObject.length != 4)
      {
        b.jdMethod_if(b.this, false);
        return Boolean.valueOf(false);
      }
      SQLiteDatabase localSQLiteDatabase2;
      try
      {
        SQLiteDatabase localSQLiteDatabase1 = SQLiteDatabase.openOrCreateDatabase(b.a2(), null);
        if (localSQLiteDatabase1 == null)
        {
          b.jdMethod_if(b.this, false);
          return Boolean.valueOf(false);
        }
      }
      catch (Exception localException)
      {
        while (true)
          localSQLiteDatabase2 = null;
      }
      try
      {
        localSQLiteDatabase2.beginTransaction();
        b.jdMethod_if(b.this, (String)paramArrayOfObject[0], (h)paramArrayOfObject[1], localSQLiteDatabase2);
        b.jdMethod_if(b.this, (com.baidu.location.h.f)paramArrayOfObject[2], (BDLocation)paramArrayOfObject[3], localSQLiteDatabase2);
        localSQLiteDatabase2.setTransactionSuccessful();
        localSQLiteDatabase2.endTransaction();
        localSQLiteDatabase2.close();
        label108: b.jdMethod_if(b.this, false);
        return Boolean.valueOf(true);
      }
      catch (Exception paramArrayOfObject)
      {
        break label108;
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.c.b
 * JD-Core Version:    0.6.2
 */