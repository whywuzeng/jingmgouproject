package com.ab.view.chart;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.Log;

public class LineChart extends XYChart
{
  private static final int SHAPE_WIDTH = 30;
  public static final String TYPE = "Line";
  private ScatterChart pointsChart;

  LineChart()
  {
  }

  public LineChart(XYMultipleSeriesDataset paramXYMultipleSeriesDataset, XYMultipleSeriesRenderer paramXYMultipleSeriesRenderer)
  {
    super(paramXYMultipleSeriesDataset, paramXYMultipleSeriesRenderer);
    Log.d("LineChart", "调用父类XYChart的XYMultipleSeriesDataset 设置1");
    this.pointsChart = new ScatterChart(paramXYMultipleSeriesDataset, paramXYMultipleSeriesRenderer);
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
    paramCanvas.drawLine(paramFloat1, paramFloat2, paramFloat1 + 30.0F, paramFloat2, paramPaint);
    if (isRenderPoints(paramSimpleSeriesRenderer))
      this.pointsChart.drawLegendShape(paramCanvas, paramSimpleSeriesRenderer, paramFloat1 + 5.0F, paramFloat2, paramInt, paramPaint);
  }

  public void drawSeries(Canvas paramCanvas, Paint paramPaint, float[] paramArrayOfFloat, int[] paramArrayOfInt, SimpleSeriesRenderer paramSimpleSeriesRenderer, float paramFloat, int paramInt1, int paramInt2)
  {
    paramInt2 = paramArrayOfFloat.length;
    paramArrayOfInt = (XYSeriesRenderer)paramSimpleSeriesRenderer;
    float f = paramPaint.getStrokeWidth();
    paramPaint.setStrokeWidth(paramArrayOfInt.getLineWidth());
    if (paramArrayOfInt.isFillBelowLine())
    {
      paramPaint.setColor(paramArrayOfInt.getFillBelowLineColor());
      paramArrayOfInt = new float[paramArrayOfFloat.length + 4];
      System.arraycopy(paramArrayOfFloat, 0, paramArrayOfInt, 0, paramInt2);
      paramArrayOfFloat[0] += 1.0F;
      paramArrayOfInt[paramInt2] = paramArrayOfInt[(paramInt2 - 2)];
      paramArrayOfInt[(paramInt2 + 1)] = paramFloat;
      paramArrayOfInt[(paramInt2 + 2)] = paramArrayOfInt[0];
      paramArrayOfInt[(paramInt2 + 3)] = paramArrayOfInt[(paramInt2 + 1)];
      paramInt1 = 0;
    }
    while (true)
    {
      if (paramInt1 >= paramInt2 + 4)
      {
        paramPaint.setStyle(Paint.Style.FILL);
        drawPath(paramCanvas, paramArrayOfInt, paramPaint, true);
        paramPaint.setColor(paramSimpleSeriesRenderer.getColor());
        paramPaint.setStyle(Paint.Style.STROKE);
        drawPath(paramCanvas, paramArrayOfFloat, paramPaint, false);
        paramPaint.setStrokeWidth(f);
        return;
      }
      if (paramArrayOfInt[(paramInt1 + 1)] < 0.0F)
        paramArrayOfInt[(paramInt1 + 1)] = 0.0F;
      paramInt1 += 2;
    }
  }

  public String getChartType()
  {
    return "Line";
  }

  public int getLegendShapeWidth(int paramInt)
  {
    return 30;
  }

  public ScatterChart getPointsChart()
  {
    return this.pointsChart;
  }

  public boolean isRenderPoints(SimpleSeriesRenderer paramSimpleSeriesRenderer)
  {
    return ((XYSeriesRenderer)paramSimpleSeriesRenderer).getPointStyle() != PointStyle.POINT;
  }

  protected void setDatasetRenderer(XYMultipleSeriesDataset paramXYMultipleSeriesDataset, XYMultipleSeriesRenderer paramXYMultipleSeriesRenderer)
  {
    super.setDatasetRenderer(paramXYMultipleSeriesDataset, paramXYMultipleSeriesRenderer);
    this.pointsChart = new ScatterChart(paramXYMultipleSeriesDataset, paramXYMultipleSeriesRenderer);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.chart.LineChart
 * JD-Core Version:    0.6.2
 */