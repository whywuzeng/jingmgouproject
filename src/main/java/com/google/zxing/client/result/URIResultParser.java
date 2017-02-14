package com.google.zxing.client.result;

import com.google.zxing.Result;

final class URIResultParser extends ResultParser
{
  static boolean isBasicallyValidURI(String paramString)
  {
    if ((paramString == null) || (paramString.indexOf(' ') >= 0) || (paramString.indexOf('\n') >= 0));
    int j;
    int k;
    do
    {
      do
      {
        do
        {
          return false;
          i = paramString.indexOf('.');
        }
        while (i >= paramString.length() - 2);
        j = paramString.indexOf(':');
      }
      while ((i < 0) && (j < 0));
      if (j < 0)
        break;
      if ((i < 0) || (i > j))
      {
        i = 0;
        while (true)
        {
          if (i >= j)
            break label159;
          k = paramString.charAt(i);
          if (((k < 97) || (k > 122)) && ((k < 65) || (k > 90)))
            break;
          i += 1;
        }
      }
    }
    while (j >= paramString.length() - 2);
    int i = j + 1;
    while (true)
    {
      if (i >= j + 3)
        break label159;
      k = paramString.charAt(i);
      if ((k < 48) || (k > 57))
        break;
      i += 1;
    }
    label159: return true;
  }

  public static URIParsedResult parse(Result paramResult)
  {
    String str = paramResult.getText();
    paramResult = str;
    if (str != null)
    {
      paramResult = str;
      if (str.startsWith("URL:"))
        paramResult = str.substring(4);
    }
    if (!isBasicallyValidURI(paramResult))
      return null;
    return new URIParsedResult(paramResult, null);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.URIResultParser
 * JD-Core Version:    0.6.2
 */