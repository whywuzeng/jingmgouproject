package com.ab.view.chart;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Zoom extends AbstractTool
{
  public static final int ZOOM_AXIS_X = 1;
  public static final int ZOOM_AXIS_XY = 0;
  public static final int ZOOM_AXIS_Y = 2;
  private boolean limitsReachedX = false;
  private boolean limitsReachedY = false;
  private boolean mZoomIn;
  private List<ZoomListener> mZoomListeners = new ArrayList();
  private float mZoomRate;

  public Zoom(AbstractChart paramAbstractChart, boolean paramBoolean, float paramFloat)
  {
    super(paramAbstractChart);
    this.mZoomIn = paramBoolean;
    setZoomRate(paramFloat);
  }

  private void notifyZoomListeners(ZoomEvent paramZoomEvent)
  {
    try
    {
      Iterator localIterator = this.mZoomListeners.iterator();
      while (true)
      {
        boolean bool = localIterator.hasNext();
        if (!bool)
          return;
        ((ZoomListener)localIterator.next()).zoomApplied(paramZoomEvent);
      }
    }
    finally
    {
    }
    throw paramZoomEvent;
  }

  public void addZoomListener(ZoomListener paramZoomListener)
  {
    try
    {
      this.mZoomListeners.add(paramZoomListener);
      return;
    }
    finally
    {
      paramZoomListener = finally;
    }
    throw paramZoomListener;
  }

  public void apply(int paramInt)
  {
    int i;
    if ((this.mChart instanceof XYChart))
    {
      int j = this.mRenderer.getScalesCount();
      i = 0;
      if (i < j);
    }
    while (true)
    {
      notifyZoomListeners(new ZoomEvent(this.mZoomIn, this.mZoomRate));
      return;
      Object localObject = getRange(i);
      checkRange((double[])localObject, i);
      double[] arrayOfDouble = this.mRenderer.getZoomLimits();
      double d5 = (localObject[0] + localObject[1]) / 2.0D;
      double d6 = (localObject[2] + localObject[3]) / 2.0D;
      double d2 = localObject[1] - localObject[0];
      double d4 = localObject[3] - localObject[2];
      double d1 = d2 / 2.0D;
      double d3 = d2 / 2.0D;
      double d7 = d4 / 2.0D;
      double d8 = d4 / 2.0D;
      boolean bool;
      if (i == 0)
      {
        if ((arrayOfDouble != null) && ((d5 - d1 <= arrayOfDouble[0]) || (d5 + d3 >= arrayOfDouble[1])))
        {
          bool = true;
          label195: this.limitsReachedX = bool;
          if ((arrayOfDouble == null) || ((d6 - d7 > arrayOfDouble[2]) && (d6 + d8 < arrayOfDouble[3])))
            break label471;
          bool = true;
          label235: this.limitsReachedY = bool;
        }
      }
      else
      {
        if (!this.mZoomIn)
          break label477;
        d1 = d2;
        if (this.mRenderer.isZoomXEnabled())
          if (paramInt != 1)
          {
            d1 = d2;
            if (paramInt != 0);
          }
          else if (this.limitsReachedX)
          {
            d1 = d2;
            if (this.mZoomRate < 1.0F);
          }
          else
          {
            d1 = d2 / this.mZoomRate;
          }
        d2 = d4;
        d3 = d1;
        if (this.mRenderer.isZoomYEnabled())
          if (paramInt != 2)
          {
            d2 = d4;
            d3 = d1;
            if (paramInt != 0);
          }
          else if (this.limitsReachedY)
          {
            d2 = d4;
            d3 = d1;
            if (this.mZoomRate < 1.0F);
          }
          else
          {
            d2 = d4 / this.mZoomRate;
            d3 = d1;
          }
      }
      while (true)
      {
        if ((this.mRenderer.isZoomXEnabled()) && ((paramInt == 1) || (paramInt == 0)))
          setXRange(d5 - d3 / 2.0D, d5 + d3 / 2.0D, i);
        if ((this.mRenderer.isZoomYEnabled()) && ((paramInt == 2) || (paramInt == 0)))
          setYRange(d6 - d2 / 2.0D, d6 + d2 / 2.0D, i);
        i += 1;
        break;
        bool = false;
        break label195;
        label471: bool = false;
        break label235;
        label477: d1 = d2;
        if (this.mRenderer.isZoomXEnabled())
        {
          d1 = d2;
          if (!this.limitsReachedX)
            if (paramInt != 1)
            {
              d1 = d2;
              if (paramInt != 0);
            }
            else
            {
              d1 = d2 * this.mZoomRate;
            }
        }
        d2 = d4;
        d3 = d1;
        if (this.mRenderer.isZoomYEnabled())
        {
          d2 = d4;
          d3 = d1;
          if (!this.limitsReachedY)
            if (paramInt != 2)
            {
              d2 = d4;
              d3 = d1;
              if (paramInt != 0);
            }
            else
            {
              d2 = d4 * this.mZoomRate;
              d3 = d1;
            }
        }
      }
      localObject = ((RoundChart)this.mChart).getRenderer();
      if (this.mZoomIn)
        ((DefaultRenderer)localObject).setScale(((DefaultRenderer)localObject).getScale() * this.mZoomRate);
      else
        ((DefaultRenderer)localObject).setScale(((DefaultRenderer)localObject).getScale() / this.mZoomRate);
    }
  }

  public void notifyZoomResetListeners()
  {
    try
    {
      Iterator localIterator = this.mZoomListeners.iterator();
      while (true)
      {
        boolean bool = localIterator.hasNext();
        if (!bool)
          return;
        ((ZoomListener)localIterator.next()).zoomReset();
      }
    }
    finally
    {
    }
  }

  public void removeZoomListener(ZoomListener paramZoomListener)
  {
    try
    {
      this.mZoomListeners.add(paramZoomListener);
      return;
    }
    finally
    {
      paramZoomListener = finally;
    }
    throw paramZoomListener;
  }

  public void setZoomRate(float paramFloat)
  {
    this.mZoomRate = paramFloat;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.chart.Zoom
 * JD-Core Version:    0.6.2
 */