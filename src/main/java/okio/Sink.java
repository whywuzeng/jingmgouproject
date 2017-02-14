package okio;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;

public abstract interface Sink extends Closeable, Flushable
{
  public abstract void close()
    throws IOException;

  public abstract void flush()
    throws IOException;

  public abstract Timeout timeout();

  public abstract void write(Buffer paramBuffer, long paramLong)
    throws IOException;
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     okio.Sink
 * JD-Core Version:    0.6.2
 */