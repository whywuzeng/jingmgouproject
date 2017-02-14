package com.ismartgo.app.waterfall.tools;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ImageView;

public class ImageViewBorder extends ImageView
{
  public ImageViewBorder(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    Rect localRect = paramCanvas.getClipBounds();
    localRect.bottom -= 1;
    localRect.right -= 1;
    Paint localPaint = new Paint();
    localPaint.setStyle(Paint.Style.STROKE);
    paramCanvas.drawRect(localRect, localPaint);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.waterfall.tools.ImageViewBorder
 * JD-Core Version:    0.6.2
 */