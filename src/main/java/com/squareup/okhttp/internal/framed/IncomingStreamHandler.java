package com.squareup.okhttp.internal.framed;

import java.io.IOException;

public abstract interface IncomingStreamHandler
{
  public static final IncomingStreamHandler REFUSE_INCOMING_STREAMS = new IncomingStreamHandler()
  {
    public void receive(FramedStream paramAnonymousFramedStream)
      throws IOException
    {
      paramAnonymousFramedStream.close(ErrorCode.REFUSED_STREAM);
    }
  };

  public abstract void receive(FramedStream paramFramedStream)
    throws IOException;
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.framed.IncomingStreamHandler
 * JD-Core Version:    0.6.2
 */