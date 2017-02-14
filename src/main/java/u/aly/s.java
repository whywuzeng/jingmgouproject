package u.aly;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.e;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class s
{
  private List<q> a = new ArrayList();
  private am b = null;
  private ao c = null;
  private ar d = null;
  private bf e = null;
  private Context f = null;

  public s(Context paramContext)
  {
    this.f = paramContext;
  }

  private void a(Context paramContext)
  {
    try
    {
      this.c.a(AnalyticsConfig.getAppkey(paramContext));
      this.c.e(AnalyticsConfig.getChannel(paramContext));
      if ((AnalyticsConfig.mWrapperType != null) && (AnalyticsConfig.mWrapperVersion != null))
      {
        this.c.f(AnalyticsConfig.mWrapperType);
        this.c.g(AnalyticsConfig.mWrapperVersion);
      }
      this.c.c(bq.u(paramContext));
      this.c.a(bk.a);
      this.c.d("5.5.3");
      this.c.b(bq.d(paramContext));
      this.c.a(Integer.parseInt(bq.c(paramContext)));
      this.c.c(AnalyticsConfig.mVerticalType);
      this.c.d(AnalyticsConfig.getSDKVersion());
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }

  private void b(Context paramContext)
  {
    try
    {
      this.d.f(bq.a());
      this.d.a(bq.f(paramContext));
      this.d.b(bq.g(paramContext));
      this.d.c(bq.p(paramContext));
      this.d.e(Build.MODEL);
      this.d.g("Android");
      this.d.h(Build.VERSION.RELEASE);
      paramContext = bq.r(paramContext);
      if (paramContext != null)
        this.d.a(new bi(paramContext[1], paramContext[0]));
      if ((AnalyticsConfig.GPU_RENDERER != null) && (AnalyticsConfig.GPU_VENDER != null));
      this.d.i(Build.BOARD);
      this.d.j(Build.BRAND);
      this.d.a(Build.TIME);
      this.d.k(Build.MANUFACTURER);
      this.d.l(Build.ID);
      this.d.m(Build.DEVICE);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }

  private void c(Context paramContext)
  {
    while (true)
    {
      try
      {
        String[] arrayOfString = bq.j(paramContext);
        if ("Wi-Fi".equals(arrayOfString[0]))
        {
          this.e.a(al.c);
          if (!"".equals(arrayOfString[1]))
            this.e.e(arrayOfString[1]);
          this.e.c(bq.s(paramContext));
          arrayOfString = bq.n(paramContext);
          this.e.b(arrayOfString[0]);
          this.e.a(arrayOfString[1]);
          this.e.a(bq.m(paramContext));
          return;
        }
        if ("2G/3G".equals(arrayOfString[0]))
        {
          this.e.a(al.b);
          continue;
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
        return;
      }
      this.e.a(al.a);
    }
  }

  public Context a()
  {
    return this.f;
  }

  public void a(am paramam)
  {
    try
    {
      this.b = paramam;
      return;
    }
    finally
    {
      paramam = finally;
    }
    throw paramam;
  }

  public void a(bn parambn)
  {
    String str = z.g(this.f);
    if (str == null)
      return;
    try
    {
      if ((this.b != null) && (new aa(this.f).f()))
      {
        parambn.a(this.b);
        this.b = null;
      }
      Iterator localIterator = this.a.iterator();
      while (localIterator.hasNext())
        ((q)localIterator.next()).a(parambn, str);
    }
    finally
    {
    }
    this.a.clear();
    parambn.a(c());
    parambn.a(d());
    parambn.a(e());
    parambn.a(h());
    parambn.a(f());
    parambn.a(g());
    parambn.a(i());
  }

  public void a(q paramq)
  {
    try
    {
      this.a.add(paramq);
      return;
    }
    finally
    {
      paramq = finally;
    }
    throw paramq;
  }

  protected boolean a(int paramInt)
  {
    return true;
  }

  public int b()
  {
    try
    {
      int j = this.a.size();
      am localam = this.b;
      int i = j;
      if (localam != null)
        i = j + 1;
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public ao c()
  {
    try
    {
      if (this.c == null)
      {
        this.c = new ao();
        a(this.f);
      }
      ao localao = this.c;
      return localao;
    }
    finally
    {
    }
  }

  public ar d()
  {
    try
    {
      if (this.d == null)
      {
        this.d = new ar();
        b(this.f);
      }
      ar localar = this.d;
      return localar;
    }
    finally
    {
    }
  }

  public bf e()
  {
    try
    {
      if (this.e == null)
      {
        this.e = new bf();
        c(this.f);
      }
      bf localbf = this.e;
      return localbf;
    }
    finally
    {
    }
  }

  public ba f()
  {
    try
    {
      ba localba = f.a(this.f).a();
      return localba;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }

  public az g()
  {
    try
    {
      az localaz = d.a(this.f).b();
      return localaz;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }

  public ap h()
  {
    try
    {
      ap localap = aa.a(this.f);
      return localap;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return new ap();
  }

  public an i()
  {
    String[] arrayOfString = e.a(this.f);
    if ((arrayOfString != null) && (!TextUtils.isEmpty(arrayOfString[0])) && (!TextUtils.isEmpty(arrayOfString[1])))
      return new an(arrayOfString[0], arrayOfString[1]);
    return null;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     u.aly.s
 * JD-Core Version:    0.6.2
 */