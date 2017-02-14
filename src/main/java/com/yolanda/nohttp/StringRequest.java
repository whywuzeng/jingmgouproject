package com.yolanda.nohttp;

import java.io.UnsupportedEncodingException;

public class StringRequest extends RestRequestor<String>
{
  public StringRequest(String paramString)
  {
    super(paramString);
  }

  public StringRequest(String paramString, int paramInt)
  {
    super(paramString, paramInt);
  }

  public String parseResponse(String paramString1, String paramString2, byte[] paramArrayOfByte)
  {
    Object localObject = null;
    paramString1 = localObject;
    if (paramArrayOfByte != null)
    {
      paramString1 = localObject;
      if (paramArrayOfByte.length <= 0);
    }
    try
    {
      paramString1 = new String(paramArrayOfByte, HeaderParser.parseHeadValue(paramString2, "charset", ""));
      return paramString1;
    }
    catch (UnsupportedEncodingException paramString1)
    {
      Logger.w("Charset error in ContentType returned by the server：" + paramString2);
    }
    return new String(paramArrayOfByte);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.yolanda.nohttp.StringRequest
 * JD-Core Version:    0.6.2
 */