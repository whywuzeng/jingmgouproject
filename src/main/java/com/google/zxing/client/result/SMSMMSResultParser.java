package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.Hashtable;
import java.util.Vector;

final class SMSMMSResultParser extends ResultParser
{
  private static void addNumberVia(Vector paramVector1, Vector paramVector2, String paramString)
  {
    Object localObject = null;
    int i = paramString.indexOf(';');
    if (i < 0)
    {
      paramVector1.addElement(paramString);
      paramVector2.addElement(null);
      return;
    }
    paramVector1.addElement(paramString.substring(0, i));
    paramString = paramString.substring(i + 1);
    paramVector1 = localObject;
    if (paramString.startsWith("via="))
      paramVector1 = paramString.substring(4);
    paramVector2.addElement(paramVector1);
  }

  public static SMSParsedResult parse(Result paramResult)
  {
    Object localObject1 = null;
    Object localObject2 = paramResult.getText();
    if (localObject2 == null);
    while ((!((String)localObject2).startsWith("sms:")) && (!((String)localObject2).startsWith("SMS:")) && (!((String)localObject2).startsWith("mms:")) && (!((String)localObject2).startsWith("MMS:")))
      return null;
    paramResult = parseNameValuePairs((String)localObject2);
    int i = 0;
    if ((paramResult != null) && (!paramResult.isEmpty()))
    {
      localObject1 = (String)paramResult.get("subject");
      paramResult = (String)paramResult.get("body");
      i = 1;
    }
    while (true)
    {
      int j = ((String)localObject2).indexOf('?', 4);
      if ((j < 0) || (i == 0));
      Vector localVector;
      for (String str = ((String)localObject2).substring(4); ; str = ((String)localObject2).substring(4, j))
      {
        localObject2 = new Vector(1);
        localVector = new Vector(1);
        for (i = -1; ; i = j)
        {
          j = str.indexOf(',', i + 1);
          if (j <= i)
            break;
          addNumberVia((Vector)localObject2, localVector, str.substring(i + 1, j));
        }
      }
      addNumberVia((Vector)localObject2, localVector, str.substring(i + 1));
      return new SMSParsedResult(toStringArray((Vector)localObject2), toStringArray(localVector), (String)localObject1, paramResult);
      str = null;
      paramResult = (Result)localObject1;
      localObject1 = str;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.SMSMMSResultParser
 * JD-Core Version:    0.6.2
 */