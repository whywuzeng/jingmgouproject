package com.ismartgo.app.grid.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class BorderLinearLayout extends LinearLayout
{
  public BorderLinearLayout(Context paramContext)
  {
    super(paramContext);
  }

  public BorderLinearLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public BorderLinearLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    Rect localRect = paramCanvas.getClipBounds();
    Paint localPaint = new Paint();
    localPaint.setColor(Color.parseColor("#E7E7E7"));
    localPaint.setStyle(Paint.Style.STROKE);
    localPaint.setStrokeWidth(1.5F);
    paramCanvas.drawRect(localRect, localPaint);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.grid.view.BorderLinearLayout
 * JD-Core Version:    0.6.2
 */