package com.linj.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.widget.ImageView;
import com.linj.imageloader.displayer.BitmapDisplayer;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Semaphore;

public class ImageLoader
{
  private static final int DEAFULT_THREAD_COUNT = 1;
  private static final String TAG = "ImageLoader";
  private static ImageLoader mInstance;
  private Context mContext;
  private LruCache<String, Bitmap> mLruCache;
  private Thread mPoolThread;
  private Semaphore mSemaphoreThreadPool;
  private LinkedBlockingDeque<Runnable> mTaskQueue;
  private ExecutorService mThreadPool;
  private Type mType = Type.LIFO;
  private Handler mUIHandler;

  private ImageLoader(int paramInt, Type paramType, Context paramContext)
  {
    init(paramInt, paramType, paramContext);
  }

  private void addTask(Runnable paramRunnable)
  {
    try
    {
      this.mTaskQueue.put(paramRunnable);
      return;
    }
    catch (Exception paramRunnable)
    {
      Log.i("ImageLoader", paramRunnable.toString());
    }
  }

  private Runnable buildTask(final String paramString, final ImageView paramImageView, final DisplayImageOptions paramDisplayImageOptions)
  {
    return new Runnable()
    {
      public void run()
      {
        Bitmap localBitmap = null;
        File localFile;
        if (paramDisplayImageOptions.fromNet)
        {
          localFile = ImageLoader.this.getDiskCacheDir(paramImageView.getContext(), ImageLoader.this.md5(paramString));
          if (localFile.exists())
            localBitmap = ImageLoader.this.loadImageFromLocal(localFile.getAbsolutePath(), paramImageView);
        }
        while (true)
        {
          if (paramDisplayImageOptions.cacheInMemory)
            ImageLoader.this.addBitmapToLruCache(paramString, localBitmap);
          ImageLoader.this.refreashBitmap(paramString, paramImageView, localBitmap, paramDisplayImageOptions);
          ImageLoader.this.mSemaphoreThreadPool.release();
          return;
          if (paramDisplayImageOptions.cacheOnDisk)
          {
            if (DownloadImgUtils.downloadImgByUrl(paramString, localFile))
              localBitmap = ImageLoader.this.loadImageFromLocal(localFile.getAbsolutePath(), paramImageView);
          }
          else
          {
            localBitmap = DownloadImgUtils.downloadImgByUrl(paramString, paramImageView);
            continue;
            localBitmap = ImageLoader.this.loadImageFromLocal(paramString, paramImageView);
          }
        }
      }
    };
  }

  private Bitmap getBitmapFromLruCache(String paramString)
  {
    return (Bitmap)this.mLruCache.get(paramString);
  }

  public static ImageLoader getInstance(int paramInt, Type paramType, Context paramContext)
  {
    if (mInstance == null);
    try
    {
      if (mInstance == null)
        mInstance = new ImageLoader(paramInt, paramType, paramContext);
      return mInstance;
    }
    finally
    {
    }
    throw paramType;
  }

  public static ImageLoader getInstance(Context paramContext)
  {
    if (mInstance == null);
    try
    {
      if (mInstance == null)
        mInstance = new ImageLoader(1, Type.LIFO, paramContext);
      return mInstance;
    }
    finally
    {
    }
    throw paramContext;
  }

  private Runnable getTask()
    throws InterruptedException
  {
    if (this.mType == Type.FIFO)
      return (Runnable)this.mTaskQueue.takeFirst();
    return (Runnable)this.mTaskQueue.takeLast();
  }

  private void init(int paramInt, Type paramType, Context paramContext)
  {
    int i = (int)Runtime.getRuntime().maxMemory() / 8;
    this.mContext = paramContext.getApplicationContext();
    this.mLruCache = new LruCache(i)
    {
      protected int sizeOf(String paramAnonymousString, Bitmap paramAnonymousBitmap)
      {
        return paramAnonymousBitmap.getRowBytes() * paramAnonymousBitmap.getHeight();
      }
    };
    this.mThreadPool = Executors.newFixedThreadPool(paramInt);
    this.mType = paramType;
    this.mSemaphoreThreadPool = new Semaphore(paramInt, true);
    this.mTaskQueue = new LinkedBlockingDeque();
    initBackThread();
  }

  private void initBackThread()
  {
    this.mPoolThread = new Thread()
    {
      public void run()
      {
        while (true)
          try
          {
            ImageLoader.this.mSemaphoreThreadPool.acquire();
            Runnable localRunnable = ImageLoader.this.getTask();
            ImageLoader.this.mThreadPool.execute(localRunnable);
          }
          catch (InterruptedException localInterruptedException)
          {
            localInterruptedException.printStackTrace();
          }
      }
    };
    this.mPoolThread.start();
  }

