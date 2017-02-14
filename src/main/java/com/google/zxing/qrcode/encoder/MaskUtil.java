package com.google.zxing.qrcode.encoder;

public final class MaskUtil
{
  public static int applyMaskPenaltyRule1(ByteMatrix paramByteMatrix)
  {
    return applyMaskPenaltyRule1Internal(paramByteMatrix, true) + applyMaskPenaltyRule1Internal(paramByteMatrix, false);
  }

  private static int applyMaskPenaltyRule1Internal(ByteMatrix paramByteMatrix, boolean paramBoolean)
  {
    int i3 = -1;
    int k;
    int m;
    label23: int n;
    int i2;
    if (paramBoolean)
    {
      k = paramByteMatrix.getHeight();
      if (!paramBoolean)
        break label121;
      m = paramByteMatrix.getWidth();
      paramByteMatrix = paramByteMatrix.getArray();
      n = 0;
      i2 = 0;
    }
    while (true)
    {
      if (n >= k)
        break label194;
      int i1 = 0;
      int j = 0;
      label46: if (i1 < m)
      {
        int i;
        label65: int i5;
        int i4;
        if (paramBoolean)
        {
          i = paramByteMatrix[n][i1];
          if (i != i3)
            break label172;
          i5 = j + 1;
          if (i5 != 5)
            break label141;
          j = i2 + 3;
          i = i5;
          i4 = i3;
        }
        while (true)
        {
          i1 += 1;
          i3 = i4;
          i2 = j;
          j = i;
          break label46;
          k = paramByteMatrix.getWidth();
          break;
          label121: m = paramByteMatrix.getHeight();
          break label23;
          i = paramByteMatrix[i1][n];
          break label65;
          i4 = i3;
          i = i5;
          j = i2;
          if (i5 > 5)
          {
            j = i2 + 1;
            i4 = i3;
            i = i5;
            continue;
            j = 1;
            i4 = i;
            i = j;
            j = i2;
          }
        }
      }
      label141: label172: n += 1;
    }
    label194: return i2;
  }

  public static int applyMaskPenaltyRule2(ByteMatrix paramByteMatrix)
  {
    byte[][] arrayOfByte = paramByteMatrix.getArray();
    int n = paramByteMatrix.getWidth();
    int i1 = paramByteMatrix.getHeight();
    int i = 0;
    int j = 0;
    while (i < i1 - 1)
    {
      int k = 0;
      while (k < n - 1)
      {
        int i2 = arrayOfByte[i][k];
        int m = j;
        if (i2 == arrayOfByte[i][(k + 1)])
        {
          m = j;
          if (i2 == arrayOfByte[(i + 1)][k])
          {
            m = j;
            if (i2 == arrayOfByte[(i + 1)][(k + 1)])
              m = j + 3;
          }
        }
        k += 1;
        j = m;
      }
      i += 1;
    }
    return j;
  }

