package com.google.zxing.datamatrix.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;

final class BitMatrixParser
{
  private final BitMatrix mappingBitMatrix;
  private final BitMatrix readMappingMatrix;
  private final Version version;

  BitMatrixParser(BitMatrix paramBitMatrix)
    throws FormatException
  {
    int i = paramBitMatrix.getHeight();
    if ((i < 10) || (i > 144) || ((i & 0x1) != 0))
      throw FormatException.getFormatInstance();
    this.version = readVersion(paramBitMatrix);
    this.mappingBitMatrix = extractDataRegion(paramBitMatrix);
    this.readMappingMatrix = new BitMatrix(this.mappingBitMatrix.getHeight());
  }

  BitMatrix extractDataRegion(BitMatrix paramBitMatrix)
  {
    int j = this.version.getSymbolSizeRows();
    int i = this.version.getSymbolSizeColumns();
    if (paramBitMatrix.getHeight() != j)
      throw new IllegalArgumentException("Dimension of bitMarix must match the version size");
    int n = this.version.getDataRegionSizeRows();
    int i1 = this.version.getDataRegionSizeColumns();
    int i2 = j / n;
    int i3 = i / i1;
    BitMatrix localBitMatrix = new BitMatrix(i2 * n);
    i = 0;
    while (i < i2)
    {
      j = 0;
      while (j < i3)
      {
        int k = 0;
        while (k < n)
        {
          int m = 0;
          while (m < i1)
          {
            if (paramBitMatrix.get((i1 + 2) * j + 1 + m, (n + 2) * i + 1 + k))
              localBitMatrix.set(j * i1 + m, i * n + k);
            m += 1;
          }
          k += 1;
        }
        j += 1;
      }
      i += 1;
    }
    return localBitMatrix;
  }

  byte[] readCodewords()
    throws FormatException
  {
    int i1 = 0;
    byte[] arrayOfByte = new byte[this.version.getTotalCodewords()];
    int i4 = this.mappingBitMatrix.getHeight();
    int n = 0;
    int m = 0;
    int i2 = 0;
    int i = 0;
    int j = 4;
    int k = 0;
    if ((j == i4) && (i == 0) && (i2 == 0))
    {
      arrayOfByte[k] = ((byte)readCorner1(i4, i4));
      k += 1;
      j -= 2;
      i += 2;
      i2 = 1;
    }
    while (true)
    {
      label81: if ((j < i4) || (i < i4))
        break label429;
      if (k == this.version.getTotalCodewords())
        break;
      throw FormatException.getFormatInstance();
      if ((j == i4 - 2) && (i == 0) && ((i4 & 0x3) != 0) && (m == 0))
      {
        arrayOfByte[k] = ((byte)readCorner2(i4, i4));
        k += 1;
        j -= 2;
        i += 2;
        m = 1;
      }
      else if ((j == i4 + 4) && (i == 2) && ((i4 & 0x7) == 0) && (n == 0))
      {
        arrayOfByte[k] = ((byte)readCorner3(i4, i4));
        k += 1;
        j -= 2;
        i += 2;
        n = 1;
      }
      else
      {
        if ((j != i4 - 2) || (i != 0) || ((i4 & 0x7) != 4) || (i1 != 0))
          break label447;
        arrayOfByte[k] = ((byte)readCorner4(i4, i4));
        k += 1;
        j -= 2;
        i += 2;
        i1 = 1;
      }
    }
    while (true)
    {
      label277: if ((k < i4) && (j >= 0) && (!this.readMappingMatrix.get(j, k)))
      {
        i3 = i + 1;
        arrayOfByte[i] = ((byte)readUtah(k, j, i4, i4));
        i = i3;
      }
      while (true)
      {
        k -= 2;
        j += 2;
        if ((k >= 0) && (j < i4))
          break label277;
        i3 = k + 1;
        j += 3;
        label349: if ((i3 >= 0) && (j < i4) && (!this.readMappingMatrix.get(j, i3)))
        {
          k = i + 1;
          arrayOfByte[i] = ((byte)readUtah(i3, j, i4, i4));
        }
        while (true)
        {
          i3 += 2;
          i = j - 2;
          if ((i3 >= i4) || (i < 0))
          {
            j = i3 + 3;
            i += 1;
            break label81;
            return arrayOfByte;
            label429: break;
          }
          j = i;
          i = k;
          break label349;
          k = i;
        }
      }
      label447: int i3 = i;
      i = k;
      k = j;
      j = i3;
    }
  }

