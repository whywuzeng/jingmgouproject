package com.dodowaterfall;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class Helper
{
  public static boolean checkConnection(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    if (paramContext != null)
      return paramContext.isAvailable();
    return false;
  }

  public static String getStringFromUrl(String paramString)
    throws ClientProtocolException, IOException
  {
    paramString = new HttpGet(paramString);
    return EntityUtils.toString(new DefaultHttpClient().execute(paramString).getEntity(), "UTF-8");
  }

  public static boolean isWifi(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    return (paramContext != null) && (paramContext.getTypeName().equals("WIFI"));
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.dodowaterfall.Helper
 * JD-Core Version:    0.6.2
 */