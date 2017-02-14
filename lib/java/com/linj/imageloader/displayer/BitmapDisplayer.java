package com.linj.imageloader.displayer;

import android.graphics.Bitmap;
import android.widget.ImageView;

public abstract interface BitmapDisplayer
{
  public abstract void display(int paramInt, ImageView paramImageView);

  public abstract void display(Bitmap paramBitmap, ImageView paramImageView);
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.linj.imageloader.displayer.BitmapDisplayer
 * JD-Core Version:    0.6.2
 */