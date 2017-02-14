package com.ab.http;

public class AbBinaryHttpResponseListener extends AbHttpResponseListener
{
  private static final String TAG = "AbBinaryHttpResponseListener";

  public void onSuccess(int paramInt, byte[] paramArrayOfByte)
  {
  }

  public void sendSuccessMessage(int paramInt, byte[] paramArrayOfByte)
  {
    sendMessage(obtainMessage(0, new Object[] { Integer.valueOf(paramInt), paramArrayOfByte }));
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.http.AbBinaryHttpResponseListener
 * JD-Core Version:    0.6.2
 */