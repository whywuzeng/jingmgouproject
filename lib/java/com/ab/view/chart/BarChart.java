package com.ab.view.chart;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;

public class BarChart extends XYChart
{
  private static final int SHAPE_WIDTH = 12;
  public static final String TYPE = "Bar";
  protected Type mType = Type.DEFAULT;

  BarChart()
  {
  }

  BarChart(Type paramType)
  {
    this.mType = paramType;
  }

  public BarChart(XYMultipleSeriesDataset paramXYMultipleSeriesDataset, XYMultipleSeriesRenderer paramXYMultipleSeriesRenderer, Type paramType)
  {
    super(paramXYMultipleSeriesDataset, paramXYMultipleSeriesRenderer);
    this.mType = paramType;
  }

  private void drawBar(Canvas paramCanvas, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, int paramInt1, int paramInt2, Paint paramPaint)
  {
    SimpleSeriesRenderer localSimpleSeriesRenderer = this.mRenderer.getSeriesRendererAt(paramInt2);
    if (localSimpleSeriesRenderer.isGradientEnabled())
    {
      f1 = (float)toScreenPoint(new double[] { 0.0D, localSimpleSeriesRenderer.getGradientStopValue() }, paramInt1)[1];
      float f2 = (float)toScreenPoint(new double[] { 0.0D, localSimpleSeriesRenderer.getGradientStartValue() }, paramInt1)[1];
      float f3 = Math.max(f1, Math.min(paramFloat2, paramFloat4));
      float f4 = Math.min(f2, Math.max(paramFloat2, paramFloat4));
      int i = localSimpleSeriesRenderer.getGradientStopColor();
      int j = localSimpleSeriesRenderer.getGradientStartColor();
      paramInt1 = j;
      paramInt2 = i;
      if (paramFloat2 < f1)
      {
        paramPaint.setColor(i);
        paramCanvas.drawRect(Math.round(paramFloat1), Math.round(paramFloat2), Math.round(paramFloat3), Math.round(f3), paramPaint);
        if (paramFloat4 <= f2)
          break label285;
        paramPaint.setColor(j);
        paramCanvas.drawRect(Math.round(paramFloat1), Math.round(f4), Math.round(paramFloat3), Math.round(paramFloat4), paramPaint);
      }
      while (true)
      {
        paramPaint = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, new int[] { paramInt1, paramInt2 });
        paramPaint.setBounds(Math.round(paramFloat1), Math.round(f3), Math.round(paramFloat3), Math.round(f4));
        paramPaint.draw(paramCanvas);
        return;
        paramInt2 = getGradientPartialColor(i, j, (f2 - f3) / (f2 - f1));
        break;
        label285: paramInt1 = getGradientPartialColor(j, i, (f4 - f1) / (f2 - f1));
      }
    }
    float f1 = paramFloat4;
    if (Math.abs(paramFloat2 - paramFloat4) < 1.0F)
      if (paramFloat2 >= paramFloat4)
        break label366;
    label366: for (f1 = paramFloat2 + 1.0F; ; f1 = paramFloat2 - 1.0F)
    {
      paramCanvas.drawRect(Math.round(paramFloat1), Math.round(paramFloat2), Math.round(paramFloat3), Math.round(f1), paramPaint);
      return;
    }
  }

  private int getGradientPartialColor(int paramInt1, int paramInt2, float paramFloat)
  {
    return Color.argb(Math.round(Color.alpha(paramInt1) * paramFloat + (1.0F - paramFloat) * Color.alpha(paramInt2)), Math.round(Color.red(paramInt1) * paramFloat + (1.0F - paramFloat) * Color.red(paramInt2)), Math.round(Color.green(paramInt1) * paramFloat + (1.0F - paramFloat) * Color.green(paramInt2)), Math.round(Color.blue(paramInt1) * paramFloat + (1.0F - paramFloat) * Color.blue(paramInt2)));
  }

  protected ClickableArea[] clickableAreasForPoints(float[] paramArrayOfFloat, double[] paramArrayOfDouble, float paramFloat, int paramInt1, int paramInt2)
  {
    int i = this.mDataset.getSeriesCount();
    int j = paramArrayOfFloat.length;
    ClickableArea[] arrayOfClickableArea = new ClickableArea[j / 2];
    float f1 = getHalfDiffX(paramArrayOfFloat, j, i);
    paramInt2 = 0;
    if (paramInt2 >= j)
      return arrayOfClickableArea;
    float f3 = paramArrayOfFloat[paramInt2];
    float f2 = paramArrayOfFloat[(paramInt2 + 1)];
    if (this.mType == Type.STACKED)
      arrayOfClickableArea[(paramInt2 / 2)] = new ClickableArea(new RectF(f3 - f1, f2, f3 + f1, paramFloat), paramArrayOfDouble[paramInt2], paramArrayOfDouble[(paramInt2 + 1)]);
    while (true)
    {
      paramInt2 += 2;
      break;
      f3 = f3 - i * f1 + paramInt1 * 2 * f1;
      arrayOfClickableArea[(paramInt2 / 2)] = new ClickableArea(new RectF(f3, f2, 2.0F * f1 + f3, paramFloat), paramArrayOfDouble[paramInt2], paramArrayOfDouble[(paramInt2 + 1)]);
    }
  }

  protected void drawBar(Canvas paramCanvas, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, int paramInt1, int paramInt2, Paint paramPaint)
  {
    int i = this.mDataset.getSeriesAt(paramInt2).getScaleNumber();
    if (this.mType == Type.STACKED)
    {
      drawBar(paramCanvas, paramFloat1 - paramFloat5, paramFloat4, paramFloat3 + paramFloat5, paramFloat2, i, paramInt2, paramPaint);
      return;
    }
    paramFloat1 = paramFloat1 - paramInt1 * paramFloat5 + paramInt2 * 2 * paramFloat5;
    drawBar(paramCanvas, paramFloat1, paramFloat4, paramFloat1 + 2.0F * paramFloat5, paramFloat2, i, paramInt2, paramPaint);
  }

  protected void drawChartValuesText(Canvas paramCanvas, XYSeries paramXYSeries, SimpleSeriesRenderer paramSimpleSeriesRenderer, Paint paramPaint, float[] paramArrayOfFloat, int paramInt1, int paramInt2)
  {
    int j = this.mDataset.getSeriesCount();
    float f3 = getHalfDiffX(paramArrayOfFloat, paramArrayOfFloat.length, j);
    int i = 0;
    if (i >= paramArrayOfFloat.length)
      return;
    double d = paramXYSeries.getY(paramInt2 + i / 2);
    float f1;
    if (!isNullValue(d))
    {
      float f2 = paramArrayOfFloat[i];
      f1 = f2;
      if (this.mType == Type.DEFAULT)
        f1 = f2 + (paramInt1 * 2 * f3 - (j - 1.5F) * f3);
      if (d <= 0.0D)
        break label144;
      drawText(paramCanvas, getLabel(d), f1, paramArrayOfFloat[(i + 1)] - paramSimpleSeriesRenderer.getChartValuesSpacing(), paramPaint, 0.0F);
    }
    while (true)
    {
      i += 2;
      break;
      label144: if (d < 0.0D)
        drawText(paramCanvas, getLabel(d), f1, paramArrayOfFloat[(i + 1)] + paramSimpleSeriesRenderer.getChartValuesTextSize() + paramSimpleSeriesRenderer.getChartValuesSpacing() - 3.0F, paramPaint, 0.0F);
      else if ((d == 0.0D) && (this.mRenderer.isDisplayValue0()))
        drawText(paramCanvas, getLabel(d), f1, paramArrayOfFloat[(i + 1)] - paramSimpleSeriesRenderer.getChartValuesSpacing(), paramPaint, 0.0F);
    }
  }

  public void drawLegendShape(Canvas paramCanvas, SimpleSeriesRenderer paramSimpleSeriesRenderer, float paramFloat1, float paramFloat2, int paramInt, Paint paramPaint)
  {
    paramCanvas.drawRect(paramFloat1, paramFloat2 - 6.0F, paramFloat1 + 12.0F, paramFloat2 + 6.0F, paramPaint);
  }

  public void drawSeries(Canvas paramCanvas, Paint paramPaint, float[] paramArrayOfFloat, int[] paramArrayOfInt, SimpleSeriesRenderer paramSimpleSeriesRenderer, float paramFloat, int paramInt1, int paramInt2)
  {
    int i = this.mDataset.getSeriesCount();
    int j = paramArrayOfFloat.length;
    paramPaint.setColor(paramSimpleSeriesRenderer.getColor());
    paramPaint.setStyle(Paint.Style.FILL);
    float f1 = getHalfDiffX(paramArrayOfFloat, j, i);
    paramInt2 = 0;
    while (true)
    {
      if (paramInt2 >= j)
      {
        paramPaint.setColor(paramSimpleSeriesRenderer.getColor());
        return;
      }
      float f2 = paramArrayOfFloat[paramInt2];
      drawBar(paramCanvas, f2, paramFloat, f2, paramArrayOfFloat[(paramInt2 + 1)], f1, i, paramInt1, paramPaint);
      paramInt2 += 2;
    }
  }

  public String getChartType()
  {
    return "Bar";
  }

  protected float getCoeficient()
  {
    return 1.0F;
  }

  public double getDefaultMinimum()
  {
    return 0.0D;
  }

  protected float getHalfDiffX(float[] paramArrayOfFloat, int paramInt1, int paramInt2)
  {
    int i = paramInt1;
    if (paramInt1 > 2)
      i = paramInt1 - 2;
    float f2 = (paramArrayOfFloat[(paramInt1 - 2)] - paramArrayOfFloat[0]) / i;
    float f1 = f2;
    if (f2 == 0.0F)
      f1 = 10.0F;
    f2 = f1;
    if (this.mType != Type.STACKED)
      f2 = f1 / paramInt2;
    return (float)(f2 / (getCoeficient() * (1.0D + this.mRenderer.getBarSpacing())));
  }

  public int getLegendShapeWidth(int paramInt)
  {
    return 12;
  }

  protected boolean isRenderNullValues()
  {
    return true;
  }

  public static enum Type
  {
    DEFAULT, STACKED;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.chart.BarChart
 * JD-Core Version:    0.6.2
 */