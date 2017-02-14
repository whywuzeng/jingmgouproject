package com.baidu.mapapi.map;

import android.os.Bundle;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.f;
import java.util.List;

public final class Polygon extends Overlay
{
  Stroke a;
  int b;
  List<LatLng> c;

  Polygon()
  {
    this.q = f.j;
  }

  Bundle a(Bundle paramBundle)
  {
    super.a(paramBundle);
    Object localObject = CoordUtil.ll2mc((LatLng)this.c.get(0));
    paramBundle.putDouble("location_x", ((GeoPoint)localObject).getLongitudeE6());
    paramBundle.putDouble("location_y", ((GeoPoint)localObject).getLatitudeE6());
    Overlay.a(this.c, paramBundle);
    Overlay.a(this.b, paramBundle);
    if (this.a == null)
    {
      paramBundle.putInt("has_stroke", 0);
      return paramBundle;
    }
    paramBundle.putInt("has_stroke", 1);
    localObject = new Bundle();
    paramBundle.putBundle("stroke", this.a.a((Bundle)localObject));
    return paramBundle;
  }

  public int getFillColor()
  {
    return this.b;
  }

  public List<LatLng> getPoints()
  {
    return this.c;
  }

  public Stroke getStroke()
  {
    return this.a;
  }

  public void setFillColor(int paramInt)
  {
    this.b = paramInt;
    this.listener.b(this);
  }

  public void setPoints(List<LatLng> paramList)
  {
    if (paramList == null)
      throw new IllegalArgumentException("points list can not be null");
    if (paramList.size() <= 2)
      throw new IllegalArgumentException("points count can not less than three");
    if (paramList.contains(null))
      throw new IllegalArgumentException("points list can not contains null");
    int i = 0;
    while (i < paramList.size())
    {
      int j = i + 1;
      while (j < paramList.size())
      {
        if ((LatLng)paramList.get(i) == (LatLng)paramList.get(j))
          throw new IllegalArgumentException("points list can not has same points");
        j += 1;
      }
      i += 1;
    }
    this.c = paramList;
    this.listener.b(this);
  }

  public void setStroke(Stroke paramStroke)
  {
    this.a = paramStroke;
    this.listener.b(this);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.Polygon
 * JD-Core Version:    0.6.2
 */