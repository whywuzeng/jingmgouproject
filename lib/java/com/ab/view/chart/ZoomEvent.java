package com.ab.view.chart;

public class ZoomEvent
{
  private boolean mZoomIn;
  private float mZoomRate;

  public ZoomEvent(boolean paramBoolean, float paramFloat)
  {
    this.mZoomIn = paramBoolean;
    this.mZoomRate = paramFloat;
  }

  public float getZoomRate()
  {
    return this.mZoomRate;
  }

  public boolean isZoomIn()
  {
    return this.mZoomIn;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.chart.ZoomEvent
 * JD-Core Version:    0.6.2
 */