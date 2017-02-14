package com.google.zxing.client.result;

import com.google.zxing.Result;

final class BookmarkDoCoMoResultParser extends AbstractDoCoMoResultParser
{
  public static URIParsedResult parse(Result paramResult)
  {
    Object localObject = paramResult.getText();
    if ((localObject == null) || (!((String)localObject).startsWith("MEBKM:")));
    do
    {
      do
      {
        return null;
        paramResult = matchSingleDoCoMoPrefixedField("TITLE:", (String)localObject, true);
        localObject = matchDoCoMoPrefixedField("URL:", (String)localObject, true);
      }
      while (localObject == null);
      localObject = localObject[0];
    }
    while (!URIResultParser.isBasicallyValidURI((String)localObject));
    return new URIParsedResult((String)localObject, paramResult);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.BookmarkDoCoMoResultParser
 * JD-Core Version:    0.6.2
 */