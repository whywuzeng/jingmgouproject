package cn.smssdk.a;

import android.os.Handler.Callback;
import android.os.Message;
import cn.smssdk.framework.utils.d;

class c extends Thread
{
  c(b paramb)
  {
  }

  public void run()
  {
    try
    {
      int i = this.a.a();
      if (b.a(this.a) != null)
      {
        Message localMessage = new Message();
        localMessage.what = b.b(this.a);
        localMessage.arg1 = i;
        b.a(this.a).handleMessage(localMessage);
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      d.b(localThrowable);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.smssdk.a.c
 * JD-Core Version:    0.6.2
 */