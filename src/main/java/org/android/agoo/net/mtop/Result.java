package org.android.agoo.net.mtop;

import java.util.HashMap;
import java.util.Map;

public final class Result
{
  private volatile boolean a;
  private volatile int b;
  private volatile String c;
  private volatile String d;
  private volatile String e;
  private Map<String, String> f = new HashMap();

  public String getData()
  {
    return this.c;
  }

  public Map<String, String> getHeaders()
  {
    return this.f;
  }

  public int getHttpCode()
  {
    return this.b;
  }

  public String getRetCode()
  {
    return this.e;
  }

  public String getRetDesc()
  {
    return this.d;
  }

  public boolean isSuccess()
  {
    return this.a;
  }

  public void setData(String paramString)
  {
    this.c = paramString;
  }

  public void setHeaders(Map<String, String> paramMap)
  {
    this.f.putAll(paramMap);
  }

  public void setHttpCode(int paramInt)
  {
    this.b = paramInt;
  }

  public void setRetCode(String paramString)
  {
    this.e = paramString;
  }

  public void setRetDesc(String paramString)
  {
    this.d = paramString;
  }

  public void setSuccess(boolean paramBoolean)
  {
    this.a = paramBoolean;
  }

  public String toString()
  {
    return "Result [isSuccess=" + this.a + ", httpCode=" + this.b + ", data=" + this.c + ", retDesc=" + this.d + ", retCode=" + this.e + ", headers=" + this.f + "]";
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.net.mtop.Result
 * JD-Core Version:    0.6.2
 */