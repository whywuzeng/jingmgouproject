package com.umeng.message.proguard;

import android.text.TextUtils;
import java.io.UnsupportedEncodingException;

public class bv
{
  public static final int a = 0;
  public static final int b = 1;
  public static final int c = 0;
  public static final int d = 2;
  public static final int e = 8;
  public static final int f = 16;
  public static final int g = 32;
  private static final int h = 76;
  private static final byte i = 61;
  private static final byte j = 10;
  private static final String k = "UTF-8";
  private static final byte l = -5;
  private static final byte m = -1;
  private static final byte[] n = { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47 };
  private static final byte[] o = { -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, -9, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, -9, -9, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -9, -9, -9, -9 };
  private static final byte[] p = { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95 };
  private static final byte[] q = { -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, 63, -9, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -9, -9, -9, -9 };
  private static final byte[] r = { 45, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 95, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 };
  private static final byte[] s = { -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 0, -9, -9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, -9, -9, -9, -1, -9, -9, -9, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, -9, -9, -9, -9, 37, -9, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, -9, -9, -9, -9 };

  private static final int a(byte[] paramArrayOfByte1, int paramInt1, byte[] paramArrayOfByte2, int paramInt2, int paramInt3)
  {
    byte[] arrayOfByte = b(paramInt3);
    if (paramArrayOfByte1[(paramInt1 + 2)] == 61)
    {
      paramInt3 = arrayOfByte[paramArrayOfByte1[paramInt1]];
      paramArrayOfByte2[paramInt2] = ((byte)(((arrayOfByte[paramArrayOfByte1[(paramInt1 + 1)]] & 0xFF) << 12 | (paramInt3 & 0xFF) << 18) >>> 16));
      return 1;
    }
    if (paramArrayOfByte1[(paramInt1 + 3)] == 61)
    {
      paramInt3 = arrayOfByte[paramArrayOfByte1[paramInt1]];
      i1 = arrayOfByte[paramArrayOfByte1[(paramInt1 + 1)]];
      paramInt1 = (arrayOfByte[paramArrayOfByte1[(paramInt1 + 2)]] & 0xFF) << 6 | ((paramInt3 & 0xFF) << 18 | (i1 & 0xFF) << 12);
      paramArrayOfByte2[paramInt2] = ((byte)(paramInt1 >>> 16));
      paramArrayOfByte2[(paramInt2 + 1)] = ((byte)(paramInt1 >>> 8));
      return 2;
    }
    paramInt3 = arrayOfByte[paramArrayOfByte1[paramInt1]];
    int i1 = arrayOfByte[paramArrayOfByte1[(paramInt1 + 1)]];
    int i2 = arrayOfByte[paramArrayOfByte1[(paramInt1 + 2)]];
    paramInt1 = arrayOfByte[paramArrayOfByte1[(paramInt1 + 3)]] & 0xFF | ((paramInt3 & 0xFF) << 18 | (i1 & 0xFF) << 12 | (i2 & 0xFF) << 6);
    paramArrayOfByte2[paramInt2] = ((byte)(paramInt1 >> 16));
    paramArrayOfByte2[(paramInt2 + 1)] = ((byte)(paramInt1 >> 8));
    paramArrayOfByte2[(paramInt2 + 2)] = ((byte)paramInt1);
    return 3;
  }