  public static int applyMaskPenaltyRule3(ByteMatrix paramByteMatrix)
  {
    byte[][] arrayOfByte = paramByteMatrix.getArray();
    int n = paramByteMatrix.getWidth();
    int i1 = paramByteMatrix.getHeight();
    int k = 0;
    int i = 0;
    while (k < i1)
    {
      int m = 0;
      while (m < n)
      {
        int j = i;
        if (m + 6 < n)
        {
          j = i;
          if (arrayOfByte[k][m] == 1)
          {
            j = i;
            if (arrayOfByte[k][(m + 1)] == 0)
            {
              j = i;
              if (arrayOfByte[k][(m + 2)] == 1)
              {
                j = i;
                if (arrayOfByte[k][(m + 3)] == 1)
                {
                  j = i;
                  if (arrayOfByte[k][(m + 4)] == 1)
                  {
                    j = i;
                    if (arrayOfByte[k][(m + 5)] == 0)
                    {
                      j = i;
                      if (arrayOfByte[k][(m + 6)] == 1)
                        if ((m + 10 >= n) || (arrayOfByte[k][(m + 7)] != 0) || (arrayOfByte[k][(m + 8)] != 0) || (arrayOfByte[k][(m + 9)] != 0) || (arrayOfByte[k][(m + 10)] != 0))
                        {
                          j = i;
                          if (m - 4 >= 0)
                          {
                            j = i;
                            if (arrayOfByte[k][(m - 1)] == 0)
                            {
                              j = i;
                              if (arrayOfByte[k][(m - 2)] == 0)
                              {
                                j = i;
                                if (arrayOfByte[k][(m - 3)] == 0)
                                {
                                  j = i;
                                  if (arrayOfByte[k][(m - 4)] != 0);
                                }
                              }
                            }
                          }
                        }
                        else
                        {
                          j = i + 40;
                        }
                    }
                  }
                }
              }
            }
          }
        }
        i = j;
        if (k + 6 < i1)
        {
          i = j;
          if (arrayOfByte[k][m] == 1)
          {
            i = j;
            if (arrayOfByte[(k + 1)][m] == 0)
            {
              i = j;
              if (arrayOfByte[(k + 2)][m] == 1)
              {
                i = j;
                if (arrayOfByte[(k + 3)][m] == 1)
                {
                  i = j;
                  if (arrayOfByte[(k + 4)][m] == 1)
                  {
                    i = j;
                    if (arrayOfByte[(k + 5)][m] == 0)
                    {
                      i = j;
                      if (arrayOfByte[(k + 6)][m] == 1)
                        if ((k + 10 >= i1) || (arrayOfByte[(k + 7)][m] != 0) || (arrayOfByte[(k + 8)][m] != 0) || (arrayOfByte[(k + 9)][m] != 0) || (arrayOfByte[(k + 10)][m] != 0))
                        {
                          i = j;
                          if (k - 4 >= 0)
                          {
                            i = j;
                            if (arrayOfByte[(k - 1)][m] == 0)
                            {
                              i = j;
                              if (arrayOfByte[(k - 2)][m] == 0)
                              {
                                i = j;
                                if (arrayOfByte[(k - 3)][m] == 0)
                                {
                                  i = j;
                                  if (arrayOfByte[(k - 4)][m] != 0);
                                }
                              }
                            }
                          }
                        }
                        else
                        {
                          i = j + 40;
                        }
                    }
                  }
                }
              }
            }
          }
        }
        m += 1;
      }
      k += 1;
    }
    return i;
  }

  public static int applyMaskPenaltyRule4(ByteMatrix paramByteMatrix)
  {
    byte[][] arrayOfByte = paramByteMatrix.getArray();
    int n = paramByteMatrix.getWidth();
    int i1 = paramByteMatrix.getHeight();
    int i = 0;
    int j = 0;
    while (i < i1)
    {
      k = 0;
      while (k < n)
      {
        int m = j;
        if (arrayOfByte[i][k] == 1)
          m = j + 1;
        k += 1;
        j = m;
      }
      i += 1;
    }
    i = paramByteMatrix.getHeight();
    int k = paramByteMatrix.getWidth();
    return Math.abs((int)(j / (i * k) * 100.0D - 50.0D)) / 5 * 10;
  }

  public static boolean getDataMaskBit(int paramInt1, int paramInt2, int paramInt3)
  {
    if (!QRCode.isValidMaskPattern(paramInt1))
      throw new IllegalArgumentException("Invalid mask pattern");
    switch (paramInt1)
    {
    default:
      throw new IllegalArgumentException("Invalid mask pattern: " + paramInt1);
    case 0:
      paramInt1 = paramInt3 + paramInt2 & 0x1;
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    case 6:
    case 7:
    }
    while (paramInt1 == 0)
    {
      return true;
      paramInt1 = paramInt3 & 0x1;
      continue;
      paramInt1 = paramInt2 % 3;
      continue;
      paramInt1 = (paramInt3 + paramInt2) % 3;
      continue;
      paramInt1 = (paramInt3 >>> 1) + paramInt2 / 3 & 0x1;
      continue;
      paramInt1 = paramInt3 * paramInt2;
      paramInt1 = paramInt1 % 3 + (paramInt1 & 0x1);
      continue;
      paramInt1 = paramInt3 * paramInt2;
      paramInt1 = paramInt1 % 3 + (paramInt1 & 0x1) & 0x1;
      continue;
      paramInt1 = paramInt3 * paramInt2 % 3 + (paramInt3 + paramInt2 & 0x1) & 0x1;
    }
    return false;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.qrcode.encoder.MaskUtil
 * JD-Core Version:    0.6.2
 */