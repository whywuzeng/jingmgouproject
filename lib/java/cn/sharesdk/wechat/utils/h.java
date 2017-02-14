package cn.sharesdk.wechat.utils;

import android.text.TextUtils;
import cn.sharesdk.framework.a.a;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import com.mob.tools.network.KVPair;
import com.mob.tools.utils.Ln;
import java.util.ArrayList;

class h extends Thread
{
  h(g paramg, String paramString, AuthorizeListener paramAuthorizeListener)
  {
  }

  public void run()
  {
    try
    {
      Object localObject = new ArrayList();
      ((ArrayList)localObject).add(new KVPair("appid", g.d(this.c)));
      ((ArrayList)localObject).add(new KVPair("secret", g.e(this.c)));
      ((ArrayList)localObject).add(new KVPair("code", this.a));
      ((ArrayList)localObject).add(new KVPair("grant_type", "authorization_code"));
      try
      {
        localObject = g.c(this.c).a("https://api.weixin.qq.com/sns/oauth2/access_token", (ArrayList)localObject, "/sns/oauth2/access_token", g.b(this.c));
        if (TextUtils.isEmpty((CharSequence)localObject))
        {
          this.b.onError(new Throwable("Authorize token is empty"));
          return;
        }
      }
      catch (Throwable localThrowable1)
      {
        this.b.onError(localThrowable1);
        return;
      }
    }
    catch (Throwable localThrowable2)
    {
      Ln.e(localThrowable2);
      return;
    }
    if (localThrowable2.contains("errcode"))
    {
      if (this.b != null)
        this.b.onError(new Throwable(localThrowable2));
    }
    else
    {
      g.a(this.c, localThrowable2);
      this.b.onComplete(null);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.wechat.utils.h
 * JD-Core Version:    0.6.2
 */