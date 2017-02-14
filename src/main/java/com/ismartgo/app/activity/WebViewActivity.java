package com.ismartgo.app.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.ab.view.ioc.AbIocView;
import com.ismartgo.app.bean.Gift;
import com.ismartgo.app.bean.User;
import com.ismartgo.app.grid.impl.WebDownloadListener;
import com.ismartgo.app.grid.impl.WebDownloadListener.DownLoadCompleteReceiver;
import com.ismartgo.app.ownself.view.ToastDefine;
import com.ismartgo.app.tools.ProgressWebView;
import com.umeng.analytics.MobclickAgent;

@SuppressLint({"SetJavaScriptEnabled"})
public class WebViewActivity extends BaseActivity
  implements View.OnClickListener
{
  private ImageView btn_closs;

  @AbIocView(click="confirm", id=2131230782)
  private Button btn_confirm;
  private WebDownloadListener downloadListener;
  private Gift gift;
  private boolean isAdvert = false;

  @AbIocView(id=2131231081)
  private View layout_bottom;
  private int requestCode = 10001;
  private int resultCode = 10002;

  @AbIocView(id=2131230925)
  private ProgressWebView webview;

  private void initView()
  {
    initTitleBar(0);
    this.tv_title.setText("");
    this.tv_title.setTextColor(getResources().getColor(2131099660));
    this.tv_left.setVisibility(0);
    this.tv_left.setImageResource(2130837803);
    ((RelativeLayout)findViewById(2131230924)).setBackgroundColor(getResources().getColor(2131099659));
    this.webview.getSettings().setJavaScriptEnabled(true);
    if (this.gift == null)
    {
      this.tv_title.setText("详情");
      this.isAdvert = true;
      this.btn_closs = ((ImageView)findViewById(2131231088));
      this.btn_closs.setOnClickListener(this);
      this.layout_bottom.setVisibility(8);
      String str = getIntent().getStringExtra("url");
      this.webview.loadUrl(addUserIdUrl(str));
      this.webview.addJavascriptInterface(new JavascriptInterfaceCloss(), "Advertise");
    }
    while (true)
    {
      this.downloadListener = new WebDownloadListener(this);
      this.webview.setDownloadListener(this.downloadListener);
      return;
      this.tv_title.setText("礼品详情");
      this.layout_bottom.setVisibility(0);
      this.webview.loadUrl(addUserIdUrl(this.gift.getH5Url()));
      if (this.gift.getIsexpire() == 1)
      {
        this.btn_confirm.setEnabled(false);
        this.btn_confirm.setBackgroundResource(2130837527);
      }
      Log.i("Test", "礼品详情Url: " + addUserIdUrl(this.gift.getH5Url()));
    }
  }

  public void back(View paramView)
  {
    if ((this.webview.canGoBack()) && (this.isAdvert))
    {
      if (this.btn_closs.getVisibility() == 8)
        this.btn_closs.setVisibility(0);
      this.webview.goBack();
      return;
    }
    super.back(paramView);
  }

  public void confirm(View paramView)
  {
    if (loginUser == null)
    {
      this.toast.setMessage("您还没有登录哦...");
      this.toast.show();
      return;
    }
    if (this.gift.getGiftType() == null)
    {
      this.toast.setMessage("还未获取到礼品类型哦，请稍后再试...");
      this.toast.show();
      return;
    }
    if (loginUser.getLoginType() == 6)
    {
      Toast.makeText(this, "您还没有登录哦...", 0).show();
      return;
    }
    paramView = new Intent(this, OrderFillActivity.class);
    paramView.putExtra("gift", this.gift);
    startActivityForResult(paramView, this.requestCode);
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if ((this.requestCode == paramInt1) && (this.resultCode == paramInt2))
      finish();
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131231088:
    }
    finish();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903101);
    this.gift = ((Gift)getIntent().getSerializableExtra("gift"));
    initView();
  }

  protected void onDestroy()
  {
    if (this.downloadListener != null)
    {
      WebDownloadListener.DownLoadCompleteReceiver localDownLoadCompleteReceiver = this.downloadListener.getReceiver();
      if (localDownLoadCompleteReceiver != null)
        unregisterReceiver(localDownLoadCompleteReceiver);
    }
    super.onDestroy();
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 4) && (this.webview.canGoBack()) && (this.isAdvert))
    {
      this.webview.goBack();
      if (this.btn_closs.getVisibility() == 8)
        this.btn_closs.setVisibility(0);
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
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

  final class JavascriptInterfaceCloss
  {
    JavascriptInterfaceCloss()
    {
    }

    @JavascriptInterface
    public void clickOnCloss()
    {
      WebViewActivity.this.finish();
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.WebViewActivity
 * JD-Core Version:    0.6.2
 */