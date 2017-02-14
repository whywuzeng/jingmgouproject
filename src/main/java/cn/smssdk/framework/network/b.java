package cn.smssdk.framework.network;

import cn.smssdk.framework.utils.Data;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class b extends c
{
  private a a;

  public b a(byte[] paramArrayOfByte)
  {
    if (this.a == null)
      this.a = new a(paramArrayOfByte.length);
    this.a.write(paramArrayOfByte);
    this.a.flush();
    return this;
  }

  protected InputStream a()
  {
    if (this.a == null)
      return new ByteArrayInputStream(new byte[0]);
    byte[] arrayOfByte = this.a.a();
    if ((arrayOfByte == null) || (this.a.size() <= 0))
      return new ByteArrayInputStream(new byte[0]);
    return new ByteArrayInputStream(arrayOfByte, 0, this.a.size());
  }

  protected long b()
  {
    if (this.a == null)
      return 0L;
    return this.a.size();
  }

  public String toString()
  {
    if (this.a == null);
    byte[] arrayOfByte;
    do
    {
      return null;
      arrayOfByte = this.a.a();
    }
    while (arrayOfByte == null);
    return Data.byteToHex(arrayOfByte, 0, this.a.size());
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.smssdk.framework.network.b
 * JD-Core Version:    0.6.2
 */