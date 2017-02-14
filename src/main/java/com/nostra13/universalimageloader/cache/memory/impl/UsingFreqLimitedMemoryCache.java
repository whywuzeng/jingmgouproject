package com.nostra13.universalimageloader.cache.memory.impl;

import android.graphics.Bitmap;
import com.nostra13.universalimageloader.cache.memory.LimitedMemoryCache;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class UsingFreqLimitedMemoryCache extends LimitedMemoryCache
{
  private final Map<Bitmap, Integer> usingCounts = Collections.synchronizedMap(new HashMap());

  public UsingFreqLimitedMemoryCache(int paramInt)
  {
    super(paramInt);
  }

  public void clear()
  {
    this.usingCounts.clear();
    super.clear();
  }

  protected Reference<Bitmap> createReference(Bitmap paramBitmap)
  {
    return new WeakReference(paramBitmap);
  }

  public Bitmap get(String paramString)
  {
    paramString = super.get(paramString);
    if (paramString != null)
    {
      Integer localInteger = (Integer)this.usingCounts.get(paramString);
      if (localInteger != null)
        this.usingCounts.put(paramString, Integer.valueOf(localInteger.intValue() + 1));
    }
    return paramString;
  }

  protected int getSize(Bitmap paramBitmap)
  {
    return paramBitmap.getRowBytes() * paramBitmap.getHeight();
  }

  public boolean put(String paramString, Bitmap paramBitmap)
  {
    boolean bool = false;
    if (super.put(paramString, paramBitmap))
    {
      this.usingCounts.put(paramBitmap, Integer.valueOf(0));
      bool = true;
    }
    return bool;
  }

  public Bitmap remove(String paramString)
  {
    Bitmap localBitmap = super.get(paramString);
    if (localBitmap != null)
      this.usingCounts.remove(localBitmap);
    return super.remove(paramString);
  }

  protected Bitmap removeNext()
  {
    Object localObject2 = null;
    Bitmap localBitmap = null;
    Object localObject3 = this.usingCounts.entrySet();
    synchronized (this.usingCounts)
    {
      Iterator localIterator = ((Set)localObject3).iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        if (localBitmap == null)
        {
          localBitmap = (Bitmap)localEntry.getKey();
          localObject2 = (Integer)localEntry.getValue();
        }
        else
        {
          localObject3 = (Integer)localEntry.getValue();
          if (((Integer)localObject3).intValue() < ((Integer)localObject2).intValue())
          {
            localObject2 = localObject3;
            localBitmap = (Bitmap)localEntry.getKey();
          }
        }
      }
      this.usingCounts.remove(localBitmap);
      return localBitmap;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache
 * JD-Core Version:    0.6.2
 */