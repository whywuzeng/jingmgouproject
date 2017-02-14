package com.baidu.location.b;

import android.util.Log;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

class u extends Thread
{
  u(m paramm)
  {
  }

  public void run()
  {
    this.a.c5 = k.ab();
    this.a.au();
    int j = this.a.dg;
    m.jdMethod_if(this.a);
    Object localObject1;
    int i;
    if (m.c8 == g.byte)
    {
      localObject1 = null;
      i = 1;
      if (j <= 0);
    }
    while (true)
    {
      try
      {
        while (true)
        {
          HttpPost localHttpPost = new HttpPost(this.a.c5);
          int k = i;
          try
          {
            localObject1 = new StringEntity(this.a.da, "UTF-8");
            k = i;
            localHttpPost.setHeader("Content-Type", "text/xml");
            k = i;
            localHttpPost.setEntity((HttpEntity)localObject1);
            k = i;
            localObject1 = new BasicHttpParams();
            k = i;
            HttpConnectionParams.setConnectionTimeout((HttpParams)localObject1, g.Q);
            k = i;
            HttpConnectionParams.setSoTimeout((HttpParams)localObject1, g.Q);
            k = i;
            localObject1 = m.jdMethod_if((HttpParams)localObject1);
            k = i;
            HttpProtocolParams.setUseExpectContinue(((HttpClient)localObject1).getParams(), false);
            if (i == 0)
              break label369;
            k = i;
            HttpHost localHttpHost = new HttpHost(m.al(), m.ar(), "http");
            k = i;
            ((HttpClient)localObject1).getParams().setParameter("http.route.default-proxy", localHttpHost);
            break label369;
            while (true)
            {
              k = i;
              localObject1 = ((HttpClient)localObject1).execute(localHttpPost);
              k = i;
              if (((HttpResponse)localObject1).getStatusLine().getStatusCode() != 200)
                break;
              k = i;
              this.a.c6 = ((HttpResponse)localObject1).getEntity();
              k = i;
              this.a.jdMethod_int(true);
              if (j > 0)
                break label349;
              m.de += 1;
              this.a.c6 = null;
              this.a.jdMethod_int(false);
              m.jdMethod_if(this.a, false);
              return;
              i = 0;
            }
            k = i;
            localHttpPost.abort();
            localObject1 = localHttpPost;
            j -= 1;
          }
          catch (Exception localException1)
          {
            localObject2 = localHttpPost;
            i = k;
          }
        }
        localObject2.abort();
        Log.d(g.m, "NetworkCommunicationException!");
        continue;
        label349: m.de = 0;
        continue;
      }
      catch (Exception localException2)
      {
        continue;
      }
      Object localObject2 = null;
      i = 0;
      break;
      label369: if (i == 0)
        i = 1;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.b.u
 * JD-Core Version:    0.6.2
 */