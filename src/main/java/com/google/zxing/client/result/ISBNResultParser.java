package com.google.zxing.client.result;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

public class ISBNResultParser extends ResultParser
{
  public static ISBNParsedResult parse(Result paramResult)
  {
    BarcodeFormat localBarcodeFormat = paramResult.getBarcodeFormat();
    if (!BarcodeFormat.EAN_13.equals(localBarcodeFormat));
    do
    {
      return null;
      paramResult = paramResult.getText();
    }
    while ((paramResult == null) || (paramResult.length() != 13) || ((!paramResult.startsWith("978")) && (!paramResult.startsWith("979"))));
    return new ISBNParsedResult(paramResult);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.ISBNResultParser
 * JD-Core Version:    0.6.2
 */