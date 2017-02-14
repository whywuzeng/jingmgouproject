package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.Hashtable;
import java.util.Vector;

public abstract class ResultParser
{
  private static void appendKeyValue(String paramString, int paramInt1, int paramInt2, Hashtable paramHashtable)
  {
    int i = paramString.indexOf('=', paramInt1);
    if (i >= 0)
      paramHashtable.put(paramString.substring(paramInt1, i), urlDecode(paramString.substring(i + 1, paramInt2)));
  }

  private static int findFirstEscape(char[] paramArrayOfChar)
  {
    int j = paramArrayOfChar.length;
    int i = 0;
    while (i < j)
    {
      int k = paramArrayOfChar[i];
      if ((k == 43) || (k == 37))
        return i;
      i += 1;
    }
    return -1;
  }

  protected static boolean isStringOfDigits(String paramString, int paramInt)
  {
    if (paramString == null);
    while (paramInt != paramString.length())
      return false;
    int i = 0;
    while (true)
    {
      if (i >= paramInt)
        break label46;
      int j = paramString.charAt(i);
      if ((j < 48) || (j > 57))
        break;
      i += 1;
    }
    label46: return true;
  }

  protected static boolean isSubstringOfDigits(String paramString, int paramInt1, int paramInt2)
  {
    if (paramString == null);
    int i;
    do
    {
      return false;
      i = paramString.length();
      paramInt2 = paramInt1 + paramInt2;
    }
    while (i < paramInt2);
    while (true)
    {
      if (paramInt1 >= paramInt2)
        break label50;
      i = paramString.charAt(paramInt1);
      if ((i < 48) || (i > 57))
        break;
      paramInt1 += 1;
    }
    label50: return true;
  }

  static String[] matchPrefixedField(String paramString1, String paramString2, char paramChar, boolean paramBoolean)
  {
    int m = paramString2.length();
    int i = 0;
    Object localObject1 = null;
    while (true)
    {
      if (i < m)
      {
        i = paramString2.indexOf(paramString1, i);
        if (i >= 0);
      }
      else
      {
        if ((localObject1 != null) && (!((Vector)localObject1).isEmpty()))
          break;
        return null;
      }
      int k = i + paramString1.length();
      int j = 0;
      i = k;
      while (j == 0)
      {
        i = paramString2.indexOf(paramChar, i);
        if (i < 0)
        {
          i = paramString2.length();
          j = 1;
        }
        else if (paramString2.charAt(i - 1) == '\\')
        {
          i += 1;
        }
        else
        {
          Object localObject2 = localObject1;
          if (localObject1 == null)
            localObject2 = new Vector(3);
          String str = unescapeBackslash(paramString2.substring(k, i));
          localObject1 = str;
          if (paramBoolean)
            localObject1 = str.trim();
          ((Vector)localObject2).addElement(localObject1);
          i += 1;
          j = 1;
          localObject1 = localObject2;
        }
      }
    }
    return toStringArray((Vector)localObject1);
  }

  static String matchSinglePrefixedField(String paramString1, String paramString2, char paramChar, boolean paramBoolean)
  {
    paramString1 = matchPrefixedField(paramString1, paramString2, paramChar, paramBoolean);
    if (paramString1 == null)
      return null;
    return paramString1[0];
  }

  protected static void maybeAppend(String paramString, StringBuffer paramStringBuffer)
  {
    if (paramString != null)
    {
      paramStringBuffer.append('\n');
      paramStringBuffer.append(paramString);
    }
  }

  protected static void maybeAppend(String[] paramArrayOfString, StringBuffer paramStringBuffer)
  {
    if (paramArrayOfString != null)
    {
      int i = 0;
      while (i < paramArrayOfString.length)
      {
        paramStringBuffer.append('\n');
        paramStringBuffer.append(paramArrayOfString[i]);
        i += 1;
      }
    }
  }

  protected static String[] maybeWrap(String paramString)
  {
    if (paramString == null)
      return null;
    return new String[] { paramString };
  }

  private static int parseHexDigit(char paramChar)
  {
    if (paramChar >= 'a')
    {
      if (paramChar <= 'f')
        return paramChar - 'a' + 10;
    }
    else if (paramChar >= 'A')
    {
      if (paramChar <= 'F')
        return paramChar - 'A' + 10;
    }
    else if ((paramChar >= '0') && (paramChar <= '9'))
      return paramChar - '0';
    return -1;
  }

  static Hashtable parseNameValuePairs(String paramString)
  {
    int i = paramString.indexOf('?');
    if (i < 0)
      return null;
    Hashtable localHashtable = new Hashtable(3);
    i += 1;
    while (true)
    {
      int j = paramString.indexOf('&', i);
      if (j < 0)
        break;
      appendKeyValue(paramString, i, j, localHashtable);
      i = j + 1;
    }
    appendKeyValue(paramString, i, paramString.length(), localHashtable);
    return localHashtable;
  }

