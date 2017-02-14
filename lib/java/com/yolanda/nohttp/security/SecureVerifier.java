package com.yolanda.nohttp.security;

import android.annotation.SuppressLint;
import com.yolanda.nohttp.BasicAnalyzeRequest;
import com.yolanda.nohttp.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManagerFactory;

public final class SecureVerifier
{
  private static SecureVerifier _HttpsVerifier;
  private HostnameVerifier hostnameVerifier = new HostnameVerifier()
  {
    public boolean verify(String paramAnonymousString, SSLSession paramAnonymousSSLSession)
    {
      return true;
    }
  };
  private boolean isAllowAll;

  private SecureVerifier()
  {
    PRNGFixes.apply();
  }

  public static SecureVerifier getInstance()
  {
    if (_HttpsVerifier == null)
      _HttpsVerifier = new SecureVerifier();
    return _HttpsVerifier;
  }

  @SuppressLint({"TrulyRandom"})
  public void doVerifier(HttpsURLConnection paramHttpsURLConnection, BasicAnalyzeRequest paramBasicAnalyzeRequest)
  {
    if ((this.isAllowAll) || (paramBasicAnalyzeRequest.isAllowHttps()))
    {
      paramHttpsURLConnection.setSSLSocketFactory(DefaultSSLSocketFactory.get());
      paramHttpsURLConnection.setHostnameVerifier(this.hostnameVerifier);
    }
    while (paramBasicAnalyzeRequest.getCertificate() == null)
      return;
    try
    {
      localObject = KeyStore.getInstance(KeyStore.getDefaultType());
      paramBasicAnalyzeRequest = paramBasicAnalyzeRequest.getCertificate();
      localInputStream = paramBasicAnalyzeRequest.getInputStream();
      if (localInputStream == null)
        throw new RuntimeException("Certificate argument error, the " + paramBasicAnalyzeRequest.getName() + " file not found");
    }
    catch (KeyManagementException paramHttpsURLConnection)
    {
      InputStream localInputStream;
      Logger.e(paramHttpsURLConnection);
      return;
      if (paramBasicAnalyzeRequest.hasKeyPass())
      {
        ((KeyStore)localObject).load(localInputStream, paramBasicAnalyzeRequest.getKeyPassCharArray());
        ((KeyStore)localObject).setCertificateEntry("NoHttp_" + paramBasicAnalyzeRequest.getName(), CertificateFactory.getInstance("X.509").generateCertificate(localInputStream));
        localInputStream.close();
        paramBasicAnalyzeRequest = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        paramBasicAnalyzeRequest.init((KeyStore)localObject);
        localObject = SSLContext.getInstance("TLS");
        ((SSLContext)localObject).init(null, paramBasicAnalyzeRequest.getTrustManagers(), new SecureRandom());
        paramHttpsURLConnection.setSSLSocketFactory(((SSLContext)localObject).getSocketFactory());
        return;
      }
    }
    catch (KeyStoreException paramHttpsURLConnection)
    {
      while (true)
      {
        Object localObject;
        Logger.e(paramHttpsURLConnection);
        return;
        ((KeyStore)localObject).load(null);
      }
    }
    catch (NoSuchAlgorithmException paramHttpsURLConnection)
    {
      Logger.e(paramHttpsURLConnection);
      return;
    }
    catch (CertificateException paramHttpsURLConnection)
    {
      Logger.e(paramHttpsURLConnection);
      return;
    }
    catch (IOException paramHttpsURLConnection)
    {
      Logger.e(paramHttpsURLConnection);
    }
  }

  public void setAllowAllHttps(boolean paramBoolean)
  {
    this.isAllowAll = paramBoolean;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.yolanda.nohttp.security.SecureVerifier
 * JD-Core Version:    0.6.2
 */