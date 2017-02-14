package org.android.agoo.net.mtop;

import com.umeng.message.proguard.bF;
import com.umeng.message.proguard.bd;
import com.umeng.message.proguard.bx;
import java.io.ByteArrayInputStream;
import java.util.Map;
import org.json.JSONObject;

public class MtopRequestHelper
{
  public static final String a = "&";
  private static final String b = "MtopRequestHelper";

  private static final long a()
  {
    try
    {
      long l = System.currentTimeMillis() / 1000L;
      return l;
    }
    catch (Throwable localThrowable)
    {
      bd.d("MtopRequestHelper", "getTime", localThrowable);
    }
    return 0L;
  }

  private static final String a(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, long paramLong, String paramString7, String paramString8)
    throws Throwable
  {
    try
    {
      paramString1 = bx.a(new ByteArrayInputStream(paramString1.getBytes("UTF-8")));
      StringBuffer localStringBuffer = new StringBuffer();
      if ((paramString8 != null) && (!"".equals(paramString8)))
      {
        localStringBuffer.append(paramString8);
        localStringBuffer.append("&");
      }
      localStringBuffer.append(paramString2);
      localStringBuffer.append("&");
      localStringBuffer.append(paramString1);
      localStringBuffer.append("&");
      localStringBuffer.append(paramString3);
      localStringBuffer.append("&");
      localStringBuffer.append(paramString4);
      localStringBuffer.append("&");
      localStringBuffer.append(paramString5);
      localStringBuffer.append("&");
      localStringBuffer.append(paramString6);
      localStringBuffer.append("&");
      localStringBuffer.append(bx.a(new ByteArrayInputStream(paramString7.getBytes("UTF-8"))));
      localStringBuffer.append("&");
      localStringBuffer.append(paramLong);
      paramString1 = bx.a(new ByteArrayInputStream(localStringBuffer.toString().getBytes("UTF-8")));
      return paramString1;
    }
    catch (Throwable paramString1)
    {
      bd.d("MtopRequestHelper", "toV3Sign", paramString1);
    }
    return null;
  }

  private static final String a(Map<String, Object> paramMap)
  {
    try
    {
      paramMap = new JSONObject(paramMap).toString();
      return paramMap;
    }
    catch (Throwable paramMap)
    {
      bd.d("MtopRequestHelper", "param2String", paramMap);
    }
    return null;
  }

  public static final void checkAppKeyAndAppSecret(MtopRequest paramMtopRequest, String paramString1, String paramString2)
  {
    try
    {
      if ((!bF.a(paramString1)) && (bF.a(paramMtopRequest.getAppKey())))
        paramMtopRequest.setAppKey(paramString1);
      if ((!bF.a(paramString2)) && (bF.a(paramMtopRequest.getAppSecret())))
        paramMtopRequest.setAppSecret(paramString2);
      return;
    }
    catch (Throwable paramMtopRequest)
    {
      bd.d("MtopRequestHelper", "checkAppKeyAndAppSecret", paramMtopRequest);
    }
  }

