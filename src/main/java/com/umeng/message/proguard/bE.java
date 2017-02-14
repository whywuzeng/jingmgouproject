package com.umeng.message.proguard;

import android.text.TextUtils;
import java.util.Map;

public class bE
{
  public static final String a = "wjas";
  public static final String b = "apoll";
  public static final String c = "nginx";
  public static final String d = "server";
  private static final String e = "ServerUtil";

  public static final boolean a(String paramString)
  {
    try
    {
      paramString = paramString.toLowerCase();
      if (!TextUtils.equals(paramString, "wjas"))
      {
        boolean bool = TextUtils.equals(paramString, "nginx");
        if (!bool);
      }
      else
      {
        return true;
      }
    }
    catch (Throwable paramString)
    {
    }
    return false;
  }

  public static final boolean a(Map<String, String> paramMap, int paramInt)
  {
    if (paramMap != null);
    boolean bool;
    try
    {
      if (paramMap.isEmpty())
      {
        bd.c("ServerUtil", "chechHttp--->[headers==null]");
        return false;
      }
      paramMap = (String)paramMap.get("server");
      if (TextUtils.isEmpty(paramMap))
      {
        bd.c("ServerUtil", "chechHttp--->[serverName==null]");
        return false;
      }
      if (!a(paramMap))
      {
        bd.c("ServerUtil", "chechHttp--->[serverName!=wjas]");
        return false;
      }
      if (paramInt == 302)
      {
        bd.d("ServerUtil", "chechHttp---->[failed][" + paramInt + "]");
        return false;
      }
      bool = true;
    }
    catch (Throwable paramMap)
    {
      bool = false;
    }
    return bool;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.bE
 * JD-Core Version:    0.6.2
 */