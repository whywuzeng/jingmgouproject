package net.tsz.afinal;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import net.tsz.afinal.bitmap.core.BitmapCache;
import net.tsz.afinal.bitmap.core.BitmapCache.ImageCacheParams;
import net.tsz.afinal.bitmap.core.BitmapDisplayConfig;
import net.tsz.afinal.bitmap.core.BitmapProcess;
import net.tsz.afinal.bitmap.display.Displayer;
import net.tsz.afinal.bitmap.display.SimpleDisplayer;
import net.tsz.afinal.bitmap.download.Downloader;
import net.tsz.afinal.bitmap.download.SimpleDownloader;
import net.tsz.afinal.core.AsyncTask;
import net.tsz.afinal.utils.Utils;

public class FinalBitmap
{
  private static FinalBitmap mFinalBitmap;
  private ExecutorService bitmapLoadAndDisplayExecutor;
  private HashMap<String, BitmapDisplayConfig> configMap = new HashMap();
  private BitmapProcess mBitmapProcess;
  private FinalBitmapConfig mConfig;
  private Context mContext;
  private boolean mExitTasksEarly = false;
  private BitmapCache mImageCache;
  private boolean mInit = false;
  private boolean mPauseWork = false;
  private final Object mPauseWorkLock = new Object();

  private FinalBitmap(Context paramContext)
  {
    this.mContext = paramContext;
    this.mConfig = new FinalBitmapConfig(paramContext);
    configDiskCachePath(Utils.getDiskCacheDir(paramContext, "afinalCache").getAbsolutePath());
    configDisplayer(new SimpleDisplayer());
    configDownlader(new SimpleDownloader());
  }

  public static boolean checkImageTask(Object paramObject, View paramView)
  {
    paramView = getBitmapTaskFromImageView(paramView);
    if (paramView != null)
    {
      Object localObject = paramView.data;
      if ((localObject == null) || (!localObject.equals(paramObject)))
        paramView.cancel(true);
    }
    else
    {
      return true;
    }
    return false;
  }

  private void clearCacheInBackgroud(String paramString)
  {
    if (this.mImageCache != null)
      this.mImageCache.clearCache(paramString);
  }

  private void clearCacheInternalInBackgroud()
  {
    if (this.mImageCache != null)
      this.mImageCache.clearCache();
  }

  private void clearDiskCacheInBackgroud()
  {
    if (this.mImageCache != null)
      this.mImageCache.clearDiskCache();
  }

  private void clearDiskCacheInBackgroud(String paramString)
  {
    if (this.mImageCache != null)
      this.mImageCache.clearDiskCache(paramString);
  }

  private void closeCacheInternalInBackgroud()
  {
    if (this.mImageCache != null)
    {
      this.mImageCache.close();
      this.mImageCache = null;
      mFinalBitmap = null;
    }
  }

  public static FinalBitmap create(Context paramContext)
  {
    try
    {
      if (mFinalBitmap == null)
        mFinalBitmap = new FinalBitmap(paramContext.getApplicationContext());
      paramContext = mFinalBitmap;
      return paramContext;
    }
    finally
    {
    }
    throw paramContext;
  }

  private void doDisplay(View paramView, String paramString, BitmapDisplayConfig paramBitmapDisplayConfig)
  {
    if (!this.mInit)
      init();
    if ((TextUtils.isEmpty(paramString)) || (paramView == null));
    do
    {
      return;
      localObject = paramBitmapDisplayConfig;
      if (paramBitmapDisplayConfig == null)
        localObject = this.mConfig.defaultDisplayConfig;
      paramBitmapDisplayConfig = null;
      if (this.mImageCache != null)
        paramBitmapDisplayConfig = this.mImageCache.getBitmapFromMemoryCache(paramString);
      if (paramBitmapDisplayConfig != null)
      {
        if ((paramView instanceof ImageView))
        {
          ((ImageView)paramView).setImageBitmap(paramBitmapDisplayConfig);
          return;
        }
        paramView.setBackgroundDrawable(new BitmapDrawable(paramBitmapDisplayConfig));
        return;
      }
    }
    while (!checkImageTask(paramString, paramView));
    paramBitmapDisplayConfig = new BitmapLoadAndDisplayTask(paramView, (BitmapDisplayConfig)localObject);
    Object localObject = new AsyncDrawable(this.mContext.getResources(), ((BitmapDisplayConfig)localObject).getLoadingBitmap(), paramBitmapDisplayConfig);
    if ((paramView instanceof ImageView))
      ((ImageView)paramView).setImageDrawable((Drawable)localObject);
    while (true)
    {
      paramBitmapDisplayConfig.executeOnExecutor(this.bitmapLoadAndDisplayExecutor, new Object[] { paramString });
      return;
      paramView.setBackgroundDrawable((Drawable)localObject);
    }
  }

