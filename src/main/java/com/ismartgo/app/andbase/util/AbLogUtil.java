package com.ismartgo.app.andbase.util;

import android.content.Context;
import android.util.Log;
import java.util.Calendar;

public class AbLogUtil
{
  public static boolean D = true;
  public static boolean E = true;
  public static boolean I = true;
  public static long startLogTimeInMillis = 0L;

  public static void closeAll()
  {
    D = false;
    I = false;
    E = false;
  }

  public static void d(Context paramContext, String paramString)
  {
    d(paramContext.getClass().getSimpleName(), paramString);
  }

  public static void d(Context paramContext, String paramString, boolean paramBoolean)
  {
    d(paramContext.getClass().getSimpleName(), paramString, paramBoolean);
  }

  public static void d(Class<?> paramClass, String paramString)
  {
    d(paramClass.getSimpleName(), paramString);
  }

  public static void d(Class<?> paramClass, String paramString, boolean paramBoolean)
  {
    d(paramClass.getSimpleName(), paramString, paramBoolean);
  }

  public static void d(String paramString1, String paramString2)
  {
    if (D)
      Log.d(paramString1, paramString2);
  }

  public static void d(String paramString1, String paramString2, boolean paramBoolean)
  {
    long l = Calendar.getInstance().getTimeInMillis();
    Log.d(paramString1, paramString2 + ":" + (l - startLogTimeInMillis) + "ms");
  }

  public static void debug(boolean paramBoolean)
  {
    D = paramBoolean;
  }

  public static void e(Context paramContext, String paramString)
  {
    e(paramContext.getClass().getSimpleName(), paramString);
  }

  public static void e(Class<?> paramClass, String paramString)
  {
    e(paramClass.getSimpleName(), paramString);
  }

  public static void e(String paramString1, String paramString2)
  {
    Log.e(paramString1, paramString2);
  }

  public static void error(boolean paramBoolean)
  {
    E = paramBoolean;
  }

  public static void i(Context paramContext, String paramString)
  {
    i(paramContext.getClass().getSimpleName(), paramString);
  }

  public static void i(Class<?> paramClass, String paramString)
  {
    i(paramClass.getSimpleName(), paramString);
  }

  public static void i(String paramString1, String paramString2)
  {
    Log.i(paramString1, paramString2);
  }

  public static void info(boolean paramBoolean)
  {
    I = paramBoolean;
  }

  public static void openAll()
  {
    D = true;
    I = true;
    E = true;
  }

  public static void prepareLog(Context paramContext)
  {
    prepareLog(paramContext.getClass().getSimpleName());
  }

  public static void prepareLog(Class<?> paramClass)
  {
    prepareLog(paramClass.getSimpleName());
  }

  public static void prepareLog(String paramString)
  {
    startLogTimeInMillis = Calendar.getInstance().getTimeInMillis();
    Log.d(paramString, "日志计时开始：" + startLogTimeInMillis);
  }

  public static void setVerbose(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    D = paramBoolean1;
    I = paramBoolean2;
    E = paramBoolean3;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.andbase.util.AbLogUtil
 * JD-Core Version:    0.6.2
 */