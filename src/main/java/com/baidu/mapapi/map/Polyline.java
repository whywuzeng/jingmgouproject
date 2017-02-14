package com.baidu.mapapi.map;

import android.os.Bundle;
import android.util.Log;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.f;
import java.util.List;

public final class Polyline extends Overlay
{
  int a;
  List<LatLng> b;
  int[] c;
  int[] d;
  int e;
  boolean f;
  boolean g = false;
  boolean h = true;
  BitmapDescriptor i;
  List<BitmapDescriptor> j;

  Polyline()
  {
    this.q = f.i;
  }

  private Bundle a(boolean paramBoolean)
  {
    if (paramBoolean)
      return BitmapDescriptorFactory.fromAsset("lineDashTexture.png").b();
    return this.i.b();
  }

  static void a(int[] paramArrayOfInt, Bundle paramBundle)
  {
    if ((paramArrayOfInt != null) && (paramArrayOfInt.length > 0))
      paramBundle.putIntArray("traffic_array", paramArrayOfInt);
  }

  private Bundle b(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      localBundle = new Bundle();
      localBundle.putInt("total", 1);
      localBundle.putBundle("texture_0", BitmapDescriptorFactory.fromAsset("lineDashTexture.png").b());
      return localBundle;
    }
    Bundle localBundle = new Bundle();
    int k = 0;
    int n;
    for (int m = 0; k < this.j.size(); m = n)
    {
      n = m;
      if (this.j.get(k) != null)
      {
        localBundle.putBundle("texture_" + String.valueOf(m), ((BitmapDescriptor)this.j.get(k)).b());
        n = m + 1;
      }
      k += 1;
    }
    localBundle.putInt("total", m);
    return localBundle;
  }

  static void b(int[] paramArrayOfInt, Bundle paramBundle)
  {
    if ((paramArrayOfInt != null) && (paramArrayOfInt.length > 0))
    {
      paramBundle.putIntArray("color_array", paramArrayOfInt);
      paramBundle.putInt("total", 1);
    }
  }

  Bundle a(Bundle paramBundle)
  {
    int m = 1;
    super.a(paramBundle);
    GeoPoint localGeoPoint = CoordUtil.ll2mc((LatLng)this.b.get(0));
    paramBundle.putDouble("location_x", localGeoPoint.getLongitudeE6());
    paramBundle.putDouble("location_y", localGeoPoint.getLatitudeE6());
    paramBundle.putInt("width", this.e);
    Overlay.a(this.b, paramBundle);
    Overlay.a(this.a, paramBundle);
    a(this.c, paramBundle);
    b(this.d, paramBundle);
    if ((this.c != null) && (this.c.length > 0) && (this.c.length > this.b.size() - 1))
      Log.e("baidumapsdk", "the size of textureIndexs is larger than the size of points");
    int k;
    if (this.f)
    {
      paramBundle.putInt("dotline", 1);
      if (this.g != true)
        break label242;
      k = 1;
      label156: paramBundle.putInt("focus", k);
    }
    while (true)
    {
      try
      {
        if (this.i != null)
        {
          paramBundle.putInt("custom", 1);
          paramBundle.putBundle("image_info", a(false));
          if (this.j == null)
            break label294;
          paramBundle.putInt("customlist", 1);
          paramBundle.putBundle("image_info_list", b(false));
          if (this.h != true)
            break label352;
          k = m;
          paramBundle.putInt("keep", k);
          return paramBundle;
          paramBundle.putInt("dotline", 0);
          break;
          label242: k = 0;
          break label156;
        }
        if (this.f)
          paramBundle.putBundle("image_info", a(true));
        paramBundle.putInt("custom", 0);
        continue;
      }
      catch (Exception localException)
      {
        Log.e("baidumapsdk", "load texture resource failed!");
        paramBundle.putInt("dotline", 0);
        return paramBundle;
      }
      label294: if ((this.f) && (((this.c != null) && (this.c.length > 0)) || ((this.d != null) && (this.d.length > 0))))
        paramBundle.putBundle("image_info_list", b(true));
      paramBundle.putInt("customlist", 0);
      continue;
      label352: k = 0;
    }
  }

  public int getColor()
  {
    return this.a;
  }

  public List<LatLng> getPoints()
  {
    return this.b;
  }

  public int getWidth()
  {
    return this.e;
  }

  public boolean isDottedLine()
  {
    return this.f;
  }

  public boolean isFocus()
  {
    return this.g;
  }

  public void setColor(int paramInt)
  {
    this.a = paramInt;
    this.listener.b(this);
  }

  public void setDottedLine(boolean paramBoolean)
  {
    this.f = paramBoolean;
    this.listener.b(this);
  }

  public void setFocus(boolean paramBoolean)
  {
    this.g = paramBoolean;
    this.listener.b(this);
  }

  public void setPoints(List<LatLng> paramList)
  {
    if (paramList == null)
      throw new IllegalArgumentException("points list can not be null");
    if (paramList.size() < 2)
      throw new IllegalArgumentException("points count can not less than 2 or more than 10000");
    if (paramList.contains(null))
      throw new IllegalArgumentException("points list can not contains null");
    this.b = paramList;
    this.listener.b(this);
  }

  public void setWidth(int paramInt)
  {
    if (paramInt > 0)
    {
      this.e = paramInt;
      this.listener.b(this);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.Polyline
 * JD-Core Version:    0.6.2
 */