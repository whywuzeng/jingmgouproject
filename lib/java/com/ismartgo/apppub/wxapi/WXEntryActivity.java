package com.ismartgo.apppub.wxapi;

import android.content.pm.PackageManager;
import android.widget.Toast;
import cn.sharesdk.wechat.utils.WXAppExtendObject;
import cn.sharesdk.wechat.utils.WXMediaMessage;
import cn.sharesdk.wechat.utils.WechatHandlerActivity;
import com.umeng.analytics.MobclickAgent;

public class WXEntryActivity extends WechatHandlerActivity
{
  public void onGetMessageFromWXReq(WXMediaMessage paramWXMediaMessage)
  {
    startActivity(getPackageManager().getLaunchIntentForPackage(getPackageName()));
  }

  protected void onPause()
  {
    super.onPause();
    MobclickAgent.onPause(this);
  }

  protected void onResume()
  {
    super.onResume();
    MobclickAgent.onResume(this);
  }

  public void onShowMessageFromWXReq(WXMediaMessage paramWXMediaMessage)
  {
    if ((paramWXMediaMessage != null) && (paramWXMediaMessage.mediaObject != null) && ((paramWXMediaMessage.mediaObject instanceof WXAppExtendObject)))
      Toast.makeText(this, ((WXAppExtendObject)paramWXMediaMessage.mediaObject).extInfo, 0).show();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.apppub.wxapi.WXEntryActivity
 * JD-Core Version:    0.6.2
 */