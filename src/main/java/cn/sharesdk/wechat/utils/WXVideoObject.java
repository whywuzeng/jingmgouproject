package cn.sharesdk.wechat.utils;

import android.os.Bundle;
import com.mob.tools.utils.Ln;

public class WXVideoObject
  implements WXMediaMessage.IMediaObject
{
  public String videoLowBandUrl;
  public String videoUrl;

  public boolean checkArgs()
  {
    if (((this.videoUrl == null) || (this.videoUrl.length() == 0)) && ((this.videoLowBandUrl == null) || (this.videoLowBandUrl.length() == 0)))
    {
      Ln.e("both arguments are null", new Object[0]);
      return false;
    }
    if ((this.videoUrl != null) && (this.videoUrl.length() > 10240))
    {
      Ln.e("checkArgs fail, videoUrl is too long", new Object[0]);
      return false;
    }
    if ((this.videoLowBandUrl != null) && (this.videoLowBandUrl.length() > 10240))
    {
      Ln.e("checkArgs fail, videoLowBandUrl is too long", new Object[0]);
      return false;
    }
    return true;
  }

  public void serialize(Bundle paramBundle)
  {
    paramBundle.putString("_wxvideoobject_videoUrl", this.videoUrl);
    paramBundle.putString("_wxvideoobject_videoLowBandUrl", this.videoLowBandUrl);
  }

  public int type()
  {
    return 4;
  }

  public void unserialize(Bundle paramBundle)
  {
    this.videoUrl = paramBundle.getString("_wxvideoobject_videoUrl");
    this.videoLowBandUrl = paramBundle.getString("_wxvideoobject_videoLowBandUrl");
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.wechat.utils.WXVideoObject
 * JD-Core Version:    0.6.2
 */