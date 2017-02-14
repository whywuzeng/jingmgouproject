package cn.smssdk.utils;

import cn.smssdk.framework.network.RawNetworkCallback;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;

class a
  implements RawNetworkCallback
{
  a(Protocols paramProtocols, HashMap paramHashMap)
  {
  }

  public void onResponse(InputStream paramInputStream)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    byte[] arrayOfByte = new byte[65536];
    for (int i = paramInputStream.read(arrayOfByte); i > 0; i = paramInputStream.read(arrayOfByte))
      localByteArrayOutputStream.write(arrayOfByte, 0, i);
    localByteArrayOutputStream.flush();
    paramInputStream.close();
    localByteArrayOutputStream.close();
    this.a.put("bResp", localByteArrayOutputStream.toByteArray());
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.smssdk.utils.a
 * JD-Core Version:    0.6.2
 */