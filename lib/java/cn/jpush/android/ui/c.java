package cn.jpush.android.ui;

import android.os.Handler;
import android.os.Message;
import java.lang.ref.WeakReference;

final class c extends Handler
{
  private final WeakReference<a> a;

  c(a parama)
  {
    this.a = new WeakReference(parama);
  }

  public final void handleMessage(Message paramMessage)
  {
    a locala = (a)this.a.get();
    if (locala != null);
    switch (paramMessage.what)
    {
    default:
      return;
    case 0:
      a.b(locala);
      return;
    case 4:
    }
    a.c(locala);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.ui.c
 * JD-Core Version:    0.6.2
 */