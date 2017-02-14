package com.squareup.okhttp.internal.http;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class RouteException extends Exception
{
  private static final Method addSuppressedExceptionMethod;
  private IOException lastException;

  static
  {
    try
    {
      Method localMethod = Throwable.class.getDeclaredMethod("addSuppressed", new Class[] { Throwable.class });
      addSuppressedExceptionMethod = localMethod;
      return;
    }
    catch (Exception localException)
    {
      while (true)
        Object localObject = null;
    }
  }

  public RouteException(IOException paramIOException)
  {
    super(paramIOException);
    this.lastException = paramIOException;
  }

  private void addSuppressedIfPossible(IOException paramIOException1, IOException paramIOException2)
  {
    if (addSuppressedExceptionMethod != null);
    try
    {
      addSuppressedExceptionMethod.invoke(paramIOException1, new Object[] { paramIOException2 });
      return;
    }
    catch (InvocationTargetException paramIOException1)
    {
    }
    catch (IllegalAccessException paramIOException1)
    {
    }
  }

  public void addConnectException(IOException paramIOException)
  {
    addSuppressedIfPossible(paramIOException, this.lastException);
    this.lastException = paramIOException;
  }

  public IOException getLastConnectException()
  {
    return this.lastException;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.http.RouteException
 * JD-Core Version:    0.6.2
 */