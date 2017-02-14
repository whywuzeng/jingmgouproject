package net.tsz.afinal.bitmap.core;

import android.graphics.Bitmap;

public abstract interface IMemoryCache
{
  public abstract void evictAll();

  public abstract Bitmap get(String paramString);

  public abstract void put(String paramString, Bitmap paramBitmap);

  public abstract void remove(String paramString);
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     net.tsz.afinal.bitmap.core.IMemoryCache
 * JD-Core Version:    0.6.2
 */