package com.umeng.message;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.common.message.Log;
import com.umeng.common.message.UmengMessageDeviceConfig;
import com.umeng.common.message.b;
import com.umeng.message.entity.UMessage;
import com.umeng.message.proguard.g;
import com.umeng.message.proguard.k;
import com.umeng.message.proguard.k.e;
import com.umeng.message.proguard.l;
import com.umeng.message.protobuffer.PushResponse;
import com.umeng.message.protobuffer.PushResponse.Info;
import com.umeng.message.protobuffer.PushResponse.responseCode;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.android.agoo.IMtopService;
import org.android.agoo.client.MtopProxyRequest;
import org.android.agoo.client.MtopSyncResult;
import org.json.JSONException;
import org.json.JSONObject;

public class UTrack
{
  private static final String a = UTrack.class.getName();
  private static UTrack d;
  private static boolean h = false;
  private static boolean i = false;
  private static boolean j = false;
  private static boolean k = false;
  private JSONObject b;
  private JSONObject c;
  private ScheduledThreadPoolExecutor e;
  private Context f;
  private boolean g;

  private UTrack(Context paramContext)
  {
    this.f = paramContext.getApplicationContext();
    b();
    this.e = new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 4);
  }

  private JSONObject a(JSONObject paramJSONObject, String paramString)
    throws k.e, JSONException, Exception
  {
    String str = k.c(paramString).H().r("application/json").i(paramJSONObject.toString()).b("UTF-8");
    Log.c(a, "sendRequest() url=" + paramString + "\n request = " + paramJSONObject + "\n response = " + str);
    return new JSONObject(str);
  }

  private void a(final String paramString, final int paramInt, long paramLong)
  {
    if (!c())
      return;
    if (TextUtils.isEmpty(paramString))
    {
      Log.b(a, "trackMsgLog: empty msgId");
      return;
    }
    final long l = System.currentTimeMillis();
    try
    {
      MsgLogStore.getInstance(this.f).addLog(paramString, paramInt, l);
      Runnable local1 = new Runnable()
      {
        public void run()
        {
          UTrack.a(UTrack.this, paramString, paramInt, l);
        }
      };
      if ((paramLong > 0L) && (paramInt != 1))
      {
        l = Math.abs(new Random().nextLong() % paramLong);
        Log.c(a, String.format("trackMsgLog(msgId=%s, actionType=%d, random=%d, delay=%d)", new Object[] { paramString, Integer.valueOf(paramInt), Long.valueOf(paramLong), Long.valueOf(l) }));
        this.e.schedule(local1, l, TimeUnit.MILLISECONDS);
        return;
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        localException.printStackTrace();
        Log.c(a, "trackMsgLog: " + localException.toString());
        continue;
        l = 0L;
      }
    }
  }

  private void a(final String paramString1, final String paramString2, final String paramString3)
  {
    if (!c())
      return;
    if (TextUtils.isEmpty(paramString1))
    {
      Log.b(a, "trackMsgLogForAgoo: empty msgId");
      return;
    }
    final long l = System.currentTimeMillis();
    try
    {
      MsgLogStore.getInstance(this.f).addLogForAgoo(paramString1, paramString2, paramString3, l);
      paramString1 = new Runnable()
      {
        public void run()
        {
          UTrack.this.sendMsgLogForAgoo(paramString1, paramString2, paramString3, l);
        }
      };
      this.e.submit(paramString1);
      return;
    }
    catch (Exception localException)
    {
      while (true)
      {
        localException.printStackTrace();
        Log.c(a, "trackMsgLog: " + localException.toString());
      }
    }
  }

  private void b()
  {
    b localb;
    if (this.b == null)
    {
      localb = new b();
      localb.b(this.f, new String[0]);
      localb.a(this.f, new String[] { PushAgent.getInstance(this.f).getMessageAppkey(), PushAgent.getInstance(this.f).getMessageChannel() });
      this.b = new JSONObject();
    }
    try
    {
      localb.b(this.b);
      if (this.c == null)
      {
        localb = new b();
        localb.c(this.f, new String[0]);
        localb.a(this.f, new String[] { PushAgent.getInstance(this.f).getMessageAppkey(), PushAgent.getInstance(this.f).getMessageChannel() });
        this.c = new JSONObject();
      }
    }
    catch (Exception localException1)
    {
      try
      {
        localb.c(this.c);
        return;
        localException1 = localException1;
        localException1.printStackTrace();
      }
      catch (Exception localException2)
      {
        localException2.printStackTrace();
      }
    }
  }

  // ERROR //
  private void b(String paramString, int paramInt, long paramLong)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial 278	com/umeng/message/UTrack:f	()Lorg/json/JSONObject;
    //   6: astore 5
    //   8: aload 5
    //   10: ldc_w 280
    //   13: aload_1
    //   14: invokevirtual 284	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   17: pop
    //   18: aload 5
    //   20: ldc_w 286
    //   23: iload_2
    //   24: invokevirtual 289	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   27: pop
    //   28: aload 5
    //   30: ldc_w 291
    //   33: lload_3
    //   34: invokevirtual 294	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   37: pop
    //   38: new 296	com/umeng/message/proguard/l
    //   41: dup
    //   42: aload_0
    //   43: getfield 64	com/umeng/message/UTrack:f	Landroid/content/Context;
    //   46: invokespecial 298	com/umeng/message/proguard/l:<init>	(Landroid/content/Context;)V
    //   49: astore 6
    //   51: aload 6
    //   53: aload 6
    //   55: aload 5
    //   57: invokevirtual 301	com/umeng/message/proguard/l:a	(Lorg/json/JSONObject;)[B
    //   60: getstatic 306	com/umeng/message/MsgConstant:LOG_ENDPOINT	Ljava/lang/String;
    //   63: invokevirtual 309	com/umeng/message/proguard/l:a	([BLjava/lang/String;)Lcom/umeng/message/protobuffer/PushResponse;
    //   66: astore 5
    //   68: aload 5
    //   70: ifnull +51 -> 121
    //   73: aload 5
    //   75: getfield 315	com/umeng/message/protobuffer/PushResponse:code	Lcom/umeng/message/protobuffer/PushResponse$responseCode;
    //   78: getstatic 320	com/umeng/message/protobuffer/PushResponse$responseCode:SUCCESS	Lcom/umeng/message/protobuffer/PushResponse$responseCode;
    //   81: invokevirtual 324	com/umeng/message/protobuffer/PushResponse$responseCode:equals	(Ljava/lang/Object;)Z
    //   84: ifeq +37 -> 121
    //   87: aload_0
    //   88: getfield 64	com/umeng/message/UTrack:f	Landroid/content/Context;
    //   91: invokestatic 173	com/umeng/message/MsgLogStore:getInstance	(Landroid/content/Context;)Lcom/umeng/message/MsgLogStore;
    //   94: aload_1
    //   95: iload_2
    //   96: invokevirtual 328	com/umeng/message/MsgLogStore:removeLog	(Ljava/lang/String;I)Z
    //   99: pop
    //   100: iload_2
    //   101: ifeq +20 -> 121
    //   104: iload_2
    //   105: iconst_3
    //   106: if_icmpeq +15 -> 121
    //   109: aload_0
    //   110: getfield 64	com/umeng/message/UTrack:f	Landroid/content/Context;
    //   113: invokestatic 173	com/umeng/message/MsgLogStore:getInstance	(Landroid/content/Context;)Lcom/umeng/message/MsgLogStore;
    //   116: aload_1
    //   117: invokevirtual 332	com/umeng/message/MsgLogStore:removeLogIdType	(Ljava/lang/String;)Z
    //   120: pop
    //   121: aload_0
    //   122: monitorexit
    //   123: return
    //   124: astore_1
    //   125: aload_1
    //   126: invokevirtual 333	org/json/JSONException:printStackTrace	()V
    //   129: getstatic 43	com/umeng/message/UTrack:a	Ljava/lang/String;
    //   132: aload_1
    //   133: invokevirtual 334	org/json/JSONException:toString	()Ljava/lang/String;
    //   136: invokestatic 140	com/umeng/common/message/Log:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   139: goto -18 -> 121
    //   142: astore_1
    //   143: aload_0
    //   144: monitorexit
    //   145: aload_1
    //   146: athrow
    //   147: astore_1
    //   148: aload_1
    //   149: invokevirtual 223	java/lang/Exception:printStackTrace	()V
    //   152: getstatic 43	com/umeng/message/UTrack:a	Ljava/lang/String;
    //   155: aload_1
    //   156: invokevirtual 226	java/lang/Exception:toString	()Ljava/lang/String;
    //   159: invokestatic 140	com/umeng/common/message/Log:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   162: goto -41 -> 121
    //
    // Exception table:
    //   from	to	target	type
    //   2	68	124	org/json/JSONException
    //   73	100	124	org/json/JSONException
    //   109	121	124	org/json/JSONException
    //   2	68	142	finally
    //   73	100	142	finally
    //   109	121	142	finally
    //   125	139	142	finally
    //   148	162	142	finally
    //   2	68	147	java/lang/Exception
    //   73	100	147	java/lang/Exception
    //   109	121	147	java/lang/Exception
  }

  private boolean c()
  {
    if (TextUtils.isEmpty(UmengMessageDeviceConfig.getUtdid(this.f)))
    {
      Log.b(a, "UTDID is empty");
      return false;
    }
    if (TextUtils.isEmpty(UmengRegistrar.getRegistrationId(this.f)))
    {
      Log.b(a, "RegistrationId is empty");
      return false;
    }
    return true;
  }

  // ERROR //
  @android.annotation.SuppressLint({"NewApi"})
  private String d()
  {
    // Byte code:
    //   0: invokestatic 364	android/os/Environment:getExternalStorageState	()Ljava/lang/String;
    //   3: ldc_w 366
    //   6: invokevirtual 367	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   9: ifne +5 -> 14
    //   12: aconst_null
    //   13: areturn
    //   14: new 120	java/lang/StringBuilder
    //   17: dup
    //   18: invokespecial 121	java/lang/StringBuilder:<init>	()V
    //   21: invokestatic 371	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   24: invokevirtual 376	java/io/File:getPath	()Ljava/lang/String;
    //   27: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   30: ldc_w 378
    //   33: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   36: aload_0
    //   37: getfield 64	com/umeng/message/UTrack:f	Landroid/content/Context;
    //   40: invokevirtual 381	android/content/Context:getPackageName	()Ljava/lang/String;
    //   43: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   46: ldc_w 383
    //   49: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   52: invokevirtual 135	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   55: astore_2
    //   56: getstatic 43	com/umeng/message/UTrack:a	Ljava/lang/String;
    //   59: new 120	java/lang/StringBuilder
    //   62: dup
    //   63: invokespecial 121	java/lang/StringBuilder:<init>	()V
    //   66: ldc_w 385
    //   69: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   72: aload_2
    //   73: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   76: invokevirtual 135	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   79: invokestatic 140	com/umeng/common/message/Log:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   82: new 373	java/io/File
    //   85: dup
    //   86: aload_2
    //   87: ldc_w 387
    //   90: invokespecial 389	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   93: astore_2
    //   94: aload_2
    //   95: invokevirtual 392	java/io/File:exists	()Z
    //   98: istore_1
    //   99: iload_1
    //   100: ifeq +194 -> 294
    //   103: new 394	java/io/BufferedReader
    //   106: dup
    //   107: new 396	java/io/FileReader
    //   110: dup
    //   111: aload_2
    //   112: invokespecial 399	java/io/FileReader:<init>	(Ljava/io/File;)V
    //   115: invokespecial 402	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   118: astore_3
    //   119: aload_3
    //   120: astore_2
    //   121: aload_3
    //   122: invokevirtual 405	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   125: astore 4
    //   127: aload 4
    //   129: ifnull +57 -> 186
    //   132: aload_3
    //   133: astore_2
    //   134: aload 4
    //   136: ldc_w 407
    //   139: invokevirtual 410	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   142: ifeq -23 -> 119
    //   145: aload_3
    //   146: astore_2
    //   147: aload 4
    //   149: ldc_w 407
    //   152: invokevirtual 413	java/lang/String:length	()I
    //   155: invokevirtual 417	java/lang/String:substring	(I)Ljava/lang/String;
    //   158: astore 4
    //   160: aload_3
    //   161: ifnull +7 -> 168
    //   164: aload_3
    //   165: invokevirtual 420	java/io/BufferedReader:close	()V
    //   168: aload 4
    //   170: areturn
    //   171: astore_2
    //   172: aload_2
    //   173: invokevirtual 421	java/io/IOException:printStackTrace	()V
    //   176: goto -8 -> 168
    //   179: astore_2
    //   180: aload_2
    //   181: invokevirtual 223	java/lang/Exception:printStackTrace	()V
    //   184: aconst_null
    //   185: areturn
    //   186: aload_3
    //   187: ifnull +107 -> 294
    //   190: aload_3
    //   191: invokevirtual 420	java/io/BufferedReader:close	()V
    //   194: aconst_null
    //   195: areturn
    //   196: astore_2
    //   197: aload_2
    //   198: invokevirtual 421	java/io/IOException:printStackTrace	()V
    //   201: aconst_null
    //   202: areturn
    //   203: astore 4
    //   205: aconst_null
    //   206: astore_3
    //   207: aload_3
    //   208: astore_2
    //   209: aload 4
    //   211: invokevirtual 422	java/io/FileNotFoundException:printStackTrace	()V
    //   214: aload_3
    //   215: ifnull +79 -> 294
    //   218: aload_3
    //   219: invokevirtual 420	java/io/BufferedReader:close	()V
    //   222: aconst_null
    //   223: areturn
    //   224: astore_2
    //   225: aload_2
    //   226: invokevirtual 421	java/io/IOException:printStackTrace	()V
    //   229: aconst_null
    //   230: areturn
    //   231: astore 4
    //   233: aconst_null
    //   234: astore_3
    //   235: aload_3
    //   236: astore_2
    //   237: aload 4
    //   239: invokevirtual 421	java/io/IOException:printStackTrace	()V
    //   242: aload_3
    //   243: ifnull +51 -> 294
    //   246: aload_3
    //   247: invokevirtual 420	java/io/BufferedReader:close	()V
    //   250: aconst_null
    //   251: areturn
    //   252: astore_2
    //   253: aload_2
    //   254: invokevirtual 421	java/io/IOException:printStackTrace	()V
    //   257: aconst_null
    //   258: areturn
    //   259: astore_3
    //   260: aconst_null
    //   261: astore_2
    //   262: aload_2
    //   263: ifnull +7 -> 270
    //   266: aload_2
    //   267: invokevirtual 420	java/io/BufferedReader:close	()V
    //   270: aload_3
    //   271: athrow
    //   272: astore_2
    //   273: aload_2
    //   274: invokevirtual 421	java/io/IOException:printStackTrace	()V
    //   277: goto -7 -> 270
    //   280: astore_3
    //   281: goto -19 -> 262
    //   284: astore 4
    //   286: goto -51 -> 235
    //   289: astore 4
    //   291: goto -84 -> 207
    //   294: aconst_null
    //   295: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   164	168	171	java/io/IOException
    //   0	12	179	java/lang/Exception
    //   14	99	179	java/lang/Exception
    //   164	168	179	java/lang/Exception
    //   172	176	179	java/lang/Exception
    //   190	194	179	java/lang/Exception
    //   197	201	179	java/lang/Exception
    //   218	222	179	java/lang/Exception
    //   225	229	179	java/lang/Exception
    //   246	250	179	java/lang/Exception
    //   253	257	179	java/lang/Exception
    //   266	270	179	java/lang/Exception
    //   270	272	179	java/lang/Exception
    //   273	277	179	java/lang/Exception
    //   190	194	196	java/io/IOException
    //   103	119	203	java/io/FileNotFoundException
    //   218	222	224	java/io/IOException
    //   103	119	231	java/io/IOException
    //   246	250	252	java/io/IOException
    //   103	119	259	finally
    //   266	270	272	java/io/IOException
    //   121	127	280	finally
    //   134	145	280	finally
    //   147	160	280	finally
    //   209	214	280	finally
    //   237	242	280	finally
    //   121	127	284	java/io/IOException
    //   134	145	284	java/io/IOException
    //   147	160	284	java/io/IOException
    //   121	127	289	java/io/FileNotFoundException
    //   134	145	289	java/io/FileNotFoundException
    //   147	160	289	java/io/FileNotFoundException
  }

  private JSONObject e()
    throws JSONException
  {
    String str1 = UmengRegistrar.getRegistrationId(this.f);
    String str2 = UmengMessageDeviceConfig.getUtdid(this.f);
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("header", this.b);
    localJSONObject.put("utdid", str2);
    localJSONObject.put("device_token", str1);
    return localJSONObject;
  }

  private JSONObject f()
    throws JSONException
  {
    String str1 = UmengRegistrar.getRegistrationId(this.f);
    String str2 = UmengMessageDeviceConfig.getUtdid(this.f);
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("header", this.c);
    localJSONObject.put("utdid", str2);
    localJSONObject.put("device_token", str1);
    return localJSONObject;
  }

  public static UTrack getInstance(Context paramContext)
  {
    try
    {
      if (d == null)
        d = new UTrack(paramContext);
      paramContext = d;
      return paramContext;
    }
    finally
    {
    }
    throw paramContext;
  }

  void a(UMessage paramUMessage)
  {
    if ((paramUMessage == null) || (paramUMessage.msg_id == null))
      return;
    a(paramUMessage.msg_id, 0, paramUMessage.random_min * 60000L);
  }

  public boolean addAlias(String paramString1, String paramString2)
    throws k.e, JSONException, Exception
  {
    if (TextUtils.isEmpty(paramString2))
      Log.b(a, "addAlias: empty type");
    Object localObject;
    do
    {
      do
        return false;
      while (!c());
      if (MessageSharedPrefs.getInstance(this.f).isAliasSet(paramString1, paramString2))
      {
        Log.c(a, String.format("addAlias: <%s, %s> has been synced to the server before. Ingore this request.", new Object[] { paramString1, paramString2 }));
        return true;
      }
      if ((MessageSharedPrefs.getInstance(this.f).getAliasCount() >= 20) && (!MessageSharedPrefs.getInstance(this.f).isAliaseTypeSet(paramString2)))
      {
        Log.b(a, String.format("addAlias: <%s, %s>, More than 20 types of alias have been added. Ignore this request", new Object[] { paramString1, paramString2 }));
        return false;
      }
      localObject = e();
      ((JSONObject)localObject).put("alias", paramString1);
      ((JSONObject)localObject).put("type", paramString2);
      ((JSONObject)localObject).put("last_alias", MessageSharedPrefs.getInstance(this.f).getLastAlias(paramString2));
      ((JSONObject)localObject).put("ts", System.currentTimeMillis());
      l locall = new l(this.f);
      localObject = locall.a(locall.a((JSONObject)localObject), MsgConstant.ALIAS_ENDPOINT);
      if (localObject != null)
        Log.c(a, "addAlias: " + ((PushResponse)localObject).code + ", " + ((PushResponse)localObject).description);
    }
    while ((localObject == null) || (!((PushResponse)localObject).code.equals(PushResponse.responseCode.SUCCESS)));
    MessageSharedPrefs.getInstance(this.f).addAlias(paramString1, paramString2);
    return true;
  }

  public boolean addExclusiveAlias(String paramString1, String paramString2)
    throws k.e, JSONException, Exception
  {
    if (TextUtils.isEmpty(paramString2))
      Log.b(a, "addExclusiveAlias: empty type");
    Object localObject;
    do
    {
      do
        return false;
      while (!c());
      if (MessageSharedPrefs.getInstance(this.f).isAliasSet(paramString1, paramString2))
      {
        Log.c(a, String.format("addExclusiveAlias: <%s, %s> has been synced to the server before. Ingore this request.", new Object[] { paramString1, paramString2 }));
        return true;
      }
      if ((MessageSharedPrefs.getInstance(this.f).getAliasCount() >= 20) && (!MessageSharedPrefs.getInstance(this.f).isAliaseTypeSet(paramString2)))
      {
        Log.b(a, String.format("addAlias: <%s, %s>, More than 20 types of alias have been added. Ignore this request", new Object[] { paramString1, paramString2 }));
        return false;
      }
      localObject = e();
      ((JSONObject)localObject).put("alias", paramString1);
      ((JSONObject)localObject).put("type", paramString2);
      ((JSONObject)localObject).put("last_alias", MessageSharedPrefs.getInstance(this.f).getLastAlias(paramString2));
      ((JSONObject)localObject).put("ts", System.currentTimeMillis());
      l locall = new l(this.f);
      localObject = locall.a(locall.a((JSONObject)localObject), MsgConstant.ALIAS_EXCLUSIVE_ENDPOINT);
      if (localObject != null)
        Log.c(a, "addExclusiveAlias: " + ((PushResponse)localObject).code + ", " + ((PushResponse)localObject).description);
    }
    while ((localObject == null) || (!((PushResponse)localObject).code.equals(PushResponse.responseCode.SUCCESS)));
    MessageSharedPrefs.getInstance(this.f).addAlias(paramString1, paramString2);
    return true;
  }

  public JSONObject getHeader()
  {
    return this.b;
  }

  public boolean removeAlias(String paramString1, String paramString2)
    throws k.e, JSONException, Exception
  {
    if (TextUtils.isEmpty(paramString2))
      Log.b(a, "removeAlias: empty type");
    Object localObject;
    do
    {
      do
        return false;
      while (!c());
      localObject = e();
      ((JSONObject)localObject).put("alias", paramString1);
      ((JSONObject)localObject).put("type", paramString2);
      ((JSONObject)localObject).put("ts", System.currentTimeMillis());
      l locall = new l(this.f);
      localObject = locall.a(locall.a((JSONObject)localObject), MsgConstant.DELETE_ALIAS_ENDPOINT);
      if (localObject != null)
        Log.c(a, "removeAlias: " + ((PushResponse)localObject).code + ", " + ((PushResponse)localObject).description);
    }
    while ((localObject == null) || (!((PushResponse)localObject).code.equals(PushResponse.responseCode.SUCCESS)));
    MessageSharedPrefs.getInstance(this.f).removeAlias(paramString1, paramString2);
    return true;
  }

  public void sendCachedMsgLog(long paramLong)
  {
    if (!c())
      return;
    if ((h) || (i))
    {
      Log.c(a, "sendCachedMsgLog already in queue, abort this request.");
      return;
    }
    Log.c(a, "sendCachedMsgLog start, set cacheLogSending flag");
    h = true;
    i = true;
    Object localObject = new Runnable()
    {
      public void run()
      {
        try
        {
          ArrayList localArrayList = MsgLogStore.getInstance(UTrack.a(UTrack.this)).getMsgLogs();
          int i = 0;
          while (i < localArrayList.size())
          {
            MsgLogStore.MsgLog localMsgLog = (MsgLogStore.MsgLog)localArrayList.get(i);
            UTrack.a(UTrack.this, localMsgLog.msgId, localMsgLog.actionType, localMsgLog.time);
            i += 1;
          }
          return;
        }
        catch (Throwable localThrowable)
        {
          localThrowable.printStackTrace();
          Log.c(UTrack.a(), localThrowable.toString());
          return;
        }
        finally
        {
          Log.c(UTrack.a(), "sendCachedMsgLog finished, clear cacheLogSending flag");
          UTrack.a(false);
        }
      }
    };
    Log.c(a, String.format("sendCachedMsgLog(delay=%d)", new Object[] { Long.valueOf(paramLong) }));
    this.e.schedule((Runnable)localObject, paramLong, TimeUnit.MILLISECONDS);
    localObject = new Runnable()
    {
      public void run()
      {
        try
        {
          ArrayList localArrayList = MsgLogStore.getInstance(UTrack.a(UTrack.this)).getMsgLogsForAgoo();
          int i = 0;
          while (i < localArrayList.size())
          {
            MsgLogStore.MsgLogForAgoo localMsgLogForAgoo = (MsgLogStore.MsgLogForAgoo)localArrayList.get(i);
            UTrack.this.sendMsgLogForAgoo(localMsgLogForAgoo.msgId, localMsgLogForAgoo.taskId, localMsgLogForAgoo.msgStatus, localMsgLogForAgoo.time);
            i += 1;
          }
          return;
        }
        catch (Throwable localThrowable)
        {
          localThrowable.printStackTrace();
          Log.c(UTrack.a(), localThrowable.toString());
          return;
        }
        finally
        {
          UTrack.b(false);
        }
      }
    };
    this.e.submit((Runnable)localObject);
  }

  public void sendMsgLogForAgoo(String paramString1, String paramString2, String paramString3, long paramLong)
  {
    try
    {
      Log.c(a, "sendMsgLogForAgoo:msgId=" + paramString1 + ",taskId=" + paramString2 + ",msgStatus=" + paramString3 + ",time=" + paramLong);
      try
      {
        MtopProxyRequest localMtopProxyRequest = new MtopProxyRequest();
        localMtopProxyRequest.setApi("mtop.push.msg.report");
        localMtopProxyRequest.setV("1.0");
        if (!TextUtils.isEmpty(paramString2))
          localMtopProxyRequest.putParams("task_id", paramString2);
        localMtopProxyRequest.putParams("messageId", paramString1);
        localMtopProxyRequest.putParams("mesgStatus", paramString3);
        paramString2 = UmengRegistrar.getIMtopService(this.f);
        if ((paramString2 != null) && (paramString2.getV3(this.f, localMtopProxyRequest).isSuccess()))
        {
          MsgLogStore.getInstance(this.f).removeLogForAgoo(paramString1, paramString3);
          if (!paramString3.equals("7"))
            MsgLogStore.getInstance(this.f).removeLogIdTypeForAgoo(paramString1);
        }
        return;
      }
      catch (Exception paramString1)
      {
        while (true)
        {
          paramString1.printStackTrace();
          Log.c(a, paramString1.toString());
        }
      }
    }
    finally
    {
    }
    throw paramString1;
  }

  public void setClearPrevMessage(boolean paramBoolean)
  {
    this.g = paramBoolean;
  }

  public void trackAppLaunch(long paramLong)
  {
    if (!c())
      return;
    if (j)
    {
      Log.c(a, "trackAppLaunch already in queue, abort this request.");
      return;
    }
    Log.c(a, "trackAppLaunch start, set appLaunchSending flag");
    j = true;
    Runnable local5 = new Runnable()
    {
      public void run()
      {
        int j = -1;
        while (true)
        {
          try
          {
            boolean bool = UmengRegistrar.isRegistered(UTrack.a(UTrack.this));
            if (!bool)
              return;
            Object localObject1 = UTrack.b(UTrack.this);
            l locall = new l(UTrack.a(UTrack.this));
            localObject1 = locall.a(locall.a((JSONObject)localObject1), MsgConstant.LAUNCH_ENDPOINT);
            if ((localObject1 != null) && (((PushResponse)localObject1).code.equals(PushResponse.responseCode.SUCCESS)))
            {
              MsgLogStore.getInstance(UTrack.a(UTrack.this)).setMsgConfigInfo_AppLaunchAt(System.currentTimeMillis());
              if (((PushResponse)localObject1).info == null)
                break label290;
              i = ((PushResponse)localObject1).info.launchPolicy.intValue();
              Log.c(UTrack.a(), "launch_policy:" + i);
              if (((PushResponse)localObject1).info != null)
              {
                j = ((PushResponse)localObject1).info.tagPolicy.intValue();
                Log.c(UTrack.a(), "tag_policy:" + j);
              }
              if (i > 0)
                MessageSharedPrefs.getInstance(UTrack.a(UTrack.this)).setAppLaunchLogSendPolicy(i);
              if (j > 0)
                MessageSharedPrefs.getInstance(UTrack.a(UTrack.this)).setTagSendPolicy(j);
            }
            return;
          }
          catch (JSONException localJSONException)
          {
            localJSONException.printStackTrace();
            Log.c(UTrack.a(), localJSONException.toString());
            return;
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
            Log.c(UTrack.a(), localException.toString());
            return;
          }
          finally
          {
            UTrack.c(false);
          }
          label290: int i = -1;
        }
      }
    };
    Log.c(a, String.format("trackAppLaunch(delay=%d)", new Object[] { Long.valueOf(paramLong) }));
    this.e.schedule(local5, paramLong, TimeUnit.MILLISECONDS);
  }

  public void trackMsgClick(UMessage paramUMessage)
  {
    if ((paramUMessage != null) && (paramUMessage.msg_id != null))
      a(paramUMessage.msg_id, 1, paramUMessage.random_min * 60000L);
    if ((paramUMessage != null) && (paramUMessage.message_id != null))
      a(paramUMessage.message_id, paramUMessage.task_id, "8");
    if (this.g)
      ((UmengMessageHandler)PushAgent.getInstance(this.f).getMessageHandler()).setPrevMessage(null);
  }

  public void trackMsgDismissed(UMessage paramUMessage)
  {
    if ((paramUMessage != null) && (paramUMessage.msg_id != null))
      a(paramUMessage.msg_id, 2, paramUMessage.random_min * 60000L);
    if ((paramUMessage != null) && (paramUMessage.message_id != null))
      a(paramUMessage.message_id, paramUMessage.task_id, "9");
    if (this.g)
      ((UmengMessageHandler)PushAgent.getInstance(this.f).getMessageHandler()).setPrevMessage(null);
  }

  public void trackMsgDisplay(UMessage paramUMessage)
  {
    if ((paramUMessage != null) && (paramUMessage.msg_id != null))
      a(paramUMessage.msg_id, 3, paramUMessage.random_min * 60000L);
  }

  public void trackRegister()
  {
    if (!c())
      return;
    if (k)
    {
      Log.c(a, "sendRegisterLog already in queue, abort this request.");
      return;
    }
    Log.c(a, "trackRegisterLog start, set registerSending flag");
    k = true;
    Runnable local6 = new Runnable()
    {
      public void run()
      {
        try
        {
          Object localObject1 = UTrack.b(UTrack.this);
          Object localObject3 = UTrack.c(UTrack.this);
          if (!g.d((String)localObject3))
          {
            Log.c(UTrack.a(), "TestDevice sign =" + (String)localObject3);
            ((JSONObject)localObject1).put("TD", localObject3);
          }
          localObject3 = new l(UTrack.a(UTrack.this));
          localObject1 = ((l)localObject3).a(((l)localObject3).a((JSONObject)localObject1), MsgConstant.REGISTER_ENDPOINT);
          if ((localObject1 != null) && (((PushResponse)localObject1).code.equals(PushResponse.responseCode.SUCCESS)))
            UmengRegistrar.a(UTrack.a(UTrack.this), true);
          return;
        }
        catch (k.e locale)
        {
          locale.printStackTrace();
          return;
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
          return;
        }
        finally
        {
          UTrack.d(false);
        }
      }
    };
    Log.c(a, String.format("trackRegister(delay=%d)", new Object[] { Integer.valueOf(0) }));
    this.e.schedule(local6, 0L, TimeUnit.MILLISECONDS);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.UTrack
 * JD-Core Version:    0.6.2
 */