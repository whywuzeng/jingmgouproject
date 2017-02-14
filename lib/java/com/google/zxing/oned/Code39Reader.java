package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.Hashtable;

public final class Code39Reader extends OneDReader
{
  private static final char[] ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%".toCharArray();
  static final String ALPHABET_STRING = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%";
  private static final int ASTERISK_ENCODING = CHARACTER_ENCODINGS[39];
  static final int[] CHARACTER_ENCODINGS = { 52, 289, 97, 352, 49, 304, 112, 37, 292, 100, 265, 73, 328, 25, 280, 88, 13, 268, 76, 28, 259, 67, 322, 19, 274, 82, 7, 262, 70, 22, 385, 193, 448, 145, 400, 208, 133, 388, 196, 148, 168, 162, 138, 42 };
  private final boolean extendedMode;
  private final boolean usingCheckDigit;

  public Code39Reader()
  {
    this.usingCheckDigit = false;
    this.extendedMode = false;
  }

  public Code39Reader(boolean paramBoolean)
  {
    this.usingCheckDigit = paramBoolean;
    this.extendedMode = false;
  }

  public Code39Reader(boolean paramBoolean1, boolean paramBoolean2)
  {
    this.usingCheckDigit = paramBoolean1;
    this.extendedMode = paramBoolean2;
  }

  private static String decodeExtended(StringBuffer paramStringBuffer)
    throws FormatException
  {
    int j = paramStringBuffer.length();
    StringBuffer localStringBuffer = new StringBuffer(j);
    int i = 0;
    if (i < j)
    {
      char c = paramStringBuffer.charAt(i);
      int k;
      if ((c == '+') || (c == '$') || (c == '%') || (c == '/'))
      {
        k = paramStringBuffer.charAt(i + 1);
        switch (c)
        {
        default:
          c = '\000';
          label106: localStringBuffer.append(c);
          i += 1;
        case '+':
        case '$':
        case '%':
        case '/':
        }
      }
      while (true)
      {
        i += 1;
        break;
        if ((k >= 65) && (k <= 90))
        {
          c = (char)(k + 32);
          break label106;
        }
        throw FormatException.getFormatInstance();
        if ((k >= 65) && (k <= 90))
        {
          c = (char)(k - 64);
          break label106;
        }
        throw FormatException.getFormatInstance();
        if ((k >= 65) && (k <= 69))
        {
          c = (char)(k - 38);
          break label106;
        }
        if ((k >= 70) && (k <= 87))
        {
          c = (char)(k - 11);
          break label106;
        }
        throw FormatException.getFormatInstance();
        if ((k >= 65) && (k <= 79))
        {
          c = (char)(k - 32);
          break label106;
        }
        if (k == 90)
        {
          c = ':';
          break label106;
        }
        throw FormatException.getFormatInstance();
        localStringBuffer.append(c);
      }
    }
    return localStringBuffer.toString();
  }

  private static int[] findAsteriskPattern(BitArray paramBitArray)
    throws NotFoundException
  {
    int i2 = paramBitArray.getSize();
    int i = 0;
    int[] arrayOfInt;
    int i3;
    int k;
    int m;
    int n;
    label41: int j;
    if ((i >= i2) || (paramBitArray.get(i)))
    {
      arrayOfInt = new int[9];
      i3 = arrayOfInt.length;
      k = i;
      m = 0;
      n = 0;
      if (k >= i2)
        break label260;
      if (!(paramBitArray.get(k) ^ m))
        break label96;
      arrayOfInt[n] += 1;
      j = m;
      m = i;
    }
    while (true)
    {
      k += 1;
      i = m;
      m = j;
      break label41;
      i += 1;
      break;
      label96: if (n == i3 - 1)
      {
        if ((toNarrowWidePattern(arrayOfInt) == ASTERISK_ENCODING) && (paramBitArray.isRange(Math.max(0, i - (k - i) / 2), i, false)))
          return new int[] { i, k };
        j = i + (arrayOfInt[0] + arrayOfInt[1]);
        i = 2;
        while (i < i3)
        {
          arrayOfInt[(i - 2)] = arrayOfInt[i];
          i += 1;
        }
        arrayOfInt[(i3 - 2)] = 0;
        arrayOfInt[(i3 - 1)] = 0;
      }
      for (i = n - 1; ; i = n)
      {
        arrayOfInt[i] = 1;
        if (m != 0)
          break label245;
        i1 = 1;
        m = j;
        n = i;
        j = i1;
        break;
        n += 1;
        j = i;
      }
      label245: int i1 = 0;
      m = j;
      n = i;
      j = i1;
    }
    label260: throw NotFoundException.getNotFoundInstance();
  }

