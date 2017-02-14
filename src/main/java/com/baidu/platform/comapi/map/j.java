package com.baidu.platform.comapi.map;

import android.annotation.SuppressLint;
import android.graphics.SurfaceTexture;
import android.opengl.GLUtils;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL10;

@SuppressLint({"NewApi"})
public class j extends Thread
{
  private AtomicBoolean a;
  private SurfaceTexture b;
  private a c;
  private EGL10 d;
  private EGLDisplay e = EGL10.EGL_NO_DISPLAY;
  private EGLContext f = EGL10.EGL_NO_CONTEXT;
  private EGLSurface g = EGL10.EGL_NO_SURFACE;
  private GL10 h;
  private int i = 1;
  private boolean j = false;
  private final C k;

  public j(SurfaceTexture paramSurfaceTexture, a parama, AtomicBoolean paramAtomicBoolean, C paramC)
  {
    this.b = paramSurfaceTexture;
    this.c = parama;
    this.a = paramAtomicBoolean;
    this.k = paramC;
  }

  private boolean a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
  {
    this.d = ((EGL10)EGLContext.getEGL());
    this.e = this.d.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
    if (this.e == EGL10.EGL_NO_DISPLAY)
      throw new RuntimeException("eglGetdisplay failed : " + GLUtils.getEGLErrorString(this.d.eglGetError()));
    Object localObject = new int[2];
    if (!this.d.eglInitialize(this.e, (int[])localObject))
      throw new RuntimeException("eglInitialize failed : " + GLUtils.getEGLErrorString(this.d.eglGetError()));
    localObject = new EGLConfig[100];
    int[] arrayOfInt = new int[1];
    if ((this.d.eglChooseConfig(this.e, new int[] { 12324, paramInt1, 12323, paramInt2, 12322, paramInt3, 12321, paramInt4, 12325, paramInt5, 12326, paramInt6, 12344 }, (EGLConfig[])localObject, 100, arrayOfInt)) && (arrayOfInt[0] > 0))
    {
      this.f = this.d.eglCreateContext(this.e, localObject[0], EGL10.EGL_NO_CONTEXT, new int[] { 12440, 1, 12344 });
      this.g = this.d.eglCreateWindowSurface(this.e, localObject[0], this.b, null);
      if ((this.g == EGL10.EGL_NO_SURFACE) || (this.f == EGL10.EGL_NO_CONTEXT))
      {
        if (this.d.eglGetError() == 12299)
          throw new RuntimeException("eglCreateWindowSurface returned  EGL_BAD_NATIVE_WINDOW. ");
        GLUtils.getEGLErrorString(this.d.eglGetError());
      }
      if (!this.d.eglMakeCurrent(this.e, this.g, this.g, this.f))
      {
        GLUtils.getEGLErrorString(this.d.eglGetError());
        throw new RuntimeException("eglMakeCurrent failed : " + GLUtils.getEGLErrorString(this.d.eglGetError()));
      }
      this.h = ((GL10)this.f.getGL());
      return true;
    }
    return false;
  }

  private void d()
  {
    a(5, 6, 5, 0, 24, 0);
    MapRenderer.nativeInit(this.k.b().g);
    MapRenderer.nativeResize(this.k.b().g, C.a, C.b);
  }

  private void e()
  {
    this.d.eglDestroyContext(this.e, this.f);
    this.d.eglDestroySurface(this.e, this.g);
    this.f = EGL10.EGL_NO_CONTEXT;
    this.g = EGL10.EGL_NO_SURFACE;
  }

  public void a()
  {
    this.i = 1;
    try
    {
      if (getState() == Thread.State.WAITING)
        notify();
      return;
    }
    finally
    {
    }
  }

  public void b()
  {
    this.i = 0;
    try
    {
      try
      {
        if (getState() != Thread.State.WAITING)
          wait();
        return;
      }
      finally
      {
      }
    }
    catch (InterruptedException localInterruptedException)
    {
      localInterruptedException.printStackTrace();
    }
  }

  public void c()
  {
    try
    {
      e();
      this.j = true;
      return;
    }
    finally
    {
    }
  }

  public void run()
  {
    d();
    while (true)
    {
      if (this.c != null)
      {
        if (this.i != 1)
          break label192;
        this.i = this.c.a();
        Iterator localIterator = this.k.b().e.iterator();
        while (localIterator.hasNext())
        {
          i locali = (i)localIterator.next();
          B localB = this.k.b().y();
          this.h.glPushMatrix();
          this.h.glRotatef(localB.c, 1.0F, 0.0F, 0.0F);
          this.h.glRotatef(localB.b, 0.0F, 0.0F, 1.0F);
          locali.a(this.h, localB);
          this.h.glPopMatrix();
          this.h.glColor4f(0.96F, 0.95F, 0.94F, 1.0F);
        }
        this.d.eglSwapBuffers(this.e, this.g);
      }
      while (this.j)
      {
        e();
        return;
        try
        {
          try
          {
            label192: wait();
          }
          finally
          {
          }
        }
        catch (InterruptedException localInterruptedException)
        {
          localInterruptedException.printStackTrace();
        }
      }
    }
  }

  public static abstract interface a
  {
    public abstract int a();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.platform.comapi.map.j
 * JD-Core Version:    0.6.2
 */