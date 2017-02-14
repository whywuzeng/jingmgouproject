package com.squareup.okhttp.internal.http;

public final class HttpMethod
{
  public static boolean invalidatesCache(String paramString)
  {
    return (paramString.equals("POST")) || (paramString.equals("PATCH")) || (paramString.equals("PUT")) || (paramString.equals("DELETE"));
  }

  public static boolean permitsRequestBody(String paramString)
  {
    return (requiresRequestBody(paramString)) || (paramString.equals("DELETE"));
  }

  public static boolean requiresRequestBody(String paramString)
  {
    return (paramString.equals("POST")) || (paramString.equals("PUT")) || (paramString.equals("PATCH"));
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.http.HttpMethod
 * JD-Core Version:    0.6.2
 */