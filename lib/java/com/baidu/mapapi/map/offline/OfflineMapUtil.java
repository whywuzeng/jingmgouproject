package com.baidu.mapapi.map.offline;

import com.baidu.mapapi.model.CoordUtil;
import com.baidu.platform.comapi.map.q;
import com.baidu.platform.comapi.map.t;
import java.util.ArrayList;
import java.util.Iterator;

public class OfflineMapUtil
{
  public static MKOLSearchRecord getSearchRecordFromLocalCityInfo(q paramq)
  {
    if (paramq == null)
      return null;
    MKOLSearchRecord localMKOLSearchRecord = new MKOLSearchRecord();
    localMKOLSearchRecord.cityID = paramq.a;
    localMKOLSearchRecord.cityName = paramq.b;
    localMKOLSearchRecord.cityType = paramq.d;
    if (paramq.a() != null)
    {
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator = paramq.a().iterator();
      for (int i = 0; ; i = j + i)
      {
        j = i;
        if (!localIterator.hasNext())
          break;
        q localq = (q)localIterator.next();
        localArrayList.add(getSearchRecordFromLocalCityInfo(localq));
        j = localq.c;
        localMKOLSearchRecord.childCities = localArrayList;
      }
    }
    int j = 0;
    if (localMKOLSearchRecord.cityType == 1);
    for (localMKOLSearchRecord.size = j; ; localMKOLSearchRecord.size = paramq.c)
      return localMKOLSearchRecord;
  }

  public static MKOLUpdateElement getUpdatElementFromLocalMapElement(t paramt)
  {
    if (paramt == null)
      return null;
    MKOLUpdateElement localMKOLUpdateElement = new MKOLUpdateElement();
    localMKOLUpdateElement.cityID = paramt.a;
    localMKOLUpdateElement.cityName = paramt.b;
    if (paramt.g != null)
      localMKOLUpdateElement.geoPt = CoordUtil.mc2ll(paramt.g);
    localMKOLUpdateElement.level = paramt.e;
    localMKOLUpdateElement.ratio = paramt.i;
    localMKOLUpdateElement.serversize = paramt.h;
    if (paramt.i == 100);
    for (localMKOLUpdateElement.size = paramt.h; ; localMKOLUpdateElement.size = (paramt.h / 100 * paramt.i))
    {
      localMKOLUpdateElement.status = paramt.l;
      localMKOLUpdateElement.update = paramt.j;
      return localMKOLUpdateElement;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.offline.OfflineMapUtil
 * JD-Core Version:    0.6.2
 */