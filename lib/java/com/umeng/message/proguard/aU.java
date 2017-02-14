package com.umeng.message.proguard;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import java.util.List;

public final class aU
{
  private static final String a = "ReceiverUtil";

  public static final void a(Context paramContext, Class<?> paramClass)
  {
    if ((paramContext != null) && (paramClass != null))
      try
      {
        PackageManager localPackageManager = paramContext.getPackageManager();
        if (localPackageManager == null)
          return;
        if (localPackageManager.getApplicationEnabledSetting(paramContext.getPackageName()) > -1)
        {
          paramContext = new ComponentName(paramContext, paramClass);
          if (localPackageManager.getComponentEnabledSetting(paramContext) != 2)
          {
            localPackageManager.setComponentEnabledSetting(paramContext, 2, 1);
            return;
          }
        }
      }
      catch (Throwable paramContext)
      {
      }
  }

  public static final void a(Context paramContext, Class<?>[] paramArrayOfClass)
  {
    if (paramContext != null)
      try
      {
        PackageManager localPackageManager = paramContext.getPackageManager();
        int j = paramArrayOfClass.length;
        int i = 0;
        if (i < j)
        {
          Object localObject = paramArrayOfClass[i];
          if (localObject == null);
          while (true)
          {
            i += 1;
            break;
            try
            {
              localObject = new ComponentName(paramContext, (Class)localObject);
              if (localPackageManager.getComponentEnabledSetting((ComponentName)localObject) == 2)
              {
                bd.a("ReceiverUtil", "rebootReceiver[" + ((ComponentName)localObject).toString() + "]--->[ENABLED]");
                localPackageManager.setComponentEnabledSetting((ComponentName)localObject, 1, 1);
              }
            }
            catch (Throwable localThrowable)
            {
            }
          }
        }
      }
      catch (Throwable paramContext)
      {
      }
  }

  public static boolean a(Context paramContext)
  {
    boolean bool3 = false;
    boolean bool1 = false;
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningServices(2147483647);
    boolean bool2 = bool1;
    if (paramContext != null)
    {
      if (paramContext.isEmpty())
        bool2 = bool1;
    }
    else
      return bool2;
    int i = 0;
    while (true)
    {
      bool1 = bool3;
      if (i < paramContext.size())
      {
        if (((ActivityManager.RunningServiceInfo)paramContext.get(i)).service.getClassName().equals("com.taobao.agoo.PushService"))
        {
          bool1 = true;
          Log.d("ReceiverUtil", "PushService is running ....................");
        }
      }
      else
      {
        bool2 = bool1;
        if (bool1)
          break;
        Log.d("ReceiverUtil", "PushService is failed ....................");
        return bool1;
      }
      i += 1;
    }
  }

  public static final boolean a(Context paramContext, String paramString)
  {
    try
    {
      if (paramContext.getPackageManager().getApplicationInfo(paramString, 0) != null)
        return true;
      aW.k(paramContext);
      return false;
    }
    catch (Throwable paramContext)
    {
    }
    return false;
  }

  public static final void b(Context paramContext, Class<?> paramClass)
  {
    if ((paramContext != null) && (paramClass != null))
      try
      {
        PackageManager localPackageManager = paramContext.getPackageManager();
        if (localPackageManager == null)
          return;
        if (localPackageManager.getApplicationEnabledSetting(paramContext.getPackageName()) > -1)
        {
          paramContext = new ComponentName(paramContext, paramClass);
          int i = localPackageManager.getComponentEnabledSetting(paramContext);
          if ((i != 1) && (i != 0))
          {
            localPackageManager.setComponentEnabledSetting(paramContext, 1, 1);
            return;
          }
        }
      }
      catch (Throwable paramContext)
      {
      }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.aU
 * JD-Core Version:    0.6.2
 */