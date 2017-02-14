package com.umeng.message.proguard;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@TargetApi(14)
public class B
  implements Application.ActivityLifecycleCallbacks
{
  private static B h = null;
  private int a = 0;
  private boolean b = false;
  private TimerTask c;
  private Object d = new Object();
  private Timer e = null;
  private List<A> f = new LinkedList();
  private Object g = new Object();

  public static B a()
  {
    try
    {
      if (h == null)
        h = new B();
      B localB = h;
      return localB;
    }
    finally
    {
    }
  }

  private void b()
  {
    synchronized (this.d)
    {
      if (this.e != null)
      {
        this.e.cancel();
        this.e = null;
      }
      return;
    }
  }

  public void a(A paramA)
  {
    if (paramA != null)
      synchronized (this.g)
      {
        this.f.add(paramA);
        return;
      }
  }

  public void onActivityCreated(Activity paramActivity, Bundle paramBundle)
  {
    synchronized (this.g)
    {
      Iterator localIterator = this.f.iterator();
      if (localIterator.hasNext())
        ((A)localIterator.next()).a(paramActivity, paramBundle);
    }
  }

  public void onActivityDestroyed(Activity paramActivity)
  {
    synchronized (this.g)
    {
      Iterator localIterator = this.f.iterator();
      if (localIterator.hasNext())
        ((A)localIterator.next()).a(paramActivity);
    }
  }

  public void onActivityPaused(Activity paramActivity)
  {
    synchronized (this.g)
    {
      Iterator localIterator = this.f.iterator();
      if (localIterator.hasNext())
        ((A)localIterator.next()).b(paramActivity);
    }
  }

  public void onActivityResumed(Activity paramActivity)
  {
    synchronized (this.g)
    {
      Iterator localIterator = this.f.iterator();
      if (localIterator.hasNext())
        ((A)localIterator.next()).c(paramActivity);
    }
  }

  public void onActivitySaveInstanceState(Activity paramActivity, Bundle paramBundle)
  {
    synchronized (this.g)
    {
      Iterator localIterator = this.f.iterator();
      if (localIterator.hasNext())
        ((A)localIterator.next()).b(paramActivity, paramBundle);
    }
  }

  public void onActivityStarted(Activity arg1)
  {
    b();
    this.a += 1;
    if (!this.b)
      synchronized (this.g)
      {
        Iterator localIterator = this.f.iterator();
        if (localIterator.hasNext())
          ((A)localIterator.next()).b();
      }
    this.b = true;
  }

  public void onActivityStopped(Activity paramActivity)
  {
    this.a -= 1;
    if (this.a == 0)
    {
      b();
      this.c = new a(null);
      this.e = new Timer();
      this.e.schedule(this.c, 1000L);
    }
  }

  private class a extends TimerTask
  {
    private a()
    {
    }

    public void run()
    {
      B.a(B.this, false);
      synchronized (B.a(B.this))
      {
        Iterator localIterator = B.b(B.this).iterator();
        if (localIterator.hasNext())
          ((A)localIterator.next()).a();
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.B
 * JD-Core Version:    0.6.2
 */