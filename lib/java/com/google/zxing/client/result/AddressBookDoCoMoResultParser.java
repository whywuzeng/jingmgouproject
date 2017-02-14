package com.google.zxing.client.result;

import com.google.zxing.Result;

final class AddressBookDoCoMoResultParser extends AbstractDoCoMoResultParser
{
  public static AddressBookParsedResult parse(Result paramResult)
  {
    String str2 = paramResult.getText();
    if ((str2 == null) || (!str2.startsWith("MECARD:")));
    do
    {
      return null;
      paramResult = matchDoCoMoPrefixedField("N:", str2, true);
    }
    while (paramResult == null);
    String str3 = parseName(paramResult[0]);
    String str4 = matchSingleDoCoMoPrefixedField("SOUND:", str2, true);
    String[] arrayOfString1 = matchDoCoMoPrefixedField("TEL:", str2, true);
    String[] arrayOfString2 = matchDoCoMoPrefixedField("EMAIL:", str2, true);
    String str5 = matchSingleDoCoMoPrefixedField("NOTE:", str2, false);
    String[] arrayOfString3 = matchDoCoMoPrefixedField("ADR:", str2, true);
    String str1 = matchSingleDoCoMoPrefixedField("BDAY:", str2, true);
    paramResult = str1;
    if (str1 != null)
    {
      paramResult = str1;
      if (!isStringOfDigits(str1, 8))
        paramResult = null;
    }
    str1 = matchSingleDoCoMoPrefixedField("URL:", str2, true);
    str2 = matchSingleDoCoMoPrefixedField("ORG:", str2, true);
    return new AddressBookParsedResult(maybeWrap(str3), str4, arrayOfString1, arrayOfString2, str5, arrayOfString3, str2, paramResult, null, str1);
  }

  private static String parseName(String paramString)
  {
    int i = paramString.indexOf(',');
    String str = paramString;
    if (i >= 0)
      str = paramString.substring(i + 1) + ' ' + paramString.substring(0, i);
    return str;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.AddressBookDoCoMoResultParser
 * JD-Core Version:    0.6.2
 */