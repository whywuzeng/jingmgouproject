package org.android.agoo.net.mtop;

import java.util.Map;

public final class MtopRequest
{
  public static final String a = "&";
  private volatile String b;
  private volatile String c;
  private volatile String d;
  private volatile String e;
  private volatile String f;
  private volatile String g;
  private volatile String h;
  private volatile long i = -1L;
  private volatile boolean j = true;
  private String k;
  private Map<String, Object> l = null;
  private Map<String, String> m = null;

  public final String getApi()
  {
    return this.b;
  }

  public final String getAppKey()
  {
    return this.g;
  }

  public final String getAppSecret()
  {
    return this.k;
  }

  public final String getDeviceId()
  {
    return this.h;
  }

  public final String getEcode()
  {
    return this.f;
  }

  public final Map<String, Object> getParams()
  {
    return this.l;
  }

  public final String getSId()
  {
    return this.e;
  }

  public final Map<String, String> getSysParams()
  {
    return this.m;
  }

  public final long getTime()
  {
    return this.i;
  }

  public final String getTtId()
  {
    return this.d;
  }

  public final String getV()
  {
    return this.c;
  }

  public final boolean isHasSigin()
  {
    return this.j;
  }

  public final void putParams(String paramString, Object paramObject)
  {
    this.l.put(paramString, paramObject);
  }

  public final void putParams(Map<String, Object> paramMap)
  {
    this.l.putAll(paramMap);
  }

  public final void putSysParams(String paramString1, String paramString2)
  {
    this.m.put(paramString1, paramString2);
  }

  public final void putSysParams(Map<String, String> paramMap)
  {
    this.m.putAll(paramMap);
  }

  public final void setApi(String paramString)
  {
    this.b = paramString;
  }

  public final void setAppKey(String paramString)
  {
    this.g = paramString;
  }

  public final void setAppSecret(String paramString)
  {
    this.k = paramString;
  }

  public final void setDeviceId(String paramString)
  {
    this.h = paramString;
  }

  public final void setEcode(String paramString)
  {
    this.f = paramString;
  }

  public final void setHasSigin(boolean paramBoolean)
  {
    this.j = paramBoolean;
  }

  public final void setSId(String paramString)
  {
    this.e = paramString;
  }

  public final void setTime(long paramLong)
  {
    this.i = paramLong;
  }

  public final void setTtId(String paramString)
  {
    this.d = paramString;
  }

  public final void setV(String paramString)
  {
    this.c = paramString;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.net.mtop.MtopRequest
 * JD-Core Version:    0.6.2
 */