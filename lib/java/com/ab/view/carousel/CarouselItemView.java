package com.ab.view.carousel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CarouselItemView extends LinearLayout
  implements Comparable<CarouselItemView>
{
  private float currentAngle;
  private boolean drawn;
  private int index;
  private float itemX;
  private float itemY;
  private float itemZ;
  private Matrix mCIMatrix;
  private ImageView mImage;
  private TextView mText;

  public CarouselItemView(Context paramContext)
  {
    super(paramContext);
  }

  public int compareTo(CarouselItemView paramCarouselItemView)
  {
    return (int)(paramCarouselItemView.itemZ - this.itemZ);
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

  public String getName()
  {
    return this.mText.getText().toString();
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

  public void setImageBitmap(Bitmap paramBitmap)
  {
    this.mImage.setImageBitmap(paramBitmap);
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

  public void setText(String paramString)
  {
    this.mText.setText(paramString);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.carousel.CarouselItemView
 * JD-Core Version:    0.6.2
 */