package net.tsz.afinal.http;

import java.io.IOException;
import java.net.UnknownHostException;
import net.tsz.afinal.http.entityhandler.StringEntityHandler;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.protocol.HttpContext;

public class SyncRequestHandler
{
  private String charset;
  private final AbstractHttpClient client;
  private final HttpContext context;
  private final StringEntityHandler entityHandler = new StringEntityHandler();
  private int executionCount = 0;

  public SyncRequestHandler(AbstractHttpClient paramAbstractHttpClient, HttpContext paramHttpContext, String paramString)
  {
    this.client = paramAbstractHttpClient;
    this.context = paramHttpContext;
    this.charset = paramString;
  }

  private Object makeRequestWithRetries(HttpUriRequest paramHttpUriRequest)
    throws IOException
  {
    boolean bool = true;
    Object localObject = null;
    HttpRequestRetryHandler localHttpRequestRetryHandler = this.client.getHttpRequestRetryHandler();
    while (true)
    {
      if (!bool)
      {
        if (localObject == null)
          break;
        throw ((Throwable)localObject);
      }
      try
      {
        localObject = this.client.execute(paramHttpUriRequest, this.context);
        localObject = this.entityHandler.handleEntity(((HttpResponse)localObject).getEntity(), null, this.charset);
        return localObject;
      }
      catch (UnknownHostException localUnknownHostException)
      {
        i = this.executionCount + 1;
        this.executionCount = i;
        bool = localHttpRequestRetryHandler.retryRequest(localUnknownHostException, i, this.context);
      }
      catch (IOException localIOException1)
      {
        i = this.executionCount + 1;
        this.executionCount = i;
        bool = localHttpRequestRetryHandler.retryRequest(localIOException1, i, this.context);
      }
      catch (NullPointerException localNullPointerException)
      {
        IOException localIOException2 = new IOException("NPE in HttpClient" + localNullPointerException.getMessage());
        i = this.executionCount + 1;
        this.executionCount = i;
        bool = localHttpRequestRetryHandler.retryRequest(localIOException2, i, this.context);
      }
      catch (Exception localException)
      {
        IOException localIOException3 = new IOException("Exception" + localException.getMessage());
        int i = this.executionCount + 1;
        this.executionCount = i;
        bool = localHttpRequestRetryHandler.retryRequest(localIOException3, i, this.context);
      }
    }
    throw new IOException("未知网络错误");
  }

  public Object sendRequest(HttpUriRequest[] paramArrayOfHttpUriRequest)
  {
    try
    {
      paramArrayOfHttpUriRequest = makeRequestWithRetries(paramArrayOfHttpUriRequest[0]);
      return paramArrayOfHttpUriRequest;
    }
    catch (IOException paramArrayOfHttpUriRequest)
    {
      paramArrayOfHttpUriRequest.printStackTrace();
    }
    return null;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     net.tsz.afinal.http.SyncRequestHandler
 * JD-Core Version:    0.6.2
 */