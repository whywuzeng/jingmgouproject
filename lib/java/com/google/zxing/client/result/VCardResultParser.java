package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Vector;

final class VCardResultParser extends ResultParser
{
  private static String decodeQuotedPrintable(String paramString1, String paramString2)
  {
    int k = paramString1.length();
    StringBuffer localStringBuffer = new StringBuffer(k);
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    int i = 0;
    char c1;
    int j;
    if (i < k)
    {
      c1 = paramString1.charAt(i);
      j = i;
      switch (c1)
      {
      default:
        maybeAppendFragment(localByteArrayOutputStream, paramString2, localStringBuffer);
        localStringBuffer.append(c1);
        j = i;
      case '\n':
      case '\r':
      case '=':
      }
    }
    while (true)
    {
      i = j + 1;
      break;
      j = i;
      if (i >= k - 2)
        continue;
      c1 = paramString1.charAt(i + 1);
      j = i;
      if (c1 == '\r')
        continue;
      j = i;
      if (c1 == '\n')
        continue;
      char c2 = paramString1.charAt(i + 2);
      try
      {
        localByteArrayOutputStream.write(toHexValue(c1) * 16 + toHexValue(c2));
        label180: j = i + 2;
        continue;
        maybeAppendFragment(localByteArrayOutputStream, paramString2, localStringBuffer);
        return localStringBuffer.toString();
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        break label180;
      }
    }
  }

  private static String formatAddress(String paramString)
  {
    if (paramString == null)
      return null;
    int j = paramString.length();
    StringBuffer localStringBuffer = new StringBuffer(j);
    int i = 0;
    if (i < j)
    {
      char c = paramString.charAt(i);
      if (c == ';')
        localStringBuffer.append(' ');
      while (true)
      {
        i += 1;
        break;
        localStringBuffer.append(c);
      }
    }
    return localStringBuffer.toString().trim();
  }

  private static void formatNames(String[] paramArrayOfString)
  {
    if (paramArrayOfString != null)
    {
      int i = 0;
      while (i < paramArrayOfString.length)
      {
        Object localObject = paramArrayOfString[i];
        String[] arrayOfString = new String[5];
        int j = 0;
        int m;
        for (int k = 0; ; k = m + 1)
        {
          m = ((String)localObject).indexOf(';', k);
          if (m <= 0)
            break;
          arrayOfString[j] = ((String)localObject).substring(k, m);
          j += 1;
        }
        arrayOfString[j] = ((String)localObject).substring(k);
        localObject = new StringBuffer(100);
        maybeAppendComponent(arrayOfString, 3, (StringBuffer)localObject);
        maybeAppendComponent(arrayOfString, 1, (StringBuffer)localObject);
        maybeAppendComponent(arrayOfString, 2, (StringBuffer)localObject);
        maybeAppendComponent(arrayOfString, 0, (StringBuffer)localObject);
        maybeAppendComponent(arrayOfString, 4, (StringBuffer)localObject);
        paramArrayOfString[i] = ((StringBuffer)localObject).toString().trim();
        i += 1;
      }
    }
  }

  private static boolean isLikeVCardDate(String paramString)
  {
    if (paramString == null);
    while ((isStringOfDigits(paramString, 8)) || ((paramString.length() == 10) && (paramString.charAt(4) == '-') && (paramString.charAt(7) == '-') && (isSubstringOfDigits(paramString, 0, 4)) && (isSubstringOfDigits(paramString, 5, 2)) && (isSubstringOfDigits(paramString, 8, 2))))
      return true;
    return false;
  }

  static String matchSingleVCardPrefixedField(String paramString1, String paramString2, boolean paramBoolean)
  {
    paramString1 = matchVCardPrefixedField(paramString1, paramString2, paramBoolean);
    if (paramString1 == null)
      return null;
    return paramString1[0];
  }

