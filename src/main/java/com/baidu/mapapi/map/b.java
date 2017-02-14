package com.baidu.mapapi.map;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.B;
import com.baidu.platform.comapi.map.c;
import com.baidu.platform.comapi.map.i;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import javax.microedition.khronos.opengles.GL10;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class b
  implements i
{
  b(BaiduMap paramBaiduMap)
  {
  }

  public void a()
  {
  }

  public void a(Bitmap paramBitmap)
  {
    if (BaiduMap.k(this.a) != null)
      BaiduMap.k(this.a).onSnapshotReady(paramBitmap);
  }

  public void a(MotionEvent paramMotionEvent)
  {
    if (BaiduMap.f(this.a) != null)
      BaiduMap.f(this.a).onTouch(paramMotionEvent);
  }

  public void a(GeoPoint paramGeoPoint)
  {
    if (BaiduMap.g(this.a) != null)
    {
      paramGeoPoint = CoordUtil.mc2ll(paramGeoPoint);
      BaiduMap.g(this.a).onMapClick(paramGeoPoint);
    }
  }

  public void a(B paramB)
  {
    if (BaiduMap.d(this.a) != null)
      BaiduMap.d(this.a).setVisibility(4);
    if (BaiduMap.e(this.a) != null)
    {
      paramB = MapStatus.a(paramB);
      BaiduMap.e(this.a).onMapStatusChangeStart(paramB);
    }
  }

  public void a(String paramString)
  {
    int i;
    Object localObject1;
    try
    {
      paramString = new JSONObject(paramString).optJSONArray("dataset").optJSONObject(0);
      i = paramString.optInt("ty");
      if (i == 17)
      {
        if (BaiduMap.g(this.a) == null)
          return;
        localObject1 = new MapPoi();
        ((MapPoi)localObject1).a(paramString);
        BaiduMap.g(this.a).onMapPoiClick((MapPoi)localObject1);
        return;
      }
      if (i == 18)
      {
        if (BaiduMap.l(this.a) == null)
          return;
        BaiduMap.l(this.a).onMyLocationClick();
        return;
      }
    }
    catch (JSONException paramString)
    {
      paramString.printStackTrace();
      return;
    }
    if (i == 19)
    {
      if (BaiduMap.b(this.a) != null)
      {
        paramString = BaiduMap.b(this.a).v();
        paramString.c = 0;
        paramString.b = 0;
        BaiduMap.b(this.a).a(paramString, 300);
      }
    }
    else
    {
      Object localObject2;
      if (i == 90909)
      {
        localObject1 = paramString.optString("marker_id");
        if ((BaiduMap.m(this.a) != null) && (((String)localObject1).equals(BaiduMap.n(this.a).p)))
        {
          paramString = BaiduMap.m(this.a).d;
          if (paramString != null)
            paramString.onInfoWindowClick();
        }
        else
        {
          localObject2 = BaiduMap.a(this.a).iterator();
          do
          {
            if (!((Iterator)localObject2).hasNext())
              break;
            paramString = (Overlay)((Iterator)localObject2).next();
          }
          while ((!(paramString instanceof Marker)) || (!paramString.p.equals(localObject1)) || (BaiduMap.o(this.a) == null));
          localObject1 = BaiduMap.o(this.a).iterator();
          while (((Iterator)localObject1).hasNext())
            ((BaiduMap.OnMarkerClickListener)((Iterator)localObject1).next()).onMarkerClick((Marker)paramString);
        }
      }
      else if (i == 90910)
      {
        paramString = paramString.optString("polyline_id");
        localObject1 = BaiduMap.a(this.a).iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (Overlay)((Iterator)localObject1).next();
          if (((localObject2 instanceof Polyline)) && (((Overlay)localObject2).p.equals(paramString)) && (BaiduMap.p(this.a) != null))
          {
            Iterator localIterator = BaiduMap.p(this.a).iterator();
            while (localIterator.hasNext())
              ((BaiduMap.OnPolylineClickListener)localIterator.next()).onPolylineClick((Polyline)localObject2);
          }
        }
      }
    }
  }

  public void a(GL10 paramGL10, B paramB)
  {
    if (BaiduMap.v(this.a) != null)
    {
      paramB = MapStatus.a(paramB);
      BaiduMap.v(this.a).onMapDrawFrame(paramGL10, paramB);
    }
  }

  public void b()
  {
    BaiduMap.a(this.a, new Projection(BaiduMap.b(this.a)));
    BaiduMap.a(this.a, true);
    if (BaiduMap.j(this.a) != null)
      BaiduMap.j(this.a).onMapLoaded();
  }

  public void b(GeoPoint paramGeoPoint)
  {
    if (BaiduMap.h(this.a) != null)
    {
      paramGeoPoint = CoordUtil.mc2ll(paramGeoPoint);
      BaiduMap.h(this.a).onMapDoubleClick(paramGeoPoint);
    }
  }

  public void b(B paramB)
  {
    if (BaiduMap.e(this.a) != null)
    {
      paramB = MapStatus.a(paramB);
      BaiduMap.e(this.a).onMapStatusChange(paramB);
    }
  }

  public boolean b(String paramString)
  {
    try
    {
      paramString = new JSONObject(paramString).optJSONArray("dataset").optJSONObject(0);
      if (paramString.optInt("ty") == 90909)
      {
        paramString = paramString.optString("marker_id");
        if ((BaiduMap.n(this.a) == null) || (!paramString.equals(BaiduMap.n(this.a).p)))
        {
          Iterator localIterator = BaiduMap.a(this.a).iterator();
          while (localIterator.hasNext())
          {
            Overlay localOverlay = (Overlay)localIterator.next();
            if (((localOverlay instanceof Marker)) && (localOverlay.p.equals(paramString)))
            {
              paramString = (Marker)localOverlay;
              if (paramString.f)
              {
                BaiduMap.a(this.a, paramString);
                paramString = BaiduMap.r(this.a).toScreenLocation(BaiduMap.q(this.a).a);
                paramString = new Point(paramString.x, paramString.y - 60);
                paramString = BaiduMap.r(this.a).fromScreenLocation(paramString);
                BaiduMap.q(this.a).setPosition(paramString);
                if (BaiduMap.s(this.a) != null)
                  BaiduMap.s(this.a).onMarkerDragStart(BaiduMap.q(this.a));
                return true;
              }
            }
          }
        }
      }
    }
    catch (JSONException paramString)
    {
      paramString.printStackTrace();
    }
    return false;
  }

  public void c()
  {
    BaiduMap.t(this.a).lock();
    try
    {
      if (BaiduMap.u(this.a) != null)
        BaiduMap.u(this.a).a();
      return;
    }
    finally
    {
      BaiduMap.t(this.a).unlock();
    }
  }

  public void c(GeoPoint paramGeoPoint)
  {
    if (BaiduMap.i(this.a) != null)
    {
      paramGeoPoint = CoordUtil.mc2ll(paramGeoPoint);
      BaiduMap.i(this.a).onMapLongClick(paramGeoPoint);
    }
  }

  public void c(B paramB)
  {
    if (BaiduMap.d(this.a) != null)
      BaiduMap.d(this.a).setVisibility(0);
    if (BaiduMap.e(this.a) != null)
    {
      paramB = MapStatus.a(paramB);
      BaiduMap.e(this.a).onMapStatusChangeFinish(paramB);
    }
  }

  public void d()
  {
    BaiduMap.t(this.a).lock();
    try
    {
      if (BaiduMap.u(this.a) != null)
      {
        BaiduMap.u(this.a).a();
        BaiduMap.b(this.a).l();
      }
      return;
    }
    finally
    {
      BaiduMap.t(this.a).unlock();
    }
  }

  public void d(GeoPoint paramGeoPoint)
  {
    if ((BaiduMap.q(this.a) != null) && (BaiduMap.q(this.a).f))
    {
      paramGeoPoint = CoordUtil.mc2ll(paramGeoPoint);
      paramGeoPoint = BaiduMap.r(this.a).toScreenLocation(paramGeoPoint);
      paramGeoPoint = new Point(paramGeoPoint.x, paramGeoPoint.y - 60);
      paramGeoPoint = BaiduMap.r(this.a).fromScreenLocation(paramGeoPoint);
      BaiduMap.q(this.a).setPosition(paramGeoPoint);
      if ((BaiduMap.s(this.a) != null) && (BaiduMap.q(this.a).f))
        BaiduMap.s(this.a).onMarkerDrag(BaiduMap.q(this.a));
    }
  }

  public void e()
  {
    BaiduMap.b(this.a).b(false);
    BaiduMap.t(this.a).lock();
    try
    {
      if (BaiduMap.u(this.a) != null)
        this.a.a(BaiduMap.u(this.a));
      return;
    }
    finally
    {
      BaiduMap.t(this.a).unlock();
    }
  }

  public void e(GeoPoint paramGeoPoint)
  {
    if ((BaiduMap.q(this.a) != null) && (BaiduMap.q(this.a).f))
    {
      paramGeoPoint = CoordUtil.mc2ll(paramGeoPoint);
      paramGeoPoint = BaiduMap.r(this.a).toScreenLocation(paramGeoPoint);
      paramGeoPoint = new Point(paramGeoPoint.x, paramGeoPoint.y - 60);
      paramGeoPoint = BaiduMap.r(this.a).fromScreenLocation(paramGeoPoint);
      BaiduMap.q(this.a).setPosition(paramGeoPoint);
      if ((BaiduMap.s(this.a) != null) && (BaiduMap.q(this.a).f))
        BaiduMap.s(this.a).onMarkerDragEnd(BaiduMap.q(this.a));
      BaiduMap.a(this.a, null);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.b
 * JD-Core Version:    0.6.2
 */