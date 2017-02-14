package com.mob.tools.network;

import java.io.IOException;
import java.io.InputStream;

public class ByteCounterInputStream extends InputStream
{
  private InputStream is;
  private OnReadListener listener;
  private long readBytes;

  public ByteCounterInputStream(InputStream paramInputStream)
  {
    this.is = paramInputStream;
  }

  public int available()
    throws IOException
  {
    return this.is.available();
  }

  public void close()
    throws IOException
  {
    this.is.close();
  }

  public void mark(int paramInt)
  {
    this.is.mark(paramInt);
  }

  public boolean markSupported()
  {
    return this.is.markSupported();
  }

  public int read()
    throws IOException
  {
    int i = this.is.read();
    if (i >= 0)
    {
      this.readBytes += 1L;
      if (this.listener != null)
        this.listener.onRead(this.readBytes);
    }
    return i;
  }

  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    paramInt1 = this.is.read(paramArrayOfByte, paramInt1, paramInt2);
    if (paramInt1 > 0)
    {
      this.readBytes += paramInt1;
      if (this.listener != null)
        this.listener.onRead(this.readBytes);
    }
    return paramInt1;
  }

  public void reset()
    throws IOException
  {
    try
    {
      this.is.reset();
      this.readBytes = 0L;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setOnInputStreamReadListener(OnReadListener paramOnReadListener)
  {
    this.listener = paramOnReadListener;
  }

  public long skip(long paramLong)
    throws IOException
  {
    return this.is.skip(paramLong);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.mob.tools.network.ByteCounterInputStream
 * JD-Core Version:    0.6.2
 */