package com.google.zxing.client.result.optional;

import com.google.zxing.client.result.ResultParser;
import java.io.UnsupportedEncodingException;

abstract class AbstractNDEFResultParser extends ResultParser
{
  static String bytesToString(byte[] paramArrayOfByte, int paramInt1, int paramInt2, String paramString)
  {
    try
    {
      paramArrayOfByte = new String(paramArrayOfByte, paramInt1, paramInt2, paramString);
      return paramArrayOfByte;
    }
    catch (UnsupportedEncodingException paramArrayOfByte)
    {
    }
    throw new RuntimeException("Platform does not support required encoding: " + paramArrayOfByte);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.optional.AbstractNDEFResultParser
 * JD-Core Version:    0.6.2
 */