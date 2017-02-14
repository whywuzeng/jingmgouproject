package com.ismartgo.app.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

public class UsuallyQuestionActivity extends BaseActivity
{
  private WebView mWebView;

  private void back()
  {
    if ((this.mWebView != null) && (this.mWebView.canGoBack()))
    {
      this.mWebView.goBack();
      return;
    }
    finish();
  }

  private void initView()
  {
    initTitleBar();
    this.tv_title.setText("常见问题");
    this.mWebView = ((WebView)findViewById(2131231275));
    this.mWebView.loadUrl("http://sv.ismartgo.cn:29090/appsv2/about/helpdoc.jsp");
    this.tv_left.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        UsuallyQuestionActivity.this.back();
      }
    });
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903174);
    initView();
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 4) && (paramKeyEvent.getAction() == 0) && (this.mWebView != null) && (this.mWebView.canGoBack()))
    {
      this.mWebView.goBack();
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.UsuallyQuestionActivity
 * JD-Core Version:    0.6.2
 */