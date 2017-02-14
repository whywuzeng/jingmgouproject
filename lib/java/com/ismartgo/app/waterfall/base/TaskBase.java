package com.ismartgo.app.waterfall.base;

import android.os.AsyncTask;
import android.util.Log;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class TaskBase extends AsyncTask<String, Integer, List<Map<String, Object>>>
{
  protected List<Map<String, Object>> doInBackground(String[] paramArrayOfString)
  {
    return null;
  }

  public String getStringFromUrl(String paramString)
    throws ClientProtocolException, IOException
  {
    HttpGet localHttpGet = new HttpGet(paramString);
    Log.e("MyUrl", paramString);
    return EntityUtils.toString(new DefaultHttpClient().execute(localHttpGet).getEntity(), "UTF-8");
  }

  protected void onPostExecute(List<Map<String, Object>> paramList)
  {
    super.onPostExecute(paramList);
  }

  protected void onPreExecute()
  {
    super.onPreExecute();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.waterfall.base.TaskBase
 * JD-Core Version:    0.6.2
 */