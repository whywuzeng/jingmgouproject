package com.ab.view.chart;

import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;

public abstract class XYChart extends AbstractChart
{
  private Map<Integer, List<ClickableArea>> clickableAreas = new HashMap();
  private final Map<Integer, List<Integer>> colors = new HashMap();
  private final Map<Integer, List<String>> explains = new HashMap();
  private final Map<Integer, double[]> mCalcRange = new HashMap();
  private Point mCenter;
  protected XYMultipleSeriesDataset mDataset;
  protected XYMultipleSeriesRenderer mRenderer;
  private float mScale;
  private Rect mScreenR;
  private float mTranslate;
  private Map<Integer, List<Float>> points = new HashMap();
  private Map<Integer, List<Double>> values = new HashMap();

  protected XYChart()
  {
  }

  public XYChart(XYMultipleSeriesDataset paramXYMultipleSeriesDataset, XYMultipleSeriesRenderer paramXYMultipleSeriesRenderer)
  {
    this.mDataset = paramXYMultipleSeriesDataset;
    Log.d("XYChart", "XYMultipleSeriesDataset 设置成功");
    this.mRenderer = paramXYMultipleSeriesRenderer;
  }

  private int getLabelLinePos(Paint.Align paramAlign)
  {
    int i = 4;
    if (paramAlign == Paint.Align.LEFT)
      i = -4;
    return i;
  }

  private List<Double> getValidLabels(List<Double> paramList)
  {
    ArrayList localArrayList = new ArrayList(paramList);
    paramList = paramList.iterator();
    while (true)
    {
      if (!paramList.hasNext())
        return localArrayList;
      Double localDouble = (Double)paramList.next();
      if (localDouble.isNaN())
        localArrayList.remove(localDouble);
    }
  }

  private void setStroke(Paint.Cap paramCap, Paint.Join paramJoin, float paramFloat, Paint.Style paramStyle, PathEffect paramPathEffect, Paint paramPaint)
  {
    paramPaint.setStrokeCap(paramCap);
    paramPaint.setStrokeJoin(paramJoin);
    paramPaint.setStrokeMiter(paramFloat);
    paramPaint.setPathEffect(paramPathEffect);
    paramPaint.setStyle(paramStyle);
  }

