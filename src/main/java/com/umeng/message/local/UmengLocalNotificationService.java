package com.umeng.message.local;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

public class UmengLocalNotificationService extends IntentService
{
  private static final String a = UmengLocalNotificationService.class.getName();
  private Context b;

  public UmengLocalNotificationService()
  {
    super("UmengLocalNotificationService");
  }

  public UmengLocalNotificationService(String paramString)
  {
    super(paramString);
  }

  private void a(String paramString)
  {
    try
    {
      Log.d(a, "displayLocalNotification");
      paramString = UmengLocalNotificationStore.getInstance(this.b).findLocalNotification(paramString);
      if (paramString != null)
      {
        if (paramString.getRepeatingNum() == 0)
          return;
        Notification localNotification = paramString.getNotificationBuilder().builder(this.b, paramString.getTitle(), paramString.getContent(), paramString.getTicker());
        paramString.getNotificationBuilder().showNotification(this.b, localNotification);
        return;
      }
    }
    catch (Exception paramString)
    {
      Log.d(a, paramString.toString());
      paramString.printStackTrace();
    }
  }

  private void a(String paramString1, String paramString2)
  {
    long l;
    try
    {
      UmengLocalNotification localUmengLocalNotification = UmengLocalNotificationStore.getInstance(this.b).findLocalNotification(paramString1);
      if (localUmengLocalNotification == null)
        return;
      l = UmengLocalNotificationHelper.getTimeAndUpdateLocalNotification(this.b, localUmengLocalNotification);
      Log.d(a, "time=" + UmengLocalNotificationHelper.getDateFromTime(l));
      int i = localUmengLocalNotification.getRepeatingNum();
      if (i == 0)
        try
        {
          UmengLocalNotificationManager.getInstance(this.b).deleteLocalNotification(paramString1);
          b(paramString1, paramString2);
          return;
        }
        catch (Exception paramString1)
        {
          paramString1.printStackTrace();
          Log.d(a, paramString1.toString());
          return;
        }
    }
    catch (Exception paramString1)
    {
      Log.d(a, paramString1.toString());
      paramString1.printStackTrace();
      return;
    }
    paramString1 = d(paramString1, paramString2);
    ((AlarmManager)getSystemService("alarm")).set(0, l, paramString1);
  }

  private void b(String paramString1, String paramString2)
  {
    paramString1 = d(paramString1, paramString2);
    ((AlarmManager)getSystemService("alarm")).cancel(paramString1);
  }

  private void c(String paramString1, String paramString2)
  {
    paramString1 = paramString1.split(";");
    int j = paramString1.length;
    int i = 0;
    while (i < j)
    {
      b(paramString1[i], paramString2);
      i += 1;
    }
  }

  private PendingIntent d(String paramString1, String paramString2)
  {
    int i = paramString1.hashCode();
    Intent localIntent = new Intent(this.b, UmengLocalNotificationService.class);
    localIntent.putExtra("local_notification_id", paramString1);
    localIntent.putExtra("local_notification_type", paramString2);
    return PendingIntent.getService(this.b, i, localIntent, 134217728);
  }

  public void onCreate()
  {
    Log.d(a, "onCreate");
    super.onCreate();
  }

  public void onDestroy()
  {
    Log.d(a, "onDestory");
    super.onDestroy();
  }

  protected void onHandleIntent(Intent paramIntent)
  {
    String str = paramIntent.getStringExtra("local_notification_id");
    paramIntent = paramIntent.getStringExtra("local_notification_type");
    if (TextUtils.equals(paramIntent, "add_local_notification"))
      a(str, "display_local_notification");
    do
    {
      return;
      if (TextUtils.equals(paramIntent, "update_local_notification"))
      {
        b(str, "display_local_notification");
        a(str, "display_local_notification");
        return;
      }
      if (TextUtils.equals(paramIntent, "delete_local_notification"))
      {
        b(str, "display_local_notification");
        return;
      }
      if (TextUtils.equals(paramIntent, "clear_local_notification"))
      {
        c(str, "display_local_notification");
        return;
      }
    }
    while (!TextUtils.equals(paramIntent, "display_local_notification"));
    a(str);
    b(str, "display_local_notification");
    a(str, "display_local_notification");
  }

  @SuppressLint({"NewApi"})
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    this.b = this;
    return super.onStartCommand(paramIntent, paramInt1, paramInt2);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.local.UmengLocalNotificationService
 * JD-Core Version:    0.6.2
 */