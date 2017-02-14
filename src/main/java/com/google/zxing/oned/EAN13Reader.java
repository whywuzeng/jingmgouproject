package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

public final class EAN13Reader extends UPCEANReader
{
  static final int[] FIRST_DIGIT_ENCODINGS = { 0, 11, 13, 14, 19, 25, 28, 21, 22, 26 };
  private final int[] decodeMiddleCounters = new int[4];

  private static void determineFirstDigit(StringBuffer paramStringBuffer, int paramInt)
    throws NotFoundException
  {
    int i = 0;
    while (i < 10)
    {
      if (paramInt == FIRST_DIGIT_ENCODINGS[i])
      {
        paramStringBuffer.insert(0, (char)(i + 48));
        return;
      }
      i += 1;
    }
    throw NotFoundException.getNotFoundInstance();
  }

  protected int decodeMiddle(BitArray paramBitArray, int[] paramArrayOfInt, StringBuffer paramStringBuffer)
    throws NotFoundException
  {
    int[] arrayOfInt = this.decodeMiddleCounters;
    arrayOfInt[0] = 0;
    arrayOfInt[1] = 0;
    arrayOfInt[2] = 0;
    arrayOfInt[3] = 0;
    int n = paramBitArray.getSize();
    int j = paramArrayOfInt[1];
    int k = 0;
    int i = 0;
    if ((k < 6) && (j < n))
    {
      int i1 = decodeDigit(paramBitArray, arrayOfInt, j, L_AND_G_PATTERNS);
      paramStringBuffer.append((char)(i1 % 10 + 48));
      int m = 0;
      while (m < arrayOfInt.length)
      {
        j += arrayOfInt[m];
        m += 1;
      }
      if (i1 < 10)
        break label240;
      i = 1 << 5 - k | i;
    }
    label240: 
    while (true)
    {
      k += 1;
      break;
      determineFirstDigit(paramStringBuffer, i);
      i = findGuardPattern(paramBitArray, j, true, MIDDLE_PATTERN)[1];
      j = 0;
      while ((j < 6) && (i < n))
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
  }

  BarcodeFormat getBarcodeFormat()
  {
    return BarcodeFormat.EAN_13;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.EAN13Reader
 * JD-Core Version:    0.6.2
 */