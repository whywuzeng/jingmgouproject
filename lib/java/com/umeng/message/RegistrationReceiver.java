package com.umeng.message;

import android.content.Context;
import com.umeng.common.message.Log;
import org.android.agoo.client.BaseBroadcastReceiver;

public class RegistrationReceiver extends BaseBroadcastReceiver
{
  private static final String a = RegistrationReceiver.class.getName();

  protected String getIntentServiceClassName(Context paramContext)
  {
    Log.c(a, "RegistrationReceiver");
    return PushAgent.getInstance(paramContext).getPushIntentServiceClass();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.RegistrationReceiver
 * JD-Core Version:    0.6.2
 */