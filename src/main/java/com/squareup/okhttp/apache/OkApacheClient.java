package com.squareup.okhttp.apache;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Request.Builder;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.RequestLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.params.AbstractHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

public final class OkApacheClient
  implements HttpClient
{
  private final OkHttpClient client;
  private final HttpParams params = new AbstractHttpParams()
  {
    public HttpParams copy()
    {
      throw new UnsupportedOperationException();
    }

    public Object getParameter(String paramAnonymousString)
    {
      if (paramAnonymousString.equals("http.route.default-proxy"))
      {
        paramAnonymousString = OkApacheClient.this.client.getProxy();
        if (paramAnonymousString == null)
          return null;
        paramAnonymousString = (InetSocketAddress)paramAnonymousString.address();
        return new HttpHost(paramAnonymousString.getHostName(), paramAnonymousString.getPort());
      }
      throw new IllegalArgumentException(paramAnonymousString);
    }

    public boolean removeParameter(String paramAnonymousString)
    {
      throw new UnsupportedOperationException();
    }

    public HttpParams setParameter(String paramAnonymousString, Object paramAnonymousObject)
    {
      if (paramAnonymousString.equals("http.route.default-proxy"))
      {
        paramAnonymousObject = (HttpHost)paramAnonymousObject;
        paramAnonymousString = null;
        if (paramAnonymousObject != null)
          paramAnonymousString = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(paramAnonymousObject.getHostName(), paramAnonymousObject.getPort()));
        OkApacheClient.this.client.setProxy(paramAnonymousString);
        return this;
      }
      throw new IllegalArgumentException(paramAnonymousString);
    }
  };

  public OkApacheClient()
  {
    this(new OkHttpClient());
  }

  public OkApacheClient(OkHttpClient paramOkHttpClient)
  {
    this.client = paramOkHttpClient;
  }

  private static void consumeContentQuietly(HttpResponse paramHttpResponse)
  {
    try
    {
      paramHttpResponse.getEntity().consumeContent();
      return;
    }
    catch (Throwable paramHttpResponse)
    {
    }
  }

  private static Request transformRequest(HttpRequest paramHttpRequest)
  {
    Request.Builder localBuilder = new Request.Builder();
    Object localObject1 = paramHttpRequest.getRequestLine();
    String str1 = ((RequestLine)localObject1).getMethod();
    localBuilder.url(((RequestLine)localObject1).getUri());
    Object localObject2 = null;
    localObject1 = paramHttpRequest.getAllHeaders();
    int j = localObject1.length;
    int i = 0;
    if (i < j)
    {
      Object localObject3 = localObject1[i];
      String str2 = localObject3.getName();
      if ("Content-Type".equalsIgnoreCase(str2))
        localObject2 = localObject3.getValue();
      while (true)
      {
        i += 1;
        break;
        localBuilder.header(str2, localObject3.getValue());
      }
    }
    localObject1 = null;
    if ((paramHttpRequest instanceof HttpEntityEnclosingRequest))
    {
      localObject1 = ((HttpEntityEnclosingRequest)paramHttpRequest).getEntity();
      if (localObject1 == null)
        break label200;
      paramHttpRequest = new HttpEntityBody((HttpEntity)localObject1, (String)localObject2);
      localObject2 = ((HttpEntity)localObject1).getContentEncoding();
      localObject1 = paramHttpRequest;
      if (localObject2 != null)
        localBuilder.header(((Header)localObject2).getName(), ((Header)localObject2).getValue());
    }
    label200: for (localObject1 = paramHttpRequest; ; localObject1 = RequestBody.create(null, new byte[0]))
    {
      localBuilder.method(str1, (RequestBody)localObject1);
      return localBuilder.build();
    }
  }

  private static HttpResponse transformResponse(Response paramResponse)
    throws IOException
  {
    int i = paramResponse.code();
    Object localObject1 = paramResponse.message();
    localObject1 = new BasicHttpResponse(HttpVersion.HTTP_1_1, i, (String)localObject1);
    Object localObject2 = paramResponse.body();
    localObject2 = new InputStreamEntity(((ResponseBody)localObject2).byteStream(), ((ResponseBody)localObject2).contentLength());
    ((BasicHttpResponse)localObject1).setEntity((HttpEntity)localObject2);
    paramResponse = paramResponse.headers();
    i = 0;
    int j = paramResponse.size();
    if (i < j)
    {
      String str1 = paramResponse.name(i);
      String str2 = paramResponse.value(i);
      ((BasicHttpResponse)localObject1).addHeader(str1, str2);
      if ("Content-Type".equalsIgnoreCase(str1))
        ((InputStreamEntity)localObject2).setContentType(str2);
      while (true)
      {
        i += 1;
        break;
        if ("Content-Encoding".equalsIgnoreCase(str1))
          ((InputStreamEntity)localObject2).setContentEncoding(str2);
      }
    }
    return localObject1;
  }

  public <T> T execute(HttpHost paramHttpHost, HttpRequest paramHttpRequest, ResponseHandler<? extends T> paramResponseHandler)
    throws IOException
  {
    return execute(paramHttpHost, paramHttpRequest, paramResponseHandler, null);
  }

  public <T> T execute(HttpHost paramHttpHost, HttpRequest paramHttpRequest, ResponseHandler<? extends T> paramResponseHandler, HttpContext paramHttpContext)
    throws IOException
  {
    paramHttpHost = execute(paramHttpHost, paramHttpRequest, paramHttpContext);
    try
    {
      paramHttpRequest = paramResponseHandler.handleResponse(paramHttpHost);
      return paramHttpRequest;
    }
    finally
    {
      consumeContentQuietly(paramHttpHost);
    }
    throw paramHttpRequest;
  }

  public <T> T execute(HttpUriRequest paramHttpUriRequest, ResponseHandler<? extends T> paramResponseHandler)
    throws IOException
  {
    return execute(null, paramHttpUriRequest, paramResponseHandler, null);
  }

  public <T> T execute(HttpUriRequest paramHttpUriRequest, ResponseHandler<? extends T> paramResponseHandler, HttpContext paramHttpContext)
    throws IOException
  {
    return execute(null, paramHttpUriRequest, paramResponseHandler, paramHttpContext);
  }

  public HttpResponse execute(HttpHost paramHttpHost, HttpRequest paramHttpRequest)
    throws IOException
  {
    return execute(paramHttpHost, paramHttpRequest, (HttpContext)null);
  }

  public HttpResponse execute(HttpHost paramHttpHost, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws IOException
  {
    paramHttpHost = transformRequest(paramHttpRequest);
    return transformResponse(this.client.newCall(paramHttpHost).execute());
  }

  public HttpResponse execute(HttpUriRequest paramHttpUriRequest)
    throws IOException
  {
    return execute(null, paramHttpUriRequest, (HttpContext)null);
  }

  public HttpResponse execute(HttpUriRequest paramHttpUriRequest, HttpContext paramHttpContext)
    throws IOException
  {
    return execute(null, paramHttpUriRequest, paramHttpContext);
  }

  public ClientConnectionManager getConnectionManager()
  {
    throw new UnsupportedOperationException();
  }

  public HttpParams getParams()
  {
    return this.params;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.apache.OkApacheClient
 * JD-Core Version:    0.6.2
 */