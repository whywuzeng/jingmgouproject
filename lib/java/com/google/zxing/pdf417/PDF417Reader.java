package com.google.zxing.pdf417;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.pdf417.decoder.Decoder;
import com.google.zxing.pdf417.detector.Detector;
import com.google.zxing.qrcode.QRCodeReader;
import java.util.Hashtable;

public final class PDF417Reader
  implements Reader
{
  private static final ResultPoint[] NO_POINTS = new ResultPoint[0];
  private final Decoder decoder = new Decoder();

  public Result decode(BinaryBitmap paramBinaryBitmap)
    throws NotFoundException, FormatException
  {
    return decode(paramBinaryBitmap, null);
  }

  public Result decode(BinaryBitmap paramBinaryBitmap, Hashtable paramHashtable)
    throws NotFoundException, FormatException
  {
    if ((paramHashtable != null) && (paramHashtable.containsKey(DecodeHintType.PURE_BARCODE)))
    {
      paramBinaryBitmap = QRCodeReader.extractPureBits(paramBinaryBitmap.getBlackMatrix());
      paramHashtable = this.decoder.decode(paramBinaryBitmap);
    }
    for (paramBinaryBitmap = NO_POINTS; ; paramBinaryBitmap = paramBinaryBitmap.getPoints())
    {
      return new Result(paramHashtable.getText(), paramHashtable.getRawBytes(), paramBinaryBitmap, BarcodeFormat.PDF417);
      paramBinaryBitmap = new Detector(paramBinaryBitmap).detect();
      paramHashtable = this.decoder.decode(paramBinaryBitmap.getBits());
    }
  }

  public void reset()
  {
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.pdf417.PDF417Reader
 * JD-Core Version:    0.6.2
 */