  private static BitmapLoadAndDisplayTask getBitmapTaskFromImageView(View paramView)
  {
    if (paramView != null)
    {
      if ((paramView instanceof ImageView));
      for (paramView = ((ImageView)paramView).getDrawable(); (paramView instanceof AsyncDrawable); paramView = paramView.getBackground())
        return ((AsyncDrawable)paramView).getBitmapWorkerTask();
    }
    return null;
  }

  private BitmapDisplayConfig getDisplayConfig()
  {
    BitmapDisplayConfig localBitmapDisplayConfig = new BitmapDisplayConfig();
    localBitmapDisplayConfig.setAnimation(this.mConfig.defaultDisplayConfig.getAnimation());
    localBitmapDisplayConfig.setAnimationType(this.mConfig.defaultDisplayConfig.getAnimationType());
    localBitmapDisplayConfig.setBitmapHeight(this.mConfig.defaultDisplayConfig.getBitmapHeight());
    localBitmapDisplayConfig.setBitmapWidth(this.mConfig.defaultDisplayConfig.getBitmapWidth());
    localBitmapDisplayConfig.setLoadfailBitmap(this.mConfig.defaultDisplayConfig.getLoadfailBitmap());
    localBitmapDisplayConfig.setLoadingBitmap(this.mConfig.defaultDisplayConfig.getLoadingBitmap());
    return localBitmapDisplayConfig;
  }

  private FinalBitmap init()
  {
    BitmapCache.ImageCacheParams localImageCacheParams;
    if (!this.mInit)
    {
      localImageCacheParams = new BitmapCache.ImageCacheParams(this.mConfig.cachePath);
      if ((this.mConfig.memCacheSizePercent <= 0.05D) || (this.mConfig.memCacheSizePercent >= 0.8D))
        break label165;
      localImageCacheParams.setMemCacheSizePercent(this.mContext, this.mConfig.memCacheSizePercent);
    }
    while (true)
    {
      if (this.mConfig.diskCacheSize > 5242880)
        localImageCacheParams.setDiskCacheSize(this.mConfig.diskCacheSize);
      localImageCacheParams.setRecycleImmediately(this.mConfig.recycleImmediately);
      this.mImageCache = new BitmapCache(localImageCacheParams);
      this.bitmapLoadAndDisplayExecutor = Executors.newFixedThreadPool(this.mConfig.poolSize, new ThreadFactory()
      {
        public Thread newThread(Runnable paramAnonymousRunnable)
        {
          paramAnonymousRunnable = new Thread(paramAnonymousRunnable);
          paramAnonymousRunnable.setPriority(4);
          return paramAnonymousRunnable;
        }
      });
      this.mBitmapProcess = new BitmapProcess(this.mConfig.downloader, this.mImageCache);
      this.mInit = true;
      return this;
      label165: if (this.mConfig.memCacheSize > 2097152)
        localImageCacheParams.setMemCacheSize(this.mConfig.memCacheSize);
      else
        localImageCacheParams.setMemCacheSizePercent(this.mContext, 0.3F);
    }
  }

  private Bitmap processBitmap(String paramString, BitmapDisplayConfig paramBitmapDisplayConfig)
  {
    if (this.mBitmapProcess != null)
      return this.mBitmapProcess.getBitmap(paramString, paramBitmapDisplayConfig);
    return null;
  }

  public void clearCache()
  {
    new CacheExecutecTask(null).execute(new Object[] { Integer.valueOf(1) });
  }

  public void clearCache(String paramString)
  {
    new CacheExecutecTask(null).execute(new Object[] { Integer.valueOf(4), paramString });
  }

  public void clearDiskCache()
  {
    new CacheExecutecTask(null).execute(new Object[] { Integer.valueOf(3) });
  }

  public void clearDiskCache(String paramString)
  {
    new CacheExecutecTask(null).execute(new Object[] { Integer.valueOf(5), paramString });
  }

  public void clearMemoryCache()
  {
    if (this.mImageCache != null)
      this.mImageCache.clearMemoryCache();
  }

  public void clearMemoryCache(String paramString)
  {
    if (this.mImageCache != null)
      this.mImageCache.clearMemoryCache(paramString);
  }

  public void closeCache()
  {
    new CacheExecutecTask(null).execute(new Object[] { Integer.valueOf(2) });
  }

