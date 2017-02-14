package org.android.agoo.net.async;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

public final class RequestParams
{
  private static final String b = "UTF-8";
  private static final String c = "&";
  private static final String d = "=";
  protected ConcurrentHashMap<String, String> a;

  public RequestParams()
  {
    b();
  }

  public RequestParams(String paramString1, String paramString2)
  {
    b();
    put(paramString1, paramString2);
  }

  public RequestParams(Map<String, String> paramMap)
  {
    b();
    paramMap = paramMap.entrySet().iterator();
    while (paramMap.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramMap.next();
      put((String)localEntry.getKey(), (String)localEntry.getValue());
    }
  }

  private static String a(String paramString)
  {
    try
    {
      paramString = URLEncoder.encode(paramString, "UTF-8");
      return paramString;
    }
    catch (UnsupportedEncodingException paramString)
    {
    }
    throw new IllegalArgumentException(paramString);
  }

  private void b()
  {
    this.a = new ConcurrentHashMap();
  }

  public static final String format(Map<String, String> paramMap)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = paramMap.entrySet().iterator();
    if (localIterator.hasNext())
    {
      paramMap = (Map.Entry)localIterator.next();
      String str = a((String)paramMap.getKey());
      paramMap = (String)paramMap.getValue();
      if (paramMap != null);
      for (paramMap = a(paramMap); ; paramMap = "")
      {
        if (localStringBuilder.length() > 0)
          localStringBuilder.append("&");
        localStringBuilder.append(str);
        localStringBuilder.append("=");
        localStringBuilder.append(paramMap);
        break;
      }
    }
    return localStringBuilder.toString();
  }

  protected List<BasicNameValuePair> a()
  {
    LinkedList localLinkedList = new LinkedList();
    Iterator localIterator = this.a.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      localLinkedList.add(new BasicNameValuePair((String)localEntry.getKey(), (String)localEntry.getValue()));
    }
    return localLinkedList;
  }

  public final HttpEntity getEntity()
  {
    try
    {
      UrlEncodedFormEntity localUrlEncodedFormEntity = new UrlEncodedFormEntity(a(), "UTF-8");
      return localUrlEncodedFormEntity;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      localUnsupportedEncodingException.printStackTrace();
    }
    return null;
  }

  public final String getParamString()
  {
    return format(this.a);
  }

  public final void put(String paramString1, String paramString2)
  {
    if ((paramString1 != null) && (paramString2 != null))
      this.a.put(paramString1, paramString2);
  }

  public final void remove(String paramString)
  {
    this.a.remove(paramString);
  }

  public final String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = this.a.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      if (localStringBuilder.length() > 0)
        localStringBuilder.append("&");
      localStringBuilder.append((String)localEntry.getKey());
      localStringBuilder.append("=");
      localStringBuilder.append((String)localEntry.getValue());
    }
    return localStringBuilder.toString();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.net.async.RequestParams
 * JD-Core Version:    0.6.2
 */