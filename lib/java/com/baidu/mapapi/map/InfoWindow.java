package com.baidu.mapapi.map;

import android.view.View;
import com.baidu.mapapi.model.LatLng;

public class InfoWindow
{
  BitmapDescriptor a;
  View b;
  LatLng c;
  OnInfoWindowClickListener d;
  int e;

  public InfoWindow(View paramView, LatLng paramLatLng, int paramInt)
  {
    if ((paramView == null) || (paramLatLng == null))
      throw new IllegalArgumentException("view and position can not be null");
    this.b = paramView;
    this.c = paramLatLng;
    this.e = paramInt;
  }

  public InfoWindow(BitmapDescriptor paramBitmapDescriptor, LatLng paramLatLng, int paramInt, OnInfoWindowClickListener paramOnInfoWindowClickListener)
  {
    if ((paramBitmapDescriptor == null) || (paramLatLng == null))
      throw new IllegalArgumentException("bitmapDescriptor and position can not be null");
    this.a = paramBitmapDescriptor;
    this.c = paramLatLng;
    this.d = paramOnInfoWindowClickListener;
    this.e = paramInt;
  }

  public static abstract interface OnInfoWindowClickListener
  {
    public abstract void onInfoWindowClick();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.InfoWindow
 * JD-Core Version:    0.6.2
 */