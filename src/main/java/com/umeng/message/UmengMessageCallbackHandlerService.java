package com.umeng.message;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.os.Process;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import com.umeng.message.entity.UMessage;
import com.umeng.message.proguard.g;
import java.lang.reflect.Method;
import org.json.JSONObject;

public class UmengMessageCallbackHandlerService extends Service
{
  public static final String TAG = UmengMessageCallbackHandlerService.class.getName();
  private Context a = this;

  public UmengMessageCallbackHandlerService()
  {
  }

  public UmengMessageCallbackHandlerService(String paramString)
  {
  }

  private void a(Context paramContext, UMessage paramUMessage)
  {
    if ((PushAgent.getInstance(paramContext).isIncludesUmengUpdateSDK()) && ((!g.c(paramContext)) || (!g.b(paramContext)) || (PushAgent.getInstance(paramContext).getNotificationOnForeground())))
    {
      paramUMessage = new UpdateActionReceiver(paramUMessage);
      Object localObject = new IntentFilter("com.umeng.message.action.autoupdate");
      LocalBroadcastManager.getInstance(paramContext).registerReceiver(paramUMessage, (IntentFilter)localObject);
      try
      {
        paramUMessage = Class.forName("com.umeng.update.UmengUpdateAgent");
        localObject = paramUMessage.getMethod("setUpdateAutoPopup", new Class[] { Boolean.TYPE });
        Method localMethod1 = paramUMessage.getMethod("update", new Class[] { Context.class });
        Method localMethod2 = paramUMessage.getMethod("setUpdateFromPushMessage", new Class[] { Boolean.TYPE });
        ((Method)localObject).invoke(paramUMessage, new Object[] { Boolean.valueOf(false) });
        localMethod2.invoke(paramUMessage, new Object[] { Boolean.valueOf(true) });
        localMethod1.invoke(paramUMessage, new Object[] { paramContext });
        Log.d(TAG, "autoUpdate");
        return;
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
        Log.d(TAG, paramContext.toString());
        return;
      }
    }
    UTrack.getInstance(paramContext).setClearPrevMessage(false);
    UTrack.getInstance(paramContext).trackMsgDismissed(paramUMessage);
  }

  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }

  @SuppressLint({"NewApi"})
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    Object localObject = g.a(this.a, Process.myPid());
    Log.d(TAG, "processName=" + (String)localObject);
    if (paramIntent.getAction().equals("com.umeng.messge.registercallback.action"));
    while (true)
    {
      try
      {
        localObject = PushAgent.getInstance(this.a).getRegisterCallback();
        if (localObject != null)
          ((IUmengRegisterCallback)localObject).onRegistered(paramIntent.getStringExtra("registration_id"));
        stopSelf();
        return super.onStartCommand(paramIntent, paramInt1, paramInt2);
      }
      catch (Exception localException1)
      {
        localException1.printStackTrace();
        continue;
      }
      if (paramIntent.getAction().equals("com.umeng.message.unregistercallback.action"))
        try
        {
          IUmengUnregisterCallback localIUmengUnregisterCallback = PushAgent.getInstance(this.a).getUnregisterCallback();
          if (localIUmengUnregisterCallback == null)
            continue;
          localIUmengUnregisterCallback.onUnregistered(paramIntent.getStringExtra("registration_id"));
        }
        catch (Exception localException2)
        {
          localException2.printStackTrace();
        }
      else if (paramIntent.getAction().equals("com.umeng.message.message.handler.action"))
        try
        {
          UHandler localUHandler = PushAgent.getInstance(this.a).getMessageHandler();
          Log.d(TAG, "messageHandler=" + localUHandler);
          if (localUHandler == null)
            continue;
          UMessage localUMessage2 = new UMessage(new JSONObject(paramIntent.getStringExtra("body")));
          localUMessage2.message_id = paramIntent.getStringExtra("id");
          localUMessage2.task_id = paramIntent.getStringExtra("task_id");
          localUHandler.handleMessage(this.a, localUMessage2);
        }
        catch (Exception localException3)
        {
          Log.d(TAG, localException3.toString());
        }
      else if (paramIntent.getAction().equals("com.umeng.message.autoupdate.handler.action"))
        try
        {
          UMessage localUMessage1 = new UMessage(new JSONObject(paramIntent.getStringExtra("body")));
          localUMessage1.message_id = paramIntent.getStringExtra("id");
          localUMessage1.task_id = paramIntent.getStringExtra("task_id");
          a(this.a, localUMessage1);
        }
        catch (Exception localException4)
        {
          Log.d(TAG, localException4.toString());
        }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.UmengMessageCallbackHandlerService
 * JD-Core Version:    0.6.2
 */