package com.umeng.message.proguard;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Process;
import java.util.Iterator;
import java.util.List;
import org.android.agoo.intent.IntentUtil;

public final class aV
{
  private static final String a = "ServiceUtil";

  public static final void a(Context paramContext)
  {
    try
    {
      bd.c("ServiceUtil", "command --->[" + paramContext.getPackageName() + ".service]:[" + "stop" + "]");
      String str = IntentUtil.getAgooStart(paramContext);
      Intent localIntent = new Intent();
      localIntent.setAction(str);
      localIntent.putExtra("method", "stop");
      localIntent.setPackage(paramContext.getPackageName());
      paramContext.startService(localIntent);
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static final void a(Context paramContext, String paramString)
  {
    bd.c("ServiceUtil", "command --->[" + paramContext.getPackageName() + ".service]:[" + "start" + "]");
    Intent localIntent = new Intent();
    localIntent.setAction(IntentUtil.getAgooStart(paramContext));
    localIntent.putExtra("method", "start");
    localIntent.putExtra("eventId", paramString);
    localIntent.setPackage(paramContext.getPackageName());
    paramContext.startService(localIntent);
  }

  public static final void b(Context paramContext)
  {
    while (true)
      try
      {
        paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningServices(2147483647);
        int j = Process.myPid();
        int i = 0;
        paramContext = paramContext.iterator();
        if (paramContext.hasNext())
        {
          ActivityManager.RunningServiceInfo localRunningServiceInfo = (ActivityManager.RunningServiceInfo)paramContext.next();
          if (localRunningServiceInfo.pid == j)
          {
            bd.c("ServiceUtil", "runningService --->[" + localRunningServiceInfo.process + "]");
            i += 1;
          }
        }
        else
        {
          if (i < 1)
          {
            bd.c("ServiceUtil", "killRunningService --->[" + j + "]");
            Process.killProcess(j);
          }
          return;
        }
      }
      catch (Throwable paramContext)
      {
        return;
      }
  }

  public static boolean c(Context paramContext)
  {
    List localList = ((ActivityManager)paramContext.getSystemService("activity")).getRunningTasks(1);
    return (!localList.isEmpty()) && (!((ActivityManager.RunningTaskInfo)localList.get(0)).topActivity.getPackageName().equals(paramContext.getPackageName()));
  }

  public static boolean d(Context paramContext)
  {
    Iterator localIterator = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses().iterator();
    while (localIterator.hasNext())
    {
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)localIterator.next();
      if (localRunningAppProcessInfo.processName.equals(paramContext.getPackageName()))
        return localRunningAppProcessInfo.importance == 400;
    }
    return false;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.aV
 * JD-Core Version:    0.6.2
 */