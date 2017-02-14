package com.umeng.message.proguard;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class I
{
  protected static final Object a = new Object();
  protected Map<String, a> b = new HashMap();
  private Random c = new Random();

  public b a(int paramInt)
  {
    Object localObject1 = a;
    if (paramInt < 0);
    try
    {
      Object localObject2 = new b();
      ((b)localObject2).a(false);
      return localObject2;
      localObject2 = "" + paramInt;
      localObject2 = (a)this.b.get(localObject2);
      if (localObject2 != null)
      {
        localObject2 = a((a)localObject2);
        return localObject2;
      }
    }
    finally
    {
    }
    Object localObject4 = "" + (paramInt - paramInt % 100);
    localObject4 = (a)this.b.get(localObject4);
    if (localObject4 != null)
    {
      localObject4 = a((a)localObject4);
      return localObject4;
    }
    localObject4 = "" + (paramInt - paramInt % 1000);
    localObject4 = (a)this.b.get(localObject4);
    if (localObject4 != null)
    {
      localObject4 = a((a)localObject4);
      return localObject4;
    }
    if (paramInt > 20000)
    {
      localObject4 = (a)this.b.get("-3");
      if (localObject4 != null)
      {
        localObject4 = a((a)localObject4);
        return localObject4;
      }
    }
    if (paramInt > 10000)
    {
      localObject4 = (a)this.b.get("-2");
      if (localObject4 != null)
      {
        localObject4 = a((a)localObject4);
        return localObject4;
      }
    }
    localObject4 = (a)this.b.get("-1");
    if (localObject4 == null)
    {
      localObject4 = new b();
      ((b)localObject4).a(false);
      ((b)localObject4).a(0);
      return localObject4;
    }
    localObject4 = a((a)localObject4);
    return localObject4;
  }

  public b a(a parama)
  {
    b localb = new b();
    localb.b(true);
    if ((parama != null) && (a.a(parama) <= 100) && (a.a(parama) > 0))
    {
      localb.a(a.a(parama));
      if (this.c.nextInt(100) % 100 < a.a(parama))
      {
        localb.a(true);
        localb.a();
        return localb;
      }
    }
    localb.a(false);
    return localb;
  }

  public void a(int paramInt1, int paramInt2)
  {
    Object localObject1 = a;
    if (paramInt2 >= 0);
    try
    {
      String str = "" + paramInt1;
      if (this.b.get(str) != null)
        this.b.remove(str);
      a locala = new a();
      locala.a(paramInt1);
      locala.b(paramInt2);
      this.b.put(str, locala);
      return;
    }
    finally
    {
    }
  }

  class a
  {
    private int b;
    private int c;

    a()
    {
    }

    public void a(int paramInt)
    {
      this.b = paramInt;
    }

    public void b(int paramInt)
    {
      this.c = paramInt;
    }
  }

  public static class b
  {
    private boolean a;
    private int b;
    private boolean c = false;
    private boolean d = false;

    public void a()
    {
      this.d = true;
    }

    public void a(int paramInt)
    {
      this.b = paramInt;
    }

    public void a(boolean paramBoolean)
    {
      this.a = paramBoolean;
    }

    public void b(boolean paramBoolean)
    {
      this.c = paramBoolean;
    }

    public boolean b()
    {
      return this.d;
    }

    public int c()
    {
      return this.b;
    }

    public boolean d()
    {
      return this.c;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.I
 * JD-Core Version:    0.6.2
 */