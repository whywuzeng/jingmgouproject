package u.aly;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.a;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class t
{
  private String a;
  private String b = "10.0.0.172";
  private int c = 80;
  private Context d;
  private r e;

  public t(Context paramContext)
  {
    this.d = paramContext;
    this.a = a(paramContext);
  }

  private String a(Context paramContext)
  {
    StringBuffer localStringBuffer1 = new StringBuffer();
    localStringBuffer1.append("Android");
    localStringBuffer1.append("/");
    localStringBuffer1.append("5.5.3");
    localStringBuffer1.append(" ");
    try
    {
      StringBuffer localStringBuffer2 = new StringBuffer();
      localStringBuffer2.append(bq.v(paramContext));
      localStringBuffer2.append("/");
      localStringBuffer2.append(bq.d(paramContext));
      localStringBuffer2.append(" ");
      localStringBuffer2.append(Build.MODEL);
      localStringBuffer2.append("/");
      localStringBuffer2.append(Build.VERSION.RELEASE);
      localStringBuffer2.append(" ");
      localStringBuffer2.append(cd.a(AnalyticsConfig.getAppkey(paramContext)));
      localStringBuffer1.append(URLEncoder.encode(localStringBuffer2.toString(), "UTF-8"));
      return localStringBuffer1.toString();
    }
    catch (Exception paramContext)
    {
      while (true)
        paramContext.printStackTrace();
    }
  }

  private boolean a()
  {
    if (this.d.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", this.d.getPackageName()) != 0)
      return false;
    try
    {
      Object localObject = ((ConnectivityManager)this.d.getSystemService("connectivity")).getActiveNetworkInfo();
      if ((localObject != null) && (((NetworkInfo)localObject).getType() != 1))
      {
        localObject = ((NetworkInfo)localObject).getExtraInfo();
        if (localObject != null)
          if ((!((String)localObject).equals("cmwap")) && (!((String)localObject).equals("3gwap")))
          {
            boolean bool = ((String)localObject).equals("uniwap");
            if (!bool);
          }
          else
          {
            return true;
          }
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }

  private byte[] a(byte[] paramArrayOfByte, String paramString)
  {
    HttpPost localHttpPost = new HttpPost(paramString);
    Object localObject = new BasicHttpParams();
    HttpConnectionParams.setConnectionTimeout((HttpParams)localObject, 10000);
    HttpConnectionParams.setSoTimeout((HttpParams)localObject, 30000);
    localObject = new DefaultHttpClient((HttpParams)localObject);
    localHttpPost.addHeader("X-Umeng-UTC", String.valueOf(System.currentTimeMillis()));
    localHttpPost.addHeader("X-Umeng-Sdk", this.a);
    localHttpPost.addHeader("Msg-Type", "envelope");
    try
    {
      if (a())
      {
        HttpHost localHttpHost = new HttpHost(this.b, this.c);
        ((HttpClient)localObject).getParams().setParameter("http.route.default-proxy", localHttpHost);
      }
      localHttpPost.setEntity(new InputStreamEntity(new ByteArrayInputStream(paramArrayOfByte), paramArrayOfByte.length));
      if (this.e != null)
        this.e.a();
      paramArrayOfByte = ((HttpClient)localObject).execute(localHttpPost);
      if (this.e != null)
        this.e.b();
      int i = paramArrayOfByte.getStatusLine().getStatusCode();
      boolean bool = cd.a(paramArrayOfByte.getFirstHeader("Content-Type"), "application/thrift");
      br.c("MobclickAgent", "status code : " + i);
      if ((i == 200) && (bool))
      {
        br.a("MobclickAgent", "Send message to " + paramString);
        paramArrayOfByte = paramArrayOfByte.getEntity();
        if (paramArrayOfByte != null)
        {
          paramArrayOfByte = paramArrayOfByte.getContent();
          try
          {
            paramString = cd.b(paramArrayOfByte);
            return paramString;
          }
          finally
          {
            cd.c(paramArrayOfByte);
          }
        }
      }
    }
    catch (ClientProtocolException paramArrayOfByte)
    {
      br.b("MobclickAgent", "ClientProtocolException,Failed to send message.", paramArrayOfByte);
      return null;
      return null;
      return null;
    }
    catch (IOException paramArrayOfByte)
    {
      br.b("MobclickAgent", "IOException,Failed to send message.", paramArrayOfByte);
    }
    return null;
  }

  public void a(r paramr)
  {
    this.e = paramr;
  }

  public byte[] a(byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte1 = null;
    int i = 0;
    while (true)
    {
      byte[] arrayOfByte2 = arrayOfByte1;
      if (i < a.f.length)
      {
        arrayOfByte1 = a(paramArrayOfByte, a.f[i]);
        if (arrayOfByte1 == null)
          break label55;
        arrayOfByte2 = arrayOfByte1;
        if (this.e != null)
        {
          this.e.c();
          arrayOfByte2 = arrayOfByte1;
        }
      }
      return arrayOfByte2;
      label55: if (this.e != null)
        this.e.d();
      i += 1;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     u.aly.t
 * JD-Core Version:    0.6.2
 */