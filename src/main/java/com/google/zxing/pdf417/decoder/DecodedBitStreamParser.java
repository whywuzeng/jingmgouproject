package com.google.zxing.pdf417.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.DecoderResult;

final class DecodedBitStreamParser
{
  private static final int AL = 28;
  private static final int ALPHA = 0;
  private static final int AS = 27;
  private static final int BEGIN_MACRO_PDF417_CONTROL_BLOCK = 928;
  private static final int BEGIN_MACRO_PDF417_OPTIONAL_FIELD = 923;
  private static final int BYTE_COMPACTION_MODE_LATCH = 901;
  private static final int BYTE_COMPACTION_MODE_LATCH_6 = 924;
  private static final String[] EXP900 = { "000000000000000000000000000000000000000000001", "000000000000000000000000000000000000000000900", "000000000000000000000000000000000000000810000", "000000000000000000000000000000000000729000000", "000000000000000000000000000000000656100000000", "000000000000000000000000000000590490000000000", "000000000000000000000000000531441000000000000", "000000000000000000000000478296900000000000000", "000000000000000000000430467210000000000000000", "000000000000000000387420489000000000000000000", "000000000000000348678440100000000000000000000", "000000000000313810596090000000000000000000000", "000000000282429536481000000000000000000000000", "000000254186582832900000000000000000000000000", "000228767924549610000000000000000000000000000", "205891132094649000000000000000000000000000000" };
  private static final int LL = 27;
  private static final int LOWER = 1;
  private static final int MACRO_PDF417_TERMINATOR = 922;
  private static final int MAX_NUMERIC_CODEWORDS = 15;
  private static final int MIXED = 2;
  private static final char[] MIXED_CHARS;
  private static final int ML = 28;
  private static final int MODE_SHIFT_TO_BYTE_COMPACTION_MODE = 913;
  private static final int NUMERIC_COMPACTION_MODE_LATCH = 902;
  private static final int PAL = 29;
  private static final int PL = 25;
  private static final int PS = 29;
  private static final int PUNCT = 3;
  private static final char[] PUNCT_CHARS = { 59, 60, 62, 64, 91, 92, 125, 95, 96, 126, 33, 13, 9, 44, 58, 10, 45, 46, 36, 47, 34, 124, 42, 40, 41, 63, 123, 125, 39 };
  private static final int PUNCT_SHIFT = 4;
  private static final int TEXT_COMPACTION_MODE_LATCH = 900;

  static
  {
    MIXED_CHARS = new char[] { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 38, 13, 9, 44, 58, 35, 45, 46, 36, 47, 43, 37, 42, 61, 94 };
  }

  private static StringBuffer add(String paramString1, String paramString2)
  {
    StringBuffer localStringBuffer1 = new StringBuffer(5);
    StringBuffer localStringBuffer2 = new StringBuffer(5);
    StringBuffer localStringBuffer3 = new StringBuffer(paramString1.length());
    int i = 0;
    while (i < paramString1.length())
    {
      localStringBuffer3.append('0');
      i += 1;
    }
    i = paramString1.length() - 3;
    int j = 0;
    while (i > -1)
    {
      localStringBuffer1.setLength(0);
      localStringBuffer1.append(paramString1.charAt(i));
      localStringBuffer1.append(paramString1.charAt(i + 1));
      localStringBuffer1.append(paramString1.charAt(i + 2));
      localStringBuffer2.setLength(0);
      localStringBuffer2.append(paramString2.charAt(i));
      localStringBuffer2.append(paramString2.charAt(i + 1));
      localStringBuffer2.append(paramString2.charAt(i + 2));
      int m = Integer.parseInt(localStringBuffer1.toString());
      int n = Integer.parseInt(localStringBuffer2.toString());
      int k = (m + n + j) % 1000;
      j = (j + (m + n)) / 1000;
      localStringBuffer3.setCharAt(i + 2, (char)(k % 10 + 48));
      localStringBuffer3.setCharAt(i + 1, (char)(k / 10 % 10 + 48));
      localStringBuffer3.setCharAt(i, (char)(k / 100 + 48));
      i -= 3;
    }
    return localStringBuffer3;
  }

