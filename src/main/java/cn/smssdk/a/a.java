package cn.smssdk.a;

import android.content.Context;
import android.os.Handler.Callback;
import cn.smssdk.utils.Protocols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public final class a
{
  private static a a;
  private Protocols b;
  private cn.smssdk.utils.b c;
  private b d;
  private cn.smssdk.contact.a e;

  private a(Context paramContext)
  {
    this.b = Protocols.a(paramContext);
    this.c = cn.smssdk.utils.b.a(paramContext, "SMSSDK");
    this.d = new b(paramContext, this);
    this.e = cn.smssdk.contact.a.a(paramContext);
  }

  public static a a(Context paramContext)
  {
    if (a == null)
      a = new a(paramContext);
    return a;
  }

  private boolean c()
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(System.currentTimeMillis());
    int i = localCalendar.get(1);
    int j = localCalendar.get(2);
    int k = localCalendar.get(5);
    localCalendar.setTimeInMillis(this.c.g());
    int m = localCalendar.get(1);
    int n = localCalendar.get(2);
    int i1 = localCalendar.get(5);
    return (i != m) || (j != n) || (k != i1);
  }

  private String[] d()
  {
    String[] arrayOfString2 = this.c.i();
    String[] arrayOfString1;
    if (arrayOfString2 != null)
    {
      arrayOfString1 = arrayOfString2;
      if (arrayOfString2.length > 0);
    }
    else
    {
      this.e.b();
      arrayOfString1 = this.e.c();
      this.c.a(arrayOfString1);
    }
    return arrayOfString1;
  }

  public int a()
  {
    ArrayList localArrayList;
    if (c())
    {
      localArrayList = a(this.b.a(d()));
      this.c.c(localArrayList);
      this.c.h();
    }
    while (true)
    {
      return localArrayList.size();
      localArrayList = this.c.f();
    }
  }

  protected ArrayList<HashMap<String, Object>> a(ArrayList<HashMap<String, Object>> paramArrayList)
  {
    Object localObject2 = this.c.e();
    Object localObject1 = new HashMap();
    paramArrayList = paramArrayList.iterator();
    label225: 
    while (true)
    {
      Object localObject3;
      Object localObject4;
      if (paramArrayList.hasNext())
      {
        localObject3 = (HashMap)paramArrayList.next();
        localObject4 = ((HashMap)localObject3).get("phone");
        if (localObject4 != null)
        {
          Iterator localIterator = ((ArrayList)localObject2).iterator();
          do
            if (!localIterator.hasNext())
              break;
          while (!localObject4.equals(((HashMap)localIterator.next()).get("phone")));
        }
      }
      else
      {
        for (int i = 0; ; i = 1)
        {
          if (i == 0)
            break label225;
          ((HashMap)localObject1).put(localObject4, localObject3);
          break;
          paramArrayList = this.c.f().iterator();
          while (paramArrayList.hasNext())
          {
            localObject2 = (HashMap)paramArrayList.next();
            localObject3 = ((HashMap)localObject2).get("phone");
            if (localObject3 != null)
              ((HashMap)localObject1).put(localObject3, localObject2);
          }
          paramArrayList = new ArrayList();
          localObject1 = ((HashMap)localObject1).entrySet().iterator();
          while (((Iterator)localObject1).hasNext())
            paramArrayList.add(((Map.Entry)((Iterator)localObject1).next()).getValue());
          return paramArrayList;
        }
      }
    }
  }

  public void a(int paramInt, Handler.Callback paramCallback)
  {
    this.d.a(paramInt, paramCallback);
  }

  public void a(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    this.b.a(paramString1, paramString2, paramString3, paramString4, paramString5);
  }

  public ArrayList<HashMap<String, Object>> b()
  {
    ArrayList localArrayList = this.b.a(d());
    Object localObject1 = a(localArrayList);
    this.c.b(localArrayList);
    this.c.c(new ArrayList());
    localObject1 = ((ArrayList)localObject1).iterator();
    while (true)
    {
      if (!((Iterator)localObject1).hasNext())
        break label130;
      Object localObject2 = ((HashMap)((Iterator)localObject1).next()).get("phone");
      if (localObject2 != null)
      {
        Iterator localIterator = localArrayList.iterator();
        if (localIterator.hasNext())
        {
          HashMap localHashMap = (HashMap)localIterator.next();
          if (!localObject2.equals(localHashMap.get("phone")))
            break;
          localHashMap.put("isnew", Boolean.valueOf(true));
        }
      }
    }
    label130: return localArrayList;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.smssdk.a.a
 * JD-Core Version:    0.6.2
 */