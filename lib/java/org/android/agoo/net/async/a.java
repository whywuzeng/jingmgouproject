package org.android.agoo.net.async;

import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPInputStream;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolException;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.RedirectHandler;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.SyncBasicHttpContext;

class a
{
  private static final int a = 10;
  private static final int b = 10000;
  private static final int c = 3;
  private static final int d = 8192;
  private static final String e = "Accept-Encoding";
  private static final String f = "gzip";
  private static int g = 10;
  private static int h = 10000;
  private final DefaultHttpClient i;
  private final HttpContext j;
  private final Map<String, String> k;

  public a()
  {
    BasicHttpParams localBasicHttpParams = new BasicHttpParams();
    ConnManagerParams.setTimeout(localBasicHttpParams, h);
    ConnManagerParams.setMaxConnectionsPerRoute(localBasicHttpParams, new ConnPerRouteBean(g));
    ConnManagerParams.setMaxTotalConnections(localBasicHttpParams, 10);
    HttpConnectionParams.setSoTimeout(localBasicHttpParams, h);
    HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, h);
    HttpConnectionParams.setTcpNoDelay(localBasicHttpParams, true);
    HttpConnectionParams.setSocketBufferSize(localBasicHttpParams, 8192);
    HttpProtocolParams.setVersion(localBasicHttpParams, HttpVersion.HTTP_1_1);
    HttpProtocolParams.setUserAgent(localBasicHttpParams, String.format("Agoo-sdk-%s", new Object[] { Double.valueOf(2.0D) }));
    this.k = new HashMap();
    this.j = new SyncBasicHttpContext(new BasicHttpContext());
    this.i = new DefaultHttpClient(localBasicHttpParams);
    this.i.addRequestInterceptor(new HttpRequestInterceptor()
    {
      public final void process(HttpRequest paramAnonymousHttpRequest, HttpContext paramAnonymousHttpContext)
      {
        if (!paramAnonymousHttpRequest.containsHeader("Accept-Encoding"))
          paramAnonymousHttpRequest.addHeader("Accept-Encoding", "gzip");
        paramAnonymousHttpContext = a.a(a.this).keySet().iterator();
        while (paramAnonymousHttpContext.hasNext())
        {
          String str = (String)paramAnonymousHttpContext.next();
          paramAnonymousHttpRequest.addHeader(str, (String)a.a(a.this).get(str));
        }
      }
    });
    this.i.addResponseInterceptor(new HttpResponseInterceptor()
    {
      public final void process(HttpResponse paramAnonymousHttpResponse, HttpContext paramAnonymousHttpContext)
      {
        paramAnonymousHttpContext = paramAnonymousHttpResponse.getEntity();
        if (paramAnonymousHttpContext == null);
        while (true)
        {
          return;
          paramAnonymousHttpContext = paramAnonymousHttpContext.getContentEncoding();
          if (paramAnonymousHttpContext != null)
          {
            paramAnonymousHttpContext = paramAnonymousHttpContext.getElements();
            int j = paramAnonymousHttpContext.length;
            int i = 0;
            while (i < j)
            {
              if (paramAnonymousHttpContext[i].getName().equalsIgnoreCase("gzip"))
              {
                paramAnonymousHttpResponse.setEntity(new a.a(paramAnonymousHttpResponse.getEntity()));
                return;
              }
              i += 1;
            }
          }
        }
      }
    });
    this.i.setRedirectHandler(new RedirectHandler()
    {
      String a(HttpResponse paramAnonymousHttpResponse)
      {
        StringBuffer localStringBuffer = new StringBuffer();
        paramAnonymousHttpResponse = paramAnonymousHttpResponse.getAllHeaders();
        int i = 0;
        while (i < paramAnonymousHttpResponse.length)
        {
          localStringBuffer.append(paramAnonymousHttpResponse[i].getName() + "==" + paramAnonymousHttpResponse[i].getValue());
          i += 1;
        }
        return localStringBuffer.toString();
      }

      public URI getLocationURI(HttpResponse paramAnonymousHttpResponse, HttpContext paramAnonymousHttpContext)
        throws ProtocolException
      {
        Log.d("httpClient", "getLocationURI[" + a(paramAnonymousHttpResponse) + "]");
        return null;
      }

      public boolean isRedirectRequested(HttpResponse paramAnonymousHttpResponse, HttpContext paramAnonymousHttpContext)
      {
        Log.d("httpClient", "isRedirectRequested[" + a(paramAnonymousHttpResponse) + "]");
        return false;
      }
    });
  }

  protected final String a(String paramString, RequestParams paramRequestParams)
  {
    String str = paramString;
    if (paramRequestParams != null)
    {
      paramRequestParams = paramRequestParams.getParamString();
      str = paramString + "?" + paramRequestParams;
    }
    return str;
  }

  public final void addHeader(String paramString1, String paramString2)
  {
    this.k.put(paramString1, paramString2);
  }

  public final void addRequestInterceptor(HttpRequestInterceptor paramHttpRequestInterceptor)
  {
    this.i.addRequestInterceptor(paramHttpRequestInterceptor);
  }

  public final DefaultHttpClient getHttpClient()
  {
    return this.i;
  }

  public final HttpContext getHttpContext()
  {
    return this.j;
  }

  public final void setBasicAuth(String paramString1, String paramString2)
  {
    setBasicAuth(paramString1, paramString2, AuthScope.ANY);
  }

  public final void setBasicAuth(String paramString1, String paramString2, AuthScope paramAuthScope)
  {
    paramString1 = new UsernamePasswordCredentials(paramString1, paramString2);
    this.i.getCredentialsProvider().setCredentials(paramAuthScope, paramString1);
  }

  public final void setCookieStore(CookieStore paramCookieStore)
  {
    this.j.setAttribute("http.cookie-store", paramCookieStore);
  }

  public final void setSSLSocketFactory(SSLSocketFactory paramSSLSocketFactory)
  {
    this.i.getConnectionManager().getSchemeRegistry().register(new Scheme("https", paramSSLSocketFactory, 443));
  }

  public final void setTimeout(int paramInt)
  {
    HttpParams localHttpParams = this.i.getParams();
    ConnManagerParams.setTimeout(localHttpParams, paramInt);
    HttpConnectionParams.setSoTimeout(localHttpParams, paramInt);
    HttpConnectionParams.setConnectionTimeout(localHttpParams, paramInt);
  }

  public final void setUserAgent(String paramString)
  {
    HttpProtocolParams.setUserAgent(this.i.getParams(), paramString);
  }

  private static final class a extends HttpEntityWrapper
  {
    public a(HttpEntity paramHttpEntity)
    {
      super();
    }

    public final InputStream getContent()
      throws IOException
    {
      return new GZIPInputStream(this.wrappedEntity.getContent());
    }

    public final long getContentLength()
    {
      return -1L;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.net.async.a
 * JD-Core Version:    0.6.2
 */