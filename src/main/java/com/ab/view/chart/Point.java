package com.ab.view.chart;

import java.io.Serializable;

public final class Point
  implements Serializable
{
  private float mX;
  private float mY;

  public Point()
  {
  }

  public Point(float paramFloat1, float paramFloat2)
  {
    this.mX = paramFloat1;
    this.mY = paramFloat2;
  }

  public float getX()
  {
    return this.mX;
  }

  public float getY()
  {
    return this.mY;
  }

  public void setX(float paramFloat)
  {
    this.mX = paramFloat;
  }

  public void setY(float paramFloat)
  {
    this.mY = paramFloat;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.chart.Point
 * JD-Core Version:    0.6.2
 */