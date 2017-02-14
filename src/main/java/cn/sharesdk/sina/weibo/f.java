package cn.sharesdk.sina.weibo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebView;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.authorize.AuthorizeHelper;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.authorize.b;
import com.mob.tools.utils.Ln;
import com.mob.tools.utils.R;

public class f extends b
{
  private boolean d;

  public f(cn.sharesdk.framework.authorize.g paramg)
  {
    super(paramg);
  }

  private void a(Platform paramPlatform, String paramString)
  {
    new g(this, paramPlatform, paramString).start();
  }

  private Intent b(String paramString)
  {
    paramString = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + paramString));
    paramString.putExtra("sms_body", "");
    paramString.setFlags(268435456);
    return paramString;
  }

  protected void a(String paramString)
  {
    if (this.d);
    Bundle localBundle;
    String str;
    do
    {
      return;
      this.d = true;
      localBundle = R.urlToBundle(paramString);
      paramString = localBundle.getString("error");
      str = localBundle.getString("error_code");
    }
    while (this.c == null);
    if ((paramString == null) && (str == null))
    {
      paramString = localBundle.getString("code");
      if (TextUtils.isEmpty(paramString))
        this.c.onError(new Throwable("Authorize code is empty"));
      a(this.a.a().getPlatform(), paramString);
      return;
    }
    if (paramString.equals("access_denied"))
    {
      this.c.onCancel();
      return;
    }
    int i = 0;
    try
    {
      int j = R.parseInt(str);
      i = j;
      paramString = paramString + " (" + i + ")";
      this.c.onError(new Throwable(paramString));
      return;
    }
    catch (Throwable localThrowable)
    {
      while (true)
        Ln.e(localThrowable);
    }
  }

  public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
  {
    if ((!TextUtils.isEmpty(this.b)) && (paramString.startsWith(this.b)))
    {
      paramWebView.stopLoading();
      this.a.finish();
      a(paramString);
    }
    do
    {
      return;
      if (!paramString.startsWith("sms:"))
        break;
      paramString = paramString.substring(4);
      try
      {
        paramBitmap = b(paramString);
        paramBitmap.setPackage("com.android.mms");
        paramWebView.getContext().startActivity(paramBitmap);
        return;
      }
      catch (Throwable paramBitmap)
      {
        try
        {
          paramString = b(paramString);
          paramWebView.getContext().startActivity(paramString);
          return;
        }
        catch (Throwable paramWebView)
        {
        }
      }
    }
    while (this.c == null);
    this.c.onError(paramWebView);
    return;
    super.onPageStarted(paramWebView, paramString, paramBitmap);
  }

  public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
  {
    if ((!TextUtils.isEmpty(this.b)) && (paramString.startsWith(this.b)))
    {
      paramWebView.stopLoading();
      this.a.finish();
      a(paramString);
    }
    do
    {
      return true;
      if (!paramString.startsWith("sms:"))
        break;
      paramString = paramString.substring(4);
      try
      {
        Intent localIntent = b(paramString);
        localIntent.setPackage("com.android.mms");
        paramWebView.getContext().startActivity(localIntent);
        return true;
      }
      catch (Throwable localThrowable)
      {
        try
        {
          paramString = b(paramString);
          paramWebView.getContext().startActivity(paramString);
          return true;
        }
        catch (Throwable paramWebView)
        {
        }
      }
    }
    while (this.c == null);
    this.c.onError(paramWebView);
    return true;
    return super.shouldOverrideUrlLoading(paramWebView, paramString);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.sina.weibo.f
 * JD-Core Version:    0.6.2
 */