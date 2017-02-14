package com.google.zxing.multi;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import java.util.Hashtable;

public abstract interface MultipleBarcodeReader
{
  public abstract Result[] decodeMultiple(BinaryBitmap paramBinaryBitmap)
    throws NotFoundException;

  public abstract Result[] decodeMultiple(BinaryBitmap paramBinaryBitmap, Hashtable paramHashtable)
    throws NotFoundException;
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.multi.MultipleBarcodeReader
 * JD-Core Version:    0.6.2
 */