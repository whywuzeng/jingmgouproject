package com.yolanda.nohttp.tools;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.yolanda.nohttp.Logger;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImageNetDowner
{
  private static ImageNetDowner instance;
  private String mCachePath;
  private ExecutorService mExecutorService;
  private Poster mPoster;

  private ImageNetDowner(Context paramContext)
  {
    setCachePath(paramContext.getCacheDir().getAbsolutePath());
    this.mPoster = new Poster();
    this.mExecutorService = Executors.newFixedThreadPool(2);
  }

  public static ImageNetDowner getInstance(Context paramContext)
  {
    if (instance == null)
      instance = new ImageNetDowner(paramContext);
    return instance;
  }

  private String getMa5ForString(String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    try
    {
      paramString = MessageDigest.getInstance("MD5").digest(paramString.getBytes());
      int i = 0;
      while (true)
      {
        int j = paramString.length;
        if (i >= j)
          return localStringBuffer.toString();
        int k = paramString[i];
        j = k;
        if (k < 0)
          j = k + 256;
        if (j < 16)
          localStringBuffer.append("0");
        localStringBuffer.append(Integer.toHexString(j));
        i += 1;
      }
    }
    catch (Exception paramString)
    {
      while (true)
        paramString.printStackTrace();
    }
  }

  public void downloadImage(String paramString, OnImageDownListener paramOnImageDownListener, Object paramObject)
  {
    downloadImage(paramString, paramOnImageDownListener, paramObject, 3000);
  }

  public void downloadImage(String paramString, OnImageDownListener paramOnImageDownListener, Object paramObject, int paramInt)
  {
    StringBuffer localStringBuffer = new StringBuffer(this.mCachePath);
    localStringBuffer.append(File.separator);
    localStringBuffer.append(getMa5ForString(paramString));
    localStringBuffer.append(".png");
    downloadImage(paramString, paramOnImageDownListener, localStringBuffer.toString(), paramObject);
  }

  public void downloadImage(String paramString1, OnImageDownListener paramOnImageDownListener, String paramString2, Object paramObject)
  {
    downloadImage(paramString1, paramOnImageDownListener, paramString2, paramObject, 3000);
  }

  public void downloadImage(String paramString1, OnImageDownListener paramOnImageDownListener, String paramString2, Object paramObject, int paramInt)
  {
    Logger.d("ImageDownload url: " + paramString1);
    if (new File(paramString2).exists())
    {
      ImageHolder localImageHolder = new ImageHolder(null);
      localImageHolder.imageUrl = paramString1;
      localImageHolder.isSucceed = true;
      localImageHolder.imagePath = paramString2;
      localImageHolder.downListener = paramOnImageDownListener;
      localImageHolder.tag = paramObject;
      this.mPoster.obtainMessage(0, localImageHolder).sendToTarget();
      return;
    }
    this.mExecutorService.execute(new DownImageThread(paramString1, paramString2, paramOnImageDownListener, paramObject, paramInt));
  }

  public void setCachePath(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      throw new NullPointerException("cachePath cann't null");
    this.mCachePath = paramString;
    paramString = new File(paramString);
    if ((paramString.exists()) && (paramString.isFile()))
      paramString.delete();
    while (paramString.exists())
      return;
    paramString.mkdirs();
  }

  private class DownImageThread
    implements Runnable
  {
    private ImageNetDowner.OnImageDownListener mDownListener;
    private String mImagePath;
    private String mImageUrl;
    private Object tag;
    private int timeOut;

    public DownImageThread(String paramString1, String paramOnImageDownListener, ImageNetDowner.OnImageDownListener paramObject, Object paramInt, int arg6)
    {
      this.mImageUrl = paramString1;
      this.mImagePath = paramOnImageDownListener;
      this.mDownListener = paramObject;
      this.tag = paramInt;
      int i;
      this.timeOut = i;
    }

    public void run()
    {
      ImageNetDowner.ImageHolder localImageHolder = new ImageNetDowner.ImageHolder(ImageNetDowner.this, null);
      localImageHolder.imageUrl = this.mImageUrl;
      localImageHolder.downListener = this.mDownListener;
      localImageHolder.imagePath = this.mImagePath;
      localImageHolder.tag = this.tag;
      try
      {
        Object localObject = (HttpURLConnection)new URL(this.mImageUrl).openConnection();
        ((HttpURLConnection)localObject).setConnectTimeout(this.timeOut);
        ((HttpURLConnection)localObject).setReadTimeout(this.timeOut);
        ((HttpURLConnection)localObject).connect();
        FileOutputStream localFileOutputStream;
        byte[] arrayOfByte;
        if (200 == ((HttpURLConnection)localObject).getResponseCode())
        {
          localFileOutputStream = new FileOutputStream(this.mImagePath);
          localObject = ((HttpURLConnection)localObject).getInputStream();
          arrayOfByte = new byte[1024];
        }
        while (true)
        {
          int i = ((InputStream)localObject).read(arrayOfByte);
          if (i == -1)
          {
            ((InputStream)localObject).close();
            localFileOutputStream.flush();
            localFileOutputStream.close();
            localImageHolder.isSucceed = true;
            ImageNetDowner.this.mPoster.obtainMessage(0, localImageHolder).sendToTarget();
            return;
          }
          localFileOutputStream.write(arrayOfByte, 0, i);
        }
      }
      catch (Exception localException)
      {
        while (true)
          localException.printStackTrace();
      }
    }
  }

  private class ImageHolder
  {
    ImageNetDowner.OnImageDownListener downListener;
    String imagePath;
    String imageUrl;
    boolean isSucceed;
    Object tag;

    private ImageHolder()
    {
    }

    void post()
    {
      this.downListener.onDownFinish(this.imageUrl, this.imagePath, this.isSucceed, this.tag);
    }
  }

  public static abstract interface OnImageDownListener
  {
    public abstract void onDownFinish(String paramString1, String paramString2, boolean paramBoolean, Object paramObject);
  }

  private static class Poster extends Handler
  {
    Poster()
    {
      super();
    }

    public void handleMessage(Message paramMessage)
    {
      ((ImageNetDowner.ImageHolder)paramMessage.obj).post();
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.yolanda.nohttp.tools.ImageNetDowner
 * JD-Core Version:    0.6.2
 */