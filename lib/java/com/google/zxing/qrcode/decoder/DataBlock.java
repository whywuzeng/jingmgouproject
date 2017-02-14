package com.google.zxing.qrcode.decoder;

final class DataBlock
{
  private final byte[] codewords;
  private final int numDataCodewords;

  private DataBlock(int paramInt, byte[] paramArrayOfByte)
  {
    this.numDataCodewords = paramInt;
    this.codewords = paramArrayOfByte;
  }

  static DataBlock[] getDataBlocks(byte[] paramArrayOfByte, Version paramVersion, ErrorCorrectionLevel paramErrorCorrectionLevel)
  {
    if (paramArrayOfByte.length != paramVersion.getTotalCodewords())
      throw new IllegalArgumentException();
    paramVersion = paramVersion.getECBlocksForLevel(paramErrorCorrectionLevel);
    paramErrorCorrectionLevel = paramVersion.getECBlocks();
    int i = 0;
    int j = 0;
    while (i < paramErrorCorrectionLevel.length)
    {
      j += paramErrorCorrectionLevel[i].getCount();
      i += 1;
    }
    DataBlock[] arrayOfDataBlock = new DataBlock[j];
    i = 0;
    int k = 0;
    while (i < paramErrorCorrectionLevel.length)
    {
      Object localObject = paramErrorCorrectionLevel[i];
      j = 0;
      while (j < localObject.getCount())
      {
        m = localObject.getDataCodewords();
        arrayOfDataBlock[k] = new DataBlock(m, new byte[paramVersion.getECCodewordsPerBlock() + m]);
        j += 1;
        k += 1;
      }
      i += 1;
    }
    j = arrayOfDataBlock[0].codewords.length;
    i = arrayOfDataBlock.length - 1;
    int i1;
    int n;
    if ((i < 0) || (arrayOfDataBlock[i].codewords.length == j))
    {
      i1 = i + 1;
      n = j - paramVersion.getECCodewordsPerBlock();
      j = 0;
      i = 0;
    }
    while (true)
    {
      if (j >= n)
        break label257;
      m = 0;
      while (true)
        if (m < k)
        {
          arrayOfDataBlock[m].codewords[j] = paramArrayOfByte[i];
          m += 1;
          i += 1;
          continue;
          i -= 1;
          break;
        }
      j += 1;
    }
    label257: int m = i1;
    j = i;
    while (m < k)
    {
      arrayOfDataBlock[m].codewords[n] = paramArrayOfByte[j];
      m += 1;
      j += 1;
    }
    int i2 = arrayOfDataBlock[0].codewords.length;
    i = n;
    while (i < i2)
    {
      m = 0;
      if (m < k)
      {
        if (m < i1);
        for (n = i; ; n = i + 1)
        {
          arrayOfDataBlock[m].codewords[n] = paramArrayOfByte[j];
          m += 1;
          j += 1;
          break;
        }
      }
      i += 1;
    }
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
 * Qualified Name:     com.google.zxing.qrcode.decoder.DataBlock
 * JD-Core Version:    0.6.2
 */