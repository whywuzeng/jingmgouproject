package com.ismartgo.beacon.util;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;
import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

public class CommonMethod
{
  public static String getAppKey(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128).metaData.getString("SMARTGO_APPKEY");
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }

  public static int getAppSatus(Context paramContext, String paramString)
  {
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningTasks(20);
    if (((ActivityManager.RunningTaskInfo)paramContext.get(0)).topActivity.getPackageName().equals(paramString))
      return 1;
    paramContext = paramContext.iterator();
    do
      if (!paramContext.hasNext())
        return 3;
    while (!((ActivityManager.RunningTaskInfo)paramContext.next()).topActivity.getPackageName().equals(paramString));
    return 2;
  }

  @SuppressLint({"NewApi"})
  public static String getMemFree(Context paramContext)
  {
    return Formatter.formatFileSize(paramContext, new StatFs(Environment.getDataDirectory().getPath()).getAvailableBytes());
  }

  public static Object getResourceId(Context paramContext, String paramString1, String paramString2)
  {
    paramContext = paramContext.getPackageName() + ".R";
    label132: label140: label152: 
    while (true)
    {
      int k;
      int i;
      int m;
      int j;
      try
      {
        paramContext = Class.forName(paramContext).getClasses();
        k = paramContext.length;
        i = 0;
        break label132;
        Field[] arrayOfField = paramContext[i];
        if (arrayOfField.getSimpleName().equals(paramString2))
        {
          arrayOfField = arrayOfField.getFields();
          m = arrayOfField.length;
          j = 0;
          break label140;
          Field localField = arrayOfField[j];
          String str = localField.getName();
          if (str.equals(paramString1))
          {
            System.out.println(str);
            paramContext = localField.get(null);
            return paramContext;
          }
          j += 1;
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
      while (true)
      {
        if (i < k)
          break label152;
        return null;
        if (j < m)
          break;
        i += 1;
      }
    }
  }

  public static String getVersionName(Context paramContext)
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
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.beacon.util.CommonMethod
 * JD-Core Version:    0.6.2
 */