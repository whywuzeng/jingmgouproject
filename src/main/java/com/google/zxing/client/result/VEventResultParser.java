package com.google.zxing.client.result;

import com.google.zxing.Result;

final class VEventResultParser extends ResultParser
{
  public static CalendarParsedResult parse(Result paramResult)
  {
    String str4 = paramResult.getText();
    if (str4 == null)
      return null;
    if (str4.indexOf("BEGIN:VEVENT") < 0)
      return null;
    paramResult = VCardResultParser.matchSingleVCardPrefixedField("SUMMARY", str4, true);
    String str1 = VCardResultParser.matchSingleVCardPrefixedField("DTSTART", str4, true);
    String str2 = VCardResultParser.matchSingleVCardPrefixedField("DTEND", str4, true);
    String str3 = VCardResultParser.matchSingleVCardPrefixedField("LOCATION", str4, true);
    str4 = VCardResultParser.matchSingleVCardPrefixedField("DESCRIPTION", str4, true);
    try
    {
      paramResult = new CalendarParsedResult(paramResult, str1, str2, str3, null, str4);
      return paramResult;
    }
    catch (IllegalArgumentException paramResult)
    {
    }
    return null;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.VEventResultParser
 * JD-Core Version:    0.6.2
 */