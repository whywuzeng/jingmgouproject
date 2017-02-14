package cn.smssdk.contact.a;

import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.Groups;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class a
{
  private String a;
  private h b;
  private i c;
  private e d;
  private k e;
  private ArrayList<d> f;
  private m g;
  private j h;
  private ArrayList<q> i;
  private ArrayList<g> j;
  private ArrayList<c> k;
  private ArrayList<l> l;
  private ArrayList<n> m;
  private ArrayList<p> n;
  private ArrayList<o> o;
  private f p;

  public a(cn.smssdk.contact.c paramc, String paramString)
  {
    this.a = paramString;
    a(paramc);
  }

  private void a(cn.smssdk.contact.c paramc)
  {
    if (this.a == null);
    while (true)
    {
      return;
      Object localObject1 = "raw_contact_id=" + this.a;
      localObject1 = paramc.a(ContactsContract.Data.CONTENT_URI, null, (String)localObject1, null, null);
      if (localObject1 != null)
      {
        localObject1 = ((ArrayList)localObject1).iterator();
        while (((Iterator)localObject1).hasNext())
        {
          Object localObject2 = (HashMap)((Iterator)localObject1).next();
          b localb = b.a((HashMap)localObject2);
          if (localb != null)
            if ((localb instanceof h))
            {
              this.b = ((h)localb);
            }
            else if ((localb instanceof i))
            {
              this.c = ((i)localb);
            }
            else if ((localb instanceof e))
            {
              localObject2 = "_id=" + ((HashMap)localObject2).get("data1");
              localObject2 = paramc.a(ContactsContract.Groups.CONTENT_URI, null, (String)localObject2, null, null);
              if ((localObject2 != null) && (((ArrayList)localObject2).size() > 0))
              {
                localObject2 = (HashMap)((ArrayList)localObject2).get(0);
                ((HashMap)localObject2).put("mimetype", "vnd.android.cursor.item/group_membership");
                this.d = ((e)b.a((HashMap)localObject2));
              }
            }
            else if ((localb instanceof k))
            {
              this.e = ((k)localb);
            }
            else if ((localb instanceof d))
            {
              if (this.f == null)
                this.f = new ArrayList();
              this.f.add((d)localb);
            }
            else if ((localb instanceof m))
            {
              this.g = ((m)localb);
            }
            else if ((localb instanceof j))
            {
              this.h = ((j)localb);
            }
            else if ((localb instanceof q))
            {
              if (this.i == null)
                this.i = new ArrayList();
              this.i.add((q)localb);
            }
            else if ((localb instanceof g))
            {
              if (this.j == null)
                this.j = new ArrayList();
              this.j.add((g)localb);
            }
            else if ((localb instanceof c))
            {
              if (this.k == null)
                this.k = new ArrayList();
              this.k.add((c)localb);
            }
            else if ((localb instanceof l))
            {
              if (this.l == null)
                this.l = new ArrayList();
              this.l.add((l)localb);
            }
            else if ((localb instanceof n))
            {
              if (this.m == null)
                this.m = new ArrayList();
              this.m.add((n)localb);
            }
            else if ((localb instanceof o))
            {
              if (this.m == null)
                this.o = new ArrayList();
              this.o.add((o)localb);
            }
            else if ((localb instanceof p))
            {
              if (this.n == null)
                this.n = new ArrayList();
              this.n.add((p)localb);
            }
            else if ((localb instanceof f))
            {
              this.p = ((f)localb);
            }
        }
      }
    }
  }

  public h a()
  {
    return this.b;
  }

  public i b()
  {
    return this.c;
  }

  public e c()
  {
    return this.d;
  }

  public k d()
  {
    return this.e;
  }

  public m e()
  {
    return this.g;
  }

  public j f()
  {
    return this.h;
  }

  public ArrayList<q> g()
  {
    return this.i;
  }

  public ArrayList<g> h()
  {
    return this.j;
  }

  public ArrayList<c> i()
  {
    return this.k;
  }

  public ArrayList<l> j()
  {
    return this.l;
  }

  public ArrayList<n> k()
  {
    return this.m;
  }

  public ArrayList<d> l()
  {
    return this.f;
  }

  public ArrayList<o> m()
  {
    return this.o;
  }

  public HashMap<String, Object> n()
  {
    HashMap localHashMap = new HashMap();
    if (this.b != null)
      localHashMap.put("name", this.b.a());
    if (this.c != null)
      localHashMap.put("nickname", this.c.a());
    if (this.d != null)
      localHashMap.put("group", this.d.a());
    if (this.e != null)
      localHashMap.put("organization", this.e.a());
    ArrayList localArrayList;
    Iterator localIterator;
    if (this.f != null)
    {
      localArrayList = new ArrayList();
      localIterator = this.f.iterator();
      while (localIterator.hasNext())
        localArrayList.add(((d)localIterator.next()).a());
      localHashMap.put("events", localArrayList);
    }
    if (this.g != null)
      localHashMap.put("photo", this.g.a());
    if (this.h != null)
      localHashMap.put("note", this.h.a());
    if (this.i != null)
    {
      localArrayList = new ArrayList();
      localIterator = this.i.iterator();
      while (localIterator.hasNext())
        localArrayList.add(((q)localIterator.next()).a());
      localHashMap.put("websites", localArrayList);
    }
    if (this.j != null)
    {
      localArrayList = new ArrayList();
      localIterator = this.j.iterator();
      while (localIterator.hasNext())
        localArrayList.add(((g)localIterator.next()).a());
      localHashMap.put("ims", localArrayList);
    }
    if (this.k != null)
    {
      localArrayList = new ArrayList();
      localIterator = this.k.iterator();
      while (localIterator.hasNext())
        localArrayList.add(((c)localIterator.next()).a());
      localHashMap.put("emails", localArrayList);
    }
    if (this.l != null)
    {
      localArrayList = new ArrayList();
      localIterator = this.l.iterator();
      while (localIterator.hasNext())
        localArrayList.add(((l)localIterator.next()).a());
      localHashMap.put("phones", localArrayList);
    }
    if (this.l != null)
    {
      localArrayList = new ArrayList();
      localIterator = this.l.iterator();
      while (localIterator.hasNext())
        localArrayList.add(((l)localIterator.next()).a());
      localHashMap.put("phones", localArrayList);
    }
    if (this.m != null)
    {
      localArrayList = new ArrayList();
      localIterator = this.m.iterator();
      while (localIterator.hasNext())
        localArrayList.add(((n)localIterator.next()).a());
      localHashMap.put("postals", localArrayList);
    }
    if (this.n != null)
    {
      localArrayList = new ArrayList();
      localIterator = this.n.iterator();
      while (localIterator.hasNext())
        localArrayList.add(((p)localIterator.next()).a());
      localHashMap.put("sipAddresses", localArrayList);
    }
    if (this.o != null)
    {
      localArrayList = new ArrayList();
      localIterator = this.o.iterator();
      while (localIterator.hasNext())
        localArrayList.add(((o)localIterator.next()).a());
      localHashMap.put("relations", localArrayList);
    }
    if (this.p != null)
      localHashMap.put("identity", this.p.a());
    return localHashMap;
  }

  public String toString()
  {
    return new cn.smssdk.framework.utils.c().a(n());
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.smssdk.contact.a.a
 * JD-Core Version:    0.6.2
 */