package com.google.zxing.client.result;

import com.google.zxing.Result;

final class EmailDoCoMoResultParser extends AbstractDoCoMoResultParser
{
  private static final char[] ATEXT_SYMBOLS = { 64, 46, 33, 35, 36, 37, 38, 39, 42, 43, 45, 47, 61, 63, 94, 95, 96, 123, 124, 125, 126 };

  private static boolean isAtextSymbol(char paramChar)
  {
    boolean bool2 = false;
    int i = 0;
    while (true)
    {
      boolean bool1 = bool2;
      if (i < ATEXT_SYMBOLS.length)
      {
        if (paramChar == ATEXT_SYMBOLS[i])
          bool1 = true;
      }
      else
        return bool1;
      i += 1;
    }
  }

  static boolean isBasicallyValidEmailAddress(String paramString)
  {
    if (paramString == null)
      return false;
    int i = 0;
    boolean bool2;
    for (boolean bool1 = false; ; bool1 = bool2)
    {
      if (i >= paramString.length())
        break label93;
      char c = paramString.charAt(i);
      if (((c < 'a') || (c > 'z')) && ((c < 'A') || (c > 'Z')) && ((c < '0') || (c > '9')) && (!isAtextSymbol(c)))
        break;
      bool2 = bool1;
      if (c == '@')
      {
        if (bool1)
          break;
        bool2 = true;
      }
      i += 1;
    }
    label93: return bool1;
  }

  public static EmailAddressParsedResult parse(Result paramResult)
  {
    paramResult = paramResult.getText();
    if ((paramResult == null) || (!paramResult.startsWith("MATMSG:")));
    Object localObject;
    do
    {
      do
      {
        return null;
        localObject = matchDoCoMoPrefixedField("TO:", paramResult, true);
      }
      while (localObject == null);
      localObject = localObject[0];
    }
    while (!isBasicallyValidEmailAddress((String)localObject));
    return new EmailAddressParsedResult((String)localObject, matchSingleDoCoMoPrefixedField("SUB:", paramResult, false), matchSingleDoCoMoPrefixedField("BODY:", paramResult, false), "mailto:" + (String)localObject);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.EmailDoCoMoResultParser
 * JD-Core Version:    0.6.2
 */