package com.yolanda.nohttp.security;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class DefaultSSLSocketFactory
{
  private static SSLSocketFactory mDefaultSocketFactory;
  private static TrustManager trustManager = new X509TrustManager()
  {
    private X509Certificate[] certificates;

    public void checkClientTrusted(X509Certificate[] paramAnonymousArrayOfX509Certificate, String paramAnonymousString)
      throws CertificateException
    {
      if (this.certificates == null)
        this.certificates = paramAnonymousArrayOfX509Certificate;
    }

    public void checkServerTrusted(X509Certificate[] paramAnonymousArrayOfX509Certificate, String paramAnonymousString)
      throws CertificateException
    {
      if (this.certificates == null)
        this.certificates = paramAnonymousArrayOfX509Certificate;
    }

    public X509Certificate[] getAcceptedIssuers()
    {
      return this.certificates;
    }
  };

  // ERROR //
  @android.annotation.SuppressLint({"TrulyRandom"})
  public static SSLSocketFactory get()
  {
    // Byte code:
    //   0: getstatic 30	com/yolanda/nohttp/security/DefaultSSLSocketFactory:mDefaultSocketFactory	Ljavax/net/ssl/SSLSocketFactory;
    //   3: ifnonnull +56 -> 59
    //   6: ldc 2
    //   8: monitorenter
    //   9: getstatic 30	com/yolanda/nohttp/security/DefaultSSLSocketFactory:mDefaultSocketFactory	Ljavax/net/ssl/SSLSocketFactory;
    //   12: astore_0
    //   13: aload_0
    //   14: ifnonnull +42 -> 56
    //   17: getstatic 17	com/yolanda/nohttp/security/DefaultSSLSocketFactory:trustManager	Ljavax/net/ssl/TrustManager;
    //   20: astore_0
    //   21: ldc 32
    //   23: invokestatic 38	javax/net/ssl/SSLContext:getInstance	(Ljava/lang/String;)Ljavax/net/ssl/SSLContext;
    //   26: astore_1
    //   27: new 40	java/security/SecureRandom
    //   30: dup
    //   31: invokespecial 41	java/security/SecureRandom:<init>	()V
    //   34: astore_2
    //   35: aload_1
    //   36: aconst_null
    //   37: iconst_1
    //   38: anewarray 43	javax/net/ssl/TrustManager
    //   41: dup
    //   42: iconst_0
    //   43: aload_0
    //   44: aastore
    //   45: aload_2
    //   46: invokevirtual 47	javax/net/ssl/SSLContext:init	([Ljavax/net/ssl/KeyManager;[Ljavax/net/ssl/TrustManager;Ljava/security/SecureRandom;)V
    //   49: aload_1
    //   50: invokevirtual 50	javax/net/ssl/SSLContext:getSocketFactory	()Ljavax/net/ssl/SSLSocketFactory;
    //   53: putstatic 30	com/yolanda/nohttp/security/DefaultSSLSocketFactory:mDefaultSocketFactory	Ljavax/net/ssl/SSLSocketFactory;
    //   56: ldc 2
    //   58: monitorexit
    //   59: getstatic 30	com/yolanda/nohttp/security/DefaultSSLSocketFactory:mDefaultSocketFactory	Ljavax/net/ssl/SSLSocketFactory;
    //   62: areturn
    //   63: astore_0
    //   64: ldc 2
    //   66: monitorexit
    //   67: aload_0
    //   68: athrow
    //   69: astore_0
    //   70: goto -14 -> 56
    //   73: astore_0
    //   74: goto -18 -> 56
    //
    // Exception table:
    //   from	to	target	type
    //   9	13	63	finally
    //   17	56	63	finally
    //   56	59	63	finally
    //   64	67	63	finally
    //   17	56	69	java/security/NoSuchAlgorithmException
    //   17	56	73	java/security/KeyManagementException
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.yolanda.nohttp.security.DefaultSSLSocketFactory
 * JD-Core Version:    0.6.2
 */