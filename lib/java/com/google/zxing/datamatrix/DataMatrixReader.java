package com.google.zxing.datamatrix;

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
import com.google.zxing.datamatrix.decoder.Decoder;
import com.google.zxing.datamatrix.detector.Detector;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.util.Hashtable;

public final class DataMatrixReader
  implements Reader
{
  private static final ResultPoint[] NO_POINTS = new ResultPoint[0];
  private final Decoder decoder = new Decoder();

  private static BitMatrix extractPureBits(BitMatrix paramBitMatrix)
    throws NotFoundException
  {
    int m = paramBitMatrix.getHeight();
    int n = paramBitMatrix.getWidth();
    int j = Math.min(m, n);
    Object localObject = paramBitMatrix.getTopLeftOnBit();
    if (localObject == null)
      throw NotFoundException.getNotFoundInstance();
    int i = localObject[0];
    int i1 = localObject[1];
    while ((i < j) && (i1 < j) && (paramBitMatrix.get(i, i1)))
      i += 1;
    if (i == j)
      throw NotFoundException.getNotFoundInstance();
    int k = i - localObject[0];
    j = n - 1;
    while ((j >= 0) && (!paramBitMatrix.get(j, i1)))
      j -= 1;
    if (j < 0)
      throw NotFoundException.getNotFoundInstance();
    j += 1;
    if ((j - i) % k != 0)
      throw NotFoundException.getNotFoundInstance();
    int i2 = (j - i) / k + 2;
    int i3 = i - (k >> 1);
    i1 = i1 + k - (k >> 1);
    if (((i2 - 1) * k + i3 >= n) || ((i2 - 1) * k + i1 >= m))
      throw NotFoundException.getNotFoundInstance();
    localObject = new BitMatrix(i2);
    i = 0;
    while (i < i2)
    {
      j = 0;
      while (j < i2)
      {
        if (paramBitMatrix.get(j * k + i3, i1 + i * k))
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
      paramBinaryBitmap = this.decoder.decode(paramBinaryBitmap);
    }
    for (paramHashtable = NO_POINTS; ; paramHashtable = paramHashtable.getPoints())
    {
      paramHashtable = new Result(paramBinaryBitmap.getText(), paramBinaryBitmap.getRawBytes(), paramHashtable, BarcodeFormat.DATA_MATRIX);
      if (paramBinaryBitmap.getByteSegments() != null)
        paramHashtable.putMetadata(ResultMetadataType.BYTE_SEGMENTS, paramBinaryBitmap.getByteSegments());
      if (paramBinaryBitmap.getECLevel() != null)
        paramHashtable.putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, paramBinaryBitmap.getECLevel().toString());
      return paramHashtable;
      paramHashtable = new Detector(paramBinaryBitmap.getBlackMatrix()).detect();
      paramBinaryBitmap = this.decoder.decode(paramHashtable.getBits());
    }
  }

  public void reset()
  {
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.datamatrix.DataMatrixReader
 * JD-Core Version:    0.6.2
 */