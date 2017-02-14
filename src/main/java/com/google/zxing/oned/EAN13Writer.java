package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Hashtable;

public final class EAN13Writer extends UPCEANWriter
{
  private static final int codeWidth = 95;

  public BitMatrix encode(String paramString, BarcodeFormat paramBarcodeFormat, int paramInt1, int paramInt2, Hashtable paramHashtable)
    throws WriterException
  {
    if (paramBarcodeFormat != BarcodeFormat.EAN_13)
      throw new IllegalArgumentException("Can only encode EAN_13, but got " + paramBarcodeFormat);
    return super.encode(paramString, paramBarcodeFormat, paramInt1, paramInt2, paramHashtable);
  }

  public byte[] encode(String paramString)
  {
    if (paramString.length() != 13)
      throw new IllegalArgumentException("Requested contents should be 13 digits long, but got " + paramString.length());
    int i = Integer.parseInt(paramString.substring(0, 1));
    int n = EAN13Reader.FIRST_DIGIT_ENCODINGS[i];
    byte[] arrayOfByte = new byte[95];
    int j = appendPattern(arrayOfByte, 0, UPCEANReader.START_END_PATTERN, 1);
    i = 1;
    j += 0;
    int k;
    while (i <= 6)
    {
      int m = Integer.parseInt(paramString.substring(i, i + 1));
      k = m;
      if ((n >> 6 - i & 0x1) == 1)
        k = m + 10;
      j += appendPattern(arrayOfByte, j, UPCEANReader.L_AND_G_PATTERNS[k], 0);
      i += 1;
    }
    j += appendPattern(arrayOfByte, j, UPCEANReader.MIDDLE_PATTERN, 0);
    i = 7;
    while (i <= 12)
    {
      k = Integer.parseInt(paramString.substring(i, i + 1));
      j += appendPattern(arrayOfByte, j, UPCEANReader.L_PATTERNS[k], 1);
      i += 1;
    }
    appendPattern(arrayOfByte, j, UPCEANReader.START_END_PATTERN, 1);
    return arrayOfByte;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.EAN13Writer
 * JD-Core Version:    0.6.2
 */