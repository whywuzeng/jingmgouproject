package com.ismartgo.app.grid.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ImageView;

public class BorderImageView extends ImageView
{
  private float borderwidth;
  private int co;

  public BorderImageView(Context paramContext)
  {
    super(paramContext);
  }

  public BorderImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public BorderImageView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    Rect localRect = paramCanvas.getClipBounds();
    Paint localPaint = new Paint();
    localPaint.setColor(this.co);
    localPaint.setStyle(Paint.Style.STROKE);
    localPaint.setStrokeWidth(this.borderwidth);
    paramCanvas.drawRect(localRect, localPaint);
  }

  public void setBorderWidth(float paramFloat)
  {
    this.borderwidth = paramFloat;
  }

  public void setColour(int paramInt)
  {
    this.co = paramInt;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.grid.view.BorderImageView
 * JD-Core Version:    0.6.2
 */