package com.squareup.okhttp.internal.framed;

import com.squareup.okhttp.Protocol;
import okio.BufferedSink;
import okio.BufferedSource;

public abstract interface Variant
{
  public abstract Protocol getProtocol();

  public abstract FrameReader newReader(BufferedSource paramBufferedSource, boolean paramBoolean);

  public abstract FrameWriter newWriter(BufferedSink paramBufferedSink, boolean paramBoolean);
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.framed.Variant
 * JD-Core Version:    0.6.2
 */