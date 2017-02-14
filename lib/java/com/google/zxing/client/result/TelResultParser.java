package com.google.zxing.client.result;

import com.google.zxing.Result;

final class TelResultParser extends ResultParser
{
  public static TelParsedResult parse(Result paramResult)
  {
    String str = paramResult.getText();
    if ((str == null) || ((!str.startsWith("tel:")) && (!str.startsWith("TEL:"))))
      return null;
    int i;
    if (str.startsWith("TEL:"))
    {
      paramResult = "tel:" + str.substring(4);
      i = str.indexOf('?', 4);
      if (i >= 0)
        break label96;
    }
    label96: for (str = str.substring(4); ; str = str.substring(4, i))
    {
      return new TelParsedResult(str, paramResult, null);
      paramResult = str;
      break;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.TelResultParser
 * JD-Core Version:    0.6.2
 */