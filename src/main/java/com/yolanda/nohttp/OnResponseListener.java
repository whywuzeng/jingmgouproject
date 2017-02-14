package com.yolanda.nohttp;

public abstract interface OnResponseListener<T>
{
  public abstract void onFailed(int paramInt, String paramString, Object paramObject, CharSequence paramCharSequence);

  public abstract void onFinish(int paramInt);

  public abstract void onStart(int paramInt);

  public abstract void onSucceed(int paramInt, Response<T> paramResponse);
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.yolanda.nohttp.OnResponseListener
 * JD-Core Version:    0.6.2
 */