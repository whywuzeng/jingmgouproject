package com.ismartgo.app.share;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import cn.sharesdk.framework.ShareSDK;
import com.ismartgo.app.url.Url;

public class ShareDialog extends Dialog
  implements View.OnClickListener
{
  public static String mImageUrl;
  public static String mTargetUrl;
  public static String mText;
  public static String mTitle;
  private String[] mArrayImage;
  private Context mContext;
  ShareUtils share;

  public ShareDialog(Context paramContext)
  {
    super(paramContext, 2131427356);
    this.mContext = paramContext;
    ShareSDK.initSDK(paramContext);
  }

  public void cancel(View paramView)
  {
    dismiss();
  }

  public String getText()
  {
    return mText;
  }

  public String getTitle()
  {
    return mTitle;
  }

  public void onClick(View paramView)
  {
    this.share = new ShareUtils(this.mContext);
    switch (paramView.getId())
    {
    default:
    case 2131231108:
    case 2131231109:
    case 2131231110:
    case 2131231111:
    case 2131231112:
    }
    while (true)
    {
      dismiss();
      return;
      this.share.setPlatform("Wechat");
      this.share.initShare(mTitle, mText, mImageUrl, mTargetUrl, this.mArrayImage);
      this.share.startShare();
      continue;
      this.share.setPlatform("WechatMoments");
      this.share.initShare(mTitle, mText, mImageUrl, mTargetUrl, this.mArrayImage);
      this.share.startShare();
      continue;
      this.share.setPlatform("SinaWeibo");
      this.share.initShare(mTitle, mText, mImageUrl, mTargetUrl, this.mArrayImage);
      this.share.startShare();
      continue;
      this.share.setPlatform("QQ");
      this.share.initShare(mTitle, mText, mImageUrl, mTargetUrl, this.mArrayImage);
      this.share.startShare();
      continue;
      this.share.setPlatform("QZone");
      this.share.initShare(mTitle, mText, mImageUrl, mTargetUrl, this.mArrayImage);
      this.share.startShare();
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903114);
    findViewById(2131231108).setOnClickListener(this);
    findViewById(2131231109).setOnClickListener(this);
    findViewById(2131231110).setOnClickListener(this);
    findViewById(2131231111).setOnClickListener(this);
    findViewById(2131231112).setOnClickListener(this);
    findViewById(2131230873).setOnClickListener(this);
    paramBundle = getWindow();
    paramBundle.setGravity(80);
    WindowManager.LayoutParams localLayoutParams = paramBundle.getAttributes();
    localLayoutParams.width = -1;
    paramBundle.setAttributes(localLayoutParams);
  }

  public void setShare(String paramString1, String paramString2, String paramString3, int paramInt1, String paramString4, int paramInt2)
  {
    mImageUrl = paramString1;
    mTitle = paramString2;
    mTargetUrl = Url.SHARE_GOODS_URL + "mid=" + paramInt1 + "&uid=" + paramString4 + "&type=" + paramInt2;
    mText = paramString3;
    Log.i("Share", "imageUrl: " + paramString1);
    Log.i("Share", "title: " + paramString2);
    Log.i("Share", "targetUrl: " + mTargetUrl);
    Log.i("Share", "text: " + paramString3);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.share.ShareDialog
 * JD-Core Version:    0.6.2
 */