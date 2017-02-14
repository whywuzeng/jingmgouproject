package com.ab.view.app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;

public class AbCompassView extends View
{
  private int h = 40;
  private Drawable mCompassDrawable = null;
  private float mDirection = 0.0F;
  private float posCompassX = 20.0F;
  private float posCompassY = 20.0F;
  private int w = 40;

  public AbCompassView(Context paramContext)
  {
    super(paramContext);
  }

  public AbCompassView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  private void drawPictures(Canvas paramCanvas)
  {
    if (this.mCompassDrawable != null)
    {
      this.mCompassDrawable.setBounds(0, 0, this.w, this.h);
      paramCanvas.save();
      paramCanvas.rotate(this.mDirection, this.posCompassX, this.posCompassY);
      this.mCompassDrawable.draw(paramCanvas);
      paramCanvas.restore();
    }
  }

  public float getDirection()
  {
    return this.mDirection;
  }

  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
  }

  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
  }

  protected void onDraw(Canvas paramCanvas)
  {
    this.w = paramCanvas.getWidth();
    this.h = paramCanvas.getHeight();
    this.posCompassX = (this.w / 2);
    this.posCompassY = (this.h / 2);
    drawPictures(paramCanvas);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    setMeasuredDimension(View.MeasureSpec.getSize(paramInt1), paramInt2);
  }

  public void setCompassDrawable(Drawable paramDrawable)
  {
    this.mCompassDrawable = paramDrawable;
  }

  public void setDirection(float paramFloat)
  {
    this.mDirection = paramFloat;
    invalidate();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.app.AbCompassView
 * JD-Core Version:    0.6.2
 */