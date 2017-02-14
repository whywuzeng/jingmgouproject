package com.ab.view.chart;

import android.graphics.Color;
import android.graphics.Paint.Align;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class XYMultipleSeriesRenderer extends DefaultRenderer
{
  private boolean displayValue0 = true;
  private int explainTextSize1 = 14;
  private int explainTextSize2 = 14;
  private Map<Integer, double[]> initialRange = new LinkedHashMap();
  private float mAxisTitleTextSize = 12.0F;
  private double mBarSpacing = 0.0D;
  private int mGridColor = Color.argb(75, 200, 200, 200);
  private int mMarginsColor = 0;
  private double[] mMaxX;
  private double[] mMaxY;
  private double[] mMinX;
  private double[] mMinY;
  private Orientation mOrientation = Orientation.HORIZONTAL;
  private double[] mPanLimits;
  private boolean mPanXEnabled = true;
  private boolean mPanYEnabled = true;
  private float mPointSize = 3.0F;
  private int mScaleLineColor = Color.argb(175, 150, 150, 150);
  private boolean mScaleLineEnabled = false;
  private int mScaleRectColor = Color.argb(150, 52, 182, 232);
  private int mScaleRectHeight = 50;
  private int mScaleRectWidth = 100;
  private int mXLabels = 5;
  private float mXLabelsAngle;
  private int mXLabelsColor = -3355444;
  private boolean mXRoundedLabels = true;
  private Map<Double, String> mXTextLabels = new HashMap();
  private String mXTitle = "";
  private int mYLabels = 5;
  private float mYLabelsAngle;
  private int[] mYLabelsColor = { -3355444 };
  private double[] mYLimitsLine;
  private int[] mYLimitsLineColor = { -3355444 };
  private Map<Integer, Map<Double, String>> mYTextLabels = new LinkedHashMap();
  private String[] mYTitle;
  private double[] mZoomLimits;
  private boolean mZoomXEnabled = true;
  private boolean mZoomYEnabled = true;
  private int scaleCircleRadius = 20;
  private int scalesCount;
  private Paint.Align xLabelsAlign = Paint.Align.CENTER;
  private Paint.Align[] yAxisAlign;
  private Paint.Align[] yLabelsAlign;

  public XYMultipleSeriesRenderer()
  {
    this(1);
  }

  public XYMultipleSeriesRenderer(int paramInt)
  {
    this.scalesCount = paramInt;
    initAxesRange(paramInt);
  }

  public void addTextLabel(double paramDouble, String paramString)
  {
    addXTextLabel(paramDouble, paramString);
  }

  public void addXTextLabel(double paramDouble, String paramString)
  {
    this.mXTextLabels.put(Double.valueOf(paramDouble), paramString);
  }

  public void addYTextLabel(double paramDouble, String paramString)
  {
    addYTextLabel(paramDouble, paramString, 0);
  }

  public void addYTextLabel(double paramDouble, String paramString, int paramInt)
  {
    ((Map)this.mYTextLabels.get(Integer.valueOf(paramInt))).put(Double.valueOf(paramDouble), paramString);
  }

  public void clearTextLabels()
  {
    clearXTextLabels();
  }

  public void clearXTextLabels()
  {
    this.mXTextLabels.clear();
  }

  public void clearYTextLabels()
  {
    clearYTextLabels(0);
  }

  public void clearYTextLabels(int paramInt)
  {
    ((Map)this.mYTextLabels.get(Integer.valueOf(paramInt))).clear();
  }

  public float getAxisTitleTextSize()
  {
    return this.mAxisTitleTextSize;
  }

  public double getBarSpacing()
  {
    return this.mBarSpacing;
  }

  public double getBarsSpacing()
  {
    return getBarSpacing();
  }

  public int getExplainTextSize1()
  {
    return this.explainTextSize1;
  }

  public int getExplainTextSize2()
  {
    return this.explainTextSize2;
  }

  public int getGridColor()
  {
    return this.mGridColor;
  }

  public double[] getInitialRange()
  {
    return getInitialRange(0);
  }

  public double[] getInitialRange(int paramInt)
  {
    return (double[])this.initialRange.get(Integer.valueOf(paramInt));
  }

  public int getMarginsColor()
  {
    return this.mMarginsColor;
  }

  public Orientation getOrientation()
  {
    return this.mOrientation;
  }

  public double[] getPanLimits()
  {
    return this.mPanLimits;
  }

  public float getPointSize()
  {
    return this.mPointSize;
  }

  public int getScaleCircleRadius()
  {
    return this.scaleCircleRadius;
  }

  public int getScaleLineColor()
  {
    return this.mScaleLineColor;
  }

  public int getScaleRectColor()
  {
    return this.mScaleRectColor;
  }

  public int getScaleRectHeight()
  {
    return this.mScaleRectHeight;
  }

  public int getScaleRectWidth()
  {
    return this.mScaleRectWidth;
  }

  public int getScalesCount()
  {
    return this.scalesCount;
  }

  public double getXAxisMax()
  {
    return getXAxisMax(0);
  }

  public double getXAxisMax(int paramInt)
  {
    return this.mMaxX[paramInt];
  }

  public double getXAxisMin()
  {
    return getXAxisMin(0);
  }

  public double getXAxisMin(int paramInt)
  {
    return this.mMinX[paramInt];
  }

  public int getXLabels()
  {
    return this.mXLabels;
  }

  public Paint.Align getXLabelsAlign()
  {
    return this.xLabelsAlign;
  }

  public float getXLabelsAngle()
  {
    return this.mXLabelsAngle;
  }

  public int getXLabelsColor()
  {
    return this.mXLabelsColor;
  }

  public String getXTextLabel(Double paramDouble)
  {
    return (String)this.mXTextLabels.get(paramDouble);
  }

  public Double[] getXTextLabelLocations()
  {
    return (Double[])this.mXTextLabels.keySet().toArray(new Double[0]);
  }

  public String getXTitle()
  {
    return this.mXTitle;
  }

  public Paint.Align getYAxisAlign(int paramInt)
  {
    return this.yAxisAlign[paramInt];
  }

  public double getYAxisMax()
  {
    return getYAxisMax(0);
  }

  public double getYAxisMax(int paramInt)
  {
    return this.mMaxY[paramInt];
  }

  public double getYAxisMin()
  {
    return getYAxisMin(0);
  }

  public double getYAxisMin(int paramInt)
  {
    return this.mMinY[paramInt];
  }

  public int getYLabels()
  {
    return this.mYLabels;
  }

  public Paint.Align getYLabelsAlign(int paramInt)
  {
    return this.yLabelsAlign[paramInt];
  }

  public float getYLabelsAngle()
  {
    return this.mYLabelsAngle;
  }

  public int getYLabelsColor(int paramInt)
  {
    return this.mYLabelsColor[paramInt];
  }

  public String getYTextLabel(Double paramDouble)
  {
    return getYTextLabel(paramDouble, 0);
  }

  public String getYTextLabel(Double paramDouble, int paramInt)
  {
    return (String)((Map)this.mYTextLabels.get(Integer.valueOf(paramInt))).get(paramDouble);
  }

  public Double[] getYTextLabelLocations()
  {
    return getYTextLabelLocations(0);
  }

  public Double[] getYTextLabelLocations(int paramInt)
  {
    return (Double[])((Map)this.mYTextLabels.get(Integer.valueOf(paramInt))).keySet().toArray(new Double[0]);
  }

  public String getYTitle()
  {
    return getYTitle(0);
  }

  public String getYTitle(int paramInt)
  {
    return this.mYTitle[paramInt];
  }

  public double[] getZoomLimits()
  {
    return this.mZoomLimits;
  }

  public double[] getmYLimitsLine()
  {
    return this.mYLimitsLine;
  }

  public int[] getmYLimitsLineColor()
  {
    return this.mYLimitsLineColor;
  }

  public void initAxesRange(int paramInt)
  {
    this.mYTitle = new String[paramInt];
    this.yLabelsAlign = new Paint.Align[paramInt];
    this.yAxisAlign = new Paint.Align[paramInt];
    this.mYLabelsColor = new int[paramInt];
    this.mMinX = new double[paramInt];
    this.mMaxX = new double[paramInt];
    this.mMinY = new double[paramInt];
    this.mMaxY = new double[paramInt];
    int i = 0;
    while (true)
    {
      if (i >= paramInt)
        return;
      this.mYLabelsColor[i] = -3355444;
      initAxesRangeForScale(i);
      i += 1;
    }
  }

  public void initAxesRangeForScale(int paramInt)
  {
    this.mMinX[paramInt] = 1.7976931348623157E+308D;
    this.mMaxX[paramInt] = -1.797693134862316E+308D;
    this.mMinY[paramInt] = 1.7976931348623157E+308D;
    this.mMaxY[paramInt] = -1.797693134862316E+308D;
    double d1 = this.mMinX[paramInt];
    double d2 = this.mMaxX[paramInt];
    double d3 = this.mMinY[paramInt];
    double d4 = this.mMaxY[paramInt];
    this.initialRange.put(Integer.valueOf(paramInt), new double[] { d1, d2, d3, d4 });
    this.mYTitle[paramInt] = "";
    this.mYTextLabels.put(Integer.valueOf(paramInt), new HashMap());
    this.yLabelsAlign[paramInt] = Paint.Align.CENTER;
    this.yAxisAlign[paramInt] = Paint.Align.LEFT;
  }

  public boolean isDisplayValue0()
  {
    return this.displayValue0;
  }

  public boolean isInitialRangeSet()
  {
    return isInitialRangeSet(0);
  }

  public boolean isInitialRangeSet(int paramInt)
  {
    return this.initialRange.get(Integer.valueOf(paramInt)) != null;
  }

  public boolean isMaxXSet()
  {
    return isMaxXSet(0);
  }

  public boolean isMaxXSet(int paramInt)
  {
    return this.mMaxX[paramInt] != -1.797693134862316E+308D;
  }

  public boolean isMaxYSet()
  {
    return isMaxYSet(0);
  }

  public boolean isMaxYSet(int paramInt)
  {
    return this.mMaxY[paramInt] != -1.797693134862316E+308D;
  }

  public boolean isMinXSet()
  {
    return isMinXSet(0);
  }

  public boolean isMinXSet(int paramInt)
  {
    return this.mMinX[paramInt] != 1.7976931348623157E+308D;
  }

  public boolean isMinYSet()
  {
    return isMinYSet(0);
  }

  public boolean isMinYSet(int paramInt)
  {
    return this.mMinY[paramInt] != 1.7976931348623157E+308D;
  }

  public boolean isPanEnabled()
  {
    return (isPanXEnabled()) || (isPanYEnabled());
  }

  public boolean isPanXEnabled()
  {
    return this.mPanXEnabled;
  }

  public boolean isPanYEnabled()
  {
    return this.mPanYEnabled;
  }

  public boolean isScaleLineEnabled()
  {
    return this.mScaleLineEnabled;
  }

  public boolean isXRoundedLabels()
  {
    return this.mXRoundedLabels;
  }

  public boolean isZoomEnabled()
  {
    return (isZoomXEnabled()) || (isZoomYEnabled());
  }

  public boolean isZoomXEnabled()
  {
    return this.mZoomXEnabled;
  }

  public boolean isZoomYEnabled()
  {
    return this.mZoomYEnabled;
  }

  public void setAxisTitleTextSize(float paramFloat)
  {
    this.mAxisTitleTextSize = paramFloat;
  }

  public void setBarSpacing(double paramDouble)
  {
    this.mBarSpacing = paramDouble;
  }

  public void setChartValuesTextSize(float paramFloat)
  {
    SimpleSeriesRenderer[] arrayOfSimpleSeriesRenderer = getSeriesRenderers();
    int j = arrayOfSimpleSeriesRenderer.length;
    int i = 0;
    while (true)
    {
      if (i >= j)
        return;
      arrayOfSimpleSeriesRenderer[i].setChartValuesTextSize(paramFloat);
      i += 1;
    }
  }

  public void setDisplayChartValues(boolean paramBoolean)
  {
    SimpleSeriesRenderer[] arrayOfSimpleSeriesRenderer = getSeriesRenderers();
    int j = arrayOfSimpleSeriesRenderer.length;
    int i = 0;
    while (true)
    {
      if (i >= j)
        return;
      arrayOfSimpleSeriesRenderer[i].setDisplayChartValues(paramBoolean);
      i += 1;
    }
  }

  public void setDisplayValue0(boolean paramBoolean)
  {
    this.displayValue0 = paramBoolean;
  }

  public void setExplainTextSize1(int paramInt)
  {
    this.explainTextSize1 = paramInt;
  }

  public void setExplainTextSize2(int paramInt)
  {
    this.explainTextSize2 = paramInt;
  }

  public void setGridColor(int paramInt)
  {
    this.mGridColor = paramInt;
  }

  public void setInitialRange(double[] paramArrayOfDouble)
  {
    setInitialRange(paramArrayOfDouble, 0);
  }

  public void setInitialRange(double[] paramArrayOfDouble, int paramInt)
  {
    this.initialRange.put(Integer.valueOf(paramInt), paramArrayOfDouble);
  }

  public void setMarginsColor(int paramInt)
  {
    this.mMarginsColor = paramInt;
  }

  public void setOrientation(Orientation paramOrientation)
  {
    this.mOrientation = paramOrientation;
  }

  public void setPanEnabled(boolean paramBoolean1, boolean paramBoolean2)
  {
    this.mPanXEnabled = paramBoolean1;
    this.mPanYEnabled = paramBoolean2;
  }

  public void setPanLimits(double[] paramArrayOfDouble)
  {
    this.mPanLimits = paramArrayOfDouble;
  }

  public void setPointSize(float paramFloat)
  {
    this.mPointSize = paramFloat;
  }

  public void setRange(double[] paramArrayOfDouble)
  {
    setRange(paramArrayOfDouble, 0);
  }

  public void setRange(double[] paramArrayOfDouble, int paramInt)
  {
    setXAxisMin(paramArrayOfDouble[0], paramInt);
    setXAxisMax(paramArrayOfDouble[1], paramInt);
    setYAxisMin(paramArrayOfDouble[2], paramInt);
    setYAxisMax(paramArrayOfDouble[3], paramInt);
  }

  public void setScaleCircleRadius(int paramInt)
  {
    this.scaleCircleRadius = paramInt;
  }

  public void setScaleLineColor(int paramInt)
  {
    this.mScaleLineColor = paramInt;
  }

  public void setScaleLineEnabled(boolean paramBoolean)
  {
    this.mScaleLineEnabled = paramBoolean;
  }

  public void setScaleRectColor(int paramInt)
  {
    this.mScaleRectColor = paramInt;
  }

  public void setScaleRectHeight(int paramInt)
  {
    this.mScaleRectHeight = paramInt;
  }

  public void setScaleRectWidth(int paramInt)
  {
    this.mScaleRectWidth = paramInt;
  }

  public void setXAxisMax(double paramDouble)
  {
    setXAxisMax(paramDouble, 0);
  }

  public void setXAxisMax(double paramDouble, int paramInt)
  {
    if (!isMaxXSet(paramInt))
      ((double[])this.initialRange.get(Integer.valueOf(paramInt)))[1] = paramDouble;
    this.mMaxX[paramInt] = paramDouble;
  }

  public void setXAxisMin(double paramDouble)
  {
    setXAxisMin(paramDouble, 0);
  }

  public void setXAxisMin(double paramDouble, int paramInt)
  {
    if (!isMinXSet(paramInt))
      ((double[])this.initialRange.get(Integer.valueOf(paramInt)))[0] = paramDouble;
    this.mMinX[paramInt] = paramDouble;
  }

  public void setXLabels(int paramInt)
  {
    this.mXLabels = paramInt;
  }

  public void setXLabelsAlign(Paint.Align paramAlign)
  {
    this.xLabelsAlign = paramAlign;
  }

  public void setXLabelsAngle(float paramFloat)
  {
    this.mXLabelsAngle = paramFloat;
  }

  public void setXLabelsColor(int paramInt)
  {
    this.mXLabelsColor = paramInt;
  }

  public void setXRoundedLabels(boolean paramBoolean)
  {
    this.mXRoundedLabels = paramBoolean;
  }

  public void setXTitle(String paramString)
  {
    this.mXTitle = paramString;
  }

  public void setYAxisAlign(Paint.Align paramAlign, int paramInt)
  {
    this.yAxisAlign[paramInt] = paramAlign;
  }

  public void setYAxisMax(double paramDouble)
  {
    setYAxisMax(paramDouble, 0);
  }

  public void setYAxisMax(double paramDouble, int paramInt)
  {
    if (!isMaxYSet(paramInt))
      ((double[])this.initialRange.get(Integer.valueOf(paramInt)))[3] = paramDouble;
    this.mMaxY[paramInt] = paramDouble;
  }

  public void setYAxisMin(double paramDouble)
  {
    setYAxisMin(paramDouble, 0);
  }

  public void setYAxisMin(double paramDouble, int paramInt)
  {
    if (!isMinYSet(paramInt))
      ((double[])this.initialRange.get(Integer.valueOf(paramInt)))[2] = paramDouble;
    this.mMinY[paramInt] = paramDouble;
  }

  public void setYLabels(int paramInt)
  {
    this.mYLabels = paramInt;
  }

  public void setYLabelsAlign(Paint.Align paramAlign)
  {
    setYLabelsAlign(paramAlign, 0);
  }

  public void setYLabelsAlign(Paint.Align paramAlign, int paramInt)
  {
    this.yLabelsAlign[paramInt] = paramAlign;
  }

  public void setYLabelsAngle(float paramFloat)
  {
    this.mYLabelsAngle = paramFloat;
  }

  public void setYLabelsColor(int paramInt1, int paramInt2)
  {
    this.mYLabelsColor[paramInt1] = paramInt2;
  }

  public void setYTitle(String paramString)
  {
    setYTitle(paramString, 0);
  }

  public void setYTitle(String paramString, int paramInt)
  {
    this.mYTitle[paramInt] = paramString;
  }

  public void setZoomEnabled(boolean paramBoolean1, boolean paramBoolean2)
  {
    this.mZoomXEnabled = paramBoolean1;
    this.mZoomYEnabled = paramBoolean2;
  }

  public void setZoomLimits(double[] paramArrayOfDouble)
  {
    this.mZoomLimits = paramArrayOfDouble;
  }

  public void setmYLimitsLine(double[] paramArrayOfDouble)
  {
    this.mYLimitsLine = paramArrayOfDouble;
  }

  public void setmYLimitsLineColor(int[] paramArrayOfInt)
  {
    this.mYLimitsLineColor = paramArrayOfInt;
  }

  public static enum Orientation
  {
    HORIZONTAL(0), VERTICAL(90);

    private int mAngle = 0;

    private Orientation(int arg3)
    {
      int j;
      this.mAngle = j;
    }

    public int getAngle()
    {
      return this.mAngle;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.chart.XYMultipleSeriesRenderer
 * JD-Core Version:    0.6.2
 */