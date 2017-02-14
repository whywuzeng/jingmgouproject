package com.alimama.mobile.csdk.umupdate.a;

import android.os.SystemClock;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import u.upd.b;

public class g
{
  public static String a = "Munion";
  public static boolean b = b.a;

  public static void a(String paramString)
  {
    b("Changing log tag to %s", new Object[] { paramString });
    a = paramString;
  }

  public static void a(String paramString, Object[] paramArrayOfObject)
  {
    if (b)
      Log.v(a, g(paramString, paramArrayOfObject));
  }

  public static void a(Throwable paramThrowable, String paramString, Object[] paramArrayOfObject)
  {
    Log.e(a, g(paramString, paramArrayOfObject), paramThrowable);
  }

  public static void b(String paramString, Object[] paramArrayOfObject)
  {
    if (b)
      Log.d(a, g(paramString, paramArrayOfObject));
  }

  public static void b(Throwable paramThrowable, String paramString, Object[] paramArrayOfObject)
  {
    Log.w(a, g(paramString, paramArrayOfObject), paramThrowable);
  }

  public static void c(String paramString, Object[] paramArrayOfObject)
  {
    if (b)
      Log.i(a, g(paramString, paramArrayOfObject));
  }

  public static void c(Throwable paramThrowable, String paramString, Object[] paramArrayOfObject)
  {
    Log.wtf(a, g(paramString, paramArrayOfObject), paramThrowable);
  }

  public static void d(String paramString, Object[] paramArrayOfObject)
  {
    Log.e(a, g(paramString, paramArrayOfObject));
  }

  public static void e(String paramString, Object[] paramArrayOfObject)
  {
    Log.w(a, g(paramString, paramArrayOfObject));
  }

  public static void f(String paramString, Object[] paramArrayOfObject)
  {
    Log.wtf(a, g(paramString, paramArrayOfObject));
  }

  private static String g(String paramString, Object[] paramArrayOfObject)
  {
    String str = paramString;
    int i;
    if (paramArrayOfObject != null)
    {
      if (paramArrayOfObject.length == 0)
        str = paramString;
    }
    else
    {
      paramString = new Throwable().fillInStackTrace().getStackTrace();
      i = 2;
      label29: if (i >= paramString.length)
        break label157;
      if (paramString[i].getClass().equals(g.class))
        break label150;
      paramArrayOfObject = paramString[i].getClassName();
      paramArrayOfObject = paramArrayOfObject.substring(paramArrayOfObject.lastIndexOf('.') + 1);
      paramArrayOfObject = paramArrayOfObject.substring(paramArrayOfObject.lastIndexOf('$') + 1);
    }
    label150: label157: for (paramString = paramArrayOfObject + "." + paramString[i].getMethodName(); ; paramString = "<unknown>")
    {
      return String.format("[%d] %s: %s", new Object[] { Long.valueOf(Thread.currentThread().getId()), paramString, str });
      str = String.format(paramString, paramArrayOfObject);
      break;
      i += 1;
      break label29;
    }
  }

  public static class a
  {
    public static final boolean a = g.b;
    private static final long b = 0L;
    private final List<a> c = new ArrayList();
    private boolean d = false;

    private long a()
    {
      if (this.c.size() == 0)
        return 0L;
      long l = ((a)this.c.get(0)).c;
      return ((a)this.c.get(this.c.size() - 1)).c - l;
    }

    public void a(String paramString)
    {
      try
      {
        this.d = true;
        long l2 = a();
        if (l2 <= 0L);
        while (true)
        {
          return;
          long l1 = ((a)this.c.get(0)).c;
          g.b("(%-4d ms) %s", new Object[] { Long.valueOf(l2), paramString });
          paramString = this.c.iterator();
          while (paramString.hasNext())
          {
            a locala = (a)paramString.next();
            l2 = locala.c;
            g.b("(+%-4d) [%2d] %s", new Object[] { Long.valueOf(l2 - l1), Long.valueOf(locala.b), locala.a });
            l1 = l2;
          }
        }
      }
      finally
      {
      }
      throw paramString;
    }

    public void a(String paramString, long paramLong)
    {
      try
      {
        boolean bool = this.d;
        if (bool);
        while (true)
        {
          return;
          this.c.add(new a(paramString, paramLong, SystemClock.elapsedRealtime()));
        }
      }
      finally
      {
      }
      throw paramString;
    }

    protected void finalize()
      throws Throwable
    {
      if (!this.d)
      {
        a("Request on the loose");
        g.d("Marker log finalized without finish() - uncaught exit point for request", new Object[0]);
      }
    }

    private static class a
    {
      public final String a;
      public final long b;
      public final long c;

      public a(String paramString, long paramLong1, long paramLong2)
      {
        this.a = paramString;
        this.b = paramLong1;
        this.c = paramLong2;
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.alimama.mobile.csdk.umupdate.a.g
 * JD-Core Version:    0.6.2
 */