package com.ab.view.carousel;

import android.content.Context;
import android.graphics.Matrix;
import android.util.Log;
import android.widget.ImageView;

public class CarouselItemImage extends ImageView
  implements Comparable<CarouselItemImage>
{
  private float currentAngle;
  private boolean drawn;
  private int index;
  private float itemX;
  private float itemY;
  private float itemZ;
  private Matrix mCIMatrix;

  public CarouselItemImage(Context paramContext)
  {
    super(paramContext);
  }

  public int compareTo(CarouselItemImage paramCarouselItemImage)
  {
    return (int)(paramCarouselItemImage.itemZ - this.itemZ);
  }

  Matrix getCIMatrix()
  {
    return this.mCIMatrix;
  }

  public float getCurrentAngle()
  {
    return this.currentAngle;
  }

  public int getIndex()
  {
    return this.index;
  }

  public float getItemX()
  {
    return this.itemX;
  }

  public float getItemY()
  {
    return this.itemY;
  }

  public float getItemZ()
  {
    return this.itemZ;
  }

  public boolean isDrawn()
  {
    return this.drawn;
  }

  void setCIMatrix(Matrix paramMatrix)
  {
    this.mCIMatrix = paramMatrix;
  }

  public void setCurrentAngle(float paramFloat)
  {
    if ((this.index == 0) && (paramFloat > 5.0F))
      Log.d("", "");
    this.currentAngle = paramFloat;
  }

  public void setDrawn(boolean paramBoolean)
  {
    this.drawn = paramBoolean;
  }

  public void setIndex(int paramInt)
  {
    this.index = paramInt;
  }

  public void setItemX(float paramFloat)
  {
    this.itemX = paramFloat;
  }

  public void setItemY(float paramFloat)
  {
    this.itemY = paramFloat;
  }

  public void setItemZ(float paramFloat)
  {
    this.itemZ = paramFloat;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.carousel.CarouselItemImage
 * JD-Core Version:    0.6.2
 */