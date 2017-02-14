package cn.sharesdk.wechat.utils;

import android.os.Bundle;
import com.mob.tools.utils.Ln;

public class a extends m
{
  public String a;
  public String b;

  public int a()
  {
    return 1;
  }

  public void a(Bundle paramBundle)
  {
    super.a(paramBundle);
    paramBundle.putString("_wxapi_sendauth_req_scope", this.a);
    paramBundle.putString("_wxapi_sendauth_req_state", this.b);
  }

  public boolean b()
  {
    if ((this.a == null) || (this.a.length() == 0) || (this.a.length() > 1024))
    {
      Ln.e("MicroMsg.SDK.SendAuth.Req", new Object[] { "checkArgs fail, scope is invalid" });
      return false;
    }
    if ((this.b != null) && (this.b.length() > 1024))
    {
      Ln.e("MicroMsg.SDK.SendAuth.Req", new Object[] { "checkArgs fail, state is invalid" });
      return false;
    }
    return true;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.wechat.utils.a
 * JD-Core Version:    0.6.2
 */