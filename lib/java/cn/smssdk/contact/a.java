package cn.smssdk.contact;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.RawContacts;
import android.text.TextUtils;
import cn.smssdk.contact.a.e;
import cn.smssdk.contact.a.g;
import cn.smssdk.contact.a.h;
import cn.smssdk.contact.a.i;
import cn.smssdk.contact.a.j;
import cn.smssdk.contact.a.k;
import cn.smssdk.contact.a.l;
import cn.smssdk.contact.a.m;
import cn.smssdk.contact.a.n;
import cn.smssdk.contact.a.o;
import cn.smssdk.contact.a.q;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class a
{
  private static a a;
  private static ContentObserver b;
  private ContentResolver c;
  private c d;
  private long e;
  private ArrayList<cn.smssdk.contact.a.a> f;
  private OnContactChangeListener g;
  private d h;

  private a(Context paramContext)
  {
    this.c = paramContext.getContentResolver();
    this.d = new c(this.c);
    d();
    this.h = new d(paramContext, this);
  }

  public static a a(Context paramContext)
  {
    if (a == null)
      a = new a(paramContext);
    return a;
  }

  private void d()
  {
    if (b == null)
      b = new b(this, new Handler());
    try
    {
      this.c.unregisterContentObserver(b);
      this.c.registerContentObserver(ContactsContract.Contacts.CONTENT_URI, true, b);
      return;
    }
    catch (Throwable localThrowable)
    {
      cn.smssdk.framework.utils.d.c(localThrowable);
    }
  }

  private ArrayList<cn.smssdk.contact.a.a> e()
  {
    Iterator localIterator = null;
    if ((this.f != null) && (System.currentTimeMillis() < this.e))
    {
      localObject1 = this.f;
      return localObject1;
    }
    String str;
    if (Build.VERSION.SDK_INT <= 10)
    {
      str = "_id";
      label38: if (Build.VERSION.SDK_INT > 9)
        break label152;
    }
    label152: for (Object localObject1 = ContactsContract.RawContacts.CONTENT_URI; ; localObject1 = ContactsContract.Contacts.CONTENT_URI)
    {
      Object localObject2 = this.d.a((Uri)localObject1, new String[] { str }, null, null, "sort_key asc");
      localObject1 = localIterator;
      if (localObject2 == null)
        break;
      localObject1 = new ArrayList();
      localIterator = ((ArrayList)localObject2).iterator();
      while (localIterator.hasNext())
      {
        localObject2 = (String)((HashMap)localIterator.next()).get(str);
        if (localObject2 != null)
          ((ArrayList)localObject1).add(new cn.smssdk.contact.a.a(this.d, (String)localObject2));
      }
      str = "name_raw_contact_id";
      break label38;
    }
    this.f = ((ArrayList)localObject1);
    this.e = (System.currentTimeMillis() + 3600000L);
    return localObject1;
  }

  public ArrayList<HashMap<String, Object>> a(boolean paramBoolean)
  {
    Object localObject1 = e();
    if (localObject1 == null)
      return null;
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = ((ArrayList)localObject1).iterator();
    Object localObject4;
    HashMap localHashMap;
    Object localObject3;
    Object localObject2;
    Object localObject5;
    label255: Object localObject6;
    if (localIterator.hasNext())
    {
      localObject4 = (cn.smssdk.contact.a.a)localIterator.next();
      localHashMap = new HashMap();
      localObject3 = ((cn.smssdk.contact.a.a)localObject4).a();
      if (localObject3 == null)
        break label1732;
      localObject1 = ((h)localObject3).b();
      if (!TextUtils.isEmpty((CharSequence)localObject1))
        localHashMap.put("prefixname", localObject1);
      localObject1 = ((h)localObject3).c();
      if (!TextUtils.isEmpty((CharSequence)localObject1))
        localHashMap.put("suffixname", localObject1);
      localObject1 = ((h)localObject3).d();
      if (!TextUtils.isEmpty((CharSequence)localObject1))
        localHashMap.put("lastname", localObject1);
      localObject1 = ((h)localObject3).e();
      if (!TextUtils.isEmpty((CharSequence)localObject1))
        localHashMap.put("firstname", localObject1);
      localObject1 = ((h)localObject3).f();
      if (!TextUtils.isEmpty((CharSequence)localObject1))
        localHashMap.put("displayname", localObject1);
      localObject2 = ((h)localObject3).i();
      if (TextUtils.isEmpty((CharSequence)localObject2))
        break label1727;
      localObject1 = new HashMap();
      ((HashMap)localObject1).put("key", "phonetic");
      localObject5 = new ArrayList();
      ((ArrayList)localObject5).add(localObject2);
      ((HashMap)localObject1).put("vals", localObject5);
      if (0 != 0)
        break label1722;
      localObject2 = new ArrayList();
      localHashMap.put("others", localObject2);
      ((ArrayList)localObject2).add(localObject1);
      label261: localObject6 = ((h)localObject3).g();
      localObject1 = localObject2;
      if (!TextUtils.isEmpty((CharSequence)localObject6))
      {
        localObject5 = new HashMap();
        ((HashMap)localObject5).put("key", "phoneticfirstname");
        localObject1 = new ArrayList();
        ((ArrayList)localObject1).add(localObject6);
        ((HashMap)localObject5).put("vals", localObject1);
        localObject1 = localObject2;
        if (localObject2 == null)
        {
          localObject1 = new ArrayList();
          localHashMap.put("others", localObject1);
        }
        ((ArrayList)localObject1).add(localObject5);
      }
      localObject5 = ((h)localObject3).h();
      localObject2 = localObject1;
      if (!TextUtils.isEmpty((CharSequence)localObject5))
      {
        localObject3 = new HashMap();
        ((HashMap)localObject3).put("key", "phoneticlastname");
        localObject2 = new ArrayList();
        ((ArrayList)localObject2).add(localObject5);
        ((HashMap)localObject3).put("vals", localObject2);
        localObject2 = localObject1;
        if (localObject1 == null)
        {
          localObject2 = new ArrayList();
          localHashMap.put("others", localObject2);
        }
        ((ArrayList)localObject2).add(localObject3);
      }
    }
    label1722: label1727: label1732: for (localObject1 = localObject2; ; localObject1 = null)
    {
      localObject2 = ((cn.smssdk.contact.a.a)localObject4).b();
      if (localObject2 != null)
      {
        localObject2 = ((i)localObject2).b();
        if (!TextUtils.isEmpty((CharSequence)localObject2))
          localHashMap.put("nickname", localObject2);
      }
      localObject2 = ((cn.smssdk.contact.a.a)localObject4).d();
      if (localObject2 != null)
      {
        localObject3 = ((k)localObject2).b();
        if (!TextUtils.isEmpty((CharSequence)localObject3))
          localHashMap.put("company", localObject3);
        localObject2 = ((k)localObject2).c();
        if (!TextUtils.isEmpty((CharSequence)localObject2))
          localHashMap.put("position", localObject2);
      }
      localObject2 = ((cn.smssdk.contact.a.a)localObject4).j();
      String str;
      if (localObject2 != null)
      {
        localObject5 = ((ArrayList)localObject2).iterator();
        localObject3 = null;
        while (((Iterator)localObject5).hasNext())
        {
          localObject6 = (l)((Iterator)localObject5).next();
          str = ((l)localObject6).b();
          if (!TextUtils.isEmpty(str))
          {
            localObject2 = localObject3;
            if (localObject3 == null)
            {
              localObject2 = new ArrayList();
              localHashMap.put("phones", localObject2);
            }
            localObject3 = new HashMap();
            ((HashMap)localObject3).put("phone", str);
            ((HashMap)localObject3).put("type", Integer.valueOf(((l)localObject6).c()));
            ((HashMap)localObject3).put("desc", ((l)localObject6).d());
            ((ArrayList)localObject2).add(localObject3);
            localObject3 = localObject2;
          }
        }
      }
      localObject2 = ((cn.smssdk.contact.a.a)localObject4).i();
      if (localObject2 != null)
      {
        localObject5 = ((ArrayList)localObject2).iterator();
        localObject3 = null;
        while (((Iterator)localObject5).hasNext())
        {
          localObject6 = (cn.smssdk.contact.a.c)((Iterator)localObject5).next();
          str = ((cn.smssdk.contact.a.c)localObject6).b();
          if (!TextUtils.isEmpty(str))
          {
            localObject2 = localObject3;
            if (localObject3 == null)
            {
              localObject2 = new ArrayList();
              localHashMap.put("mails", localObject2);
            }
            localObject3 = new HashMap();
            ((HashMap)localObject3).put("email", str);
            ((HashMap)localObject3).put("type", Integer.valueOf(((cn.smssdk.contact.a.c)localObject6).c()));
            ((HashMap)localObject3).put("desc", ((cn.smssdk.contact.a.c)localObject6).d());
            ((ArrayList)localObject2).add(localObject3);
            localObject3 = localObject2;
          }
        }
      }
      localObject2 = ((cn.smssdk.contact.a.a)localObject4).k();
      if (localObject2 != null)
      {
        localObject5 = ((ArrayList)localObject2).iterator();
        localObject3 = null;
        while (((Iterator)localObject5).hasNext())
        {
          localObject6 = (n)((Iterator)localObject5).next();
          str = ((n)localObject6).b();
          if (!TextUtils.isEmpty(str))
          {
            localObject2 = localObject3;
            if (localObject3 == null)
            {
              localObject2 = new ArrayList();
              localHashMap.put("addresses", localObject2);
            }
            localObject3 = new HashMap();
            ((HashMap)localObject3).put("address", str);
            ((HashMap)localObject3).put("type", Integer.valueOf(((n)localObject6).c()));
            ((HashMap)localObject3).put("desc", ((n)localObject6).d());
            ((ArrayList)localObject2).add(localObject3);
            localObject3 = localObject2;
          }
        }
      }
      localObject2 = ((cn.smssdk.contact.a.a)localObject4).l();
      if (localObject2 != null)
      {
        localObject5 = ((ArrayList)localObject2).iterator();
        localObject3 = null;
        while (((Iterator)localObject5).hasNext())
        {
          localObject6 = (cn.smssdk.contact.a.d)((Iterator)localObject5).next();
          str = ((cn.smssdk.contact.a.d)localObject6).b();
          if (!TextUtils.isEmpty(str))
          {
            localObject2 = localObject3;
            if (localObject3 == null)
            {
              localObject2 = new ArrayList();
              localHashMap.put("specialdate", localObject2);
            }
            localObject3 = new HashMap();
            ((HashMap)localObject3).put("date", str);
            ((HashMap)localObject3).put("type", Integer.valueOf(((cn.smssdk.contact.a.d)localObject6).c()));
            ((HashMap)localObject3).put("desc", ((cn.smssdk.contact.a.d)localObject6).d());
            ((ArrayList)localObject2).add(localObject3);
            localObject3 = localObject2;
          }
        }
      }
      localObject2 = ((cn.smssdk.contact.a.a)localObject4).h();
      if (localObject2 != null)
      {
        localObject5 = ((ArrayList)localObject2).iterator();
        localObject3 = null;
        while (((Iterator)localObject5).hasNext())
        {
          localObject6 = (g)((Iterator)localObject5).next();
          str = ((g)localObject6).b();
          if (!TextUtils.isEmpty(str))
          {
            localObject2 = localObject3;
            if (localObject3 == null)
            {
              localObject2 = new ArrayList();
              localHashMap.put("ims", localObject2);
            }
            localObject3 = new HashMap();
            ((HashMap)localObject3).put("val", str);
            ((HashMap)localObject3).put("type", Integer.valueOf(((g)localObject6).c()));
            ((HashMap)localObject3).put("desc", ((g)localObject6).d());
            ((ArrayList)localObject2).add(localObject3);
            localObject3 = localObject2;
          }
        }
      }
      localObject2 = ((cn.smssdk.contact.a.a)localObject4).c();
      if (localObject2 != null)
      {
        localObject2 = ((e)localObject2).b();
        if (!TextUtils.isEmpty((CharSequence)localObject2))
          localHashMap.put("group", localObject2);
      }
      localObject2 = ((cn.smssdk.contact.a.a)localObject4).f();
      if (localObject2 != null)
      {
        localObject2 = ((j)localObject2).b();
        if (!TextUtils.isEmpty((CharSequence)localObject2))
          localHashMap.put("remarks", localObject2);
      }
      localObject2 = ((cn.smssdk.contact.a.a)localObject4).g();
      if (localObject2 != null)
      {
        localObject5 = ((ArrayList)localObject2).iterator();
        localObject3 = null;
        while (((Iterator)localObject5).hasNext())
        {
          localObject6 = (q)((Iterator)localObject5).next();
          str = ((q)localObject6).b();
          if (!TextUtils.isEmpty(str))
          {
            localObject2 = localObject3;
            if (localObject3 == null)
            {
              localObject2 = new ArrayList();
              localHashMap.put("websites", localObject2);
            }
            localObject3 = new HashMap();
            ((HashMap)localObject3).put("val", str);
            ((HashMap)localObject3).put("type", Integer.valueOf(((q)localObject6).c()));
            ((HashMap)localObject3).put("desc", ((q)localObject6).d());
            ((ArrayList)localObject2).add(localObject3);
            localObject3 = localObject2;
          }
        }
      }
      localObject2 = ((cn.smssdk.contact.a.a)localObject4).m();
      if (localObject2 != null)
      {
        localObject5 = ((ArrayList)localObject2).iterator();
        localObject3 = null;
        while (((Iterator)localObject5).hasNext())
        {
          localObject6 = (o)((Iterator)localObject5).next();
          str = ((o)localObject6).b();
          if (!TextUtils.isEmpty(str))
          {
            localObject2 = localObject3;
            if (localObject3 == null)
            {
              localObject2 = new ArrayList();
              localHashMap.put("relations", localObject2);
            }
            localObject3 = new HashMap();
            ((HashMap)localObject3).put("val", str);
            ((HashMap)localObject3).put("type", Integer.valueOf(((o)localObject6).c()));
            ((HashMap)localObject3).put("desc", ((o)localObject6).d());
            ((ArrayList)localObject2).add(localObject3);
            localObject3 = localObject2;
          }
        }
      }
      if (paramBoolean)
      {
        localObject2 = ((cn.smssdk.contact.a.a)localObject4).e();
        if (localObject2 != null)
        {
          localObject2 = ((m)localObject2).b();
          if (!TextUtils.isEmpty((CharSequence)localObject2))
          {
            localObject3 = new HashMap();
            ((HashMap)localObject3).put("key", "avatar");
            localObject4 = new ArrayList();
            ((ArrayList)localObject4).add(localObject2);
            ((HashMap)localObject3).put("vals", localObject4);
            localObject2 = localObject1;
            if (localObject1 == null)
            {
              localObject2 = new ArrayList();
              localHashMap.put("others", localObject2);
            }
            ((ArrayList)localObject2).add(localObject3);
          }
        }
      }
      localArrayList.add(localHashMap);
      break;
      return localArrayList;
      localObject2 = null;
      break label255;
      localObject2 = null;
      break label261;
    }
  }

  public void a()
  {
    this.h.a();
  }

  public void a(OnContactChangeListener paramOnContactChangeListener)
  {
    this.g = paramOnContactChangeListener;
  }

  public void b()
  {
    this.e = 0L;
  }

  public String[] c()
  {
    Object localObject1 = e();
    if (localObject1 == null)
      return null;
    Object localObject2 = new HashSet();
    localObject1 = ((ArrayList)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject3 = ((cn.smssdk.contact.a.a)((Iterator)localObject1).next()).j();
      if (localObject3 != null)
      {
        localObject3 = ((ArrayList)localObject3).iterator();
        while (((Iterator)localObject3).hasNext())
        {
          String str = ((l)((Iterator)localObject3).next()).b();
          if (!TextUtils.isEmpty(str))
            ((HashSet)localObject2).add(str);
        }
      }
    }
    localObject1 = new String[((HashSet)localObject2).size()];
    localObject2 = ((HashSet)localObject2).iterator();
    int i = 0;
    while (((Iterator)localObject2).hasNext())
    {
      localObject1[i] = ((String)((Iterator)localObject2).next());
      i += 1;
    }
    if (localObject1.length > 0);
    while (true)
    {
      return localObject1;
      localObject1 = null;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.smssdk.contact.a
 * JD-Core Version:    0.6.2
 */