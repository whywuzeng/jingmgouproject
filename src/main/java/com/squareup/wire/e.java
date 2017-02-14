package com.squareup.wire;

import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.io.StreamCorruptedException;

final class e
  implements Serializable
{
  private static final long a = 0L;
  private final byte[] b;
  private final Class<? extends Message> c;

  public e(Message paramMessage, Class<? extends Message> paramClass)
  {
    this.b = paramMessage.toByteArray();
    this.c = paramClass;
  }

  Object a()
    throws ObjectStreamException
  {
    try
    {
      Message localMessage = new Wire(new Class[0]).parseFrom(this.b, this.c);
      return localMessage;
    }
    catch (IOException localIOException)
    {
      throw new StreamCorruptedException(localIOException.getMessage());
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.squareup.wire.e
 * JD-Core Version:    0.6.2
 */