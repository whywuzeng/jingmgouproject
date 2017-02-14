package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.Hashtable;

public final class ITFReader extends OneDReader
{
  private static final int[] DEFAULT_ALLOWED_LENGTHS = { 6, 10, 12, 14, 44 };
  private static final int[] END_PATTERN_REVERSED;
  private static final int MAX_AVG_VARIANCE = 107;
  private static final int MAX_INDIVIDUAL_VARIANCE = 204;
  private static final int N = 1;
  static final int[][] PATTERNS = { { 1, 1, 3, 3, 1 }, arrayOfInt1, { 1, 3, 1, 1, 3 }, { 3, 3, 1, 1, 1 }, { 1, 1, 3, 1, 3 }, arrayOfInt2, { 1, 3, 3, 1, 1 }, { 1, 1, 1, 3, 3 }, { 3, 1, 1, 3, 1 }, arrayOfInt3 };
  private static final int[] START_PATTERN = { 1, 1, 1, 1 };
  private static final int W = 3;
  private int narrowLineWidth = -1;

  static
  {
    END_PATTERN_REVERSED = new int[] { 1, 1, 3 };
    int[] arrayOfInt1 = { 3, 1, 1, 1, 3 };
    int[] arrayOfInt2 = { 3, 1, 3, 1, 1 };
    int[] arrayOfInt3 = { 1, 3, 1, 3, 1 };
  }

  private static int decodeDigit(int[] paramArrayOfInt)
    throws NotFoundException
  {
    int j = 107;
    int k = -1;
    int n = PATTERNS.length;
    int i = 0;
    if (i < n)
    {
      int m = patternMatchVariance(paramArrayOfInt, PATTERNS[i], 204);
      if (m >= j)
        break label61;
      k = i;
      j = m;
    }
    label61: 
    while (true)
    {
      i += 1;
      break;
      if (k >= 0)
        return k;
      throw NotFoundException.getNotFoundInstance();
    }
  }

  private static void decodeMiddle(BitArray paramBitArray, int paramInt1, int paramInt2, StringBuffer paramStringBuffer)
    throws NotFoundException
  {
    int[] arrayOfInt1 = new int[10];
    int[] arrayOfInt2 = new int[5];
    int[] arrayOfInt3 = new int[5];
    while (paramInt1 < paramInt2)
    {
      recordPattern(paramBitArray, paramInt1, arrayOfInt1);
      int i = 0;
      while (i < 5)
      {
        int j = i << 1;
        arrayOfInt2[i] = arrayOfInt1[j];
        arrayOfInt3[i] = arrayOfInt1[(j + 1)];
        i += 1;
      }
      paramStringBuffer.append((char)(decodeDigit(arrayOfInt2) + 48));
      paramStringBuffer.append((char)(decodeDigit(arrayOfInt3) + 48));
      i = 0;
      while (i < arrayOfInt1.length)
      {
        paramInt1 += arrayOfInt1[i];
        i += 1;
      }
    }
  }

  private static int[] findGuardPattern(BitArray paramBitArray, int paramInt, int[] paramArrayOfInt)
    throws NotFoundException
  {
    int i1 = paramArrayOfInt.length;
    int[] arrayOfInt = new int[i1];
    int i2 = paramBitArray.getSize();
    int i = paramInt;
    int m = 0;
    int k = 0;
    int j = paramInt;
    paramInt = i;
    if (j < i2)
    {
      if ((paramBitArray.get(j) ^ k))
      {
        arrayOfInt[m] += 1;
        i = k;
        k = paramInt;
      }
      while (true)
      {
        j += 1;
        paramInt = k;
        k = i;
        break;
        if (m == i1 - 1)
        {
          if (patternMatchVariance(arrayOfInt, paramArrayOfInt, 204) < 107)
            return new int[] { paramInt, j };
          i = paramInt + (arrayOfInt[0] + arrayOfInt[1]);
          paramInt = 2;
          while (paramInt < i1)
          {
            arrayOfInt[(paramInt - 2)] = arrayOfInt[paramInt];
            paramInt += 1;
          }
          arrayOfInt[(i1 - 2)] = 0;
          arrayOfInt[(i1 - 1)] = 0;
        }
        for (paramInt = m - 1; ; paramInt = m)
        {
          arrayOfInt[paramInt] = 1;
          if (k != 0)
            break label214;
          n = 1;
          k = i;
          m = paramInt;
          i = n;
          break;
          m += 1;
          i = paramInt;
        }
        label214: int n = 0;
        k = i;
        m = paramInt;
        i = n;
      }
    }
    throw NotFoundException.getNotFoundInstance();
  }

