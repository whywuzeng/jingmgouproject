package com.baidu.mapapi.map;

import android.view.View;
import android.view.View.OnClickListener;
import com.baidu.platform.comapi.map.B;
import com.baidu.platform.comapi.map.c;
import com.baidu.platform.comapi.map.g;

class i
  implements View.OnClickListener
{
  i(MapView paramMapView)
  {
  }

  public void onClick(View paramView)
  {
    paramView = MapView.a(this.a).a().v();
    paramView.a += 1.0F;
    MapView.a(this.a).a().a(paramView, 300);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.i
 * JD-Core Version:    0.6.2
 */