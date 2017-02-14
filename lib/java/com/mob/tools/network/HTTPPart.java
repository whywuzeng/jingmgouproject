package com.mob.tools.network;

import java.io.InputStream;
import org.apache.http.entity.InputStreamEntity;

public abstract class HTTPPart
{
  private OnReadListener listener;
  private long offset;

  protected abstract InputStream getInputStream()
    throws Throwable;

  public InputStreamEntity getInputStreamEntity()
    throws Throwable
  {
    ByteCounterInputStream localByteCounterInputStream = new ByteCounterInputStream(getInputStream());
    localByteCounterInputStream.setOnInputStreamReadListener(this.listener);
    if (this.offset > 0L)
      localByteCounterInputStream.skip(this.offset);
    return new InputStreamEntity(localByteCounterInputStream, length() - this.offset);
  }

  protected abstract long length()
    throws Throwable;

  public void setOffset(long paramLong)
  {
    this.offset = paramLong;
  }

  public void setOnReadListener(OnReadListener paramOnReadListener)
  {
    this.listener = paramOnReadListener;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.mob.tools.network.HTTPPart
 * JD-Core Version:    0.6.2
 */