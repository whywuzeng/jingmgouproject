package com.umeng.message.proguard;

import java.io.Closeable;
import java.io.IOException;

public abstract interface aO extends Closeable
{
  public abstract void b(aB paramaB, long paramLong)
    throws IOException;

  public abstract void close()
    throws IOException;

  public abstract void s()
    throws IOException;

  public abstract aQ t();
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.aO
 * JD-Core Version:    0.6.2
 */