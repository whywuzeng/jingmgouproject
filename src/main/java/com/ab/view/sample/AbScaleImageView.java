package com.ab.view.sample;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class AbScaleImageView extends ImageView
{
  private Bitmap currentBitmap;
  private int imageHeight;
  private int imageWidth;

  public AbScaleImageView(Context paramContext)
  {
    super(paramContext);
    init();
  }

  public AbScaleImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init();
  }

  public AbScaleImageView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init();
  }

  private void init()
  {
    setScaleType(ImageView.ScaleType.CENTER_INSIDE);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    if (this.imageWidth == 0)
    {
      super.onMeasure(paramInt1, paramInt2);
      return;
    }
    setScaleType(ImageView.ScaleType.CENTER_CROP);
    setMeasuredDimension(this.imageWidth, this.imageHeight);
  }

  public void recycle()
  {
    setImageBitmap(null);
    if ((this.currentBitmap == null) || (this.currentBitmap.isRecycled()))
      return;
    this.currentBitmap.recycle();
    this.currentBitmap = null;
  }

  public void setImageBitmap(Bitmap paramBitmap)
  {
    this.currentBitmap = paramBitmap;
    super.setImageBitmap(this.currentBitmap);
  }

  public void setImageDrawable(Drawable paramDrawable)
  {
    super.setImageDrawable(paramDrawable);
  }

  public void setImageHeight(int paramInt)
  {
    this.imageHeight = paramInt;
  }

  public void setImageResource(int paramInt)
  {
    super.setImageResource(paramInt);
  }

  public void setImageWidth(int paramInt)
  {
    this.imageWidth = paramInt;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.sample.AbScaleImageView
 * JD-Core Version:    0.6.2
 */