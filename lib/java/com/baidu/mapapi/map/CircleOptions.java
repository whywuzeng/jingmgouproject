package com.baidu.mapapi.map;

import android.os.Bundle;
import com.baidu.mapapi.model.LatLng;

public final class CircleOptions extends OverlayOptions
{
  private static final String d = CircleOptions.class.getSimpleName();
  int a;
  boolean b = true;
  Bundle c;
  private LatLng e;
  private int f = -16777216;
  private int g;
  private Stroke h;

  Overlay a()
  {
    Circle localCircle = new Circle();
    localCircle.s = this.b;
    localCircle.r = this.a;
    localCircle.t = this.c;
    localCircle.b = this.f;
    localCircle.a = this.e;
    localCircle.c = this.g;
    localCircle.d = this.h;
    return localCircle;
  }

  public CircleOptions center(LatLng paramLatLng)
  {
    if (paramLatLng == null)
      throw new IllegalArgumentException("circle center can not be null");
    this.e = paramLatLng;
    return this;
  }

  public CircleOptions extraInfo(Bundle paramBundle)
  {
    this.c = paramBundle;
    return this;
  }

  public CircleOptions fillColor(int paramInt)
  {
    this.f = paramInt;
    return this;
  }

  public LatLng getCenter()
  {
    return this.e;
  }

  public Bundle getExtraInfo()
  {
    return this.c;
  }

  public int getFillColor()
  {
    return this.f;
  }

  public int getRadius()
  {
    return this.g;
  }

  public Stroke getStroke()
  {
    return this.h;
  }

  public int getZIndex()
  {
    return this.a;
  }

  public boolean isVisible()
  {
    return this.b;
  }

  public CircleOptions radius(int paramInt)
  {
    this.g = paramInt;
    return this;
  }

  public CircleOptions stroke(Stroke paramStroke)
  {
    this.h = paramStroke;
    return this;
  }

  public CircleOptions visible(boolean paramBoolean)
  {
    this.b = paramBoolean;
    return this;
  }

  public CircleOptions zIndex(int paramInt)
  {
    this.a = paramInt;
    return this;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.CircleOptions
 * JD-Core Version:    0.6.2
 */