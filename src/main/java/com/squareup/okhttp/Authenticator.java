package com.squareup.okhttp;

import java.io.IOException;
import java.net.Proxy;

public abstract interface Authenticator
{
  public abstract Request authenticate(Proxy paramProxy, Response paramResponse)
    throws IOException;

  public abstract Request authenticateProxy(Proxy paramProxy, Response paramResponse)
    throws IOException;
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.Authenticator
 * JD-Core Version:    0.6.2
 */