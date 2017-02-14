package com.ab.view.chart;

import android.graphics.RectF;

public class ClickableArea
{
  private RectF rect;
  private double x;
  private double y;

  public ClickableArea(RectF paramRectF, double paramDouble1, double paramDouble2)
  {
    this.rect = paramRectF;
    this.x = paramDouble1;
    this.y = paramDouble2;
  }

  public RectF getRect()
  {
    return this.rect;
  }

  public double getX()
  {
    return this.x;
  }

  public double getY()
  {
    return this.y;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.chart.ClickableArea
 * JD-Core Version:    0.6.2
 */