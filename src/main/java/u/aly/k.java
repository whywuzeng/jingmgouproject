package u.aly;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.ReportPolicy.a;
import com.umeng.analytics.ReportPolicy.b;
import com.umeng.analytics.ReportPolicy.c;
import com.umeng.analytics.ReportPolicy.d;
import com.umeng.analytics.ReportPolicy.f;
import com.umeng.analytics.ReportPolicy.g;
import com.umeng.analytics.ReportPolicy.h;
import com.umeng.analytics.b;
import com.umeng.analytics.f;
import com.umeng.analytics.g;
import com.umeng.analytics.h;
import java.util.Iterator;
import java.util.List;

public final class k
  implements com.umeng.analytics.onlineconfig.c, p, w
{
  private s a = null;
  private h b = null;
  private aa c = null;
  private ak d = new ak();
  private a e = null;
  private int f = 10;
  private int g;
  private Context h;

  public k(Context paramContext)
  {
    this.h = paramContext;
    this.a = new s(paramContext);
    this.c = new aa(paramContext);
    this.b = h.a(paramContext);
    this.d.a(this.b.d());
    this.e = new a();
    this.g = this.b.d(-1);
  }

  private bn a(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null)
      return null;
    try
    {
      bn localbn = new bn();
      new ck().a(localbn, paramArrayOfByte);
      return localbn;
    }
    catch (Exception paramArrayOfByte)
    {
      paramArrayOfByte.printStackTrace();
    }
    return null;
  }

  private void a(bn parambn)
  {
    d locald;
    if (parambn != null)
    {
      locald = d.a(this.h);
      locald.a();
      parambn.a(locald.b());
      parambn = b(parambn);
      if (parambn != null);
    }
    else
    {
      return;
    }
    if (f());
    for (parambn = c.b(this.h, AnalyticsConfig.getAppkey(this.h), parambn); ; parambn = c.a(this.h, AnalyticsConfig.getAppkey(this.h), parambn))
    {
      parambn = parambn.c();
      h localh = h.a(this.h);
      localh.g();
      localh.b(parambn);
      locald.d();
      return;
    }
  }

  private void a(boolean paramBoolean)
  {
    boolean bool = this.c.f();
    if (bool)
      this.a.a(new am(this.c.n()));
    if (b(paramBoolean))
      e();
    while ((!bool) && (!d()))
      return;
    b();
  }

  private boolean b(boolean paramBoolean)
  {
    boolean bool2 = true;
    boolean bool1;
    if (!bq.l(this.h))
    {
      if (br.a)
        br.c("MobclickAgent", "network is unavailable");
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      }
      while (this.c.f());
      if (!br.a)
        break;
      bool1 = bool2;
    }
    while (bq.w(this.h));
    return this.e.c().a(paramBoolean);
  }

  private byte[] b(bn parambn)
  {
    if (parambn == null)
      return null;
    try
    {
      byte[] arrayOfByte = new cq().a(parambn);
      if (br.a)
        br.c("MobclickAgent", parambn.toString());
      return arrayOfByte;
    }
    catch (Exception parambn)
    {
      br.b("MobclickAgent", "Fail to serialize log ...", parambn);
    }
    return null;
  }

  private void d(int paramInt)
  {
    a(a(new int[] { paramInt, (int)(System.currentTimeMillis() - this.c.o()) }));
    f.a(new g()
    {
      public void a()
      {
        k.this.a();
      }
    }
    , paramInt);
  }

  private boolean d()
  {
    return this.a.b() > this.f;
  }

  private void e()
  {
    try
    {
      if (this.b.h())
      {
        localObject = new y(this.h, this.c);
        ((y)localObject).a(this);
        if (this.d.c())
          ((y)localObject).b(true);
        ((y)localObject).a();
        return;
      }
      Object localObject = a(new int[0]);
      if (localObject == null)
      {
        br.a("MobclickAgent", "No data to report");
        return;
      }
    }
    catch (Throwable localThrowable)
    {
      if ((!(localThrowable instanceof OutOfMemoryError)) || (localThrowable != null))
      {
        localThrowable.printStackTrace();
        return;
        y localy = new y(this.h, this.c);
        localy.a(this);
        if (this.d.c())
          localy.b(true);
        localy.a(localThrowable);
        localy.a(f());
        localy.a();
      }
    }
  }

  private void e(int paramInt)
  {
    d(paramInt);
  }

  private boolean f()
  {
    switch (this.g)
    {
    case 0:
    default:
      return false;
    case 1:
      return true;
    case -1:
    }
    return AnalyticsConfig.sEncrypt;
  }

  protected bn a(int[] paramArrayOfInt)
  {
    int i = 0;
    label241: label244: label247: label249: 
    while (true)
    {
      Object localObject1;
      try
      {
        if (TextUtils.isEmpty(AnalyticsConfig.getAppkey(this.h)))
        {
          br.b("MobclickAgent", "Appkey is missing ,Please check AndroidManifest.xml");
          return null;
        }
        localObject1 = h.a(this.h).f();
        if (localObject1 == null)
        {
          localObject1 = null;
          if ((localObject1 == null) && (this.a.b() == 0))
            return null;
        }
        else
        {
          localObject1 = a((byte[])localObject1);
          continue;
        }
        if (localObject1 != null)
          break label244;
        localObject1 = new bn();
        this.a.a((bn)localObject1);
        if ((br.a) && (((bn)localObject1).B()))
        {
          localObject2 = ((bn)localObject1).z().iterator();
          if (((Iterator)localObject2).hasNext())
          {
            if (((bl)((Iterator)localObject2).next()).p() <= 0)
              break label241;
            i = 1;
            break label249;
          }
          if (i == 0)
            br.e("MobclickAgent", "missing Activities or PageViews");
        }
        localObject1 = this.d.a(this.h, (bn)localObject1);
        if ((paramArrayOfInt == null) || (paramArrayOfInt.length != 2))
          break label247;
        Object localObject2 = new aq();
        ((aq)localObject2).a(new bd(paramArrayOfInt[0] / 1000, paramArrayOfInt[1]));
        ((bn)localObject1).a((aq)localObject2);
        return localObject1;
      }
      catch (Exception paramArrayOfInt)
      {
        br.b("MobclickAgent", "Fail to construct message ...", paramArrayOfInt);
        h.a(this.h).g();
        return null;
      }
      break label249;
      continue;
      return localObject1;
    }
  }

  public void a()
  {
    if (bq.l(this.h))
      e();
    while (!br.a)
      return;
    br.c("MobclickAgent", "network is unavailable");
  }

  public void a(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt <= 3))
    {
      this.d.a(paramInt);
      this.e.b(paramInt);
    }
  }

  public void a(int paramInt, long paramLong)
  {
    this.e.a(paramInt, (int)paramLong);
  }

  public void a(q paramq)
  {
    if (paramq != null)
      this.a.a(paramq);
    a(paramq instanceof bl);
  }

  public void b()
  {
    if (this.a.b() > 0);
    try
    {
      byte[] arrayOfByte = b(a(new int[0]));
      if (arrayOfByte != null)
        this.b.a(arrayOfByte);
      return;
    }
    catch (Throwable localThrowable)
    {
      do
        if ((localThrowable instanceof OutOfMemoryError))
          this.b.g();
      while (localThrowable == null);
      localThrowable.printStackTrace();
    }
  }

  public void b(int paramInt)
  {
    if (paramInt > 0)
      this.e.a(paramInt);
  }

  public void b(q paramq)
  {
    this.a.a(paramq);
  }

  public void c()
  {
    a(a(new int[0]));
  }

  public void c(int paramInt)
  {
    this.g = paramInt;
  }

  public class a
  {
    private final long b = 1296000000L;
    private final int c = 1800000;
    private final int d = 10000;
    private ReportPolicy.g e;
    private int f = k.a(k.this).d();
    private int g;
    private int h;
    private int i;
    private boolean j = false;

    public a()
    {
      int k = k.a(k.this).e();
      if (k > 0)
        this.g = c(k);
      while (true)
      {
        this$1 = k.a(k.this).c();
        this.h = k.this[0];
        this.i = k.this[1];
        return;
        if (AnalyticsConfig.sLatentWindow > 0)
          this.g = c(AnalyticsConfig.sLatentWindow);
        else
          this.g = 10000;
      }
    }

    private ReportPolicy.g b(int paramInt1, int paramInt2)
    {
      switch (paramInt1)
      {
      case 2:
      case 3:
      default:
        return new ReportPolicy.c();
      case 1:
        return new ReportPolicy.c();
      case 6:
        return new ReportPolicy.d(k.b(k.this), paramInt2);
      case 4:
        return new ReportPolicy.f(k.b(k.this));
      case 0:
        return new ReportPolicy.g();
      case 5:
      }
      return new ReportPolicy.h(k.d(k.this));
    }

    private int c(int paramInt)
    {
      int k = paramInt;
      if (paramInt > 1800000)
        k = 1800000;
      return k;
    }

    protected void a()
    {
      int m = 1;
      int k = 1;
      Object localObject;
      if (this.f > 0)
        if (((this.e instanceof ReportPolicy.a)) && (this.e.a()))
        {
          if (k == 0)
            break label56;
          localObject = this.e;
          label40: this.e = ((ReportPolicy.g)localObject);
        }
      while (true)
      {
        this.j = false;
        return;
        k = 0;
        break;
        label56: localObject = new ReportPolicy.a(k.b(k.this), k.c(k.this));
        break label40;
        if (((this.e instanceof ReportPolicy.b)) && (this.e.a()));
        for (k = m; ; k = 0)
        {
          if (k != 0)
            break label160;
          if (!b())
            break label162;
          localObject = c.a(k.d(k.this));
          k = b.a(this.g, (String)localObject);
          this.e = new ReportPolicy.b(k);
          k.a(k.this, k);
          break;
        }
        label160: continue;
        label162: this.e = b(this.h, this.i);
      }
    }

    public void a(int paramInt)
    {
      this.g = c(paramInt);
      d();
    }

    public void a(int paramInt1, int paramInt2)
    {
      this.h = paramInt1;
      this.i = paramInt2;
      d();
    }

    public void b(int paramInt)
    {
      this.f = paramInt;
      d();
    }

    protected boolean b()
    {
      if (k.a(k.this).h());
      while ((k.b(k.this).f()) || (System.currentTimeMillis() - k.b(k.this).o() <= 1296000000L))
        return false;
      return true;
    }

    public ReportPolicy.g c()
    {
      a();
      return this.e;
    }

    protected void d()
    {
      this.j = true;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     u.aly.k
 * JD-Core Version:    0.6.2
 */