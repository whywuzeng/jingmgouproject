package com.ab.view.chart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PieMapper
  implements Serializable
{
  private int mCenterX;
  private int mCenterY;
  private int mPieChartRadius;
  private List<PieSegment> mPieSegmentList = new ArrayList();

  public void addPieSegment(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    this.mPieSegmentList.add(new PieSegment(paramInt, paramFloat1, paramFloat2, paramFloat3));
  }

  public boolean areAllSegmentPresent(int paramInt)
  {
    return this.mPieSegmentList.size() == paramInt;
  }

  public void clearPieSegments()
  {
    this.mPieSegmentList.clear();
  }

  public double getAngle(Point paramPoint)
  {
    double d = paramPoint.getX() - this.mCenterX;
    d = Math.atan2(-(paramPoint.getY() - this.mCenterY), d);
    if (d < 0.0D);
    for (d = Math.abs(d); ; d = 6.283185307179586D - d)
      return Math.toDegrees(d);
  }

  public SeriesSelection getSeriesAndPointForScreenCoordinate(Point paramPoint)
  {
    double d;
    if (isOnPieChart(paramPoint))
    {
      d = getAngle(paramPoint);
      paramPoint = this.mPieSegmentList.iterator();
    }
    PieSegment localPieSegment;
    do
    {
      if (!paramPoint.hasNext())
        return null;
      localPieSegment = (PieSegment)paramPoint.next();
    }
    while (!localPieSegment.isInSegment(d));
    return new SeriesSelection(0, localPieSegment.getDataIndex(), localPieSegment.getValue(), localPieSegment.getValue());
  }

  public boolean isOnPieChart(Point paramPoint)
  {
    return Math.pow(this.mCenterX - paramPoint.getX(), 2.0D) + Math.pow(this.mCenterY - paramPoint.getY(), 2.0D) <= this.mPieChartRadius * this.mPieChartRadius;
  }

  public void setDimensions(int paramInt1, int paramInt2, int paramInt3)
  {
    this.mPieChartRadius = paramInt1;
    this.mCenterX = paramInt2;
    this.mCenterY = paramInt3;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.chart.PieMapper
 * JD-Core Version:    0.6.2
 */