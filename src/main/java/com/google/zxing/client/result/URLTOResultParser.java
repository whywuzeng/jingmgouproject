package com.google.zxing.client.result;

import com.google.zxing.Result;

final class URLTOResultParser
{
  public static URIParsedResult parse(Result paramResult)
  {
    Object localObject = null;
    String str = paramResult.getText();
    if ((str == null) || ((!str.startsWith("urlto:")) && (!str.startsWith("URLTO:"))));
    int i;
    do
    {
      return null;
      i = str.indexOf(':', 6);
    }
    while (i < 0);
    if (i <= 6);
    for (paramResult = localObject; ; paramResult = str.substring(6, i))
      return new URIParsedResult(str.substring(i + 1), paramResult);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.URLTOResultParser
 * JD-Core Version:    0.6.2
 */