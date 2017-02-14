package com.squareup.okhttp.internal.http;

import java.io.IOException;

public final class RequestException extends Exception
{
  public RequestException(IOException paramIOException)
  {
    super(paramIOException);
  }

  public IOException getCause()
  {
    return (IOException)super.getCause();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.http.RequestException
 * JD-Core Version:    0.6.2
 */