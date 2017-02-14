package com.ismartgo.beacon.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.v4.app.NotificationCompat.Builder;
import com.ismartgo.beacon.pojo.BeaconActivityInfo;
import com.ismartgo.beacon.view.IbeaconWebActivity;

public class NotificationUtil
{
  public static void pushNotification(Context paramContext, BeaconActivityInfo paramBeaconActivityInfo)
  {
    try
    {
      NotificationManager localNotificationManager = (NotificationManager)paramContext.getSystemService("notification");
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).applicationInfo.icon;
      Notification localNotification = new NotificationCompat.Builder(paramContext).setSmallIcon(i).setAutoCancel(true).build();
      localNotification.defaults = -1;
      Object localObject = new Intent(paramContext, IbeaconWebActivity.class);
      ((Intent)localObject).putExtra("activity_info", paramBeaconActivityInfo);
      localObject = PendingIntent.getActivity(paramContext, paramBeaconActivityInfo.getActivityId(), (Intent)localObject, 268435456);
      localNotification.setLatestEventInfo(paramContext, "推送标题", paramBeaconActivityInfo.getActivityName(), (PendingIntent)localObject);
      localNotificationManager.notify(paramBeaconActivityInfo.getId(), localNotification);
      return;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.beacon.util.NotificationUtil
 * JD-Core Version:    0.6.2
 */