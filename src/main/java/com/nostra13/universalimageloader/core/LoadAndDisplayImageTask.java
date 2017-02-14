package com.nostra13.universalimageloader.core;

import android.graphics.Bitmap;
import android.os.Handler;
import com.nostra13.universalimageloader.cache.disc.DiskCache;
import com.nostra13.universalimageloader.cache.memory.MemoryCache;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.FailReason.FailType;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.core.decode.ImageDecoder;
import com.nostra13.universalimageloader.core.decode.ImageDecodingInfo;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.process.BitmapProcessor;
import com.nostra13.universalimageloader.utils.IoUtils.CopyListener;
import com.nostra13.universalimageloader.utils.L;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

final class LoadAndDisplayImageTask
  implements Runnable, IoUtils.CopyListener
{
  private static final String ERROR_POST_PROCESSOR_NULL = "Post-processor returned null [%s]";
  private static final String ERROR_PRE_PROCESSOR_NULL = "Pre-processor returned null [%s]";
  private static final String ERROR_PROCESSOR_FOR_DISK_CACHE_NULL = "Bitmap processor for disk cache returned null [%s]";
  private static final String LOG_CACHE_IMAGE_IN_MEMORY = "Cache image in memory [%s]";
  private static final String LOG_CACHE_IMAGE_ON_DISK = "Cache image on disk [%s]";
  private static final String LOG_DELAY_BEFORE_LOADING = "Delay %d ms before loading...  [%s]";
  private static final String LOG_GET_IMAGE_FROM_MEMORY_CACHE_AFTER_WAITING = "...Get cached bitmap from memory after waiting. [%s]";
  private static final String LOG_LOAD_IMAGE_FROM_DISK_CACHE = "Load image from disk cache [%s]";
  private static final String LOG_LOAD_IMAGE_FROM_NETWORK = "Load image from network [%s]";
  private static final String LOG_POSTPROCESS_IMAGE = "PostProcess image before displaying [%s]";
  private static final String LOG_PREPROCESS_IMAGE = "PreProcess image before caching in memory [%s]";
  private static final String LOG_PROCESS_IMAGE_BEFORE_CACHE_ON_DISK = "Process image before cache on disk [%s]";
  private static final String LOG_RESIZE_CACHED_IMAGE_FILE = "Resize image in disk cache [%s]";
  private static final String LOG_RESUME_AFTER_PAUSE = ".. Resume loading [%s]";
  private static final String LOG_START_DISPLAY_IMAGE_TASK = "Start display image task [%s]";
  private static final String LOG_TASK_CANCELLED_IMAGEAWARE_COLLECTED = "ImageAware was collected by GC. Task is cancelled. [%s]";
  private static final String LOG_TASK_CANCELLED_IMAGEAWARE_REUSED = "ImageAware is reused for another image. Task is cancelled. [%s]";
  private static final String LOG_TASK_INTERRUPTED = "Task was interrupted [%s]";
  private static final String LOG_WAITING_FOR_IMAGE_LOADED = "Image already is loading. Waiting... [%s]";
  private static final String LOG_WAITING_FOR_RESUME = "ImageLoader is paused. Waiting...  [%s]";
  private final ImageLoaderConfiguration configuration;
  private final ImageDecoder decoder;
  private final ImageDownloader downloader;
  private final ImageLoaderEngine engine;
  private final Handler handler;
  final ImageAware imageAware;
  private final ImageLoadingInfo imageLoadingInfo;
  final ImageLoadingListener listener;
  private LoadedFrom loadedFrom = LoadedFrom.NETWORK;
  private final String memoryCacheKey;
  private final ImageDownloader networkDeniedDownloader;
  final DisplayImageOptions options;
  final ImageLoadingProgressListener progressListener;
  private final ImageDownloader slowNetworkDownloader;
  private final boolean syncLoading;
  private final ImageSize targetSize;
  final String uri;

  public LoadAndDisplayImageTask(ImageLoaderEngine paramImageLoaderEngine, ImageLoadingInfo paramImageLoadingInfo, Handler paramHandler)
  {
    this.engine = paramImageLoaderEngine;
    this.imageLoadingInfo = paramImageLoadingInfo;
    this.handler = paramHandler;
    this.configuration = paramImageLoaderEngine.configuration;
    this.downloader = this.configuration.downloader;
    this.networkDeniedDownloader = this.configuration.networkDeniedDownloader;
    this.slowNetworkDownloader = this.configuration.slowNetworkDownloader;
    this.decoder = this.configuration.decoder;
    this.uri = paramImageLoadingInfo.uri;
    this.memoryCacheKey = paramImageLoadingInfo.memoryCacheKey;
    this.imageAware = paramImageLoadingInfo.imageAware;
    this.targetSize = paramImageLoadingInfo.targetSize;
    this.options = paramImageLoadingInfo.options;
    this.listener = paramImageLoadingInfo.listener;
    this.progressListener = paramImageLoadingInfo.progressListener;
    this.syncLoading = this.options.isSyncLoading();
  }

  private void checkTaskInterrupted()
    throws LoadAndDisplayImageTask.TaskCancelledException
  {
    if (isTaskInterrupted())
      throw new TaskCancelledException();
  }

  private void checkTaskNotActual()
    throws LoadAndDisplayImageTask.TaskCancelledException
  {
    checkViewCollected();
    checkViewReused();
  }

  private void checkViewCollected()
    throws LoadAndDisplayImageTask.TaskCancelledException
  {
    if (isViewCollected())
      throw new TaskCancelledException();
  }

  private void checkViewReused()
    throws LoadAndDisplayImageTask.TaskCancelledException
  {
    if (isViewReused())
      throw new TaskCancelledException();
  }

  private Bitmap decodeImage(String paramString)
    throws IOException
  {
    ViewScaleType localViewScaleType = this.imageAware.getScaleType();
    paramString = new ImageDecodingInfo(this.memoryCacheKey, paramString, this.uri, this.targetSize, localViewScaleType, getDownloader(), this.options);
    return this.decoder.decode(paramString);
  }

  private boolean delayIfNeed()
  {
    if (this.options.shouldDelayBeforeLoading())
    {
      L.d("Delay %d ms before loading...  [%s]", new Object[] { Integer.valueOf(this.options.getDelayBeforeLoading()), this.memoryCacheKey });
      try
      {
        Thread.sleep(this.options.getDelayBeforeLoading());
        return isTaskNotActual();
      }
      catch (InterruptedException localInterruptedException)
      {
        L.e("Task was interrupted [%s]", new Object[] { this.memoryCacheKey });
        return true;
      }
    }
    return false;
  }

  private boolean downloadImage()
    throws IOException
  {
    InputStream localInputStream = getDownloader().getStream(this.uri, this.options.getExtraForDownloader());
    return this.configuration.diskCache.save(this.uri, localInputStream, this);
  }

  private void fireCancelEvent()
  {
    if ((this.syncLoading) || (isTaskInterrupted()))
      return;
    runTask(new Runnable()
    {
      public void run()
      {
        LoadAndDisplayImageTask.this.listener.onLoadingCancelled(LoadAndDisplayImageTask.this.uri, LoadAndDisplayImageTask.this.imageAware.getWrappedView());
      }
    }
    , false, this.handler, this.engine);
  }

  private void fireFailEvent(final FailReason.FailType paramFailType, final Throwable paramThrowable)
  {
    if ((this.syncLoading) || (isTaskInterrupted()) || (isTaskNotActual()))
      return;
    runTask(new Runnable()
    {
      public void run()
      {
        if (LoadAndDisplayImageTask.this.options.shouldShowImageOnFail())
          LoadAndDisplayImageTask.this.imageAware.setImageDrawable(LoadAndDisplayImageTask.this.options.getImageOnFail(LoadAndDisplayImageTask.this.configuration.resources));
        LoadAndDisplayImageTask.this.listener.onLoadingFailed(LoadAndDisplayImageTask.this.uri, LoadAndDisplayImageTask.this.imageAware.getWrappedView(), new FailReason(paramFailType, paramThrowable));
      }
    }
    , false, this.handler, this.engine);
  }

  private boolean fireProgressEvent(final int paramInt1, final int paramInt2)
  {
    if ((isTaskInterrupted()) || (isTaskNotActual()))
      return false;
    if (this.progressListener != null)
      runTask(new Runnable()
      {
        public void run()
        {
          LoadAndDisplayImageTask.this.progressListener.onProgressUpdate(LoadAndDisplayImageTask.this.uri, LoadAndDisplayImageTask.this.imageAware.getWrappedView(), paramInt1, paramInt2);
        }
      }
      , false, this.handler, this.engine);
    return true;
  }

  private ImageDownloader getDownloader()
  {
    if (this.engine.isNetworkDenied())
      return this.networkDeniedDownloader;
    if (this.engine.isSlowNetwork())
      return this.slowNetworkDownloader;
    return this.downloader;
  }

  private boolean isTaskInterrupted()
  {
    if (Thread.interrupted())
    {
      L.d("Task was interrupted [%s]", new Object[] { this.memoryCacheKey });
      return true;
    }
    return false;
  }

  private boolean isTaskNotActual()
  {
    return (isViewCollected()) || (isViewReused());
  }

  private boolean isViewCollected()
  {
    if (this.imageAware.isCollected())
    {
      L.d("ImageAware was collected by GC. Task is cancelled. [%s]", new Object[] { this.memoryCacheKey });
      return true;
    }
    return false;
  }

  private boolean isViewReused()
  {
    String str = this.engine.getLoadingUriForView(this.imageAware);
    if (!this.memoryCacheKey.equals(str));
    for (int i = 1; i != 0; i = 0)
    {
      L.d("ImageAware is reused for another image. Task is cancelled. [%s]", new Object[] { this.memoryCacheKey });
      return true;
    }
    return false;
  }

  private boolean resizeAndSaveImage(int paramInt1, int paramInt2)
    throws IOException
  {
    boolean bool2 = false;
    Object localObject1 = this.configuration.diskCache.get(this.uri);
    boolean bool1 = bool2;
    if (localObject1 != null)
    {
      bool1 = bool2;
      if (((File)localObject1).exists())
      {
        Object localObject2 = new ImageSize(paramInt1, paramInt2);
        DisplayImageOptions localDisplayImageOptions = new DisplayImageOptions.Builder().cloneFrom(this.options).imageScaleType(ImageScaleType.IN_SAMPLE_INT).build();
        localObject1 = new ImageDecodingInfo(this.memoryCacheKey, ImageDownloader.Scheme.FILE.wrap(((File)localObject1).getAbsolutePath()), this.uri, (ImageSize)localObject2, ViewScaleType.FIT_INSIDE, getDownloader(), localDisplayImageOptions);
        localObject2 = this.decoder.decode((ImageDecodingInfo)localObject1);
        localObject1 = localObject2;
        if (localObject2 != null)
        {
          localObject1 = localObject2;
          if (this.configuration.processorForDiskCache != null)
          {
            L.d("Process image before cache on disk [%s]", new Object[] { this.memoryCacheKey });
            localObject2 = this.configuration.processorForDiskCache.process((Bitmap)localObject2);
            localObject1 = localObject2;
            if (localObject2 == null)
            {
              L.e("Bitmap processor for disk cache returned null [%s]", new Object[] { this.memoryCacheKey });
              localObject1 = localObject2;
            }
          }
        }
        bool1 = bool2;
        if (localObject1 != null)
        {
          bool1 = this.configuration.diskCache.save(this.uri, (Bitmap)localObject1);
          ((Bitmap)localObject1).recycle();
        }
      }
    }
    return bool1;
  }

  static void runTask(Runnable paramRunnable, boolean paramBoolean, Handler paramHandler, ImageLoaderEngine paramImageLoaderEngine)
  {
    if (paramBoolean)
    {
      paramRunnable.run();
      return;
    }
    if (paramHandler == null)
    {
      paramImageLoaderEngine.fireCallback(paramRunnable);
      return;
    }
    paramHandler.post(paramRunnable);
  }

  private boolean tryCacheImageOnDisk()
    throws LoadAndDisplayImageTask.TaskCancelledException
  {
    L.d("Cache image on disk [%s]", new Object[] { this.memoryCacheKey });
    try
    {
      boolean bool = downloadImage();
      if (bool)
      {
        int i = this.configuration.maxImageWidthForDiskCache;
        int j = this.configuration.maxImageHeightForDiskCache;
        if ((i > 0) || (j > 0))
        {
          L.d("Resize image in disk cache [%s]", new Object[] { this.memoryCacheKey });
          resizeAndSaveImage(i, j);
        }
      }
      return bool;
    }
    catch (IOException localIOException)
    {
      L.e(localIOException);
    }
    return false;
  }

  private Bitmap tryLoadBitmap()
    throws LoadAndDisplayImageTask.TaskCancelledException
  {
    String str = null;
    File localFile1 = null;
    Object localObject7 = null;
    Object localObject8 = null;
    Object localObject6 = null;
    Object localObject2 = str;
    Object localObject3 = localFile1;
    Object localObject4 = localObject7;
    Object localObject5 = localObject8;
    try
    {
      File localFile2 = this.configuration.diskCache.get(this.uri);
      Object localObject1 = localObject6;
      if (localFile2 != null)
      {
        localObject1 = localObject6;
        localObject2 = str;
        localObject3 = localFile1;
        localObject4 = localObject7;
        localObject5 = localObject8;
        if (localFile2.exists())
        {
          localObject2 = str;
          localObject3 = localFile1;
          localObject4 = localObject7;
          localObject5 = localObject8;
          L.d("Load image from disk cache [%s]", new Object[] { this.memoryCacheKey });
          localObject2 = str;
          localObject3 = localFile1;
          localObject4 = localObject7;
          localObject5 = localObject8;
          this.loadedFrom = LoadedFrom.DISC_CACHE;
          localObject2 = str;
          localObject3 = localFile1;
          localObject4 = localObject7;
          localObject5 = localObject8;
          checkTaskNotActual();
          localObject2 = str;
          localObject3 = localFile1;
          localObject4 = localObject7;
          localObject5 = localObject8;
          localObject1 = decodeImage(ImageDownloader.Scheme.FILE.wrap(localFile2.getAbsolutePath()));
        }
      }
      if (localObject1 != null)
      {
        localObject2 = localObject1;
        localObject3 = localObject1;
        localObject4 = localObject1;
        localObject5 = localObject1;
        if (((Bitmap)localObject1).getWidth() > 0)
        {
          localObject6 = localObject1;
          localObject2 = localObject1;
          localObject3 = localObject1;
          localObject4 = localObject1;
          localObject5 = localObject1;
          if (((Bitmap)localObject1).getHeight() > 0)
            break label477;
        }
      }
      localObject2 = localObject1;
      localObject3 = localObject1;
      localObject4 = localObject1;
      localObject5 = localObject1;
      L.d("Load image from network [%s]", new Object[] { this.memoryCacheKey });
      localObject2 = localObject1;
      localObject3 = localObject1;
      localObject4 = localObject1;
      localObject5 = localObject1;
      this.loadedFrom = LoadedFrom.NETWORK;
      localObject2 = localObject1;
      localObject3 = localObject1;
      localObject4 = localObject1;
      localObject5 = localObject1;
      str = this.uri;
      localObject6 = str;
      localObject2 = localObject1;
      localObject3 = localObject1;
      localObject4 = localObject1;
      localObject5 = localObject1;
      if (this.options.isCacheOnDisk())
      {
        localObject6 = str;
        localObject2 = localObject1;
        localObject3 = localObject1;
        localObject4 = localObject1;
        localObject5 = localObject1;
        if (tryCacheImageOnDisk())
        {
          localObject2 = localObject1;
          localObject3 = localObject1;
          localObject4 = localObject1;
          localObject5 = localObject1;
          localFile1 = this.configuration.diskCache.get(this.uri);
          localObject6 = str;
          if (localFile1 != null)
          {
            localObject2 = localObject1;
            localObject3 = localObject1;
            localObject4 = localObject1;
            localObject5 = localObject1;
            localObject6 = ImageDownloader.Scheme.FILE.wrap(localFile1.getAbsolutePath());
          }
        }
      }
      localObject2 = localObject1;
      localObject3 = localObject1;
      localObject4 = localObject1;
      localObject5 = localObject1;
      checkTaskNotActual();
      localObject2 = localObject1;
      localObject3 = localObject1;
      localObject4 = localObject1;
      localObject5 = localObject1;
      localObject1 = decodeImage((String)localObject6);
      if (localObject1 != null)
      {
        localObject2 = localObject1;
        localObject3 = localObject1;
        localObject4 = localObject1;
        localObject5 = localObject1;
        if (((Bitmap)localObject1).getWidth() > 0)
        {
          localObject6 = localObject1;
          localObject2 = localObject1;
          localObject3 = localObject1;
          localObject4 = localObject1;
          localObject5 = localObject1;
          if (((Bitmap)localObject1).getHeight() > 0)
            break label477;
        }
      }
      localObject2 = localObject1;
      localObject3 = localObject1;
      localObject4 = localObject1;
      localObject5 = localObject1;
      fireFailEvent(FailReason.FailType.DECODING_ERROR, null);
      localObject6 = localObject1;
      label477: return localObject6;
    }
    catch (IllegalStateException localIllegalStateException)
    {
      fireFailEvent(FailReason.FailType.NETWORK_DENIED, null);
      return localObject2;
    }
    catch (TaskCancelledException localTaskCancelledException)
    {
      throw localTaskCancelledException;
    }
    catch (IOException localIOException)
    {
      L.e(localIOException);
      fireFailEvent(FailReason.FailType.IO_ERROR, localIOException);
      return localObject3;
    }
    catch (OutOfMemoryError localOutOfMemoryError)
    {
      L.e(localOutOfMemoryError);
      fireFailEvent(FailReason.FailType.OUT_OF_MEMORY, localOutOfMemoryError);
      return localObject4;
    }
    catch (Throwable localThrowable)
    {
      L.e(localThrowable);
      fireFailEvent(FailReason.FailType.UNKNOWN, localThrowable);
    }
    return localObject5;
  }

  private boolean waitIfPaused()
  {
    AtomicBoolean localAtomicBoolean = this.engine.getPause();
    if (localAtomicBoolean.get());
    synchronized (this.engine.getPauseLock())
    {
      if (localAtomicBoolean.get())
        L.d("ImageLoader is paused. Waiting...  [%s]", new Object[] { this.memoryCacheKey });
      try
      {
        this.engine.getPauseLock().wait();
        L.d(".. Resume loading [%s]", new Object[] { this.memoryCacheKey });
        return isTaskNotActual();
      }
      catch (InterruptedException localInterruptedException)
      {
        L.e("Task was interrupted [%s]", new Object[] { this.memoryCacheKey });
        return true;
      }
    }
  }

  String getLoadingUri()
  {
    return this.uri;
  }

  public boolean onBytesCopied(int paramInt1, int paramInt2)
  {
    return (this.syncLoading) || (fireProgressEvent(paramInt1, paramInt2));
  }

  public void run()
  {
    if (waitIfPaused());
    while (delayIfNeed())
      return;
    ReentrantLock localReentrantLock = this.imageLoadingInfo.loadFromUriLock;
    L.d("Start display image task [%s]", new Object[] { this.memoryCacheKey });
    if (localReentrantLock.isLocked())
      L.d("Image already is loading. Waiting... [%s]", new Object[] { this.memoryCacheKey });
    localReentrantLock.lock();
    try
    {
      checkTaskNotActual();
      Object localObject3 = (Bitmap)this.configuration.memoryCache.get(this.memoryCacheKey);
      Object localObject1;
      if ((localObject3 == null) || (((Bitmap)localObject3).isRecycled()))
      {
        localObject3 = tryLoadBitmap();
        if (localObject3 == null)
          return;
        checkTaskNotActual();
        checkTaskInterrupted();
        localObject1 = localObject3;
        if (this.options.shouldPreProcess())
        {
          L.d("PreProcess image before caching in memory [%s]", new Object[] { this.memoryCacheKey });
          localObject3 = this.options.getPreProcessor().process((Bitmap)localObject3);
          localObject1 = localObject3;
          if (localObject3 == null)
          {
            L.e("Pre-processor returned null [%s]", new Object[] { this.memoryCacheKey });
            localObject1 = localObject3;
          }
        }
        localObject3 = localObject1;
        if (localObject1 != null)
        {
          localObject3 = localObject1;
          if (this.options.isCacheInMemory())
          {
            L.d("Cache image in memory [%s]", new Object[] { this.memoryCacheKey });
            this.configuration.memoryCache.put(this.memoryCacheKey, localObject1);
            localObject3 = localObject1;
          }
        }
      }
      while (true)
      {
        localObject1 = localObject3;
        if (localObject3 != null)
        {
          localObject1 = localObject3;
          if (this.options.shouldPostProcess())
          {
            L.d("PostProcess image before displaying [%s]", new Object[] { this.memoryCacheKey });
            localObject3 = this.options.getPostProcessor().process((Bitmap)localObject3);
            localObject1 = localObject3;
            if (localObject3 == null)
            {
              L.e("Post-processor returned null [%s]", new Object[] { this.memoryCacheKey });
              localObject1 = localObject3;
            }
          }
        }
        checkTaskNotActual();
        checkTaskInterrupted();
        localReentrantLock.unlock();
        runTask(new DisplayBitmapTask(localObject1, this.imageLoadingInfo, this.engine, this.loadedFrom), this.syncLoading, this.handler, this.engine);
        return;
        this.loadedFrom = LoadedFrom.MEMORY_CACHE;
        L.d("...Get cached bitmap from memory after waiting. [%s]", new Object[] { this.memoryCacheKey });
      }
    }
    catch (TaskCancelledException localTaskCancelledException)
    {
      fireCancelEvent();
      return;
    }
    finally
    {
      localReentrantLock.unlock();
    }
  }

  class TaskCancelledException extends Exception
  {
    TaskCancelledException()
    {
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.nostra13.universalimageloader.core.LoadAndDisplayImageTask
 * JD-Core Version:    0.6.2
 */