package com.umeng.message.proguard;

import android.text.TextUtils;

public final class bC
{
  public static final int a(long paramLong)
  {
    return a(paramLong, null);
  }

  public static final int a(long paramLong, String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      paramString = paramString.getBytes();
      return (int)(Math.abs(a(paramString, 0, paramString.length, 2147483647)) % paramLong);
    }
    return (int)(Math.random() * paramLong);
  }

  public static final int a(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3)
  {
    int j = paramInt2 >> 2;
    paramInt3 ^= paramInt2;
    int i = 0;
    while (i < j)
    {
      int k = i << 2;
      int m = paramArrayOfByte[(paramInt1 + k + 3)];
      int n = paramArrayOfByte[(paramInt1 + k + 2)];
      int i1 = paramArrayOfByte[(paramInt1 + k + 1)];
      k = (paramArrayOfByte[(k + paramInt1 + 0)] & 0xFF | ((m << 8 | n & 0xFF) << 8 | i1 & 0xFF) << 8) * 1540483477;
      i += 1;
      paramInt3 = (k ^ k >>> 24) * 1540483477 ^ paramInt3 * 1540483477;
    }
    j = paramInt2 - (j << 2);
    i = paramInt3;
    if (j != 0)
    {
      i = paramInt3;
      if (j >= 3)
        i = paramInt3 ^ paramArrayOfByte[(paramInt1 + paramInt2 - 3)] << 16;
      paramInt3 = i;
      if (j >= 2)
        paramInt3 = i ^ paramArrayOfByte[(paramInt1 + paramInt2 - 2)] << 8;
      i = paramInt3;
      if (j >= 1)
        i = paramInt3 ^ paramArrayOfByte[(paramInt1 + paramInt2 - 1)];
      i *= 1540483477;
    }
    paramInt1 = (i ^ i >>> 13) * 1540483477;
    return paramInt1 ^ paramInt1 >>> 15;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.bC
 * JD-Core Version:    0.6.2
 */