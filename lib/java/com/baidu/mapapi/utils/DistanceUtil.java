package com.baidu.mapapi.utils;

import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;

public class DistanceUtil
{
  public static double getDistance(LatLng paramLatLng1, LatLng paramLatLng2)
  {
    if ((paramLatLng1 == null) || (paramLatLng2 == null));
    do
    {
      return -1.0D;
      paramLatLng1 = CoordUtil.ll2point(paramLatLng1);
      paramLatLng2 = CoordUtil.ll2point(paramLatLng2);
    }
    while ((paramLatLng1 == null) || (paramLatLng2 == null));
    return CoordUtil.getDistance(paramLatLng1, paramLatLng2);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.utils.DistanceUtil
 * JD-Core Version:    0.6.2
 */