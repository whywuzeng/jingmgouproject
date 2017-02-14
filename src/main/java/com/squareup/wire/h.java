package com.squareup.wire;

import com.umeng.message.proguard.aE;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

final class h
{
  Map<Integer, List<a>> a;

  h()
  {
  }

  h(h paramh)
  {
    if (paramh.a != null)
      b().putAll(paramh.a);
  }

  private <T> void a(Map<Integer, List<a>> paramMap, int paramInt, T paramT, WireType paramWireType)
    throws IOException
  {
    Object localObject = (List)paramMap.get(Integer.valueOf(paramInt));
    if (localObject == null)
    {
      localObject = new ArrayList();
      paramMap.put(Integer.valueOf(paramInt), localObject);
    }
    while (true)
    {
      switch (1.a[paramWireType.ordinal()])
      {
      default:
        throw new IllegalArgumentException("Unsupported wireType = " + paramWireType);
      case 1:
        paramMap = a.a(paramInt, (Long)paramT);
      case 2:
      case 3:
      case 4:
      }
      while ((((List)localObject).size() > 0) && (((a)((List)localObject).get(0)).c() != paramMap.c()))
      {
        throw new IOException(String.format("Wire type %s differs from previous type %s for tag %s", new Object[] { paramMap.c(), ((a)((List)localObject).get(0)).c(), Integer.valueOf(paramInt) }));
        paramMap = a.a(paramInt, (Integer)paramT);
        continue;
        paramMap = a.b(paramInt, (Long)paramT);
        continue;
        paramMap = a.a(paramInt, (aE)paramT);
      }
      ((List)localObject).add(paramMap);
      return;
    }
  }

  private Map<Integer, List<a>> b()
  {
    if (this.a == null)
      this.a = new TreeMap();
    return this.a;
  }

  int a()
  {
    if (this.a != null)
    {
      Iterator localIterator = this.a.entrySet().iterator();
      int i = 0;
      while (true)
      {
        j = i;
        if (!localIterator.hasNext())
          break;
        Object localObject = (Map.Entry)localIterator.next();
        i = WireOutput.a(((Integer)((Map.Entry)localObject).getKey()).intValue()) + i;
        localObject = ((List)((Map.Entry)localObject).getValue()).iterator();
        while (((Iterator)localObject).hasNext())
          i += ((a)((Iterator)localObject).next()).a();
      }
    }
    int j = 0;
    return j;
  }

  void a(int paramInt, aE paramaE)
    throws IOException
  {
    a(b(), paramInt, paramaE, WireType.LENGTH_DELIMITED);
  }

  void a(int paramInt, Integer paramInteger)
    throws IOException
  {
    a(b(), paramInt, paramInteger, WireType.FIXED32);
  }

  void a(int paramInt, Long paramLong)
    throws IOException
  {
    a(b(), paramInt, paramLong, WireType.VARINT);
  }

  void a(WireOutput paramWireOutput)
    throws IOException
  {
    if (this.a != null)
    {
      Iterator localIterator = this.a.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Object localObject = (Map.Entry)localIterator.next();
        int i = ((Integer)((Map.Entry)localObject).getKey()).intValue();
        localObject = ((List)((Map.Entry)localObject).getValue()).iterator();
        while (((Iterator)localObject).hasNext())
          ((a)((Iterator)localObject).next()).a(i, paramWireOutput);
      }
    }
  }

  void b(int paramInt, Long paramLong)
    throws IOException
  {
    a(b(), paramInt, paramLong, WireType.FIXED64);
  }

  static abstract class a
  {
    private final int a;
    private final WireType b;

    public a(int paramInt, WireType paramWireType)
    {
      this.a = paramInt;
      this.b = paramWireType;
    }

    public static h.b a(int paramInt, Integer paramInteger)
    {
      return new h.b(paramInt, paramInteger);
    }

    public static h.d a(int paramInt, aE paramaE)
    {
      return new h.d(paramInt, paramaE);
    }

    public static h.f a(int paramInt, Long paramLong)
    {
      return new h.f(paramInt, paramLong);
    }

    public static h.c b(int paramInt, Long paramLong)
    {
      return new h.c(paramInt, paramLong);
    }

    public abstract int a();

    public abstract void a(int paramInt, WireOutput paramWireOutput)
      throws IOException;

    public int b()
    {
      return this.a;
    }

    public WireType c()
    {
      return this.b;
    }

    public Integer d()
    {
      throw new IllegalStateException();
    }

    public Long e()
    {
      throw new IllegalStateException();
    }

    public aE f()
    {
      throw new IllegalStateException();
    }
  }

  static final class b extends h.a
  {
    private final Integer a;

    public b(int paramInt, Integer paramInteger)
    {
      super(WireType.FIXED32);
      this.a = paramInteger;
    }

    public int a()
    {
      return 4;
    }

    public void a(int paramInt, WireOutput paramWireOutput)
      throws IOException
    {
      paramWireOutput.a(paramInt, WireType.FIXED32);
      paramWireOutput.f(this.a.intValue());
    }

    public Integer d()
    {
      return this.a;
    }
  }

  static final class c extends h.a
  {
    private final Long a;

    public c(int paramInt, Long paramLong)
    {
      super(WireType.FIXED64);
      this.a = paramLong;
    }

    public int a()
    {
      return 8;
    }

    public void a(int paramInt, WireOutput paramWireOutput)
      throws IOException
    {
      paramWireOutput.a(paramInt, WireType.FIXED64);
      paramWireOutput.c(this.a.longValue());
    }

    public Long e()
    {
      return this.a;
    }
  }

  static final class d extends h.a
  {
    private final aE a;

    public d(int paramInt, aE paramaE)
    {
      super(WireType.LENGTH_DELIMITED);
      this.a = paramaE;
    }

    public int a()
    {
      return WireOutput.b(this.a.f()) + this.a.f();
    }

    public void a(int paramInt, WireOutput paramWireOutput)
      throws IOException
    {
      paramWireOutput.a(paramInt, WireType.LENGTH_DELIMITED);
      paramWireOutput.e(this.a.f());
      paramWireOutput.b(this.a.g());
    }

    public aE f()
    {
      return this.a;
    }
  }

  static enum e
  {
    public static e a(String paramString)
    {
      if ("varint".equals(paramString))
        return a;
      if ("fixed32".equals(paramString))
        return b;
      if ("fixed64".equals(paramString))
        return c;
      if ("length-delimited".equals(paramString))
        return d;
      throw new IllegalArgumentException("Unknown type " + paramString);
    }
  }

  static final class f extends h.a
  {
    private final Long a;

    public f(int paramInt, Long paramLong)
    {
      super(WireType.VARINT);
      this.a = paramLong;
    }

    public int a()
    {
      return WireOutput.a(this.a.longValue());
    }

    public void a(int paramInt, WireOutput paramWireOutput)
      throws IOException
    {
      paramWireOutput.a(paramInt, WireType.VARINT);
      paramWireOutput.b(this.a.longValue());
    }

    public Long e()
    {
      return this.a;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.squareup.wire.h
 * JD-Core Version:    0.6.2
 */