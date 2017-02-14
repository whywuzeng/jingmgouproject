package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Hashtable;

public abstract class UPCEANWriter
  implements Writer
{
  protected static int appendPattern(byte[] paramArrayOfByte, int paramInt1, int[] paramArrayOfInt, int paramInt2)
  {
    if ((paramInt2 != 0) && (paramInt2 != 1))
      throw new IllegalArgumentException("startColor must be either 0 or 1, but got: " + paramInt2);
    int i = (byte)paramInt2;
    paramInt2 = 0;
    int k = 0;
    int j = paramInt1;
    paramInt1 = k;
    while (paramInt1 < paramArrayOfInt.length)
    {
      k = 0;
      while (k < paramArrayOfInt[paramInt1])
      {
        paramArrayOfByte[j] = i;
        j += 1;
        k += 1;
        paramInt2 += 1;
      }
      i = (byte)(i ^ 0x1);
      paramInt1 += 1;
    }
    return paramInt2;
  }

  private static BitMatrix renderResult(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    int i = paramArrayOfByte.length;
    int k = (UPCEANReader.START_END_PATTERN.length << 1) + i;
    int m = Math.max(paramInt1, k);
    int j = Math.max(1, paramInt2);
    k = m / k;
    paramInt1 = (m - i * k) / 2;
    BitMatrix localBitMatrix = new BitMatrix(m, j);
    paramInt2 = 0;
    while (paramInt2 < i)
    {
      if (paramArrayOfByte[paramInt2] == 1)
        localBitMatrix.setRegion(paramInt1, 0, k, j);
      paramInt2 += 1;
      paramInt1 += k;
    }
    return localBitMatrix;
  }

  public BitMatrix encode(String paramString, BarcodeFormat paramBarcodeFormat, int paramInt1, int paramInt2)
    throws WriterException
  {
    return encode(paramString, paramBarcodeFormat, paramInt1, paramInt2, null);
  }

  public BitMatrix encode(String paramString, BarcodeFormat paramBarcodeFormat, int paramInt1, int paramInt2, Hashtable paramHashtable)
    throws WriterException
  {
    if ((paramString == null) || (paramString.length() == 0))
      throw new IllegalArgumentException("Found empty contents");
    if ((paramInt1 < 0) || (paramInt2 < 0))
      throw new IllegalArgumentException("Requested dimensions are too small: " + paramInt1 + 'x' + paramInt2);
    return renderResult(encode(paramString), paramInt1, paramInt2);
  }

  public abstract byte[] encode(String paramString);
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.UPCEANWriter
 * JD-Core Version:    0.6.2
 */