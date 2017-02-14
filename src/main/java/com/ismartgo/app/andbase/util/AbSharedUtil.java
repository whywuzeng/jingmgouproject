package com.ismartgo.app.andbase.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class AbSharedUtil
{
  private static final String SHARED_PATH = AbAppConfig.SHARED_PATH;

  public static boolean getBoolean(Context paramContext, String paramString, boolean paramBoolean)
  {
    return getDefaultSharedPreferences(paramContext).getBoolean(paramString, paramBoolean);
  }

  public static SharedPreferences getDefaultSharedPreferences(Context paramContext)
  {
    return paramContext.getSharedPreferences(SHARED_PATH, 0);
  }

  public static int getInt(Context paramContext, String paramString)
  {
    return getDefaultSharedPreferences(paramContext).getInt(paramString, 0);
  }

  public static String getString(Context paramContext, String paramString)
  {
    return getDefaultSharedPreferences(paramContext).getString(paramString, null);
  }

  public static void putBoolean(Context paramContext, String paramString, boolean paramBoolean)
  {
    paramContext = getDefaultSharedPreferences(paramContext).edit();
    paramContext.putBoolean(paramString, paramBoolean);
    paramContext.commit();
  }

  public static void putInt(Context paramContext, String paramString, int paramInt)
  {
    paramContext = getDefaultSharedPreferences(paramContext).edit();
    paramContext.putInt(paramString, paramInt);
    paramContext.commit();
  }

  public static void putString(Context paramContext, String paramString1, String paramString2)
  {
    paramContext = getDefaultSharedPreferences(paramContext).edit();
    paramContext.putString(paramString1, paramString2);
    paramContext.commit();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.andbase.util.AbSharedUtil
 * JD-Core Version:    0.6.2
 */