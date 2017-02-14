package com.umeng.message;

import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.text.TextUtils;
import com.umeng.common.message.Log;
import com.umeng.message.entity.UMessage;
import com.umeng.message.local.UmengLocalNotificationManager;
import com.umeng.message.local.UmengLocalNotificationService;
import com.umeng.message.proguard.g;
import org.android.agoo.client.BaseIntentService;
import org.json.JSONObject;

public abstract class UmengBaseIntentService extends BaseIntentService
{
  private static final String a = UmengBaseIntentService.class.getName();

  public Class<?> callAgooElectionReceiver()
  {
    Log.c(a, "callAgooElectionReceiver");
    return ElectionReceiver.class;
  }

  public Class<?> callAgooMessageReceiver()
  {
    Log.c(a, "callAgooMessageReceiver");
    return MessageReceiver.class;
  }

  public Class<?> callAgooRegistrationReceiver()
  {
    Log.c(a, "callAgooRegistrationReceiver");
    return RegistrationReceiver.class;
  }

  public Class<?> callAgooSystemReceiver()
  {
    Log.c(a, "callAgooSystemReceiver");
    return SystemReceiver.class;
  }

  protected final Class<?> getAgooService()
  {
    Log.c(a, "getAgooService");
    return UmengService.class.getClass();
  }

  protected void onError(Context paramContext, String paramString)
  {
    Log.c(a, "onError()[" + paramString + "]");
  }

  protected void onMessage(Context paramContext, Intent paramIntent)
  {
    if (Process.getElapsedCpuTime() < 3000L)
    {
      Log.a(a, "App is launched by push message");
      PushAgent.setAppLaunchByMessage();
    }
    String str = paramIntent.getStringExtra("body");
    Log.c(a, "onMessage():[" + str + "]");
    try
    {
      Object localObject = new UMessage(new JSONObject(str));
      ((UMessage)localObject).message_id = paramIntent.getStringExtra("id");
      ((UMessage)localObject).task_id = paramIntent.getStringExtra("task_id");
      UTrack.getInstance(getApplicationContext()).a((UMessage)localObject);
      MsgLogStore.getInstance(paramContext).addLogIdType(((UMessage)localObject).msg_id, ((UMessage)localObject).display_type);
      MsgLogStore.getInstance(paramContext).addLogIdTypeForAgoo(((UMessage)localObject).message_id, ((UMessage)localObject).task_id, ((UMessage)localObject).display_type);
      if (TextUtils.equals("autoupdate", ((UMessage)localObject).display_type))
      {
        localObject = paramIntent.getStringExtra("id");
        paramIntent = paramIntent.getStringExtra("task_id");
        Intent localIntent = new Intent();
        localIntent.setPackage(paramContext.getPackageName());
        localIntent.setAction("com.umeng.message.autoupdate.handler.action");
        localIntent.putExtra("body", str);
        localIntent.putExtra("id", (String)localObject);
        localIntent.putExtra("task_id", paramIntent);
        paramContext.startService(localIntent);
      }
      if (!g.c(this, UmengLocalNotificationService.class.getName()))
        UmengLocalNotificationManager.getInstance(this).resetLocalNotifications();
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      Log.c(a, paramContext.toString());
    }
  }

  protected void onRegistered(Context paramContext, String paramString)
  {
    Log.c(a, "onRegistered()[" + paramString + "]");
    try
    {
      UTrack.getInstance(getApplicationContext()).trackRegister();
      Intent localIntent = new Intent();
      localIntent.setPackage(paramContext.getPackageName());
      localIntent.setAction("com.umeng.messge.registercallback.action");
      localIntent.putExtra("registration_id", paramString);
      paramContext.startService(localIntent);
      return;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  protected void onUnregistered(Context paramContext, String paramString)
  {
    Log.c(a, "onUnregistered()[" + paramString + "]");
    Intent localIntent = new Intent();
    localIntent.setPackage(paramContext.getPackageName());
    localIntent.setAction("com.umeng.message.unregistercallback.action");
    localIntent.putExtra("registration_id", paramString);
    paramContext.startService(localIntent);
  }

  protected boolean shouldProcessMessage(Context paramContext, Intent paramIntent)
  {
    return PushAgent.getInstance(paramContext).isEnabled();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.UmengBaseIntentService
 * JD-Core Version:    0.6.2
 */