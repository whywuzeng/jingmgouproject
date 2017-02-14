package com.google.zxing.common;

public final class BitArray
{
  public int[] bits;
  public int size;

  public BitArray()
  {
    this.size = 0;
    this.bits = new int[1];
  }

  public BitArray(int paramInt)
  {
    this.size = paramInt;
    this.bits = makeArray(paramInt);
  }

  private void ensureCapacity(int paramInt)
  {
    if (paramInt > this.bits.length << 5)
    {
      int[] arrayOfInt = makeArray(paramInt);
      System.arraycopy(this.bits, 0, arrayOfInt, 0, this.bits.length);
      this.bits = arrayOfInt;
    }
  }

  private static int[] makeArray(int paramInt)
  {
    return new int[paramInt + 31 >> 5];
  }

  public void appendBit(boolean paramBoolean)
  {
    ensureCapacity(this.size + 1);
    if (paramBoolean)
    {
      int[] arrayOfInt = this.bits;
      int i = this.size >> 5;
      arrayOfInt[i] |= 1 << (this.size & 0x1F);
    }
    this.size += 1;
  }

  public void appendBitArray(BitArray paramBitArray)
  {
    int j = paramBitArray.getSize();
    ensureCapacity(this.size + j);
    int i = 0;
    while (i < j)
    {
      appendBit(paramBitArray.get(i));
      i += 1;
    }
  }

  public void appendBits(int paramInt1, int paramInt2)
  {
    if ((paramInt2 < 0) || (paramInt2 > 32))
      throw new IllegalArgumentException("Num bits must be between 0 and 32");
    ensureCapacity(this.size + paramInt2);
    if (paramInt2 > 0)
    {
      if ((paramInt1 >> paramInt2 - 1 & 0x1) == 1);
      for (boolean bool = true; ; bool = false)
      {
        appendBit(bool);
        paramInt2 -= 1;
        break;
      }
    }
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

  public void flip(int paramInt)
  {
    int[] arrayOfInt = this.bits;
    int i = paramInt >> 5;
    arrayOfInt[i] ^= 1 << (paramInt & 0x1F);
  }

  public boolean get(int paramInt)
  {
    return (this.bits[(paramInt >> 5)] & 1 << (paramInt & 0x1F)) != 0;
  }

  public int[] getBitArray()
  {
    return this.bits;
  }

  public int getSize()
  {
    return this.size;
  }

  public int getSizeInBytes()
  {
    return this.size + 7 >> 3;
  }

  public boolean isRange(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    if (paramInt2 < paramInt1)
      throw new IllegalArgumentException();
    if (paramInt2 == paramInt1)
      return true;
    int i1 = paramInt2 - 1;
    int m = paramInt1 >> 5;
    int i2 = i1 >> 5;
    int j = m;
    while (j <= i2)
    {
      int k;
      if (j > m)
      {
        paramInt2 = 0;
        if (j >= i2)
          break label115;
        k = 31;
        label67: if ((paramInt2 != 0) || (k != 31))
          break label125;
        paramInt2 = -1;
        k = this.bits[j];
        if (!paramBoolean)
          break label158;
      }
      label158: for (int i = paramInt2; ; i = 0)
      {
        if ((k & paramInt2) == i)
          break label164;
        return false;
        paramInt2 = paramInt1 & 0x1F;
        break;
        label115: k = i1 & 0x1F;
        break label67;
        label125: int n;
        for (i = 0; ; i = 1 << n | i)
        {
          n = paramInt2;
          paramInt2 = i;
          if (n > k)
            break;
          paramInt2 = n + 1;
        }
      }
      label164: j += 1;
    }
    return true;
  }

  public void reverse()
  {
    int[] arrayOfInt = new int[this.bits.length];
    int j = this.size;
    int i = 0;
    while (i < j)
    {
      if (get(j - i - 1))
      {
        int k = i >> 5;
        arrayOfInt[k] |= 1 << (i & 0x1F);
      }
      i += 1;
    }
    this.bits = arrayOfInt;
  }

  public void set(int paramInt)
  {
    int[] arrayOfInt = this.bits;
    int i = paramInt >> 5;
    arrayOfInt[i] |= 1 << (paramInt & 0x1F);
  }

  public void setBulk(int paramInt1, int paramInt2)
  {
    this.bits[(paramInt1 >> 5)] = paramInt2;
  }

  public void toBytes(int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3)
  {
    int i = 0;
    while (i < paramInt3)
    {
      int j = 0;
      int m;
      for (int k = 0; j < 8; k = m)
      {
        m = k;
        if (get(paramInt1))
          m = k | 1 << 7 - j;
        paramInt1 += 1;
        j += 1;
      }
      paramArrayOfByte[(paramInt2 + i)] = ((byte)k);
      i += 1;
    }
  }

  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer(this.size);
    int i = 0;
    if (i < this.size)
    {
      if ((i & 0x7) == 0)
        localStringBuffer.append(' ');
      if (get(i));
      for (char c = 'X'; ; c = '.')
      {
        localStringBuffer.append(c);
        i += 1;
        break;
      }
    }
    return localStringBuffer.toString();
  }

  public void xor(BitArray paramBitArray)
  {
    if (this.bits.length != paramBitArray.bits.length)
      throw new IllegalArgumentException("Sizes don't match");
    int i = 0;
    while (i < this.bits.length)
    {
      int[] arrayOfInt = this.bits;
      arrayOfInt[i] ^= paramBitArray.bits[i];
      i += 1;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.common.BitArray
 * JD-Core Version:    0.6.2
 */