package com.ab.view.chart;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;

public class XYSeries
  implements Serializable
{
  private double mMaxX = -1.797693134862316E+308D;
  private double mMaxY = -1.797693134862316E+308D;
  private double mMinX = 1.7976931348623157E+308D;
  private double mMinY = 1.7976931348623157E+308D;
  private final int mScaleNumber;
  private String mTitle;
  private final IndexXYMap<Double, Integer> mXC = new IndexXYMap();
  private final IndexXYMap<Double, String> mXE = new IndexXYMap();
  private final IndexXYMap<Double, Double> mXY = new IndexXYMap();

  public XYSeries(String paramString)
  {
    this(paramString, 0);
  }

  public XYSeries(String paramString, int paramInt)
  {
    this.mTitle = paramString;
    this.mScaleNumber = paramInt;
    initRange();
  }

  private void initRange()
  {
    this.mMinX = 1.7976931348623157E+308D;
    this.mMaxX = -1.797693134862316E+308D;
    this.mMinY = 1.7976931348623157E+308D;
    this.mMaxY = -1.797693134862316E+308D;
    int j = getItemCount();
    int i = 0;
    while (true)
    {
      if (i >= j)
        return;
      updateRange(getX(i), getY(i));
      i += 1;
    }
  }

  private void updateRange(double paramDouble1, double paramDouble2)
  {
    this.mMinX = Math.min(this.mMinX, paramDouble1);
    this.mMaxX = Math.max(this.mMaxX, paramDouble1);
    this.mMinY = Math.min(this.mMinY, paramDouble2);
    this.mMaxY = Math.max(this.mMaxY, paramDouble2);
  }

  public void add(double paramDouble1, double paramDouble2)
  {
    try
    {
      this.mXY.put(Double.valueOf(paramDouble1), Double.valueOf(paramDouble2));
      updateRange(paramDouble1, paramDouble2);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void add(double paramDouble1, double paramDouble2, int paramInt)
  {
    try
    {
      this.mXY.put(Double.valueOf(paramDouble1), Double.valueOf(paramDouble2));
      updateRange(paramDouble1, paramDouble2);
      this.mXC.put(Double.valueOf(paramDouble1), Integer.valueOf(paramInt));
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void add(double paramDouble1, double paramDouble2, int paramInt, String paramString)
  {
    try
    {
      this.mXY.put(Double.valueOf(paramDouble1), Double.valueOf(paramDouble2));
      updateRange(paramDouble1, paramDouble2);
      this.mXC.put(Double.valueOf(paramDouble1), Integer.valueOf(paramInt));
      this.mXE.put(Double.valueOf(paramDouble1), paramString);
      return;
    }
    finally
    {
      paramString = finally;
    }
    throw paramString;
  }

  public void clear()
  {
    try
    {
      this.mXY.clear();
      initRange();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public int getIndexForKey(double paramDouble)
  {
    return this.mXY.getIndexForKey(Double.valueOf(paramDouble));
  }

  public int getItemCount()
  {
    try
    {
      int i = this.mXY.size();
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public double getMaxX()
  {
    return this.mMaxX;
  }

  public double getMaxY()
  {
    return this.mMaxY;
  }

  public double getMinX()
  {
    return this.mMinX;
  }

  public double getMinY()
  {
    return this.mMinY;
  }

  public SortedMap<Double, Double> getRange(double paramDouble1, double paramDouble2, int paramInt)
  {
    try
    {
      Object localObject1 = this.mXY.headMap(Double.valueOf(paramDouble1));
      double d = paramDouble1;
      if (!((SortedMap)localObject1).isEmpty())
        d = ((Double)((SortedMap)localObject1).lastKey()).doubleValue();
      localObject1 = this.mXY.tailMap(Double.valueOf(paramDouble2));
      paramDouble1 = paramDouble2;
      Double localDouble;
      if (!((SortedMap)localObject1).isEmpty())
      {
        localObject1 = ((SortedMap)localObject1).keySet().iterator();
        localDouble = (Double)((Iterator)localObject1).next();
        if (!((Iterator)localObject1).hasNext())
          break label141;
      }
      for (paramDouble1 = ((Double)((Iterator)localObject1).next()).doubleValue(); ; paramDouble1 = paramDouble2 + paramDouble1)
      {
        localObject1 = this.mXY.subMap(Double.valueOf(d), Double.valueOf(paramDouble1));
        return localObject1;
        label141: paramDouble1 = localDouble.doubleValue();
      }
    }
    finally
    {
    }
  }

  public int getScaleNumber()
  {
    return this.mScaleNumber;
  }

  public String getTitle()
  {
    return this.mTitle;
  }

  public double getX(int paramInt)
  {
    try
    {
      double d = ((Double)this.mXY.getXByIndex(paramInt)).doubleValue();
      return d;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public double getY(int paramInt)
  {
    try
    {
      double d = ((Double)this.mXY.getYByIndex(paramInt)).doubleValue();
      return d;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public IndexXYMap<Double, Integer> getmXC()
  {
    return this.mXC;
  }

  public int getmXCValue(double paramDouble)
  {
    if (this.mXC.get(Double.valueOf(paramDouble)) == null)
      return 0;
    return ((Integer)this.mXC.get(Double.valueOf(paramDouble))).intValue();
  }

  public IndexXYMap<Double, String> getmXE()
  {
    return this.mXE;
  }

  public String getmXEValue(double paramDouble)
  {
    if (this.mXC.get(Double.valueOf(paramDouble)) == null)
      return "";
    return (String)this.mXE.get(Double.valueOf(paramDouble));
  }

  public void remove(int paramInt)
  {
    try
    {
      XYEntry localXYEntry = this.mXY.removeByIndex(paramInt);
      double d1 = ((Double)localXYEntry.getKey()).doubleValue();
      double d2 = ((Double)localXYEntry.getValue()).doubleValue();
      if ((d1 == this.mMinX) || (d1 == this.mMaxX) || (d2 == this.mMinY) || (d2 == this.mMaxY))
        initRange();
      return;
    }
    finally
    {
    }
  }

  public void setTitle(String paramString)
  {
    this.mTitle = paramString;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.chart.XYSeries
 * JD-Core Version:    0.6.2
 */