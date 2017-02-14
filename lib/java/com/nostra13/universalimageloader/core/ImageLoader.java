package com.nostra13.universalimageloader.core;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.nostra13.universalimageloader.cache.disc.DiskCache;
import com.nostra13.universalimageloader.cache.memory.MemoryCache;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;
import com.nostra13.universalimageloader.core.imageaware.NonViewAware;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.utils.ImageSizeUtils;
import com.nostra13.universalimageloader.utils.L;
import com.nostra13.universalimageloader.utils.MemoryCacheUtils;

public class ImageLoader
{
  private static final String ERROR_INIT_CONFIG_WITH_NULL = "ImageLoader configuration can not be initialized with null";
  private static final String ERROR_NOT_INIT = "ImageLoader must be init with configuration before using";
  private static final String ERROR_WRONG_ARGUMENTS = "Wrong arguments were passed to displayImage() method (ImageView reference must not be null)";
  static final String LOG_DESTROY = "Destroy ImageLoader";
  static final String LOG_INIT_CONFIG = "Initialize ImageLoader with configuration";
  static final String LOG_LOAD_IMAGE_FROM_MEMORY_CACHE = "Load image from memory cache [%s]";
  public static final String TAG = ImageLoader.class.getSimpleName();
  private static final String WARNING_RE_INIT_CONFIG = "Try to initialize ImageLoader which had already been initialized before. To re-init ImageLoader with new configuration call ImageLoader.destroy() at first.";
  private static volatile ImageLoader instance;
  private ImageLoaderConfiguration configuration;
  private final ImageLoadingListener emptyListener = new SimpleImageLoadingListener();
  private ImageLoaderEngine engine;

  private void checkConfiguration()
  {
    if (this.configuration == null)
      throw new IllegalStateException("ImageLoader must be init with configuration before using");
  }

  private static Handler defineHandler(DisplayImageOptions paramDisplayImageOptions)
  {
    Handler localHandler = paramDisplayImageOptions.getHandler();
    if (paramDisplayImageOptions.isSyncLoading())
      paramDisplayImageOptions = null;
    do
    {
      do
      {
        return paramDisplayImageOptions;
        paramDisplayImageOptions = localHandler;
      }
      while (localHandler != null);
      paramDisplayImageOptions = localHandler;
    }
    while (Looper.myLooper() != Looper.getMainLooper());
    return new Handler();
  }

  public static ImageLoader getInstance()
  {
    if (instance == null);
    try
    {
      if (instance == null)
        instance = new ImageLoader();
      return instance;
    }
    finally
    {
    }
  }

  public void cancelDisplayTask(ImageView paramImageView)
  {
    this.engine.cancelDisplayTaskFor(new ImageViewAware(paramImageView));
  }

  public void cancelDisplayTask(ImageAware paramImageAware)
  {
    this.engine.cancelDisplayTaskFor(paramImageAware);
  }

  @Deprecated
  public void clearDiscCache()
  {
    clearDiskCache();
  }

  public void clearDiskCache()
  {
    checkConfiguration();
    this.configuration.diskCache.clear();
  }

  public void clearMemoryCache()
  {
    checkConfiguration();
    this.configuration.memoryCache.clear();
  }

  public void denyNetworkDownloads(boolean paramBoolean)
  {
    this.engine.denyNetworkDownloads(paramBoolean);
  }

  public void destroy()
  {
    if (this.configuration != null)
      L.d("Destroy ImageLoader", new Object[0]);
    stop();
    this.configuration.diskCache.close();
    this.engine = null;
    this.configuration = null;
  }

  public void displayImage(String paramString, ImageView paramImageView)
  {
    displayImage(paramString, new ImageViewAware(paramImageView), null, null, null);
  }

  public void displayImage(String paramString, ImageView paramImageView, DisplayImageOptions paramDisplayImageOptions)
  {
    displayImage(paramString, new ImageViewAware(paramImageView), paramDisplayImageOptions, null, null);
  }

  public void displayImage(String paramString, ImageView paramImageView, DisplayImageOptions paramDisplayImageOptions, ImageLoadingListener paramImageLoadingListener)
  {
    displayImage(paramString, paramImageView, paramDisplayImageOptions, paramImageLoadingListener, null);
  }

  public void displayImage(String paramString, ImageView paramImageView, DisplayImageOptions paramDisplayImageOptions, ImageLoadingListener paramImageLoadingListener, ImageLoadingProgressListener paramImageLoadingProgressListener)
  {
    displayImage(paramString, new ImageViewAware(paramImageView), paramDisplayImageOptions, paramImageLoadingListener, paramImageLoadingProgressListener);
  }

  public void displayImage(String paramString, ImageView paramImageView, ImageLoadingListener paramImageLoadingListener)
  {
    displayImage(paramString, new ImageViewAware(paramImageView), null, paramImageLoadingListener, null);
  }

