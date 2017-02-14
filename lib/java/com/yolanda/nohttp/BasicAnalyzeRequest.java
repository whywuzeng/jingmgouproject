package com.yolanda.nohttp;

import com.yolanda.nohttp.able.Queueable;
import com.yolanda.nohttp.able.Startable;
import com.yolanda.nohttp.security.Certificate;

public abstract interface BasicAnalyzeRequest extends Queueable, Startable
{
  public abstract Certificate getCertificate();

  public abstract int getConnectTimeout();

  public abstract Headers getHeaders();

  public abstract String getParamsEncoding();

  public abstract int getReadTimeout();

  public abstract int getRequestMethod();

  public abstract boolean hasBinary();

  public abstract boolean isAllowHttps();

  public abstract boolean isOutPut();

  public abstract String url();
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.yolanda.nohttp.BasicAnalyzeRequest
 * JD-Core Version:    0.6.2
 */