package u.aly;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class ck
{
  private final dg a;
  private final dt b = new dt();

  public ck()
  {
    this(new da.a());
  }

  public ck(di paramdi)
  {
    this.a = paramdi.a(this.b);
  }

  private Object a(byte paramByte, byte[] paramArrayOfByte, co paramco, co[] paramArrayOfco)
    throws cn
  {
    try
    {
      paramArrayOfByte = j(paramArrayOfByte, paramco, paramArrayOfco);
      if (paramArrayOfByte != null)
        switch (paramByte)
        {
        default:
        case 2:
        case 3:
        case 4:
        case 6:
        case 8:
        case 10:
        case 11:
        case 100:
        }
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                do
                {
                  do
                  {
                    do
                      return null;
                    while (paramArrayOfByte.b != 2);
                    boolean bool = this.a.t();
                    return Boolean.valueOf(bool);
                  }
                  while (paramArrayOfByte.b != 3);
                  byte b1 = this.a.u();
                  return Byte.valueOf(b1);
                }
                while (paramArrayOfByte.b != 4);
                double d = this.a.y();
                return Double.valueOf(d);
              }
              while (paramArrayOfByte.b != 6);
              short s = this.a.v();
              return Short.valueOf(s);
            }
            while (paramArrayOfByte.b != 8);
            paramByte = this.a.w();
            return Integer.valueOf(paramByte);
          }
          while (paramArrayOfByte.b != 10);
          long l = this.a.x();
          return Long.valueOf(l);
        }
        while (paramArrayOfByte.b != 11);
        paramArrayOfByte = this.a.z();
        return paramArrayOfByte;
      }
      while (paramArrayOfByte.b != 11);
      paramArrayOfByte = this.a.A();
      return paramArrayOfByte;
    }
    catch (Exception paramArrayOfByte)
    {
      throw new cn(paramArrayOfByte);
    }
    finally
    {
      this.b.e();
      this.a.B();
    }
    throw paramArrayOfByte;
  }

  private db j(byte[] paramArrayOfByte, co paramco, co[] paramArrayOfco)
    throws cn
  {
    int j = 0;
    this.b.a(paramArrayOfByte);
    co[] arrayOfco = new co[paramArrayOfco.length + 1];
    arrayOfco[0] = paramco;
    int i = 0;
    while (i < paramArrayOfco.length)
    {
      arrayOfco[(i + 1)] = paramArrayOfco[i];
      i += 1;
    }
    this.a.j();
    paramArrayOfByte = null;
    i = j;
    while (i < arrayOfco.length)
    {
      paramco = this.a.l();
      if ((paramco.b == 0) || (paramco.c > arrayOfco[i].a()))
        return null;
      if (paramco.c != arrayOfco[i].a())
      {
        dj.a(this.a, paramco.b);
        this.a.m();
        paramArrayOfByte = paramco;
      }
      else
      {
        j = i + 1;
        paramArrayOfByte = paramco;
        i = j;
        if (j < arrayOfco.length)
        {
          this.a.j();
          paramArrayOfByte = paramco;
          i = j;
        }
      }
    }
    return paramArrayOfByte;
  }

  public Boolean a(byte[] paramArrayOfByte, co paramco, co[] paramArrayOfco)
    throws cn
  {
    return (Boolean)a((byte)2, paramArrayOfByte, paramco, paramArrayOfco);
  }

  public void a(ch paramch, String paramString)
    throws cn
  {
    a(paramch, paramString.getBytes());
  }

  public void a(ch paramch, String paramString1, String paramString2)
    throws cn
  {
    try
    {
      a(paramch, paramString1.getBytes(paramString2));
      return;
    }
    catch (UnsupportedEncodingException paramch)
    {
      throw new cn("JVM DOES NOT SUPPORT ENCODING: " + paramString2);
    }
    finally
    {
      this.a.B();
    }
    throw paramch;
  }

  public void a(ch paramch, byte[] paramArrayOfByte)
    throws cn
  {
    try
    {
      this.b.a(paramArrayOfByte);
      paramch.a(this.a);
      return;
    }
    finally
    {
      this.b.e();
      this.a.B();
    }
    throw paramch;
  }

  public void a(ch paramch, byte[] paramArrayOfByte, co paramco, co[] paramArrayOfco)
    throws cn
  {
    try
    {
      if (j(paramArrayOfByte, paramco, paramArrayOfco) != null)
        paramch.a(this.a);
      return;
    }
    catch (Exception paramch)
    {
      throw new cn(paramch);
    }
    finally
    {
      this.b.e();
      this.a.B();
    }
    throw paramch;
  }

  public Byte b(byte[] paramArrayOfByte, co paramco, co[] paramArrayOfco)
    throws cn
  {
    return (Byte)a((byte)3, paramArrayOfByte, paramco, paramArrayOfco);
  }

  public Double c(byte[] paramArrayOfByte, co paramco, co[] paramArrayOfco)
    throws cn
  {
    return (Double)a((byte)4, paramArrayOfByte, paramco, paramArrayOfco);
  }

  public Short d(byte[] paramArrayOfByte, co paramco, co[] paramArrayOfco)
    throws cn
  {
    return (Short)a((byte)6, paramArrayOfByte, paramco, paramArrayOfco);
  }

  public Integer e(byte[] paramArrayOfByte, co paramco, co[] paramArrayOfco)
    throws cn
  {
    return (Integer)a((byte)8, paramArrayOfByte, paramco, paramArrayOfco);
  }

  public Long f(byte[] paramArrayOfByte, co paramco, co[] paramArrayOfco)
    throws cn
  {
    return (Long)a((byte)10, paramArrayOfByte, paramco, paramArrayOfco);
  }

  public String g(byte[] paramArrayOfByte, co paramco, co[] paramArrayOfco)
    throws cn
  {
    return (String)a((byte)11, paramArrayOfByte, paramco, paramArrayOfco);
  }

  public ByteBuffer h(byte[] paramArrayOfByte, co paramco, co[] paramArrayOfco)
    throws cn
  {
    return (ByteBuffer)a((byte)100, paramArrayOfByte, paramco, paramArrayOfco);
  }

  public Short i(byte[] paramArrayOfByte, co paramco, co[] paramArrayOfco)
    throws cn
  {
    try
    {
      if (j(paramArrayOfByte, paramco, paramArrayOfco) != null)
      {
        this.a.j();
        short s = this.a.l().c;
        return Short.valueOf(s);
      }
      return null;
    }
    catch (Exception paramArrayOfByte)
    {
      throw new cn(paramArrayOfByte);
    }
    finally
    {
      this.b.e();
      this.a.B();
    }
    throw paramArrayOfByte;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     u.aly.ck
 * JD-Core Version:    0.6.2
 */