  private static int byteCompaction(int paramInt1, int[] paramArrayOfInt, int paramInt2, StringBuffer paramStringBuffer)
  {
    long l1;
    char[] arrayOfChar;
    int[] arrayOfInt;
    int j;
    int i;
    int m;
    long l2;
    int k;
    if (paramInt1 == 901)
    {
      paramInt1 = 0;
      l1 = 0L;
      arrayOfChar = new char[6];
      arrayOfInt = new int[6];
      j = 0;
      i = paramInt2;
      paramInt2 = paramInt1;
      if ((i < paramArrayOfInt[0]) && (j == 0))
      {
        paramInt1 = i + 1;
        i = paramArrayOfInt[i];
        if (i < 900)
        {
          arrayOfInt[paramInt2] = i;
          m = paramInt2 + 1;
          l2 = l1 * 900L + i;
          k = j;
        }
      }
    }
    while (true)
    {
      j = k;
      paramInt2 = m;
      l1 = l2;
      i = paramInt1;
      if (m % 5 != 0)
        break;
      j = k;
      paramInt2 = m;
      l1 = l2;
      i = paramInt1;
      if (m <= 0)
        break;
      paramInt2 = 0;
      l1 = l2;
      while (true)
        if (paramInt2 < 6)
        {
          arrayOfChar[(5 - paramInt2)] = ((char)(int)(l1 % 256L));
          l1 >>= 8;
          paramInt2 += 1;
          continue;
          if ((i != 900) && (i != 901) && (i != 902) && (i != 924) && (i != 928) && (i != 923) && (i != 922))
            break label559;
          paramInt1 -= 1;
          k = 1;
          m = paramInt2;
          l2 = l1;
          break;
        }
      paramStringBuffer.append(arrayOfChar);
      paramInt2 = 0;
      j = k;
      i = paramInt1;
      break;
      paramInt1 = paramInt2 / 5 * 5;
      while (true)
      {
        j = i;
        if (paramInt1 >= paramInt2)
          break;
        paramStringBuffer.append((char)arrayOfInt[paramInt1]);
        paramInt1 += 1;
      }
      j = paramInt2;
      if (paramInt1 == 924)
      {
        i = 0;
        l1 = 0L;
        k = 0;
        j = paramInt2;
        if (paramInt2 < paramArrayOfInt[0])
        {
          j = paramInt2;
          if (k == 0)
          {
            paramInt1 = paramInt2 + 1;
            paramInt2 = paramArrayOfInt[paramInt2];
            if (paramInt2 < 900)
            {
              m = i + 1;
              l2 = l1 * 900L + paramInt2;
              j = k;
            }
          }
        }
      }
      while (true)
      {
        k = j;
        i = m;
        l1 = l2;
        paramInt2 = paramInt1;
        if (m % 5 != 0)
          break;
        k = j;
        i = m;
        l1 = l2;
        paramInt2 = paramInt1;
        if (m <= 0)
          break;
        arrayOfChar = new char[6];
        paramInt2 = 0;
        l1 = l2;
        while (true)
          if (paramInt2 < 6)
          {
            arrayOfChar[(5 - paramInt2)] = ((char)(int)(0xFF & l1));
            paramInt2 += 1;
            l1 >>= 8;
            continue;
            if ((paramInt2 != 900) && (paramInt2 != 901) && (paramInt2 != 902) && (paramInt2 != 924) && (paramInt2 != 928) && (paramInt2 != 923) && (paramInt2 != 922))
              break label544;
            paramInt1 -= 1;
            j = 1;
            m = i;
            l2 = l1;
            break;
          }
        paramStringBuffer.append(arrayOfChar);
        k = j;
        i = m;
        paramInt2 = paramInt1;
        break;
        return j;
        label544: j = k;
        m = i;
        l2 = l1;
      }
      label559: k = j;
      m = paramInt2;
      l2 = l1;
    }
  }

