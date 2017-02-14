package cn.sharesdk.sina.weibo;

import android.os.Bundle;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.authorize.SSOListener;
import com.mob.tools.utils.Ln;
import com.mob.tools.utils.R;

class j
  implements SSOListener
{
  j(i parami, AuthorizeListener paramAuthorizeListener)
  {
  }

  public void onCancel()
  {
    this.a.onCancel();
  }

  public void onComplete(Bundle paramBundle)
  {
    try
    {
      R.parseLong(paramBundle.getString("expires_in"));
      this.a.onComplete(paramBundle);
      return;
    }
    catch (Throwable paramBundle)
    {
      onFailed(paramBundle);
    }
  }

  public void onFailed(Throwable paramThrowable)
  {
    Ln.e(paramThrowable);
    i.a(this.b, this.a);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.sina.weibo.j
 * JD-Core Version:    0.6.2
 */