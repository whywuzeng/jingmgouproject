package com.yolanda.nohttp;

import android.util.Log;
import java.util.Locale;

public class Logger
{
  private static boolean SDebug = false;
  private static String STag = "NoHttp";

  protected static String buildMessage(String paramString, Object[] paramArrayOfObject)
  {
    StackTraceElement[] arrayOfStackTraceElement;
    int i;
    if (paramArrayOfObject == null)
    {
      arrayOfStackTraceElement = new Throwable().fillInStackTrace().getStackTrace();
      paramArrayOfObject = "<unknown>";
      i = 2;
    }
    while (true)
    {
      if (i >= arrayOfStackTraceElement.length);
      while (true)
      {
        return String.format(Locale.US, "[%d] %s: %s", new Object[] { Long.valueOf(Thread.currentThread().getId()), paramArrayOfObject, paramString });
        paramString = String.format(Locale.US, paramString, paramArrayOfObject);
        break;
        if (arrayOfStackTraceElement[i].getClass().equals(Logger.class))
          break label151;
        paramArrayOfObject = arrayOfStackTraceElement[i].getClassName();
        paramArrayOfObject = paramArrayOfObject.substring(paramArrayOfObject.lastIndexOf('.') + 1);
        paramArrayOfObject = paramArrayOfObject.substring(paramArrayOfObject.lastIndexOf('$') + 1) + "." + arrayOfStackTraceElement[i].getMethodName();
      }
      label151: i += 1;
    }
  }

  public static void d(String paramString)
  {
    if (SDebug)
      Log.d(STag, paramString);
  }

  public static void d(String paramString, Object[] paramArrayOfObject)
  {
    if (SDebug)
      Log.d(STag, buildMessage(paramString, paramArrayOfObject));
  }

  public static void d(Throwable paramThrowable)
  {
    if (SDebug)
      Log.d(STag, "", paramThrowable);
  }

  public static void d(Throwable paramThrowable, String paramString, Object[] paramArrayOfObject)
  {
    if (SDebug)
      Log.d(STag, buildMessage(paramString, paramArrayOfObject), paramThrowable);
  }

  public static void e(String paramString)
  {
    if (SDebug)
      Log.e(STag, paramString);
  }

  public static void e(String paramString, Object[] paramArrayOfObject)
  {
    if (SDebug)
      Log.e(STag, buildMessage(paramString, paramArrayOfObject));
  }

  public static void e(Throwable paramThrowable)
  {
    if (SDebug)
      Log.e(STag, "", paramThrowable);
  }

  public static void e(Throwable paramThrowable, String paramString, Object[] paramArrayOfObject)
  {
    if (SDebug)
      Log.e(STag, buildMessage(paramString, paramArrayOfObject), paramThrowable);
  }

  public static void i(String paramString)
  {
    if (SDebug)
      Log.i(STag, paramString);
  }

  public static void i(String paramString, Object[] paramArrayOfObject)
  {
    if (SDebug)
      Log.i(STag, buildMessage(paramString, paramArrayOfObject));
  }

  public static void i(Throwable paramThrowable)
  {
    if (SDebug)
      Log.i(STag, "", paramThrowable);
  }

  public static void i(Throwable paramThrowable, String paramString, Object[] paramArrayOfObject)
  {
    if (SDebug)
      Log.i(STag, buildMessage(paramString, paramArrayOfObject), paramThrowable);
  }

  public static void setDebug(boolean paramBoolean)
  {
    SDebug = paramBoolean;
  }

  public static void setTag(String paramString)
  {
    STag = paramString;
  }

  public static void v(String paramString)
  {
    if (SDebug)
      Log.v(STag, paramString);
  }

  public static void v(String paramString, Object[] paramArrayOfObject)
  {
    if (SDebug)
      Log.v(STag, buildMessage(paramString, paramArrayOfObject));
  }

  public static void v(Throwable paramThrowable)
  {
    if (SDebug)
      Log.v(STag, "", paramThrowable);
  }

  public static void v(Throwable paramThrowable, String paramString, Object[] paramArrayOfObject)
  {
    if (SDebug)
      Log.v(STag, buildMessage(paramString, paramArrayOfObject), paramThrowable);
  }

  public static void w(String paramString)
  {
    if (SDebug)
      Log.w(STag, paramString);
  }

  public static void w(String paramString, Object[] paramArrayOfObject)
  {
    if (SDebug)
      Log.w(STag, buildMessage(paramString, paramArrayOfObject));
  }

  public static void w(Throwable paramThrowable)
  {
    if (SDebug)
      Log.w(STag, "", paramThrowable);
  }

  public static void w(Throwable paramThrowable, String paramString, Object[] paramArrayOfObject)
  {
    if (SDebug)
      Log.w(STag, buildMessage(paramString, paramArrayOfObject), paramThrowable);
  }

  public static void wtf(String paramString)
  {
    if (SDebug)
      Log.wtf(STag, paramString);
  }

  public static void wtf(String paramString, Object[] paramArrayOfObject)
  {
    if (SDebug)
      Log.wtf(STag, buildMessage(paramString, paramArrayOfObject));
  }

  public static void wtf(Throwable paramThrowable)
  {
    if (SDebug)
      Log.wtf(STag, "", paramThrowable);
  }

  public static void wtf(Throwable paramThrowable, String paramString)
  {
    if (SDebug)
      Log.wtf(STag, paramString, paramThrowable);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.yolanda.nohttp.Logger
 * JD-Core Version:    0.6.2
 */