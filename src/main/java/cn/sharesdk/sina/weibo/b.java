package cn.sharesdk.sina.weibo;

import android.os.Bundle;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.authorize.AuthorizeListener;

class b
  implements AuthorizeListener
{
  b(a parama)
  {
  }

  public void onCancel()
  {
    if (a.a(this.a) != null)
      a.a(this.a).onCancel(a.b(this.a), 1);
    this.a.finish();
  }

  public void onComplete(Bundle paramBundle)
  {
    String str = paramBundle.getString("access_token");
    if (!TextUtils.isEmpty(str))
    {
      a.b(this.a).getDb().putToken(str);
      a.b(this.a).getDb().putUserId(paramBundle.getString("uid"));
      a.b(this.a).getDb().putExpiresIn(paramBundle.getLong("expires_in", 0L));
      a.b(this.a).getDb().put("refresh_token", paramBundle.getString("refresh_token"));
      a.b(this.a).getDb().put("nickname", paramBundle.getString("userName"));
      a.b(this.a).getDb().put("remind_in", paramBundle.getString("remind_in"));
      if (a.a(this.a) != null)
        a.a(this.a).onComplete(a.b(this.a), 1, null);
    }
    while (true)
    {
      this.a.finish();
      return;
      paramBundle = paramBundle.getString("code");
      if (a.a(this.a) != null)
        a.a(this.a).onError(a.b(this.a), 1, new Throwable("Error. Obtained the code: " + paramBundle));
    }
  }

  public void onError(Throwable paramThrowable)
  {
    if (a.a(this.a) != null)
      a.a(this.a).onError(a.b(this.a), 1, paramThrowable);
    this.a.finish();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.sina.weibo.b
 * JD-Core Version:    0.6.2
 */