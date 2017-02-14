package com.ismartgo.app.activity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.widget.TextView;
import com.ismartgo.app.tools.ProgressWebView;
import com.umeng.analytics.MobclickAgent;

public class ContactUsActivity extends BaseActivity
{
  private ProgressWebView mWebView;
  private String url = "http://www.ismartgo.com";

  private void initView()
  {
    initTitleBar();
    this.tv_title.setText("联系我们");
    this.mWebView = ((ProgressWebView)findViewById(2131230925));
    this.mWebView.getSettings().setJavaScriptEnabled(true);
    this.mWebView.loadUrl(this.url);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903067);
    initView();
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
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.ContactUsActivity
 * JD-Core Version:    0.6.2
 */