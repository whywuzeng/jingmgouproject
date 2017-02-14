package com.mob.tools;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;

public abstract class SSDKHandlerThread
  implements Handler.Callback
{
  private static final int MSG_START = -1;
  private static final int MSG_STOP = -2;
  protected final Handler handler;
  private String name;

  public SSDKHandlerThread(String paramString)
  {
    this.name = paramString;
    paramString = new HandlerThread(paramString);
    paramString.start();
    this.handler = new Handler(paramString.getLooper(), this);
  }

  public String getName()
  {
    return this.name;
  }

  public final boolean handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default:
      onMessage(paramMessage);
    case -1:
    case -2:
    }
    while (true)
    {
      return false;
      onStart(paramMessage);
      continue;
      onStop(paramMessage);
    }
  }

  protected abstract void onMessage(Message paramMessage);

  protected void onStart(Message paramMessage)
  {
  }

  protected void onStop(Message paramMessage)
  {
  }

  public void startThread()
  {
    startThread(0, 0, null);
  }

  public void startThread(int paramInt1, int paramInt2, Object paramObject)
  {
    Message localMessage = new Message();
    localMessage.what = -1;
    localMessage.arg1 = paramInt1;
    localMessage.arg2 = paramInt2;
    localMessage.obj = paramObject;
    this.handler.sendMessage(localMessage);
  }

  public void stopThread()
  {
    stopThread(0, 0, null);
  }

  public void stopThread(int paramInt1, int paramInt2, Object paramObject)
  {
    Message localMessage = new Message();
    localMessage.what = -2;
    localMessage.arg1 = paramInt1;
    localMessage.arg2 = paramInt2;
    localMessage.obj = paramObject;
    this.handler.sendMessage(localMessage);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.mob.tools.SSDKHandlerThread
 * JD-Core Version:    0.6.2
 */