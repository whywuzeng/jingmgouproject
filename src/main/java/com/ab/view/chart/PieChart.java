package com.ab.view.chart;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import java.util.ArrayList;
import java.util.List;

public class PieChart extends RoundChart
{
  private PieMapper mPieMapper = new PieMapper();

  public PieChart(CategorySeries paramCategorySeries, DefaultRenderer paramDefaultRenderer)
  {
    super(paramCategorySeries, paramDefaultRenderer);
  }

  public void draw(Canvas paramCanvas, int paramInt1, int paramInt2, int paramInt3, int paramInt4, Paint paramPaint)
  {
    paramPaint.setAntiAlias(this.mRenderer.isAntialiasing());
    paramPaint.setStyle(Paint.Style.FILL);
    paramPaint.setTextSize(this.mRenderer.getLabelsTextSize());
    int j = getLegendSize(this.mRenderer, paramInt4 / 5, 0.0F);
    int m = paramInt1 + paramInt3;
    int n = this.mDataset.getItemCount();
    double d = 0.0D;
    String[] arrayOfString = new String[n];
    int i = 0;
    float f1;
    int k;
    label263: float f2;
    float f3;
    RectF localRectF;
    ArrayList localArrayList;
    if (i >= n)
    {
      i = j;
      if (this.mRenderer.isFitLegend())
        i = drawLegend(paramCanvas, this.mRenderer, arrayOfString, paramInt1, m, paramInt2, paramInt3, paramInt4, j, paramPaint, true);
      j = paramInt2 + paramInt4 - i;
      drawBackground(this.mRenderer, paramCanvas, paramInt1, paramInt2, paramInt3, paramInt4, paramPaint, false, 0);
      f1 = this.mRenderer.getStartAngle();
      k = (int)(Math.min(Math.abs(m - paramInt1), Math.abs(j - paramInt2)) * 0.35D * this.mRenderer.getScale());
      if (this.mCenterX == 2147483647)
        this.mCenterX = ((paramInt1 + m) / 2);
      if (this.mCenterY == 2147483647)
        this.mCenterY = ((j + paramInt2) / 2);
      this.mPieMapper.setDimensions(k, this.mCenterX, this.mCenterY);
      if (!this.mPieMapper.areAllSegmentPresent(n))
        break label432;
      j = 0;
      if (j != 0)
        this.mPieMapper.clearPieSegments();
      f2 = k * 0.9F;
      f3 = k * 1.1F;
      localRectF = new RectF(this.mCenterX - k, this.mCenterY - k, this.mCenterX + k, this.mCenterY + k);
      localArrayList = new ArrayList();
      k = 0;
    }
    while (true)
    {
      if (k >= n)
      {
        localArrayList.clear();
        drawLegend(paramCanvas, this.mRenderer, arrayOfString, paramInt1, m, paramInt2, paramInt3, paramInt4, i, paramPaint, false);
        drawTitle(paramCanvas, paramInt1, paramInt2, paramInt3, paramPaint);
        return;
        d += this.mDataset.getValue(i);
        arrayOfString[i] = this.mDataset.getCategory(i);
        i += 1;
        break;
        label432: j = 1;
        break label263;
      }
      paramPaint.setColor(this.mRenderer.getSeriesRendererAt(k).getColor());
      float f4 = (float)this.mDataset.getValue(k);
      float f5 = (float)(f4 / d * 360.0D);
      paramCanvas.drawArc(localRectF, f1, f5, true, paramPaint);
      drawLabel(paramCanvas, this.mDataset.getCategory(k), this.mRenderer, localArrayList, this.mCenterX, this.mCenterY, f2, f3, f1, f5, paramInt1, m, this.mRenderer.getLabelsColor(), paramPaint, true);
      if (this.mRenderer.isDisplayValues())
        drawLabel(paramCanvas, getLabel(this.mDataset.getValue(k)), this.mRenderer, localArrayList, this.mCenterX, this.mCenterY, f2 / 2.0F, f3 / 2.0F, f1, f5, paramInt1, m, this.mRenderer.getLabelsColor(), paramPaint, false);
      if (j != 0)
        this.mPieMapper.addPieSegment(k, f4, f1, f5);
      f1 += f5;
      k += 1;
    }
  }

  public SeriesSelection getSeriesAndPointForScreenCoordinate(Point paramPoint)
  {
    return this.mPieMapper.getSeriesAndPointForScreenCoordinate(paramPoint);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.chart.PieChart
 * JD-Core Version:    0.6.2
 */