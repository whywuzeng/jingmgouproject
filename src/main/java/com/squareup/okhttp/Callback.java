package com.squareup.okhttp;

import java.io.IOException;

public abstract interface Callback
{
  public abstract void onFailure(Request paramRequest, IOException paramIOException);

  public abstract void onResponse(Response paramResponse)
    throws IOException;
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.Callback
 * JD-Core Version:    0.6.2
 */