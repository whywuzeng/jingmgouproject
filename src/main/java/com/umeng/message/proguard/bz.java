package com.umeng.message.proguard;

import android.net.Uri;
import android.text.TextUtils;
import java.net.URLDecoder;

public class bz
{
  private static final String[] a = { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "0A", "0B", "0C", "0D", "0E", "0F", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "1A", "1B", "1C", "1D", "1E", "1F", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "2A", "2B", "2C", "2D", "2E", "2F", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "3A", "3B", "3C", "3D", "3E", "3F", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "4A", "4B", "4C", "4D", "4E", "4F", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "5A", "5B", "5C", "5D", "5E", "5F", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "6A", "6B", "6C", "6D", "6E", "6F", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "7A", "7B", "7C", "7D", "7E", "7F", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "8A", "8B", "8C", "8D", "8E", "8F", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "9A", "9B", "9C", "9D", "9E", "9F", "A0", "A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "AA", "AB", "AC", "AD", "AE", "AF", "B0", "B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "B9", "BA", "BB", "BC", "BD", "BE", "BF", "C0", "C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "CA", "CB", "CC", "CD", "CE", "CF", "D0", "D1", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "DA", "DB", "DC", "DD", "DE", "DF", "E0", "E1", "E2", "E3", "E4", "E5", "E6", "E7", "E8", "E9", "EA", "EB", "EC", "ED", "EE", "EF", "F0", "F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8", "F9", "FA", "FB", "FC", "FD", "FE", "FF" };
  private static final byte[] b = { 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 63, 63, 63, 63, 63, 63, 63, 10, 11, 12, 13, 14, 15, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 10, 11, 12, 13, 14, 15, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63 };

  public static String a(String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int j = paramString.length();
    int i = 0;
    if (i < j)
    {
      int k = paramString.charAt(i);
      if (k == 32)
        localStringBuffer.append('+');
      while (true)
      {
        i += 1;
        break;
        if ((65 <= k) && (k <= 90))
        {
          localStringBuffer.append((char)k);
        }
        else if ((97 <= k) && (k <= 122))
        {
          localStringBuffer.append((char)k);
        }
        else if ((48 <= k) && (k <= 57))
        {
          localStringBuffer.append((char)k);
        }
        else if ((k == 45) || (k == 95) || (k == 46) || (k == 33) || (k == 126) || (k == 42) || (k == 47) || (k == 40) || (k == 41))
        {
          localStringBuffer.append((char)k);
        }
        else if (k <= 127)
        {
          localStringBuffer.append('%');
          localStringBuffer.append(a[k]);
        }
        else
        {
          localStringBuffer.append('%');
          localStringBuffer.append('u');
          localStringBuffer.append(a[(k >>> 8)]);
          localStringBuffer.append(a[(k & 0xFF)]);
        }
      }
    }
    return localStringBuffer.toString();
  }

  public static String b(String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 0;
    int k = paramString.length();
    if (i < k)
    {
      int m = paramString.charAt(i);
      int j;
      if (m == 43)
      {
        localStringBuffer.append(' ');
        j = i;
      }
      label213: 
      do
      {
        while (true)
        {
          i = j + 1;
          break;
          if ((65 <= m) && (m <= 90))
          {
            localStringBuffer.append((char)m);
            j = i;
          }
          else if ((97 <= m) && (m <= 122))
          {
            localStringBuffer.append((char)m);
            j = i;
          }
          else if ((48 <= m) && (m <= 57))
          {
            localStringBuffer.append((char)m);
            j = i;
          }
          else
          {
            if ((m != 45) && (m != 95) && (m != 46) && (m != 33) && (m != 126) && (m != 42) && (m != 47) && (m != 40) && (m != 41))
              break label213;
            localStringBuffer.append((char)m);
            j = i;
          }
        }
        j = i;
      }
      while (m != 37);
      if ('u' != paramString.charAt(i + 1))
      {
        j = (b[paramString.charAt(i + 1)] | 0x0) << 4 | b[paramString.charAt(i + 2)];
        i += 2;
      }
      while (true)
      {
        localStringBuffer.append((char)j);
        j = i;
        break;
        j = (((b[paramString.charAt(i + 2)] | 0x0) << 4 | b[paramString.charAt(i + 3)]) << 4 | b[paramString.charAt(i + 4)]) << 4 | b[paramString.charAt(i + 5)];
        i += 5;
      }
    }
    return localStringBuffer.toString();
  }

  public static String c(String paramString)
  {
    try
    {
      Uri.parse(paramString);
      return paramString;
    }
    catch (Throwable localThrowable)
    {
    }
    return e(paramString);
  }

  private static String d(String paramString)
  {
    Object localObject3 = "";
    Object localObject1 = localObject3;
    if (!TextUtils.isEmpty(paramString));
    try
    {
      localObject1 = URLDecoder.decode(paramString, "utf-8");
      localObject3 = localObject1;
      label23: localObject1 = localObject3;
      if (TextUtils.isEmpty((CharSequence)localObject3));
      try
      {
        localObject1 = URLDecoder.decode(paramString, "gbk");
        localObject3 = localObject1;
        if (TextUtils.isEmpty((CharSequence)localObject1));
        try
        {
          localObject3 = b(paramString);
          localObject1 = localObject3;
          if (TextUtils.isEmpty((CharSequence)localObject3))
            localObject1 = paramString;
          return localObject1;
        }
        catch (Throwable localThrowable3)
        {
          while (true)
            localObject4 = localObject1;
        }
      }
      catch (Throwable localThrowable1)
      {
        while (true)
        {
          Object localObject4;
          Object localObject2 = localObject4;
        }
      }
    }
    catch (Throwable localThrowable2)
    {
      break label23;
    }
  }

  private static String e(String paramString)
  {
    int i = 0;
    Object localObject1 = paramString;
    if (paramString != null);
    while (true)
    {
      try
      {
        int j = paramString.indexOf("/");
        localObject1 = paramString;
        if (j > -1)
        {
          StringBuffer localStringBuffer = new StringBuffer();
          localStringBuffer.append(paramString.substring(0, j));
          localObject1 = null;
          int k = paramString.indexOf("?");
          String str;
          if (k > -1)
          {
            paramString.substring(j, k);
            str = d(paramString.substring(j, k));
            localObject1 = paramString.substring(k);
            if (!TextUtils.isEmpty(str))
              localStringBuffer.append(str);
            if (!TextUtils.isEmpty((CharSequence)localObject1))
            {
              localObject1 = ((String)localObject1).split("&");
              if (localObject1 != null)
              {
                j = localObject1.length;
                if (i < j)
                {
                  Object localObject2 = localObject1[i].split("=");
                  if ((localObject2 == null) || (localObject2.length != 2))
                    break label252;
                  str = localObject2[0];
                  localObject2 = d(localObject2[1]);
                  localStringBuffer.append(str + "=" + (String)localObject2);
                  if (i >= j - 1)
                    break label252;
                  localStringBuffer.append("&");
                  break label252;
                }
              }
            }
          }
          else
          {
            str = d(paramString.substring(j));
            continue;
          }
          localObject1 = localStringBuffer.toString();
        }
        else
        {
          return localObject1;
        }
      }
      catch (Throwable localThrowable)
      {
        return paramString;
      }
      label252: i += 1;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.bz
 * JD-Core Version:    0.6.2
 */