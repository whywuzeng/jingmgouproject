package com.alibaba.sdk.android.oss.model;

import com.alibaba.sdk.android.oss.OSSClient;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.apache.OkApacheClient;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.http.client.HttpClient;

public class SharedComponent
{
  private static DocumentBuilderFactory domBuilderFact = DocumentBuilderFactory.newInstance();
  private static ExecutorService eService;
  private static volatile HttpClient sharedClient;

  public static DocumentBuilderFactory getDomBuilderFact()
  {
    return domBuilderFact;
  }

  public static ExecutorService getExecutorService()
  {
    try
    {
      if (eService == null)
        if ((OSSClient.getClientConfiguration() == null) || (OSSClient.getClientConfiguration().getMaxConcurrentTaskNum() <= 0))
          break label45;
      label45: for (eService = Executors.newFixedThreadPool(OSSClient.getClientConfiguration().getMaxConcurrentTaskNum()); ; eService = Executors.newFixedThreadPool(6))
      {
        ExecutorService localExecutorService = eService;
        return localExecutorService;
      }
    }
    finally
    {
    }
  }

  public static HttpClient getSharedClient()
  {
    try
    {
      Object localObject1;
      if (sharedClient == null)
        localObject1 = new OkHttpClient();
      try
      {
        Object localObject3 = SSLContext.getInstance("SSL");
        X509TrustManager local1 = new X509TrustManager()
        {
          public void checkClientTrusted(X509Certificate[] paramAnonymousArrayOfX509Certificate, String paramAnonymousString)
          {
          }

          public void checkServerTrusted(X509Certificate[] paramAnonymousArrayOfX509Certificate, String paramAnonymousString)
          {
          }

          public X509Certificate[] getAcceptedIssuers()
          {
            return null;
          }
        };
        SecureRandom localSecureRandom = new SecureRandom();
        ((SSLContext)localObject3).init(null, new TrustManager[] { local1 }, localSecureRandom);
        ((OkHttpClient)localObject1).setSslSocketFactory(((SSLContext)localObject3).getSocketFactory());
        ((OkHttpClient)localObject1).setHostnameVerifier(new HostnameVerifier()
        {
          public boolean verify(String paramAnonymousString, SSLSession paramAnonymousSSLSession)
          {
            return true;
          }
        });
        ((OkHttpClient)localObject1).setFollowRedirects(false);
        ((OkHttpClient)localObject1).setRetryOnConnectionFailure(false);
        ((OkHttpClient)localObject1).setCache(null);
        ((OkHttpClient)localObject1).setFollowSslRedirects(false);
        localObject3 = OSSClient.getClientConfiguration();
        ((OkHttpClient)localObject1).setConnectTimeout(((ClientConfiguration)localObject3).getConnectTimeout(), TimeUnit.MILLISECONDS);
        ((OkHttpClient)localObject1).setReadTimeout(((ClientConfiguration)localObject3).getSocketTimeout(), TimeUnit.MILLISECONDS);
        ((OkHttpClient)localObject1).setWriteTimeout(((ClientConfiguration)localObject3).getSocketTimeout(), TimeUnit.MILLISECONDS);
        if ((localObject3 != null) && (((ClientConfiguration)localObject3).getProxyHost() != null))
          ((OkHttpClient)localObject1).setProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(((ClientConfiguration)localObject3).getProxyHost(), ((ClientConfiguration)localObject3).getProxyPort())));
        ((OkHttpClient)localObject1).setRetryOnConnectionFailure(false);
        sharedClient = new OkApacheClient((OkHttpClient)localObject1);
        localObject1 = sharedClient;
        return localObject1;
      }
      catch (Exception localException)
      {
        while (true)
          localException.printStackTrace();
      }
    }
    finally
    {
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.sdk.android.oss.model.SharedComponent
 * JD-Core Version:    0.6.2
 */