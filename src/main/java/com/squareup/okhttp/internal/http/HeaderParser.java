package com.squareup.okhttp.internal.http;

public final class HeaderParser
{
  public static int parseSeconds(String paramString, int paramInt)
  {
    try
    {
      long l = Long.parseLong(paramString);
      if (l > 2147483647L)
        return 2147483647;
      if (l < 0L)
        return 0;
      return (int)l;
    }
    catch (NumberFormatException paramString)
    {
    }
    return paramInt;
  }

  public static int skipUntil(String paramString1, int paramInt, String paramString2)
  {
    while (true)
    {
      if ((paramInt >= paramString1.length()) || (paramString2.indexOf(paramString1.charAt(paramInt)) != -1))
        return paramInt;
      paramInt += 1;
    }
  }

  public static int skipWhitespace(String paramString, int paramInt)
  {
    while (true)
    {
      if (paramInt < paramString.length())
      {
        int i = paramString.charAt(paramInt);
        if ((i == 32) || (i == 9));
      }
      else
      {
        return paramInt;
      }
      paramInt += 1;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.http.HeaderParser
 * JD-Core Version:    0.6.2
 */