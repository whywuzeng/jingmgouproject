package com.google.zxing.datamatrix.decoder;

final class DataBlock
{
  private final byte[] codewords;
  private final int numDataCodewords;

  private DataBlock(int paramInt, byte[] paramArrayOfByte)
  {
    this.numDataCodewords = paramInt;
    this.codewords = paramArrayOfByte;
  }

  static DataBlock[] getDataBlocks(byte[] paramArrayOfByte, Version paramVersion)
  {
    Version.ECBlocks localECBlocks = paramVersion.getECBlocks();
    Version.ECB[] arrayOfECB = localECBlocks.getECBlocks();
    int i = 0;
    int j = 0;
    while (i < arrayOfECB.length)
    {
      j += arrayOfECB[i].getCount();
      i += 1;
    }
    DataBlock[] arrayOfDataBlock = new DataBlock[j];
    j = 0;
    i = 0;
    int m;
    while (j < arrayOfECB.length)
    {
      Version.ECB localECB = arrayOfECB[j];
      k = 0;
      while (k < localECB.getCount())
      {
        m = localECB.getDataCodewords();
        arrayOfDataBlock[i] = new DataBlock(m, new byte[localECBlocks.getECCodewords() + m]);
        k += 1;
        i += 1;
      }
      j += 1;
    }
    int i1 = arrayOfDataBlock[0].codewords.length - localECBlocks.getECCodewords();
    int k = 0;
    j = 0;
    while (k < i1 - 1)
    {
      m = 0;
      while (m < i)
      {
        arrayOfDataBlock[m].codewords[k] = paramArrayOfByte[j];
        m += 1;
        j += 1;
      }
      k += 1;
    }
    if (paramVersion.getVersionNumber() == 24)
    {
      m = 1;
      if (m == 0)
        break label268;
    }
    int n;
    label268: for (k = 8; ; k = i)
    {
      n = 0;
      while (n < k)
      {
        arrayOfDataBlock[n].codewords[(i1 - 1)] = paramArrayOfByte[j];
        n += 1;
        j += 1;
      }
      m = 0;
      break;
    }
    int i2 = arrayOfDataBlock[0].codewords.length;
    k = j;
    j = i1;
    while (j < i2)
    {
      n = 0;
      if (n < i)
      {
        if ((m != 0) && (n > 7));
        for (i1 = j - 1; ; i1 = j)
        {
          arrayOfDataBlock[n].codewords[i1] = paramArrayOfByte[k];
          n += 1;
          k += 1;
          break;
        }
      }
      j += 1;
    }
    if (k != paramArrayOfByte.length)
      throw new IllegalArgumentException();
    return arrayOfDataBlock;
  }

  byte[] getCodewords()
  {
    return this.codewords;
  }

  int getNumDataCodewords()
  {
    return this.numDataCodewords;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.datamatrix.decoder.DataBlock
 * JD-Core Version:    0.6.2
 */