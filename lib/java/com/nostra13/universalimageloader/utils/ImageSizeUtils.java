package com.nostra13.universalimageloader.utils;

import android.opengl.GLES10;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;

public final class ImageSizeUtils
{
  private static final int DEFAULT_MAX_BITMAP_DIMENSION = 2048;
  private static ImageSize maxBitmapSize = new ImageSize(i, i);

  static
  {
    int[] arrayOfInt = new int[1];
    GLES10.glGetIntegerv(3379, arrayOfInt, 0);
    int i = Math.max(arrayOfInt[0], 2048);
  }

  public static int computeImageSampleSize(ImageSize paramImageSize1, ImageSize paramImageSize2, ViewScaleType paramViewScaleType, boolean paramBoolean)
  {
    int m = paramImageSize1.getWidth();
    int n = paramImageSize1.getHeight();
    int i1 = paramImageSize2.getWidth();
    int i2 = paramImageSize2.getHeight();
    int j = 1;
    int i = 1;
    int k = 1;
    switch (1.$SwitchMap$com$nostra13$universalimageloader$core$assist$ViewScaleType[paramViewScaleType.ordinal()])
    {
    default:
      i = k;
    case 1:
    case 2:
    }
    while (true)
    {
      j = i;
      if (i < 1)
        j = 1;
      return considerMaxTextureSize(m, n, j, paramBoolean);
      int i3;
      if (paramBoolean)
      {
        k = m / 2;
        i3 = n / 2;
        while (true)
        {
          if (k / j <= i1)
          {
            i = j;
            if (i3 / j <= i2)
              break;
          }
          j *= 2;
        }
      }
      i = Math.max(m / i1, n / i2);
      continue;
      if (paramBoolean)
      {
        k = m / 2;
        i3 = n / 2;
        j = i;
        while (true)
        {
          i = j;
          if (k / j <= i1)
            break;
          i = j;
          if (i3 / j <= i2)
            break;
          j *= 2;
        }
      }
      i = Math.min(m / i1, n / i2);
    }
  }

  public static float computeImageScale(ImageSize paramImageSize1, ImageSize paramImageSize2, ViewScaleType paramViewScaleType, boolean paramBoolean)
  {
    int k = paramImageSize1.getWidth();
    int m = paramImageSize1.getHeight();
    int j = paramImageSize2.getWidth();
    int i = paramImageSize2.getHeight();
    float f1 = k / j;
    float f2 = m / i;
    if (((paramViewScaleType == ViewScaleType.FIT_INSIDE) && (f1 >= f2)) || ((paramViewScaleType == ViewScaleType.CROP) && (f1 < f2)))
      i = (int)(m / f1);
    while (true)
    {
      f2 = 1.0F;
      if ((paramBoolean) || (j >= k) || (i >= m))
      {
        f1 = f2;
        if (paramBoolean)
        {
          f1 = f2;
          if (j != k)
          {
            f1 = f2;
            if (i == m);
          }
        }
      }
      else
      {
        f1 = j / k;
      }
      return f1;
      j = (int)(k / f2);
    }
  }

  public static int computeMinImageSampleSize(ImageSize paramImageSize)
  {
    int i = paramImageSize.getWidth();
    int j = paramImageSize.getHeight();
    int k = maxBitmapSize.getWidth();
    int m = maxBitmapSize.getHeight();
    return Math.max((int)Math.ceil(i / k), (int)Math.ceil(j / m));
  }

  private static int considerMaxTextureSize(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    int i = maxBitmapSize.getWidth();
    int j = maxBitmapSize.getHeight();
    while ((paramInt1 / paramInt3 > i) || (paramInt2 / paramInt3 > j))
      if (paramBoolean)
        paramInt3 *= 2;
      else
        paramInt3 += 1;
    return paramInt3;
  }

  public static ImageSize defineTargetSizeForView(ImageAware paramImageAware, ImageSize paramImageSize)
  {
    int j = paramImageAware.getWidth();
    int i = j;
    if (j <= 0)
      i = paramImageSize.getWidth();
    int k = paramImageAware.getHeight();
    j = k;
    if (k <= 0)
      j = paramImageSize.getHeight();
    return new ImageSize(i, j);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.nostra13.universalimageloader.utils.ImageSizeUtils
 * JD-Core Version:    0.6.2
 */