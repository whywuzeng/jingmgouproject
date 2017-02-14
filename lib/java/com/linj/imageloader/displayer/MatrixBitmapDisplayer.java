package com.linj.imageloader.displayer;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class MatrixBitmapDisplayer
  implements BitmapDisplayer
{
  public void display(int paramInt, ImageView paramImageView)
  {
    paramImageView.setScaleType(ImageView.ScaleType.CENTER);
    paramImageView.setImageResource(paramInt);
  }

  public void display(Bitmap paramBitmap, ImageView paramImageView)
  {
    paramImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
    paramImageView.setImageBitmap(paramBitmap);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.linj.imageloader.displayer.MatrixBitmapDisplayer
 * JD-Core Version:    0.6.2
 */