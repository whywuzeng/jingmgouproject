package cn.smssdk.contact.a;

import java.util.HashMap;

public abstract class b
{
  private static final HashMap<String, Class<? extends b>> a = new HashMap();
  private HashMap<String, Object> b;

  static
  {
    a.put("vnd.android.cursor.item/name", h.class);
    a.put("vnd.android.cursor.item/nickname", i.class);
    a.put("vnd.android.cursor.item/group_membership", e.class);
    a.put("vnd.android.cursor.item/organization", k.class);
    a.put("vnd.android.cursor.item/contact_event", d.class);
    a.put("vnd.android.cursor.item/photo", m.class);
    a.put("vnd.android.cursor.item/note", j.class);
    a.put("vnd.android.cursor.item/website", q.class);
    a.put("vnd.android.cursor.item/im", g.class);
    a.put("vnd.android.cursor.item/email_v2", c.class);
    a.put("vnd.android.cursor.item/phone_v2", l.class);
    a.put("vnd.android.cursor.item/postal-address_v2", n.class);
    a.put("vnd.android.cursor.item/relation", o.class);
    a.put("vnd.android.cursor.item/sip_address", p.class);
    a.put("vnd.android.cursor.item/identity", f.class);
  }

  public static b a(HashMap<String, Object> paramHashMap)
  {
    Object localObject = (String)paramHashMap.get("mimetype");
    localObject = (Class)a.get(localObject);
    if (localObject != null)
      try
      {
        localObject = (b)((Class)localObject).newInstance();
        ((b)localObject).b(paramHashMap);
        return localObject;
      }
      catch (Throwable paramHashMap)
      {
        cn.smssdk.framework.utils.d.c(paramHashMap);
      }
    return null;
  }

  protected int a(String paramString, int paramInt)
  {
    paramString = this.b.get(paramString);
    if ((paramString instanceof Integer))
      return ((Integer)paramString).intValue();
    try
    {
      int i = Integer.parseInt((String)paramString);
      return i;
    }
    catch (Throwable paramString)
    {
      cn.smssdk.framework.utils.d.c(paramString);
    }
    return paramInt;
  }

  protected HashMap<String, Object> a()
  {
    return this.b;
  }

  protected byte[] a(String paramString)
  {
    return (byte[])this.b.get(paramString);
  }

  protected String b(String paramString)
  {
    return (String)this.b.get(paramString);
  }

  protected void b(HashMap<String, Object> paramHashMap)
  {
    this.b = paramHashMap;
  }

  public String toString()
  {
    if (this.b == null)
      return "";
    return new cn.smssdk.framework.utils.c().a(this.b);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.smssdk.contact.a.b
 * JD-Core Version:    0.6.2
 */