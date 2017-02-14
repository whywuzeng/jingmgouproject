package com.ismartgo.app.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils.TruncateAt;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ab.view.ioc.AbIocView;
import com.ismartgo.app.common.CommonMethod;
import com.ismartgo.app.tools.ProgressWebView;

public class ShootReceiptActivity extends BaseActivity
{
  private String url;

  @AbIocView(id=2131230925)
  private ProgressWebView webview;

  private void initView()
  {
    initTitleBar(0);
    this.tv_title.setTextColor(getResources().getColor(2131099660));
    this.tv_title.setText("");
    this.tv_title.setSingleLine();
    this.tv_title.setMaxEms(10);
    this.tv_title.setEllipsize(TextUtils.TruncateAt.END);
    this.tv_left.setImageResource(2130837803);
    ((RelativeLayout)findViewById(2131230924)).setBackgroundColor(getResources().getColor(2131099659));
    this.url = getIntent().getStringExtra("url");
    if (!CommonMethod.isEmpty(this.url))
    {
      this.webview.loadUrl(addUserIdUrl(this.url));
      this.webview.setWebChromeClient(new WebChromeClient()
      {
        public void onReceivedTitle(WebView paramAnonymousWebView, String paramAnonymousString)
        {
          super.onReceivedTitle(paramAnonymousWebView, paramAnonymousString);
          if (!CommonMethod.isEmpty(paramAnonymousString))
            ShootReceiptActivity.this.tv_title.setText(paramAnonymousString);
        }
      });
      this.webview.addJavascriptInterface(new JavaScriptInterface(), "Receipt");
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903090);
    initView();
  }

  final class JavaScriptInterface
  {
    JavaScriptInterface()
    {
    }

    @JavascriptInterface
    public void clickOnClient(String paramString)
    {
      if ((!CommonMethod.isEmpty(paramString)) && (paramString.startsWith("smartgoapp")))
      {
        if (!paramString.equals("smartgoapp://receipt/take"))
          break label48;
        paramString = new Intent(ShootReceiptActivity.this, CameraActivity1.class);
        ShootReceiptActivity.this.startActivity(paramString);
      }
      label48: 
      do
      {
        return;
        if (paramString.equals("smartgoapp://receipt/list"))
        {
          paramString = new Intent(ShootReceiptActivity.this, MyReceiptListActivity.class);
          ShootReceiptActivity.this.startActivity(paramString);
          return;
        }
      }
      while (!paramString.equals("smartgoapp://giftlist"));
      paramString = new Intent(ShootReceiptActivity.this, RewardExchangeActivity.class);
      ShootReceiptActivity.this.startActivity(paramString);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.ShootReceiptActivity
 * JD-Core Version:    0.6.2
 */