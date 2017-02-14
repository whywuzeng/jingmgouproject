package com.nostra13.universalimageloader.utils;

import android.util.Log;
import com.nostra13.universalimageloader.core.ImageLoader;

public final class L
{
  private static final String LOG_FORMAT = "%1$s\n%2$s";
  private static volatile boolean writeDebugLogs = false;
  private static volatile boolean writeLogs = true;

  public static void d(String paramString, Object[] paramArrayOfObject)
  {
    if (writeDebugLogs)
      log(3, null, paramString, paramArrayOfObject);
  }

  @Deprecated
  public static void disableLogging()
  {
    writeLogs(false);
  }

  public static void e(String paramString, Object[] paramArrayOfObject)
  {
    log(6, null, paramString, paramArrayOfObject);
  }

  public static void e(Throwable paramThrowable)
  {
    log(6, paramThrowable, null, new Object[0]);
  }

  public static void e(Throwable paramThrowable, String paramString, Object[] paramArrayOfObject)
  {
    log(6, paramThrowable, paramString, paramArrayOfObject);
  }

  @Deprecated
  public static void enableLogging()
  {
    writeLogs(true);
  }

  public static void i(String paramString, Object[] paramArrayOfObject)
  {
    log(4, null, paramString, paramArrayOfObject);
  }

  private static void log(int paramInt, Throwable paramThrowable, String paramString, Object[] paramArrayOfObject)
  {
    if (!writeLogs)
      return;
    String str = paramString;
    if (paramArrayOfObject.length > 0)
      str = String.format(paramString, paramArrayOfObject);
    if (paramThrowable == null)
    {
      Log.println(paramInt, ImageLoader.TAG, str);
      return;
    }
    if (str == null);
    for (paramString = paramThrowable.getMessage(); ; paramString = str)
    {
      str = String.format("%1$s\n%2$s", new Object[] { paramString, Log.getStackTraceString(paramThrowable) });
      break;
    }
  }

  public static void w(String paramString, Object[] paramArrayOfObject)
  {
    log(5, null, paramString, paramArrayOfObject);
  }

  public static void writeDebugLogs(boolean paramBoolean)
  {
    writeDebugLogs = paramBoolean;
  }

  public static void writeLogs(boolean paramBoolean)
  {
    writeLogs = paramBoolean;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.nostra13.universalimageloader.utils.L
 * JD-Core Version:    0.6.2
 */