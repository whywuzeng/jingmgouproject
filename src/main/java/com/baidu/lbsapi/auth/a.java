package com.baidu.lbsapi.auth;

import android.util.Log;

class a
{
  public static boolean a = false;

  public static String a()
  {
    StackTraceElement localStackTraceElement = new java.lang.Throwable().getStackTrace()[2];
    return localStackTraceElement.getFileName() + "[" + localStackTraceElement.getLineNumber() + "]";
  }

  public static void a(String paramString)
  {
    if (Thread.currentThread().getStackTrace().length != 0)
      Log.d(a(), paramString);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.lbsapi.auth.a
 * JD-Core Version:    0.6.2
 */