package com.example.android.bitmapfun.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.LruCache;
import android.util.Log;
import java.io.File;

public class ImageCache
{
  private static final boolean DEFAULT_CLEAR_DISK_CACHE_ON_START = false;
  private static final Bitmap.CompressFormat DEFAULT_COMPRESS_FORMAT = Bitmap.CompressFormat.JPEG;
  private static final int DEFAULT_COMPRESS_QUALITY = 70;
  private static final boolean DEFAULT_DISK_CACHE_ENABLED = true;
  private static final int DEFAULT_DISK_CACHE_SIZE = 10485760;
  private static final boolean DEFAULT_MEM_CACHE_ENABLED = true;
  private static final int DEFAULT_MEM_CACHE_SIZE = 5242880;
  private static final String TAG = "ImageCache";
  private DiskLruCache mDiskCache;
  private LruCache<String, Bitmap> mMemoryCache;

  public ImageCache(Context paramContext, ImageCacheParams paramImageCacheParams)
  {
    init(paramContext, paramImageCacheParams);
  }

  public ImageCache(Context paramContext, String paramString)
  {
    init(paramContext, new ImageCacheParams(paramString));
  }

  public static ImageCache findOrCreateCache(FragmentActivity paramFragmentActivity, ImageCacheParams paramImageCacheParams)
  {
    RetainFragment localRetainFragment = RetainFragment.findOrCreateRetainFragment(paramFragmentActivity.getSupportFragmentManager());
    ImageCache localImageCache2 = (ImageCache)localRetainFragment.getObject();
    ImageCache localImageCache1 = localImageCache2;
    if (localImageCache2 == null)
    {
      localImageCache1 = new ImageCache(paramFragmentActivity, paramImageCacheParams);
      localRetainFragment.setObject(localImageCache1);
    }
    return localImageCache1;
  }

  public static ImageCache findOrCreateCache(FragmentActivity paramFragmentActivity, String paramString)
  {
    return findOrCreateCache(paramFragmentActivity, new ImageCacheParams(paramString));
  }

  private void init(Context paramContext, ImageCacheParams paramImageCacheParams)
  {
    File localFile = DiskLruCache.getDiskCacheDir(paramContext, paramImageCacheParams.uniqueName);
    if (paramImageCacheParams.diskCacheEnabled)
    {
      this.mDiskCache = DiskLruCache.openCache(paramContext, localFile, paramImageCacheParams.diskCacheSize);
      this.mDiskCache.setCompressParams(paramImageCacheParams.compressFormat, paramImageCacheParams.compressQuality);
      if (paramImageCacheParams.clearDiskCacheOnStart)
        this.mDiskCache.clearCache();
    }
    if (paramImageCacheParams.memoryCacheEnabled)
      this.mMemoryCache = new LruCache(paramImageCacheParams.memCacheSize)
      {
        protected int sizeOf(String paramAnonymousString, Bitmap paramAnonymousBitmap)
        {
          return Utils.getBitmapSize(paramAnonymousBitmap);
        }
      };
  }

  public void addBitmapToCache(String paramString, Bitmap paramBitmap)
  {
    if ((paramString == null) || (paramBitmap == null));
    do
    {
      return;
      if ((this.mMemoryCache != null) && (this.mMemoryCache.get(paramString) == null))
        this.mMemoryCache.put(paramString, paramBitmap);
    }
    while ((this.mDiskCache == null) || (this.mDiskCache.containsKey(paramString)));
    this.mDiskCache.put(paramString, paramBitmap);
  }

  public void clearCaches()
  {
    this.mDiskCache.clearCache();
    this.mMemoryCache.evictAll();
  }

  public Bitmap getBitmapFromDiskCache(String paramString)
  {
    if (this.mDiskCache != null)
      return this.mDiskCache.get(paramString);
    return null;
  }

  public Bitmap getBitmapFromMemCache(String paramString)
  {
    if (this.mMemoryCache != null)
    {
      paramString = (Bitmap)this.mMemoryCache.get(paramString);
      if (paramString != null)
      {
        Log.d("ImageCache", "Memory cache hit");
        return paramString;
      }
    }
    return null;
  }

  public static class ImageCacheParams
  {
    public boolean clearDiskCacheOnStart = false;
    public Bitmap.CompressFormat compressFormat = ImageCache.DEFAULT_COMPRESS_FORMAT;
    public int compressQuality = 70;
    public boolean diskCacheEnabled = true;
    public int diskCacheSize = 10485760;
    public int memCacheSize = 5242880;
    public boolean memoryCacheEnabled = true;
    public String uniqueName;

    public ImageCacheParams(String paramString)
    {
      this.uniqueName = paramString;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.example.android.bitmapfun.util.ImageCache
 * JD-Core Version:    0.6.2
 */