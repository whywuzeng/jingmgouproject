package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.Vector;

final class BizcardResultParser extends AbstractDoCoMoResultParser
{
  private static String buildName(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      return paramString2;
    if (paramString2 == null);
    while (true)
    {
      return paramString1;
      paramString1 = paramString1 + ' ' + paramString2;
    }
  }

  private static String[] buildPhoneNumbers(String paramString1, String paramString2, String paramString3)
  {
    Vector localVector = new Vector(3);
    if (paramString1 != null)
      localVector.addElement(paramString1);
    if (paramString2 != null)
      localVector.addElement(paramString2);
    if (paramString3 != null)
      localVector.addElement(paramString3);
    int j = localVector.size();
    if (j == 0)
      return null;
    paramString1 = new String[j];
    int i = 0;
    while (i < j)
    {
      paramString1[i] = ((String)localVector.elementAt(i));
      i += 1;
    }
    return paramString1;
  }

  public static AddressBookParsedResult parse(Result paramResult)
  {
    String str6 = paramResult.getText();
    if ((str6 == null) || (!str6.startsWith("BIZCARD:")))
      return null;
    paramResult = buildName(matchSingleDoCoMoPrefixedField("N:", str6, true), matchSingleDoCoMoPrefixedField("X:", str6, true));
    String str1 = matchSingleDoCoMoPrefixedField("T:", str6, true);
    String str2 = matchSingleDoCoMoPrefixedField("C:", str6, true);
    String[] arrayOfString = matchDoCoMoPrefixedField("A:", str6, true);
    String str3 = matchSingleDoCoMoPrefixedField("B:", str6, true);
    String str4 = matchSingleDoCoMoPrefixedField("M:", str6, true);
    String str5 = matchSingleDoCoMoPrefixedField("F:", str6, true);
    str6 = matchSingleDoCoMoPrefixedField("E:", str6, true);
    return new AddressBookParsedResult(maybeWrap(paramResult), null, buildPhoneNumbers(str3, str4, str5), maybeWrap(str6), null, arrayOfString, str2, null, str1, null);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.BizcardResultParser
 * JD-Core Version:    0.6.2
 */