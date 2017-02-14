package cn.sharesdk.wechat.utils;

import android.os.Bundle;

public class f extends WechatResp
{
  public WXMediaMessage a;

  public f(Bundle paramBundle)
  {
    super(paramBundle);
  }

  public int a()
  {
    return 4;
  }

  public void a(Bundle paramBundle)
  {
    super.a(paramBundle);
    this.a = WXMediaMessage.a.a(paramBundle);
  }

  public void b(Bundle paramBundle)
  {
    super.b(paramBundle);
    paramBundle.putAll(WXMediaMessage.a.a(this.a));
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.wechat.utils.f
 * JD-Core Version:    0.6.2
 */