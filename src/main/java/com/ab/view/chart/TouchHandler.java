package com.ab.view.chart;

import android.graphics.RectF;
import android.view.MotionEvent;

public class TouchHandler
  implements ITouchHandler
{
  private GraphicalView graphicalView;
  private Pan mPan;
  private Zoom mPinchZoom;
  private DefaultRenderer mRenderer;
  private float oldX;
  private float oldX2;
  private float oldY;
  private float oldY2;
  private RectF zoomR = new RectF();

  public TouchHandler(GraphicalView paramGraphicalView, AbstractChart paramAbstractChart)
  {
    this.graphicalView = paramGraphicalView;
    this.zoomR = this.graphicalView.getZoomRectangle();
    if ((paramAbstractChart instanceof XYChart));
    for (this.mRenderer = ((XYChart)paramAbstractChart).getRenderer(); ; this.mRenderer = ((RoundChart)paramAbstractChart).getRenderer())
    {
      if (this.mRenderer.isPanEnabled())
        this.mPan = new Pan(paramAbstractChart);
      if (this.mRenderer.isZoomEnabled())
        this.mPinchZoom = new Zoom(paramAbstractChart, true, 1.0F);
      return;
    }
  }

  public void addPanListener(PanListener paramPanListener)
  {
    if (this.mPan != null)
      this.mPan.addPanListener(paramPanListener);
  }

  public void addZoomListener(ZoomListener paramZoomListener)
  {
    if (this.mPinchZoom != null)
      this.mPinchZoom.addZoomListener(paramZoomListener);
  }

  public float getOldX()
  {
    return this.oldX;
  }

  public float getOldY()
  {
    return this.oldY;
  }

  public boolean handleTouch(MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getAction();
    if ((this.mRenderer != null) && (i == 2))
    {
      if ((this.oldX >= 0.0F) || (this.oldY >= 0.0F))
      {
        float f2 = paramMotionEvent.getX(0);
        float f3 = paramMotionEvent.getY(0);
        float f1;
        float f6;
        float f7;
        float f8;
        float f9;
        float f10;
        if ((paramMotionEvent.getPointerCount() > 1) && ((this.oldX2 >= 0.0F) || (this.oldY2 >= 0.0F)) && (this.mRenderer.isZoomEnabled()))
        {
          float f4 = paramMotionEvent.getX(1);
          float f5 = paramMotionEvent.getY(1);
          f1 = Math.abs(f2 - f4);
          f6 = Math.abs(f3 - f5);
          f7 = Math.abs(this.oldX - this.oldX2);
          f8 = Math.abs(this.oldY - this.oldY2);
          f9 = Math.abs(f3 - this.oldY) / Math.abs(f2 - this.oldX);
          f10 = Math.abs(f5 - this.oldY2) / Math.abs(f4 - this.oldX2);
          if ((f9 <= 0.577D) && (f10 <= 0.577D))
          {
            f1 /= f7;
            if ((f1 > 0.909D) && (f1 < 1.1D))
            {
              this.mPinchZoom.setZoomRate(f1);
              this.mPinchZoom.apply(1);
            }
            this.oldX2 = f4;
            this.oldY2 = f5;
          }
        }
        while (true)
        {
          this.oldX = f2;
          this.oldY = f3;
          this.graphicalView.repaint();
          return true;
          if ((f9 >= 1.732D) && (f10 >= 1.732D))
          {
            f1 = f6 / f8;
            if ((f1 <= 0.909D) || (f1 >= 1.1D))
              break;
            this.mPinchZoom.setZoomRate(f1);
            this.mPinchZoom.apply(2);
            break;
          }
          if ((f9 <= 0.577D) || (f9 >= 1.732D) || (f10 <= 0.577D) || (f10 >= 1.732D))
            break;
          if (Math.abs(f2 - this.oldX) >= Math.abs(f3 - this.oldY));
          for (f1 /= f7; ; f1 = f6 / f8)
          {
            if ((f1 <= 0.909D) || (f1 >= 1.1D))
              break label457;
            this.mPinchZoom.setZoomRate(f1);
            this.mPinchZoom.apply(0);
            break;
          }
          label457: break;
          if (this.mRenderer.isPanEnabled())
          {
            this.mPan.apply(this.oldX, this.oldY, f2, f3);
            this.oldX2 = 0.0F;
            this.oldY2 = 0.0F;
          }
        }
      }
    }
    else
    {
      if (i != 0)
        break label535;
      this.oldX = paramMotionEvent.getX(0);
      this.oldY = paramMotionEvent.getY(0);
    }
    while (this.mRenderer.isClickEnabled())
    {
      return false;
      label535: if ((i == 1) || (i == 6))
      {
        this.oldX2 = 0.0F;
        this.oldY2 = 0.0F;
        if (i == 6)
        {
          this.oldX = -1.0F;
          this.oldY = -1.0F;
        }
      }
    }
    return true;
  }

  public boolean handleTouchControl(MotionEvent paramMotionEvent)
  {
    boolean bool = false;
    int i = paramMotionEvent.getAction();
    if ((this.mRenderer != null) && (i == 2))
    {
      if (((this.oldX >= 0.0F) || (this.oldY >= 0.0F)) && ((this.oldX >= 0.0F) || (this.oldY >= 0.0F)))
      {
        float f1 = paramMotionEvent.getX();
        float f2 = paramMotionEvent.getY();
        this.oldX = f1;
        this.oldY = f2;
        this.graphicalView.repaint();
        return true;
      }
    }
    else if (i == 0)
    {
      this.oldX = paramMotionEvent.getX(0);
      this.oldY = paramMotionEvent.getY(0);
      if ((this.mRenderer != null) && (this.mRenderer.isZoomEnabled()) && (this.zoomR.contains(this.oldX, this.oldY)))
      {
        if (this.oldX < this.zoomR.left + this.zoomR.width() / 3.0F)
        {
          this.graphicalView.zoomIn();
          return true;
        }
        if (this.oldX < this.zoomR.left + this.zoomR.width() * 2.0F / 3.0F)
        {
          this.graphicalView.zoomOut();
          return true;
        }
        this.graphicalView.zoomReset();
        return true;
      }
    }
    else if ((i == 1) || (i == 6))
    {
      this.oldX2 = 0.0F;
      this.oldY2 = 0.0F;
      if (i == 6)
      {
        this.oldX = -1.0F;
        this.oldY = -1.0F;
      }
    }
    if (this.mRenderer.isClickEnabled());
    while (true)
    {
      return bool;
      bool = true;
    }
  }

  public void removePanListener(PanListener paramPanListener)
  {
    if (this.mPan != null)
      this.mPan.removePanListener(paramPanListener);
  }

  public void removeZoomListener(ZoomListener paramZoomListener)
  {
    if (this.mPinchZoom != null)
      this.mPinchZoom.removeZoomListener(paramZoomListener);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.chart.TouchHandler
 * JD-Core Version:    0.6.2
 */