  static DecoderResult decode(int[] paramArrayOfInt)
    throws FormatException
  {
    StringBuffer localStringBuffer = new StringBuffer(100);
    int j = 2;
    int i = paramArrayOfInt[1];
    if (j < paramArrayOfInt[0])
    {
      switch (i)
      {
      default:
        i = textCompaction(paramArrayOfInt, j - 1, localStringBuffer);
      case 900:
      case 901:
      case 902:
      case 913:
      case 924:
      }
      while (true)
      {
        if (i >= paramArrayOfInt.length)
          break label155;
        j = i + 1;
        i = paramArrayOfInt[i];
        break;
        i = textCompaction(paramArrayOfInt, j, localStringBuffer);
        continue;
        i = byteCompaction(i, paramArrayOfInt, j, localStringBuffer);
        continue;
        i = numericCompaction(paramArrayOfInt, j, localStringBuffer);
        continue;
        i = byteCompaction(i, paramArrayOfInt, j, localStringBuffer);
        continue;
        i = byteCompaction(i, paramArrayOfInt, j, localStringBuffer);
      }
      label155: throw FormatException.getFormatInstance();
    }
    return new DecoderResult(null, localStringBuffer.toString(), null, null);
  }

  private static String decodeBase900toBase10(int[] paramArrayOfInt, int paramInt)
  {
    int i = 0;
    Object localObject1 = null;
    Object localObject2;
    if (i < paramInt)
    {
      localObject2 = multiply(EXP900[(paramInt - i - 1)], paramArrayOfInt[i]);
      if (localObject1 == null);
      for (localObject1 = localObject2; ; localObject1 = add(((StringBuffer)localObject1).toString(), ((StringBuffer)localObject2).toString()))
      {
        i += 1;
        break;
      }
    }
    paramInt = 0;
    if (paramInt < ((StringBuffer)localObject1).length())
      if (((StringBuffer)localObject1).charAt(paramInt) != '1');
    for (paramArrayOfInt = ((StringBuffer)localObject1).toString().substring(paramInt + 1); ; paramArrayOfInt = null)
    {
      localObject2 = paramArrayOfInt;
      if (paramArrayOfInt == null)
        localObject2 = ((StringBuffer)localObject1).toString();
      return localObject2;
      paramInt += 1;
      break;
    }
  }

  private static void decodeTextCompaction(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt, StringBuffer paramStringBuffer)
  {
    int k = 0;
    int j = 0;
    int i = 0;
    int m;
    label56: char c;
    if (k < paramInt)
    {
      m = paramArrayOfInt1[k];
      switch (i)
      {
      default:
        c = '\000';
      case 0:
      case 1:
      case 2:
      case 3:
      case 4:
      }
    }
    while (true)
    {
      if (c != 0)
        paramStringBuffer.append(c);
      k += 1;
      break;
      if (m < 26)
      {
        c = (char)(m + 65);
      }
      else if (m == 26)
      {
        c = ' ';
      }
      else if (m == 27)
      {
        i = 1;
        c = '\000';
      }
      else if (m == 28)
      {
        i = 2;
        c = '\000';
      }
      else if (m == 29)
      {
        c = '\000';
        m = 4;
        j = i;
        i = m;
      }
      else
      {
        if (m != 913)
          break label56;
        paramStringBuffer.append((char)paramArrayOfInt2[k]);
        c = '\000';
        continue;
        if (m < 26)
        {
          c = (char)(m + 97);
        }
        else if (m == 26)
        {
          c = ' ';
        }
        else if (m == 28)
        {
          c = '\000';
          i = 0;
        }
        else if (m == 28)
        {
          i = 2;
          c = '\000';
        }
        else if (m == 29)
        {
          c = '\000';
          m = 4;
          j = i;
          i = m;
        }
        else
        {
          if (m != 913)
            break label56;
          paramStringBuffer.append((char)paramArrayOfInt2[k]);
          c = '\000';
          continue;
          if (m < 25)
          {
            c = MIXED_CHARS[m];
          }
          else if (m == 25)
          {
            i = 3;
            c = '\000';
          }
          else if (m == 26)
          {
            c = ' ';
          }
          else if (m == 27)
          {
            c = '\000';
          }
          else if (m == 28)
          {
            c = '\000';
            i = 0;
          }
          else if (m == 29)
          {
            c = '\000';
            m = 4;
            j = i;
            i = m;
          }
          else
          {
            if (m != 913)
              break label56;
            paramStringBuffer.append((char)paramArrayOfInt2[k]);
            c = '\000';
            continue;
            if (m < 29)
            {
              c = PUNCT_CHARS[m];
            }
            else if (m == 29)
            {
              c = '\000';
              i = 0;
            }
            else
            {
              if (m != 913)
                break label56;
              paramStringBuffer.append((char)paramArrayOfInt2[k]);
              c = '\000';
              continue;
              if (m < 29)
              {
                c = PUNCT_CHARS[m];
                i = j;
              }
              else if (m == 29)
              {
                c = '\000';
                i = 0;
              }
              else
              {
                c = '\000';
                i = j;
              }
            }
          }
        }
      }
    }
  }

