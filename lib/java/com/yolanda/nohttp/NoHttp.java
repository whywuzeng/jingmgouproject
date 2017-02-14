package com.yolanda.nohttp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.widget.ImageView.ScaleType;
import com.yolanda.nohttp.download.DownloadConnection;
import com.yolanda.nohttp.download.DownloadListener;
import com.yolanda.nohttp.download.DownloadQueue;
import com.yolanda.nohttp.download.DownloadRequest;
import com.yolanda.nohttp.download.Downloader;
import com.yolanda.nohttp.download.RestDownloadRequestor;
import com.yolanda.nohttp.security.SecureVerifier;
import java.net.CookieManager;

public class NoHttp
{
  public static final String CHARSET_UTF8 = "UTF-8";
  public static final String MIMETYE_FILE = "application/octet-stream";
  public static final int TIMEOUT_8S = 8000;
  private static CookieManager sCookieManager;

  public static DownloadRequest createDownloadRequest(String paramString1, String paramString2, String paramString3, boolean paramBoolean1, boolean paramBoolean2)
  {
    return new RestDownloadRequestor(paramString1, paramString2, paramString3, paramBoolean1, paramBoolean2);
  }

  public static Request<Bitmap> createImageRequest(String paramString)
  {
    return createImageRequest(paramString, 1000, 1000, Bitmap.Config.ARGB_8888, ImageView.ScaleType.CENTER_INSIDE);
  }

  public static Request<Bitmap> createImageRequest(String paramString, int paramInt1, int paramInt2, Bitmap.Config paramConfig, ImageView.ScaleType paramScaleType)
  {
    return new ImageRequest(paramString, paramInt1, paramInt2, paramConfig, paramScaleType);
  }

  public static Request<String> createStringRequest(String paramString, int paramInt)
  {
    return new StringRequest(paramString, paramInt);
  }

  public static Request<String> createStringRequestGet(String paramString)
  {
    return createStringRequest(paramString, 0);
  }

  public static Request<String> createStringRequestPost(String paramString)
  {
    return createStringRequest(paramString, 1);
  }

  public static void downloadSync(Context paramContext, int paramInt, DownloadRequest paramDownloadRequest, DownloadListener paramDownloadListener)
  {
    DownloadConnection.getInstance(paramContext).download(paramInt, paramDownloadRequest, paramDownloadListener);
  }

  public static CookieManager getDefaultCookieManager()
  {
    if (sCookieManager == null)
      sCookieManager = new CookieManager();
    return sCookieManager;
  }

  public static DownloadQueue newDownloadQueue(Context paramContext)
  {
    return newDownloadQueue(paramContext, 2);
  }

  public static DownloadQueue newDownloadQueue(Context paramContext, int paramInt)
  {
    paramContext = new DownloadQueue(DownloadConnection.getInstance(paramContext), paramInt);
    paramContext.start();
    return paramContext;
  }

  public static RequestQueue newRequestQueue(Context paramContext)
  {
    return newRequestQueue(paramContext, 5);
  }

  public static RequestQueue newRequestQueue(Context paramContext, int paramInt)
  {
    paramContext = new RequestQueue(HttpRestConnection.getInstance(paramContext), paramInt);
    paramContext.start();
    return paramContext;
  }

  public static void setAllowAllHttps(boolean paramBoolean)
  {
    SecureVerifier.getInstance().setAllowAllHttps(paramBoolean);
  }

  public static void setDefaultCookieManager(CookieManager paramCookieManager)
  {
    if (paramCookieManager == null)
      throw new IllegalArgumentException("cookieManager == null");
    sCookieManager = paramCookieManager;
  }

  public static <T> Response<T> startRequestSync(Context paramContext, Request<T> paramRequest)
  {
    Response localResponse = null;
    if (paramRequest != null)
      localResponse = HttpRestConnection.getInstance(paramContext).request(paramRequest);
    return localResponse;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.yolanda.nohttp.NoHttp
 * JD-Core Version:    0.6.2
 */