  public void displayImage(String paramString, ImageAware paramImageAware)
  {
    displayImage(paramString, paramImageAware, null, null, null);
  }

  public void displayImage(String paramString, ImageAware paramImageAware, DisplayImageOptions paramDisplayImageOptions)
  {
    displayImage(paramString, paramImageAware, paramDisplayImageOptions, null, null);
  }

  public void displayImage(String paramString, ImageAware paramImageAware, DisplayImageOptions paramDisplayImageOptions, ImageLoadingListener paramImageLoadingListener)
  {
    displayImage(paramString, paramImageAware, paramDisplayImageOptions, paramImageLoadingListener, null);
  }

  public void displayImage(String paramString, ImageAware paramImageAware, DisplayImageOptions paramDisplayImageOptions, ImageLoadingListener paramImageLoadingListener, ImageLoadingProgressListener paramImageLoadingProgressListener)
  {
    checkConfiguration();
    if (paramImageAware == null)
      throw new IllegalArgumentException("Wrong arguments were passed to displayImage() method (ImageView reference must not be null)");
    ImageLoadingListener localImageLoadingListener = paramImageLoadingListener;
    if (paramImageLoadingListener == null)
      localImageLoadingListener = this.emptyListener;
    paramImageLoadingListener = paramDisplayImageOptions;
    if (paramDisplayImageOptions == null)
      paramImageLoadingListener = this.configuration.defaultDisplayImageOptions;
    if (TextUtils.isEmpty(paramString))
    {
      this.engine.cancelDisplayTaskFor(paramImageAware);
      localImageLoadingListener.onLoadingStarted(paramString, paramImageAware.getWrappedView());
      if (paramImageLoadingListener.shouldShowImageForEmptyUri())
        paramImageAware.setImageDrawable(paramImageLoadingListener.getImageForEmptyUri(this.configuration.resources));
      while (true)
      {
        localImageLoadingListener.onLoadingComplete(paramString, paramImageAware.getWrappedView(), null);
        return;
        paramImageAware.setImageDrawable(null);
      }
    }
    ImageSize localImageSize = ImageSizeUtils.defineTargetSizeForView(paramImageAware, this.configuration.getMaxImageSize());
    String str = MemoryCacheUtils.generateKey(paramString, localImageSize);
    this.engine.prepareDisplayTaskFor(paramImageAware, str);
    localImageLoadingListener.onLoadingStarted(paramString, paramImageAware.getWrappedView());
    paramDisplayImageOptions = (Bitmap)this.configuration.memoryCache.get(str);
    if ((paramDisplayImageOptions != null) && (!paramDisplayImageOptions.isRecycled()))
    {
      L.d("Load image from memory cache [%s]", new Object[] { str });
      if (paramImageLoadingListener.shouldPostProcess())
      {
        paramString = new ImageLoadingInfo(paramString, paramImageAware, localImageSize, str, paramImageLoadingListener, localImageLoadingListener, paramImageLoadingProgressListener, this.engine.getLockForUri(paramString));
        paramString = new ProcessAndDisplayImageTask(this.engine, paramDisplayImageOptions, paramString, defineHandler(paramImageLoadingListener));
        if (paramImageLoadingListener.isSyncLoading())
        {
          paramString.run();
          return;
        }
        this.engine.submit(paramString);
        return;
      }
      paramImageLoadingListener.getDisplayer().display(paramDisplayImageOptions, paramImageAware, LoadedFrom.MEMORY_CACHE);
      localImageLoadingListener.onLoadingComplete(paramString, paramImageAware.getWrappedView(), paramDisplayImageOptions);
      return;
    }
    if (paramImageLoadingListener.shouldShowImageOnLoading())
      paramImageAware.setImageDrawable(paramImageLoadingListener.getImageOnLoading(this.configuration.resources));
    while (true)
    {
      paramString = new ImageLoadingInfo(paramString, paramImageAware, localImageSize, str, paramImageLoadingListener, localImageLoadingListener, paramImageLoadingProgressListener, this.engine.getLockForUri(paramString));
      paramString = new LoadAndDisplayImageTask(this.engine, paramString, defineHandler(paramImageLoadingListener));
      if (!paramImageLoadingListener.isSyncLoading())
        break;
      paramString.run();
      return;
      if (paramImageLoadingListener.isResetViewBeforeLoading())
        paramImageAware.setImageDrawable(null);
    }
    this.engine.submit(paramString);
  }

  public void displayImage(String paramString, ImageAware paramImageAware, ImageLoadingListener paramImageLoadingListener)
  {
    displayImage(paramString, paramImageAware, null, paramImageLoadingListener, null);
  }

  @Deprecated
  public DiskCache getDiscCache()
  {
    return getDiskCache();
  }

  public DiskCache getDiskCache()
  {
    checkConfiguration();
    return this.configuration.diskCache;
  }

