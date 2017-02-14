package com.umeng.message;

import android.content.Context;
import org.android.agoo.client.MessageReceiverService;

public class UmengMessageIntentReceiverService extends MessageReceiverService
{
  public String getIntentServiceClassName(Context paramContext)
  {
    return PushAgent.getInstance(paramContext).getPushIntentServiceClass();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.UmengMessageIntentReceiverService
 * JD-Core Version:    0.6.2
 */