  public static ParsedResult parseResult(Result paramResult)
  {
    Object localObject1 = BookmarkDoCoMoResultParser.parse(paramResult);
    if (localObject1 != null);
    Object localObject2;
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                do
                {
                  do
                  {
                    do
                    {
                      do
                      {
                        do
                        {
                          do
                          {
                            do
                            {
                              do
                              {
                                do
                                {
                                  do
                                  {
                                    do
                                    {
                                      return localObject1;
                                      localObject2 = AddressBookDoCoMoResultParser.parse(paramResult);
                                      localObject1 = localObject2;
                                    }
                                    while (localObject2 != null);
                                    localObject2 = EmailDoCoMoResultParser.parse(paramResult);
                                    localObject1 = localObject2;
                                  }
                                  while (localObject2 != null);
                                  localObject2 = AddressBookAUResultParser.parse(paramResult);
                                  localObject1 = localObject2;
                                }
                                while (localObject2 != null);
                                localObject2 = VCardResultParser.parse(paramResult);
                                localObject1 = localObject2;
                              }
                              while (localObject2 != null);
                              localObject2 = BizcardResultParser.parse(paramResult);
                              localObject1 = localObject2;
                            }
                            while (localObject2 != null);
                            localObject2 = VEventResultParser.parse(paramResult);
                            localObject1 = localObject2;
                          }
                          while (localObject2 != null);
                          localObject2 = EmailAddressResultParser.parse(paramResult);
                          localObject1 = localObject2;
                        }
                        while (localObject2 != null);
                        localObject2 = TelResultParser.parse(paramResult);
                        localObject1 = localObject2;
                      }
                      while (localObject2 != null);
                      localObject2 = SMSMMSResultParser.parse(paramResult);
                      localObject1 = localObject2;
                    }
                    while (localObject2 != null);
                    localObject2 = SMSTOMMSTOResultParser.parse(paramResult);
                    localObject1 = localObject2;
                  }
                  while (localObject2 != null);
                  localObject2 = GeoResultParser.parse(paramResult);
                  localObject1 = localObject2;
                }
                while (localObject2 != null);
                localObject2 = WifiResultParser.parse(paramResult);
                localObject1 = localObject2;
              }
              while (localObject2 != null);
              localObject2 = URLTOResultParser.parse(paramResult);
              localObject1 = localObject2;
            }
            while (localObject2 != null);
            localObject2 = URIResultParser.parse(paramResult);
            localObject1 = localObject2;
          }
          while (localObject2 != null);
          localObject2 = ISBNResultParser.parse(paramResult);
          localObject1 = localObject2;
        }
        while (localObject2 != null);
        localObject2 = ProductResultParser.parse(paramResult);
        localObject1 = localObject2;
      }
      while (localObject2 != null);
      localObject2 = ExpandedProductResultParser.parse(paramResult);
      localObject1 = localObject2;
    }
    while (localObject2 != null);
    return new TextParsedResult(paramResult.getText(), null);
  }

  static String[] toStringArray(Vector paramVector)
  {
    int j = paramVector.size();
    String[] arrayOfString = new String[j];
    int i = 0;
    while (i < j)
    {
      arrayOfString[i] = ((String)paramVector.elementAt(i));
      i += 1;
    }
    return arrayOfString;
  }

  protected static String unescapeBackslash(String paramString)
  {
    Object localObject = paramString;
    if (paramString != null)
    {
      int j = paramString.indexOf('\\');
      localObject = paramString;
      if (j >= 0)
      {
        int k = paramString.length();
        localObject = new StringBuffer(k - 1);
        ((StringBuffer)localObject).append(paramString.toCharArray(), 0, j);
        int i = 0;
        if (j < k)
        {
          char c = paramString.charAt(j);
          if ((i != 0) || (c != '\\'))
            ((StringBuffer)localObject).append(c);
          for (i = 0; ; i = 1)
          {
            j += 1;
            break;
          }
        }
        localObject = ((StringBuffer)localObject).toString();
      }
    }
    return localObject;
  }

  private static String urlDecode(String paramString)
  {
    if (paramString == null)
      paramString = null;
    char[] arrayOfChar;
    int i;
    do
    {
      return paramString;
      arrayOfChar = paramString.toCharArray();
      i = findFirstEscape(arrayOfChar);
    }
    while (i < 0);
    int j = arrayOfChar.length;
    paramString = new StringBuffer(j - 2);
    paramString.append(arrayOfChar, 0, i);
    if (i < j)
    {
      char c = arrayOfChar[i];
      if (c == '+')
        paramString.append(' ');
      while (true)
      {
        i += 1;
        break;
        if (c == '%')
        {
          if (i >= j - 2)
          {
            paramString.append('%');
          }
          else
          {
            i += 1;
            int k = parseHexDigit(arrayOfChar[i]);
            i += 1;
            int m = parseHexDigit(arrayOfChar[i]);
            if ((k < 0) || (m < 0))
            {
              paramString.append('%');
              paramString.append(arrayOfChar[(i - 1)]);
              paramString.append(arrayOfChar[i]);
            }
            paramString.append((char)((k << 4) + m));
          }
        }
        else
          paramString.append(c);
      }
    }
    return paramString.toString();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.ResultParser
 * JD-Core Version:    0.6.2
 */