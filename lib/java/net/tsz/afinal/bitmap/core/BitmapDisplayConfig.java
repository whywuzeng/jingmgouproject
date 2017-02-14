package net.tsz.afinal.bitmap.core;

import android.graphics.Bitmap;
import android.view.animation.Animation;

public class BitmapDisplayConfig
{
  private Animation animation;
  private int animationType;
  private int bitmapHeight;
  private int bitmapWidth;
  private Bitmap loadfailBitmap;
  private Bitmap loadingBitmap;

  public Animation getAnimation()
  {
    return this.animation;
  }

  public int getAnimationType()
  {
    return this.animationType;
  }

  public int getBitmapHeight()
  {
    return this.bitmapHeight;
  }

  public int getBitmapWidth()
  {
    return this.bitmapWidth;
  }

  public Bitmap getLoadfailBitmap()
  {
    return this.loadfailBitmap;
  }

  public Bitmap getLoadingBitmap()
  {
    return this.loadingBitmap;
  }

  public void setAnimation(Animation paramAnimation)
  {
    this.animation = paramAnimation;
  }

  public void setAnimationType(int paramInt)
  {
    this.animationType = paramInt;
  }

  public void setBitmapHeight(int paramInt)
  {
    this.bitmapHeight = paramInt;
  }

  public void setBitmapWidth(int paramInt)
  {
    this.bitmapWidth = paramInt;
  }

  public void setLoadfailBitmap(Bitmap paramBitmap)
  {
    this.loadfailBitmap = paramBitmap;
  }

  public void setLoadingBitmap(Bitmap paramBitmap)
  {
    this.loadingBitmap = paramBitmap;
  }

  public class AnimationType
  {
    public static final int fadeIn = 1;
    public static final int userDefined = 0;

    public AnimationType()
    {
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     net.tsz.afinal.bitmap.core.BitmapDisplayConfig
 * JD-Core Version:    0.6.2
 */