package com.baidu.mapapi.map;

import android.graphics.Point;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;

public final class MapStatusUpdateFactory
{
  public static MapStatusUpdate newLatLng(LatLng paramLatLng)
  {
    if (paramLatLng == null)
      return null;
    MapStatusUpdate localMapStatusUpdate = new MapStatusUpdate(2);
    localMapStatusUpdate.c = paramLatLng;
    return localMapStatusUpdate;
  }

  public static MapStatusUpdate newLatLngBounds(LatLngBounds paramLatLngBounds)
  {
    if (paramLatLngBounds == null)
      return null;
    MapStatusUpdate localMapStatusUpdate = new MapStatusUpdate(3);
    localMapStatusUpdate.d = paramLatLngBounds;
    return localMapStatusUpdate;
  }

  public static MapStatusUpdate newLatLngBounds(LatLngBounds paramLatLngBounds, int paramInt1, int paramInt2)
  {
    if ((paramLatLngBounds == null) || (paramInt1 <= 0) || (paramInt2 <= 0))
      return null;
    MapStatusUpdate localMapStatusUpdate = new MapStatusUpdate(9);
    localMapStatusUpdate.d = paramLatLngBounds;
    localMapStatusUpdate.e = paramInt1;
    localMapStatusUpdate.f = paramInt2;
    return localMapStatusUpdate;
  }

  public static MapStatusUpdate newLatLngZoom(LatLng paramLatLng, float paramFloat)
  {
    MapStatusUpdate localMapStatusUpdate = new MapStatusUpdate(4);
    if (paramLatLng == null)
      return null;
    localMapStatusUpdate.c = paramLatLng;
    localMapStatusUpdate.g = paramFloat;
    return localMapStatusUpdate;
  }

  public static MapStatusUpdate newMapStatus(MapStatus paramMapStatus)
  {
    if (paramMapStatus == null)
      return null;
    MapStatusUpdate localMapStatusUpdate = new MapStatusUpdate(1);
    localMapStatusUpdate.b = paramMapStatus;
    return localMapStatusUpdate;
  }

  public static MapStatusUpdate scrollBy(int paramInt1, int paramInt2)
  {
    MapStatusUpdate localMapStatusUpdate = new MapStatusUpdate(5);
    localMapStatusUpdate.h = paramInt1;
    localMapStatusUpdate.i = paramInt2;
    return localMapStatusUpdate;
  }

  public static MapStatusUpdate zoomBy(float paramFloat)
  {
    MapStatusUpdate localMapStatusUpdate = new MapStatusUpdate(6);
    localMapStatusUpdate.j = paramFloat;
    return localMapStatusUpdate;
  }

  public static MapStatusUpdate zoomBy(float paramFloat, Point paramPoint)
  {
    if (paramPoint == null)
      return null;
    MapStatusUpdate localMapStatusUpdate = new MapStatusUpdate(7);
    localMapStatusUpdate.j = paramFloat;
    localMapStatusUpdate.k = paramPoint;
    return localMapStatusUpdate;
  }

  public static MapStatusUpdate zoomIn()
  {
    MapStatusUpdate localMapStatusUpdate = new MapStatusUpdate(6);
    localMapStatusUpdate.j = 1.0F;
    return localMapStatusUpdate;
  }

  public static MapStatusUpdate zoomOut()
  {
    MapStatusUpdate localMapStatusUpdate = new MapStatusUpdate(6);
    localMapStatusUpdate.j = -1.0F;
    return localMapStatusUpdate;
  }

  public static MapStatusUpdate zoomTo(float paramFloat)
  {
    MapStatusUpdate localMapStatusUpdate = new MapStatusUpdate(8);
    localMapStatusUpdate.g = paramFloat;
    return localMapStatusUpdate;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.MapStatusUpdateFactory
 * JD-Core Version:    0.6.2
 */