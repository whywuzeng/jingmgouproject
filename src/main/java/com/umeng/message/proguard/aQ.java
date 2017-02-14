package com.umeng.message.proguard;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

public class aQ
{
  public static final aQ b = new aQ()
  {
    public aQ a(long paramAnonymousLong, TimeUnit paramAnonymousTimeUnit)
    {
      return this;
    }

    public aQ b(long paramAnonymousLong, TimeUnit paramAnonymousTimeUnit)
    {
      return this;
    }

    public void g()
      throws IOException
    {
    }
  };
  private long a;
  private long c = -1L;
  private long d = -1L;

  public aQ a(long paramLong, TimeUnit paramTimeUnit)
  {
    if (paramLong <= 0L)
      throw new IllegalArgumentException("timeout <= 0: " + paramLong);
    this.d = paramTimeUnit.toNanos(paramLong);
    return this;
  }

  public aQ b(long paramLong, TimeUnit paramTimeUnit)
  {
    if (paramLong <= 0L)
      throw new IllegalArgumentException("duration <= 0: " + paramLong);
    this.a = System.nanoTime();
    this.c = paramTimeUnit.toNanos(paramLong);
    return this;
  }

  public long e()
  {
    return this.d;
  }

  public aQ f()
  {
    this.c = -1L;
    this.d = -1L;
    return this;
  }

  public void g()
    throws IOException
  {
    if (Thread.interrupted())
      throw new InterruptedIOException();
    if ((this.c != -1L) && (System.nanoTime() - this.a > this.c))
      throw new IOException("deadline reached");
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.aQ
 * JD-Core Version:    0.6.2
 */