  int readCorner1(int paramInt1, int paramInt2)
  {
    if (readModule(paramInt1 - 1, 0, paramInt1, paramInt2));
    for (int i = 1; ; i = 0)
    {
      int j = i << 1;
      i = j;
      if (readModule(paramInt1 - 1, 1, paramInt1, paramInt2))
        i = j | 0x1;
      j = i << 1;
      i = j;
      if (readModule(paramInt1 - 1, 2, paramInt1, paramInt2))
        i = j | 0x1;
      j = i << 1;
      i = j;
      if (readModule(0, paramInt2 - 2, paramInt1, paramInt2))
        i = j | 0x1;
      j = i << 1;
      i = j;
      if (readModule(0, paramInt2 - 1, paramInt1, paramInt2))
        i = j | 0x1;
      j = i << 1;
      i = j;
      if (readModule(1, paramInt2 - 1, paramInt1, paramInt2))
        i = j | 0x1;
      j = i << 1;
      i = j;
      if (readModule(2, paramInt2 - 1, paramInt1, paramInt2))
        i = j | 0x1;
      j = i << 1;
      i = j;
      if (readModule(3, paramInt2 - 1, paramInt1, paramInt2))
        i = j | 0x1;
      return i;
    }
  }

  int readCorner2(int paramInt1, int paramInt2)
  {
    if (readModule(paramInt1 - 3, 0, paramInt1, paramInt2));
    for (int i = 1; ; i = 0)
    {
      int j = i << 1;
      i = j;
      if (readModule(paramInt1 - 2, 0, paramInt1, paramInt2))
        i = j | 0x1;
      j = i << 1;
      i = j;
      if (readModule(paramInt1 - 1, 0, paramInt1, paramInt2))
        i = j | 0x1;
      j = i << 1;
      i = j;
      if (readModule(0, paramInt2 - 4, paramInt1, paramInt2))
        i = j | 0x1;
      j = i << 1;
      i = j;
      if (readModule(0, paramInt2 - 3, paramInt1, paramInt2))
        i = j | 0x1;
      j = i << 1;
      i = j;
      if (readModule(0, paramInt2 - 2, paramInt1, paramInt2))
        i = j | 0x1;
      j = i << 1;
      i = j;
      if (readModule(0, paramInt2 - 1, paramInt1, paramInt2))
        i = j | 0x1;
      j = i << 1;
      i = j;
      if (readModule(1, paramInt2 - 1, paramInt1, paramInt2))
        i = j | 0x1;
      return i;
    }
  }

  int readCorner3(int paramInt1, int paramInt2)
  {
    if (readModule(paramInt1 - 1, 0, paramInt1, paramInt2));
    for (int i = 1; ; i = 0)
    {
      int j = i << 1;
      i = j;
      if (readModule(paramInt1 - 1, paramInt2 - 1, paramInt1, paramInt2))
        i = j | 0x1;
      j = i << 1;
      i = j;
      if (readModule(0, paramInt2 - 3, paramInt1, paramInt2))
        i = j | 0x1;
      j = i << 1;
      i = j;
      if (readModule(0, paramInt2 - 2, paramInt1, paramInt2))
        i = j | 0x1;
      j = i << 1;
      i = j;
      if (readModule(0, paramInt2 - 1, paramInt1, paramInt2))
        i = j | 0x1;
      j = i << 1;
      i = j;
      if (readModule(1, paramInt2 - 3, paramInt1, paramInt2))
        i = j | 0x1;
      j = i << 1;
      i = j;
      if (readModule(1, paramInt2 - 2, paramInt1, paramInt2))
        i = j | 0x1;
      j = i << 1;
      i = j;
      if (readModule(1, paramInt2 - 1, paramInt1, paramInt2))
        i = j | 0x1;
      return i;
    }
  }

