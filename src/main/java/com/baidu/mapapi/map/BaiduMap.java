package com.baidu.mapapi.map;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.ParcelItem;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.A;
import com.baidu.platform.comapi.map.B;
import com.baidu.platform.comapi.map.C;
import com.baidu.platform.comapi.map.I;
import com.baidu.platform.comapi.map.f;
import com.baidu.platform.comapi.map.g;
import com.baidu.platform.comapi.map.i;
import com.baidu.platform.comapi.map.n;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.microedition.khronos.opengles.GL10;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BaiduMap
{
  public static final int MAP_TYPE_NORMAL = 1;
  public static final int MAP_TYPE_SATELLITE = 2;
  private static final String d = BaiduMap.class.getSimpleName();
  private Lock A = new ReentrantLock();
  private Lock B = new ReentrantLock();
  private InfoWindow C;
  private Marker D;
  private View E;
  private Marker F;
  private MyLocationData G;
  private MyLocationConfiguration H;
  private boolean I;
  MapView a;
  TextureMapView b;
  A c;
  private Projection e;
  private UiSettings f;
  private g g;
  private com.baidu.platform.comapi.map.c h;
  private C i;
  private List<Overlay> j;
  private List<Marker> k;
  private Overlay.a l;
  private OnMapStatusChangeListener m;
  private OnMapTouchListener n;
  private OnMapClickListener o;
  private OnMapLoadedCallback p;
  private OnMapDoubleClickListener q;
  private OnMapLongClickListener r;
  private CopyOnWriteArrayList<OnMarkerClickListener> s = new CopyOnWriteArrayList();
  private CopyOnWriteArrayList<OnPolylineClickListener> t = new CopyOnWriteArrayList();
  private OnMarkerDragListener u;
  private OnMyLocationClickListener v;
  private SnapshotReadyCallback w;
  private OnMapDrawFrameCallback x;
  private TileOverlay y;
  private HeatMap z;

  BaiduMap(C paramC)
  {
    this.i = paramC;
    this.h = this.i.b();
    this.c = A.b;
    c();
  }

  BaiduMap(g paramg)
  {
    this.g = paramg;
    this.h = this.g.a();
    this.c = A.a;
    c();
  }

  private B a(MapStatusUpdate paramMapStatusUpdate)
  {
    B localB = this.h.v();
    return paramMapStatusUpdate.a(this.h, getMapStatus()).b(localB);
  }

  private final void a(MyLocationData paramMyLocationData, MyLocationConfiguration paramMyLocationConfiguration)
  {
    int i1 = 0;
    if ((paramMyLocationData == null) || (paramMyLocationConfiguration == null) || (!isMyLocationEnabled()))
      return;
    JSONObject localJSONObject = new JSONObject();
    JSONArray localJSONArray = new JSONArray();
    Object localObject1 = new JSONObject();
    Object localObject2 = new JSONObject();
    Object localObject3 = new LatLng(paramMyLocationData.latitude, paramMyLocationData.longitude);
    Object localObject4 = CoordUtil.ll2mc((LatLng)localObject3);
    try
    {
      localJSONObject.put("type", 0);
      ((JSONObject)localObject1).put("ptx", ((GeoPoint)localObject4).getLongitudeE6());
      ((JSONObject)localObject1).put("pty", ((GeoPoint)localObject4).getLatitudeE6());
      ((JSONObject)localObject1).put("radius", CoordUtil.getMCDistanceByOneLatLngAndRadius((LatLng)localObject3, (int)paramMyLocationData.accuracy));
      float f1 = paramMyLocationData.direction;
      float f2;
      if (paramMyLocationConfiguration.enableDirection)
      {
        f2 = paramMyLocationData.direction % 360.0F;
        if (f2 > 180.0F)
          f1 = f2 - 360.0F;
      }
      while (true)
      {
        ((JSONObject)localObject1).put("direction", f1);
        ((JSONObject)localObject1).put("iconarrownor", "NormalLocArrow");
        ((JSONObject)localObject1).put("iconarrownorid", 28);
        ((JSONObject)localObject1).put("iconarrowfoc", "FocusLocArrow");
        ((JSONObject)localObject1).put("iconarrowfocid", 29);
        localJSONArray.put(localObject1);
        localJSONObject.put("data", localJSONArray);
        if (paramMyLocationConfiguration.locationMode == MyLocationConfiguration.LocationMode.COMPASS)
        {
          ((JSONObject)localObject2).put("ptx", ((GeoPoint)localObject4).getLongitudeE6());
          ((JSONObject)localObject2).put("pty", ((GeoPoint)localObject4).getLatitudeE6());
          ((JSONObject)localObject2).put("radius", 0);
          ((JSONObject)localObject2).put("direction", 0);
          ((JSONObject)localObject2).put("iconarrownor", "direction_wheel");
          ((JSONObject)localObject2).put("iconarrownorid", 54);
          ((JSONObject)localObject2).put("iconarrowfoc", "direction_wheel");
          ((JSONObject)localObject2).put("iconarrowfocid", 54);
          localJSONArray.put(localObject2);
        }
        if (paramMyLocationConfiguration.customMarker != null)
          break;
        localJSONArray = null;
        if (this.h != null)
          this.h.a(localJSONObject.toString(), localJSONArray);
        switch (1.a[paramMyLocationConfiguration.locationMode.ordinal()])
        {
        case 3:
        default:
          return;
        case 1:
          animateMapStatus(MapStatusUpdateFactory.newMapStatus(new MapStatus.Builder().rotate(paramMyLocationData.direction).overlook(-45.0F).target(new LatLng(paramMyLocationData.latitude, paramMyLocationData.longitude)).targetScreen(getMapStatus().targetScreen).zoom(getMapStatus().zoom).build()));
          return;
          f1 = f2;
          if (f2 < -180.0F)
          {
            f1 = f2 + 360.0F;
            continue;
            f1 = -1.0F;
          }
          break;
        case 2:
        }
      }
    }
    catch (JSONException localJSONException)
    {
      while (true)
      {
        localJSONException.printStackTrace();
        continue;
        localObject2 = new ArrayList();
        ((List)localObject2).add(paramMyLocationConfiguration.customMarker);
        Bundle localBundle1 = new Bundle();
        localObject1 = new ArrayList();
        localObject2 = ((List)localObject2).iterator();
        while (((Iterator)localObject2).hasNext())
        {
          localObject3 = (BitmapDescriptor)((Iterator)localObject2).next();
          localObject4 = new ParcelItem();
          Bundle localBundle2 = new Bundle();
          Bitmap localBitmap = ((BitmapDescriptor)localObject3).a;
          ByteBuffer localByteBuffer = ByteBuffer.allocate(localBitmap.getWidth() * localBitmap.getHeight() * 4);
          localBitmap.copyPixelsToBuffer(localByteBuffer);
          localBundle2.putByteArray("imgdata", localByteBuffer.array());
          localBundle2.putInt("imgindex", localObject3.hashCode());
          localBundle2.putInt("imgH", localBitmap.getHeight());
          localBundle2.putInt("imgW", localBitmap.getWidth());
          ((ParcelItem)localObject4).setBundle(localBundle2);
          ((ArrayList)localObject1).add(localObject4);
        }
        if (((ArrayList)localObject1).size() > 0)
        {
          localObject2 = new ParcelItem[((ArrayList)localObject1).size()];
          while (i1 < ((ArrayList)localObject1).size())
          {
            localObject2[i1] = ((ParcelItem)((ArrayList)localObject1).get(i1));
            i1 += 1;
          }
          localBundle1.putParcelableArray("icondata", (Parcelable[])localObject2);
        }
      }
      animateMapStatus(MapStatusUpdateFactory.newMapStatus(new MapStatus.Builder().target(new LatLng(paramMyLocationData.latitude, paramMyLocationData.longitude)).zoom(getMapStatus().zoom).rotate(getMapStatus().rotate).overlook(getMapStatus().overlook).targetScreen(getMapStatus().targetScreen).build()));
    }
  }

  private void c()
  {
    this.j = new CopyOnWriteArrayList();
    this.k = new CopyOnWriteArrayList();
    this.f = new UiSettings(this.h);
    this.l = new a(this);
    Object localObject = new b(this);
    this.h.a((i)localObject);
    localObject = new c(this);
    this.h.a((n)localObject);
    localObject = new d(this);
    this.h.a((I)localObject);
  }

  void a()
  {
    this.h.o();
  }

  void a(HeatMap paramHeatMap)
  {
    this.A.lock();
    try
    {
      if ((this.z != null) && (paramHeatMap == this.z))
      {
        this.z.b();
        this.z.c();
        this.z.a = null;
        this.h.l();
        this.z = null;
        this.h.i(false);
      }
      return;
    }
    finally
    {
      this.A.unlock();
    }
    throw paramHeatMap;
  }

  void a(TileOverlay paramTileOverlay)
  {
    this.B.lock();
    if (paramTileOverlay != null);
    try
    {
      if (this.y == paramTileOverlay)
      {
        paramTileOverlay.clearTileCache();
        paramTileOverlay.b();
        paramTileOverlay.a = null;
        if (this.h != null)
          this.h.c(false);
      }
      return;
    }
    finally
    {
      this.y = null;
      this.B.unlock();
    }
    throw paramTileOverlay;
  }

  public void addHeatMap(HeatMap paramHeatMap)
  {
    if (paramHeatMap == null)
      return;
    this.A.lock();
    try
    {
      HeatMap localHeatMap = this.z;
      if (paramHeatMap == localHeatMap)
        return;
      if (this.z != null)
      {
        this.z.b();
        this.z.c();
        this.z.a = null;
        this.h.l();
      }
      this.z = paramHeatMap;
      this.z.a = this;
      this.h.i(true);
      return;
    }
    finally
    {
      this.A.unlock();
    }
    throw paramHeatMap;
  }

  public final Overlay addOverlay(OverlayOptions paramOverlayOptions)
  {
    if (paramOverlayOptions == null)
      return null;
    paramOverlayOptions = paramOverlayOptions.a();
    paramOverlayOptions.listener = this.l;
    if ((paramOverlayOptions instanceof Marker))
    {
      localObject = (Marker)paramOverlayOptions;
      if ((((Marker)localObject).n != null) && (((Marker)localObject).n.size() != 0))
      {
        this.k.add(localObject);
        if (this.h != null)
          this.h.b(true);
      }
    }
    Object localObject = new Bundle();
    paramOverlayOptions.a((Bundle)localObject);
    if (this.h != null)
      this.h.b((Bundle)localObject);
    this.j.add(paramOverlayOptions);
    return paramOverlayOptions;
  }

  public TileOverlay addTileLayer(TileOverlayOptions paramTileOverlayOptions)
  {
    if (paramTileOverlayOptions == null);
    do
    {
      return null;
      if (this.y != null)
      {
        this.y.b();
        this.y.clearTileCache();
        this.y.a = null;
      }
    }
    while ((this.h == null) || (!this.h.a(paramTileOverlayOptions.a())));
    paramTileOverlayOptions = paramTileOverlayOptions.a(this);
    this.y = paramTileOverlayOptions;
    return paramTileOverlayOptions;
  }

  public final void animateMapStatus(MapStatusUpdate paramMapStatusUpdate)
  {
    animateMapStatus(paramMapStatusUpdate, 300);
  }

  public final void animateMapStatus(MapStatusUpdate paramMapStatusUpdate, int paramInt)
  {
    if ((paramMapStatusUpdate == null) || (paramInt <= 0));
    do
    {
      return;
      paramMapStatusUpdate = a(paramMapStatusUpdate);
    }
    while (this.h == null);
    if (!this.I)
    {
      this.h.a(paramMapStatusUpdate);
      return;
    }
    this.h.a(paramMapStatusUpdate, paramInt);
  }

  boolean b()
  {
    if (this.h == null)
      return false;
    return this.h.d();
  }

  public final void clear()
  {
    this.j.clear();
    this.k.clear();
    if (this.h != null)
    {
      this.h.b(false);
      this.h.k();
    }
    hideInfoWindow();
  }

  public final MyLocationConfiguration getLocationConfigeration()
  {
    return this.H;
  }

  public final MyLocationData getLocationData()
  {
    return this.G;
  }

  public final MapStatus getMapStatus()
  {
    if (this.h == null)
      return null;
    return MapStatus.a(this.h.v());
  }

  public final int getMapType()
  {
    if (this.h == null);
    while (!this.h.i())
      return 1;
    return 2;
  }

  public final float getMaxZoomLevel()
  {
    if (this.h == null)
      return 0.0F;
    return this.h.a;
  }

  public final float getMinZoomLevel()
  {
    if (this.h != null)
      return 0.0F;
    return this.h.b;
  }

  public final Projection getProjection()
  {
    return this.e;
  }

  public final UiSettings getUiSettings()
  {
    return this.f;
  }

  public void hideInfoWindow()
  {
    if (this.C != null)
    {
      if (this.C.b != null)
      {
        if (this.a != null)
          this.a.removeView(this.E);
        this.E = null;
      }
      this.C = null;
      this.D.remove();
      this.D = null;
    }
  }

  public final boolean isBaiduHeatMapEnabled()
  {
    if (this.h == null)
      return false;
    return this.h.g();
  }

  public final boolean isBuildingsEnabled()
  {
    if (this.h == null)
      return false;
    return this.h.j();
  }

  public final boolean isMyLocationEnabled()
  {
    if (this.h == null)
      return false;
    return this.h.n();
  }

  public final boolean isSupportBaiduHeatMap()
  {
    if (this.h == null)
      return false;
    return this.h.h();
  }

  public final boolean isTrafficEnabled()
  {
    if (this.h == null)
      return false;
    return this.h.f();
  }

  public final void removeMarkerClickListener(OnMarkerClickListener paramOnMarkerClickListener)
  {
    if (this.s.contains(paramOnMarkerClickListener))
      this.s.remove(paramOnMarkerClickListener);
  }

  public final void setBaiduHeatMapEnabled(boolean paramBoolean)
  {
    if (this.h != null)
      this.h.d(paramBoolean);
  }

  public final void setBuildingsEnabled(boolean paramBoolean)
  {
    if (this.h != null)
      this.h.f(paramBoolean);
  }

  public final void setMapStatus(MapStatusUpdate paramMapStatusUpdate)
  {
    if (paramMapStatusUpdate == null);
    do
    {
      do
      {
        return;
        paramMapStatusUpdate = a(paramMapStatusUpdate);
      }
      while (this.h == null);
      this.h.a(paramMapStatusUpdate);
    }
    while (this.m == null);
    this.m.onMapStatusChange(getMapStatus());
  }

  public final void setMapType(int paramInt)
  {
    if (this.h == null);
    do
    {
      return;
      if (paramInt == 1)
      {
        this.h.a(false);
        return;
      }
    }
    while (paramInt != 2);
    this.h.a(true);
  }

  public final void setMaxAndMinZoomLevel(float paramFloat1, float paramFloat2)
  {
    if (paramFloat1 > 20.0F);
    while ((paramFloat2 < 3.0F) || (paramFloat1 < paramFloat2) || (this.h == null))
      return;
    this.h.a = paramFloat1;
    this.h.b = paramFloat2;
  }

  public final void setMyLocationConfigeration(MyLocationConfiguration paramMyLocationConfiguration)
  {
    this.H = paramMyLocationConfiguration;
    a(this.G, this.H);
  }

  public final void setMyLocationData(MyLocationData paramMyLocationData)
  {
    this.G = paramMyLocationData;
    if (this.H == null)
      this.H = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL, false, null);
    a(paramMyLocationData, this.H);
  }

  public final void setMyLocationEnabled(boolean paramBoolean)
  {
    if (this.h != null)
      this.h.h(paramBoolean);
  }

  public final void setOnMapClickListener(OnMapClickListener paramOnMapClickListener)
  {
    this.o = paramOnMapClickListener;
  }

  public final void setOnMapDoubleClickListener(OnMapDoubleClickListener paramOnMapDoubleClickListener)
  {
    this.q = paramOnMapDoubleClickListener;
  }

  public final void setOnMapDrawFrameCallback(OnMapDrawFrameCallback paramOnMapDrawFrameCallback)
  {
    this.x = paramOnMapDrawFrameCallback;
  }

  public void setOnMapLoadedCallback(OnMapLoadedCallback paramOnMapLoadedCallback)
  {
    this.p = paramOnMapLoadedCallback;
  }

  public final void setOnMapLongClickListener(OnMapLongClickListener paramOnMapLongClickListener)
  {
    this.r = paramOnMapLongClickListener;
  }

  public final void setOnMapStatusChangeListener(OnMapStatusChangeListener paramOnMapStatusChangeListener)
  {
    this.m = paramOnMapStatusChangeListener;
  }

  public final void setOnMapTouchListener(OnMapTouchListener paramOnMapTouchListener)
  {
    this.n = paramOnMapTouchListener;
  }

  public final void setOnMarkerClickListener(OnMarkerClickListener paramOnMarkerClickListener)
  {
    if ((paramOnMarkerClickListener != null) && (!this.s.contains(paramOnMarkerClickListener)))
      this.s.add(paramOnMarkerClickListener);
  }

  public final void setOnMarkerDragListener(OnMarkerDragListener paramOnMarkerDragListener)
  {
    this.u = paramOnMarkerDragListener;
  }

  public final void setOnMyLocationClickListener(OnMyLocationClickListener paramOnMyLocationClickListener)
  {
    this.v = paramOnMyLocationClickListener;
  }

  public final void setOnPolylineClickListener(OnPolylineClickListener paramOnPolylineClickListener)
  {
    if (paramOnPolylineClickListener != null)
      this.t.add(paramOnPolylineClickListener);
  }

  public final void setTrafficEnabled(boolean paramBoolean)
  {
    if (this.h != null)
      this.h.e(paramBoolean);
  }

  public void showInfoWindow(InfoWindow paramInfoWindow)
  {
    if (paramInfoWindow != null)
    {
      hideInfoWindow();
      if (paramInfoWindow.b != null)
      {
        this.E = paramInfoWindow.b;
        this.E.destroyDrawingCache();
        localObject = new MapViewLayoutParams.Builder().layoutMode(MapViewLayoutParams.ELayoutMode.mapMode).position(paramInfoWindow.c).yOffset(paramInfoWindow.e).build();
        if (this.a != null)
          this.a.addView(this.E, (ViewGroup.LayoutParams)localObject);
      }
      this.C = paramInfoWindow;
      if (paramInfoWindow.b == null)
        break label203;
    }
    label203: for (Object localObject = BitmapDescriptorFactory.fromView(paramInfoWindow.b); ; localObject = paramInfoWindow.a)
    {
      paramInfoWindow = new MarkerOptions().perspective(false).icon((BitmapDescriptor)localObject).position(paramInfoWindow.c).zIndex(2147483647).a(paramInfoWindow.e).a();
      paramInfoWindow.listener = this.l;
      paramInfoWindow.q = f.b;
      localObject = new Bundle();
      paramInfoWindow.a((Bundle)localObject);
      if (this.h != null)
        this.h.b((Bundle)localObject);
      this.j.add(paramInfoWindow);
      this.D = ((Marker)paramInfoWindow);
      return;
    }
  }

  public final void showMapPoi(boolean paramBoolean)
  {
    if (this.h != null)
      this.h.n(paramBoolean);
  }

  public final void snapshot(SnapshotReadyCallback paramSnapshotReadyCallback)
  {
    this.w = paramSnapshotReadyCallback;
    switch (1.b[this.c.ordinal()])
    {
    default:
    case 1:
    case 2:
    }
    do
    {
      do
        return;
      while (this.i == null);
      this.i.a("anything", null);
      return;
    }
    while (this.g == null);
    this.g.a("anything", null);
  }

  public final void snapshotScope(Rect paramRect, SnapshotReadyCallback paramSnapshotReadyCallback)
  {
    this.w = paramSnapshotReadyCallback;
    switch (1.b[this.c.ordinal()])
    {
    default:
    case 1:
    case 2:
    }
    do
    {
      do
        return;
      while (this.i == null);
      this.i.a("anything", paramRect);
      return;
    }
    while (this.g == null);
    this.g.a("anything", paramRect);
  }

  public static abstract interface OnMapClickListener
  {
    public abstract void onMapClick(LatLng paramLatLng);

    public abstract boolean onMapPoiClick(MapPoi paramMapPoi);
  }

  public static abstract interface OnMapDoubleClickListener
  {
    public abstract void onMapDoubleClick(LatLng paramLatLng);
  }

  public static abstract interface OnMapDrawFrameCallback
  {
    public abstract void onMapDrawFrame(GL10 paramGL10, MapStatus paramMapStatus);
  }

  public static abstract interface OnMapLoadedCallback
  {
    public abstract void onMapLoaded();
  }

  public static abstract interface OnMapLongClickListener
  {
    public abstract void onMapLongClick(LatLng paramLatLng);
  }

  public static abstract interface OnMapStatusChangeListener
  {
    public abstract void onMapStatusChange(MapStatus paramMapStatus);

    public abstract void onMapStatusChangeFinish(MapStatus paramMapStatus);

    public abstract void onMapStatusChangeStart(MapStatus paramMapStatus);
  }

  public static abstract interface OnMapTouchListener
  {
    public abstract void onTouch(MotionEvent paramMotionEvent);
  }

  public static abstract interface OnMarkerClickListener
  {
    public abstract boolean onMarkerClick(Marker paramMarker);
  }

  public static abstract interface OnMarkerDragListener
  {
    public abstract void onMarkerDrag(Marker paramMarker);

    public abstract void onMarkerDragEnd(Marker paramMarker);

    public abstract void onMarkerDragStart(Marker paramMarker);
  }

  public static abstract interface OnMyLocationClickListener
  {
    public abstract boolean onMyLocationClick();
  }

  public static abstract interface OnPolylineClickListener
  {
    public abstract boolean onPolylineClick(Polyline paramPolyline);
  }

  public static abstract interface SnapshotReadyCallback
  {
    public abstract void onSnapshotReady(Bitmap paramBitmap);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.BaiduMap
 * JD-Core Version:    0.6.2
 */