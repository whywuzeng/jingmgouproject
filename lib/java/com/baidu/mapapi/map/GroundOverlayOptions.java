package com.baidu.mapapi.map;

import android.graphics.Bitmap;
import android.os.Bundle;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;

public final class GroundOverlayOptions extends OverlayOptions
{
  int a;
  boolean b = true;
  Bundle c;
  private BitmapDescriptor d;
  private LatLng e;
  private int f;
  private int g;
  private float h = 0.5F;
  private float i = 0.5F;
  private LatLngBounds j;
  private float k = 1.0F;

  Overlay a()
  {
    GroundOverlay localGroundOverlay = new GroundOverlay();
    localGroundOverlay.s = this.b;
    localGroundOverlay.r = this.a;
    localGroundOverlay.t = this.c;
    if (this.d == null)
      throw new IllegalStateException("when you add ground overlay, you must set the image");
    localGroundOverlay.b = this.d;
    if ((this.j == null) && (this.e != null))
    {
      if ((this.f <= 0) || (this.g <= 0))
        throw new IllegalArgumentException("when you add ground overlay, the width and height must greater than 0");
      localGroundOverlay.c = this.e;
      localGroundOverlay.f = this.h;
      localGroundOverlay.g = this.i;
      localGroundOverlay.d = this.f;
      localGroundOverlay.e = this.g;
    }
    for (localGroundOverlay.a = 2; ; localGroundOverlay.a = 1)
    {
      localGroundOverlay.i = this.k;
      return localGroundOverlay;
      if ((this.e != null) || (this.j == null))
        break;
      localGroundOverlay.h = this.j;
    }
    throw new IllegalStateException("when you add ground overlay, you must set one of position or bounds");
  }

  public GroundOverlayOptions anchor(float paramFloat1, float paramFloat2)
  {
    if ((paramFloat1 < 0.0F) || (paramFloat1 > 1.0F));
    while ((paramFloat2 < 0.0F) || (paramFloat2 > 1.0F))
      return this;
    this.h = paramFloat1;
    this.i = paramFloat2;
    return this;
  }

  public GroundOverlayOptions dimensions(int paramInt)
  {
    this.f = paramInt;
    this.g = 2147483647;
    return this;
  }

  public GroundOverlayOptions dimensions(int paramInt1, int paramInt2)
  {
    this.f = paramInt1;
    this.g = paramInt2;
    return this;
  }

  public GroundOverlayOptions extraInfo(Bundle paramBundle)
  {
    this.c = paramBundle;
    return this;
  }

  public float getAnchorX()
  {
    return this.h;
  }

  public float getAnchorY()
  {
    return this.i;
  }

  public LatLngBounds getBounds()
  {
    return this.j;
  }

  public Bundle getExtraInfo()
  {
    return this.c;
  }

  public int getHeight()
  {
    if (this.g == 2147483647)
      return (int)(this.f * this.d.a.getHeight() / this.d.a.getWidth());
    return this.g;
  }

  public BitmapDescriptor getImage()
  {
    return this.d;
  }

  public LatLng getPosition()
  {
    return this.e;
  }

  public float getTransparency()
  {
    return this.k;
  }

  public int getWidth()
  {
    return this.f;
  }

  public int getZIndex()
  {
    return this.a;
  }

  public GroundOverlayOptions image(BitmapDescriptor paramBitmapDescriptor)
  {
    if (paramBitmapDescriptor == null)
      throw new IllegalArgumentException("image can not be null");
    this.d = paramBitmapDescriptor;
    return this;
  }

  public boolean isVisible()
  {
    return this.b;
  }

  public GroundOverlayOptions position(LatLng paramLatLng)
  {
    if (paramLatLng == null)
      throw new IllegalArgumentException("position can not be null");
    this.e = paramLatLng;
    return this;
  }

  public GroundOverlayOptions positionFromBounds(LatLngBounds paramLatLngBounds)
  {
    if (paramLatLngBounds == null)
      throw new IllegalArgumentException("bounds can not be null");
    this.j = paramLatLngBounds;
    return this;
  }

  public GroundOverlayOptions transparency(float paramFloat)
  {
    if ((paramFloat > 1.0F) || (paramFloat < 0.0F))
      return this;
    this.k = paramFloat;
    return this;
  }

  public GroundOverlayOptions visible(boolean paramBoolean)
  {
    this.b = paramBoolean;
    return this;
  }

  public GroundOverlayOptions zIndex(int paramInt)
  {
    this.a = paramInt;
    return this;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.GroundOverlayOptions
 * JD-Core Version:    0.6.2
 */