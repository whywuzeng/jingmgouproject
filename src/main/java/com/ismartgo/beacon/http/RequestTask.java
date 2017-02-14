package com.ismartgo.beacon.http;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

public class RequestTask extends AsyncTask<Object, Object, String>
{
  private Context context;
  private Handler handler;
  private Map<String, String> map;
  private String url;

  public RequestTask(Context paramContext, String paramString, Map<String, String> paramMap, Handler paramHandler)
  {
    this.context = paramContext;
    this.url = paramString;
    this.map = paramMap;
    this.handler = paramHandler;
  }

  protected String doInBackground(Object[] paramArrayOfObject)
  {
    paramArrayOfObject = null;
    String str = null;
    if (!NetUtil.checkNetWork(this.context))
    {
      this.handler.sendEmptyMessage(258);
      return null;
    }
    Object localObject1 = new BasicHttpParams();
    HttpConnectionParams.setConnectionTimeout((HttpParams)localObject1, 8000);
    HttpConnectionParams.setSoTimeout((HttpParams)localObject1, 8000);
    Object localObject2 = new ArrayList();
    if ((this.map != null) && (!this.map.isEmpty()))
      localObject1 = this.map.entrySet().iterator();
    while (true)
    {
      Object localObject3;
      if (!((Iterator)localObject1).hasNext())
      {
        localObject1 = new DefaultHttpClient();
        localObject3 = new HttpPost(this.url);
      }
      try
      {
        ((HttpPost)localObject3).setEntity(new UrlEncodedFormEntity((List)localObject2, "utf-8"));
        localObject2 = ((HttpClient)localObject1).execute((HttpUriRequest)localObject3);
        if (((HttpResponse)localObject2).getStatusLine().getStatusCode() == 200)
          str = EntityUtils.toString(((HttpResponse)localObject2).getEntity());
        for (paramArrayOfObject = str; ; paramArrayOfObject = str)
        {
          return paramArrayOfObject;
          localObject3 = (Map.Entry)((Iterator)localObject1).next();
          ((List)localObject2).add(new BasicNameValuePair((String)((Map.Entry)localObject3).getKey(), (String)((Map.Entry)localObject3).getValue()));
          break;
          this.handler.sendEmptyMessage(259);
        }
      }
      catch (Exception localException)
      {
        while (true)
        {
          localException.printStackTrace();
          this.handler.sendEmptyMessage(259);
          ((HttpClient)localObject1).getConnectionManager().shutdown();
        }
      }
      finally
      {
        ((HttpClient)localObject1).getConnectionManager().shutdown();
      }
    }
    throw paramArrayOfObject;
  }

  protected void onPostExecute(String paramString)
  {
    super.onPostExecute(paramString);
    Message localMessage = this.handler.obtainMessage();
    localMessage.what = 260;
    localMessage.obj = paramString;
    this.handler.sendMessage(localMessage);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.beacon.http.RequestTask
 * JD-Core Version:    0.6.2
 */