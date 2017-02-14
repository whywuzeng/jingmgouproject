package com.baidu.mapapi.map;

import android.os.Bundle;
import com.baidu.platform.comapi.map.c;
import java.util.ArrayList;
import java.util.List;

class a
  implements Overlay.a
{
  a(BaiduMap paramBaiduMap)
  {
  }

  public void a(Overlay paramOverlay)
  {
    if ((paramOverlay != null) && (BaiduMap.a(this.a).contains(paramOverlay)))
    {
      Bundle localBundle = paramOverlay.a();
      if (BaiduMap.b(this.a) != null)
        BaiduMap.b(this.a).d(localBundle);
      BaiduMap.a(this.a).remove(paramOverlay);
    }
    if ((paramOverlay != null) && (BaiduMap.c(this.a).contains(paramOverlay)))
    {
      paramOverlay = (Marker)paramOverlay;
      if (paramOverlay.n != null)
      {
        BaiduMap.c(this.a).remove(paramOverlay);
        if ((BaiduMap.c(this.a).size() == 0) && (BaiduMap.b(this.a) != null))
          BaiduMap.b(this.a).b(false);
      }
    }
  }

  public void b(Overlay paramOverlay)
  {
    if ((paramOverlay != null) && (BaiduMap.a(this.a).contains(paramOverlay)))
    {
      if ((paramOverlay instanceof Marker))
      {
        localObject = (Marker)paramOverlay;
        if ((((Marker)localObject).n != null) && (((Marker)localObject).n.size() != 0))
        {
          if (BaiduMap.c(this.a).contains(localObject))
            BaiduMap.c(this.a).remove(localObject);
          BaiduMap.c(this.a).add(localObject);
          if (BaiduMap.b(this.a) != null)
            BaiduMap.b(this.a).b(true);
        }
      }
      Object localObject = new Bundle();
      if (BaiduMap.b(this.a) != null)
        BaiduMap.b(this.a).c(paramOverlay.a((Bundle)localObject));
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.a
 * JD-Core Version:    0.6.2
 */