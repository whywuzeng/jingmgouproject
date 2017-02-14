package com.yolanda.nohttp;

import java.net.HttpCookie;
import java.util.List;

public abstract interface Response<T>
{
  public abstract T get();

  public abstract byte[] getByteArray();

  public abstract int getContentLength();

  public abstract String getContentType();

  public abstract List<HttpCookie> getCookies();

  public abstract String getErrorMessage();

  public abstract Headers getHeaders();

  public abstract List<String> getHeaders(String paramString);

  public abstract int getResponseCode();

  public abstract Object getTag();

  public abstract boolean isSucceed();

  public abstract String url();
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.yolanda.nohttp.Response
 * JD-Core Version:    0.6.2
 */