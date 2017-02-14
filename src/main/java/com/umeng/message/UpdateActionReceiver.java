package com.umeng.message;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import com.umeng.common.message.Log;
import com.umeng.message.entity.UMessage;

public class UpdateActionReceiver extends BroadcastReceiver
{
  private static final String a = UpdateActionReceiver.class.getName();
  private UMessage b;

  public UpdateActionReceiver(UMessage paramUMessage)
  {
    this.b = paramUMessage;
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    try
    {
      if (PushAgent.getInstance(paramContext).isIncludesUmengUpdateSDK())
      {
        paramIntent = paramIntent.getBundleExtra("UpdateListener");
        if (paramIntent.getInt("UpdateStatus") == 0)
        {
          paramIntent = paramIntent.getSerializable("UpdateResponse");
          MsgLogStore.getInstance(paramContext).setMsgConfigInfo_UpdateResponse(paramIntent);
          paramIntent = (UmengMessageHandler)PushAgent.getInstance(paramContext).getMessageHandler();
          if (paramIntent != null)
            paramIntent.dealWithNotificationMessage(paramContext, this.b);
        }
        while (true)
        {
          LocalBroadcastManager.getInstance(paramContext).unregisterReceiver(this);
          return;
          UTrack.getInstance(paramContext).setClearPrevMessage(false);
          UTrack.getInstance(paramContext).trackMsgDismissed(this.b);
        }
      }
    }
    catch (Exception paramContext)
    {
      Log.c(a, paramContext.toString());
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.UpdateActionReceiver
 * JD-Core Version:    0.6.2
 */