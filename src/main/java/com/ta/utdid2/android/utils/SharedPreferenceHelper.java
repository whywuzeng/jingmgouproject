package com.ta.utdid2.android.utils;

import android.annotation.TargetApi;
import android.content.SharedPreferences.Editor;

public class SharedPreferenceHelper
{
  @TargetApi(9)
  public static void apply(SharedPreferences.Editor paramEditor)
  {
    if (paramEditor != null)
      paramEditor.apply();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ta.utdid2.android.utils.SharedPreferenceHelper
 * JD-Core Version:    0.6.2
 */