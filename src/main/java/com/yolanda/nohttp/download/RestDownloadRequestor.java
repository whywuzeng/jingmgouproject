package com.yolanda.nohttp.download;

import com.yolanda.nohttp.BasicAnalyzeRequest;
import com.yolanda.nohttp.Headers;
import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.security.Certificate;
import java.io.File;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;

public class RestDownloadRequestor
  implements DownloadRequest, BasicAnalyzeRequest
{
  private Object cancelSign;
  private boolean inQueue = false;
  private boolean isAllowHttps = true;
  private boolean isCancel = false;
  private final boolean isDeleteOld;
  private final boolean isRange;
  private boolean isStart = false;
  private Certificate mCertificate;
  private int mConnectTimeout = 8000;
  private final String mFileDir;
  private final String mFileName;
  private int mReadTimeout = 8000;
  private Headers mheaders;
  private final String url;

  public RestDownloadRequestor(String paramString1, String paramString2, String paramString3, boolean paramBoolean1, boolean paramBoolean2)
  {
    this.url = paramString1;
    this.mFileDir = paramString2;
    this.mFileName = paramString3;
    this.isRange = paramBoolean1;
    this.isDeleteOld = paramBoolean2;
    this.mheaders = new Headers();
  }

  public void addCookie(CookieStore paramCookieStore)
  {
    try
    {
      paramCookieStore = paramCookieStore.get(new URI(this.url)).iterator();
      while (true)
      {
        if (!paramCookieStore.hasNext())
          return;
        addCookie((HttpCookie)paramCookieStore.next());
      }
    }
    catch (URISyntaxException paramCookieStore)
    {
      Logger.w(paramCookieStore);
    }
  }

  public void addCookie(HttpCookie paramHttpCookie)
  {
    try
    {
      URI localURI = new URI(this.url);
      if (HttpCookie.domainMatches(paramHttpCookie.getDomain(), localURI.getHost()))
        this.mheaders.add("Cookie", paramHttpCookie.getName() + "=" + paramHttpCookie.getValue());
      return;
    }
    catch (URISyntaxException paramHttpCookie)
    {
      Logger.w(paramHttpCookie);
    }
  }

  public void addHeader(String paramString1, String paramString2)
  {
    this.mheaders.add(paramString1, paramString2);
  }

  public void cancel()
  {
    this.isCancel = true;
    this.isStart = false;
  }

  public void cancelBySign(Object paramObject)
  {
    if (this.cancelSign == paramObject)
      cancel();
  }

  public int checkBeforeStatus()
  {
    if (this.isRange)
      try
      {
        if (new File(this.mFileDir, this.mFileName).exists())
          return 2;
        boolean bool = new File(this.mFileDir, this.mFileName + ".nohttp").exists();
        if (bool)
          return 1;
      }
      catch (Exception localException)
      {
      }
    return 0;
  }

  public BasicAnalyzeRequest getAnalyzeReqeust()
  {
    return this;
  }

  public Certificate getCertificate()
  {
    return this.mCertificate;
  }

  public int getConnectTimeout()
  {
    return this.mConnectTimeout;
  }

  public String getFileDir()
  {
    return this.mFileDir;
  }

  public String getFileName()
  {
    return this.mFileName;
  }

  public Headers getHeaders()
  {
    return this.mheaders;
  }

  public String getParamsEncoding()
  {
    return "UTF-8";
  }

  public int getReadTimeout()
  {
    return this.mReadTimeout;
  }

  public int getRequestMethod()
  {
    return 0;
  }

  public boolean hasBinary()
  {
    return false;
  }

  public boolean inQueue()
  {
    return this.inQueue;
  }

  public boolean isAllowHttps()
  {
    return this.isAllowHttps;
  }

  public boolean isCanceled()
  {
    return this.isCancel;
  }

  public boolean isDeleteOld()
  {
    return this.isDeleteOld;
  }

  public boolean isOutPut()
  {
    return false;
  }

  public boolean isRange()
  {
    return this.isRange;
  }

  public boolean isStarted()
  {
    return this.isStart;
  }

  public void removeAllHeaders()
  {
    this.mheaders.clear();
  }

  public void removeHeader(String paramString)
  {
    this.mheaders.removeAll(paramString);
  }

  public void setAllowHttps(boolean paramBoolean)
  {
    this.isAllowHttps = paramBoolean;
  }

  public void setCancelSign(Object paramObject)
  {
    this.cancelSign = paramObject;
  }

  public void setCertificate(Certificate paramCertificate)
  {
    this.mCertificate = paramCertificate;
  }

  public void setConnectTimeout(int paramInt)
  {
    this.mConnectTimeout = paramInt;
  }

  public void setHeader(String paramString1, String paramString2)
  {
    this.mheaders.set(paramString1, paramString2);
  }

  public void setReadTimeout(int paramInt)
  {
    this.mReadTimeout = paramInt;
  }

  public void start()
  {
    this.isStart = true;
    this.isCancel = false;
  }

  public void takeQueue(boolean paramBoolean)
  {
    this.inQueue = paramBoolean;
  }

  public String url()
  {
    return this.url;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.yolanda.nohttp.download.RestDownloadRequestor
 * JD-Core Version:    0.6.2
 */