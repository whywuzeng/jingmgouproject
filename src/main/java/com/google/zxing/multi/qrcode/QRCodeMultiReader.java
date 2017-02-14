package com.google.zxing.multi.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.NotFoundException;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.multi.MultipleBarcodeReader;
import com.google.zxing.multi.qrcode.detector.MultiDetector;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.decoder.Decoder;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.util.Hashtable;
import java.util.Vector;

public final class QRCodeMultiReader extends QRCodeReader
  implements MultipleBarcodeReader
{
  private static final Result[] EMPTY_RESULT_ARRAY = new Result[0];

  public Result[] decodeMultiple(BinaryBitmap paramBinaryBitmap)
    throws NotFoundException
  {
    return decodeMultiple(paramBinaryBitmap, null);
  }

  public Result[] decodeMultiple(BinaryBitmap paramBinaryBitmap, Hashtable paramHashtable)
    throws NotFoundException
  {
    int j = 0;
    Vector localVector = new Vector();
    paramBinaryBitmap = new MultiDetector(paramBinaryBitmap.getBlackMatrix()).detectMulti(paramHashtable);
    int i = 0;
    while (true)
    {
      if (i < paramBinaryBitmap.length);
      try
      {
        paramHashtable = getDecoder().decode(paramBinaryBitmap[i].getBits());
        Object localObject = paramBinaryBitmap[i].getPoints();
        localObject = new Result(paramHashtable.getText(), paramHashtable.getRawBytes(), (ResultPoint[])localObject, BarcodeFormat.QR_CODE);
        if (paramHashtable.getByteSegments() != null)
          ((Result)localObject).putMetadata(ResultMetadataType.BYTE_SEGMENTS, paramHashtable.getByteSegments());
        if (paramHashtable.getECLevel() != null)
          ((Result)localObject).putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, paramHashtable.getECLevel().toString());
        localVector.addElement(localObject);
        label128: i += 1;
        continue;
        if (localVector.isEmpty())
          return EMPTY_RESULT_ARRAY;
        paramBinaryBitmap = new Result[localVector.size()];
        i = j;
        while (i < localVector.size())
        {
          paramBinaryBitmap[i] = ((Result)localVector.elementAt(i));
          i += 1;
        }
        return paramBinaryBitmap;
      }
      catch (ReaderException paramHashtable)
      {
        break label128;
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.multi.qrcode.QRCodeMultiReader
 * JD-Core Version:    0.6.2
 */