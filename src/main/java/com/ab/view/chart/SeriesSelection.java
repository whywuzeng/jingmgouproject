package com.ab.view.chart;

public class SeriesSelection
{
  private int mPointIndex;
  private int mSeriesIndex;
  private double mValue;
  private double mXValue;

  public SeriesSelection(int paramInt1, int paramInt2, double paramDouble1, double paramDouble2)
  {
    this.mSeriesIndex = paramInt1;
    this.mPointIndex = paramInt2;
    this.mXValue = paramDouble1;
    this.mValue = paramDouble2;
  }

  public int getPointIndex()
  {
    return this.mPointIndex;
  }

  public int getSeriesIndex()
  {
    return this.mSeriesIndex;
  }

  public double getValue()
  {
    return this.mValue;
  }

  public double getXValue()
  {
    return this.mXValue;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.chart.SeriesSelection
 * JD-Core Version:    0.6.2
 */