  public FinalBitmap configBitmapLoadThreadSize(int paramInt)
  {
    if (paramInt >= 1)
      this.mConfig.poolSize = paramInt;
    return this;
  }

  public FinalBitmap configBitmapMaxHeight(int paramInt)
  {
    this.mConfig.defaultDisplayConfig.setBitmapHeight(paramInt);
    return this;
  }

  public FinalBitmap configBitmapMaxWidth(int paramInt)
  {
    this.mConfig.defaultDisplayConfig.setBitmapWidth(paramInt);
    return this;
  }

  public FinalBitmap configDiskCachePath(String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
      this.mConfig.cachePath = paramString;
    return this;
  }

  public FinalBitmap configDiskCacheSize(int paramInt)
  {
    this.mConfig.diskCacheSize = paramInt;
    return this;
  }

  public FinalBitmap configDisplayer(Displayer paramDisplayer)
  {
    this.mConfig.displayer = paramDisplayer;
    return this;
  }

  public FinalBitmap configDownlader(Downloader paramDownloader)
  {
    this.mConfig.downloader = paramDownloader;
    return this;
  }

  public FinalBitmap configLoadfailImage(int paramInt)
  {
    this.mConfig.defaultDisplayConfig.setLoadfailBitmap(BitmapFactory.decodeResource(this.mContext.getResources(), paramInt));
    return this;
  }

  public FinalBitmap configLoadfailImage(Bitmap paramBitmap)
  {
    this.mConfig.defaultDisplayConfig.setLoadfailBitmap(paramBitmap);
    return this;
  }

  public FinalBitmap configLoadingImage(int paramInt)
  {
    this.mConfig.defaultDisplayConfig.setLoadingBitmap(BitmapFactory.decodeResource(this.mContext.getResources(), paramInt));
    return this;
  }

  public FinalBitmap configLoadingImage(Bitmap paramBitmap)
  {
    this.mConfig.defaultDisplayConfig.setLoadingBitmap(paramBitmap);
    return this;
  }

  public FinalBitmap configMemoryCachePercent(float paramFloat)
  {
    this.mConfig.memCacheSizePercent = paramFloat;
    return this;
  }

  public FinalBitmap configMemoryCacheSize(int paramInt)
  {
    this.mConfig.memCacheSize = paramInt;
    return this;
  }

  public FinalBitmap configRecycleImmediately(boolean paramBoolean)
  {
    this.mConfig.recycleImmediately = paramBoolean;
    return this;
  }

  public void display(View paramView, String paramString)
  {
    doDisplay(paramView, paramString, null);
  }

  public void display(View paramView, String paramString, int paramInt1, int paramInt2)
  {
    BitmapDisplayConfig localBitmapDisplayConfig2 = (BitmapDisplayConfig)this.configMap.get(paramInt1 + "_" + paramInt2);
    BitmapDisplayConfig localBitmapDisplayConfig1 = localBitmapDisplayConfig2;
    if (localBitmapDisplayConfig2 == null)
    {
      localBitmapDisplayConfig1 = getDisplayConfig();
      localBitmapDisplayConfig1.setBitmapHeight(paramInt2);
      localBitmapDisplayConfig1.setBitmapWidth(paramInt1);
      this.configMap.put(paramInt1 + "_" + paramInt2, localBitmapDisplayConfig1);
    }
    doDisplay(paramView, paramString, localBitmapDisplayConfig1);
  }

  public void display(View paramView, String paramString, int paramInt1, int paramInt2, Bitmap paramBitmap1, Bitmap paramBitmap2)
  {
    BitmapDisplayConfig localBitmapDisplayConfig2 = (BitmapDisplayConfig)this.configMap.get(paramInt1 + "_" + paramInt2 + "_" + String.valueOf(paramBitmap1) + "_" + String.valueOf(paramBitmap2));
    BitmapDisplayConfig localBitmapDisplayConfig1 = localBitmapDisplayConfig2;
    if (localBitmapDisplayConfig2 == null)
    {
      localBitmapDisplayConfig1 = getDisplayConfig();
      localBitmapDisplayConfig1.setBitmapHeight(paramInt2);
      localBitmapDisplayConfig1.setBitmapWidth(paramInt1);
      localBitmapDisplayConfig1.setLoadingBitmap(paramBitmap1);
      localBitmapDisplayConfig1.setLoadfailBitmap(paramBitmap2);
      this.configMap.put(paramInt1 + "_" + paramInt2 + "_" + String.valueOf(paramBitmap1) + "_" + String.valueOf(paramBitmap2), localBitmapDisplayConfig1);
    }
    doDisplay(paramView, paramString, localBitmapDisplayConfig1);
  }

