package com.yolanda.nohttp;

import java.net.HttpCookie;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class RestResponser<T>
  implements Response<T>
{
  private final byte[] byteArray;
  private final Headers headers;
  private final boolean isSucceed;
  private final int responseCode;
  private final T result;
  private final Object tag;
  private final String url;

  public RestResponser(String paramString, boolean paramBoolean, int paramInt, Headers paramHeaders, byte[] paramArrayOfByte, Object paramObject, T paramT)
  {
    this.url = paramString;
    this.isSucceed = paramBoolean;
    this.responseCode = paramInt;
    this.headers = paramHeaders;
    this.byteArray = paramArrayOfByte;
    this.tag = paramObject;
    this.result = paramT;
  }

  public T get()
  {
    return this.result;
  }

  public byte[] getByteArray()
  {
    return this.byteArray;
  }

  public int getContentLength()
  {
    int i = 0;
    try
    {
      if (this.headers != null)
        i = Integer.valueOf(this.headers.get("Content-Length")).intValue();
      return i;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      Logger.wtf(localNumberFormatException);
    }
    return 0;
  }

  public String getContentType()
  {
    if (this.headers == null)
      return null;
    return this.headers.get("Content-Type");
  }

  public List<HttpCookie> getCookies()
  {
    return HeaderParser.parseCookie(this.headers);
  }

  public String getErrorMessage()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if ((!this.isSucceed) && (this.byteArray != null) && (this.byteArray.length > 0))
      localStringBuilder.append(new String(this.byteArray));
    return localStringBuilder.toString();
  }

  public Headers getHeaders()
  {
    return this.headers;
  }

  public List<String> getHeaders(String paramString)
  {
    if (this.headers == null)
      return null;
    return this.headers.values(paramString);
  }

  public int getResponseCode()
  {
    return this.responseCode;
  }

  public Object getTag()
  {
    return this.tag;
  }

  public boolean isSucceed()
  {
    return this.isSucceed;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Object localObject = getHeaders();
    Iterator localIterator1;
    if (localObject != null)
      localIterator1 = ((Headers)localObject).names().iterator();
    while (true)
    {
      if (!localIterator1.hasNext())
      {
        localObject = get();
        if (localObject != null)
          localStringBuilder.append(localObject.toString());
        return localStringBuilder.toString();
      }
      String str1 = (String)localIterator1.next();
      Iterator localIterator2 = ((Headers)localObject).values(str1).iterator();
      while (localIterator2.hasNext())
      {
        String str2 = (String)localIterator2.next();
        if (str1 != null)
          localStringBuilder.append(str1).append(": ");
        localStringBuilder.append(str2).append("\n");
      }
    }
  }

  public String url()
  {
    return this.url;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.yolanda.nohttp.RestResponser
 * JD-Core Version:    0.6.2
 */