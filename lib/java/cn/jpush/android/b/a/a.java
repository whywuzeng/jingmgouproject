package cn.jpush.android.b.a;

import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebView;

public final class a extends c
{
  public a(String paramString, Class paramClass)
  {
    super(paramString, paramClass);
  }

  public final boolean onJsAlert(WebView paramWebView, String paramString1, String paramString2, JsResult paramJsResult)
  {
    return super.onJsAlert(paramWebView, paramString1, paramString2, paramJsResult);
  }

  public final boolean onJsPrompt(WebView paramWebView, String paramString1, String paramString2, String paramString3, JsPromptResult paramJsPromptResult)
  {
    return super.onJsPrompt(paramWebView, paramString1, paramString2, paramString3, paramJsPromptResult);
  }

  public final void onProgressChanged(WebView paramWebView, int paramInt)
  {
    super.onProgressChanged(paramWebView, paramInt);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.b.a.a
 * JD-Core Version:    0.6.2
 */