package cn.sharesdk.wechat.moments;

import android.os.Bundle;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.authorize.AuthorizeListener;

class a
  implements AuthorizeListener
{
  a(WechatMoments paramWechatMoments)
  {
  }

  public void onCancel()
  {
    if (WechatMoments.c(this.a) != null)
      WechatMoments.d(this.a).onCancel(this.a, 1);
  }

  public void onComplete(Bundle paramBundle)
  {
    WechatMoments.a(this.a, 1, null);
  }

  public void onError(Throwable paramThrowable)
  {
    if (WechatMoments.a(this.a) != null)
      WechatMoments.b(this.a).onError(this.a, 1, paramThrowable);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.wechat.moments.a
 * JD-Core Version:    0.6.2
 */