  private static String[] matchVCardPrefixedField(String paramString1, String paramString2, boolean paramBoolean)
  {
    Object localObject1 = null;
    int i = 0;
    int i2 = paramString2.length();
    int j;
    do
    {
      while (true)
      {
        if (i < i2)
        {
          i = paramString2.indexOf(paramString1, i);
          if (i >= 0);
        }
        else
        {
          if ((localObject1 != null) && (!((Vector)localObject1).isEmpty()))
            break label558;
          return null;
        }
        if ((i <= 0) || (paramString2.charAt(i - 1) == '\n'))
          break;
        i += 1;
      }
      j = paramString1.length() + i;
      if (paramString2.charAt(j) == ':')
        break;
      i = j;
    }
    while (paramString2.charAt(j) != ';');
    int k = j;
    while (paramString2.charAt(k) != ':')
      k += 1;
    int m = 0;
    i = 0;
    int n;
    Object localObject2;
    label152: Object localObject3;
    int i1;
    String str;
    if (k > j)
    {
      m = j + 1;
      n = j;
      localObject2 = null;
      j = m;
      localObject3 = localObject2;
      m = i;
      if (j > k)
        break label319;
      if (paramString2.charAt(j) != ';')
      {
        localObject3 = localObject2;
        i1 = n;
        m = i;
        if (paramString2.charAt(j) != ':');
      }
      else
      {
        localObject3 = paramString2.substring(n + 1, j);
        m = ((String)localObject3).indexOf('=');
        if (m < 0)
          break label564;
        str = ((String)localObject3).substring(0, m);
        localObject3 = ((String)localObject3).substring(m + 1);
        if (!str.equalsIgnoreCase("ENCODING"))
          break label299;
        if (!((String)localObject3).equalsIgnoreCase("QUOTED-PRINTABLE"))
          break label564;
        i = 1;
      }
    }
    label299: label558: label564: 
    while (true)
    {
      i1 = j;
      m = i;
      localObject3 = localObject2;
      j += 1;
      localObject2 = localObject3;
      n = i1;
      i = m;
      break label152;
      if (str.equalsIgnoreCase("CHARSET"))
      {
        localObject2 = localObject3;
        continue;
        localObject3 = null;
        label319: j = k + 1;
        i = j;
        while (true)
        {
          k = paramString2.indexOf('\n', i);
          if (k < 0)
            break;
          if ((k < paramString2.length() - 1) && ((paramString2.charAt(k + 1) == ' ') || (paramString2.charAt(k + 1) == '\t')))
          {
            i = k + 2;
          }
          else
          {
            if ((m == 0) || ((paramString2.charAt(k - 1) != '=') && (paramString2.charAt(k - 2) != '=')))
              break;
            i = k + 1;
          }
        }
        if (k < 0)
          i = i2;
        while (true)
        {
          break;
          if (k > j)
          {
            localObject2 = localObject1;
            if (localObject1 == null)
              localObject2 = new Vector(1);
            i = k;
            if (paramString2.charAt(k - 1) == '\r')
              i = k - 1;
            str = paramString2.substring(j, i);
            localObject1 = str;
            if (paramBoolean)
              localObject1 = str.trim();
            if (m != 0);
            for (localObject1 = decodeQuotedPrintable((String)localObject1, (String)localObject3); ; localObject1 = stripContinuationCRLF((String)localObject1))
            {
              ((Vector)localObject2).addElement(localObject1);
              localObject1 = localObject2;
              i += 1;
              break;
            }
          }
          i = k + 1;
        }
        return toStringArray((Vector)localObject1);
      }
    }
  }

  private static void maybeAppendComponent(String[] paramArrayOfString, int paramInt, StringBuffer paramStringBuffer)
  {
    if (paramArrayOfString[paramInt] != null)
    {
      paramStringBuffer.append(' ');
      paramStringBuffer.append(paramArrayOfString[paramInt]);
    }
  }

  private static void maybeAppendFragment(ByteArrayOutputStream paramByteArrayOutputStream, String paramString, StringBuffer paramStringBuffer)
  {
    byte[] arrayOfByte;
    if (paramByteArrayOutputStream.size() > 0)
    {
      arrayOfByte = paramByteArrayOutputStream.toByteArray();
      if (paramString != null)
        break label36;
      paramString = new String(arrayOfByte);
    }
    while (true)
    {
      paramByteArrayOutputStream.reset();
      paramStringBuffer.append(paramString);
      return;
      try
      {
        label36: paramString = new String(arrayOfByte, paramString);
      }
      catch (UnsupportedEncodingException paramString)
      {
        paramString = new String(arrayOfByte);
      }
    }
  }

  public static AddressBookParsedResult parse(Result paramResult)
  {
    int i = 0;
    String str2 = paramResult.getText();
    if ((str2 == null) || (!str2.startsWith("BEGIN:VCARD")))
      return null;
    Object localObject = matchVCardPrefixedField("FN", str2, true);
    paramResult = (Result)localObject;
    if (localObject == null)
    {
      paramResult = matchVCardPrefixedField("N", str2, true);
      formatNames(paramResult);
    }
    String[] arrayOfString1 = matchVCardPrefixedField("TEL", str2, true);
    String[] arrayOfString2 = matchVCardPrefixedField("EMAIL", str2, true);
    String str3 = matchSingleVCardPrefixedField("NOTE", str2, false);
    String[] arrayOfString3 = matchVCardPrefixedField("ADR", str2, true);
    if (arrayOfString3 != null)
      while (i < arrayOfString3.length)
      {
        arrayOfString3[i] = formatAddress(arrayOfString3[i]);
        i += 1;
      }
    String str4 = matchSingleVCardPrefixedField("ORG", str2, true);
    String str1 = matchSingleVCardPrefixedField("BDAY", str2, true);
    localObject = str1;
    if (!isLikeVCardDate(str1))
      localObject = null;
    return new AddressBookParsedResult(paramResult, null, arrayOfString1, arrayOfString2, str3, arrayOfString3, str4, (String)localObject, matchSingleVCardPrefixedField("TITLE", str2, true), matchSingleVCardPrefixedField("URL", str2, true));
  }

  private static String stripContinuationCRLF(String paramString)
  {
    int k = paramString.length();
    StringBuffer localStringBuffer = new StringBuffer(k);
    int j = 0;
    int i = 0;
    if (j < k)
    {
      if (i != 0)
        i = 0;
      while (true)
      {
        j += 1;
        break;
        char c = paramString.charAt(j);
        switch (c)
        {
        case '\013':
        case '\f':
        default:
          localStringBuffer.append(c);
          i = 0;
          break;
        case '\n':
          i = 1;
          break;
        case '\r':
          i = 0;
        }
      }
    }
    return localStringBuffer.toString();
  }

  private static int toHexValue(char paramChar)
  {
    if ((paramChar >= '0') && (paramChar <= '9'))
      return paramChar - '0';
    if ((paramChar >= 'A') && (paramChar <= 'F'))
      return paramChar - 'A' + 10;
    if ((paramChar >= 'a') && (paramChar <= 'f'))
      return paramChar - 'a' + 10;
    throw new IllegalArgumentException();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.VCardResultParser
 * JD-Core Version:    0.6.2
 */