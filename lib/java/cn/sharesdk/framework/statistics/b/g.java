package cn.sharesdk.framework.statistics.b;

import android.content.Context;
import android.text.TextUtils;

public class g extends c
{
  private static int a;
  private static long b;

  protected String a()
  {
    return "[RUN]";
  }

  protected void a(long paramLong)
  {
    b = paramLong;
  }

  public boolean a(Context paramContext)
  {
    cn.sharesdk.framework.statistics.a.c localc = cn.sharesdk.framework.statistics.a.c.a(paramContext);
    a = localc.c("insertRunEventCount");
    b = localc.b("lastInsertRunEventTime");
    return super.a(paramContext);
  }

  protected int b()
  {
    return 5000;
  }

  public void b(Context paramContext)
  {
    super.b(paramContext);
    paramContext = cn.sharesdk.framework.statistics.a.c.a(paramContext);
    paramContext.a("lastInsertRunEventTime", Long.valueOf(b));
    paramContext.a("insertRunEventCount", a);
  }

  protected int c()
  {
    return 5;
  }

  protected long d()
  {
    return a;
  }

  protected long e()
  {
    return b;
  }

  protected void f()
  {
    a += 1;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(super.toString());
    localStringBuilder.append('|');
    if (!TextUtils.isEmpty(this.m))
      localStringBuilder.append(this.m);
    return localStringBuilder.toString();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.framework.statistics.b.g
 * JD-Core Version:    0.6.2
 */