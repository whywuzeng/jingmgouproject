package com.ab.view.chart;

import android.graphics.Canvas;
import android.graphics.Paint;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TimeChart extends LineChart
{
  public static final long DAY = 86400000L;
  public static final String TYPE = "Time";
  private String mDateFormat;
  private Double mStartPoint;

  TimeChart()
  {
  }

  public TimeChart(XYMultipleSeriesDataset paramXYMultipleSeriesDataset, XYMultipleSeriesRenderer paramXYMultipleSeriesRenderer)
  {
    super(paramXYMultipleSeriesDataset, paramXYMultipleSeriesRenderer);
  }

  private DateFormat getDateFormat(double paramDouble1, double paramDouble2)
  {
    if (this.mDateFormat != null);
    do
    {
      try
      {
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(this.mDateFormat);
        return localSimpleDateFormat;
      }
      catch (Exception localException)
      {
      }
      DateFormat localDateFormat = SimpleDateFormat.getDateInstance(2);
      paramDouble1 = paramDouble2 - paramDouble1;
      if ((paramDouble1 > 86400000.0D) && (paramDouble1 < 432000000.0D))
        return SimpleDateFormat.getDateTimeInstance(3, 3);
    }
    while (paramDouble1 >= 86400000.0D);
    return SimpleDateFormat.getTimeInstance(2);
  }

  protected void drawXLabels(List<Double> paramList, Double[] paramArrayOfDouble, Canvas paramCanvas, Paint paramPaint, int paramInt1, int paramInt2, int paramInt3, double paramDouble1, double paramDouble2, double paramDouble3)
  {
    int j = paramList.size();
    boolean bool1;
    boolean bool2;
    DateFormat localDateFormat;
    int i;
    if (j > 0)
    {
      bool1 = this.mRenderer.isShowLabels();
      bool2 = this.mRenderer.isShowGridY();
      localDateFormat = getDateFormat(((Double)paramList.get(0)).doubleValue(), ((Double)paramList.get(j - 1)).doubleValue());
      i = 0;
    }
    while (true)
    {
      if (i >= j)
      {
        drawXTextLabels(paramArrayOfDouble, paramCanvas, paramPaint, true, paramInt1, paramInt2, paramInt3, paramDouble1, paramDouble2, paramDouble3);
        return;
      }
      long l = Math.round(((Double)paramList.get(i)).doubleValue());
      float f = (float)(paramInt1 + (l - paramDouble2) * paramDouble1);
      if (bool1)
      {
        paramPaint.setColor(this.mRenderer.getXLabelsColor());
        paramCanvas.drawLine(f, paramInt3, f, paramInt3 + this.mRenderer.getLabelsTextSize() / 3.0F, paramPaint);
        drawText(paramCanvas, localDateFormat.format(new Date(l)), f, paramInt3 + this.mRenderer.getLabelsTextSize() * 4.0F / 3.0F, paramPaint, this.mRenderer.getXLabelsAngle());
      }
      if (bool2)
      {
        paramPaint.setColor(this.mRenderer.getGridColor());
        paramCanvas.drawLine(f, paramInt3, f, paramInt2, paramPaint);
      }
      i += 1;
    }
  }

  public String getChartType()
  {
    return "Time";
  }

  public String getDateFormat()
  {
    return this.mDateFormat;
  }

  protected List<Double> getXLabels(double paramDouble1, double paramDouble2, int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    XYSeries localXYSeries;
    int i1;
    int k;
    int i;
    int j;
    if (!this.mRenderer.isXRoundedLabels())
      if (this.mDataset.getSeriesCount() > 0)
      {
        localXYSeries = this.mDataset.getSeriesAt(0);
        i1 = localXYSeries.getItemCount();
        k = 0;
        i = -1;
        j = 0;
        if (j >= i1)
        {
          if (k >= paramInt)
            break label196;
          paramInt = i;
          label73: if (paramInt < i + k)
            break label169;
        }
      }
    label169: label196: double d3;
    do
    {
      while (true)
      {
        return localArrayList;
        d1 = localXYSeries.getX(j);
        int m = k;
        int n = i;
        if (paramDouble1 <= d1)
        {
          m = k;
          n = i;
          if (d1 <= paramDouble2)
          {
            k += 1;
            m = k;
            n = i;
            if (i < 0)
            {
              n = j;
              m = k;
            }
          }
        }
        j += 1;
        k = m;
        i = n;
        break;
        localArrayList.add(Double.valueOf(localXYSeries.getX(paramInt)));
        paramInt += 1;
        break label73;
        float f = k / paramInt;
        j = 0;
        i = 0;
        while ((i < i1) && (j < paramInt))
        {
          d1 = localXYSeries.getX(Math.round(i * f));
          k = j;
          if (paramDouble1 <= d1)
          {
            k = j;
            if (d1 <= paramDouble2)
            {
              localArrayList.add(Double.valueOf(d1));
              k = j + 1;
            }
          }
          i += 1;
          j = k;
        }
      }
      return super.getXLabels(paramDouble1, paramDouble2, paramInt);
      if (this.mStartPoint == null)
        this.mStartPoint = Double.valueOf(paramDouble1 - paramDouble1 % 86400000.0D + 86400000.0D + new Date(Math.round(paramDouble1)).getTimezoneOffset() * 60 * 1000);
      i = paramInt;
      if (paramInt > 25)
        i = 25;
      d3 = (paramDouble2 - paramDouble1) / i;
    }
    while (d3 <= 0.0D);
    double d2 = 86400000.0D;
    double d1 = d2;
    if (d3 <= 86400000.0D)
    {
      d1 = d2;
      label405: if (d3 < d1 / 2.0D);
    }
    while (true)
    {
      paramDouble1 = this.mStartPoint.doubleValue() - Math.floor((this.mStartPoint.doubleValue() - paramDouble1) / d1) * d1;
      paramInt = 0;
      while (true)
      {
        if (paramDouble1 >= paramDouble2)
          break label516;
        if (paramInt > i)
          break;
        localArrayList.add(Double.valueOf(paramDouble1));
        paramDouble1 += d1;
        paramInt += 1;
      }
      d1 /= 2.0D;
      break label405;
      do
        d1 *= 2.0D;
      while (d3 > d1);
    }
    label516: return localArrayList;
  }

  public void setDateFormat(String paramString)
  {
    this.mDateFormat = paramString;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.chart.TimeChart
 * JD-Core Version:    0.6.2
 */