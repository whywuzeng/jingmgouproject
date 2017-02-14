package u.aly;

public class cf
{
  private short[] a;
  private int b = -1;

  public cf(int paramInt)
  {
    this.a = new short[paramInt];
  }

  private void d()
  {
    short[] arrayOfShort = new short[this.a.length * 2];
    System.arraycopy(this.a, 0, arrayOfShort, 0, this.a.length);
    this.a = arrayOfShort;
  }

  public short a()
  {
    short[] arrayOfShort = this.a;
    int i = this.b;
    this.b = (i - 1);
    return arrayOfShort[i];
  }

  public void a(short paramShort)
  {
    if (this.a.length == this.b + 1)
      d();
    short[] arrayOfShort = this.a;
    int i = this.b + 1;
    this.b = i;
    arrayOfShort[i] = paramShort;
  }

  public short b()
  {
    return this.a[this.b];
  }

  public void c()
  {
    this.b = -1;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("<ShortStack vector:[");
    int i = 0;
    while (i < this.a.length)
    {
      if (i != 0)
        localStringBuilder.append(" ");
      if (i == this.b)
        localStringBuilder.append(">>");
      localStringBuilder.append(this.a[i]);
      if (i == this.b)
        localStringBuilder.append("<<");
      i += 1;
    }
    localStringBuilder.append("]>");
    return localStringBuilder.toString();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     u.aly.cf
 * JD-Core Version:    0.6.2
 */