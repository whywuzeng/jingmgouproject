package cn.sharesdk.sina.weibo;

import android.os.Bundle;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import com.mob.tools.utils.R;

class e
  implements AuthorizeListener
{
  e(SinaWeibo paramSinaWeibo, i parami)
  {
  }

  public void onCancel()
  {
    if (SinaWeibo.l(this.b) != null)
      SinaWeibo.m(this.b).onCancel(this.b, 1);
  }

  public void onComplete(Bundle paramBundle)
  {
    String str1 = paramBundle.getString("uid");
    String str2 = paramBundle.getString("access_token");
    String str3 = paramBundle.getString("expires_in");
    SinaWeibo.e(this.b).put("nickname", paramBundle.getString("userName"));
    SinaWeibo.f(this.b).put("remind_in", paramBundle.getString("remind_in"));
    SinaWeibo.g(this.b).putToken(str2);
    try
    {
      l = R.parseLong(str3);
      SinaWeibo.h(this.b).putExpiresIn(l);
      SinaWeibo.i(this.b).putUserId(str1);
      this.a.b(str2);
      SinaWeibo.b(this.b, 1, null);
      return;
    }
    catch (Throwable paramBundle)
    {
      while (true)
        long l = 0L;
    }
  }

  public void onError(Throwable paramThrowable)
  {
    if (SinaWeibo.j(this.b) != null)
      SinaWeibo.k(this.b).onError(this.b, 1, paramThrowable);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.sina.weibo.e
 * JD-Core Version:    0.6.2
 */