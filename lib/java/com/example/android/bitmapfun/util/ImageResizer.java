package com.example.android.bitmapfun.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.util.Log;

public class ImageResizer extends ImageWorker
{
  private static final String TAG = "ImageWorker";
  protected int mImageHeight;
  protected int mImageWidth;

  public ImageResizer(Context paramContext, int paramInt)
  {
    super(paramContext);
    setImageSize(paramInt);
  }

  public ImageResizer(Context paramContext, int paramInt1, int paramInt2)
  {
    super(paramContext);
    setImageSize(paramInt1, paramInt2);
  }

  public static int calculateInSampleSize(BitmapFactory.Options paramOptions, int paramInt1, int paramInt2)
  {
    int j = paramOptions.outHeight;
    int k = paramOptions.outWidth;
    int i = 1;
    float f1;
    float f2;
    if ((j > paramInt2) || (k > paramInt1))
    {
      if (k <= j)
        break label77;
      i = Math.round(j / paramInt2);
      f1 = k * j;
      f2 = paramInt1 * paramInt2 * 2;
    }
    while (true)
    {
      if (f1 / (i * i) <= f2)
      {
        return i;
        label77: i = Math.round(k / paramInt1);
        break;
      }
      i += 1;
    }
  }

  public static Bitmap decodeSampledBitmapFromFile(String paramString, int paramInt1, int paramInt2)
  {
    try
    {
      BitmapFactory.Options localOptions = new BitmapFactory.Options();
      localOptions.inJustDecodeBounds = true;
      BitmapFactory.decodeFile(paramString, localOptions);
      localOptions.inSampleSize = calculateInSampleSize(localOptions, paramInt1, paramInt2);
      localOptions.inJustDecodeBounds = false;
      paramString = BitmapFactory.decodeFile(paramString, localOptions);
      return paramString;
    }
    finally
    {
      paramString = finally;
    }
    throw paramString;
  }

  public static Bitmap decodeSampledBitmapFromResource(Resources paramResources, int paramInt1, int paramInt2, int paramInt3)
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inJustDecodeBounds = true;
    BitmapFactory.decodeResource(paramResources, paramInt1, localOptions);
    localOptions.inSampleSize = calculateInSampleSize(localOptions, paramInt2, paramInt3);
    localOptions.inJustDecodeBounds = false;
    return BitmapFactory.decodeResource(paramResources, paramInt1, localOptions);
  }

  private Bitmap processBitmap(int paramInt)
  {
    Log.d("ImageWorker", "processBitmap - " + paramInt);
    return decodeSampledBitmapFromResource(this.mContext.getResources(), paramInt, this.mImageWidth, this.mImageHeight);
  }

  protected Bitmap processBitmap(Object paramObject)
  {
    return processBitmap(Integer.parseInt(String.valueOf(paramObject)));
  }

  public void setImageSize(int paramInt)
  {
    setImageSize(paramInt, paramInt);
  }

  public void setImageSize(int paramInt1, int paramInt2)
  {
    this.mImageWidth = paramInt1;
    this.mImageHeight = paramInt2;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.example.android.bitmapfun.util.ImageResizer
 * JD-Core Version:    0.6.2
 */