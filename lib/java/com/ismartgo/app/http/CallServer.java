package com.ismartgo.app.http;

import android.content.Context;
import com.ismartgo.app.application.AndroidApplication;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.Request;
import com.yolanda.nohttp.RequestQueue;
import com.yolanda.nohttp.download.DownloadQueue;

public class CallServer
{
  private static CallServer callServer;
  private static DownloadQueue downloadQueue;
  private RequestQueue requestQueue = NoHttp.newRequestQueue(AndroidApplication.getInstance());

  public static DownloadQueue getDownloadInstance()
  {
    if (downloadQueue == null)
      downloadQueue = NoHttp.newDownloadQueue(AndroidApplication.getInstance());
    return downloadQueue;
  }

  public static CallServer getRequestInstance()
  {
    if (callServer == null)
      callServer = new CallServer();
    return callServer;
  }

  public <T> void add(Context paramContext, int paramInt, Request<T> paramRequest, HttpCallback<T> paramHttpCallback)
  {
    this.requestQueue.add(paramInt, paramRequest, new HttpResponseListener(paramContext, paramHttpCallback));
  }

  public void cancelBySign(Object paramObject)
  {
    this.requestQueue.cancelAll(paramObject);
  }

  public void stopAll()
  {
    this.requestQueue.stop();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.http.CallServer
 * JD-Core Version:    0.6.2
 */