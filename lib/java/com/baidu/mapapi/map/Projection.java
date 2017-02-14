package com.baidu.mapapi.map;

import android.graphics.Point;
import android.graphics.PointF;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.B;
import com.baidu.platform.comapi.map.c;

public final class Projection
{
  private c a;

  Projection(c paramc)
  {
    this.a = paramc;
  }

  public LatLng fromScreenLocation(Point paramPoint)
  {
    if (paramPoint != null)
      return CoordUtil.mc2ll(this.a.b(paramPoint.x, paramPoint.y));
    return null;
  }

  public float metersToEquatorPixels(float paramFloat)
  {
    if (paramFloat <= 0.0F)
      return 0.0F;
    return (float)(paramFloat / this.a.z());
  }

  public PointF toOpenGLLocation(LatLng paramLatLng, MapStatus paramMapStatus)
  {
    if ((paramLatLng != null) && (paramMapStatus != null))
    {
      paramLatLng = CoordUtil.ll2mc(paramLatLng);
      paramMapStatus = paramMapStatus.a;
      double d1 = paramMapStatus.d;
      double d2 = paramMapStatus.e;
      return new PointF((float)((paramLatLng.getLongitudeE6() - d1) / paramMapStatus.n), (float)((paramLatLng.getLatitudeE6() - d2) / paramMapStatus.n));
    }
    return null;
  }

  public Point toScreenLocation(LatLng paramLatLng)
  {
    if (paramLatLng != null)
    {
      paramLatLng = CoordUtil.ll2mc(paramLatLng);
      return this.a.a(paramLatLng);
    }
    return null;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.Projection
 * JD-Core Version:    0.6.2
 */