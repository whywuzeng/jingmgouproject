package cn.sharesdk.tencent.qzone;

import android.webkit.WebView;
import com.mob.tools.SSDKWebViewClient;

class l extends SSDKWebViewClient
{
  l(i parami)
  {
  }

  public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
  {
    if ((paramString != null) && (paramString.startsWith(i.b(this.a))))
      i.a(this.a, paramString);
    while (true)
    {
      return super.shouldOverrideUrlLoading(paramWebView, paramString);
      if ((paramString != null) && (paramString.startsWith("mqzone://")))
        i.b(this.a, paramString);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.tencent.qzone.l
 * JD-Core Version:    0.6.2
 */