  public void display(View paramView, String paramString, Bitmap paramBitmap)
  {
    BitmapDisplayConfig localBitmapDisplayConfig2 = (BitmapDisplayConfig)this.configMap.get(String.valueOf(paramBitmap));
    BitmapDisplayConfig localBitmapDisplayConfig1 = localBitmapDisplayConfig2;
    if (localBitmapDisplayConfig2 == null)
    {
      localBitmapDisplayConfig1 = getDisplayConfig();
      localBitmapDisplayConfig1.setLoadingBitmap(paramBitmap);
      this.configMap.put(String.valueOf(paramBitmap), localBitmapDisplayConfig1);
    }
    doDisplay(paramView, paramString, localBitmapDisplayConfig1);
  }

  public void display(View paramView, String paramString, Bitmap paramBitmap1, Bitmap paramBitmap2)
  {
    BitmapDisplayConfig localBitmapDisplayConfig2 = (BitmapDisplayConfig)this.configMap.get(String.valueOf(paramBitmap1) + "_" + String.valueOf(paramBitmap2));
    BitmapDisplayConfig localBitmapDisplayConfig1 = localBitmapDisplayConfig2;
    if (localBitmapDisplayConfig2 == null)
    {
      localBitmapDisplayConfig1 = getDisplayConfig();
      localBitmapDisplayConfig1.setLoadingBitmap(paramBitmap1);
      localBitmapDisplayConfig1.setLoadfailBitmap(paramBitmap2);
      this.configMap.put(String.valueOf(paramBitmap1) + "_" + String.valueOf(paramBitmap2), localBitmapDisplayConfig1);
    }
    doDisplay(paramView, paramString, localBitmapDisplayConfig1);
  }

  public void display(View paramView, String paramString, BitmapDisplayConfig paramBitmapDisplayConfig)
  {
    doDisplay(paramView, paramString, paramBitmapDisplayConfig);
  }

  public void exitTasksEarly(boolean paramBoolean)
  {
    this.mExitTasksEarly = paramBoolean;
    if (paramBoolean)
      pauseWork(false);
  }

  public Bitmap getBitmapFromCache(String paramString)
  {
    Bitmap localBitmap2 = getBitmapFromMemoryCache(paramString);
    Bitmap localBitmap1 = localBitmap2;
    if (localBitmap2 == null)
      localBitmap1 = getBitmapFromDiskCache(paramString);
    return localBitmap1;
  }

  public Bitmap getBitmapFromDiskCache(String paramString)
  {
    return getBitmapFromDiskCache(paramString, null);
  }

  public Bitmap getBitmapFromDiskCache(String paramString, BitmapDisplayConfig paramBitmapDisplayConfig)
  {
    return this.mBitmapProcess.getFromDisk(paramString, paramBitmapDisplayConfig);
  }

  public Bitmap getBitmapFromMemoryCache(String paramString)
  {
    return this.mImageCache.getBitmapFromMemoryCache(paramString);
  }

  public void onDestroy()
  {
    closeCache();
  }

  public void onPause()
  {
    setExitTasksEarly(true);
  }

  public void onResume()
  {
    setExitTasksEarly(false);
  }

  public void pauseWork(boolean paramBoolean)
  {
    synchronized (this.mPauseWorkLock)
    {
      this.mPauseWork = paramBoolean;
      if (!this.mPauseWork)
        this.mPauseWorkLock.notifyAll();
      return;
    }
  }

  public void setExitTasksEarly(boolean paramBoolean)
  {
    this.mExitTasksEarly = paramBoolean;
  }

  private static class AsyncDrawable extends BitmapDrawable
  {
    private final WeakReference<FinalBitmap.BitmapLoadAndDisplayTask> bitmapWorkerTaskReference;

    public AsyncDrawable(Resources paramResources, Bitmap paramBitmap, FinalBitmap.BitmapLoadAndDisplayTask paramBitmapLoadAndDisplayTask)
    {
      super(paramBitmap);
      this.bitmapWorkerTaskReference = new WeakReference(paramBitmapLoadAndDisplayTask);
    }

    public FinalBitmap.BitmapLoadAndDisplayTask getBitmapWorkerTask()
    {
      return (FinalBitmap.BitmapLoadAndDisplayTask)this.bitmapWorkerTaskReference.get();
    }
  }

  private class BitmapLoadAndDisplayTask extends AsyncTask<Object, Void, Bitmap>
  {
    private Object data;
    private final BitmapDisplayConfig displayConfig;
    private final WeakReference<View> imageViewReference;

