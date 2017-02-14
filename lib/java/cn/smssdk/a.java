package cn.smssdk;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import cn.smssdk.contact.OnContactChangeListener;
import cn.smssdk.framework.FakeActivity;
import cn.smssdk.framework.utils.R;
import cn.smssdk.framework.utils.UIHandler;
import cn.smssdk.framework.utils.d;
import cn.smssdk.utils.Protocols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;

public class a
  implements OnContactChangeListener
{
  private Context a;
  private HashSet<EventHandler> b;
  private Protocols c;
  private cn.smssdk.contact.a d;
  private cn.smssdk.a.a e;
  private HashMap<Character, ArrayList<String[]>> f;

  public a(Context paramContext)
  {
    UIHandler.prepare();
    FakeActivity.setShell(SMSSDKUIShell.class);
    this.a = paramContext;
    this.b = new HashSet();
    this.c = Protocols.a(paramContext);
    this.e = cn.smssdk.a.a.a(paramContext);
    this.d = cn.smssdk.contact.a.a(paramContext);
  }

  private void a(int paramInt1, int paramInt2, Object paramObject)
  {
    synchronized (this.b)
    {
      Iterator localIterator = this.b.iterator();
      if (localIterator.hasNext())
        ((EventHandler)localIterator.next()).afterEvent(paramInt1, paramInt2, paramObject);
    }
  }

  private void a(Object paramObject)
  {
    int i = 0;
    try
    {
      boolean bool = ((Boolean)paramObject).booleanValue();
      paramObject = this.d.a(bool);
      i = -1;
      label21: a(4, i, paramObject);
      return;
    }
    catch (Throwable paramObject)
    {
      break label21;
    }
  }

  private void b(int paramInt, Object paramObject)
  {
    switch (paramInt)
    {
    default:
      return;
    case 4:
      a(paramObject);
      return;
    case 2:
      b(paramObject);
      return;
    case 3:
      c(paramObject);
      return;
    case 1:
      d();
      return;
    case 5:
      d(paramObject);
      return;
    case 7:
      e();
      return;
    case 6:
    }
    f();
  }

  private void b(Object paramObject)
  {
    int i = 0;
    Object localObject1 = null;
    try
    {
      Object localObject2 = (String[])paramObject;
      paramObject = localObject2[0];
      localObject2 = localObject2[1];
      this.c.b(paramObject, (String)localObject2);
      i = -1;
      paramObject = localObject1;
      label38: a(2, i, paramObject);
      return;
    }
    catch (Throwable paramObject)
    {
      break label38;
    }
  }

  private void c(Object paramObject)
  {
    int i = 0;
    try
    {
      Object localObject = (String[])paramObject;
      paramObject = localObject[0];
      String str = localObject[1];
      localObject = localObject[2];
      paramObject = this.c.a((String)localObject, paramObject, str);
      i = -1;
      a(3, i, paramObject);
      return;
    }
    catch (Throwable paramObject)
    {
      while (true)
        d.b(paramObject);
    }
  }

  private void d()
  {
    int i = 0;
    try
    {
      ArrayList localArrayList = this.c.a();
      i = -1;
      label12: a(1, i, localArrayList);
      return;
    }
    catch (Throwable localThrowable)
    {
      break label12;
    }
  }

  private void d(Object paramObject)
  {
    try
    {
      Object localObject = (String[])paramObject;
      paramObject = localObject[0];
      String str1 = localObject[1];
      String str2 = localObject[2];
      String str3 = localObject[3];
      localObject = localObject[4];
      this.e.a(paramObject, str1, str2, str3, (String)localObject);
      i = -1;
      paramObject = null;
      a(5, i, paramObject);
      return;
    }
    catch (Throwable paramObject)
    {
      while (true)
        int i = 0;
    }
  }

  private void e()
  {
    int i = 0;
    try
    {
      int j = this.e.a();
      Integer localInteger = Integer.valueOf(j);
      i = -1;
      label17: a(7, i, localInteger);
      return;
    }
    catch (Throwable localThrowable)
    {
      break label17;
    }
  }

  private void f()
  {
    int i = 0;
    try
    {
      ArrayList localArrayList = this.e.b();
      i = -1;
      label12: a(6, i, localArrayList);
      return;
    }
    catch (Throwable localThrowable)
    {
      break label12;
    }
  }

  public void a()
  {
    this.d.a(this);
    this.d.a();
  }

  public void a(int paramInt, Object paramObject)
  {
    new b(this, paramInt, paramObject).start();
  }

  public void a(EventHandler paramEventHandler)
  {
    HashSet localHashSet = this.b;
    if (paramEventHandler != null);
    try
    {
      if (!this.b.contains(paramEventHandler))
      {
        this.b.add(paramEventHandler);
        paramEventHandler.onRegister();
      }
      return;
    }
    finally
    {
    }
    throw paramEventHandler;
  }

  public void a(String paramString1, String paramString2)
  {
    this.c.a(paramString1, paramString2);
  }

  public String[] a(String paramString)
  {
    Object localObject;
    if (TextUtils.isEmpty(paramString))
    {
      localObject = null;
      return localObject;
    }
    Iterator localIterator = c().entrySet().iterator();
    label27: if (localIterator.hasNext())
    {
      ArrayList localArrayList = (ArrayList)((Map.Entry)localIterator.next()).getValue();
      if (localArrayList == null);
      for (int i = 0; ; i = localArrayList.size())
      {
        int j = 0;
        while (true)
        {
          if (j >= i)
            break label114;
          String[] arrayOfString = (String[])localArrayList.get(j);
          if ((arrayOfString != null) && (arrayOfString.length > 2))
          {
            localObject = arrayOfString;
            if (paramString.equals(arrayOfString[2]))
              break;
          }
          j += 1;
        }
        label114: break label27;
      }
    }
    return null;
  }

  public void b()
  {
    synchronized (this.b)
    {
      Iterator localIterator = this.b.iterator();
      if (localIterator.hasNext())
        ((EventHandler)localIterator.next()).onUnregister();
    }
    this.b.clear();
  }

  public void b(EventHandler paramEventHandler)
  {
    HashSet localHashSet = this.b;
    if (paramEventHandler != null);
    try
    {
      if (!this.b.contains(paramEventHandler))
        return;
      paramEventHandler.onUnregister();
      this.b.remove(paramEventHandler);
      return;
    }
    finally
    {
    }
    throw paramEventHandler;
  }

  public HashMap<Character, ArrayList<String[]>> c()
  {
    if ((this.f != null) && (this.f.size() > 0))
      return this.f;
    char c1 = 'A';
    Object localObject2;
    for (Object localObject1 = null; c1 <= 'Z'; localObject1 = localObject2)
    {
      localObject2 = "smssdk_country_group_" + Character.toLowerCase(c1);
      int i = R.getStringArrayRes(this.a, (String)localObject2);
      localObject2 = localObject1;
      if (i > 0)
      {
        String[] arrayOfString1 = this.a.getResources().getStringArray(i);
        if (arrayOfString1 != null)
        {
          int j = arrayOfString1.length;
          i = 0;
          for (localObject2 = null; ; localObject2 = localObject3)
          {
            localObject3 = localObject2;
            if (i >= j)
              break;
            String[] arrayOfString2 = arrayOfString1[i].split(",");
            localObject3 = localObject2;
            if (localObject2 == null)
              localObject3 = new ArrayList();
            ((ArrayList)localObject3).add(arrayOfString2);
            i += 1;
          }
        }
        Object localObject3 = null;
        localObject2 = localObject1;
        if (localObject3 != null)
        {
          localObject2 = localObject1;
          if (localObject1 == null)
            localObject2 = new LinkedHashMap();
          ((LinkedHashMap)localObject2).put(Character.valueOf(c1), localObject3);
        }
      }
      c1 = (char)(c1 + '\001');
    }
    this.f = localObject1;
    return this.f;
  }

  public void onContactChange(boolean paramBoolean)
  {
    this.e.a(0, new c(this));
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.smssdk.a
 * JD-Core Version:    0.6.2
 */