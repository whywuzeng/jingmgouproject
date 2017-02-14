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

public class bn
  implements Serializable, Cloneable, ch<bn, e>
{
  public static final Map<e, ct> l;
  private static final dl m = new dl("UALogEntry");
  private static final db n = new db("client_stats", (byte)12, (short)1);
  private static final db o = new db("app_info", (byte)12, (short)2);
  private static final db p = new db("device_info", (byte)12, (short)3);
  private static final db q = new db("misc_info", (byte)12, (short)4);
  private static final db r = new db("activate_msg", (byte)12, (short)5);
  private static final db s = new db("instant_msgs", (byte)15, (short)6);
  private static final db t = new db("sessions", (byte)15, (short)7);
  private static final db u = new db("imprint", (byte)12, (short)8);
  private static final db v = new db("id_tracking", (byte)12, (short)9);
  private static final db w = new db("active_user", (byte)12, (short)10);
  private static final db x = new db("control_policy", (byte)12, (short)11);
  private static final Map<Class<? extends do>, dp> y = new HashMap();
  public ap a;
  public ao b;
  public ar c;
  public bf d;
  public am e;
  public List<bc> f;
  public List<bl> g;
  public ba h;
  public az i;
  public an j;
  public aq k;
  private e[] z = { e.e, e.f, e.g, e.h, e.i, e.j, e.k };

  static
  {
    y.put(dq.class, new b(null));
    y.put(dr.class, new d(null));
    EnumMap localEnumMap = new EnumMap(e.class);
    localEnumMap.put(e.a, new ct("client_stats", (byte)1, new cy((byte)12, ap.class)));
    localEnumMap.put(e.b, new ct("app_info", (byte)1, new cy((byte)12, ao.class)));
    localEnumMap.put(e.c, new ct("device_info", (byte)1, new cy((byte)12, ar.class)));
    localEnumMap.put(e.d, new ct("misc_info", (byte)1, new cy((byte)12, bf.class)));
    localEnumMap.put(e.e, new ct("activate_msg", (byte)2, new cy((byte)12, am.class)));
    localEnumMap.put(e.f, new ct("instant_msgs", (byte)2, new cv((byte)15, new cy((byte)12, bc.class))));
    localEnumMap.put(e.g, new ct("sessions", (byte)2, new cv((byte)15, new cy((byte)12, bl.class))));
    localEnumMap.put(e.h, new ct("imprint", (byte)2, new cy((byte)12, ba.class)));
    localEnumMap.put(e.i, new ct("id_tracking", (byte)2, new cy((byte)12, az.class)));
    localEnumMap.put(e.j, new ct("active_user", (byte)2, new cy((byte)12, an.class)));
    localEnumMap.put(e.k, new ct("control_policy", (byte)2, new cy((byte)12, aq.class)));
    l = Collections.unmodifiableMap(localEnumMap);
    ct.a(bn.class, l);
  }

  public bn()
  {
  }

  public bn(ap paramap, ao paramao, ar paramar, bf parambf)
  {
    this();
    this.a = paramap;
    this.b = paramao;
    this.c = paramar;
    this.d = parambf;
  }

  public bn(bn parambn)
  {
    if (parambn.e())
      this.a = new ap(parambn.a);
    if (parambn.i())
      this.b = new ao(parambn.b);
    if (parambn.l())
      this.c = new ar(parambn.c);
    if (parambn.o())
      this.d = new bf(parambn.d);
    if (parambn.r())
      this.e = new am(parambn.e);
    ArrayList localArrayList;
    Iterator localIterator;
    if (parambn.w())
    {
      localArrayList = new ArrayList();
      localIterator = parambn.f.iterator();
      while (localIterator.hasNext())
        localArrayList.add(new bc((bc)localIterator.next()));
      this.f = localArrayList;
    }
    if (parambn.B())
    {
      localArrayList = new ArrayList();
      localIterator = parambn.g.iterator();
      while (localIterator.hasNext())
        localArrayList.add(new bl((bl)localIterator.next()));
      this.g = localArrayList;
    }
    if (parambn.E())
      this.h = new ba(parambn.h);
    if (parambn.H())
      this.i = new az(parambn.i);
    if (parambn.K())
      this.j = new an(parambn.j);
    if (parambn.N())
      this.k = new aq(parambn.k);
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

  public void A()
  {
    this.g = null;
  }

  public boolean B()
  {
    return this.g != null;
  }

  public ba C()
  {
    return this.h;
  }

  public void D()
  {
    this.h = null;
  }

  public boolean E()
  {
    return this.h != null;
  }

  public az F()
  {
    return this.i;
  }

  public void G()
  {
    this.i = null;
  }

  public boolean H()
  {
    return this.i != null;
  }

  public an I()
  {
    return this.j;
  }

  public void J()
  {
    this.j = null;
  }

  public boolean K()
  {
    return this.j != null;
  }

  public aq L()
  {
    return this.k;
  }

  public void M()
  {
    this.k = null;
  }

  public boolean N()
  {
    return this.k != null;
  }

  public void O()
    throws cn
  {
    if (this.a == null)
      throw new dh("Required field 'client_stats' was not present! Struct: " + toString());
    if (this.b == null)
      throw new dh("Required field 'app_info' was not present! Struct: " + toString());
    if (this.c == null)
      throw new dh("Required field 'device_info' was not present! Struct: " + toString());
    if (this.d == null)
      throw new dh("Required field 'misc_info' was not present! Struct: " + toString());
    if (this.a != null)
      this.a.m();
    if (this.b != null)
      this.b.H();
    if (this.c != null)
      this.c.ac();
    if (this.d != null)
      this.d.H();
    if (this.e != null)
      this.e.f();
    if (this.h != null)
      this.h.n();
    if (this.i != null)
      this.i.p();
    if (this.j != null)
      this.j.j();
    if (this.k != null)
      this.k.f();
  }

  public e a(int paramInt)
  {
    return e.a(paramInt);
  }

  public bn a()
  {
    return new bn(this);
  }

  public bn a(List<bc> paramList)
  {
    this.f = paramList;
    return this;
  }

  public bn a(am paramam)
  {
    this.e = paramam;
    return this;
  }

  public bn a(an paraman)
  {
    this.j = paraman;
    return this;
  }

  public bn a(ao paramao)
  {
    this.b = paramao;
    return this;
  }

  public bn a(ap paramap)
  {
    this.a = paramap;
    return this;
  }

  public bn a(aq paramaq)
  {
    this.k = paramaq;
    return this;
  }

  public bn a(ar paramar)
  {
    this.c = paramar;
    return this;
  }

  public bn a(az paramaz)
  {
    this.i = paramaz;
    return this;
  }

  public bn a(ba paramba)
  {
    this.h = paramba;
    return this;
  }

  public bn a(bf parambf)
  {
    this.d = parambf;
    return this;
  }

  public void a(bc parambc)
  {
    if (this.f == null)
      this.f = new ArrayList();
    this.f.add(parambc);
  }

  public void a(bl parambl)
  {
    if (this.g == null)
      this.g = new ArrayList();
    this.g.add(parambl);
  }

  public void a(dg paramdg)
    throws cn
  {
    ((dp)y.get(paramdg.D())).b().b(paramdg, this);
  }

  public void a(boolean paramBoolean)
  {
    if (!paramBoolean)
      this.a = null;
  }

  public bn b(List<bl> paramList)
  {
    this.g = paramList;
    return this;
  }

  public void b()
  {
    this.a = null;
    this.b = null;
    this.c = null;
    this.d = null;
    this.e = null;
    this.f = null;
    this.g = null;
    this.h = null;
    this.i = null;
    this.j = null;
    this.k = null;
  }

  public void b(dg paramdg)
    throws cn
  {
    ((dp)y.get(paramdg.D())).b().a(paramdg, this);
  }

  public void b(boolean paramBoolean)
  {
    if (!paramBoolean)
      this.b = null;
  }

  public ap c()
  {
    return this.a;
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

  public void e(boolean paramBoolean)
  {
    if (!paramBoolean)
      this.e = null;
  }

  public boolean e()
  {
    return this.a != null;
  }

  public ao f()
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
    this.b = null;
  }

  public void h(boolean paramBoolean)
  {
    if (!paramBoolean)
      this.h = null;
  }

  public void i(boolean paramBoolean)
  {
    if (!paramBoolean)
      this.i = null;
  }

  public boolean i()
  {
    return this.b != null;
  }

  public ar j()
  {
    return this.c;
  }

  public void j(boolean paramBoolean)
  {
    if (!paramBoolean)
      this.j = null;
  }

  public void k()
  {
    this.c = null;
  }

  public void k(boolean paramBoolean)
  {
    if (!paramBoolean)
      this.k = null;
  }

  public boolean l()
  {
    return this.c != null;
  }

  public bf m()
  {
    return this.d;
  }

  public void n()
  {
    this.d = null;
  }

  public boolean o()
  {
    return this.d != null;
  }

  public am p()
  {
    return this.e;
  }

  public void q()
  {
    this.e = null;
  }

  public boolean r()
  {
    return this.e != null;
  }

  public int s()
  {
    if (this.f == null)
      return 0;
    return this.f.size();
  }

  public Iterator<bc> t()
  {
    if (this.f == null)
      return null;
    return this.f.iterator();
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("UALogEntry(");
    localStringBuilder.append("client_stats:");
    if (this.a == null)
    {
      localStringBuilder.append("null");
      localStringBuilder.append(", ");
      localStringBuilder.append("app_info:");
      if (this.b != null)
        break label418;
      localStringBuilder.append("null");
      label65: localStringBuilder.append(", ");
      localStringBuilder.append("device_info:");
      if (this.c != null)
        break label430;
      localStringBuilder.append("null");
      label96: localStringBuilder.append(", ");
      localStringBuilder.append("misc_info:");
      if (this.d != null)
        break label442;
      localStringBuilder.append("null");
      label127: if (r())
      {
        localStringBuilder.append(", ");
        localStringBuilder.append("activate_msg:");
        if (this.e != null)
          break label454;
        localStringBuilder.append("null");
      }
      label165: if (w())
      {
        localStringBuilder.append(", ");
        localStringBuilder.append("instant_msgs:");
        if (this.f != null)
          break label466;
        localStringBuilder.append("null");
      }
      label203: if (B())
      {
        localStringBuilder.append(", ");
        localStringBuilder.append("sessions:");
        if (this.g != null)
          break label478;
        localStringBuilder.append("null");
      }
      label241: if (E())
      {
        localStringBuilder.append(", ");
        localStringBuilder.append("imprint:");
        if (this.h != null)
          break label490;
        localStringBuilder.append("null");
      }
      label279: if (H())
      {
        localStringBuilder.append(", ");
        localStringBuilder.append("id_tracking:");
        if (this.i != null)
          break label502;
        localStringBuilder.append("null");
      }
      label317: if (K())
      {
        localStringBuilder.append(", ");
        localStringBuilder.append("active_user:");
        if (this.j != null)
          break label514;
        localStringBuilder.append("null");
      }
      label355: if (N())
      {
        localStringBuilder.append(", ");
        localStringBuilder.append("control_policy:");
        if (this.k != null)
          break label526;
        localStringBuilder.append("null");
      }
    }
    while (true)
    {
      localStringBuilder.append(")");
      return localStringBuilder.toString();
      localStringBuilder.append(this.a);
      break;
      label418: localStringBuilder.append(this.b);
      break label65;
      label430: localStringBuilder.append(this.c);
      break label96;
      label442: localStringBuilder.append(this.d);
      break label127;
      label454: localStringBuilder.append(this.e);
      break label165;
      label466: localStringBuilder.append(this.f);
      break label203;
      label478: localStringBuilder.append(this.g);
      break label241;
      label490: localStringBuilder.append(this.h);
      break label279;
      label502: localStringBuilder.append(this.i);
      break label317;
      label514: localStringBuilder.append(this.j);
      break label355;
      label526: localStringBuilder.append(this.k);
    }
  }

  public List<bc> u()
  {
    return this.f;
  }

  public void v()
  {
    this.f = null;
  }

  public boolean w()
  {
    return this.f != null;
  }

  public int x()
  {
    if (this.g == null)
      return 0;
    return this.g.size();
  }

  public Iterator<bl> y()
  {
    if (this.g == null)
      return null;
    return this.g.iterator();
  }

  public List<bl> z()
  {
    return this.g;
  }

  private static class a extends dq<bn>
  {
    public void a(dg paramdg, bn parambn)
      throws cn
    {
      paramdg.j();
      Object localObject1 = paramdg.l();
      if (((db)localObject1).b == 0)
      {
        paramdg.k();
        parambn.O();
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
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
      case 10:
      case 11:
      }
      while (true)
      {
        paramdg.m();
        break;
        if (((db)localObject1).b == 12)
        {
          parambn.a = new ap();
          parambn.a.a(paramdg);
          parambn.a(true);
        }
        else
        {
          dj.a(paramdg, ((db)localObject1).b);
          continue;
          if (((db)localObject1).b == 12)
          {
            parambn.b = new ao();
            parambn.b.a(paramdg);
            parambn.b(true);
          }
          else
          {
            dj.a(paramdg, ((db)localObject1).b);
            continue;
            if (((db)localObject1).b == 12)
            {
              parambn.c = new ar();
              parambn.c.a(paramdg);
              parambn.c(true);
            }
            else
            {
              dj.a(paramdg, ((db)localObject1).b);
              continue;
              if (((db)localObject1).b == 12)
              {
                parambn.d = new bf();
                parambn.d.a(paramdg);
                parambn.d(true);
              }
              else
              {
                dj.a(paramdg, ((db)localObject1).b);
                continue;
                if (((db)localObject1).b == 12)
                {
                  parambn.e = new am();
                  parambn.e.a(paramdg);
                  parambn.e(true);
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
                    parambn.f = new ArrayList(((dc)localObject1).b);
                    i = 0;
                    while (i < ((dc)localObject1).b)
                    {
                      localObject2 = new bc();
                      ((bc)localObject2).a(paramdg);
                      parambn.f.add(localObject2);
                      i += 1;
                    }
                    paramdg.q();
                    parambn.f(true);
                  }
                  else
                  {
                    dj.a(paramdg, ((db)localObject1).b);
                    continue;
                    if (((db)localObject1).b == 15)
                    {
                      localObject1 = paramdg.p();
                      parambn.g = new ArrayList(((dc)localObject1).b);
                      i = 0;
                      while (i < ((dc)localObject1).b)
                      {
                        localObject2 = new bl();
                        ((bl)localObject2).a(paramdg);
                        parambn.g.add(localObject2);
                        i += 1;
                      }
                      paramdg.q();
                      parambn.g(true);
                    }
                    else
                    {
                      dj.a(paramdg, ((db)localObject1).b);
                      continue;
                      if (((db)localObject1).b == 12)
                      {
                        parambn.h = new ba();
                        parambn.h.a(paramdg);
                        parambn.h(true);
                      }
                      else
                      {
                        dj.a(paramdg, ((db)localObject1).b);
                        continue;
                        if (((db)localObject1).b == 12)
                        {
                          parambn.i = new az();
                          parambn.i.a(paramdg);
                          parambn.i(true);
                        }
                        else
                        {
                          dj.a(paramdg, ((db)localObject1).b);
                          continue;
                          if (((db)localObject1).b == 12)
                          {
                            parambn.j = new an();
                            parambn.j.a(paramdg);
                            parambn.j(true);
                          }
                          else
                          {
                            dj.a(paramdg, ((db)localObject1).b);
                            continue;
                            if (((db)localObject1).b == 12)
                            {
                              parambn.k = new aq();
                              parambn.k.a(paramdg);
                              parambn.k(true);
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
          }
        }
      }
    }

    public void b(dg paramdg, bn parambn)
      throws cn
    {
      parambn.O();
      paramdg.a(bn.P());
      if (parambn.a != null)
      {
        paramdg.a(bn.Q());
        parambn.a.b(paramdg);
        paramdg.c();
      }
      if (parambn.b != null)
      {
        paramdg.a(bn.R());
        parambn.b.b(paramdg);
        paramdg.c();
      }
      if (parambn.c != null)
      {
        paramdg.a(bn.S());
        parambn.c.b(paramdg);
        paramdg.c();
      }
      if (parambn.d != null)
      {
        paramdg.a(bn.T());
        parambn.d.b(paramdg);
        paramdg.c();
      }
      if ((parambn.e != null) && (parambn.r()))
      {
        paramdg.a(bn.U());
        parambn.e.b(paramdg);
        paramdg.c();
      }
      Iterator localIterator;
      if ((parambn.f != null) && (parambn.w()))
      {
        paramdg.a(bn.V());
        paramdg.a(new dc((byte)12, parambn.f.size()));
        localIterator = parambn.f.iterator();
        while (localIterator.hasNext())
          ((bc)localIterator.next()).b(paramdg);
        paramdg.f();
        paramdg.c();
      }
      if ((parambn.g != null) && (parambn.B()))
      {
        paramdg.a(bn.W());
        paramdg.a(new dc((byte)12, parambn.g.size()));
        localIterator = parambn.g.iterator();
        while (localIterator.hasNext())
          ((bl)localIterator.next()).b(paramdg);
        paramdg.f();
        paramdg.c();
      }
      if ((parambn.h != null) && (parambn.E()))
      {
        paramdg.a(bn.X());
        parambn.h.b(paramdg);
        paramdg.c();
      }
      if ((parambn.i != null) && (parambn.H()))
      {
        paramdg.a(bn.Y());
        parambn.i.b(paramdg);
        paramdg.c();
      }
      if ((parambn.j != null) && (parambn.K()))
      {
        paramdg.a(bn.Z());
        parambn.j.b(paramdg);
        paramdg.c();
      }
      if ((parambn.k != null) && (parambn.N()))
      {
        paramdg.a(bn.aa());
        parambn.k.b(paramdg);
        paramdg.c();
      }
      paramdg.d();
      paramdg.b();
    }
  }

  private static class b
    implements dp
  {
    public bn.a a()
    {
      return new bn.a(null);
    }
  }

  private static class c extends dr<bn>
  {
    public void a(dg paramdg, bn parambn)
      throws cn
    {
      paramdg = (dm)paramdg;
      parambn.a.b(paramdg);
      parambn.b.b(paramdg);
      parambn.c.b(paramdg);
      parambn.d.b(paramdg);
      Object localObject = new BitSet();
      if (parambn.r())
        ((BitSet)localObject).set(0);
      if (parambn.w())
        ((BitSet)localObject).set(1);
      if (parambn.B())
        ((BitSet)localObject).set(2);
      if (parambn.E())
        ((BitSet)localObject).set(3);
      if (parambn.H())
        ((BitSet)localObject).set(4);
      if (parambn.K())
        ((BitSet)localObject).set(5);
      if (parambn.N())
        ((BitSet)localObject).set(6);
      paramdg.a((BitSet)localObject, 7);
      if (parambn.r())
        parambn.e.b(paramdg);
      if (parambn.w())
      {
        paramdg.a(parambn.f.size());
        localObject = parambn.f.iterator();
        while (((Iterator)localObject).hasNext())
          ((bc)((Iterator)localObject).next()).b(paramdg);
      }
      if (parambn.B())
      {
        paramdg.a(parambn.g.size());
        localObject = parambn.g.iterator();
        while (((Iterator)localObject).hasNext())
          ((bl)((Iterator)localObject).next()).b(paramdg);
      }
      if (parambn.E())
        parambn.h.b(paramdg);
      if (parambn.H())
        parambn.i.b(paramdg);
      if (parambn.K())
        parambn.j.b(paramdg);
      if (parambn.N())
        parambn.k.b(paramdg);
    }

    public void b(dg paramdg, bn parambn)
      throws cn
    {
      int j = 0;
      paramdg = (dm)paramdg;
      parambn.a = new ap();
      parambn.a.a(paramdg);
      parambn.a(true);
      parambn.b = new ao();
      parambn.b.a(paramdg);
      parambn.b(true);
      parambn.c = new ar();
      parambn.c.a(paramdg);
      parambn.c(true);
      parambn.d = new bf();
      parambn.d.a(paramdg);
      parambn.d(true);
      BitSet localBitSet = paramdg.b(7);
      if (localBitSet.get(0))
      {
        parambn.e = new am();
        parambn.e.a(paramdg);
        parambn.e(true);
      }
      dc localdc;
      int i;
      Object localObject;
      if (localBitSet.get(1))
      {
        localdc = new dc((byte)12, paramdg.w());
        parambn.f = new ArrayList(localdc.b);
        i = 0;
        while (i < localdc.b)
        {
          localObject = new bc();
          ((bc)localObject).a(paramdg);
          parambn.f.add(localObject);
          i += 1;
        }
        parambn.f(true);
      }
      if (localBitSet.get(2))
      {
        localdc = new dc((byte)12, paramdg.w());
        parambn.g = new ArrayList(localdc.b);
        i = j;
        while (i < localdc.b)
        {
          localObject = new bl();
          ((bl)localObject).a(paramdg);
          parambn.g.add(localObject);
          i += 1;
        }
        parambn.g(true);
      }
      if (localBitSet.get(3))
      {
        parambn.h = new ba();
        parambn.h.a(paramdg);
        parambn.h(true);
      }
      if (localBitSet.get(4))
      {
        parambn.i = new az();
        parambn.i.a(paramdg);
        parambn.i(true);
      }
      if (localBitSet.get(5))
      {
        parambn.j = new an();
        parambn.j.a(paramdg);
        parambn.j(true);
      }
      if (localBitSet.get(6))
      {
        parambn.k = new aq();
        parambn.k.a(paramdg);
        parambn.k(true);
      }
    }
  }

  private static class d
    implements dp
  {
    public bn.c a()
    {
      return new bn.c(null);
    }
  }

  public static enum e
    implements co
  {
    private static final Map<String, e> l;
    private final short m;
    private final String n;

    static
    {
      l = new HashMap();
      Iterator localIterator = EnumSet.allOf(e.class).iterator();
      while (localIterator.hasNext())
      {
        e locale = (e)localIterator.next();
        l.put(locale.b(), locale);
      }
    }

    private e(short paramShort, String paramString)
    {
      this.m = paramShort;
      this.n = paramString;
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
        return g;
      case 8:
        return h;
      case 9:
        return i;
      case 10:
        return j;
      case 11:
      }
      return k;
    }

    public static e a(String paramString)
    {
      return (e)l.get(paramString);
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
      return this.m;
    }

    public String b()
    {
      return this.n;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     u.aly.bn
 * JD-Core Version:    0.6.2
 */