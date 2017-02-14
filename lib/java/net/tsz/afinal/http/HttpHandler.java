package net.tsz.afinal.http;

import android.os.SystemClock;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import net.tsz.afinal.core.AsyncTask;
import net.tsz.afinal.http.entityhandler.EntityCallBack;
import net.tsz.afinal.http.entityhandler.FileEntityHandler;
import net.tsz.afinal.http.entityhandler.StringEntityHandler;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.protocol.HttpContext;

public class HttpHandler<T> extends AsyncTask<Object, Object, Object>
  implements EntityCallBack
{
  private static final int UPDATE_FAILURE = 3;
  private static final int UPDATE_LOADING = 2;
  private static final int UPDATE_START = 1;
  private static final int UPDATE_SUCCESS = 4;
  private final AjaxCallBack<T> callback;
  private String charset;
  private final AbstractHttpClient client;
  private final HttpContext context;
  private int executionCount = 0;
  private boolean isResume = false;
  private final FileEntityHandler mFileEntityHandler = new FileEntityHandler();
  private final StringEntityHandler mStrEntityHandler = new StringEntityHandler();
  private String targetUrl = null;
  private long time;

  public HttpHandler(AbstractHttpClient paramAbstractHttpClient, HttpContext paramHttpContext, AjaxCallBack<T> paramAjaxCallBack, String paramString)
  {
    this.client = paramAbstractHttpClient;
    this.context = paramHttpContext;
    this.callback = paramAjaxCallBack;
    this.charset = paramString;
  }

  private void handleResponse(HttpResponse paramHttpResponse)
  {
    StatusLine localStatusLine = paramHttpResponse.getStatusLine();
    Object localObject;
    if (localStatusLine.getStatusCode() >= 300)
    {
      localObject = "response status error code:" + localStatusLine.getStatusCode();
      paramHttpResponse = (HttpResponse)localObject;
      if (localStatusLine.getStatusCode() == 416)
      {
        paramHttpResponse = (HttpResponse)localObject;
        if (this.isResume)
          paramHttpResponse = localObject + " \n maybe you have download complete.";
      }
      publishProgress(new Object[] { Integer.valueOf(3), new HttpResponseException(localStatusLine.getStatusCode(), localStatusLine.getReasonPhrase()), Integer.valueOf(localStatusLine.getStatusCode()), paramHttpResponse });
      return;
    }
    while (true)
    {
      try
      {
        localObject = paramHttpResponse.getEntity();
        paramHttpResponse = null;
        if (localObject != null)
        {
          this.time = SystemClock.uptimeMillis();
          if (this.targetUrl != null)
            paramHttpResponse = this.mFileEntityHandler.handleEntity((HttpEntity)localObject, this, this.targetUrl, this.isResume);
        }
        else
        {
          publishProgress(new Object[] { Integer.valueOf(4), paramHttpResponse });
          return;
        }
      }
      catch (IOException paramHttpResponse)
      {
        publishProgress(new Object[] { Integer.valueOf(3), paramHttpResponse, Integer.valueOf(0), paramHttpResponse.getMessage() });
        return;
      }
      paramHttpResponse = this.mStrEntityHandler.handleEntity((HttpEntity)localObject, this, this.charset);
    }
  }

  private void makeRequestWithRetries(HttpUriRequest paramHttpUriRequest)
    throws IOException
  {
    if ((this.isResume) && (this.targetUrl != null))
    {
      localObject = new File(this.targetUrl);
      long l2 = 0L;
      long l1 = l2;
      if (((File)localObject).isFile())
      {
        l1 = l2;
        if (((File)localObject).exists())
          l1 = ((File)localObject).length();
      }
      if (l1 > 0L)
        paramHttpUriRequest.setHeader("RANGE", "bytes=" + l1 + "-");
    }
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
        if (!isCancelled())
        {
          localObject = this.client.execute(paramHttpUriRequest, this.context);
          if (!isCancelled())
            handleResponse((HttpResponse)localObject);
        }
        return;
      }
      catch (UnknownHostException paramHttpUriRequest)
      {
        publishProgress(new Object[] { Integer.valueOf(3), paramHttpUriRequest, Integer.valueOf(0), "unknownHostException：can't resolve host" });
        return;
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

  public void callBack(long paramLong1, long paramLong2, boolean paramBoolean)
  {
    if ((this.callback != null) && (this.callback.isProgress()))
    {
      if (!paramBoolean)
        break label52;
      publishProgress(new Object[] { Integer.valueOf(2), Long.valueOf(paramLong1), Long.valueOf(paramLong2) });
    }
    label52: long l;
    do
    {
      return;
      l = SystemClock.uptimeMillis();
    }
    while (l - this.time < this.callback.getRate());
    this.time = l;
    publishProgress(new Object[] { Integer.valueOf(2), Long.valueOf(paramLong1), Long.valueOf(paramLong2) });
  }

  protected Object doInBackground(Object[] paramArrayOfObject)
  {
    if ((paramArrayOfObject != null) && (paramArrayOfObject.length == 3))
    {
      this.targetUrl = String.valueOf(paramArrayOfObject[1]);
      this.isResume = ((Boolean)paramArrayOfObject[2]).booleanValue();
    }
    try
    {
      publishProgress(new Object[] { Integer.valueOf(1) });
      makeRequestWithRetries((HttpUriRequest)paramArrayOfObject[0]);
      return null;
    }
    catch (IOException paramArrayOfObject)
    {
      while (true)
        publishProgress(new Object[] { Integer.valueOf(3), paramArrayOfObject, Integer.valueOf(0), paramArrayOfObject.getMessage() });
    }
  }

  public boolean isStop()
  {
    return this.mFileEntityHandler.isStop();
  }

  protected void onProgressUpdate(Object[] paramArrayOfObject)
  {
    switch (Integer.valueOf(String.valueOf(paramArrayOfObject[0])).intValue())
    {
    default:
    case 1:
    case 2:
    case 3:
    case 4:
    }
    while (true)
    {
      super.onProgressUpdate(paramArrayOfObject);
      return;
      if (this.callback != null)
      {
        this.callback.onStart();
        continue;
        if (this.callback != null)
        {
          this.callback.onLoading(Long.valueOf(String.valueOf(paramArrayOfObject[1])).longValue(), Long.valueOf(String.valueOf(paramArrayOfObject[2])).longValue());
          continue;
          if (this.callback != null)
          {
            this.callback.onFailure((Throwable)paramArrayOfObject[1], ((Integer)paramArrayOfObject[2]).intValue(), (String)paramArrayOfObject[3]);
            continue;
            if (this.callback != null)
              this.callback.onSuccess(paramArrayOfObject[1]);
          }
        }
      }
    }
  }

  public void stop()
  {
    this.mFileEntityHandler.setStop(true);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     net.tsz.afinal.http.HttpHandler
 * JD-Core Version:    0.6.2
 */