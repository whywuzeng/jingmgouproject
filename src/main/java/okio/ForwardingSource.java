package okio;

import java.io.IOException;

public abstract class ForwardingSource
  implements Source
{
  private final Source delegate;

  public ForwardingSource(Source paramSource)
  {
    if (paramSource == null)
      throw new IllegalArgumentException("delegate == null");
    this.delegate = paramSource;
  }

  public void close()
    throws IOException
  {
    this.delegate.close();
  }

  public final Source delegate()
  {
    return this.delegate;
  }

  public long read(Buffer paramBuffer, long paramLong)
    throws IOException
  {
    return this.delegate.read(paramBuffer, paramLong);
  }

  public Timeout timeout()
  {
    return this.delegate.timeout();
  }

  public String toString()
  {
    return getClass().getSimpleName() + "(" + this.delegate.toString() + ")";
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     okio.ForwardingSource
 * JD-Core Version:    0.6.2
 */