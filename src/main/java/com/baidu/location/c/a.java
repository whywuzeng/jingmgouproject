package com.baidu.location.c;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.baidu.location.BDLocation;
import com.baidu.location.Jni;
import com.baidu.location.Poi;
import com.baidu.location.b.b;
import com.baidu.location.b.m;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

final class a
  implements b
{
  private final d b;
  private int c;
  private double d;
  private double e;
  private Long f;
  private final b g;
  private final b h;
  private final SQLiteDatabase i;
  private final SQLiteDatabase j;
  private StringBuffer k;
  private StringBuffer l;
  private HashSet m;
  private ConcurrentHashMap n;
  private ConcurrentHashMap o;
  private StringBuffer p;
  private boolean q;

  // ERROR //
  a(d paramd)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aload_0
    //   3: invokespecial 46	java/lang/Object:<init>	()V
    //   6: aload_0
    //   7: aload_1
    //   8: putfield 48	com/baidu/location/c/a:b	Lcom/baidu/location/c/d;
    //   11: aload_0
    //   12: iconst_0
    //   13: putfield 50	com/baidu/location/c/a:q	Z
    //   16: aload_0
    //   17: new 11	com/baidu/location/c/a$b
    //   20: dup
    //   21: aload_0
    //   22: aload_0
    //   23: iconst_1
    //   24: invokespecial 53	com/baidu/location/c/a$b:<init>	(Lcom/baidu/location/c/a;Lcom/baidu/location/c/a;Z)V
    //   27: putfield 55	com/baidu/location/c/a:g	Lcom/baidu/location/c/a$b;
    //   30: aload_0
    //   31: new 11	com/baidu/location/c/a$b
    //   34: dup
    //   35: aload_0
    //   36: aload_0
    //   37: iconst_0
    //   38: invokespecial 53	com/baidu/location/c/a$b:<init>	(Lcom/baidu/location/c/a;Lcom/baidu/location/c/a;Z)V
    //   41: putfield 57	com/baidu/location/c/a:h	Lcom/baidu/location/c/a$b;
    //   44: aload_0
    //   45: new 59	java/lang/StringBuffer
    //   48: dup
    //   49: invokespecial 60	java/lang/StringBuffer:<init>	()V
    //   52: putfield 62	com/baidu/location/c/a:p	Ljava/lang/StringBuffer;
    //   55: aload_0
    //   56: aconst_null
    //   57: putfield 64	com/baidu/location/c/a:k	Ljava/lang/StringBuffer;
    //   60: aload_0
    //   61: aconst_null
    //   62: putfield 66	com/baidu/location/c/a:l	Ljava/lang/StringBuffer;
    //   65: aload_0
    //   66: new 68	java/util/HashSet
    //   69: dup
    //   70: invokespecial 69	java/util/HashSet:<init>	()V
    //   73: putfield 71	com/baidu/location/c/a:m	Ljava/util/HashSet;
    //   76: aload_0
    //   77: new 73	java/util/concurrent/ConcurrentHashMap
    //   80: dup
    //   81: invokespecial 74	java/util/concurrent/ConcurrentHashMap:<init>	()V
    //   84: putfield 76	com/baidu/location/c/a:n	Ljava/util/concurrent/ConcurrentHashMap;
    //   87: aload_0
    //   88: new 73	java/util/concurrent/ConcurrentHashMap
    //   91: dup
    //   92: invokespecial 74	java/util/concurrent/ConcurrentHashMap:<init>	()V
    //   95: putfield 78	com/baidu/location/c/a:o	Ljava/util/concurrent/ConcurrentHashMap;
    //   98: new 80	java/io/File
    //   101: dup
    //   102: aload_0
    //   103: getfield 48	com/baidu/location/c/a:b	Lcom/baidu/location/c/d;
    //   106: invokevirtual 85	com/baidu/location/c/d:d	()Ljava/io/File;
    //   109: ldc 87
    //   111: invokespecial 90	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   114: astore_1
    //   115: aload_1
    //   116: invokevirtual 94	java/io/File:exists	()Z
    //   119: ifne +8 -> 127
    //   122: aload_1
    //   123: invokevirtual 97	java/io/File:createNewFile	()Z
    //   126: pop
    //   127: aload_1
    //   128: aconst_null
    //   129: invokestatic 103	android/database/sqlite/SQLiteDatabase:openOrCreateDatabase	(Ljava/io/File;Landroid/database/sqlite/SQLiteDatabase$CursorFactory;)Landroid/database/sqlite/SQLiteDatabase;
    //   132: astore_1
    //   133: aload_0
    //   134: aload_1
    //   135: putfield 105	com/baidu/location/c/a:i	Landroid/database/sqlite/SQLiteDatabase;
    //   138: aload_0
    //   139: getfield 105	com/baidu/location/c/a:i	Landroid/database/sqlite/SQLiteDatabase;
    //   142: ifnull +21 -> 163
    //   145: aload_0
    //   146: getfield 105	com/baidu/location/c/a:i	Landroid/database/sqlite/SQLiteDatabase;
    //   149: ldc 107
    //   151: invokevirtual 111	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   154: aload_0
    //   155: getfield 105	com/baidu/location/c/a:i	Landroid/database/sqlite/SQLiteDatabase;
    //   158: ldc 113
    //   160: invokevirtual 111	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   163: new 80	java/io/File
    //   166: dup
    //   167: aload_0
    //   168: getfield 48	com/baidu/location/c/a:b	Lcom/baidu/location/c/d;
    //   171: invokevirtual 85	com/baidu/location/c/d:d	()Ljava/io/File;
    //   174: ldc 115
    //   176: invokespecial 90	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   179: astore_1
    //   180: aload_1
    //   181: invokevirtual 94	java/io/File:exists	()Z
    //   184: ifne +8 -> 192
    //   187: aload_1
    //   188: invokevirtual 97	java/io/File:createNewFile	()Z
    //   191: pop
    //   192: aload_1
    //   193: aconst_null
    //   194: invokestatic 103	android/database/sqlite/SQLiteDatabase:openOrCreateDatabase	(Ljava/io/File;Landroid/database/sqlite/SQLiteDatabase$CursorFactory;)Landroid/database/sqlite/SQLiteDatabase;
    //   197: astore_1
    //   198: aload_0
    //   199: aload_1
    //   200: putfield 117	com/baidu/location/c/a:j	Landroid/database/sqlite/SQLiteDatabase;
    //   203: aload_0
    //   204: getfield 117	com/baidu/location/c/a:j	Landroid/database/sqlite/SQLiteDatabase;
    //   207: ifnull +21 -> 228
    //   210: aload_0
    //   211: getfield 117	com/baidu/location/c/a:j	Landroid/database/sqlite/SQLiteDatabase;
    //   214: ldc 119
    //   216: invokevirtual 111	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   219: aload_0
    //   220: getfield 117	com/baidu/location/c/a:j	Landroid/database/sqlite/SQLiteDatabase;
    //   223: ldc 121
    //   225: invokevirtual 111	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   228: return
    //   229: astore_1
    //   230: aconst_null
    //   231: astore_1
    //   232: goto -99 -> 133
    //   235: astore_1
    //   236: return
    //   237: astore_1
    //   238: aload_2
    //   239: astore_1
    //   240: goto -42 -> 198
    //   243: astore_1
    //   244: goto -81 -> 163
    //
    // Exception table:
    //   from	to	target	type
    //   98	127	229	java/lang/Exception
    //   127	133	229	java/lang/Exception
    //   210	228	235	java/lang/Exception
    //   163	192	237	java/lang/Exception
    //   192	198	237	java/lang/Exception
    //   145	163	243	java/lang/Exception
  }

  private double a(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
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

  private int a(ArrayList paramArrayList, double paramDouble)
  {
    int i3;
    if (paramArrayList.size() == 0)
    {
      i3 = 0;
      return i3;
    }
    int i1 = 0;
    label16: double d1;
    int i2;
    if (paramArrayList.size() >= 3)
    {
      double d2 = 0.0D;
      d1 = 0.0D;
      i2 = 0;
      while (i2 < paramArrayList.size())
      {
        d2 += ((c)paramArrayList.get(i2)).a;
        d1 += ((c)paramArrayList.get(i2)).b;
        i2 += 1;
      }
      double d3 = d2 / paramArrayList.size();
      double d4 = d1 / paramArrayList.size();
      i2 = 0;
      i3 = -1;
      d1 = -1.0D;
      label116: if (i2 < paramArrayList.size())
      {
        d2 = a(d4, d3, ((c)paramArrayList.get(i2)).b, ((c)paramArrayList.get(i2)).a);
        if (d2 <= d1)
          break label240;
        i3 = i2;
        d1 = d2;
      }
    }
    label240: 
    while (true)
    {
      i2 += 1;
      break label116;
      if ((d1 > paramDouble) && (i3 >= 0) && (i3 < paramArrayList.size()))
      {
        paramArrayList.remove(i3);
        i2 = 1;
        i1 += 1;
      }
      while (true)
      {
        i3 = i1;
        if (i2 != 1)
          break;
        break label16;
        i2 = 0;
      }
    }
  }

  // ERROR //
  private BDLocation a(Long paramLong)
  {
    // Byte code:
    //   0: aload_0
    //   1: iconst_0
    //   2: putfield 50	com/baidu/location/c/a:q	Z
    //   5: iconst_0
    //   6: istore 19
    //   8: iconst_0
    //   9: istore 18
    //   11: dconst_0
    //   12: dstore 12
    //   14: dconst_0
    //   15: dstore 10
    //   17: iconst_0
    //   18: istore 21
    //   20: iconst_0
    //   21: istore 20
    //   23: aload_0
    //   24: getfield 171	com/baidu/location/c/a:f	Ljava/lang/Long;
    //   27: ifnull +81 -> 108
    //   30: aload_0
    //   31: getfield 171	com/baidu/location/c/a:f	Ljava/lang/Long;
    //   34: aload_1
    //   35: invokevirtual 177	java/lang/Long:equals	(Ljava/lang/Object;)Z
    //   38: ifeq +70 -> 108
    //   41: iconst_1
    //   42: istore 19
    //   44: aload_0
    //   45: getfield 179	com/baidu/location/c/a:d	D
    //   48: dstore 10
    //   50: aload_0
    //   51: getfield 181	com/baidu/location/c/a:e	D
    //   54: dstore 12
    //   56: aload_0
    //   57: getfield 183	com/baidu/location/c/a:c	I
    //   60: istore 18
    //   62: iload 19
    //   64: ifeq +707 -> 771
    //   67: new 185	com/baidu/location/BDLocation
    //   70: dup
    //   71: invokespecial 186	com/baidu/location/BDLocation:<init>	()V
    //   74: astore_1
    //   75: aload_1
    //   76: iload 18
    //   78: i2f
    //   79: invokevirtual 190	com/baidu/location/BDLocation:setRadius	(F)V
    //   82: aload_1
    //   83: dload 12
    //   85: invokevirtual 194	com/baidu/location/BDLocation:setLatitude	(D)V
    //   88: aload_1
    //   89: dload 10
    //   91: invokevirtual 197	com/baidu/location/BDLocation:setLongitude	(D)V
    //   94: aload_1
    //   95: ldc 199
    //   97: invokevirtual 202	com/baidu/location/BDLocation:setNetworkLocationType	(Ljava/lang/String;)V
    //   100: aload_1
    //   101: bipush 66
    //   103: invokevirtual 206	com/baidu/location/BDLocation:setLocType	(I)V
    //   106: aload_1
    //   107: areturn
    //   108: aconst_null
    //   109: astore 23
    //   111: getstatic 212	java/util/Locale:US	Ljava/util/Locale;
    //   114: ldc 214
    //   116: iconst_3
    //   117: anewarray 4	java/lang/Object
    //   120: dup
    //   121: iconst_0
    //   122: aload_1
    //   123: aastore
    //   124: dup
    //   125: iconst_1
    //   126: ldc 215
    //   128: invokestatic 221	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   131: aastore
    //   132: dup
    //   133: iconst_2
    //   134: invokestatic 227	java/lang/System:currentTimeMillis	()J
    //   137: ldc2_w 228
    //   140: ldiv
    //   141: invokestatic 232	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   144: aastore
    //   145: invokestatic 238	java/lang/String:format	(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   148: astore 22
    //   150: iload 21
    //   152: istore 16
    //   154: dload 10
    //   156: dstore_2
    //   157: dload 12
    //   159: dstore 4
    //   161: iload 19
    //   163: istore 15
    //   165: aload_0
    //   166: getfield 105	com/baidu/location/c/a:i	Landroid/database/sqlite/SQLiteDatabase;
    //   169: aload 22
    //   171: aconst_null
    //   172: invokevirtual 242	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   175: astore 22
    //   177: iload 20
    //   179: istore 17
    //   181: dload 10
    //   183: dstore 8
    //   185: dload 12
    //   187: dstore 6
    //   189: iload 18
    //   191: istore 14
    //   193: aload 22
    //   195: ifnull +406 -> 601
    //   198: iload 20
    //   200: istore 17
    //   202: dload 10
    //   204: dstore 8
    //   206: dload 12
    //   208: dstore 6
    //   210: iload 18
    //   212: istore 14
    //   214: aload 22
    //   216: astore 23
    //   218: iload 21
    //   220: istore 16
    //   222: dload 10
    //   224: dstore_2
    //   225: dload 12
    //   227: dstore 4
    //   229: iload 19
    //   231: istore 15
    //   233: aload 22
    //   235: invokeinterface 247 1 0
    //   240: ifeq +361 -> 601
    //   243: iload 20
    //   245: istore 17
    //   247: dload 10
    //   249: dstore 8
    //   251: dload 12
    //   253: dstore 6
    //   255: iload 18
    //   257: istore 14
    //   259: aload 22
    //   261: astore 23
    //   263: iload 21
    //   265: istore 16
    //   267: dload 10
    //   269: dstore_2
    //   270: dload 12
    //   272: dstore 4
    //   274: iload 19
    //   276: istore 15
    //   278: aload 22
    //   280: aload 22
    //   282: ldc 199
    //   284: invokeinterface 251 2 0
    //   289: invokeinterface 255 2 0
    //   294: dconst_0
    //   295: dcmpl
    //   296: ifle +305 -> 601
    //   299: iconst_1
    //   300: istore 20
    //   302: iconst_1
    //   303: istore 18
    //   305: aload 22
    //   307: astore 23
    //   309: iload 20
    //   311: istore 16
    //   313: dload 10
    //   315: dstore_2
    //   316: dload 12
    //   318: dstore 4
    //   320: iload 19
    //   322: istore 15
    //   324: aload 22
    //   326: aload 22
    //   328: ldc_w 257
    //   331: invokeinterface 251 2 0
    //   336: invokeinterface 255 2 0
    //   341: dstore 6
    //   343: aload 22
    //   345: astore 23
    //   347: iload 20
    //   349: istore 16
    //   351: dload 10
    //   353: dstore_2
    //   354: dload 6
    //   356: dstore 4
    //   358: iload 19
    //   360: istore 15
    //   362: aload 22
    //   364: aload 22
    //   366: ldc_w 259
    //   369: invokeinterface 251 2 0
    //   374: invokeinterface 255 2 0
    //   379: dstore 8
    //   381: aload 22
    //   383: astore 23
    //   385: iload 20
    //   387: istore 16
    //   389: dload 8
    //   391: dstore_2
    //   392: dload 6
    //   394: dstore 4
    //   396: iload 19
    //   398: istore 15
    //   400: aload 22
    //   402: aload 22
    //   404: ldc_w 261
    //   407: invokeinterface 251 2 0
    //   412: invokeinterface 265 2 0
    //   417: istore 17
    //   419: aload 22
    //   421: astore 23
    //   423: iload 20
    //   425: istore 16
    //   427: dload 8
    //   429: dstore_2
    //   430: dload 6
    //   432: dstore 4
    //   434: iload 17
    //   436: istore 15
    //   438: aload 22
    //   440: aload 22
    //   442: ldc_w 267
    //   445: invokeinterface 251 2 0
    //   450: invokeinterface 265 2 0
    //   455: ldc_w 268
    //   458: iadd
    //   459: i2l
    //   460: invokestatic 227	java/lang/System:currentTimeMillis	()J
    //   463: ldc2_w 228
    //   466: ldiv
    //   467: lcmp
    //   468: ifge +314 -> 782
    //   471: aload 22
    //   473: astore 23
    //   475: iload 20
    //   477: istore 16
    //   479: dload 8
    //   481: dstore_2
    //   482: dload 6
    //   484: dstore 4
    //   486: iload 17
    //   488: istore 15
    //   490: aload_0
    //   491: iconst_1
    //   492: putfield 50	com/baidu/location/c/a:q	Z
    //   495: goto +287 -> 782
    //   498: aload 22
    //   500: astore 23
    //   502: iload 20
    //   504: istore 16
    //   506: dload 8
    //   508: dstore_2
    //   509: dload 6
    //   511: dstore 4
    //   513: iload 14
    //   515: istore 15
    //   517: aload_0
    //   518: dload 6
    //   520: putfield 179	com/baidu/location/c/a:d	D
    //   523: aload 22
    //   525: astore 23
    //   527: iload 20
    //   529: istore 16
    //   531: dload 8
    //   533: dstore_2
    //   534: dload 6
    //   536: dstore 4
    //   538: iload 14
    //   540: istore 15
    //   542: aload_0
    //   543: dload 8
    //   545: putfield 181	com/baidu/location/c/a:e	D
    //   548: aload 22
    //   550: astore 23
    //   552: iload 20
    //   554: istore 16
    //   556: dload 8
    //   558: dstore_2
    //   559: dload 6
    //   561: dstore 4
    //   563: iload 14
    //   565: istore 15
    //   567: aload_0
    //   568: iload 14
    //   570: putfield 183	com/baidu/location/c/a:c	I
    //   573: aload 22
    //   575: astore 23
    //   577: iload 20
    //   579: istore 16
    //   581: dload 8
    //   583: dstore_2
    //   584: dload 6
    //   586: dstore 4
    //   588: iload 14
    //   590: istore 15
    //   592: aload_0
    //   593: aload_1
    //   594: putfield 171	com/baidu/location/c/a:f	Ljava/lang/Long;
    //   597: iload 18
    //   599: istore 17
    //   601: iload 17
    //   603: istore 19
    //   605: dload 8
    //   607: dstore 12
    //   609: dload 6
    //   611: dstore 10
    //   613: iload 14
    //   615: istore 18
    //   617: aload 22
    //   619: ifnull -557 -> 62
    //   622: aload 22
    //   624: invokeinterface 271 1 0
    //   629: iload 17
    //   631: istore 19
    //   633: dload 8
    //   635: dstore 12
    //   637: dload 6
    //   639: dstore 10
    //   641: iload 14
    //   643: istore 18
    //   645: goto -583 -> 62
    //   648: astore_1
    //   649: iload 17
    //   651: istore 19
    //   653: dload 8
    //   655: dstore 12
    //   657: dload 6
    //   659: dstore 10
    //   661: iload 14
    //   663: istore 18
    //   665: goto -603 -> 62
    //   668: iload 17
    //   670: istore 14
    //   672: sipush 2000
    //   675: iload 17
    //   677: if_icmpge -179 -> 498
    //   680: sipush 2000
    //   683: istore 14
    //   685: goto -187 -> 498
    //   688: astore_1
    //   689: iload 16
    //   691: istore 19
    //   693: dload_2
    //   694: dstore 12
    //   696: dload 4
    //   698: dstore 10
    //   700: iload 15
    //   702: istore 18
    //   704: aload 23
    //   706: ifnull -644 -> 62
    //   709: aload 23
    //   711: invokeinterface 271 1 0
    //   716: iload 16
    //   718: istore 19
    //   720: dload_2
    //   721: dstore 12
    //   723: dload 4
    //   725: dstore 10
    //   727: iload 15
    //   729: istore 18
    //   731: goto -669 -> 62
    //   734: astore_1
    //   735: iload 16
    //   737: istore 19
    //   739: dload_2
    //   740: dstore 12
    //   742: dload 4
    //   744: dstore 10
    //   746: iload 15
    //   748: istore 18
    //   750: goto -688 -> 62
    //   753: astore_1
    //   754: aconst_null
    //   755: astore 22
    //   757: aload 22
    //   759: ifnull +10 -> 769
    //   762: aload 22
    //   764: invokeinterface 271 1 0
    //   769: aload_1
    //   770: athrow
    //   771: aconst_null
    //   772: areturn
    //   773: astore 22
    //   775: goto -6 -> 769
    //   778: astore_1
    //   779: goto -22 -> 757
    //   782: iload 17
    //   784: sipush 300
    //   787: if_icmpge -119 -> 668
    //   790: sipush 300
    //   793: istore 14
    //   795: goto -297 -> 498
    //
    // Exception table:
    //   from	to	target	type
    //   622	629	648	java/lang/Exception
    //   165	177	688	java/lang/Exception
    //   233	243	688	java/lang/Exception
    //   278	299	688	java/lang/Exception
    //   324	343	688	java/lang/Exception
    //   362	381	688	java/lang/Exception
    //   400	419	688	java/lang/Exception
    //   438	471	688	java/lang/Exception
    //   490	495	688	java/lang/Exception
    //   517	523	688	java/lang/Exception
    //   542	548	688	java/lang/Exception
    //   567	573	688	java/lang/Exception
    //   592	597	688	java/lang/Exception
    //   709	716	734	java/lang/Exception
    //   165	177	753	finally
    //   762	769	773	java/lang/Exception
    //   233	243	778	finally
    //   278	299	778	finally
    //   324	343	778	finally
    //   362	381	778	finally
    //   400	419	778	finally
    //   438	471	778	finally
    //   490	495	778	finally
    //   517	523	778	finally
    //   542	548	778	finally
    //   567	573	778	finally
    //   592	597	778	finally
  }

  // ERROR //
  private BDLocation a(LinkedHashMap paramLinkedHashMap, BDLocation paramBDLocation, int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 62	com/baidu/location/c/a:p	Ljava/lang/StringBuffer;
    //   4: iconst_0
    //   5: invokevirtual 275	java/lang/StringBuffer:setLength	(I)V
    //   8: dconst_0
    //   9: dstore 10
    //   11: dconst_0
    //   12: dstore 8
    //   14: aload_2
    //   15: ifnull +1376 -> 1391
    //   18: aload_2
    //   19: invokevirtual 279	com/baidu/location/BDLocation:getLatitude	()D
    //   22: dstore 8
    //   24: aload_2
    //   25: invokevirtual 282	com/baidu/location/BDLocation:getLongitude	()D
    //   28: dstore 10
    //   30: iconst_1
    //   31: istore 29
    //   33: new 59	java/lang/StringBuffer
    //   36: dup
    //   37: invokespecial 60	java/lang/StringBuffer:<init>	()V
    //   40: astore 31
    //   42: aload_1
    //   43: invokevirtual 288	java/util/LinkedHashMap:entrySet	()Ljava/util/Set;
    //   46: invokeinterface 294 1 0
    //   51: astore 32
    //   53: iconst_0
    //   54: istore 26
    //   56: iconst_1
    //   57: istore 25
    //   59: iload 26
    //   61: aload_1
    //   62: invokevirtual 295	java/util/LinkedHashMap:size	()I
    //   65: bipush 30
    //   67: invokestatic 299	java/lang/Math:min	(II)I
    //   70: if_icmpge +131 -> 201
    //   73: aload 32
    //   75: invokeinterface 305 1 0
    //   80: checkcast 307	java/util/Map$Entry
    //   83: astore_2
    //   84: aload_2
    //   85: invokeinterface 310 1 0
    //   90: checkcast 234	java/lang/String
    //   93: astore 33
    //   95: aload_2
    //   96: invokeinterface 313 1 0
    //   101: checkcast 217	java/lang/Integer
    //   104: astore 30
    //   106: aload 30
    //   108: astore_2
    //   109: aload 30
    //   111: invokevirtual 316	java/lang/Integer:intValue	()I
    //   114: ifge +13 -> 127
    //   117: aload 30
    //   119: invokevirtual 316	java/lang/Integer:intValue	()I
    //   122: ineg
    //   123: invokestatic 221	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   126: astore_2
    //   127: aload 33
    //   129: invokestatic 321	com/baidu/location/Jni:I	(Ljava/lang/String;)Ljava/lang/Long;
    //   132: astore 30
    //   134: aload 30
    //   136: ifnonnull +12 -> 148
    //   139: iload 26
    //   141: iconst_1
    //   142: iadd
    //   143: istore 26
    //   145: goto -86 -> 59
    //   148: aload_0
    //   149: getfield 78	com/baidu/location/c/a:o	Ljava/util/concurrent/ConcurrentHashMap;
    //   152: aload 30
    //   154: aload 33
    //   156: invokevirtual 325	java/util/concurrent/ConcurrentHashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   159: pop
    //   160: iload 25
    //   162: ifeq +28 -> 190
    //   165: iconst_0
    //   166: istore 25
    //   168: aload_0
    //   169: getfield 76	com/baidu/location/c/a:n	Ljava/util/concurrent/ConcurrentHashMap;
    //   172: aload 30
    //   174: aload_2
    //   175: invokevirtual 325	java/util/concurrent/ConcurrentHashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   178: pop
    //   179: aload 31
    //   181: aload 30
    //   183: invokevirtual 329	java/lang/StringBuffer:append	(Ljava/lang/Object;)Ljava/lang/StringBuffer;
    //   186: pop
    //   187: goto -48 -> 139
    //   190: aload 31
    //   192: bipush 44
    //   194: invokevirtual 332	java/lang/StringBuffer:append	(C)Ljava/lang/StringBuffer;
    //   197: pop
    //   198: goto -30 -> 168
    //   201: getstatic 212	java/util/Locale:US	Ljava/util/Locale;
    //   204: ldc_w 334
    //   207: iconst_3
    //   208: anewarray 4	java/lang/Object
    //   211: dup
    //   212: iconst_0
    //   213: aload 31
    //   215: aastore
    //   216: dup
    //   217: iconst_1
    //   218: ldc_w 335
    //   221: invokestatic 221	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   224: aastore
    //   225: dup
    //   226: iconst_2
    //   227: invokestatic 227	java/lang/System:currentTimeMillis	()J
    //   230: ldc2_w 228
    //   233: ldiv
    //   234: invokestatic 232	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   237: aastore
    //   238: invokestatic 238	java/lang/String:format	(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   241: astore 30
    //   243: aconst_null
    //   244: astore_2
    //   245: aload_0
    //   246: getfield 105	com/baidu/location/c/a:i	Landroid/database/sqlite/SQLiteDatabase;
    //   249: aload 30
    //   251: aconst_null
    //   252: invokevirtual 242	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   255: astore 30
    //   257: aload 30
    //   259: astore_2
    //   260: aload_2
    //   261: ifnull +1116 -> 1377
    //   264: aload_2
    //   265: invokeinterface 247 1 0
    //   270: ifeq +1107 -> 1377
    //   273: new 149	java/util/ArrayList
    //   276: dup
    //   277: invokespecial 336	java/util/ArrayList:<init>	()V
    //   280: astore 30
    //   282: aload_2
    //   283: invokeinterface 339 1 0
    //   288: ifne +477 -> 765
    //   291: aload_2
    //   292: iconst_0
    //   293: invokeinterface 343 2 0
    //   298: invokestatic 232	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   301: astore 31
    //   303: aload_2
    //   304: iconst_1
    //   305: invokeinterface 255 2 0
    //   310: dstore 6
    //   312: aload_2
    //   313: iconst_2
    //   314: invokeinterface 255 2 0
    //   319: dstore 12
    //   321: aload_2
    //   322: iconst_3
    //   323: invokeinterface 265 2 0
    //   328: istore 25
    //   330: aload_2
    //   331: iconst_4
    //   332: invokeinterface 255 2 0
    //   337: dstore 4
    //   339: aload_2
    //   340: iconst_5
    //   341: invokeinterface 265 2 0
    //   346: istore 26
    //   348: aload_0
    //   349: getfield 71	com/baidu/location/c/a:m	Ljava/util/HashSet;
    //   352: aload 31
    //   354: invokevirtual 346	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   357: pop
    //   358: iload 26
    //   360: ldc_w 268
    //   363: iadd
    //   364: i2l
    //   365: invokestatic 227	java/lang/System:currentTimeMillis	()J
    //   368: ldc2_w 228
    //   371: ldiv
    //   372: lcmp
    //   373: ifge +71 -> 444
    //   376: aload_0
    //   377: getfield 62	com/baidu/location/c/a:p	Ljava/lang/StringBuffer;
    //   380: invokevirtual 349	java/lang/StringBuffer:length	()I
    //   383: ifle +14 -> 397
    //   386: aload_0
    //   387: getfield 62	com/baidu/location/c/a:p	Ljava/lang/StringBuffer;
    //   390: ldc_w 351
    //   393: invokevirtual 354	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   396: pop
    //   397: aload_0
    //   398: getfield 62	com/baidu/location/c/a:p	Ljava/lang/StringBuffer;
    //   401: getstatic 212	java/util/Locale:US	Ljava/util/Locale;
    //   404: ldc_w 356
    //   407: iconst_3
    //   408: anewarray 4	java/lang/Object
    //   411: dup
    //   412: iconst_0
    //   413: aload 31
    //   415: aastore
    //   416: dup
    //   417: iconst_1
    //   418: aload_0
    //   419: getfield 78	com/baidu/location/c/a:o	Ljava/util/concurrent/ConcurrentHashMap;
    //   422: aload 31
    //   424: invokevirtual 359	java/util/concurrent/ConcurrentHashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   427: aastore
    //   428: dup
    //   429: iconst_2
    //   430: ldc_w 360
    //   433: invokestatic 221	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   436: aastore
    //   437: invokestatic 238	java/lang/String:format	(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   440: invokevirtual 354	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   443: pop
    //   444: dload 4
    //   446: dconst_0
    //   447: dcmpg
    //   448: ifgt +114 -> 562
    //   451: aload_2
    //   452: invokeinterface 363 1 0
    //   457: pop
    //   458: goto -176 -> 282
    //   461: astore_1
    //   462: aload_2
    //   463: astore_1
    //   464: iconst_0
    //   465: istore_3
    //   466: dconst_0
    //   467: dstore 6
    //   469: dconst_0
    //   470: dstore 4
    //   472: iconst_0
    //   473: istore 25
    //   475: iload_3
    //   476: istore 26
    //   478: iload 25
    //   480: istore 27
    //   482: dload 4
    //   484: dstore 8
    //   486: dload 6
    //   488: dstore 10
    //   490: aload_1
    //   491: ifnull +24 -> 515
    //   494: aload_1
    //   495: invokeinterface 271 1 0
    //   500: dload 6
    //   502: dstore 10
    //   504: dload 4
    //   506: dstore 8
    //   508: iload 25
    //   510: istore 27
    //   512: iload_3
    //   513: istore 26
    //   515: iload 26
    //   517: ifeq +740 -> 1257
    //   520: new 185	com/baidu/location/BDLocation
    //   523: dup
    //   524: invokespecial 186	com/baidu/location/BDLocation:<init>	()V
    //   527: astore_1
    //   528: aload_1
    //   529: iload 27
    //   531: i2f
    //   532: invokevirtual 190	com/baidu/location/BDLocation:setRadius	(F)V
    //   535: aload_1
    //   536: dload 10
    //   538: invokevirtual 194	com/baidu/location/BDLocation:setLatitude	(D)V
    //   541: aload_1
    //   542: dload 8
    //   544: invokevirtual 197	com/baidu/location/BDLocation:setLongitude	(D)V
    //   547: aload_1
    //   548: ldc_w 365
    //   551: invokevirtual 202	com/baidu/location/BDLocation:setNetworkLocationType	(Ljava/lang/String;)V
    //   554: aload_1
    //   555: bipush 66
    //   557: invokevirtual 206	com/baidu/location/BDLocation:setLocType	(I)V
    //   560: aload_1
    //   561: areturn
    //   562: dload 6
    //   564: dconst_0
    //   565: dcmpg
    //   566: ifle +23 -> 589
    //   569: dload 12
    //   571: dconst_0
    //   572: dcmpg
    //   573: ifle +16 -> 589
    //   576: iload 25
    //   578: ifle +11 -> 589
    //   581: iload 25
    //   583: sipush 1000
    //   586: if_icmplt +26 -> 612
    //   589: aload_2
    //   590: invokeinterface 363 1 0
    //   595: pop
    //   596: goto -314 -> 282
    //   599: astore_1
    //   600: aload_2
    //   601: ifnull +9 -> 610
    //   604: aload_2
    //   605: invokeinterface 271 1 0
    //   610: aload_1
    //   611: athrow
    //   612: iload 29
    //   614: iconst_1
    //   615: if_icmpne +32 -> 647
    //   618: aload_0
    //   619: dload 8
    //   621: dload 10
    //   623: dload 12
    //   625: dload 6
    //   627: invokespecial 165	com/baidu/location/c/a:a	(DDDD)D
    //   630: ldc2_w 366
    //   633: dcmpl
    //   634: ifle +13 -> 647
    //   637: aload_2
    //   638: invokeinterface 363 1 0
    //   643: pop
    //   644: goto -362 -> 282
    //   647: bipush 100
    //   649: bipush 30
    //   651: aload_0
    //   652: getfield 76	com/baidu/location/c/a:n	Ljava/util/concurrent/ConcurrentHashMap;
    //   655: aload 31
    //   657: invokevirtual 359	java/util/concurrent/ConcurrentHashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   660: checkcast 217	java/lang/Integer
    //   663: invokevirtual 316	java/lang/Integer:intValue	()I
    //   666: invokestatic 370	java/lang/Math:max	(II)I
    //   669: invokestatic 299	java/lang/Math:min	(II)I
    //   672: istore 26
    //   674: iload 26
    //   676: bipush 70
    //   678: if_icmple +70 -> 748
    //   681: iload 26
    //   683: bipush 70
    //   685: isub
    //   686: i2d
    //   687: ldc2_w 371
    //   690: ddiv
    //   691: dconst_1
    //   692: dadd
    //   693: dstore 4
    //   695: aload 30
    //   697: new 14	com/baidu/location/c/a$c
    //   700: dup
    //   701: dload 6
    //   703: dload 12
    //   705: dload 4
    //   707: ldc2_w 373
    //   710: iload 25
    //   712: i2d
    //   713: invokestatic 376	java/lang/Math:max	(DD)D
    //   716: ldc2_w 377
    //   719: invokestatic 381	java/lang/Math:pow	(DD)D
    //   722: ldc2_w 382
    //   725: dmul
    //   726: dmul
    //   727: invokestatic 386	java/lang/Math:exp	(D)D
    //   730: aconst_null
    //   731: invokespecial 389	com/baidu/location/c/a$c:<init>	(DDDLcom/baidu/location/c/c;)V
    //   734: invokevirtual 390	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   737: pop
    //   738: aload_2
    //   739: invokeinterface 363 1 0
    //   744: pop
    //   745: goto -463 -> 282
    //   748: iload 26
    //   750: bipush 70
    //   752: isub
    //   753: i2d
    //   754: ldc2_w 373
    //   757: ddiv
    //   758: dconst_1
    //   759: dadd
    //   760: dstore 4
    //   762: goto -67 -> 695
    //   765: aload_0
    //   766: aload 30
    //   768: ldc2_w 391
    //   771: invokespecial 394	com/baidu/location/c/a:a	(Ljava/util/ArrayList;D)I
    //   774: pop
    //   775: dconst_0
    //   776: dstore 12
    //   778: dconst_0
    //   779: dstore 4
    //   781: dconst_0
    //   782: dstore 6
    //   784: iconst_0
    //   785: istore 25
    //   787: iload 25
    //   789: aload 30
    //   791: invokevirtual 153	java/util/ArrayList:size	()I
    //   794: if_icmpge +113 -> 907
    //   797: aload 30
    //   799: iload 25
    //   801: invokevirtual 157	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   804: checkcast 14	com/baidu/location/c/a$c
    //   807: astore 31
    //   809: aload 31
    //   811: getfield 396	com/baidu/location/c/a$c:c	D
    //   814: dconst_0
    //   815: dcmpg
    //   816: ifgt +18 -> 834
    //   819: dload 4
    //   821: dstore 14
    //   823: dload 6
    //   825: dstore 4
    //   827: dload 14
    //   829: dstore 6
    //   831: goto +566 -> 1397
    //   834: aload 31
    //   836: getfield 159	com/baidu/location/c/a$c:a	D
    //   839: dstore 16
    //   841: aload 31
    //   843: getfield 396	com/baidu/location/c/a$c:c	D
    //   846: dstore 18
    //   848: aload 31
    //   850: getfield 161	com/baidu/location/c/a$c:b	D
    //   853: dstore 14
    //   855: aload 31
    //   857: getfield 396	com/baidu/location/c/a$c:c	D
    //   860: dstore 20
    //   862: aload 31
    //   864: getfield 396	com/baidu/location/c/a$c:c	D
    //   867: dstore 22
    //   869: dload 22
    //   871: dload 6
    //   873: dadd
    //   874: dstore 6
    //   876: dload 14
    //   878: dload 20
    //   880: dmul
    //   881: dload 4
    //   883: dadd
    //   884: dstore 14
    //   886: dload 16
    //   888: dload 18
    //   890: dmul
    //   891: dload 12
    //   893: dadd
    //   894: dstore 12
    //   896: dload 6
    //   898: dstore 4
    //   900: dload 14
    //   902: dstore 6
    //   904: goto +493 -> 1397
    //   907: dload 6
    //   909: dconst_0
    //   910: dcmpl
    //   911: ifle +451 -> 1362
    //   914: dload 12
    //   916: dconst_0
    //   917: dcmpl
    //   918: ifle +444 -> 1362
    //   921: dload 4
    //   923: dconst_0
    //   924: dcmpl
    //   925: ifle +437 -> 1362
    //   928: dload 12
    //   930: dload 6
    //   932: ddiv
    //   933: dstore 12
    //   935: dload 4
    //   937: dload 6
    //   939: ddiv
    //   940: dstore 6
    //   942: iconst_1
    //   943: istore 26
    //   945: fconst_0
    //   946: fstore 24
    //   948: iconst_0
    //   949: istore 25
    //   951: iload 25
    //   953: aload 30
    //   955: invokevirtual 153	java/util/ArrayList:size	()I
    //   958: if_icmpge +57 -> 1015
    //   961: fload 24
    //   963: f2d
    //   964: dstore 4
    //   966: aload_0
    //   967: dload 12
    //   969: dload 6
    //   971: aload 30
    //   973: iload 25
    //   975: invokevirtual 157	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   978: checkcast 14	com/baidu/location/c/a$c
    //   981: getfield 159	com/baidu/location/c/a$c:a	D
    //   984: aload 30
    //   986: iload 25
    //   988: invokevirtual 157	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   991: checkcast 14	com/baidu/location/c/a$c
    //   994: getfield 161	com/baidu/location/c/a$c:b	D
    //   997: invokespecial 165	com/baidu/location/c/a:a	(DDDD)D
    //   1000: dload 4
    //   1002: dadd
    //   1003: d2f
    //   1004: fstore 24
    //   1006: iload 25
    //   1008: iconst_1
    //   1009: iadd
    //   1010: istore 25
    //   1012: goto -61 -> 951
    //   1015: fload 24
    //   1017: aload 30
    //   1019: invokevirtual 153	java/util/ArrayList:size	()I
    //   1022: i2f
    //   1023: fdiv
    //   1024: invokestatic 400	java/lang/Math:round	(F)I
    //   1027: istore 25
    //   1029: iload 25
    //   1031: bipush 30
    //   1033: if_icmpge +199 -> 1232
    //   1036: iconst_1
    //   1037: istore 26
    //   1039: dload 6
    //   1041: dstore 4
    //   1043: bipush 30
    //   1045: istore 25
    //   1047: dload 12
    //   1049: dstore 6
    //   1051: iload 26
    //   1053: istore 27
    //   1055: iload 29
    //   1057: ifne +23 -> 1080
    //   1060: iload 26
    //   1062: istore 27
    //   1064: iload 26
    //   1066: istore 28
    //   1068: aload 30
    //   1070: invokevirtual 153	java/util/ArrayList:size	()I
    //   1073: iconst_1
    //   1074: if_icmpgt +6 -> 1080
    //   1077: iconst_0
    //   1078: istore 27
    //   1080: iload 27
    //   1082: istore 26
    //   1084: iload 27
    //   1086: istore 28
    //   1088: aload 30
    //   1090: invokevirtual 153	java/util/ArrayList:size	()I
    //   1093: iload_3
    //   1094: if_icmpge +35 -> 1129
    //   1097: iload 27
    //   1099: istore 26
    //   1101: iload 27
    //   1103: istore 28
    //   1105: dconst_1
    //   1106: aload 30
    //   1108: invokevirtual 153	java/util/ArrayList:size	()I
    //   1111: i2d
    //   1112: dmul
    //   1113: aload_1
    //   1114: invokevirtual 295	java/util/LinkedHashMap:size	()I
    //   1117: i2d
    //   1118: ddiv
    //   1119: ldc2_w 401
    //   1122: dcmpg
    //   1123: ifge +6 -> 1129
    //   1126: iconst_0
    //   1127: istore 26
    //   1129: iload 29
    //   1131: iconst_1
    //   1132: if_icmpne +210 -> 1342
    //   1135: iload 26
    //   1137: iconst_1
    //   1138: if_icmpne +204 -> 1342
    //   1141: iload 26
    //   1143: istore 28
    //   1145: aload_0
    //   1146: dload 8
    //   1148: dload 10
    //   1150: dload 4
    //   1152: dload 6
    //   1154: invokespecial 165	com/baidu/location/c/a:a	(DDDD)D
    //   1157: dstore 8
    //   1159: dload 8
    //   1161: ldc2_w 366
    //   1164: dcmpl
    //   1165: ifle +177 -> 1342
    //   1168: iconst_0
    //   1169: istore_3
    //   1170: iload_3
    //   1171: istore 26
    //   1173: iload 25
    //   1175: istore 27
    //   1177: dload 6
    //   1179: dstore 8
    //   1181: dload 4
    //   1183: dstore 10
    //   1185: aload_2
    //   1186: ifnull -671 -> 515
    //   1189: aload_2
    //   1190: invokeinterface 271 1 0
    //   1195: iload_3
    //   1196: istore 26
    //   1198: iload 25
    //   1200: istore 27
    //   1202: dload 6
    //   1204: dstore 8
    //   1206: dload 4
    //   1208: dstore 10
    //   1210: goto -695 -> 515
    //   1213: astore_1
    //   1214: iload_3
    //   1215: istore 26
    //   1217: iload 25
    //   1219: istore 27
    //   1221: dload 6
    //   1223: dstore 8
    //   1225: dload 4
    //   1227: dstore 10
    //   1229: goto -714 -> 515
    //   1232: bipush 100
    //   1234: iload 25
    //   1236: if_icmpge +112 -> 1348
    //   1239: iconst_1
    //   1240: istore 26
    //   1242: dload 6
    //   1244: dstore 4
    //   1246: bipush 100
    //   1248: istore 25
    //   1250: dload 12
    //   1252: dstore 6
    //   1254: goto -203 -> 1051
    //   1257: aconst_null
    //   1258: areturn
    //   1259: astore_1
    //   1260: iload_3
    //   1261: istore 26
    //   1263: iload 25
    //   1265: istore 27
    //   1267: dload 4
    //   1269: dstore 8
    //   1271: dload 6
    //   1273: dstore 10
    //   1275: goto -760 -> 515
    //   1278: astore_2
    //   1279: goto -669 -> 610
    //   1282: astore_1
    //   1283: aconst_null
    //   1284: astore_2
    //   1285: goto -685 -> 600
    //   1288: astore_1
    //   1289: iconst_0
    //   1290: istore_3
    //   1291: dconst_0
    //   1292: dstore 6
    //   1294: dconst_0
    //   1295: dstore 4
    //   1297: iconst_0
    //   1298: istore 25
    //   1300: aload_2
    //   1301: astore_1
    //   1302: goto -827 -> 475
    //   1305: astore_1
    //   1306: aload_2
    //   1307: astore_1
    //   1308: iconst_0
    //   1309: istore 25
    //   1311: iload 26
    //   1313: istore_3
    //   1314: dload 12
    //   1316: dstore 4
    //   1318: goto -843 -> 475
    //   1321: astore_1
    //   1322: iload 28
    //   1324: istore_3
    //   1325: dload 4
    //   1327: dstore 8
    //   1329: aload_2
    //   1330: astore_1
    //   1331: dload 6
    //   1333: dstore 4
    //   1335: dload 8
    //   1337: dstore 6
    //   1339: goto -864 -> 475
    //   1342: iload 26
    //   1344: istore_3
    //   1345: goto -175 -> 1170
    //   1348: iconst_1
    //   1349: istore 26
    //   1351: dload 6
    //   1353: dstore 4
    //   1355: dload 12
    //   1357: dstore 6
    //   1359: goto -308 -> 1051
    //   1362: iconst_0
    //   1363: istore 26
    //   1365: dconst_0
    //   1366: dstore 4
    //   1368: dconst_0
    //   1369: dstore 6
    //   1371: iconst_0
    //   1372: istore 25
    //   1374: goto -323 -> 1051
    //   1377: iconst_0
    //   1378: istore_3
    //   1379: dconst_0
    //   1380: dstore 4
    //   1382: dconst_0
    //   1383: dstore 6
    //   1385: iconst_0
    //   1386: istore 25
    //   1388: goto -218 -> 1170
    //   1391: iconst_0
    //   1392: istore 29
    //   1394: goto -1361 -> 33
    //   1397: iload 25
    //   1399: iconst_1
    //   1400: iadd
    //   1401: istore 25
    //   1403: dload 6
    //   1405: dstore 14
    //   1407: dload 4
    //   1409: dstore 6
    //   1411: dload 14
    //   1413: dstore 4
    //   1415: goto -628 -> 787
    //
    // Exception table:
    //   from	to	target	type
    //   264	282	461	java/lang/Exception
    //   282	397	461	java/lang/Exception
    //   397	444	461	java/lang/Exception
    //   451	458	461	java/lang/Exception
    //   589	596	461	java/lang/Exception
    //   618	644	461	java/lang/Exception
    //   647	674	461	java/lang/Exception
    //   681	695	461	java/lang/Exception
    //   695	745	461	java/lang/Exception
    //   748	762	461	java/lang/Exception
    //   765	775	461	java/lang/Exception
    //   787	819	461	java/lang/Exception
    //   834	869	461	java/lang/Exception
    //   264	282	599	finally
    //   282	397	599	finally
    //   397	444	599	finally
    //   451	458	599	finally
    //   589	596	599	finally
    //   618	644	599	finally
    //   647	674	599	finally
    //   681	695	599	finally
    //   695	745	599	finally
    //   748	762	599	finally
    //   765	775	599	finally
    //   787	819	599	finally
    //   834	869	599	finally
    //   951	961	599	finally
    //   966	1006	599	finally
    //   1015	1029	599	finally
    //   1068	1077	599	finally
    //   1088	1097	599	finally
    //   1105	1126	599	finally
    //   1145	1159	599	finally
    //   1189	1195	1213	java/lang/Exception
    //   494	500	1259	java/lang/Exception
    //   604	610	1278	java/lang/Exception
    //   245	257	1282	finally
    //   245	257	1288	java/lang/Exception
    //   951	961	1305	java/lang/Exception
    //   966	1006	1305	java/lang/Exception
    //   1015	1029	1305	java/lang/Exception
    //   1068	1077	1321	java/lang/Exception
    //   1088	1097	1321	java/lang/Exception
    //   1105	1126	1321	java/lang/Exception
    //   1145	1159	1321	java/lang/Exception
  }

  private void a(BDLocation paramBDLocation1, BDLocation paramBDLocation2, BDLocation paramBDLocation3, String paramString, Long paramLong)
  {
    if ((paramBDLocation1 != null) && (paramBDLocation1.getLocType() == 161))
      if ((paramBDLocation2 != null) && (paramBDLocation1.getNetworkLocationType().equals("cl")) && (a(paramBDLocation2.getLatitude(), paramBDLocation2.getLongitude(), paramBDLocation1.getLatitude(), paramBDLocation1.getLongitude()) > 300.0D))
      {
        paramBDLocation2 = String.format(Locale.US, "UPDATE CL SET cl = 0 WHERE id = %d;", new Object[] { paramLong });
        paramString = String.format(Locale.US, "INSERT OR REPLACE INTO CL VALUES (%d,\"%s\",%d);", new Object[] { paramLong, paramString, Integer.valueOf(100000) });
      }
    try
    {
      this.i.execSQL(paramBDLocation2);
      this.j.execSQL(paramString);
      label127: if ((paramBDLocation3 != null) && (paramBDLocation1.getNetworkLocationType().equals("wf")) && (a(paramBDLocation3.getLatitude(), paramBDLocation3.getLongitude(), paramBDLocation1.getLatitude(), paramBDLocation1.getLongitude()) > 100.0D))
      {
        paramBDLocation1 = String.format("UPDATE AP SET cl = 0 WHERE id In (%s);", new Object[] { this.k.toString() });
        paramBDLocation2 = String.format("INSERT OR REPLACE INTO AP VALUES %s;", new Object[] { this.l.toString() });
      }
      try
      {
        this.i.execSQL(paramBDLocation1);
        this.j.execSQL(paramBDLocation2);
        return;
      }
      catch (Exception paramBDLocation1)
      {
      }
    }
    catch (Exception paramBDLocation2)
    {
      break label127;
    }
  }

  private void a(String paramString, Long paramLong, BDLocation paramBDLocation)
  {
    if (paramString != null)
    {
      if (paramBDLocation == null)
        break label81;
      paramBDLocation = String.format(Locale.US, "UPDATE CL SET frequency=frequency+1 WHERE id = %d;", new Object[] { paramLong });
    }
    try
    {
      this.i.execSQL(paramBDLocation);
      while (true)
      {
        label34: if (this.q)
          paramString = String.format(Locale.US, "INSERT OR IGNORE INTO CL VALUES (%d,\"%s\",%d);", new Object[] { paramLong, paramString, Integer.valueOf(100000) });
        try
        {
          this.j.execSQL(paramString);
          return;
          label81: paramBDLocation = String.format(Locale.US, "INSERT OR IGNORE INTO CL VALUES (%d,\"%s\",0);", new Object[] { paramLong, paramString });
          String str = String.format(Locale.US, "UPDATE CL SET frequency=frequency+1 WHERE id = %d;", new Object[] { paramLong });
          try
          {
            this.j.execSQL(paramBDLocation);
            this.j.execSQL(str);
          }
          catch (Exception paramBDLocation)
          {
          }
        }
        catch (Exception paramString)
        {
        }
      }
    }
    catch (Exception paramBDLocation)
    {
      break label34;
    }
  }

  private void a(String paramString1, String paramString2, String paramString3)
  {
    b.a(this.g, paramString1, paramString2, paramString3);
  }

  private void a(LinkedHashMap paramLinkedHashMap)
  {
    StringBuffer localStringBuffer;
    Object localObject1;
    int i1;
    int i2;
    if ((paramLinkedHashMap != null) && (paramLinkedHashMap.size() > 0))
    {
      this.k = new StringBuffer();
      this.l = new StringBuffer();
      paramLinkedHashMap = new StringBuffer();
      localStringBuffer = new StringBuffer();
      localObject1 = this.n.keySet().iterator();
      i1 = 1;
      i2 = 1;
    }
    while (true)
    {
      int i4;
      int i3;
      if (((Iterator)localObject1).hasNext())
      {
        i4 = i1;
        i3 = i2;
      }
      try
      {
        Object localObject2 = (Long)((Iterator)localObject1).next();
        i4 = i1;
        i3 = i2;
        if (this.m.contains(localObject2))
        {
          if (i2 != 0)
            i2 = 0;
          while (true)
          {
            i4 = i1;
            i3 = i2;
            this.k.append(localObject2);
            i4 = i1;
            i3 = i2;
            str = (String)this.o.get(localObject2);
            i4 = i1;
            i3 = i2;
            this.l.append('(').append(localObject2).append(',').append('"').append(str).append('"').append(',').append(100000).append(')');
            break;
            i4 = i1;
            i3 = i2;
            this.k.append(',');
            i4 = i1;
            i3 = i2;
            this.l.append(',');
          }
        }
        i4 = i1;
        i3 = i2;
        String str = (String)this.o.get(localObject2);
        if (i1 != 0)
          i1 = 0;
        while (true)
        {
          i4 = i1;
          i3 = i2;
          paramLinkedHashMap.append(localObject2);
          i4 = i1;
          i3 = i2;
          localStringBuffer.append('(').append(localObject2).append(',').append('"').append(str).append('"').append(",0)");
          break;
          i4 = i1;
          i3 = i2;
          paramLinkedHashMap.append(',');
          i4 = i1;
          i3 = i2;
          localStringBuffer.append(',');
        }
        localObject1 = String.format(Locale.US, "UPDATE AP SET frequency=frequency+1 WHERE id IN(%s)", new Object[] { this.k.toString() });
        try
        {
          this.i.execSQL((String)localObject1);
          label402: if (this.p.length() > 0)
          {
            if (localStringBuffer.length() > 0)
              localStringBuffer.append(",");
            localStringBuffer.append(this.p);
          }
          localObject1 = String.format("INSERT OR IGNORE INTO AP VALUES %s;", new Object[] { localStringBuffer.toString() });
          localObject2 = String.format("UPDATE AP SET frequency=frequency+1 WHERE id in (%s);", new Object[] { paramLinkedHashMap.toString() });
          try
          {
            if (localStringBuffer.length() > 0)
              this.j.execSQL((String)localObject1);
            if (paramLinkedHashMap.length() > 0)
              this.j.execSQL((String)localObject2);
            return;
          }
          catch (Exception paramLinkedHashMap)
          {
            return;
          }
        }
        catch (Exception localException1)
        {
          break label402;
        }
      }
      catch (Exception localException2)
      {
        i1 = i4;
        i2 = i3;
      }
    }
  }

  private void a(String[] paramArrayOfString)
  {
    this.b.jdMethod_new().a(paramArrayOfString);
  }

  Cursor a(h.a parama)
  {
    Object localObject3 = new BDLocation();
    ((BDLocation)localObject3).setLocType(67);
    int i2 = 0;
    int i1;
    Object localObject1;
    Object localObject2;
    Object localObject5;
    Object localObject6;
    Object localObject4;
    Object localObject7;
    int i3;
    if (parama.c)
    {
      String str = parama.b;
      LinkedHashMap localLinkedHashMap = parama.i;
      i1 = parama.f;
      BDLocation localBDLocation = parama.g;
      localObject1 = null;
      localObject2 = Long.valueOf(-9223372036854775808L);
      Object localObject8 = localObject2;
      localObject5 = localObject1;
      if (str != null)
      {
        localObject8 = localObject2;
        localObject5 = localObject1;
        if (this.i != null)
        {
          localObject2 = Jni.I(str);
          localObject8 = localObject2;
          localObject5 = localObject1;
          if (localObject2 != null)
          {
            localObject5 = a((Long)localObject2);
            localObject8 = localObject2;
          }
        }
      }
      localObject1 = null;
      localObject6 = localObject1;
      if (localLinkedHashMap != null)
      {
        localObject6 = localObject1;
        if (localLinkedHashMap.size() > 0)
        {
          localObject6 = localObject1;
          if (this.i != null)
          {
            this.n.clear();
            this.m.clear();
            this.o.clear();
            localObject6 = a(localLinkedHashMap, (BDLocation)localObject5, i1);
          }
        }
      }
      localObject1 = null;
      localObject2 = null;
      localObject4 = null;
      localObject7 = null;
      Object localObject9;
      if (localObject5 != null)
      {
        localObject1 = Double.valueOf(((BDLocation)localObject5).getLongitude());
        localObject2 = Double.valueOf(((BDLocation)localObject5).getLatitude());
        localObject9 = Jni.jdMethod_if(((BDLocation)localObject5).getLongitude(), ((BDLocation)localObject5).getLatitude(), "bd09ll2gcj");
        ((BDLocation)localObject5).setCoorType("gcj");
        ((BDLocation)localObject5).setLatitude(localObject9[1]);
        ((BDLocation)localObject5).setLongitude(localObject9[0]);
        ((BDLocation)localObject5).setNetworkLocationType("cl");
      }
      if (localObject6 != null)
      {
        localObject4 = Double.valueOf(((BDLocation)localObject6).getLongitude());
        localObject7 = Double.valueOf(((BDLocation)localObject6).getLatitude());
        localObject9 = Jni.jdMethod_if(((BDLocation)localObject6).getLongitude(), ((BDLocation)localObject6).getLatitude(), "bd09ll2gcj");
        ((BDLocation)localObject6).setCoorType("gcj");
        ((BDLocation)localObject6).setLatitude(localObject9[1]);
        ((BDLocation)localObject6).setLongitude(localObject9[0]);
        ((BDLocation)localObject6).setNetworkLocationType("wf");
      }
      if ((localObject5 != null) && (localObject6 == null))
      {
        i1 = 1;
        if (parama.f <= 0)
          break label876;
        i3 = 1;
        label373: if ((localLinkedHashMap != null) && (localLinkedHashMap.size() > 0))
          break label882;
        i2 = 1;
        label388: if (i3 == 0)
          break label907;
        if (localObject6 == null)
          break label887;
        localObject1 = localObject4;
        localObject2 = localObject6;
        localObject4 = localObject7;
        label410: if ((parama.e) && (this.b.jdMethod_new().l()) && (localObject4 != null) && (localObject1 != null))
          ((BDLocation)localObject2).setAddr(this.b.l().a(((Double)localObject1).doubleValue(), ((Double)localObject4).doubleValue()));
        i2 = i1;
        Object localObject10 = localObject4;
        localObject9 = localObject1;
        localObject7 = localObject2;
        if (i3 != 0)
        {
          i2 = i1;
          localObject10 = localObject4;
          localObject9 = localObject1;
          localObject7 = localObject2;
          if (parama.e)
          {
            i2 = i1;
            localObject10 = localObject4;
            localObject9 = localObject1;
            localObject7 = localObject2;
            if (((BDLocation)localObject2).getAddrStr() == null)
            {
              localObject10 = null;
              localObject9 = null;
              i2 = 0;
              localObject7 = localObject3;
            }
          }
        }
        if (((!parama.d) && (!parama.h)) || (localObject10 == null) || (localObject9 == null))
          break label948;
        localObject1 = this.b.l().b(((Double)localObject9).doubleValue(), localObject10.doubleValue());
        if (parama.d)
          ((BDLocation)localObject7).setPoiList((List)localObject1);
        label599: if ((i3 == 0) || (!parama.d) || ((localObject1 != null) && (((List)localObject1).size() > 0)))
          break label943;
        i1 = 0;
        localObject7 = localObject3;
        label632: localObject4 = null;
        localObject2 = localObject4;
        if (parama.h)
        {
          localObject2 = localObject4;
          if (localObject1 != null)
          {
            localObject2 = localObject4;
            if (((List)localObject1).size() > 0)
            {
              localObject2 = String.format(Locale.CHINA, "在%s附近", new Object[] { ((Poi)((List)localObject1).get(0)).getName() });
              ((BDLocation)localObject7).setLocationDescribe((String)localObject2);
            }
          }
        }
        i2 = i1;
        localObject1 = localObject7;
        if (i3 != 0)
        {
          i2 = i1;
          localObject1 = localObject7;
          if (parama.h)
          {
            i2 = i1;
            localObject1 = localObject7;
            if (localObject2 == null)
            {
              i2 = 0;
              localObject1 = localObject3;
            }
          }
        }
        localObject3 = new StringBuffer();
        localObject2 = null;
        if (parama.a != null)
        {
          ((StringBuffer)localObject3).append(parama.a);
          ((StringBuffer)localObject3).append(h.a((BDLocation)localObject6, (BDLocation)localObject5, parama));
          ((StringBuffer)localObject3).append(h.a((BDLocation)localObject1, i2));
          localObject2 = ((StringBuffer)localObject3).toString();
        }
        new c(this, str, localObject8, (BDLocation)localObject5, (BDLocation)localObject6, localBDLocation, (String)localObject2, localLinkedHashMap).start();
      }
    }
    while (true)
    {
      return h.a((BDLocation)localObject1);
      if ((localObject5 == null) && (localObject6 != null))
      {
        i1 = 2;
        break;
      }
      i1 = i2;
      if (localObject5 == null)
        break;
      i1 = i2;
      if (localObject6 == null)
        break;
      i1 = 4;
      break;
      label876: i3 = 0;
      break label373;
      label882: i2 = 0;
      break label388;
      label887: if ((i2 != 0) && (localObject5 != null))
      {
        localObject4 = localObject2;
        localObject2 = localObject5;
        break label410;
        if (localObject6 != null)
        {
          localObject1 = localObject4;
          localObject2 = localObject6;
          localObject4 = localObject7;
          break label410;
        }
        if (localObject5 != null)
        {
          localObject4 = localObject2;
          localObject2 = localObject5;
          break label410;
          i1 = i2;
          break label632;
          localObject1 = null;
          break label599;
        }
      }
      label907: label943: label948: localObject4 = null;
      localObject1 = null;
      localObject2 = localObject3;
      break label410;
      localObject1 = localObject3;
    }
  }

  SQLiteDatabase a()
  {
    return this.j;
  }

  void b()
  {
    this.h.a();
  }

  private class a extends Thread
  {
    private String a;
    private Long c;
    private BDLocation d;
    private BDLocation e;
    private BDLocation f;
    private String g;
    private LinkedHashMap h;

    private a(String paramLong, Long paramBDLocation1, BDLocation paramBDLocation2, BDLocation paramBDLocation3, BDLocation paramString1, String paramLinkedHashMap, LinkedHashMap arg8)
    {
      this.a = paramLong;
      this.c = paramBDLocation1;
      this.d = paramBDLocation2;
      this.e = paramBDLocation3;
      this.f = paramString1;
      this.g = paramLinkedHashMap;
      Object localObject;
      this.h = localObject;
    }

    public void run()
    {
      a.a(a.this, this.a, this.c, this.d);
      a.a(a.this, null);
      a.b(a.this, null);
      a.a(a.this, this.h);
      a.a(a.this, this.f, this.d, this.e, this.a, this.c);
      if (this.g != null)
        a.a(a.this).j().a(this.g);
      this.h = null;
      this.a = null;
      this.g = null;
      this.c = null;
      this.d = null;
      this.e = null;
      this.f = null;
    }
  }

  private final class b extends m
  {
    private String c;
    private final String d;
    private String e;
    private a f;
    private boolean g = false;
    private int h = 0;
    private long i = -1L;
    private long j = -1L;
    private long k = -1L;
    private long l = -1L;

    b(a paramBoolean, boolean arg3)
    {
      this.f = paramBoolean;
      int m;
      if (m != 0);
      for (this.d = "load"; ; this.d = "update")
      {
        this.c7 = new ArrayList();
        this.c = d.ak;
        return;
      }
    }

    private void a(String paramString1, String paramString2, String paramString3)
    {
      this.e = paramString3;
      this.c = String.format("http://%s/%s", new Object[] { paramString1, paramString2 });
      ao();
    }

    private void b()
    {
      this.h += 1;
      this.i = System.currentTimeMillis();
    }

    private boolean c()
    {
      if (this.h < 2)
        return true;
      if (this.i + 43200000L < System.currentTimeMillis())
      {
        this.h = 0;
        this.i = -1L;
        return true;
      }
      return false;
    }

    private void d()
    {
      this.e = null;
      if (i())
      {
        if ((this.j == -1L) || (this.j + 86400000L <= System.currentTimeMillis()))
          this.e = e();
        if ((this.e == null) && ((this.k == -1L) || (this.k + 86400000L <= System.currentTimeMillis())))
          if (!a.a(a.this).l().a())
            break label126;
      }
      label126: for (this.e = g(); ; this.e = h())
      {
        if (this.e != null)
          ao();
        return;
        this.e = f();
        break;
      }
    }

    private String e()
    {
      String str = null;
      try
      {
        JSONObject localJSONObject = new JSONObject();
        localJSONObject.put("type", "0");
        localJSONObject.put("cuid", com.baidu.location.b.c.N().bm);
        localJSONObject.put("ver", "1");
        localJSONObject.put("prod", com.baidu.location.b.c.bj + ":" + com.baidu.location.b.c.bn);
        if (localJSONObject != null)
          str = Jni.G(localJSONObject.toString());
        return str;
      }
      catch (Exception localException)
      {
        while (true)
          Object localObject = null;
      }
    }

    // ERROR //
    private String f()
    {
      // Byte code:
      //   0: iconst_0
      //   1: istore_3
      //   2: aconst_null
      //   3: astore 5
      //   5: new 128	org/json/JSONObject
      //   8: dup
      //   9: invokespecial 129	org/json/JSONObject:<init>	()V
      //   12: astore 8
      //   14: new 128	org/json/JSONObject
      //   17: dup
      //   18: invokespecial 129	org/json/JSONObject:<init>	()V
      //   21: astore 4
      //   23: aload_0
      //   24: getfield 26	com/baidu/location/c/a$b:b	Lcom/baidu/location/c/a;
      //   27: invokestatic 182	com/baidu/location/c/a:c	(Lcom/baidu/location/c/a;)Landroid/database/sqlite/SQLiteDatabase;
      //   30: ldc 184
      //   32: iconst_3
      //   33: anewarray 79	java/lang/Object
      //   36: dup
      //   37: iconst_0
      //   38: ldc 186
      //   40: aastore
      //   41: dup
      //   42: iconst_1
      //   43: iconst_5
      //   44: invokestatic 192	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   47: aastore
      //   48: dup
      //   49: iconst_2
      //   50: bipush 50
      //   52: invokestatic 192	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   55: aastore
      //   56: invokestatic 85	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
      //   59: aconst_null
      //   60: invokevirtual 198	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
      //   63: astore 6
      //   65: aload 6
      //   67: ifnull +557 -> 624
      //   70: aload 6
      //   72: invokeinterface 203 1 0
      //   77: ifeq +547 -> 624
      //   80: aload 6
      //   82: invokeinterface 207 1 0
      //   87: istore_1
      //   88: new 209	org/json/JSONArray
      //   91: dup
      //   92: invokespecial 210	org/json/JSONArray:<init>	()V
      //   95: astore 7
      //   97: aload 6
      //   99: invokeinterface 213 1 0
      //   104: ifne +155 -> 259
      //   107: aload 7
      //   109: aload 6
      //   111: iconst_1
      //   112: invokeinterface 217 2 0
      //   117: invokevirtual 220	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
      //   120: pop
      //   121: aload 6
      //   123: invokeinterface 223 1 0
      //   128: pop
      //   129: goto -32 -> 97
      //   132: astore 5
      //   134: aconst_null
      //   135: astore 5
      //   137: aload 5
      //   139: ifnull +10 -> 149
      //   142: aload 5
      //   144: invokeinterface 226 1 0
      //   149: aload 4
      //   151: astore 5
      //   153: aload 6
      //   155: ifnull +462 -> 617
      //   158: aload 6
      //   160: invokeinterface 226 1 0
      //   165: aload 4
      //   167: ifnull +444 -> 611
      //   170: aload 4
      //   172: ldc 228
      //   174: invokevirtual 232	org/json/JSONObject:has	(Ljava/lang/String;)Z
      //   177: ifne +434 -> 611
      //   180: aload_0
      //   181: getfield 43	com/baidu/location/c/a$b:l	J
      //   184: ldc2_w 34
      //   187: lcmp
      //   188: ifeq +18 -> 206
      //   191: aload_0
      //   192: getfield 43	com/baidu/location/c/a$b:l	J
      //   195: ldc2_w 104
      //   198: ladd
      //   199: invokestatic 95	java/lang/System:currentTimeMillis	()J
      //   202: lcmp
      //   203: ifge +408 -> 611
      //   206: aload 4
      //   208: invokevirtual 173	org/json/JSONObject:toString	()Ljava/lang/String;
      //   211: invokestatic 179	com/baidu/location/Jni:G	(Ljava/lang/String;)Ljava/lang/String;
      //   214: astore 5
      //   216: aload_0
      //   217: invokestatic 95	java/lang/System:currentTimeMillis	()J
      //   220: putfield 43	com/baidu/location/c/a$b:l	J
      //   223: aload 5
      //   225: astore 6
      //   227: aload 4
      //   229: ifnull +27 -> 256
      //   232: aload 5
      //   234: astore 6
      //   236: aload 4
      //   238: ldc 228
      //   240: invokevirtual 232	org/json/JSONObject:has	(Ljava/lang/String;)Z
      //   243: ifeq +13 -> 256
      //   246: aload 4
      //   248: invokevirtual 173	org/json/JSONObject:toString	()Ljava/lang/String;
      //   251: invokestatic 179	com/baidu/location/Jni:G	(Ljava/lang/String;)Ljava/lang/String;
      //   254: astore 6
      //   256: aload 6
      //   258: areturn
      //   259: aload 8
      //   261: ldc 234
      //   263: aload 7
      //   265: invokevirtual 137	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   268: pop
      //   269: aload_0
      //   270: getfield 26	com/baidu/location/c/a$b:b	Lcom/baidu/location/c/a;
      //   273: invokestatic 182	com/baidu/location/c/a:c	(Lcom/baidu/location/c/a;)Landroid/database/sqlite/SQLiteDatabase;
      //   276: ldc 184
      //   278: iconst_3
      //   279: anewarray 79	java/lang/Object
      //   282: dup
      //   283: iconst_0
      //   284: ldc 236
      //   286: aastore
      //   287: dup
      //   288: iconst_1
      //   289: iconst_5
      //   290: invokestatic 192	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   293: aastore
      //   294: dup
      //   295: iconst_2
      //   296: bipush 50
      //   298: invokestatic 192	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   301: aastore
      //   302: invokestatic 85	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
      //   305: aconst_null
      //   306: invokevirtual 198	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
      //   309: astore 7
      //   311: aload 7
      //   313: astore 5
      //   315: iload_3
      //   316: istore_2
      //   317: aload 5
      //   319: ifnull +77 -> 396
      //   322: iload_3
      //   323: istore_2
      //   324: aload 5
      //   326: invokeinterface 203 1 0
      //   331: ifeq +65 -> 396
      //   334: aload 5
      //   336: invokeinterface 207 1 0
      //   341: istore_2
      //   342: new 209	org/json/JSONArray
      //   345: dup
      //   346: invokespecial 210	org/json/JSONArray:<init>	()V
      //   349: astore 7
      //   351: aload 5
      //   353: invokeinterface 213 1 0
      //   358: ifne +28 -> 386
      //   361: aload 7
      //   363: aload 5
      //   365: iconst_1
      //   366: invokeinterface 217 2 0
      //   371: invokevirtual 220	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
      //   374: pop
      //   375: aload 5
      //   377: invokeinterface 223 1 0
      //   382: pop
      //   383: goto -32 -> 351
      //   386: aload 8
      //   388: ldc 238
      //   390: aload 7
      //   392: invokevirtual 137	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   395: pop
      //   396: aload 4
      //   398: ldc 131
      //   400: ldc 152
      //   402: invokevirtual 137	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   405: pop
      //   406: aload 4
      //   408: ldc 139
      //   410: invokestatic 145	com/baidu/location/b/c:N	()Lcom/baidu/location/b/c;
      //   413: getfield 148	com/baidu/location/b/c:bm	Ljava/lang/String;
      //   416: invokevirtual 137	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   419: pop
      //   420: aload 4
      //   422: ldc 150
      //   424: ldc 152
      //   426: invokevirtual 137	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   429: pop
      //   430: aload 4
      //   432: ldc 154
      //   434: new 156	java/lang/StringBuilder
      //   437: dup
      //   438: invokespecial 157	java/lang/StringBuilder:<init>	()V
      //   441: getstatic 160	com/baidu/location/b/c:bj	Ljava/lang/String;
      //   444: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   447: ldc 166
      //   449: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   452: getstatic 169	com/baidu/location/b/c:bn	Ljava/lang/String;
      //   455: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   458: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   461: invokevirtual 137	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   464: pop
      //   465: iload_1
      //   466: ifne +7 -> 473
      //   469: iload_2
      //   470: ifeq +13 -> 483
      //   473: aload 4
      //   475: ldc 228
      //   477: aload 8
      //   479: invokevirtual 137	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   482: pop
      //   483: aload 5
      //   485: ifnull +10 -> 495
      //   488: aload 5
      //   490: invokeinterface 226 1 0
      //   495: aload 4
      //   497: astore 5
      //   499: aload 6
      //   501: ifnull +116 -> 617
      //   504: aload 6
      //   506: invokeinterface 226 1 0
      //   511: goto -346 -> 165
      //   514: astore 5
      //   516: goto -351 -> 165
      //   519: astore 5
      //   521: goto -356 -> 165
      //   524: astore 4
      //   526: aconst_null
      //   527: astore 6
      //   529: aload 5
      //   531: ifnull +10 -> 541
      //   534: aload 5
      //   536: invokeinterface 226 1 0
      //   541: aload 6
      //   543: ifnull +10 -> 553
      //   546: aload 6
      //   548: invokeinterface 226 1 0
      //   553: aload 4
      //   555: athrow
      //   556: astore 5
      //   558: goto -63 -> 495
      //   561: astore 5
      //   563: goto -414 -> 149
      //   566: astore 5
      //   568: goto -27 -> 541
      //   571: astore 5
      //   573: goto -20 -> 553
      //   576: astore 4
      //   578: goto -49 -> 529
      //   581: astore 4
      //   583: goto -54 -> 529
      //   586: astore 4
      //   588: aconst_null
      //   589: astore 4
      //   591: aconst_null
      //   592: astore 6
      //   594: aconst_null
      //   595: astore 5
      //   597: goto -460 -> 137
      //   600: astore 5
      //   602: aconst_null
      //   603: astore 6
      //   605: aconst_null
      //   606: astore 5
      //   608: goto -471 -> 137
      //   611: aconst_null
      //   612: astore 5
      //   614: goto -391 -> 223
      //   617: aload 5
      //   619: astore 4
      //   621: goto -456 -> 165
      //   624: iconst_0
      //   625: istore_1
      //   626: goto -357 -> 269
      //   629: astore 7
      //   631: goto -494 -> 137
      //
      // Exception table:
      //   from	to	target	type
      //   70	97	132	java/lang/Exception
      //   97	129	132	java/lang/Exception
      //   259	269	132	java/lang/Exception
      //   269	311	132	java/lang/Exception
      //   504	511	514	java/lang/Exception
      //   158	165	519	java/lang/Exception
      //   5	23	524	finally
      //   23	65	524	finally
      //   488	495	556	java/lang/Exception
      //   142	149	561	java/lang/Exception
      //   534	541	566	java/lang/Exception
      //   546	553	571	java/lang/Exception
      //   70	97	576	finally
      //   97	129	576	finally
      //   259	269	576	finally
      //   269	311	576	finally
      //   324	351	581	finally
      //   351	383	581	finally
      //   386	396	581	finally
      //   396	465	581	finally
      //   473	483	581	finally
      //   5	23	586	java/lang/Exception
      //   23	65	600	java/lang/Exception
      //   324	351	629	java/lang/Exception
      //   351	383	629	java/lang/Exception
      //   386	396	629	java/lang/Exception
      //   396	465	629	java/lang/Exception
      //   473	483	629	java/lang/Exception
    }

    private String g()
    {
      String str = null;
      try
      {
        localJSONObject = new JSONObject();
      }
      catch (Exception localException1)
      {
        try
        {
          JSONObject localJSONObject;
          localJSONObject.put("type", "2");
          localJSONObject.put("ver", "1");
          localJSONObject.put("cuid", com.baidu.location.b.c.N().bm);
          localJSONObject.put("prod", com.baidu.location.b.c.bj + ":" + com.baidu.location.b.c.bn);
          this.k = System.currentTimeMillis();
          while (true)
          {
            label82: if (localJSONObject != null)
              str = Jni.G(localJSONObject.toString());
            return str;
            localException1 = localException1;
            Object localObject = null;
          }
        }
        catch (Exception localException2)
        {
          break label82;
        }
      }
    }

    private String h()
    {
      String str = null;
      try
      {
        localJSONObject2 = a.a(a.this).l().b();
        if (localJSONObject2 != null)
          localJSONObject1 = new JSONObject();
      }
      catch (Exception localException1)
      {
        while (true)
        {
          try
          {
            JSONObject localJSONObject2;
            JSONObject localJSONObject1;
            localJSONObject1.put("type", "3");
            localJSONObject1.put("ver", "1");
            localJSONObject1.put("cuid", com.baidu.location.b.c.N().bm);
            localJSONObject1.put("prod", com.baidu.location.b.c.bj + ":" + com.baidu.location.b.c.bn);
            localJSONObject1.put("rgc", localJSONObject2);
            this.k = System.currentTimeMillis();
            if (localJSONObject1 != null)
              str = Jni.G(localJSONObject1.toString());
            return str;
            localException1 = localException1;
            localObject = null;
            continue;
          }
          catch (Exception localException2)
          {
            continue;
          }
          Object localObject = null;
        }
      }
    }

    // ERROR //
    private boolean i()
    {
      // Byte code:
      //   0: aconst_null
      //   1: astore 6
      //   3: aconst_null
      //   4: astore 9
      //   6: aconst_null
      //   7: astore 8
      //   9: aconst_null
      //   10: astore 7
      //   12: iconst_1
      //   13: istore 4
      //   15: iconst_1
      //   16: istore_3
      //   17: aload_0
      //   18: getfield 26	com/baidu/location/c/a$b:b	Lcom/baidu/location/c/a;
      //   21: invokestatic 249	com/baidu/location/c/a:b	(Lcom/baidu/location/c/a;)Landroid/database/sqlite/SQLiteDatabase;
      //   24: ldc 251
      //   26: aconst_null
      //   27: invokevirtual 198	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
      //   30: astore 5
      //   32: aload 9
      //   34: astore 6
      //   36: aload 8
      //   38: astore 7
      //   40: aload_0
      //   41: getfield 26	com/baidu/location/c/a$b:b	Lcom/baidu/location/c/a;
      //   44: invokestatic 249	com/baidu/location/c/a:b	(Lcom/baidu/location/c/a;)Landroid/database/sqlite/SQLiteDatabase;
      //   47: ldc 253
      //   49: aconst_null
      //   50: invokevirtual 198	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
      //   53: astore 8
      //   55: iload_3
      //   56: istore_2
      //   57: aload 5
      //   59: ifnull +94 -> 153
      //   62: iload_3
      //   63: istore_2
      //   64: aload 8
      //   66: astore 6
      //   68: aload 8
      //   70: astore 7
      //   72: aload 5
      //   74: invokeinterface 203 1 0
      //   79: ifeq +74 -> 153
      //   82: iload_3
      //   83: istore_2
      //   84: aload 8
      //   86: ifnull +67 -> 153
      //   89: iload_3
      //   90: istore_2
      //   91: aload 8
      //   93: astore 6
      //   95: aload 8
      //   97: astore 7
      //   99: aload 8
      //   101: invokeinterface 203 1 0
      //   106: ifeq +47 -> 153
      //   109: aload 8
      //   111: astore 6
      //   113: aload 8
      //   115: astore 7
      //   117: aload 5
      //   119: iconst_0
      //   120: invokeinterface 257 2 0
      //   125: ifne +26 -> 151
      //   128: aload 8
      //   130: astore 6
      //   132: aload 8
      //   134: astore 7
      //   136: aload 8
      //   138: iconst_0
      //   139: invokeinterface 257 2 0
      //   144: istore_1
      //   145: iload_3
      //   146: istore_2
      //   147: iload_1
      //   148: ifeq +5 -> 153
      //   151: iconst_0
      //   152: istore_2
      //   153: aload 5
      //   155: ifnull +10 -> 165
      //   158: aload 5
      //   160: invokeinterface 226 1 0
      //   165: iload_2
      //   166: istore_3
      //   167: aload 8
      //   169: ifnull +12 -> 181
      //   172: aload 8
      //   174: invokeinterface 226 1 0
      //   179: iload_2
      //   180: istore_3
      //   181: iload_3
      //   182: ireturn
      //   183: astore 5
      //   185: aconst_null
      //   186: astore 5
      //   188: aload 5
      //   190: ifnull +10 -> 200
      //   193: aload 5
      //   195: invokeinterface 226 1 0
      //   200: iload 4
      //   202: istore_3
      //   203: aload 7
      //   205: ifnull -24 -> 181
      //   208: aload 7
      //   210: invokeinterface 226 1 0
      //   215: iconst_1
      //   216: ireturn
      //   217: astore 5
      //   219: iconst_1
      //   220: ireturn
      //   221: astore 7
      //   223: aconst_null
      //   224: astore 5
      //   226: aload 5
      //   228: ifnull +10 -> 238
      //   231: aload 5
      //   233: invokeinterface 226 1 0
      //   238: aload 6
      //   240: ifnull +10 -> 250
      //   243: aload 6
      //   245: invokeinterface 226 1 0
      //   250: aload 7
      //   252: athrow
      //   253: astore 5
      //   255: goto -90 -> 165
      //   258: astore 5
      //   260: iload_2
      //   261: ireturn
      //   262: astore 5
      //   264: goto -64 -> 200
      //   267: astore 5
      //   269: goto -31 -> 238
      //   272: astore 5
      //   274: goto -24 -> 250
      //   277: astore 7
      //   279: goto -53 -> 226
      //   282: astore 6
      //   284: goto -96 -> 188
      //
      // Exception table:
      //   from	to	target	type
      //   17	32	183	java/lang/Exception
      //   208	215	217	java/lang/Exception
      //   17	32	221	finally
      //   158	165	253	java/lang/Exception
      //   172	179	258	java/lang/Exception
      //   193	200	262	java/lang/Exception
      //   231	238	267	java/lang/Exception
      //   243	250	272	java/lang/Exception
      //   40	55	277	finally
      //   72	82	277	finally
      //   99	109	277	finally
      //   117	128	277	finally
      //   136	145	277	finally
      //   40	55	282	java/lang/Exception
      //   72	82	282	java/lang/Exception
      //   99	109	282	java/lang/Exception
      //   117	128	282	java/lang/Exception
      //   136	145	282	java/lang/Exception
    }

    void a()
    {
      if ((c()) && (!this.g))
        a.d(a.this).d();
    }

    public void au()
    {
      this.g = true;
      this.c5 = this.c;
      this.c7.clear();
      this.c7.add(new BasicNameValuePair("qt", this.d));
      this.c7.add(new BasicNameValuePair("req", this.e));
    }

    public void jdMethod_int(boolean paramBoolean)
    {
      if ((paramBoolean) && (this.c6 != null))
      {
        new e(this).start();
        return;
      }
      this.g = false;
      b();
    }
  }

  private static final class c
  {
    double a;
    double b;
    double c;

    private c(double paramDouble1, double paramDouble2, double paramDouble3)
    {
      this.a = paramDouble1;
      this.b = paramDouble2;
      this.c = paramDouble3;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.c.a
 * JD-Core Version:    0.6.2
 */