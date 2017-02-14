package cn.jpush.android.data;

import android.content.Context;
import android.text.TextUtils;
import cn.jpush.android.service.ServiceInterface;
import cn.jpush.android.util.a;
import cn.jpush.android.util.n;
import cn.jpush.android.util.x;

final class q extends Thread
{
  q(String paramString1, Context paramContext, String paramString2)
  {
  }

  public final void run()
  {
    int k = 0;
    Object localObject = null;
    int i = 0;
    String str;
    do
    {
      j = k;
      if (i >= 4)
        break;
      i += 1;
      str = n.a(this.a, 5, 8000L);
      localObject = str;
    }
    while (n.a(str));
    int j = 1;
    localObject = str;
    if ((j != 0) && (!TextUtils.isEmpty(localObject)))
    {
      p.a(this.b, localObject);
      return;
    }
    ServiceInterface.a(this.c, 1021, a.b(this.b, this.a), this.b);
    ServiceInterface.a(this.c, 996, this.b);
    x.c();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.data.q
 * JD-Core Version:    0.6.2
 */