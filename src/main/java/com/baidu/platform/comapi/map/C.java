package com.baidu.platform.comapi.map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import com.baidu.mapapi.common.EnvironmentUtilities;
import com.baidu.mapapi.common.SysOSUtil;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comjni.map.basemap.a;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@SuppressLint({"NewApi"})
public class C extends TextureView
  implements GestureDetector.OnDoubleTapListener, GestureDetector.OnGestureListener, TextureView.SurfaceTextureListener, j.a
{
  public static int a;
  public static int b;
  private GestureDetector c;
  private Handler d;
  private j e = null;
  private c f;

  public C(Context paramContext, z paramz)
  {
    super(paramContext);
    a(paramContext, paramz);
  }

  private void a(Context paramContext, z paramz)
  {
    setSurfaceTextureListener(this);
    if (paramContext == null)
      throw new RuntimeException("when you create an mapview, the context can not be null");
    this.c = new GestureDetector(paramContext, this);
    EnvironmentUtilities.initAppDirectory(paramContext);
    if (this.f == null)
      this.f = new c(paramContext);
    this.f.a();
    this.f.b();
    this.f.a(paramz);
    f();
    this.f.a(this.d);
    this.f.e();
  }

  private void f()
  {
    this.d = new D(this);
  }

  public int a()
  {
    return MapRenderer.nativeRender(this.f.g);
  }

  public void a(String paramString, Rect paramRect)
  {
    if (this.f.f == null);
    label178: 
    do
    {
      do
      {
        return;
        if (paramRect == null)
          break;
        int n = paramRect.left;
        if (b < paramRect.bottom);
        int k;
        int j;
        for (int i = 0; ; i = b - paramRect.bottom)
        {
          k = paramRect.width();
          int m = paramRect.height();
          if ((n < 0) || (i < 0) || (k <= 0) || (m <= 0))
            break;
          j = k;
          if (k > a)
            j = Math.abs(paramRect.width()) - (paramRect.right - a);
          k = m;
          if (m > b)
            k = Math.abs(paramRect.height()) - (paramRect.bottom - b);
          if ((n <= SysOSUtil.GetScreenSizeX()) && (i <= SysOSUtil.GetScreenSizeY()))
            break label178;
          this.f.f.a(paramString, null);
          if (this.e == null)
            break;
          this.e.a();
          return;
        }
        a = j;
        b = k;
        paramRect = new Bundle();
        paramRect.putInt("x", n);
        paramRect.putInt("y", i);
        paramRect.putInt("width", j);
        paramRect.putInt("height", k);
        this.f.f.a(paramString, paramRect);
      }
      while (this.e == null);
      this.e.a();
      return;
      this.f.f.a(paramString, null);
    }
    while (this.e == null);
    this.e.a();
  }

  public c b()
  {
    return this.f;
  }

  public void c()
  {
    Iterator localIterator = this.f.e.iterator();
    while (localIterator.hasNext())
      ((i)localIterator.next()).c();
    this.f.f.f();
    this.f.f.e();
    this.f.f.l();
    if (this.e != null)
      this.e.a();
  }

  public void d()
  {
    this.f.f.d();
    if (this.e != null)
      this.e.b();
  }

  public void e()
  {
    if (this.e != null)
    {
      this.e.c();
      this.e = null;
    }
    Iterator localIterator = this.f.e.iterator();
    while (localIterator.hasNext())
      ((i)localIterator.next()).e();
    if (this.f != null)
    {
      this.f.b(this.d);
      this.f.D();
      this.f = null;
    }
  }

  public boolean onDoubleTap(MotionEvent paramMotionEvent)
  {
    if ((this.f == null) || (this.f.f == null) || (!this.f.h))
      return true;
    paramMotionEvent = this.f.b((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY());
    if (paramMotionEvent != null)
    {
      Object localObject = this.f.e.iterator();
      while (((Iterator)localObject).hasNext())
        ((i)((Iterator)localObject).next()).b(paramMotionEvent);
      if (this.f.d)
      {
        localObject = this.f.v();
        ((B)localObject).a += 1.0F;
        ((B)localObject).d = paramMotionEvent.getLongitudeE6();
        ((B)localObject).e = paramMotionEvent.getLatitudeE6();
        this.f.a((B)localObject, 300);
        paramMotionEvent = this.f;
        c.j = System.currentTimeMillis();
        return true;
      }
      return false;
    }
    return false;
  }

  public boolean onDoubleTapEvent(MotionEvent paramMotionEvent)
  {
    return false;
  }

  public boolean onDown(MotionEvent paramMotionEvent)
  {
    return false;
  }

  public boolean onFling(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
  {
    boolean bool2 = false;
    boolean bool1;
    if ((this.f == null) || (this.f.f == null) || (!this.f.h))
      bool1 = true;
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      }
      while (!this.f.c);
      paramFloat1 = (float)Math.sqrt(paramFloat1 * paramFloat1 + paramFloat2 * paramFloat2);
      bool1 = bool2;
    }
    while (paramFloat1 <= 500.0F);
    this.f.t();
    this.f.a(34, (int)(paramFloat1 * 0.6F), (int)paramMotionEvent2.getY() << 16 | (int)paramMotionEvent2.getX());
    this.f.C();
    return true;
  }

  public void onLongPress(MotionEvent paramMotionEvent)
  {
    if ((this.f == null) || (this.f.f == null) || (!this.f.h));
    while (true)
    {
      return;
      Object localObject = this.f.f.a(-1, (int)paramMotionEvent.getX(), (int)paramMotionEvent.getY(), this.f.i);
      if ((localObject != null) && (!((String)localObject).equals("")))
      {
        Iterator localIterator = this.f.e.iterator();
        while (localIterator.hasNext())
        {
          i locali = (i)localIterator.next();
          if (locali.b((String)localObject))
            this.f.m = true;
          else
            locali.c(this.f.b((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY()));
        }
      }
      else
      {
        localObject = this.f.e.iterator();
        while (((Iterator)localObject).hasNext())
          ((i)((Iterator)localObject).next()).c(this.f.b((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY()));
      }
    }
  }

  public boolean onScroll(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
  {
    return false;
  }

  public void onShowPress(MotionEvent paramMotionEvent)
  {
  }

  public boolean onSingleTapConfirmed(MotionEvent paramMotionEvent)
  {
    if ((this.f == null) || (this.f.f == null) || (!this.f.h));
    while (true)
    {
      return true;
      Object localObject = this.f.f.a(-1, (int)paramMotionEvent.getX(), (int)paramMotionEvent.getY(), this.f.i);
      if ((localObject != null) && (!((String)localObject).equals("")))
      {
        paramMotionEvent = this.f.e.iterator();
        while (paramMotionEvent.hasNext())
          ((i)paramMotionEvent.next()).a((String)localObject);
      }
      else
      {
        localObject = this.f.e.iterator();
        while (((Iterator)localObject).hasNext())
          ((i)((Iterator)localObject).next()).a(this.f.b((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY()));
      }
    }
  }

  public boolean onSingleTapUp(MotionEvent paramMotionEvent)
  {
    return false;
  }

  public void onSurfaceTextureAvailable(SurfaceTexture paramSurfaceTexture, int paramInt1, int paramInt2)
  {
    if (this.f == null)
      return;
    this.e = new j(paramSurfaceTexture, this, new AtomicBoolean(true), this);
    this.e.start();
    a = paramInt1;
    b = paramInt2;
    paramSurfaceTexture = this.f.v();
    if ((paramSurfaceTexture.f == 0) || (paramSurfaceTexture.f == -1) || (paramSurfaceTexture.f == (paramSurfaceTexture.j.a - paramSurfaceTexture.j.b) / 2))
      paramSurfaceTexture.f = -1;
    if ((paramSurfaceTexture.g == 0) || (paramSurfaceTexture.g == -1) || (paramSurfaceTexture.g == (paramSurfaceTexture.j.d - paramSurfaceTexture.j.c) / 2))
      paramSurfaceTexture.g = -1;
    paramSurfaceTexture.j.a = 0;
    paramSurfaceTexture.j.c = 0;
    paramSurfaceTexture.j.d = paramInt2;
    paramSurfaceTexture.j.b = paramInt1;
    this.f.a(paramSurfaceTexture);
    this.f.a(a, b);
  }

  public boolean onSurfaceTextureDestroyed(SurfaceTexture paramSurfaceTexture)
  {
    this.e = null;
    return true;
  }

  public void onSurfaceTextureSizeChanged(SurfaceTexture paramSurfaceTexture, int paramInt1, int paramInt2)
  {
    if (this.f == null)
      return;
    a = paramInt1;
    b = paramInt2;
    this.f.a(a, b);
    MapRenderer.nativeResize(this.f.g, paramInt1, paramInt2);
  }

  public void onSurfaceTextureUpdated(SurfaceTexture paramSurfaceTexture)
  {
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((this.f == null) || (this.f.f == null))
      return true;
    super.onTouchEvent(paramMotionEvent);
    Iterator localIterator = this.f.e.iterator();
    while (localIterator.hasNext())
      ((i)localIterator.next()).a(paramMotionEvent);
    if (this.c.onTouchEvent(paramMotionEvent))
      return true;
    return this.f.a(paramMotionEvent);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.platform.comapi.map.C
 * JD-Core Version:    0.6.2
 */