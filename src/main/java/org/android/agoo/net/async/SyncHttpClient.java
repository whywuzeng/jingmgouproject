package org.android.agoo.net.async;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.message.proguard.bd;
import com.umeng.message.proguard.bl;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

public class SyncHttpClient extends a
{
  private static final String a = "SyncHttp.client";

  private final Map<String, String> a(Header[] paramArrayOfHeader)
  {
    HashMap localHashMap = new HashMap();
    int j = paramArrayOfHeader.length;
    int i = 0;
    if (i < j)
    {
      Object localObject = paramArrayOfHeader[i];
      String str = ((Header)localObject).getName();
      if (TextUtils.isEmpty(str));
      while (true)
      {
        i += 1;
        break;
        localObject = ((Header)localObject).getValue();
        if (!TextUtils.isEmpty((CharSequence)localObject))
          localHashMap.put(str.toLowerCase(), localObject);
      }
    }
    return localHashMap;
  }

  public final a get(Context paramContext, String paramString, RequestParams paramRequestParams)
    throws Throwable
  {
    return get(paramContext, null, paramString, paramRequestParams);
  }

  public final a get(Context paramContext, HttpHost paramHttpHost, String paramString, RequestParams paramRequestParams)
    throws Throwable
  {
    Object localObject = null;
    try
    {
      paramRequestParams = new HttpGet(a(paramString, paramRequestParams));
      try
      {
        if (bl.a(paramContext))
          break label147;
        bd.c("SyncHttp.client", "network connection error[" + paramRequestParams.getURI().toString() + "]");
        throw new RuntimeException("network connection error[" + paramRequestParams.getURI().toString() + "]");
      }
      catch (Throwable paramContext)
      {
        paramHttpHost = paramRequestParams;
      }
      if (paramHttpHost != null)
        bd.e("SyncHttp.client", "request url error:[" + paramHttpHost.getURI().toString() + "]", paramContext);
      while (true)
      {
        throw paramContext;
        label147: paramContext = getHttpClient();
        if (paramHttpHost != null)
          paramContext.getParams().setParameter("http.route.default-proxy", paramHttpHost);
        paramContext = paramContext.execute(paramRequestParams);
        paramHttpHost = new a();
        StatusLine localStatusLine = paramContext.getStatusLine();
        paramHttpHost.a = localStatusLine.getStatusCode();
        paramHttpHost.b = a(paramContext.getAllHeaders());
        if (localStatusLine.getStatusCode() >= 300)
        {
          bd.c("SyncHttp.client", "request url [" + paramRequestParams.getURI().toString() + "]  result code:[" + localStatusLine.getStatusCode() + "]");
          return paramHttpHost;
        }
        HttpEntity localHttpEntity = paramContext.getEntity();
        paramContext = localObject;
        if (localHttpEntity != null)
        {
          paramContext = EntityUtils.toString(new BufferedHttpEntity(localHttpEntity), "UTF-8");
          paramHttpHost.c = paramContext;
        }
        bd.c("SyncHttp.client", "request url:[" + paramRequestParams.getURI().toString() + "] : result code [" + localStatusLine.getStatusCode() + "]:[" + paramContext + "]");
        return paramHttpHost;
        bd.e("SyncHttp.client", "request url error:[" + paramString + "]", paramContext);
      }
    }
    catch (Throwable paramContext)
    {
      while (true)
        paramHttpHost = null;
    }
  }

  public static final class a
  {
    public int a;
    public Map<String, String> b;
    public String c;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.net.async.SyncHttpClient
 * JD-Core Version:    0.6.2
 */