  public String getLoadingUriForView(ImageView paramImageView)
  {
    return this.engine.getLoadingUriForView(new ImageViewAware(paramImageView));
  }

  public String getLoadingUriForView(ImageAware paramImageAware)
  {
    return this.engine.getLoadingUriForView(paramImageAware);
  }

  public MemoryCache getMemoryCache()
  {
    checkConfiguration();
    return this.configuration.memoryCache;
  }

  public void handleSlowNetwork(boolean paramBoolean)
  {
    this.engine.handleSlowNetwork(paramBoolean);
  }

  public void init(ImageLoaderConfiguration paramImageLoaderConfiguration)
  {
    if (paramImageLoaderConfiguration == null)
      try
      {
        throw new IllegalArgumentException("ImageLoader configuration can not be initialized with null");
      }
      finally
      {
      }
    if (this.configuration == null)
    {
      L.d("Initialize ImageLoader with configuration", new Object[0]);
      this.engine = new ImageLoaderEngine(paramImageLoaderConfiguration);
      this.configuration = paramImageLoaderConfiguration;
    }
    while (true)
    {
      return;
      L.w("Try to initialize ImageLoader which had already been initialized before. To re-init ImageLoader with new configuration call ImageLoader.destroy() at first.", new Object[0]);
    }
  }

  public boolean isInited()
  {
    return this.configuration != null;
  }

  public void loadImage(String paramString, DisplayImageOptions paramDisplayImageOptions, ImageLoadingListener paramImageLoadingListener)
  {
    loadImage(paramString, null, paramDisplayImageOptions, paramImageLoadingListener, null);
  }

  public void loadImage(String paramString, ImageSize paramImageSize, DisplayImageOptions paramDisplayImageOptions, ImageLoadingListener paramImageLoadingListener)
  {
    loadImage(paramString, paramImageSize, paramDisplayImageOptions, paramImageLoadingListener, null);
  }

  public void loadImage(String paramString, ImageSize paramImageSize, DisplayImageOptions paramDisplayImageOptions, ImageLoadingListener paramImageLoadingListener, ImageLoadingProgressListener paramImageLoadingProgressListener)
  {
    checkConfiguration();
    ImageSize localImageSize = paramImageSize;
    if (paramImageSize == null)
      localImageSize = this.configuration.getMaxImageSize();
    paramImageSize = paramDisplayImageOptions;
    if (paramDisplayImageOptions == null)
      paramImageSize = this.configuration.defaultDisplayImageOptions;
    displayImage(paramString, new NonViewAware(paramString, localImageSize, ViewScaleType.CROP), paramImageSize, paramImageLoadingListener, paramImageLoadingProgressListener);
  }

  public void loadImage(String paramString, ImageSize paramImageSize, ImageLoadingListener paramImageLoadingListener)
  {
    loadImage(paramString, paramImageSize, null, paramImageLoadingListener, null);
  }

  public void loadImage(String paramString, ImageLoadingListener paramImageLoadingListener)
  {
    loadImage(paramString, null, null, paramImageLoadingListener, null);
  }

  public Bitmap loadImageSync(String paramString)
  {
    return loadImageSync(paramString, null, null);
  }

  public Bitmap loadImageSync(String paramString, DisplayImageOptions paramDisplayImageOptions)
  {
    return loadImageSync(paramString, null, paramDisplayImageOptions);
  }

  public Bitmap loadImageSync(String paramString, ImageSize paramImageSize)
  {
    return loadImageSync(paramString, paramImageSize, null);
  }

  public Bitmap loadImageSync(String paramString, ImageSize paramImageSize, DisplayImageOptions paramDisplayImageOptions)
  {
    Object localObject = paramDisplayImageOptions;
    if (paramDisplayImageOptions == null)
      localObject = this.configuration.defaultDisplayImageOptions;
    paramDisplayImageOptions = new DisplayImageOptions.Builder().cloneFrom((DisplayImageOptions)localObject).syncLoading(true).build();
    localObject = new SyncImageLoadingListener(null);
    loadImage(paramString, paramImageSize, paramDisplayImageOptions, (ImageLoadingListener)localObject);
    return ((SyncImageLoadingListener)localObject).getLoadedBitmap();
  }

  public void pause()
  {
    this.engine.pause();
  }

  public void resume()
  {
    this.engine.resume();
  }

  public void stop()
  {
    this.engine.stop();
  }

  private static class SyncImageLoadingListener extends SimpleImageLoadingListener
  {
    private Bitmap loadedImage;

    public Bitmap getLoadedBitmap()
    {
      return this.loadedImage;
    }

    public void onLoadingComplete(String paramString, View paramView, Bitmap paramBitmap)
    {
      this.loadedImage = paramBitmap;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.nostra13.universalimageloader.core.ImageLoader
 * JD-Core Version:    0.6.2
 */