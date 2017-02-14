package cn.sharesdk.tencent.qq;

import android.os.Bundle;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.authorize.SSOListener;

class f
  implements SSOListener
{
  f(e parame, AuthorizeListener paramAuthorizeListener)
  {
  }

  public void onCancel()
  {
    this.a.onCancel();
  }

  public void onComplete(Bundle paramBundle)
  {
    this.a.onComplete(paramBundle);
  }

  public void onFailed(Throwable paramThrowable)
  {
    e.a(this.b, this.a);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.tencent.qq.f
 * JD-Core Version:    0.6.2
 */