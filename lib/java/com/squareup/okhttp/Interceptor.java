package com.squareup.okhttp;

import java.io.IOException;

public abstract interface Interceptor
{
  public abstract Response intercept(Chain paramChain)
    throws IOException;

  public static abstract interface Chain
  {
    public abstract Connection connection();

    public abstract Response proceed(Request paramRequest)
      throws IOException;

    public abstract Request request();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.Interceptor
 * JD-Core Version:    0.6.2
 */