package com.baidu.location.b;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.util.List;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import org.apache.http.HttpEntity;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

public abstract class m
  implements f
{
  public static int c8 = g.w;
  private static String dd = "10.0.0.172";
  protected static int de = 0;
  private static int df = 80;
  public String c5 = null;
  public HttpEntity c6 = null;
  public List c7 = null;
  private boolean c9 = false;
  public String da = null;
  public byte[] db = null;
  public String dc = null;
  public int dg = 3;

  private void am()
  {
    c8 = aq();
  }

  private int aq()
  {
    Object localObject1 = com.baidu.location.f.getServiceContext();
    try
    {
      Object localObject2 = (ConnectivityManager)((Context)localObject1).getSystemService("connectivity");
      if (localObject2 == null)
        return g.w;
      localObject2 = ((ConnectivityManager)localObject2).getActiveNetworkInfo();
      if ((localObject2 == null) || (!((NetworkInfo)localObject2).isAvailable()))
        return g.w;
      if (((NetworkInfo)localObject2).getType() == 1)
      {
        localObject1 = Proxy.getDefaultHost();
        if ((localObject1 != null) && (((String)localObject1).length() > 0))
          return g.p;
        return g.o;
      }
      int i = jdMethod_if((Context)localObject1, (NetworkInfo)localObject2);
      return i;
    }
    catch (Exception localException)
    {
    }
    return g.w;
  }

  private static int jdMethod_if(Context paramContext, NetworkInfo paramNetworkInfo)
  {
    if ((paramNetworkInfo != null) && (paramNetworkInfo.getExtraInfo() != null))
    {
      paramContext = paramNetworkInfo.getExtraInfo().toLowerCase();
      if (paramContext != null)
      {
        if ((paramContext.startsWith("cmwap")) || (paramContext.startsWith("uniwap")) || (paramContext.startsWith("3gwap")))
        {
          paramContext = Proxy.getDefaultHost();
          if ((paramContext != null) && (!paramContext.equals("")) && (!paramContext.equals("null")));
          while (true)
          {
            dd = paramContext;
            return g.byte;
            paramContext = "10.0.0.172";
          }
        }
        if (paramContext.startsWith("ctwap"))
        {
          paramContext = Proxy.getDefaultHost();
          if ((paramContext != null) && (!paramContext.equals("")) && (!paramContext.equals("null")));
          while (true)
          {
            dd = paramContext;
            return g.byte;
            paramContext = "10.0.0.200";
          }
        }
        if ((paramContext.startsWith("cmnet")) || (paramContext.startsWith("uninet")) || (paramContext.startsWith("ctnet")) || (paramContext.startsWith("3gnet")))
          return g.for;
      }
    }
    paramContext = Proxy.getDefaultHost();
    if ((paramContext != null) && (paramContext.length() > 0))
    {
      if ("10.0.0.172".equals(paramContext.trim()))
      {
        dd = "10.0.0.172";
        return g.byte;
      }
      if ("10.0.0.200".equals(paramContext.trim()))
      {
        dd = "10.0.0.200";
        return g.byte;
      }
    }
    return g.for;
  }

  public static HttpClient jdMethod_if(HttpParams paramHttpParams)
  {
    try
    {
      Object localObject = KeyStore.getInstance(KeyStore.getDefaultType());
      ((KeyStore)localObject).load(null, null);
      localObject = new a((KeyStore)localObject);
      ((org.apache.http.conn.ssl.SSLSocketFactory)localObject).setHostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
      HttpProtocolParams.setVersion(paramHttpParams, HttpVersion.HTTP_1_1);
      HttpProtocolParams.setContentCharset(paramHttpParams, "UTF-8");
      SchemeRegistry localSchemeRegistry = new SchemeRegistry();
      localSchemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
      localSchemeRegistry.register(new Scheme("https", (SocketFactory)localObject, 443));
      localObject = new DefaultHttpClient(new ThreadSafeClientConnManager(paramHttpParams, localSchemeRegistry), paramHttpParams);
      return localObject;
    }
    catch (Exception localException)
    {
    }
    return new DefaultHttpClient(paramHttpParams);
  }

  public static boolean jdMethod_int(Context paramContext)
  {
    try
    {
      paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
      if (paramContext.getActiveNetworkInfo() != null)
      {
        boolean bool = paramContext.getActiveNetworkInfo().isAvailable();
        return bool;
      }
    }
    catch (Exception paramContext)
    {
      return false;
    }
    return false;
  }

  public void an()
  {
    new t(this).start();
  }

  public void ao()
  {
    new r(this).start();
  }

  public void ap()
  {
    new q(this).start();
  }

  public void as()
  {
    new p(this).start();
  }

  public void at()
  {
    new s(this).start();
  }

  public abstract void au();

  public void av()
  {
    new u(this).start();
  }

  public abstract void jdMethod_int(boolean paramBoolean);

  public static class a extends org.apache.http.conn.ssl.SSLSocketFactory
  {
    final SSLContext a = SSLContext.getInstance("TLS");

    public a(KeyStore paramKeyStore)
      throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException
    {
      super();
      paramKeyStore = new v(this);
      this.a.init(null, new TrustManager[] { paramKeyStore }, null);
    }

    public Socket createSocket()
      throws IOException
    {
      return this.a.getSocketFactory().createSocket();
    }

    public Socket createSocket(Socket paramSocket, String paramString, int paramInt, boolean paramBoolean)
      throws IOException, UnknownHostException
    {
      return this.a.getSocketFactory().createSocket(paramSocket, paramString, paramInt, paramBoolean);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.b.m
 * JD-Core Version:    0.6.2
 */