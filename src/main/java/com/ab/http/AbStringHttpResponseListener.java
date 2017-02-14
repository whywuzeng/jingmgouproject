package com.ab.http;

public class AbStringHttpResponseListener extends AbHttpResponseListener
{
  private static final String TAG = "AbStringHttpResponseListener";

  public void onSuccess(int paramInt, String paramString)
  {
  }

  public void sendSuccessMessage(int paramInt, String paramString)
  {
    sendMessage(obtainMessage(0, new Object[] { Integer.valueOf(paramInt), paramString }));
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.http.AbStringHttpResponseListener
 * JD-Core Version:    0.6.2
 */