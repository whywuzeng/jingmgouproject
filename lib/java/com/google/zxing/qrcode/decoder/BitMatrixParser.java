package com.google.zxing.qrcode.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;

final class BitMatrixParser
{
  private final BitMatrix bitMatrix;
  private FormatInformation parsedFormatInfo;
  private Version parsedVersion;

  BitMatrixParser(BitMatrix paramBitMatrix)
    throws FormatException
  {
    int i = paramBitMatrix.getHeight();
    if ((i < 21) || ((i & 0x3) != 1))
      throw FormatException.getFormatInstance();
    this.bitMatrix = paramBitMatrix;
  }

  private int copyBit(int paramInt1, int paramInt2, int paramInt3)
  {
    if (this.bitMatrix.get(paramInt1, paramInt2))
      return paramInt3 << 1 | 0x1;
    return paramInt3 << 1;
  }

  byte[] readCodewords()
    throws FormatException
  {
    Object localObject = readFormatInformation();
    Version localVersion = readVersion();
    localObject = DataMask.forReference(((FormatInformation)localObject).getDataMask());
    int i7 = this.bitMatrix.getHeight();
    ((DataMask)localObject).unmaskBitMatrix(this.bitMatrix, i7);
    localObject = localVersion.buildFunctionPattern();
    byte[] arrayOfByte = new byte[localVersion.getTotalCodewords()];
    int i = i7 - 1;
    int j = 0;
    int k = 0;
    int i4 = 0;
    int n = 1;
    while (i > 0)
    {
      int i1 = i;
      if (i == 6)
        i1 = i - 1;
      i = 0;
      while (i < i7)
      {
        if (n != 0);
        int m;
        int i6;
        for (int i2 = i7 - 1 - i; ; i2 = i)
        {
          int i3 = 0;
          m = k;
          i6 = j;
          while (i3 < 2)
          {
            k = i6;
            j = m;
            int i5 = i4;
            if (!((BitMatrix)localObject).get(i1 - i3, i2))
            {
              i6 += 1;
              j = m << 1;
              m = j;
              if (this.bitMatrix.get(i1 - i3, i2))
                m = j | 0x1;
              k = i6;
              j = m;
              i5 = i4;
              if (i6 == 8)
              {
                arrayOfByte[i4] = ((byte)m);
                j = 0;
                i5 = i4 + 1;
                k = 0;
              }
            }
            i3 += 1;
            i6 = k;
            m = j;
            i4 = i5;
          }
        }
        i += 1;
        j = i6;
        k = m;
      }
      i = i1 - 2;
      n ^= 1;
    }
    if (i4 != localVersion.getTotalCodewords())
      throw FormatException.getFormatInstance();
    return arrayOfByte;
  }

  FormatInformation readFormatInformation()
    throws FormatException
  {
    int k = 0;
    if (this.parsedFormatInfo != null)
      return this.parsedFormatInfo;
    int i = 0;
    int j = 0;
    while (i < 6)
    {
      j = copyBit(i, 8, j);
      i += 1;
    }
    j = copyBit(8, 7, copyBit(8, 8, copyBit(7, 8, j)));
    i = 5;
    while (i >= 0)
    {
      j = copyBit(8, i, j);
      i -= 1;
    }
    int n = this.bitMatrix.getHeight();
    int m = n - 1;
    i = k;
    k = m;
    while (k >= n - 8)
    {
      i = copyBit(k, 8, i);
      k -= 1;
    }
    m = n - 7;
    k = i;
    i = m;
    while (i < n)
    {
      k = copyBit(8, i, k);
      i += 1;
    }
    this.parsedFormatInfo = FormatInformation.decodeFormatInformation(j, k);
    if (this.parsedFormatInfo != null)
      return this.parsedFormatInfo;
    throw FormatException.getFormatInstance();
  }

  Version readVersion()
    throws FormatException
  {
    int m = 0;
    if (this.parsedVersion != null)
      return this.parsedVersion;
    int n = this.bitMatrix.getHeight();
    int i = n - 17 >> 2;
    if (i <= 6)
      return Version.getVersionForNumber(i);
    int i1 = n - 11;
    i = 5;
    int j = 0;
    int k;
    while (i >= 0)
    {
      k = n - 9;
      while (k >= i1)
      {
        j = copyBit(k, i, j);
        k -= 1;
      }
      i -= 1;
    }
    this.parsedVersion = Version.decodeVersionInformation(j);
    if ((this.parsedVersion != null) && (this.parsedVersion.getDimensionForVersion() == n))
      return this.parsedVersion;
    i = 5;
    j = m;
    while (i >= 0)
    {
      k = n - 9;
      while (k >= i1)
      {
        j = copyBit(i, k, j);
        k -= 1;
      }
      i -= 1;
    }
    this.parsedVersion = Version.decodeVersionInformation(j);
    if ((this.parsedVersion != null) && (this.parsedVersion.getDimensionForVersion() == n))
      return this.parsedVersion;
    throw FormatException.getFormatInstance();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.qrcode.decoder.BitMatrixParser
 * JD-Core Version:    0.6.2
 */