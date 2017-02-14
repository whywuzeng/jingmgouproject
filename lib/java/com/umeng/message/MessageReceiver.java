package com.umeng.message;

import android.content.Context;
import com.umeng.common.message.Log;
import com.umeng.message.local.UmengLocalNotificationManager;
import com.umeng.message.local.UmengLocalNotificationService;
import com.umeng.message.proguard.g;
import org.android.agoo.client.BaseBroadcastReceiver;

public class MessageReceiver extends BaseBroadcastReceiver
{
  private static final String a = MessageReceiver.class.getName();

  protected String getIntentServiceClassName(Context paramContext)
  {
    Log.c(a, "MessageReceiver");
    if (!g.c(paramContext, UmengLocalNotificationService.class.getName()))
      UmengLocalNotificationManager.getInstance(paramContext).resetLocalNotifications();
    return PushAgent.getInstance(paramContext).getPushIntentServiceClass();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.MessageReceiver
 * JD-Core Version:    0.6.2
 */