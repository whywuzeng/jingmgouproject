package com.yolanda.nohttp.able;

public abstract interface Cancelable
{
  public abstract void cancel();

  public abstract void cancelBySign(Object paramObject);

  public abstract boolean isCanceled();
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.yolanda.nohttp.able.Cancelable
 * JD-Core Version:    0.6.2
 */