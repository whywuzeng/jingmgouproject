package com.yolanda.nohttp;

public abstract interface BasicConnectionRest
{
  public abstract <T> Response<T> request(Request<T> paramRequest);
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.yolanda.nohttp.BasicConnectionRest
 * JD-Core Version:    0.6.2
 */