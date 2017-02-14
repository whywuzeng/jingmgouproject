package com.baidu.android.bbalbs.common.a;

import java.io.UnsupportedEncodingException;

public final class b
{
  private static final byte[] a = { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47 };

  public static String a(byte[] paramArrayOfByte, String paramString)
    throws UnsupportedEncodingException
  {
    int i = paramArrayOfByte.length * 4 / 3;
    byte[] arrayOfByte = new byte[i + (i / 76 + 3)];
    int k = 0;
    int n = paramArrayOfByte.length - paramArrayOfByte.length % 3;
    int j = 0;
    i = 0;
    if (j >= n);
    switch (paramArrayOfByte.length % 3)
    {
    default:
      label68: return new String(arrayOfByte, 0, i, paramString);
      int m = i + 1;
      arrayOfByte[i] = a[((paramArrayOfByte[j] & 0xFF) >> 2)];
      i = m + 1;
      arrayOfByte[m] = a[((paramArrayOfByte[j] & 0x3) << 4 | (paramArrayOfByte[(j + 1)] & 0xFF) >> 4)];
      m = i + 1;
      arrayOfByte[i] = a[((paramArrayOfByte[(j + 1)] & 0xF) << 2 | (paramArrayOfByte[(j + 2)] & 0xFF) >> 6)];
      i = m + 1;
      arrayOfByte[m] = a[(paramArrayOfByte[(j + 2)] & 0x3F)];
      if (((i - k) % 76 == 0) && (i != 0))
      {
        m = i + 1;
        arrayOfByte[i] = 10;
        k += 1;
        i = m;
      }
      break;
    case 1:
    case 2:
    }
    while (true)
    {
      j += 3;
      break;
      j = i + 1;
      arrayOfByte[i] = a[((paramArrayOfByte[n] & 0xFF) >> 2)];
      i = j + 1;
      arrayOfByte[j] = a[((paramArrayOfByte[n] & 0x3) << 4)];
      j = i + 1;
      arrayOfByte[i] = 61;
      arrayOfByte[j] = 61;
      i = j + 1;
      break label68;
      j = i + 1;
      arrayOfByte[i] = a[((paramArrayOfByte[n] & 0xFF) >> 2)];
      i = j + 1;
      arrayOfByte[j] = a[((paramArrayOfByte[n] & 0x3) << 4 | (paramArrayOfByte[(n + 1)] & 0xFF) >> 4)];
      j = i + 1;
      arrayOfByte[i] = a[((paramArrayOfByte[(n + 1)] & 0xF) << 2)];
      i = j + 1;
      arrayOfByte[j] = 61;
      break label68;
    }
  }

  public static byte[] a(byte[] paramArrayOfByte)
  {
    return a(paramArrayOfByte, paramArrayOfByte.length);
  }

  public static byte[] a(byte[] paramArrayOfByte, int paramInt)
  {
    int i = paramInt / 4 * 3;
    if (i == 0)
      return new byte[0];
    byte[] arrayOfByte = new byte[i];
    int j = 0;
    int k = paramInt;
    i = paramArrayOfByte[(k - 1)];
    paramInt = j;
    if (i != 10)
    {
      paramInt = j;
      if (i != 13)
      {
        paramInt = j;
        if (i != 32)
          if (i != 9)
            break label74;
      }
    }
    for (paramInt = j; ; paramInt = j + 1)
    {
      k -= 1;
      j = paramInt;
      break;
      label74: if (i != 61)
        break label87;
    }
    label87: int n = 0;
    int i1 = 0;
    int m = 0;
    paramInt = 0;
    if (m >= k)
    {
      i = paramInt;
      if (j > 0)
      {
        k = i1 << j * 6;
        i = paramInt + 1;
        arrayOfByte[paramInt] = ((byte)((0xFF0000 & k) >> 16));
        paramInt = i;
        if (j == 1)
        {
          paramInt = i + 1;
          arrayOfByte[i] = ((byte)((0xFF00 & k) >> 8));
          i = paramInt;
        }
      }
      else
      {
        paramInt = i;
      }
      paramArrayOfByte = new byte[paramInt];
      System.arraycopy(arrayOfByte, 0, paramArrayOfByte, 0, paramInt);
      return paramArrayOfByte;
    }
    i = paramArrayOfByte[m];
    if ((i != 10) && (i != 13) && (i != 32))
      if (i != 9);
    while (true)
    {
      m += 1;
      break;
      if ((i >= 65) && (i <= 90))
      {
        i -= 65;
        label235: i1 = i1 << 6 | (byte)i;
        if (n % 4 != 3)
          break label379;
        i = paramInt + 1;
        arrayOfByte[paramInt] = ((byte)((0xFF0000 & i1) >> 16));
        int i2 = i + 1;
        arrayOfByte[i] = ((byte)((0xFF00 & i1) >> 8));
        paramInt = i2 + 1;
        arrayOfByte[i2] = ((byte)(i1 & 0xFF));
      }
      label379: 
      while (true)
      {
        n += 1;
        break;
        if ((i >= 97) && (i <= 122))
        {
          i -= 71;
          break label235;
        }
        if ((i >= 48) && (i <= 57))
        {
          i += 4;
          break label235;
        }
        if (i == 43)
        {
          i = 62;
          break label235;
        }
        if (i == 47)
        {
          i = 63;
          break label235;
        }
        return null;
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.android.bbalbs.common.a.b
 * JD-Core Version:    0.6.2
 */