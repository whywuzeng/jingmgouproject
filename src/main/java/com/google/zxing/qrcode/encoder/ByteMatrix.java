package com.google.zxing.qrcode.encoder;

import java.lang.reflect.Array;

public final class ByteMatrix
{
  private final byte[][] bytes;
  private final int height;
  private final int width;

  public ByteMatrix(int paramInt1, int paramInt2)
  {
    this.bytes = ((byte[][])Array.newInstance(Byte.TYPE, new int[] { paramInt2, paramInt1 }));
    this.width = paramInt1;
    this.height = paramInt2;
  }

  public void clear(byte paramByte)
  {
    int i = 0;
    while (i < this.height)
    {
      int j = 0;
      while (j < this.width)
      {
        this.bytes[i][j] = paramByte;
        j += 1;
      }
      i += 1;
    }
  }

  public byte get(int paramInt1, int paramInt2)
  {
    return this.bytes[paramInt2][paramInt1];
  }

  public byte[][] getArray()
  {
    return this.bytes;
  }

  public int getHeight()
  {
    return this.height;
  }

  public int getWidth()
  {
    return this.width;
  }

  public void set(int paramInt1, int paramInt2, byte paramByte)
  {
    this.bytes[paramInt2][paramInt1] = paramByte;
  }

  public void set(int paramInt1, int paramInt2, int paramInt3)
  {
    this.bytes[paramInt2][paramInt1] = ((byte)paramInt3);
  }

  public void set(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    byte[] arrayOfByte = this.bytes[paramInt2];
    if (paramBoolean);
    for (paramInt2 = 1; ; paramInt2 = 0)
    {
      arrayOfByte[paramInt1] = ((byte)paramInt2);
      return;
    }
  }

  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer(this.width * 2 * this.height + 2);
    int i = 0;
    while (i < this.height)
    {
      int j = 0;
      if (j < this.width)
      {
        switch (this.bytes[i][j])
        {
        default:
          localStringBuffer.append("  ");
        case 0:
        case 1:
        }
        while (true)
        {
          j += 1;
          break;
          localStringBuffer.append(" 0");
          continue;
          localStringBuffer.append(" 1");
        }
      }
      localStringBuffer.append('\n');
      i += 1;
    }
    return localStringBuffer.toString();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.qrcode.encoder.ByteMatrix
 * JD-Core Version:    0.6.2
 */