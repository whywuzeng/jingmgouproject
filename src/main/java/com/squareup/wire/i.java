package com.squareup.wire;

import com.umeng.message.proguard.aB;
import com.umeng.message.proguard.aD;
import com.umeng.message.proguard.aE;
import com.umeng.message.proguard.aJ;
import com.umeng.message.proguard.aP;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

final class i
{
  public static final int a = 64;
  private static final Charset c = Charset.forName("UTF-8");
  private static final String d = "Encountered a negative size";
  private static final String e = "The input ended unexpectedly in the middle of a field";
  private static final String f = "Protocol message contained an invalid tag (zero).";
  private static final String g = "Protocol message end-group tag did not match expected tag.";
  private static final String h = "WireInput encountered a malformed varint.";
  public int b;
  private final aD i;
  private int j = 0;
  private int k = 2147483647;
  private int l;

  private i(aD paramaD)
  {
    this.i = paramaD;
  }

  public static long a(long paramLong)
  {
    return paramLong >>> 1 ^ -(1L & paramLong);
  }

  public static i a(aP paramaP)
  {
    return new i(aJ.a(paramaP));
  }

  public static i a(InputStream paramInputStream)
  {
    return new i(aJ.a(aJ.a(paramInputStream)));
  }

  public static i a(byte[] paramArrayOfByte)
  {
    return new i(new aB().a(paramArrayOfByte));
  }

  public static i a(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    return new i(new aB().b(paramArrayOfByte, paramInt1, paramInt2));
  }

  private void b(long paramLong)
    throws IOException
  {
    this.j = ((int)(this.j + paramLong));
    this.i.g(paramLong);
  }

  public static int c(int paramInt)
  {
    return paramInt >>> 1 ^ -(paramInt & 0x1);
  }

  private boolean f(int paramInt)
    throws IOException
  {
    switch (1.a[WireType.valueOf(paramInt).ordinal()])
    {
    default:
      throw new AssertionError();
    case 1:
      e();
      return false;
    case 2:
      f();
      return false;
    case 3:
      g();
      return false;
    case 4:
      b(d());
      return false;
    case 5:
      i();
      a(paramInt & 0xFFFFFFF8 | WireType.END_GROUP.value());
      return false;
    case 6:
    }
    return true;
  }

  private boolean j()
    throws IOException
  {
    if (h() == this.k)
      return true;
    return this.i.e();
  }

  public int a()
    throws IOException
  {
    if (j())
    {
      this.l = 0;
      return 0;
    }
    this.l = d();
    if (this.l == 0)
      throw new IOException("Protocol message contained an invalid tag (zero).");
    return this.l;
  }

  public void a(int paramInt)
    throws IOException
  {
    if (this.l != paramInt)
      throw new IOException("Protocol message end-group tag did not match expected tag.");
  }

  public aE b(int paramInt)
    throws IOException
  {
    this.j += paramInt;
    this.i.a(paramInt);
    return this.i.c(paramInt);
  }

  public String b()
    throws IOException
  {
    int m = d();
    this.j += m;
    return this.i.a(m, c);
  }

  public aE c()
    throws IOException
  {
    return b(d());
  }

  public int d()
    throws IOException
  {
    this.j += 1;
    int m = this.i.h();
    if (m >= 0);
    int i1;
    do
    {
      return m;
      m &= 127;
      this.j += 1;
      n = this.i.h();
      if (n >= 0)
        return m | n << 7;
      m |= (n & 0x7F) << 7;
      this.j += 1;
      n = this.i.h();
      if (n >= 0)
        return m | n << 14;
      m |= (n & 0x7F) << 14;
      this.j += 1;
      i1 = this.i.h();
      if (i1 >= 0)
        return m | i1 << 21;
      this.j += 1;
      n = this.i.h();
      i1 = m | (i1 & 0x7F) << 21 | n << 28;
      m = i1;
    }
    while (n >= 0);
    int n = 0;
    while (true)
    {
      if (n >= 5)
        break label223;
      this.j += 1;
      m = i1;
      if (this.i.h() >= 0)
        break;
      n += 1;
    }
    label223: throw new IOException("WireInput encountered a malformed varint.");
  }

  public int d(int paramInt)
    throws IOException
  {
    if (paramInt < 0)
      throw new IOException("Encountered a negative size");
    paramInt = this.j + paramInt;
    int m = this.k;
    if (paramInt > m)
      throw new EOFException("The input ended unexpectedly in the middle of a field");
    this.k = paramInt;
    return m;
  }

  public long e()
    throws IOException
  {
    int m = 0;
    long l1 = 0L;
    while (m < 64)
    {
      this.j += 1;
      int n = this.i.h();
      l1 |= (n & 0x7F) << m;
      if ((n & 0x80) == 0)
        return l1;
      m += 7;
    }
    throw new IOException("WireInput encountered a malformed varint.");
  }

  public void e(int paramInt)
  {
    this.k = paramInt;
  }

  public int f()
    throws IOException
  {
    this.j += 4;
    return this.i.m();
  }

  public long g()
    throws IOException
  {
    this.j += 8;
    return this.i.n();
  }

  public long h()
  {
    return this.j;
  }

  public void i()
    throws IOException
  {
    int m;
    do
      m = a();
    while ((m != 0) && (!f(m)));
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.squareup.wire.i
 * JD-Core Version:    0.6.2
 */