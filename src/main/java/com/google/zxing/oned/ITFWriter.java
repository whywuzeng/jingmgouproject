package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Hashtable;

public final class ITFWriter extends UPCEANWriter
{
  public BitMatrix encode(String paramString, BarcodeFormat paramBarcodeFormat, int paramInt1, int paramInt2, Hashtable paramHashtable)
    throws WriterException
  {
    if (paramBarcodeFormat != BarcodeFormat.ITF)
      throw new IllegalArgumentException("Can only encode ITF, but got " + paramBarcodeFormat);
    return super.encode(paramString, paramBarcodeFormat, paramInt1, paramInt2, paramHashtable);
  }

  public byte[] encode(String paramString)
  {
    int m = paramString.length();
    if (m > 80)
      throw new IllegalArgumentException("Requested contents should be less than 80 digits long, but got " + m);
    byte[] arrayOfByte = new byte[m * 9 + 9];
    int j = appendPattern(arrayOfByte, 0, new int[] { 1, 1, 1, 1 }, 1);
    int i = 0;
    while (i < m)
    {
      int n = Character.digit(paramString.charAt(i), 10);
      int i1 = Character.digit(paramString.charAt(i + 1), 10);
      int[] arrayOfInt = new int[18];
      int k = 0;
      while (k < 5)
      {
        arrayOfInt[(k << 1)] = ITFReader.PATTERNS[n][k];
        arrayOfInt[((k << 1) + 1)] = ITFReader.PATTERNS[i1][k];
        k += 1;
      }
      j += appendPattern(arrayOfByte, j, arrayOfInt, 1);
      i += 2;
    }
    appendPattern(arrayOfByte, j, new int[] { 3, 1, 1 }, 1);
    return arrayOfByte;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.ITFWriter
 * JD-Core Version:    0.6.2
 */