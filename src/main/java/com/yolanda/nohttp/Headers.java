package com.yolanda.nohttp;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public final class Headers
{
  private static final Comparator<String> FIELD_NAME_COMPARATOR = new Comparator()
  {
    public int compare(String paramAnonymousString1, String paramAnonymousString2)
    {
      if (paramAnonymousString1 == paramAnonymousString2)
        return 0;
      if (paramAnonymousString1 == null)
        return -1;
      if (paramAnonymousString2 == null)
        return 1;
      return String.CASE_INSENSITIVE_ORDER.compare(paramAnonymousString1, paramAnonymousString2);
    }
  };
  public static final String HEAD_KEY_ACCEPT = "Accept";
  public static final String HEAD_KEY_ACCEPT_ENCODING = "Accept-Encoding";
  public static final String HEAD_KEY_CACHE_CONTROL = "Cache-Control";
  public static final String HEAD_KEY_CONNECTION = "Connection";
  public static final String HEAD_KEY_CONTENT_LENGTH = "Content-Length";
  public static final String HEAD_KEY_CONTENT_TYPE = "Content-Type";
  public static final String HEAD_KEY_COOKIE = "Cookie";
  public static final String HEAD_KEY_COOKIE2 = "Cookie2";
  public static final String HEAD_KEY_USER_AGENT = "User-Agent";
  public static final String HEAD_VALUE_ACCEPT = "*/*";
  public static final String HEAD_VALUE_ACCEPT_ENCODING = "gzip";
  public static final String HEAD_VALUE_CACHE_CONTROL = "no-cache";
  public static final String HEAD_VALUE_CONNECTION = "Keep-Alive";
  private final List<String> namesAndValues = new ArrayList(20);

  public static void addCookiesToHeaders(Headers paramHeaders, Map<String, List<String>> paramMap)
  {
    if ((paramMap != null) && (paramHeaders != null))
      paramMap = paramMap.entrySet().iterator();
    while (true)
    {
      if (!paramMap.hasNext())
        return;
      Map.Entry localEntry = (Map.Entry)paramMap.next();
      String str = (String)localEntry.getKey();
      if ((("Cookie".equalsIgnoreCase(str)) || ("Cookie2".equalsIgnoreCase(str))) && (!((List)localEntry.getValue()).isEmpty()))
        paramHeaders.add(str, buildCookieHeader((List)localEntry.getValue()));
    }
  }

  public static String buildCookieHeader(List<String> paramList)
  {
    if (paramList.size() == 1)
      return (String)paramList.get(0);
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    int j = paramList.size();
    while (true)
    {
      if (i >= j)
        return localStringBuilder.toString();
      if (i > 0)
        localStringBuilder.append("; ");
      localStringBuilder.append((String)paramList.get(i));
      i += 1;
    }
  }

  private void checkNameAndValue(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString1.isEmpty()))
      throw new IllegalArgumentException("name == null or name is empty");
    int i = 0;
    int j = paramString1.length();
    int k;
    while (true)
    {
      if (i >= j)
      {
        if (paramString2 != null)
          break;
        throw new IllegalArgumentException("value == null");
      }
      k = paramString1.charAt(i);
      if ((k <= 31) || (k >= 127))
        throw new IllegalArgumentException(String.format("Unexpected char %#04x at %d in header name: %s", new Object[] { Integer.valueOf(k), Integer.valueOf(i), paramString1 }));
      i += 1;
    }
    i = 0;
    j = paramString2.length();
    while (true)
    {
      if (i >= j)
        return;
      k = paramString2.charAt(i);
      if ((k <= 31) || (k >= 127))
        throw new IllegalArgumentException(String.format("Unexpected char %#04x at %d in header value: %s", new Object[] { Integer.valueOf(k), Integer.valueOf(i), paramString2 }));
      i += 1;
    }
  }

  public static Headers parseMultimap(Map<String, List<String>> paramMap)
  {
    Headers localHeaders = new Headers();
    if (paramMap != null)
      paramMap = paramMap.entrySet().iterator();
    while (true)
    {
      if (!paramMap.hasNext())
        return localHeaders;
      Object localObject = (Map.Entry)paramMap.next();
      String str1 = (String)((Map.Entry)localObject).getKey();
      if (!TextUtils.isEmpty(str1))
      {
        localObject = ((List)((Map.Entry)localObject).getValue()).iterator();
        while (((Iterator)localObject).hasNext())
        {
          String str2 = (String)((Iterator)localObject).next();
          if (!TextUtils.isEmpty(str2))
            localHeaders.add(str1, str2);
        }
      }
    }
  }

  public static Map<String, String> parseRequestCookie(Headers paramHeaders)
  {
    HashMap localHashMap = new HashMap();
    int i;
    if (paramHeaders != null)
    {
      localHashMap.put("Cookie", "");
      localHashMap.put("Cookie2", "");
      if ((paramHeaders != null) && (paramHeaders.size() > 0))
      {
        i = 0;
        if (i < paramHeaders.size())
          break label57;
      }
    }
    return localHashMap;
    label57: if ("Cookie".equalsIgnoreCase(paramHeaders.name(i)))
      localHashMap.put("Cookie", (String)localHashMap.get("Cookie") + paramHeaders.value(i) + "; ");
    while (true)
    {
      i += 1;
      break;
      if ("Cookie2".equalsIgnoreCase(paramHeaders.name(i)))
        localHashMap.put("Cookie2", (String)localHashMap.get("Cookie2") + paramHeaders.value(i) + "; ");
    }
  }

  public static Map<String, List<String>> toMultimap(Headers paramHeaders)
  {
    TreeMap localTreeMap = new TreeMap(FIELD_NAME_COMPARATOR);
    int i = 0;
    int j = paramHeaders.size();
    while (true)
    {
      if ((i >= j) || (paramHeaders == null))
        return Collections.unmodifiableMap(localTreeMap);
      String str1 = paramHeaders.name(i);
      String str2 = paramHeaders.value(i);
      ArrayList localArrayList = new ArrayList();
      List localList = (List)localTreeMap.get(str1);
      if (localList != null)
        localArrayList.addAll(localList);
      localArrayList.add(str2);
      localTreeMap.put(str1, Collections.unmodifiableList(localArrayList));
      i += 1;
    }
  }

  public void add(String paramString1, String paramString2)
  {
    checkNameAndValue(paramString1, paramString2);
    addSummation(paramString1, paramString2);
  }

  public void addLine(String paramString)
  {
    int i = paramString.indexOf(":");
    if (i == -1)
      throw new IllegalArgumentException("Unexpected header: " + paramString);
    add(paramString.substring(0, i).trim(), paramString.substring(i + 1));
  }

  void addSummation(String paramString1, String paramString2)
  {
    this.namesAndValues.add(paramString1);
    this.namesAndValues.add(paramString2.trim());
  }

  public void clear()
  {
    this.namesAndValues.clear();
  }

  public String get(String paramString)
  {
    int i = this.namesAndValues.size() - 2;
    while (true)
    {
      if (i < 0)
        return null;
      if (paramString.equalsIgnoreCase((String)this.namesAndValues.get(i)))
        return (String)this.namesAndValues.get(i + 1);
      i -= 2;
    }
  }

  public String name(int paramInt)
  {
    paramInt *= 2;
    if ((paramInt < 0) || (paramInt >= this.namesAndValues.size()))
      return null;
    return (String)this.namesAndValues.get(paramInt);
  }

  public Set<String> names()
  {
    TreeSet localTreeSet = new TreeSet(String.CASE_INSENSITIVE_ORDER);
    int i = 0;
    int j = size();
    while (true)
    {
      if (i >= j)
        return Collections.unmodifiableSet(localTreeSet);
      localTreeSet.add(name(i));
      i += 1;
    }
  }

  public void removeAll(String paramString)
  {
    int j;
    for (int i = 0; ; i = j + 2)
    {
      if (i >= this.namesAndValues.size())
        return;
      j = i;
      if (paramString.equalsIgnoreCase((String)this.namesAndValues.get(i)))
      {
        this.namesAndValues.remove(i);
        this.namesAndValues.remove(i);
        j = i - 2;
      }
    }
  }

  public void set(String paramString1, String paramString2)
  {
    checkNameAndValue(paramString1, paramString2);
    removeAll(paramString1);
    addSummation(paramString1, paramString2);
  }

  public int size()
  {
    return this.namesAndValues.size() / 2;
  }

  public String value(int paramInt)
  {
    paramInt = paramInt * 2 + 1;
    if ((paramInt < 0) || (paramInt >= this.namesAndValues.size()))
      return null;
    return (String)this.namesAndValues.get(paramInt);
  }

  public List<String> values(String paramString)
  {
    Object localObject1 = null;
    int i = 0;
    int j = this.namesAndValues.size();
    while (true)
    {
      if (i >= j)
      {
        if (localObject1 == null)
          break;
        return Collections.unmodifiableList((List)localObject1);
      }
      Object localObject2 = localObject1;
      if (paramString.equalsIgnoreCase(name(i)))
      {
        localObject2 = localObject1;
        if (localObject1 == null)
          localObject2 = new ArrayList(2);
        ((List)localObject2).add(value(i));
      }
      i += 1;
      localObject1 = localObject2;
    }
    return Collections.emptyList();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.yolanda.nohttp.Headers
 * JD-Core Version:    0.6.2
 */