package cn.sharesdk.sina.weibo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import com.mob.tools.FakeActivity;

public class a extends FakeActivity
{
  private String a;
  private String b;
  private int c;
  private boolean d;
  private String[] e;
  private PlatformActionListener f;
  private Platform g;
  private Platform.ShareParams h;

  public a(Platform paramPlatform, String paramString1, String paramString2)
  {
    this.a = paramString1;
    this.g = paramPlatform;
    this.b = paramString2;
  }

  private void a()
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (this.e != null)
    {
      localObject1 = localObject2;
      if (this.e.length != 0)
      {
        localObject2 = this.e[0];
        int i = 1;
        while (true)
        {
          localObject1 = localObject2;
          if (i >= this.e.length)
            break;
          localObject2 = (String)localObject2 + "," + this.e[i];
          i += 1;
        }
      }
    }
    if (this.d)
      ShareSDK.logApiEvent("com.sina.weibo.sdk.auth.sso.SsoHandler.authorize", this.c);
    while (true)
    {
      Intent localIntent = new Intent(this.activity, SinaActivity.class);
      localIntent.putExtra("action", 1);
      localIntent.putExtra("appkey", this.a);
      localIntent.putExtra("isUserClient", this.d);
      localIntent.putExtra("redirectUrl", this.b);
      localObject2 = localObject1;
      if (localObject1 == null)
        localObject2 = "";
      localIntent.putExtra("permissions", (String)localObject2);
      SinaActivity.a(new b(this));
      this.activity.startActivity(localIntent);
      return;
      ShareSDK.logApiEvent("com.sina.weibo.sdk.auth.sso.SsoHandler.authorizeWeb", this.c);
    }
  }

  private void b()
  {
    ShareSDK.logApiEvent("com.sina.weibo.sdk.api.share.IWeiboShareAPI.sendRequest", this.c);
    Intent localIntent = new Intent(this.activity, SinaActivity.class);
    localIntent.putExtra("action", 2);
    localIntent.putExtra("token", this.g.getDb().getToken());
    localIntent.putExtra("appkey", this.a);
    localIntent.putExtra("redirectUrl", this.b);
    localIntent.putExtra("isUserClient", this.d);
    c localc = new c(this);
    SinaActivity.a(this.h);
    SinaActivity.a(localc);
    this.activity.startActivity(localIntent);
  }

  public void a(PlatformActionListener paramPlatformActionListener, Platform.ShareParams paramShareParams, boolean paramBoolean)
  {
    this.d = true;
    this.h = paramShareParams;
    this.f = paramPlatformActionListener;
  }

  public void a(PlatformActionListener paramPlatformActionListener, String[] paramArrayOfString, boolean paramBoolean)
  {
    if (!paramBoolean);
    for (paramBoolean = true; ; paramBoolean = false)
    {
      this.d = paramBoolean;
      this.e = paramArrayOfString;
      this.f = paramPlatformActionListener;
      return;
    }
  }

  public void onCreate()
  {
    super.onCreate();
    Bundle localBundle = this.activity.getIntent().getExtras();
    int i = localBundle.getInt("action");
    this.c = localBundle.getInt("platformID");
    if (1 == i)
    {
      a();
      return;
    }
    b();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.sina.weibo.a
 * JD-Core Version:    0.6.2
 */