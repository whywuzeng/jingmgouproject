package com.umeng.message;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.umeng.common.message.Log;
import com.umeng.message.local.UmengLocalNotificationManager;
import com.umeng.message.local.UmengLocalNotificationService;
import com.umeng.message.proguard.g;
import java.util.ArrayList;
import java.util.Iterator;

public class UmengMessageBootReceiver extends BroadcastReceiver
{
  private static final String b = UmengMessageBootReceiver.class.getName();
  private static final String c = "android.intent.action.BOOT_COMPLETED";
  Runnable a = new Runnable()
  {
    public void run()
    {
      do
      {
        Object localObject;
        try
        {
          Iterator localIterator1 = MsgLogStore.getInstance(UmengMessageBootReceiver.a(UmengMessageBootReceiver.this)).getMsgLogIdTypes().iterator();
          while (localIterator1.hasNext())
          {
            localObject = (MsgLogStore.MsgLogIdType)localIterator1.next();
            if ((MsgLogStore.getInstance(UmengMessageBootReceiver.a(UmengMessageBootReceiver.this)).getMsgLog(((MsgLogStore.MsgLogIdType)localObject).msgId) == null) && (((MsgLogStore.MsgLogIdType)localObject).msgType.equals("notification")))
              MsgLogStore.getInstance(UmengMessageBootReceiver.a(UmengMessageBootReceiver.this)).addLog(((MsgLogStore.MsgLogIdType)localObject).msgId, 2, System.currentTimeMillis());
          }
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
          Log.c(UmengMessageBootReceiver.a(), localException.toString());
          return;
        }
        Iterator localIterator2 = MsgLogStore.getInstance(UmengMessageBootReceiver.a(UmengMessageBootReceiver.this)).getMsgLogIdTypesForAgoo().iterator();
        while (localIterator2.hasNext())
        {
          localObject = (MsgLogStore.MsgLogIdTypeForAgoo)localIterator2.next();
          if ((MsgLogStore.getInstance(UmengMessageBootReceiver.a(UmengMessageBootReceiver.this)).getMsgLogForAgoo(((MsgLogStore.MsgLogIdTypeForAgoo)localObject).msgId) == null) && (((MsgLogStore.MsgLogIdTypeForAgoo)localObject).msgStatus.equals("notification")))
            MsgLogStore.getInstance(UmengMessageBootReceiver.a(UmengMessageBootReceiver.this)).addLogForAgoo(((MsgLogStore.MsgLogIdTypeForAgoo)localObject).msgId, ((MsgLogStore.MsgLogIdTypeForAgoo)localObject).taskId, "9", System.currentTimeMillis());
        }
      }
      while (g.c(UmengMessageBootReceiver.a(UmengMessageBootReceiver.this), UmengLocalNotificationService.class.getName()));
      UmengLocalNotificationManager.getInstance(UmengMessageBootReceiver.a(UmengMessageBootReceiver.this)).resetLocalNotifications();
    }
  };
  private Context d;

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    Log.c(b, "Boot this system , UmengMessageBootReceiver onReceive()");
    Log.c(b, "action=" + paramIntent.getAction());
    if (paramIntent.getAction().equals("android.intent.action.BOOT_COMPLETED"))
    {
      this.d = paramContext;
      new Thread(this.a).start();
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.UmengMessageBootReceiver
 * JD-Core Version:    0.6.2
 */