package com.ab.view.chart;

import android.view.MotionEvent;

public abstract interface ITouchHandler
{
  public abstract void addPanListener(PanListener paramPanListener);

  public abstract void addZoomListener(ZoomListener paramZoomListener);

  public abstract boolean handleTouch(MotionEvent paramMotionEvent);

  public abstract boolean handleTouchControl(MotionEvent paramMotionEvent);

  public abstract void removePanListener(PanListener paramPanListener);

  public abstract void removeZoomListener(ZoomListener paramZoomListener);
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.chart.ITouchHandler
 * JD-Core Version:    0.6.2
 */