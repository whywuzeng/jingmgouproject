package com.ismartgo.app.http;

import com.yolanda.nohttp.Response;

public abstract interface HttpCallback<T>
{
  public abstract void onFailed(int paramInt, String paramString, Object paramObject, CharSequence paramCharSequence);

  public abstract void onSucceed(int paramInt, Response<T> paramResponse);
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.http.HttpCallback
 * JD-Core Version:    0.6.2
 */