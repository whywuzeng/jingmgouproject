package com.baidu.location.b;

import android.util.Log;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

class p extends Thread
{
  p(m paramm)
  {
  }

  public void run()
  {
    this.a.c5 = k.Z();
    this.a.au();
    int j = this.a.dg;
    m.jdMethod_if(this.a);
    int i;
    int k;
    if (m.c8 == g.byte)
    {
      i = 1;
      if (j > 0)
        k = i;
    }
    while (true)
    {
      try
      {
        HttpGet localHttpGet = new HttpGet(this.a.c5);
        k = i;
        localHttpGet.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
        k = i;
        localHttpGet.setHeader("Accept-Charset", "UTF-8;");
        k = i;
        Object localObject = new DefaultHttpClient();
        k = i;
        ((HttpClient)localObject).getParams().setParameter("http.connection.timeout", Integer.valueOf(g.Q));
        k = i;
        ((HttpClient)localObject).getParams().setParameter("http.socket.timeout", Integer.valueOf(g.Q));
        k = i;
        HttpProtocolParams.setUseExpectContinue(((HttpClient)localObject).getParams(), false);
        if (i == 0)
          break label346;
        k = i;
        HttpHost localHttpHost = new HttpHost(m.al(), m.ar(), "http");
        k = i;
        ((HttpClient)localObject).getParams().setParameter("http.route.default-proxy", localHttpHost);
        break label346;
        k = i;
        localObject = ((HttpClient)localObject).execute(localHttpGet);
        k = i;
        if (((HttpResponse)localObject).getStatusLine().getStatusCode() == 200)
        {
          k = i;
          this.a.c6 = ((HttpResponse)localObject).getEntity();
          k = i;
          this.a.jdMethod_int(true);
          if (j > 0)
            continue;
          m.de += 1;
          this.a.c6 = null;
          this.a.jdMethod_int(false);
          m.jdMethod_if(this.a, false);
          return;
          i = 0;
          continue;
        }
        k = i;
        localHttpGet.abort();
        j -= 1;
      }
      catch (Exception localException)
      {
        Log.d(g.m, "NetworkCommunicationException!");
        i = k;
        continue;
        m.de = 0;
        continue;
      }
      i = 0;
      break;
      label346: if (i == 0)
        i = 1;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.b.p
 * JD-Core Version:    0.6.2
 */