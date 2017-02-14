package cn.sharesdk.tencent.qq;

import android.os.Bundle;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import com.mob.tools.utils.Ln;
import com.mob.tools.utils.R;

class a
  implements AuthorizeListener
{
  a(QQ paramQQ, e parame)
  {
  }

  public void onCancel()
  {
    if (QQ.g(this.b) != null)
      QQ.h(this.b).onCancel(this.b, 1);
  }

  public void onComplete(Bundle paramBundle)
  {
    String str1 = paramBundle.getString("open_id");
    String str2 = paramBundle.getString("access_token");
    paramBundle = paramBundle.getString("expires_in");
    QQ.c(this.b).putToken(str2);
    QQ.d(this.b).putTokenSecret("");
    try
    {
      QQ.e(this.b).putExpiresIn(R.parseLong(paramBundle));
      QQ.f(this.b).putUserId(str1);
      this.a.b(str1);
      this.a.d(str2);
      QQ.a(this.b, 1, null);
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
    if (QQ.a(this.b) != null)
      QQ.b(this.b).onError(this.b, 1, paramThrowable);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.tencent.qq.a
 * JD-Core Version:    0.6.2
 */