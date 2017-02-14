package org.android.agoo.net.async;

import android.content.Context;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HttpContext;

public class AsyncHttpClient extends a
{
  private static final String c = "AsyncHttp.client";
  private final ThreadPoolExecutor a = (ThreadPoolExecutor)Executors.newFixedThreadPool(5);
  private final Map<Context, List<WeakReference<Future<?>>>> b = new WeakHashMap();

  private HttpEntity a(RequestParams paramRequestParams)
  {
    HttpEntity localHttpEntity = null;
    if (paramRequestParams != null)
      localHttpEntity = paramRequestParams.getEntity();
    return localHttpEntity;
  }

  private void a(Context paramContext, DefaultHttpClient paramDefaultHttpClient, HttpContext paramHttpContext, HttpHost paramHttpHost, HttpUriRequest paramHttpUriRequest, String paramString, AsyncHttpResponseHandler paramAsyncHttpResponseHandler)
  {
    if (paramString != null)
      paramHttpUriRequest.addHeader("Content-Type", paramString);
    paramHttpHost = this.a.submit(new b(paramContext, paramDefaultHttpClient, paramHttpContext, paramHttpHost, paramHttpUriRequest, paramAsyncHttpResponseHandler));
    if (paramContext != null)
    {
      paramHttpContext = (List)this.b.get(paramContext);
      paramDefaultHttpClient = paramHttpContext;
      if (paramHttpContext == null)
      {
        paramDefaultHttpClient = new LinkedList();
        this.b.put(paramContext, paramDefaultHttpClient);
      }
      paramDefaultHttpClient.add(new WeakReference(paramHttpHost));
    }
  }

  public final void cancelRequests(Context paramContext, boolean paramBoolean)
  {
    Object localObject = (List)this.b.get(paramContext);
    if (localObject != null)
    {
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        Future localFuture = (Future)((WeakReference)((Iterator)localObject).next()).get();
        if (localFuture != null)
          localFuture.cancel(paramBoolean);
      }
    }
    this.b.remove(paramContext);
  }

  public final void get(Context paramContext, String paramString, AsyncHttpResponseHandler paramAsyncHttpResponseHandler)
  {
    get(paramContext, null, paramString, null, null, paramAsyncHttpResponseHandler);
  }

  public final void get(Context paramContext, String paramString, RequestParams paramRequestParams, AsyncHttpResponseHandler paramAsyncHttpResponseHandler)
  {
    get(paramContext, null, paramString, null, paramRequestParams, paramAsyncHttpResponseHandler);
  }

  public final void get(Context paramContext, String paramString, HttpHost paramHttpHost, AsyncHttpResponseHandler paramAsyncHttpResponseHandler)
  {
    get(paramContext, paramHttpHost, paramString, null, null, paramAsyncHttpResponseHandler);
  }

  public final void get(Context paramContext, HttpHost paramHttpHost, String paramString, RequestParams paramRequestParams, AsyncHttpResponseHandler paramAsyncHttpResponseHandler)
  {
    get(paramContext, paramHttpHost, paramString, null, paramRequestParams, paramAsyncHttpResponseHandler);
  }

  public final void get(Context paramContext, HttpHost paramHttpHost, String paramString, Header[] paramArrayOfHeader, RequestParams paramRequestParams, AsyncHttpResponseHandler paramAsyncHttpResponseHandler)
  {
    paramString = new HttpGet(a(paramString, paramRequestParams));
    if (paramArrayOfHeader != null)
      paramString.setHeaders(paramArrayOfHeader);
    a(paramContext, getHttpClient(), getHttpContext(), paramHttpHost, paramString, null, paramAsyncHttpResponseHandler);
  }

  public final void post(Context paramContext, String paramString1, RequestParams paramRequestParams, String paramString2, AsyncHttpResponseHandler paramAsyncHttpResponseHandler)
  {
    post(paramContext, paramString1, null, paramRequestParams, paramString2, paramAsyncHttpResponseHandler);
  }

  public final void post(Context paramContext, String paramString1, Header[] paramArrayOfHeader, RequestParams paramRequestParams, String paramString2, AsyncHttpResponseHandler paramAsyncHttpResponseHandler)
  {
    paramString1 = new HttpPost(paramString1);
    if (paramRequestParams != null)
      paramString1.setEntity(a(paramRequestParams));
    if (paramArrayOfHeader != null)
      paramString1.setHeaders(paramArrayOfHeader);
    a(paramContext, getHttpClient(), getHttpContext(), null, paramString1, paramString2, paramAsyncHttpResponseHandler);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.net.async.AsyncHttpClient
 * JD-Core Version:    0.6.2
 */