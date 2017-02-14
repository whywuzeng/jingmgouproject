package com.baidu.vi;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

public class VMsg
{
  private static final String a = VMsg.class.getSimpleName();
  private static Handler b;
  private static HandlerThread c;

  private static native void OnUserCommand1(int paramInt1, int paramInt2, int paramInt3, long paramLong);

  public static void destroy()
  {
    c.quit();
    c = null;
    b.removeCallbacksAndMessages(null);
    b = null;
  }

  public static void init()
  {
    c = new HandlerThread("VIMsgThread");
    c.start();
    b = new a(c.getLooper());
  }

  private static void postMessage(int paramInt1, int paramInt2, int paramInt3, long paramLong)
  {
    if (b == null)
      return;
    Message.obtain(b, paramInt1, paramInt2, paramInt3, Long.valueOf(paramLong)).sendToTarget();
  }

  static class a extends Handler
  {
    public a(Looper paramLooper)
    {
      super();
    }

    public void handleMessage(Message paramMessage)
    {
      if (paramMessage.obj == null);
      for (long l = 0L; ; l = ((Long)paramMessage.obj).longValue())
      {
        VMsg.a(paramMessage.what, paramMessage.arg1, paramMessage.arg2, l);
        return;
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.vi.VMsg
 * JD-Core Version:    0.6.2
 */