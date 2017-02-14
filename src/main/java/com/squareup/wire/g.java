package com.squareup.wire;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

abstract class g<T>
{
  private static final int b = 64;
  private static final float c = 0.75F;
  private static final Comparator<? super Map.Entry<Integer, ?>> d = new Comparator()
  {
    public int a(Map.Entry<Integer, ?> paramAnonymousEntry1, Map.Entry<Integer, ?> paramAnonymousEntry2)
    {
      return ((Integer)paramAnonymousEntry1.getKey()).compareTo((Integer)paramAnonymousEntry2.getKey());
    }
  };
  List<T> a;

  protected g(Map<Integer, T> paramMap)
  {
    this.a = c(paramMap);
  }

  public static <T> g<T> a(Map<Integer, T> paramMap)
  {
    int i = b(paramMap);
    if (a(paramMap.size(), i))
      return a.a(paramMap, i);
    return b.b(paramMap);
  }

  private static boolean a(int paramInt1, int paramInt2)
  {
    return (paramInt2 <= 64) || (paramInt1 / paramInt2 > 0.75F);
  }

  private static <T> int b(Map<Integer, T> paramMap)
  {
    paramMap = paramMap.keySet().iterator();
    int i = -1;
    if (paramMap.hasNext())
    {
      int j = ((Integer)paramMap.next()).intValue();
      if (j <= i)
        break label48;
      i = j;
    }
    label48: 
    while (true)
    {
      break;
      return i;
    }
  }

  private static <T> List<T> c(Map<Integer, T> paramMap)
  {
    Object localObject = new TreeSet(d);
    ((TreeSet)localObject).addAll(paramMap.entrySet());
    paramMap = new ArrayList();
    localObject = ((TreeSet)localObject).iterator();
    while (((Iterator)localObject).hasNext())
      paramMap.add(((Map.Entry)((Iterator)localObject).next()).getValue());
    return paramMap;
  }

  public abstract T a(int paramInt);

  public Collection<T> a()
  {
    return this.a;
  }

  public abstract boolean b(int paramInt);

  static final class a<T> extends g<T>
  {
    Object[] b;
    int c = -1;

    private a(Map<Integer, T> paramMap, int paramInt)
    {
      super();
      this.c = paramInt;
      this.b = new Object[paramInt + 1];
      paramMap = paramMap.entrySet().iterator();
      while (paramMap.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramMap.next();
        Integer localInteger = (Integer)localEntry.getKey();
        if (localInteger.intValue() <= 0)
          throw new IllegalArgumentException("Input map key is negative or zero");
        this.b[localInteger.intValue()] = localEntry.getValue();
      }
    }

    public static <T> a<T> a(Map<Integer, T> paramMap, int paramInt)
    {
      return new a(paramMap, paramInt);
    }

    public T a(int paramInt)
    {
      if (paramInt > this.c)
        return null;
      return this.b[paramInt];
    }

    public boolean b(int paramInt)
    {
      if (paramInt > this.c);
      while (this.b[paramInt] == null)
        return false;
      return true;
    }
  }

  static final class b<T> extends g<T>
  {
    Map<Integer, T> b;

    private b(Map<Integer, T> paramMap)
    {
      super();
      this.b = paramMap;
    }

    public static <T> b<T> b(Map<Integer, T> paramMap)
    {
      return new b(paramMap);
    }

    public T a(int paramInt)
    {
      return this.b.get(Integer.valueOf(paramInt));
    }

    public boolean b(int paramInt)
    {
      return this.b.containsKey(Integer.valueOf(paramInt));
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.squareup.wire.g
 * JD-Core Version:    0.6.2
 */