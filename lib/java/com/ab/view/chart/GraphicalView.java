package com.ab.view.chart;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Handler;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import com.ab.util.AbFileUtil;
import com.ab.util.AbGraphical;
import com.ab.util.AbViewUtil;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class GraphicalView extends View
{
  private static final int ZOOM_BUTTONS_COLOR = Color.argb(175, 150, 150, 150);
  private Bitmap fitZoomImage;
  private AbstractChart mChart;
  private Context mContext;
  private FitZoom mFitZoom;
  private Handler mHandler;
  private Paint mPaint = new Paint();
  private Rect mRect = new Rect();
  private DefaultRenderer mRenderer;
  private ITouchHandler mTouchHandler;
  private Zoom mZoomIn;
  private Zoom mZoomOut;
  private RectF mZoomR = new RectF();
  private float oldX;
  private float oldY;
  private int screenHeight = 0;
  private int screenWidth = 0;
  private Bitmap zoomInImage;
  private Bitmap zoomOutImage;
  private int zoomSize = 50;

  public GraphicalView(Context paramContext, AbstractChart paramAbstractChart)
  {
    super(paramContext);
    this.mContext = paramContext;
    this.mChart = paramAbstractChart;
    this.mHandler = new Handler();
    int i;
    int j;
    if ((this.mChart instanceof XYChart))
    {
      this.mRenderer = ((XYChart)this.mChart).getRenderer();
      if (this.mRenderer.isZoomButtonsVisible())
      {
        this.zoomInImage = AbFileUtil.getBitmapFormSrc("image/zoom_in.png");
        this.zoomOutImage = AbFileUtil.getBitmapFormSrc("image/zoom_out.png");
        this.fitZoomImage = AbFileUtil.getBitmapFormSrc("image/zoom-1.png");
      }
      if (((this.mRenderer instanceof XYMultipleSeriesRenderer)) && (((XYMultipleSeriesRenderer)this.mRenderer).getMarginsColor() == 0))
        ((XYMultipleSeriesRenderer)this.mRenderer).setMarginsColor(this.mPaint.getColor());
      if (((this.mRenderer.isZoomEnabled()) && (this.mRenderer.isZoomButtonsVisible())) || (this.mRenderer.isExternalZoomEnabled()))
      {
        this.mZoomIn = new Zoom(this.mChart, true, this.mRenderer.getZoomRate());
        this.mZoomOut = new Zoom(this.mChart, false, this.mRenderer.getZoomRate());
        this.mFitZoom = new FitZoom(this.mChart);
      }
      this.mTouchHandler = new TouchHandler(this, this.mChart);
      paramAbstractChart = new DisplayMetrics();
      ((Activity)paramContext).getWindowManager().getDefaultDisplay().getMetrics(paramAbstractChart);
      this.screenWidth = paramAbstractChart.widthPixels;
      this.screenHeight = paramAbstractChart.heightPixels;
      if ((this.mChart instanceof XYChart))
      {
        paramContext = ((XYChart)this.mChart).getRenderer();
        i = paramContext.getExplainTextSize1();
        j = paramContext.getExplainTextSize2();
        int k = paramContext.getScaleCircleRadius();
        int m = paramContext.getScaleRectWidth();
        int n = paramContext.getScaleRectHeight();
        paramContext.setExplainTextSize1(AbViewUtil.resizeTextSize(this.screenWidth, this.screenHeight, i));
        paramContext.setExplainTextSize2(AbViewUtil.resizeTextSize(this.screenWidth, this.screenHeight, j));
        paramContext.setScaleCircleRadius(AbViewUtil.resizeTextSize(this.screenWidth, this.screenHeight, k));
        paramContext.setScaleRectWidth(AbViewUtil.resizeTextSize(this.screenWidth, this.screenHeight, m));
        paramContext.setScaleRectHeight(AbViewUtil.resizeTextSize(this.screenWidth, this.screenHeight, n));
        paramContext = this.mRenderer.getSeriesRenderers();
        if ((paramContext != null) && (paramContext.length > 0))
          i = 0;
      }
    }
    while (true)
    {
      if (i >= paramContext.length)
      {
        i = (int)this.mRenderer.getChartTitleTextSize();
        this.mRenderer.setChartTitleTextSize(AbViewUtil.resizeTextSize(this.screenWidth, this.screenHeight, i));
        i = (int)this.mRenderer.getLabelsTextSize();
        this.mRenderer.setLabelsTextSize(AbViewUtil.resizeTextSize(this.screenWidth, this.screenHeight, i));
        i = (int)this.mRenderer.getLegendTextSize();
        this.mRenderer.setLabelsTextSize(AbViewUtil.resizeTextSize(this.screenWidth, this.screenHeight, i));
        return;
        this.mRenderer = ((RoundChart)this.mChart).getRenderer();
        break;
      }
      paramAbstractChart = paramContext[i];
      j = (int)paramAbstractChart.getChartValuesTextSize();
      paramAbstractChart.setChartValuesTextSize(AbViewUtil.resizeTextSize(this.screenWidth, this.screenHeight, j));
      i += 1;
    }
  }

  public void addPanListener(PanListener paramPanListener)
  {
    this.mTouchHandler.addPanListener(paramPanListener);
  }

  public void addZoomListener(ZoomListener paramZoomListener, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramBoolean1)
    {
      if (this.mZoomIn != null)
      {
        this.mZoomIn.addZoomListener(paramZoomListener);
        this.mZoomOut.addZoomListener(paramZoomListener);
      }
      if (paramBoolean2)
        this.mTouchHandler.addZoomListener(paramZoomListener);
    }
  }

  public SeriesSelection getCurrentSeriesAndPoint()
  {
    return this.mChart.getSeriesAndPointForScreenCoordinate(new Point(this.oldX, this.oldY));
  }

  protected RectF getZoomRectangle()
  {
    return this.mZoomR;
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    paramCanvas.getClipBounds(this.mRect);
    int m = this.mRect.top;
    int j = this.mRect.left;
    int k = this.mRect.width();
    int i = this.mRect.height();
    if (this.mRenderer.isInScroll())
    {
      m = 0;
      j = 0;
      k = getMeasuredWidth();
      i = getMeasuredHeight();
    }
    this.mChart.draw(paramCanvas, j, m, k, i, this.mPaint);
    float f1;
    if ((this.mRenderer != null) && (this.mRenderer.isZoomEnabled()) && (this.mRenderer.isZoomButtonsVisible()))
    {
      this.mPaint.setColor(ZOOM_BUTTONS_COLOR);
      this.zoomSize = Math.max(this.zoomSize, Math.min(k, i) / 7);
      this.mZoomR.set(j + k - this.zoomSize * 3, m + i - this.zoomSize * 0.775F, j + k, m + i);
      paramCanvas.drawRoundRect(this.mZoomR, this.zoomSize / 3, this.zoomSize / 3, this.mPaint);
      f1 = m + i - this.zoomSize * 0.625F;
      paramCanvas.drawBitmap(this.zoomInImage, j + k - this.zoomSize * 2.75F, f1, null);
      paramCanvas.drawBitmap(this.zoomOutImage, j + k - this.zoomSize * 1.75F, f1, null);
      paramCanvas.drawBitmap(this.fitZoomImage, j + k - this.zoomSize * 0.75F, f1, null);
    }
    XYMultipleSeriesRenderer localXYMultipleSeriesRenderer;
    int i2;
    int i3;
    int i1;
    int i4;
    int i5;
    int n;
    Object localObject1;
    Object localObject3;
    float f2;
    Object localObject2;
    Object localObject4;
    Object localObject6;
    Map localMap;
    Iterator localIterator;
    if ((this.mChart instanceof XYChart))
    {
      localXYMultipleSeriesRenderer = ((XYChart)this.mChart).getRenderer();
      if (localXYMultipleSeriesRenderer.isScaleLineEnabled())
      {
        this.oldX = ((TouchHandler)this.mTouchHandler).getOldX();
        i2 = localXYMultipleSeriesRenderer.getExplainTextSize1();
        i3 = localXYMultipleSeriesRenderer.getExplainTextSize2();
        i1 = localXYMultipleSeriesRenderer.getScaleCircleRadius();
        i4 = AbViewUtil.resizeTextSize(k, i, 50);
        i5 = AbViewUtil.resizeTextSize(k, i, 20);
        n = 0;
        m = 0;
        if ((this.mChart instanceof XYChart))
        {
          localObject1 = ((XYChart)this.mChart).getScreenR();
          i = ((Rect)localObject1).bottom;
          j = ((Rect)localObject1).top;
          n = i;
          m = j;
          if (this.oldX == 0.0F)
          {
            this.oldX = ((Rect)localObject1).right;
            m = j;
            n = i;
          }
        }
        this.mPaint.setColor(localXYMultipleSeriesRenderer.getScaleLineColor());
        paramCanvas.drawLine(this.oldX, i4, this.oldX, n + i5, this.mPaint);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setColor(ZOOM_BUTTONS_COLOR);
        paramCanvas.drawCircle(this.oldX, n + i5 + i1, i1, this.mPaint);
        if ((this.mChart instanceof XYChart))
        {
          localObject3 = null;
          f2 = -1.0F;
          i1 = -1;
          localObject2 = "";
          localObject1 = "";
          localObject4 = ((XYChart)this.mChart).getPoints();
          localObject6 = ((XYChart)this.mChart).getValues();
          localMap = ((XYChart)this.mChart).getExplains();
          localIterator = ((Map)localObject4).entrySet().iterator();
          if (localIterator.hasNext())
            break label1231;
          if (f2 < 5.0F)
          {
            if (!localXYMultipleSeriesRenderer.isDisplayValue0())
              break label1752;
            i = 1;
          }
        }
      }
    }
    while (true)
    {
      if (i != 0)
      {
        localObject4 = new TextPaint(1);
        ((TextPaint)localObject4).setColor(-1);
        ((TextPaint)localObject4).setTypeface(Typeface.DEFAULT);
        ((TextPaint)localObject4).setTextSize(i2);
        localObject5 = new TextPaint(1);
        ((TextPaint)localObject5).setColor(-1);
        ((TextPaint)localObject5).setTypeface(Typeface.DEFAULT);
        ((TextPaint)localObject5).setTextSize(i3);
        localObject6 = ((TextPaint)localObject4).getFontMetrics();
        int i6 = (int)Math.ceil(((Paint.FontMetrics)localObject6).descent - ((Paint.FontMetrics)localObject6).ascent) + 2;
        localObject6 = ((TextPaint)localObject5).getFontMetrics();
        i = (int)Math.ceil(((Paint.FontMetrics)localObject6).descent - ((Paint.FontMetrics)localObject6).ascent);
        int i7 = AbGraphical.getDrawRowCount((String)localObject2, localXYMultipleSeriesRenderer.getScaleRectWidth() - 10, (TextPaint)localObject4);
        i4 = i7 * i6 + AbGraphical.getDrawRowCount((String)localObject1, localXYMultipleSeriesRenderer.getScaleRectWidth() - 10, (TextPaint)localObject5) * (i + 2) + 10;
        i2 = localXYMultipleSeriesRenderer.getScaleRectHeight();
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setColor(localXYMultipleSeriesRenderer.getScaleRectColor());
        this.mPaint.setAntiAlias(true);
        i3 = (int)((Float)((List)localObject3).get(i1)).floatValue() + 5;
        j = (int)((Float)((List)localObject3).get(i1 + 1)).floatValue() + 5;
        i5 = i3 + localXYMultipleSeriesRenderer.getScaleRectWidth();
        i = j + localXYMultipleSeriesRenderer.getScaleRectHeight();
        if (i4 > localXYMultipleSeriesRenderer.getScaleRectHeight())
        {
          i2 = i4;
          i = j + i2;
        }
        i4 = i5;
        if (i5 > k)
        {
          i3 = (int)((Float)((List)localObject3).get(i1)).floatValue() - 5 - localXYMultipleSeriesRenderer.getScaleRectWidth();
          i4 = (int)((Float)((List)localObject3).get(i1)).floatValue() - 5;
        }
        k = i;
        if (i > n)
        {
          i = (int)((Float)((List)localObject3).get(i1 + 1)).floatValue() - 5 - i2;
          n = (int)((Float)((List)localObject3).get(i1 + 1)).floatValue() - 5;
          k = n;
          j = i;
          if (i < m)
          {
            j = i + i2 / 2;
            k = n + i2 / 2;
          }
        }
        paramCanvas.drawRoundRect(new RectF(i3, j, i4, k), 5.0F, 5.0F, this.mPaint);
        i = AbViewUtil.resizeTextSize(this.screenWidth, this.screenHeight, 15);
        AbGraphical.drawText(paramCanvas, (String)localObject2, localXYMultipleSeriesRenderer.getScaleRectWidth() - 10, (TextPaint)localObject4, i3 + 5, j + i);
        AbGraphical.drawText(paramCanvas, (String)localObject1, localXYMultipleSeriesRenderer.getScaleRectWidth() - 10, (TextPaint)localObject5, i3 + 5, j + i + i7 * i6);
      }
      return;
      label1231: Object localObject5 = (Map.Entry)localIterator.next();
      i1 = ((Integer)((Map.Entry)localObject5).getKey()).intValue();
      localObject4 = (List)((Map.Entry)localObject5).getValue();
      localObject3 = (List)((Map)localObject6).get(((Map.Entry)localObject5).getKey());
      List localList = (List)localMap.get(((Map.Entry)localObject5).getKey());
      f1 = 1000.0F;
      j = -1;
      i = 0;
      while (true)
      {
        if (i >= ((List)localObject4).size())
        {
          if (i1 != 0)
            break label1525;
          localObject5 = String.valueOf(((Double)((List)localObject3).get(j + 1)).doubleValue()).replace(".0", "");
          str = (String)localList.get(j / 2);
          localObject2 = localObject5;
          localObject3 = localObject4;
          i1 = j;
          f2 = f1;
          if (str == null)
            break;
          localObject2 = localObject5;
          localObject3 = localObject4;
          i1 = j;
          f2 = f1;
          if ("".equals(str.trim()))
            break;
          localObject1 = (String)localList.get(j / 2);
          localObject2 = localObject5;
          localObject3 = localObject4;
          i1 = j;
          f2 = f1;
          break;
        }
        localObject5 = (Float)((List)localObject4).get(i);
        f2 = f1;
        if (Math.abs(((Float)localObject5).floatValue() - this.oldX) < f1)
        {
          f2 = Math.abs(((Float)localObject5).floatValue() - this.oldX);
          j = i;
        }
        i += 2;
        f1 = f2;
      }
      label1525: localObject5 = localObject2 + "/" + String.valueOf(((Double)((List)localObject3).get(j + 1)).doubleValue()).replace(".0", "");
      String str = (String)localList.get(j / 2);
      localObject2 = localObject5;
      localObject3 = localObject4;
      i1 = j;
      f2 = f1;
      if (str == null)
        break;
      localObject2 = localObject5;
      localObject3 = localObject4;
      i1 = j;
      f2 = f1;
      if ("".equals(str.trim()))
        break;
      if ((localObject1 != null) && (!"".equals(((String)localObject1).trim())))
      {
        localObject1 = localObject1 + "/" + (String)localList.get(j / 2);
        localObject2 = localObject5;
        localObject3 = localObject4;
        i1 = j;
        f2 = f1;
        break;
      }
      localObject1 = (String)localList.get(j / 2);
      localObject2 = localObject5;
      localObject3 = localObject4;
      i1 = j;
      f2 = f1;
      break;
      label1752: if (("0".equals(localObject2)) || ("0/0".equals(localObject2)))
        i = 0;
      else
        i = 1;
    }
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.mRenderer != null)
    {
      paramMotionEvent.getX();
      float f = paramMotionEvent.getY();
      int i = 0;
      if ((this.mChart instanceof XYChart))
        i = ((XYChart)this.mChart).getScreenR().bottom;
      if (f >= i - 10)
      {
        if (this.mTouchHandler.handleTouchControl(paramMotionEvent))
          return true;
      }
      else if (((this.mRenderer.isPanEnabled()) || (this.mRenderer.isZoomEnabled())) && (this.mTouchHandler.handleTouch(paramMotionEvent)))
        return true;
    }
    return super.onTouchEvent(paramMotionEvent);
  }

  public void removePanListener(PanListener paramPanListener)
  {
    this.mTouchHandler.removePanListener(paramPanListener);
  }

  public void removeZoomListener(ZoomListener paramZoomListener)
  {
    try
    {
      if (this.mZoomIn != null)
      {
        this.mZoomIn.removeZoomListener(paramZoomListener);
        this.mZoomOut.removeZoomListener(paramZoomListener);
      }
      this.mTouchHandler.removeZoomListener(paramZoomListener);
      return;
    }
    finally
    {
    }
    throw paramZoomListener;
  }

  public void repaint()
  {
    this.mHandler.post(new Runnable()
    {
      public void run()
      {
        GraphicalView.this.invalidate();
      }
    });
  }

  public void repaint(final int paramInt1, final int paramInt2, final int paramInt3, final int paramInt4)
  {
    this.mHandler.post(new Runnable()
    {
      public void run()
      {
        GraphicalView.this.invalidate(paramInt1, paramInt2, paramInt3, paramInt4);
      }
    });
  }

  public void setZoomRate(float paramFloat)
  {
    if ((this.mZoomIn != null) && (this.mZoomOut != null))
    {
      this.mZoomIn.setZoomRate(paramFloat);
      this.mZoomOut.setZoomRate(paramFloat);
    }
  }

  public Bitmap toBitmap()
  {
    setDrawingCacheEnabled(false);
    if (!isDrawingCacheEnabled())
      setDrawingCacheEnabled(true);
    if (this.mRenderer.isApplyBackgroundColor())
      setDrawingCacheBackgroundColor(this.mRenderer.getBackgroundColor());
    setDrawingCacheQuality(1048576);
    return getDrawingCache(true);
  }

  public double[] toRealPoint(int paramInt)
  {
    if ((this.mChart instanceof XYChart))
      return ((XYChart)this.mChart).toRealPoint(this.oldX, this.oldY, paramInt);
    return null;
  }

  public void zoomIn()
  {
    if (this.mZoomIn != null)
    {
      this.mZoomIn.apply(0);
      repaint();
    }
  }

  public void zoomOut()
  {
    if (this.mZoomOut != null)
    {
      this.mZoomOut.apply(0);
      repaint();
    }
  }

  public void zoomReset()
  {
    if (this.mFitZoom != null)
    {
      this.mFitZoom.apply();
      this.mZoomIn.notifyZoomResetListeners();
      repaint();
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.chart.GraphicalView
 * JD-Core Version:    0.6.2
 */