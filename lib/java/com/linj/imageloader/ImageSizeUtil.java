package com.linj.imageloader;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory.Options;
import android.util.DisplayMetrics;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import java.lang.reflect.Field;

public class ImageSizeUtil
{
  public static int caculateInSampleSize(BitmapFactory.Options paramOptions, int paramInt1, int paramInt2)
  {
    int j = paramOptions.outWidth;
    int k = paramOptions.outHeight;
    int i = 1;
    if ((j > paramInt1) || (k > paramInt2))
      i = Math.max(Math.round(j * 1.0F / paramInt1), Math.round(k * 1.0F / paramInt2));
    return i;
  }

  private static int getImageViewFieldValue(Object paramObject, String paramString)
  {
    int j = 0;
    try
    {
      paramString = ImageView.class.getDeclaredField(paramString);
      paramString.setAccessible(true);
      int k = paramString.getInt(paramObject);
      int i = j;
      if (k > 0)
      {
        i = j;
        if (k < 2147483647)
          i = k;
      }
      return i;
    }
    catch (Exception paramObject)
    {
    }
    return 0;
  }

  public static ImageSize getImageViewSize(ImageView paramImageView)
  {
    ImageSize localImageSize = new ImageSize();
    DisplayMetrics localDisplayMetrics = paramImageView.getContext().getResources().getDisplayMetrics();
    ViewGroup.LayoutParams localLayoutParams = paramImageView.getLayoutParams();
    int j = paramImageView.getWidth();
    int i = j;
    if (j <= 0)
      i = localLayoutParams.width;
    j = i;
    if (i <= 0)
      j = getImageViewFieldValue(paramImageView, "mMaxWidth");
    int k = j;
    if (j <= 0)
      k = localDisplayMetrics.widthPixels;
    j = paramImageView.getHeight();
    i = j;
    if (j <= 0)
      i = localLayoutParams.height;
    j = i;
    if (i <= 0)
      j = getImageViewFieldValue(paramImageView, "mMaxHeight");
    i = j;
    if (j <= 0)
      i = localDisplayMetrics.heightPixels;
    localImageSize.width = k;
    localImageSize.height = i;
    return localImageSize;
  }

  public static class ImageSize
  {
    int height;
    int width;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.linj.imageloader.ImageSizeUtil
 * JD-Core Version:    0.6.2
 */