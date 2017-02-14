package com.ismartgo.app.map;

import com.baidu.mapapi.model.LatLng;

public class DistanceMap
{
  public static double getDistance(LatLng paramLatLng1, LatLng paramLatLng2)
  {
    double d1 = 0.0174532925199433D * paramLatLng1.latitude;
    double d2 = 0.0174532925199433D * paramLatLng2.latitude;
    double d3 = paramLatLng1.longitude;
    double d4 = paramLatLng2.longitude;
    return 1000.0D * (Math.acos(Math.sin(d1) * Math.sin(d2) + Math.cos(d1) * Math.cos(d2) * Math.cos(0.0174532925199433D * d4 - 0.0174532925199433D * d3)) * 6371.0D);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.map.DistanceMap
 * JD-Core Version:    0.6.2
 */