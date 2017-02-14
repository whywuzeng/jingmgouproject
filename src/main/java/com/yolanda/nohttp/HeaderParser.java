package com.yolanda.nohttp;

import android.text.TextUtils;
import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;

public class HeaderParser
{
  private static final String VERSION_ONE_HEADER = "Set-cookie2";
  private static final String VERSION_ZERO_HEADER = "Set-cookie";

  public static boolean isGzipContent(String paramString)
  {
    return (paramString != null) && (paramString.toLowerCase(Locale.getDefault()).contains("gzip"));
  }

  public static List<HttpCookie> parseCookie(Headers paramHeaders)
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    if ((paramHeaders == null) || (i >= paramHeaders.size()))
      return localArrayList;
    Object localObject1 = paramHeaders.name(i);
    if ((localObject1 != null) && ((((String)localObject1).equalsIgnoreCase("Set-cookie")) || (((String)localObject1).equalsIgnoreCase("Set-cookie2"))))
      localObject1 = paramHeaders.values((String)localObject1).iterator();
    while (true)
    {
      if (!((Iterator)localObject1).hasNext())
      {
        i += 1;
        break;
      }
      Object localObject2 = (String)((Iterator)localObject1).next();
      try
      {
        localObject2 = HttpCookie.parse((String)localObject2).iterator();
        while (((Iterator)localObject2).hasNext())
          localArrayList.add((HttpCookie)((Iterator)localObject2).next());
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        Logger.w(localIllegalArgumentException);
      }
    }
  }

  public static List<HttpCookie> parseCookie(Map<String, List<String>> paramMap)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramMap != null)
      paramMap = paramMap.entrySet().iterator();
    while (true)
    {
      if (!paramMap.hasNext())
        return localArrayList;
      Object localObject1 = (Map.Entry)paramMap.next();
      Object localObject2 = (String)((Map.Entry)localObject1).getKey();
      if ((localObject2 != null) && ((((String)localObject2).equalsIgnoreCase("Set-cookie")) || (((String)localObject2).equalsIgnoreCase("Set-cookie2"))))
      {
        localObject1 = ((List)((Map.Entry)localObject1).getValue()).iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (String)((Iterator)localObject1).next();
          try
          {
            localObject2 = HttpCookie.parse((String)localObject2).iterator();
            while (((Iterator)localObject2).hasNext())
              localArrayList.add((HttpCookie)((Iterator)localObject2).next());
          }
          catch (IllegalArgumentException localIllegalArgumentException)
          {
            Logger.w(localIllegalArgumentException);
          }
        }
      }
    }
  }

  public static String parseHeadValue(Headers paramHeaders, String paramString1, String paramString2)
  {
    Iterator localIterator;
    if (paramHeaders != null)
      localIterator = paramHeaders.names().iterator();
    String str;
    do
    {
      if (!localIterator.hasNext())
        return paramString2;
      str = parseHeadValue(paramHeaders.values((String)localIterator.next()), paramString1, "");
    }
    while (TextUtils.isEmpty(str));
    return str;
  }

  public static String parseHeadValue(String paramString1, String paramString2, String paramString3)
  {
    if ((!TextUtils.isEmpty(paramString1)) && (!TextUtils.isEmpty(paramString2)))
      paramString1 = new StringTokenizer(paramString1, ";");
    String str;
    int i;
    do
    {
      if (!paramString1.hasMoreElements())
        return paramString3;
      str = paramString1.nextToken();
      i = str.indexOf('=');
    }
    while ((i <= 0) || (!paramString2.equalsIgnoreCase(str.substring(0, i).trim())));
    return str.substring(i + 1).trim();
  }

  public static String parseHeadValue(List<String> paramList, String paramString1, String paramString2)
  {
    int i = 0;
    String str;
    while (true)
    {
      if ((paramList == null) || (i >= paramList.size()))
        return paramString2;
      str = parseHeadValue((String)paramList.get(i), paramString1, "");
      if (!TextUtils.isEmpty(str))
        break;
      i += 1;
    }
    return str;
  }

  public static String parseHeadValue(Map<String, List<String>> paramMap, String paramString1, String paramString2)
  {
    Iterator localIterator;
    if (paramMap != null)
      localIterator = paramMap.keySet().iterator();
    String str;
    do
    {
      if (!localIterator.hasNext())
        return paramString2;
      str = parseHeadValue((List)paramMap.get((String)localIterator.next()), paramString1, "");
    }
    while (TextUtils.isEmpty(str));
    return str;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.yolanda.nohttp.HeaderParser
 * JD-Core Version:    0.6.2
 */