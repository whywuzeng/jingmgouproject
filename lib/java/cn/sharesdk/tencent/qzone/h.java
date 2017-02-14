package cn.sharesdk.tencent.qzone;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import cn.sharesdk.framework.authorize.SSOListener;
import cn.sharesdk.framework.authorize.e;
import cn.sharesdk.framework.authorize.f;
import org.json.JSONObject;

public class h extends f
{
  private String d;
  private String e;

  public h(e parame)
  {
    super(parame);
  }

  public void a()
  {
    String str1 = "com.tencent.mobileqq";
    do
    {
      Object localObject;
      do
      {
        try
        {
          localObject = this.a.getContext().getPackageManager().getPackageInfo("com.tencent.mobileqq", 0);
          if (localObject == null)
          {
            this.a.finish();
            if (this.c != null)
              this.c.onFailed(new TencentSSOClientNotInstalledException());
            return;
          }
        }
        catch (Throwable localThrowable1)
        {
          do
            while (true)
            {
              String str2 = "com.qzone";
              try
              {
                localObject = this.a.getContext().getPackageManager().getPackageInfo("com.qzone", 0);
              }
              catch (Throwable localThrowable2)
              {
                this.a.finish();
              }
            }
          while (this.c == null);
          this.c.onFailed(new TencentSSOClientNotInstalledException());
          return;
        }
        localObject = new Intent();
        ((Intent)localObject).setClassName(localThrowable2, "com.tencent.open.agent.AgentActivity");
        if (this.a.getContext().getPackageManager().resolveActivity((Intent)localObject, 0) != null)
          break;
        this.a.finish();
      }
      while (this.c == null);
      this.c.onFailed(new TencentSSOClientNotInstalledException());
      return;
      Bundle localBundle = new Bundle();
      localBundle.putString("scope", this.e);
      localBundle.putString("client_id", this.d);
      localBundle.putString("pf", "openmobile_android");
      localBundle.putString("need_pay", "1");
      ((Intent)localObject).putExtra("key_params", localBundle);
      ((Intent)localObject).putExtra("key_request_code", this.b);
      ((Intent)localObject).putExtra("key_action", "action_login");
      try
      {
        this.a.startActivityForResult((Intent)localObject, this.b);
        return;
      }
      catch (Throwable localThrowable3)
      {
        this.a.finish();
      }
    }
    while (this.c == null);
    this.c.onFailed(localThrowable3);
  }

  public void a(int paramInt1, int paramInt2, Intent paramIntent)
  {
    this.a.finish();
    if (paramInt2 == 0)
      if (this.c != null)
        this.c.onCancel();
    do
      while (true)
      {
        return;
        if (paramIntent == null)
        {
          if (this.c != null)
            this.c.onFailed(new Throwable("response is empty"));
        }
        else
        {
          paramIntent = paramIntent.getExtras();
          if (paramIntent == null)
          {
            if (this.c != null)
              this.c.onFailed(new Throwable("response is empty"));
          }
          else if (!paramIntent.containsKey("key_response"))
          {
            if (this.c != null)
              this.c.onFailed(new Throwable("response is empty"));
          }
          else
          {
            paramIntent = paramIntent.getString("key_response");
            if ((paramIntent == null) || (paramIntent.length() <= 0))
            {
              if (this.c != null)
                this.c.onFailed(new Throwable("response is empty"));
            }
            else
              try
              {
                paramIntent = new JSONObject(paramIntent);
                Bundle localBundle = new Bundle();
                localBundle.putInt("ret", paramIntent.optInt("ret"));
                localBundle.putString("pay_token", paramIntent.optString("pay_token"));
                localBundle.putString("pf", paramIntent.optString("pf"));
                localBundle.putString("open_id", paramIntent.optString("openid"));
                localBundle.putString("expires_in", paramIntent.optString("expires_in"));
                localBundle.putString("pfkey", paramIntent.optString("pfkey"));
                localBundle.putString("msg", paramIntent.optString("msg"));
                localBundle.putString("access_token", paramIntent.optString("access_token"));
                if (this.c != null)
                {
                  this.c.onComplete(localBundle);
                  return;
                }
              }
              catch (Throwable paramIntent)
              {
              }
          }
        }
      }
    while (this.c == null);
    this.c.onFailed(paramIntent);
  }

  public void a(String paramString1, String paramString2)
  {
    this.d = paramString1;
    this.e = paramString2;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.tencent.qzone.h
 * JD-Core Version:    0.6.2
 */