package com.ab.view.chart;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import java.io.Serializable;
import java.util.List;

public abstract class AbstractChart
  implements Serializable
{
  private static float[] calculateDrawPoints(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, int paramInt1, int paramInt2)
  {
    float f4;
    float f3;
    float f2;
    float f1;
    if (paramFloat2 > paramInt1)
    {
      f4 = (paramFloat4 - paramFloat2) / (paramFloat3 - paramFloat1);
      f3 = (paramInt1 - paramFloat2 + f4 * paramFloat1) / f4;
      f2 = paramInt1;
      if (f3 < 0.0F)
      {
        f1 = 0.0F;
        f2 = paramFloat2 - f4 * paramFloat1;
        if (paramFloat4 <= paramInt1)
          break label294;
        f4 = (paramFloat4 - paramFloat2) / (paramFloat3 - paramFloat1);
        f3 = (paramInt1 - paramFloat2 + f4 * paramFloat1) / f4;
        paramFloat4 = paramInt1;
        if (f3 >= 0.0F)
          break label261;
        paramFloat3 = 0.0F;
        paramFloat4 = paramFloat2 - f4 * paramFloat1;
      }
    }
    while (true)
    {
      return new float[] { f1, f2, paramFloat3, paramFloat4 };
      f1 = f3;
      if (f3 <= paramInt2)
        break;
      f1 = paramInt2;
      f2 = paramInt2 * f4 + paramFloat2 - f4 * paramFloat1;
      break;
      if (paramFloat2 < 0.0F)
      {
        f4 = (paramFloat4 - paramFloat2) / (paramFloat3 - paramFloat1);
        f3 = (-paramFloat2 + f4 * paramFloat1) / f4;
        f2 = 0.0F;
        if (f3 < 0.0F)
        {
          f1 = 0.0F;
          f2 = paramFloat2 - f4 * paramFloat1;
          break;
        }
        f1 = f3;
        if (f3 <= paramInt2)
          break;
        f1 = paramInt2;
        f2 = paramInt2 * f4 + paramFloat2 - f4 * paramFloat1;
        break;
      }
      f1 = paramFloat1;
      f2 = paramFloat2;
      break;
      label261: paramFloat3 = f3;
      if (f3 > paramInt2)
      {
        paramFloat3 = paramInt2;
        paramFloat4 = paramInt2 * f4 + paramFloat2 - f4 * paramFloat1;
        continue;
        label294: if (paramFloat4 < 0.0F)
        {
          f4 = (paramFloat4 - paramFloat2) / (paramFloat3 - paramFloat1);
          f3 = (-paramFloat2 + f4 * paramFloat1) / f4;
          paramFloat4 = 0.0F;
          if (f3 < 0.0F)
          {
            paramFloat3 = 0.0F;
            paramFloat4 = paramFloat2 - f4 * paramFloat1;
          }
          else
          {
            paramFloat3 = f3;
            if (f3 > paramInt2)
            {
              paramFloat3 = paramInt2;
              paramFloat4 = paramInt2 * f4 + paramFloat2 - f4 * paramFloat1;
            }
          }
        }
      }
    }
  }

  private String getFitText(String paramString, float paramFloat, Paint paramPaint)
  {
    String str = paramString;
    int j = paramString.length();
    int i = 0;
    while (true)
    {
      if ((paramPaint.measureText(str) <= paramFloat) || (i >= j))
      {
        if (i == j)
          str = "...";
        return str;
      }
      i += 1;
      str = paramString.substring(0, j - i) + "...";
    }
  }

  public abstract void draw(Canvas paramCanvas, int paramInt1, int paramInt2, int paramInt3, int paramInt4, Paint paramPaint);

  protected void drawBackground(DefaultRenderer paramDefaultRenderer, Canvas paramCanvas, int paramInt1, int paramInt2, int paramInt3, int paramInt4, Paint paramPaint, boolean paramBoolean, int paramInt5)
  {
    if ((paramDefaultRenderer.isApplyBackgroundColor()) || (paramBoolean))
    {
      if (!paramBoolean)
        break label55;
      paramPaint.setColor(paramInt5);
    }
    while (true)
    {
      paramPaint.setStyle(Paint.Style.FILL);
      paramCanvas.drawRect(paramInt1, paramInt2, paramInt1 + paramInt3, paramInt2 + paramInt4, paramPaint);
      return;
      label55: paramPaint.setColor(paramDefaultRenderer.getBackgroundColor());
    }
  }

  protected void drawLabel(Canvas paramCanvas, String paramString, DefaultRenderer paramDefaultRenderer, List<RectF> paramList, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, int paramInt3, int paramInt4, int paramInt5, Paint paramPaint, boolean paramBoolean)
  {
    float f1;
    float f2;
    if (paramDefaultRenderer.isShowLabels())
    {
      paramPaint.setColor(paramInt5);
      double d2 = Math.toRadians(90.0F - (paramFloat4 / 2.0F + paramFloat3));
      double d1 = Math.sin(d2);
      d2 = Math.cos(d2);
      paramInt5 = Math.round(paramInt1 + (float)(paramFloat1 * d1));
      int i = Math.round(paramInt2 + (float)(paramFloat1 * d2));
      int j = Math.round(paramInt1 + (float)(paramFloat2 * d1));
      paramInt1 = Math.round(paramInt2 + (float)(paramFloat2 * d2));
      paramFloat4 = paramDefaultRenderer.getLabelsTextSize();
      paramFloat2 = Math.max(paramFloat4 / 2.0F, 10.0F);
      paramPaint.setTextAlign(Paint.Align.LEFT);
      paramFloat1 = paramFloat2;
      if (paramInt5 > j)
      {
        paramFloat1 = -paramFloat2;
        paramPaint.setTextAlign(Paint.Align.RIGHT);
      }
      f1 = j + paramFloat1;
      paramFloat3 = paramInt1;
      paramFloat2 = paramInt4 - f1;
      if (paramInt5 > j)
        paramFloat2 = f1 - paramInt3;
      paramString = getFitText(paramString, paramFloat2, paramPaint);
      f2 = paramPaint.measureText(paramString);
      paramInt1 = 0;
      paramFloat2 = paramFloat3;
      if ((paramInt1 == 0) && (paramBoolean))
        break label328;
      if (!paramBoolean)
        break label437;
      paramInt1 = (int)(paramFloat2 - paramFloat4 / 2.0F);
      paramCanvas.drawLine(paramInt5, i, j, paramInt1, paramPaint);
      paramCanvas.drawLine(j, paramInt1, j + paramFloat1, paramInt1, paramPaint);
    }
    while (true)
    {
      paramCanvas.drawText(paramString, f1, paramFloat2, paramPaint);
      if (paramBoolean)
        paramList.add(new RectF(f1, paramFloat2, f1 + f2, paramFloat2 + paramFloat4));
      return;
      label328: paramInt2 = 0;
      paramInt3 = paramList.size();
      paramInt1 = 0;
      label343: if ((paramInt1 >= paramInt3) || (paramInt2 != 0))
        if (paramInt2 == 0)
          break label431;
      label431: for (paramInt1 = 0; ; paramInt1 = 1)
      {
        break;
        paramDefaultRenderer = (RectF)paramList.get(paramInt1);
        paramFloat3 = paramFloat2;
        if (paramDefaultRenderer.intersects(f1, paramFloat2, f1 + f2, paramFloat2 + paramFloat4))
        {
          paramInt2 = 1;
          paramFloat3 = Math.max(paramFloat2, paramDefaultRenderer.bottom);
        }
        paramInt1 += 1;
        paramFloat2 = paramFloat3;
        break label343;
      }
      label437: paramPaint.setTextAlign(Paint.Align.CENTER);
    }
  }

  protected int drawLegend(Canvas paramCanvas, DefaultRenderer paramDefaultRenderer, String[] paramArrayOfString, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, Paint paramPaint, boolean paramBoolean)
  {
    float f1 = 32.0F;
    float f2 = f1;
    float f4;
    if (paramDefaultRenderer.isShowLegend())
    {
      f2 = paramInt1;
      f4 = paramInt3 + paramInt5 - paramInt6 + 32.0F;
      paramPaint.setTextAlign(Paint.Align.LEFT);
      paramPaint.setTextSize(paramDefaultRenderer.getLegendTextSize());
      paramInt6 = Math.min(paramArrayOfString.length, paramDefaultRenderer.getSeriesRendererCount());
      paramInt3 = 0;
      if (paramInt3 >= paramInt6)
        f2 = f1;
    }
    else
    {
      return Math.round(paramDefaultRenderer.getLegendTextSize() + f2);
    }
    float f9 = getLegendShapeWidth(paramInt3);
    String str2 = paramArrayOfString[paramInt3];
    label125: float[] arrayOfFloat;
    float f3;
    int i;
    if (paramArrayOfString.length == paramDefaultRenderer.getSeriesRendererCount())
    {
      paramPaint.setColor(paramDefaultRenderer.getSeriesRendererAt(paramInt3).getColor());
      arrayOfFloat = new float[str2.length()];
      paramPaint.getTextWidths(str2, arrayOfFloat);
      f3 = 0.0F;
      i = arrayOfFloat.length;
      paramInt5 = 0;
    }
    while (true)
    {
      if (paramInt5 >= i)
      {
        float f10 = 10.0F + f9 + f3;
        float f8 = f2 + f10;
        float f6 = f2;
        float f5 = f4;
        float f7 = f8;
        f3 = f1;
        if (paramInt3 > 0)
        {
          f6 = f2;
          f5 = f4;
          f7 = f8;
          f3 = f1;
          if (getExceed(f8, paramDefaultRenderer, paramInt2, paramInt4))
          {
            f6 = paramInt1;
            f5 = f4 + paramDefaultRenderer.getLegendTextSize();
            f3 = f1 + paramDefaultRenderer.getLegendTextSize();
            f7 = f6 + f10;
          }
        }
        String str1 = str2;
        if (getExceed(f7, paramDefaultRenderer, paramInt2, paramInt4))
        {
          f1 = paramInt2 - f6 - f9 - 10.0F;
          if (isVertical(paramDefaultRenderer))
            f1 = paramInt4 - f6 - f9 - 10.0F;
          str1 = str2.substring(0, paramPaint.breakText(str2, true, f1, arrayOfFloat)) + "...";
        }
        if (!paramBoolean)
        {
          drawLegendShape(paramCanvas, paramDefaultRenderer.getSeriesRendererAt(paramInt3), f6, f5, paramInt3, paramPaint);
          drawString(paramCanvas, str1, f6 + f9 + 5.0F, f5 + 5.0F, paramPaint);
        }
        f2 = f6 + f10;
        paramInt3 += 1;
        f4 = f5;
        f1 = f3;
        break;
        paramPaint.setColor(-3355444);
        break label125;
      }
      f3 += arrayOfFloat[paramInt5];
      paramInt5 += 1;
    }
  }

  public abstract void drawLegendShape(Canvas paramCanvas, SimpleSeriesRenderer paramSimpleSeriesRenderer, float paramFloat1, float paramFloat2, int paramInt, Paint paramPaint);

  protected void drawPath(Canvas paramCanvas, float[] paramArrayOfFloat, Paint paramPaint, boolean paramBoolean)
  {
    Path localPath = new Path();
    int j = paramCanvas.getHeight();
    int k = paramCanvas.getWidth();
    if (paramArrayOfFloat.length < 4)
      return;
    float[] arrayOfFloat = calculateDrawPoints(paramArrayOfFloat[0], paramArrayOfFloat[1], paramArrayOfFloat[2], paramArrayOfFloat[3], j, k);
    localPath.moveTo(arrayOfFloat[0], arrayOfFloat[1]);
    localPath.lineTo(arrayOfFloat[2], arrayOfFloat[3]);
    int i = 4;
    if (i >= paramArrayOfFloat.length)
    {
      if (paramBoolean)
        localPath.lineTo(paramArrayOfFloat[0], paramArrayOfFloat[1]);
      paramCanvas.drawPath(localPath, paramPaint);
      return;
    }
    if (((paramArrayOfFloat[(i - 1)] < 0.0F) && (paramArrayOfFloat[(i + 1)] < 0.0F)) || ((paramArrayOfFloat[(i - 1)] > j) && (paramArrayOfFloat[(i + 1)] > j)));
    while (true)
    {
      i += 2;
      break;
      arrayOfFloat = calculateDrawPoints(paramArrayOfFloat[(i - 2)], paramArrayOfFloat[(i - 1)], paramArrayOfFloat[i], paramArrayOfFloat[(i + 1)], j, k);
      if (!paramBoolean)
        localPath.moveTo(arrayOfFloat[0], arrayOfFloat[1]);
      localPath.lineTo(arrayOfFloat[2], arrayOfFloat[3]);
    }
  }

  protected void drawString(Canvas paramCanvas, String paramString, float paramFloat1, float paramFloat2, Paint paramPaint)
  {
    paramString = paramString.split("\n");
    Rect localRect = new Rect();
    int j = 0;
    int i = 0;
    while (true)
    {
      if (i >= paramString.length)
        return;
      paramCanvas.drawText(paramString[i], paramFloat1, j + paramFloat2, paramPaint);
      paramPaint.getTextBounds(paramString[i], 0, paramString[i].length(), localRect);
      j = localRect.height() + j + 5;
      i += 1;
    }
  }

  protected boolean getExceed(float paramFloat, DefaultRenderer paramDefaultRenderer, int paramInt1, int paramInt2)
  {
    if (paramFloat > paramInt1);
    for (boolean bool = true; ; bool = false)
    {
      if (isVertical(paramDefaultRenderer))
      {
        if (paramFloat <= paramInt2)
          break;
        bool = true;
      }
      return bool;
    }
    return false;
  }

  protected String getLabel(double paramDouble)
  {
    if (paramDouble == Math.round(paramDouble))
      return Math.round(paramDouble);
    return paramDouble;
  }

  public abstract int getLegendShapeWidth(int paramInt);

  protected int getLegendSize(DefaultRenderer paramDefaultRenderer, int paramInt, float paramFloat)
  {
    int j = paramDefaultRenderer.getLegendHeight();
    int i = j;
    if (paramDefaultRenderer.isShowLegend())
    {
      i = j;
      if (j == 0)
        i = paramInt;
    }
    paramInt = i;
    if (!paramDefaultRenderer.isShowLegend())
    {
      paramInt = i;
      if (paramDefaultRenderer.isShowLabels())
        paramInt = (int)(paramDefaultRenderer.getLabelsTextSize() * 4.0F / 3.0F + paramFloat);
    }
    return paramInt;
  }

  public SeriesSelection getSeriesAndPointForScreenCoordinate(Point paramPoint)
  {
    return null;
  }

  public boolean isNullValue(double paramDouble)
  {
    return (Double.isNaN(paramDouble)) || (Double.isInfinite(paramDouble)) || (paramDouble == 1.7976931348623157E+308D);
  }

  public boolean isVertical(DefaultRenderer paramDefaultRenderer)
  {
    return ((paramDefaultRenderer instanceof XYMultipleSeriesRenderer)) && (((XYMultipleSeriesRenderer)paramDefaultRenderer).getOrientation() == XYMultipleSeriesRenderer.Orientation.VERTICAL);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.chart.AbstractChart
 * JD-Core Version:    0.6.2
 */