package com.baidu.mapapi.map;

import android.os.Bundle;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.f;
import java.util.List;

public abstract class Overlay
{
  protected a listener;
  String p = System.currentTimeMillis() + "_" + hashCode();
  f q;
  int r;
  boolean s;
  Bundle t;

  static void a(int paramInt, Bundle paramBundle)
  {
    Bundle localBundle = new Bundle();
    localBundle.putFloat("red", (paramInt >> 16 & 0xFF) / 255.0F);
    localBundle.putFloat("green", (paramInt >> 8 & 0xFF) / 255.0F);
    localBundle.putFloat("blue", (paramInt & 0xFF) / 255.0F);
    localBundle.putFloat("alpha", (paramInt >>> 24) / 255.0F);
    paramBundle.putBundle("color", localBundle);
  }

  static void a(List<LatLng> paramList, Bundle paramBundle)
  {
    int j = paramList.size();
    double[] arrayOfDouble1 = new double[j];
    double[] arrayOfDouble2 = new double[j];
    int i = 0;
    while (i < j)
    {
      GeoPoint localGeoPoint = CoordUtil.ll2mc((LatLng)paramList.get(i));
      arrayOfDouble1[i] = localGeoPoint.getLongitudeE6();
      arrayOfDouble2[i] = localGeoPoint.getLatitudeE6();
      i += 1;
    }
    paramBundle.putDoubleArray("x_array", arrayOfDouble1);
    paramBundle.putDoubleArray("y_array", arrayOfDouble2);
  }

  Bundle a()
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("id", this.p);
    localBundle.putInt("type", this.q.ordinal());
    return localBundle;
  }

  Bundle a(Bundle paramBundle)
  {
    paramBundle.putString("id", this.p);
    paramBundle.putInt("type", this.q.ordinal());
    if (this.s);
    for (int i = 1; ; i = 0)
    {
      paramBundle.putInt("visibility", i);
      paramBundle.putInt("z_index", this.r);
      return paramBundle;
    }
  }

  public Bundle getExtraInfo()
  {
    return this.t;
  }

  public int getZIndex()
  {
    return this.r;
  }

  public boolean isVisible()
  {
    return this.s;
  }

  public void remove()
  {
    this.listener.a(this);
  }

  public void setExtraInfo(Bundle paramBundle)
  {
    this.t = paramBundle;
  }

  public void setVisible(boolean paramBoolean)
  {
    this.s = paramBoolean;
    this.listener.b(this);
  }

  public void setZIndex(int paramInt)
  {
    this.r = paramInt;
    this.listener.b(this);
  }

  static abstract interface a
  {
    public abstract void a(Overlay paramOverlay);

    public abstract void b(Overlay paramOverlay);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.Overlay
 * JD-Core Version:    0.6.2
 */