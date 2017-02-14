package com.google.zxing;

import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.oned.Code39Writer;
import com.google.zxing.oned.EAN13Writer;
import com.google.zxing.oned.EAN8Writer;
import com.google.zxing.oned.ITFWriter;
import com.google.zxing.qrcode.QRCodeWriter;
import java.util.Hashtable;

public final class MultiFormatWriter
  implements Writer
{
  public BitMatrix encode(String paramString, BarcodeFormat paramBarcodeFormat, int paramInt1, int paramInt2)
    throws WriterException
  {
    return encode(paramString, paramBarcodeFormat, paramInt1, paramInt2, null);
  }

  public BitMatrix encode(String paramString, BarcodeFormat paramBarcodeFormat, int paramInt1, int paramInt2, Hashtable paramHashtable)
    throws WriterException
  {
    Object localObject;
    if (paramBarcodeFormat == BarcodeFormat.EAN_8)
      localObject = new EAN8Writer();
    while (true)
    {
      return ((Writer)localObject).encode(paramString, paramBarcodeFormat, paramInt1, paramInt2, paramHashtable);
      if (paramBarcodeFormat == BarcodeFormat.EAN_13)
      {
        localObject = new EAN13Writer();
      }
      else if (paramBarcodeFormat == BarcodeFormat.QR_CODE)
      {
        localObject = new QRCodeWriter();
      }
      else if (paramBarcodeFormat == BarcodeFormat.CODE_39)
      {
        localObject = new Code39Writer();
      }
      else if (paramBarcodeFormat == BarcodeFormat.CODE_128)
      {
        localObject = new Code128Writer();
      }
      else
      {
        if (paramBarcodeFormat != BarcodeFormat.ITF)
          break;
        localObject = new ITFWriter();
      }
    }
    throw new IllegalArgumentException("No encoder available for format " + paramBarcodeFormat);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.MultiFormatWriter
 * JD-Core Version:    0.6.2
 */