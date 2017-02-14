package cn.jpush.android.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class j
{
  private static final String a;
  private static Map<String, String> b;
  private static final String[] z;

  static
  {
    String[] arrayOfString = new String[17];
    int j = 0;
    Object localObject2 = "axRk\033";
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
        i = 126;
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
        localObject2 = "b|Nu\026Wh^p\027kido\020nc";
        i = 0;
        localObject1 = arrayOfString;
        break;
      case 0:
        localObject3[i4] = localObject1;
        j = 2;
        localObject2 = "gdp\033zRi\020";
        i = 1;
        localObject1 = arrayOfString;
        break;
      case 1:
        localObject3[i4] = localObject1;
        j = 3;
        localObject2 = "dmUa\013ik^";
        i = 2;
        localObject1 = arrayOfString;
        break;
      case 2:
        localObject3[i4] = localObject1;
        j = 4;
        localObject2 = "|eVc\004gb^";
        i = 3;
        localObject1 = arrayOfString;
        break;
      case 3:
        localObject3[i4] = localObject1;
        j = 5;
        localObject2 = "i|KY\bm~Ho\021foTb\033";
        i = 4;
        localObject1 = arrayOfString;
        break;
      case 4:
        localObject3[i4] = localObject1;
        j = 6;
        localObject2 = "k|NY\027fjT";
        i = 5;
        localObject1 = arrayOfString;
        break;
      case 5:
        localObject3[i4] = localObject1;
        j = 7;
        localObject2 = "liMo\035mSRh\030g";
        i = 6;
        localObject1 = arrayOfString;
        break;
      case 6:
        localObject3[i4] = localObject1;
        j = 8;
        localObject2 = "ec_c\022";
        i = 7;
        localObject1 = arrayOfString;
        break;
      case 7:
        localObject3[i4] = localObject1;
        j = 9;
        localObject2 = "i|KY\bm~Ho\021fbZk\033";
        i = 8;
        localObject1 = arrayOfString;
        break;
      case 8:
        localObject3[i4] = localObject1;
        j = 10;
        localObject2 = "kdZh\020m`";
        i = 9;
        localObject1 = arrayOfString;
        break;
      case 9:
        localObject3[i4] = localObject1;
        j = 11;
        localObject2 = "ziHi\022}xRi\020";
        i = 10;
        localObject1 = arrayOfString;
        break;
      case 10:
        localObject3[i4] = localObject1;
        j = 12;
        localObject2 = "{hPY\bm~Ho\021f";
        i = 11;
        localObject1 = arrayOfString;
        break;
      case 11:
        localObject3[i4] = localObject1;
        j = 13;
        localObject2 = "|uKc";
        i = 12;
        localObject1 = arrayOfString;
        break;
      case 12:
        localObject3[i4] = localObject1;
        j = 14;
        localObject2 = "i|KY\025mu";
        i = 13;
        localObject1 = arrayOfString;
        break;
      case 13:
        localObject3[i4] = localObject1;
        j = 15;
        localObject2 = "9\"\003(L";
        i = 14;
        localObject1 = arrayOfString;
        break;
      case 14:
        localObject3[i4] = localObject1;
        j = 16;
        localObject2 = "SRZ+\004I!a6S1S\025[";
        i = 15;
        localObject1 = arrayOfString;
        break;
      case 15:
        localObject3[i4] = localObject1;
        z = arrayOfString;
        a = j.class.getSimpleName();
        b = null;
        return;
        i = 8;
        continue;
        i = 12;
        continue;
        i = 59;
        continue;
        i = 6;
      }
    }
  }

  // ERROR //
  public static void a(Context paramContext)
  {
    // Byte code:
    //   0: getstatic 70	cn/jpush/android/util/j:a	Ljava/lang/String;
    //   3: astore_2
    //   4: invokestatic 83	cn/jpush/android/util/x:c	()V
    //   7: getstatic 89	cn/jpush/android/a:j	Z
    //   10: ifne +4 -> 14
    //   13: return
    //   14: aload_0
    //   15: invokestatic 92	cn/jpush/android/util/j:b	(Landroid/content/Context;)Ljava/util/Map;
    //   18: astore_2
    //   19: aload_2
    //   20: ifnull -7 -> 13
    //   23: aload_2
    //   24: invokeinterface 98 1 0
    //   29: ifne -16 -> 13
    //   32: getstatic 72	cn/jpush/android/util/j:b	Ljava/util/Map;
    //   35: ifnonnull +433 -> 468
    //   38: new 100	java/util/HashMap
    //   41: dup
    //   42: invokespecial 101	java/util/HashMap:<init>	()V
    //   45: astore_3
    //   46: aload_0
    //   47: getstatic 63	cn/jpush/android/util/j:z	[Ljava/lang/String;
    //   50: iconst_1
    //   51: aaload
    //   52: iconst_0
    //   53: invokevirtual 107	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   56: astore 13
    //   58: aload 13
    //   60: getstatic 63	cn/jpush/android/util/j:z	[Ljava/lang/String;
    //   63: bipush 6
    //   65: aaload
    //   66: aconst_null
    //   67: invokeinterface 113 3 0
    //   72: astore 4
    //   74: aload 13
    //   76: getstatic 63	cn/jpush/android/util/j:z	[Ljava/lang/String;
    //   79: bipush 11
    //   81: aaload
    //   82: aconst_null
    //   83: invokeinterface 113 3 0
    //   88: astore 5
    //   90: aload 13
    //   92: getstatic 63	cn/jpush/android/util/j:z	[Ljava/lang/String;
    //   95: bipush 10
    //   97: aaload
    //   98: aconst_null
    //   99: invokeinterface 113 3 0
    //   104: astore 6
    //   106: aload 13
    //   108: getstatic 63	cn/jpush/android/util/j:z	[Ljava/lang/String;
    //   111: bipush 14
    //   113: aaload
    //   114: aconst_null
    //   115: invokeinterface 113 3 0
    //   120: astore 7
    //   122: aload 13
    //   124: getstatic 63	cn/jpush/android/util/j:z	[Ljava/lang/String;
    //   127: iconst_5
    //   128: aaload
    //   129: aconst_null
    //   130: invokeinterface 113 3 0
    //   135: astore 8
    //   137: aload 13
    //   139: getstatic 63	cn/jpush/android/util/j:z	[Ljava/lang/String;
    //   142: bipush 9
    //   144: aaload
    //   145: aconst_null
    //   146: invokeinterface 113 3 0
    //   151: astore 9
    //   153: aload 13
    //   155: getstatic 63	cn/jpush/android/util/j:z	[Ljava/lang/String;
    //   158: iconst_3
    //   159: aaload
    //   160: aconst_null
    //   161: invokeinterface 113 3 0
    //   166: astore 10
    //   168: aload 13
    //   170: getstatic 63	cn/jpush/android/util/j:z	[Ljava/lang/String;
    //   173: iconst_4
    //   174: aaload
    //   175: aconst_null
    //   176: invokeinterface 113 3 0
    //   181: astore 11
    //   183: aload 13
    //   185: getstatic 63	cn/jpush/android/util/j:z	[Ljava/lang/String;
    //   188: bipush 12
    //   190: aaload
    //   191: aconst_null
    //   192: invokeinterface 113 3 0
    //   197: astore 12
    //   199: aload 13
    //   201: getstatic 63	cn/jpush/android/util/j:z	[Ljava/lang/String;
    //   204: bipush 8
    //   206: aaload
    //   207: aconst_null
    //   208: invokeinterface 113 3 0
    //   213: astore 13
    //   215: aload 4
    //   217: invokestatic 118	cn/jpush/android/util/ai:a	(Ljava/lang/String;)Z
    //   220: ifne +18 -> 238
    //   223: aload_3
    //   224: getstatic 63	cn/jpush/android/util/j:z	[Ljava/lang/String;
    //   227: bipush 6
    //   229: aaload
    //   230: aload 4
    //   232: invokeinterface 122 3 0
    //   237: pop
    //   238: aload 5
    //   240: invokestatic 118	cn/jpush/android/util/ai:a	(Ljava/lang/String;)Z
    //   243: ifne +18 -> 261
    //   246: aload_3
    //   247: getstatic 63	cn/jpush/android/util/j:z	[Ljava/lang/String;
    //   250: bipush 11
    //   252: aaload
    //   253: aload 5
    //   255: invokeinterface 122 3 0
    //   260: pop
    //   261: aload 6
    //   263: invokestatic 118	cn/jpush/android/util/ai:a	(Ljava/lang/String;)Z
    //   266: ifne +18 -> 284
    //   269: aload_3
    //   270: getstatic 63	cn/jpush/android/util/j:z	[Ljava/lang/String;
    //   273: bipush 10
    //   275: aaload
    //   276: aload 6
    //   278: invokeinterface 122 3 0
    //   283: pop
    //   284: aload 7
    //   286: invokestatic 118	cn/jpush/android/util/ai:a	(Ljava/lang/String;)Z
    //   289: ifne +18 -> 307
    //   292: aload_3
    //   293: getstatic 63	cn/jpush/android/util/j:z	[Ljava/lang/String;
    //   296: bipush 14
    //   298: aaload
    //   299: aload 7
    //   301: invokeinterface 122 3 0
    //   306: pop
    //   307: aload 8
    //   309: invokestatic 118	cn/jpush/android/util/ai:a	(Ljava/lang/String;)Z
    //   312: ifne +17 -> 329
    //   315: aload_3
    //   316: getstatic 63	cn/jpush/android/util/j:z	[Ljava/lang/String;
    //   319: iconst_5
    //   320: aaload
    //   321: aload 8
    //   323: invokeinterface 122 3 0
    //   328: pop
    //   329: aload 9
    //   331: invokestatic 118	cn/jpush/android/util/ai:a	(Ljava/lang/String;)Z
    //   334: ifne +18 -> 352
    //   337: aload_3
    //   338: getstatic 63	cn/jpush/android/util/j:z	[Ljava/lang/String;
    //   341: bipush 9
    //   343: aaload
    //   344: aload 9
    //   346: invokeinterface 122 3 0
    //   351: pop
    //   352: aload 10
    //   354: invokestatic 118	cn/jpush/android/util/ai:a	(Ljava/lang/String;)Z
    //   357: ifne +17 -> 374
    //   360: aload_3
    //   361: getstatic 63	cn/jpush/android/util/j:z	[Ljava/lang/String;
    //   364: iconst_2
    //   365: aaload
    //   366: aload 10
    //   368: invokeinterface 122 3 0
    //   373: pop
    //   374: aload 10
    //   376: invokestatic 118	cn/jpush/android/util/ai:a	(Ljava/lang/String;)Z
    //   379: ifne +17 -> 396
    //   382: aload_3
    //   383: getstatic 63	cn/jpush/android/util/j:z	[Ljava/lang/String;
    //   386: iconst_3
    //   387: aaload
    //   388: aload 10
    //   390: invokeinterface 122 3 0
    //   395: pop
    //   396: aload 11
    //   398: invokestatic 118	cn/jpush/android/util/ai:a	(Ljava/lang/String;)Z
    //   401: ifne +17 -> 418
    //   404: aload_3
    //   405: getstatic 63	cn/jpush/android/util/j:z	[Ljava/lang/String;
    //   408: iconst_4
    //   409: aaload
    //   410: aload 11
    //   412: invokeinterface 122 3 0
    //   417: pop
    //   418: aload 12
    //   420: invokestatic 118	cn/jpush/android/util/ai:a	(Ljava/lang/String;)Z
    //   423: ifne +18 -> 441
    //   426: aload_3
    //   427: getstatic 63	cn/jpush/android/util/j:z	[Ljava/lang/String;
    //   430: bipush 12
    //   432: aaload
    //   433: aload 12
    //   435: invokeinterface 122 3 0
    //   440: pop
    //   441: aload 13
    //   443: invokestatic 118	cn/jpush/android/util/ai:a	(Ljava/lang/String;)Z
    //   446: ifne +18 -> 464
    //   449: aload_3
    //   450: getstatic 63	cn/jpush/android/util/j:z	[Ljava/lang/String;
    //   453: bipush 8
    //   455: aaload
    //   456: aload 13
    //   458: invokeinterface 122 3 0
    //   463: pop
    //   464: aload_3
    //   465: putstatic 72	cn/jpush/android/util/j:b	Ljava/util/Map;
    //   468: getstatic 72	cn/jpush/android/util/j:b	Ljava/util/Map;
    //   471: astore_3
    //   472: aload_3
    //   473: ifnull +12 -> 485
    //   476: aload_3
    //   477: invokeinterface 98 1 0
    //   482: ifeq +131 -> 613
    //   485: iconst_1
    //   486: istore_1
    //   487: iload_1
    //   488: ifeq +71 -> 559
    //   491: aload_2
    //   492: putstatic 72	cn/jpush/android/util/j:b	Ljava/util/Map;
    //   495: aload_0
    //   496: aload_2
    //   497: invokestatic 125	cn/jpush/android/util/j:a	(Landroid/content/Context;Ljava/util/Map;)V
    //   500: new 127	org/json/JSONObject
    //   503: dup
    //   504: aload_2
    //   505: invokespecial 130	org/json/JSONObject:<init>	(Ljava/util/Map;)V
    //   508: astore_3
    //   509: aload_3
    //   510: getstatic 63	cn/jpush/android/util/j:z	[Ljava/lang/String;
    //   513: iconst_0
    //   514: aaload
    //   515: getstatic 136	cn/jpush/android/service/PushService:m	J
    //   518: invokestatic 142	java/lang/System:currentTimeMillis	()J
    //   521: ldc2_w 143
    //   524: ldiv
    //   525: getstatic 147	cn/jpush/android/service/PushService:n	J
    //   528: lsub
    //   529: invokestatic 153	java/lang/Math:abs	(J)J
    //   532: ladd
    //   533: invokevirtual 156	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   536: pop
    //   537: aload_3
    //   538: getstatic 63	cn/jpush/android/util/j:z	[Ljava/lang/String;
    //   541: bipush 13
    //   543: aaload
    //   544: getstatic 63	cn/jpush/android/util/j:z	[Ljava/lang/String;
    //   547: bipush 7
    //   549: aaload
    //   550: invokevirtual 159	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   553: pop
    //   554: aload_0
    //   555: aload_3
    //   556: invokestatic 164	cn/jpush/android/util/ac:a	(Landroid/content/Context;Lorg/json/JSONObject;)V
    //   559: invokestatic 169	cn/jpush/android/api/c:a	()Lcn/jpush/android/api/c;
    //   562: aload_0
    //   563: invokevirtual 172	cn/jpush/android/api/c:c	(Landroid/content/Context;)Lorg/json/JSONObject;
    //   566: astore_3
    //   567: aload_3
    //   568: ifnull -555 -> 13
    //   571: new 127	org/json/JSONObject
    //   574: dup
    //   575: aload_2
    //   576: invokespecial 130	org/json/JSONObject:<init>	(Ljava/util/Map;)V
    //   579: astore_2
    //   580: aload_2
    //   581: ifnull +22 -> 603
    //   584: aload_2
    //   585: invokevirtual 176	org/json/JSONObject:length	()I
    //   588: ifle +15 -> 603
    //   591: aload_3
    //   592: getstatic 63	cn/jpush/android/util/j:z	[Ljava/lang/String;
    //   595: bipush 7
    //   597: aaload
    //   598: aload_2
    //   599: invokevirtual 159	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   602: pop
    //   603: aload_0
    //   604: aload_3
    //   605: invokestatic 164	cn/jpush/android/util/ac:a	(Landroid/content/Context;Lorg/json/JSONObject;)V
    //   608: aload_0
    //   609: invokestatic 178	cn/jpush/android/api/c:b	(Landroid/content/Context;)V
    //   612: return
    //   613: aload_2
    //   614: aload_3
    //   615: invokevirtual 182	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   618: ifne +8 -> 626
    //   621: iconst_1
    //   622: istore_1
    //   623: goto -136 -> 487
    //   626: iconst_0
    //   627: istore_1
    //   628: goto -141 -> 487
    //   631: astore_0
    //   632: getstatic 70	cn/jpush/android/util/j:a	Ljava/lang/String;
    //   635: astore_2
    //   636: aload_0
    //   637: invokevirtual 185	org/json/JSONException:getMessage	()Ljava/lang/String;
    //   640: pop
    //   641: invokestatic 188	cn/jpush/android/util/x:f	()V
    //   644: return
    //   645: astore_2
    //   646: goto -43 -> 603
    //
    // Exception table:
    //   from	to	target	type
    //   500	559	631	org/json/JSONException
    //   591	603	645	org/json/JSONException
  }

  private static void a(Context paramContext, Map<String, String> paramMap)
  {
    if (paramMap == null);
    do
    {
      return;
      localObject = paramMap.keySet();
    }
    while ((localObject == null) || (((Set)localObject).size() <= 0));
    paramContext = paramContext.getSharedPreferences(z[1], 0).edit();
    Object localObject = ((Set)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      String str = (String)((Iterator)localObject).next();
      paramContext.putString(str, (String)paramMap.get(str));
    }
    paramContext.commit();
  }

  // ERROR //
  private static Map<String, String> b(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 235	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   4: astore 25
    //   6: aload_0
    //   7: invokevirtual 238	android/content/Context:getPackageName	()Ljava/lang/String;
    //   10: astore 26
    //   12: new 100	java/util/HashMap
    //   15: dup
    //   16: invokespecial 101	java/util/HashMap:<init>	()V
    //   19: astore 24
    //   21: aconst_null
    //   22: astore 18
    //   24: aconst_null
    //   25: astore_3
    //   26: aconst_null
    //   27: astore 23
    //   29: aconst_null
    //   30: astore 21
    //   32: aconst_null
    //   33: astore 19
    //   35: aconst_null
    //   36: astore 22
    //   38: aconst_null
    //   39: astore 9
    //   41: aconst_null
    //   42: astore 12
    //   44: aconst_null
    //   45: astore 14
    //   47: aconst_null
    //   48: astore 20
    //   50: aconst_null
    //   51: astore 17
    //   53: aload 17
    //   55: astore 10
    //   57: aload 14
    //   59: astore 11
    //   61: aload 12
    //   63: astore 5
    //   65: aload 9
    //   67: astore 13
    //   69: aload 19
    //   71: astore 15
    //   73: aload 23
    //   75: astore 6
    //   77: aload_3
    //   78: astore 16
    //   80: invokestatic 242	cn/jpush/android/util/a:c	()Ljava/lang/String;
    //   83: astore 7
    //   85: aload 17
    //   87: astore 10
    //   89: aload 14
    //   91: astore 11
    //   93: aload 12
    //   95: astore 5
    //   97: aload 9
    //   99: astore 13
    //   101: aload 19
    //   103: astore 15
    //   105: aload 23
    //   107: astore 6
    //   109: aload_3
    //   110: astore 16
    //   112: aload 7
    //   114: astore 18
    //   116: aload_0
    //   117: invokestatic 245	cn/jpush/android/util/a:a	(Landroid/content/Context;)Ljava/lang/String;
    //   120: astore 8
    //   122: aload 17
    //   124: astore 10
    //   126: aload 14
    //   128: astore 11
    //   130: aload 12
    //   132: astore 5
    //   134: aload 9
    //   136: astore 13
    //   138: aload 19
    //   140: astore 15
    //   142: aload 23
    //   144: astore 6
    //   146: aload 8
    //   148: astore 16
    //   150: aload 7
    //   152: astore 18
    //   154: aload 25
    //   156: aload 26
    //   158: sipush 128
    //   161: invokevirtual 251	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   164: astore 27
    //   166: aload 22
    //   168: astore 4
    //   170: aload 21
    //   172: astore_3
    //   173: aload 27
    //   175: ifnull +122 -> 297
    //   178: aload 22
    //   180: astore 4
    //   182: aload 21
    //   184: astore_3
    //   185: aload 17
    //   187: astore 10
    //   189: aload 14
    //   191: astore 11
    //   193: aload 12
    //   195: astore 5
    //   197: aload 9
    //   199: astore 13
    //   201: aload 19
    //   203: astore 15
    //   205: aload 23
    //   207: astore 6
    //   209: aload 8
    //   211: astore 16
    //   213: aload 7
    //   215: astore 18
    //   217: aload 27
    //   219: getfield 257	android/content/pm/ApplicationInfo:metaData	Landroid/os/Bundle;
    //   222: ifnull +75 -> 297
    //   225: aload 17
    //   227: astore 10
    //   229: aload 14
    //   231: astore 11
    //   233: aload 12
    //   235: astore 5
    //   237: aload 9
    //   239: astore 13
    //   241: aload 19
    //   243: astore 15
    //   245: aload 23
    //   247: astore 6
    //   249: aload 8
    //   251: astore 16
    //   253: aload 7
    //   255: astore 18
    //   257: getstatic 260	cn/jpush/android/a:e	Ljava/lang/String;
    //   260: astore_3
    //   261: aload 17
    //   263: astore 10
    //   265: aload 14
    //   267: astore 11
    //   269: aload 12
    //   271: astore 5
    //   273: aload 9
    //   275: astore 13
    //   277: aload 19
    //   279: astore 15
    //   281: aload_3
    //   282: astore 6
    //   284: aload 8
    //   286: astore 16
    //   288: aload 7
    //   290: astore 18
    //   292: getstatic 262	cn/jpush/android/a:f	Ljava/lang/String;
    //   295: astore 4
    //   297: aload 17
    //   299: astore 10
    //   301: aload 14
    //   303: astore 11
    //   305: aload 12
    //   307: astore 5
    //   309: aload 9
    //   311: astore 13
    //   313: aload 4
    //   315: astore 15
    //   317: aload_3
    //   318: astore 6
    //   320: aload 8
    //   322: astore 16
    //   324: aload 7
    //   326: astore 18
    //   328: aload 25
    //   330: aload 26
    //   332: iconst_0
    //   333: invokevirtual 266	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   336: astore 19
    //   338: aload 17
    //   340: astore 10
    //   342: aload 14
    //   344: astore 11
    //   346: aload 12
    //   348: astore 5
    //   350: aload 9
    //   352: astore 13
    //   354: aload 4
    //   356: astore 15
    //   358: aload_3
    //   359: astore 6
    //   361: aload 8
    //   363: astore 16
    //   365: aload 7
    //   367: astore 18
    //   369: new 268	java/lang/StringBuilder
    //   372: dup
    //   373: invokespecial 269	java/lang/StringBuilder:<init>	()V
    //   376: aload 19
    //   378: getfield 275	android/content/pm/PackageInfo:versionCode	I
    //   381: invokevirtual 279	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   384: invokevirtual 282	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   387: astore 9
    //   389: aload 17
    //   391: astore 10
    //   393: aload 14
    //   395: astore 11
    //   397: aload 12
    //   399: astore 5
    //   401: aload 9
    //   403: astore 13
    //   405: aload 4
    //   407: astore 15
    //   409: aload_3
    //   410: astore 6
    //   412: aload 8
    //   414: astore 16
    //   416: aload 7
    //   418: astore 18
    //   420: aload 19
    //   422: getfield 285	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   425: astore 12
    //   427: aload 17
    //   429: astore 10
    //   431: aload 14
    //   433: astore 11
    //   435: aload 12
    //   437: astore 5
    //   439: aload 9
    //   441: astore 13
    //   443: aload 4
    //   445: astore 15
    //   447: aload_3
    //   448: astore 6
    //   450: aload 8
    //   452: astore 16
    //   454: aload 7
    //   456: astore 18
    //   458: aload 12
    //   460: getstatic 63	cn/jpush/android/util/j:z	[Ljava/lang/String;
    //   463: bipush 16
    //   465: aaload
    //   466: ldc_w 287
    //   469: invokevirtual 290	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   472: astore 12
    //   474: aload 17
    //   476: astore 10
    //   478: aload 14
    //   480: astore 11
    //   482: aload 12
    //   484: astore 5
    //   486: aload 9
    //   488: astore 13
    //   490: aload 4
    //   492: astore 15
    //   494: aload_3
    //   495: astore 6
    //   497: aload 8
    //   499: astore 16
    //   501: aload 7
    //   503: astore 18
    //   505: new 268	java/lang/StringBuilder
    //   508: dup
    //   509: invokespecial 269	java/lang/StringBuilder:<init>	()V
    //   512: getstatic 295	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   515: invokevirtual 298	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   518: invokevirtual 282	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   521: astore 14
    //   523: aload 17
    //   525: astore 10
    //   527: aload 14
    //   529: astore 11
    //   531: aload 12
    //   533: astore 5
    //   535: aload 9
    //   537: astore 13
    //   539: aload 4
    //   541: astore 15
    //   543: aload_3
    //   544: astore 6
    //   546: aload 8
    //   548: astore 16
    //   550: aload 7
    //   552: astore 18
    //   554: getstatic 63	cn/jpush/android/util/j:z	[Ljava/lang/String;
    //   557: bipush 15
    //   559: aaload
    //   560: astore 17
    //   562: aload 17
    //   564: astore 10
    //   566: aload 14
    //   568: astore 11
    //   570: aload 12
    //   572: astore 5
    //   574: aload 9
    //   576: astore 13
    //   578: aload 4
    //   580: astore 15
    //   582: aload_3
    //   583: astore 6
    //   585: aload 8
    //   587: astore 16
    //   589: aload 7
    //   591: astore 18
    //   593: getstatic 303	android/os/Build:MODEL	Ljava/lang/String;
    //   596: astore 19
    //   598: aload_0
    //   599: invokevirtual 307	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   602: invokevirtual 313	android/content/res/Resources:getConfiguration	()Landroid/content/res/Configuration;
    //   605: getfield 319	android/content/res/Configuration:locale	Ljava/util/Locale;
    //   608: invokevirtual 322	java/util/Locale:toString	()Ljava/lang/String;
    //   611: astore 5
    //   613: invokestatic 328	java/util/TimeZone:getDefault	()Ljava/util/TimeZone;
    //   616: invokevirtual 331	java/util/TimeZone:getRawOffset	()I
    //   619: i2l
    //   620: ldc2_w 332
    //   623: ldiv
    //   624: lstore_1
    //   625: lload_1
    //   626: lconst_0
    //   627: lcmp
    //   628: ifle +288 -> 916
    //   631: new 268	java/lang/StringBuilder
    //   634: dup
    //   635: ldc_w 335
    //   638: invokespecial 338	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   641: lload_1
    //   642: invokevirtual 341	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   645: invokevirtual 282	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   648: astore_0
    //   649: aload 14
    //   651: astore 11
    //   653: aload 17
    //   655: astore 10
    //   657: aload 7
    //   659: invokestatic 118	cn/jpush/android/util/ai:a	(Ljava/lang/String;)Z
    //   662: ifne +19 -> 681
    //   665: aload 24
    //   667: getstatic 63	cn/jpush/android/util/j:z	[Ljava/lang/String;
    //   670: bipush 6
    //   672: aaload
    //   673: aload 7
    //   675: invokeinterface 122 3 0
    //   680: pop
    //   681: aload 8
    //   683: invokestatic 118	cn/jpush/android/util/ai:a	(Ljava/lang/String;)Z
    //   686: ifne +19 -> 705
    //   689: aload 24
    //   691: getstatic 63	cn/jpush/android/util/j:z	[Ljava/lang/String;
    //   694: bipush 11
    //   696: aaload
    //   697: aload 8
    //   699: invokeinterface 122 3 0
    //   704: pop
    //   705: aload_3
    //   706: invokestatic 118	cn/jpush/android/util/ai:a	(Ljava/lang/String;)Z
    //   709: ifne +18 -> 727
    //   712: aload 24
    //   714: getstatic 63	cn/jpush/android/util/j:z	[Ljava/lang/String;
    //   717: bipush 10
    //   719: aaload
    //   720: aload_3
    //   721: invokeinterface 122 3 0
    //   726: pop
    //   727: aload 4
    //   729: invokestatic 118	cn/jpush/android/util/ai:a	(Ljava/lang/String;)Z
    //   732: ifne +19 -> 751
    //   735: aload 24
    //   737: getstatic 63	cn/jpush/android/util/j:z	[Ljava/lang/String;
    //   740: bipush 14
    //   742: aaload
    //   743: aload 4
    //   745: invokeinterface 122 3 0
    //   750: pop
    //   751: aload 9
    //   753: invokestatic 118	cn/jpush/android/util/ai:a	(Ljava/lang/String;)Z
    //   756: ifne +18 -> 774
    //   759: aload 24
    //   761: getstatic 63	cn/jpush/android/util/j:z	[Ljava/lang/String;
    //   764: iconst_5
    //   765: aaload
    //   766: aload 9
    //   768: invokeinterface 122 3 0
    //   773: pop
    //   774: aload 12
    //   776: invokestatic 118	cn/jpush/android/util/ai:a	(Ljava/lang/String;)Z
    //   779: ifne +19 -> 798
    //   782: aload 24
    //   784: getstatic 63	cn/jpush/android/util/j:z	[Ljava/lang/String;
    //   787: bipush 9
    //   789: aaload
    //   790: aload 12
    //   792: invokeinterface 122 3 0
    //   797: pop
    //   798: aload 11
    //   800: invokestatic 118	cn/jpush/android/util/ai:a	(Ljava/lang/String;)Z
    //   803: ifne +18 -> 821
    //   806: aload 24
    //   808: getstatic 63	cn/jpush/android/util/j:z	[Ljava/lang/String;
    //   811: iconst_2
    //   812: aaload
    //   813: aload 11
    //   815: invokeinterface 122 3 0
    //   820: pop
    //   821: aload 5
    //   823: invokestatic 118	cn/jpush/android/util/ai:a	(Ljava/lang/String;)Z
    //   826: ifne +18 -> 844
    //   829: aload 24
    //   831: getstatic 63	cn/jpush/android/util/j:z	[Ljava/lang/String;
    //   834: iconst_3
    //   835: aaload
    //   836: aload 5
    //   838: invokeinterface 122 3 0
    //   843: pop
    //   844: aload_0
    //   845: invokestatic 118	cn/jpush/android/util/ai:a	(Ljava/lang/String;)Z
    //   848: ifne +17 -> 865
    //   851: aload 24
    //   853: getstatic 63	cn/jpush/android/util/j:z	[Ljava/lang/String;
    //   856: iconst_4
    //   857: aaload
    //   858: aload_0
    //   859: invokeinterface 122 3 0
    //   864: pop
    //   865: aload 10
    //   867: invokestatic 118	cn/jpush/android/util/ai:a	(Ljava/lang/String;)Z
    //   870: ifne +19 -> 889
    //   873: aload 24
    //   875: getstatic 63	cn/jpush/android/util/j:z	[Ljava/lang/String;
    //   878: bipush 12
    //   880: aaload
    //   881: aload 10
    //   883: invokeinterface 122 3 0
    //   888: pop
    //   889: aload 19
    //   891: invokestatic 118	cn/jpush/android/util/ai:a	(Ljava/lang/String;)Z
    //   894: ifne +19 -> 913
    //   897: aload 24
    //   899: getstatic 63	cn/jpush/android/util/j:z	[Ljava/lang/String;
    //   902: bipush 8
    //   904: aaload
    //   905: aload 19
    //   907: invokeinterface 122 3 0
    //   912: pop
    //   913: aload 24
    //   915: areturn
    //   916: lload_1
    //   917: lconst_0
    //   918: lcmp
    //   919: ifge +32 -> 951
    //   922: new 268	java/lang/StringBuilder
    //   925: dup
    //   926: ldc_w 343
    //   929: invokespecial 338	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   932: lload_1
    //   933: invokevirtual 341	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   936: invokevirtual 282	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   939: astore_0
    //   940: aload 17
    //   942: astore 10
    //   944: aload 14
    //   946: astore 11
    //   948: goto -291 -> 657
    //   951: new 268	java/lang/StringBuilder
    //   954: dup
    //   955: invokespecial 269	java/lang/StringBuilder:<init>	()V
    //   958: lload_1
    //   959: invokevirtual 341	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   962: invokevirtual 282	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   965: astore_0
    //   966: aload 17
    //   968: astore 10
    //   970: aload 14
    //   972: astore 11
    //   974: goto -317 -> 657
    //   977: astore 14
    //   979: aconst_null
    //   980: astore_0
    //   981: aconst_null
    //   982: astore 17
    //   984: aload 18
    //   986: astore 7
    //   988: aload 16
    //   990: astore 8
    //   992: aload 6
    //   994: astore_3
    //   995: aload 15
    //   997: astore 4
    //   999: aload 13
    //   1001: astore 9
    //   1003: aload 5
    //   1005: astore 12
    //   1007: aload 17
    //   1009: astore 5
    //   1011: getstatic 70	cn/jpush/android/util/j:a	Ljava/lang/String;
    //   1014: astore 6
    //   1016: aload 14
    //   1018: invokevirtual 344	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   1021: pop
    //   1022: invokestatic 188	cn/jpush/android/util/x:f	()V
    //   1025: aload_0
    //   1026: astore 19
    //   1028: aload 20
    //   1030: astore_0
    //   1031: goto -374 -> 657
    //   1034: astore 6
    //   1036: aconst_null
    //   1037: astore 5
    //   1039: aload 19
    //   1041: astore_0
    //   1042: aload 17
    //   1044: astore 10
    //   1046: aload 14
    //   1048: astore 11
    //   1050: aload 6
    //   1052: astore 14
    //   1054: goto -43 -> 1011
    //   1057: astore 6
    //   1059: aload 19
    //   1061: astore_0
    //   1062: aload 17
    //   1064: astore 10
    //   1066: aload 14
    //   1068: astore 11
    //   1070: aload 6
    //   1072: astore 14
    //   1074: goto -63 -> 1011
    //
    // Exception table:
    //   from	to	target	type
    //   80	85	977	java/lang/Exception
    //   116	122	977	java/lang/Exception
    //   154	166	977	java/lang/Exception
    //   217	225	977	java/lang/Exception
    //   257	261	977	java/lang/Exception
    //   292	297	977	java/lang/Exception
    //   328	338	977	java/lang/Exception
    //   369	389	977	java/lang/Exception
    //   420	427	977	java/lang/Exception
    //   458	474	977	java/lang/Exception
    //   505	523	977	java/lang/Exception
    //   554	562	977	java/lang/Exception
    //   593	598	977	java/lang/Exception
    //   598	613	1034	java/lang/Exception
    //   613	625	1057	java/lang/Exception
    //   631	649	1057	java/lang/Exception
    //   922	940	1057	java/lang/Exception
    //   951	966	1057	java/lang/Exception
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.util.j
 * JD-Core Version:    0.6.2
 */