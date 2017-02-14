package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.common.BitArray;
import java.util.Hashtable;

public final class UPCAReader extends UPCEANReader
{
  private final UPCEANReader ean13Reader = new EAN13Reader();

  private static Result maybeReturnResult(Result paramResult)
    throws FormatException
  {
    String str = paramResult.getText();
    if (str.charAt(0) == '0')
      return new Result(str.substring(1), null, paramResult.getResultPoints(), BarcodeFormat.UPC_A);
    throw FormatException.getFormatInstance();
  }

  public Result decode(BinaryBitmap paramBinaryBitmap)
    throws NotFoundException, FormatException
  {
    return maybeReturnResult(this.ean13Reader.decode(paramBinaryBitmap));
  }

  public Result decode(BinaryBitmap paramBinaryBitmap, Hashtable paramHashtable)
    throws NotFoundException, FormatException
  {
    return maybeReturnResult(this.ean13Reader.decode(paramBinaryBitmap, paramHashtable));
  }

  protected int decodeMiddle(BitArray paramBitArray, int[] paramArrayOfInt, StringBuffer paramStringBuffer)
    throws NotFoundException
  {
    return this.ean13Reader.decodeMiddle(paramBitArray, paramArrayOfInt, paramStringBuffer);
  }

  public Result decodeRow(int paramInt, BitArray paramBitArray, Hashtable paramHashtable)
    throws NotFoundException, FormatException, ChecksumException
  {
    return maybeReturnResult(this.ean13Reader.decodeRow(paramInt, paramBitArray, paramHashtable));
  }

  public Result decodeRow(int paramInt, BitArray paramBitArray, int[] paramArrayOfInt, Hashtable paramHashtable)
    throws NotFoundException, FormatException, ChecksumException
  {
    return maybeReturnResult(this.ean13Reader.decodeRow(paramInt, paramBitArray, paramArrayOfInt, paramHashtable));
  }

  BarcodeFormat getBarcodeFormat()
  {
    return BarcodeFormat.UPC_A;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.UPCAReader
 * JD-Core Version:    0.6.2
 */