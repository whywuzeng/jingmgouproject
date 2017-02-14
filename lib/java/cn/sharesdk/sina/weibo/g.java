package cn.sharesdk.sina.weibo;

import android.os.Bundle;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.Ln;
import java.util.HashMap;

class g extends Thread
{
  g(f paramf, Platform paramPlatform, String paramString)
  {
  }

  public void run()
  {
    try
    {
      Object localObject1 = i.a(this.a);
      try
      {
        localObject1 = ((i)localObject1).a(this.a.getContext(), this.b);
        if (localObject1 == null)
        {
          f.b(this.c).onError(new Throwable("Authorize token is empty"));
          return;
        }
      }
      catch (Throwable localThrowable1)
      {
        while (true)
        {
          f.a(this.c).onError(localThrowable1);
          localObject2 = null;
        }
        Object localObject2 = new Hashon().fromJson((String)localObject2);
        Bundle localBundle = new Bundle();
        localBundle.putString("access_token", String.valueOf(((HashMap)localObject2).get("access_token")));
        localBundle.putString("remind_in", String.valueOf(((HashMap)localObject2).get("remind_in")));
        localBundle.putString("expires_in", String.valueOf(((HashMap)localObject2).get("expires_in")));
        localBundle.putString("uid", String.valueOf(((HashMap)localObject2).get("uid")));
        f.c(this.c).onComplete(localBundle);
        return;
      }
    }
    catch (Throwable localThrowable2)
    {
      Ln.e(localThrowable2);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.sina.weibo.g
 * JD-Core Version:    0.6.2
 */