package com.baidu.mapapi.map;

import com.baidu.mapapi.model.LatLng;
import com.baidu.platform.comapi.map.z;

public final class BaiduMapOptions
{
  MapStatus a = new MapStatus(0.0F, new LatLng(39.914935D, 116.403119D), 0.0F, 12.0F, null, null);
  boolean b = true;
  int c = 1;
  boolean d = true;
  boolean e = true;
  boolean f = true;
  boolean g = true;
  boolean h = true;
  boolean i = true;

  z a()
  {
    return new z().a(this.a.c()).a(this.b).a(this.c).b(this.d).c(this.e).d(this.f).e(this.g);
  }

  public BaiduMapOptions compassEnabled(boolean paramBoolean)
  {
    this.b = paramBoolean;
    return this;
  }

  public BaiduMapOptions mapStatus(MapStatus paramMapStatus)
  {
    if (paramMapStatus != null)
      this.a = paramMapStatus;
    return this;
  }

  public BaiduMapOptions mapType(int paramInt)
  {
    this.c = paramInt;
    return this;
  }

  public BaiduMapOptions overlookingGesturesEnabled(boolean paramBoolean)
  {
    this.f = paramBoolean;
    return this;
  }

  public BaiduMapOptions rotateGesturesEnabled(boolean paramBoolean)
  {
    this.d = paramBoolean;
    return this;
  }

  public BaiduMapOptions scaleControlEnabled(boolean paramBoolean)
  {
    this.i = paramBoolean;
    return this;
  }

  public BaiduMapOptions scrollGesturesEnabled(boolean paramBoolean)
  {
    this.e = paramBoolean;
    return this;
  }

  public BaiduMapOptions zoomControlsEnabled(boolean paramBoolean)
  {
    this.h = paramBoolean;
    return this;
  }

  public BaiduMapOptions zoomGesturesEnabled(boolean paramBoolean)
  {
    this.g = paramBoolean;
    return this;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.BaiduMapOptions
 * JD-Core Version:    0.6.2
 */