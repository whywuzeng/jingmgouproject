package com.baidu.mapapi.map;

import android.os.Bundle;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.f;
import java.util.ArrayList;
import java.util.List;

public final class Arc extends Overlay
{
  private static final String f = Arc.class.getSimpleName();
  int a;
  int b;
  LatLng c;
  LatLng d;
  LatLng e;

  Arc()
  {
    this.q = f.f;
  }

  Bundle a(Bundle paramBundle)
  {
    super.a(paramBundle);
    ArrayList localArrayList = new ArrayList();
    localArrayList.clear();
    localArrayList.add(this.c);
    localArrayList.add(this.d);
    localArrayList.add(this.e);
    GeoPoint localGeoPoint = CoordUtil.ll2mc((LatLng)localArrayList.get(0));
    paramBundle.putDouble("location_x", localGeoPoint.getLongitudeE6());
    paramBundle.putDouble("location_y", localGeoPoint.getLatitudeE6());
    paramBundle.putInt("width", this.b);
    Overlay.a(localArrayList, paramBundle);
    Overlay.a(this.a, paramBundle);
    return paramBundle;
  }

  public int getColor()
  {
    return this.a;
  }

  public LatLng getEndPoint()
  {
    return this.e;
  }

  public LatLng getMiddlePoint()
  {
    return this.d;
  }

  public LatLng getStartPoint()
  {
    return this.c;
  }

  public int getWidth()
  {
    return this.b;
  }

  public void setColor(int paramInt)
  {
    this.a = paramInt;
    this.listener.b(this);
  }

  public void setPoints(LatLng paramLatLng1, LatLng paramLatLng2, LatLng paramLatLng3)
  {
    if ((paramLatLng1 == null) || (paramLatLng2 == null) || (paramLatLng3 == null))
      throw new IllegalArgumentException("start and middle and end points can not be null");
    if ((paramLatLng1 == paramLatLng2) || (paramLatLng1 == paramLatLng3) || (paramLatLng2 == paramLatLng3))
      throw new IllegalArgumentException("start and middle and end points can not be same");
    this.c = paramLatLng1;
    this.d = paramLatLng2;
    this.e = paramLatLng3;
    this.listener.b(this);
  }

  public void setWidth(int paramInt)
  {
    if (paramInt > 0)
    {
      this.b = paramInt;
      this.listener.b(this);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.Arc
 * JD-Core Version:    0.6.2
 */