    public BitmapLoadAndDisplayTask(View paramBitmapDisplayConfig, BitmapDisplayConfig arg3)
    {
      this.imageViewReference = new WeakReference(paramBitmapDisplayConfig);
      Object localObject;
      this.displayConfig = localObject;
    }

    private View getAttachedImageView()
    {
      View localView = (View)this.imageViewReference.get();
      if (this == FinalBitmap.getBitmapTaskFromImageView(localView))
        return localView;
      return null;
    }

    protected Bitmap doInBackground(Object[] arg1)
    {
      this.data = ???[0];
      String str = String.valueOf(this.data);
      Object localObject1 = null;
      synchronized (FinalBitmap.this.mPauseWorkLock)
      {
        while (true)
        {
          if ((!FinalBitmap.this.mPauseWork) || (isCancelled()))
          {
            ??? = localObject1;
            if (0 == 0)
            {
              ??? = localObject1;
              if (!isCancelled())
              {
                ??? = localObject1;
                if (getAttachedImageView() != null)
                {
                  ??? = localObject1;
                  if (!FinalBitmap.this.mExitTasksEarly)
                    ??? = FinalBitmap.this.processBitmap(str, this.displayConfig);
                }
              }
            }
            if (??? != null)
              FinalBitmap.this.mImageCache.addToMemoryCache(str, ???);
            return ???;
          }
          try
          {
            FinalBitmap.this.mPauseWorkLock.wait();
          }
          catch (InterruptedException localInterruptedException)
          {
          }
        }
      }
    }

    protected void onCancelled(Bitmap arg1)
    {
      super.onCancelled(???);
      synchronized (FinalBitmap.this.mPauseWorkLock)
      {
        FinalBitmap.this.mPauseWorkLock.notifyAll();
        return;
      }
    }

    protected void onPostExecute(Bitmap paramBitmap)
    {
      if ((isCancelled()) || (FinalBitmap.this.mExitTasksEarly))
        paramBitmap = null;
      View localView = getAttachedImageView();
      if ((paramBitmap != null) && (localView != null))
        FinalBitmap.this.mConfig.displayer.loadCompletedisplay(localView, paramBitmap, this.displayConfig);
      while ((paramBitmap != null) || (localView == null))
        return;
      FinalBitmap.this.mConfig.displayer.loadFailDisplay(localView, this.displayConfig.getLoadfailBitmap());
    }
  }

  private class CacheExecutecTask extends AsyncTask<Object, Void, Void>
  {
    public static final int MESSAGE_CLEAR = 1;
    public static final int MESSAGE_CLEAR_DISK = 3;
    public static final int MESSAGE_CLEAR_KEY = 4;
    public static final int MESSAGE_CLEAR_KEY_IN_DISK = 5;
    public static final int MESSAGE_CLOSE = 2;

    private CacheExecutecTask()
    {
    }

    protected Void doInBackground(Object[] paramArrayOfObject)
    {
      switch (((Integer)paramArrayOfObject[0]).intValue())
      {
      default:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      }
      while (true)
      {
        return null;
        FinalBitmap.this.clearCacheInternalInBackgroud();
        continue;
        FinalBitmap.this.closeCacheInternalInBackgroud();
        continue;
        FinalBitmap.this.clearDiskCacheInBackgroud();
        continue;
        FinalBitmap.this.clearCacheInBackgroud(String.valueOf(paramArrayOfObject[1]));
        continue;
        FinalBitmap.this.clearDiskCacheInBackgroud(String.valueOf(paramArrayOfObject[1]));
      }
    }
  }

  private class FinalBitmapConfig
  {
    public String cachePath;
    public BitmapDisplayConfig defaultDisplayConfig = new BitmapDisplayConfig();
    public int diskCacheSize;
    public Displayer displayer;
    public Downloader downloader;
    public int memCacheSize;
    public float memCacheSizePercent;
    public int poolSize = 3;
    public boolean recycleImmediately = true;

    public FinalBitmapConfig(Context arg2)
    {
      this.defaultDisplayConfig.setAnimation(null);
      this.defaultDisplayConfig.setAnimationType(1);
      Object localObject;
      int i = (int)Math.floor(localObject.getResources().getDisplayMetrics().widthPixels / 2);
      this.defaultDisplayConfig.setBitmapHeight(i);
      this.defaultDisplayConfig.setBitmapWidth(i);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     net.tsz.afinal.FinalBitmap
 * JD-Core Version:    0.6.2
 */