  private static StringBuffer multiply(String paramString, int paramInt)
  {
    int j = 0;
    StringBuffer localStringBuffer1 = new StringBuffer(paramString.length());
    int i = 0;
    while (i < paramString.length())
    {
      localStringBuffer1.append('0');
      i += 1;
    }
    int k = paramInt / 100;
    int m = paramInt / 10;
    i = 0;
    while (i < paramInt % 10)
    {
      localStringBuffer1 = add(localStringBuffer1.toString(), paramString);
      i += 1;
    }
    paramInt = 0;
    StringBuffer localStringBuffer2;
    while (true)
    {
      localStringBuffer2 = localStringBuffer1;
      i = j;
      if (paramInt >= m % 10)
        break;
      localStringBuffer1 = add(localStringBuffer1.toString(), (paramString + '0').substring(1));
      paramInt += 1;
    }
    while (i < k)
    {
      localStringBuffer2 = add(localStringBuffer2.toString(), (paramString + "00").substring(2));
      i += 1;
    }
    return localStringBuffer2;
  }

  private static int numericCompaction(int[] paramArrayOfInt, int paramInt, StringBuffer paramStringBuffer)
  {
    int[] arrayOfInt = new int[15];
    int k = 0;
    int i = 0;
    int n = paramInt;
    int j;
    int m;
    if ((n < paramArrayOfInt[0]) && (k == 0))
    {
      paramInt = n + 1;
      n = paramArrayOfInt[n];
      j = k;
      if (paramInt == paramArrayOfInt[0])
        j = 1;
      if (n < 900)
      {
        arrayOfInt[i] = n;
        m = i + 1;
      }
    }
    while (true)
    {
      if ((m % 15 != 0) && (n != 902))
      {
        k = j;
        i = m;
        n = paramInt;
        if (j == 0)
          break;
      }
      paramStringBuffer.append(decodeBase900toBase10(arrayOfInt, m));
      i = 0;
      k = j;
      n = paramInt;
      break;
      if ((n == 900) || (n == 901) || (n == 924) || (n == 928) || (n == 923) || (n == 922))
      {
        paramInt -= 1;
        j = 1;
        m = i;
        continue;
        return n;
      }
      else
      {
        m = i;
      }
    }
  }

  private static int textCompaction(int[] paramArrayOfInt, int paramInt, StringBuffer paramStringBuffer)
  {
    int[] arrayOfInt1 = new int[paramArrayOfInt[0] << 1];
    int[] arrayOfInt2 = new int[paramArrayOfInt[0] << 1];
    int i = 0;
    int j = 0;
    while ((paramInt < paramArrayOfInt[0]) && (i == 0))
    {
      int k = paramInt + 1;
      paramInt = paramArrayOfInt[paramInt];
      if (paramInt < 900)
      {
        arrayOfInt1[j] = (paramInt / 30);
        arrayOfInt1[(j + 1)] = (paramInt % 30);
        j += 2;
        paramInt = k;
      }
      else
      {
        switch (paramInt)
        {
        default:
          paramInt = k;
          break;
        case 900:
          paramInt = k - 1;
          i = 1;
          break;
        case 901:
          paramInt = k - 1;
          i = 1;
          break;
        case 902:
          paramInt = k - 1;
          i = 1;
          break;
        case 913:
          arrayOfInt1[j] = 913;
          arrayOfInt2[j] = paramInt;
          j += 1;
          paramInt = k;
          break;
        case 924:
          paramInt = k - 1;
          i = 1;
        }
      }
    }
    decodeTextCompaction(arrayOfInt1, arrayOfInt2, j, paramStringBuffer);
    return paramInt;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.pdf417.decoder.DecodedBitStreamParser
 * JD-Core Version:    0.6.2
 */