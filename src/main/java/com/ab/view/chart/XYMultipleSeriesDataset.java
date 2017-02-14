package com.ab.view.chart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class XYMultipleSeriesDataset
  implements Serializable
{
  private List<XYSeries> mSeries = new ArrayList();

  public void addSeries(int paramInt, XYSeries paramXYSeries)
  {
    try
    {
      this.mSeries.add(paramInt, paramXYSeries);
      return;
    }
    finally
    {
      paramXYSeries = finally;
    }
    throw paramXYSeries;
  }

  public void addSeries(XYSeries paramXYSeries)
  {
    try
    {
      this.mSeries.add(paramXYSeries);
      return;
    }
    finally
    {
      paramXYSeries = finally;
    }
    throw paramXYSeries;
  }

  public XYSeries[] getSeries()
  {
    try
    {
      XYSeries[] arrayOfXYSeries = (XYSeries[])this.mSeries.toArray(new XYSeries[0]);
      return arrayOfXYSeries;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public XYSeries getSeriesAt(int paramInt)
  {
    try
    {
      XYSeries localXYSeries = (XYSeries)this.mSeries.get(paramInt);
      return localXYSeries;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public int getSeriesCount()
  {
    try
    {
      int i = this.mSeries.size();
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void removeSeries(int paramInt)
  {
    try
    {
      this.mSeries.remove(paramInt);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void removeSeries(XYSeries paramXYSeries)
  {
    try
    {
      this.mSeries.remove(paramXYSeries);
      return;
    }
    finally
    {
      paramXYSeries = finally;
    }
    throw paramXYSeries;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.chart.XYMultipleSeriesDataset
 * JD-Core Version:    0.6.2
 */