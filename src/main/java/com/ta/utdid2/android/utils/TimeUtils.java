package com.ta.utdid2.android.utils;

import android.util.Log;

public class TimeUtils
{
  public static final String TAG = TimeUtils.class.getName();
  public static final int TOTAL_M_S_ONE_DAY = 86400000;

  public static boolean isUpToDate(long paramLong, int paramInt)
  {
    if ((System.currentTimeMillis() - paramLong) / 86400000L < paramInt);
    for (boolean bool = true; ; bool = false)
    {
      if (DebugUtils.DBG)
        Log.d(TAG, "isUpToDate: " + bool + "; oldTimestamp: " + paramLong + "; currentTimestamp" + System.currentTimeMillis());
      return bool;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ta.utdid2.android.utils.TimeUtils
 * JD-Core Version:    0.6.2
 */