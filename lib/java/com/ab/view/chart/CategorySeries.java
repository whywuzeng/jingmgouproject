package com.ab.view.chart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CategorySeries
  implements Serializable
{
  private List<String> mCategories = new ArrayList();
  private List<Integer> mColors = new ArrayList();
  List<String> mExplains = new ArrayList();
  private String mTitle;
  private List<Double> mValues = new ArrayList();

  public CategorySeries(String paramString)
  {
    this.mTitle = paramString;
  }

  public void add(double paramDouble)
  {
    try
    {
      add(this.mCategories.size(), paramDouble);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void add(double paramDouble, int paramInt)
  {
    try
    {
      add(this.mCategories.size(), paramDouble, paramInt);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void add(double paramDouble, int paramInt, String paramString)
  {
    try
    {
      add(this.mCategories.size(), paramDouble, paramInt, paramString);
      return;
    }
    finally
    {
      paramString = finally;
    }
    throw paramString;
  }

  public void add(String paramString, double paramDouble)
  {
    try
    {
      this.mCategories.add(paramString);
      this.mValues.add(Double.valueOf(paramDouble));
      return;
    }
    finally
    {
      paramString = finally;
    }
    throw paramString;
  }

  public void add(String paramString, double paramDouble, int paramInt)
  {
    try
    {
      this.mCategories.add(paramString);
      this.mValues.add(Double.valueOf(paramDouble));
      this.mColors.add(Integer.valueOf(paramInt));
      return;
    }
    finally
    {
      paramString = finally;
    }
    throw paramString;
  }

  public void add(String paramString1, double paramDouble, int paramInt, String paramString2)
  {
    try
    {
      this.mCategories.add(paramString1);
      this.mValues.add(Double.valueOf(paramDouble));
      this.mColors.add(Integer.valueOf(paramInt));
      this.mExplains.add(paramString2);
      return;
    }
    finally
    {
      paramString1 = finally;
    }
    throw paramString1;
  }

  public void clear()
  {
    try
    {
      this.mCategories.clear();
      this.mValues.clear();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public String getCategory(int paramInt)
  {
    try
    {
      String str = (String)this.mCategories.get(paramInt);
      return str;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public List<Integer> getColors()
  {
    return this.mColors;
  }

  public List<String> getExplains()
  {
    return this.mExplains;
  }

  public int getItemCount()
  {
    try
    {
      int i = this.mCategories.size();
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public String getTitle()
  {
    return this.mTitle;
  }

  public double getValue(int paramInt)
  {
    try
    {
      double d = ((Double)this.mValues.get(paramInt)).doubleValue();
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
      this.mCategories.remove(paramInt);
      this.mValues.remove(paramInt);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void set(int paramInt, String paramString, double paramDouble)
  {
    try
    {
      this.mCategories.set(paramInt, paramString);
      this.mValues.set(paramInt, Double.valueOf(paramDouble));
      return;
    }
    finally
    {
      paramString = finally;
    }
    throw paramString;
  }

  public XYSeries toXYSeries()
  {
    XYSeries localXYSeries = new XYSeries(this.mTitle);
    int i = 0;
    while (true)
    {
      if (i >= this.mValues.size())
        return localXYSeries;
      double d = ((Double)this.mValues.get(i)).doubleValue();
      localXYSeries.add(i + 1, d, ((Integer)this.mColors.get(i)).intValue(), (String)this.mExplains.get(i));
      i += 1;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.chart.CategorySeries
 * JD-Core Version:    0.6.2
 */