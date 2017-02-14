package com.google.zxing.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import com.google.zxing.qrcode.encoder.Encoder;
import com.google.zxing.qrcode.encoder.QRCode;
import java.util.Hashtable;

public final class QRCodeWriter
  implements Writer
{
  private static final int QUIET_ZONE_SIZE = 4;

  private static BitMatrix renderResult(QRCode paramQRCode, int paramInt1, int paramInt2)
  {
    paramQRCode = paramQRCode.getMatrix();
    int m = paramQRCode.getWidth();
    int n = paramQRCode.getHeight();
    int j = m + 8;
    int k = n + 8;
    paramInt1 = Math.max(paramInt1, j);
    int i = Math.max(paramInt2, k);
    int i1 = Math.min(paramInt1 / j, i / k);
    k = (paramInt1 - m * i1) / 2;
    paramInt2 = (i - n * i1) / 2;
    BitMatrix localBitMatrix = new BitMatrix(paramInt1, i);
    paramInt1 = 0;
    while (paramInt1 < n)
    {
      i = k;
      j = 0;
      while (j < m)
      {
        if (paramQRCode.get(j, paramInt1) == 1)
          localBitMatrix.setRegion(i, paramInt2, i1, i1);
        j += 1;
        i += i1;
      }
      paramInt2 += i1;
      paramInt1 += 1;
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
    if (paramBarcodeFormat != BarcodeFormat.QR_CODE)
      throw new IllegalArgumentException("Can only encode QR_CODE, but got " + paramBarcodeFormat);
    if ((paramInt1 < 0) || (paramInt2 < 0))
      throw new IllegalArgumentException("Requested dimensions are too small: " + paramInt1 + 'x' + paramInt2);
    paramBarcodeFormat = ErrorCorrectionLevel.L;
    Object localObject;
    if (paramHashtable != null)
    {
      localObject = (ErrorCorrectionLevel)paramHashtable.get(EncodeHintType.ERROR_CORRECTION);
      if (localObject != null)
        paramBarcodeFormat = (BarcodeFormat)localObject;
    }
    while (true)
    {
      localObject = new QRCode();
      Encoder.encode(paramString, paramBarcodeFormat, paramHashtable, (QRCode)localObject);
      return renderResult((QRCode)localObject, paramInt1, paramInt2);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.qrcode.QRCodeWriter
 * JD-Core Version:    0.6.2
 */