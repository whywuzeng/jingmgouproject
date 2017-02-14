package com.nostra13.universalimageloader.utils;

import com.nostra13.universalimageloader.cache.disc.DiskCache;
import java.io.File;

public final class DiskCacheUtils
{
  public static File findInCache(String paramString, DiskCache paramDiskCache)
  {
    paramString = paramDiskCache.get(paramString);
    if ((paramString != null) && (paramString.exists()))
      return paramString;
    return null;
  }

  public static boolean removeFromCache(String paramString, DiskCache paramDiskCache)
  {
    paramString = paramDiskCache.get(paramString);
    return (paramString != null) && (paramString.exists()) && (paramString.delete());
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.nostra13.universalimageloader.utils.DiskCacheUtils
 * JD-Core Version:    0.6.2
 */