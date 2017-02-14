package cn.sharesdk.framework.statistics.b;

public class d extends c
{
  private static int d;
  private static long n;
  public String a;
  public int b;
  public String c = "";

  protected String a()
  {
    return "[EVT]";
  }

  protected void a(long paramLong)
  {
    n = paramLong;
  }

  protected int b()
  {
    return 5000;
  }

  protected int c()
  {
    return 30;
  }

  protected long d()
  {
    return d;
  }

  protected long e()
  {
    return n;
  }

  protected void f()
  {
    d += 1;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(super.toString());
    localStringBuilder.append('|').append(this.a);
    localStringBuilder.append('|').append(this.b);
    localStringBuilder.append('|').append(this.c);
    return localStringBuilder.toString();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.framework.statistics.b.d
 * JD-Core Version:    0.6.2
 */