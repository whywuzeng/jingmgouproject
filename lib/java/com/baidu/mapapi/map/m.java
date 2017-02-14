package com.baidu.mapapi.map;

import android.view.View;
import android.view.View.OnClickListener;
import com.baidu.platform.comapi.map.B;
import com.baidu.platform.comapi.map.C;
import com.baidu.platform.comapi.map.c;

class m
  implements View.OnClickListener
{
  m(TextureMapView paramTextureMapView)
  {
  }

  public void onClick(View paramView)
  {
    paramView = TextureMapView.a(this.a).b().v();
    paramView.a += 1.0F;
    TextureMapView.a(this.a).b().a(paramView, 300);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.m
 * JD-Core Version:    0.6.2
 */