  private Bitmap loadImageFromLocal(String paramString, ImageView paramImageView)
  {
    paramImageView = ImageSizeUtil.getImageViewSize(paramImageView);
    return decodeSampledBitmapFromPath(paramString, paramImageView.width, paramImageView.height);
  }

  private void refreashBitmap(String paramString, ImageView paramImageView, Bitmap paramBitmap, DisplayImageOptions paramDisplayImageOptions)
  {
    Message localMessage = Message.obtain();
    ImgBeanHolder localImgBeanHolder = new ImgBeanHolder(null);
    localImgBeanHolder.bitmap = paramBitmap;
    localImgBeanHolder.path = paramString;
    localImgBeanHolder.imageView = paramImageView;
    localImgBeanHolder.options = paramDisplayImageOptions;
    localMessage.obj = localImgBeanHolder;
    this.mUIHandler.sendMessage(localMessage);
  }

  protected void addBitmapToLruCache(String paramString, Bitmap paramBitmap)
  {
    if ((getBitmapFromLruCache(paramString) == null) && (paramBitmap != null))
      this.mLruCache.put(paramString, paramBitmap);
  }

  public String bytes2hex02(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int j = paramArrayOfByte.length;
    int i = 0;
    while (true)
    {
      if (i >= j)
        return localStringBuilder.toString();
      String str2 = Integer.toHexString(paramArrayOfByte[i] & 0xFF);
      String str1 = str2;
      if (str2.length() == 1)
        str1 = "0" + str2;
      localStringBuilder.append(str1);
      i += 1;
    }
  }

  protected Bitmap decodeSampledBitmapFromPath(String paramString, int paramInt1, int paramInt2)
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inJustDecodeBounds = true;
    BitmapFactory.decodeFile(paramString, localOptions);
    localOptions.inSampleSize = ImageSizeUtil.caculateInSampleSize(localOptions, paramInt1, paramInt2);
    localOptions.inJustDecodeBounds = false;
    return BitmapFactory.decodeFile(paramString, localOptions);
  }

  public File getDiskCacheDir(Context paramContext, String paramString)
  {
    if ("mounted".equals(Environment.getExternalStorageState()));
    for (paramContext = paramContext.getExternalCacheDir().getPath(); ; paramContext = paramContext.getCacheDir().getPath())
      return new File(paramContext + File.separator + paramString);
  }

  public void loadImage(String paramString, ImageView paramImageView, DisplayImageOptions paramDisplayImageOptions)
  {
    paramDisplayImageOptions.displayer.display(paramDisplayImageOptions.imageResOnLoading, paramImageView);
    if (this.mUIHandler == null)
      this.mUIHandler = new Handler(this.mContext.getMainLooper())
      {
        public void handleMessage(Message paramAnonymousMessage)
        {
          Object localObject = (ImageLoader.ImgBeanHolder)paramAnonymousMessage.obj;
          paramAnonymousMessage = ((ImageLoader.ImgBeanHolder)localObject).bitmap;
          ImageView localImageView = ((ImageLoader.ImgBeanHolder)localObject).imageView;
          localObject = ((ImageLoader.ImgBeanHolder)localObject).options;
          if (paramAnonymousMessage != null)
          {
            ((DisplayImageOptions)localObject).displayer.display(paramAnonymousMessage, localImageView);
            return;
          }
          ((DisplayImageOptions)localObject).displayer.display(((DisplayImageOptions)localObject).imageResOnFail, localImageView);
        }
      };
    Bitmap localBitmap = getBitmapFromLruCache(paramString);
    if (localBitmap != null)
    {
      refreashBitmap(paramString, paramImageView, localBitmap, paramDisplayImageOptions);
      return;
    }
    addTask(buildTask(paramString, paramImageView, paramDisplayImageOptions));
  }

  public String md5(String paramString)
  {
    try
    {
      paramString = bytes2hex02(MessageDigest.getInstance("md5").digest(paramString.getBytes()));
      return paramString;
    }
    catch (NoSuchAlgorithmException paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }

  private class ImgBeanHolder
  {
    Bitmap bitmap;
    ImageView imageView;
    DisplayImageOptions options;
    String path;

    private ImgBeanHolder()
    {
    }
  }

  public static enum Type
  {
    FIFO, LIFO;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.linj.imageloader.ImageLoader
 * JD-Core Version:    0.6.2
 */