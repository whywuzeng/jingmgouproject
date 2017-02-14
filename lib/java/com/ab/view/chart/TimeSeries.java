package com.ab.view.chart;

import java.util.Date;

public class TimeSeries extends XYSeries
{
  public TimeSeries(String paramString)
  {
    super(paramString);
  }

  public void add(Date paramDate, double paramDouble)
  {
    try
    {
      super.add(paramDate.getTime(), paramDouble);
      return;
    }
    finally
    {
      paramDate = finally;
    }
    throw paramDate;
  }

  public void add(Date paramDate, double paramDouble, int paramInt, String paramString)
  {
    try
    {
      super.add(paramDate.getTime(), paramDouble, paramInt, paramString);
      return;
    }
    finally
    {
      paramDate = finally;
    }
    throw paramDate;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.chart.TimeSeries
 * JD-Core Version:    0.6.2
 */