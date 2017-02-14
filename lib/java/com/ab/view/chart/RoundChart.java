package com.ab.view.chart;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;

public abstract class RoundChart extends AbstractChart
{
  protected static final int NO_VALUE = 2147483647;
  protected static final int SHAPE_WIDTH = 10;
  protected int mCenterX = 2147483647;
  protected int mCenterY = 2147483647;
  protected CategorySeries mDataset;
  protected DefaultRenderer mRenderer;

  public RoundChart(CategorySeries paramCategorySeries, DefaultRenderer paramDefaultRenderer)
  {
    this.mDataset = paramCategorySeries;
    this.mRenderer = paramDefaultRenderer;
  }

  public void drawLegendShape(Canvas paramCanvas, SimpleSeriesRenderer paramSimpleSeriesRenderer, float paramFloat1, float paramFloat2, int paramInt, Paint paramPaint)
  {
    paramCanvas.drawRect(paramFloat1, paramFloat2 - 5.0F, paramFloat1 + 10.0F, paramFloat2 + 5.0F, paramPaint);
  }

  public void drawTitle(Canvas paramCanvas, int paramInt1, int paramInt2, int paramInt3, Paint paramPaint)
  {
    if (this.mRenderer.isShowLabels())
    {
      paramPaint.setColor(this.mRenderer.getLabelsColor());
      paramPaint.setTextAlign(Paint.Align.CENTER);
      paramPaint.setTextSize(this.mRenderer.getChartTitleTextSize());
      drawString(paramCanvas, this.mRenderer.getChartTitle(), paramInt3 / 2 + paramInt1, paramInt2 + this.mRenderer.getChartTitleTextSize(), paramPaint);
    }
  }

  public int getCenterX()
  {
    return this.mCenterX;
  }

  public int getCenterY()
  {
    return this.mCenterY;
  }

  public int getLegendShapeWidth(int paramInt)
  {
    return 10;
  }

  public DefaultRenderer getRenderer()
  {
    return this.mRenderer;
  }

  public void setCenterX(int paramInt)
  {
    this.mCenterX = paramInt;
  }

  public void setCenterY(int paramInt)
  {
    this.mCenterY = paramInt;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.chart.RoundChart
 * JD-Core Version:    0.6.2
 */