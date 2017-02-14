package u.aly;

public final class de
{
  public final String a;
  public final byte b;
  public final int c;

  public de()
  {
    this("", (byte)0, 0);
  }

  public de(String paramString, byte paramByte, int paramInt)
  {
    this.a = paramString;
    this.b = paramByte;
    this.c = paramInt;
  }

  public boolean a(de paramde)
  {
    return (this.a.equals(paramde.a)) && (this.b == paramde.b) && (this.c == paramde.c);
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof de))
      return a((de)paramObject);
    return false;
  }

  public String toString()
  {
    return "<TMessage name:'" + this.a + "' type: " + this.b + " seqid:" + this.c + ">";
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     u.aly.de
 * JD-Core Version:    0.6.2
 */