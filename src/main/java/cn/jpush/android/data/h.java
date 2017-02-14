package cn.jpush.android.data;

import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

final class h extends Handler
{
  h(g paramg)
  {
  }

  public final void handleMessage(Message paramMessage)
  {
    if (paramMessage.what == 0)
      if ((g.a(this.a) != null) && (g.b(this.a) != null))
        g.a(this.a).setImageBitmap(g.b(this.a));
    while ((paramMessage.what != 1) || (g.c(this.a) == null) || (g.d(this.a) == null))
      return;
    g.c(this.a).setImageBitmap(g.d(this.a));
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.data.h
 * JD-Core Version:    0.6.2
 */