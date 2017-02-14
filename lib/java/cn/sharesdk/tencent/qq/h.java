package cn.sharesdk.tencent.qq;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import com.mob.tools.FakeActivity;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.R;
import java.util.HashMap;

public class h extends FakeActivity
{
  private String a;
  private Platform b;
  private PlatformActionListener c;

  public void a(Platform paramPlatform, PlatformActionListener paramPlatformActionListener)
  {
    this.b = paramPlatform;
    this.c = paramPlatformActionListener;
  }

  public void a(String paramString)
  {
    this.a = paramString;
  }

  public void onCreate()
  {
    Object localObject = this.activity.getIntent();
    String str1 = ((Intent)localObject).getScheme();
    finish();
    if ((str1 != null) && (str1.startsWith(this.a)))
    {
      localObject = R.urlToBundle(((Intent)localObject).getDataString());
      str1 = String.valueOf(((Bundle)localObject).get("result"));
      String str2 = String.valueOf(((Bundle)localObject).get("action"));
      if (("shareToQQ".equals(str2)) || ("shareToQzone".equals(str2)))
      {
        if (!"complete".equals(str1))
          break label174;
        if (this.c != null)
        {
          localObject = String.valueOf(((Bundle)localObject).get("response"));
          localObject = new Hashon().fromJson((String)localObject);
          this.c.onComplete(this.b, 9, (HashMap)localObject);
        }
      }
    }
    while (true)
    {
      localObject = new Intent("android.intent.action.VIEW");
      ((Intent)localObject).setClass(this.activity, this.activity.getClass());
      ((Intent)localObject).setFlags(335544320);
      this.activity.startActivity((Intent)localObject);
      return;
      label174: if ("error".equals(str1))
      {
        if (this.c != null)
        {
          localObject = new Throwable(String.valueOf(((Bundle)localObject).get("response")));
          this.c.onError(this.b, 9, (Throwable)localObject);
        }
      }
      else if (this.c != null)
        this.c.onCancel(this.b, 9);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.tencent.qq.h
 * JD-Core Version:    0.6.2
 */