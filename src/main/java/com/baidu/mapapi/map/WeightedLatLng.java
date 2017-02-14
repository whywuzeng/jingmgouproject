package com.baidu.mapapi.map;

import android.graphics.Point;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;

public class WeightedLatLng extends j.a
{
  public static final double DEFAULT_INTENSITY = 1.0D;
  private Point a;
  public final double intensity;
  public final LatLng latLng;

  public WeightedLatLng(LatLng paramLatLng)
  {
    this(paramLatLng, 1.0D);
  }

  public WeightedLatLng(LatLng paramLatLng, double paramDouble)
  {
    if (paramLatLng == null)
      throw new IllegalArgumentException("latLng can not be null");
    this.latLng = paramLatLng;
    paramLatLng = CoordUtil.ll2mc(paramLatLng);
    this.a = new Point((int)paramLatLng.getLongitudeE6(), (int)paramLatLng.getLatitudeE6());
    if (paramDouble > 0.0D)
    {
      this.intensity = paramDouble;
      return;
    }
    this.intensity = 1.0D;
  }

  Point a()
  {
    return this.a;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.WeightedLatLng
 * JD-Core Version:    0.6.2
 */