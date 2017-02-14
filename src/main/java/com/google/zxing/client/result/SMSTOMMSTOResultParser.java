package com.google.zxing.client.result;

import com.google.zxing.Result;

final class SMSTOMMSTOResultParser extends ResultParser
{
  public static SMSParsedResult parse(Result paramResult)
  {
    paramResult = paramResult.getText();
    if (paramResult == null);
    while ((!paramResult.startsWith("smsto:")) && (!paramResult.startsWith("SMSTO:")) && (!paramResult.startsWith("mmsto:")) && (!paramResult.startsWith("MMSTO:")))
      return null;
    String str = paramResult.substring(6);
    int i = str.indexOf(':');
    if (i >= 0)
    {
      paramResult = str.substring(i + 1);
      str = str.substring(0, i);
    }
    while (true)
    {
      return new SMSParsedResult(str, null, null, paramResult);
      paramResult = null;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.SMSTOMMSTOResultParser
 * JD-Core Version:    0.6.2
 */