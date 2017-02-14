package com.nostra13.universalimageloader.cache.disc.impl.ext;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import com.nostra13.universalimageloader.cache.disc.DiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.FileNameGenerator;
import com.nostra13.universalimageloader.utils.IoUtils;
import com.nostra13.universalimageloader.utils.IoUtils.CopyListener;
import com.nostra13.universalimageloader.utils.L;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class LruDiscCache
  implements DiskCache
{
  public static final int DEFAULT_BUFFER_SIZE = 32768;
  public static final Bitmap.CompressFormat DEFAULT_COMPRESS_FORMAT = Bitmap.CompressFormat.PNG;
  public static final int DEFAULT_COMPRESS_QUALITY = 100;
  private static final String ERROR_ARG_NEGATIVE = " argument must be positive number";
  private static final String ERROR_ARG_NULL = " argument must be not null";
  protected int bufferSize = 32768;
  protected DiskLruCache cache;
  protected Bitmap.CompressFormat compressFormat = DEFAULT_COMPRESS_FORMAT;
  protected int compressQuality = 100;
  protected final FileNameGenerator fileNameGenerator;
  private File reserveCacheDir;

  public LruDiscCache(File paramFile, FileNameGenerator paramFileNameGenerator, long paramLong)
    throws IOException
  {
    this(paramFile, null, paramFileNameGenerator, paramLong, 0);
  }

  public LruDiscCache(File paramFile1, File paramFile2, FileNameGenerator paramFileNameGenerator, long paramLong, int paramInt)
    throws IOException
  {
    if (paramFile1 == null)
      throw new IllegalArgumentException("cacheDir argument must be not null");
    if (paramLong < 0L)
      throw new IllegalArgumentException("cacheMaxSize argument must be positive number");
    if (paramInt < 0)
      throw new IllegalArgumentException("cacheMaxFileCount argument must be positive number");
    if (paramFileNameGenerator == null)
      throw new IllegalArgumentException("fileNameGenerator argument must be not null");
    long l = paramLong;
    if (paramLong == 0L)
      l = 9223372036854775807L;
    int i = paramInt;
    if (paramInt == 0)
      i = 2147483647;
    this.reserveCacheDir = paramFile2;
    this.fileNameGenerator = paramFileNameGenerator;
    initCache(paramFile1, paramFile2, l, i);
  }

  private String getKey(String paramString)
  {
    return this.fileNameGenerator.generate(paramString);
  }

  private void initCache(File paramFile1, File paramFile2, long paramLong, int paramInt)
    throws IOException
  {
    try
    {
      this.cache = DiskLruCache.open(paramFile1, 1, 1, paramLong, paramInt);
      return;
    }
    catch (IOException paramFile1)
    {
      do
      {
        L.e(paramFile1);
        if (paramFile2 != null)
          initCache(paramFile2, null, paramLong, paramInt);
      }
      while (this.cache != null);
    }
    throw paramFile1;
  }

  public void clear()
  {
    try
    {
      this.cache.delete();
    }
    catch (IOException localIOException1)
    {
      try
      {
        while (true)
        {
          initCache(this.cache.getDirectory(), this.reserveCacheDir, this.cache.getMaxSize(), this.cache.getMaxFileCount());
          return;
          localIOException1 = localIOException1;
          L.e(localIOException1);
        }
      }
      catch (IOException localIOException2)
      {
        L.e(localIOException2);
      }
    }
  }

  public void close()
  {
    try
    {
      this.cache.close();
      this.cache = null;
      return;
    }
    catch (IOException localIOException)
    {
      while (true)
        L.e(localIOException);
    }
  }

  public File get(String paramString)
  {
    Object localObject3 = null;
    File localFile = null;
    Object localObject2 = null;
    Object localObject1 = null;
    try
    {
      paramString = this.cache.get(getKey(paramString));
      if (paramString == null);
      for (localObject1 = localFile; ; localObject1 = localFile)
      {
        localObject2 = localObject1;
        if (paramString != null)
        {
          paramString.close();
          localObject2 = localObject1;
        }
        return localObject2;
        localObject1 = paramString;
        localObject2 = paramString;
        localFile = paramString.getFile(0);
      }
    }
    catch (IOException paramString)
    {
      localObject2 = localObject1;
      L.e(paramString);
      localObject2 = localObject3;
      return null;
    }
    finally
    {
      if (localObject2 != null)
        ((DiskLruCache.Snapshot)localObject2).close();
    }
    throw paramString;
  }

  public File getDirectory()
  {
    return this.cache.getDirectory();
  }

  public boolean remove(String paramString)
  {
    try
    {
      boolean bool = this.cache.remove(getKey(paramString));
      return bool;
    }
    catch (IOException paramString)
    {
      L.e(paramString);
    }
    return false;
  }

  public boolean save(String paramString, Bitmap paramBitmap)
    throws IOException
  {
    DiskLruCache.Editor localEditor = this.cache.edit(getKey(paramString));
    if (localEditor == null)
      return false;
    paramString = new BufferedOutputStream(localEditor.newOutputStream(0), this.bufferSize);
    boolean bool;
    try
    {
      bool = paramBitmap.compress(this.compressFormat, this.compressQuality, paramString);
      IoUtils.closeSilently(paramString);
      if (bool)
      {
        localEditor.commit();
        return bool;
      }
    }
    finally
    {
      IoUtils.closeSilently(paramString);
    }
    localEditor.abort();
    return bool;
  }

  public boolean save(String paramString, InputStream paramInputStream, IoUtils.CopyListener paramCopyListener)
    throws IOException
  {
    paramString = this.cache.edit(getKey(paramString));
    if (paramString == null)
      return false;
    BufferedOutputStream localBufferedOutputStream = new BufferedOutputStream(paramString.newOutputStream(0), this.bufferSize);
    try
    {
      boolean bool = IoUtils.copyStream(paramInputStream, localBufferedOutputStream, paramCopyListener, this.bufferSize);
      IoUtils.closeSilently(localBufferedOutputStream);
      if (bool)
      {
        paramString.commit();
        return bool;
      }
      paramString.abort();
      return bool;
    }
    finally
    {
      IoUtils.closeSilently(localBufferedOutputStream);
      if (0 == 0)
        break label90;
    }
    paramString.commit();
    while (true)
    {
      throw paramInputStream;
      label90: paramString.abort();
    }
  }

  public void setBufferSize(int paramInt)
  {
    this.bufferSize = paramInt;
  }

  public void setCompressFormat(Bitmap.CompressFormat paramCompressFormat)
  {
    this.compressFormat = paramCompressFormat;
  }

  public void setCompressQuality(int paramInt)
  {
    this.compressQuality = paramInt;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.nostra13.universalimageloader.cache.disc.impl.ext.LruDiscCache
 * JD-Core Version:    0.6.2
 */