package net.tsz.afinal.bitmap.core;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import net.tsz.afinal.utils.Utils;

public class BitmapCache
{
  private static final int DEFAULT_DISK_CACHE_COUNT = 10000;
  private static final boolean DEFAULT_DISK_CACHE_ENABLED = true;
  private static final int DEFAULT_DISK_CACHE_SIZE = 52428800;
  private static final boolean DEFAULT_MEM_CACHE_ENABLED = true;
  private static final int DEFAULT_MEM_CACHE_SIZE = 8388608;
  private ImageCacheParams mCacheParams;
  private DiskCache mDiskCache;
  private IMemoryCache mMemoryCache;

  public BitmapCache(ImageCacheParams paramImageCacheParams)
  {
    init(paramImageCacheParams);
  }

  private void init(ImageCacheParams paramImageCacheParams)
  {
    this.mCacheParams = paramImageCacheParams;
    if (this.mCacheParams.memoryCacheEnabled)
    {
      if (!this.mCacheParams.recycleImmediately)
        break label87;
      this.mMemoryCache = new SoftMemoryCacheImpl(this.mCacheParams.memCacheSize);
    }
    while (true)
    {
      if (paramImageCacheParams.diskCacheEnabled);
      try
      {
        this.mDiskCache = new DiskCache(this.mCacheParams.diskCacheDir.getAbsolutePath(), this.mCacheParams.diskCacheCount, this.mCacheParams.diskCacheSize, false);
        return;
        label87: this.mMemoryCache = new BaseMemoryCacheImpl(this.mCacheParams.memCacheSize);
      }
      catch (IOException paramImageCacheParams)
      {
      }
    }
  }

  public void addToDiskCache(String paramString, byte[] arg2)
  {
    if ((this.mDiskCache == null) || (paramString == null) || (??? == null))
      return;
    byte[] arrayOfByte = Utils.makeKey(paramString);
    long l = Utils.crc64Long(arrayOfByte);
    paramString = ByteBuffer.allocate(arrayOfByte.length + ???.length);
    paramString.put(arrayOfByte);
    paramString.put(???);
    try
    {
      synchronized (this.mDiskCache)
      {
        this.mDiskCache.insert(l, paramString.array());
        label70: return;
      }
    }
    catch (IOException paramString)
    {
      break label70;
    }
  }

  public void addToMemoryCache(String paramString, Bitmap paramBitmap)
  {
    if ((paramString == null) || (paramBitmap == null))
      return;
    this.mMemoryCache.put(paramString, paramBitmap);
  }

  public void clearCache()
  {
    clearMemoryCache();
    clearDiskCache();
  }

  public void clearCache(String paramString)
  {
    clearMemoryCache(paramString);
    clearDiskCache(paramString);
  }

  public void clearDiskCache()
  {
    if (this.mDiskCache != null)
      this.mDiskCache.delete();
  }

  public void clearDiskCache(String paramString)
  {
    addToDiskCache(paramString, new byte[0]);
  }

  public void clearMemoryCache()
  {
    if (this.mMemoryCache != null)
      this.mMemoryCache.evictAll();
  }

  public void clearMemoryCache(String paramString)
  {
    if (this.mMemoryCache != null)
      this.mMemoryCache.remove(paramString);
  }

  public void close()
  {
    if (this.mDiskCache != null)
      this.mDiskCache.close();
  }

  public Bitmap getBitmapFromMemoryCache(String paramString)
  {
    if (this.mMemoryCache != null)
      return this.mMemoryCache.get(paramString);
    return null;
  }

  public boolean getImageData(String arg1, BytesBufferPool.BytesBuffer paramBytesBuffer)
  {
    if (this.mDiskCache == null);
    byte[] arrayOfByte;
    DiskCache.LookupRequest localLookupRequest;
    do
    {
      return false;
      arrayOfByte = Utils.makeKey(???);
      long l = Utils.crc64Long(arrayOfByte);
      try
      {
        localLookupRequest = new DiskCache.LookupRequest();
        localLookupRequest.key = l;
        localLookupRequest.buffer = paramBytesBuffer.data;
        synchronized (this.mDiskCache)
        {
          if (!this.mDiskCache.lookup(localLookupRequest))
            return false;
        }
      }
      catch (IOException )
      {
        return false;
      }
    }
    while (!Utils.isSameKey(arrayOfByte, localLookupRequest.buffer));
    paramBytesBuffer.data = localLookupRequest.buffer;
    paramBytesBuffer.offset = arrayOfByte.length;
    paramBytesBuffer.length = (localLookupRequest.length - paramBytesBuffer.offset);
    return true;
  }

  public static class ImageCacheParams
  {
    public int diskCacheCount = 10000;
    public File diskCacheDir;
    public boolean diskCacheEnabled = true;
    public int diskCacheSize = 52428800;
    public int memCacheSize = 8388608;
    public boolean memoryCacheEnabled = true;
    public boolean recycleImmediately = true;

    public ImageCacheParams(File paramFile)
    {
      this.diskCacheDir = paramFile;
    }

    public ImageCacheParams(String paramString)
    {
      this.diskCacheDir = new File(paramString);
    }

    private static int getMemoryClass(Context paramContext)
    {
      return ((ActivityManager)paramContext.getSystemService("activity")).getMemoryClass();
    }

    public void setDiskCacheCount(int paramInt)
    {
      this.diskCacheCount = paramInt;
    }

    public void setDiskCacheSize(int paramInt)
    {
      this.diskCacheSize = paramInt;
    }

    public void setMemCacheSize(int paramInt)
    {
      this.memCacheSize = paramInt;
    }

    public void setMemCacheSizePercent(Context paramContext, float paramFloat)
    {
      if ((paramFloat < 0.05F) || (paramFloat > 0.8F))
        throw new IllegalArgumentException("setMemCacheSizePercent - percent must be between 0.05 and 0.8 (inclusive)");
      this.memCacheSize = Math.round(getMemoryClass(paramContext) * paramFloat * 1024.0F * 1024.0F);
    }

    public void setRecycleImmediately(boolean paramBoolean)
    {
      this.recycleImmediately = paramBoolean;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     net.tsz.afinal.bitmap.core.BitmapCache
 * JD-Core Version:    0.6.2
 */