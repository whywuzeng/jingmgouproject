package cn.sharesdk.wechat.utils;

import android.os.Bundle;

public class b extends WechatResp
{
  public String a;
  public String b;
  public int c;
  public String d;
  public String e;

  public b(Bundle paramBundle)
  {
    super(paramBundle);
  }

  public int a()
  {
    return 1;
  }

  public void a(Bundle paramBundle)
  {
    super.a(paramBundle);
    this.a = paramBundle.getString("_wxapi_sendauth_resp_userName");
    this.b = paramBundle.getString("_wxapi_sendauth_resp_token");
    this.c = paramBundle.getInt("_wxapi_sendauth_resp_expireDate", 0);
    this.d = paramBundle.getString("_wxapi_sendauth_resp_state");
    this.e = paramBundle.getString("_wxapi_sendauth_resp_url");
  }

  public void b(Bundle paramBundle)
  {
    super.b(paramBundle);
    paramBundle.putString("_wxapi_sendauth_resp_userName", this.a);
    paramBundle.putString("_wxapi_sendauth_resp_token", this.b);
    paramBundle.putInt("_wxapi_sendauth_resp_expireDate", this.c);
    paramBundle.putString("_wxapi_sendauth_resp_state", this.d);
    paramBundle.putString("_wxapi_sendauth_resp_url", this.e);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.wechat.utils.b
 * JD-Core Version:    0.6.2
 */