  private void transform(Canvas paramCanvas, float paramFloat, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      paramCanvas.scale(1.0F / this.mScale, this.mScale);
      paramCanvas.translate(this.mTranslate, -this.mTranslate);
      paramCanvas.rotate(-paramFloat, this.mCenter.getX(), this.mCenter.getY());
      return;
    }
    paramCanvas.rotate(paramFloat, this.mCenter.getX(), this.mCenter.getY());
    paramCanvas.translate(-this.mTranslate, this.mTranslate);
    paramCanvas.scale(this.mScale, 1.0F / this.mScale);
  }

  protected abstract ClickableArea[] clickableAreasForPoints(float[] paramArrayOfFloat, double[] paramArrayOfDouble, float paramFloat, int paramInt1, int paramInt2);

  public void draw(Canvas paramCanvas, int paramInt1, int paramInt2, int paramInt3, int paramInt4, Paint paramPaint)
  {
    Log.d("XYChart", "draw在这里初始化点的数据的");
    paramPaint.setAntiAlias(this.mRenderer.isAntialiasing());
    int j = getLegendSize(this.mRenderer, paramInt4 / 5, this.mRenderer.getAxisTitleTextSize());
    Object localObject4 = this.mRenderer.getMargins();
    int i3 = paramInt1 + localObject4[1];
    int i5 = paramInt2 + localObject4[0];
    int m = paramInt1 + paramInt3 - localObject4[3];
    int i8 = this.mDataset.getSeriesCount();
    String[] arrayOfString = new String[i8];
    int i = 0;
    int n;
    XYMultipleSeriesRenderer.Orientation localOrientation;
    int k;
    int i6;
    label353: int i1;
    label447: int i7;
    if (i >= i8)
    {
      n = j;
      if (this.mRenderer.isFitLegend())
      {
        n = j;
        if (this.mRenderer.isShowLegend())
          n = drawLegend(paramCanvas, this.mRenderer, arrayOfString, i3, m, paramInt2, paramInt3, paramInt4, j, paramPaint, true);
      }
      i = paramInt2 + paramInt4 - localObject4[2] - n;
      if (this.mScreenR == null)
        this.mScreenR = new Rect();
      this.mScreenR.set(i3, i5, m, i);
      drawBackground(this.mRenderer, paramCanvas, paramInt1, paramInt2, paramInt3, paramInt4, paramPaint, false, 0);
      if ((paramPaint.getTypeface() == null) || (!paramPaint.getTypeface().toString().equals(this.mRenderer.getTextTypefaceName())) || (paramPaint.getTypeface().getStyle() != this.mRenderer.getTextTypefaceStyle()))
        paramPaint.setTypeface(Typeface.create(this.mRenderer.getTextTypefaceName(), this.mRenderer.getTextTypefaceStyle()));
      localOrientation = this.mRenderer.getOrientation();
      k = m;
      j = i;
      if (localOrientation == XYMultipleSeriesRenderer.Orientation.VERTICAL)
      {
        k = m - n;
        j = i + (n - 20);
      }
      i6 = localOrientation.getAngle();
      if (i6 != 90)
        break label492;
      m = 1;
      this.mScale = (paramInt4 / paramInt3);
      this.mTranslate = (Math.abs(paramInt3 - paramInt4) / 2);
      if (this.mScale < 1.0F)
        this.mTranslate *= -1.0F;
      this.mCenter = new Point((paramInt1 + paramInt3) / 2, (paramInt2 + paramInt4) / 2);
      if (m != 0)
        transform(paramCanvas, i6, false);
      i1 = -2147483647;
      i = 0;
      if (i < i8)
        break label498;
      i7 = i1 + 1;
      if (i7 >= 0)
        break label526;
    }
    label526: label609: label3555: 
    while (true)
    {
      return;
      arrayOfString[i] = this.mDataset.getSeriesAt(i).getTitle();
      i += 1;
      break;
      label492: m = 0;
      break label353;
      label498: i1 = Math.max(i1, this.mDataset.getSeriesAt(i).getScaleNumber());
      i += 1;
      break label447;
      Object localObject1 = new double[i7];
      Object localObject2 = new double[i7];
      double[] arrayOfDouble1 = new double[i7];
      double[] arrayOfDouble2 = new double[i7];
      Object localObject5 = new boolean[i7];
      Object localObject6 = new boolean[i7];
      Object localObject7 = new boolean[i7];
      Object localObject8 = new boolean[i7];
      i = 0;
      Object localObject3;
      double[] arrayOfDouble3;
      label633: int i2;
      boolean bool2;
      float f1;
      if (i >= i7)
      {
        localObject3 = new double[i7];
        arrayOfDouble3 = new double[i7];
        i = 0;
        if (i < i8)
          break label1490;
        i = 0;
        if (i < i7)
          break label1748;
        localObject5 = this.mRenderer.getmYLimitsLine();
        if (localObject5 != null)
        {
          i1 = 0;
          if (i1 < i7)
            break label1837;
        }
        i2 = 0;
        this.clickableAreas = new HashMap();
        i = 0;
        if (i < i8)
          break label2072;
        drawBackground(this.mRenderer, paramCanvas, paramInt1, j, paramInt3, paramInt4 - j, paramPaint, true, this.mRenderer.getMarginsColor());
        drawBackground(this.mRenderer, paramCanvas, paramInt1, paramInt2, paramInt3, localObject4[0], paramPaint, true, this.mRenderer.getMarginsColor());
        if (localOrientation != XYMultipleSeriesRenderer.Orientation.HORIZONTAL)
          break label2784;
        drawBackground(this.mRenderer, paramCanvas, paramInt1, paramInt2, i3 - paramInt1, paramInt4 - paramInt2, paramPaint, true, this.mRenderer.getMarginsColor());
        drawBackground(this.mRenderer, paramCanvas, k, paramInt2, localObject4[3], paramInt4 - paramInt2, paramPaint, true, this.mRenderer.getMarginsColor());
        if ((!this.mRenderer.isShowLabels()) || (i2 == 0))
          break label2855;
        i = 1;
        boolean bool1 = this.mRenderer.isShowGridX();
        bool2 = this.mRenderer.isShowCustomTextGrid();
        if ((i != 0) || (bool1))
        {
          localObject4 = getValidLabels(getXLabels(localObject1[0], localObject2[0], this.mRenderer.getXLabels()));
          localObject5 = getYLabels(arrayOfDouble1, arrayOfDouble2, i7);
          i1 = i3;
          i2 = i1;
          if (i != 0)
          {
            paramPaint.setColor(this.mRenderer.getXLabelsColor());
            paramPaint.setTextSize(this.mRenderer.getLabelsTextSize());
            paramPaint.setTextAlign(this.mRenderer.getXLabelsAlign());
            i2 = i1;
            if (this.mRenderer.getXLabelsAlign() == Paint.Align.LEFT)
              i2 = (int)(i1 + this.mRenderer.getLabelsTextSize() / 4.0F);
          }
          drawXLabels((List)localObject4, this.mRenderer.getXTextLabelLocations(), paramCanvas, paramPaint, i2, i5, j, localObject3[0], localObject1[0], localObject2[0]);
          drawYLabels((Map)localObject5, paramCanvas, paramPaint, i7, i3, k, j, arrayOfDouble3, arrayOfDouble1);
          if (i != 0)
          {
            paramPaint.setColor(this.mRenderer.getLabelsColor());
            i1 = 0;
            if (i1 < i7)
              break label2861;
          }
          if (i != 0)
          {
            paramPaint.setColor(this.mRenderer.getLabelsColor());
            f1 = this.mRenderer.getAxisTitleTextSize();
            paramPaint.setTextSize(f1);
            paramPaint.setTextAlign(Paint.Align.CENTER);
            if (localOrientation != XYMultipleSeriesRenderer.Orientation.HORIZONTAL)
              break label3334;
            drawText(paramCanvas, this.mRenderer.getXTitle(), paramInt3 / 2 + paramInt1, j + this.mRenderer.getLabelsTextSize() * 4.0F / 3.0F + f1, paramPaint, 0.0F);
            i = 0;
            if (i < i7)
              break label3245;
            paramPaint.setTextSize(this.mRenderer.getChartTitleTextSize());
            drawText(paramCanvas, this.mRenderer.getChartTitle(), paramInt3 / 2 + paramInt1, paramInt2 + this.mRenderer.getChartTitleTextSize(), paramPaint, 0.0F);
          }
        }
        if (localOrientation != XYMultipleSeriesRenderer.Orientation.HORIZONTAL)
          break label3445;
        drawLegend(paramCanvas, this.mRenderer, arrayOfString, i3, k, paramInt2, paramInt3, paramInt4, n, paramPaint, false);
        if (this.mRenderer.isShowAxes())
        {
          paramPaint.setColor(this.mRenderer.getAxesColor());
          paramCanvas.drawLine(i3, j, k, j, paramPaint);
          paramInt1 = 0;
          paramInt2 = 0;
          if ((paramInt2 < i7) && (paramInt1 == 0))
            break label3500;
          if (localOrientation != XYMultipleSeriesRenderer.Orientation.HORIZONTAL)
            break label3528;
          paramCanvas.drawLine(i3, i5, i3, j, paramPaint);
          if (paramInt1 != 0)
            paramCanvas.drawLine(k, i5, k, j, paramPaint);
        }
      }
      while (true)
      {
        if (m == 0)
          break label3555;
        transform(paramCanvas, i6, true);
        return;
        localObject1[i] = this.mRenderer.getXAxisMin(i);
        localObject2[i] = this.mRenderer.getXAxisMax(i);
        arrayOfDouble1[i] = this.mRenderer.getYAxisMin(i);
        arrayOfDouble2[i] = this.mRenderer.getYAxisMax(i);
        localObject5[i] = this.mRenderer.isMinXSet(i);
        localObject6[i] = this.mRenderer.isMaxXSet(i);
        localObject7[i] = this.mRenderer.isMinYSet(i);
        localObject8[i] = this.mRenderer.isMaxYSet(i);
        if (this.mCalcRange.get(Integer.valueOf(i)) == null)
          this.mCalcRange.put(Integer.valueOf(i), new double[4]);
        i += 1;
        break;
        Object localObject9 = this.mDataset.getSeriesAt(i);
        i1 = ((XYSeries)localObject9).getScaleNumber();
        if (((XYSeries)localObject9).getItemCount() == 0);
        while (true)
        {
          i += 1;
          break;
          if (localObject5[i1] == 0)
          {
            d1 = ((XYSeries)localObject9).getMinX();
            localObject1[i1] = Math.min(localObject1[i1], d1);
            ((double[])this.mCalcRange.get(Integer.valueOf(i1)))[0] = localObject1[i1];
          }
          if (localObject6[i1] == 0)
          {
            d1 = ((XYSeries)localObject9).getMaxX();
            localObject2[i1] = Math.max(localObject2[i1], d1);
            ((double[])this.mCalcRange.get(Integer.valueOf(i1)))[1] = localObject2[i1];
          }
          if (localObject7[i1] == 0)
          {
            d1 = ((XYSeries)localObject9).getMinY();
            arrayOfDouble1[i1] = Math.min(arrayOfDouble1[i1], (float)d1);
            ((double[])this.mCalcRange.get(Integer.valueOf(i1)))[2] = arrayOfDouble1[i1];
          }
          if (localObject8[i1] == 0)
          {
            d1 = ((XYSeries)localObject9).getMaxY();
            arrayOfDouble2[i1] = Math.max(arrayOfDouble2[i1], (float)d1);
            ((double[])this.mCalcRange.get(Integer.valueOf(i1)))[3] = arrayOfDouble2[i1];
          }
        }
        if (localObject2[i] - localObject1[i] != 0.0D)
          localObject3[i] = ((k - i3) / (localObject2[i] - localObject1[i]));
        if (arrayOfDouble2[i] - arrayOfDouble1[i] != 0.0D)
          arrayOfDouble3[i] = ((float)((j - i5) / (arrayOfDouble2[i] - arrayOfDouble1[i])));
        i += 1;
        break label609;
        this.mRenderer.getYAxisAlign(i1);
        i = 0;
        if (i >= localObject5.length)
        {
          i1 += 1;
          break label633;
        }
        double d1 = localObject5[i];
        f1 = (float)(j - arrayOfDouble3[i1] * (Double.valueOf(d1).doubleValue() - arrayOfDouble1[i1]));
        i2 = this.mRenderer.getmYLimitsLineColor()[i];
        paramPaint.setColor(i2);
        float f2;
        if (i + 1 < localObject5.length)
        {
          d1 = localObject5[(i + 1)];
          i4 = this.mRenderer.getmYLimitsLineColor()[(i + 1)];
          f2 = (float)(j - arrayOfDouble3[i1] * (Double.valueOf(d1).doubleValue() - arrayOfDouble1[i1]));
          if (i2 == i4)
          {
            paramCanvas.drawRect(i3, f1, k, f2, paramPaint);
            i += 1;
          }
        }
        while (true)
        {
          i += 1;
          break;
          paramCanvas.drawLine(i3, f1, k, f1, paramPaint);
          paramCanvas.drawLine(i3, f2, k, f2, paramPaint);
          break label2003;
          paramCanvas.drawLine(i3, f1, k, f1, paramPaint);
        }
        localObject5 = this.mDataset.getSeriesAt(i);
        int i9 = ((XYSeries)localObject5).getScaleNumber();
        if (((XYSeries)localObject5).getItemCount() == 0)
        {
          i += 1;
          break label657;
        }
        int i4 = 1;
        localObject6 = this.mRenderer.getSeriesRendererAt(i);
        localObject7 = new ArrayList();
        localObject8 = new ArrayList();
        localObject9 = new ArrayList();
        ArrayList localArrayList = new ArrayList();
        f1 = Math.min(j, (float)(j + arrayOfDouble3[i9] * arrayOfDouble1[i9]));
        LinkedList localLinkedList = new LinkedList();
        this.clickableAreas.put(Integer.valueOf(i), localLinkedList);
        Object localObject10 = ((XYSeries)localObject5).getRange(localObject1[i9], localObject2[i9], 1);
        i1 = -1;
        localObject10 = ((SortedMap)localObject10).entrySet().iterator();
        while (true)
        {
          if (!((Iterator)localObject10).hasNext())
          {
            this.points.put(Integer.valueOf(i), localObject7);
            this.values.put(Integer.valueOf(i), localObject8);
            this.colors.put(Integer.valueOf(i), localObject9);
            this.explains.put(Integer.valueOf(i), localArrayList);
            i2 = i4;
            if (this.points.size() <= 0)
              break;
            drawSeries((XYSeries)localObject5, paramCanvas, paramPaint, (List)localObject7, (List)localObject9, (SimpleSeriesRenderer)localObject6, f1, i, localOrientation, i1);
            localLinkedList.addAll(Arrays.asList(clickableAreasForPoints(MathHelper.getFloats((List)localObject7), MathHelper.getDoubles((List)localObject8), f1, i, i1)));
            i2 = i4;
            break;
          }
          Map.Entry localEntry = (Map.Entry)((Iterator)localObject10).next();
          d1 = ((Double)localEntry.getKey()).doubleValue();
          double d2 = ((Double)localEntry.getValue()).doubleValue();
          i2 = i1;
          if (i1 < 0)
            i2 = ((XYSeries)localObject5).getIndexForKey(d1);
          ((List)localObject8).add((Double)localEntry.getKey());
          ((List)localObject8).add((Double)localEntry.getValue());
          ((List)localObject9).add(Integer.valueOf(((XYSeries)localObject5).getmXCValue(((Double)localEntry.getKey()).doubleValue())));
          localArrayList.add(((XYSeries)localObject5).getmXEValue(((Double)localEntry.getKey()).doubleValue()));
          if (!isNullValue(d2))
          {
            ((List)localObject7).add(Float.valueOf((float)(i3 + localObject3[i9] * (d1 - localObject1[i9]))));
            ((List)localObject7).add(Float.valueOf((float)(j - arrayOfDouble3[i9] * (d2 - arrayOfDouble1[i9]))));
            i1 = i2;
          }
          else if (isRenderNullValues())
          {
            ((List)localObject7).add(Float.valueOf((float)(i3 + localObject3[i9] * (d1 - localObject1[i9]))));
            ((List)localObject7).add(Float.valueOf((float)(j - arrayOfDouble3[i9] * -arrayOfDouble1[i9])));
            i1 = i2;
          }
          else
          {
            if (((List)localObject7).size() > 0)
            {
              drawSeries((XYSeries)localObject5, paramCanvas, paramPaint, (List)localObject7, (List)localObject9, (SimpleSeriesRenderer)localObject6, f1, i, localOrientation, i2);
              localLinkedList.addAll(Arrays.asList(clickableAreasForPoints(MathHelper.getFloats((List)localObject7), MathHelper.getDoubles((List)localObject8), f1, i, i2)));
              ((List)localObject7).clear();
              ((List)localObject8).clear();
            }
            localLinkedList.add(null);
            i1 = i2;
          }
        }
        if (localOrientation != XYMultipleSeriesRenderer.Orientation.VERTICAL)
          break label787;
        drawBackground(this.mRenderer, paramCanvas, k, paramInt2, paramInt3 - k, paramInt4 - paramInt2, paramPaint, true, this.mRenderer.getMarginsColor());
        drawBackground(this.mRenderer, paramCanvas, paramInt1, paramInt2, i3 - paramInt1, paramInt4 - paramInt2, paramPaint, true, this.mRenderer.getMarginsColor());
        break label787;
        i = 0;
        break label805;
        localObject1 = this.mRenderer.getYAxisAlign(i1);
        localObject2 = this.mRenderer.getYTextLabelLocations(i1);
        i4 = localObject2.length;
        i2 = 0;
        if (i2 >= i4)
        {
          i1 += 1;
          break label1029;
        }
        localObject3 = localObject2[i2];
        if ((arrayOfDouble1[i1] <= ((Double)localObject3).doubleValue()) && (((Double)localObject3).doubleValue() <= arrayOfDouble2[i1]))
        {
          f1 = (float)(j - arrayOfDouble3[i1] * (((Double)localObject3).doubleValue() - arrayOfDouble1[i1]));
          localObject3 = this.mRenderer.getYTextLabel((Double)localObject3, i1);
          paramPaint.setColor(this.mRenderer.getYLabelsColor(i1));
          paramPaint.setTextAlign(this.mRenderer.getYLabelsAlign(i1));
          if (localOrientation != XYMultipleSeriesRenderer.Orientation.HORIZONTAL)
            break label3160;
          if (localObject1 != Paint.Align.LEFT)
            break label3111;
          paramCanvas.drawLine(getLabelLinePos((Paint.Align)localObject1) + i3, f1, i3, f1, paramPaint);
          drawText(paramCanvas, (String)localObject3, i3, f1 - 2.0F, paramPaint, this.mRenderer.getYLabelsAngle());
          label3069: if (bool2)
          {
            paramPaint.setColor(this.mRenderer.getGridColor());
            paramCanvas.drawLine(i3, f1, k, f1, paramPaint);
          }
        }
        while (true)
        {
          i2 += 1;
          break;
          paramCanvas.drawLine(k, f1, getLabelLinePos((Paint.Align)localObject1) + k, f1, paramPaint);
          drawText(paramCanvas, (String)localObject3, k, f1 - 2.0F, paramPaint, this.mRenderer.getYLabelsAngle());
          break label3069;
          paramCanvas.drawLine(k - getLabelLinePos((Paint.Align)localObject1), f1, k, f1, paramPaint);
          drawText(paramCanvas, (String)localObject3, k + 10, f1 - 2.0F, paramPaint, this.mRenderer.getYLabelsAngle());
          if (bool2)
          {
            paramPaint.setColor(this.mRenderer.getGridColor());
            paramCanvas.drawLine(k, f1, i3, f1, paramPaint);
          }
        }
        if (this.mRenderer.getYAxisAlign(i) == Paint.Align.LEFT)
          drawText(paramCanvas, this.mRenderer.getYTitle(i), paramInt1 + f1, paramInt4 / 2 + paramInt2, paramPaint, -90.0F);
        while (true)
        {
          i += 1;
          break;
          drawText(paramCanvas, this.mRenderer.getYTitle(i), paramInt1 + paramInt3, paramInt4 / 2 + paramInt2, paramPaint, -90.0F);
        }
        if (localOrientation != XYMultipleSeriesRenderer.Orientation.VERTICAL)
          break label1183;
        drawText(paramCanvas, this.mRenderer.getXTitle(), paramInt3 / 2 + paramInt1, paramInt2 + paramInt4 - f1, paramPaint, -90.0F);
        drawText(paramCanvas, this.mRenderer.getYTitle(), k + 20, paramInt4 / 2 + paramInt2, paramPaint, 0.0F);
        paramPaint.setTextSize(this.mRenderer.getChartTitleTextSize());
        drawText(paramCanvas, this.mRenderer.getChartTitle(), paramInt1 + f1, paramInt4 / 2 + i5, paramPaint, 0.0F);
        break label1183;
        label3445: if (localOrientation != XYMultipleSeriesRenderer.Orientation.VERTICAL)
          break label1217;
        transform(paramCanvas, i6, true);
        drawLegend(paramCanvas, this.mRenderer, arrayOfString, i3, k, paramInt2, paramInt3, paramInt4, n, paramPaint, false);
        transform(paramCanvas, i6, false);
        break label1217;
        if (this.mRenderer.getYAxisAlign(paramInt2) == Paint.Align.RIGHT);
        for (paramInt1 = 1; ; paramInt1 = 0)
        {
          paramInt2 += 1;
          break;
        }
        if (localOrientation == XYMultipleSeriesRenderer.Orientation.VERTICAL)
          paramCanvas.drawLine(k, i5, k, j, paramPaint);
      }
    }
  }

  protected void drawChartValuesText(Canvas paramCanvas, XYSeries paramXYSeries, SimpleSeriesRenderer paramSimpleSeriesRenderer, Paint paramPaint, float[] paramArrayOfFloat, int paramInt1, int paramInt2)
  {
    if (paramArrayOfFloat.length > 1)
    {
      float f4 = paramArrayOfFloat[0];
      float f3 = paramArrayOfFloat[1];
      paramInt1 = 0;
      if (paramInt1 >= paramArrayOfFloat.length)
        return;
      float f1;
      float f2;
      if (paramInt1 == 2)
        if (Math.abs(paramArrayOfFloat[2] - paramArrayOfFloat[0]) <= 100.0F)
        {
          f1 = f4;
          f2 = f3;
          if (Math.abs(paramArrayOfFloat[3] - paramArrayOfFloat[1]) <= 100.0F);
        }
        else
        {
          if (!this.mRenderer.isDisplayValue0())
            break label186;
          drawText(paramCanvas, getLabel(paramXYSeries.getY(paramInt2)), paramArrayOfFloat[0], paramArrayOfFloat[1] - paramSimpleSeriesRenderer.getChartValuesSpacing(), paramPaint, 0.0F);
          drawText(paramCanvas, getLabel(paramXYSeries.getY(paramInt2 + 1)), paramArrayOfFloat[2], paramArrayOfFloat[3] - paramSimpleSeriesRenderer.getChartValuesSpacing(), paramPaint, 0.0F);
          label157: f1 = paramArrayOfFloat[2];
          f2 = paramArrayOfFloat[3];
        }
      label186: 
      do
      {
        do
        {
          paramInt1 += 2;
          f4 = f1;
          f3 = f2;
          break;
          if (paramArrayOfFloat[1] != 0.0F)
            drawText(paramCanvas, getLabel(paramXYSeries.getY(paramInt2)), paramArrayOfFloat[0], paramArrayOfFloat[1] - paramSimpleSeriesRenderer.getChartValuesSpacing(), paramPaint, 0.0F);
          if (paramArrayOfFloat[3] == 0.0F)
            break label157;
          drawText(paramCanvas, getLabel(paramXYSeries.getY(paramInt2 + 1)), paramArrayOfFloat[2], paramArrayOfFloat[3] - paramSimpleSeriesRenderer.getChartValuesSpacing(), paramPaint, 0.0F);
          break label157;
          f1 = f4;
          f2 = f3;
        }
        while (paramInt1 <= 2);
        if (Math.abs(paramArrayOfFloat[paramInt1] - f4) > 100.0F)
          break label331;
        f1 = f4;
        f2 = f3;
      }
      while (Math.abs(paramArrayOfFloat[(paramInt1 + 1)] - f3) <= 100.0F);
      label331: if (this.mRenderer.isDisplayValue0())
        drawText(paramCanvas, getLabel(paramXYSeries.getY(paramInt1 / 2 + paramInt2)), paramArrayOfFloat[paramInt1], paramArrayOfFloat[(paramInt1 + 1)] - paramSimpleSeriesRenderer.getChartValuesSpacing(), paramPaint, 0.0F);
      while (true)
      {
        f1 = paramArrayOfFloat[paramInt1];
        f2 = paramArrayOfFloat[(paramInt1 + 1)];
        break;
        if (paramArrayOfFloat[(paramInt1 + 1)] != 0.0F)
          drawText(paramCanvas, getLabel(paramXYSeries.getY(paramInt1 / 2 + paramInt2)), paramArrayOfFloat[paramInt1], paramArrayOfFloat[(paramInt1 + 1)] - paramSimpleSeriesRenderer.getChartValuesSpacing(), paramPaint, 0.0F);
      }
    }
    paramInt1 = 0;
    label458: if (paramInt1 < paramArrayOfFloat.length)
    {
      if (!this.mRenderer.isDisplayValue0())
        break label525;
      drawText(paramCanvas, getLabel(paramXYSeries.getY(paramInt1 / 2 + paramInt2)), paramArrayOfFloat[paramInt1], paramArrayOfFloat[(paramInt1 + 1)] - paramSimpleSeriesRenderer.getChartValuesSpacing(), paramPaint, 0.0F);
    }
    while (true)
    {
      paramInt1 += 2;
      break label458;
      break;
      label525: if (paramArrayOfFloat[(paramInt1 + 1)] != 0.0F)
        drawText(paramCanvas, getLabel(paramXYSeries.getY(paramInt1 / 2 + paramInt2)), paramArrayOfFloat[paramInt1], paramArrayOfFloat[(paramInt1 + 1)] - paramSimpleSeriesRenderer.getChartValuesSpacing(), paramPaint, 0.0F);
    }
  }

  public abstract void drawSeries(Canvas paramCanvas, Paint paramPaint, float[] paramArrayOfFloat, int[] paramArrayOfInt, SimpleSeriesRenderer paramSimpleSeriesRenderer, float paramFloat, int paramInt1, int paramInt2);

  protected void drawSeries(XYSeries paramXYSeries, Canvas paramCanvas, Paint paramPaint, List<Float> paramList, List<Integer> paramList1, SimpleSeriesRenderer paramSimpleSeriesRenderer, float paramFloat, int paramInt1, XYMultipleSeriesRenderer.Orientation paramOrientation, int paramInt2)
  {
    BasicStroke localBasicStroke = paramSimpleSeriesRenderer.getStroke();
    Paint.Cap localCap = paramPaint.getStrokeCap();
    Paint.Join localJoin = paramPaint.getStrokeJoin();
    float f = paramPaint.getStrokeMiter();
    PathEffect localPathEffect = paramPaint.getPathEffect();
    Paint.Style localStyle = paramPaint.getStyle();
    Object localObject;
    if (localBasicStroke != null)
    {
      localObject = null;
      if (localBasicStroke.getIntervals() != null)
        localObject = new DashPathEffect(localBasicStroke.getIntervals(), localBasicStroke.getPhase());
      setStroke(localBasicStroke.getCap(), localBasicStroke.getJoin(), localBasicStroke.getMiter(), Paint.Style.FILL_AND_STROKE, (PathEffect)localObject, paramPaint);
    }
    paramList = MathHelper.getFloats(paramList);
    paramList1 = MathHelper.getInts(paramList1);
    drawSeries(paramCanvas, paramPaint, paramList, paramList1, paramSimpleSeriesRenderer, paramFloat, paramInt1, paramInt2);
    if (isRenderPoints(paramSimpleSeriesRenderer))
    {
      localObject = getPointsChart();
      if (localObject != null)
        ((ScatterChart)localObject).drawSeries(paramCanvas, paramPaint, paramList, paramList1, paramSimpleSeriesRenderer, paramFloat, paramInt1, paramInt2);
    }
    paramPaint.setTextSize(paramSimpleSeriesRenderer.getChartValuesTextSize());
    if (paramOrientation == XYMultipleSeriesRenderer.Orientation.HORIZONTAL)
      paramPaint.setTextAlign(Paint.Align.CENTER);
    while (true)
    {
      if (paramSimpleSeriesRenderer.isDisplayChartValues())
      {
        paramPaint.setTextAlign(paramSimpleSeriesRenderer.getChartValuesTextAlign());
        drawChartValuesText(paramCanvas, paramXYSeries, paramSimpleSeriesRenderer, paramPaint, paramList, paramInt1, paramInt2);
      }
      if (localBasicStroke != null)
        setStroke(localCap, localJoin, f, localStyle, localPathEffect, paramPaint);
      return;
      paramPaint.setTextAlign(Paint.Align.LEFT);
    }
  }

  protected void drawText(Canvas paramCanvas, String paramString, float paramFloat1, float paramFloat2, Paint paramPaint, float paramFloat3)
  {
    paramFloat3 = -this.mRenderer.getOrientation().getAngle() + paramFloat3;
    if (paramFloat3 != 0.0F)
      paramCanvas.rotate(paramFloat3, paramFloat1, paramFloat2);
    drawString(paramCanvas, paramString, paramFloat1, paramFloat2, paramPaint);
    if (paramFloat3 != 0.0F)
      paramCanvas.rotate(-paramFloat3, paramFloat1, paramFloat2);
  }

  protected void drawXLabels(List<Double> paramList, Double[] paramArrayOfDouble, Canvas paramCanvas, Paint paramPaint, int paramInt1, int paramInt2, int paramInt3, double paramDouble1, double paramDouble2, double paramDouble3)
  {
    int j = paramList.size();
    boolean bool1 = this.mRenderer.isShowLabels();
    boolean bool2 = this.mRenderer.isShowGridY();
    int i = 0;
    while (true)
    {
      if (i >= j)
      {
        drawXTextLabels(paramArrayOfDouble, paramCanvas, paramPaint, bool1, paramInt1, paramInt2, paramInt3, paramDouble1, paramDouble2, paramDouble3);
        return;
      }
      double d = ((Double)paramList.get(i)).doubleValue();
      float f = (float)(paramInt1 + (d - paramDouble2) * paramDouble1);
      if (bool1)
      {
        paramPaint.setColor(this.mRenderer.getXLabelsColor());
        paramCanvas.drawLine(f, paramInt3, f, paramInt3 + this.mRenderer.getLabelsTextSize() / 3.0F, paramPaint);
        drawText(paramCanvas, getLabel(d), f, paramInt3 + this.mRenderer.getLabelsTextSize() * 4.0F / 3.0F, paramPaint, this.mRenderer.getXLabelsAngle());
      }
      if (bool2)
      {
        paramPaint.setColor(this.mRenderer.getGridColor());
        paramCanvas.drawLine(f, paramInt3, f, paramInt2, paramPaint);
      }
      i += 1;
    }
  }

  protected void drawXTextLabels(Double[] paramArrayOfDouble, Canvas paramCanvas, Paint paramPaint, boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, double paramDouble1, double paramDouble2, double paramDouble3)
  {
    boolean bool = this.mRenderer.isShowCustomTextGrid();
    int j;
    int i;
    if (paramBoolean)
    {
      paramPaint.setColor(this.mRenderer.getXLabelsColor());
      j = paramArrayOfDouble.length;
      i = 0;
    }
    while (true)
    {
      if (i >= j)
        return;
      Double localDouble = paramArrayOfDouble[i];
      if ((paramDouble2 <= localDouble.doubleValue()) && (localDouble.doubleValue() <= paramDouble3))
      {
        float f = (float)(paramInt1 + (localDouble.doubleValue() - paramDouble2) * paramDouble1);
        paramPaint.setColor(this.mRenderer.getXLabelsColor());
        paramCanvas.drawLine(f, paramInt3, f, paramInt3 + this.mRenderer.getLabelsTextSize() / 3.0F, paramPaint);
        drawText(paramCanvas, this.mRenderer.getXTextLabel(localDouble), f, paramInt3 + this.mRenderer.getLabelsTextSize() * 4.0F / 3.0F, paramPaint, this.mRenderer.getXLabelsAngle());
        if (bool)
        {
          paramPaint.setColor(this.mRenderer.getGridColor());
          paramCanvas.drawLine(f, paramInt3, f, paramInt2, paramPaint);
        }
      }
      i += 1;
    }
  }

  protected void drawYLabels(Map<Integer, List<Double>> paramMap, Canvas paramCanvas, Paint paramPaint, int paramInt1, int paramInt2, int paramInt3, int paramInt4, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2)
  {
    XYMultipleSeriesRenderer.Orientation localOrientation = this.mRenderer.getOrientation();
    boolean bool1 = this.mRenderer.isShowGridX();
    boolean bool2 = this.mRenderer.isShowLabels();
    int i = 0;
    List localList;
    int j;
    while (true)
    {
      if (i >= paramInt1)
        return;
      paramPaint.setTextAlign(this.mRenderer.getYLabelsAlign(i));
      localList = (List)paramMap.get(Integer.valueOf(i));
      int m = localList.size();
      j = 0;
      if (j < m)
        break;
      i += 1;
    }
    double d = ((Double)localList.get(j)).doubleValue();
    Paint.Align localAlign = this.mRenderer.getYAxisAlign(i);
    int k;
    label143: float f;
    if (this.mRenderer.getYTextLabel(Double.valueOf(d), i) != null)
    {
      k = 1;
      f = (float)(paramInt4 - paramArrayOfDouble1[i] * (d - paramArrayOfDouble2[i]));
      if (localOrientation != XYMultipleSeriesRenderer.Orientation.HORIZONTAL)
        break label348;
      if ((bool2) && (k == 0))
      {
        paramPaint.setColor(this.mRenderer.getYLabelsColor(i));
        if (localAlign != Paint.Align.LEFT)
          break label297;
        paramCanvas.drawLine(getLabelLinePos(localAlign) + paramInt2, f, paramInt2, f, paramPaint);
        drawText(paramCanvas, getLabel(d), paramInt2, f - 2.0F, paramPaint, this.mRenderer.getYLabelsAngle());
      }
      label251: if (bool1)
      {
        paramPaint.setColor(this.mRenderer.getGridColor());
        paramCanvas.drawLine(paramInt2, f, paramInt3, f, paramPaint);
      }
    }
    while (true)
    {
      j += 1;
      break;
      k = 0;
      break label143;
      label297: paramCanvas.drawLine(paramInt3, f, getLabelLinePos(localAlign) + paramInt3, f, paramPaint);
      drawText(paramCanvas, getLabel(d), paramInt3, f - 2.0F, paramPaint, this.mRenderer.getYLabelsAngle());
      break label251;
      label348: if (localOrientation == XYMultipleSeriesRenderer.Orientation.VERTICAL)
      {
        if ((bool2) && (k == 0))
        {
          paramPaint.setColor(this.mRenderer.getYLabelsColor(i));
          paramCanvas.drawLine(paramInt3 - getLabelLinePos(localAlign), f, paramInt3, f, paramPaint);
          drawText(paramCanvas, getLabel(d), paramInt3 + 10, f - 2.0F, paramPaint, this.mRenderer.getYLabelsAngle());
        }
        if (bool1)
        {
          paramPaint.setColor(this.mRenderer.getGridColor());
          paramCanvas.drawLine(paramInt3, f, paramInt2, f, paramPaint);
        }
      }
    }
  }

  public double[] getCalcRange(int paramInt)
  {
    return (double[])this.mCalcRange.get(Integer.valueOf(paramInt));
  }

  public abstract String getChartType();

  public XYMultipleSeriesDataset getDataset()
  {
    return this.mDataset;
  }

  public double getDefaultMinimum()
  {
    return 1.7976931348623157E+308D;
  }

  public Map<Integer, List<String>> getExplains()
  {
    return this.explains;
  }

  public Map<Integer, List<Float>> getPoints()
  {
    return this.points;
  }

  public ScatterChart getPointsChart()
  {
    return null;
  }

  public XYMultipleSeriesRenderer getRenderer()
  {
    return this.mRenderer;
  }

  protected Rect getScreenR()
  {
    return this.mScreenR;
  }

  public SeriesSelection getSeriesAndPointForScreenCoordinate(Point paramPoint)
  {
    int i;
    if (this.clickableAreas != null)
    {
      i = this.clickableAreas.size() - 1;
      if (i >= 0);
    }
    else
    {
      return super.getSeriesAndPointForScreenCoordinate(paramPoint);
    }
    int j = 0;
    Iterator localIterator;
    if (this.clickableAreas.get(Integer.valueOf(i)) != null)
      localIterator = ((List)this.clickableAreas.get(Integer.valueOf(i))).iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        i -= 1;
        break;
      }
      ClickableArea localClickableArea = (ClickableArea)localIterator.next();
      RectF localRectF = localClickableArea.getRect();
      if ((localRectF != null) && (localRectF.contains(paramPoint.getX(), paramPoint.getY())))
        return new SeriesSelection(i, j, localClickableArea.getX(), localClickableArea.getY());
      j += 1;
    }
  }

  public Map<Integer, List<Double>> getValues()
  {
    return this.values;
  }

  protected List<Double> getXLabels(double paramDouble1, double paramDouble2, int paramInt)
  {
    return MathHelper.getLabels(paramDouble1, paramDouble2, paramInt);
  }

  protected Map<Integer, List<Double>> getYLabels(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, int paramInt)
  {
    HashMap localHashMap = new HashMap();
    int i = 0;
    while (true)
    {
      if (i >= paramInt)
        return localHashMap;
      localHashMap.put(Integer.valueOf(i), getValidLabels(MathHelper.getLabels(paramArrayOfDouble1[i], paramArrayOfDouble2[i], this.mRenderer.getYLabels())));
      i += 1;
    }
  }

  protected boolean isRenderNullValues()
  {
    return false;
  }

  public boolean isRenderPoints(SimpleSeriesRenderer paramSimpleSeriesRenderer)
  {
    return false;
  }

  public void setCalcRange(double[] paramArrayOfDouble, int paramInt)
  {
    this.mCalcRange.put(Integer.valueOf(paramInt), paramArrayOfDouble);
  }

  protected void setDatasetRenderer(XYMultipleSeriesDataset paramXYMultipleSeriesDataset, XYMultipleSeriesRenderer paramXYMultipleSeriesRenderer)
  {
    this.mDataset = paramXYMultipleSeriesDataset;
    this.mRenderer = paramXYMultipleSeriesRenderer;
  }

  protected void setScreenR(Rect paramRect)
  {
    this.mScreenR = paramRect;
  }

  public double[] toRealPoint(float paramFloat1, float paramFloat2)
  {
    return toRealPoint(paramFloat1, paramFloat2, 0);
  }

  public double[] toRealPoint(float paramFloat1, float paramFloat2, int paramInt)
  {
    double d1 = this.mRenderer.getXAxisMin(paramInt);
    double d2 = this.mRenderer.getXAxisMax(paramInt);
    double d3 = this.mRenderer.getYAxisMin(paramInt);
    double d4 = this.mRenderer.getYAxisMax(paramInt);
    return new double[] { (paramFloat1 - this.mScreenR.left) * (d2 - d1) / this.mScreenR.width() + d1, (this.mScreenR.top + this.mScreenR.height() - paramFloat2) * (d4 - d3) / this.mScreenR.height() + d3 };
  }

  public double[] toScreenPoint(double[] paramArrayOfDouble)
  {
    return toScreenPoint(paramArrayOfDouble, 0);
  }

  public double[] toScreenPoint(double[] paramArrayOfDouble, int paramInt)
  {
    double d3 = this.mRenderer.getXAxisMin(paramInt);
    double d1 = this.mRenderer.getXAxisMax(paramInt);
    double d4 = this.mRenderer.getYAxisMin(paramInt);
    double d2 = this.mRenderer.getYAxisMax(paramInt);
    if ((!this.mRenderer.isMinXSet(paramInt)) || (!this.mRenderer.isMaxXSet(paramInt)) || (!this.mRenderer.isMinXSet(paramInt)) || (!this.mRenderer.isMaxYSet(paramInt)))
    {
      double[] arrayOfDouble = getCalcRange(paramInt);
      d3 = arrayOfDouble[0];
      d1 = arrayOfDouble[1];
      d4 = arrayOfDouble[2];
      d2 = arrayOfDouble[3];
    }
    return new double[] { (paramArrayOfDouble[0] - d3) * this.mScreenR.width() / (d1 - d3) + this.mScreenR.left, (d2 - paramArrayOfDouble[1]) * this.mScreenR.height() / (d2 - d4) + this.mScreenR.top };
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.chart.XYChart
 * JD-Core Version:    0.6.2
 */