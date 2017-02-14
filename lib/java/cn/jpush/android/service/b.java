package cn.jpush.android.service;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import cn.jpush.android.util.x;

final class b extends Handler
{
  private c b = null;

  public b(a parama, Looper paramLooper, c paramc)
  {
    super(paramLooper);
    this.b = paramc;
  }

  public final void handleMessage(Message paramMessage)
  {
    super.handleMessage(paramMessage);
    if (this.a.a)
    {
      x.b();
      return;
    }
    if (this.b != null)
      this.b.a(a.a(this.a), a.b(this.a));
    a.c(this.a).sendEmptyMessageDelayed(0, 2000L);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.service.b
 * JD-Core Version:    0.6.2
 */