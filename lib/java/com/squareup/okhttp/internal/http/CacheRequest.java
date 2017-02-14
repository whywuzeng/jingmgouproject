package com.squareup.okhttp.internal.http;

import java.io.IOException;
import okio.Sink;

public abstract interface CacheRequest
{
  public abstract void abort();

  public abstract Sink body()
    throws IOException;
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.http.CacheRequest
 * JD-Core Version:    0.6.2
 */