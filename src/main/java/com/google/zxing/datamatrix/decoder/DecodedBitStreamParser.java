package com.google.zxing.datamatrix.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.BitSource;
import com.google.zxing.common.DecoderResult;
import java.io.UnsupportedEncodingException;
import java.util.Vector;

final class DecodedBitStreamParser
{
  private static final int ANSIX12_ENCODE = 4;
  private static final int ASCII_ENCODE = 1;
  private static final int BASE256_ENCODE = 6;
  private static final char[] C40_BASIC_SET_CHARS = { 42, 42, 42, 32, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90 };
  private static final int C40_ENCODE = 2;
  private static final char[] C40_SHIFT2_SET_CHARS = { 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 58, 59, 60, 61, 62, 63, 64, 91, 92, 93, 94, 95 };
  private static final int EDIFACT_ENCODE = 5;
  private static final int PAD_ENCODE = 0;
  private static final char[] TEXT_BASIC_SET_CHARS = { 42, 42, 42, 32, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 };
  private static final int TEXT_ENCODE = 3;
  private static final char[] TEXT_SHIFT3_SET_CHARS = { 39, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 123, 124, 125, 126, 127 };

  static DecoderResult decode(byte[] paramArrayOfByte)
    throws FormatException
  {
    Object localObject1 = new BitSource(paramArrayOfByte);
    Object localObject2 = new StringBuffer(100);
    StringBuffer localStringBuffer = new StringBuffer(0);
    Vector localVector = new Vector(1);
    int i = 1;
    while (i == 1)
    {
      i = decodeAsciiSegment((BitSource)localObject1, (StringBuffer)localObject2, localStringBuffer);
      if ((i == 0) || (((BitSource)localObject1).available() <= 0))
      {
        if (localStringBuffer.length() > 0)
          ((StringBuffer)localObject2).append(localStringBuffer.toString());
        localObject2 = ((StringBuffer)localObject2).toString();
        localObject1 = localVector;
        if (localVector.isEmpty())
          localObject1 = null;
        return new DecoderResult(paramArrayOfByte, (String)localObject2, (Vector)localObject1, null);
      }
    }
    switch (i)
    {
    default:
      throw FormatException.getFormatInstance();
    case 2:
      decodeC40Segment((BitSource)localObject1, (StringBuffer)localObject2);
    case 3:
    case 4:
    case 5:
    case 6:
    }
    while (true)
    {
      i = 1;
      break;
      decodeTextSegment((BitSource)localObject1, (StringBuffer)localObject2);
      continue;
      decodeAnsiX12Segment((BitSource)localObject1, (StringBuffer)localObject2);
      continue;
      decodeEdifactSegment((BitSource)localObject1, (StringBuffer)localObject2);
      continue;
      decodeBase256Segment((BitSource)localObject1, (StringBuffer)localObject2, localVector);
    }
  }

  private static void decodeAnsiX12Segment(BitSource paramBitSource, StringBuffer paramStringBuffer)
    throws FormatException
  {
    int[] arrayOfInt = new int[3];
    label154: 
    do
    {
      if (paramBitSource.available() == 8);
      do
      {
        return;
        i = paramBitSource.readBits(8);
      }
      while (i == 254);
      parseTwoBytes(i, paramBitSource.readBits(8), arrayOfInt);
      int i = 0;
      if (i < 3)
      {
        int j = arrayOfInt[i];
        if (j == 0)
          paramStringBuffer.append('\r');
        while (true)
        {
          i += 1;
          break;
          if (j == 1)
          {
            paramStringBuffer.append('*');
          }
          else if (j == 2)
          {
            paramStringBuffer.append('>');
          }
          else if (j == 3)
          {
            paramStringBuffer.append(' ');
          }
          else if (j < 14)
          {
            paramStringBuffer.append((char)(j + 44));
          }
          else
          {
            if (j >= 40)
              break label154;
            paramStringBuffer.append((char)(j + 51));
          }
        }
        throw FormatException.getFormatInstance();
      }
    }
    while (paramBitSource.available() > 0);
  }

