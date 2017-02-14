package com.baidu.mapapi.map;

import android.content.Context;
import android.os.Bundle;
import com.baidu.platform.comapi.map.I;
import java.util.concurrent.locks.Lock;

class d
  implements I
{
  d(BaiduMap paramBaiduMap)
  {
  }

  public Bundle a(int paramInt1, int paramInt2, int paramInt3, Context paramContext)
  {
    BaiduMap.w(this.a).lock();
    try
    {
      if (BaiduMap.x(this.a) != null)
      {
        paramContext = BaiduMap.x(this.a).a(paramInt1, paramInt2, paramInt3);
        if (paramContext != null)
        {
          paramContext = paramContext.a();
          return paramContext;
        }
      }
      return null;
    }
    finally
    {
      BaiduMap.w(this.a).unlock();
    }
    throw paramContext;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.d
 * JD-Core Version:    0.6.2
 */