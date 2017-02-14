package com.nostra13.universalimageloader.cache.disc.impl;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import com.nostra13.universalimageloader.cache.disc.DiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.FileNameGenerator;
import com.nostra13.universalimageloader.core.DefaultConfigurationFactory;
import com.nostra13.universalimageloader.utils.IoUtils;
import com.nostra13.universalimageloader.utils.IoUtils.CopyListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public abstract class BaseDiscCache
  implements DiskCache
{
  public static final int DEFAULT_BUFFER_SIZE = 32768;
  public static final Bitmap.CompressFormat DEFAULT_COMPRESS_FORMAT = Bitmap.CompressFormat.PNG;
  public static final int DEFAULT_COMPRESS_QUALITY = 100;
  private static final String ERROR_ARG_NULL = " argument must be not null";
  private static final String TEMP_IMAGE_POSTFIX = ".tmp";
  protected int bufferSize = 32768;
  protected final File cacheDir;
  protected Bitmap.CompressFormat compressFormat = DEFAULT_COMPRESS_FORMAT;
  protected int compressQuality = 100;
  protected final FileNameGenerator fileNameGenerator;
  protected final File reserveCacheDir;

  public BaseDiscCache(File paramFile)
  {
    this(paramFile, null);
  }

  public BaseDiscCache(File paramFile1, File paramFile2)
  {
    this(paramFile1, paramFile2, DefaultConfigurationFactory.createFileNameGenerator());
  }

  public BaseDiscCache(File paramFile1, File paramFile2, FileNameGenerator paramFileNameGenerator)
  {
    if (paramFile1 == null)
      throw new IllegalArgumentException("cacheDir argument must be not null");
    if (paramFileNameGenerator == null)
      throw new IllegalArgumentException("fileNameGenerator argument must be not null");
    this.cacheDir = paramFile1;
    this.reserveCacheDir = paramFile2;
    this.fileNameGenerator = paramFileNameGenerator;
  }

  public void clear()
  {
    File[] arrayOfFile = this.cacheDir.listFiles();
    if (arrayOfFile != null)
    {
      int j = arrayOfFile.length;
      int i = 0;
      while (i < j)
      {
        arrayOfFile[i].delete();
        i += 1;
      }
    }
  }

  public void close()
  {
  }

  public File get(String paramString)
  {
    return getFile(paramString);
  }

  public File getDirectory()
  {
    return this.cacheDir;
  }

  protected File getFile(String paramString)
  {
    String str = this.fileNameGenerator.generate(paramString);
    File localFile = this.cacheDir;
    paramString = localFile;
    if (!this.cacheDir.exists())
    {
      paramString = localFile;
      if (!this.cacheDir.mkdirs())
      {
        paramString = localFile;
        if (this.reserveCacheDir != null)
          if (!this.reserveCacheDir.exists())
          {
            paramString = localFile;
            if (!this.reserveCacheDir.mkdirs());
          }
          else
          {
            paramString = this.reserveCacheDir;
          }
      }
    }
    return new File(paramString, str);
  }

  public boolean remove(String paramString)
  {
    return getFile(paramString).delete();
  }

  public boolean save(String paramString, Bitmap paramBitmap)
    throws IOException
  {
    paramString = getFile(paramString);
    File localFile = new File(paramString.getAbsolutePath() + ".tmp");
    BufferedOutputStream localBufferedOutputStream = new BufferedOutputStream(new FileOutputStream(localFile), this.bufferSize);
    try
    {
      boolean bool1 = paramBitmap.compress(this.compressFormat, this.compressQuality, localBufferedOutputStream);
      IoUtils.closeSilently(localBufferedOutputStream);
      boolean bool2 = bool1;
      if (bool1)
      {
        bool2 = bool1;
        if (!localFile.renameTo(paramString))
          bool2 = false;
      }
      if (!bool2)
        localFile.delete();
      paramBitmap.recycle();
      return bool2;
    }
    finally
    {
      IoUtils.closeSilently(localBufferedOutputStream);
      if (((0 == 0) || (localFile.renameTo(paramString))) || (0 == 0))
        localFile.delete();
    }
    throw paramBitmap;
  }

  public boolean save(String paramString, InputStream paramInputStream, IoUtils.CopyListener paramCopyListener)
    throws IOException
  {
    paramString = getFile(paramString);
    File localFile = new File(paramString.getAbsolutePath() + ".tmp");
    boolean bool2 = false;
    boolean bool1 = bool2;
    try
    {
      BufferedOutputStream localBufferedOutputStream = new BufferedOutputStream(new FileOutputStream(localFile), this.bufferSize);
      try
      {
        bool1 = IoUtils.copyStream(paramInputStream, localBufferedOutputStream, paramCopyListener, this.bufferSize);
        bool2 = bool1;
        bool1 = bool2;
        IoUtils.closeSilently(localBufferedOutputStream);
        return bool1;
      }
      finally
      {
        bool1 = bool2;
        IoUtils.closeSilently(localBufferedOutputStream);
        bool1 = bool2;
      }
    }
    finally
    {
      IoUtils.closeSilently(paramInputStream);
      bool2 = bool1;
      if (bool1)
      {
        bool2 = bool1;
        if (!localFile.renameTo(paramString))
          bool2 = false;
      }
      if (!bool2)
        localFile.delete();
    }
    throw paramCopyListener;
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
 * Qualified Name:     com.nostra13.universalimageloader.cache.disc.impl.BaseDiscCache
 * JD-Core Version:    0.6.2
 */