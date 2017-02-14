package com.ismartgo.app.grid.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.ismartgo.app.tools.DataUtil;
import java.util.Iterator;
import java.util.List;

public class Helper
{
  public static String MD5Params(String[] paramArrayOfString)
  {
    StringBuffer localStringBuffer = new StringBuffer("");
    int i;
    if ((paramArrayOfString != null) && (paramArrayOfString.length > 0))
      i = 0;
    while (true)
    {
      if (i >= paramArrayOfString.length)
      {
        localStringBuffer.append(Utils.APP_KEY);
        paramArrayOfString = localStringBuffer.toString();
        Log.i("hahaha", "加密前字符： " + paramArrayOfString);
        return DataUtil.md5(paramArrayOfString);
      }
      if (paramArrayOfString[i] != null)
        localStringBuffer.append(paramArrayOfString[i]);
      localStringBuffer.append("#");
      i += 1;
    }
  }

  public static String getVersion(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return "";
  }

  public static boolean hasExternal(Context paramContext)
  {
    return Environment.getExternalStorageState() == "mounted";
  }

  public static void hideSoftKeyboard(Context paramContext, View paramView)
  {
    ((InputMethodManager)paramContext.getSystemService("input_method")).hideSoftInputFromWindow(paramView.getWindowToken(), 0);
  }

  public static boolean isAppAlive(Context paramContext)
  {
    Iterator localIterator = ((ActivityManager)paramContext.getSystemService("activity")).getRunningTasks(100).iterator();
    ActivityManager.RunningTaskInfo localRunningTaskInfo;
    do
    {
      if (!localIterator.hasNext())
        return false;
      localRunningTaskInfo = (ActivityManager.RunningTaskInfo)localIterator.next();
    }
    while ((!localRunningTaskInfo.topActivity.getPackageName().equals(paramContext.getPackageName())) || (!localRunningTaskInfo.baseActivity.getPackageName().equals(paramContext.getPackageName())));
    return true;
  }

  public static boolean isAppAlive(Context paramContext, String paramString)
  {
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
    int i = 0;
    while (true)
    {
      if (i >= paramContext.size())
      {
        Log.i("NotificationLaunch", String.format("the %s is not running, isAppAlive return false", new Object[] { paramString }));
        return false;
      }
      if (((ActivityManager.RunningAppProcessInfo)paramContext.get(i)).processName.equals(paramString))
      {
        Log.i("NotificationLaunch", String.format("the %s is running, isAppAlive return true", new Object[] { paramString }));
        return true;
      }
      i += 1;
    }
  }

  public static boolean isBackground(Context paramContext)
  {
    Iterator localIterator = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses().iterator();
    ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo;
    do
    {
      if (!localIterator.hasNext())
        return false;
      localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)localIterator.next();
    }
    while (!localRunningAppProcessInfo.processName.equals(paramContext.getPackageName()));
    if (localRunningAppProcessInfo.importance == 400)
    {
      Log.i("后台", localRunningAppProcessInfo.processName);
      return true;
    }
    Log.i("前台", localRunningAppProcessInfo.processName);
    return false;
  }

  public static void showSoftKeyboard(Context paramContext, View paramView)
  {
    ((InputMethodManager)paramContext.getSystemService("input_method")).showSoftInput(paramView, 2);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.grid.utils.Helper
 * JD-Core Version:    0.6.2
 */