package u.aly;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public abstract class a
{
  private final int a = 10;
  private final int b = 20;
  private final String c;
  private List<ax> d;
  private ay e;

  public a(String paramString)
  {
    this.c = paramString;
  }

  private boolean g()
  {
    boolean bool2 = false;
    Object localObject3 = this.e;
    Object localObject1;
    if (localObject3 == null)
    {
      localObject1 = null;
      if (localObject3 != null)
        break label218;
    }
    label218: for (int i = 0; ; i = ((ay)localObject3).j())
    {
      String str = a(f());
      boolean bool1 = bool2;
      if (str != null)
      {
        bool1 = bool2;
        if (!str.equals(localObject1))
        {
          Object localObject2 = localObject3;
          if (localObject3 == null)
            localObject2 = new ay();
          ((ay)localObject2).a(str);
          ((ay)localObject2).a(System.currentTimeMillis());
          ((ay)localObject2).a(i + 1);
          localObject3 = new ax();
          ((ax)localObject3).a(this.c);
          ((ax)localObject3).c(str);
          ((ax)localObject3).b((String)localObject1);
          ((ax)localObject3).a(((ay)localObject2).f());
          if (this.d == null)
            this.d = new ArrayList(2);
          this.d.add(localObject3);
          if (this.d.size() > 10)
            this.d.remove(0);
          this.e = ((ay)localObject2);
          bool1 = true;
        }
      }
      return bool1;
      localObject1 = ((ay)localObject3).c();
      break;
    }
  }

  public String a(String paramString)
  {
    if (paramString == null);
    do
    {
      return null;
      paramString = paramString.trim();
    }
    while ((paramString.length() == 0) || ("0".equals(paramString)) || ("unknown".equals(paramString.toLowerCase(Locale.US))));
    return paramString;
  }

  public void a(List<ax> paramList)
  {
    this.d = paramList;
  }

  public void a(ay paramay)
  {
    this.e = paramay;
  }

  public void a(az paramaz)
  {
    this.e = ((ay)paramaz.d().get(this.c));
    paramaz = paramaz.j();
    if ((paramaz != null) && (paramaz.size() > 0))
    {
      if (this.d == null)
        this.d = new ArrayList();
      paramaz = paramaz.iterator();
      while (paramaz.hasNext())
      {
        ax localax = (ax)paramaz.next();
        if (this.c.equals(localax.a))
          this.d.add(localax);
      }
    }
  }

  public boolean a()
  {
    return g();
  }

  public String b()
  {
    return this.c;
  }

  public boolean c()
  {
    return (this.e == null) || (this.e.j() <= 20);
  }

  public ay d()
  {
    return this.e;
  }

  public List<ax> e()
  {
    return this.d;
  }

  public abstract String f();
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     u.aly.a
 * JD-Core Version:    0.6.2
 */