package com.ismartgo.app.grid;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.ImageView;

public class DynamicHeightImageView extends ImageView
{
  public static float radius = 2.0F;
  Path clipPath = new Path();
  private double mHeightRatio;
  RectF rect = new RectF(0.0F, 0.0F, getWidth(), getHeight());

  @TargetApi(11)
  public DynamicHeightImageView(Context paramContext)
  {
    super(paramContext);
    if (Build.VERSION.SDK_INT < 18)
      setLayerType(1, null);
  }

  @TargetApi(11)
  public DynamicHeightImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    if (Build.VERSION.SDK_INT < 18)
      setLayerType(1, null);
  }

  public double getHeightRatio()
  {
    return this.mHeightRatio;
  }

  protected void onDraw(Canvas paramCanvas)
  {
    this.rect.left = 0.0F;
    this.rect.top = 0.0F;
    this.rect.right = getWidth();
    this.rect.bottom = getHeight();
    this.clipPath.addRoundRect(this.rect, radius, radius, Path.Direction.CW);
    paramCanvas.clipPath(this.clipPath);
    super.onDraw(paramCanvas);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    if (this.mHeightRatio > 0.0D)
    {
      paramInt1 = View.MeasureSpec.getSize(paramInt1);
      setMeasuredDimension(paramInt1, (int)(paramInt1 * this.mHeightRatio));
      return;
    }
    super.onMeasure(paramInt1, paramInt2);
  }

  public void setHeightRatio(double paramDouble)
  {
    if (paramDouble != this.mHeightRatio)
    {
      this.mHeightRatio = paramDouble;
      requestLayout();
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.grid.DynamicHeightImageView
 * JD-Core Version:    0.6.2
 */