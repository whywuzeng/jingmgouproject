package com.ismartgo.app.share;

import android.app.Activity;
import android.content.Context;
import cn.sharesdk.onekeyshare.OnekeyShare;
import com.ismartgo.app.utils.UMengStatisticsUtils;
import com.umeng.analytics.MobclickAgent;

public class ShareUtils
{
  private String[] arrayImage;
  private Context context;
  private String imageUrl;
  private String inviteCode;
  private OnekeyShare oks;
  private String platform;
  private String targetUrl;
  private String text;
  private String title = "";

  public ShareUtils(Context paramContext)
  {
    this.context = paramContext;
  }

  public String[] getArrayImage()
  {
    return this.arrayImage;
  }

  public String getImageUrl()
  {
    return this.imageUrl;
  }

  public String getInviteCode()
  {
    return this.inviteCode;
  }

  public String getPlatform()
  {
    return this.platform;
  }

  public String getTargetUrl()
  {
    return this.targetUrl;
  }

  public String getText()
  {
    if (this.text == null)
      return "";
    return this.text;
  }

  public String getTitle()
  {
    return this.title;
  }

  public void initShare(String paramString1, String paramString2, String paramString3, String paramString4, String[] paramArrayOfString)
  {
    this.oks = new OnekeyShare();
    setTitle(paramString1.replaceAll("\r", "").replaceAll("\n", ""));
    setText(paramString2);
    setImageUrl(paramString3);
    setTargetUrl(paramString4);
    setArrayImage(paramArrayOfString);
  }

  public void setArrayImage(String[] paramArrayOfString)
  {
    this.arrayImage = paramArrayOfString;
  }

  public void setImageUrl(String paramString)
  {
    this.imageUrl = paramString;
  }

  public void setInviteCode(String paramString)
  {
    this.inviteCode = paramString;
  }

  public void setPlatform(String paramString)
  {
    this.platform = paramString;
  }

  public void setTargetUrl(String paramString)
  {
    this.targetUrl = paramString;
  }

  public void setText(String paramString)
  {
    this.text = paramString;
  }

  public void setTitle(String paramString)
  {
    String str = paramString;
    if (paramString == null)
      str = "";
    this.title = str;
  }

  public void startShare()
  {
    this.oks = new OnekeyShare();
    if (this.platform != null)
      this.oks.setPlatform(this.platform);
    this.oks.setPlatform(getPlatform());
    this.oks.setTitle(getTitle().toString() + ", " + getText().toString());
    this.oks.setTitleUrl(getTargetUrl());
    this.oks.setText("我在“精明购”发现了一个很划算的促销，值得去看看。");
    this.oks.setImageUrl(getImageUrl());
    this.oks.setUrl(getTargetUrl());
    this.oks.setComment("暂无评论");
    this.oks.setSite("精明购");
    this.oks.setVenueName("精明购");
    this.oks.setSiteUrl(getTargetUrl());
    this.oks.show(this.context);
    if ((this.context instanceof Activity))
    {
      if (((Activity)this.context).getClass().getSimpleName().equals("HomeActivity"))
      {
        UMengStatisticsUtils.homeShare(this.context);
        return;
      }
      if (((Activity)this.context).getClass().getSimpleName().equals("PromotionActivity"))
      {
        UMengStatisticsUtils.promotionShare(this.context);
        return;
      }
      try
      {
        MobclickAgent.onEvent(this.context, "sharemsg");
        return;
      }
      catch (Exception localException1)
      {
        localException1.printStackTrace();
        return;
      }
    }
    try
    {
      MobclickAgent.onEvent(this.context, "sharemsg");
      return;
    }
    catch (Exception localException2)
    {
      localException2.printStackTrace();
    }
  }

  public void startShareToInvite()
  {
    this.oks = new OnekeyShare();
    if (this.platform != null)
      this.oks.setPlatform(this.platform);
    this.oks.setPlatform(getPlatform());
    this.oks.setTitle(getTitle().toString());
    this.oks.setTitleUrl(getTargetUrl());
    this.oks.setText(getText());
    this.oks.setImageUrl(getImageUrl());
    this.oks.setUrl(getTargetUrl());
    this.oks.setComment("暂无评论");
    this.oks.setSite(getTitle());
    this.oks.setVenueName("精明购");
    this.oks.setSiteUrl(getTargetUrl());
    this.oks.show(this.context);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.share.ShareUtils
 * JD-Core Version:    0.6.2
 */