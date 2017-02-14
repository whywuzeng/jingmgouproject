package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.Hashtable;

public final class CodaBarReader extends OneDReader
{
  private static final char[] ALPHABET = "0123456789-$:/.+ABCDTN".toCharArray();
  private static final String ALPHABET_STRING = "0123456789-$:/.+ABCDTN";
  private static final int[] CHARACTER_ENCODINGS = { 3, 6, 9, 96, 18, 66, 33, 36, 48, 72, 12, 24, 37, 81, 84, 21, 26, 41, 11, 14, 26, 41 };
  private static final char[] STARTEND_ENCODING = { 69, 42, 65, 66, 67, 68, 84, 78 };
  private static final int minCharacterLength = 6;

  private static boolean arrayContains(char[] paramArrayOfChar, char paramChar)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    int i;
    if (paramArrayOfChar != null)
      i = 0;
    while (true)
    {
      bool1 = bool2;
      if (i < paramArrayOfChar.length)
      {
        if (paramArrayOfChar[i] == paramChar)
          bool1 = true;
      }
      else
        return bool1;
      i += 1;
    }
  }

  private static int[] findAsteriskPattern(BitArray paramBitArray)
    throws NotFoundException
  {
    int i1 = paramBitArray.getSize();
    int i = 0;
    int[] arrayOfInt;
    int i2;
    int m;
    int j;
    int k;
    int n;
    while (true)
    {
      if ((i >= i1) || (paramBitArray.get(i)))
      {
        arrayOfInt = new int[7];
        i2 = arrayOfInt.length;
        m = i;
        j = 0;
        k = 0;
        while (true)
        {
          if (m >= i1)
            break label254;
          if (!(paramBitArray.get(m) ^ j))
            break;
          arrayOfInt[k] += 1;
          n = j;
          j = k;
          k = i;
          i = n;
          n = m + 1;
          m = i;
          i = k;
          k = j;
          j = m;
          m = n;
        }
      }
      i += 1;
    }
    if (k == i2 - 1)
      try
      {
        if ((arrayContains(STARTEND_ENCODING, toNarrowWidePattern(arrayOfInt))) && (paramBitArray.isRange(Math.max(0, i - (m - i) / 2), i, false)))
          return new int[] { i, m };
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        n = i + (arrayOfInt[0] + arrayOfInt[1]);
        i = 2;
        while (i < i2)
        {
          arrayOfInt[(i - 2)] = arrayOfInt[i];
          i += 1;
        }
        arrayOfInt[(i2 - 2)] = 0;
        arrayOfInt[(i2 - 1)] = 0;
        k -= 1;
        i = n;
      }
    while (true)
    {
      arrayOfInt[k] = 1;
      n = k;
      k = i;
      i = j ^ 0x1;
      j = n;
      break;
      k += 1;
    }
    label254: throw NotFoundException.getNotFoundInstance();
  }

  private static char toNarrowWidePattern(int[] paramArrayOfInt)
  {
    int i3 = paramArrayOfInt.length;
    int j = 2147483647;
    int m = 0;
    int k;
    int n;
    for (int i = 0; ; i = n)
    {
      k = i;
      if (m >= i3)
        break;
      k = j;
      if (paramArrayOfInt[m] < j)
        k = paramArrayOfInt[m];
      n = i;
      if (paramArrayOfInt[m] > i)
        n = paramArrayOfInt[m];
      m += 1;
      j = k;
    }
    do
    {
      m = 0;
      i = 0;
      int i1;
      for (n = 0; m < i3; n = i1)
      {
        int i2 = i;
        i1 = n;
        if (paramArrayOfInt[m] > k)
        {
          i2 = i | 1 << i3 - 1 - m;
          i1 = n + 1;
        }
        m += 1;
        i = i2;
      }
      if ((n == 2) || (n == 3))
      {
        m = 0;
        while (m < CHARACTER_ENCODINGS.length)
        {
          if (CHARACTER_ENCODINGS[m] == i)
            return ALPHABET[m];
          m += 1;
        }
      }
      i = k - 1;
      k = i;
    }
    while (i > j);
    return '!';
  }

  public Result decodeRow(int paramInt, BitArray paramBitArray, Hashtable paramHashtable)
    throws NotFoundException
  {
    paramHashtable = findAsteriskPattern(paramBitArray);
    paramHashtable[1] = 0;
    int i = paramHashtable[1];
    int n = paramBitArray.getSize();
    while ((i < n) && (!paramBitArray.get(i)))
      i += 1;
    Object localObject1 = new StringBuffer();
    Object localObject2 = new int[7];
    Object tmp62_60 = localObject2;
    tmp62_60[0] = 0;
    Object tmp66_62 = tmp62_60;
    tmp66_62[1] = 0;
    Object tmp70_66 = tmp66_62;
    tmp70_66[2] = 0;
    Object tmp74_70 = tmp70_66;
    tmp74_70[3] = 0;
    Object tmp78_74 = tmp74_70;
    tmp78_74[4] = 0;
    Object tmp82_78 = tmp78_74;
    tmp82_78[5] = 0;
    Object tmp86_82 = tmp82_78;
    tmp86_82[6] = 0;
    tmp86_82;
    recordPattern(paramBitArray, i, (int[])localObject2);
    char c = toNarrowWidePattern((int[])localObject2);
    if (c == '!')
      throw NotFoundException.getNotFoundInstance();
    ((StringBuffer)localObject1).append(c);
    int k = 0;
    int j = i;
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
        if (j >= n)
        {
          int m = 0;
          k = 0;
          while (k < localObject2.length)
          {
            m += localObject2[k];
            k += 1;
          }
          if ((j != n) && ((j - i - m) / 2 < m))
            throw NotFoundException.getNotFoundInstance();
          if (((StringBuffer)localObject1).length() < 2)
            throw NotFoundException.getNotFoundInstance();
          c = ((StringBuffer)localObject1).charAt(0);
          if (!arrayContains(STARTEND_ENCODING, c))
            throw NotFoundException.getNotFoundInstance();
          for (k = 1; k < ((StringBuffer)localObject1).length(); k = m + 1)
          {
            m = k;
            if (((StringBuffer)localObject1).charAt(k) == c)
            {
              m = k;
              if (k + 1 != ((StringBuffer)localObject1).length())
              {
                ((StringBuffer)localObject1).delete(k + 1, ((StringBuffer)localObject1).length() - 1);
                m = ((StringBuffer)localObject1).length();
              }
            }
          }
          if (((StringBuffer)localObject1).length() > 6)
          {
            ((StringBuffer)localObject1).deleteCharAt(((StringBuffer)localObject1).length() - 1);
            ((StringBuffer)localObject1).deleteCharAt(0);
            float f1 = (paramHashtable[1] + paramHashtable[0]) / 2.0F;
            float f2 = (i + j) / 2.0F;
            paramBitArray = ((StringBuffer)localObject1).toString();
            paramHashtable = new ResultPoint(f1, paramInt);
            localObject1 = new ResultPoint(f2, paramInt);
            localObject2 = BarcodeFormat.CODABAR;
            return new Result(paramBitArray, null, new ResultPoint[] { paramHashtable, localObject1 }, (BarcodeFormat)localObject2);
          }
          throw NotFoundException.getNotFoundInstance();
        }
        i = j;
        break;
      }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.CodaBarReader
 * JD-Core Version:    0.6.2
 */