package com.umeng.message;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import com.umeng.common.message.Log;
import com.umeng.message.entity.UMessage;
import org.json.JSONException;
import org.json.JSONObject;

public class NotificationProxyBroadcastReceiver extends BroadcastReceiver
{
  public static final int EXTRA_ACTION_CLICK = 10;
  public static final int EXTRA_ACTION_DISMISS = 11;
  public static final int EXTRA_ACTION_NOT_EXIST = -1;
  public static final String EXTRA_KEY_ACTION = "ACTION";
  public static final String EXTRA_KEY_MESSAGE_ID = "MESSAGE_ID";
  public static final String EXTRA_KEY_MSG = "MSG";
  public static final String EXTRA_KEY_TASK_ID = "TASK_ID";
  public static final int LOCAL_ACTION_CLICK = 12;
  private static final String a = NotificationProxyBroadcastReceiver.class.getName();
  private UHandler b;

  private void a(Context paramContext)
  {
    Intent localIntent = paramContext.getPackageManager().getLaunchIntentForPackage(paramContext.getPackageName());
    if (localIntent == null)
    {
      Log.b(a, "handleMessage(): cannot find app: " + paramContext.getPackageName());
      return;
    }
    localIntent.setPackage(null);
    localIntent.addFlags(268435456);
    paramContext.startActivity(localIntent);
    Log.c(a, "handleMessage(): lunach app: " + paramContext.getPackageName());
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    Object localObject = paramIntent.getStringExtra("MSG");
    try
    {
      int i = paramIntent.getIntExtra("ACTION", -1);
      Log.c(a, String.format("onReceive[msg=%s, action=%d]", new Object[] { localObject, Integer.valueOf(i) }));
      if (i == 12)
      {
        a(paramContext);
        return;
      }
      localObject = new UMessage(new JSONObject((String)localObject));
      ((UMessage)localObject).message_id = paramIntent.getStringExtra("MESSAGE_ID");
      ((UMessage)localObject).task_id = paramIntent.getStringExtra("TASK_ID");
      switch (i)
      {
      case 10:
        Log.c(a, "click notification");
        UTrack.getInstance(paramContext).setClearPrevMessage(true);
        UTrack.getInstance(paramContext).trackMsgClick((UMessage)localObject);
        this.b = PushAgent.getInstance(paramContext).getNotificationClickHandler();
        if (this.b != null)
        {
          ((UMessage)localObject).clickOrDismiss = true;
          this.b.handleMessage(paramContext, (UMessage)localObject);
          return;
        }
        break;
      case 11:
      }
    }
    catch (JSONException paramContext)
    {
      paramContext.printStackTrace();
      return;
      Log.c(a, "dismiss notification");
      UTrack.getInstance(paramContext).setClearPrevMessage(true);
      UTrack.getInstance(paramContext).trackMsgDismissed((UMessage)localObject);
      this.b = PushAgent.getInstance(paramContext).getNotificationClickHandler();
      if (this.b != null)
      {
        ((UMessage)localObject).clickOrDismiss = false;
        this.b.handleMessage(paramContext, (UMessage)localObject);
        return;
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.NotificationProxyBroadcastReceiver
 * JD-Core Version:    0.6.2
 */