package com.google.zxing.qrcode.decoder;

final class FormatInformation
{
  private static final int[] BITS_SET_IN_HALF_BYTE = { 0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4 };
  private static final int[][] FORMAT_INFO_DECODE_LOOKUP;
  private static final int FORMAT_INFO_MASK_QR = 21522;
  private final byte dataMask;
  private final ErrorCorrectionLevel errorCorrectionLevel;

  static
  {
    int[] arrayOfInt1 = { 24188, 2 };
    int[] arrayOfInt2 = { 17913, 4 };
    int[] arrayOfInt3 = { 16590, 5 };
    int[] arrayOfInt4 = { 20375, 6 };
    int[] arrayOfInt5 = { 30877, 11 };
    int[] arrayOfInt6 = { 27713, 14 };
    int[] arrayOfInt7 = { 26998, 15 };
    int[] arrayOfInt8 = { 13663, 24 };
    int[] arrayOfInt9 = { 12392, 25 };
    int[] arrayOfInt10 = { 14854, 27 };
    int[] arrayOfInt11 = { 9396, 28 };
    int[] arrayOfInt12 = { 8579, 29 };
    int[] arrayOfInt13 = { 11994, 30 };
    int[] arrayOfInt14 = { 11245, 31 };
    FORMAT_INFO_DECODE_LOOKUP = new int[][] { { 21522, 0 }, { 20773, 1 }, arrayOfInt1, { 23371, 3 }, arrayOfInt2, arrayOfInt3, arrayOfInt4, { 19104, 7 }, { 30660, 8 }, { 29427, 9 }, { 32170, 10 }, arrayOfInt5, { 26159, 12 }, { 25368, 13 }, arrayOfInt6, arrayOfInt7, { 5769, 16 }, { 5054, 17 }, { 7399, 18 }, { 6608, 19 }, { 1890, 20 }, { 597, 21 }, { 3340, 22 }, { 2107, 23 }, arrayOfInt8, arrayOfInt9, { 16177, 26 }, arrayOfInt10, arrayOfInt11, arrayOfInt12, arrayOfInt13, arrayOfInt14 };
  }

  private FormatInformation(int paramInt)
  {
    this.errorCorrectionLevel = ErrorCorrectionLevel.forBits(paramInt >> 3 & 0x3);
    this.dataMask = ((byte)(paramInt & 0x7));
  }

  static FormatInformation decodeFormatInformation(int paramInt1, int paramInt2)
  {
    FormatInformation localFormatInformation = doDecodeFormatInformation(paramInt1, paramInt2);
    if (localFormatInformation != null)
      return localFormatInformation;
    return doDecodeFormatInformation(paramInt1 ^ 0x5412, paramInt2 ^ 0x5412);
  }

  private static FormatInformation doDecodeFormatInformation(int paramInt1, int paramInt2)
  {
    int i = 0;
    int j = 2147483647;
    int k = 0;
    int[] arrayOfInt;
    int n;
    int m;
    if (k < FORMAT_INFO_DECODE_LOOKUP.length)
    {
      arrayOfInt = FORMAT_INFO_DECODE_LOOKUP[k];
      n = arrayOfInt[0];
      if ((n == paramInt1) || (n == paramInt2))
        return new FormatInformation(arrayOfInt[1]);
      m = numBitsDiffering(paramInt1, n);
      if (m >= j)
        break label132;
      i = arrayOfInt[1];
      j = m;
    }
    label132: 
    while (true)
    {
      if (paramInt1 != paramInt2)
      {
        m = numBitsDiffering(paramInt2, n);
        if (m < j)
        {
          i = arrayOfInt[1];
          j = m;
        }
      }
      while (true)
      {
        k += 1;
        break;
        if (j <= 3)
          return new FormatInformation(i);
        return null;
      }
    }
  }

  static int numBitsDiffering(int paramInt1, int paramInt2)
  {
    paramInt1 ^= paramInt2;
    paramInt2 = BITS_SET_IN_HALF_BYTE[(paramInt1 & 0xF)];
    int i = BITS_SET_IN_HALF_BYTE[(paramInt1 >>> 4 & 0xF)];
    int j = BITS_SET_IN_HALF_BYTE[(paramInt1 >>> 8 & 0xF)];
    int k = BITS_SET_IN_HALF_BYTE[(paramInt1 >>> 12 & 0xF)];
    int m = BITS_SET_IN_HALF_BYTE[(paramInt1 >>> 16 & 0xF)];
    int n = BITS_SET_IN_HALF_BYTE[(paramInt1 >>> 20 & 0xF)];
    int i1 = BITS_SET_IN_HALF_BYTE[(paramInt1 >>> 24 & 0xF)];
    return BITS_SET_IN_HALF_BYTE[(paramInt1 >>> 28 & 0xF)] + (paramInt2 + i + j + k + m + n + i1);
  }

  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof FormatInformation));
    do
    {
      return false;
      paramObject = (FormatInformation)paramObject;
    }
    while ((this.errorCorrectionLevel != paramObject.errorCorrectionLevel) || (this.dataMask != paramObject.dataMask));
    return true;
  }

  byte getDataMask()
  {
    return this.dataMask;
  }

  ErrorCorrectionLevel getErrorCorrectionLevel()
  {
    return this.errorCorrectionLevel;
  }

  public int hashCode()
  {
    return this.errorCorrectionLevel.ordinal() << 3 | this.dataMask;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.qrcode.decoder.FormatInformation
 * JD-Core Version:    0.6.2
 */