  private static int decodeAsciiSegment(BitSource paramBitSource, StringBuffer paramStringBuffer1, StringBuffer paramStringBuffer2)
    throws FormatException
  {
    int i = 0;
    int k = paramBitSource.readBits(8);
    if (k == 0)
      throw FormatException.getFormatInstance();
    if (k <= 128)
    {
      if (i != 0);
      for (i = k + 128; ; i = k)
      {
        paramStringBuffer1.append((char)(i - 1));
        return 1;
      }
    }
    if (k == 129)
      return 0;
    int j;
    if (k <= 229)
    {
      j = k - 130;
      if (j < 10)
        paramStringBuffer1.append('0');
      paramStringBuffer1.append(j);
      j = i;
    }
    label243: 
    do
    {
      do
      {
        while (true)
        {
          i = j;
          if (paramBitSource.available() > 0)
            break;
          return 1;
          if (k == 230)
            return 2;
          if (k == 231)
            return 6;
          j = i;
          if (k != 232)
          {
            j = i;
            if (k != 233)
            {
              j = i;
              if (k != 234)
                if (k == 235)
                {
                  j = 1;
                }
                else if (k == 236)
                {
                  paramStringBuffer1.append("[)>\03605\035");
                  paramStringBuffer2.insert(0, "\036\004");
                  j = i;
                }
                else
                {
                  if (k != 237)
                    break label243;
                  paramStringBuffer1.append("[)>\03606\035");
                  paramStringBuffer2.insert(0, "\036\004");
                  j = i;
                }
            }
          }
        }
        if (k == 238)
          return 4;
        if (k == 239)
          return 3;
        if (k == 240)
          return 5;
        j = i;
      }
      while (k == 241);
      j = i;
    }
    while (k < 242);
    throw FormatException.getFormatInstance();
  }

  private static void decodeBase256Segment(BitSource paramBitSource, StringBuffer paramStringBuffer, Vector paramVector)
    throws FormatException
  {
    int j = paramBitSource.readBits(8);
    int i;
    byte[] arrayOfByte;
    if (j == 0)
    {
      i = paramBitSource.available() / 8;
      arrayOfByte = new byte[i];
      j = 0;
    }
    while (true)
    {
      if (j >= i)
        break label105;
      if (paramBitSource.available() < 8)
      {
        throw FormatException.getFormatInstance();
        i = j;
        if (j < 250)
          break;
        i = (j - 249) * 250 + paramBitSource.readBits(8);
        break;
      }
      arrayOfByte[j] = unrandomize255State(paramBitSource.readBits(8), j);
      j += 1;
    }
    label105: paramVector.addElement(arrayOfByte);
    try
    {
      paramStringBuffer.append(new String(arrayOfByte, "ISO8859_1"));
      return;
    }
    catch (UnsupportedEncodingException paramBitSource)
    {
    }
    throw new RuntimeException("Platform does not support required encoding: " + paramBitSource);
  }

  private static void decodeC40Segment(BitSource paramBitSource, StringBuffer paramStringBuffer)
    throws FormatException
  {
    int[] arrayOfInt = new int[3];
    int i = 0;
    label291: 
    do
    {
      if (paramBitSource.available() == 8);
      do
      {
        return;
        j = paramBitSource.readBits(8);
      }
      while (j == 254);
      parseTwoBytes(j, paramBitSource.readBits(8), arrayOfInt);
      int k = 0;
      int j = 0;
      if (k < 3)
      {
        int m = arrayOfInt[k];
        switch (j)
        {
        default:
          throw FormatException.getFormatInstance();
        case 0:
          if (m < 3)
          {
            m += 1;
            j = i;
            i = m;
          }
          while (true)
          {
            m = k + 1;
            k = i;
            i = j;
            j = k;
            k = m;
            break;
            if (i != 0)
            {
              paramStringBuffer.append((char)(C40_BASIC_SET_CHARS[m] + ''));
              i = j;
              j = 0;
            }
            else
            {
              paramStringBuffer.append(C40_BASIC_SET_CHARS[m]);
              m = i;
              i = j;
              j = m;
            }
          }
        case 1:
          if (i != 0)
          {
            paramStringBuffer.append((char)(m + 128));
            i = 0;
          }
          while (true)
          {
            j = i;
            i = 0;
            break;
            paramStringBuffer.append(m);
          }
        case 2:
          if (m < 27)
            if (i != 0)
            {
              paramStringBuffer.append((char)(C40_SHIFT2_SET_CHARS[m] + ''));
              i = 0;
            }
          while (true)
          {
            j = i;
            i = 0;
            break;
            paramStringBuffer.append(C40_SHIFT2_SET_CHARS[m]);
            continue;
            if (m == 27)
              throw FormatException.getFormatInstance();
            if (m != 30)
              break label291;
            i = 1;
          }
          throw FormatException.getFormatInstance();
        case 3:
        }
        if (i != 0)
        {
          paramStringBuffer.append((char)(m + 224));
          i = 0;
        }
        while (true)
        {
          j = i;
          i = 0;
          break;
          paramStringBuffer.append((char)(m + 96));
        }
      }
    }
    while (paramBitSource.available() > 0);
  }

