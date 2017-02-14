package net.tsz.afinal.http.entityhandler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpEntity;

public class StringEntityHandler
{
  public Object handleEntity(HttpEntity paramHttpEntity, EntityCallBack paramEntityCallBack, String paramString)
    throws IOException
  {
    if (paramHttpEntity == null)
      return null;
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    byte[] arrayOfByte = new byte[1024];
    long l3 = paramHttpEntity.getContentLength();
    long l1 = 0L;
    paramHttpEntity = paramHttpEntity.getContent();
    while (true)
    {
      int i = paramHttpEntity.read(arrayOfByte);
      if (i == -1)
      {
        if (paramEntityCallBack != null)
          paramEntityCallBack.callBack(l3, l1, true);
        paramEntityCallBack = localByteArrayOutputStream.toByteArray();
        localByteArrayOutputStream.close();
        paramHttpEntity.close();
        return new String(paramEntityCallBack, paramString);
      }
      localByteArrayOutputStream.write(arrayOfByte, 0, i);
      long l2 = l1 + i;
      l1 = l2;
      if (paramEntityCallBack != null)
      {
        paramEntityCallBack.callBack(l3, l2, false);
        l1 = l2;
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     net.tsz.afinal.http.entityhandler.StringEntityHandler
 * JD-Core Version:    0.6.2
 */