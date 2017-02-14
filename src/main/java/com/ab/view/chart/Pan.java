package com.ab.view.chart;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Pan extends AbstractTool
{
  private boolean limitsReachedX = false;
  private boolean limitsReachedY = false;
  private List<PanListener> mPanListeners = new ArrayList();

  public Pan(AbstractChart paramAbstractChart)
  {
    super(paramAbstractChart);
  }

  private double getAxisRatio(double[] paramArrayOfDouble)
  {
    return Math.abs(paramArrayOfDouble[1] - paramArrayOfDouble[0]) / Math.abs(paramArrayOfDouble[3] - paramArrayOfDouble[2]);
  }

  private void notifyPanListeners()
  {
    try
    {
      Iterator localIterator = this.mPanListeners.iterator();
      while (true)
      {
        boolean bool = localIterator.hasNext();
        if (!bool)
          return;
        ((PanListener)localIterator.next()).panApplied();
      }
    }
    finally
    {
    }
  }

  public void addPanListener(PanListener paramPanListener)
  {
    try
    {
      this.mPanListeners.add(paramPanListener);
      return;
    }
    finally
    {
      paramPanListener = finally;
    }
    throw paramPanListener;
  }

  public void apply(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    int n = 1;
    int i = 1;
    int j = 1;
    int i1 = 1;
    Object localObject;
    int i3;
    XYChart localXYChart;
    int i4;
    if ((this.mChart instanceof XYChart))
    {
      int i6 = this.mRenderer.getScalesCount();
      localObject = this.mRenderer.getPanLimits();
      if ((localObject != null) && (localObject.length == 4))
      {
        i3 = 1;
        localXYChart = (XYChart)this.mChart;
        i4 = 0;
        if (i4 < i6)
          break label85;
      }
    }
    while (true)
    {
      notifyPanListeners();
      label85: double[] arrayOfDouble1;
      do
      {
        return;
        i3 = 0;
        break;
        arrayOfDouble1 = getRange(i4);
        arrayOfDouble2 = localXYChart.getCalcRange(i4);
      }
      while ((this.limitsReachedX) && (this.limitsReachedY) && (((arrayOfDouble1[0] == arrayOfDouble1[1]) && (arrayOfDouble2[0] == arrayOfDouble2[1])) || ((arrayOfDouble1[2] == arrayOfDouble1[3]) && (arrayOfDouble2[2] == arrayOfDouble2[3]))));
      checkRange(arrayOfDouble1, i4);
      double[] arrayOfDouble2 = localXYChart.toRealPoint(paramFloat1, paramFloat2, i4);
      double[] arrayOfDouble3 = localXYChart.toRealPoint(paramFloat3, paramFloat4, i4);
      double d3 = arrayOfDouble2[0] - arrayOfDouble3[0];
      double d4 = arrayOfDouble2[1] - arrayOfDouble3[1];
      double d5 = getAxisRatio(arrayOfDouble1);
      double d2 = d3;
      double d1 = d4;
      if (localXYChart.isVertical(this.mRenderer))
      {
        d2 = -d4;
        d1 = d3 / d5;
        d2 *= d5;
      }
      int i2 = j;
      int i5 = i1;
      int m;
      int k;
      if (this.mRenderer.isPanXEnabled())
      {
        i2 = j;
        m = i1;
        if (localObject != null)
        {
          k = j;
          if (j != 0)
          {
            if (localObject[0] > arrayOfDouble1[0] + d2)
              break label559;
            k = 1;
          }
          label322: i2 = k;
          m = i1;
          if (i1 != 0)
          {
            if (localObject[1] < arrayOfDouble1[1] + d2)
              break label565;
            m = 1;
            i2 = k;
          }
        }
        label357: if ((i3 != 0) && ((i2 == 0) || (m == 0)))
          break label575;
        setXRange(arrayOfDouble1[0] + d2, arrayOfDouble1[1] + d2, i4);
        this.limitsReachedX = false;
        i5 = m;
      }
      else
      {
        label401: m = i;
        k = n;
        if (this.mRenderer.isPanYEnabled())
        {
          m = i;
          k = n;
          if (localObject != null)
          {
            j = i;
            if (i != 0)
            {
              if (localObject[2] > arrayOfDouble1[2] + d1)
                break label587;
              j = 1;
            }
            label459: m = j;
            k = n;
            if (n != 0)
            {
              if (localObject[3] < arrayOfDouble1[3] + d1)
                break label593;
              k = 1;
              m = j;
            }
          }
          label494: if ((i3 != 0) && ((m == 0) || (k == 0)))
            break label603;
          setYRange(arrayOfDouble1[2] + d1, arrayOfDouble1[3] + d1, i4);
        }
      }
      label559: label565: label575: label587: label593: label603: for (this.limitsReachedY = false; ; this.limitsReachedY = true)
      {
        i4 += 1;
        i = m;
        j = i2;
        i1 = i5;
        n = k;
        break;
        k = 0;
        break label322;
        m = 0;
        i2 = k;
        break label357;
        this.limitsReachedX = true;
        i5 = m;
        break label401;
        j = 0;
        break label459;
        k = 0;
        m = j;
        break label494;
      }
      localObject = (RoundChart)this.mChart;
      ((RoundChart)localObject).setCenterX(((RoundChart)localObject).getCenterX() + (int)(paramFloat3 - paramFloat1));
      ((RoundChart)localObject).setCenterY(((RoundChart)localObject).getCenterY() + (int)(paramFloat4 - paramFloat2));
    }
  }

  public void removePanListener(PanListener paramPanListener)
  {
    try
    {
      this.mPanListeners.add(paramPanListener);
      return;
    }
    finally
    {
      paramPanListener = finally;
    }
    throw paramPanListener;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.chart.Pan
 * JD-Core Version:    0.6.2
 */