package com.ismartgo.app.utils;

import android.util.Log;

public class LogUtils
{
  private static final boolean isDebug = true;

  public static void d(String paramString1, String paramString2)
  {
    Log.d(paramString1, paramString2);
  }

  public static void e(String paramString1, String paramString2)
  {
    Log.e(paramString1, paramString2);
  }

  public static void i(String paramString1, String paramString2)
  {
    Log.i(paramString1, paramString2);
  }

  public static void v(String paramString1, String paramString2)
  {
    Log.v(paramString1, paramString2);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.utils.LogUtils
 * JD-Core Version:    0.6.2
 */