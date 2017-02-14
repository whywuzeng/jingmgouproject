package com.ab.view.chart;

import android.graphics.Color;

public class XYSeriesRenderer extends SimpleSeriesRenderer
{
  private boolean mFillBelowLine = false;
  private int mFillColor = Color.argb(125, 0, 0, 200);
  private boolean mFillPoints = false;
  private float mLineWidth = 1.0F;
  private PointStyle mPointStyle = PointStyle.POINT;

  public int getFillBelowLineColor()
  {
    return this.mFillColor;
  }

  public float getLineWidth()
  {
    return this.mLineWidth;
  }

  public PointStyle getPointStyle()
  {
    return this.mPointStyle;
  }

  public boolean isFillBelowLine()
  {
    return this.mFillBelowLine;
  }

  public boolean isFillPoints()
  {
    return this.mFillPoints;
  }

  public void setFillBelowLine(boolean paramBoolean)
  {
    this.mFillBelowLine = paramBoolean;
  }

  public void setFillBelowLineColor(int paramInt)
  {
    this.mFillColor = paramInt;
  }

  public void setFillPoints(boolean paramBoolean)
  {
    this.mFillPoints = paramBoolean;
  }

  public void setLineWidth(float paramFloat)
  {
    this.mLineWidth = paramFloat;
  }

  public void setPointStyle(PointStyle paramPointStyle)
  {
    this.mPointStyle = paramPointStyle;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.chart.XYSeriesRenderer
 * JD-Core Version:    0.6.2
 */