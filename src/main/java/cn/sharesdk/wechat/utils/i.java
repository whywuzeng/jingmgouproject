package cn.sharesdk.wechat.utils;

import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.a.a;
import com.mob.tools.network.KVPair;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.Ln;
import com.mob.tools.utils.R;
import java.util.ArrayList;
import java.util.HashMap;

class i extends Thread
{
  i(g paramg, PlatformActionListener paramPlatformActionListener)
  {
  }

  public void run()
  {
    try
    {
      Object localObject = new ArrayList();
      ((ArrayList)localObject).add(new KVPair("access_token", g.a(this.b).getDb().getToken()));
      ((ArrayList)localObject).add(new KVPair("openid", g.a(this.b).getDb().get("openid")));
      localObject = g.c(this.b).a("https://api.weixin.qq.com/sns/userinfo", (ArrayList)localObject, "/sns/userinfo", g.b(this.b));
      if (TextUtils.isEmpty((CharSequence)localObject))
      {
        if (this.a == null)
          return;
        this.a.onError(g.a(this.b), 8, new Throwable());
        return;
      }
      Ln.d("getUserInfo ==>>" + (String)localObject, new Object[0]);
      localObject = new Hashon().fromJson((String)localObject);
      if ((((HashMap)localObject).containsKey("errcode")) && (((Integer)((HashMap)localObject).get("errcode")).intValue() != 0))
      {
        if (this.a == null)
          return;
        localObject = new Hashon().fromHashMap((HashMap)localObject);
        this.a.onError(g.a(this.b), 8, new Throwable((String)localObject));
        return;
      }
    }
    catch (Throwable localThrowable1)
    {
      Ln.e(localThrowable1);
      return;
    }
    String str1 = String.valueOf(localThrowable1.get("openid"));
    String str2 = String.valueOf(localThrowable1.get("nickname"));
    try
    {
      i = R.parseInt(String.valueOf(localThrowable1.get("sex")));
      String str3 = String.valueOf(localThrowable1.get("province"));
      String str4 = String.valueOf(localThrowable1.get("city"));
      String str5 = String.valueOf(localThrowable1.get("country"));
      String str6 = String.valueOf(localThrowable1.get("headimgurl"));
      String str7 = String.valueOf(localThrowable1.get("unionid"));
      g.a(this.b).getDb().put("nickname", str2);
      if (i == 1)
      {
        g.a(this.b).getDb().put("gender", "0");
        g.a(this.b).getDb().putUserId(str1);
        g.a(this.b).getDb().put("icon", str6);
        g.a(this.b).getDb().put("province", str3);
        g.a(this.b).getDb().put("city", str4);
        g.a(this.b).getDb().put("country", str5);
        g.a(this.b).getDb().put("openid", str1);
        g.a(this.b).getDb().put("unionid", str7);
        this.a.onComplete(g.a(this.b), 8, localThrowable1);
        return;
      }
    }
    catch (Throwable localThrowable2)
    {
      while (true)
      {
        Ln.e(localThrowable2);
        int i = 2;
        continue;
        if (i == 2)
          g.a(this.b).getDb().put("gender", "1");
        else
          g.a(this.b).getDb().put("gender", "2");
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.wechat.utils.i
 * JD-Core Version:    0.6.2
 */