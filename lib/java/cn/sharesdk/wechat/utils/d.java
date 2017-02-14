package cn.sharesdk.wechat.utils;

import android.os.Bundle;
import com.mob.tools.utils.Ln;

public class d extends m
{
  public WXMediaMessage a;
  public int b;

  public int a()
  {
    return 2;
  }

  public void a(Bundle paramBundle)
  {
    super.a(paramBundle);
    paramBundle.putAll(WXMediaMessage.a.a(this.a));
    paramBundle.putInt("_wxapi_sendmessagetowx_req_scene", this.b);
  }

  public boolean b()
  {
    if ((this.a.getType() == 8) && ((this.a.thumbData == null) || (this.a.thumbData.length <= 0)))
    {
      Ln.e("checkArgs fail, thumbData should not be null when send emoji", new Object[0]);
      return false;
    }
    if ((this.a.thumbData != null) && (this.a.thumbData.length > 32768))
    {
      Ln.e("checkArgs fail, thumbData is invalid", new Object[0]);
      return false;
    }
    if ((this.a.title != null) && (this.a.title.length() > 512))
    {
      Ln.e("checkArgs fail, title is invalid", new Object[0]);
      return false;
    }
    if ((this.a.description != null) && (this.a.description.length() > 1024))
      this.a.description = (this.a.description.substring(0, 1021) + "...");
    if (this.a.mediaObject == null)
    {
      Ln.e("checkArgs fail, mediaObject is null", new Object[0]);
      return false;
    }
    return this.a.mediaObject.checkArgs();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.wechat.utils.d
 * JD-Core Version:    0.6.2
 */