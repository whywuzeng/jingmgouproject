package u.aly;

public class dj
{
  private static int a = 2147483647;

  public static di a(byte[] paramArrayOfByte, di paramdi)
  {
    Object localObject;
    if (paramArrayOfByte[0] > 16)
      localObject = new da.a();
    do
    {
      do
      {
        return localObject;
        localObject = paramdi;
      }
      while (paramArrayOfByte.length <= 1);
      localObject = paramdi;
    }
    while ((paramArrayOfByte[1] & 0x80) == 0);
    return new da.a();
  }

  public static void a(int paramInt)
  {
    a = paramInt;
  }

  public static void a(dg paramdg, byte paramByte)
    throws cn
  {
    a(paramdg, paramByte, a);
  }

  public static void a(dg paramdg, byte paramByte, int paramInt)
    throws cn
  {
    byte b2 = 0;
    byte b3 = 0;
    byte b1 = 0;
    if (paramInt <= 0)
      throw new cn("Maximum skip depth exceeded");
    switch (paramByte)
    {
    case 5:
    case 7:
    case 9:
    default:
      return;
    case 2:
      paramdg.t();
      return;
    case 3:
      paramdg.u();
      return;
    case 6:
      paramdg.v();
      return;
    case 8:
      paramdg.w();
      return;
    case 10:
      paramdg.x();
      return;
    case 4:
      paramdg.y();
      return;
    case 11:
      paramdg.A();
      return;
    case 12:
      paramdg.j();
      while (true)
      {
        localObject = paramdg.l();
        if (((db)localObject).b == 0)
        {
          paramdg.k();
          return;
        }
        a(paramdg, ((db)localObject).b, paramInt - 1);
        paramdg.m();
      }
    case 13:
      localObject = paramdg.n();
      paramByte = b1;
      while (paramByte < ((dd)localObject).c)
      {
        a(paramdg, ((dd)localObject).a, paramInt - 1);
        a(paramdg, ((dd)localObject).b, paramInt - 1);
        paramByte += 1;
      }
      paramdg.o();
      return;
    case 14:
      localObject = paramdg.r();
      paramByte = b2;
      while (paramByte < ((dk)localObject).b)
      {
        a(paramdg, ((dk)localObject).a, paramInt - 1);
        paramByte += 1;
      }
      paramdg.s();
      return;
    case 15:
    }
    Object localObject = paramdg.p();
    paramByte = b3;
    while (paramByte < ((dc)localObject).b)
    {
      a(paramdg, ((dc)localObject).a, paramInt - 1);
      paramByte += 1;
    }
    paramdg.q();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     u.aly.dj
 * JD-Core Version:    0.6.2
 */