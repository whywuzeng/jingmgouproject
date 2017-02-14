package com.yolanda.nohttp;

import com.yolanda.nohttp.able.Cancelable;
import java.util.Map;

public abstract interface Request<T> extends CommonRequest, Cancelable
{
  public abstract void add(String paramString, byte paramByte);

  public abstract void add(String paramString, char paramChar);

  public abstract void add(String paramString, double paramDouble);

  public abstract void add(String paramString, float paramFloat);

  public abstract void add(String paramString, int paramInt);

  public abstract void add(String paramString, long paramLong);

  public abstract void add(String paramString, Binary paramBinary);

  public abstract void add(String paramString, CharSequence paramCharSequence);

  public abstract void add(String paramString, short paramShort);

  public abstract void add(String paramString, boolean paramBoolean);

  public abstract void add(Map<String, String> paramMap);

  public abstract T parseResponse(String paramString1, String paramString2, byte[] paramArrayOfByte);

  public abstract void remove(String paramString);

  public abstract void removeAll();

  public abstract void setCancelSign(Object paramObject);

  public abstract void setRequestBody(String paramString);

  public abstract void setTag(Object paramObject);
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.yolanda.nohttp.Request
 * JD-Core Version:    0.6.2
 */