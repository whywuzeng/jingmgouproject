package com.mob.tools.network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class BufferedByteArrayOutputStream extends ByteArrayOutputStream
{
  public BufferedByteArrayOutputStream()
  {
  }

  public BufferedByteArrayOutputStream(int paramInt)
  {
    super(paramInt);
  }

  public byte[] getBuffer()
  {
    return this.buf;
  }

  public int getBufferSize()
  {
    return this.buf.length;
  }

  public boolean switchBuffer(byte[] paramArrayOfByte)
  {
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length != this.buf.length))
      return false;
    byte[] arrayOfByte = this.buf;
    this.buf = paramArrayOfByte;
    paramArrayOfByte = (byte[])arrayOfByte;
    return true;
  }

  public void write(ByteBuffer paramByteBuffer, int paramInt)
    throws IOException
  {
    if (this.buf.length - this.count >= paramInt)
    {
      paramByteBuffer.get(this.buf, this.count, paramInt);
      this.count += paramInt;
      return;
    }
    byte[] arrayOfByte = new byte[paramInt];
    paramByteBuffer.get(arrayOfByte);
    write(arrayOfByte);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.mob.tools.network.BufferedByteArrayOutputStream
 * JD-Core Version:    0.6.2
 */