  private static char patternToChar(int paramInt)
    throws NotFoundException
  {
    int i = 0;
    while (i < CHARACTER_ENCODINGS.length)
    {
      if (CHARACTER_ENCODINGS[i] == paramInt)
        return ALPHABET[i];
      i += 1;
    }
    throw NotFoundException.getNotFoundInstance();
  }

  private static int toNarrowWidePattern(int[] paramArrayOfInt)
  {
    int i4 = paramArrayOfInt.length;
    int i;
    for (int j = 0; ; j = i)
    {
      i = 2147483647;
      int k = 0;
      while (k < i4)
      {
        n = paramArrayOfInt[k];
        m = i;
        if (n < i)
        {
          m = i;
          if (n > j)
            m = n;
        }
        k += 1;
        i = m;
      }
      int n = 0;
      j = 0;
      int m = 0;
      int i2;
      int i1;
      for (k = 0; n < i4; k = i1)
      {
        int i5 = paramArrayOfInt[n];
        int i3 = j;
        i2 = m;
        i1 = k;
        if (paramArrayOfInt[n] > i)
        {
          i3 = j | 1 << i4 - 1 - n;
          i1 = k + 1;
          i2 = m + i5;
        }
        n += 1;
        j = i3;
        m = i2;
      }
      if (k == 3)
      {
        n = k;
        k = 0;
        while (true)
        {
          i1 = j;
          if (k < i4)
          {
            i1 = j;
            if (n > 0)
            {
              i2 = paramArrayOfInt[k];
              i1 = n;
              if (paramArrayOfInt[k] <= i)
                break label203;
              i1 = n - 1;
              if (i2 << 1 < m)
                break label203;
              i1 = -1;
            }
          }
          return i1;
          label203: k += 1;
          n = i1;
        }
      }
      if (k <= 3)
        return -1;
    }
  }

  public Result decodeRow(int paramInt, BitArray paramBitArray, Hashtable paramHashtable)
    throws NotFoundException, ChecksumException, FormatException
  {
    paramHashtable = findAsteriskPattern(paramBitArray);
    int i = paramHashtable[1];
    int n = paramBitArray.getSize();
    while ((i < n) && (!paramBitArray.get(i)))
      i += 1;
    Object localObject1 = new StringBuffer(20);
    Object localObject2 = new int[9];
    recordPattern(paramBitArray, i, (int[])localObject2);
    int j = toNarrowWidePattern((int[])localObject2);
    if (j < 0)
      throw NotFoundException.getNotFoundInstance();
    char c = patternToChar(j);
    ((StringBuffer)localObject1).append(c);
    int k = 0;
    j = i;
    while (k < localObject2.length)
    {
      j += localObject2[k];
      k += 1;
    }
    while (true)
      if ((j < n) && (!paramBitArray.get(j)))
      {
        j += 1;
      }
      else
      {
        if (c == '*')
        {
          ((StringBuffer)localObject1).deleteCharAt(((StringBuffer)localObject1).length() - 1);
          k = 0;
          int m = 0;
          while (k < localObject2.length)
          {
            m += localObject2[k];
            k += 1;
          }
          if ((j != n) && ((j - i - m) / 2 < m))
            throw NotFoundException.getNotFoundInstance();
          if (this.usingCheckDigit)
          {
            n = ((StringBuffer)localObject1).length() - 1;
            k = 0;
            m = 0;
            while (k < n)
            {
              m += "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%".indexOf(((StringBuffer)localObject1).charAt(k));
              k += 1;
            }
            if (((StringBuffer)localObject1).charAt(n) != ALPHABET[(m % 43)])
              throw ChecksumException.getChecksumInstance();
            ((StringBuffer)localObject1).deleteCharAt(n);
          }
          if (((StringBuffer)localObject1).length() == 0)
            throw NotFoundException.getNotFoundInstance();
          if (this.extendedMode);
          for (paramBitArray = decodeExtended((StringBuffer)localObject1); ; paramBitArray = ((StringBuffer)localObject1).toString())
          {
            float f1 = (paramHashtable[1] + paramHashtable[0]) / 2.0F;
            float f2 = (i + j) / 2.0F;
            paramHashtable = new ResultPoint(f1, paramInt);
            localObject1 = new ResultPoint(f2, paramInt);
            localObject2 = BarcodeFormat.CODE_39;
            return new Result(paramBitArray, null, new ResultPoint[] { paramHashtable, localObject1 }, (BarcodeFormat)localObject2);
          }
        }
        i = j;
        break;
      }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.Code39Reader
 * JD-Core Version:    0.6.2
 */