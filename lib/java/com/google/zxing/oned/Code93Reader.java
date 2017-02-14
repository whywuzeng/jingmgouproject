package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.Hashtable;

public final class Code93Reader extends OneDReader
{
  private static final char[] ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".toCharArray();
  private static final String ALPHABET_STRING = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*";
  private static final int ASTERISK_ENCODING = CHARACTER_ENCODINGS[47];
  private static final int[] CHARACTER_ENCODINGS = { 276, 328, 324, 322, 296, 292, 290, 336, 274, 266, 424, 420, 418, 404, 402, 394, 360, 356, 354, 308, 282, 344, 332, 326, 300, 278, 436, 434, 428, 422, 406, 410, 364, 358, 310, 314, 302, 468, 466, 458, 366, 374, 430, 294, 474, 470, 306, 350 };

  private static void checkChecksums(StringBuffer paramStringBuffer)
    throws ChecksumException
  {
    int i = paramStringBuffer.length();
    checkOneChecksum(paramStringBuffer, i - 2, 20);
    checkOneChecksum(paramStringBuffer, i - 1, 15);
  }

  private static void checkOneChecksum(StringBuffer paramStringBuffer, int paramInt1, int paramInt2)
    throws ChecksumException
  {
    int i = 1;
    int k = paramInt1 - 1;
    int m;
    int i1;
    for (int j = 0; ; j = i1 * m + j)
    {
      m = i;
      if (k < 0)
        break;
      i1 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".indexOf(paramStringBuffer.charAt(k));
      int n = m + 1;
      i = n;
      if (n > paramInt2)
        i = 1;
      k -= 1;
    }
    if (paramStringBuffer.charAt(paramInt1) != ALPHABET[(j % 47)])
      throw ChecksumException.getChecksumInstance();
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
      if ((c >= 'a') && (c <= 'd'))
      {
        k = paramStringBuffer.charAt(i + 1);
        switch (c)
        {
        default:
          c = '\000';
          label82: localStringBuffer.append(c);
          i += 1;
        case 'd':
        case 'a':
        case 'b':
        case 'c':
        }
      }
      while (true)
      {
        i += 1;
        break;
        if ((k >= 65) && (k <= 90))
        {
          c = (char)(k + 32);
          break label82;
        }
        throw FormatException.getFormatInstance();
        if ((k >= 65) && (k <= 90))
        {
          c = (char)(k - 64);
          break label82;
        }
        throw FormatException.getFormatInstance();
        if ((k >= 65) && (k <= 69))
        {
          c = (char)(k - 38);
          break label82;
        }
        if ((k >= 70) && (k <= 87))
        {
          c = (char)(k - 11);
          break label82;
        }
        throw FormatException.getFormatInstance();
        if ((k >= 65) && (k <= 79))
        {
          c = (char)(k - 32);
          break label82;
        }
        if (k == 90)
        {
          c = ':';
          break label82;
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
      arrayOfInt = new int[6];
      i3 = arrayOfInt.length;
      k = i;
      m = 0;
      n = 0;
      if (k >= i2)
        break label240;
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
        if (toPattern(arrayOfInt) == ASTERISK_ENCODING)
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
          break label225;
        i1 = 1;
        m = j;
        n = i;
        j = i1;
        break;
        n += 1;
        j = i;
      }
      label225: int i1 = 0;
      m = j;
      n = i;
      j = i1;
    }
    label240: throw NotFoundException.getNotFoundInstance();
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

  private static int toPattern(int[] paramArrayOfInt)
  {
    int i2 = paramArrayOfInt.length;
    int i = 0;
    for (int k = 0; i < i2; k = j + k)
    {
      j = paramArrayOfInt[i];
      i += 1;
    }
    int m = 0;
    i = 0;
    int j = i;
    int n;
    if (m < i2)
    {
      j = (paramArrayOfInt[m] << 8) * 9 / k;
      n = j >> 8;
      if ((j & 0xFF) <= 127)
        break label144;
      n += 1;
    }
    label144: 
    while (true)
    {
      if ((n < 1) || (n > 4))
      {
        j = -1;
        return j;
      }
      if ((m & 0x1) == 0)
      {
        int i1 = 0;
        while (true)
        {
          j = i;
          if (i1 >= n)
            break;
          i1 += 1;
          i = i << 1 | 0x1;
        }
      }
      j = i << n;
      m += 1;
      i = j;
      break;
    }
  }

  public Result decodeRow(int paramInt, BitArray paramBitArray, Hashtable paramHashtable)
    throws NotFoundException, ChecksumException, FormatException
  {
    paramHashtable = findAsteriskPattern(paramBitArray);
    int i = paramHashtable[1];
    int m = paramBitArray.getSize();
    while ((i < m) && (!paramBitArray.get(i)))
      i += 1;
    Object localObject1 = new StringBuffer(20);
    Object localObject2 = new int[6];
    recordPattern(paramBitArray, i, (int[])localObject2);
    int j = toPattern((int[])localObject2);
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
      if ((j < m) && (!paramBitArray.get(j)))
      {
        j += 1;
      }
      else
      {
        if (c == '*')
        {
          ((StringBuffer)localObject1).deleteCharAt(((StringBuffer)localObject1).length() - 1);
          if ((j == m) || (!paramBitArray.get(j)))
            throw NotFoundException.getNotFoundInstance();
          if (((StringBuffer)localObject1).length() < 2)
            throw NotFoundException.getNotFoundInstance();
          checkChecksums((StringBuffer)localObject1);
          ((StringBuffer)localObject1).setLength(((StringBuffer)localObject1).length() - 2);
          paramBitArray = decodeExtended((StringBuffer)localObject1);
          k = paramHashtable[1];
          float f1 = (paramHashtable[0] + k) / 2.0F;
          float f2 = (i + j) / 2.0F;
          paramHashtable = new ResultPoint(f1, paramInt);
          localObject1 = new ResultPoint(f2, paramInt);
          localObject2 = BarcodeFormat.CODE_93;
          return new Result(paramBitArray, null, new ResultPoint[] { paramHashtable, localObject1 }, (BarcodeFormat)localObject2);
        }
        i = j;
        break;
      }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.Code93Reader
 * JD-Core Version:    0.6.2
 */