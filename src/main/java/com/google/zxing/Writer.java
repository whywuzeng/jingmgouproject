package com.google.zxing;

import com.google.zxing.common.BitMatrix;
import java.util.Hashtable;

public abstract interface Writer
{
  public abstract BitMatrix encode(String paramString, BarcodeFormat paramBarcodeFormat, int paramInt1, int paramInt2)
    throws WriterException;

  public abstract BitMatrix encode(String paramString, BarcodeFormat paramBarcodeFormat, int paramInt1, int paramInt2, Hashtable paramHashtable)
    throws WriterException;
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.Writer
 * JD-Core Version:    0.6.2
 */