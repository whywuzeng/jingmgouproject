package com.umeng.message.proguard;

import android.content.Context;

public class R
{
  private static String a = null;
  private static boolean b = true;

  public static String a(Context paramContext)
  {
    if (a != null)
      return a;
    if ((paramContext != null) && (b));
    try
    {
      Class localClass = Class.forName("com.taobao.dp.DeviceSecuritySDK");
      if (localClass != null)
      {
        paramContext = ar.a(localClass, "getInstance", new Object[] { paramContext }, new Class[] { Context.class });
        if (paramContext != null)
        {
          paramContext = ar.a(paramContext, "getSecurityToken");
          if (paramContext != null)
            a = (String)paramContext;
          return (String)paramContext;
        }
      }
      else
      {
        b = false;
      }
      label85: return null;
    }
    catch (Exception paramContext)
    {
      break label85;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.R
 * JD-Core Version:    0.6.2
 */