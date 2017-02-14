package com.mob.tools.network;

import com.mob.tools.utils.Data;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ByteArrayPart extends HTTPPart
{
  private BufferedByteArrayOutputStream buffer;

  public ByteArrayPart append(byte[] paramArrayOfByte)
    throws Throwable
  {
    if (this.buffer == null)
      this.buffer = new BufferedByteArrayOutputStream(paramArrayOfByte.length);
    this.buffer.write(paramArrayOfByte);
    this.buffer.flush();
    return this;
  }

  protected InputStream getInputStream()
    throws Throwable
  {
    if (this.buffer == null)
      return new ByteArrayInputStream(new byte[0]);
    byte[] arrayOfByte = this.buffer.getBuffer();
    if ((arrayOfByte == null) || (this.buffer.size() <= 0))
      return new ByteArrayInputStream(new byte[0]);
    return new ByteArrayInputStream(arrayOfByte, 0, this.buffer.size());
  }

  protected long length()
    throws Throwable
  {
    if (this.buffer == null)
      return 0L;
    return this.buffer.size();
  }

  public String toString()
  {
    if (this.buffer == null);
    byte[] arrayOfByte;
    do
    {
      return null;
      arrayOfByte = this.buffer.getBuffer();
    }
    while (arrayOfByte == null);
    return Data.byteToHex(arrayOfByte, 0, this.buffer.size());
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.mob.tools.network.ByteArrayPart
 * JD-Core Version:    0.6.2
 */