package com.yolanda.nohttp;

class NetworkRequestor<T>
{
  public final Request<T> request;
  public final OnResponseListener<T> responseListener;
  public final int what;

  public NetworkRequestor(int paramInt, Request<T> paramRequest, OnResponseListener<T> paramOnResponseListener)
  {
    this.what = paramInt;
    this.request = paramRequest;
    this.responseListener = paramOnResponseListener;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.yolanda.nohttp.NetworkRequestor
 * JD-Core Version:    0.6.2
 */