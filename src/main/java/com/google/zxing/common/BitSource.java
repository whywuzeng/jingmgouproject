package com.google.zxing.common;

public final class BitSource
{
  private int bitOffset;
  private int byteOffset;
  private final byte[] bytes;

  public BitSource(byte[] paramArrayOfByte)
  {
    this.bytes = paramArrayOfByte;
  }

  public int available()
  {
    return (this.bytes.length - this.byteOffset) * 8 - this.bitOffset;
  }

  public int readBits(int paramInt)
  {
    if ((paramInt < 1) || (paramInt > 32))
      throw new IllegalArgumentException();
    int j;
    int i;
    int k;
    if (this.bitOffset > 0)
    {
      j = 8 - this.bitOffset;
      if (paramInt < j)
      {
        i = paramInt;
        j -= i;
        k = this.bytes[this.byteOffset];
        this.bitOffset = (i + this.bitOffset);
        if (this.bitOffset == 8)
        {
          this.bitOffset = 0;
          this.byteOffset += 1;
        }
        k = (255 >> 8 - i << j & k) >> j;
        j = paramInt - i;
      }
    }
    for (paramInt = k; ; paramInt = i)
    {
      i = paramInt;
      if (j > 0)
      {
        while (true)
          if (j >= 8)
          {
            paramInt = paramInt << 8 | this.bytes[this.byteOffset] & 0xFF;
            this.byteOffset += 1;
            j -= 8;
            continue;
            i = j;
            break;
          }
        i = paramInt;
        if (j > 0)
        {
          i = 8 - j;
          i = paramInt << j | (255 >> i << i & this.bytes[this.byteOffset]) >> i;
          this.bitOffset = (j + this.bitOffset);
        }
      }
      return i;
      i = 0;
      j = paramInt;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.common.BitSource
 * JD-Core Version:    0.6.2
 */