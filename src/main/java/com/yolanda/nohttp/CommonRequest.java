package com.yolanda.nohttp;

import com.yolanda.nohttp.security.Certificate;
import java.net.CookieStore;
import java.net.HttpCookie;

public abstract interface CommonRequest
{
  public abstract void addCookie(CookieStore paramCookieStore);

  public abstract void addCookie(HttpCookie paramHttpCookie);

  public abstract void addHeader(String paramString1, String paramString2);

  public abstract BasicAnalyzeRequest getAnalyzeReqeust();

  public abstract void removeAllHeaders();

  public abstract void removeHeader(String paramString);

  public abstract void setAllowHttps(boolean paramBoolean);

  public abstract void setCertificate(Certificate paramCertificate);

  public abstract void setConnectTimeout(int paramInt);

  public abstract void setHeader(String paramString1, String paramString2);

  public abstract void setReadTimeout(int paramInt);
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.yolanda.nohttp.CommonRequest
 * JD-Core Version:    0.6.2
 */