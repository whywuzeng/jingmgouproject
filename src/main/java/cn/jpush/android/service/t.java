package cn.jpush.android.service;

import android.os.PowerManager.WakeLock;

public final class t
{
  private static t a = null;
  private PowerManager.WakeLock b = null;

  public static t a()
  {
    if (a == null)
      a = new t();
    return a;
  }

  public final void a(PowerManager.WakeLock paramWakeLock)
  {
    this.b = paramWakeLock;
  }

  public final PowerManager.WakeLock b()
  {
    return this.b;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.service.t
 * JD-Core Version:    0.6.2
 */