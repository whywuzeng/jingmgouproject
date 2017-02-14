package com.squareup.wire;

import java.io.IOException;

public final class WireOutput
{
  private final byte[] a;
  private final int b;
  private int c;

  private WireOutput(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    this.a = paramArrayOfByte;
    this.c = paramInt1;
    this.b = (paramInt1 + paramInt2);
  }

  static int a(int paramInt)
  {
    return b(makeTag(paramInt, WireType.VARINT));
  }

  static int a(long paramLong)
  {
    if ((0xFFFFFF80 & paramLong) == 0L)
      return 1;
    if ((0xFFFFC000 & paramLong) == 0L)
      return 2;
    if ((0xFFE00000 & paramLong) == 0L)
      return 3;
    if ((0xF0000000 & paramLong) == 0L)
      return 4;
    if ((0x0 & paramLong) == 0L)
      return 5;
    if ((0x0 & paramLong) == 0L)
      return 6;
    if ((0x0 & paramLong) == 0L)
      return 7;
    if ((0x0 & paramLong) == 0L)
      return 8;
    if ((0x0 & paramLong) == 0L)
      return 9;
    return 10;
  }

  static WireOutput a(byte[] paramArrayOfByte)
  {
    return a(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  static WireOutput a(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    return new WireOutput(paramArrayOfByte, paramInt1, paramInt2);
  }

  static int b(int paramInt)
  {
    if ((paramInt & 0xFFFFFF80) == 0)
      return 1;
    if ((paramInt & 0xFFFFC000) == 0)
      return 2;
    if ((0xFFE00000 & paramInt) == 0)
      return 3;
    if ((0xF0000000 & paramInt) == 0)
      return 4;
    return 5;
  }

  static long d(long paramLong)
  {
    return paramLong << 1 ^ paramLong >> 63;
  }

  static int g(int paramInt)
  {
    return paramInt << 1 ^ paramInt >> 31;
  }

  public static int int32Size(int paramInt)
  {
    if (paramInt >= 0)
      return b(paramInt);
    return 10;
  }

  public static int int64Size(long paramLong)
  {
    if (paramLong >= 0L)
      return a(paramLong);
    return 10;
  }

  public static int makeTag(int paramInt, WireType paramWireType)
  {
    return paramInt << 3 | paramWireType.value();
  }

  public static int messageHeaderSize(int paramInt1, int paramInt2)
  {
    return tagSize(paramInt1, WireType.LENGTH_DELIMITED) + int32Size(paramInt2);
  }

  public static int messageSize(int paramInt1, int paramInt2)
  {
    return tagSize(paramInt1, WireType.LENGTH_DELIMITED) + int32Size(paramInt2) + paramInt2;
  }

  public static int tagSize(int paramInt, WireType paramWireType)
  {
    return int32Size(makeTag(paramInt, paramWireType));
  }

  public static int writeMessageHeader(int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3)
  {
    paramInt1 = writeTag(paramInt1, WireType.LENGTH_DELIMITED, paramArrayOfByte, paramInt2) + paramInt2;
    return paramInt1 + writeVarint(paramInt3, paramArrayOfByte, paramInt1) - paramInt2;
  }

  public static int writeTag(int paramInt1, WireType paramWireType, byte[] paramArrayOfByte, int paramInt2)
  {
    return writeVarint(makeTag(paramInt1, paramWireType), paramArrayOfByte, paramInt2);
  }

  public static int writeVarint(long paramLong, byte[] paramArrayOfByte, int paramInt)
  {
    int i = paramInt;
    while (true)
    {
      if ((0xFFFFFF80 & paramLong) == 0L)
      {
        paramArrayOfByte[i] = ((byte)(int)paramLong);
        return i + 1 - paramInt;
      }
      paramArrayOfByte[i] = ((byte)(int)(0x7F & paramLong | 0x80));
      paramLong >>>= 7;
      i += 1;
    }
  }

  void a(byte paramByte)
    throws IOException
  {
    if (this.c == this.b)
      throw new IOException("Out of space: position=" + this.c + ", limit=" + this.b);
    byte[] arrayOfByte = this.a;
    int i = this.c;
    this.c = (i + 1);
    arrayOfByte[i] = paramByte;
  }

  void a(int paramInt, WireType paramWireType)
    throws IOException
  {
    e(makeTag(paramInt, paramWireType));
  }

  void b(long paramLong)
    throws IOException
  {
    while (true)
    {
      if ((0xFFFFFF80 & paramLong) == 0L)
      {
        c((int)paramLong);
        return;
      }
      c((int)paramLong & 0x7F | 0x80);
      paramLong >>>= 7;
    }
  }

  void b(byte[] paramArrayOfByte)
    throws IOException
  {
    b(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  void b(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (this.b - this.c >= paramInt2)
    {
      System.arraycopy(paramArrayOfByte, paramInt1, this.a, this.c, paramInt2);
      this.c += paramInt2;
      return;
    }
    throw new IOException("Out of space: position=" + this.c + ", limit=" + this.b);
  }

  void c(int paramInt)
    throws IOException
  {
    a((byte)paramInt);
  }

  void c(long paramLong)
    throws IOException
  {
    c((int)paramLong & 0xFF);
    c((int)(paramLong >> 8) & 0xFF);
    c((int)(paramLong >> 16) & 0xFF);
    c((int)(paramLong >> 24) & 0xFF);
    c((int)(paramLong >> 32) & 0xFF);
    c((int)(paramLong >> 40) & 0xFF);
    c((int)(paramLong >> 48) & 0xFF);
    c((int)(paramLong >> 56) & 0xFF);
  }

  void d(int paramInt)
    throws IOException
  {
    if (paramInt >= 0)
    {
      e(paramInt);
      return;
    }
    b(paramInt);
  }

  void e(int paramInt)
    throws IOException
  {
    while (true)
    {
      if ((paramInt & 0xFFFFFF80) == 0)
      {
        c(paramInt);
        return;
      }
      c(paramInt & 0x7F | 0x80);
      paramInt >>>= 7;
    }
  }

  void f(int paramInt)
    throws IOException
  {
    c(paramInt & 0xFF);
    c(paramInt >> 8 & 0xFF);
    c(paramInt >> 16 & 0xFF);
    c(paramInt >> 24 & 0xFF);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.squareup.wire.WireOutput
 * JD-Core Version:    0.6.2
 */