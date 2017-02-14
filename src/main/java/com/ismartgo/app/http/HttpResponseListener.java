package com.ismartgo.app.http;

import android.content.Context;
import com.yolanda.nohttp.OnResponseListener;
import com.yolanda.nohttp.Response;

public class HttpResponseListener<T>
  implements OnResponseListener<T>
{
  private HttpCallback<T> callback;

  public HttpResponseListener(Context paramContext, HttpCallback<T> paramHttpCallback)
  {
    if (paramContext != null)
      this.callback = paramHttpCallback;
  }

  public void onFailed(int paramInt, String paramString, Object paramObject, CharSequence paramCharSequence)
  {
    if (this.callback != null)
      this.callback.onFailed(paramInt, paramString, paramObject, paramCharSequence);
  }

  public void onFinish(int paramInt)
  {
  }

  public void onStart(int paramInt)
  {
  }

  public void onSucceed(int paramInt, Response<T> paramResponse)
  {
    if (this.callback != null)
      this.callback.onSucceed(paramInt, paramResponse);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.http.HttpResponseListener
 * JD-Core Version:    0.6.2
 */