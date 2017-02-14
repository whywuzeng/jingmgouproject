package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

public final class UPCEReader extends UPCEANReader
{
  private static final int[] MIDDLE_END_PATTERN = { 1, 1, 1, 1, 1, 1 };
  private static final int[][] NUMSYS_AND_CHECK_DIGIT_PATTERNS = { { 56, 52, 50, 49, 44, 38, 35, 42, 41, 37 }, { 7, 11, 13, 14, 19, 25, 28, 21, 22, 26 } };
  private final int[] decodeMiddleCounters = new int[4];

  public static String convertUPCEtoUPCA(String paramString)
  {
    char[] arrayOfChar = new char[6];
    paramString.getChars(1, 7, arrayOfChar, 0);
    StringBuffer localStringBuffer = new StringBuffer(12);
    localStringBuffer.append(paramString.charAt(0));
    char c = arrayOfChar[5];
    switch (c)
    {
    default:
      localStringBuffer.append(arrayOfChar, 0, 5);
      localStringBuffer.append("0000");
      localStringBuffer.append(c);
    case '0':
    case '1':
    case '2':
    case '3':
    case '4':
    }
    while (true)
    {
      localStringBuffer.append(paramString.charAt(7));
      return localStringBuffer.toString();
      localStringBuffer.append(arrayOfChar, 0, 2);
      localStringBuffer.append(c);
      localStringBuffer.append("0000");
      localStringBuffer.append(arrayOfChar, 2, 3);
      continue;
      localStringBuffer.append(arrayOfChar, 0, 3);
      localStringBuffer.append("00000");
      localStringBuffer.append(arrayOfChar, 3, 2);
      continue;
      localStringBuffer.append(arrayOfChar, 0, 4);
      localStringBuffer.append("00000");
      localStringBuffer.append(arrayOfChar[4]);
    }
  }

  private static void determineNumSysAndCheckDigit(StringBuffer paramStringBuffer, int paramInt)
    throws NotFoundException
  {
    int i = 0;
    while (i <= 1)
    {
      int j = 0;
      while (j < 10)
      {
        if (paramInt == NUMSYS_AND_CHECK_DIGIT_PATTERNS[i][j])
        {
          paramStringBuffer.insert(0, (char)(i + 48));
          paramStringBuffer.append((char)(j + 48));
          return;
        }
        j += 1;
      }
      i += 1;
    }
    throw NotFoundException.getNotFoundInstance();
  }

  protected boolean checkChecksum(String paramString)
    throws FormatException, ChecksumException
  {
    return super.checkChecksum(convertUPCEtoUPCA(paramString));
  }

  protected int[] decodeEnd(BitArray paramBitArray, int paramInt)
    throws NotFoundException
  {
    return findGuardPattern(paramBitArray, paramInt, true, MIDDLE_END_PATTERN);
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
        break label150;
      i = 1 << 5 - k | i;
    }
    label150: 
    while (true)
    {
      k += 1;
      break;
      determineNumSysAndCheckDigit(paramStringBuffer, i);
      return j;
    }
  }

  BarcodeFormat getBarcodeFormat()
  {
    return BarcodeFormat.UPC_E;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.UPCEReader
 * JD-Core Version:    0.6.2
 */