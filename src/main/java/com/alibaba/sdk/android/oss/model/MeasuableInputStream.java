package com.alibaba.sdk.android.oss.model;

import com.alibaba.sdk.android.oss.callback.GenericProgressHandler;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.util.concurrent.atomic.AtomicBoolean;

public class MeasuableInputStream extends FilterInputStream
{
  private int byteRead = 0;
  private GenericProgressHandler handler;
  private AtomicBoolean isCancel = null;
  private int totalSize = 0;

  public MeasuableInputStream(InputStream paramInputStream, GenericProgressHandler paramGenericProgressHandler, int paramInt)
  {
    super(paramInputStream);
    this.handler = paramGenericProgressHandler;
    this.totalSize = paramInt;
    this.isCancel = new AtomicBoolean(false);
  }

  public int read()
    throws IOException
  {
    byte[] arrayOfByte = new byte[1];
    int j = super.read(arrayOfByte, 0, 1);
    int i = j;
    if (j != -1)
      i = arrayOfByte[0];
    return i;
  }

  public int read(byte[] paramArrayOfByte)
    throws IOException
  {
    return super.read(paramArrayOfByte);
  }

  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if ((this.isCancel != null) && (this.isCancel.get()))
    {
      super.close();
      throw new InterruptedIOException();
    }
    paramInt1 = super.read(paramArrayOfByte, paramInt1, paramInt2);
    if (paramInt1 != -1)
    {
      this.byteRead += paramInt1;
      this.handler.onProgress(paramInt1, this.byteRead, this.totalSize);
    }
    return paramInt1;
  }

  public void setSwitch(AtomicBoolean paramAtomicBoolean)
  {
    this.isCancel = paramAtomicBoolean;
  }

  public long skip(long paramLong)
    throws IOException
  {
    if ((this.isCancel != null) && (this.isCancel.get()))
    {
      super.close();
      throw new InterruptedIOException();
    }
    return super.skip(paramLong);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.sdk.android.oss.model.MeasuableInputStream
 * JD-Core Version:    0.6.2
 */