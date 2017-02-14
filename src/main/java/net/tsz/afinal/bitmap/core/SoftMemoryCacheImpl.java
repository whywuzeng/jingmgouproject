package net.tsz.afinal.bitmap.core;

import android.graphics.Bitmap;
import java.lang.ref.SoftReference;
import java.util.HashMap;

public class SoftMemoryCacheImpl
  implements IMemoryCache
{
  private final HashMap<String, SoftReference<Bitmap>> mMemoryCache = new HashMap();

  public SoftMemoryCacheImpl(int paramInt)
  {
  }

  public void evictAll()
  {
    this.mMemoryCache.clear();
  }

  public Bitmap get(String paramString)
  {
    paramString = (SoftReference)this.mMemoryCache.get(paramString);
    if (paramString != null)
      return (Bitmap)paramString.get();
    return null;
  }

  public void put(String paramString, Bitmap paramBitmap)
  {
    this.mMemoryCache.put(paramString, new SoftReference(paramBitmap));
  }

  public void remove(String paramString)
  {
    this.mMemoryCache.remove(paramString);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     net.tsz.afinal.bitmap.core.SoftMemoryCacheImpl
 * JD-Core Version:    0.6.2
 */