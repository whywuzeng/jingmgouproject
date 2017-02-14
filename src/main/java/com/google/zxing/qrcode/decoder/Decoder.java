package com.google.zxing.qrcode.decoder;

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.reedsolomon.GF256;
import com.google.zxing.common.reedsolomon.ReedSolomonDecoder;
import com.google.zxing.common.reedsolomon.ReedSolomonException;
import java.util.Hashtable;

public final class Decoder
{
  private final ReedSolomonDecoder rsDecoder = new ReedSolomonDecoder(GF256.QR_CODE_FIELD);

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
    throws ChecksumException, FormatException, NotFoundException
  {
    return decode(paramBitMatrix, null);
  }

  public DecoderResult decode(BitMatrix paramBitMatrix, Hashtable paramHashtable)
    throws FormatException, ChecksumException
  {
    Object localObject1 = new BitMatrixParser(paramBitMatrix);
    paramBitMatrix = ((BitMatrixParser)localObject1).readVersion();
    ErrorCorrectionLevel localErrorCorrectionLevel = ((BitMatrixParser)localObject1).readFormatInformation().getErrorCorrectionLevel();
    localObject1 = DataBlock.getDataBlocks(((BitMatrixParser)localObject1).readCodewords(), paramBitMatrix, localErrorCorrectionLevel);
    int i = 0;
    int j = 0;
    while (i < localObject1.length)
    {
      j += localObject1[i].getNumDataCodewords();
      i += 1;
    }
    byte[] arrayOfByte1 = new byte[j];
    i = 0;
    j = 0;
    while (i < localObject1.length)
    {
      Object localObject2 = localObject1[i];
      byte[] arrayOfByte2 = localObject2.getCodewords();
      int m = localObject2.getNumDataCodewords();
      correctErrors(arrayOfByte2, m);
      int k = 0;
      while (k < m)
      {
        arrayOfByte1[j] = arrayOfByte2[k];
        k += 1;
        j += 1;
      }
      i += 1;
    }
    return DecodedBitStreamParser.decode(arrayOfByte1, paramBitMatrix, localErrorCorrectionLevel, paramHashtable);
  }

  public DecoderResult decode(boolean[][] paramArrayOfBoolean)
    throws ChecksumException, FormatException, NotFoundException
  {
    return decode(paramArrayOfBoolean, null);
  }

  public DecoderResult decode(boolean[][] paramArrayOfBoolean, Hashtable paramHashtable)
    throws ChecksumException, FormatException, NotFoundException
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
    return decode(localBitMatrix, paramHashtable);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.qrcode.decoder.Decoder
 * JD-Core Version:    0.6.2
 */