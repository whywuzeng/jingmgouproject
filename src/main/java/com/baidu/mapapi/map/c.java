package com.baidu.mapapi.map;

import android.os.Bundle;
import com.baidu.platform.comapi.map.n;
import java.util.concurrent.locks.Lock;

class c
  implements n
{
  c(BaiduMap paramBaiduMap)
  {
  }

  public Bundle a(int paramInt1, int paramInt2, int paramInt3)
  {
    BaiduMap.t(this.a).lock();
    try
    {
      if (BaiduMap.u(this.a) != null)
      {
        Object localObject1 = BaiduMap.u(this.a).a(paramInt1, paramInt2, paramInt3);
        if (localObject1 != null)
        {
          localObject1 = ((Tile)localObject1).a();
          return localObject1;
        }
      }
      return null;
    }
    finally
    {
      BaiduMap.t(this.a).unlock();
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.c
 * JD-Core Version:    0.6.2
 */