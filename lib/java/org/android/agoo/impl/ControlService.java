package org.android.agoo.impl;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import com.umeng.message.proguard.aT;
import com.umeng.message.proguard.aU;
import com.umeng.message.proguard.aV;
import com.umeng.message.proguard.aW;
import com.umeng.message.proguard.bD;
import com.umeng.message.proguard.bE;
import com.umeng.message.proguard.bQ;
import com.umeng.message.proguard.bQ.a;
import com.umeng.message.proguard.bd;
import com.umeng.message.proguard.bl;
import com.umeng.message.proguard.bw;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import org.android.Config;
import org.android.agoo.IControlService;
import org.android.agoo.a.a;
import org.android.agoo.callback.IControlCallBack;
import org.android.agoo.intent.IntentUtil;
import org.android.agoo.net.async.c;
import org.android.agoo.net.mtop.IMtopSynClient;
import org.android.agoo.net.mtop.MtopRequest;
import org.android.agoo.net.mtop.MtopSyncClientV3;
import org.android.agoo.net.mtop.Result;
import org.android.agoo.service.AgooService;
import org.android.agoo.service.IMessageService;
import org.android.agoo.service.IMessageService.Stub;
import org.json.JSONException;
import org.json.JSONObject;

public final class ControlService
  implements IControlService
{
  private static final String a = "SERVICE_NOT_AVAILABLE";
  private static final String b = "HAS_RETTY_REGISTER";
  private static final String c = "org.rome.android.ipp.intent.action.PINGA";
  private static final String d = "ControlService";
  private static final Random e = new Random();
  private static final int f = 5;
  private static final int g = 10000;
  private static final String h = "4";
  private static final IMtopSynClient j = new MtopSyncClientV3();
  private Context i = null;
  private final ServiceConnection k = new ServiceConnection()
  {
    public final void onServiceConnected(ComponentName paramAnonymousComponentName, IBinder paramAnonymousIBinder)
    {
      paramAnonymousComponentName = null;
      try
      {
        paramAnonymousIBinder = IMessageService.Stub.asInterface(paramAnonymousIBinder);
        paramAnonymousComponentName = paramAnonymousIBinder;
        label9: if (paramAnonymousComponentName != null);
        try
        {
          bd.d("ControlService", "messageService.proble");
          paramAnonymousComponentName.probe();
        }
        catch (Throwable paramAnonymousComponentName)
        {
          try
          {
            while (true)
            {
              if (ControlService.a(ControlService.this) != null)
              {
                bd.c("ControlService", "messageConnection [unbind]");
                ControlService.a(ControlService.this).unbindService(ControlService.b(ControlService.this));
              }
              return;
              paramAnonymousComponentName = paramAnonymousComponentName;
              bd.d("ControlService", "messageConnection", paramAnonymousComponentName);
            }
          }
          catch (Throwable paramAnonymousComponentName)
          {
            bd.d("ControlService", "messageDisconnected", paramAnonymousComponentName);
            return;
          }
        }
      }
      catch (Throwable paramAnonymousIBinder)
      {
        break label9;
      }
    }

    public final void onServiceDisconnected(ComponentName paramAnonymousComponentName)
    {
      bd.c("ControlService", "messageDisconnectedon ServiceDisconnected");
    }
  };
  private final ServiceConnection l = new ServiceConnection()
  {
    public final void onServiceConnected(ComponentName paramAnonymousComponentName, IBinder paramAnonymousIBinder)
    {
      try
      {
        paramAnonymousComponentName = bQ.a.a(paramAnonymousIBinder);
        long l = System.currentTimeMillis();
        paramAnonymousIBinder = new JSONObject();
        paramAnonymousIBinder.put("packageName", ControlService.a(ControlService.this).getPackageName());
        paramAnonymousIBinder.put("data", "");
        paramAnonymousIBinder.put("from", "agoo");
        paramAnonymousComponentName.a(paramAnonymousIBinder.toString());
        Log.d("ControlService", "ippConnection target time[" + (System.currentTimeMillis() - l) + "]");
      }
      catch (Throwable paramAnonymousComponentName)
      {
        try
        {
          while (true)
          {
            if (ControlService.a(ControlService.this) != null)
            {
              Log.d("ControlService", "ippConnection [unbind]");
              ControlService.a(ControlService.this).unbindService(ControlService.c(ControlService.this));
            }
            return;
            paramAnonymousComponentName = paramAnonymousComponentName;
            Log.d("ControlService", "ippConnection", paramAnonymousComponentName);
          }
        }
        catch (Throwable paramAnonymousComponentName)
        {
          Log.w("ControlService", "ippConnection", paramAnonymousComponentName);
        }
      }
    }

    public final void onServiceDisconnected(ComponentName paramAnonymousComponentName)
    {
      Log.d("ControlService", "ippConnection onServiceDisconnected  ");
    }
  };

  private final String a(Context paramContext, long paramLong)
  {
    Object localObject1 = null;
    try
    {
      localObject4 = new bl(paramContext);
      localObject2 = ((bl)localObject4).f();
    }
    catch (Throwable localThrowable1)
    {
      try
      {
        Object localObject2;
        Object localObject4 = ((bl)localObject4).c();
        localObject1 = localObject4;
        while (true)
        {
          label31: localObject4 = localObject2;
          if (TextUtils.isEmpty((CharSequence)localObject2))
            localObject4 = "unknow";
          localObject2 = localObject1;
          if (TextUtils.isEmpty((CharSequence)localObject1))
            localObject2 = "unknow";
          localObject1 = new StringBuffer();
          ((StringBuffer)localObject1).append(org.android.agoo.a.f(paramContext));
          ((StringBuffer)localObject1).append("|");
          ((StringBuffer)localObject1).append(paramLong);
          ((StringBuffer)localObject1).append("|");
          ((StringBuffer)localObject1).append(System.currentTimeMillis());
          ((StringBuffer)localObject1).append("|");
          ((StringBuffer)localObject1).append((String)localObject4);
          ((StringBuffer)localObject1).append("|");
          ((StringBuffer)localObject1).append((String)localObject2);
          return ((StringBuffer)localObject1).toString();
          localThrowable1 = localThrowable1;
          Object localObject3 = null;
        }
      }
      catch (Throwable localThrowable2)
      {
        break label31;
      }
    }
  }

  private final void a(Context paramContext)
  {
    try
    {
      Object localObject = IntentUtil.createComandIntent(paramContext, "register_retry");
      ((Intent)localObject).setPackage(paramContext.getPackageName());
      localObject = PendingIntent.getBroadcast(paramContext, 0, (Intent)localObject, 1073741824);
      ((AlarmManager)paramContext.getSystemService("alarm")).cancel((PendingIntent)localObject);
      ((PendingIntent)localObject).cancel();
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  private final void a(Context paramContext, Intent paramIntent)
  {
    if (org.android.agoo.a.m(paramContext))
      return;
    org.android.agoo.a.t(paramContext);
    e(paramContext);
  }

  private final void a(Context paramContext, Intent paramIntent, String paramString, IControlCallBack paramIControlCallBack)
  {
    try
    {
      paramString = new JSONObject(paramString).getString("device_id");
      if (TextUtils.isEmpty(paramString))
      {
        a(paramContext, "SERVICE_NOT_AVAILABLE", paramIControlCallBack);
        return;
      }
      org.android.agoo.a.b(paramContext, paramString);
      org.android.agoo.a.s(paramContext);
      org.android.agoo.a.y(paramContext);
      a.a(paramContext, paramIControlCallBack.callAgooElectionReceiver());
      b(paramContext);
      aW.i(paramContext);
      j(paramContext, paramIntent, paramIControlCallBack);
      return;
    }
    catch (Throwable paramIntent)
    {
      a(paramContext, "SERVICE_NOT_AVAILABLE", paramIControlCallBack);
      aW.h(paramContext, "data_parse_error");
    }
  }

  private final void a(Context paramContext, Intent paramIntent, IControlCallBack paramIControlCallBack)
  {
    if (!a(paramContext, paramIControlCallBack))
    {
      bd.c("ControlService", "handleWake[" + paramContext.getPackageName() + "]--->[appkey==null,appSecret==null,ttid==null]");
      return;
    }
    if (!org.android.agoo.a.m(paramContext))
    {
      int m = org.android.agoo.a.u(paramContext);
      paramIControlCallBack = new StringBuilder().append("handleRetryRegister begin,retryCount = ").append(m).append("isRetryRegister=");
      if (m < 3);
      for (boolean bool = true; ; bool = false)
      {
        Log.d("ControlService", bool);
        if (m >= 3)
          break;
        a(paramContext, paramIntent);
        return;
      }
    }
    if (!bl.a(paramContext))
    {
      bd.c("ControlService", "connectManager[network connectedOrConnecting failed]");
      return;
    }
    paramIntent = paramContext.getPackageName();
    String str = a.a(paramContext);
    c localc = new c(this.i, "handleWake");
    LinkedHashMap localLinkedHashMap = Config.getConnectHeader(this.i);
    localLinkedHashMap.put("currentSudoPack", str);
    localc.a(localLinkedHashMap);
    aW.c(paramContext, str, "handleWake");
    if ((!TextUtils.isEmpty(paramIntent)) && (!TextUtils.isEmpty(str)) && (TextUtils.equals(paramIntent, str)))
    {
      a(paramContext, paramIntent, str, paramIControlCallBack, "handleWake");
      return;
    }
    b(paramContext, "handleWake");
  }

  private final void a(Context paramContext, Intent paramIntent, IControlCallBack paramIControlCallBack, String paramString, boolean paramBoolean)
  {
    if ((org.android.agoo.a.m(paramContext)) && (a(paramContext, paramIControlCallBack)))
    {
      String str = org.android.agoo.a.n(paramContext);
      MtopRequest localMtopRequest = new MtopRequest();
      localMtopRequest.setApi("mtop.push.device.reportKickAss");
      localMtopRequest.setV("4.0");
      localMtopRequest.setTtId(org.android.agoo.a.h(paramContext));
      localMtopRequest.setDeviceId(str);
      localMtopRequest.putParams("app_version", org.android.agoo.a.a(paramContext));
      localMtopRequest.putParams("sdk_version", Long.valueOf(org.android.agoo.a.a()));
      localMtopRequest.putParams("app_pack", paramString);
      localMtopRequest.putParams("app_replace", Boolean.valueOf(paramBoolean));
      paramString = j.getV3(paramContext, localMtopRequest);
      bd.c("ControlService", "uninstall--->[result:" + paramString.getData() + "]");
      a(paramContext, paramIntent, paramIControlCallBack, paramString);
    }
  }

  private final void a(Context paramContext, String paramString)
  {
    a.a(paramContext, paramString);
  }

  private final void a(Context paramContext, String paramString1, String paramString2, IControlCallBack paramIControlCallBack)
  {
    Class localClass = paramIControlCallBack.callAgooService();
    bd.c("ControlService", "restart---->[currentSudoPack:" + paramString2 + "][currentPack:" + paramString1 + "]:[stop]");
    if (a(localClass))
    {
      bd.c("ControlService", "disableService---->[" + paramString1 + "/" + paramIControlCallBack.callAgooService() + "]");
      aU.a(paramContext, paramIControlCallBack.callAgooService());
    }
    aV.a(paramContext);
  }

  private final void a(Context paramContext, String paramString1, String paramString2, IControlCallBack paramIControlCallBack, String paramString3)
  {
    Class localClass = paramIControlCallBack.callAgooService();
    bd.c("ControlService", "restart---->[currentSudoPack:" + paramString2 + "]:[start]");
    if (a(localClass))
    {
      bd.c("ControlService", "enabledService---->[" + paramString1 + "/" + paramIControlCallBack.callAgooService() + "]");
      aU.b(paramContext, paramIControlCallBack.callAgooService());
    }
    aV.a(paramContext, paramString3);
  }

  private final void a(Context paramContext, String paramString, IControlCallBack paramIControlCallBack)
  {
    try
    {
      if ("SERVICE_NOT_AVAILABLE".equals(paramString))
      {
        boolean bool = paramIControlCallBack.callRecoverableError(paramContext, paramString);
        int m = org.android.agoo.a.r(paramContext);
        if ((bool) && (m < 5))
        {
          m += 1;
          long l1 = System.currentTimeMillis() + e.nextInt(10000);
          bd.c("ControlService", "registerfailed retrying--->[" + m + "][" + bw.a(l1) + "]ms");
          org.android.agoo.a.a(paramContext, m);
          paramString = IntentUtil.createComandIntent(paramContext, "register_retry");
          paramString.setPackage(paramContext.getPackageName());
          paramString = PendingIntent.getBroadcast(paramContext, 0, paramString, 1073741824);
          ((AlarmManager)paramContext.getSystemService("alarm")).set(1, l1, paramString);
          return;
        }
        bd.c("ControlService", "Not retrying failed operation[" + m + "]");
        return;
      }
    }
    catch (Throwable paramContext)
    {
    }
  }

  private final void a(Context paramContext, Result paramResult, MtopRequest paramMtopRequest, String paramString)
  {
    paramMtopRequest = null;
    if (paramResult != null);
    try
    {
      String str;
      if (paramResult.isSuccess())
      {
        str = "y";
        paramResult = new JSONObject(paramResult.getData()).getString("device_id");
      }
      while (true)
      {
        StringBuffer localStringBuffer = new StringBuffer();
        localStringBuffer.append("utdid=" + aW.c(paramContext));
        localStringBuffer.append("-->");
        localStringBuffer.append("appkey=" + org.android.agoo.a.f(paramContext));
        localStringBuffer.append("-->");
        localStringBuffer.append("startTime=" + bw.a(System.currentTimeMillis()));
        localStringBuffer.append("-->");
        localStringBuffer.append("ret=" + str);
        localStringBuffer.append("-->");
        localStringBuffer.append("fail_reasons=" + paramMtopRequest);
        localStringBuffer.append("-->");
        localStringBuffer.append("deviceId=" + paramResult);
        localStringBuffer.append("-->");
        localStringBuffer.append("sdkVersion=" + org.android.agoo.a.a());
        localStringBuffer.append("-->");
        localStringBuffer.append("requestUrl=" + paramString);
        localStringBuffer.append("-->");
        localStringBuffer.append("actiontype=register");
        aW.d(paramContext, str, localStringBuffer.toString());
        return;
        str = "n";
        if (paramResult != null)
        {
          paramMtopRequest = paramResult.getRetCode();
          paramResult = null;
        }
        else
        {
          paramMtopRequest = "fail_reasons == null";
          paramResult = null;
        }
      }
    }
    catch (Throwable paramResult)
    {
      aW.d(paramContext, "n", paramResult.toString());
    }
  }

  private final void a(Context paramContext, boolean paramBoolean, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    try
    {
      String str2 = Config.getDeviceToken(paramContext);
      if (paramBoolean);
      for (String str1 = "y"; ; str1 = "n")
      {
        localObject1 = localObject2;
        StringBuffer localStringBuffer = new StringBuffer();
        localObject1 = localObject2;
        localStringBuffer.append("utdid=" + aW.c(paramContext));
        localObject1 = localObject2;
        localStringBuffer.append("-->");
        localObject1 = localObject2;
        localStringBuffer.append("appkey=" + org.android.agoo.a.f(paramContext));
        localObject1 = localObject2;
        localStringBuffer.append("-->");
        localObject1 = localObject2;
        localStringBuffer.append("startTime=" + bw.a(System.currentTimeMillis()));
        localObject1 = localObject2;
        localStringBuffer.append("-->");
        localObject1 = localObject2;
        localStringBuffer.append("ret=" + str1);
        localObject1 = localObject2;
        localStringBuffer.append("-->");
        localObject1 = localObject2;
        localStringBuffer.append("categries=" + paramString1);
        localObject1 = localObject2;
        localStringBuffer.append("-->");
        localObject1 = localObject2;
        localStringBuffer.append("currentPack=" + paramString2);
        localObject1 = localObject2;
        localStringBuffer.append("-->");
        localObject1 = localObject2;
        localStringBuffer.append("currentSudoPack=" + paramString3);
        localObject1 = localObject2;
        localStringBuffer.append("-->");
        localObject1 = localObject2;
        localStringBuffer.append("fail_reasons=" + paramString4);
        localObject1 = localObject2;
        localStringBuffer.append("-->");
        localObject1 = localObject2;
        localStringBuffer.append("deviceId=" + str2);
        localObject1 = localObject2;
        localStringBuffer.append("-->");
        localObject1 = localObject2;
        localStringBuffer.append("sdkVersion=" + org.android.agoo.a.a());
        localObject1 = localObject2;
        localStringBuffer.append("-->");
        localObject1 = localObject2;
        localStringBuffer.append("actiontype=getNoticeElectionTrace");
        localObject1 = localObject2;
        paramString1 = localStringBuffer.toString();
        localObject1 = paramString1;
        aW.j(paramContext, paramString1);
        return;
      }
    }
    catch (Throwable paramString1)
    {
      aW.j(paramContext, (String)localObject1);
    }
  }

  private final boolean a(Context paramContext, Intent paramIntent, IControlCallBack paramIControlCallBack, Result paramResult)
  {
    paramIntent = paramResult.getRetCode();
    if (!TextUtils.isEmpty(paramIntent))
    {
      bd.d("ControlService", "checkMtopResultFailed---->[" + paramIntent + "]");
      aW.h(paramContext, paramIntent);
      if (TextUtils.equals(paramIntent, "ERRCODE_AUTH_REJECT"))
      {
        paramResult = IntentUtil.createComandIntent(paramContext, "error");
        paramResult.setPackage(paramContext.getPackageName());
        paramResult.putExtra("error", paramIntent);
        n(paramContext, paramResult, paramIControlCallBack);
        return false;
      }
    }
    return true;
  }

  private final boolean a(Context paramContext, IControlCallBack paramIControlCallBack)
  {
    String str1 = org.android.agoo.a.f(paramContext);
    String str2 = org.android.agoo.a.h(paramContext);
    Intent localIntent = IntentUtil.createComandIntent(paramContext, "error");
    localIntent.setPackage(paramContext.getPackageName());
    if (TextUtils.isEmpty(str1))
    {
      localIntent.putExtra("error", "ERROR_APPKEY_NULL");
      n(paramContext, localIntent, paramIControlCallBack);
      return false;
    }
    if (TextUtils.isEmpty(str2))
    {
      localIntent.putExtra("error", "ERROR_TTID_NULL");
      n(paramContext, localIntent, paramIControlCallBack);
      return false;
    }
    j.setDefaultAppkey(str1);
    paramIControlCallBack = org.android.agoo.a.j(paramContext);
    if ((TextUtils.isEmpty(paramIControlCallBack)) && (!org.android.agoo.a.E(paramContext)))
    {
      localIntent.putExtra("error", "ERROR_APPSECRET_NULL");
      return false;
    }
    j.setDefaultAppSecret(paramIControlCallBack);
    j.setBaseUrl(org.android.agoo.a.G(paramContext));
    return true;
  }

  private final boolean a(Class<?> paramClass)
  {
    return (paramClass != null) && (TextUtils.equals(paramClass.getSuperclass().getName(), AgooService.class.getName()));
  }

  private final void b(Context paramContext)
  {
    Intent localIntent = IntentUtil.createComandIntent(paramContext, "registration");
    localIntent.setPackage(paramContext.getPackageName());
    paramContext.sendBroadcast(localIntent);
  }

  // ERROR //
  private final void b(Context paramContext, Intent paramIntent, IControlCallBack paramIControlCallBack)
  {
    // Byte code:
    //   0: aload_2
    //   1: ldc_w 545
    //   4: invokevirtual 548	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   7: astore 8
    //   9: aload 8
    //   11: astore 9
    //   13: aload 8
    //   15: invokestatic 86	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   18: ifne +92 -> 110
    //   21: aload 8
    //   23: astore 9
    //   25: aload 8
    //   27: invokevirtual 551	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   30: ldc_w 553
    //   33: invokestatic 557	android/text/TextUtils:indexOf	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)I
    //   36: iconst_m1
    //   37: if_icmpne +73 -> 110
    //   40: aload_1
    //   41: invokestatic 258	org/android/agoo/impl/a:a	(Landroid/content/Context;)Ljava/lang/String;
    //   44: astore 9
    //   46: new 260	org/android/agoo/net/async/c
    //   49: dup
    //   50: aload_0
    //   51: getfield 58	org/android/agoo/impl/ControlService:i	Landroid/content/Context;
    //   54: ldc_w 559
    //   57: invokespecial 264	org/android/agoo/net/async/c:<init>	(Landroid/content/Context;Ljava/lang/String;)V
    //   60: astore 10
    //   62: aload_0
    //   63: getfield 58	org/android/agoo/impl/ControlService:i	Landroid/content/Context;
    //   66: invokestatic 270	org/android/Config:getConnectHeader	(Landroid/content/Context;)Ljava/util/LinkedHashMap;
    //   69: astore 11
    //   71: aload 11
    //   73: ldc_w 272
    //   76: aload 9
    //   78: invokevirtual 278	java/util/LinkedHashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   81: pop
    //   82: aload 10
    //   84: aload 11
    //   86: invokevirtual 281	org/android/agoo/net/async/c:a	(Ljava/util/LinkedHashMap;)V
    //   89: aload_1
    //   90: aload 9
    //   92: ldc_w 559
    //   95: invokestatic 284	com/umeng/message/proguard/aW:c	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)V
    //   98: aload_0
    //   99: aload_1
    //   100: ldc_w 559
    //   103: invokespecial 292	org/android/agoo/impl/ControlService:b	(Landroid/content/Context;Ljava/lang/String;)V
    //   106: aload 8
    //   108: astore 9
    //   110: aload_2
    //   111: ldc_w 561
    //   114: iconst_0
    //   115: invokevirtual 565	android/content/Intent:getBooleanExtra	(Ljava/lang/String;Z)Z
    //   118: ifeq +4 -> 122
    //   121: return
    //   122: aload_1
    //   123: invokestatic 159	org/android/agoo/a:m	(Landroid/content/Context;)Z
    //   126: ifne +12 -> 138
    //   129: ldc 23
    //   131: ldc_w 567
    //   134: invokestatic 231	com/umeng/message/proguard/bd:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   137: return
    //   138: aload_2
    //   139: ldc_w 569
    //   142: invokevirtual 548	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   145: astore 13
    //   147: aload_2
    //   148: ldc_w 571
    //   151: invokevirtual 548	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   154: astore 8
    //   156: aload_2
    //   157: ldc_w 573
    //   160: invokevirtual 548	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   163: astore 14
    //   165: aload_0
    //   166: aload_1
    //   167: aload_2
    //   168: ldc_w 575
    //   171: ldc2_w 576
    //   174: invokevirtual 581	android/content/Intent:getLongExtra	(Ljava/lang/String;J)J
    //   177: invokestatic 334	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   180: invokevirtual 584	java/lang/Long:longValue	()J
    //   183: invokespecial 586	org/android/agoo/impl/ControlService:a	(Landroid/content/Context;J)Ljava/lang/String;
    //   186: astore 10
    //   188: aload_2
    //   189: ldc_w 588
    //   192: invokevirtual 548	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   195: astore 11
    //   197: aload 8
    //   199: invokestatic 86	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   202: ifeq +73 -> 275
    //   205: aload_2
    //   206: ldc_w 590
    //   209: invokevirtual 548	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   212: astore_3
    //   213: aload_1
    //   214: invokestatic 595	com/umeng/message/proguard/bi:a	(Landroid/content/Context;)Lcom/umeng/message/proguard/bi;
    //   217: aload 13
    //   219: aload 11
    //   221: aload_3
    //   222: aload 9
    //   224: aload 10
    //   226: ldc_w 597
    //   229: ldc 33
    //   231: invokevirtual 600	com/umeng/message/proguard/bi:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   234: ifeq +10 -> 244
    //   237: aload_2
    //   238: ldc_w 590
    //   241: invokevirtual 603	android/content/Intent:removeExtra	(Ljava/lang/String;)V
    //   244: ldc 23
    //   246: ldc_w 605
    //   249: invokestatic 231	com/umeng/message/proguard/bd:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   252: aload_1
    //   253: aload 13
    //   255: invokestatic 607	com/umeng/message/proguard/aW:e	(Landroid/content/Context;Ljava/lang/String;)V
    //   258: return
    //   259: astore 10
    //   261: aconst_null
    //   262: astore 10
    //   264: goto -76 -> 188
    //   267: astore 11
    //   269: aconst_null
    //   270: astore 11
    //   272: goto -75 -> 197
    //   275: aload_2
    //   276: ldc_w 609
    //   279: invokevirtual 548	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   282: astore 12
    //   284: ldc_w 611
    //   287: aload 12
    //   289: invokestatic 288	android/text/TextUtils:equals	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
    //   292: ifeq +155 -> 447
    //   295: aload_1
    //   296: invokestatic 296	org/android/agoo/a:n	(Landroid/content/Context;)Ljava/lang/String;
    //   299: aload 8
    //   301: iconst_0
    //   302: invokestatic 616	com/umeng/message/proguard/by:b	(Ljava/lang/String;Ljava/lang/String;I)Ljava/lang/String;
    //   305: astore 12
    //   307: aload 12
    //   309: astore 8
    //   311: aload 8
    //   313: invokestatic 86	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   316: istore 5
    //   318: iload 5
    //   320: ifeq +247 -> 567
    //   323: aload_2
    //   324: ldc_w 590
    //   327: invokevirtual 548	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   330: astore_3
    //   331: aload_1
    //   332: invokestatic 595	com/umeng/message/proguard/bi:a	(Landroid/content/Context;)Lcom/umeng/message/proguard/bi;
    //   335: aload 13
    //   337: aload 11
    //   339: aload_3
    //   340: aload 9
    //   342: aload 10
    //   344: ldc_w 618
    //   347: ldc 33
    //   349: invokevirtual 600	com/umeng/message/proguard/bi:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   352: ifeq +10 -> 362
    //   355: aload_2
    //   356: ldc_w 590
    //   359: invokevirtual 603	android/content/Intent:removeExtra	(Ljava/lang/String;)V
    //   362: aload_1
    //   363: aload 13
    //   365: aload 8
    //   367: invokestatic 620	com/umeng/message/proguard/aW:b	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)V
    //   370: return
    //   371: astore_3
    //   372: aload_2
    //   373: ldc_w 590
    //   376: invokevirtual 548	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   379: astore 12
    //   381: aload_1
    //   382: invokestatic 595	com/umeng/message/proguard/bi:a	(Landroid/content/Context;)Lcom/umeng/message/proguard/bi;
    //   385: aload 13
    //   387: aload 11
    //   389: aload 12
    //   391: aload 9
    //   393: aload 10
    //   395: ldc_w 618
    //   398: ldc 33
    //   400: invokevirtual 600	com/umeng/message/proguard/bi:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   403: ifeq +10 -> 413
    //   406: aload_2
    //   407: ldc_w 590
    //   410: invokevirtual 603	android/content/Intent:removeExtra	(Ljava/lang/String;)V
    //   413: ldc 23
    //   415: new 217	java/lang/StringBuilder
    //   418: dup
    //   419: invokespecial 218	java/lang/StringBuilder:<init>	()V
    //   422: ldc_w 622
    //   425: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   428: aload 8
    //   430: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   433: ldc_w 358
    //   436: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   439: invokevirtual 226	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   442: aload_3
    //   443: invokestatic 625	com/umeng/message/proguard/bd:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   446: return
    //   447: ldc_w 627
    //   450: aload 12
    //   452: invokestatic 288	android/text/TextUtils:equals	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
    //   455: ifeq +22 -> 477
    //   458: aload_1
    //   459: invokestatic 296	org/android/agoo/a:n	(Landroid/content/Context;)Ljava/lang/String;
    //   462: aload 8
    //   464: iconst_1
    //   465: invokestatic 616	com/umeng/message/proguard/by:b	(Ljava/lang/String;Ljava/lang/String;I)Ljava/lang/String;
    //   468: astore 12
    //   470: aload 12
    //   472: astore 8
    //   474: goto -163 -> 311
    //   477: ldc_w 629
    //   480: aload 12
    //   482: invokestatic 288	android/text/TextUtils:equals	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
    //   485: ifeq +22 -> 507
    //   488: aload_1
    //   489: invokestatic 296	org/android/agoo/a:n	(Landroid/content/Context;)Ljava/lang/String;
    //   492: aload 8
    //   494: iconst_2
    //   495: invokestatic 616	com/umeng/message/proguard/by:b	(Ljava/lang/String;Ljava/lang/String;I)Ljava/lang/String;
    //   498: astore 12
    //   500: aload 12
    //   502: astore 8
    //   504: goto -193 -> 311
    //   507: ldc_w 631
    //   510: aload 12
    //   512: invokestatic 288	android/text/TextUtils:equals	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
    //   515: ifeq +22 -> 537
    //   518: aload_1
    //   519: invokestatic 634	org/android/agoo/a:B	(Landroid/content/Context;)Ljava/lang/String;
    //   522: aload 8
    //   524: iconst_1
    //   525: invokestatic 616	com/umeng/message/proguard/by:b	(Ljava/lang/String;Ljava/lang/String;I)Ljava/lang/String;
    //   528: astore 12
    //   530: aload 12
    //   532: astore 8
    //   534: goto -223 -> 311
    //   537: ldc_w 636
    //   540: aload 12
    //   542: invokestatic 288	android/text/TextUtils:equals	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
    //   545: ifeq +465 -> 1010
    //   548: aload_1
    //   549: invokestatic 634	org/android/agoo/a:B	(Landroid/content/Context;)Ljava/lang/String;
    //   552: aload 8
    //   554: iconst_2
    //   555: invokestatic 616	com/umeng/message/proguard/by:b	(Ljava/lang/String;Ljava/lang/String;I)Ljava/lang/String;
    //   558: astore 12
    //   560: aload 12
    //   562: astore 8
    //   564: goto -253 -> 311
    //   567: aload_2
    //   568: ldc_w 609
    //   571: invokevirtual 603	android/content/Intent:removeExtra	(Ljava/lang/String;)V
    //   574: aload_2
    //   575: ldc_w 571
    //   578: aload 8
    //   580: invokevirtual 499	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   583: pop
    //   584: aload_2
    //   585: ldc_w 590
    //   588: invokevirtual 548	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   591: astore 12
    //   593: aload_1
    //   594: invokestatic 595	com/umeng/message/proguard/bi:a	(Landroid/content/Context;)Lcom/umeng/message/proguard/bi;
    //   597: aload 13
    //   599: aload 11
    //   601: aload 12
    //   603: aload 9
    //   605: aload 10
    //   607: aconst_null
    //   608: ldc 33
    //   610: invokevirtual 600	com/umeng/message/proguard/bi:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   613: ifeq +10 -> 623
    //   616: aload_2
    //   617: ldc_w 590
    //   620: invokevirtual 603	android/content/Intent:removeExtra	(Ljava/lang/String;)V
    //   623: aload_1
    //   624: invokestatic 595	com/umeng/message/proguard/bi:a	(Landroid/content/Context;)Lcom/umeng/message/proguard/bi;
    //   627: aload 13
    //   629: invokevirtual 639	com/umeng/message/proguard/bi:b	(Ljava/lang/String;)Z
    //   632: ifne -511 -> 121
    //   635: aload_2
    //   636: ldc_w 641
    //   639: invokevirtual 548	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   642: astore 12
    //   644: aload 12
    //   646: invokestatic 86	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   649: ifne +39 -> 688
    //   652: aload 12
    //   654: ldc_w 611
    //   657: invokestatic 288	android/text/TextUtils:equals	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
    //   660: ifeq +28 -> 688
    //   663: aload 8
    //   665: invokevirtual 645	java/lang/String:hashCode	()I
    //   668: istore 4
    //   670: aload_1
    //   671: invokestatic 595	com/umeng/message/proguard/bi:a	(Landroid/content/Context;)Lcom/umeng/message/proguard/bi;
    //   674: aload 13
    //   676: iload 4
    //   678: invokevirtual 648	com/umeng/message/proguard/bi:a	(Ljava/lang/String;I)Z
    //   681: istore 5
    //   683: iload 5
    //   685: ifne -564 -> 121
    //   688: ldc 23
    //   690: new 217	java/lang/StringBuilder
    //   693: dup
    //   694: invokespecial 218	java/lang/StringBuilder:<init>	()V
    //   697: ldc_w 650
    //   700: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   703: aload 8
    //   705: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   708: ldc_w 652
    //   711: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   714: aload 9
    //   716: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   719: ldc_w 358
    //   722: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   725: invokevirtual 226	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   728: invokestatic 231	com/umeng/message/proguard/bd:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   731: aload_1
    //   732: aload 13
    //   734: invokestatic 653	com/umeng/message/proguard/aW:b	(Landroid/content/Context;Ljava/lang/String;)V
    //   737: aload_2
    //   738: ldc_w 655
    //   741: invokevirtual 548	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   744: invokestatic 661	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   747: istore 4
    //   749: aload_2
    //   750: ldc_w 663
    //   753: invokevirtual 548	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   756: astore 9
    //   758: aload 9
    //   760: invokestatic 86	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   763: ifne +32 -> 795
    //   766: aload 9
    //   768: ldc_w 611
    //   771: invokestatic 288	android/text/TextUtils:equals	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
    //   774: ifeq +21 -> 795
    //   777: aload_1
    //   778: invokestatic 595	com/umeng/message/proguard/bi:a	(Landroid/content/Context;)Lcom/umeng/message/proguard/bi;
    //   781: aload 13
    //   783: aload 8
    //   785: aload 14
    //   787: iload 4
    //   789: invokevirtual 666	com/umeng/message/proguard/bi:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V
    //   792: return
    //   793: astore 9
    //   795: aload_2
    //   796: ldc_w 668
    //   799: invokevirtual 548	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   802: astore 9
    //   804: aload 9
    //   806: invokestatic 86	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   809: ifne +87 -> 896
    //   812: aload_1
    //   813: invokestatic 595	com/umeng/message/proguard/bi:a	(Landroid/content/Context;)Lcom/umeng/message/proguard/bi;
    //   816: aload 13
    //   818: aload 8
    //   820: aload 14
    //   822: aload 9
    //   824: iload 4
    //   826: invokevirtual 671	com/umeng/message/proguard/bi:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V
    //   829: aload_2
    //   830: ldc_w 668
    //   833: invokevirtual 603	android/content/Intent:removeExtra	(Ljava/lang/String;)V
    //   836: return
    //   837: astore 12
    //   839: aload_2
    //   840: ldc_w 590
    //   843: invokevirtual 548	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   846: astore 12
    //   848: aload_1
    //   849: invokestatic 595	com/umeng/message/proguard/bi:a	(Landroid/content/Context;)Lcom/umeng/message/proguard/bi;
    //   852: aload 13
    //   854: aload 11
    //   856: aload 12
    //   858: aload 9
    //   860: aload 10
    //   862: ldc_w 673
    //   865: ldc 33
    //   867: invokevirtual 600	com/umeng/message/proguard/bi:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   870: ifeq -182 -> 688
    //   873: aload_2
    //   874: ldc_w 590
    //   877: invokevirtual 603	android/content/Intent:removeExtra	(Ljava/lang/String;)V
    //   880: goto -192 -> 688
    //   883: astore 10
    //   885: goto -197 -> 688
    //   888: astore 9
    //   890: iconst_m1
    //   891: istore 4
    //   893: goto -144 -> 749
    //   896: aload_1
    //   897: invokestatic 677	org/android/agoo/a:D	(Landroid/content/Context;)J
    //   900: lstore 6
    //   902: lload 6
    //   904: ldc2_w 576
    //   907: lcmp
    //   908: ifeq +40 -> 948
    //   911: aload_1
    //   912: invokestatic 595	com/umeng/message/proguard/bi:a	(Landroid/content/Context;)Lcom/umeng/message/proguard/bi;
    //   915: aload 13
    //   917: aload 8
    //   919: aload 14
    //   921: new 217	java/lang/StringBuilder
    //   924: dup
    //   925: invokespecial 218	java/lang/StringBuilder:<init>	()V
    //   928: lload 6
    //   930: invokevirtual 460	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   933: ldc_w 679
    //   936: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   939: invokevirtual 226	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   942: iload 4
    //   944: invokevirtual 671	com/umeng/message/proguard/bi:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V
    //   947: return
    //   948: aload_1
    //   949: invokestatic 595	com/umeng/message/proguard/bi:a	(Landroid/content/Context;)Lcom/umeng/message/proguard/bi;
    //   952: aload 13
    //   954: aload 8
    //   956: aload 14
    //   958: iload 4
    //   960: invokevirtual 666	com/umeng/message/proguard/bi:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V
    //   963: aload_3
    //   964: aload_1
    //   965: aload_2
    //   966: invokeinterface 682 3 0
    //   971: return
    //   972: astore 12
    //   974: goto -351 -> 623
    //   977: astore_1
    //   978: goto -565 -> 413
    //   981: astore_3
    //   982: goto -610 -> 372
    //   985: astore_3
    //   986: goto -624 -> 362
    //   989: astore_2
    //   990: goto -746 -> 244
    //   993: astore 8
    //   995: aconst_null
    //   996: astore 9
    //   998: goto -888 -> 110
    //   1001: astore 9
    //   1003: aload 8
    //   1005: astore 9
    //   1007: goto -897 -> 110
    //   1010: goto -699 -> 311
    //
    // Exception table:
    //   from	to	target	type
    //   165	188	259	java/lang/Throwable
    //   188	197	267	java/lang/Throwable
    //   311	318	371	java/lang/Throwable
    //   362	370	371	java/lang/Throwable
    //   567	584	371	java/lang/Throwable
    //   749	792	793	java/lang/Throwable
    //   635	683	837	java/lang/Throwable
    //   839	880	883	java/lang/Throwable
    //   737	749	888	java/lang/Throwable
    //   584	623	972	java/lang/Throwable
    //   372	413	977	java/lang/Throwable
    //   275	307	981	java/lang/Throwable
    //   447	470	981	java/lang/Throwable
    //   477	500	981	java/lang/Throwable
    //   507	530	981	java/lang/Throwable
    //   537	560	981	java/lang/Throwable
    //   323	362	985	java/lang/Throwable
    //   205	244	989	java/lang/Throwable
    //   0	9	993	java/lang/Throwable
    //   13	21	1001	java/lang/Throwable
    //   25	106	1001	java/lang/Throwable
  }

  private final void b(Context paramContext, String paramString)
  {
    String str = a.a(paramContext);
    if (TextUtils.isEmpty(str))
    {
      bd.c("ControlService", "onPingMessage:[currentSudoPack==null][retry election]");
      a(paramContext, paramString);
      return;
    }
    try
    {
      bd.c("ControlService", "messageConnection [bind]");
      paramString = new Intent();
      paramString.setAction("org.agoo.android.intent.action.PING_V4");
      paramString.addCategory(Config.getAgooGroup(paramContext));
      paramString.setPackage(str);
      paramContext.bindService(paramString, this.k, 1);
      return;
    }
    catch (Throwable paramContext)
    {
      bd.d("ControlService", "onPingMessage", paramContext);
    }
  }

  private final void b(Context paramContext, IControlCallBack paramIControlCallBack)
  {
    a.b(paramContext, paramIControlCallBack.callAgooElectionReceiver());
    org.android.agoo.a.x(paramContext);
    bd.a("ControlService", "handleDisableCurrentPack-->[" + paramContext.getPackageName() + "]");
    aV.a(paramContext);
  }

  private final void c(Context paramContext)
  {
    boolean bool = Config.getUnregisterFlag(paramContext);
    Log.d("ControlService", "doUnRegisterTemp,begin,flag=" + bool);
    String str;
    Object localObject2;
    Object localObject1;
    if (bool)
    {
      str = org.android.agoo.a.g(paramContext);
      localObject2 = org.android.agoo.a.k(paramContext);
      localObject1 = org.android.agoo.a.i(paramContext);
      if ((!TextUtils.isEmpty(str)) && (!TextUtils.isEmpty((CharSequence)localObject2)))
        break label69;
    }
    label69: 
    do
    {
      return;
      j.setDefaultAppkey(str);
      j.setDefaultAppSecret((String)localObject2);
      j.setBaseUrl(org.android.agoo.a.G(paramContext));
      str = org.android.agoo.a.o(paramContext);
      localObject2 = new MtopRequest();
      ((MtopRequest)localObject2).setApi("mtop.push.device.unregister");
      ((MtopRequest)localObject2).setV("4.0");
      ((MtopRequest)localObject2).setTtId((String)localObject1);
      ((MtopRequest)localObject2).setDeviceId(str);
      ((MtopRequest)localObject2).putParams("app_version", org.android.agoo.a.a(paramContext));
      ((MtopRequest)localObject2).putParams("sdk_version", Long.valueOf(org.android.agoo.a.a()));
      ((MtopRequest)localObject2).putParams("app_pack", paramContext.getPackageName());
      localObject1 = j.getV3(paramContext, (MtopRequest)localObject2);
      bd.c("ControlService", "unregister--->[server result:" + ((Result)localObject1).getData() + "]");
      localObject1 = ((Result)localObject1).getRetCode();
    }
    while (!TextUtils.isEmpty((CharSequence)localObject1));
    Log.d("ControlService", "doUnRegisterTemp,errorId=" + (String)localObject1);
    Config.setUnregisterFlag(paramContext, false);
  }

  private final void c(Context paramContext, Intent paramIntent, IControlCallBack paramIControlCallBack)
  {
    String str = paramIntent.getStringExtra("id");
    c localc = new c(this.i, "appMessageSuccess");
    LinkedHashMap localLinkedHashMap = Config.getConnectHeader(this.i);
    localLinkedHashMap.put("messageId", str);
    localc.a(localLinkedHashMap);
    if (!paramIControlCallBack.callShouldProcessMessage(paramContext, paramIntent))
      return;
    if (org.android.agoo.a.z(paramContext))
    {
      bd.a("ControlService", "handleMessage[" + paramContext.getPackageName() + "]--->[disable]");
      new c(this.i, "appMessageFailed").a(localLinkedHashMap);
      return;
    }
    b(paramContext, paramIntent, paramIControlCallBack);
  }

  private final void d(Context paramContext)
  {
    try
    {
      if (Config.getPingMessage(paramContext))
      {
        bd.c("ControlService", "ippConnection [bind]");
        Intent localIntent = new Intent();
        localIntent.setAction("org.rome.android.ipp.intent.action.PINGA");
        paramContext.bindService(localIntent, this.l, 1);
      }
      return;
    }
    catch (Throwable paramContext)
    {
      Log.d("ControlService", "ippConnection", paramContext);
    }
  }

  private final void d(Context paramContext, Intent paramIntent, IControlCallBack paramIControlCallBack)
  {
    if (!a(paramContext, paramIControlCallBack))
    {
      bd.c("ControlService", "handleAddPackage---->[appkey or appSecret ===null]");
      return;
    }
    if (!org.android.agoo.a.m(paramContext))
    {
      bd.c("ControlService", "handleAddPackage---->[devicetoken ===null]");
      return;
    }
    if (org.android.agoo.a.H(paramContext) == a.a.a)
    {
      aV.a(paramContext, null);
      return;
    }
    a(paramContext, "handleAddPackage");
  }

  private static final void e(Context paramContext)
  {
    try
    {
      Intent localIntent = IntentUtil.createComandIntent(paramContext, "register");
      localIntent.setPackage(paramContext.getPackageName());
      localIntent.putExtra("HAS_RETTY_REGISTER", true);
      paramContext.sendBroadcast(localIntent);
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  private final void e(Context paramContext, Intent paramIntent, IControlCallBack paramIControlCallBack)
  {
    if ((paramIntent == null) || (paramContext == null));
    String str;
    do
    {
      do
      {
        return;
        if (!org.android.agoo.a.m(paramContext))
        {
          bd.c("ControlService", "handleRemovePackage---->[devicetoken ===null]");
          return;
        }
        str = null;
        localObject = paramIntent.getData();
        if (localObject != null)
          str = ((Uri)localObject).getSchemeSpecificPart();
      }
      while (TextUtils.isEmpty(str));
      boolean bool = paramIntent.getBooleanExtra("android.intent.extra.REPLACING", false);
      bd.c("ControlService", "handleRemovePackage---->[replacing:" + bool + "]");
      a(paramContext, paramIntent, paramIControlCallBack, str, bool);
      paramIntent = a.a(paramContext);
      if (TextUtils.isEmpty(paramIntent))
      {
        a(paramContext, "handleRemovePackage");
        return;
      }
      if (TextUtils.equals(str, paramIntent))
      {
        a(paramContext, "handleRemovePackage");
        return;
      }
      str = paramContext.getPackageName();
    }
    while (!TextUtils.equals(paramIntent, str));
    Object localObject = new c(this.i, "handleRemovePackage");
    LinkedHashMap localLinkedHashMap = Config.getConnectHeader(this.i);
    localLinkedHashMap.put("currentSudoPack", paramIntent);
    ((c)localObject).a(localLinkedHashMap);
    aW.c(paramContext, paramIntent, "handleRemovePackage");
    a(paramContext, str, paramIntent, paramIControlCallBack, "handleRemovePackage");
  }

  private final void f(Context paramContext, Intent paramIntent, IControlCallBack paramIControlCallBack)
  {
    Object localObject = paramIntent.getStringExtra("command");
    bd.c("ControlService", "command --->[" + (String)localObject + "]");
    if (((String)localObject).equals("registration"))
    {
      paramIControlCallBack.callRegistered(paramContext, org.android.agoo.a.n(paramContext));
      d(paramContext, paramIntent, paramIControlCallBack);
    }
    do
    {
      return;
      if (((String)localObject).equals("unregister"))
      {
        o(paramContext, paramIntent, paramIControlCallBack);
        return;
      }
      if (((String)localObject).equals("error"))
      {
        n(paramContext, paramIntent, paramIControlCallBack);
        return;
      }
      if (((String)localObject).equals("register"))
      {
        m(paramContext, paramIntent, paramIControlCallBack);
        return;
      }
      if (((String)localObject).equals("register_retry"))
      {
        a(paramContext, paramIntent);
        return;
      }
      if (((String)localObject).equals("command_other_channel"))
      {
        g(paramContext, paramIntent, paramIControlCallBack);
        return;
      }
      if (((String)localObject).equals("command_restart_sudo"))
      {
        paramIntent = paramContext.getPackageName();
        localObject = a.a(paramContext);
        if ((!TextUtils.isEmpty(paramIntent)) && (!TextUtils.isEmpty((CharSequence)localObject)) && (TextUtils.equals(paramIntent, (CharSequence)localObject)))
          a(paramContext, paramIntent, (String)localObject, paramIControlCallBack, "command_restart_sudo");
        aU.a(this.i);
        Log.d("ControlService", "restartByApp by zhifubao begin.....................");
        return;
      }
      if (((String)localObject).equals("command_bind_user"))
      {
        j(paramContext, paramIntent, paramIControlCallBack);
        return;
      }
      if (((String)localObject).equals("command_unbind_user"))
      {
        k(paramContext, paramIntent, paramIControlCallBack);
        return;
      }
      if (!((String)localObject).equals("agoo_command_restart_sudo_app"))
        break;
      paramIControlCallBack = paramContext.getPackageName();
      paramIntent = a.a(paramContext);
    }
    while ((TextUtils.isEmpty(paramIControlCallBack)) || (TextUtils.isEmpty(paramIntent)));
    paramIControlCallBack = new c(this.i, "restartByApp");
    localObject = Config.getConnectHeader(this.i);
    ((LinkedHashMap)localObject).put("currentSudoPack", paramIntent);
    paramIControlCallBack.a((LinkedHashMap)localObject);
    b(paramContext, "restartByApp");
    aW.c(paramContext, paramIntent, "restartByApp");
    return;
    paramIControlCallBack.callUserCommand(paramContext, paramIntent);
  }

  private final void g(Context paramContext, Intent paramIntent, IControlCallBack paramIControlCallBack)
  {
    String str = paramIntent.getStringExtra("channel_android_device_token");
    paramIntent = paramIntent.getStringExtra("channel_android_device_type");
    if ((!TextUtils.isEmpty(str)) && (!TextUtils.isEmpty(paramIntent)) && (org.android.agoo.a.m(paramContext)) && (a(paramContext, paramIControlCallBack)))
    {
      paramIControlCallBack = org.android.agoo.a.n(paramContext);
      MtopRequest localMtopRequest = new MtopRequest();
      localMtopRequest.setApi("mtop.push.device.bind.android");
      localMtopRequest.setV("5.0");
      localMtopRequest.setDeviceId(paramIControlCallBack);
      localMtopRequest.putParams("tb_app_device_token", paramIControlCallBack);
      localMtopRequest.putParams("android_device_token", str);
      localMtopRequest.putParams("android_device_type", paramIntent);
      j.setBaseUrl(org.android.agoo.a.G(paramContext));
      paramIntent = j.getV3(paramContext, localMtopRequest);
      if (paramIntent.isSuccess())
        Log.d("ControlService", "register GCM success");
    }
    else
    {
      return;
    }
    aW.h(paramContext, paramIntent.getRetCode());
  }

  private final void h(Context paramContext, Intent paramIntent, IControlCallBack paramIControlCallBack)
  {
    boolean bool = false;
    String str2 = paramContext.getPackageName();
    String str3 = paramIntent.getStringExtra("election_result");
    aW.j(paramContext, "handleElectionResult begin");
    Set localSet = paramIntent.getCategories();
    if ((localSet != null) && (!localSet.isEmpty()) && (localSet.contains(Config.getAgooGroup(paramContext))))
    {
      while (true)
      {
        String str1;
        try
        {
          str1 = paramIntent.getStringExtra("eventId");
          if (!org.android.agoo.a.m(paramContext))
          {
            bd.c("ControlService", "handleElection---->[devicetoken == null]");
            paramIntent = str1;
            if (TextUtils.isEmpty(str1))
              paramIntent = "handleElectionResult";
            a(paramContext, false, localSet.toString(), str2, str3, "isRegistered is failed");
            a(paramContext, paramIntent);
            return;
          }
          if (org.android.agoo.a.z(paramContext))
          {
            bd.c("ControlService", "handleElection--->[app:disable]");
            paramIntent = str1;
            if (TextUtils.isEmpty(str1))
              paramIntent = "handleElectionResult";
            a(paramContext, false, localSet.toString(), str2, str3, "currentPack hasDisableApp");
            a(paramContext, paramIntent);
            return;
          }
        }
        catch (Throwable paramIntent)
        {
          a(paramContext, bool, localSet.toString(), str2, str3, paramIntent.toString());
          return;
        }
        Object localObject = paramIntent.getStringExtra("election_source");
        long l1 = paramIntent.getLongExtra("election_timeout", -1L);
        if (TextUtils.isEmpty(str3))
          return;
        if ((TextUtils.isEmpty(str2)) || (TextUtils.isEmpty(str3)) || (!TextUtils.equals(str2, str3)))
          break;
        if (Config.hasNoticeElection(paramContext))
        {
          bd.c("ControlService", "handleElection---->[noticeResult is true, result has came]");
          return;
        }
        Config.setNoticeResult(paramContext, true);
        org.android.agoo.a.a(paramContext, l1, (String)localObject);
        paramIntent = new c(this.i, "handleElectionResult");
        localObject = Config.getConnectHeader(this.i);
        ((LinkedHashMap)localObject).put("currentSudoPack", str3);
        paramIntent.a((LinkedHashMap)localObject);
        aW.c(paramContext, str3, "handleElectionResult");
        paramIntent = str1;
        if (TextUtils.isEmpty(str1))
          paramIntent = "handleElectionResult";
        try
        {
          a(paramContext, true, localSet.toString(), str2, str3, null);
          a(paramContext, str2, str3, paramIControlCallBack, paramIntent);
          return;
        }
        catch (Throwable paramIntent)
        {
          bool = true;
        }
      }
      a(paramContext, false, localSet.toString(), str2, str3, "currentPack != currentSudoPack");
      if (org.android.agoo.a.H(paramContext) != a.a.a)
        a(paramContext, str2, str3, paramIControlCallBack);
    }
  }

  private final void i(Context paramContext, Intent paramIntent, IControlCallBack paramIControlCallBack)
  {
    if ((org.android.agoo.a.m(paramContext)) && (a(paramContext, paramIControlCallBack)))
    {
      Object localObject = org.android.agoo.a.n(paramContext);
      MtopRequest localMtopRequest = new MtopRequest();
      localMtopRequest.setApi("mtop.push.device.unregister");
      localMtopRequest.setV("4.0");
      localMtopRequest.setTtId(org.android.agoo.a.h(paramContext));
      localMtopRequest.setDeviceId((String)localObject);
      localMtopRequest.putParams("app_version", org.android.agoo.a.a(paramContext));
      localMtopRequest.putParams("sdk_version", Long.valueOf(org.android.agoo.a.a()));
      localMtopRequest.putParams("app_pack", paramContext.getPackageName());
      localObject = j.getV3(paramContext, localMtopRequest);
      bd.c("ControlService", "unregister--->[server result:" + ((Result)localObject).getData() + "],result.isSuccess()=" + ((Result)localObject).isSuccess());
      if ((localObject != null) && (!((Result)localObject).isSuccess()))
        Config.setUnregisterFlag(paramContext, true);
      a(paramContext, paramIntent, paramIControlCallBack, (Result)localObject);
    }
  }

  private final void j(Context paramContext, Intent paramIntent, IControlCallBack paramIControlCallBack)
  {
    String str;
    Object localObject1;
    Object localObject2;
    if ((org.android.agoo.a.m(paramContext)) && (a(paramContext, paramIControlCallBack)))
    {
      str = org.android.agoo.a.d(paramContext);
      if (!TextUtils.isEmpty(str))
      {
        localObject1 = org.android.agoo.a.n(paramContext);
        localObject2 = new MtopRequest();
        ((MtopRequest)localObject2).setApi("mtop.push.device.bindUser");
        ((MtopRequest)localObject2).setV("4.0");
        ((MtopRequest)localObject2).setDeviceId((String)localObject1);
        ((MtopRequest)localObject2).setSId(str);
        ((MtopRequest)localObject2).putParams("s_token", str);
        ((MtopRequest)localObject2).putParams("push_token", "ajflajdflajflajflajlfajldfjalfdj");
        localObject1 = j.getV3(paramContext, (MtopRequest)localObject2);
        localObject2 = new StringBuilder().append("doBinderUser--->[server result:");
        if (localObject1 == null)
          break label215;
        str = ((Result)localObject1).getData();
      }
    }
    while (true)
    {
      bd.c("ControlService", str + "]");
      if ((localObject1 == null) || (((Result)localObject1).isSuccess()));
      try
      {
        str = new JSONObject(((Result)localObject1).getData()).getString("push_user_token");
        if (!TextUtils.isEmpty(str))
          org.android.agoo.a.c(paramContext, str);
        label204: a(paramContext, paramIntent, paramIControlCallBack, (Result)localObject1);
        return;
        label215: str = null;
      }
      catch (JSONException localJSONException)
      {
        break label204;
      }
    }
  }

  private final void k(Context paramContext, Intent paramIntent, IControlCallBack paramIControlCallBack)
  {
    Object localObject1;
    Object localObject2;
    if ((org.android.agoo.a.m(paramContext)) && (a(paramContext, paramIControlCallBack)))
    {
      str = Config.getPushUserToken(paramContext);
      if (!TextUtils.isEmpty(str))
      {
        localObject1 = org.android.agoo.a.n(paramContext);
        localObject2 = new MtopRequest();
        ((MtopRequest)localObject2).setApi("mtop.push.device.unBindUser");
        ((MtopRequest)localObject2).setV("4.0");
        ((MtopRequest)localObject2).setDeviceId((String)localObject1);
        ((MtopRequest)localObject2).putParams("push_user_token", str);
        ((MtopRequest)localObject2).putParams("push_token", "ajflajdflajflajflajlfajldfjalfdj");
        localObject1 = j.getV3(paramContext, (MtopRequest)localObject2);
        localObject2 = new StringBuilder().append("doBinderUser--->[server result:");
        if (localObject1 == null)
          break label172;
      }
    }
    label172: for (String str = ((Result)localObject1).getData(); ; str = null)
    {
      bd.c("ControlService", str + "]");
      if (localObject1 != null)
      {
        if (!((Result)localObject1).isSuccess())
          break;
        org.android.agoo.a.C(paramContext);
        org.android.agoo.a.c(paramContext);
      }
      return;
    }
    a(paramContext, paramIntent, paramIControlCallBack, (Result)localObject1);
  }

  private final void l(Context paramContext, Intent paramIntent, IControlCallBack paramIControlCallBack)
  {
    String str = null;
    MtopRequest localMtopRequest = new MtopRequest();
    localMtopRequest.setApi("mtop.push.device.createAndRegister");
    localMtopRequest.setV("4.0");
    localMtopRequest.setTtId(org.android.agoo.a.h(paramContext));
    localMtopRequest.putParams("new_device", "true");
    localMtopRequest.putParams("device_global_id", aW.c(paramContext));
    localMtopRequest.putParams("c0", Build.BRAND);
    localMtopRequest.putParams("c1", Build.MODEL);
    localMtopRequest.putParams("c2", bD.d(paramContext));
    localMtopRequest.putParams("c3", bD.e(paramContext));
    localMtopRequest.putParams("c4", bD.c(paramContext));
    localMtopRequest.putParams("c5", bD.a());
    localMtopRequest.putParams("c6", bD.f(paramContext));
    localMtopRequest.putParams("app_version", org.android.agoo.a.a(paramContext));
    localMtopRequest.putParams("sdk_version", Long.valueOf(org.android.agoo.a.a()));
    localMtopRequest.putParams("package_name", paramContext.getPackageName());
    if (org.android.agoo.a.m(paramContext))
      localMtopRequest.putParams("old_device_id", org.android.agoo.a.n(paramContext));
    Log.d("ControlService", "doRegister app_version=" + org.android.agoo.a.a(paramContext));
    Map localMap = j.getV3ForRegister(paramContext, localMtopRequest);
    Result localResult;
    if (localMap != null)
    {
      localResult = (Result)localMap.get("result");
      str = (String)localMap.get("requestUrl");
    }
    while (true)
    {
      if (localResult != null)
      {
        a(paramContext, localResult, localMtopRequest, str);
        if (!bE.a(localResult.getHeaders(), localResult.getHttpCode()))
          bd.c("ControlService", "register--->[failed]");
        do
        {
          return;
          if (localResult.isSuccess())
          {
            bd.c("ControlService", "register--->[result:" + localResult.getData() + "]");
            Config.setAgooReleaseTime(paramContext, org.android.agoo.a.a());
            a(paramContext, paramIntent, localResult.getData(), paramIControlCallBack);
            return;
          }
        }
        while (a(paramContext, paramIntent, paramIControlCallBack, localResult));
      }
      a(paramContext, localResult, localMtopRequest, str);
      a(paramContext, "SERVICE_NOT_AVAILABLE", paramIControlCallBack);
      return;
      localResult = null;
    }
  }

  private final void m(Context paramContext, Intent paramIntent, IControlCallBack paramIControlCallBack)
  {
    aW.k(paramContext, "utdid=" + aW.c(paramContext));
    if (!a(paramContext, paramIControlCallBack))
    {
      bd.a("ControlService", "handleRegister[" + paramContext.getPackageName() + "]--->[appkey==null,appSecret==null,ttid==null]");
      return;
    }
    boolean bool = paramIntent.getBooleanExtra("HAS_RETTY_REGISTER", false);
    if (!org.android.agoo.a.m(paramContext))
    {
      bd.a("ControlService", "handleRegister[" + paramContext.getPackageName() + "]--->[deviceToken==null][retty:" + bool + "]");
      org.android.agoo.a.y(paramContext);
      if (!bool)
        org.android.agoo.a.s(paramContext);
      aT.a(paramContext);
      a(paramContext);
      l(paramContext, paramIntent, paramIControlCallBack);
      return;
    }
    if (org.android.agoo.a.w(paramContext))
    {
      bd.a("ControlService", "handleRegister[" + paramContext.getPackageName() + "]--->[" + org.android.agoo.a.n(paramContext) + "][register timeout][retty:" + bool + "]");
      if (!bool)
        org.android.agoo.a.s(paramContext);
      a(paramContext);
      l(paramContext, paramIntent, paramIControlCallBack);
      return;
    }
    if (org.android.agoo.a.a(paramContext, true))
    {
      bd.a("ControlService", "handleRegister[" + paramContext.getPackageName() + "]--->[disable]");
      return;
    }
    if (org.android.agoo.a.H(paramContext) == a.a.a)
    {
      aV.a(paramContext, null);
      return;
    }
    a.a(paramContext, paramIControlCallBack.callAgooElectionReceiver());
    aU.a(paramContext, new Class[] { paramIControlCallBack.callAgooMessageReceiver(), paramIControlCallBack.callAgooSystemReceiver(), paramIControlCallBack.callAgooRegistrationReceiver() });
    paramIntent = a.a(paramContext);
    paramIControlCallBack = new c(this.i, "handleRegister");
    LinkedHashMap localLinkedHashMap = Config.getConnectHeader(this.i);
    localLinkedHashMap.put("currentSudoPack", paramIntent);
    paramIControlCallBack.a(localLinkedHashMap);
    aW.c(paramContext, paramIntent, "handleRegister");
    b(paramContext, "handleRegister");
  }

  private final void n(Context paramContext, Intent paramIntent, IControlCallBack paramIControlCallBack)
  {
    String str = paramIntent.getStringExtra("error");
    paramIntent = paramIntent.getStringExtra("eventId");
    bd.c("ControlService", "handleError:" + str);
    if (TextUtils.equals(str, "ERROR_NEED_ELECTION"))
      a(paramContext, paramIntent);
    do
    {
      return;
      if (TextUtils.equals(str, "ERROR_DEVICETOKEN_NULL"))
      {
        aW.g(paramContext, "ERROR_DEVICETOKEN_NULL");
        paramIControlCallBack.callError(paramContext, str);
        return;
      }
      if (TextUtils.equals(str, "ERRCODE_AUTH_REJECT"))
      {
        paramIControlCallBack.callError(paramContext, str);
        b(paramContext, paramIControlCallBack);
        return;
      }
    }
    while ((!TextUtils.equals(str, "ERROR_APPKEY_NULL")) && (!TextUtils.equals(str, "ERROR_APPSECRET_NULL")) && (!TextUtils.equals(str, "ERROR_TTID_NULL")));
    paramIControlCallBack.callError(paramContext, str);
    aW.g(paramContext, "APPKEY_OR_SECRET_IS_NULL");
    b(paramContext, paramIControlCallBack);
  }

  private final void o(Context paramContext, Intent paramIntent, IControlCallBack paramIControlCallBack)
  {
    int m = 0;
    if (org.android.agoo.a.m(paramContext))
    {
      String str1 = paramContext.getPackageName();
      String str2 = a.a(paramContext);
      if ((TextUtils.isEmpty(str2)) || (TextUtils.equals(str1, str2)))
      {
        bd.c("ControlService", "handleUnRegister---->[currentPack:" + str1 + "][currentSudoPack:" + str2 + "]:[retryElection]");
        if (a(paramIControlCallBack.callAgooService()))
        {
          bd.c("ControlService", "disableService---->[" + paramIControlCallBack.callAgooService() + "]");
          aU.a(paramContext, paramIControlCallBack.callAgooService());
        }
        aV.a(paramContext);
        m = 1;
      }
      a.b(paramContext, paramIControlCallBack.callAgooElectionReceiver());
      if (m != 0)
        a(paramContext, "handleUnRegister");
      i(paramContext, paramIntent, paramIControlCallBack);
      paramIntent = org.android.agoo.a.n(paramContext);
      org.android.agoo.a.s(paramContext);
      org.android.agoo.a.e(paramContext);
      paramIControlCallBack.callUnregistered(paramContext, paramIntent);
    }
  }

  public final void onHandleIntent(Context paramContext, Intent paramIntent, IControlCallBack paramIControlCallBack)
  {
    String str;
    try
    {
      bd.a(paramContext);
      bd.c("ControlService", "onHandleIntent [" + paramContext.getPackageName() + "][" + paramIntent.getAction() + "]");
      aW.a(paramContext);
      this.i = paramContext;
      str = paramIntent.getAction();
      if (TextUtils.equals(str, IntentUtil.getAgooCommand(paramContext)))
      {
        f(paramContext, paramIntent, paramIControlCallBack);
        return;
      }
      if ("android.intent.action.PACKAGE_REMOVED".equals(str))
      {
        e(paramContext, paramIntent, paramIControlCallBack);
        return;
      }
    }
    catch (Throwable paramContext)
    {
      bd.d("ControlService", "onHandleIntent", paramContext);
      return;
    }
    if (TextUtils.equals(str, "org.agoo.android.intent.action.RECEIVE"))
    {
      c(paramContext, paramIntent, paramIControlCallBack);
      return;
    }
    if (TextUtils.equals(str, "org.agoo.android.intent.action.ELECTION_RESULT_V4"))
    {
      h(paramContext, paramIntent, paramIControlCallBack);
      return;
    }
    if ((TextUtils.equals(str, "android.net.conn.CONNECTIVITY_CHANGE")) || (TextUtils.equals(str, "android.intent.action.BOOT_COMPLETED")) || (TextUtils.equals(str, "android.intent.action.PACKAGE_ADDED")) || (TextUtils.equals(str, "android.intent.action.PACKAGE_REPLACED")) || (TextUtils.equals(str, "android.intent.action.USER_PRESENT")))
    {
      if (TextUtils.equals(str, "android.net.conn.CONNECTIVITY_CHANGE"))
        org.android.agoo.a.v(paramContext);
      c(paramContext);
      a(paramContext, paramIntent, paramIControlCallBack);
      return;
    }
    bd.d("ControlService", "handleWake[sms]");
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.impl.ControlService
 * JD-Core Version:    0.6.2
 */