package org.android.spdy;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public final class SpdyRequest
{
  public static final String GET_METHOD = "GET";
  public static final String POST_METHOD = "POST";
  private int connectionTimeoutMs = 0;
  private Map<String, String> extHead;
  private String host;
  private String method;
  private int port;
  private RequestPriority priority;
  private String proxyIp = "0.0.0.0";
  private int proxyPort = 0;
  private int requestTimeoutMs = 0;
  private int retryTimes = 0;
  private URL url;

  public SpdyRequest(URL paramURL, String paramString)
  {
    this.url = paramURL;
    this.host = paramURL.getHost();
    this.port = paramURL.getPort();
    if (this.port < 0)
      this.port = paramURL.getDefaultPort();
    this.method = paramString;
    this.extHead = new HashMap(5);
    this.priority = RequestPriority.DEFAULT_PRIORITY;
  }

  public SpdyRequest(URL paramURL, String paramString1, int paramInt1, String paramString2, int paramInt2, String paramString3, RequestPriority paramRequestPriority, int paramInt3, int paramInt4, int paramInt5)
  {
    this.url = paramURL;
    this.host = paramString1;
    this.port = paramInt1;
    if ((paramString2 != null) && (paramInt2 != 0))
    {
      this.proxyIp = paramString2;
      this.proxyPort = paramInt2;
    }
    this.method = paramString3;
    this.extHead = new HashMap(5);
    this.priority = paramRequestPriority;
    if (paramRequestPriority == null)
      this.priority = RequestPriority.DEFAULT_PRIORITY;
    this.requestTimeoutMs = paramInt3;
    this.connectionTimeoutMs = paramInt4;
    this.retryTimes = paramInt5;
  }

  public SpdyRequest(URL paramURL, String paramString1, int paramInt, String paramString2, RequestPriority paramRequestPriority)
  {
    this.url = paramURL;
    this.host = paramString1;
    this.port = paramInt;
    this.method = paramString2;
    this.extHead = new HashMap(5);
    this.priority = paramRequestPriority;
    if (paramRequestPriority == null)
      this.priority = RequestPriority.DEFAULT_PRIORITY;
  }

  public SpdyRequest(URL paramURL, String paramString, RequestPriority paramRequestPriority)
  {
    this.url = paramURL;
    this.host = paramURL.getHost();
    this.port = paramURL.getPort();
    if (this.port < 0)
      this.port = paramURL.getDefaultPort();
    this.method = paramString;
    this.extHead = new HashMap(5);
    this.priority = paramRequestPriority;
    if (paramRequestPriority == null)
      this.priority = RequestPriority.DEFAULT_PRIORITY;
  }

  public SpdyRequest(URL paramURL, String paramString, RequestPriority paramRequestPriority, int paramInt1, int paramInt2)
  {
    this.url = paramURL;
    this.host = paramURL.getHost();
    this.port = paramURL.getPort();
    if (this.port < 0)
      this.port = paramURL.getDefaultPort();
    this.method = paramString;
    this.extHead = new HashMap(5);
    this.priority = paramRequestPriority;
    if (paramRequestPriority == null)
      this.priority = RequestPriority.DEFAULT_PRIORITY;
    this.requestTimeoutMs = paramInt1;
    this.connectionTimeoutMs = paramInt2;
  }

  private String getPath()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.url.getPath());
    if (this.url.getQuery() != null)
      localStringBuilder.append("?").append(this.url.getQuery());
    if (this.url.getRef() != null)
      localStringBuilder.append("#").append(this.url.getRef());
    return localStringBuilder.toString();
  }

  public void addHeader(String paramString1, String paramString2)
  {
    this.extHead.put(paramString1, paramString2);
  }

  public void addHeaders(Map<String, String> paramMap)
  {
    this.extHead.putAll(paramMap);
  }

  String getAuthority()
  {
    return this.host + ":" + Integer.toString(this.port) + "/" + this.proxyIp + ":" + this.proxyPort;
  }

  public int getConnectionTimeoutMs()
  {
    return this.connectionTimeoutMs;
  }

  Map<String, String> getHeaders()
  {
    HashMap localHashMap = new HashMap(5);
    localHashMap.put(":path", getPath());
    localHashMap.put(":method", this.method);
    localHashMap.put(":version", "HTTP/1.1");
    localHashMap.put(":host", this.url.getAuthority());
    localHashMap.put(":scheme", this.url.getProtocol());
    if ((this.extHead != null) && (this.extHead.size() > 0))
      localHashMap.putAll(this.extHead);
    return localHashMap;
  }

  String getHost()
  {
    return this.host;
  }

  String getMethod()
  {
    return this.method;
  }

  int getPort()
  {
    if (this.port < 0)
      return 80;
    return this.port;
  }

  int getPriority()
  {
    return this.priority.getPriorityInt();
  }

  String getProxyIp()
  {
    return this.proxyIp;
  }

  int getProxyPort()
  {
    return this.proxyPort;
  }

  public int getRequestTimeoutMs()
  {
    return this.requestTimeoutMs;
  }

  public int getRetryTimes()
  {
    return this.retryTimes;
  }

  URL getUrl()
  {
    return this.url;
  }

  String getUrlPath()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.url.getProtocol()).append("://").append(this.url.getAuthority()).append(getPath());
    return localStringBuilder.toString();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.spdy.SpdyRequest
 * JD-Core Version:    0.6.2
 */