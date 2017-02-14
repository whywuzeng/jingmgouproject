package com.nostra13.universalimageloader.cache.memory.impl;

import android.graphics.Bitmap;
import com.nostra13.universalimageloader.cache.memory.MemoryCache;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

public class FuzzyKeyMemoryCache
  implements MemoryCache
{
  private final MemoryCache cache;
  private final Comparator<String> keyComparator;

  public FuzzyKeyMemoryCache(MemoryCache paramMemoryCache, Comparator<String> paramComparator)
  {
    this.cache = paramMemoryCache;
    this.keyComparator = paramComparator;
  }

  public void clear()
  {
    this.cache.clear();
  }

  public Bitmap get(String paramString)
  {
    return (Bitmap)this.cache.get(paramString);
  }

  public Collection<String> keys()
  {
    return this.cache.keys();
  }

  public boolean put(String paramString, Bitmap paramBitmap)
  {
    MemoryCache localMemoryCache = this.cache;
    Object localObject2 = null;
    try
    {
      Iterator localIterator = this.cache.keys().iterator();
      Object localObject1;
      do
      {
        localObject1 = localObject2;
        if (!localIterator.hasNext())
          break;
        localObject1 = (String)localIterator.next();
      }
      while (this.keyComparator.compare(paramString, localObject1) != 0);
      if (localObject1 != null)
        this.cache.remove(localObject1);
      return this.cache.put(paramString, paramBitmap);
    }
    finally
    {
    }
    throw paramString;
  }

  public Bitmap remove(String paramString)
  {
    return (Bitmap)this.cache.remove(paramString);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.nostra13.universalimageloader.cache.memory.impl.FuzzyKeyMemoryCache
 * JD-Core Version:    0.6.2
 */