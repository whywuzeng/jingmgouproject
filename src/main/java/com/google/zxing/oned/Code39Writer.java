package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Hashtable;

public final class Code39Writer extends UPCEANWriter
{
  private static void toIntArray(int paramInt, int[] paramArrayOfInt)
  {
    int i = 0;
    if (i < 9)
    {
      if ((1 << i & paramInt) == 0);
      for (int j = 1; ; j = 2)
      {
        paramArrayOfInt[i] = j;
        i += 1;
        break;
      }
    }
  }

  public BitMatrix encode(String paramString, BarcodeFormat paramBarcodeFormat, int paramInt1, int paramInt2, Hashtable paramHashtable)
    throws WriterException
  {
    if (paramBarcodeFormat != BarcodeFormat.CODE_39)
      throw new IllegalArgumentException("Can only encode CODE_39, but got " + paramBarcodeFormat);
    return super.encode(paramString, paramBarcodeFormat, paramInt1, paramInt2, paramHashtable);
  }

  public byte[] encode(String paramString)
  {
    int m = paramString.length();
    if (m > 80)
      throw new IllegalArgumentException("Requested contents should be less than 80 digits long, but got " + m);
    int[] arrayOfInt1 = new int[9];
    int i = m + 25;
    int j = 0;
    int k;
    while (j < m)
    {
      k = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%".indexOf(paramString.charAt(j));
      toIntArray(Code39Reader.CHARACTER_ENCODINGS[k], arrayOfInt1);
      k = 0;
      while (k < arrayOfInt1.length)
      {
        i += arrayOfInt1[k];
        k += 1;
      }
      j += 1;
    }
    byte[] arrayOfByte = new byte[i];
    toIntArray(Code39Reader.CHARACTER_ENCODINGS[39], arrayOfInt1);
    i = appendPattern(arrayOfByte, 0, arrayOfInt1, 1);
    int[] arrayOfInt2 = new int[1];
    arrayOfInt2[0] = 1;
    j = appendPattern(arrayOfByte, i, arrayOfInt2, 0) + i;
    i = m - 1;
    while (i >= 0)
    {
      k = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%".indexOf(paramString.charAt(i));
      toIntArray(Code39Reader.CHARACTER_ENCODINGS[k], arrayOfInt1);
      j += appendPattern(arrayOfByte, j, arrayOfInt1, 1);
      j += appendPattern(arrayOfByte, j, arrayOfInt2, 0);
      i -= 1;
    }
    toIntArray(Code39Reader.CHARACTER_ENCODINGS[39], arrayOfInt1);
    appendPattern(arrayOfByte, j, arrayOfInt1, 1);
    return arrayOfByte;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.Code39Writer
 * JD-Core Version:    0.6.2
 */