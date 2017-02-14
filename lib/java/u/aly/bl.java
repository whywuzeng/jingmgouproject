package u.aly;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class bl
  implements Serializable, Cloneable, ch<bl, e>
{
  public static final Map<e, ct> h;
  private static final dl i = new dl("Session");
  private static final db j = new db("id", (byte)11, (short)1);
  private static final db k = new db("start_time", (byte)10, (short)2);
  private static final db l = new db("end_time", (byte)10, (short)3);
  private static final db m = new db("duration", (byte)10, (short)4);
  private static final db n = new db("pages", (byte)15, (short)5);
  private static final db o = new db("locations", (byte)15, (short)6);
  private static final db p = new db("traffic", (byte)12, (short)7);
  private static final Map<Class<? extends do>, dp> q = new HashMap();
  private static final int r = 0;
  private static final int s = 1;
  private static final int t = 2;
  public String a;
  public long b;
  public long c;
  public long d;
  public List<bg> e;
  public List<be> f;
  public bm g;
  private byte u = 0;
  private e[] v = { e.e, e.f, e.g };

  static
  {
    q.put(dq.class, new b(null));
    q.put(dr.class, new d(null));
    EnumMap localEnumMap = new EnumMap(e.class);
    localEnumMap.put(e.a, new ct("id", (byte)1, new cu((byte)11)));
    localEnumMap.put(e.b, new ct("start_time", (byte)1, new cu((byte)10)));
    localEnumMap.put(e.c, new ct("end_time", (byte)1, new cu((byte)10)));
    localEnumMap.put(e.d, new ct("duration", (byte)1, new cu((byte)10)));
    localEnumMap.put(e.e, new ct("pages", (byte)2, new cv((byte)15, new cy((byte)12, bg.class))));
    localEnumMap.put(e.f, new ct("locations", (byte)2, new cv((byte)15, new cy((byte)12, be.class))));
    localEnumMap.put(e.g, new ct("traffic", (byte)2, new cy((byte)12, bm.class)));
    h = Collections.unmodifiableMap(localEnumMap);
    ct.a(bl.class, h);
  }

  public bl()
  {
  }

  public bl(String paramString, long paramLong1, long paramLong2, long paramLong3)
  {
    this();
    this.a = paramString;
    this.b = paramLong1;
    b(true);
    this.c = paramLong2;
    c(true);
    this.d = paramLong3;
    d(true);
  }

  public bl(bl parambl)
  {
    this.u = parambl.u;
    if (parambl.e())
      this.a = parambl.a;
    this.b = parambl.b;
    this.c = parambl.c;
    this.d = parambl.d;
    ArrayList localArrayList;
    Iterator localIterator;
    if (parambl.t())
    {
      localArrayList = new ArrayList();
      localIterator = parambl.e.iterator();
      while (localIterator.hasNext())
        localArrayList.add(new bg((bg)localIterator.next()));
      this.e = localArrayList;
    }
    if (parambl.y())
    {
      localArrayList = new ArrayList();
      localIterator = parambl.f.iterator();
      while (localIterator.hasNext())
        localArrayList.add(new be((be)localIterator.next()));
      this.f = localArrayList;
    }
    if (parambl.B())
      this.g = new bm(parambl.g);
  }

  private void a(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    try
    {
      this.u = 0;
      a(new da(new ds(paramObjectInputStream)));
      return;
    }
    catch (cn paramObjectInputStream)
    {
    }
    throw new IOException(paramObjectInputStream.getMessage());
  }

  private void a(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
    try
    {
      b(new da(new ds(paramObjectOutputStream)));
      return;
    }
    catch (cn paramObjectOutputStream)
    {
    }
    throw new IOException(paramObjectOutputStream.getMessage());
  }

  public void A()
  {
    this.g = null;
  }

  public boolean B()
  {
    return this.g != null;
  }

  public void C()
    throws cn
  {
    if (this.a == null)
      throw new dh("Required field 'id' was not present! Struct: " + toString());
    if (this.g != null)
      this.g.j();
  }

  public e a(int paramInt)
  {
    return e.a(paramInt);
  }

  public bl a()
  {
    return new bl(this);
  }

  public bl a(long paramLong)
  {
    this.b = paramLong;
    b(true);
    return this;
  }

  public bl a(String paramString)
  {
    this.a = paramString;
    return this;
  }

  public bl a(List<bg> paramList)
  {
    this.e = paramList;
    return this;
  }

  public bl a(bm parambm)
  {
    this.g = parambm;
    return this;
  }

  public void a(be parambe)
  {
    if (this.f == null)
      this.f = new ArrayList();
    this.f.add(parambe);
  }

  public void a(bg parambg)
  {
    if (this.e == null)
      this.e = new ArrayList();
    this.e.add(parambg);
  }

  public void a(dg paramdg)
    throws cn
  {
    ((dp)q.get(paramdg.D())).b().b(paramdg, this);
  }

  public void a(boolean paramBoolean)
  {
    if (!paramBoolean)
      this.a = null;
  }

  public bl b(long paramLong)
  {
    this.c = paramLong;
    c(true);
    return this;
  }

  public bl b(List<be> paramList)
  {
    this.f = paramList;
    return this;
  }

  public void b()
  {
    this.a = null;
    b(false);
    this.b = 0L;
    c(false);
    this.c = 0L;
    d(false);
    this.d = 0L;
    this.e = null;
    this.f = null;
    this.g = null;
  }

  public void b(dg paramdg)
    throws cn
  {
    ((dp)q.get(paramdg.D())).b().a(paramdg, this);
  }

  public void b(boolean paramBoolean)
  {
    this.u = ce.a(this.u, 0, paramBoolean);
  }

  public String c()
  {
    return this.a;
  }

  public bl c(long paramLong)
  {
    this.d = paramLong;
    d(true);
    return this;
  }

  public void c(boolean paramBoolean)
  {
    this.u = ce.a(this.u, 1, paramBoolean);
  }

  public void d()
  {
    this.a = null;
  }

  public void d(boolean paramBoolean)
  {
    this.u = ce.a(this.u, 2, paramBoolean);
  }

  public void e(boolean paramBoolean)
  {
    if (!paramBoolean)
      this.e = null;
  }

  public boolean e()
  {
    return this.a != null;
  }

  public long f()
  {
    return this.b;
  }

  public void f(boolean paramBoolean)
  {
    if (!paramBoolean)
      this.f = null;
  }

  public void g(boolean paramBoolean)
  {
    if (!paramBoolean)
      this.g = null;
  }

  public void h()
  {
    this.u = ce.b(this.u, 0);
  }

  public boolean i()
  {
    return ce.a(this.u, 0);
  }

  public long j()
  {
    return this.c;
  }

  public void k()
  {
    this.u = ce.b(this.u, 1);
  }

  public boolean l()
  {
    return ce.a(this.u, 1);
  }

  public long m()
  {
    return this.d;
  }

  public void n()
  {
    this.u = ce.b(this.u, 2);
  }

  public boolean o()
  {
    return ce.a(this.u, 2);
  }

  public int p()
  {
    if (this.e == null)
      return 0;
    return this.e.size();
  }

  public Iterator<bg> q()
  {
    if (this.e == null)
      return null;
    return this.e.iterator();
  }

  public List<bg> r()
  {
    return this.e;
  }

  public void s()
  {
    this.e = null;
  }

  public boolean t()
  {
    return this.e != null;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("Session(");
    localStringBuilder.append("id:");
    if (this.a == null)
    {
      localStringBuilder.append("null");
      localStringBuilder.append(", ");
      localStringBuilder.append("start_time:");
      localStringBuilder.append(this.b);
      localStringBuilder.append(", ");
      localStringBuilder.append("end_time:");
      localStringBuilder.append(this.c);
      localStringBuilder.append(", ");
      localStringBuilder.append("duration:");
      localStringBuilder.append(this.d);
      if (t())
      {
        localStringBuilder.append(", ");
        localStringBuilder.append("pages:");
        if (this.e != null)
          break label248;
        localStringBuilder.append("null");
      }
      label147: if (y())
      {
        localStringBuilder.append(", ");
        localStringBuilder.append("locations:");
        if (this.f != null)
          break label260;
        localStringBuilder.append("null");
      }
      label185: if (B())
      {
        localStringBuilder.append(", ");
        localStringBuilder.append("traffic:");
        if (this.g != null)
          break label272;
        localStringBuilder.append("null");
      }
    }
    while (true)
    {
      localStringBuilder.append(")");
      return localStringBuilder.toString();
      localStringBuilder.append(this.a);
      break;
      label248: localStringBuilder.append(this.e);
      break label147;
      label260: localStringBuilder.append(this.f);
      break label185;
      label272: localStringBuilder.append(this.g);
    }
  }

  public int u()
  {
    if (this.f == null)
      return 0;
    return this.f.size();
  }

  public Iterator<be> v()
  {
    if (this.f == null)
      return null;
    return this.f.iterator();
  }

  public List<be> w()
  {
    return this.f;
  }

  public void x()
  {
    this.f = null;
  }

  public boolean y()
  {
    return this.f != null;
  }

  public bm z()
  {
    return this.g;
  }

  private static class a extends dq<bl>
  {
    public void a(dg paramdg, bl parambl)
      throws cn
    {
      paramdg.j();
      Object localObject1 = paramdg.l();
      if (((db)localObject1).b == 0)
      {
        paramdg.k();
        if (!parambl.i())
          throw new dh("Required field 'start_time' was not found in serialized data! Struct: " + toString());
      }
      else
      {
        switch (((db)localObject1).c)
        {
        default:
          dj.a(paramdg, ((db)localObject1).b);
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
        case 6:
        case 7:
        }
        while (true)
        {
          paramdg.m();
          break;
          if (((db)localObject1).b == 11)
          {
            parambl.a = paramdg.z();
            parambl.a(true);
          }
          else
          {
            dj.a(paramdg, ((db)localObject1).b);
            continue;
            if (((db)localObject1).b == 10)
            {
              parambl.b = paramdg.x();
              parambl.b(true);
            }
            else
            {
              dj.a(paramdg, ((db)localObject1).b);
              continue;
              if (((db)localObject1).b == 10)
              {
                parambl.c = paramdg.x();
                parambl.c(true);
              }
              else
              {
                dj.a(paramdg, ((db)localObject1).b);
                continue;
                if (((db)localObject1).b == 10)
                {
                  parambl.d = paramdg.x();
                  parambl.d(true);
                }
                else
                {
                  dj.a(paramdg, ((db)localObject1).b);
                  continue;
                  int i;
                  Object localObject2;
                  if (((db)localObject1).b == 15)
                  {
                    localObject1 = paramdg.p();
                    parambl.e = new ArrayList(((dc)localObject1).b);
                    i = 0;
                    while (i < ((dc)localObject1).b)
                    {
                      localObject2 = new bg();
                      ((bg)localObject2).a(paramdg);
                      parambl.e.add(localObject2);
                      i += 1;
                    }
                    paramdg.q();
                    parambl.e(true);
                  }
                  else
                  {
                    dj.a(paramdg, ((db)localObject1).b);
                    continue;
                    if (((db)localObject1).b == 15)
                    {
                      localObject1 = paramdg.p();
                      parambl.f = new ArrayList(((dc)localObject1).b);
                      i = 0;
                      while (i < ((dc)localObject1).b)
                      {
                        localObject2 = new be();
                        ((be)localObject2).a(paramdg);
                        parambl.f.add(localObject2);
                        i += 1;
                      }
                      paramdg.q();
                      parambl.f(true);
                    }
                    else
                    {
                      dj.a(paramdg, ((db)localObject1).b);
                      continue;
                      if (((db)localObject1).b == 12)
                      {
                        parambl.g = new bm();
                        parambl.g.a(paramdg);
                        parambl.g(true);
                      }
                      else
                      {
                        dj.a(paramdg, ((db)localObject1).b);
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
      if (!parambl.l())
        throw new dh("Required field 'end_time' was not found in serialized data! Struct: " + toString());
      if (!parambl.o())
        throw new dh("Required field 'duration' was not found in serialized data! Struct: " + toString());
      parambl.C();
    }

    public void b(dg paramdg, bl parambl)
      throws cn
    {
      parambl.C();
      paramdg.a(bl.D());
      if (parambl.a != null)
      {
        paramdg.a(bl.E());
        paramdg.a(parambl.a);
        paramdg.c();
      }
      paramdg.a(bl.F());
      paramdg.a(parambl.b);
      paramdg.c();
      paramdg.a(bl.G());
      paramdg.a(parambl.c);
      paramdg.c();
      paramdg.a(bl.H());
      paramdg.a(parambl.d);
      paramdg.c();
      Iterator localIterator;
      if ((parambl.e != null) && (parambl.t()))
      {
        paramdg.a(bl.I());
        paramdg.a(new dc((byte)12, parambl.e.size()));
        localIterator = parambl.e.iterator();
        while (localIterator.hasNext())
          ((bg)localIterator.next()).b(paramdg);
        paramdg.f();
        paramdg.c();
      }
      if ((parambl.f != null) && (parambl.y()))
      {
        paramdg.a(bl.J());
        paramdg.a(new dc((byte)12, parambl.f.size()));
        localIterator = parambl.f.iterator();
        while (localIterator.hasNext())
          ((be)localIterator.next()).b(paramdg);
        paramdg.f();
        paramdg.c();
      }
      if ((parambl.g != null) && (parambl.B()))
      {
        paramdg.a(bl.K());
        parambl.g.b(paramdg);
        paramdg.c();
      }
      paramdg.d();
      paramdg.b();
    }
  }

  private static class b
    implements dp
  {
    public bl.a a()
    {
      return new bl.a(null);
    }
  }

  private static class c extends dr<bl>
  {
    public void a(dg paramdg, bl parambl)
      throws cn
    {
      paramdg = (dm)paramdg;
      paramdg.a(parambl.a);
      paramdg.a(parambl.b);
      paramdg.a(parambl.c);
      paramdg.a(parambl.d);
      Object localObject = new BitSet();
      if (parambl.t())
        ((BitSet)localObject).set(0);
      if (parambl.y())
        ((BitSet)localObject).set(1);
      if (parambl.B())
        ((BitSet)localObject).set(2);
      paramdg.a((BitSet)localObject, 3);
      if (parambl.t())
      {
        paramdg.a(parambl.e.size());
        localObject = parambl.e.iterator();
        while (((Iterator)localObject).hasNext())
          ((bg)((Iterator)localObject).next()).b(paramdg);
      }
      if (parambl.y())
      {
        paramdg.a(parambl.f.size());
        localObject = parambl.f.iterator();
        while (((Iterator)localObject).hasNext())
          ((be)((Iterator)localObject).next()).b(paramdg);
      }
      if (parambl.B())
        parambl.g.b(paramdg);
    }

    public void b(dg paramdg, bl parambl)
      throws cn
    {
      int j = 0;
      paramdg = (dm)paramdg;
      parambl.a = paramdg.z();
      parambl.a(true);
      parambl.b = paramdg.x();
      parambl.b(true);
      parambl.c = paramdg.x();
      parambl.c(true);
      parambl.d = paramdg.x();
      parambl.d(true);
      BitSet localBitSet = paramdg.b(3);
      dc localdc;
      int i;
      Object localObject;
      if (localBitSet.get(0))
      {
        localdc = new dc((byte)12, paramdg.w());
        parambl.e = new ArrayList(localdc.b);
        i = 0;
        while (i < localdc.b)
        {
          localObject = new bg();
          ((bg)localObject).a(paramdg);
          parambl.e.add(localObject);
          i += 1;
        }
        parambl.e(true);
      }
      if (localBitSet.get(1))
      {
        localdc = new dc((byte)12, paramdg.w());
        parambl.f = new ArrayList(localdc.b);
        i = j;
        while (i < localdc.b)
        {
          localObject = new be();
          ((be)localObject).a(paramdg);
          parambl.f.add(localObject);
          i += 1;
        }
        parambl.f(true);
      }
      if (localBitSet.get(2))
      {
        parambl.g = new bm();
        parambl.g.a(paramdg);
        parambl.g(true);
      }
    }
  }

  private static class d
    implements dp
  {
    public bl.c a()
    {
      return new bl.c(null);
    }
  }

  public static enum e
    implements co
  {
    private static final Map<String, e> h;
    private final short i;
    private final String j;

    static
    {
      h = new HashMap();
      Iterator localIterator = EnumSet.allOf(e.class).iterator();
      while (localIterator.hasNext())
      {
        e locale = (e)localIterator.next();
        h.put(locale.b(), locale);
      }
    }

    private e(short paramShort, String paramString)
    {
      this.i = paramShort;
      this.j = paramString;
    }

    public static e a(int paramInt)
    {
      switch (paramInt)
      {
      default:
        return null;
      case 1:
        return a;
      case 2:
        return b;
      case 3:
        return c;
      case 4:
        return d;
      case 5:
        return e;
      case 6:
        return f;
      case 7:
      }
      return g;
    }

    public static e a(String paramString)
    {
      return (e)h.get(paramString);
    }

    public static e b(int paramInt)
    {
      e locale = a(paramInt);
      if (locale == null)
        throw new IllegalArgumentException("Field " + paramInt + " doesn't exist!");
      return locale;
    }

    public short a()
    {
      return this.i;
    }

    public String b()
    {
      return this.j;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     u.aly.bl
 * JD-Core Version:    0.6.2
 */