package net.tsz.afinal.http;

import android.os.SystemClock;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashSet;
import javax.net.ssl.SSLHandshakeException;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.protocol.HttpContext;

public class RetryHandler
  implements HttpRequestRetryHandler
{
  private static final int RETRY_SLEEP_TIME_MILLIS = 1000;
  private static HashSet<Class<?>> exceptionBlacklist;
  private static HashSet<Class<?>> exceptionWhitelist = new HashSet();
  private final int maxRetries;

  static
  {
    exceptionBlacklist = new HashSet();
    exceptionWhitelist.add(NoHttpResponseException.class);
    exceptionWhitelist.add(UnknownHostException.class);
    exceptionWhitelist.add(SocketException.class);
    exceptionBlacklist.add(InterruptedIOException.class);
    exceptionBlacklist.add(SSLHandshakeException.class);
  }

  public RetryHandler(int paramInt)
  {
    this.maxRetries = paramInt;
  }

  public boolean retryRequest(IOException paramIOException, int paramInt, HttpContext paramHttpContext)
  {
    boolean bool1 = true;
    Boolean localBoolean = (Boolean)paramHttpContext.getAttribute("http.request_sent");
    int i;
    if ((localBoolean != null) && (localBoolean.booleanValue()))
    {
      i = 1;
      if (paramInt <= this.maxRetries)
        break label105;
      bool1 = false;
      label43: bool2 = bool1;
      if (bool1)
      {
        paramHttpContext = (HttpUriRequest)paramHttpContext.getAttribute("http.request");
        if ((paramHttpContext == null) || ("POST".equals(paramHttpContext.getMethod())))
          break label154;
      }
    }
    label154: for (boolean bool2 = true; ; bool2 = false)
    {
      if (!bool2)
        break label160;
      SystemClock.sleep(1000L);
      return bool2;
      i = 0;
      break;
      label105: if (exceptionBlacklist.contains(paramIOException.getClass()))
      {
        bool1 = false;
        break label43;
      }
      if (exceptionWhitelist.contains(paramIOException.getClass()))
      {
        bool1 = true;
        break label43;
      }
      if (i != 0)
        break label43;
      bool1 = true;
      break label43;
    }
    label160: paramIOException.printStackTrace();
    return bool2;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     net.tsz.afinal.http.RetryHandler
 * JD-Core Version:    0.6.2
 */