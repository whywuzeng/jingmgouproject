package com.squareup.okhttp;

import com.squareup.okhttp.internal.Internal;
import com.squareup.okhttp.internal.NamedRunnable;
import com.squareup.okhttp.internal.http.HttpEngine;
import com.squareup.okhttp.internal.http.RequestException;
import com.squareup.okhttp.internal.http.RouteException;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Call
{
  volatile boolean canceled;
  private final OkHttpClient client;
  HttpEngine engine;
  private boolean executed;
  Request originalRequest;

  protected Call(OkHttpClient paramOkHttpClient, Request paramRequest)
  {
    this.client = paramOkHttpClient.copyWithDefaults();
    this.originalRequest = paramRequest;
  }

  private Response getResponseWithInterceptorChain(boolean paramBoolean)
    throws IOException
  {
    return new ApplicationInterceptorChain(0, this.originalRequest, paramBoolean).proceed(this.originalRequest);
  }

  private String toLoggableString()
  {
    if (this.canceled);
    for (String str = "canceled call"; ; str = "call")
    {
      HttpUrl localHttpUrl = this.originalRequest.httpUrl().resolve("/...");
      return str + " to " + localHttpUrl;
    }
  }

  public void cancel()
  {
    this.canceled = true;
    if (this.engine != null)
      this.engine.disconnect();
  }

  public void enqueue(Callback paramCallback)
  {
    enqueue(paramCallback, false);
  }

  void enqueue(Callback paramCallback, boolean paramBoolean)
  {
    try
    {
      if (this.executed)
        throw new IllegalStateException("Already Executed");
    }
    finally
    {
    }
    this.executed = true;
    this.client.getDispatcher().enqueue(new AsyncCall(paramCallback, paramBoolean, null));
  }

  public Response execute()
    throws IOException
  {
    try
    {
      if (this.executed)
        throw new IllegalStateException("Already Executed");
    }
    finally
    {
    }
    this.executed = true;
    try
    {
      this.client.getDispatcher().executed(this);
      Response localResponse1 = getResponseWithInterceptorChain(false);
      if (localResponse1 == null)
        throw new IOException("Canceled");
    }
    finally
    {
      this.client.getDispatcher().finished(this);
    }
    this.client.getDispatcher().finished(this);
    return localResponse2;
  }

  Response getResponse(Request paramRequest, boolean paramBoolean)
    throws IOException
  {
    Object localObject2 = paramRequest.body();
    Object localObject1 = paramRequest;
    int i;
    if (localObject2 != null)
    {
      paramRequest = paramRequest.newBuilder();
      localObject1 = ((RequestBody)localObject2).contentType();
      if (localObject1 != null)
        paramRequest.header("Content-Type", ((MediaType)localObject1).toString());
      long l = ((RequestBody)localObject2).contentLength();
      if (l != -1L)
      {
        paramRequest.header("Content-Length", Long.toString(l));
        paramRequest.removeHeader("Transfer-Encoding");
        localObject1 = paramRequest.build();
      }
    }
    else
    {
      this.engine = new HttpEngine(this.client, (Request)localObject1, false, false, paramBoolean, null, null, null, null);
      i = 0;
    }
    while (true)
    {
      if (this.canceled)
      {
        this.engine.releaseConnection();
        throw new IOException("Canceled");
        paramRequest.header("Transfer-Encoding", "chunked");
        paramRequest.removeHeader("Content-Length");
        break;
      }
      try
      {
        this.engine.sendRequest();
        this.engine.readResponse();
        paramRequest = this.engine.getResponse();
        localObject1 = this.engine.followUpRequest();
        if (localObject1 == null)
        {
          if (!paramBoolean)
            this.engine.releaseConnection();
          return paramRequest;
        }
      }
      catch (RequestException paramRequest)
      {
        throw paramRequest.getCause();
      }
      catch (RouteException paramRequest)
      {
        localObject1 = this.engine.recover(paramRequest);
        if (localObject1 != null)
        {
          this.engine = ((HttpEngine)localObject1);
          continue;
        }
        throw paramRequest.getLastConnectException();
      }
      catch (IOException paramRequest)
      {
        localObject1 = this.engine.recover(paramRequest, null);
        if (localObject1 != null)
        {
          this.engine = ((HttpEngine)localObject1);
          continue;
        }
        throw paramRequest;
      }
      i += 1;
      if (i > 20)
        throw new ProtocolException("Too many follow-up requests: " + i);
      if (!this.engine.sameConnection(((Request)localObject1).httpUrl()))
        this.engine.releaseConnection();
      localObject2 = this.engine.close();
      this.engine = new HttpEngine(this.client, (Request)localObject1, false, false, paramBoolean, (Connection)localObject2, null, null, paramRequest);
    }
  }

  public boolean isCanceled()
  {
    return this.canceled;
  }

  Object tag()
  {
    return this.originalRequest.tag();
  }

  class ApplicationInterceptorChain
    implements Interceptor.Chain
  {
    private final boolean forWebSocket;
    private final int index;
    private final Request request;

    ApplicationInterceptorChain(int paramRequest, Request paramBoolean, boolean arg4)
    {
      this.index = paramRequest;
      this.request = paramBoolean;
      boolean bool;
      this.forWebSocket = bool;
    }

    public Connection connection()
    {
      return null;
    }

    public Response proceed(Request paramRequest)
      throws IOException
    {
      if (this.index < Call.this.client.interceptors().size())
      {
        paramRequest = new ApplicationInterceptorChain(Call.this, this.index + 1, paramRequest, this.forWebSocket);
        return ((Interceptor)Call.this.client.interceptors().get(this.index)).intercept(paramRequest);
      }
      return Call.this.getResponse(paramRequest, this.forWebSocket);
    }

    public Request request()
    {
      return this.request;
    }
  }

  final class AsyncCall extends NamedRunnable
  {
    private final boolean forWebSocket;
    private final Callback responseCallback;

    private AsyncCall(Callback paramBoolean, boolean arg3)
    {
      super(new Object[] { Call.this.originalRequest.urlString() });
      this.responseCallback = paramBoolean;
      boolean bool;
      this.forWebSocket = bool;
    }

    void cancel()
    {
      Call.this.cancel();
    }

    protected void execute()
    {
      int j = 0;
      int i = j;
      try
      {
        Response localResponse = Call.this.getResponseWithInterceptorChain(this.forWebSocket);
        i = j;
        if (Call.this.canceled)
        {
          i = 1;
          this.responseCallback.onFailure(Call.this.originalRequest, new IOException("Canceled"));
        }
        while (true)
        {
          return;
          i = 1;
          this.responseCallback.onResponse(localResponse);
        }
      }
      catch (IOException localIOException)
      {
        if (i != 0)
          Internal.logger.log(Level.INFO, "Callback failure for " + Call.this.toLoggableString(), localIOException);
        while (true)
        {
          return;
          this.responseCallback.onFailure(Call.this.engine.getRequest(), localIOException);
        }
      }
      finally
      {
        Call.this.client.getDispatcher().finished(this);
      }
    }

    Call get()
    {
      return Call.this;
    }

    String host()
    {
      return Call.this.originalRequest.httpUrl().host();
    }

    Request request()
    {
      return Call.this.originalRequest;
    }

    Object tag()
    {
      return Call.this.originalRequest.tag();
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.Call
 * JD-Core Version:    0.6.2
 */