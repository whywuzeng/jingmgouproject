package com.umeng.message.proguard;

import android.content.Context;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class Z
{
  private static String a()
  {
    Object localObject = w.a().f();
    if (localObject != null)
      try
      {
        localObject = am.c(((String)localObject).getBytes("UTF-8"));
        if ((localObject != null) && (localObject.length > 0))
        {
          localObject = ai.b((byte[])localObject, 2);
          return localObject;
        }
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        localUnsupportedEncodingException.printStackTrace();
      }
    return "";
  }

  public static String a(String paramString)
    throws Exception
  {
    try
    {
      paramString = d(paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
    }
    return d(t.a());
  }

  private static String a(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8)
    throws Exception
  {
    aa localaa = w.a().d();
    if (localaa != null)
    {
      HashMap localHashMap = new HashMap();
      localHashMap.put("appkey", paramString1);
      localHashMap.put("channel", paramString2);
      localHashMap.put("app_version", paramString3);
      localHashMap.put("platform", paramString4);
      localHashMap.put("sdk_version", paramString5);
      localHashMap.put("utdid", paramString6);
      localHashMap.put("v", paramString8);
      localHashMap.put("t", paramString7);
      return localaa.a(localHashMap);
    }
    return null;
  }

  public static String b(String paramString)
    throws Exception
  {
    return String.format("%s&dd=%s&nsgs=1", new Object[] { d(paramString), URLEncoder.encode(a(), "UTF-8") });
  }

  public static String c(String paramString)
    throws Exception
  {
    return String.format("%s", new Object[] { d(paramString) });
  }

  private static String d(String paramString)
    throws Exception
  {
    Object localObject3 = w.a().k();
    String str1 = w.a().l();
    Object localObject2 = w.a().h();
    Object localObject1 = localObject2;
    if (localObject2 == null)
      localObject1 = "";
    String str2 = (String)z.a((Context)localObject3).get(v.k.toString());
    String str3 = (String)z.a((Context)localObject3).get(v.q.toString());
    localObject2 = w.a().c();
    if (localObject2 != null)
    {
      localObject2 = ((s)localObject2).a();
      if (at.a((String)localObject2));
    }
    while (true)
    {
      localObject3 = (String)z.a((Context)localObject3).get(v.u.toString());
      String str4 = String.valueOf(System.currentTimeMillis());
      String str5 = a(str1, (String)localObject1, str2, str3, (String)localObject2, (String)localObject3, str4, "2.0");
      return String.format("%s?ak=%s&av=%s&c=%s&v=%s&s=%s&d=%s&sv=%s&p=%s&t=%s&u=%s", new Object[] { paramString, e(str1), e(str2), e((String)localObject1), e("2.0"), e(str5), e((String)localObject3), localObject2, str3, str4, "" });
      localObject2 = "4.1.0";
    }
  }

  private static String e(String paramString)
  {
    if (paramString == null)
      return "";
    try
    {
      String str = URLEncoder.encode(paramString, "UTF-8");
      return str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      localUnsupportedEncodingException.printStackTrace();
    }
    return paramString;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.Z
 * JD-Core Version:    0.6.2
 */