package com.baidu.mapapi.map;

import android.graphics.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

class j<T extends a>
{
  private final e a;
  private final int b;
  private List<T> c;
  private List<j<T>> d = null;

  private j(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, int paramInt)
  {
    this(new e(paramDouble1, paramDouble2, paramDouble3, paramDouble4), paramInt);
  }

  public j(e parame)
  {
    this(parame, 0);
  }

  private j(e parame, int paramInt)
  {
    this.a = parame;
    this.b = paramInt;
  }

  private void a()
  {
    this.d = new ArrayList(4);
    this.d.add(new j(this.a.a, this.a.e, this.a.b, this.a.f, this.b + 1));
    this.d.add(new j(this.a.e, this.a.c, this.a.b, this.a.f, this.b + 1));
    this.d.add(new j(this.a.a, this.a.e, this.a.f, this.a.d, this.b + 1));
    this.d.add(new j(this.a.e, this.a.c, this.a.f, this.a.d, this.b + 1));
    Object localObject = this.c;
    this.c = null;
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      a locala = (a)((Iterator)localObject).next();
      a(locala.a().x, locala.a().y, locala);
    }
  }

  private void a(double paramDouble1, double paramDouble2, T paramT)
  {
    if (this.d != null)
      if (paramDouble2 < this.a.f)
        if (paramDouble1 < this.a.e)
          ((j)this.d.get(0)).a(paramDouble1, paramDouble2, paramT);
    do
    {
      return;
      ((j)this.d.get(1)).a(paramDouble1, paramDouble2, paramT);
      return;
      if (paramDouble1 < this.a.e)
      {
        ((j)this.d.get(2)).a(paramDouble1, paramDouble2, paramT);
        return;
      }
      ((j)this.d.get(3)).a(paramDouble1, paramDouble2, paramT);
      return;
      if (this.c == null)
        this.c = new ArrayList();
      this.c.add(paramT);
    }
    while ((this.c.size() <= 40) || (this.b >= 40));
    a();
  }

  private void a(e parame, Collection<T> paramCollection)
  {
    if (!this.a.a(parame));
    while (true)
    {
      return;
      Iterator localIterator;
      if (this.d != null)
      {
        localIterator = this.d.iterator();
        while (localIterator.hasNext())
          ((j)localIterator.next()).a(parame, paramCollection);
      }
      else if (this.c != null)
      {
        if (parame.b(this.a))
        {
          paramCollection.addAll(this.c);
          return;
        }
        localIterator = this.c.iterator();
        while (localIterator.hasNext())
        {
          a locala = (a)localIterator.next();
          if (parame.a(locala.a()))
            paramCollection.add(locala);
        }
      }
    }
  }

  public Collection<T> a(e parame)
  {
    ArrayList localArrayList = new ArrayList();
    a(parame, localArrayList);
    return localArrayList;
  }

  public void a(T paramT)
  {
    Point localPoint = paramT.a();
    if (this.a.a(localPoint.x, localPoint.y))
      a(localPoint.x, localPoint.y, paramT);
  }

  static abstract class a
  {
    abstract Point a();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.j
 * JD-Core Version:    0.6.2
 */