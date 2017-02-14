package com.baidu.mapapi.utils;

import com.baidu.mapapi.model.LatLng;
import java.util.List;

public class SpatialRelationUtil
{
  private static boolean a(LatLng paramLatLng1, LatLng paramLatLng2, LatLng paramLatLng3)
  {
    return (int)DistanceUtil.getDistance(paramLatLng1, paramLatLng2) >= (int)(DistanceUtil.getDistance(paramLatLng1, paramLatLng3) + DistanceUtil.getDistance(paramLatLng2, paramLatLng3));
  }

  private static LatLng b(LatLng paramLatLng1, LatLng paramLatLng2, LatLng paramLatLng3)
  {
    double d1 = Math.sqrt((paramLatLng2.longitude - paramLatLng1.longitude) * (paramLatLng2.longitude - paramLatLng1.longitude) + (paramLatLng2.latitude - paramLatLng1.latitude) * (paramLatLng2.latitude - paramLatLng1.latitude));
    d1 = ((paramLatLng2.longitude - paramLatLng1.longitude) * (paramLatLng3.longitude - paramLatLng1.longitude) + (paramLatLng2.latitude - paramLatLng1.latitude) * (paramLatLng3.latitude - paramLatLng1.latitude)) / (d1 * d1);
    double d2 = paramLatLng1.longitude;
    double d3 = paramLatLng2.longitude;
    double d4 = paramLatLng1.longitude;
    double d5 = paramLatLng1.latitude;
    return new LatLng(d1 * (paramLatLng2.latitude - paramLatLng1.latitude) + d5, d2 + (d3 - d4) * d1);
  }

  public static LatLng getNearestPointFromLine(List<LatLng> paramList, LatLng paramLatLng)
  {
    if ((paramList == null) || (paramList.size() == 0) || (paramLatLng == null))
      localObject2 = null;
    int i;
    Object localObject1;
    do
    {
      return localObject2;
      i = 0;
      localObject1 = null;
      localObject2 = localObject1;
    }
    while (i >= paramList.size() - 1);
    Object localObject2 = b((LatLng)paramList.get(i), (LatLng)paramList.get(i + 1), paramLatLng);
    if (a((LatLng)paramList.get(i), (LatLng)paramList.get(i + 1), (LatLng)localObject2))
    {
      label100: localObject3 = localObject2;
      if (localObject1 != null)
        if (DistanceUtil.getDistance(paramLatLng, (LatLng)localObject2) >= DistanceUtil.getDistance(paramLatLng, (LatLng)localObject1))
          break label203;
    }
    label203: for (Object localObject3 = localObject2; ; localObject3 = localObject1)
    {
      i += 1;
      localObject1 = localObject3;
      break;
      if (DistanceUtil.getDistance(paramLatLng, (LatLng)paramList.get(i)) < DistanceUtil.getDistance(paramLatLng, (LatLng)paramList.get(i + 1)))
      {
        localObject2 = (LatLng)paramList.get(i);
        break label100;
      }
      localObject2 = (LatLng)paramList.get(i + 1);
      break label100;
    }
  }

  public static boolean isCircleContainsPoint(LatLng paramLatLng1, int paramInt, LatLng paramLatLng2)
  {
    if ((paramLatLng1 == null) || (paramInt == 0) || (paramLatLng2 == null));
    double d;
    do
    {
      return false;
      d = DistanceUtil.getDistance(paramLatLng1, paramLatLng2);
    }
    while (d > paramInt);
    return d == paramInt;
  }

  public static boolean isPolygonContainsPoint(List<LatLng> paramList, LatLng paramLatLng)
  {
    boolean bool2 = true;
    boolean bool3 = false;
    boolean bool1;
    if ((paramList == null) || (paramList.size() == 0) || (paramLatLng == null))
    {
      bool1 = false;
      return bool1;
    }
    int i = 0;
    while (true)
    {
      if (i >= paramList.size())
        break label100;
      if (paramLatLng.longitude == ((LatLng)paramList.get(i)).longitude)
      {
        bool1 = bool2;
        if (paramLatLng.latitude == ((LatLng)paramList.get(i)).latitude)
          break;
      }
      i += 1;
    }
    label100: int k = paramList.size();
    int j = 0;
    i = 0;
    label114: LatLng localLatLng1;
    LatLng localLatLng2;
    if (j < k)
    {
      localLatLng1 = (LatLng)paramList.get(j);
      localLatLng2 = (LatLng)paramList.get((j + 1) % k);
      if (localLatLng1.latitude != localLatLng2.latitude);
    }
    while (true)
    {
      j += 1;
      break label114;
      if ((paramLatLng.latitude >= Math.min(localLatLng1.latitude, localLatLng2.latitude)) && (paramLatLng.latitude <= Math.max(localLatLng1.latitude, localLatLng2.latitude)))
      {
        double d = (paramLatLng.latitude - localLatLng1.latitude) * (localLatLng2.longitude - localLatLng1.longitude) / (localLatLng2.latitude - localLatLng1.latitude);
        d = localLatLng1.longitude + d;
        bool1 = bool2;
        if (d == paramLatLng.longitude)
          break;
        if (d < paramLatLng.longitude)
        {
          i += 1;
          continue;
          bool1 = bool3;
          if (i % 2 == 1)
            bool1 = true;
          return bool1;
        }
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.utils.SpatialRelationUtil
 * JD-Core Version:    0.6.2
 */