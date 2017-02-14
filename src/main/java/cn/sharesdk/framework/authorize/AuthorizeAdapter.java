package cn.sharesdk.framework.authorize;

import android.app.Activity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import cn.sharesdk.framework.TitleLayout;

public class AuthorizeAdapter
{
  private Activity activity;
  private boolean noTitle;
  private String platform;
  private boolean popUpAnimationDisable;
  private RelativeLayout rlBody;
  private TitleLayout title;
  private WebView webview;

  protected void disablePopUpAnimation()
  {
    this.popUpAnimationDisable = true;
  }

  public Activity getActivity()
  {
    return this.activity;
  }

  public RelativeLayout getBodyView()
  {
    return this.rlBody;
  }

  public String getPlatformName()
  {
    return this.platform;
  }

  public TitleLayout getTitleLayout()
  {
    return this.title;
  }

  public WebView getWebBody()
  {
    return this.webview;
  }

  public void hideShareSDKLogo()
  {
    int i = getTitleLayout().getChildCount();
    getTitleLayout().getChildAt(i - 1).setVisibility(8);
  }

  boolean isNotitle()
  {
    return this.noTitle;
  }

  boolean isPopUpAnimationDisable()
  {
    return this.popUpAnimationDisable;
  }

  public void onCreate()
  {
  }

  public void onDestroy()
  {
  }

  public boolean onFinish()
  {
    return false;
  }

  public boolean onKeyEvent(int paramInt, KeyEvent paramKeyEvent)
  {
    return false;
  }

  public void onPause()
  {
  }

  public void onResize(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
  }

  public void onRestart()
  {
  }

  public void onResume()
  {
  }

  public void onStart()
  {
  }

  public void onStop()
  {
  }

  void setActivity(Activity paramActivity)
  {
    this.activity = paramActivity;
  }

  void setBodyView(RelativeLayout paramRelativeLayout)
  {
    this.rlBody = paramRelativeLayout;
  }

  void setNotitle(boolean paramBoolean)
  {
    this.noTitle = paramBoolean;
  }

  void setPlatformName(String paramString)
  {
    this.platform = paramString;
  }

  void setTitleView(TitleLayout paramTitleLayout)
  {
    this.title = paramTitleLayout;
  }

  void setWebView(WebView paramWebView)
  {
    this.webview = paramWebView;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.framework.authorize.AuthorizeAdapter
 * JD-Core Version:    0.6.2
 */