package com.squareup.okhttp.internal.http;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.Request;
import java.net.Proxy.Type;

public final class RequestLine
{
  static String get(Request paramRequest, Proxy.Type paramType, Protocol paramProtocol)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramRequest.method());
    localStringBuilder.append(' ');
    if (includeAuthorityInRequestLine(paramRequest, paramType))
      localStringBuilder.append(paramRequest.httpUrl());
    while (true)
    {
      localStringBuilder.append(' ');
      localStringBuilder.append(version(paramProtocol));
      return localStringBuilder.toString();
      localStringBuilder.append(requestPath(paramRequest.httpUrl()));
    }
  }

  private static boolean includeAuthorityInRequestLine(Request paramRequest, Proxy.Type paramType)
  {
    return (!paramRequest.isHttps()) && (paramType == Proxy.Type.HTTP);
  }

  public static String requestPath(HttpUrl paramHttpUrl)
  {
    String str1 = paramHttpUrl.encodedPath();
    String str2 = paramHttpUrl.encodedQuery();
    paramHttpUrl = str1;
    if (str2 != null)
      paramHttpUrl = str1 + '?' + str2;
    return paramHttpUrl;
  }

  public static String version(Protocol paramProtocol)
  {
    if (paramProtocol == Protocol.HTTP_1_0)
      return "HTTP/1.0";
    return "HTTP/1.1";
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.http.RequestLine
 * JD-Core Version:    0.6.2
 */