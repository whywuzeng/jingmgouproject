package u.aly;

public enum bk
  implements cl
{
  private final int e;

  private bk(int paramInt)
  {
    this.e = paramInt;
  }

  public static bk a(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return null;
    case 0:
      return a;
    case 1:
      return b;
    case 2:
      return c;
    case 3:
    }
    return d;
  }

  public int a()
  {
    return this.e;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     u.aly.bk
 * JD-Core Version:    0.6.2
 */