  // ERROR //
  public static final org.android.agoo.net.async.RequestParams getUrlWithRequestParams(android.content.Context paramContext, MtopRequest paramMtopRequest)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnonnull +5 -> 6
    //   4: aconst_null
    //   5: areturn
    //   6: new 114	org/android/agoo/net/async/RequestParams
    //   9: dup
    //   10: invokespecial 115	org/android/agoo/net/async/RequestParams:<init>	()V
    //   13: astore 8
    //   15: aload 8
    //   17: ldc 117
    //   19: aload_1
    //   20: invokevirtual 120	org/android/agoo/net/mtop/MtopRequest:getApi	()Ljava/lang/String;
    //   23: invokevirtual 124	org/android/agoo/net/async/RequestParams:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   26: aload 8
    //   28: ldc 126
    //   30: aload_1
    //   31: invokevirtual 129	org/android/agoo/net/mtop/MtopRequest:getV	()Ljava/lang/String;
    //   34: invokevirtual 124	org/android/agoo/net/async/RequestParams:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   37: aload_1
    //   38: invokevirtual 131	org/android/agoo/net/mtop/MtopRequest:getTime	()J
    //   41: lstore 4
    //   43: lload 4
    //   45: lstore_2
    //   46: lload 4
    //   48: lconst_0
    //   49: lcmp
    //   50: ifgt +7 -> 57
    //   53: invokestatic 133	org/android/agoo/net/mtop/MtopRequestHelper:a	()J
    //   56: lstore_2
    //   57: aload 8
    //   59: ldc 135
    //   61: new 137	java/lang/StringBuilder
    //   64: dup
    //   65: invokespecial 138	java/lang/StringBuilder:<init>	()V
    //   68: ldc 58
    //   70: invokevirtual 141	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   73: lload_2
    //   74: invokevirtual 144	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   77: invokevirtual 145	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   80: invokevirtual 124	org/android/agoo/net/async/RequestParams:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   83: aload_0
    //   84: ifnull +359 -> 443
    //   87: aload_0
    //   88: invokestatic 150	com/umeng/message/proguard/bD:a	(Landroid/content/Context;)Ljava/lang/String;
    //   91: astore 7
    //   93: aload_0
    //   94: invokestatic 152	com/umeng/message/proguard/bD:b	(Landroid/content/Context;)Ljava/lang/String;
    //   97: astore 6
    //   99: aload 7
    //   101: astore_0
    //   102: aload 8
    //   104: ldc 154
    //   106: aload_0
    //   107: invokevirtual 124	org/android/agoo/net/async/RequestParams:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   110: aload 8
    //   112: ldc 156
    //   114: aload 6
    //   116: invokevirtual 124	org/android/agoo/net/async/RequestParams:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   119: aload 8
    //   121: ldc 158
    //   123: aload_1
    //   124: invokevirtual 161	org/android/agoo/net/mtop/MtopRequest:getTtId	()Ljava/lang/String;
    //   127: invokevirtual 124	org/android/agoo/net/async/RequestParams:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   130: aload 8
    //   132: ldc 163
    //   134: aload_1
    //   135: invokevirtual 99	org/android/agoo/net/mtop/MtopRequest:getAppKey	()Ljava/lang/String;
    //   138: invokevirtual 124	org/android/agoo/net/async/RequestParams:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   141: aload_1
    //   142: invokevirtual 166	org/android/agoo/net/mtop/MtopRequest:getDeviceId	()Ljava/lang/String;
    //   145: invokestatic 94	com/umeng/message/proguard/bF:a	(Ljava/lang/String;)Z
    //   148: ifne +14 -> 162
    //   151: aload 8
    //   153: ldc 168
    //   155: aload_1
    //   156: invokevirtual 166	org/android/agoo/net/mtop/MtopRequest:getDeviceId	()Ljava/lang/String;
    //   159: invokevirtual 124	org/android/agoo/net/async/RequestParams:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   162: aload_1
    //   163: invokevirtual 172	org/android/agoo/net/mtop/MtopRequest:getSysParams	()Ljava/util/Map;
    //   166: astore 7
    //   168: aload 7
    //   170: ifnull +109 -> 279
    //   173: aload 7
    //   175: invokeinterface 178 1 0
    //   180: invokeinterface 184 1 0
    //   185: astore 7
    //   187: aload 7
    //   189: ifnull +90 -> 279
    //   192: aload 7
    //   194: invokeinterface 190 1 0
    //   199: ifeq +80 -> 279
    //   202: aload 7
    //   204: invokeinterface 194 1 0
    //   209: checkcast 196	java/util/Map$Entry
    //   212: astore 9
    //   214: aload 9
    //   216: ifnull -24 -> 192
    //   219: aload 9
    //   221: invokeinterface 199 1 0
    //   226: checkcast 41	java/lang/String
    //   229: invokestatic 94	com/umeng/message/proguard/bF:a	(Ljava/lang/String;)Z
    //   232: ifne -40 -> 192
    //   235: aload 9
    //   237: invokeinterface 202 1 0
    //   242: checkcast 41	java/lang/String
    //   245: invokestatic 94	com/umeng/message/proguard/bF:a	(Ljava/lang/String;)Z
    //   248: ifne -56 -> 192
    //   251: aload 8
    //   253: aload 9
    //   255: invokeinterface 199 1 0
    //   260: checkcast 41	java/lang/String
    //   263: aload 9
    //   265: invokeinterface 202 1 0
    //   270: checkcast 41	java/lang/String
    //   273: invokevirtual 124	org/android/agoo/net/async/RequestParams:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   276: goto -84 -> 192
    //   279: aload_1
    //   280: invokevirtual 205	org/android/agoo/net/mtop/MtopRequest:getParams	()Ljava/util/Map;
    //   283: invokestatic 207	org/android/agoo/net/mtop/MtopRequestHelper:a	(Ljava/util/Map;)Ljava/lang/String;
    //   286: astore 7
    //   288: aload 7
    //   290: invokestatic 94	com/umeng/message/proguard/bF:a	(Ljava/lang/String;)Z
    //   293: ifeq +10 -> 303
    //   296: ldc 11
    //   298: ldc 209
    //   300: invokestatic 211	com/umeng/message/proguard/bd:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   303: aload_1
    //   304: invokevirtual 99	org/android/agoo/net/mtop/MtopRequest:getAppKey	()Ljava/lang/String;
    //   307: astore 9
    //   309: aload 9
    //   311: invokestatic 94	com/umeng/message/proguard/bF:a	(Ljava/lang/String;)Z
    //   314: ifeq +10 -> 324
    //   317: ldc 11
    //   319: ldc 213
    //   321: invokestatic 211	com/umeng/message/proguard/bd:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   324: aload_1
    //   325: invokevirtual 106	org/android/agoo/net/mtop/MtopRequest:getAppSecret	()Ljava/lang/String;
    //   328: astore 10
    //   330: aload_1
    //   331: invokevirtual 216	org/android/agoo/net/mtop/MtopRequest:isHasSigin	()Z
    //   334: ifeq +45 -> 379
    //   337: aload 9
    //   339: aload 10
    //   341: aload_1
    //   342: invokevirtual 120	org/android/agoo/net/mtop/MtopRequest:getApi	()Ljava/lang/String;
    //   345: aload_1
    //   346: invokevirtual 129	org/android/agoo/net/mtop/MtopRequest:getV	()Ljava/lang/String;
    //   349: aload_0
    //   350: aload 6
    //   352: lload_2
    //   353: aload 7
    //   355: aload_1
    //   356: invokevirtual 219	org/android/agoo/net/mtop/MtopRequest:getEcode	()Ljava/lang/String;
    //   359: invokestatic 221	org/android/agoo/net/mtop/MtopRequestHelper:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   362: astore_0
    //   363: aload 7
    //   365: invokestatic 94	com/umeng/message/proguard/bF:a	(Ljava/lang/String;)Z
    //   368: ifne +52 -> 420
    //   371: aload 8
    //   373: ldc 223
    //   375: aload_0
    //   376: invokevirtual 124	org/android/agoo/net/async/RequestParams:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   379: aload 7
    //   381: invokestatic 94	com/umeng/message/proguard/bF:a	(Ljava/lang/String;)Z
    //   384: ifne +46 -> 430
    //   387: aload 8
    //   389: ldc 225
    //   391: aload 7
    //   393: invokevirtual 124	org/android/agoo/net/async/RequestParams:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   396: aload_1
    //   397: invokevirtual 228	org/android/agoo/net/mtop/MtopRequest:getSId	()Ljava/lang/String;
    //   400: invokestatic 94	com/umeng/message/proguard/bF:a	(Ljava/lang/String;)Z
    //   403: ifne +52 -> 455
    //   406: aload 8
    //   408: ldc 230
    //   410: aload_1
    //   411: invokevirtual 228	org/android/agoo/net/mtop/MtopRequest:getSId	()Ljava/lang/String;
    //   414: invokevirtual 124	org/android/agoo/net/async/RequestParams:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   417: goto +38 -> 455
    //   420: ldc 11
    //   422: ldc 232
    //   424: invokestatic 211	com/umeng/message/proguard/bd:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   427: goto -48 -> 379
    //   430: ldc 11
    //   432: ldc 209
    //   434: invokestatic 211	com/umeng/message/proguard/bd:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   437: goto -41 -> 396
    //   440: astore_0
    //   441: aconst_null
    //   442: areturn
    //   443: aconst_null
    //   444: astore 6
    //   446: aconst_null
    //   447: astore_0
    //   448: goto -346 -> 102
    //   451: astore_0
    //   452: aload 8
    //   454: areturn
    //   455: aload 8
    //   457: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   6	15	440	java/lang/Throwable
    //   15	43	451	java/lang/Throwable
    //   53	57	451	java/lang/Throwable
    //   57	83	451	java/lang/Throwable
    //   87	99	451	java/lang/Throwable
    //   102	162	451	java/lang/Throwable
    //   162	168	451	java/lang/Throwable
    //   173	187	451	java/lang/Throwable
    //   192	214	451	java/lang/Throwable
    //   219	276	451	java/lang/Throwable
    //   279	303	451	java/lang/Throwable
    //   303	324	451	java/lang/Throwable
    //   324	379	451	java/lang/Throwable
    //   379	396	451	java/lang/Throwable
    //   396	417	451	java/lang/Throwable
    //   420	427	451	java/lang/Throwable
    //   430	437	451	java/lang/Throwable
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.net.mtop.MtopRequestHelper
 * JD-Core Version:    0.6.2
 */