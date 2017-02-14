package com.google.zxing.client.result;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.google.zxing.oned.UPCEReader;

final class ProductResultParser extends ResultParser
{
  public static ProductParsedResult parse(Result paramResult)
  {
    BarcodeFormat localBarcodeFormat = paramResult.getBarcodeFormat();
    if ((!BarcodeFormat.UPC_A.equals(localBarcodeFormat)) && (!BarcodeFormat.UPC_E.equals(localBarcodeFormat)) && (!BarcodeFormat.EAN_8.equals(localBarcodeFormat)) && (!BarcodeFormat.EAN_13.equals(localBarcodeFormat)));
    String str;
    do
    {
      return null;
      str = paramResult.getText();
    }
    while (str == null);
    int j = str.length();
    int i = 0;
    while (true)
    {
      if (i >= j)
        break label102;
      int k = str.charAt(i);
      if ((k < 48) || (k > 57))
        break;
      i += 1;
    }
    label102: if (BarcodeFormat.UPC_E.equals(localBarcodeFormat));
    for (paramResult = UPCEReader.convertUPCEtoUPCA(str); ; paramResult = str)
      return new ProductParsedResult(str, paramResult);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.ProductResultParser
 * JD-Core Version:    0.6.2
 */