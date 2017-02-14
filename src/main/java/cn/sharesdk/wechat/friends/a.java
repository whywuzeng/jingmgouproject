package cn.sharesdk.wechat.friends;

import android.os.Bundle;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.authorize.AuthorizeListener;

class a
  implements AuthorizeListener
{
  a(Wechat paramWechat)
  {
  }

  public void onCancel()
  {
    if (Wechat.c(this.a) != null)
      Wechat.d(this.a).onCancel(this.a, 1);
  }

  public void onComplete(Bundle paramBundle)
  {
    Wechat.a(this.a, 1, null);
  }

  public void onError(Throwable paramThrowable)
  {
    if (Wechat.a(this.a) != null)
      Wechat.b(this.a).onError(this.a, 1, paramThrowable);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.wechat.friends.a
 * JD-Core Version:    0.6.2
 */