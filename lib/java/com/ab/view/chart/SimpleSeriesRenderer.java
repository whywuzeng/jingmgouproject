package com.ab.view.chart;

import android.graphics.Paint.Align;
import java.io.Serializable;

public class SimpleSeriesRenderer
  implements Serializable
{
  private float mChartValuesSpacing = 5.0F;
  private Paint.Align mChartValuesTextAlign = Paint.Align.CENTER;
  private float mChartValuesTextSize = 10.0F;
  private int mColor = -16776961;
  private boolean mDisplayChartValues;
  private boolean mGradientEnabled = false;
  private int mGradientStartColor;
  private double mGradientStartValue;
  private int mGradientStopColor;
  private double mGradientStopValue;
  private BasicStroke mStroke;

  public float getChartValuesSpacing()
  {
    return this.mChartValuesSpacing;
  }

  public Paint.Align getChartValuesTextAlign()
  {
    return this.mChartValuesTextAlign;
  }

  public float getChartValuesTextSize()
  {
    return this.mChartValuesTextSize;
  }

  public int getColor()
  {
    return this.mColor;
  }

  public int getGradientStartColor()
  {
    return this.mGradientStartColor;
  }

  public double getGradientStartValue()
  {
    return this.mGradientStartValue;
  }

  public int getGradientStopColor()
  {
    return this.mGradientStopColor;
  }

  public double getGradientStopValue()
  {
    return this.mGradientStopValue;
  }

  public BasicStroke getStroke()
  {
    return this.mStroke;
  }

  public boolean isDisplayChartValues()
  {
    return this.mDisplayChartValues;
  }

  public boolean isGradientEnabled()
  {
    return this.mGradientEnabled;
  }

  public void setChartValuesSpacing(float paramFloat)
  {
    this.mChartValuesSpacing = paramFloat;
  }

  public void setChartValuesTextAlign(Paint.Align paramAlign)
  {
    this.mChartValuesTextAlign = paramAlign;
  }

  public void setChartValuesTextSize(float paramFloat)
  {
    this.mChartValuesTextSize = paramFloat;
  }

  public void setColor(int paramInt)
  {
    this.mColor = paramInt;
  }

  public void setDisplayChartValues(boolean paramBoolean)
  {
    this.mDisplayChartValues = paramBoolean;
  }

  public void setGradientEnabled(boolean paramBoolean)
  {
    this.mGradientEnabled = paramBoolean;
  }

  public void setGradientStart(double paramDouble, int paramInt)
  {
    this.mGradientStartValue = paramDouble;
    this.mGradientStartColor = paramInt;
  }

  public void setGradientStop(double paramDouble, int paramInt)
  {
    this.mGradientStopValue = paramDouble;
    this.mGradientStopColor = paramInt;
  }

  public void setStroke(BasicStroke paramBasicStroke)
  {
    this.mStroke = paramBasicStroke;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.chart.SimpleSeriesRenderer
 * JD-Core Version:    0.6.2
 */