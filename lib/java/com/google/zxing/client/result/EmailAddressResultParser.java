package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.Hashtable;

final class EmailAddressResultParser extends ResultParser
{
  public static EmailAddressParsedResult parse(Result paramResult)
  {
    String str1 = null;
    String str3 = paramResult.getText();
    if (str3 == null)
      return null;
    Object localObject;
    label90: String str2;
    if ((str3.startsWith("mailto:")) || (str3.startsWith("MAILTO:")))
    {
      localObject = str3.substring(7);
      int i = ((String)localObject).indexOf('?');
      paramResult = (Result)localObject;
      if (i >= 0)
        paramResult = ((String)localObject).substring(0, i);
      localObject = parseNameValuePairs(str3);
      if (localObject == null)
        break label172;
      if (paramResult.length() != 0)
        break label169;
      paramResult = (String)((Hashtable)localObject).get("to");
      str2 = (String)((Hashtable)localObject).get("subject");
      str1 = (String)((Hashtable)localObject).get("body");
      localObject = paramResult;
    }
    for (paramResult = str2; ; paramResult = null)
    {
      return new EmailAddressParsedResult((String)localObject, paramResult, str1, str3);
      if (!EmailDoCoMoResultParser.isBasicallyValidEmailAddress(str3))
        break;
      return new EmailAddressParsedResult(str3, null, null, "mailto:" + str3);
      label169: break label90;
      label172: localObject = paramResult;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.EmailAddressResultParser
 * JD-Core Version:    0.6.2
 */