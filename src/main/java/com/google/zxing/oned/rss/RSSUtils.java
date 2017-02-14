package com.google.zxing.oned.rss;

public final class RSSUtils
{
  static int combins(int paramInt1, int paramInt2)
  {
    int m = 1;
    int j;
    if (paramInt1 - paramInt2 > paramInt2)
      j = paramInt1 - paramInt2;
    for (int i = paramInt2; ; i = paramInt1 - paramInt2)
    {
      paramInt2 = 1;
      int k = paramInt1;
      for (paramInt1 = m; k > j; paramInt1 = m)
      {
        int n = paramInt2 * k;
        m = paramInt1;
        paramInt2 = n;
        if (paramInt1 <= i)
        {
          paramInt2 = n / paramInt1;
          m = paramInt1 + 1;
        }
        k -= 1;
      }
      j = paramInt2;
    }
    while (true)
      if (paramInt1 <= i)
      {
        paramInt2 /= paramInt1;
        paramInt1 += 1;
      }
      else
      {
        return paramInt2;
      }
  }

  static int[] elements(int[] paramArrayOfInt, int paramInt1, int paramInt2)
  {
    int[] arrayOfInt = new int[paramArrayOfInt.length + 2];
    int m = paramInt2 << 1;
    arrayOfInt[0] = 1;
    paramInt2 = 10;
    int j = 1;
    int i = 1;
    while (i < m - 2)
    {
      arrayOfInt[i] = (paramArrayOfInt[(i - 1)] - arrayOfInt[(i - 1)]);
      arrayOfInt[(i + 1)] = (paramArrayOfInt[i] - arrayOfInt[i]);
      int k = j + (arrayOfInt[i] + arrayOfInt[(i + 1)]);
      j = paramInt2;
      if (arrayOfInt[i] < paramInt2)
        j = arrayOfInt[i];
      i += 2;
      paramInt2 = j;
      j = k;
    }
    arrayOfInt[(m - 1)] = (paramInt1 - j);
    if (arrayOfInt[(m - 1)] < paramInt2);
    for (paramInt1 = arrayOfInt[(m - 1)]; ; paramInt1 = paramInt2)
    {
      if (paramInt1 > 1)
      {
        paramInt2 = 0;
        while (paramInt2 < m)
        {
          arrayOfInt[paramInt2] += paramInt1 - 1;
          i = paramInt2 + 1;
          arrayOfInt[i] -= paramInt1 - 1;
          paramInt2 += 2;
        }
      }
      return arrayOfInt;
    }
  }

  public static int getRSSvalue(int[] paramArrayOfInt, int paramInt, boolean paramBoolean)
  {
    int i4 = paramArrayOfInt.length;
    int j = 0;
    for (int i = 0; j < i4; i = k + i)
    {
      k = paramArrayOfInt[j];
      j += 1;
    }
    int i1 = 0;
    j = 0;
    int k = 0;
    int n = i;
    i = j;
    j = k;
    while (i1 < i4 - 1)
    {
      k = j | 1 << i1;
      int i2 = 1;
      j = i;
      i = k;
      if (i2 < paramArrayOfInt[i1])
      {
        int m = combins(n - i2 - 1, i4 - i1 - 2);
        k = m;
        if (paramBoolean)
        {
          k = m;
          if (i == 0)
          {
            k = m;
            if (n - i2 - (i4 - i1 - 1) >= i4 - i1 - 1)
              k = m - combins(n - i2 - (i4 - i1), i4 - i1 - 2);
          }
        }
        if (i4 - i1 - 1 > 1)
        {
          m = n - i2 - (i4 - i1 - 2);
          int i3 = 0;
          while (m > paramInt)
          {
            i3 += combins(n - i2 - m - 1, i4 - i1 - 3);
            m -= 1;
          }
          m = k - (i4 - 1 - i1) * i3;
        }
        while (true)
        {
          j += m;
          i2 += 1;
          i &= (1 << i1 ^ 0xFFFFFFFF);
          break;
          m = k;
          if (n - i2 > paramInt)
            m = k - 1;
        }
      }
      i1 += 1;
      n -= i2;
      k = j;
      j = i;
      i = k;
    }
    return i;
  }

  static int[] getRSSwidths(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean)
  {
    int[] arrayOfInt = new int[paramInt3];
    int i = 0;
    int j = 0;
    int k = paramInt2;
    paramInt2 = i;
    if (j < paramInt3 - 1)
    {
      paramInt2 |= 1 << j;
      int m = 1;
      int n = paramInt1;
      paramInt1 = paramInt2;
      while (true)
      {
        i = combins(k - m - 1, paramInt3 - j - 2);
        paramInt2 = i;
        if (paramBoolean)
        {
          paramInt2 = i;
          if (paramInt1 == 0)
          {
            paramInt2 = i;
            if (k - m - (paramInt3 - j - 1) >= paramInt3 - j - 1)
              paramInt2 = i - combins(k - m - (paramInt3 - j), paramInt3 - j - 2);
          }
        }
        if (paramInt3 - j - 1 > 1)
        {
          int i1 = 0;
          i = k - m - (paramInt3 - j - 2);
          while (i > paramInt4)
          {
            i1 += combins(k - m - i - 1, paramInt3 - j - 3);
            i -= 1;
          }
          i = paramInt2 - (paramInt3 - 1 - j) * i1;
        }
        while (true)
        {
          n -= i;
          if (n >= 0)
            break label266;
          i = n + i;
          k -= m;
          arrayOfInt[j] = m;
          j += 1;
          paramInt2 = paramInt1;
          paramInt1 = i;
          break;
          i = paramInt2;
          if (k - m > paramInt4)
            i = paramInt2 - 1;
        }
        label266: m += 1;
        paramInt1 &= (1 << j ^ 0xFFFFFFFF);
      }
    }
    arrayOfInt[j] = k;
    return arrayOfInt;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.rss.RSSUtils
 * JD-Core Version:    0.6.2
 */