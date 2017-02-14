package cn.jpush.android.a;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import cn.jpush.android.util.x;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;

public final class i
{
  private static final String z;
  private WifiManager a;

  static
  {
    Object localObject1 = "\025BF'".toCharArray();
    int j = localObject1.length;
    int m = 0;
    int i = 0;
    Object localObject2 = localObject1;
    int k = j;
    label30: int n;
    if (j <= 1)
    {
      m = i;
      k = i;
      localObject2 = localObject1;
      n = localObject2[k];
      switch (m % 5)
      {
      default:
        i = 1;
      case 0:
      case 1:
      case 2:
      case 3:
      }
    }
    while (true)
    {
      localObject2[k] = ((char)(i ^ n));
      m += 1;
      if (j == 0)
      {
        k = j;
        break label30;
      }
      k = j;
      localObject2 = localObject1;
      localObject1 = localObject2;
      j = k;
      i = m;
      if (k > m)
        break;
      z = new String(localObject2).intern();
      return;
      i = 98;
      continue;
      i = 43;
      continue;
      i = 32;
      continue;
      i = 78;
    }
  }

  public i(Context paramContext)
  {
    this.a = ((WifiManager)paramContext.getSystemService(z));
  }

  private List<j> d()
  {
    if (!a())
      return new ArrayList();
    Object localObject1 = this.a.getConnectionInfo();
    if (localObject1 != null);
    for (localObject1 = new j(this, ((WifiInfo)localObject1).getBSSID(), ((WifiInfo)localObject1).getRssi(), ((WifiInfo)localObject1).getSSID()); ; localObject1 = null)
    {
      ArrayList localArrayList = new ArrayList();
      if (localObject1 != null)
        localArrayList.add(localObject1);
      Object localObject2 = this.a.getScanResults();
      if ((localObject2 != null) && (((List)localObject2).size() > 0))
      {
        localObject2 = ((List)localObject2).iterator();
        while (((Iterator)localObject2).hasNext())
        {
          j localj = new j(this, (ScanResult)((Iterator)localObject2).next());
          if (!localj.c.equals(((j)localObject1).c))
            localArrayList.add(localj);
        }
      }
      return localArrayList;
    }
  }

  public final boolean a()
  {
    try
    {
      boolean bool = this.a.isWifiEnabled();
      return bool;
    }
    catch (Exception localException)
    {
      x.j();
    }
    return false;
  }

  public final WifiManager b()
  {
    return this.a;
  }

  public final JSONArray c()
  {
    JSONArray localJSONArray = new JSONArray();
    try
    {
      Iterator localIterator = d().iterator();
      while (true)
      {
        if (!localIterator.hasNext())
          return localJSONArray;
        localJSONArray.put(((j)localIterator.next()).a());
      }
    }
    catch (Exception localException)
    {
      x.j();
    }
    return localJSONArray;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.a.i
 * JD-Core Version:    0.6.2
 */