package com.google.zxing.common;

public final class BitMatrix
{
  public final int[] bits;
  public final int height;
  public final int rowSize;
  public final int width;

  public BitMatrix(int paramInt)
  {
    this(paramInt, paramInt);
  }

  public BitMatrix(int paramInt1, int paramInt2)
  {
    if ((paramInt1 < 1) || (paramInt2 < 1))
      throw new IllegalArgumentException("Both dimensions must be greater than 0");
    this.width = paramInt1;
    this.height = paramInt2;
    this.rowSize = (paramInt1 + 31 >> 5);
    this.bits = new int[this.rowSize * paramInt2];
  }

  public void clear()
  {
    int j = this.bits.length;
    int i = 0;
    while (i < j)
    {
      this.bits[i] = 0;
      i += 1;
    }
  }

  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof BitMatrix));
    do
    {
      return false;
      paramObject = (BitMatrix)paramObject;
    }
    while ((this.width != paramObject.width) || (this.height != paramObject.height) || (this.rowSize != paramObject.rowSize) || (this.bits.length != paramObject.bits.length));
    int i = 0;
    while (true)
    {
      if (i >= this.bits.length)
        break label93;
      if (this.bits[i] != paramObject.bits[i])
        break;
      i += 1;
    }
    label93: return true;
  }

  public void flip(int paramInt1, int paramInt2)
  {
    paramInt2 = this.rowSize * paramInt2 + (paramInt1 >> 5);
    int[] arrayOfInt = this.bits;
    arrayOfInt[paramInt2] ^= 1 << (paramInt1 & 0x1F);
  }

  public boolean get(int paramInt1, int paramInt2)
  {
    int i = this.rowSize;
    return (this.bits[(i * paramInt2 + (paramInt1 >> 5))] >>> (paramInt1 & 0x1F) & 0x1) != 0;
  }

  public int getHeight()
  {
    return this.height;
  }

  public BitArray getRow(int paramInt, BitArray paramBitArray)
  {
    BitArray localBitArray;
    if (paramBitArray != null)
    {
      localBitArray = paramBitArray;
      if (paramBitArray.getSize() >= this.width);
    }
    else
    {
      localBitArray = new BitArray(this.width);
    }
    int j = this.rowSize;
    int i = 0;
    while (i < this.rowSize)
    {
      localBitArray.setBulk(i << 5, this.bits[(paramInt * j + i)]);
      i += 1;
    }
    return localBitArray;
  }

  public int[] getTopLeftOnBit()
  {
    int i = 0;
    while ((i < this.bits.length) && (this.bits[i] == 0))
      i += 1;
    if (i == this.bits.length)
      return null;
    int k = i / this.rowSize;
    int m = this.rowSize;
    int n = this.bits[i];
    int j = 0;
    while (n << 31 - j == 0)
      j += 1;
    return new int[] { (i % m << 5) + j, k };
  }

  public int getWidth()
  {
    return this.width;
  }

  public int hashCode()
  {
    int i = this.width;
    int j = this.width;
    int k = this.height;
    j = this.rowSize + ((i * 31 + j) * 31 + k) * 31;
    i = 0;
    while (i < this.bits.length)
    {
      j = j * 31 + this.bits[i];
      i += 1;
    }
    return j;
  }

  public void set(int paramInt1, int paramInt2)
  {
    paramInt2 = this.rowSize * paramInt2 + (paramInt1 >> 5);
    int[] arrayOfInt = this.bits;
    arrayOfInt[paramInt2] |= 1 << (paramInt1 & 0x1F);
  }

  public void setRegion(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if ((paramInt2 < 0) || (paramInt1 < 0))
      throw new IllegalArgumentException("Left and top must be nonnegative");
    if ((paramInt4 < 1) || (paramInt3 < 1))
      throw new IllegalArgumentException("Height and width must be at least 1");
    int i = paramInt1 + paramInt3;
    paramInt4 = paramInt2 + paramInt4;
    if ((paramInt4 > this.height) || (i > this.width))
      throw new IllegalArgumentException("The region must fit inside the matrix");
    while (true)
    {
      paramInt2 += 1;
      if (paramInt2 >= paramInt4)
        break;
      int j = this.rowSize;
      paramInt3 = paramInt1;
      while (paramInt3 < i)
      {
        int[] arrayOfInt = this.bits;
        int k = (paramInt3 >> 5) + paramInt2 * j;
        arrayOfInt[k] |= 1 << (paramInt3 & 0x1F);
        paramInt3 += 1;
      }
    }
  }

  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer(this.height * (this.width + 1));
    int i = 0;
    while (i < this.height)
    {
      int j = 0;
      if (j < this.width)
      {
        if (get(j, i));
        for (String str = "X "; ; str = "  ")
        {
          localStringBuffer.append(str);
          j += 1;
          break;
        }
      }
      localStringBuffer.append('\n');
      i += 1;
    }
    return localStringBuffer.toString();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.common.BitMatrix
 * JD-Core Version:    0.6.2
 */