  public static final String a(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null)
      return null;
    return a(paramArrayOfByte, 0, paramArrayOfByte.length, 0);
  }

  public static final String a(byte[] paramArrayOfByte, int paramInt)
  {
    return a(paramArrayOfByte, 0, paramArrayOfByte.length, paramInt);
  }

  public static final String a(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    return a(paramArrayOfByte, paramInt1, paramInt2, 0);
  }

  public static final String a(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3)
  {
    int i3;
    int i4;
    int i1;
    if ((paramInt3 & 0x8) == 0)
    {
      i3 = 1;
      i4 = paramInt2 * 4 / 3;
      if (paramInt2 % 3 <= 0)
        break label155;
      i1 = 4;
      label26: if (i3 == 0)
        break label161;
    }
    byte[] arrayOfByte;
    label155: label161: for (int i2 = i4 / 76; ; i2 = 0)
    {
      arrayOfByte = new byte[i2 + (i4 + i1)];
      i2 = 0;
      i1 = 0;
      i4 = 0;
      while (i4 < paramInt2 - 2)
      {
        a(paramArrayOfByte, i4 + paramInt1, 3, arrayOfByte, i1, paramInt3);
        int i6 = i2 + 4;
        i2 = i6;
        int i5 = i1;
        if (i3 != 0)
        {
          i2 = i6;
          i5 = i1;
          if (i6 == 76)
          {
            arrayOfByte[(i1 + 4)] = 10;
            i5 = i1 + 1;
            i2 = 0;
          }
        }
        i1 = i5 + 4;
        i4 += 3;
      }
      i3 = 0;
      break;
      i1 = 0;
      break label26;
    }
    i2 = i1;
    if (i4 < paramInt2)
    {
      a(paramArrayOfByte, i4 + paramInt1, paramInt2 - i4, arrayOfByte, i1, paramInt3);
      i2 = i1 + 4;
    }
    try
    {
      paramArrayOfByte = new String(arrayOfByte, 0, i2, "UTF-8");
      return paramArrayOfByte;
    }
    catch (UnsupportedEncodingException paramArrayOfByte)
    {
    }
    return new String(arrayOfByte, 0, i2);
  }

  private static final byte[] a(int paramInt)
  {
    if ((paramInt & 0x10) == 16)
      return p;
    if ((paramInt & 0x20) == 32)
      return r;
    return n;
  }

  public static final byte[] a(String paramString)
  {
    return a(paramString, 0);
  }

  public static final byte[] a(String paramString, int paramInt)
  {
    if (TextUtils.isEmpty(paramString))
      return null;
    try
    {
      byte[] arrayOfByte = paramString.getBytes("UTF-8");
      paramString = arrayOfByte;
      return b(paramString, 0, paramString.length, paramInt);
    }
    catch (Throwable localThrowable)
    {
      while (true)
        paramString = paramString.getBytes();
    }
  }

  private static final byte[] a(byte[] paramArrayOfByte1, int paramInt1, int paramInt2, byte[] paramArrayOfByte2, int paramInt3, int paramInt4)
  {
    int i2 = 0;
    byte[] arrayOfByte = a(paramInt4);
    if (paramInt2 > 0)
    {
      paramInt4 = paramArrayOfByte1[paramInt1] << 24 >>> 8;
      label25: if (paramInt2 <= 1)
        break label104;
    }
    label104: for (int i1 = paramArrayOfByte1[(paramInt1 + 1)] << 24 >>> 16; ; i1 = 0)
    {
      if (paramInt2 > 2)
        i2 = paramArrayOfByte1[(paramInt1 + 2)] << 24 >>> 24;
      paramInt1 = i2 | (i1 | paramInt4);
      switch (paramInt2)
      {
      default:
        return paramArrayOfByte2;
        paramInt4 = 0;
        break label25;
      case 3:
      case 2:
      case 1:
      }
    }
    paramArrayOfByte2[paramInt3] = arrayOfByte[(paramInt1 >>> 18)];
    paramArrayOfByte2[(paramInt3 + 1)] = arrayOfByte[(paramInt1 >>> 12 & 0x3F)];
    paramArrayOfByte2[(paramInt3 + 2)] = arrayOfByte[(paramInt1 >>> 6 & 0x3F)];
    paramArrayOfByte2[(paramInt3 + 3)] = arrayOfByte[(paramInt1 & 0x3F)];
    return paramArrayOfByte2;
    paramArrayOfByte2[paramInt3] = arrayOfByte[(paramInt1 >>> 18)];
    paramArrayOfByte2[(paramInt3 + 1)] = arrayOfByte[(paramInt1 >>> 12 & 0x3F)];
    paramArrayOfByte2[(paramInt3 + 2)] = arrayOfByte[(paramInt1 >>> 6 & 0x3F)];
    paramArrayOfByte2[(paramInt3 + 3)] = 61;
    return paramArrayOfByte2;
    paramArrayOfByte2[paramInt3] = arrayOfByte[(paramInt1 >>> 18)];
    paramArrayOfByte2[(paramInt3 + 1)] = arrayOfByte[(paramInt1 >>> 12 & 0x3F)];
    paramArrayOfByte2[(paramInt3 + 2)] = 61;
    paramArrayOfByte2[(paramInt3 + 3)] = 61;
    return paramArrayOfByte2;
  }

  private static final byte[] b(int paramInt)
  {
    if ((paramInt & 0x10) == 16)
      return q;
    if ((paramInt & 0x20) == 32)
      return s;
    return o;
  }

  public static final byte[] b(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3)
  {
    byte[] arrayOfByte2 = b(paramInt3);
    byte[] arrayOfByte1 = new byte[paramInt2 * 3 / 4];
    byte[] arrayOfByte3 = new byte[4];
    int i4 = paramInt1;
    int i3 = 0;
    int i2 = 0;
    int i5;
    if (i4 < paramInt1 + paramInt2)
    {
      int i1 = (byte)(paramArrayOfByte[i4] & 0x7F);
      i5 = arrayOfByte2[i1];
      if (i5 >= -5)
      {
        if (i5 < -1)
          break label167;
        i5 = i3 + 1;
        arrayOfByte3[i3] = i1;
        if (i5 <= 3)
          break label156;
        i3 = a(arrayOfByte3, 0, arrayOfByte1, i2, paramInt3) + i2;
        if (i1 != 61)
          break label128;
      }
    }
    while (true)
    {
      paramArrayOfByte = new byte[i3];
      System.arraycopy(arrayOfByte1, 0, paramArrayOfByte, 0, i3);
      return paramArrayOfByte;
      return null;
      label128: i2 = 0;
      while (true)
      {
        i5 = i4 + 1;
        i4 = i3;
        i3 = i2;
        i2 = i4;
        i4 = i5;
        break;
        label156: i3 = i2;
        i2 = i5;
        continue;
        label167: i5 = i2;
        i2 = i3;
        i3 = i5;
      }
      i3 = i2;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.bv
 * JD-Core Version:    0.6.2
 */