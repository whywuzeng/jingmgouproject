package com.google.zxing.client.result.optional;

import com.google.zxing.Result;

final class NDEFSmartPosterResultParser extends AbstractNDEFResultParser
{
  public static NDEFSmartPosterParsedResult parse(Result paramResult)
  {
    paramResult = paramResult.getRawBytes();
    if (paramResult == null);
    int k;
    Object localObject2;
    Object localObject1;
    int j;
    Object localObject3;
    do
    {
      int i;
      do
      {
        do
        {
          return null;
          paramResult = NDEFRecord.readRecord(paramResult, 0);
        }
        while ((paramResult == null) || (!paramResult.isMessageBegin()) || (!paramResult.isMessageEnd()) || (!paramResult.getType().equals("Sp")));
        byte[] arrayOfByte = paramResult.getPayload();
        k = -1;
        localObject2 = null;
        localObject1 = null;
        paramResult = null;
        j = 0;
        i = 0;
        localObject3 = paramResult;
        if (i >= arrayOfByte.length)
          break;
        paramResult = NDEFRecord.readRecord(arrayOfByte, i);
        localObject3 = paramResult;
        if (paramResult == null)
          break;
      }
      while ((j == 0) && (!paramResult.isMessageBegin()));
      String str = paramResult.getType();
      Object localObject4;
      if ("T".equals(str))
      {
        localObject4 = NDEFTextResultParser.decodeTextPayload(paramResult.getPayload())[1];
        localObject3 = localObject2;
      }
      while (true)
      {
        j += 1;
        i += paramResult.getTotalRecordLength();
        localObject2 = localObject3;
        localObject1 = localObject4;
        break;
        if ("U".equals(str))
        {
          localObject3 = NDEFURIResultParser.decodeURIPayload(paramResult.getPayload());
          localObject4 = localObject1;
        }
        else
        {
          localObject3 = localObject2;
          localObject4 = localObject1;
          if ("act".equals(str))
          {
            k = paramResult.getPayload()[0];
            localObject3 = localObject2;
            localObject4 = localObject1;
          }
        }
      }
    }
    while ((j == 0) || ((localObject3 != null) && (!((NDEFRecord)localObject3).isMessageEnd())));
    return new NDEFSmartPosterParsedResult(k, localObject2, localObject1);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.optional.NDEFSmartPosterResultParser
 * JD-Core Version:    0.6.2
 */