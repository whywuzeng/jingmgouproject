package com.yolanda.nohttp;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.widget.ImageView.ScaleType;
import java.util.Locale;

public class ImageRequest extends RestRequestor<Bitmap>
{
  private static final Object DECODE_LOCK = new Object();
  private final Bitmap.Config mDecodeConfig;
  private final int mMaxHeight;
  private final int mMaxWidth;
  private ImageView.ScaleType mScaleType;

  public ImageRequest(String paramString, int paramInt1, int paramInt2, Bitmap.Config paramConfig, ImageView.ScaleType paramScaleType)
  {
    super(paramString, 0);
    this.mMaxWidth = paramInt1;
    this.mMaxHeight = paramInt2;
    this.mDecodeConfig = paramConfig;
    this.mScaleType = paramScaleType;
  }

  private Bitmap doResponse(byte[] paramArrayOfByte)
    throws OutOfMemoryError
  {
    Object localObject = new BitmapFactory.Options();
    if ((this.mMaxWidth == 0) && (this.mMaxHeight == 0))
    {
      ((BitmapFactory.Options)localObject).inPreferredConfig = this.mDecodeConfig;
      return BitmapFactory.decodeByteArray(paramArrayOfByte, 0, paramArrayOfByte.length, (BitmapFactory.Options)localObject);
    }
    ((BitmapFactory.Options)localObject).inJustDecodeBounds = true;
    BitmapFactory.decodeByteArray(paramArrayOfByte, 0, paramArrayOfByte.length, (BitmapFactory.Options)localObject);
    int i = ((BitmapFactory.Options)localObject).outWidth;
    int j = ((BitmapFactory.Options)localObject).outHeight;
    int k = getResizedDimension(this.mMaxWidth, this.mMaxHeight, i, j, this.mScaleType);
    int m = getResizedDimension(this.mMaxHeight, this.mMaxWidth, j, i, this.mScaleType);
    ((BitmapFactory.Options)localObject).inJustDecodeBounds = false;
    ((BitmapFactory.Options)localObject).inSampleSize = findBestSampleSize(i, j, k, m);
    paramArrayOfByte = BitmapFactory.decodeByteArray(paramArrayOfByte, 0, paramArrayOfByte.length, (BitmapFactory.Options)localObject);
    if ((paramArrayOfByte != null) && ((paramArrayOfByte.getWidth() > k) || (paramArrayOfByte.getHeight() > m)))
    {
      localObject = Bitmap.createScaledBitmap(paramArrayOfByte, k, m, true);
      paramArrayOfByte.recycle();
      return localObject;
    }
    return paramArrayOfByte;
  }

  public static int findBestSampleSize(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    double d = Math.min(paramInt1 / paramInt3, paramInt2 / paramInt4);
    for (float f = 1.0F; ; f *= 2.0F)
      if (2.0F * f > d)
        return (int)f;
  }

  private static int getResizedDimension(int paramInt1, int paramInt2, int paramInt3, int paramInt4, ImageView.ScaleType paramScaleType)
  {
    if ((paramInt1 == 0) && (paramInt2 == 0));
    do
    {
      return paramInt3;
      if (paramScaleType != ImageView.ScaleType.FIT_XY)
        break;
    }
    while (paramInt1 == 0);
    return paramInt1;
    if (paramInt1 == 0)
    {
      d = paramInt2 / paramInt4;
      return (int)(paramInt3 * d);
    }
    if (paramInt2 == 0)
      return paramInt1;
    double d = paramInt4 / paramInt3;
    if (paramScaleType == ImageView.ScaleType.CENTER_CROP)
    {
      paramInt3 = paramInt1;
      if (paramInt1 * d < paramInt2)
        paramInt3 = (int)(paramInt2 / d);
      return paramInt3;
    }
    paramInt3 = paramInt1;
    if (paramInt1 * d > paramInt2)
      paramInt3 = (int)(paramInt2 / d);
    return paramInt3;
  }

  public Bitmap parseResponse(String paramString1, String paramString2, byte[] paramArrayOfByte)
  {
    Object localObject2 = DECODE_LOCK;
    Object localObject1 = null;
    paramString2 = localObject1;
    if (paramArrayOfByte != null);
    try
    {
      paramString2 = doResponse(paramArrayOfByte);
      return paramString2;
    }
    catch (OutOfMemoryError paramString2)
    {
      while (true)
      {
        Logger.wtf(paramString2, String.format(Locale.getDefault(), "Caught OOM for %d byte image, url=%s", new Object[] { Integer.valueOf(paramArrayOfByte.length), paramString1 }));
        paramString2 = localObject1;
      }
    }
    finally
    {
    }
    throw paramString1;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.yolanda.nohttp.ImageRequest
 * JD-Core Version:    0.6.2
 */