package net.tsz.afinal.bitmap.core;

import android.graphics.Bitmap;
import net.tsz.afinal.utils.Utils;

public class BaseMemoryCacheImpl
  implements IMemoryCache
{
  private final LruMemoryCache<String, Bitmap> mMemoryCache;

  public BaseMemoryCacheImpl(int paramInt)
  {
    this.mMemoryCache = new LruMemoryCache(paramInt)
    {
      protected int sizeOf(String paramAnonymousString, Bitmap paramAnonymousBitmap)
      {
        return Utils.getBitmapSize(paramAnonymousBitmap);
      }
    };
  }

  public void evictAll()
  {
    this.mMemoryCache.evictAll();
  }

  public Bitmap get(String paramString)
  {
    return (Bitmap)this.mMemoryCache.get(paramString);
  }

  public void put(String paramString, Bitmap paramBitmap)
  {
    this.mMemoryCache.put(paramString, paramBitmap);
  }

  public void remove(String paramString)
  {
    this.mMemoryCache.remove(paramString);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     net.tsz.afinal.bitmap.core.BaseMemoryCacheImpl
 * JD-Core Version:    0.6.2
 */