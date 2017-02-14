package com.ab.http;

import android.content.Context;

public class AbHttpUtil
{
  private static AbHttpUtil mAbHttpUtil = null;
  private AbHttpClient client = null;
  private Context mContext;

  private AbHttpUtil(Context paramContext)
  {
    this.mContext = paramContext;
    this.client = new AbHttpClient(this.mContext);
  }

  public static AbHttpUtil getInstance(Context paramContext)
  {
    if (mAbHttpUtil == null)
      mAbHttpUtil = new AbHttpUtil(paramContext);
    return mAbHttpUtil;
  }

  public void get(String paramString, AbBinaryHttpResponseListener paramAbBinaryHttpResponseListener)
  {
    this.client.get(paramString, paramAbBinaryHttpResponseListener);
  }

  public void get(String paramString, AbHttpResponseListener paramAbHttpResponseListener)
  {
    this.client.get(paramString, paramAbHttpResponseListener);
  }

  public void get(String paramString, AbRequestParams paramAbRequestParams, AbFileHttpResponseListener paramAbFileHttpResponseListener)
  {
    this.client.get(paramString, paramAbRequestParams, paramAbFileHttpResponseListener);
  }

  public void get(String paramString, AbRequestParams paramAbRequestParams, AbHttpResponseListener paramAbHttpResponseListener)
  {
    this.client.get(paramString, paramAbRequestParams, paramAbHttpResponseListener);
  }

  public void post(String paramString, AbHttpResponseListener paramAbHttpResponseListener)
  {
    this.client.post(paramString, paramAbHttpResponseListener);
  }

  public void post(String paramString, AbRequestParams paramAbRequestParams, AbFileHttpResponseListener paramAbFileHttpResponseListener)
  {
    this.client.post(paramString, paramAbRequestParams, paramAbFileHttpResponseListener);
  }

  public void post(String paramString, AbRequestParams paramAbRequestParams, AbHttpResponseListener paramAbHttpResponseListener)
  {
    this.client.post(paramString, paramAbRequestParams, paramAbHttpResponseListener);
  }

  public void setDebug(boolean paramBoolean)
  {
    this.client.setDebug(paramBoolean);
  }

  public void setTimeout(int paramInt)
  {
    this.client.setTimeout(paramInt);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.http.AbHttpUtil
 * JD-Core Version:    0.6.2
 */