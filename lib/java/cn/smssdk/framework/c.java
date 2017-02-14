package cn.smssdk.framework;

import android.os.Handler.Callback;
import android.os.Message;

class c
  implements Handler.Callback
{
  c(FakeActivity paramFakeActivity, Runnable paramRunnable)
  {
  }

  public boolean handleMessage(Message paramMessage)
  {
    this.a.run();
    return false;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.smssdk.framework.c
 * JD-Core Version:    0.6.2
 */