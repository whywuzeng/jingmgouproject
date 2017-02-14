package com.ismartgo.app.grid.location;

import android.app.ActivityManager;
import android.content.Context;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SuUtil
{
  private static Process process;

  private static void close()
  {
    if (process != null);
    try
    {
      process.getOutputStream().close();
      process = null;
      return;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
  }

  private static void initProcess()
  {
    if (process == null);
    try
    {
      process = Runtime.getRuntime().exec("su");
      return;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
  }

  public static void kill(Context paramContext)
  {
    paramContext = (ActivityManager)paramContext.getSystemService("activity");
    try
    {
      Method localMethod = paramContext.getClass().getDeclaredMethod("forceStopPackage", new Class[] { String.class });
      localMethod.setAccessible(true);
      localMethod.invoke(paramContext, new Object[] { "com.process.cn" });
      System.exit(0);
      return;
    }
    catch (SecurityException paramContext)
    {
      while (true)
        paramContext.printStackTrace();
    }
    catch (IllegalArgumentException paramContext)
    {
      while (true)
        paramContext.printStackTrace();
    }
    catch (NoSuchMethodException paramContext)
    {
      while (true)
        paramContext.printStackTrace();
    }
    catch (IllegalAccessException paramContext)
    {
      while (true)
        paramContext.printStackTrace();
    }
    catch (InvocationTargetException paramContext)
    {
      while (true)
        paramContext.printStackTrace();
    }
  }

  public static void kill(String paramString)
  {
    initProcess();
    killProcess(paramString);
    close();
  }

  private static void killProcess(String paramString)
  {
    OutputStream localOutputStream = process.getOutputStream();
    paramString = "am force-stop " + paramString + " \n";
    try
    {
      localOutputStream.write(paramString.getBytes());
      localOutputStream.flush();
      return;
    }
    catch (IOException paramString)
    {
      paramString.printStackTrace();
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.grid.location.SuUtil
 * JD-Core Version:    0.6.2
 */