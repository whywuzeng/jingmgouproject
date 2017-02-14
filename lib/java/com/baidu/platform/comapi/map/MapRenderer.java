package com.baidu.platform.comapi.map;

import android.opengl.GLSurfaceView.Renderer;
import java.util.Iterator;
import java.util.List;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MapRenderer
  implements GLSurfaceView.Renderer
{
  private static final String d = MapRenderer.class.getSimpleName();
  public int a;
  public int b;
  public int c;
  private long e;
  private a f;
  private final g g;

  public MapRenderer(g paramg, a parama)
  {
    this.f = parama;
    this.g = paramg;
  }

  private void a(GL10 paramGL10)
  {
    paramGL10.glClear(16640);
    paramGL10.glClearColor(0.85F, 0.8F, 0.8F, 0.0F);
  }

  private boolean a()
  {
    return this.e != 0L;
  }

  public static native void nativeInit(long paramLong);

  public static native int nativeRender(long paramLong);

  public static native void nativeResize(long paramLong, int paramInt1, int paramInt2);

  public void a(long paramLong)
  {
    this.e = paramLong;
  }

  public void onDrawFrame(GL10 paramGL10)
  {
    if (!a())
      a(paramGL10);
    do
    {
      do
      {
        return;
        if (this.c <= 1)
        {
          nativeResize(this.e, this.a, this.b);
          this.c += 1;
        }
        this.f.c();
        int i = nativeRender(this.e);
        Iterator localIterator = this.g.a().e.iterator();
        while (localIterator.hasNext())
        {
          i locali = (i)localIterator.next();
          B localB = this.g.a().y();
          paramGL10.glPushMatrix();
          paramGL10.glRotatef(localB.c, 1.0F, 0.0F, 0.0F);
          paramGL10.glRotatef(localB.b, 0.0F, 0.0F, 1.0F);
          locali.a(paramGL10, localB);
          paramGL10.glPopMatrix();
          paramGL10.glColor4f(0.96F, 0.95F, 0.94F, 1.0F);
        }
        paramGL10 = this.g;
        if (i == 1)
        {
          paramGL10.requestRender();
          return;
        }
        if (this.g.a().c())
          break;
      }
      while (paramGL10.getRenderMode() == 0);
      paramGL10.setRenderMode(0);
      return;
    }
    while (paramGL10.getRenderMode() == 1);
    paramGL10.setRenderMode(1);
  }

  public void onSurfaceChanged(GL10 paramGL10, int paramInt1, int paramInt2)
  {
    if (this.e != 0L)
      nativeResize(this.e, paramInt1, paramInt2);
  }

  public void onSurfaceCreated(GL10 paramGL10, EGLConfig paramEGLConfig)
  {
    nativeInit(this.e);
    if (!a())
      return;
    this.f.c();
  }

  public static abstract interface a
  {
    public abstract void c();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.platform.comapi.map.MapRenderer
 * JD-Core Version:    0.6.2
 */