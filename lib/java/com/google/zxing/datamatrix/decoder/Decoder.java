package com.google.zxing.datamatrix.decoder;

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.reedsolomon.GF256;
import com.google.zxing.common.reedsolomon.ReedSolomonDecoder;
import com.google.zxing.common.reedsolomon.ReedSolomonException;

public final class Decoder
{
  private final ReedSolomonDecoder rsDecoder = new ReedSolomonDecoder(GF256.DATA_MATRIX_FIELD);

  private void correctErrors(byte[] paramArrayOfByte, int paramInt)
    throws ChecksumException
  {
    int j = 0;
    int k = paramArrayOfByte.length;
    int[] arrayOfInt = new int[k];
    int i = 0;
    while (i < k)
    {
      paramArrayOfByte[i] &= 255;
      i += 1;
    }
    i = paramArrayOfByte.length;
    try
    {
      this.rsDecoder.decode(arrayOfInt, i - paramInt);
      i = j;
      while (i < paramInt)
      {
        paramArrayOfByte[i] = ((byte)arrayOfInt[i]);
        i += 1;
      }
    }
    catch (ReedSolomonException paramArrayOfByte)
    {
      throw ChecksumException.getChecksumInstance();
    }
  }

  public DecoderResult decode(BitMatrix paramBitMatrix)
    throws FormatException, ChecksumException
  {
    Object localObject1 = new BitMatrixParser(paramBitMatrix);
    paramBitMatrix = ((BitMatrixParser)localObject1).readVersion(paramBitMatrix);
    paramBitMatrix = DataBlock.getDataBlocks(((BitMatrixParser)localObject1).readCodewords(), paramBitMatrix);
    int i = 0;
    int j = 0;
    while (i < paramBitMatrix.length)
    {
      j += paramBitMatrix[i].getNumDataCodewords();
      i += 1;
    }
    localObject1 = new byte[j];
    i = 0;
    j = 0;
    while (i < paramBitMatrix.length)
    {
      Object localObject2 = paramBitMatrix[i];
      byte[] arrayOfByte = localObject2.getCodewords();
      int m = localObject2.getNumDataCodewords();
      correctErrors(arrayOfByte, m);
      int k = 0;
      while (k < m)
      {
        localObject1[j] = arrayOfByte[k];
        k += 1;
        j += 1;
      }
      i += 1;
    }
    return DecodedBitStreamParser.decode((byte[])localObject1);
  }

  public DecoderResult decode(boolean[][] paramArrayOfBoolean)
    throws FormatException, ChecksumException
  {
    int k = paramArrayOfBoolean.length;
    BitMatrix localBitMatrix = new BitMatrix(k);
    int i = 0;
    while (i < k)
    {
      int j = 0;
      while (j < k)
      {
        if (paramArrayOfBoolean[i][j] != 0)
          localBitMatrix.set(j, i);
        j += 1;
      }
      i += 1;
    }
    return decode(localBitMatrix);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.datamatrix.decoder.Decoder
 * JD-Core Version:    0.6.2
 */