  private static int skipWhiteSpace(BitArray paramBitArray)
    throws NotFoundException
  {
    int j = paramBitArray.getSize();
    int i = 0;
    while (true)
    {
      if ((i >= j) || (paramBitArray.get(i)))
      {
        if (i != j)
          break;
        throw NotFoundException.getNotFoundInstance();
      }
      i += 1;
    }
    return i;
  }

  private void validateQuietZone(BitArray paramBitArray, int paramInt)
    throws NotFoundException
  {
    int i = this.narrowLineWidth * 10;
    paramInt -= 1;
    while (true)
    {
      if ((i <= 0) || (paramInt < 0) || (paramBitArray.get(paramInt)))
      {
        if (i == 0)
          break;
        throw NotFoundException.getNotFoundInstance();
      }
      i -= 1;
      paramInt -= 1;
    }
  }

  int[] decodeEnd(BitArray paramBitArray)
    throws NotFoundException
  {
    paramBitArray.reverse();
    try
    {
      int[] arrayOfInt = findGuardPattern(paramBitArray, skipWhiteSpace(paramBitArray), END_PATTERN_REVERSED);
      validateQuietZone(paramBitArray, arrayOfInt[0]);
      int i = arrayOfInt[0];
      arrayOfInt[0] = (paramBitArray.getSize() - arrayOfInt[1]);
      arrayOfInt[1] = (paramBitArray.getSize() - i);
      return arrayOfInt;
    }
    finally
    {
      paramBitArray.reverse();
    }
  }

  public Result decodeRow(int paramInt, BitArray paramBitArray, Hashtable paramHashtable)
    throws FormatException, NotFoundException
  {
    int[] arrayOfInt = decodeStart(paramBitArray);
    Object localObject1 = decodeEnd(paramBitArray);
    Object localObject2 = new StringBuffer(20);
    decodeMiddle(paramBitArray, arrayOfInt[1], localObject1[0], (StringBuffer)localObject2);
    localObject2 = ((StringBuffer)localObject2).toString();
    if (paramHashtable != null);
    for (paramBitArray = (int[])paramHashtable.get(DecodeHintType.ALLOWED_LENGTHS); ; paramBitArray = null)
    {
      paramHashtable = paramBitArray;
      if (paramBitArray == null)
        paramHashtable = DEFAULT_ALLOWED_LENGTHS;
      int j = ((String)localObject2).length();
      int i = 0;
      if (i < paramHashtable.length)
        if (j != paramHashtable[i]);
      for (i = 1; ; i = 0)
      {
        if (i == 0)
        {
          throw FormatException.getFormatInstance();
          i += 1;
          break;
        }
        paramBitArray = new ResultPoint(arrayOfInt[1], paramInt);
        paramHashtable = new ResultPoint(localObject1[0], paramInt);
        localObject1 = BarcodeFormat.ITF;
        return new Result((String)localObject2, null, new ResultPoint[] { paramBitArray, paramHashtable }, (BarcodeFormat)localObject1);
      }
    }
  }

  int[] decodeStart(BitArray paramBitArray)
    throws NotFoundException
  {
    int[] arrayOfInt = findGuardPattern(paramBitArray, skipWhiteSpace(paramBitArray), START_PATTERN);
    this.narrowLineWidth = (arrayOfInt[1] - arrayOfInt[0] >> 2);
    validateQuietZone(paramBitArray, arrayOfInt[0]);
    return arrayOfInt;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.ITFReader
 * JD-Core Version:    0.6.2
 */