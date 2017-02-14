package com.baidu.mapapi.map.offline;

import com.baidu.mapapi.BMapManager;
import com.baidu.platform.comapi.map.q;
import com.baidu.platform.comapi.map.r;
import com.baidu.platform.comapi.map.t;
import com.baidu.platform.comapi.map.u;
import java.util.ArrayList;
import java.util.Iterator;

public class MKOfflineMap
{
  public static final int TYPE_DOWNLOAD_UPDATE = 0;
  public static final int TYPE_NEW_OFFLINE = 6;
  public static final int TYPE_VER_UPDATE = 4;
  private static final String a = MKOfflineMap.class.getSimpleName();
  private r b;
  private MKOfflineMapListener c;

  public void destroy()
  {
    this.b.d(0);
    this.b.b(null);
    this.b.b();
    BMapManager.destroy();
  }

  public ArrayList<MKOLUpdateElement> getAllUpdateInfo()
  {
    Object localObject = this.b.e();
    if (localObject == null)
      return null;
    ArrayList localArrayList = new ArrayList();
    localObject = ((ArrayList)localObject).iterator();
    while (((Iterator)localObject).hasNext())
      localArrayList.add(OfflineMapUtil.getUpdatElementFromLocalMapElement(((u)((Iterator)localObject).next()).a()));
    return localArrayList;
  }

  public ArrayList<MKOLSearchRecord> getHotCityList()
  {
    Object localObject = this.b.c();
    if (localObject == null)
      return null;
    ArrayList localArrayList = new ArrayList();
    localObject = ((ArrayList)localObject).iterator();
    while (((Iterator)localObject).hasNext())
      localArrayList.add(OfflineMapUtil.getSearchRecordFromLocalCityInfo((q)((Iterator)localObject).next()));
    return localArrayList;
  }

  public ArrayList<MKOLSearchRecord> getOfflineCityList()
  {
    Object localObject = this.b.d();
    if (localObject == null)
      return null;
    ArrayList localArrayList = new ArrayList();
    localObject = ((ArrayList)localObject).iterator();
    while (((Iterator)localObject).hasNext())
      localArrayList.add(OfflineMapUtil.getSearchRecordFromLocalCityInfo((q)((Iterator)localObject).next()));
    return localArrayList;
  }

  public MKOLUpdateElement getUpdateInfo(int paramInt)
  {
    u localu = this.b.g(paramInt);
    if (localu == null)
      return null;
    return OfflineMapUtil.getUpdatElementFromLocalMapElement(localu.a());
  }

  @Deprecated
  public int importOfflineData()
  {
    return importOfflineData(false);
  }

  @Deprecated
  public int importOfflineData(boolean paramBoolean)
  {
    int i = 0;
    ArrayList localArrayList = this.b.e();
    if (localArrayList != null)
      i = localArrayList.size();
    for (int j = i; ; j = 0)
    {
      this.b.a(paramBoolean, true);
      localArrayList = this.b.e();
      if (localArrayList != null)
        i = localArrayList.size();
      return i - j;
    }
  }

  public boolean init(MKOfflineMapListener paramMKOfflineMapListener)
  {
    BMapManager.init();
    this.b = r.a();
    if (this.b == null)
      return false;
    this.b.a(new a(this));
    this.c = paramMKOfflineMapListener;
    return true;
  }

  public boolean pause(int paramInt)
  {
    return this.b.c(paramInt);
  }

  public boolean remove(int paramInt)
  {
    return this.b.e(paramInt);
  }

  public ArrayList<MKOLSearchRecord> searchCity(String paramString)
  {
    Object localObject = this.b.a(paramString);
    if (localObject == null)
      return null;
    paramString = new ArrayList();
    localObject = ((ArrayList)localObject).iterator();
    while (((Iterator)localObject).hasNext())
      paramString.add(OfflineMapUtil.getSearchRecordFromLocalCityInfo((q)((Iterator)localObject).next()));
    return paramString;
  }

  public boolean start(int paramInt)
  {
    if (this.b == null)
      return false;
    if (this.b.e() != null)
    {
      Iterator localIterator = this.b.e().iterator();
      while (localIterator.hasNext())
      {
        u localu = (u)localIterator.next();
        if (localu.a.a == paramInt)
        {
          if ((localu.a.j) || (localu.a.l == 2) || (localu.a.l == 3) || (localu.a.l == 6))
            return this.b.b(paramInt);
          return false;
        }
      }
    }
    return this.b.a(paramInt);
  }

  public boolean update(int paramInt)
  {
    if (this.b == null)
      return false;
    if (this.b.e() != null)
    {
      Iterator localIterator = this.b.e().iterator();
      while (localIterator.hasNext())
      {
        u localu = (u)localIterator.next();
        if (localu.a.a == paramInt)
        {
          if (localu.a.j)
            return this.b.f(paramInt);
          return false;
        }
      }
    }
    return false;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.offline.MKOfflineMap
 * JD-Core Version:    0.6.2
 */