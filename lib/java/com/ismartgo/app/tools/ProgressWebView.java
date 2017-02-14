package com.ismartgo.app.tools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.ismartgo.app.grid.utils.MyDialog;

public class ProgressWebView extends WebView
{
  private MyDialog dialog;
  private boolean isLoad;
  private ProgressBar progressbar;
  TextView tv_title;
  WebViewClient wvc = new WebViewClient()
  {
    public boolean shouldOverrideUrlLoading(WebView paramAnonymousWebView, String paramAnonymousString)
    {
      ProgressWebView.this.loadUrl(paramAnonymousString);
      return true;
    }
  };

  @SuppressLint({"SetJavaScriptEnabled"})
  public ProgressWebView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.dialog = new MyDialog(paramContext);
    this.dialog.setCancelable(false);
    this.isLoad = true;
    setWebViewClient(this.wvc);
    setWebChromeClient(new MyWebChromeClient(this)
    {
    });
    if (getSettings() == null)
      return;
    getSettings().setJavaScriptEnabled(true);
    getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
    getSettings().setLoadWithOverviewMode(true);
    getSettings().setUseWideViewPort(true);
    getSettings().setBlockNetworkImage(false);
  }

  public MyDialog getMyDialog()
  {
    return this.dialog;
  }

  public void getTitleView(TextView paramTextView)
  {
    this.tv_title = paramTextView;
  }

  public class MyWebChromeClient extends WebChromeClient
  {
    public MyWebChromeClient()
    {
    }

    public void onProgressChanged(WebView paramWebView, int paramInt)
    {
      if (paramInt == 100)
      {
        ProgressWebView.this.dialog.dismiss();
        paramWebView.setVisibility(0);
      }
      while (true)
      {
        super.onProgressChanged(paramWebView, paramInt);
        return;
        if (ProgressWebView.this.isLoad)
        {
          ProgressWebView.this.dialog.show();
          ProgressWebView.this.isLoad = false;
          paramWebView.setVisibility(4);
        }
      }
    }

    public void onReceivedTitle(WebView paramWebView, String paramString)
    {
      super.onReceivedTitle(paramWebView, paramString);
      if (ProgressWebView.this.tv_title != null)
        ProgressWebView.this.tv_title.setText(paramString);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.tools.ProgressWebView
 * JD-Core Version:    0.6.2
 */