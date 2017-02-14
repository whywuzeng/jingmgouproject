package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.Vector;

final class AddressBookAUResultParser extends ResultParser
{
  private static String[] matchMultipleValuePrefix(String paramString1, int paramInt, String paramString2, boolean paramBoolean)
  {
    int i = 1;
    Object localObject2;
    for (Object localObject1 = null; ; localObject1 = localObject2)
    {
      String str;
      if (i <= paramInt)
      {
        str = matchSinglePrefixedField(paramString1 + i + ':', paramString2, '\r', paramBoolean);
        if (str != null);
      }
      else
      {
        if (localObject1 != null)
          break;
        return null;
      }
      localObject2 = localObject1;
      if (localObject1 == null)
        localObject2 = new Vector(paramInt);
      ((Vector)localObject2).addElement(str);
      i += 1;
    }
    return toStringArray(localObject1);
  }

  public static AddressBookParsedResult parse(Result paramResult)
  {
    paramResult = paramResult.getText();
    if ((paramResult == null) || (paramResult.indexOf("MEMORY") < 0) || (paramResult.indexOf("\r\n") < 0))
      return null;
    String str1 = matchSinglePrefixedField("NAME1:", paramResult, '\r', true);
    String str2 = matchSinglePrefixedField("NAME2:", paramResult, '\r', true);
    String[] arrayOfString1 = matchMultipleValuePrefix("TEL", 3, paramResult, true);
    String[] arrayOfString2 = matchMultipleValuePrefix("MAIL", 3, paramResult, true);
    String str3 = matchSinglePrefixedField("MEMORY:", paramResult, '\r', false);
    String str4 = matchSinglePrefixedField("ADD:", paramResult, '\r', true);
    if (str4 == null)
      paramResult = null;
    while (true)
    {
      return new AddressBookParsedResult(maybeWrap(str1), str2, arrayOfString1, arrayOfString2, str3, paramResult, null, null, null, null);
      paramResult = new String[1];
      paramResult[0] = str4;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.AddressBookAUResultParser
 * JD-Core Version:    0.6.2
 */