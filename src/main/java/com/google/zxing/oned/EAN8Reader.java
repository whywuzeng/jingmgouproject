package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

public final class EAN8Reader extends UPCEANReader
{
  private final int[] decodeMiddleCounters = new int[4];

  protected int decodeMiddle(BitArray paramBitArray, int[] paramArrayOfInt, StringBuffer paramStringBuffer)
    throws NotFoundException
  {
    int[] arrayOfInt = this.decodeMiddleCounters;
    arrayOfInt[0] = 0;
    arrayOfInt[1] = 0;
    arrayOfInt[2] = 0;
    arrayOfInt[3] = 0;
    int m = paramBitArray.getSize();
    int i = paramArrayOfInt[1];
    int j = 0;
    int k;
    while ((j < 4) && (i < m))
    {
      paramStringBuffer.append((char)(decodeDigit(paramBitArray, arrayOfInt, i, L_PATTERNS) + 48));
      k = 0;
      while (k < arrayOfInt.length)
      {
        i += arrayOfInt[k];
        k += 1;
      }
      j += 1;
    }
    i = findGuardPattern(paramBitArray, i, true, MIDDLE_PATTERN)[1];
    j = 0;
    while ((j < 4) && (i < m))
    {
      paramStringBuffer.append((char)(decodeDigit(paramBitArray, arrayOfInt, i, L_PATTERNS) + 48));
      k = 0;
      while (k < arrayOfInt.length)
      {
        i += arrayOfInt[k];
        k += 1;
      }
      j += 1;
    }
    return i;
  }

  BarcodeFormat getBarcodeFormat()
  {
    return BarcodeFormat.EAN_8;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.EAN8Reader
 * JD-Core Version:    0.6.2
 */