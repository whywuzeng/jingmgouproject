package com.baidu.mapapi.map;

import android.graphics.Point;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.B;
import com.baidu.platform.comapi.map.B.b;
import com.baidu.platform.comapi.map.c;

public final class MapStatusUpdate
{
  private static final String l = MapStatusUpdate.class.getSimpleName();
  int a;
  MapStatus b;
  LatLng c;
  LatLngBounds d;
  int e;
  int f;
  float g;
  int h;
  int i;
  float j;
  Point k;

  MapStatusUpdate()
  {
  }

  MapStatusUpdate(int paramInt)
  {
    this.a = paramInt;
  }

  MapStatus a(c paramc, MapStatus paramMapStatus)
  {
    switch (this.a)
    {
    default:
      return null;
    case 1:
      return this.b;
    case 2:
      return new MapStatus(paramMapStatus.rotate, this.c, paramMapStatus.overlook, paramMapStatus.zoom, paramMapStatus.targetScreen, null);
    case 3:
      localObject = CoordUtil.ll2mc(this.d.southwest);
      localGeoPoint = CoordUtil.ll2mc(this.d.northeast);
      d1 = ((GeoPoint)localObject).getLongitudeE6();
      d2 = localGeoPoint.getLatitudeE6();
      d3 = localGeoPoint.getLongitudeE6();
      d4 = ((GeoPoint)localObject).getLatitudeE6();
      f1 = paramc.a((int)d1, (int)d2, (int)d3, (int)d4, paramMapStatus.a.j.b - paramMapStatus.a.j.a, paramMapStatus.a.j.d - paramMapStatus.a.j.c);
      paramc = this.d.getCenter();
      return new MapStatus(paramMapStatus.rotate, paramc, paramMapStatus.overlook, f1, paramMapStatus.targetScreen, null);
    case 4:
      return new MapStatus(paramMapStatus.rotate, this.c, paramMapStatus.overlook, this.g, paramMapStatus.targetScreen, null);
    case 5:
      paramc.w();
      paramc = paramc.b(paramc.w() / 2 + this.h, paramc.x() / 2 + this.i);
      localObject = CoordUtil.mc2ll(paramc);
      return new MapStatus(paramMapStatus.rotate, (LatLng)localObject, paramMapStatus.overlook, paramMapStatus.zoom, paramMapStatus.targetScreen, paramc.getLongitudeE6(), paramc.getLatitudeE6(), null);
    case 6:
      return new MapStatus(paramMapStatus.rotate, paramMapStatus.target, paramMapStatus.overlook, paramMapStatus.zoom + this.j, paramMapStatus.targetScreen, paramMapStatus.a(), paramMapStatus.b(), null);
    case 7:
      paramc = CoordUtil.mc2ll(paramc.b(this.k.x, this.k.y));
      return new MapStatus(paramMapStatus.rotate, paramc, paramMapStatus.overlook, paramMapStatus.zoom + this.j, this.k, null);
    case 8:
      return new MapStatus(paramMapStatus.rotate, paramMapStatus.target, paramMapStatus.overlook, this.g, paramMapStatus.targetScreen, paramMapStatus.a(), paramMapStatus.b(), null);
    case 9:
    }
    Object localObject = CoordUtil.ll2mc(this.d.southwest);
    GeoPoint localGeoPoint = CoordUtil.ll2mc(this.d.northeast);
    double d1 = ((GeoPoint)localObject).getLongitudeE6();
    double d2 = localGeoPoint.getLatitudeE6();
    double d3 = localGeoPoint.getLongitudeE6();
    double d4 = ((GeoPoint)localObject).getLatitudeE6();
    float f1 = paramc.a((int)d1, (int)d2, (int)d3, (int)d4, this.e, this.f);
    paramc = this.d.getCenter();
    return new MapStatus(paramMapStatus.rotate, paramc, paramMapStatus.overlook, f1, paramMapStatus.targetScreen, null);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.MapStatusUpdate
 * JD-Core Version:    0.6.2
 */