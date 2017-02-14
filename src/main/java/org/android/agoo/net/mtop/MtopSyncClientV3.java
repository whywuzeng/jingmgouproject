package org.android.agoo.net.mtop;

import android.content.Context;
import android.text.TextUtils;
import org.android.agoo.net.async.RequestParams;
import org.android.agoo.net.async.SyncHttpClient;
import org.android.agoo.net.async.SyncHttpClient.a;

public final class MtopSyncClientV3 extends SyncHttpClient
  implements IMtopSynClient
{
  private volatile String a;
  private volatile String b;
  private volatile String c;

  private final String b(String paramString, RequestParams paramRequestParams)
  {
    String str = paramString;
    if (paramRequestParams != null)
    {
      paramRequestParams = paramRequestParams.getParamString();
      str = paramString + "?" + paramRequestParams;
    }
    return str;
  }

  public final Result getV3(Context paramContext, MtopRequest paramMtopRequest)
  {
    try
    {
      MtopRequestHelper.checkAppKeyAndAppSecret(paramMtopRequest, this.a, this.b);
      paramMtopRequest = MtopRequestHelper.getUrlWithRequestParams(paramContext, paramMtopRequest);
      b(this.c, paramMtopRequest);
      paramContext = get(paramContext, this.c, paramMtopRequest);
      paramMtopRequest = paramContext.c;
      if (TextUtils.isEmpty(paramMtopRequest))
      {
        paramContext = new Result();
        paramContext.setSuccess(false);
        paramContext.setRetDesc("request result is null");
        return paramContext;
      }
      paramMtopRequest = MtopResponseHelper.parse(paramMtopRequest);
      paramMtopRequest.setHeaders(paramContext.b);
      return paramMtopRequest;
    }
    catch (Throwable paramContext)
    {
      paramMtopRequest = new Result();
      paramMtopRequest.setSuccess(false);
      paramMtopRequest.setRetDesc(paramContext.getMessage());
    }
    return paramMtopRequest;
  }

  // ERROR //
  public java.util.Map getV3ForRegister(Context paramContext, MtopRequest paramMtopRequest)
  {
    // Byte code:
    //   0: new 102	java/util/HashMap
    //   3: dup
    //   4: invokespecial 103	java/util/HashMap:<init>	()V
    //   7: astore 4
    //   9: aload_2
    //   10: aload_0
    //   11: getfield 40	org/android/agoo/net/mtop/MtopSyncClientV3:a	Ljava/lang/String;
    //   14: aload_0
    //   15: getfield 42	org/android/agoo/net/mtop/MtopSyncClientV3:b	Ljava/lang/String;
    //   18: invokestatic 48	org/android/agoo/net/mtop/MtopRequestHelper:checkAppKeyAndAppSecret	(Lorg/android/agoo/net/mtop/MtopRequest;Ljava/lang/String;Ljava/lang/String;)V
    //   21: aload_1
    //   22: aload_2
    //   23: invokestatic 52	org/android/agoo/net/mtop/MtopRequestHelper:getUrlWithRequestParams	(Landroid/content/Context;Lorg/android/agoo/net/mtop/MtopRequest;)Lorg/android/agoo/net/async/RequestParams;
    //   26: astore_3
    //   27: aload_0
    //   28: aload_0
    //   29: getfield 54	org/android/agoo/net/mtop/MtopSyncClientV3:c	Ljava/lang/String;
    //   32: aload_3
    //   33: invokespecial 56	org/android/agoo/net/mtop/MtopSyncClientV3:b	(Ljava/lang/String;Lorg/android/agoo/net/async/RequestParams;)Ljava/lang/String;
    //   36: astore_2
    //   37: aload_0
    //   38: aload_1
    //   39: aload_0
    //   40: getfield 54	org/android/agoo/net/mtop/MtopSyncClientV3:c	Ljava/lang/String;
    //   43: aload_3
    //   44: invokevirtual 60	org/android/agoo/net/mtop/MtopSyncClientV3:get	(Landroid/content/Context;Ljava/lang/String;Lorg/android/agoo/net/async/RequestParams;)Lorg/android/agoo/net/async/SyncHttpClient$a;
    //   47: astore_3
    //   48: aload_3
    //   49: getfield 63	org/android/agoo/net/async/SyncHttpClient$a:c	Ljava/lang/String;
    //   52: astore_1
    //   53: aload_1
    //   54: invokestatic 69	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   57: ifeq +47 -> 104
    //   60: new 71	org/android/agoo/net/mtop/Result
    //   63: dup
    //   64: invokespecial 72	org/android/agoo/net/mtop/Result:<init>	()V
    //   67: astore_1
    //   68: aload_1
    //   69: iconst_0
    //   70: invokevirtual 76	org/android/agoo/net/mtop/Result:setSuccess	(Z)V
    //   73: aload_1
    //   74: ldc 78
    //   76: invokevirtual 82	org/android/agoo/net/mtop/Result:setRetDesc	(Ljava/lang/String;)V
    //   79: aload 4
    //   81: ldc 105
    //   83: aload_1
    //   84: invokeinterface 111 3 0
    //   89: pop
    //   90: aload 4
    //   92: ldc 113
    //   94: aload_2
    //   95: invokeinterface 111 3 0
    //   100: pop
    //   101: aload 4
    //   103: areturn
    //   104: aload_1
    //   105: invokestatic 88	org/android/agoo/net/mtop/MtopResponseHelper:parse	(Ljava/lang/String;)Lorg/android/agoo/net/mtop/Result;
    //   108: astore_1
    //   109: aload_1
    //   110: aload_3
    //   111: getfield 91	org/android/agoo/net/async/SyncHttpClient$a:b	Ljava/util/Map;
    //   114: invokevirtual 95	org/android/agoo/net/mtop/Result:setHeaders	(Ljava/util/Map;)V
    //   117: aload 4
    //   119: ldc 105
    //   121: aload_1
    //   122: invokeinterface 111 3 0
    //   127: pop
    //   128: aload 4
    //   130: ldc 113
    //   132: aload_2
    //   133: invokeinterface 111 3 0
    //   138: pop
    //   139: aload 4
    //   141: areturn
    //   142: astore_1
    //   143: aconst_null
    //   144: astore_2
    //   145: new 71	org/android/agoo/net/mtop/Result
    //   148: dup
    //   149: invokespecial 72	org/android/agoo/net/mtop/Result:<init>	()V
    //   152: astore_3
    //   153: aload_3
    //   154: iconst_0
    //   155: invokevirtual 76	org/android/agoo/net/mtop/Result:setSuccess	(Z)V
    //   158: aload_3
    //   159: aload_1
    //   160: invokevirtual 98	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   163: invokevirtual 82	org/android/agoo/net/mtop/Result:setRetDesc	(Ljava/lang/String;)V
    //   166: aload_3
    //   167: astore_1
    //   168: goto -51 -> 117
    //   171: astore_1
    //   172: goto -27 -> 145
    //
    // Exception table:
    //   from	to	target	type
    //   9	37	142	java/lang/Throwable
    //   37	101	171	java/lang/Throwable
    //   104	117	171	java/lang/Throwable
  }

  public final void setBaseUrl(String paramString)
  {
    this.c = paramString;
  }

  public final void setDefaultAppSecret(String paramString)
  {
    this.b = paramString;
  }

  public final void setDefaultAppkey(String paramString)
  {
    this.a = paramString;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.net.mtop.MtopSyncClientV3
 * JD-Core Version:    0.6.2
 */