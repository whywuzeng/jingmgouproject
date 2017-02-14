package com.umeng.message.proguard;

import android.app.Application;
import android.content.Context;
import android.os.Build.VERSION;
import java.util.HashMap;
import java.util.Map;

public class m
{
  private static m a = null;
  private r b;
  private Map<String, r> c = new HashMap();

  private m()
  {
    if (Build.VERSION.SDK_INT < 14)
    {
      U.a().a(new ag());
      return;
    }
    C.a(new ag());
  }

  public static m a()
  {
    try
    {
      if (a == null)
        a = new m();
      m localm = a;
      return localm;
    }
    finally
    {
    }
  }

  public void a(Application paramApplication)
  {
    w.a().a(paramApplication);
  }

  public void a(Context paramContext)
  {
    w.a().a(paramContext);
    if ((paramContext != null) && (!ae.a().b()))
      ae.a().a(paramContext);
  }

  public void a(aa paramaa)
  {
    if (paramaa == null)
      y.c(1, "setRequestAuthentication", "Fatal Error,pRequestAuth must not be null.");
    w.a().a(paramaa);
  }

  public void a(ad paramad)
  {
    ae.a().a(paramad);
  }

  public void a(String paramString)
  {
    w.a().a(paramString);
  }

  public void a(String paramString1, String paramString2)
  {
    w.a().a(paramString1, paramString2);
  }

  public r b()
  {
    try
    {
      if ((this.b == null) && (w.a().d() != null))
        this.b = new r();
      if (this.b == null)
        y.c(1, "getDefaultTracker error", "Fatal Error,must call setRequestAuthentication method first.");
      r localr = this.b;
      return localr;
    }
    finally
    {
    }
  }

  public r b(String paramString)
  {
    while (true)
    {
      try
      {
        if (!at.a(paramString))
        {
          if (this.c.containsKey(paramString))
          {
            paramString = (r)this.c.get(paramString);
            return paramString;
          }
          r localr = new r();
          localr.a(paramString);
          this.c.put(paramString, localr);
          paramString = localr;
          continue;
        }
      }
      finally
      {
      }
      y.c(1, "getTracker", "TrackId is null.");
      paramString = null;
    }
  }

  public void c()
  {
    ae.a().c();
  }

  public void c(String paramString)
  {
    w.a().b(paramString);
  }

  public void d()
  {
    w.a().e();
  }

  public void d(String paramString)
  {
    if (!at.a(paramString))
    {
      r localr = b();
      if (localr != null)
      {
        localr.a(new ay("UT", 1006, paramString, null, null, null).a());
        return;
      }
      y.c(1, "Record userRegister event error", "Fatal Error,must call setRequestAuthentication method first.");
      return;
    }
    y.c(1, "userRegister", "Fatal Error,usernick can not be null or empty!");
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.m
 * JD-Core Version:    0.6.2
 */