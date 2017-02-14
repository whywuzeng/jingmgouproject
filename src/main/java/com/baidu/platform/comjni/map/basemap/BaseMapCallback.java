package com.baidu.platform.comjni.map.basemap;

import android.os.Bundle;
import android.util.LongSparseArray;

public class BaseMapCallback
{
  private static LongSparseArray<b> a = new LongSparseArray();

  public static int ReqLayerData(Bundle paramBundle1, long paramLong, int paramInt, Bundle paramBundle2)
  {
    int j = a.size();
    int i = 0;
    while (i < j)
    {
      b localb = (b)a.valueAt(i);
      if ((localb != null) && (localb.a(paramLong)))
        return localb.a(paramBundle1, paramLong, paramInt, paramBundle2);
      i += 1;
    }
    return 0;
  }

  public static void addLayerDataInterface(long paramLong, b paramb)
  {
    a.put(paramLong, paramb);
  }

  public static void removeLayerDataInterface(long paramLong)
  {
    a.remove(paramLong);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.platform.comjni.map.basemap.BaseMapCallback
 * JD-Core Version:    0.6.2
 */