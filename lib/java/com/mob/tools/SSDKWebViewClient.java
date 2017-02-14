package com.mob.tools;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Message;
import android.view.KeyEvent;
import android.webkit.HttpAuthHandler;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class SSDKWebViewClient extends WebViewClient
{
  public static final int ERROR_AUTHENTICATION = -4;
  public static final int ERROR_BAD_URL = -12;
  public static final int ERROR_CONNECT = -6;
  public static final int ERROR_FAILED_SSL_HANDSHAKE = -11;
  public static final int ERROR_FILE = -13;
  public static final int ERROR_FILE_NOT_FOUND = -14;
  public static final int ERROR_HOST_LOOKUP = -2;
  public static final int ERROR_IO = -7;
  public static final int ERROR_PROXY_AUTHENTICATION = -5;
  public static final int ERROR_REDIRECT_LOOP = -9;
  public static final int ERROR_TIMEOUT = -8;
  public static final int ERROR_TOO_MANY_REQUESTS = -15;
  public static final int ERROR_UNKNOWN = -1;
  public static final int ERROR_UNSUPPORTED_AUTH_SCHEME = -3;
  public static final int ERROR_UNSUPPORTED_SCHEME = -10;

  public void doUpdateVisitedHistory(WebView paramWebView, String paramString, boolean paramBoolean)
  {
  }

  public void onFormResubmission(WebView paramWebView, Message paramMessage1, Message paramMessage2)
  {
    paramMessage1.sendToTarget();
  }

  public void onLoadResource(WebView paramWebView, String paramString)
  {
  }

  public void onPageFinished(WebView paramWebView, String paramString)
  {
  }

  public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
  {
  }

  public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
  {
  }

  public void onReceivedHttpAuthRequest(WebView paramWebView, HttpAuthHandler paramHttpAuthHandler, String paramString1, String paramString2)
  {
    paramHttpAuthHandler.cancel();
  }

  public void onReceivedSslError(WebView paramWebView, SslErrorHandler paramSslErrorHandler, SslError paramSslError)
  {
    paramSslErrorHandler.cancel();
  }

  public void onScaleChanged(WebView paramWebView, float paramFloat1, float paramFloat2)
  {
  }

  public void onTooManyRedirects(WebView paramWebView, Message paramMessage1, Message paramMessage2)
  {
    paramMessage1.sendToTarget();
  }

  public void onUnhandledKeyEvent(WebView paramWebView, KeyEvent paramKeyEvent)
  {
  }

  public boolean shouldOverrideKeyEvent(WebView paramWebView, KeyEvent paramKeyEvent)
  {
    return false;
  }

  public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
  {
    return false;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.mob.tools.SSDKWebViewClient
 * JD-Core Version:    0.6.2
 */