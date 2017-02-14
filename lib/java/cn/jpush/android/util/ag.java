package cn.jpush.android.util;

import android.content.Context;
import cn.jpush.android.data.w;
import org.json.JSONException;
import org.json.JSONObject;

public final class ag
{
  private static final String[] z;

  static
  {
    String[] arrayOfString = new String[19];
    int j = 0;
    Object localObject2 = "";
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
        i = 7;
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
        localObject2 = "C\n\025";
        i = 0;
        localObject1 = arrayOfString;
        break;
      case 0:
        localObject3[i4] = localObject1;
        j = 2;
        localObject2 = "Y\006\f\031t";
        i = 1;
        localObject1 = arrayOfString;
        break;
      case 1:
        localObject3[i4] = localObject1;
        j = 3;
        localObject2 = "";
        i = 2;
        localObject1 = arrayOfString;
        break;
      case 2:
        localObject3[i4] = localObject1;
        j = 4;
        localObject2 = "";
        i = 3;
        localObject1 = arrayOfString;
        break;
      case 3:
        localObject3[i4] = localObject1;
        j = 5;
        localObject2 = "";
        i = 4;
        localObject1 = arrayOfString;
        break;
      case 4:
        localObject3[i4] = localObject1;
        j = 6;
        localObject2 = "";
        i = 5;
        localObject1 = arrayOfString;
        break;
      case 5:
        localObject3[i4] = localObject1;
        j = 7;
        localObject2 = "T\026\030\005*`\"L\030c";
        i = 6;
        localObject1 = arrayOfString;
        break;
      case 6:
        localObject3[i4] = localObject1;
        j = 8;
        localObject2 = "K\016\b\020bI0\025\023w";
        i = 7;
        localObject1 = arrayOfString;
        break;
      case 7:
        localObject3[i4] = localObject1;
        j = 9;
        localObject2 = "^\013\n#nC\013\004\004";
        i = 8;
        localObject1 = arrayOfString;
        break;
      case 8:
        localObject3[i4] = localObject1;
        j = 10;
        localObject2 = "I\016\025\031";
        i = 9;
        localObject1 = arrayOfString;
        break;
      case 9:
        localObject3[i4] = localObject1;
        j = 11;
        localObject2 = "Y\026\021\031";
        i = 10;
        localObject1 = arrayOfString;
        break;
      case 10:
        localObject3[i4] = localObject1;
        j = 12;
        localObject2 = "^\032\002\037bH\013>\bh]";
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
        j = 14;
        localObject2 = "D\033\b\021b";
        i = 13;
        localObject1 = arrayOfString;
        break;
      case 13:
        localObject3[i4] = localObject1;
        j = 15;
        localObject2 = "";
        i = 14;
        localObject1 = arrayOfString;
        break;
      case 14:
        localObject3[i4] = localObject1;
        j = 16;
        localObject2 = "";
        i = 15;
        localObject1 = arrayOfString;
        break;
      case 15:
        localObject3[i4] = localObject1;
        j = 17;
        localObject2 = "^\032\002\037b^\034>\030bY\016\b\020t";
        i = 16;
        localObject1 = arrayOfString;
        break;
      case 16:
        localObject3[i4] = localObject1;
        j = 18;
        localObject2 = "";
        i = 17;
        localObject1 = arrayOfString;
        break;
      case 17:
        localObject3[i4] = localObject1;
        z = arrayOfString;
        return;
        i = 45;
        continue;
        i = 111;
        continue;
        i = 97;
        continue;
        i = 124;
      }
    }
  }

  private static JSONObject a(w paramw)
  {
    JSONObject localJSONObject1 = new JSONObject();
    try
    {
      localJSONObject1.put(z[1], paramw.b());
      localJSONObject1.put(z[4], paramw.c());
      localJSONObject1.put(z[0], paramw.d());
      localJSONObject1.put(z[3], paramw.e());
      localJSONObject1.put(z[2], paramw.g());
      JSONObject localJSONObject2 = new JSONObject();
      localJSONObject2.put(z[15], paramw.h());
      localJSONObject2.put(z[16], paramw.i());
      localJSONObject2.put(z[18], paramw.j());
      localJSONObject1.put(z[17], localJSONObject2);
      return localJSONObject1;
    }
    catch (JSONException paramw)
    {
      paramw.printStackTrace();
    }
    return localJSONObject1;
  }

  // ERROR //
  public static void a(Context paramContext)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aload_0
    //   4: ifnonnull +4 -> 8
    //   7: return
    //   8: getstatic 114	cn/jpush/android/a:j	Z
    //   11: ifeq +357 -> 368
    //   14: new 69	org/json/JSONObject
    //   17: dup
    //   18: invokespecial 71	org/json/JSONObject:<init>	()V
    //   21: astore 5
    //   23: aload 5
    //   25: getstatic 62	cn/jpush/android/util/ag:z	[Ljava/lang/String;
    //   28: bipush 14
    //   30: aaload
    //   31: getstatic 120	cn/jpush/android/service/PushService:m	J
    //   34: invokestatic 126	java/lang/System:currentTimeMillis	()J
    //   37: ldc2_w 127
    //   40: ldiv
    //   41: getstatic 131	cn/jpush/android/service/PushService:n	J
    //   44: lsub
    //   45: invokestatic 137	java/lang/Math:abs	(J)J
    //   48: ladd
    //   49: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   52: pop
    //   53: aload 5
    //   55: getstatic 62	cn/jpush/android/util/ag:z	[Ljava/lang/String;
    //   58: bipush 11
    //   60: aaload
    //   61: getstatic 62	cn/jpush/android/util/ag:z	[Ljava/lang/String;
    //   64: bipush 9
    //   66: aaload
    //   67: invokevirtual 80	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   70: pop
    //   71: aload 5
    //   73: getstatic 62	cn/jpush/android/util/ag:z	[Ljava/lang/String;
    //   76: bipush 10
    //   78: aaload
    //   79: new 142	java/text/SimpleDateFormat
    //   82: dup
    //   83: getstatic 62	cn/jpush/android/util/ag:z	[Ljava/lang/String;
    //   86: bipush 7
    //   88: aaload
    //   89: invokespecial 145	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;)V
    //   92: new 147	java/util/Date
    //   95: dup
    //   96: aload_0
    //   97: invokestatic 150	cn/jpush/android/util/ag:b	(Landroid/content/Context;)J
    //   100: ldc2_w 127
    //   103: lmul
    //   104: invokespecial 153	java/util/Date:<init>	(J)V
    //   107: invokevirtual 157	java/text/SimpleDateFormat:format	(Ljava/util/Date;)Ljava/lang/String;
    //   110: invokevirtual 80	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   113: pop
    //   114: new 159	cn/jpush/android/data/u
    //   117: dup
    //   118: aload_0
    //   119: invokespecial 161	cn/jpush/android/data/u:<init>	(Landroid/content/Context;)V
    //   122: astore 6
    //   124: aload 6
    //   126: invokevirtual 163	cn/jpush/android/data/u:a	()V
    //   129: aload 5
    //   131: getstatic 62	cn/jpush/android/util/ag:z	[Ljava/lang/String;
    //   134: bipush 6
    //   136: aaload
    //   137: aload 6
    //   139: iconst_1
    //   140: invokevirtual 166	cn/jpush/android/data/u:a	(Z)I
    //   143: invokevirtual 96	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   146: pop
    //   147: aload 5
    //   149: getstatic 62	cn/jpush/android/util/ag:z	[Ljava/lang/String;
    //   152: bipush 13
    //   154: aaload
    //   155: aload 6
    //   157: iconst_0
    //   158: invokevirtual 166	cn/jpush/android/data/u:a	(Z)I
    //   161: invokevirtual 96	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   164: pop
    //   165: new 168	org/json/JSONArray
    //   168: dup
    //   169: invokespecial 169	org/json/JSONArray:<init>	()V
    //   172: astore 7
    //   174: aload 6
    //   176: invokevirtual 172	cn/jpush/android/data/u:d	()Landroid/database/Cursor;
    //   179: astore_3
    //   180: aload_3
    //   181: ifnull +53 -> 234
    //   184: aload_3
    //   185: astore_2
    //   186: aload_3
    //   187: astore_1
    //   188: aload_3
    //   189: invokestatic 175	cn/jpush/android/data/u:a	(Landroid/database/Cursor;)Lcn/jpush/android/data/w;
    //   192: astore 8
    //   194: aload 8
    //   196: ifnull +18 -> 214
    //   199: aload_3
    //   200: astore_2
    //   201: aload_3
    //   202: astore_1
    //   203: aload 8
    //   205: invokevirtual 177	cn/jpush/android/data/w:a	()Ljava/lang/String;
    //   208: invokestatic 182	cn/jpush/android/util/ai:a	(Ljava/lang/String;)Z
    //   211: ifeq +194 -> 405
    //   214: aload_3
    //   215: astore_2
    //   216: aload_3
    //   217: astore_1
    //   218: invokestatic 186	cn/jpush/android/util/x:c	()V
    //   221: aload_3
    //   222: astore_2
    //   223: aload_3
    //   224: astore_1
    //   225: aload_3
    //   226: invokeinterface 192 1 0
    //   231: ifne -47 -> 184
    //   234: aload_3
    //   235: astore_2
    //   236: aload_3
    //   237: astore_1
    //   238: aload 5
    //   240: getstatic 62	cn/jpush/android/util/ag:z	[Ljava/lang/String;
    //   243: bipush 8
    //   245: aaload
    //   246: aload 7
    //   248: invokevirtual 80	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   251: pop
    //   252: new 168	org/json/JSONArray
    //   255: dup
    //   256: invokespecial 169	org/json/JSONArray:<init>	()V
    //   259: astore 7
    //   261: aload 6
    //   263: invokevirtual 194	cn/jpush/android/data/u:e	()Landroid/database/Cursor;
    //   266: astore_3
    //   267: aload_3
    //   268: ifnull +53 -> 321
    //   271: aload_3
    //   272: astore_2
    //   273: aload_3
    //   274: astore_1
    //   275: aload_3
    //   276: invokestatic 175	cn/jpush/android/data/u:a	(Landroid/database/Cursor;)Lcn/jpush/android/data/w;
    //   279: astore 4
    //   281: aload 4
    //   283: ifnull +18 -> 301
    //   286: aload_3
    //   287: astore_2
    //   288: aload_3
    //   289: astore_1
    //   290: aload 4
    //   292: invokevirtual 177	cn/jpush/android/data/w:a	()Ljava/lang/String;
    //   295: invokestatic 182	cn/jpush/android/util/ai:a	(Ljava/lang/String;)Z
    //   298: ifeq +150 -> 448
    //   301: aload_3
    //   302: astore_2
    //   303: aload_3
    //   304: astore_1
    //   305: invokestatic 186	cn/jpush/android/util/x:c	()V
    //   308: aload_3
    //   309: astore_2
    //   310: aload_3
    //   311: astore_1
    //   312: aload_3
    //   313: invokeinterface 192 1 0
    //   318: ifne -47 -> 271
    //   321: aload_3
    //   322: astore_2
    //   323: aload_3
    //   324: astore_1
    //   325: aload 5
    //   327: getstatic 62	cn/jpush/android/util/ag:z	[Ljava/lang/String;
    //   330: bipush 12
    //   332: aaload
    //   333: aload 7
    //   335: invokevirtual 80	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   338: pop
    //   339: aload_3
    //   340: astore_2
    //   341: aload_3
    //   342: astore_1
    //   343: aload 6
    //   345: invokevirtual 196	cn/jpush/android/data/u:b	()V
    //   348: aload_3
    //   349: astore_2
    //   350: aload_3
    //   351: astore_1
    //   352: aload_0
    //   353: aload 5
    //   355: invokestatic 201	cn/jpush/android/util/ac:a	(Landroid/content/Context;Lorg/json/JSONObject;)V
    //   358: aload_3
    //   359: ifnull +9 -> 368
    //   362: aload_3
    //   363: invokeinterface 204 1 0
    //   368: aload_0
    //   369: invokestatic 126	java/lang/System:currentTimeMillis	()J
    //   372: ldc2_w 127
    //   375: ldiv
    //   376: invokestatic 207	cn/jpush/android/util/ag:a	(Landroid/content/Context;J)V
    //   379: new 159	cn/jpush/android/data/u
    //   382: dup
    //   383: aload_0
    //   384: invokespecial 161	cn/jpush/android/data/u:<init>	(Landroid/content/Context;)V
    //   387: astore_0
    //   388: aload_0
    //   389: ifnull -382 -> 7
    //   392: aload_0
    //   393: invokevirtual 163	cn/jpush/android/data/u:a	()V
    //   396: aload_0
    //   397: invokevirtual 208	cn/jpush/android/data/u:c	()V
    //   400: aload_0
    //   401: invokevirtual 196	cn/jpush/android/data/u:b	()V
    //   404: return
    //   405: aload_3
    //   406: astore_2
    //   407: aload_3
    //   408: astore_1
    //   409: aload 7
    //   411: aload 8
    //   413: invokestatic 210	cn/jpush/android/util/ag:b	(Lcn/jpush/android/data/w;)Lorg/json/JSONObject;
    //   416: invokevirtual 213	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   419: pop
    //   420: goto -199 -> 221
    //   423: astore_3
    //   424: aload_2
    //   425: astore_1
    //   426: invokestatic 216	cn/jpush/android/util/x:f	()V
    //   429: aload_2
    //   430: astore_1
    //   431: aload_3
    //   432: invokevirtual 108	org/json/JSONException:printStackTrace	()V
    //   435: aload_2
    //   436: ifnull -68 -> 368
    //   439: aload_2
    //   440: invokeinterface 204 1 0
    //   445: goto -77 -> 368
    //   448: aload_3
    //   449: astore_2
    //   450: aload_3
    //   451: astore_1
    //   452: aload 7
    //   454: aload 4
    //   456: invokestatic 218	cn/jpush/android/util/ag:a	(Lcn/jpush/android/data/w;)Lorg/json/JSONObject;
    //   459: invokevirtual 213	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   462: pop
    //   463: goto -155 -> 308
    //   466: astore_0
    //   467: aload_1
    //   468: ifnull +9 -> 477
    //   471: aload_1
    //   472: invokeinterface 204 1 0
    //   477: aload_0
    //   478: athrow
    //   479: astore_0
    //   480: aload 4
    //   482: astore_1
    //   483: goto -16 -> 467
    //   486: astore_3
    //   487: aconst_null
    //   488: astore_2
    //   489: goto -65 -> 424
    //
    // Exception table:
    //   from	to	target	type
    //   188	194	423	org/json/JSONException
    //   203	214	423	org/json/JSONException
    //   218	221	423	org/json/JSONException
    //   225	234	423	org/json/JSONException
    //   238	252	423	org/json/JSONException
    //   275	281	423	org/json/JSONException
    //   290	301	423	org/json/JSONException
    //   305	308	423	org/json/JSONException
    //   312	321	423	org/json/JSONException
    //   325	339	423	org/json/JSONException
    //   343	348	423	org/json/JSONException
    //   352	358	423	org/json/JSONException
    //   409	420	423	org/json/JSONException
    //   452	463	423	org/json/JSONException
    //   188	194	466	finally
    //   203	214	466	finally
    //   218	221	466	finally
    //   225	234	466	finally
    //   238	252	466	finally
    //   275	281	466	finally
    //   290	301	466	finally
    //   305	308	466	finally
    //   312	321	466	finally
    //   325	339	466	finally
    //   343	348	466	finally
    //   352	358	466	finally
    //   409	420	466	finally
    //   426	429	466	finally
    //   431	435	466	finally
    //   452	463	466	finally
    //   23	180	479	finally
    //   252	267	479	finally
    //   23	180	486	org/json/JSONException
    //   252	267	486	org/json/JSONException
  }

  public static void a(Context paramContext, long paramLong)
  {
    if (paramContext == null)
      return;
    ah.b(paramContext, z[5], paramLong);
  }

  public static long b(Context paramContext)
  {
    if (paramContext == null)
      return -1L;
    return ah.a(paramContext, z[5], 0L);
  }

  private static JSONObject b(w paramw)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put(z[1], paramw.b());
      localJSONObject.put(z[4], paramw.c());
      localJSONObject.put(z[0], paramw.d());
      localJSONObject.put(z[3], paramw.e());
      localJSONObject.put(z[2], paramw.g());
      return localJSONObject;
    }
    catch (JSONException paramw)
    {
      paramw.printStackTrace();
    }
    return localJSONObject;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.util.ag
 * JD-Core Version:    0.6.2
 */