package cn.sharesdk.wechat.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.mob.tools.utils.Ln;

public class WechatHandlerActivity extends Activity
{
  protected void onCreate(Bundle paramBundle)
  {
    setTheme(16973839);
    super.onCreate(paramBundle);
    try
    {
      WechatHelper.a().a(this);
      finish();
      return;
    }
    catch (Throwable paramBundle)
    {
      while (true)
        Ln.w(paramBundle);
    }
  }

  public void onGetMessageFromWXReq(WXMediaMessage paramWXMediaMessage)
  {
  }

  protected void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
    setIntent(paramIntent);
    try
    {
      WechatHelper.a().a(this);
      finish();
      return;
    }
    catch (Throwable paramIntent)
    {
      while (true)
        Ln.w(paramIntent);
    }
  }

  public void onShowMessageFromWXReq(WXMediaMessage paramWXMediaMessage)
  {
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.wechat.utils.WechatHandlerActivity
 * JD-Core Version:    0.6.2
 */