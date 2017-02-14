package com.squareup.okhttp;

import java.io.UnsupportedEncodingException;
import okio.ByteString;

public final class Credentials
{
  public static String basic(String paramString1, String paramString2)
  {
    try
    {
      paramString1 = ByteString.of((paramString1 + ":" + paramString2).getBytes("ISO-8859-1")).base64();
      paramString1 = "Basic " + paramString1;
      return paramString1;
    }
    catch (UnsupportedEncodingException paramString1)
    {
    }
    throw new AssertionError();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.Credentials
 * JD-Core Version:    0.6.2
 */