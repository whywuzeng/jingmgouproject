package cn.sharesdk.tencent.qzone;

import android.os.Bundle;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import com.mob.tools.utils.Ln;
import com.mob.tools.utils.R;

class a
  implements AuthorizeListener
{
  a(QZone paramQZone, f paramf)
  {
  }

  public void onCancel()
  {
    if (QZone.g(this.b) != null)
      QZone.h(this.b).onCancel(this.b, 1);
  }

  public void onComplete(Bundle paramBundle)
  {
    String str1 = paramBundle.getString("open_id");
    String str2 = paramBundle.getString("access_token");
    paramBundle = paramBundle.getString("expires_in");
    QZone.c(this.b).putToken(str2);
    QZone.d(this.b).putTokenSecret("");
    try
    {
      QZone.e(this.b).putExpiresIn(R.parseLong(paramBundle));
      QZone.f(this.b).putUserId(str1);
      this.a.b(str1);
      this.a.c(str2);
      QZone.a(this.b, 1, null);
      return;
    }
    catch (Throwable paramBundle)
    {
      while (true)
        Ln.e(paramBundle);
    }
  }

  public void onError(Throwable paramThrowable)
  {
    if (QZone.a(this.b) != null)
      QZone.b(this.b).onError(this.b, 1, paramThrowable);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.tencent.qzone.a
 * JD-Core Version:    0.6.2
 */