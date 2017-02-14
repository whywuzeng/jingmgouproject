package com.baidu.location.c;

import android.database.sqlite.SQLiteDatabase;
import com.baidu.location.b.b;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONObject;

final class k
  implements b
{
  private static final double[] c = { 45.0D, 135.0D, 225.0D, 315.0D };
  private final d b;
  private final int d;
  private final SQLiteDatabase e;
  private int f;
  private int g;

  k(d paramd, SQLiteDatabase paramSQLiteDatabase, int paramInt)
  {
    this.b = paramd;
    this.f = -1;
    this.g = -1;
    this.e = paramSQLiteDatabase;
    this.d = paramInt;
    if ((this.e != null) && (this.e.isOpen()))
    {
      this.e.execSQL("CREATE TABLE IF NOT EXISTS RGCAREA(gridkey VARCHAR(10) PRIMARY KEY, country VARCHAR(100),countrycode VARCHAR(100), province VARCHAR(100), city VARCHAR(100), citycode VARCHAR(100), district VARCHAR(100), timestamp INTEGER, version VARCHAR(50))");
      this.e.execSQL("CREATE TABLE IF NOT EXISTS RGCROAD(_id INTEGER PRIMARY KEY AUTOINCREMENT, gridkey VARCHAR(10), street VARCHAR(100), x1 DOUBLE, y1 DOUBLE, x2 DOUBLE, y2 DOUBLE)");
      this.e.execSQL("CREATE TABLE IF NOT EXISTS RGCSITE(_id INTEGER PRIMARY KEY AUTOINCREMENT, gridkey VARCHAR(10), street VARCHAR(100), streetnumber VARCHAR(100), x DOUBLE, y DOUBLE)");
      this.e.execSQL("CREATE TABLE IF NOT EXISTS RGCPOI(pid VARCHAR(50) PRIMARY KEY , gridkey VARCHAR(10), name VARCHAR(100), type VARCHAR(50), x DOUBLE, y DOUBLE, rank INTEGER)");
      this.e.execSQL("CREATE TABLE IF NOT EXISTS RGCUPDATE(gridkey VARCHAR(10), version VARCHAR(50), type INTEGER, timestamp INTEGER, PRIMARY KEY(gridkey, type))");
    }
  }

  private double a(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6)
  {
    double d1 = (paramDouble5 - paramDouble3) * (paramDouble1 - paramDouble3) + (paramDouble6 - paramDouble4) * (paramDouble2 - paramDouble4);
    if (d1 <= 0.0D)
      return Math.sqrt((paramDouble1 - paramDouble3) * (paramDouble1 - paramDouble3) + (paramDouble2 - paramDouble4) * (paramDouble2 - paramDouble4));
    double d2 = (paramDouble5 - paramDouble3) * (paramDouble5 - paramDouble3) + (paramDouble6 - paramDouble4) * (paramDouble6 - paramDouble4);
    if (d1 >= d2)
      return Math.sqrt((paramDouble1 - paramDouble5) * (paramDouble1 - paramDouble5) + (paramDouble2 - paramDouble6) * (paramDouble2 - paramDouble6));
    d1 /= d2;
    paramDouble3 = (paramDouble5 - paramDouble3) * d1 + paramDouble3;
    paramDouble4 = d1 * (paramDouble6 - paramDouble4) + paramDouble4;
    return Math.sqrt((paramDouble4 - paramDouble2) * (paramDouble4 - paramDouble2) + (paramDouble1 - paramDouble3) * (paramDouble1 - paramDouble3));
  }

  private static int a(int paramInt1, int paramInt2)
  {
    double d1;
    int i;
    if (100 > paramInt2)
    {
      d1 = -0.1D;
      i = 60000;
    }
    while (true)
    {
      double d2 = paramInt2;
      return (int)(i + d1 * d2) + paramInt1;
      if (500 > paramInt2)
      {
        d1 = -0.75D;
        i = 55500;
      }
      else
      {
        d1 = -0.5D;
        i = 0;
      }
    }
  }

  private static String b(int paramInt, double paramDouble1, double paramDouble2)
  {
    char[] arrayOfChar = new char[paramInt + 1];
    a locala1 = new a(90.0D, -90.0D, null);
    a locala2 = new a(180.0D, -180.0D, null);
    int k = 1;
    int j = 1;
    int i = 0;
    Object localObject;
    double d1;
    label68: double d2;
    if (k <= paramInt * 5)
      if (j != 0)
      {
        localObject = locala2;
        d1 = paramDouble1;
        d2 = (a.a((a)localObject) + a.b((a)localObject)) / 2.0D;
        i <<= 1;
        if ((int)(d1 * 1000000.0D) <= (int)(1000000.0D * d2))
          break label175;
        a.a((a)localObject, d2);
        i |= 1;
        label122: if (k % 5 != 0)
          break label241;
        arrayOfChar[(k / 5 - 1)] = "0123456789bcdefghjkmnpqrstuvwxyz".charAt(i);
        i = 0;
      }
    label175: label241: 
    while (true)
    {
      if (j == 0);
      for (j = 1; ; j = 0)
      {
        k += 1;
        break;
        localObject = locala1;
        d1 = paramDouble2;
        break label68;
        a.b((a)localObject, d2);
        break label122;
      }
      arrayOfChar[paramInt] = '\000';
      localObject = new StringBuffer();
      i = 0;
      while (i < paramInt)
      {
        ((StringBuffer)localObject).append(arrayOfChar[i]);
        i += 1;
      }
      return ((StringBuffer)localObject).toString();
    }
  }

  private static double[] b(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    paramDouble1 = Math.toRadians(paramDouble1);
    paramDouble2 = Math.toRadians(paramDouble2);
    double d1 = Math.toRadians(paramDouble4);
    paramDouble4 = Math.asin(Math.sin(paramDouble1) * Math.cos(paramDouble3 / 6378137.0D) + Math.cos(paramDouble1) * Math.sin(paramDouble3 / 6378137.0D) * Math.cos(d1));
    paramDouble1 = Math.atan2(Math.sin(d1) * Math.sin(paramDouble3 / 6378137.0D) * Math.cos(paramDouble1), Math.cos(paramDouble3 / 6378137.0D) - Math.sin(paramDouble1) * Math.sin(paramDouble4));
    return new double[] { Math.toDegrees(paramDouble4), Math.toDegrees(paramDouble1 + paramDouble2) };
  }

  private double c(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    double d2 = Math.toRadians(paramDouble1);
    Math.toRadians(paramDouble2);
    double d1 = Math.toRadians(paramDouble3);
    Math.toRadians(paramDouble4);
    paramDouble2 = Math.toRadians(paramDouble4 - paramDouble2);
    paramDouble3 = Math.toRadians(paramDouble3 - paramDouble1);
    paramDouble1 = Math.sin(paramDouble3 / 2.0D);
    paramDouble3 = Math.sin(paramDouble3 / 2.0D);
    paramDouble4 = Math.cos(d2);
    d1 = Math.cos(d1);
    d2 = Math.sin(paramDouble2 / 2.0D);
    paramDouble1 = Math.sin(paramDouble2 / 2.0D) * (paramDouble4 * d1 * d2) + paramDouble3 * paramDouble1;
    return Math.atan2(Math.sqrt(paramDouble1), Math.sqrt(1.0D - paramDouble1)) * 2.0D * 6378137.0D;
  }

  // ERROR //
  com.baidu.location.Address a(double paramDouble1, double paramDouble2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 13
    //   3: getstatic 163	com/baidu/location/c/k$b:c	Lcom/baidu/location/c/k$b;
    //   6: aload_0
    //   7: getfield 48	com/baidu/location/c/k:d	I
    //   10: dload_1
    //   11: dload_3
    //   12: invokestatic 166	com/baidu/location/c/k$b:a	(Lcom/baidu/location/c/k$b;IDD)Ljava/lang/String;
    //   15: astore 11
    //   17: aload_0
    //   18: getfield 46	com/baidu/location/c/k:e	Landroid/database/sqlite/SQLiteDatabase;
    //   21: aload 11
    //   23: aconst_null
    //   24: invokevirtual 170	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   27: astore 11
    //   29: aload 11
    //   31: astore 13
    //   33: aload 13
    //   35: invokeinterface 175 1 0
    //   40: istore 10
    //   42: iload 10
    //   44: ifeq +1421 -> 1465
    //   47: ldc2_w 176
    //   50: dstore 5
    //   52: aconst_null
    //   53: astore 12
    //   55: aconst_null
    //   56: astore 11
    //   58: aload 13
    //   60: invokeinterface 180 1 0
    //   65: ifne +107 -> 172
    //   68: aload 13
    //   70: iconst_2
    //   71: invokeinterface 184 2 0
    //   76: astore 14
    //   78: aload 13
    //   80: iconst_3
    //   81: invokeinterface 184 2 0
    //   86: astore 15
    //   88: aload_0
    //   89: dload_3
    //   90: dload_1
    //   91: aload 13
    //   93: iconst_5
    //   94: invokeinterface 188 2 0
    //   99: aload 13
    //   101: iconst_4
    //   102: invokeinterface 188 2 0
    //   107: invokespecial 190	com/baidu/location/c/k:c	(DDDD)D
    //   110: dstore 7
    //   112: dload 7
    //   114: dload 5
    //   116: dcmpg
    //   117: ifge +1333 -> 1450
    //   120: getstatic 163	com/baidu/location/c/k$b:c	Lcom/baidu/location/c/k$b;
    //   123: invokestatic 193	com/baidu/location/c/k$b:d	(Lcom/baidu/location/c/k$b;)I
    //   126: istore 9
    //   128: dload 7
    //   130: iload 9
    //   132: i2d
    //   133: dcmpg
    //   134: ifgt +1316 -> 1450
    //   137: aload 15
    //   139: astore 11
    //   141: aload 14
    //   143: astore 12
    //   145: dload 7
    //   147: dstore 5
    //   149: aload 13
    //   151: invokeinterface 196 1 0
    //   156: pop
    //   157: aload 12
    //   159: astore 14
    //   161: aload 11
    //   163: astore 12
    //   165: aload 14
    //   167: astore 11
    //   169: goto -111 -> 58
    //   172: aload 12
    //   174: astore 15
    //   176: aload 11
    //   178: astore 14
    //   180: aload 13
    //   182: ifnull +1261 -> 1443
    //   185: aload 13
    //   187: invokeinterface 199 1 0
    //   192: aload 12
    //   194: astore 15
    //   196: aload 11
    //   198: astore 13
    //   200: aload 15
    //   202: ifnonnull +315 -> 517
    //   205: getstatic 201	com/baidu/location/c/k$b:b	Lcom/baidu/location/c/k$b;
    //   208: aload_0
    //   209: getfield 48	com/baidu/location/c/k:d	I
    //   212: dload_1
    //   213: dload_3
    //   214: invokestatic 166	com/baidu/location/c/k$b:a	(Lcom/baidu/location/c/k$b;IDD)Ljava/lang/String;
    //   217: astore 12
    //   219: aload_0
    //   220: getfield 46	com/baidu/location/c/k:e	Landroid/database/sqlite/SQLiteDatabase;
    //   223: aload 12
    //   225: aconst_null
    //   226: invokevirtual 170	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   229: astore 13
    //   231: aload 11
    //   233: astore 12
    //   235: aload 13
    //   237: invokeinterface 175 1 0
    //   242: ifeq +251 -> 493
    //   245: ldc2_w 176
    //   248: dstore 5
    //   250: aload 11
    //   252: astore 12
    //   254: dload_1
    //   255: dload_3
    //   256: ldc 203
    //   258: invokestatic 209	com/baidu/location/Jni:if	(DDLjava/lang/String;)[D
    //   261: astore 14
    //   263: aload 11
    //   265: astore 12
    //   267: aload 13
    //   269: invokeinterface 180 1 0
    //   274: ifne +223 -> 497
    //   277: aload 13
    //   279: iconst_2
    //   280: invokeinterface 184 2 0
    //   285: astore 12
    //   287: aload 13
    //   289: iconst_3
    //   290: invokeinterface 188 2 0
    //   295: aload 13
    //   297: iconst_4
    //   298: invokeinterface 188 2 0
    //   303: ldc 203
    //   305: invokestatic 209	com/baidu/location/Jni:if	(DDLjava/lang/String;)[D
    //   308: astore 16
    //   310: aload 13
    //   312: iconst_5
    //   313: invokeinterface 188 2 0
    //   318: aload 13
    //   320: bipush 6
    //   322: invokeinterface 188 2 0
    //   327: ldc 203
    //   329: invokestatic 209	com/baidu/location/Jni:if	(DDLjava/lang/String;)[D
    //   332: astore 17
    //   334: aload_0
    //   335: aload 14
    //   337: iconst_0
    //   338: daload
    //   339: aload 14
    //   341: iconst_1
    //   342: daload
    //   343: aload 16
    //   345: iconst_0
    //   346: daload
    //   347: aload 16
    //   349: iconst_1
    //   350: daload
    //   351: aload 17
    //   353: iconst_0
    //   354: daload
    //   355: aload 17
    //   357: iconst_1
    //   358: daload
    //   359: invokespecial 211	com/baidu/location/c/k:a	(DDDDDD)D
    //   362: dstore 7
    //   364: dload 7
    //   366: dload 5
    //   368: dcmpg
    //   369: ifge +1071 -> 1440
    //   372: getstatic 201	com/baidu/location/c/k$b:b	Lcom/baidu/location/c/k$b;
    //   375: invokestatic 193	com/baidu/location/c/k$b:d	(Lcom/baidu/location/c/k$b;)I
    //   378: istore 9
    //   380: dload 7
    //   382: iload 9
    //   384: i2d
    //   385: dcmpg
    //   386: ifgt +1054 -> 1440
    //   389: dload 7
    //   391: dstore 5
    //   393: aload 12
    //   395: astore 11
    //   397: aload 11
    //   399: astore 12
    //   401: aload 13
    //   403: invokeinterface 196 1 0
    //   408: pop
    //   409: goto -146 -> 263
    //   412: astore 13
    //   414: aload 12
    //   416: astore 15
    //   418: goto -222 -> 196
    //   421: astore 11
    //   423: aconst_null
    //   424: astore 13
    //   426: aconst_null
    //   427: astore 11
    //   429: aconst_null
    //   430: astore 12
    //   432: aload 11
    //   434: astore 15
    //   436: aload 12
    //   438: astore 14
    //   440: aload 13
    //   442: ifnull +1001 -> 1443
    //   445: aload 13
    //   447: invokeinterface 199 1 0
    //   452: aload 11
    //   454: astore 15
    //   456: aload 12
    //   458: astore 11
    //   460: goto -264 -> 196
    //   463: astore 13
    //   465: aload 11
    //   467: astore 15
    //   469: aload 12
    //   471: astore 11
    //   473: goto -277 -> 196
    //   476: astore 11
    //   478: aload 13
    //   480: ifnull +10 -> 490
    //   483: aload 13
    //   485: invokeinterface 199 1 0
    //   490: aload 11
    //   492: athrow
    //   493: aload 11
    //   495: astore 12
    //   497: aload 12
    //   499: astore 14
    //   501: aload 13
    //   503: ifnull +930 -> 1433
    //   506: aload 13
    //   508: invokeinterface 199 1 0
    //   513: aload 12
    //   515: astore 13
    //   517: getstatic 213	com/baidu/location/c/k$b:a	Lcom/baidu/location/c/k$b;
    //   520: aload_0
    //   521: getfield 48	com/baidu/location/c/k:d	I
    //   524: dload_1
    //   525: dload_3
    //   526: invokestatic 166	com/baidu/location/c/k$b:a	(Lcom/baidu/location/c/k$b;IDD)Ljava/lang/String;
    //   529: astore 11
    //   531: aconst_null
    //   532: astore 18
    //   534: aload_0
    //   535: getfield 46	com/baidu/location/c/k:e	Landroid/database/sqlite/SQLiteDatabase;
    //   538: aload 11
    //   540: aconst_null
    //   541: invokevirtual 170	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   544: astore 19
    //   546: aload 19
    //   548: astore 18
    //   550: aload 19
    //   552: invokeinterface 175 1 0
    //   557: ifeq +855 -> 1412
    //   560: aload 19
    //   562: astore 18
    //   564: aload 19
    //   566: invokeinterface 180 1 0
    //   571: ifne +841 -> 1412
    //   574: aload 19
    //   576: astore 18
    //   578: aload 19
    //   580: aload 19
    //   582: ldc 215
    //   584: invokeinterface 219 2 0
    //   589: invokeinterface 184 2 0
    //   594: astore 17
    //   596: aload 19
    //   598: aload 19
    //   600: ldc 221
    //   602: invokeinterface 219 2 0
    //   607: invokeinterface 184 2 0
    //   612: astore 16
    //   614: aload 19
    //   616: aload 19
    //   618: ldc 223
    //   620: invokeinterface 219 2 0
    //   625: invokeinterface 184 2 0
    //   630: astore 14
    //   632: aload 19
    //   634: aload 19
    //   636: ldc 225
    //   638: invokeinterface 219 2 0
    //   643: invokeinterface 184 2 0
    //   648: astore 12
    //   650: aload 19
    //   652: aload 19
    //   654: ldc 227
    //   656: invokeinterface 219 2 0
    //   661: invokeinterface 184 2 0
    //   666: astore 11
    //   668: aload 19
    //   670: aload 19
    //   672: ldc 229
    //   674: invokeinterface 219 2 0
    //   679: invokeinterface 184 2 0
    //   684: astore 20
    //   686: aload 17
    //   688: astore 25
    //   690: aload 16
    //   692: astore 24
    //   694: aload 14
    //   696: astore 23
    //   698: aload 12
    //   700: astore 22
    //   702: aload 11
    //   704: astore 21
    //   706: aload 20
    //   708: astore 11
    //   710: aload 21
    //   712: astore 12
    //   714: aload 22
    //   716: astore 14
    //   718: aload 23
    //   720: astore 16
    //   722: aload 24
    //   724: astore 17
    //   726: aload 25
    //   728: astore 18
    //   730: aload 19
    //   732: ifnull +34 -> 766
    //   735: aload 19
    //   737: invokeinterface 199 1 0
    //   742: aload 25
    //   744: astore 18
    //   746: aload 24
    //   748: astore 17
    //   750: aload 23
    //   752: astore 16
    //   754: aload 22
    //   756: astore 14
    //   758: aload 21
    //   760: astore 12
    //   762: aload 20
    //   764: astore 11
    //   766: aload 18
    //   768: astore 19
    //   770: aload 18
    //   772: ifnull +20 -> 792
    //   775: new 117	java/lang/String
    //   778: dup
    //   779: aload 18
    //   781: invokevirtual 233	java/lang/String:getBytes	()[B
    //   784: invokestatic 238	com/baidu/location/f/b/b:a	([B)[B
    //   787: invokespecial 241	java/lang/String:<init>	([B)V
    //   790: astore 19
    //   792: aload 17
    //   794: astore 18
    //   796: aload 17
    //   798: ifnull +20 -> 818
    //   801: new 117	java/lang/String
    //   804: dup
    //   805: aload 17
    //   807: invokevirtual 233	java/lang/String:getBytes	()[B
    //   810: invokestatic 238	com/baidu/location/f/b/b:a	([B)[B
    //   813: invokespecial 241	java/lang/String:<init>	([B)V
    //   816: astore 18
    //   818: aload 16
    //   820: astore 17
    //   822: aload 16
    //   824: ifnull +20 -> 844
    //   827: new 117	java/lang/String
    //   830: dup
    //   831: aload 16
    //   833: invokevirtual 233	java/lang/String:getBytes	()[B
    //   836: invokestatic 238	com/baidu/location/f/b/b:a	([B)[B
    //   839: invokespecial 241	java/lang/String:<init>	([B)V
    //   842: astore 17
    //   844: aload 14
    //   846: astore 16
    //   848: aload 14
    //   850: ifnull +20 -> 870
    //   853: new 117	java/lang/String
    //   856: dup
    //   857: aload 14
    //   859: invokevirtual 233	java/lang/String:getBytes	()[B
    //   862: invokestatic 238	com/baidu/location/f/b/b:a	([B)[B
    //   865: invokespecial 241	java/lang/String:<init>	([B)V
    //   868: astore 16
    //   870: aload 12
    //   872: astore 14
    //   874: aload 12
    //   876: ifnull +20 -> 896
    //   879: new 117	java/lang/String
    //   882: dup
    //   883: aload 12
    //   885: invokevirtual 233	java/lang/String:getBytes	()[B
    //   888: invokestatic 238	com/baidu/location/f/b/b:a	([B)[B
    //   891: invokespecial 241	java/lang/String:<init>	([B)V
    //   894: astore 14
    //   896: aload 11
    //   898: astore 12
    //   900: aload 11
    //   902: ifnull +20 -> 922
    //   905: new 117	java/lang/String
    //   908: dup
    //   909: aload 11
    //   911: invokevirtual 233	java/lang/String:getBytes	()[B
    //   914: invokestatic 238	com/baidu/location/f/b/b:a	([B)[B
    //   917: invokespecial 241	java/lang/String:<init>	([B)V
    //   920: astore 12
    //   922: aload 13
    //   924: astore 11
    //   926: aload 13
    //   928: ifnull +20 -> 948
    //   931: new 117	java/lang/String
    //   934: dup
    //   935: aload 13
    //   937: invokevirtual 233	java/lang/String:getBytes	()[B
    //   940: invokestatic 238	com/baidu/location/f/b/b:a	([B)[B
    //   943: invokespecial 241	java/lang/String:<init>	([B)V
    //   946: astore 11
    //   948: aload 15
    //   950: ifnull +429 -> 1379
    //   953: new 117	java/lang/String
    //   956: dup
    //   957: aload 15
    //   959: invokevirtual 233	java/lang/String:getBytes	()[B
    //   962: invokestatic 238	com/baidu/location/f/b/b:a	([B)[B
    //   965: invokespecial 241	java/lang/String:<init>	([B)V
    //   968: astore 13
    //   970: new 243	com/baidu/location/Address$Builder
    //   973: dup
    //   974: invokespecial 244	com/baidu/location/Address$Builder:<init>	()V
    //   977: aload 19
    //   979: invokevirtual 247	com/baidu/location/Address$Builder:country	(Ljava/lang/String;)Lcom/baidu/location/Address$Builder;
    //   982: aload 18
    //   984: invokevirtual 250	com/baidu/location/Address$Builder:countryCode	(Ljava/lang/String;)Lcom/baidu/location/Address$Builder;
    //   987: aload 17
    //   989: invokevirtual 252	com/baidu/location/Address$Builder:province	(Ljava/lang/String;)Lcom/baidu/location/Address$Builder;
    //   992: aload 16
    //   994: invokevirtual 254	com/baidu/location/Address$Builder:city	(Ljava/lang/String;)Lcom/baidu/location/Address$Builder;
    //   997: aload 14
    //   999: invokevirtual 257	com/baidu/location/Address$Builder:cityCode	(Ljava/lang/String;)Lcom/baidu/location/Address$Builder;
    //   1002: aload 12
    //   1004: invokevirtual 259	com/baidu/location/Address$Builder:district	(Ljava/lang/String;)Lcom/baidu/location/Address$Builder;
    //   1007: aload 11
    //   1009: invokevirtual 262	com/baidu/location/Address$Builder:street	(Ljava/lang/String;)Lcom/baidu/location/Address$Builder;
    //   1012: aload 13
    //   1014: invokevirtual 265	com/baidu/location/Address$Builder:streetNumber	(Ljava/lang/String;)Lcom/baidu/location/Address$Builder;
    //   1017: invokevirtual 269	com/baidu/location/Address$Builder:build	()Lcom/baidu/location/Address;
    //   1020: areturn
    //   1021: astore 11
    //   1023: aload 12
    //   1025: astore 13
    //   1027: goto -510 -> 517
    //   1030: astore 12
    //   1032: aconst_null
    //   1033: astore 12
    //   1035: aload 11
    //   1037: astore 14
    //   1039: aload 12
    //   1041: ifnull +392 -> 1433
    //   1044: aload 12
    //   1046: invokeinterface 199 1 0
    //   1051: aload 11
    //   1053: astore 13
    //   1055: goto -538 -> 517
    //   1058: astore 12
    //   1060: aload 11
    //   1062: astore 13
    //   1064: goto -547 -> 517
    //   1067: astore 11
    //   1069: aconst_null
    //   1070: astore 13
    //   1072: aload 13
    //   1074: ifnull +10 -> 1084
    //   1077: aload 13
    //   1079: invokeinterface 199 1 0
    //   1084: aload 11
    //   1086: athrow
    //   1087: astore 11
    //   1089: aconst_null
    //   1090: astore 11
    //   1092: aconst_null
    //   1093: astore 12
    //   1095: aconst_null
    //   1096: astore 14
    //   1098: aconst_null
    //   1099: astore 16
    //   1101: aconst_null
    //   1102: astore 17
    //   1104: aload 18
    //   1106: ifnull +280 -> 1386
    //   1109: aload 18
    //   1111: invokeinterface 199 1 0
    //   1116: aload 17
    //   1118: astore 18
    //   1120: aload 16
    //   1122: astore 17
    //   1124: aload 14
    //   1126: astore 16
    //   1128: aload 12
    //   1130: astore 14
    //   1132: aload 11
    //   1134: astore 12
    //   1136: aconst_null
    //   1137: astore 11
    //   1139: goto -373 -> 766
    //   1142: astore 18
    //   1144: aload 17
    //   1146: astore 18
    //   1148: aload 16
    //   1150: astore 17
    //   1152: aload 14
    //   1154: astore 16
    //   1156: aload 12
    //   1158: astore 14
    //   1160: aload 11
    //   1162: astore 12
    //   1164: aconst_null
    //   1165: astore 11
    //   1167: goto -401 -> 766
    //   1170: astore 11
    //   1172: aconst_null
    //   1173: astore 19
    //   1175: aload 19
    //   1177: ifnull +10 -> 1187
    //   1180: aload 19
    //   1182: invokeinterface 199 1 0
    //   1187: aload 11
    //   1189: athrow
    //   1190: astore 12
    //   1192: goto -702 -> 490
    //   1195: astore 12
    //   1197: goto -113 -> 1084
    //   1200: astore 11
    //   1202: aload 20
    //   1204: astore 11
    //   1206: aload 21
    //   1208: astore 12
    //   1210: aload 22
    //   1212: astore 14
    //   1214: aload 23
    //   1216: astore 16
    //   1218: aload 24
    //   1220: astore 17
    //   1222: aload 25
    //   1224: astore 18
    //   1226: goto -460 -> 766
    //   1229: astore 12
    //   1231: goto -44 -> 1187
    //   1234: astore 11
    //   1236: goto -61 -> 1175
    //   1239: astore 11
    //   1241: aconst_null
    //   1242: astore 11
    //   1244: aconst_null
    //   1245: astore 12
    //   1247: aconst_null
    //   1248: astore 14
    //   1250: aconst_null
    //   1251: astore 16
    //   1253: aload 19
    //   1255: astore 18
    //   1257: goto -153 -> 1104
    //   1260: astore 11
    //   1262: aconst_null
    //   1263: astore 11
    //   1265: aconst_null
    //   1266: astore 12
    //   1268: aconst_null
    //   1269: astore 14
    //   1271: aload 19
    //   1273: astore 18
    //   1275: goto -171 -> 1104
    //   1278: astore 11
    //   1280: aconst_null
    //   1281: astore 11
    //   1283: aconst_null
    //   1284: astore 12
    //   1286: aload 19
    //   1288: astore 18
    //   1290: goto -186 -> 1104
    //   1293: astore 11
    //   1295: aconst_null
    //   1296: astore 11
    //   1298: aload 19
    //   1300: astore 18
    //   1302: goto -198 -> 1104
    //   1305: astore 18
    //   1307: aload 19
    //   1309: astore 18
    //   1311: goto -207 -> 1104
    //   1314: astore 11
    //   1316: goto -244 -> 1072
    //   1319: astore 11
    //   1321: aload 12
    //   1323: astore 11
    //   1325: aload 13
    //   1327: astore 12
    //   1329: goto -294 -> 1035
    //   1332: astore 12
    //   1334: aload 13
    //   1336: astore 12
    //   1338: goto -303 -> 1035
    //   1341: astore 11
    //   1343: goto -865 -> 478
    //   1346: astore 11
    //   1348: aconst_null
    //   1349: astore 11
    //   1351: aconst_null
    //   1352: astore 12
    //   1354: goto -922 -> 432
    //   1357: astore 14
    //   1359: goto -927 -> 432
    //   1362: astore 14
    //   1364: aload 11
    //   1366: astore 14
    //   1368: aload 12
    //   1370: astore 11
    //   1372: aload 14
    //   1374: astore 12
    //   1376: goto -944 -> 432
    //   1379: aload 15
    //   1381: astore 13
    //   1383: goto -413 -> 970
    //   1386: aload 17
    //   1388: astore 18
    //   1390: aload 16
    //   1392: astore 17
    //   1394: aload 14
    //   1396: astore 16
    //   1398: aload 12
    //   1400: astore 14
    //   1402: aload 11
    //   1404: astore 12
    //   1406: aconst_null
    //   1407: astore 11
    //   1409: goto -643 -> 766
    //   1412: aconst_null
    //   1413: astore 20
    //   1415: aconst_null
    //   1416: astore 21
    //   1418: aconst_null
    //   1419: astore 22
    //   1421: aconst_null
    //   1422: astore 23
    //   1424: aconst_null
    //   1425: astore 24
    //   1427: aconst_null
    //   1428: astore 25
    //   1430: goto -724 -> 706
    //   1433: aload 14
    //   1435: astore 13
    //   1437: goto -920 -> 517
    //   1440: goto -1043 -> 397
    //   1443: aload 14
    //   1445: astore 11
    //   1447: goto -1251 -> 196
    //   1450: aload 11
    //   1452: astore 14
    //   1454: aload 12
    //   1456: astore 11
    //   1458: aload 14
    //   1460: astore 12
    //   1462: goto -1313 -> 149
    //   1465: aconst_null
    //   1466: astore 12
    //   1468: aconst_null
    //   1469: astore 11
    //   1471: goto -1299 -> 172
    //
    // Exception table:
    //   from	to	target	type
    //   185	192	412	java/lang/Exception
    //   3	29	421	java/lang/Exception
    //   445	452	463	java/lang/Exception
    //   3	29	476	finally
    //   506	513	1021	java/lang/Exception
    //   205	231	1030	java/lang/Exception
    //   1044	1051	1058	java/lang/Exception
    //   205	231	1067	finally
    //   534	546	1087	java/lang/Exception
    //   550	560	1087	java/lang/Exception
    //   564	574	1087	java/lang/Exception
    //   578	596	1087	java/lang/Exception
    //   1109	1116	1142	java/lang/Exception
    //   534	546	1170	finally
    //   483	490	1190	java/lang/Exception
    //   1077	1084	1195	java/lang/Exception
    //   735	742	1200	java/lang/Exception
    //   1180	1187	1229	java/lang/Exception
    //   550	560	1234	finally
    //   564	574	1234	finally
    //   578	596	1234	finally
    //   596	614	1234	finally
    //   614	632	1234	finally
    //   632	650	1234	finally
    //   650	668	1234	finally
    //   668	686	1234	finally
    //   596	614	1239	java/lang/Exception
    //   614	632	1260	java/lang/Exception
    //   632	650	1278	java/lang/Exception
    //   650	668	1293	java/lang/Exception
    //   668	686	1305	java/lang/Exception
    //   235	245	1314	finally
    //   254	263	1314	finally
    //   267	364	1314	finally
    //   372	380	1314	finally
    //   401	409	1314	finally
    //   235	245	1319	java/lang/Exception
    //   254	263	1319	java/lang/Exception
    //   401	409	1319	java/lang/Exception
    //   267	364	1332	java/lang/Exception
    //   372	380	1332	java/lang/Exception
    //   33	42	1341	finally
    //   58	112	1341	finally
    //   120	128	1341	finally
    //   149	157	1341	finally
    //   33	42	1346	java/lang/Exception
    //   149	157	1357	java/lang/Exception
    //   58	112	1362	java/lang/Exception
    //   120	128	1362	java/lang/Exception
  }

  void a(JSONObject paramJSONObject)
  {
    if ((this.e != null) && (this.e.isOpen()));
    try
    {
      this.e.beginTransaction();
      b[] arrayOfb = b.values();
      int j = arrayOfb.length;
      i = 0;
      if (i < j)
      {
        Object localObject2 = arrayOfb[i];
        if (paramJSONObject.has(b.a((b)localObject2)))
        {
          Object localObject1 = "";
          if (paramJSONObject.has(b.b((b)localObject2)))
            localObject1 = paramJSONObject.getString(b.b((b)localObject2));
          ArrayList localArrayList = new ArrayList();
          JSONObject localJSONObject = paramJSONObject.getJSONObject(b.a((b)localObject2));
          localArrayList.add(b.a((b)localObject2, localJSONObject));
          localArrayList.addAll(((b)localObject2).a(localJSONObject, (String)localObject1, b.c((b)localObject2)));
          localObject1 = localArrayList.iterator();
          while (((Iterator)localObject1).hasNext())
          {
            localObject2 = (String)((Iterator)localObject1).next();
            this.e.execSQL((String)localObject2);
          }
        }
      }
    }
    catch (Exception paramJSONObject)
    {
    }
    finally
    {
      try
      {
        while (true)
        {
          int i;
          this.e.endTransaction();
          return;
          i += 1;
        }
        this.e.setTransactionSuccessful();
        this.f = -1;
        this.g = -1;
        try
        {
          this.e.endTransaction();
          return;
        }
        catch (Exception paramJSONObject)
        {
          return;
        }
        paramJSONObject = finally;
        try
        {
          this.e.endTransaction();
          label237: throw paramJSONObject;
        }
        catch (Exception localException)
        {
          break label237;
        }
      }
      catch (Exception paramJSONObject)
      {
      }
    }
  }

  // ERROR //
  boolean a()
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: aconst_null
    //   3: astore 4
    //   5: aconst_null
    //   6: astore 6
    //   8: aconst_null
    //   9: astore 7
    //   11: aconst_null
    //   12: astore 5
    //   14: aload_0
    //   15: getfield 40	com/baidu/location/c/k:b	Lcom/baidu/location/c/d;
    //   18: invokevirtual 343	com/baidu/location/c/d:new	()Lcom/baidu/location/c/j;
    //   21: invokevirtual 348	com/baidu/location/c/j:l	()Z
    //   24: ifeq +161 -> 185
    //   27: aload_0
    //   28: getfield 44	com/baidu/location/c/k:g	I
    //   31: iconst_m1
    //   32: if_icmpne +153 -> 185
    //   35: aload_0
    //   36: getfield 42	com/baidu/location/c/k:f	I
    //   39: iconst_m1
    //   40: if_icmpne +145 -> 185
    //   43: aload_0
    //   44: getfield 46	com/baidu/location/c/k:e	Landroid/database/sqlite/SQLiteDatabase;
    //   47: ifnull +138 -> 185
    //   50: aload_0
    //   51: getfield 46	com/baidu/location/c/k:e	Landroid/database/sqlite/SQLiteDatabase;
    //   54: invokevirtual 54	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   57: ifeq +128 -> 185
    //   60: aload_0
    //   61: getfield 46	com/baidu/location/c/k:e	Landroid/database/sqlite/SQLiteDatabase;
    //   64: ldc_w 350
    //   67: aconst_null
    //   68: invokevirtual 170	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   71: astore_3
    //   72: aload 6
    //   74: astore 4
    //   76: aload 7
    //   78: astore 5
    //   80: aload_3
    //   81: invokeinterface 175 1 0
    //   86: pop
    //   87: aload 6
    //   89: astore 4
    //   91: aload 7
    //   93: astore 5
    //   95: aload_0
    //   96: aload_3
    //   97: iconst_0
    //   98: invokeinterface 354 2 0
    //   103: putfield 44	com/baidu/location/c/k:g	I
    //   106: aload 6
    //   108: astore 4
    //   110: aload 7
    //   112: astore 5
    //   114: aload_0
    //   115: getfield 46	com/baidu/location/c/k:e	Landroid/database/sqlite/SQLiteDatabase;
    //   118: ldc_w 356
    //   121: aconst_null
    //   122: invokevirtual 170	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   125: astore 6
    //   127: aload 6
    //   129: astore 4
    //   131: aload 6
    //   133: astore 5
    //   135: aload 6
    //   137: invokeinterface 175 1 0
    //   142: pop
    //   143: aload 6
    //   145: astore 4
    //   147: aload 6
    //   149: astore 5
    //   151: aload_0
    //   152: aload 6
    //   154: iconst_0
    //   155: invokeinterface 354 2 0
    //   160: putfield 42	com/baidu/location/c/k:f	I
    //   163: aload_3
    //   164: ifnull +9 -> 173
    //   167: aload_3
    //   168: invokeinterface 199 1 0
    //   173: aload 6
    //   175: ifnull +10 -> 185
    //   178: aload 6
    //   180: invokeinterface 199 1 0
    //   185: iload_2
    //   186: istore_1
    //   187: aload_0
    //   188: getfield 44	com/baidu/location/c/k:g	I
    //   191: ifne +14 -> 205
    //   194: iload_2
    //   195: istore_1
    //   196: aload_0
    //   197: getfield 42	com/baidu/location/c/k:f	I
    //   200: ifne +5 -> 205
    //   203: iconst_1
    //   204: istore_1
    //   205: iload_1
    //   206: ireturn
    //   207: astore_3
    //   208: aconst_null
    //   209: astore_3
    //   210: aload_3
    //   211: ifnull +9 -> 220
    //   214: aload_3
    //   215: invokeinterface 199 1 0
    //   220: aload 5
    //   222: ifnull -37 -> 185
    //   225: aload 5
    //   227: invokeinterface 199 1 0
    //   232: goto -47 -> 185
    //   235: astore_3
    //   236: goto -51 -> 185
    //   239: astore 5
    //   241: aconst_null
    //   242: astore_3
    //   243: aload_3
    //   244: ifnull +9 -> 253
    //   247: aload_3
    //   248: invokeinterface 199 1 0
    //   253: aload 4
    //   255: ifnull +10 -> 265
    //   258: aload 4
    //   260: invokeinterface 199 1 0
    //   265: aload 5
    //   267: athrow
    //   268: astore_3
    //   269: goto -96 -> 173
    //   272: astore_3
    //   273: goto -88 -> 185
    //   276: astore_3
    //   277: goto -57 -> 220
    //   280: astore_3
    //   281: goto -28 -> 253
    //   284: astore_3
    //   285: goto -20 -> 265
    //   288: astore 5
    //   290: goto -47 -> 243
    //   293: astore 4
    //   295: goto -85 -> 210
    //
    // Exception table:
    //   from	to	target	type
    //   60	72	207	java/lang/Exception
    //   225	232	235	java/lang/Exception
    //   60	72	239	finally
    //   167	173	268	java/lang/Exception
    //   178	185	272	java/lang/Exception
    //   214	220	276	java/lang/Exception
    //   247	253	280	java/lang/Exception
    //   258	265	284	java/lang/Exception
    //   80	87	288	finally
    //   95	106	288	finally
    //   114	127	288	finally
    //   135	143	288	finally
    //   151	163	288	finally
    //   80	87	293	java/lang/Exception
    //   95	106	293	java/lang/Exception
    //   114	127	293	java/lang/Exception
    //   135	143	293	java/lang/Exception
    //   151	163	293	java/lang/Exception
  }

  // ERROR //
  List b(double paramDouble1, double paramDouble2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 11
    //   3: new 295	java/util/ArrayList
    //   6: dup
    //   7: invokespecial 296	java/util/ArrayList:<init>	()V
    //   10: astore 12
    //   12: getstatic 359	com/baidu/location/c/k$b:d	Lcom/baidu/location/c/k$b;
    //   15: aload_0
    //   16: getfield 48	com/baidu/location/c/k:d	I
    //   19: dload_1
    //   20: dload_3
    //   21: invokestatic 166	com/baidu/location/c/k$b:a	(Lcom/baidu/location/c/k$b;IDD)Ljava/lang/String;
    //   24: astore 10
    //   26: new 361	java/util/HashMap
    //   29: dup
    //   30: invokespecial 362	java/util/HashMap:<init>	()V
    //   33: astore 13
    //   35: new 295	java/util/ArrayList
    //   38: dup
    //   39: invokespecial 296	java/util/ArrayList:<init>	()V
    //   42: astore 14
    //   44: aload_0
    //   45: getfield 46	com/baidu/location/c/k:e	Landroid/database/sqlite/SQLiteDatabase;
    //   48: aload 10
    //   50: aconst_null
    //   51: invokevirtual 170	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   54: astore 10
    //   56: aload 10
    //   58: invokeinterface 175 1 0
    //   63: ifeq +262 -> 325
    //   66: aload 10
    //   68: invokeinterface 180 1 0
    //   73: ifne +252 -> 325
    //   76: aload 10
    //   78: iconst_0
    //   79: invokeinterface 184 2 0
    //   84: astore 11
    //   86: aload 10
    //   88: iconst_2
    //   89: invokeinterface 184 2 0
    //   94: astore 15
    //   96: aload 10
    //   98: iconst_4
    //   99: invokeinterface 188 2 0
    //   104: dstore 5
    //   106: aload 10
    //   108: iconst_5
    //   109: invokeinterface 188 2 0
    //   114: dstore 7
    //   116: aload 10
    //   118: bipush 6
    //   120: invokeinterface 354 2 0
    //   125: istore 9
    //   127: aload_0
    //   128: dload_3
    //   129: dload_1
    //   130: dload 7
    //   132: dload 5
    //   134: invokespecial 190	com/baidu/location/c/k:c	(DDDD)D
    //   137: dstore 5
    //   139: dload 5
    //   141: getstatic 359	com/baidu/location/c/k$b:d	Lcom/baidu/location/c/k$b;
    //   144: invokestatic 193	com/baidu/location/c/k$b:d	(Lcom/baidu/location/c/k$b;)I
    //   147: i2d
    //   148: dcmpg
    //   149: ifge +63 -> 212
    //   152: aload 13
    //   154: new 364	com/baidu/location/Poi
    //   157: dup
    //   158: new 117	java/lang/String
    //   161: dup
    //   162: aload 11
    //   164: invokevirtual 233	java/lang/String:getBytes	()[B
    //   167: invokestatic 238	com/baidu/location/f/b/b:a	([B)[B
    //   170: invokespecial 241	java/lang/String:<init>	([B)V
    //   173: new 117	java/lang/String
    //   176: dup
    //   177: aload 15
    //   179: invokevirtual 233	java/lang/String:getBytes	()[B
    //   182: invokestatic 238	com/baidu/location/f/b/b:a	([B)[B
    //   185: invokespecial 241	java/lang/String:<init>	([B)V
    //   188: dconst_0
    //   189: invokespecial 367	com/baidu/location/Poi:<init>	(Ljava/lang/String;Ljava/lang/String;D)V
    //   192: iload 9
    //   194: dload 5
    //   196: d2f
    //   197: invokestatic 371	java/lang/Math:round	(F)I
    //   200: invokestatic 373	com/baidu/location/c/k:a	(II)I
    //   203: invokestatic 379	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   206: invokeinterface 385 3 0
    //   211: pop
    //   212: aload 10
    //   214: invokeinterface 196 1 0
    //   219: pop
    //   220: goto -154 -> 66
    //   223: astore 11
    //   225: aload 10
    //   227: ifnull +10 -> 237
    //   230: aload 10
    //   232: invokeinterface 199 1 0
    //   237: aload 14
    //   239: aload 13
    //   241: invokeinterface 389 1 0
    //   246: invokeinterface 318 2 0
    //   251: pop
    //   252: aload 14
    //   254: new 391	com/baidu/location/c/l
    //   257: dup
    //   258: aload_0
    //   259: invokespecial 394	com/baidu/location/c/l:<init>	(Lcom/baidu/location/c/k;)V
    //   262: invokestatic 400	java/util/Collections:sort	(Ljava/util/List;Ljava/util/Comparator;)V
    //   265: aload 14
    //   267: invokeinterface 404 1 0
    //   272: ifle +50 -> 322
    //   275: aload 14
    //   277: iconst_0
    //   278: invokeinterface 408 2 0
    //   283: checkcast 410	java/util/Map$Entry
    //   286: invokeinterface 413 1 0
    //   291: checkcast 364	com/baidu/location/Poi
    //   294: astore 10
    //   296: aload 12
    //   298: new 364	com/baidu/location/Poi
    //   301: dup
    //   302: aload 10
    //   304: invokevirtual 416	com/baidu/location/Poi:getId	()Ljava/lang/String;
    //   307: aload 10
    //   309: invokevirtual 419	com/baidu/location/Poi:getName	()Ljava/lang/String;
    //   312: dconst_1
    //   313: invokespecial 367	com/baidu/location/Poi:<init>	(Ljava/lang/String;Ljava/lang/String;D)V
    //   316: invokeinterface 309 2 0
    //   321: pop
    //   322: aload 12
    //   324: areturn
    //   325: aload 10
    //   327: ifnull -90 -> 237
    //   330: aload 10
    //   332: invokeinterface 199 1 0
    //   337: goto -100 -> 237
    //   340: astore 10
    //   342: goto -105 -> 237
    //   345: astore 10
    //   347: aload 11
    //   349: ifnull +10 -> 359
    //   352: aload 11
    //   354: invokeinterface 199 1 0
    //   359: aload 10
    //   361: athrow
    //   362: astore 10
    //   364: goto -127 -> 237
    //   367: astore 11
    //   369: goto -10 -> 359
    //   372: astore 12
    //   374: aload 10
    //   376: astore 11
    //   378: aload 12
    //   380: astore 10
    //   382: goto -35 -> 347
    //   385: astore 10
    //   387: aconst_null
    //   388: astore 10
    //   390: goto -165 -> 225
    //
    // Exception table:
    //   from	to	target	type
    //   56	66	223	java/lang/Exception
    //   66	212	223	java/lang/Exception
    //   212	220	223	java/lang/Exception
    //   330	337	340	java/lang/Exception
    //   44	56	345	finally
    //   230	237	362	java/lang/Exception
    //   352	359	367	java/lang/Exception
    //   56	66	372	finally
    //   66	212	372	finally
    //   212	220	372	finally
    //   44	56	385	java/lang/Exception
  }

  // ERROR //
  JSONObject b()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 11
    //   3: aconst_null
    //   4: astore 13
    //   6: aconst_null
    //   7: astore 9
    //   9: aconst_null
    //   10: astore 12
    //   12: aconst_null
    //   13: astore 14
    //   15: aconst_null
    //   16: astore 8
    //   18: new 282	org/json/JSONObject
    //   21: dup
    //   22: invokespecial 421	org/json/JSONObject:<init>	()V
    //   25: astore 10
    //   27: new 125	java/lang/StringBuffer
    //   30: dup
    //   31: invokespecial 126	java/lang/StringBuffer:<init>	()V
    //   34: astore 15
    //   36: new 125	java/lang/StringBuffer
    //   39: dup
    //   40: invokespecial 126	java/lang/StringBuffer:<init>	()V
    //   43: astore 16
    //   45: invokestatic 427	java/lang/System:currentTimeMillis	()J
    //   48: ldc2_w 428
    //   51: ldiv
    //   52: l2i
    //   53: istore_1
    //   54: aload 8
    //   56: astore 6
    //   58: aload 9
    //   60: astore 4
    //   62: aload 12
    //   64: astore 7
    //   66: aload 11
    //   68: astore 5
    //   70: aload 14
    //   72: astore_3
    //   73: aload 13
    //   75: astore_2
    //   76: aload_0
    //   77: getfield 46	com/baidu/location/c/k:e	Landroid/database/sqlite/SQLiteDatabase;
    //   80: ifnull +1406 -> 1486
    //   83: aload 8
    //   85: astore 6
    //   87: aload 9
    //   89: astore 4
    //   91: aload 12
    //   93: astore 7
    //   95: aload 11
    //   97: astore 5
    //   99: aload 14
    //   101: astore_3
    //   102: aload 13
    //   104: astore_2
    //   105: aload_0
    //   106: getfield 46	com/baidu/location/c/k:e	Landroid/database/sqlite/SQLiteDatabase;
    //   109: invokevirtual 54	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   112: ifeq +1374 -> 1486
    //   115: aload 8
    //   117: astore 6
    //   119: aload 9
    //   121: astore 4
    //   123: aload 12
    //   125: astore 7
    //   127: aload 11
    //   129: astore 5
    //   131: new 431	org/json/JSONArray
    //   134: dup
    //   135: invokespecial 432	org/json/JSONArray:<init>	()V
    //   138: astore 13
    //   140: aload 8
    //   142: astore 6
    //   144: aload 9
    //   146: astore 4
    //   148: aload 12
    //   150: astore 7
    //   152: aload 11
    //   154: astore 5
    //   156: new 431	org/json/JSONArray
    //   159: dup
    //   160: invokespecial 432	org/json/JSONArray:<init>	()V
    //   163: astore 14
    //   165: aload 8
    //   167: astore 6
    //   169: aload 9
    //   171: astore 4
    //   173: aload 12
    //   175: astore 7
    //   177: aload 11
    //   179: astore 5
    //   181: new 431	org/json/JSONArray
    //   184: dup
    //   185: invokespecial 432	org/json/JSONArray:<init>	()V
    //   188: astore_2
    //   189: aload 8
    //   191: astore 6
    //   193: aload 9
    //   195: astore 4
    //   197: aload 12
    //   199: astore 7
    //   201: aload 11
    //   203: astore 5
    //   205: new 431	org/json/JSONArray
    //   208: dup
    //   209: invokespecial 432	org/json/JSONArray:<init>	()V
    //   212: astore_3
    //   213: aload 8
    //   215: astore 6
    //   217: aload 9
    //   219: astore 4
    //   221: aload 12
    //   223: astore 7
    //   225: aload 11
    //   227: astore 5
    //   229: aload_0
    //   230: getfield 46	com/baidu/location/c/k:e	Landroid/database/sqlite/SQLiteDatabase;
    //   233: ldc_w 434
    //   236: iconst_3
    //   237: anewarray 4	java/lang/Object
    //   240: dup
    //   241: iconst_0
    //   242: iconst_0
    //   243: invokestatic 379	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   246: aastore
    //   247: dup
    //   248: iconst_1
    //   249: iload_1
    //   250: invokestatic 379	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   253: aastore
    //   254: dup
    //   255: iconst_2
    //   256: aload_0
    //   257: getfield 40	com/baidu/location/c/k:b	Lcom/baidu/location/c/d;
    //   260: invokevirtual 343	com/baidu/location/c/d:new	()Lcom/baidu/location/c/j;
    //   263: invokevirtual 437	com/baidu/location/c/j:p	()I
    //   266: invokestatic 379	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   269: aastore
    //   270: invokestatic 441	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   273: aconst_null
    //   274: invokevirtual 170	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   277: astore 8
    //   279: aload 8
    //   281: astore 6
    //   283: aload 9
    //   285: astore 4
    //   287: aload 8
    //   289: astore 7
    //   291: aload 11
    //   293: astore 5
    //   295: aload_0
    //   296: getfield 46	com/baidu/location/c/k:e	Landroid/database/sqlite/SQLiteDatabase;
    //   299: ldc_w 434
    //   302: iconst_3
    //   303: anewarray 4	java/lang/Object
    //   306: dup
    //   307: iconst_0
    //   308: iconst_1
    //   309: invokestatic 379	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   312: aastore
    //   313: dup
    //   314: iconst_1
    //   315: iload_1
    //   316: invokestatic 379	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   319: aastore
    //   320: dup
    //   321: iconst_2
    //   322: aload_0
    //   323: getfield 40	com/baidu/location/c/k:b	Lcom/baidu/location/c/d;
    //   326: invokevirtual 343	com/baidu/location/c/d:new	()Lcom/baidu/location/c/j;
    //   329: invokevirtual 444	com/baidu/location/c/j:q	()I
    //   332: invokestatic 379	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   335: aastore
    //   336: invokestatic 441	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   339: aconst_null
    //   340: invokevirtual 170	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   343: astore 9
    //   345: aload 8
    //   347: astore 6
    //   349: aload 9
    //   351: astore 4
    //   353: aload 8
    //   355: astore 7
    //   357: aload 9
    //   359: astore 5
    //   361: aload 8
    //   363: invokeinterface 175 1 0
    //   368: ifeq +429 -> 797
    //   371: aload 8
    //   373: astore 6
    //   375: aload 9
    //   377: astore 4
    //   379: aload 8
    //   381: astore 7
    //   383: aload 9
    //   385: astore 5
    //   387: new 446	java/util/HashSet
    //   390: dup
    //   391: invokespecial 447	java/util/HashSet:<init>	()V
    //   394: astore 11
    //   396: aload 8
    //   398: astore 6
    //   400: aload 9
    //   402: astore 4
    //   404: aload 8
    //   406: astore 7
    //   408: aload 9
    //   410: astore 5
    //   412: aload 8
    //   414: invokeinterface 180 1 0
    //   419: ifne +271 -> 690
    //   422: aload 8
    //   424: astore 6
    //   426: aload 9
    //   428: astore 4
    //   430: aload 8
    //   432: astore 7
    //   434: aload 9
    //   436: astore 5
    //   438: aload 8
    //   440: iconst_0
    //   441: invokeinterface 184 2 0
    //   446: astore 12
    //   448: aload 8
    //   450: astore 6
    //   452: aload 9
    //   454: astore 4
    //   456: aload 8
    //   458: astore 7
    //   460: aload 9
    //   462: astore 5
    //   464: aload 8
    //   466: iconst_1
    //   467: invokeinterface 184 2 0
    //   472: astore 17
    //   474: aload 8
    //   476: astore 6
    //   478: aload 9
    //   480: astore 4
    //   482: aload 8
    //   484: astore 7
    //   486: aload 9
    //   488: astore 5
    //   490: aload_2
    //   491: aload 12
    //   493: invokevirtual 450	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   496: pop
    //   497: aload 8
    //   499: astore 6
    //   501: aload 9
    //   503: astore 4
    //   505: aload 8
    //   507: astore 7
    //   509: aload 9
    //   511: astore 5
    //   513: aload 11
    //   515: aload 17
    //   517: invokevirtual 451	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   520: pop
    //   521: aload 8
    //   523: astore 6
    //   525: aload 9
    //   527: astore 4
    //   529: aload 8
    //   531: astore 7
    //   533: aload 9
    //   535: astore 5
    //   537: aload 16
    //   539: invokevirtual 454	java/lang/StringBuffer:length	()I
    //   542: ifle +28 -> 570
    //   545: aload 8
    //   547: astore 6
    //   549: aload 9
    //   551: astore 4
    //   553: aload 8
    //   555: astore 7
    //   557: aload 9
    //   559: astore 5
    //   561: aload 16
    //   563: ldc_w 456
    //   566: invokevirtual 459	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   569: pop
    //   570: aload 8
    //   572: astore 6
    //   574: aload 9
    //   576: astore 4
    //   578: aload 8
    //   580: astore 7
    //   582: aload 9
    //   584: astore 5
    //   586: aload 16
    //   588: ldc_w 461
    //   591: invokevirtual 459	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   594: aload 12
    //   596: invokevirtual 459	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   599: ldc_w 461
    //   602: invokevirtual 459	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   605: pop
    //   606: aload 8
    //   608: astore 6
    //   610: aload 9
    //   612: astore 4
    //   614: aload 8
    //   616: astore 7
    //   618: aload 9
    //   620: astore 5
    //   622: aload 8
    //   624: invokeinterface 196 1 0
    //   629: pop
    //   630: goto -234 -> 396
    //   633: astore_2
    //   634: aload 6
    //   636: ifnull +10 -> 646
    //   639: aload 6
    //   641: invokeinterface 199 1 0
    //   646: aload 4
    //   648: ifnull +10 -> 658
    //   651: aload 4
    //   653: invokeinterface 199 1 0
    //   658: aload 10
    //   660: astore_2
    //   661: aload 10
    //   663: ldc_w 463
    //   666: invokevirtual 286	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   669: ifne +19 -> 688
    //   672: aload 10
    //   674: astore_2
    //   675: aload 10
    //   677: ldc_w 465
    //   680: invokevirtual 286	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   683: ifne +5 -> 688
    //   686: aconst_null
    //   687: astore_2
    //   688: aload_2
    //   689: areturn
    //   690: aload 8
    //   692: astore 6
    //   694: aload 9
    //   696: astore 4
    //   698: aload 8
    //   700: astore 7
    //   702: aload 9
    //   704: astore 5
    //   706: aload 11
    //   708: invokevirtual 466	java/util/HashSet:size	()I
    //   711: anewarray 117	java/lang/String
    //   714: astore 12
    //   716: aload 8
    //   718: astore 6
    //   720: aload 9
    //   722: astore 4
    //   724: aload 8
    //   726: astore 7
    //   728: aload 9
    //   730: astore 5
    //   732: aload 11
    //   734: aload 12
    //   736: invokevirtual 470	java/util/HashSet:toArray	([Ljava/lang/Object;)[Ljava/lang/Object;
    //   739: pop
    //   740: iconst_0
    //   741: istore_1
    //   742: aload 8
    //   744: astore 6
    //   746: aload 9
    //   748: astore 4
    //   750: aload 8
    //   752: astore 7
    //   754: aload 9
    //   756: astore 5
    //   758: iload_1
    //   759: aload 12
    //   761: arraylength
    //   762: if_icmpge +35 -> 797
    //   765: aload 8
    //   767: astore 6
    //   769: aload 9
    //   771: astore 4
    //   773: aload 8
    //   775: astore 7
    //   777: aload 9
    //   779: astore 5
    //   781: aload_3
    //   782: aload 12
    //   784: iload_1
    //   785: aaload
    //   786: invokevirtual 450	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   789: pop
    //   790: iload_1
    //   791: iconst_1
    //   792: iadd
    //   793: istore_1
    //   794: goto -52 -> 742
    //   797: aload 8
    //   799: astore 6
    //   801: aload 9
    //   803: astore 4
    //   805: aload 8
    //   807: astore 7
    //   809: aload 9
    //   811: astore 5
    //   813: aload 9
    //   815: invokeinterface 175 1 0
    //   820: ifeq +401 -> 1221
    //   823: aload 8
    //   825: astore 6
    //   827: aload 9
    //   829: astore 4
    //   831: aload 8
    //   833: astore 7
    //   835: aload 9
    //   837: astore 5
    //   839: new 446	java/util/HashSet
    //   842: dup
    //   843: invokespecial 447	java/util/HashSet:<init>	()V
    //   846: astore 11
    //   848: aload 8
    //   850: astore 6
    //   852: aload 9
    //   854: astore 4
    //   856: aload 8
    //   858: astore 7
    //   860: aload 9
    //   862: astore 5
    //   864: aload 9
    //   866: invokeinterface 180 1 0
    //   871: ifne +242 -> 1113
    //   874: aload 8
    //   876: astore 6
    //   878: aload 9
    //   880: astore 4
    //   882: aload 8
    //   884: astore 7
    //   886: aload 9
    //   888: astore 5
    //   890: aload 9
    //   892: iconst_0
    //   893: invokeinterface 184 2 0
    //   898: astore 12
    //   900: aload 8
    //   902: astore 6
    //   904: aload 9
    //   906: astore 4
    //   908: aload 8
    //   910: astore 7
    //   912: aload 9
    //   914: astore 5
    //   916: aload 9
    //   918: iconst_1
    //   919: invokeinterface 184 2 0
    //   924: astore 17
    //   926: aload 8
    //   928: astore 6
    //   930: aload 9
    //   932: astore 4
    //   934: aload 8
    //   936: astore 7
    //   938: aload 9
    //   940: astore 5
    //   942: aload 13
    //   944: aload 12
    //   946: invokevirtual 450	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   949: pop
    //   950: aload 8
    //   952: astore 6
    //   954: aload 9
    //   956: astore 4
    //   958: aload 8
    //   960: astore 7
    //   962: aload 9
    //   964: astore 5
    //   966: aload 11
    //   968: aload 17
    //   970: invokevirtual 451	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   973: pop
    //   974: aload 8
    //   976: astore 6
    //   978: aload 9
    //   980: astore 4
    //   982: aload 8
    //   984: astore 7
    //   986: aload 9
    //   988: astore 5
    //   990: aload 15
    //   992: invokevirtual 454	java/lang/StringBuffer:length	()I
    //   995: ifle +28 -> 1023
    //   998: aload 8
    //   1000: astore 6
    //   1002: aload 9
    //   1004: astore 4
    //   1006: aload 8
    //   1008: astore 7
    //   1010: aload 9
    //   1012: astore 5
    //   1014: aload 15
    //   1016: ldc_w 456
    //   1019: invokevirtual 459	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1022: pop
    //   1023: aload 8
    //   1025: astore 6
    //   1027: aload 9
    //   1029: astore 4
    //   1031: aload 8
    //   1033: astore 7
    //   1035: aload 9
    //   1037: astore 5
    //   1039: aload 15
    //   1041: ldc_w 461
    //   1044: invokevirtual 459	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1047: aload 12
    //   1049: invokevirtual 459	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1052: ldc_w 461
    //   1055: invokevirtual 459	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1058: pop
    //   1059: aload 8
    //   1061: astore 6
    //   1063: aload 9
    //   1065: astore 4
    //   1067: aload 8
    //   1069: astore 7
    //   1071: aload 9
    //   1073: astore 5
    //   1075: aload 9
    //   1077: invokeinterface 196 1 0
    //   1082: pop
    //   1083: goto -235 -> 848
    //   1086: astore_2
    //   1087: aload 7
    //   1089: ifnull +10 -> 1099
    //   1092: aload 7
    //   1094: invokeinterface 199 1 0
    //   1099: aload 5
    //   1101: ifnull +10 -> 1111
    //   1104: aload 5
    //   1106: invokeinterface 199 1 0
    //   1111: aload_2
    //   1112: athrow
    //   1113: aload 8
    //   1115: astore 6
    //   1117: aload 9
    //   1119: astore 4
    //   1121: aload 8
    //   1123: astore 7
    //   1125: aload 9
    //   1127: astore 5
    //   1129: aload 11
    //   1131: invokevirtual 466	java/util/HashSet:size	()I
    //   1134: anewarray 117	java/lang/String
    //   1137: astore 12
    //   1139: aload 8
    //   1141: astore 6
    //   1143: aload 9
    //   1145: astore 4
    //   1147: aload 8
    //   1149: astore 7
    //   1151: aload 9
    //   1153: astore 5
    //   1155: aload 11
    //   1157: aload 12
    //   1159: invokevirtual 470	java/util/HashSet:toArray	([Ljava/lang/Object;)[Ljava/lang/Object;
    //   1162: pop
    //   1163: iconst_0
    //   1164: istore_1
    //   1165: aload 8
    //   1167: astore 6
    //   1169: aload 9
    //   1171: astore 4
    //   1173: aload 8
    //   1175: astore 7
    //   1177: aload 9
    //   1179: astore 5
    //   1181: iload_1
    //   1182: aload 12
    //   1184: arraylength
    //   1185: if_icmpge +36 -> 1221
    //   1188: aload 8
    //   1190: astore 6
    //   1192: aload 9
    //   1194: astore 4
    //   1196: aload 8
    //   1198: astore 7
    //   1200: aload 9
    //   1202: astore 5
    //   1204: aload 14
    //   1206: aload 12
    //   1208: iload_1
    //   1209: aaload
    //   1210: invokevirtual 450	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   1213: pop
    //   1214: iload_1
    //   1215: iconst_1
    //   1216: iadd
    //   1217: istore_1
    //   1218: goto -53 -> 1165
    //   1221: aload 8
    //   1223: astore 6
    //   1225: aload 9
    //   1227: astore 4
    //   1229: aload 8
    //   1231: astore 7
    //   1233: aload 9
    //   1235: astore 5
    //   1237: aload_2
    //   1238: invokevirtual 471	org/json/JSONArray:length	()I
    //   1241: ifeq +107 -> 1348
    //   1244: aload 8
    //   1246: astore 6
    //   1248: aload 9
    //   1250: astore 4
    //   1252: aload 8
    //   1254: astore 7
    //   1256: aload 9
    //   1258: astore 5
    //   1260: new 282	org/json/JSONObject
    //   1263: dup
    //   1264: invokespecial 421	org/json/JSONObject:<init>	()V
    //   1267: astore 11
    //   1269: aload 8
    //   1271: astore 6
    //   1273: aload 9
    //   1275: astore 4
    //   1277: aload 8
    //   1279: astore 7
    //   1281: aload 9
    //   1283: astore 5
    //   1285: aload 11
    //   1287: ldc_w 473
    //   1290: aload_2
    //   1291: invokevirtual 476	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1294: pop
    //   1295: aload 8
    //   1297: astore 6
    //   1299: aload 9
    //   1301: astore 4
    //   1303: aload 8
    //   1305: astore 7
    //   1307: aload 9
    //   1309: astore 5
    //   1311: aload 11
    //   1313: ldc_w 478
    //   1316: aload_3
    //   1317: invokevirtual 476	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1320: pop
    //   1321: aload 8
    //   1323: astore 6
    //   1325: aload 9
    //   1327: astore 4
    //   1329: aload 8
    //   1331: astore 7
    //   1333: aload 9
    //   1335: astore 5
    //   1337: aload 10
    //   1339: ldc_w 465
    //   1342: aload 11
    //   1344: invokevirtual 476	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1347: pop
    //   1348: aload 8
    //   1350: astore 6
    //   1352: aload 9
    //   1354: astore 4
    //   1356: aload 8
    //   1358: astore 7
    //   1360: aload 9
    //   1362: astore 5
    //   1364: aload 8
    //   1366: astore_3
    //   1367: aload 9
    //   1369: astore_2
    //   1370: aload 13
    //   1372: invokevirtual 471	org/json/JSONArray:length	()I
    //   1375: ifeq +111 -> 1486
    //   1378: aload 8
    //   1380: astore 6
    //   1382: aload 9
    //   1384: astore 4
    //   1386: aload 8
    //   1388: astore 7
    //   1390: aload 9
    //   1392: astore 5
    //   1394: new 282	org/json/JSONObject
    //   1397: dup
    //   1398: invokespecial 421	org/json/JSONObject:<init>	()V
    //   1401: astore_2
    //   1402: aload 8
    //   1404: astore 6
    //   1406: aload 9
    //   1408: astore 4
    //   1410: aload 8
    //   1412: astore 7
    //   1414: aload 9
    //   1416: astore 5
    //   1418: aload_2
    //   1419: ldc_w 473
    //   1422: aload 13
    //   1424: invokevirtual 476	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1427: pop
    //   1428: aload 8
    //   1430: astore 6
    //   1432: aload 9
    //   1434: astore 4
    //   1436: aload 8
    //   1438: astore 7
    //   1440: aload 9
    //   1442: astore 5
    //   1444: aload_2
    //   1445: ldc_w 478
    //   1448: aload 14
    //   1450: invokevirtual 476	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1453: pop
    //   1454: aload 8
    //   1456: astore 6
    //   1458: aload 9
    //   1460: astore 4
    //   1462: aload 8
    //   1464: astore 7
    //   1466: aload 9
    //   1468: astore 5
    //   1470: aload 10
    //   1472: ldc_w 463
    //   1475: aload_2
    //   1476: invokevirtual 476	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1479: pop
    //   1480: aload 9
    //   1482: astore_2
    //   1483: aload 8
    //   1485: astore_3
    //   1486: aload_3
    //   1487: astore 6
    //   1489: aload_2
    //   1490: astore 4
    //   1492: aload_3
    //   1493: astore 7
    //   1495: aload_2
    //   1496: astore 5
    //   1498: aload 16
    //   1500: invokevirtual 454	java/lang/StringBuffer:length	()I
    //   1503: ifle +66 -> 1569
    //   1506: aload_3
    //   1507: astore 6
    //   1509: aload_2
    //   1510: astore 4
    //   1512: aload_3
    //   1513: astore 7
    //   1515: aload_2
    //   1516: astore 5
    //   1518: getstatic 484	java/util/Locale:US	Ljava/util/Locale;
    //   1521: ldc_w 486
    //   1524: iconst_2
    //   1525: anewarray 4	java/lang/Object
    //   1528: dup
    //   1529: iconst_0
    //   1530: iconst_0
    //   1531: invokestatic 379	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1534: aastore
    //   1535: dup
    //   1536: iconst_1
    //   1537: aload 16
    //   1539: invokevirtual 134	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   1542: aastore
    //   1543: invokestatic 489	java/lang/String:format	(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   1546: astore 8
    //   1548: aload_3
    //   1549: astore 6
    //   1551: aload_2
    //   1552: astore 4
    //   1554: aload_3
    //   1555: astore 7
    //   1557: aload_2
    //   1558: astore 5
    //   1560: aload_0
    //   1561: getfield 46	com/baidu/location/c/k:e	Landroid/database/sqlite/SQLiteDatabase;
    //   1564: aload 8
    //   1566: invokevirtual 60	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   1569: aload_3
    //   1570: astore 6
    //   1572: aload_2
    //   1573: astore 4
    //   1575: aload_3
    //   1576: astore 7
    //   1578: aload_2
    //   1579: astore 5
    //   1581: aload 15
    //   1583: invokevirtual 454	java/lang/StringBuffer:length	()I
    //   1586: ifle +66 -> 1652
    //   1589: aload_3
    //   1590: astore 6
    //   1592: aload_2
    //   1593: astore 4
    //   1595: aload_3
    //   1596: astore 7
    //   1598: aload_2
    //   1599: astore 5
    //   1601: getstatic 484	java/util/Locale:US	Ljava/util/Locale;
    //   1604: ldc_w 486
    //   1607: iconst_2
    //   1608: anewarray 4	java/lang/Object
    //   1611: dup
    //   1612: iconst_0
    //   1613: iconst_1
    //   1614: invokestatic 379	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1617: aastore
    //   1618: dup
    //   1619: iconst_1
    //   1620: aload 15
    //   1622: invokevirtual 134	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   1625: aastore
    //   1626: invokestatic 489	java/lang/String:format	(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   1629: astore 8
    //   1631: aload_3
    //   1632: astore 6
    //   1634: aload_2
    //   1635: astore 4
    //   1637: aload_3
    //   1638: astore 7
    //   1640: aload_2
    //   1641: astore 5
    //   1643: aload_0
    //   1644: getfield 46	com/baidu/location/c/k:e	Landroid/database/sqlite/SQLiteDatabase;
    //   1647: aload 8
    //   1649: invokevirtual 60	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   1652: aload_3
    //   1653: ifnull +9 -> 1662
    //   1656: aload_3
    //   1657: invokeinterface 199 1 0
    //   1662: aload_2
    //   1663: ifnull -1005 -> 658
    //   1666: aload_2
    //   1667: invokeinterface 199 1 0
    //   1672: goto -1014 -> 658
    //   1675: astore_2
    //   1676: goto -1018 -> 658
    //   1679: astore_3
    //   1680: goto -18 -> 1662
    //   1683: astore_2
    //   1684: goto -1038 -> 646
    //   1687: astore_2
    //   1688: goto -1030 -> 658
    //   1691: astore_3
    //   1692: goto -593 -> 1099
    //   1695: astore_3
    //   1696: goto -585 -> 1111
    //
    // Exception table:
    //   from	to	target	type
    //   76	83	633	java/lang/Exception
    //   105	115	633	java/lang/Exception
    //   131	140	633	java/lang/Exception
    //   156	165	633	java/lang/Exception
    //   181	189	633	java/lang/Exception
    //   205	213	633	java/lang/Exception
    //   229	279	633	java/lang/Exception
    //   295	345	633	java/lang/Exception
    //   361	371	633	java/lang/Exception
    //   387	396	633	java/lang/Exception
    //   412	422	633	java/lang/Exception
    //   438	448	633	java/lang/Exception
    //   464	474	633	java/lang/Exception
    //   490	497	633	java/lang/Exception
    //   513	521	633	java/lang/Exception
    //   537	545	633	java/lang/Exception
    //   561	570	633	java/lang/Exception
    //   586	606	633	java/lang/Exception
    //   622	630	633	java/lang/Exception
    //   706	716	633	java/lang/Exception
    //   732	740	633	java/lang/Exception
    //   758	765	633	java/lang/Exception
    //   781	790	633	java/lang/Exception
    //   813	823	633	java/lang/Exception
    //   839	848	633	java/lang/Exception
    //   864	874	633	java/lang/Exception
    //   890	900	633	java/lang/Exception
    //   916	926	633	java/lang/Exception
    //   942	950	633	java/lang/Exception
    //   966	974	633	java/lang/Exception
    //   990	998	633	java/lang/Exception
    //   1014	1023	633	java/lang/Exception
    //   1039	1059	633	java/lang/Exception
    //   1075	1083	633	java/lang/Exception
    //   1129	1139	633	java/lang/Exception
    //   1155	1163	633	java/lang/Exception
    //   1181	1188	633	java/lang/Exception
    //   1204	1214	633	java/lang/Exception
    //   1237	1244	633	java/lang/Exception
    //   1260	1269	633	java/lang/Exception
    //   1285	1295	633	java/lang/Exception
    //   1311	1321	633	java/lang/Exception
    //   1337	1348	633	java/lang/Exception
    //   1370	1378	633	java/lang/Exception
    //   1394	1402	633	java/lang/Exception
    //   1418	1428	633	java/lang/Exception
    //   1444	1454	633	java/lang/Exception
    //   1470	1480	633	java/lang/Exception
    //   1498	1506	633	java/lang/Exception
    //   1518	1548	633	java/lang/Exception
    //   1560	1569	633	java/lang/Exception
    //   1581	1589	633	java/lang/Exception
    //   1601	1631	633	java/lang/Exception
    //   1643	1652	633	java/lang/Exception
    //   76	83	1086	finally
    //   105	115	1086	finally
    //   131	140	1086	finally
    //   156	165	1086	finally
    //   181	189	1086	finally
    //   205	213	1086	finally
    //   229	279	1086	finally
    //   295	345	1086	finally
    //   361	371	1086	finally
    //   387	396	1086	finally
    //   412	422	1086	finally
    //   438	448	1086	finally
    //   464	474	1086	finally
    //   490	497	1086	finally
    //   513	521	1086	finally
    //   537	545	1086	finally
    //   561	570	1086	finally
    //   586	606	1086	finally
    //   622	630	1086	finally
    //   706	716	1086	finally
    //   732	740	1086	finally
    //   758	765	1086	finally
    //   781	790	1086	finally
    //   813	823	1086	finally
    //   839	848	1086	finally
    //   864	874	1086	finally
    //   890	900	1086	finally
    //   916	926	1086	finally
    //   942	950	1086	finally
    //   966	974	1086	finally
    //   990	998	1086	finally
    //   1014	1023	1086	finally
    //   1039	1059	1086	finally
    //   1075	1083	1086	finally
    //   1129	1139	1086	finally
    //   1155	1163	1086	finally
    //   1181	1188	1086	finally
    //   1204	1214	1086	finally
    //   1237	1244	1086	finally
    //   1260	1269	1086	finally
    //   1285	1295	1086	finally
    //   1311	1321	1086	finally
    //   1337	1348	1086	finally
    //   1370	1378	1086	finally
    //   1394	1402	1086	finally
    //   1418	1428	1086	finally
    //   1444	1454	1086	finally
    //   1470	1480	1086	finally
    //   1498	1506	1086	finally
    //   1518	1548	1086	finally
    //   1560	1569	1086	finally
    //   1581	1589	1086	finally
    //   1601	1631	1086	finally
    //   1643	1652	1086	finally
    //   1666	1672	1675	java/lang/Exception
    //   1656	1662	1679	java/lang/Exception
    //   639	646	1683	java/lang/Exception
    //   651	658	1687	java/lang/Exception
    //   1092	1099	1691	java/lang/Exception
    //   1104	1111	1695	java/lang/Exception
  }

  private static final class a
  {
    private double a;
    private double b;

    private a(double paramDouble1, double paramDouble2)
    {
      this.a = paramDouble1;
      this.b = paramDouble2;
    }
  }

  private static abstract enum b
  {
    private final int e;
    private final String f;
    private final String g;
    private final String h;
    private final int i;

    private b(String paramString3, String paramInt1, String paramInt2, int arg6, int arg7)
    {
      this.f = paramString3;
      this.g = paramInt1;
      this.h = paramInt2;
      int k;
      this.e = k;
      int m;
      this.i = m;
    }

    private String a(int paramInt, double paramDouble1, double paramDouble2)
    {
      Object localObject2 = new HashSet();
      ((HashSet)localObject2).add(k.a(paramInt, paramDouble1, paramDouble2));
      double d1 = this.e;
      if (this.e > 0)
      {
        int k = 0;
        while (k < k.c().length)
        {
          localObject1 = k.a(paramDouble2, paramDouble1, d1 * 1.414D, k.c()[k]);
          ((HashSet)localObject2).add(k.a(paramInt, localObject1[1], localObject1[0]));
          k += 1;
        }
      }
      Object localObject1 = new StringBuffer();
      localObject2 = ((HashSet)localObject2).iterator();
      paramInt = 1;
      if (((Iterator)localObject2).hasNext())
      {
        String str = (String)((Iterator)localObject2).next();
        if (paramInt != 0)
          paramInt = 0;
        while (true)
        {
          ((StringBuffer)localObject1).append("\"").append(str).append("\"");
          break;
          ((StringBuffer)localObject1).append(',');
        }
      }
      return String.format("SELECT * FROM %s WHERE gridkey IN (%s);", new Object[] { this.f, ((StringBuffer)localObject1).toString() });
    }

    private String a(JSONObject paramJSONObject)
    {
      paramJSONObject = paramJSONObject.keys();
      StringBuffer localStringBuffer = new StringBuffer();
      while (paramJSONObject.hasNext())
      {
        String str = (String)paramJSONObject.next();
        if (localStringBuffer.length() != 0)
          localStringBuffer.append(",");
        localStringBuffer.append("\"").append(str).append("\"");
      }
      return String.format(Locale.US, "DELETE FROM %s WHERE gridkey IN (%s)", new Object[] { this.f, localStringBuffer });
    }

    private static void b(StringBuffer paramStringBuffer, String paramString1, String paramString2, int paramInt)
    {
      if (paramStringBuffer.length() > 0)
        paramStringBuffer.append(",");
      paramStringBuffer.append("(\"").append(paramString1).append("\",\"").append(paramString2).append("\",").append(paramInt).append(",").append(System.currentTimeMillis() / 86400000L).append(")");
    }

    abstract List a(JSONObject paramJSONObject, String paramString, int paramInt);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.c.k
 * JD-Core Version:    0.6.2
 */