  int readCorner4(int paramInt1, int paramInt2)
  {
    if (readModule(paramInt1 - 3, 0, paramInt1, paramInt2));
    for (int i = 1; ; i = 0)
    {
      int j = i << 1;
      i = j;
      if (readModule(paramInt1 - 2, 0, paramInt1, paramInt2))
        i = j | 0x1;
      j = i << 1;
      i = j;
      if (readModule(paramInt1 - 1, 0, paramInt1, paramInt2))
        i = j | 0x1;
      j = i << 1;
      i = j;
      if (readModule(0, paramInt2 - 2, paramInt1, paramInt2))
        i = j | 0x1;
      j = i << 1;
      i = j;
      if (readModule(0, paramInt2 - 1, paramInt1, paramInt2))
        i = j | 0x1;
      j = i << 1;
      i = j;
      if (readModule(1, paramInt2 - 1, paramInt1, paramInt2))
        i = j | 0x1;
      j = i << 1;
      i = j;
      if (readModule(2, paramInt2 - 1, paramInt1, paramInt2))
        i = j | 0x1;
      j = i << 1;
      i = j;
      if (readModule(3, paramInt2 - 1, paramInt1, paramInt2))
        i = j | 0x1;
      return i;
    }
  }

  boolean readModule(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (paramInt1 < 0)
    {
      paramInt1 += paramInt3;
      paramInt2 = 4 - (paramInt3 + 4 & 0x7) + paramInt2;
    }
    while (true)
    {
      int i = paramInt2;
      paramInt3 = paramInt1;
      if (paramInt2 < 0)
      {
        i = paramInt2 + paramInt4;
        paramInt3 = paramInt1 + (4 - (paramInt4 + 4 & 0x7));
      }
      this.readMappingMatrix.set(i, paramInt3);
      return this.mappingBitMatrix.get(i, paramInt3);
    }
  }

  int readUtah(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = 0;
    if (readModule(paramInt1 - 2, paramInt2 - 2, paramInt3, paramInt4))
      i = 1;
    int j = i << 1;
    i = j;
    if (readModule(paramInt1 - 2, paramInt2 - 1, paramInt3, paramInt4))
      i = j | 0x1;
    j = i << 1;
    i = j;
    if (readModule(paramInt1 - 1, paramInt2 - 2, paramInt3, paramInt4))
      i = j | 0x1;
    j = i << 1;
    i = j;
    if (readModule(paramInt1 - 1, paramInt2 - 1, paramInt3, paramInt4))
      i = j | 0x1;
    j = i << 1;
    i = j;
    if (readModule(paramInt1 - 1, paramInt2, paramInt3, paramInt4))
      i = j | 0x1;
    j = i << 1;
    i = j;
    if (readModule(paramInt1, paramInt2 - 2, paramInt3, paramInt4))
      i = j | 0x1;
    j = i << 1;
    i = j;
    if (readModule(paramInt1, paramInt2 - 1, paramInt3, paramInt4))
      i = j | 0x1;
    j = i << 1;
    i = j;
    if (readModule(paramInt1, paramInt2, paramInt3, paramInt4))
      i = j | 0x1;
    return i;
  }

  Version readVersion(BitMatrix paramBitMatrix)
    throws FormatException
  {
    if (this.version != null)
      return this.version;
    int i = paramBitMatrix.getHeight();
    return Version.getVersionForDimensions(i, i);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.datamatrix.decoder.BitMatrixParser
 * JD-Core Version:    0.6.2
 */