package cn.sharesdk.sina.weibo;

import android.os.Bundle;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import java.util.HashMap;

class c
  implements AuthorizeListener
{
  c(a parama)
  {
  }

  public void onCancel()
  {
    if (a.a(this.a) != null)
      a.a(this.a).onCancel(a.b(this.a), 9);
    this.a.finish();
  }

  public void onComplete(Bundle paramBundle)
  {
    if (a.a(this.a) != null)
    {
      paramBundle = new HashMap();
      paramBundle.put("ShareParams", a.c(this.a));
      a.a(this.a).onComplete(a.b(this.a), 9, paramBundle);
    }
    this.a.finish();
  }

  public void onError(Throwable paramThrowable)
  {
    if (a.a(this.a) != null)
      a.a(this.a).onError(a.b(this.a), 9, paramThrowable);
    this.a.finish();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.sina.weibo.c
 * JD-Core Version:    0.6.2
 */