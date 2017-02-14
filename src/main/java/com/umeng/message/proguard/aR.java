package com.umeng.message.proguard;

import java.nio.charset.Charset;

final class aR
{
  public static final Charset a = Charset.forName("UTF-8");

  public static int a(int paramInt)
  {
    return (0xFF000000 & paramInt) >>> 24 | (0xFF0000 & paramInt) >>> 8 | (0xFF00 & paramInt) << 8 | (paramInt & 0xFF) << 24;
  }

  public static long a(long paramLong)
  {
    return (0x0 & paramLong) >>> 56 | (0x0 & paramLong) >>> 40 | (0x0 & paramLong) >>> 24 | (0x0 & paramLong) >>> 8 | (0xFF000000 & paramLong) << 8 | (0xFF0000 & paramLong) << 24 | (0xFF00 & paramLong) << 40 | (0xFF & paramLong) << 56;
  }

  public static short a(short paramShort)
  {
    paramShort = 0xFFFF & paramShort;
    return (short)((paramShort & 0xFF) << 8 | (0xFF00 & paramShort) >>> 8);
  }

  public static void a(long paramLong1, long paramLong2, long paramLong3)
  {
    if (((paramLong2 | paramLong3) < 0L) || (paramLong2 > paramLong1) || (paramLong1 - paramLong2 < paramLong3))
      throw new ArrayIndexOutOfBoundsException(String.format("size=%s offset=%s byteCount=%s", new Object[] { Long.valueOf(paramLong1), Long.valueOf(paramLong2), Long.valueOf(paramLong3) }));
  }

  public static void a(Throwable paramThrowable)
  {
    b(paramThrowable);
  }

  private static <T extends Throwable> void b(Throwable paramThrowable)
    throws Throwable
  {
    throw paramThrowable;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.aR
 * JD-Core Version:    0.6.2
 */