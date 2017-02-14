package com.google.zxing.client.result.optional;

import com.google.zxing.Result;
import com.google.zxing.client.result.TextParsedResult;

final class NDEFTextResultParser extends AbstractNDEFResultParser
{
  static String[] decodeTextPayload(byte[] paramArrayOfByte)
  {
    int j = paramArrayOfByte[0];
    int i;
    String str2;
    if ((j & 0x80) != 0)
    {
      i = 1;
      j &= 31;
      str2 = bytesToString(paramArrayOfByte, 1, j, "US-ASCII");
      if (i == 0)
        break label68;
    }
    label68: for (String str1 = "UTF-16"; ; str1 = "UTF8")
    {
      return new String[] { str2, bytesToString(paramArrayOfByte, j + 1, paramArrayOfByte.length - j - 1, str1) };
      i = 0;
      break;
    }
  }

  public static TextParsedResult parse(Result paramResult)
  {
    paramResult = paramResult.getRawBytes();
    if (paramResult == null);
    do
    {
      return null;
      paramResult = NDEFRecord.readRecord(paramResult, 0);
    }
    while ((paramResult == null) || (!paramResult.isMessageBegin()) || (!paramResult.isMessageEnd()) || (!paramResult.getType().equals("T")));
    paramResult = decodeTextPayload(paramResult.getPayload());
    return new TextParsedResult(paramResult[0], paramResult[1]);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.optional.NDEFTextResultParser
 * JD-Core Version:    0.6.2
 */