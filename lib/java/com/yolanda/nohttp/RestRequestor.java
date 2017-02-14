package com.yolanda.nohttp;

import android.text.TextUtils;
import com.yolanda.nohttp.security.Certificate;
import java.io.UnsupportedEncodingException;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class RestRequestor<T>
  implements Request<T>, AnalyzeRequest
{
  private Object cancelSign;
  private boolean inQueue = false;
  private boolean isAllowHttps = true;
  private boolean isCaneled;
  private boolean isStart = false;
  private Certificate mCertificate;
  private int mConnectTimeout = 8000;
  protected Map<String, Object> mParamMap = null;
  private int mReadTimeout = 8000;
  protected int mRequestMethod;
  private Headers mheaders;
  private String requestBody = "";
  private Object tag;
  protected String url;
  private boolean urlBuilded = false;

  public RestRequestor(String paramString)
  {
    this(paramString, 0);
  }

  public RestRequestor(String paramString, int paramInt)
  {
    BasicConnection.checkRequestMethod(paramInt);
    if (TextUtils.isEmpty(paramString))
      throw new IllegalArgumentException("url is null");
    if ((paramInt < 0) || (paramInt > 7))
      throw new IllegalArgumentException("RequestMethod error, value shuld from RequestMethod");
    String str;
    if (paramString.regionMatches(true, 0, "ws://", 0, 5))
      str = "http" + paramString.substring(2);
    while (true)
    {
      this.url = str;
      this.mRequestMethod = paramInt;
      this.mheaders = new Headers();
      this.mParamMap = new LinkedHashMap();
      return;
      str = paramString;
      if (paramString.regionMatches(true, 0, "wss://", 0, 6))
        str = "https" + paramString.substring(3);
    }
  }

  public void add(String paramString, byte paramByte)
  {
    this.mParamMap.put(paramString, Integer.toString(paramByte));
  }

  public void add(String paramString, char paramChar)
  {
    this.mParamMap.put(paramString, String.valueOf(paramChar));
  }

  public void add(String paramString, double paramDouble)
  {
    this.mParamMap.put(paramString, Double.toString(paramDouble));
  }

  public void add(String paramString, float paramFloat)
  {
    this.mParamMap.put(paramString, Float.toString(paramFloat));
  }

  public void add(String paramString, int paramInt)
  {
    this.mParamMap.put(paramString, Integer.toString(paramInt));
  }

  public void add(String paramString, long paramLong)
  {
    this.mParamMap.put(paramString, Long.toString(paramLong));
  }

  public void add(String paramString, Binary paramBinary)
  {
    this.mParamMap.put(paramString, paramBinary);
  }

  public void add(String paramString, CharSequence paramCharSequence)
  {
    this.mParamMap.put(paramString, String.valueOf(paramCharSequence));
  }

  public void add(String paramString, short paramShort)
  {
    this.mParamMap.put(paramString, Short.toString(paramShort));
  }

  public void add(String paramString, boolean paramBoolean)
  {
    this.mParamMap.put(paramString, String.valueOf(paramBoolean));
  }

  public void add(Map<String, String> paramMap)
  {
    if ((paramMap != null) && (paramMap.size() > 0))
      this.mParamMap.putAll(paramMap);
  }

  public void addCookie(CookieStore paramCookieStore)
  {
    try
    {
      paramCookieStore = paramCookieStore.get(new URI(this.url)).iterator();
      while (true)
      {
        if (!paramCookieStore.hasNext())
          return;
        addCookie((HttpCookie)paramCookieStore.next());
      }
    }
    catch (URISyntaxException paramCookieStore)
    {
      Logger.e(paramCookieStore);
    }
  }

  public void addCookie(HttpCookie paramHttpCookie)
  {
    try
    {
      URI localURI = new URI(this.url);
      if (HttpCookie.domainMatches(paramHttpCookie.getDomain(), localURI.getHost()))
        this.mheaders.add("Cookie", paramHttpCookie.getName() + "=" + paramHttpCookie.getValue());
      return;
    }
    catch (URISyntaxException paramHttpCookie)
    {
      Logger.e(paramHttpCookie);
    }
  }

  public void addHeader(String paramString1, String paramString2)
  {
    this.mheaders.add(paramString1, paramString2);
  }

  protected StringBuffer buildReuqestParam()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 1;
    Iterator localIterator = this.mParamMap.keySet().iterator();
    String str1;
    Object localObject;
    do
    {
      if (!localIterator.hasNext())
        return localStringBuffer;
      str1 = (String)localIterator.next();
      localObject = this.mParamMap.get(str1);
    }
    while ((localObject == null) || (!(localObject instanceof CharSequence)));
    if (i != 0)
      i = 0;
    while (true)
    {
      try
      {
        String str2 = getParamsEncoding();
        localStringBuffer.append(URLEncoder.encode(str1, str2));
        localStringBuffer.append("=");
        localStringBuffer.append(URLEncoder.encode(localObject.toString(), str2));
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        throw new RuntimeException("Encoding " + getParamsEncoding() + " format is not supported by the system");
      }
      localUnsupportedEncodingException.append("&");
    }
  }

  public void cancel()
  {
    this.isCaneled = true;
    this.isStart = false;
  }

  public void cancelBySign(Object paramObject)
  {
    if (this.cancelSign == paramObject)
      cancel();
  }

  public AnalyzeRequest getAnalyzeReqeust()
  {
    return this;
  }

  public Certificate getCertificate()
  {
    return this.mCertificate;
  }

  public int getConnectTimeout()
  {
    return this.mConnectTimeout;
  }

  public Headers getHeaders()
  {
    return this.mheaders;
  }

  public String getParamsEncoding()
  {
    return "UTF-8";
  }

  public int getReadTimeout()
  {
    return this.mReadTimeout;
  }

  public final byte[] getRequestBody()
  {
    Object localObject = buildReuqestParam();
    if (((StringBuffer)localObject).length() == 0);
    try
    {
      ((StringBuffer)localObject).append(URLEncoder.encode(this.requestBody, getParamsEncoding()));
      localObject = ((StringBuffer)localObject).toString();
      Logger.d("RequestBody: " + (String)localObject);
      return ((String)localObject).getBytes();
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      throw new RuntimeException("ParamEncoding Error: " + getParamsEncoding(), localUnsupportedEncodingException);
    }
  }

  public final int getRequestMethod()
  {
    return this.mRequestMethod;
  }

  public Object getTag()
  {
    return this.tag;
  }

  public boolean hasBinary()
  {
    Iterator localIterator = this.mParamMap.keySet().iterator();
    String str;
    do
    {
      if (!localIterator.hasNext())
        return false;
      str = (String)localIterator.next();
    }
    while (!(this.mParamMap.get(str) instanceof Binary));
    return true;
  }

  public boolean inQueue()
  {
    return this.inQueue;
  }

  public boolean isAllowHttps()
  {
    return this.isAllowHttps;
  }

  public boolean isCanceled()
  {
    return this.isCaneled;
  }

  public final boolean isOutPut()
  {
    switch (this.mRequestMethod)
    {
    case 0:
    case 3:
    case 4:
    case 5:
    case 6:
    default:
      return false;
    case 1:
    case 2:
      return true;
    case 7:
    }
    return true;
  }

  public boolean isStarted()
  {
    return this.isStart;
  }

  public Set<String> keySet()
  {
    return this.mParamMap.keySet();
  }

  public void remove(String paramString)
  {
    this.mParamMap.remove(paramString);
  }

  public void removeAll()
  {
    this.mParamMap.clear();
  }

  public void removeAllHeaders()
  {
    this.mheaders.clear();
  }

  public void removeHeader(String paramString)
  {
    this.mheaders.removeAll(paramString);
  }

  public void setAllowHttps(boolean paramBoolean)
  {
    this.isAllowHttps = paramBoolean;
  }

  public void setCancelSign(Object paramObject)
  {
    this.cancelSign = paramObject;
  }

  public void setCertificate(Certificate paramCertificate)
  {
    this.mCertificate = paramCertificate;
  }

  public void setConnectTimeout(int paramInt)
  {
    this.mConnectTimeout = paramInt;
  }

  public void setHeader(String paramString1, String paramString2)
  {
    this.mheaders.set(paramString1, paramString2);
  }

  public void setReadTimeout(int paramInt)
  {
    this.mReadTimeout = paramInt;
  }

  public void setRequestBody(String paramString)
  {
    if ((!TextUtils.isEmpty(paramString)) && (isOutPut()))
    {
      this.mParamMap.clear();
      this.requestBody = paramString;
    }
  }

  public void setTag(Object paramObject)
  {
    this.tag = paramObject;
  }

  public void start()
  {
    this.isStart = true;
    this.isCaneled = false;
  }

  public void takeQueue(boolean paramBoolean)
  {
    this.inQueue = paramBoolean;
  }

  public final String url()
  {
    StringBuffer localStringBuffer1;
    StringBuffer localStringBuffer2;
    if (!this.urlBuilded)
    {
      this.urlBuilded = true;
      localStringBuffer1 = new StringBuffer(this.url);
      if ((!isOutPut()) && (this.mParamMap.size() > 0))
      {
        localStringBuffer2 = buildReuqestParam();
        if ((!this.url.contains("?")) || (!this.url.contains("=")) || (localStringBuffer2.length() <= 0))
          break label107;
        localStringBuffer1.append("&");
      }
    }
    while (true)
    {
      localStringBuffer1.append(localStringBuffer2);
      this.url = localStringBuffer1.toString();
      return this.url;
      label107: if (localStringBuffer2.length() > 0)
        localStringBuffer1.append("?");
    }
  }

  public Object value(String paramString)
  {
    return this.mParamMap.get(paramString);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.yolanda.nohttp.RestRequestor
 * JD-Core Version:    0.6.2
 */