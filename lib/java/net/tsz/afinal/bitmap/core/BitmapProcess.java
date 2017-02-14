package net.tsz.afinal.bitmap.core;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import net.tsz.afinal.bitmap.download.Downloader;

public class BitmapProcess
{
  private static final int BYTESBUFFER_SIZE = 204800;
  private static final int BYTESBUFFE_POOL_SIZE = 4;
  private static final BytesBufferPool sMicroThumbBufferPool = new BytesBufferPool(4, 204800);
  private BitmapCache mCache;
  private Downloader mDownloader;

  public BitmapProcess(Downloader paramDownloader, BitmapCache paramBitmapCache)
  {
    this.mDownloader = paramDownloader;
    this.mCache = paramBitmapCache;
  }

  public Bitmap getBitmap(String paramString, BitmapDisplayConfig paramBitmapDisplayConfig)
  {
    Bitmap localBitmap2 = getFromDisk(paramString, paramBitmapDisplayConfig);
    Bitmap localBitmap1 = localBitmap2;
    byte[] arrayOfByte;
    if (localBitmap2 == null)
    {
      arrayOfByte = this.mDownloader.download(paramString);
      localBitmap1 = localBitmap2;
      if (arrayOfByte != null)
      {
        localBitmap1 = localBitmap2;
        if (arrayOfByte.length > 0)
        {
          if (paramBitmapDisplayConfig == null)
            break label79;
          localBitmap1 = BitmapDecoder.decodeSampledBitmapFromByteArray(arrayOfByte, 0, arrayOfByte.length, paramBitmapDisplayConfig.getBitmapWidth(), paramBitmapDisplayConfig.getBitmapHeight());
          this.mCache.addToDiskCache(paramString, arrayOfByte);
        }
      }
    }
    return localBitmap1;
    label79: return BitmapFactory.decodeByteArray(arrayOfByte, 0, arrayOfByte.length);
  }

  public Bitmap getFromDisk(String paramString, BitmapDisplayConfig paramBitmapDisplayConfig)
  {
    BytesBufferPool.BytesBuffer localBytesBuffer = sMicroThumbBufferPool.get();
    Object localObject2 = null;
    Object localObject1 = localObject2;
    try
    {
      if (this.mCache.getImageData(paramString, localBytesBuffer))
      {
        localObject1 = localObject2;
        if (localBytesBuffer.length - localBytesBuffer.offset > 0)
          if (paramBitmapDisplayConfig == null)
            break label85;
      }
      label85: for (localObject1 = BitmapDecoder.decodeSampledBitmapFromByteArray(localBytesBuffer.data, localBytesBuffer.offset, localBytesBuffer.length, paramBitmapDisplayConfig.getBitmapWidth(), paramBitmapDisplayConfig.getBitmapHeight()); ; localObject1 = BitmapFactory.decodeByteArray(localBytesBuffer.data, localBytesBuffer.offset, localBytesBuffer.length))
        return localObject1;
    }
    finally
    {
      sMicroThumbBufferPool.recycle(localBytesBuffer);
    }
    throw paramString;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     net.tsz.afinal.bitmap.core.BitmapProcess
 * JD-Core Version:    0.6.2
 */