  private static void decodeEdifactSegment(BitSource paramBitSource, StringBuffer paramStringBuffer)
  {
    int i = 0;
    if (paramBitSource.available() <= 16)
      label11: return;
    int j = 0;
    label14: int k;
    if (j < 4)
    {
      k = paramBitSource.readBits(6);
      if (k != 11111)
        break label85;
      i = 1;
    }
    label82: label85: 
    while (true)
    {
      if (i == 0)
      {
        if ((k & 0x20) != 0)
          break label82;
        k |= 64;
      }
      while (true)
      {
        paramStringBuffer.append(k);
        j += 1;
        break label14;
        if (i != 0)
          break label11;
        if (paramBitSource.available() > 0)
          break;
        return;
      }
    }
  }

  private static void decodeTextSegment(BitSource paramBitSource, StringBuffer paramStringBuffer)
    throws FormatException
  {
    int[] arrayOfInt = new int[3];
    int i = 0;
    label291: 
    do
    {
      if (paramBitSource.available() == 8);
      do
      {
        return;
        j = paramBitSource.readBits(8);
      }
      while (j == 254);
      parseTwoBytes(j, paramBitSource.readBits(8), arrayOfInt);
      int k = 0;
      int j = 0;
      if (k < 3)
      {
        int m = arrayOfInt[k];
        switch (j)
        {
        default:
          throw FormatException.getFormatInstance();
        case 0:
          if (m < 3)
          {
            m += 1;
            j = i;
            i = m;
          }
          while (true)
          {
            m = k + 1;
            k = i;
            i = j;
            j = k;
            k = m;
            break;
            if (i != 0)
            {
              paramStringBuffer.append((char)(TEXT_BASIC_SET_CHARS[m] + ''));
              i = j;
              j = 0;
            }
            else
            {
              paramStringBuffer.append(TEXT_BASIC_SET_CHARS[m]);
              m = i;
              i = j;
              j = m;
            }
          }
        case 1:
          if (i != 0)
          {
            paramStringBuffer.append((char)(m + 128));
            i = 0;
          }
          while (true)
          {
            j = i;
            i = 0;
            break;
            paramStringBuffer.append(m);
          }
        case 2:
          if (m < 27)
            if (i != 0)
            {
              paramStringBuffer.append((char)(C40_SHIFT2_SET_CHARS[m] + ''));
              i = 0;
            }
          while (true)
          {
            j = i;
            i = 0;
            break;
            paramStringBuffer.append(C40_SHIFT2_SET_CHARS[m]);
            continue;
            if (m == 27)
              throw FormatException.getFormatInstance();
            if (m != 30)
              break label291;
            i = 1;
          }
          throw FormatException.getFormatInstance();
        case 3:
        }
        if (i != 0)
        {
          paramStringBuffer.append((char)(TEXT_SHIFT3_SET_CHARS[m] + ''));
          i = 0;
        }
        while (true)
        {
          j = i;
          i = 0;
          break;
          paramStringBuffer.append(TEXT_SHIFT3_SET_CHARS[m]);
        }
      }
    }
    while (paramBitSource.available() > 0);
  }

  private static void parseTwoBytes(int paramInt1, int paramInt2, int[] paramArrayOfInt)
  {
    paramInt1 = (paramInt1 << 8) + paramInt2 - 1;
    paramInt2 = paramInt1 / 1600;
    paramArrayOfInt[0] = paramInt2;
    paramInt1 -= paramInt2 * 1600;
    paramInt2 = paramInt1 / 40;
    paramArrayOfInt[1] = paramInt2;
    paramArrayOfInt[2] = (paramInt1 - paramInt2 * 40);
  }

  private static byte unrandomize255State(int paramInt1, int paramInt2)
  {
    paramInt1 -= paramInt2 * 149 % 255 + 1;
    if (paramInt1 >= 0);
    while (true)
    {
      return (byte)paramInt1;
      paramInt1 += 256;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.datamatrix.decoder.DecodedBitStreamParser
 * JD-Core Version:    0.6.2
 */