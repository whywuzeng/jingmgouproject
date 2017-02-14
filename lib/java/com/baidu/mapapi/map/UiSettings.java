package com.baidu.mapapi.map;

import android.graphics.Point;
import com.baidu.platform.comapi.map.c;

public final class UiSettings
{
  private c a;

  UiSettings(c paramc)
  {
    this.a = paramc;
  }

  public boolean isCompassEnabled()
  {
    return this.a.m();
  }

  public boolean isOverlookingGesturesEnabled()
  {
    return this.a.s();
  }

  public boolean isRotateGesturesEnabled()
  {
    return this.a.r();
  }

  public boolean isScrollGesturesEnabled()
  {
    return this.a.p();
  }

  public boolean isZoomGesturesEnabled()
  {
    return this.a.q();
  }

  public void setAllGesturesEnabled(boolean paramBoolean)
  {
    setRotateGesturesEnabled(paramBoolean);
    setScrollGesturesEnabled(paramBoolean);
    setOverlookingGesturesEnabled(paramBoolean);
    setZoomGesturesEnabled(paramBoolean);
  }

  public void setCompassEnabled(boolean paramBoolean)
  {
    this.a.g(paramBoolean);
  }

  public void setCompassPosition(Point paramPoint)
  {
    this.a.a(paramPoint);
  }

  public void setOverlookingGesturesEnabled(boolean paramBoolean)
  {
    this.a.m(paramBoolean);
  }

  public void setRotateGesturesEnabled(boolean paramBoolean)
  {
    this.a.l(paramBoolean);
  }

  public void setScrollGesturesEnabled(boolean paramBoolean)
  {
    this.a.j(paramBoolean);
  }

  public void setZoomGesturesEnabled(boolean paramBoolean)
  {
    this.a.k(paramBoolean);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.UiSettings
 * JD-Core Version:    0.6.2
 */