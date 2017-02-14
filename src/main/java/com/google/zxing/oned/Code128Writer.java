package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Hashtable;

public final class Code128Writer extends UPCEANWriter
{
  public BitMatrix encode(String paramString, BarcodeFormat paramBarcodeFormat, int paramInt1, int paramInt2, Hashtable paramHashtable)
    throws WriterException
  {
    if (paramBarcodeFormat != BarcodeFormat.CODE_128)
      throw new IllegalArgumentException("Can only encode CODE_128, but got " + paramBarcodeFormat);
    return super.encode(paramString, paramBarcodeFormat, paramInt1, paramInt2, paramHashtable);
  }

  public byte[] encode(String paramString)
  {
    int m = 0;
    int n = paramString.length();
    if (n > 80)
      throw new IllegalArgumentException("Requested contents should be less than 80 digits long, but got " + n);
    int i = 35;
    int j = 0;
    while (j < n)
    {
      localObject = Code128Reader.CODE_PATTERNS[(paramString.charAt(j) - ' ')];
      k = 0;
      while (k < localObject.length)
      {
        i += localObject[k];
        k += 1;
      }
      j += 1;
    }
    Object localObject = new byte[i];
    j = appendPattern((byte[])localObject, 0, Code128Reader.CODE_PATTERNS[104], 1);
    int k = 104;
    i = m;
    while (i < n)
    {
      k += (paramString.charAt(i) - ' ') * (i + 1);
      j += appendPattern((byte[])localObject, j, Code128Reader.CODE_PATTERNS[(paramString.charAt(i) - ' ')], 1);
      i += 1;
    }
    i = appendPattern((byte[])localObject, j, Code128Reader.CODE_PATTERNS[(k % 103)], 1) + j;
    appendPattern((byte[])localObject, i, Code128Reader.CODE_PATTERNS[106], 1);
    return localObject;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.Code128Writer
 * JD-Core Version:    0.6.2
 */