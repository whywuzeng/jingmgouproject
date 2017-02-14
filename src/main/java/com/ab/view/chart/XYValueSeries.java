package com.ab.view.chart;

import java.util.ArrayList;
import java.util.List;

public class XYValueSeries extends XYSeries
{
  private double mMaxValue = -1.797693134862316E+308D;
  private double mMinValue = 1.7976931348623157E+308D;
  private List<Double> mValue = new ArrayList();

  public XYValueSeries(String paramString)
  {
    super(paramString);
  }

  private void initRange()
  {
    this.mMinValue = 1.7976931348623157E+308D;
    this.mMaxValue = 1.7976931348623157E+308D;
    int j = getItemCount();
    int i = 0;
    while (true)
    {
      if (i >= j)
        return;
      updateRange(getValue(i));
      i += 1;
    }
  }

  private void updateRange(double paramDouble)
  {
    this.mMinValue = Math.min(this.mMinValue, paramDouble);
    this.mMaxValue = Math.max(this.mMaxValue, paramDouble);
  }

  public void add(double paramDouble1, double paramDouble2)
  {
    try
    {
      add(paramDouble1, paramDouble2, 0.0D);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void add(double paramDouble1, double paramDouble2, double paramDouble3)
  {
    try
    {
      super.add(paramDouble1, paramDouble2);
      this.mValue.add(Double.valueOf(paramDouble3));
      updateRange(paramDouble3);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void clear()
  {
    try
    {
      super.clear();
      this.mValue.clear();
      initRange();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public double getMaxValue()
  {
    return this.mMaxValue;
  }

  public double getMinValue()
  {
    return this.mMinValue;
  }

  public double getValue(int paramInt)
  {
    try
    {
      double d = ((Double)this.mValue.get(paramInt)).doubleValue();
      return d;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void remove(int paramInt)
  {
    try
    {
      super.remove(paramInt);
      double d = ((Double)this.mValue.remove(paramInt)).doubleValue();
      if ((d == this.mMinValue) || (d == this.mMaxValue))
        initRange();
      return;
    }
    finally
    {
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.chart.XYValueSeries
 * JD-Core Version:    0.6.2
 */