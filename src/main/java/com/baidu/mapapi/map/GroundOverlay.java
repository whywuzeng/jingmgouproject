package com.baidu.mapapi.map;

import android.graphics.Bitmap;
import android.os.Bundle;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.f;

public final class GroundOverlay extends Overlay
{
  private static final String j = GroundOverlay.class.getSimpleName();
  int a;
  BitmapDescriptor b;
  LatLng c;
  double d;
  double e;
  float f;
  float g;
  LatLngBounds h;
  float i;

  GroundOverlay()
  {
    this.q = f.d;
  }

  Bundle a(Bundle paramBundle)
  {
    super.a(paramBundle);
    paramBundle.putBundle("image_info", this.b.b());
    if (this.a == 1)
    {
      localGeoPoint = CoordUtil.ll2mc(this.h.southwest);
      double d1 = localGeoPoint.getLongitudeE6();
      double d2 = localGeoPoint.getLatitudeE6();
      localGeoPoint = CoordUtil.ll2mc(this.h.northeast);
      double d3 = localGeoPoint.getLongitudeE6();
      double d4 = localGeoPoint.getLatitudeE6();
      this.d = (d3 - d1);
      this.e = (d4 - d2);
      this.c = CoordUtil.mc2ll(new GeoPoint(d2 + this.e / 2.0D, d1 + this.d / 2.0D));
      this.f = 0.5F;
      this.g = 0.5F;
    }
    if ((this.d <= 0.0D) || (this.e <= 0.0D))
      throw new IllegalStateException("when you add ground overlay, the width and height must greater than 0");
    paramBundle.putDouble("x_distance", this.d);
    if (this.e == 2147483647.0D)
      this.e = ((int)(this.d * this.b.a.getHeight() / this.b.a.getWidth()));
    paramBundle.putDouble("y_distance", this.e);
    GeoPoint localGeoPoint = CoordUtil.ll2mc(this.c);
    paramBundle.putDouble("location_x", localGeoPoint.getLongitudeE6());
    paramBundle.putDouble("location_y", localGeoPoint.getLatitudeE6());
    paramBundle.putFloat("anchor_x", this.f);
    paramBundle.putFloat("anchor_y", this.g);
    paramBundle.putFloat("transparency", this.i);
    return paramBundle;
  }

  public float getAnchorX()
  {
    return this.f;
  }

  public float getAnchorY()
  {
    return this.g;
  }

  public LatLngBounds getBounds()
  {
    return this.h;
  }

  public double getHeight()
  {
    return this.e;
  }

  public BitmapDescriptor getImage()
  {
    return this.b;
  }

  public LatLng getPosition()
  {
    return this.c;
  }

  public float getTransparency()
  {
    return this.i;
  }

  public double getWidth()
  {
    return this.d;
  }

  public void setAnchor(float paramFloat1, float paramFloat2)
  {
    if ((paramFloat1 < 0.0F) || (paramFloat1 > 1.0F));
    while ((paramFloat2 < 0.0F) || (paramFloat2 > 1.0F))
      return;
    this.f = paramFloat1;
    this.g = paramFloat2;
    this.listener.b(this);
  }

  public void setDimensions(int paramInt)
  {
    this.d = paramInt;
    this.e = 2147483647.0D;
    this.listener.b(this);
  }

  public void setDimensions(int paramInt1, int paramInt2)
  {
    this.d = paramInt1;
    this.e = paramInt2;
    this.listener.b(this);
  }

  public void setImage(BitmapDescriptor paramBitmapDescriptor)
  {
    if (paramBitmapDescriptor == null)
      throw new IllegalArgumentException("image can not be null");
    this.b = paramBitmapDescriptor;
    this.listener.b(this);
  }

  public void setPosition(LatLng paramLatLng)
  {
    if (paramLatLng == null)
      throw new IllegalArgumentException("position can not be null");
    this.a = 2;
    this.c = paramLatLng;
    this.listener.b(this);
  }

  public void setPositionFromBounds(LatLngBounds paramLatLngBounds)
  {
    if (paramLatLngBounds == null)
      throw new IllegalArgumentException("bounds can not be null");
    this.a = 1;
    this.h = paramLatLngBounds;
    this.listener.b(this);
  }

  public void setTransparency(float paramFloat)
  {
    if ((paramFloat > 1.0F) || (paramFloat < 0.0F))
      return;
    this.i = paramFloat;
    this.listener.b(this);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.GroundOverlay
 * JD-Core Version:    0.6.2
 */