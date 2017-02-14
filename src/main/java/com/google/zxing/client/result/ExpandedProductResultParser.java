package com.google.zxing.client.result;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import java.util.Hashtable;

final class ExpandedProductResultParser extends ResultParser
{
  private static String findAIvalue(int paramInt, String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    if (paramString.charAt(paramInt) != '(')
      return "ERROR";
    paramString = paramString.substring(paramInt + 1);
    paramInt = 0;
    while (paramInt < paramString.length())
    {
      char c = paramString.charAt(paramInt);
      switch (c)
      {
      case '*':
      case '+':
      case ',':
      case '-':
      case '.':
      case '/':
      default:
        return "ERROR";
      case '0':
      case '1':
      case '2':
      case '3':
      case '4':
      case '5':
      case '6':
      case '7':
      case '8':
      case '9':
        localStringBuffer.append(c);
        paramInt += 1;
        break;
      case ')':
        return localStringBuffer.toString();
      }
    }
    return localStringBuffer.toString();
  }

  private static String findValue(int paramInt, String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    paramString = paramString.substring(paramInt);
    paramInt = 0;
    if (paramInt < paramString.length())
    {
      char c = paramString.charAt(paramInt);
      if (c == '(')
      {
        if ("ERROR".equals(findAIvalue(paramInt, paramString)))
          localStringBuffer.append('(');
      }
      else
        while (true)
        {
          paramInt += 1;
          break;
          localStringBuffer.append(c);
        }
    }
    return localStringBuffer.toString();
  }

  public static ExpandedProductParsedResult parse(Result paramResult)
  {
    Object localObject1 = paramResult.getBarcodeFormat();
    if (!BarcodeFormat.RSS_EXPANDED.equals(localObject1))
      return null;
    String str4 = paramResult.getText();
    if (str4 == null)
      return null;
    Object localObject10 = "-";
    Object localObject9 = "-";
    Object localObject7 = "-";
    Object localObject6 = "-";
    Object localObject5 = "-";
    Object localObject4 = "-";
    Object localObject3 = "-";
    Object localObject8 = "-";
    localObject1 = "-";
    String str1 = "-";
    Object localObject2 = "-";
    String str2 = "-";
    String str3 = "-";
    Hashtable localHashtable = new Hashtable();
    int i = 0;
    if (i < str4.length())
    {
      String str5 = findAIvalue(i, str4);
      if ("ERROR".equals(str5))
        return null;
      i = str5.length() + 2 + i;
      paramResult = findValue(i, str4);
      int j = paramResult.length();
      if ("00".equals(str5))
      {
        localObject9 = paramResult;
        paramResult = (Result)localObject8;
      }
      while (true)
      {
        i += j;
        localObject8 = paramResult;
        break;
        if ("01".equals(str5))
        {
          localObject10 = paramResult;
          paramResult = (Result)localObject8;
        }
        else if ("10".equals(str5))
        {
          localObject7 = paramResult;
          paramResult = (Result)localObject8;
        }
        else if ("11".equals(str5))
        {
          localObject6 = paramResult;
          paramResult = (Result)localObject8;
        }
        else if ("13".equals(str5))
        {
          localObject5 = paramResult;
          paramResult = (Result)localObject8;
        }
        else if ("15".equals(str5))
        {
          localObject4 = paramResult;
          paramResult = (Result)localObject8;
        }
        else if ("17".equals(str5))
        {
          localObject3 = paramResult;
          paramResult = (Result)localObject8;
        }
        else if (("3100".equals(str5)) || ("3101".equals(str5)) || ("3102".equals(str5)) || ("3103".equals(str5)) || ("3104".equals(str5)) || ("3105".equals(str5)) || ("3106".equals(str5)) || ("3107".equals(str5)) || ("3108".equals(str5)) || ("3109".equals(str5)))
        {
          localObject1 = "KG";
          str1 = str5.substring(3);
        }
        else if (("3200".equals(str5)) || ("3201".equals(str5)) || ("3202".equals(str5)) || ("3203".equals(str5)) || ("3204".equals(str5)) || ("3205".equals(str5)) || ("3206".equals(str5)) || ("3207".equals(str5)) || ("3208".equals(str5)) || ("3209".equals(str5)))
        {
          localObject1 = "LB";
          str1 = str5.substring(3);
        }
        else if (("3920".equals(str5)) || ("3921".equals(str5)) || ("3922".equals(str5)) || ("3923".equals(str5)))
        {
          str2 = str5.substring(3);
          localObject2 = paramResult;
          paramResult = (Result)localObject8;
        }
        else if (("3930".equals(str5)) || ("3931".equals(str5)) || ("3932".equals(str5)) || ("3933".equals(str5)))
        {
          if (paramResult.length() < 4)
            return null;
          localObject2 = paramResult.substring(3);
          str3 = paramResult.substring(0, 3);
          str2 = str5.substring(3);
          paramResult = (Result)localObject8;
        }
        else
        {
          localHashtable.put(str5, paramResult);
          paramResult = (Result)localObject8;
        }
      }
    }
    return new ExpandedProductParsedResult((String)localObject10, (String)localObject9, (String)localObject7, (String)localObject6, (String)localObject5, (String)localObject4, (String)localObject3, (String)localObject8, (String)localObject1, str1, (String)localObject2, str2, str3, localHashtable);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.ExpandedProductResultParser
 * JD-Core Version:    0.6.2
 */