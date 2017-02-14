package com.ab.view.chart;

import java.io.Serializable;

public class PieSegment
  implements Serializable
{
  private int mDataIndex;
  private float mEndAngle;
  private float mStartAngle;
  private float mValue;

  public PieSegment(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    this.mStartAngle = paramFloat2;
    this.mEndAngle = (paramFloat3 + paramFloat2);
    this.mDataIndex = paramInt;
    this.mValue = paramFloat1;
  }

  protected int getDataIndex()
  {
    return this.mDataIndex;
  }

  protected float getEndAngle()
  {
    return this.mEndAngle;
  }

  protected float getStartAngle()
  {
    return this.mStartAngle;
  }

  protected float getValue()
  {
    return this.mValue;
  }

  public boolean isInSegment(double paramDouble)
  {
    return (paramDouble >= this.mStartAngle) && (paramDouble <= this.mEndAngle);
  }

  public String toString()
  {
    return "mDataIndex=" + this.mDataIndex + ",mValue=" + this.mValue + ",mStartAngle=" + this.mStartAngle + ",mEndAngle=" + this.mEndAngle;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.chart.PieSegment
 * JD-Core Version:    0.6.2
 */