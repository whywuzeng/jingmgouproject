package com.baidu.mapapi.map;

import android.os.Bundle;
import com.baidu.mapapi.model.LatLng;

public final class ArcOptions extends OverlayOptions
{
  private static final String d = ArcOptions.class.getSimpleName();
  int a;
  boolean b = true;
  Bundle c;
  private int e = -16777216;
  private int f = 5;
  private LatLng g;
  private LatLng h;
  private LatLng i;

  Overlay a()
  {
    Arc localArc = new Arc();
    localArc.s = this.b;
    localArc.r = this.a;
    localArc.t = this.c;
    localArc.a = this.e;
    localArc.b = this.f;
    localArc.c = this.g;
    localArc.d = this.h;
    localArc.e = this.i;
    return localArc;
  }

  public ArcOptions color(int paramInt)
  {
    this.e = paramInt;
    return this;
  }

  public ArcOptions extraInfo(Bundle paramBundle)
  {
    this.c = paramBundle;
    return this;
  }

  public int getColor()
  {
    return this.e;
  }

  public LatLng getEndPoint()
  {
    return this.i;
  }

  public Bundle getExtraInfo()
  {
    return this.c;
  }

  public LatLng getMiddlePoint()
  {
    return this.h;
  }

  public LatLng getStartPoint()
  {
    return this.g;
  }

  public int getWidth()
  {
    return this.f;
  }

  public int getZIndex()
  {
    return this.a;
  }

  public boolean isVisible()
  {
    return this.b;
  }

  public ArcOptions points(LatLng paramLatLng1, LatLng paramLatLng2, LatLng paramLatLng3)
  {
    if ((paramLatLng1 == null) || (paramLatLng2 == null) || (paramLatLng3 == null))
      throw new IllegalArgumentException("start and middle and end points can not be null");
    if ((paramLatLng1 == paramLatLng2) || (paramLatLng1 == paramLatLng3) || (paramLatLng2 == paramLatLng3))
      throw new IllegalArgumentException("start and middle and end points can not be same");
    this.g = paramLatLng1;
    this.h = paramLatLng2;
    this.i = paramLatLng3;
    return this;
  }

  public ArcOptions visible(boolean paramBoolean)
  {
    this.b = paramBoolean;
    return this;
  }

  public ArcOptions width(int paramInt)
  {
    if (paramInt > 0)
      this.f = paramInt;
    return this;
  }

  public ArcOptions zIndex(int paramInt)
  {
    this.a = paramInt;
    return this;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.ArcOptions
 * JD-Core Version:    0.6.2
 */