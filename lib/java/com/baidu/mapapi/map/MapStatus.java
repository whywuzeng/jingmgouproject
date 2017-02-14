package com.baidu.mapapi.map;

import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.model.LatLngBounds.Builder;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.B;
import com.baidu.platform.comapi.map.B.a;

public final class MapStatus
{
  B a;
  private double b;
  public final LatLngBounds bound;
  private double c;
  public final float overlook;
  public final float rotate;
  public final LatLng target;
  public final android.graphics.Point targetScreen;
  public final float zoom;

  MapStatus(float paramFloat1, LatLng paramLatLng, float paramFloat2, float paramFloat3, android.graphics.Point paramPoint, double paramDouble1, double paramDouble2, LatLngBounds paramLatLngBounds)
  {
    this.rotate = paramFloat1;
    this.target = paramLatLng;
    this.overlook = paramFloat2;
    this.zoom = paramFloat3;
    this.targetScreen = paramPoint;
    this.b = paramDouble1;
    this.c = paramDouble2;
    this.bound = paramLatLngBounds;
  }

  MapStatus(float paramFloat1, LatLng paramLatLng, float paramFloat2, float paramFloat3, android.graphics.Point paramPoint, LatLngBounds paramLatLngBounds)
  {
    this.rotate = paramFloat1;
    this.target = paramLatLng;
    this.overlook = paramFloat2;
    this.zoom = paramFloat3;
    this.targetScreen = paramPoint;
    if (this.target != null)
    {
      this.b = CoordUtil.ll2mc(this.target).getLongitudeE6();
      this.c = CoordUtil.ll2mc(this.target).getLatitudeE6();
    }
    this.bound = paramLatLngBounds;
  }

  MapStatus(float paramFloat1, LatLng paramLatLng, float paramFloat2, float paramFloat3, android.graphics.Point paramPoint, B paramB, double paramDouble1, double paramDouble2, LatLngBounds paramLatLngBounds)
  {
    this.rotate = paramFloat1;
    this.target = paramLatLng;
    this.overlook = paramFloat2;
    this.zoom = paramFloat3;
    this.targetScreen = paramPoint;
    this.a = paramB;
    this.b = paramDouble1;
    this.c = paramDouble2;
    this.bound = paramLatLngBounds;
  }

  static MapStatus a(B paramB)
  {
    float f1 = paramB.b;
    double d1 = paramB.e;
    double d2 = paramB.d;
    LatLng localLatLng1 = CoordUtil.mc2ll(new GeoPoint(d1, d2));
    float f2 = paramB.c;
    float f3 = paramB.a;
    android.graphics.Point localPoint = new android.graphics.Point(paramB.f, paramB.g);
    LatLng localLatLng2 = CoordUtil.mc2ll(new GeoPoint(paramB.k.e.y, paramB.k.e.x));
    LatLng localLatLng3 = CoordUtil.mc2ll(new GeoPoint(paramB.k.f.y, paramB.k.f.x));
    LatLng localLatLng4 = CoordUtil.mc2ll(new GeoPoint(paramB.k.h.y, paramB.k.h.x));
    LatLng localLatLng5 = CoordUtil.mc2ll(new GeoPoint(paramB.k.g.y, paramB.k.g.x));
    LatLngBounds.Builder localBuilder = new LatLngBounds.Builder();
    localBuilder.include(localLatLng2);
    localBuilder.include(localLatLng3);
    localBuilder.include(localLatLng4);
    localBuilder.include(localLatLng5);
    return new MapStatus(f1, localLatLng1, f2, f3, localPoint, paramB, d2, d1, localBuilder.build());
  }

  double a()
  {
    return this.b;
  }

  double b()
  {
    return this.c;
  }

  B b(B paramB)
  {
    if (this.rotate != -2.147484E+009F)
      paramB.b = ((int)this.rotate);
    if (this.zoom != -2.147484E+009F)
      paramB.a = this.zoom;
    if (this.overlook != -2.147484E+009F)
      paramB.c = ((int)this.overlook);
    if (this.target != null)
    {
      CoordUtil.ll2mc(this.target);
      paramB.d = this.b;
      paramB.e = this.c;
    }
    if (this.targetScreen != null)
    {
      paramB.f = this.targetScreen.x;
      paramB.g = this.targetScreen.y;
    }
    return paramB;
  }

  B c()
  {
    return b(new B());
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (this.target != null)
    {
      localStringBuilder.append("target lat: " + this.target.latitude + "\n");
      localStringBuilder.append("target lng: " + this.target.longitude + "\n");
    }
    if (this.targetScreen != null)
    {
      localStringBuilder.append("target screen x: " + this.targetScreen.x + "\n");
      localStringBuilder.append("target screen y: " + this.targetScreen.y + "\n");
    }
    localStringBuilder.append("zoom: " + this.zoom + "\n");
    localStringBuilder.append("rotate: " + this.rotate + "\n");
    localStringBuilder.append("overlook: " + this.overlook + "\n");
    return localStringBuilder.toString();
  }

  public static final class Builder
  {
    private float a = -2.147484E+009F;
    private LatLng b = null;
    private float c = -2.147484E+009F;
    private float d = -2.147484E+009F;
    private android.graphics.Point e = null;
    private LatLngBounds f = null;
    private double g = 0.0D;
    private double h = 0.0D;

    public Builder()
    {
    }

    public Builder(MapStatus paramMapStatus)
    {
      this.a = paramMapStatus.rotate;
      this.b = paramMapStatus.target;
      this.c = paramMapStatus.overlook;
      this.d = paramMapStatus.zoom;
      this.e = paramMapStatus.targetScreen;
      this.g = paramMapStatus.a();
      this.h = paramMapStatus.b();
    }

    public MapStatus build()
    {
      return new MapStatus(this.a, this.b, this.c, this.d, this.e, this.f);
    }

    public Builder overlook(float paramFloat)
    {
      this.c = paramFloat;
      return this;
    }

    public Builder rotate(float paramFloat)
    {
      this.a = paramFloat;
      return this;
    }

    public Builder target(LatLng paramLatLng)
    {
      this.b = paramLatLng;
      return this;
    }

    public Builder targetScreen(android.graphics.Point paramPoint)
    {
      this.e = paramPoint;
      return this;
    }

    public Builder zoom(float paramFloat)
    {
      this.d = paramFloat;
      return this;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.MapStatus
 * JD-Core Version:    0.6.2
 */