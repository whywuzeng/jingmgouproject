package com.baidu.mapapi.map;

import android.os.Bundle;
import com.baidu.mapapi.model.LatLng;

public final class DotOptions extends OverlayOptions
{
  int a;
  boolean b = true;
  Bundle c;
  private LatLng d;
  private int e = -16777216;
  private int f = 5;

  Overlay a()
  {
    Dot localDot = new Dot();
    localDot.s = this.b;
    localDot.r = this.a;
    localDot.t = this.c;
    localDot.b = this.e;
    localDot.a = this.d;
    localDot.c = this.f;
    return localDot;
  }

  public DotOptions center(LatLng paramLatLng)
  {
    if (paramLatLng == null)
      throw new IllegalArgumentException("dot center can not be null");
    this.d = paramLatLng;
    return this;
  }

  public DotOptions color(int paramInt)
  {
    this.e = paramInt;
    return this;
  }

  public DotOptions extraInfo(Bundle paramBundle)
  {
    this.c = paramBundle;
    return this;
  }

  public LatLng getCenter()
  {
    return this.d;
  }

  public int getColor()
  {
    return this.e;
  }

  public Bundle getExtraInfo()
  {
    return this.c;
  }

  public int getRadius()
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

  public DotOptions radius(int paramInt)
  {
    if (paramInt > 0)
      this.f = paramInt;
    return this;
  }

  public DotOptions visible(boolean paramBoolean)
  {
    this.b = paramBoolean;
    return this;
  }

  public DotOptions zIndex(int paramInt)
  {
    this.a = paramInt;
    return this;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.DotOptions
 * JD-Core Version:    0.6.2
 */