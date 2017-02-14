package cn.sharesdk.framework.authorize;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

class d extends WebChromeClient
{
  d(RegisterView paramRegisterView, int paramInt)
  {
  }

  public void onProgressChanged(WebView paramWebView, int paramInt)
  {
    super.onProgressChanged(paramWebView, paramInt);
    paramWebView = (LinearLayout.LayoutParams)RegisterView.a(this.b).getLayoutParams();
    paramWebView.width = (this.a * paramInt / 100);
    RegisterView.a(this.b).setLayoutParams(paramWebView);
    if ((paramInt > 0) && (paramInt < 100))
    {
      RegisterView.a(this.b).setVisibility(0);
      return;
    }
    RegisterView.a(this.b).setVisibility(8);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.framework.authorize.d
 * JD-Core Version:    0.6.2
 */