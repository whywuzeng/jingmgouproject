package com.ta.utdid2.android.utils;

import java.lang.reflect.Method;

public class DebugUtils
{
  public static boolean DBG = false;
  private static final String PROPERTY_DEBUG = "alidebug";
  private static Class<?> mClassType;
  private static Method mGetIntMethod;
  private static Method mGetMethod;

  static
  {
    boolean bool = true;
    if (getInt("alidebug", 0) == 1);
    while (true)
    {
      DBG = bool;
      mClassType = null;
      mGetMethod = null;
      mGetIntMethod = null;
      return;
      bool = false;
    }
  }

  public static String get(String paramString)
  {
    init();
    try
    {
      paramString = (String)mGetMethod.invoke(mClassType, new Object[] { paramString });
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }

  public static int getInt(String paramString, int paramInt)
  {
    init();
    try
    {
      int i = ((Integer)mGetIntMethod.invoke(mClassType, new Object[] { paramString, Integer.valueOf(paramInt) })).intValue();
      return i;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return paramInt;
  }

  private static void init()
  {
    try
    {
      if (mClassType == null)
      {
        mClassType = Class.forName("android.os.SystemProperties");
        mGetMethod = mClassType.getDeclaredMethod("get", new Class[] { String.class });
        mGetIntMethod = mClassType.getDeclaredMethod("getInt", new Class[] { String.class, Integer.TYPE });
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ta.utdid2.android.utils.DebugUtils
 * JD-Core Version:    0.6.2
 */