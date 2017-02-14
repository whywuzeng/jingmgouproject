package cn.jpush.android.service;

import cn.jpush.android.util.x;

final class l
  implements Runnable
{
  l(PushService paramPushService)
  {
  }

  public final void run()
  {
    PushService.b(this.a, true);
    PushService.a(this.a, false, true, true);
    x.c();
    PushService.c(this.a);
    PushService.b(this.a, false);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.service.l
 * JD-Core Version:    0.6.2
 */