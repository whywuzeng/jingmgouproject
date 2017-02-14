package cn.sharesdk.framework.authorize;

import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import com.mob.tools.SSDKWebViewClient;

public abstract class b extends SSDKWebViewClient
{
  protected g a;
  protected String b;
  protected AuthorizeListener c;

  public b(g paramg)
  {
    this.a = paramg;
    paramg = paramg.a();
    this.b = paramg.getRedirectUri();
    this.c = paramg.getAuthorizeListener();
  }

  public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
  {
    paramWebView.stopLoading();
    paramWebView = this.a.a().getAuthorizeListener();
    this.a.finish();
    if (paramWebView != null)
      paramWebView.onError(new Throwable(paramString1 + " (" + paramInt + "): " + paramString2));
  }

  public void onReceivedSslError(WebView paramWebView, SslErrorHandler paramSslErrorHandler, SslError paramSslError)
  {
    paramSslErrorHandler.proceed();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.framework.authorize.b
 * JD-Core Version:    0.6.2
 */