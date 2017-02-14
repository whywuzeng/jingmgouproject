package org.android.spdy;

import android.util.Log;

public class spduLog
{
  private static long savedTraffic = 0L;

  public static void Logd(String paramString1, String paramString2)
  {
    if ((SpdyAgent.enableDebug) && (paramString1 != null) && (paramString2 != null))
      Log.d(paramString1, Thread.currentThread().getId() + " - " + paramString2);
  }

  public static void Logd(String paramString1, String paramString2, long paramLong)
  {
    if ((SpdyAgent.enableDebug) && (paramString1 != null) && (paramString2 != null))
      Log.i(paramString1, Thread.currentThread().getId() + " - " + paramString2 + (System.nanoTime() - paramLong) / 1000000L);
  }

  public static void Loge(String paramString1, String paramString2)
  {
    if ((SpdyAgent.enableDebug) && (paramString1 != null) && (paramString2 != null))
      Log.e(paramString1, Thread.currentThread().getId() + " - " + paramString2);
  }

  public static void Logf(String paramString1, String paramString2)
  {
    if ((paramString1 != null) && (paramString2 != null))
      Log.d(paramString1, Thread.currentThread().getId() + " - " + paramString2);
  }

  public static void Logi(String paramString1, String paramString2)
  {
    if ((SpdyAgent.enableDebug) && (paramString1 != null) && (paramString2 != null))
      Log.i(paramString1, Thread.currentThread().getId() + " - " + paramString2);
  }

  public static void Logv(String paramString1, String paramString2)
  {
    if ((SpdyAgent.enableDebug) && (paramString1 != null) && (paramString2 != null))
      Log.v(paramString1, Thread.currentThread().getId() + " - " + paramString2);
  }

  public static void Logw(String paramString1, String paramString2)
  {
    if ((SpdyAgent.enableDebug) && (paramString1 != null) && (paramString2 != null))
      Log.w(paramString1, Thread.currentThread().getId() + " - " + paramString2);
  }

  public static void addTraffic(long paramLong)
  {
    savedTraffic += paramLong;
  }

  public static long getSavedTraffic()
  {
    return savedTraffic;
  }

  public static long now()
  {
    if (SpdyAgent.enableDebug)
      return System.nanoTime();
    return 0L;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.spdy.spduLog
 * JD-Core Version:    0.6.2
 */