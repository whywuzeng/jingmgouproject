package org.android.agoo.client;

import java.util.Map;

public final class MtopProxyRequest
{
  private volatile String api;
  private volatile String appKey;
  private String appSecret;
  private volatile String ecode;
  private volatile boolean hasSigin = true;
  private Map<String, Object> params = null;
  private volatile String sId;
  private Map<String, String> sysParams = null;
  private volatile long time = -1L;
  private volatile String ttId;
  private volatile String v;

  public final String getApi()
  {
    return this.api;
  }

  public final String getAppKey()
  {
    return this.appKey;
  }

  public final String getAppSecret()
  {
    return this.appSecret;
  }

  public final String getEcode()
  {
    return this.ecode;
  }

  public final Map<String, Object> getParams()
  {
    return this.params;
  }

  public final String getSId()
  {
    return this.sId;
  }

  public final Map<String, String> getSysParams()
  {
    return this.sysParams;
  }

  public final long getTime()
  {
    return this.time;
  }

  public final String getTtId()
  {
    return this.ttId;
  }

  public final String getV()
  {
    return this.v;
  }

  public final boolean isHasSigin()
  {
    return this.hasSigin;
  }

  public final void putParams(String paramString, Object paramObject)
  {
    this.params.put(paramString, paramObject);
  }

  public final void putSysParams(String paramString1, String paramString2)
  {
    this.params.put(paramString1, paramString2);
  }

  public final void setApi(String paramString)
  {
    this.api = paramString;
  }

  public final void setAppKey(String paramString)
  {
    this.appKey = paramString;
  }

  public final void setAppSecret(String paramString)
  {
    this.appSecret = paramString;
  }

  public final void setEcode(String paramString)
  {
    this.ecode = paramString;
  }

  public final void setHasSigin(boolean paramBoolean)
  {
    this.hasSigin = paramBoolean;
  }

  public final void setSId(String paramString)
  {
    this.sId = paramString;
  }

  public final void setTime(long paramLong)
  {
    this.time = paramLong;
  }

  public final void setTtId(String paramString)
  {
    this.ttId = paramString;
  }

  public final void setV(String paramString)
  {
    this.v = paramString;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.client.MtopProxyRequest
 * JD-Core Version:    0.6.2
 */