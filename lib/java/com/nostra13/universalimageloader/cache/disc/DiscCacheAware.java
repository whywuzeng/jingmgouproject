package com.nostra13.universalimageloader.cache.disc;

import android.graphics.Bitmap;
import com.nostra13.universalimageloader.utils.IoUtils.CopyListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Deprecated
public abstract interface DiscCacheAware
{
  public abstract void clear();

  public abstract void close();

  public abstract File get(String paramString);

  public abstract File getDirectory();

  public abstract boolean remove(String paramString);

  public abstract boolean save(String paramString, Bitmap paramBitmap)
    throws IOException;

  public abstract boolean save(String paramString, InputStream paramInputStream, IoUtils.CopyListener paramCopyListener)
    throws IOException;
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.nostra13.universalimageloader.cache.disc.DiscCacheAware
 * JD-Core Version:    0.6.2
 */