package com.baidu.location.b;

import android.util.Log;
import java.io.File;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

class s extends Thread
{
  s(m paramm)
  {
  }

  public void run()
  {
    this.a.c5 = k.Z();
    this.a.au();
    int i = this.a.dg;
    m.jdMethod_if(this.a);
    Object localObject1 = null;
    while (true)
    {
      if (i > 0);
      try
      {
        HttpPost localHttpPost = new HttpPost(this.a.c5);
        while (true)
        {
          Object localObject2;
          try
          {
            localObject1 = new FileEntity(new File(this.a.dc), "binary/octet-stream");
            localHttpPost.setHeader("Content-Type", "application/octet-stream");
            localHttpPost.setHeader("Accept-Charset", "UTF-8;");
            localHttpPost.setEntity((HttpEntity)localObject1);
            localObject1 = new DefaultHttpClient();
            ((HttpClient)localObject1).getParams().setParameter("http.connection.timeout", Integer.valueOf(g.Q));
            ((HttpClient)localObject1).getParams().setParameter("http.socket.timeout", Integer.valueOf(g.d));
            HttpProtocolParams.setUseExpectContinue(((HttpClient)localObject1).getParams(), false);
            if (((m.c8 == g.byte) || (m.c8 == g.w)) && ((this.a.dg - i) % 2 == 0))
            {
              HttpHost localHttpHost = new HttpHost(m.al(), m.ar(), "http");
              ((HttpClient)localObject1).getParams().setParameter("http.route.default-proxy", localHttpHost);
            }
            localObject1 = ((HttpClient)localObject1).execute(localHttpPost);
            if (((HttpResponse)localObject1).getStatusLine().getStatusCode() == 200)
            {
              this.a.c6 = ((HttpResponse)localObject1).getEntity();
              this.a.jdMethod_int(true);
              if (i > 0)
                break label337;
              m.de += 1;
              this.a.c6 = null;
              this.a.jdMethod_int(false);
              m.jdMethod_if(this.a, false);
              return;
            }
            localHttpPost.abort();
            localObject1 = localHttpPost;
            i -= 1;
          }
          catch (Exception localException1)
          {
            localObject2 = localHttpPost;
          }
          label321: localObject2.abort();
          Log.d(g.m, "NetworkCommunicationException!");
          continue;
          label337: m.de = 0;
        }
      }
      catch (Exception localException2)
      {
        break label321;
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.b.s
 * JD-Core Version:    0.6.2
 */