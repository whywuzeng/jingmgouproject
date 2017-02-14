package com.umeng.message.proguard;

import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Deflater;

public final class aG
  implements aO
{
  private final aC a;
  private final Deflater b = new Deflater(-1, true);
  private final aF c;
  private boolean d;
  private final CRC32 e = new CRC32();

  public aG(aO paramaO)
  {
    this.a = aJ.a(paramaO);
    this.c = new aF(this.a, this.b);
    a();
  }

  private void a()
  {
    aB localaB = this.a.b();
    localaB.b(8075);
    localaB.a(8);
    localaB.a(0);
    localaB.d(0);
    localaB.a(0);
    localaB.a(0);
  }

  private void a(aB paramaB, long paramLong)
  {
    for (paramaB = paramaB.a; paramLong > 0L; paramaB = paramaB.e)
    {
      int i = (int)Math.min(paramLong, paramaB.d - paramaB.c);
      this.e.update(paramaB.b, paramaB.c, i);
      paramLong -= i;
    }
  }

  private void b()
    throws IOException
  {
    this.a.g((int)this.e.getValue());
    this.a.g(this.b.getTotalIn());
  }

  public void b(aB paramaB, long paramLong)
    throws IOException
  {
    if (paramLong < 0L)
      throw new IllegalArgumentException("byteCount < 0: " + paramLong);
    if (paramLong == 0L)
      return;
    a(paramaB, paramLong);
    this.c.b(paramaB, paramLong);
  }

  public void close()
    throws IOException
  {
    if (this.d);
    while (true)
    {
      return;
      Object localObject2 = null;
      try
      {
        this.c.a();
        b();
      }
      catch (Throwable localThrowable1)
      {
        try
        {
          this.b.end();
          localObject1 = localObject2;
        }
        catch (Throwable localThrowable1)
        {
          try
          {
            while (true)
            {
              Object localObject1;
              this.a.close();
              localObject2 = localObject1;
              this.d = true;
              if (localObject2 == null)
                break;
              aR.a(localObject2);
              return;
              localThrowable3 = localThrowable3;
              continue;
              localThrowable1 = localThrowable1;
              if (localThrowable3 != null)
                localThrowable2 = localThrowable3;
            }
          }
          catch (Throwable localThrowable4)
          {
            while (true)
            {
              Throwable localThrowable2;
              Object localObject3 = localThrowable2;
              if (localThrowable2 == null)
                localObject3 = localThrowable4;
            }
          }
        }
      }
    }
  }

  public void s()
    throws IOException
  {
    this.c.s();
  }

  public aQ t()
  {
    return this.a.t();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.aG
 * JD-Core Version:    0.6.2
 */