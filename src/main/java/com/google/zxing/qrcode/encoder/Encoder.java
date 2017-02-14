package com.google.zxing.qrcode.encoder;

import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitArray;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.ECI;
import com.google.zxing.common.reedsolomon.GF256;
import com.google.zxing.common.reedsolomon.ReedSolomonEncoder;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Mode;
import com.google.zxing.qrcode.decoder.Version;
import com.google.zxing.qrcode.decoder.Version.ECBlocks;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.Vector;

public final class Encoder
{
  private static final int[] ALPHANUMERIC_TABLE = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 36, -1, -1, -1, 37, 38, -1, -1, -1, -1, 39, 40, -1, 41, 42, 43, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 44, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, -1, -1, -1, -1, -1 };
  static final String DEFAULT_BYTE_MODE_ENCODING = "ISO-8859-1";

  static void append8BitBytes(String paramString1, BitArray paramBitArray, String paramString2)
    throws WriterException
  {
    try
    {
      paramString1 = paramString1.getBytes(paramString2);
      int i = 0;
      while (i < paramString1.length)
      {
        paramBitArray.appendBits(paramString1[i], 8);
        i += 1;
      }
    }
    catch (UnsupportedEncodingException paramString1)
    {
      throw new WriterException(paramString1.toString());
    }
  }

  static void appendAlphanumericBytes(String paramString, BitArray paramBitArray)
    throws WriterException
  {
    int j = paramString.length();
    int i = 0;
    while (i < j)
    {
      int k = getAlphanumericCode(paramString.charAt(i));
      if (k == -1)
        throw new WriterException();
      if (i + 1 < j)
      {
        int m = getAlphanumericCode(paramString.charAt(i + 1));
        if (m == -1)
          throw new WriterException();
        paramBitArray.appendBits(k * 45 + m, 11);
        i += 2;
      }
      else
      {
        paramBitArray.appendBits(k, 6);
        i += 1;
      }
    }
  }

  static void appendBytes(String paramString1, Mode paramMode, BitArray paramBitArray, String paramString2)
    throws WriterException
  {
    if (paramMode.equals(Mode.NUMERIC))
    {
      appendNumericBytes(paramString1, paramBitArray);
      return;
    }
    if (paramMode.equals(Mode.ALPHANUMERIC))
    {
      appendAlphanumericBytes(paramString1, paramBitArray);
      return;
    }
    if (paramMode.equals(Mode.BYTE))
    {
      append8BitBytes(paramString1, paramBitArray, paramString2);
      return;
    }
    if (paramMode.equals(Mode.KANJI))
    {
      appendKanjiBytes(paramString1, paramBitArray);
      return;
    }
    throw new WriterException("Invalid mode: " + paramMode);
  }

  private static void appendECI(ECI paramECI, BitArray paramBitArray)
  {
    paramBitArray.appendBits(Mode.ECI.getBits(), 4);
    paramBitArray.appendBits(paramECI.getValue(), 8);
  }

  static void appendKanjiBytes(String paramString, BitArray paramBitArray)
    throws WriterException
  {
    while (true)
    {
      int j;
      int i;
      try
      {
        paramString = paramString.getBytes("Shift_JIS");
        int k = paramString.length;
        j = 0;
        if (j >= k)
          break label132;
        i = (paramString[j] & 0xFF) << 8 | paramString[(j + 1)] & 0xFF;
        if ((i >= 33088) && (i <= 40956))
        {
          i -= 33088;
          if (i != -1)
            break label105;
          throw new WriterException("Invalid byte sequence");
        }
      }
      catch (UnsupportedEncodingException paramString)
      {
        throw new WriterException(paramString.toString());
      }
      if ((i >= 57408) && (i <= 60351))
      {
        i -= 49472;
        continue;
        label105: paramBitArray.appendBits((i & 0xFF) + (i >> 8) * 192, 13);
        j += 2;
      }
      else
      {
        label132: i = -1;
      }
    }
  }

  static void appendLengthInfo(int paramInt1, int paramInt2, Mode paramMode, BitArray paramBitArray)
    throws WriterException
  {
    paramInt2 = paramMode.getCharacterCountBits(Version.getVersionForNumber(paramInt2));
    if (paramInt1 > (1 << paramInt2) - 1)
      throw new WriterException(paramInt1 + "is bigger than" + ((1 << paramInt2) - 1));
    paramBitArray.appendBits(paramInt1, paramInt2);
  }

  static void appendModeInfo(Mode paramMode, BitArray paramBitArray)
  {
    paramBitArray.appendBits(paramMode.getBits(), 4);
  }

  static void appendNumericBytes(String paramString, BitArray paramBitArray)
  {
    int j = paramString.length();
    int i = 0;
    while (i < j)
    {
      int k = paramString.charAt(i) - '0';
      if (i + 2 < j)
      {
        paramBitArray.appendBits(k * 100 + (paramString.charAt(i + 1) - '0') * 10 + (paramString.charAt(i + 2) - '0'), 10);
        i += 3;
      }
      else if (i + 1 < j)
      {
        paramBitArray.appendBits(k * 10 + (paramString.charAt(i + 1) - '0'), 7);
        i += 2;
      }
      else
      {
        paramBitArray.appendBits(k, 4);
        i += 1;
      }
    }
  }

  private static int calculateMaskPenalty(ByteMatrix paramByteMatrix)
  {
    return 0 + MaskUtil.applyMaskPenaltyRule1(paramByteMatrix) + MaskUtil.applyMaskPenaltyRule2(paramByteMatrix) + MaskUtil.applyMaskPenaltyRule3(paramByteMatrix) + MaskUtil.applyMaskPenaltyRule4(paramByteMatrix);
  }

  private static int chooseMaskPattern(BitArray paramBitArray, ErrorCorrectionLevel paramErrorCorrectionLevel, int paramInt, ByteMatrix paramByteMatrix)
    throws WriterException
  {
    int j = 2147483647;
    int k = -1;
    int i = 0;
    if (i < 8)
    {
      MatrixUtil.buildMatrix(paramBitArray, paramErrorCorrectionLevel, paramInt, i, paramByteMatrix);
      int m = calculateMaskPenalty(paramByteMatrix);
      if (m >= j)
        break label59;
      k = i;
      j = m;
    }
    label59: 
    while (true)
    {
      i += 1;
      break;
      return k;
    }
  }

  public static Mode chooseMode(String paramString)
  {
    return chooseMode(paramString, null);
  }

  public static Mode chooseMode(String paramString1, String paramString2)
  {
    int i = 0;
    if ("Shift_JIS".equals(paramString2))
    {
      if (isOnlyDoubleByteKanji(paramString1))
        return Mode.KANJI;
      return Mode.BYTE;
    }
    int k = 0;
    int j = 0;
    if (i < paramString1.length())
    {
      int m = paramString1.charAt(i);
      if ((m >= 48) && (m <= 57))
        j = 1;
      while (true)
      {
        i += 1;
        break;
        if (getAlphanumericCode(m) == -1)
          break label84;
        k = 1;
      }
      label84: return Mode.BYTE;
    }
    if (k != 0)
      return Mode.ALPHANUMERIC;
    if (j != 0)
      return Mode.NUMERIC;
    return Mode.BYTE;
  }

  public static void encode(String paramString, ErrorCorrectionLevel paramErrorCorrectionLevel, QRCode paramQRCode)
    throws WriterException
  {
    encode(paramString, paramErrorCorrectionLevel, null, paramQRCode);
  }

  public static void encode(String paramString, ErrorCorrectionLevel paramErrorCorrectionLevel, Hashtable paramHashtable, QRCode paramQRCode)
    throws WriterException
  {
    BitArray localBitArray;
    if (paramHashtable == null)
    {
      paramHashtable = null;
      Object localObject = paramHashtable;
      if (paramHashtable == null)
        localObject = "ISO-8859-1";
      paramHashtable = chooseMode(paramString, (String)localObject);
      localBitArray = new BitArray();
      appendBytes(paramString, paramHashtable, localBitArray, (String)localObject);
      initQRCode(localBitArray.getSizeInBytes(), paramErrorCorrectionLevel, paramHashtable, paramQRCode);
      paramErrorCorrectionLevel = new BitArray();
      if ((paramHashtable == Mode.BYTE) && (!"ISO-8859-1".equals(localObject)))
      {
        localObject = CharacterSetECI.getCharacterSetECIByName((String)localObject);
        if (localObject != null)
          appendECI((ECI)localObject, paramErrorCorrectionLevel);
      }
      appendModeInfo(paramHashtable, paramErrorCorrectionLevel);
      if (!paramHashtable.equals(Mode.BYTE))
        break label275;
    }
    label275: for (int i = localBitArray.getSizeInBytes(); ; i = paramString.length())
    {
      appendLengthInfo(i, paramQRCode.getVersion(), paramHashtable, paramErrorCorrectionLevel);
      paramErrorCorrectionLevel.appendBitArray(localBitArray);
      terminateBits(paramQRCode.getNumDataBytes(), paramErrorCorrectionLevel);
      paramString = new BitArray();
      interleaveWithECBytes(paramErrorCorrectionLevel, paramQRCode.getNumTotalBytes(), paramQRCode.getNumDataBytes(), paramQRCode.getNumRSBlocks(), paramString);
      paramErrorCorrectionLevel = new ByteMatrix(paramQRCode.getMatrixWidth(), paramQRCode.getMatrixWidth());
      paramQRCode.setMaskPattern(chooseMaskPattern(paramString, paramQRCode.getECLevel(), paramQRCode.getVersion(), paramErrorCorrectionLevel));
      MatrixUtil.buildMatrix(paramString, paramQRCode.getECLevel(), paramQRCode.getVersion(), paramQRCode.getMaskPattern(), paramErrorCorrectionLevel);
      paramQRCode.setMatrix(paramErrorCorrectionLevel);
      if (paramQRCode.isValid())
        return;
      throw new WriterException("Invalid QR code: " + paramQRCode.toString());
      paramHashtable = (String)paramHashtable.get(EncodeHintType.CHARACTER_SET);
      break;
    }
  }

  static byte[] generateECBytes(byte[] paramArrayOfByte, int paramInt)
  {
    int j = 0;
    int k = paramArrayOfByte.length;
    int[] arrayOfInt = new int[k + paramInt];
    int i = 0;
    while (i < k)
    {
      paramArrayOfByte[i] &= 255;
      i += 1;
    }
    new ReedSolomonEncoder(GF256.QR_CODE_FIELD).encode(arrayOfInt, paramInt);
    paramArrayOfByte = new byte[paramInt];
    i = j;
    while (i < paramInt)
    {
      paramArrayOfByte[i] = ((byte)arrayOfInt[(k + i)]);
      i += 1;
    }
    return paramArrayOfByte;
  }

  static int getAlphanumericCode(int paramInt)
  {
    if (paramInt < ALPHANUMERIC_TABLE.length)
      return ALPHANUMERIC_TABLE[paramInt];
    return -1;
  }

  static void getNumDataBytesAndNumECBytesForBlockID(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfInt1, int[] paramArrayOfInt2)
    throws WriterException
  {
    if (paramInt4 >= paramInt3)
      throw new WriterException("Block ID too large");
    int i = paramInt1 % paramInt3;
    int j = paramInt3 - i;
    int k = paramInt1 / paramInt3;
    paramInt2 /= paramInt3;
    int m = paramInt2 + 1;
    int n = k - paramInt2;
    k = k + 1 - m;
    if (n != k)
      throw new WriterException("EC bytes mismatch");
    if (paramInt3 != j + i)
      throw new WriterException("RS blocks mismatch");
    if (paramInt1 != i * (m + k) + (paramInt2 + n) * j)
      throw new WriterException("Total bytes mismatch");
    if (paramInt4 < j)
    {
      paramArrayOfInt1[0] = paramInt2;
      paramArrayOfInt2[0] = n;
      return;
    }
    paramArrayOfInt1[0] = m;
    paramArrayOfInt2[0] = k;
  }

  private static void initQRCode(int paramInt, ErrorCorrectionLevel paramErrorCorrectionLevel, Mode paramMode, QRCode paramQRCode)
    throws WriterException
  {
    paramQRCode.setECLevel(paramErrorCorrectionLevel);
    paramQRCode.setMode(paramMode);
    int i = 1;
    while (i <= 40)
    {
      paramMode = Version.getVersionForNumber(i);
      int j = paramMode.getTotalCodewords();
      Version.ECBlocks localECBlocks = paramMode.getECBlocksForLevel(paramErrorCorrectionLevel);
      int k = localECBlocks.getTotalECCodewords();
      int m = localECBlocks.getNumBlocks();
      int n = j - k;
      if (n >= paramInt + 3)
      {
        paramQRCode.setVersion(i);
        paramQRCode.setNumTotalBytes(j);
        paramQRCode.setNumDataBytes(n);
        paramQRCode.setNumRSBlocks(m);
        paramQRCode.setNumECBytes(k);
        paramQRCode.setMatrixWidth(paramMode.getDimensionForVersion());
        return;
      }
      i += 1;
    }
    throw new WriterException("Cannot find proper rs block info (input data too big?)");
  }

  static void interleaveWithECBytes(BitArray paramBitArray1, int paramInt1, int paramInt2, int paramInt3, BitArray paramBitArray2)
    throws WriterException
  {
    if (paramBitArray1.getSizeInBytes() != paramInt2)
      throw new WriterException("Number of bits and data bytes does not match");
    Vector localVector = new Vector(paramInt3);
    int m = 0;
    int i = 0;
    int j = 0;
    int n;
    for (int k = 0; m < paramInt3; k = n + k)
    {
      int[] arrayOfInt = new int[1];
      Object localObject = new int[1];
      getNumDataBytesAndNumECBytesForBlockID(paramInt1, paramInt2, paramInt3, m, arrayOfInt, (int[])localObject);
      n = arrayOfInt[0];
      byte[] arrayOfByte = new byte[n];
      paramBitArray1.toBytes(k * 8, arrayOfByte, 0, n);
      localObject = generateECBytes(arrayOfByte, localObject[0]);
      localVector.addElement(new BlockPair(arrayOfByte, (byte[])localObject));
      j = Math.max(j, n);
      i = Math.max(i, localObject.length);
      n = arrayOfInt[0];
      m += 1;
    }
    if (paramInt2 != k)
      throw new WriterException("Data bytes does not match offset");
    paramInt2 = 0;
    while (paramInt2 < j)
    {
      paramInt3 = 0;
      while (paramInt3 < localVector.size())
      {
        paramBitArray1 = ((BlockPair)localVector.elementAt(paramInt3)).getDataBytes();
        if (paramInt2 < paramBitArray1.length)
          paramBitArray2.appendBits(paramBitArray1[paramInt2], 8);
        paramInt3 += 1;
      }
      paramInt2 += 1;
    }
    paramInt2 = 0;
    while (paramInt2 < i)
    {
      paramInt3 = 0;
      while (paramInt3 < localVector.size())
      {
        paramBitArray1 = ((BlockPair)localVector.elementAt(paramInt3)).getErrorCorrectionBytes();
        if (paramInt2 < paramBitArray1.length)
          paramBitArray2.appendBits(paramBitArray1[paramInt2], 8);
        paramInt3 += 1;
      }
      paramInt2 += 1;
    }
    if (paramInt1 != paramBitArray2.getSizeInBytes())
      throw new WriterException("Interleaving error: " + paramInt1 + " and " + paramBitArray2.getSizeInBytes() + " differ.");
  }

  private static boolean isOnlyDoubleByteKanji(String paramString)
  {
    try
    {
      paramString = paramString.getBytes("Shift_JIS");
      int j = paramString.length;
      if (j % 2 != 0)
        return false;
      int i = 0;
      while (true)
      {
        if (i >= j)
          break label68;
        int k = paramString[i] & 0xFF;
        if (((k < 129) || (k > 159)) && ((k < 224) || (k > 235)))
          break;
        i += 2;
      }
      label68: return true;
    }
    catch (UnsupportedEncodingException paramString)
    {
    }
    return false;
  }

  static void terminateBits(int paramInt, BitArray paramBitArray)
    throws WriterException
  {
    int k = paramInt << 3;
    if (paramBitArray.getSize() > k)
      throw new WriterException("data bits cannot fit in the QR Code" + paramBitArray.getSize() + " > " + k);
    int i = 0;
    while ((i < 4) && (paramBitArray.getSize() < k))
    {
      paramBitArray.appendBit(false);
      i += 1;
    }
    i = paramBitArray.getSize() & 0x7;
    if (i > 0)
      while (i < 8)
      {
        paramBitArray.appendBit(false);
        i += 1;
      }
    int m = paramBitArray.getSizeInBytes();
    i = 0;
    if (i < paramInt - m)
    {
      if ((i & 0x1) == 0);
      for (int j = 236; ; j = 17)
      {
        paramBitArray.appendBits(j, 8);
        i += 1;
        break;
      }
    }
    if (paramBitArray.getSize() != k)
      throw new WriterException("Bits size does not equal capacity");
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.qrcode.encoder.Encoder
 * JD-Core Version:    0.6.2
 */