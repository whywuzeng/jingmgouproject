package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Hashtable;

public final class EAN8Writer extends UPCEANWriter
{
  private static final int codeWidth = 67;

  public BitMatrix encode(String paramString, BarcodeFormat paramBarcodeFormat, int paramInt1, int paramInt2, Hashtable paramHashtable)
    throws WriterException
  {
    if (paramBarcodeFormat != BarcodeFormat.EAN_8)
      throw new IllegalArgumentException("Can only encode EAN_8, but got " + paramBarcodeFormat);
    return super.encode(paramString, paramBarcodeFormat, paramInt1, paramInt2, paramHashtable);
  }

  public byte[] encode(String paramString)
  {
    if (paramString.length() != 8)
      throw new IllegalArgumentException("Requested contents should be 8 digits long, but got " + paramString.length());
    byte[] arrayOfByte = new byte[67];
    int j = appendPattern(arrayOfByte, 0, UPCEANReader.START_END_PATTERN, 1) + 0;
    int i = 0;
    int k;
    while (i <= 3)
    {
      k = Integer.parseInt(paramString.substring(i, i + 1));
      j += appendPattern(arrayOfByte, j, UPCEANReader.L_PATTERNS[k], 0);
      i += 1;
    }
    j += appendPattern(arrayOfByte, j, UPCEANReader.MIDDLE_PATTERN, 0);
    i = 4;
    while (i <= 7)
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
 * Qualified Name:     com.google.zxing.oned.EAN8Writer
 * JD-Core Version:    0.6.2
 */