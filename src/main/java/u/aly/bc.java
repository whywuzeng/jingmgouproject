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

public class bc
  implements Serializable, Cloneable, ch<bc, e>
{
  public static final Map<e, ct> e;
  private static final dl f = new dl("InstantMsg");
  private static final db g = new db("id", (byte)11, (short)1);
  private static final db h = new db("errors", (byte)15, (short)2);
  private static final db i = new db("events", (byte)15, (short)3);
  private static final db j = new db("game_events", (byte)15, (short)4);
  private static final Map<Class<? extends do>, dp> k = new HashMap();
  public String a;
  public List<at> b;
  public List<av> c;
  public List<av> d;
  private e[] l = { e.b, e.c, e.d };

  static
  {
    k.put(dq.class, new b(null));
    k.put(dr.class, new d(null));
    EnumMap localEnumMap = new EnumMap(e.class);
    localEnumMap.put(e.a, new ct("id", (byte)1, new cu((byte)11)));
    localEnumMap.put(e.b, new ct("errors", (byte)2, new cv((byte)15, new cy((byte)12, at.class))));
    localEnumMap.put(e.c, new ct("events", (byte)2, new cv((byte)15, new cy((byte)12, av.class))));
    localEnumMap.put(e.d, new ct("game_events", (byte)2, new cv((byte)15, new cy((byte)12, av.class))));
    e = Collections.unmodifiableMap(localEnumMap);
    ct.a(bc.class, e);
  }

  public bc()
  {
  }

  public bc(String paramString)
  {
    this();
    this.a = paramString;
  }

  public bc(bc parambc)
  {
    if (parambc.e())
      this.a = parambc.a;
    ArrayList localArrayList;
    Iterator localIterator;
    if (parambc.k())
    {
      localArrayList = new ArrayList();
      localIterator = parambc.b.iterator();
      while (localIterator.hasNext())
        localArrayList.add(new at((at)localIterator.next()));
      this.b = localArrayList;
    }
    if (parambc.p())
    {
      localArrayList = new ArrayList();
      localIterator = parambc.c.iterator();
      while (localIterator.hasNext())
        localArrayList.add(new av((av)localIterator.next()));
      this.c = localArrayList;
    }
    if (parambc.u())
    {
      localArrayList = new ArrayList();
      parambc = parambc.d.iterator();
      while (parambc.hasNext())
        localArrayList.add(new av((av)parambc.next()));
      this.d = localArrayList;
    }
  }

  private void a(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    try
    {
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

  public e a(int paramInt)
  {
    return e.a(paramInt);
  }

  public bc a()
  {
    return new bc(this);
  }

  public bc a(String paramString)
  {
    this.a = paramString;
    return this;
  }

  public bc a(List<at> paramList)
  {
    this.b = paramList;
    return this;
  }

  public void a(at paramat)
  {
    if (this.b == null)
      this.b = new ArrayList();
    this.b.add(paramat);
  }

  public void a(av paramav)
  {
    if (this.c == null)
      this.c = new ArrayList();
    this.c.add(paramav);
  }

  public void a(dg paramdg)
    throws cn
  {
    ((dp)k.get(paramdg.D())).b().b(paramdg, this);
  }

  public void a(boolean paramBoolean)
  {
    if (!paramBoolean)
      this.a = null;
  }

  public bc b(List<av> paramList)
  {
    this.c = paramList;
    return this;
  }

  public void b()
  {
    this.a = null;
    this.b = null;
    this.c = null;
    this.d = null;
  }

  public void b(av paramav)
  {
    if (this.d == null)
      this.d = new ArrayList();
    this.d.add(paramav);
  }

  public void b(dg paramdg)
    throws cn
  {
    ((dp)k.get(paramdg.D())).b().a(paramdg, this);
  }

  public void b(boolean paramBoolean)
  {
    if (!paramBoolean)
      this.b = null;
  }

  public String c()
  {
    return this.a;
  }

  public bc c(List<av> paramList)
  {
    this.d = paramList;
    return this;
  }

  public void c(boolean paramBoolean)
  {
    if (!paramBoolean)
      this.c = null;
  }

  public void d()
  {
    this.a = null;
  }

  public void d(boolean paramBoolean)
  {
    if (!paramBoolean)
      this.d = null;
  }

  public boolean e()
  {
    return this.a != null;
  }

  public int f()
  {
    if (this.b == null)
      return 0;
    return this.b.size();
  }

  public Iterator<at> h()
  {
    if (this.b == null)
      return null;
    return this.b.iterator();
  }

  public List<at> i()
  {
    return this.b;
  }

  public void j()
  {
    this.b = null;
  }

  public boolean k()
  {
    return this.b != null;
  }

  public int l()
  {
    if (this.c == null)
      return 0;
    return this.c.size();
  }

  public Iterator<av> m()
  {
    if (this.c == null)
      return null;
    return this.c.iterator();
  }

  public List<av> n()
  {
    return this.c;
  }

  public void o()
  {
    this.c = null;
  }

  public boolean p()
  {
    return this.c != null;
  }

  public int q()
  {
    if (this.d == null)
      return 0;
    return this.d.size();
  }

  public Iterator<av> r()
  {
    if (this.d == null)
      return null;
    return this.d.iterator();
  }

  public List<av> s()
  {
    return this.d;
  }

  public void t()
  {
    this.d = null;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("InstantMsg(");
    localStringBuilder.append("id:");
    if (this.a == null)
    {
      localStringBuilder.append("null");
      if (k())
      {
        localStringBuilder.append(", ");
        localStringBuilder.append("errors:");
        if (this.b != null)
          break label173;
        localStringBuilder.append("null");
      }
      label72: if (p())
      {
        localStringBuilder.append(", ");
        localStringBuilder.append("events:");
        if (this.c != null)
          break label185;
        localStringBuilder.append("null");
      }
      label110: if (u())
      {
        localStringBuilder.append(", ");
        localStringBuilder.append("game_events:");
        if (this.d != null)
          break label197;
        localStringBuilder.append("null");
      }
    }
    while (true)
    {
      localStringBuilder.append(")");
      return localStringBuilder.toString();
      localStringBuilder.append(this.a);
      break;
      label173: localStringBuilder.append(this.b);
      break label72;
      label185: localStringBuilder.append(this.c);
      break label110;
      label197: localStringBuilder.append(this.d);
    }
  }

  public boolean u()
  {
    return this.d != null;
  }

  public void v()
    throws cn
  {
    if (this.a == null)
      throw new dh("Required field 'id' was not present! Struct: " + toString());
  }

  private static class a extends dq<bc>
  {
    public void a(dg paramdg, bc parambc)
      throws cn
    {
      paramdg.j();
      Object localObject1 = paramdg.l();
      if (((db)localObject1).b == 0)
      {
        paramdg.k();
        parambc.v();
        return;
      }
      switch (((db)localObject1).c)
      {
      default:
        dj.a(paramdg, ((db)localObject1).b);
      case 1:
      case 2:
      case 3:
      case 4:
      }
      while (true)
      {
        paramdg.m();
        break;
        if (((db)localObject1).b == 11)
        {
          parambc.a = paramdg.z();
          parambc.a(true);
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
            parambc.b = new ArrayList(((dc)localObject1).b);
            i = 0;
            while (i < ((dc)localObject1).b)
            {
              localObject2 = new at();
              ((at)localObject2).a(paramdg);
              parambc.b.add(localObject2);
              i += 1;
            }
            paramdg.q();
            parambc.b(true);
          }
          else
          {
            dj.a(paramdg, ((db)localObject1).b);
            continue;
            if (((db)localObject1).b == 15)
            {
              localObject1 = paramdg.p();
              parambc.c = new ArrayList(((dc)localObject1).b);
              i = 0;
              while (i < ((dc)localObject1).b)
              {
                localObject2 = new av();
                ((av)localObject2).a(paramdg);
                parambc.c.add(localObject2);
                i += 1;
              }
              paramdg.q();
              parambc.c(true);
            }
            else
            {
              dj.a(paramdg, ((db)localObject1).b);
              continue;
              if (((db)localObject1).b == 15)
              {
                localObject1 = paramdg.p();
                parambc.d = new ArrayList(((dc)localObject1).b);
                i = 0;
                while (i < ((dc)localObject1).b)
                {
                  localObject2 = new av();
                  ((av)localObject2).a(paramdg);
                  parambc.d.add(localObject2);
                  i += 1;
                }
                paramdg.q();
                parambc.d(true);
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

    public void b(dg paramdg, bc parambc)
      throws cn
    {
      parambc.v();
      paramdg.a(bc.w());
      if (parambc.a != null)
      {
        paramdg.a(bc.x());
        paramdg.a(parambc.a);
        paramdg.c();
      }
      Iterator localIterator;
      if ((parambc.b != null) && (parambc.k()))
      {
        paramdg.a(bc.y());
        paramdg.a(new dc((byte)12, parambc.b.size()));
        localIterator = parambc.b.iterator();
        while (localIterator.hasNext())
          ((at)localIterator.next()).b(paramdg);
        paramdg.f();
        paramdg.c();
      }
      if ((parambc.c != null) && (parambc.p()))
      {
        paramdg.a(bc.z());
        paramdg.a(new dc((byte)12, parambc.c.size()));
        localIterator = parambc.c.iterator();
        while (localIterator.hasNext())
          ((av)localIterator.next()).b(paramdg);
        paramdg.f();
        paramdg.c();
      }
      if ((parambc.d != null) && (parambc.u()))
      {
        paramdg.a(bc.A());
        paramdg.a(new dc((byte)12, parambc.d.size()));
        parambc = parambc.d.iterator();
        while (parambc.hasNext())
          ((av)parambc.next()).b(paramdg);
        paramdg.f();
        paramdg.c();
      }
      paramdg.d();
      paramdg.b();
    }
  }

  private static class b
    implements dp
  {
    public bc.a a()
    {
      return new bc.a(null);
    }
  }

  private static class c extends dr<bc>
  {
    public void a(dg paramdg, bc parambc)
      throws cn
    {
      paramdg = (dm)paramdg;
      paramdg.a(parambc.a);
      Object localObject = new BitSet();
      if (parambc.k())
        ((BitSet)localObject).set(0);
      if (parambc.p())
        ((BitSet)localObject).set(1);
      if (parambc.u())
        ((BitSet)localObject).set(2);
      paramdg.a((BitSet)localObject, 3);
      if (parambc.k())
      {
        paramdg.a(parambc.b.size());
        localObject = parambc.b.iterator();
        while (((Iterator)localObject).hasNext())
          ((at)((Iterator)localObject).next()).b(paramdg);
      }
      if (parambc.p())
      {
        paramdg.a(parambc.c.size());
        localObject = parambc.c.iterator();
        while (((Iterator)localObject).hasNext())
          ((av)((Iterator)localObject).next()).b(paramdg);
      }
      if (parambc.u())
      {
        paramdg.a(parambc.d.size());
        parambc = parambc.d.iterator();
        while (parambc.hasNext())
          ((av)parambc.next()).b(paramdg);
      }
    }

    public void b(dg paramdg, bc parambc)
      throws cn
    {
      int j = 0;
      paramdg = (dm)paramdg;
      parambc.a = paramdg.z();
      parambc.a(true);
      Object localObject1 = paramdg.b(3);
      Object localObject2;
      int i;
      Object localObject3;
      if (((BitSet)localObject1).get(0))
      {
        localObject2 = new dc((byte)12, paramdg.w());
        parambc.b = new ArrayList(((dc)localObject2).b);
        i = 0;
        while (i < ((dc)localObject2).b)
        {
          localObject3 = new at();
          ((at)localObject3).a(paramdg);
          parambc.b.add(localObject3);
          i += 1;
        }
        parambc.b(true);
      }
      if (((BitSet)localObject1).get(1))
      {
        localObject2 = new dc((byte)12, paramdg.w());
        parambc.c = new ArrayList(((dc)localObject2).b);
        i = 0;
        while (i < ((dc)localObject2).b)
        {
          localObject3 = new av();
          ((av)localObject3).a(paramdg);
          parambc.c.add(localObject3);
          i += 1;
        }
        parambc.c(true);
      }
      if (((BitSet)localObject1).get(2))
      {
        localObject1 = new dc((byte)12, paramdg.w());
        parambc.d = new ArrayList(((dc)localObject1).b);
        i = j;
        while (i < ((dc)localObject1).b)
        {
          localObject2 = new av();
          ((av)localObject2).a(paramdg);
          parambc.d.add(localObject2);
          i += 1;
        }
        parambc.d(true);
      }
    }
  }

  private static class d
    implements dp
  {
    public bc.c a()
    {
      return new bc.c(null);
    }
  }

  public static enum e
    implements co
  {
    private static final Map<String, e> e;
    private final short f;
    private final String g;

    static
    {
      e = new HashMap();
      Iterator localIterator = EnumSet.allOf(e.class).iterator();
      while (localIterator.hasNext())
      {
        e locale = (e)localIterator.next();
        e.put(locale.b(), locale);
      }
    }

    private e(short paramShort, String paramString)
    {
      this.f = paramShort;
      this.g = paramString;
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
      }
      return d;
    }

    public static e a(String paramString)
    {
      return (e)e.get(paramString);
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
      return this.f;
    }

    public String b()
    {
      return this.g;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     u.aly.bc
 * JD-Core Version:    0.6.2
 */