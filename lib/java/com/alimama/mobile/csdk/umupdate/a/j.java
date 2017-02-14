package com.alimama.mobile.csdk.umupdate.a;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class j
{
  private static Map<String, String> a;

  public static String a(String paramString)
  {
    String str = paramString;
    if (paramString.contains("/"))
      str = paramString.replace("/", "^$^");
    return str;
  }

  public static String a(String paramString, Map<String, Object> paramMap)
  {
    StringBuilder localStringBuilder1 = new StringBuilder(paramString);
    Iterator localIterator = paramMap.keySet().iterator();
    if (localIterator.hasNext())
      for (paramString = (String)localIterator.next(); ; paramString = paramMap.get(paramString).toString())
      {
        try
        {
          StringBuilder localStringBuilder2 = localStringBuilder1.append(URLEncoder.encode(paramString, "utf-8")).append("=");
          if (paramMap.get(paramString) != null)
            continue;
          paramString = "";
          localStringBuilder2.append(URLEncoder.encode(paramString, "utf-8")).append("&");
        }
        catch (UnsupportedEncodingException paramString)
        {
          Log.e("Alimama", "", paramString);
        }
        break;
      }
    if (localStringBuilder1.toString().endsWith("&"))
      localStringBuilder1.deleteCharAt(localStringBuilder1.length() - 1);
    return localStringBuilder1.toString().replaceAll(" ", "");
  }

  public static Map<String, String> a()
  {
    if ((a == null) || (a.size() < 1))
    {
      a = new HashMap();
      a.put("Accept-Encoding", "gzip");
    }
    return a;
  }

  public static void a(Context paramContext, String paramString)
  {
    paramContext.startActivity(paramContext.getPackageManager().getLaunchIntentForPackage(paramString));
  }

  public static String b(String paramString)
  {
    String str = paramString;
    if (paramString.contains("^$^"))
      str = paramString.replace("^$^", "/");
    return str;
  }

  public static boolean b(Context paramContext, String paramString)
  {
    try
    {
      paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString)));
      return true;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }

  public static String c(String paramString)
  {
    int i = 0;
    if (paramString == null)
      return null;
    try
    {
      Object localObject1 = paramString.getBytes();
      Object localObject2 = MessageDigest.getInstance("MD5");
      ((MessageDigest)localObject2).reset();
      ((MessageDigest)localObject2).update((byte[])localObject1);
      localObject1 = ((MessageDigest)localObject2).digest();
      localObject2 = new StringBuffer();
      while (i < localObject1.length)
      {
        ((StringBuffer)localObject2).append(String.format("%02X", new Object[] { Byte.valueOf(localObject1[i]) }));
        i += 1;
      }
      localObject1 = ((StringBuffer)localObject2).toString();
      return localObject1;
    }
    catch (Exception localException)
    {
    }
    return paramString.replaceAll("[^[a-z][A-Z][0-9][.][_]]", "");
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.alimama.mobile.csdk.umupdate.a.j
 * JD-Core Version:    0.6.2
 */