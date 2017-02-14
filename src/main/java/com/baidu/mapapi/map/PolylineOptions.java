package com.baidu.mapapi.map;

import android.os.Bundle;
import android.util.Log;
import com.baidu.mapapi.model.LatLng;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public final class PolylineOptions extends OverlayOptions
{
  int a;
  boolean b = true;
  boolean c = false;
  Bundle d;
  private int e = -16777216;
  private List<LatLng> f;
  private List<Integer> g;
  private List<Integer> h;
  private int i = 5;
  private BitmapDescriptor j;
  private List<BitmapDescriptor> k;
  private boolean l = true;
  private boolean m = false;

  Overlay a()
  {
    int i1 = 0;
    Polyline localPolyline = new Polyline();
    localPolyline.s = this.b;
    localPolyline.f = this.c;
    localPolyline.r = this.a;
    localPolyline.t = this.d;
    if ((this.f == null) || (this.f.size() < 2))
      throw new IllegalStateException("when you add polyline, you must at least supply 2 points");
    localPolyline.b = this.f;
    localPolyline.a = this.e;
    localPolyline.e = this.i;
    localPolyline.i = this.j;
    localPolyline.j = this.k;
    localPolyline.g = this.l;
    localPolyline.h = this.m;
    Object localObject;
    if ((this.g != null) && (this.g.size() < this.f.size() - 1))
    {
      localObject = new ArrayList(this.f.size() - 1 - this.g.size());
      this.g.addAll(this.g.size(), (Collection)localObject);
    }
    Iterator localIterator;
    int n;
    if ((this.g != null) && (this.g.size() > 0))
    {
      localObject = new int[this.g.size()];
      localIterator = this.g.iterator();
      n = 0;
      while (localIterator.hasNext())
      {
        localObject[n] = ((Integer)localIterator.next()).intValue();
        n += 1;
      }
      localPolyline.c = ((int[])localObject);
    }
    if ((this.h != null) && (this.h.size() < this.f.size() - 1))
    {
      localObject = new ArrayList(this.f.size() - 1 - this.h.size());
      this.h.addAll(this.h.size(), (Collection)localObject);
    }
    if ((this.h != null) && (this.h.size() > 0))
    {
      localObject = new int[this.h.size()];
      localIterator = this.h.iterator();
      n = i1;
      while (localIterator.hasNext())
      {
        localObject[n] = ((Integer)localIterator.next()).intValue();
        n += 1;
      }
      localPolyline.d = ((int[])localObject);
    }
    return localPolyline;
  }

  public PolylineOptions color(int paramInt)
  {
    this.e = paramInt;
    return this;
  }

  public PolylineOptions colorsValues(List<Integer> paramList)
  {
    if (paramList == null)
      throw new IllegalArgumentException("colors list can not be null");
    if (paramList.contains(null))
      throw new IllegalArgumentException("colors list can not contains null");
    this.h = paramList;
    return this;
  }

  public PolylineOptions customTexture(BitmapDescriptor paramBitmapDescriptor)
  {
    this.j = paramBitmapDescriptor;
    return this;
  }

  public PolylineOptions customTextureList(List<BitmapDescriptor> paramList)
  {
    if (paramList == null)
      throw new IllegalArgumentException("customTexture list can not be null");
    if (paramList.size() == 0)
      Log.e("baidumapsdk", "custom texture list is empty,the texture will not work");
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
      if ((BitmapDescriptor)localIterator.next() == null)
        Log.e("baidumapsdk", "the custom texture item is null,it will be discard");
    this.k = paramList;
    return this;
  }

  public PolylineOptions dottedLine(boolean paramBoolean)
  {
    this.c = paramBoolean;
    return this;
  }

  public PolylineOptions extraInfo(Bundle paramBundle)
  {
    this.d = paramBundle;
    return this;
  }

  public PolylineOptions focus(boolean paramBoolean)
  {
    this.l = paramBoolean;
    return this;
  }

  public int getColor()
  {
    return this.e;
  }

  public BitmapDescriptor getCustomTexture()
  {
    return this.j;
  }

  public List<BitmapDescriptor> getCustomTextureList()
  {
    return this.k;
  }

  public Bundle getExtraInfo()
  {
    return this.d;
  }

  public List<LatLng> getPoints()
  {
    return this.f;
  }

  public List<Integer> getTextureIndexs()
  {
    return this.g;
  }

  public int getWidth()
  {
    return this.i;
  }

  public int getZIndex()
  {
    return this.a;
  }

  public boolean isDottedLine()
  {
    return this.c;
  }

  public boolean isFocus()
  {
    return this.l;
  }

  public boolean isVisible()
  {
    return this.b;
  }

  public PolylineOptions keepScale(boolean paramBoolean)
  {
    this.m = paramBoolean;
    return this;
  }

  public PolylineOptions points(List<LatLng> paramList)
  {
    if (paramList == null)
      throw new IllegalArgumentException("points list can not be null");
    if (paramList.size() < 2)
      throw new IllegalArgumentException("points count can not less than 2");
    if (paramList.contains(null))
      throw new IllegalArgumentException("points list can not contains null");
    this.f = paramList;
    return this;
  }

  public PolylineOptions textureIndex(List<Integer> paramList)
  {
    if (paramList == null)
      throw new IllegalArgumentException("indexs list can not be null");
    if (paramList.contains(null))
      throw new IllegalArgumentException("index list can not contains null");
    this.g = paramList;
    return this;
  }

  public PolylineOptions visible(boolean paramBoolean)
  {
    this.b = paramBoolean;
    return this;
  }

  public PolylineOptions width(int paramInt)
  {
    if (paramInt > 0)
      this.i = paramInt;
    return this;
  }

  public PolylineOptions zIndex(int paramInt)
  {
    this.a = paramInt;
    return this;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.PolylineOptions
 * JD-Core Version:    0.6.2
 */