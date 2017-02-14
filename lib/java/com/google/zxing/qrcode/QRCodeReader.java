package com.google.zxing.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.qrcode.decoder.Decoder;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.detector.Detector;
import java.util.Hashtable;

public class QRCodeReader
  implements Reader
{
  private static final ResultPoint[] NO_POINTS = new ResultPoint[0];
  private final Decoder decoder = new Decoder();

  public static BitMatrix extractPureBits(BitMatrix paramBitMatrix)
    throws NotFoundException
  {
    int m = 1;
    int n = paramBitMatrix.getHeight();
    int i1 = paramBitMatrix.getWidth();
    int k = Math.min(n, i1);
    Object localObject = paramBitMatrix.getTopLeftOnBit();
    if (localObject == null)
      throw NotFoundException.getNotFoundInstance();
    int j = localObject[0];
    int i = localObject[1];
    while ((j < k) && (i < k) && (paramBitMatrix.get(j, i)))
    {
      j += 1;
      i += 1;
    }
    if ((j == k) || (i == k))
      throw NotFoundException.getNotFoundInstance();
    int i2 = j - localObject[0];
    if (i2 == 0)
      throw NotFoundException.getNotFoundInstance();
    k = i1 - 1;
    while ((k > j) && (!paramBitMatrix.get(k, i)))
      k -= 1;
    if (k <= j)
      throw NotFoundException.getNotFoundInstance();
    k += 1;
    if ((k - j) % i2 != 0)
      throw NotFoundException.getNotFoundInstance();
    int i3 = (k - j) / i2 + 1;
    if (i2 == 1);
    for (k = m; ; k = i2 >> 1)
    {
      m = j - k;
      k = i - k;
      if (((i3 - 1) * i2 + m < i1) && ((i3 - 1) * i2 + k < n))
        break;
      throw NotFoundException.getNotFoundInstance();
    }
    localObject = new BitMatrix(i3);
    i = 0;
    while (i < i3)
    {
      j = 0;
      while (j < i3)
      {
        if (paramBitMatrix.get(j * i2 + m, k + i * i2))
          ((BitMatrix)localObject).set(j, i);
        j += 1;
      }
      i += 1;
    }
    return localObject;
  }

  public Result decode(BinaryBitmap paramBinaryBitmap)
    throws NotFoundException, ChecksumException, FormatException
  {
    return decode(paramBinaryBitmap, null);
  }

  public Result decode(BinaryBitmap paramBinaryBitmap, Hashtable paramHashtable)
    throws NotFoundException, ChecksumException, FormatException
  {
    if ((paramHashtable != null) && (paramHashtable.containsKey(DecodeHintType.PURE_BARCODE)))
    {
      paramBinaryBitmap = extractPureBits(paramBinaryBitmap.getBlackMatrix());
      paramBinaryBitmap = this.decoder.decode(paramBinaryBitmap, paramHashtable);
    }
    DetectorResult localDetectorResult;
    for (paramHashtable = NO_POINTS; ; paramHashtable = localDetectorResult.getPoints())
    {
      paramHashtable = new Result(paramBinaryBitmap.getText(), paramBinaryBitmap.getRawBytes(), paramHashtable, BarcodeFormat.QR_CODE);
      if (paramBinaryBitmap.getByteSegments() != null)
        paramHashtable.putMetadata(ResultMetadataType.BYTE_SEGMENTS, paramBinaryBitmap.getByteSegments());
      if (paramBinaryBitmap.getECLevel() != null)
        paramHashtable.putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, paramBinaryBitmap.getECLevel().toString());
      return paramHashtable;
      localDetectorResult = new Detector(paramBinaryBitmap.getBlackMatrix()).detect(paramHashtable);
      paramBinaryBitmap = this.decoder.decode(localDetectorResult.getBits(), paramHashtable);
    }
  }

  protected Decoder getDecoder()
  {
    return this.decoder;
  }

  public void reset()
  {
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.qrcode.QRCodeReader
 * JD-Core Version:    0.6.2
 */