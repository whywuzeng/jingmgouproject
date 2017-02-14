package com.ab.view.chart;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.Log;

public class ScatterChart extends XYChart
{
  private static final int SHAPE_WIDTH = 10;
  private static final float SIZE = 3.0F;
  public static final String TYPE = "Scatter";
  private float size = 3.0F;

  ScatterChart()
  {
  }

  public ScatterChart(XYMultipleSeriesDataset paramXYMultipleSeriesDataset, XYMultipleSeriesRenderer paramXYMultipleSeriesRenderer)
  {
    super(paramXYMultipleSeriesDataset, paramXYMultipleSeriesRenderer);
    Log.d("ScatterChart", "调用父类XYChart的XYMultipleSeriesDataset 设置2");
    this.size = paramXYMultipleSeriesRenderer.getPointSize();
  }

  private void drawCircle(Canvas paramCanvas, Paint paramPaint, float paramFloat1, float paramFloat2)
  {
    paramCanvas.drawCircle(paramFloat1, paramFloat2, this.size, paramPaint);
  }

  private void drawDiamond(Canvas paramCanvas, Paint paramPaint, float[] paramArrayOfFloat, float paramFloat1, float paramFloat2)
  {
    paramArrayOfFloat[0] = paramFloat1;
    paramArrayOfFloat[1] = (paramFloat2 - this.size);
    paramArrayOfFloat[2] = (paramFloat1 - this.size);
    paramArrayOfFloat[3] = paramFloat2;
    paramArrayOfFloat[4] = paramFloat1;
    paramArrayOfFloat[5] = (this.size + paramFloat2);
    paramArrayOfFloat[6] = (this.size + paramFloat1);
    paramArrayOfFloat[7] = paramFloat2;
    drawPath(paramCanvas, paramArrayOfFloat, paramPaint, true);
  }

  private void drawSquare(Canvas paramCanvas, Paint paramPaint, float paramFloat1, float paramFloat2)
  {
    paramCanvas.drawRect(paramFloat1 - this.size, paramFloat2 - this.size, paramFloat1 + this.size, paramFloat2 + this.size, paramPaint);
  }

  private void drawTriangle(Canvas paramCanvas, Paint paramPaint, float[] paramArrayOfFloat, float paramFloat1, float paramFloat2)
  {
    paramArrayOfFloat[0] = paramFloat1;
    paramArrayOfFloat[1] = (paramFloat2 - this.size - this.size / 2.0F);
    paramArrayOfFloat[2] = (paramFloat1 - this.size);
    paramArrayOfFloat[3] = (this.size + paramFloat2);
    paramArrayOfFloat[4] = (this.size + paramFloat1);
    paramArrayOfFloat[5] = paramArrayOfFloat[3];
    drawPath(paramCanvas, paramArrayOfFloat, paramPaint, true);
  }

  private void drawX(Canvas paramCanvas, Paint paramPaint, float paramFloat1, float paramFloat2)
  {
    paramCanvas.drawLine(paramFloat1 - this.size, paramFloat2 - this.size, paramFloat1 + this.size, paramFloat2 + this.size, paramPaint);
    paramCanvas.drawLine(paramFloat1 + this.size, paramFloat2 - this.size, paramFloat1 - this.size, paramFloat2 + this.size, paramPaint);
  }

  protected ClickableArea[] clickableAreasForPoints(float[] paramArrayOfFloat, double[] paramArrayOfDouble, float paramFloat, int paramInt1, int paramInt2)
  {
    paramInt2 = paramArrayOfFloat.length;
    ClickableArea[] arrayOfClickableArea = new ClickableArea[paramInt2 / 2];
    paramInt1 = 0;
    while (true)
    {
      if (paramInt1 >= paramInt2)
        return arrayOfClickableArea;
      int i = this.mRenderer.getSelectableBuffer();
      arrayOfClickableArea[(paramInt1 / 2)] = new ClickableArea(new RectF(paramArrayOfFloat[paramInt1] - i, paramArrayOfFloat[(paramInt1 + 1)] - i, paramArrayOfFloat[paramInt1] + i, paramArrayOfFloat[(paramInt1 + 1)] + i), paramArrayOfDouble[paramInt1], paramArrayOfDouble[(paramInt1 + 1)]);
      paramInt1 += 2;
    }
  }

