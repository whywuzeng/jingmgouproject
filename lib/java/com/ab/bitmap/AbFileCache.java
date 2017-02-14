package com.ab.bitmap;

import com.ab.global.AbAppData;
import com.ab.util.AbFileUtil;
import com.ab.util.AbStrUtil;
import java.io.File;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class AbFileCache
{
  private static final boolean D;
  private static String TAG = "AbFileCache";
  public static int cacheSize;
  private static final HashMap<String, File> fileCache;
  public static final ReentrantLock lock;
  public static int maxCacheSize;

  static
  {
    D = AbAppData.DEBUG;
    maxCacheSize = 10485760;
    cacheSize = 0;
    fileCache = new HashMap();
    lock = new ReentrantLock();
    AbFileUtil.initFileCache();
  }

  public static void addFileToCache(String paramString, File paramFile)
  {
    try
    {
      lock.lock();
      boolean bool = AbStrUtil.isEmpty(paramString);
      if (bool)
        return;
      if ((getFileFromCache(paramString) == null) && (paramFile != null))
        fileCache.put(paramString, paramFile);
      if (cacheSize > maxCacheSize)
        AbFileUtil.freeCacheFiles();
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
      return;
    }
    finally
    {
      lock.unlock();
    }
    throw paramString;
  }

  public static int getCacheSize()
  {
    return cacheSize;
  }

  public static File getFileFromCache(String paramString)
  {
    return (File)fileCache.get(paramString);
  }

  public static void removeAllFileFromCache()
  {
    AbFileUtil.removeAllFileCache();
    fileCache.clear();
  }

  public static void removeFileFromCache(String paramString)
  {
    try
    {
      lock.lock();
      if (getFileFromCache(paramString) != null)
        fileCache.remove(paramString);
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
      return;
    }
    finally
    {
      lock.unlock();
    }
    throw paramString;
  }

  public static void setMaxCacheSize(int paramInt)
  {
    maxCacheSize = paramInt;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.bitmap.AbFileCache
 * JD-Core Version:    0.6.2
 */