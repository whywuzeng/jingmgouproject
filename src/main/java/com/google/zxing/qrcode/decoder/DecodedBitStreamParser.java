package com.google.zxing.qrcode.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.BitSource;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.StringUtils;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.Vector;

final class DecodedBitStreamParser
{
  private static final char[] ALPHANUMERIC_CHARS = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 32, 36, 37, 42, 43, 45, 46, 47, 58 };

  static DecoderResult decode(byte[] paramArrayOfByte, Version paramVersion, ErrorCorrectionLevel paramErrorCorrectionLevel, Hashtable paramHashtable)
    throws FormatException
  {
    Object localObject = null;
    BitSource localBitSource = new BitSource(paramArrayOfByte);
    StringBuffer localStringBuffer = new StringBuffer(50);
    boolean bool = false;
    Vector localVector = new Vector(1);
    CharacterSetECI localCharacterSetECI = null;
    label149: label326: label329: 
    while (true)
    {
      Mode localMode;
      if (localBitSource.available() < 4)
      {
        localMode = Mode.TERMINATOR;
        if (localMode.equals(Mode.TERMINATOR))
          break label326;
        if ((!localMode.equals(Mode.FNC1_FIRST_POSITION)) && (!localMode.equals(Mode.FNC1_SECOND_POSITION)))
          break label149;
        bool = true;
      }
      while (true)
      {
        label90: if (!localMode.equals(Mode.TERMINATOR))
          break label329;
        paramHashtable = localStringBuffer.toString();
        if (localVector.isEmpty());
        for (paramVersion = localObject; ; paramVersion = localVector)
        {
          while (true)
          {
            return new DecoderResult(paramArrayOfByte, paramHashtable, paramVersion, paramErrorCorrectionLevel);
            try
            {
              localMode = Mode.forBits(localBitSource.readBits(4));
            }
            catch (IllegalArgumentException paramArrayOfByte)
            {
              throw FormatException.getFormatInstance();
            }
          }
          if (localMode.equals(Mode.STRUCTURED_APPEND))
          {
            localBitSource.readBits(16);
            break label90;
          }
          if (localMode.equals(Mode.ECI))
          {
            localCharacterSetECI = CharacterSetECI.getCharacterSetECIByValue(parseECIValue(localBitSource));
            if (localCharacterSetECI == null)
              throw FormatException.getFormatInstance();
            break label90;
          }
          int i = localBitSource.readBits(localMode.getCharacterCountBits(paramVersion));
          if (localMode.equals(Mode.NUMERIC))
          {
            decodeNumericSegment(localBitSource, localStringBuffer, i);
            break label90;
          }
          if (localMode.equals(Mode.ALPHANUMERIC))
          {
            decodeAlphanumericSegment(localBitSource, localStringBuffer, i, bool);
            break label90;
          }
          if (localMode.equals(Mode.BYTE))
          {
            decodeByteSegment(localBitSource, localStringBuffer, i, localCharacterSetECI, localVector, paramHashtable);
            break label90;
          }
          if (localMode.equals(Mode.KANJI))
          {
            decodeKanjiSegment(localBitSource, localStringBuffer, i);
            break label90;
          }
          throw FormatException.getFormatInstance();
        }
      }
    }
  }

  private static void decodeAlphanumericSegment(BitSource paramBitSource, StringBuffer paramStringBuffer, int paramInt, boolean paramBoolean)
    throws FormatException
  {
    int i = paramStringBuffer.length();
    while (paramInt > 1)
    {
      int j = paramBitSource.readBits(11);
      paramStringBuffer.append(toAlphaNumericChar(j / 45));
      paramStringBuffer.append(toAlphaNumericChar(j % 45));
      paramInt -= 2;
    }
    if (paramInt == 1)
      paramStringBuffer.append(toAlphaNumericChar(paramBitSource.readBits(6)));
    if (paramBoolean)
    {
      paramInt = i;
      if (paramInt < paramStringBuffer.length())
      {
        if (paramStringBuffer.charAt(paramInt) == '%')
        {
          if ((paramInt >= paramStringBuffer.length() - 1) || (paramStringBuffer.charAt(paramInt + 1) != '%'))
            break label133;
          paramStringBuffer.deleteCharAt(paramInt + 1);
        }
        while (true)
        {
          paramInt += 1;
          break;
          label133: paramStringBuffer.setCharAt(paramInt, '\035');
        }
      }
    }
  }

  private static void decodeByteSegment(BitSource paramBitSource, StringBuffer paramStringBuffer, int paramInt, CharacterSetECI paramCharacterSetECI, Vector paramVector, Hashtable paramHashtable)
    throws FormatException
  {
    byte[] arrayOfByte = new byte[paramInt];
    if (paramInt << 3 > paramBitSource.available())
      throw FormatException.getFormatInstance();
    int i = 0;
    while (i < paramInt)
    {
      arrayOfByte[i] = ((byte)paramBitSource.readBits(8));
      i += 1;
    }
    if (paramCharacterSetECI == null)
      paramBitSource = StringUtils.guessEncoding(arrayOfByte, paramHashtable);
    try
    {
      while (true)
      {
        paramStringBuffer.append(new String(arrayOfByte, paramBitSource));
        paramVector.addElement(arrayOfByte);
        return;
        paramBitSource = paramCharacterSetECI.getEncodingName();
      }
    }
    catch (UnsupportedEncodingException paramBitSource)
    {
    }
    throw FormatException.getFormatInstance();
  }

  private static void decodeKanjiSegment(BitSource paramBitSource, StringBuffer paramStringBuffer, int paramInt)
    throws FormatException
  {
    byte[] arrayOfByte = new byte[paramInt * 2];
    int j = 0;
    int i = paramInt;
    paramInt = j;
    if (i > 0)
    {
      j = paramBitSource.readBits(13);
      j = j % 192 | j / 192 << 8;
      if (j < 7936)
        j += 33088;
      while (true)
      {
        arrayOfByte[paramInt] = ((byte)(j >> 8));
        arrayOfByte[(paramInt + 1)] = ((byte)j);
        i -= 1;
        paramInt += 2;
        break;
        j += 49472;
      }
    }
    try
    {
      paramStringBuffer.append(new String(arrayOfByte, "SJIS"));
      return;
    }
    catch (UnsupportedEncodingException paramBitSource)
    {
    }
    throw FormatException.getFormatInstance();
  }

  private static void decodeNumericSegment(BitSource paramBitSource, StringBuffer paramStringBuffer, int paramInt)
    throws FormatException
  {
    while (paramInt >= 3)
    {
      int i = paramBitSource.readBits(10);
      if (i >= 1000)
        throw FormatException.getFormatInstance();
      paramStringBuffer.append(toAlphaNumericChar(i / 100));
      paramStringBuffer.append(toAlphaNumericChar(i / 10 % 10));
      paramStringBuffer.append(toAlphaNumericChar(i % 10));
      paramInt -= 3;
    }
    if (paramInt == 2)
    {
      paramInt = paramBitSource.readBits(7);
      if (paramInt >= 100)
        throw FormatException.getFormatInstance();
      paramStringBuffer.append(toAlphaNumericChar(paramInt / 10));
      paramStringBuffer.append(toAlphaNumericChar(paramInt % 10));
    }
    while (paramInt != 1)
      return;
    paramInt = paramBitSource.readBits(4);
    if (paramInt >= 10)
      throw FormatException.getFormatInstance();
    paramStringBuffer.append(toAlphaNumericChar(paramInt));
  }

  private static int parseECIValue(BitSource paramBitSource)
  {
    int i = paramBitSource.readBits(8);
    if ((i & 0x80) == 0)
      return i & 0x7F;
    if ((i & 0xC0) == 128)
      return (i & 0x3F) << 8 | paramBitSource.readBits(8);
    if ((i & 0xE0) == 192)
      return (i & 0x1F) << 16 | paramBitSource.readBits(16);
    throw new IllegalArgumentException("Bad ECI bits starting with byte " + i);
  }

  private static char toAlphaNumericChar(int paramInt)
    throws FormatException
  {
    if (paramInt >= ALPHANUMERIC_CHARS.length)
      throw FormatException.getFormatInstance();
    return ALPHANUMERIC_CHARS[paramInt];
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.qrcode.decoder.DecodedBitStreamParser
 * JD-Core Version:    0.6.2
 */