  public void drawLegendShape(Canvas paramCanvas, SimpleSeriesRenderer paramSimpleSeriesRenderer, float paramFloat1, float paramFloat2, int paramInt, Paint paramPaint)
  {
    if (((XYSeriesRenderer)paramSimpleSeriesRenderer).isFillPoints())
      paramPaint.setStyle(Paint.Style.FILL);
    while (true)
      switch ($SWITCH_TABLE$com$ab$view$chart$PointStyle()[((XYSeriesRenderer)paramSimpleSeriesRenderer).getPointStyle().ordinal()])
      {
      default:
        return;
        paramPaint.setStyle(Paint.Style.STROKE);
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      }
    drawX(paramCanvas, paramPaint, paramFloat1 + 10.0F, paramFloat2);
    return;
    drawCircle(paramCanvas, paramPaint, paramFloat1 + 10.0F, paramFloat2);
    return;
    drawTriangle(paramCanvas, paramPaint, new float[6], paramFloat1 + 10.0F, paramFloat2);
    return;
    drawSquare(paramCanvas, paramPaint, paramFloat1 + 10.0F, paramFloat2);
    return;
    drawDiamond(paramCanvas, paramPaint, new float[8], paramFloat1 + 10.0F, paramFloat2);
    return;
    paramCanvas.drawPoint(paramFloat1 + 10.0F, paramFloat2, paramPaint);
  }

  public void drawSeries(Canvas paramCanvas, Paint paramPaint, float[] paramArrayOfFloat, int[] paramArrayOfInt, SimpleSeriesRenderer paramSimpleSeriesRenderer, float paramFloat, int paramInt1, int paramInt2)
  {
    paramSimpleSeriesRenderer = (XYSeriesRenderer)paramSimpleSeriesRenderer;
    paramPaint.setColor(paramSimpleSeriesRenderer.getColor());
    if (paramSimpleSeriesRenderer.isFillPoints())
      paramPaint.setStyle(Paint.Style.FILL);
    while (true)
    {
      paramInt2 = paramArrayOfFloat.length;
      switch ($SWITCH_TABLE$com$ab$view$chart$PointStyle()[paramSimpleSeriesRenderer.getPointStyle().ordinal()])
      {
      default:
        return;
        paramPaint.setStyle(Paint.Style.STROKE);
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      }
    }
    paramInt1 = 0;
    while (paramInt1 < paramInt2)
    {
      drawX(paramCanvas, paramPaint, paramArrayOfFloat[paramInt1], paramArrayOfFloat[(paramInt1 + 1)]);
      paramInt1 += 2;
    }
    paramInt1 = 0;
    while (paramInt1 < paramInt2)
    {
      if ((paramArrayOfInt != null) && (paramArrayOfInt[(paramInt1 / 2)] != 0))
        paramPaint.setColor(paramArrayOfInt[(paramInt1 / 2)]);
      drawCircle(paramCanvas, paramPaint, paramArrayOfFloat[paramInt1], paramArrayOfFloat[(paramInt1 + 1)]);
      paramPaint.setColor(paramSimpleSeriesRenderer.getColor());
      paramInt1 += 2;
    }
    paramArrayOfInt = new float[6];
    paramInt1 = 0;
    while (paramInt1 < paramInt2)
    {
      drawTriangle(paramCanvas, paramPaint, paramArrayOfInt, paramArrayOfFloat[paramInt1], paramArrayOfFloat[(paramInt1 + 1)]);
      paramInt1 += 2;
    }
    paramInt1 = 0;
    while (paramInt1 < paramInt2)
    {
      drawSquare(paramCanvas, paramPaint, paramArrayOfFloat[paramInt1], paramArrayOfFloat[(paramInt1 + 1)]);
      paramInt1 += 2;
    }
    paramArrayOfInt = new float[8];
    paramInt1 = 0;
    while (paramInt1 < paramInt2)
    {
      drawDiamond(paramCanvas, paramPaint, paramArrayOfInt, paramArrayOfFloat[paramInt1], paramArrayOfFloat[(paramInt1 + 1)]);
      paramInt1 += 2;
    }
    paramCanvas.drawPoints(paramArrayOfFloat, paramPaint);
  }

  public String getChartType()
  {
    return "Scatter";
  }

  public int getLegendShapeWidth(int paramInt)
  {
    return 10;
  }

  protected void setDatasetRenderer(XYMultipleSeriesDataset paramXYMultipleSeriesDataset, XYMultipleSeriesRenderer paramXYMultipleSeriesRenderer)
  {
    super.setDatasetRenderer(paramXYMultipleSeriesDataset, paramXYMultipleSeriesRenderer);
    this.size = paramXYMultipleSeriesRenderer.getPointSize();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.chart.ScatterChart
 * JD-Core Version:    0.6.2
 */