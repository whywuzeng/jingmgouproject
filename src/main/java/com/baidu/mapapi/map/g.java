package com.baidu.mapapi.map;

import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.B;
import com.baidu.platform.comapi.map.c;
import com.baidu.platform.comapi.map.i;
import javax.microedition.khronos.opengles.GL10;

class g
  implements i
{
  g(MapView paramMapView)
  {
  }

  public void a()
  {
    float f = MapView.a(this.a).a().v().a;
    int i;
    if (MapView.b(this.a) != f)
    {
      i = ((Integer)MapView.a().get((int)f)).intValue();
      int j = (int)(i / MapView.a(this.a).a().v().m);
      MapView.c(this.a).setPadding(j / 2, 0, j / 2, 0);
      if (i < 1000)
        break label160;
    }
    label160: for (String str = String.format(" %d公里 ", new Object[] { Integer.valueOf(i / 1000) }); ; str = String.format(" %d米 ", new Object[] { Integer.valueOf(i) }))
    {
      MapView.d(this.a).setText(str);
      MapView.e(this.a).setText(str);
      MapView.a(this.a, f);
      MapView.f(this.a);
      this.a.requestLayout();
      return;
    }
  }

  public void a(Bitmap paramBitmap)
  {
  }

  public void a(MotionEvent paramMotionEvent)
  {
  }

  public void a(GeoPoint paramGeoPoint)
  {
  }

  public void a(B paramB)
  {
  }

  public void a(String paramString)
  {
  }

  public void a(GL10 paramGL10, B paramB)
  {
  }

  public void b()
  {
  }

  public void b(GeoPoint paramGeoPoint)
  {
  }

  public void b(B paramB)
  {
  }

  public boolean b(String paramString)
  {
    return false;
  }

  public void c()
  {
  }

  public void c(GeoPoint paramGeoPoint)
  {
  }

  public void c(B paramB)
  {
  }

  public void d()
  {
  }

  public void d(GeoPoint paramGeoPoint)
  {
  }

  public void e()
  {
  }

  public void e(GeoPoint paramGeoPoint)
  {
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.g
 * JD-Core Version:    0.6.2
 */