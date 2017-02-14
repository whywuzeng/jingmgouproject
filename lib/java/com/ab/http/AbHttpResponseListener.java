package com.ab.http;

import android.os.Handler;
import android.os.Message;

public class AbHttpResponseListener
{
  protected static final int FAILURE_MESSAGE = 1;
  protected static final int FINISH_MESSAGE = 3;
  protected static final int PROGRESS_MESSAGE = 4;
  protected static final int RETRY_MESSAGE = 5;
  protected static final int START_MESSAGE = 2;
  protected static final int SUCCESS_MESSAGE = 0;
  private static final String TAG = "AbHttpResponseListener";
  private Handler mHandler;

  public Handler getHandler()
  {
    return this.mHandler;
  }

  protected Message obtainMessage(int paramInt, Object paramObject)
  {
    Object localObject;
    if (this.mHandler != null)
      localObject = this.mHandler.obtainMessage(paramInt, paramObject);
    Message localMessage;
    do
    {
      return localObject;
      localMessage = Message.obtain();
      localObject = localMessage;
    }
    while (localMessage == null);
    localMessage.what = paramInt;
    localMessage.obj = paramObject;
    return localMessage;
  }

  public void onFailure(int paramInt, String paramString, Throwable paramThrowable)
  {
  }

  public void onFinish()
  {
  }

  public void onProgress(int paramInt1, int paramInt2)
  {
  }

  public void onRetry()
  {
  }

  public void onStart()
  {
  }

  public void sendFailureMessage(int paramInt, String paramString, Throwable paramThrowable)
  {
    sendMessage(obtainMessage(1, new Object[] { Integer.valueOf(paramInt), paramString, paramThrowable }));
  }

  public void sendFinishMessage()
  {
    sendMessage(obtainMessage(3, null));
  }

  public void sendMessage(Message paramMessage)
  {
    if (paramMessage != null)
      paramMessage.sendToTarget();
  }

  public void sendProgressMessage(int paramInt1, int paramInt2)
  {
    sendMessage(obtainMessage(4, new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) }));
  }

  public void sendRetryMessage()
  {
    sendMessage(obtainMessage(5, null));
  }

  public void sendStartMessage()
  {
    sendMessage(obtainMessage(2, null));
  }

  public void setHandler(Handler paramHandler)
  {
    this.mHandler = paramHandler;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.http.AbHttpResponseListener
 * JD-Core Version:    0.6.2
 */