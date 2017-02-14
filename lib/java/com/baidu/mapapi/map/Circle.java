package com.baidu.mapapi.map;

import android.os.Bundle;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.f;

public final class Circle extends Overlay
{
  LatLng a;
  int b;
  int c;
  Stroke d;

  Circle()
  {
    this.q = f.h;
  }

  Bundle a(Bundle paramBundle)
  {
    super.a(paramBundle);
    Object localObject = CoordUtil.ll2mc(this.a);
    paramBundle.putDouble("location_x", ((GeoPoint)localObject).getLongitudeE6());
    paramBundle.putDouble("location_y", ((GeoPoint)localObject).getLatitudeE6());
    paramBundle.putInt("radius", CoordUtil.getMCDistanceByOneLatLngAndRadius(this.a, this.c));
    Overlay.a(this.b, paramBundle);
    if (this.d == null)
    {
      paramBundle.putInt("has_stroke", 0);
      return paramBundle;
    }
    paramBundle.putInt("has_stroke", 1);
    localObject = new Bundle();
    paramBundle.putBundle("stroke", this.d.a((Bundle)localObject));
    return paramBundle;
  }

  public LatLng getCenter()
  {
    return this.a;
  }

  public int getFillColor()
  {
    return this.b;
  }

  public int getRadius()
  {
    return this.c;
  }

  public Stroke getStroke()
  {
    return this.d;
  }

  public void setCenter(LatLng paramLatLng)
  {
    if (paramLatLng == null)
      throw new IllegalArgumentException("circle center can not be null");
    this.a = paramLatLng;
    this.listener.b(this);
  }

  public void setFillColor(int paramInt)
  {
    this.b = paramInt;
    this.listener.b(this);
  }

  public void setRadius(int paramInt)
  {
    this.c = paramInt;
    this.listener.b(this);
  }

  public void setStroke(Stroke paramStroke)
  {
    this.d = paramStroke;
    this.listener.b(this);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.Circle
 * JD-Core Version:    0.6.2
 */