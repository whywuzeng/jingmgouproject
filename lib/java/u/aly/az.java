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
import java.util.Map.Entry;
import java.util.Set;

public class az
  implements Serializable, Cloneable, ch<az, e>
{
  public static final Map<e, ct> d;
  private static final dl e = new dl("IdTracking");
  private static final db f = new db("snapshots", (byte)13, (short)1);
  private static final db g = new db("journals", (byte)15, (short)2);
  private static final db h = new db("checksum", (byte)11, (short)3);
  private static final Map<Class<? extends do>, dp> i = new HashMap();
  public Map<String, ay> a;
  public List<ax> b;
  public String c;
  private e[] j = { e.b, e.c };

  static
  {
    i.put(dq.class, new b(null));
    i.put(dr.class, new d(null));
    EnumMap localEnumMap = new EnumMap(e.class);
    localEnumMap.put(e.a, new ct("snapshots", (byte)1, new cw((byte)13, new cu((byte)11), new cy((byte)12, ay.class))));
    localEnumMap.put(e.b, new ct("journals", (byte)2, new cv((byte)15, new cy((byte)12, ax.class))));
    localEnumMap.put(e.c, new ct("checksum", (byte)2, new cu((byte)11)));
    d = Collections.unmodifiableMap(localEnumMap);
    ct.a(az.class, d);
  }

  public az()
  {
  }

  public az(Map<String, ay> paramMap)
  {
    this();
    this.a = paramMap;
  }

  public az(az paramaz)
  {
    Object localObject;
    Iterator localIterator;
    if (paramaz.f())
    {
      localObject = new HashMap();
      localIterator = paramaz.a.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        ((Map)localObject).put((String)localEntry.getKey(), new ay((ay)localEntry.getValue()));
      }
      this.a = ((Map)localObject);
    }
    if (paramaz.l())
    {
      localObject = new ArrayList();
      localIterator = paramaz.b.iterator();
      while (localIterator.hasNext())
        ((List)localObject).add(new ax((ax)localIterator.next()));
      this.b = ((List)localObject);
    }
    if (paramaz.o())
      this.c = paramaz.c;
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

  public az a()
  {
    return new az(this);
  }

  public az a(String paramString)
  {
    this.c = paramString;
    return this;
  }

  public az a(List<ax> paramList)
  {
    this.b = paramList;
    return this;
  }

  public az a(Map<String, ay> paramMap)
  {
    this.a = paramMap;
    return this;
  }

  public void a(String paramString, ay paramay)
  {
    if (this.a == null)
      this.a = new HashMap();
    this.a.put(paramString, paramay);
  }

  public void a(ax paramax)
  {
    if (this.b == null)
      this.b = new ArrayList();
    this.b.add(paramax);
  }

  public void a(dg paramdg)
    throws cn
  {
    ((dp)i.get(paramdg.D())).b().b(paramdg, this);
  }

  public void a(boolean paramBoolean)
  {
    if (!paramBoolean)
      this.a = null;
  }

  public void b()
  {
    this.a = null;
    this.b = null;
    this.c = null;
  }

  public void b(dg paramdg)
    throws cn
  {
    ((dp)i.get(paramdg.D())).b().a(paramdg, this);
  }

  public void b(boolean paramBoolean)
  {
    if (!paramBoolean)
      this.b = null;
  }

  public int c()
  {
    if (this.a == null)
      return 0;
    return this.a.size();
  }

  public void c(boolean paramBoolean)
  {
    if (!paramBoolean)
      this.c = null;
  }

  public Map<String, ay> d()
  {
    return this.a;
  }

  public void e()
  {
    this.a = null;
  }

  public boolean f()
  {
    return this.a != null;
  }

  public int h()
  {
    if (this.b == null)
      return 0;
    return this.b.size();
  }

  public Iterator<ax> i()
  {
    if (this.b == null)
      return null;
    return this.b.iterator();
  }

  public List<ax> j()
  {
    return this.b;
  }

  public void k()
  {
    this.b = null;
  }

  public boolean l()
  {
    return this.b != null;
  }

  public String m()
  {
    return this.c;
  }

  public void n()
  {
    this.c = null;
  }

  public boolean o()
  {
    return this.c != null;
  }

  public void p()
    throws cn
  {
    if (this.a == null)
      throw new dh("Required field 'snapshots' was not present! Struct: " + toString());
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("IdTracking(");
    localStringBuilder.append("snapshots:");
    if (this.a == null)
    {
      localStringBuilder.append("null");
      if (l())
      {
        localStringBuilder.append(", ");
        localStringBuilder.append("journals:");
        if (this.b != null)
          break label135;
        localStringBuilder.append("null");
      }
      label72: if (o())
      {
        localStringBuilder.append(", ");
        localStringBuilder.append("checksum:");
        if (this.c != null)
          break label147;
        localStringBuilder.append("null");
      }
    }
    while (true)
    {
      localStringBuilder.append(")");
      return localStringBuilder.toString();
      localStringBuilder.append(this.a);
      break;
      label135: localStringBuilder.append(this.b);
      break label72;
      label147: localStringBuilder.append(this.c);
    }
  }

  private static class a extends dq<az>
  {
    public void a(dg paramdg, az paramaz)
      throws cn
    {
      paramdg.j();
      Object localObject1 = paramdg.l();
      if (((db)localObject1).b == 0)
      {
        paramdg.k();
        paramaz.p();
        return;
      }
      switch (((db)localObject1).c)
      {
      default:
        dj.a(paramdg, ((db)localObject1).b);
      case 1:
      case 2:
      case 3:
      }
      while (true)
      {
        paramdg.m();
        break;
        int i;
        Object localObject2;
        if (((db)localObject1).b == 13)
        {
          localObject1 = paramdg.n();
          paramaz.a = new HashMap(((dd)localObject1).c * 2);
          i = 0;
          while (i < ((dd)localObject1).c)
          {
            localObject2 = paramdg.z();
            ay localay = new ay();
            localay.a(paramdg);
            paramaz.a.put(localObject2, localay);
            i += 1;
          }
          paramdg.o();
          paramaz.a(true);
        }
        else
        {
          dj.a(paramdg, ((db)localObject1).b);
          continue;
          if (((db)localObject1).b == 15)
          {
            localObject1 = paramdg.p();
            paramaz.b = new ArrayList(((dc)localObject1).b);
            i = 0;
            while (i < ((dc)localObject1).b)
            {
              localObject2 = new ax();
              ((ax)localObject2).a(paramdg);
              paramaz.b.add(localObject2);
              i += 1;
            }
            paramdg.q();
            paramaz.b(true);
          }
          else
          {
            dj.a(paramdg, ((db)localObject1).b);
            continue;
            if (((db)localObject1).b == 11)
            {
              paramaz.c = paramdg.z();
              paramaz.c(true);
            }
            else
            {
              dj.a(paramdg, ((db)localObject1).b);
            }
          }
        }
      }
    }

    public void b(dg paramdg, az paramaz)
      throws cn
    {
      paramaz.p();
      paramdg.a(az.q());
      Iterator localIterator;
      if (paramaz.a != null)
      {
        paramdg.a(az.r());
        paramdg.a(new dd((byte)11, (byte)12, paramaz.a.size()));
        localIterator = paramaz.a.entrySet().iterator();
        while (localIterator.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)localIterator.next();
          paramdg.a((String)localEntry.getKey());
          ((ay)localEntry.getValue()).b(paramdg);
        }
        paramdg.e();
        paramdg.c();
      }
      if ((paramaz.b != null) && (paramaz.l()))
      {
        paramdg.a(az.s());
        paramdg.a(new dc((byte)12, paramaz.b.size()));
        localIterator = paramaz.b.iterator();
        while (localIterator.hasNext())
          ((ax)localIterator.next()).b(paramdg);
        paramdg.f();
        paramdg.c();
      }
      if ((paramaz.c != null) && (paramaz.o()))
      {
        paramdg.a(az.t());
        paramdg.a(paramaz.c);
        paramdg.c();
      }
      paramdg.d();
      paramdg.b();
    }
  }

  private static class b
    implements dp
  {
    public az.a a()
    {
      return new az.a(null);
    }
  }

  private static class c extends dr<az>
  {
    public void a(dg paramdg, az paramaz)
      throws cn
    {
      paramdg = (dm)paramdg;
      paramdg.a(paramaz.a.size());
      Object localObject = paramaz.a.entrySet().iterator();
      while (((Iterator)localObject).hasNext())
      {
        Map.Entry localEntry = (Map.Entry)((Iterator)localObject).next();
        paramdg.a((String)localEntry.getKey());
        ((ay)localEntry.getValue()).b(paramdg);
      }
      localObject = new BitSet();
      if (paramaz.l())
        ((BitSet)localObject).set(0);
      if (paramaz.o())
        ((BitSet)localObject).set(1);
      paramdg.a((BitSet)localObject, 2);
      if (paramaz.l())
      {
        paramdg.a(paramaz.b.size());
        localObject = paramaz.b.iterator();
        while (((Iterator)localObject).hasNext())
          ((ax)((Iterator)localObject).next()).b(paramdg);
      }
      if (paramaz.o())
        paramdg.a(paramaz.c);
    }

    public void b(dg paramdg, az paramaz)
      throws cn
    {
      int j = 0;
      paramdg = (dm)paramdg;
      Object localObject1 = new dd((byte)11, (byte)12, paramdg.w());
      paramaz.a = new HashMap(((dd)localObject1).c * 2);
      int i = 0;
      Object localObject2;
      Object localObject3;
      while (i < ((dd)localObject1).c)
      {
        localObject2 = paramdg.z();
        localObject3 = new ay();
        ((ay)localObject3).a(paramdg);
        paramaz.a.put(localObject2, localObject3);
        i += 1;
      }
      paramaz.a(true);
      localObject1 = paramdg.b(2);
      if (((BitSet)localObject1).get(0))
      {
        localObject2 = new dc((byte)12, paramdg.w());
        paramaz.b = new ArrayList(((dc)localObject2).b);
        i = j;
        while (i < ((dc)localObject2).b)
        {
          localObject3 = new ax();
          ((ax)localObject3).a(paramdg);
          paramaz.b.add(localObject3);
          i += 1;
        }
        paramaz.b(true);
      }
      if (((BitSet)localObject1).get(1))
      {
        paramaz.c = paramdg.z();
        paramaz.c(true);
      }
    }
  }

  private static class d
    implements dp
  {
    public az.c a()
    {
      return new az.c(null);
    }
  }

  public static enum e
    implements co
  {
    private static final Map<String, e> d;
    private final short e;
    private final String f;

    static
    {
      d = new HashMap();
      Iterator localIterator = EnumSet.allOf(e.class).iterator();
      while (localIterator.hasNext())
      {
        e locale = (e)localIterator.next();
        d.put(locale.b(), locale);
      }
    }

    private e(short paramShort, String paramString)
    {
      this.e = paramShort;
      this.f = paramString;
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
      }
      return c;
    }

    public static e a(String paramString)
    {
      return (e)d.get(paramString);
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
      return this.e;
    }

    public String b()
    {
      return this.f;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     u.aly.az
 * JD-Core Version:    0.6.2
 */