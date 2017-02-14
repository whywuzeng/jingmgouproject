package com.ab.view.chart;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

public class AreaChart extends LineChart
{
  public static final String TYPE = "Area";
  private static final long serialVersionUID = 1L;
  private float firstMultiplier;
  private Point p1 = new Point();
  private Point p2 = new Point();
  private Point p3 = new Point();
  private float secondMultiplier;

  public AreaChart()
  {
    this.firstMultiplier = 0.33F;
    this.secondMultiplier = (1.0F - this.firstMultiplier);
  }

  public AreaChart(XYMultipleSeriesDataset paramXYMultipleSeriesDataset, XYMultipleSeriesRenderer paramXYMultipleSeriesRenderer, float paramFloat)
  {
    super(paramXYMultipleSeriesDataset, paramXYMultipleSeriesRenderer);
    this.firstMultiplier = paramFloat;
    this.secondMultiplier = (1.0F - this.firstMultiplier);
  }

  private void calc(float[] paramArrayOfFloat, Point paramPoint, int paramInt1, int paramInt2, float paramFloat)
  {
    float f1 = paramArrayOfFloat[paramInt1];
    float f2 = paramArrayOfFloat[(paramInt1 + 1)];
    float f3 = paramArrayOfFloat[paramInt2];
    float f4 = paramArrayOfFloat[(paramInt2 + 1)];
    paramPoint.setX((f3 - f1) * paramFloat + f1);
    paramPoint.setY((f4 - f2) * paramFloat + f2);
  }

  protected void drawPath(Canvas paramCanvas, float[] paramArrayOfFloat, Paint paramPaint, boolean paramBoolean)
  {
    Path localPath = new Path();
    localPath.moveTo(paramArrayOfFloat[0], paramArrayOfFloat[1]);
    int j = paramArrayOfFloat.length;
    int i = j;
    if (paramBoolean)
      i = j - 4;
    j = 0;
    if (j >= i)
      if (paramBoolean)
        j = i;
    while (true)
    {
      if (j >= i + 4)
      {
        localPath.lineTo(paramArrayOfFloat[0], paramArrayOfFloat[1]);
        paramCanvas.drawPath(localPath, paramPaint);
        return;
        int k;
        if (j + 2 < i)
        {
          k = j + 2;
          label101: if (j + 4 >= i)
            break label237;
        }
        label237: for (int m = j + 4; ; m = k)
        {
          calc(paramArrayOfFloat, this.p1, j, k, this.secondMultiplier);
          this.p2.setX(paramArrayOfFloat[k]);
          this.p2.setY(paramArrayOfFloat[(k + 1)]);
          calc(paramArrayOfFloat, this.p3, k, m, this.firstMultiplier);
          localPath.cubicTo(this.p1.getX(), this.p1.getY(), this.p2.getX(), this.p2.getY(), this.p3.getX(), this.p3.getY());
          j += 2;
          break;
          k = j;
          break label101;
        }
      }
      localPath.lineTo(paramArrayOfFloat[j], paramArrayOfFloat[(j + 1)]);
      j += 2;
    }
  }

  public String getChartType()
  {
    return "Area";
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.chart.AreaChart
 * JD-Core Version:    0.6.2
 */