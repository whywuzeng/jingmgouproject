package cn.sharesdk.wechat.utils;

import android.os.Bundle;
import com.mob.tools.utils.Ln;

public class WXTextObject
  implements WXMediaMessage.IMediaObject
{
  public String text;

  public WXTextObject()
  {
    this(null);
  }

  public WXTextObject(String paramString)
  {
    this.text = paramString;
  }

  public boolean checkArgs()
  {
    if ((this.text == null) || (this.text.length() == 0) || (this.text.length() > 10240))
    {
      Ln.e("checkArgs fail, text is invalid", new Object[0]);
      return false;
    }
    return true;
  }

  public void serialize(Bundle paramBundle)
  {
    paramBundle.putString("_wxtextobject_text", this.text);
  }

  public int type()
  {
    return 1;
  }

  public void unserialize(Bundle paramBundle)
  {
    this.text = paramBundle.getString("_wxtextobject_text");
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.wechat.utils.WXTextObject
 * JD-Core Version:    0.6.2
 */