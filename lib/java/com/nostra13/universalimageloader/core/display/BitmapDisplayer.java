package com.nostra13.universalimageloader.core.display;

import android.graphics.Bitmap;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;

public abstract interface BitmapDisplayer
{
  public abstract void display(Bitmap paramBitmap, ImageAware paramImageAware, LoadedFrom paramLoadedFrom);
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.nostra13.universalimageloader.core.display.BitmapDisplayer
 * JD-Core Version:    0.6.2
 */