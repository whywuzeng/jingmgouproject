package com.squareup.okhttp;

import com.squareup.okhttp.internal.Util;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLSocket;

public final class ConnectionSpec
{
  private static final CipherSuite[] APPROVED_CIPHER_SUITES = { CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_DHE_RSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_DHE_DSS_WITH_AES_128_CBC_SHA, CipherSuite.TLS_DHE_RSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_RSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_RSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_RSA_WITH_3DES_EDE_CBC_SHA };
  public static final ConnectionSpec CLEARTEXT = new Builder(false).build();
  public static final ConnectionSpec COMPATIBLE_TLS;
  public static final ConnectionSpec MODERN_TLS = new Builder(true).cipherSuites(APPROVED_CIPHER_SUITES).tlsVersions(new TlsVersion[] { TlsVersion.TLS_1_2, TlsVersion.TLS_1_1, TlsVersion.TLS_1_0 }).supportsTlsExtensions(true).build();
  private final String[] cipherSuites;
  final boolean supportsTlsExtensions;
  final boolean tls;
  private final String[] tlsVersions;

  static
  {
    COMPATIBLE_TLS = new Builder(MODERN_TLS).tlsVersions(new TlsVersion[] { TlsVersion.TLS_1_0 }).supportsTlsExtensions(true).build();
  }

  private ConnectionSpec(Builder paramBuilder)
  {
    this.tls = paramBuilder.tls;
    this.cipherSuites = paramBuilder.cipherSuites;
    this.tlsVersions = paramBuilder.tlsVersions;
    this.supportsTlsExtensions = paramBuilder.supportsTlsExtensions;
  }

  private static <T> boolean contains(T[] paramArrayOfT, T paramT)
  {
    boolean bool2 = false;
    int j = paramArrayOfT.length;
    int i = 0;
    while (true)
    {
      boolean bool1 = bool2;
      if (i < j)
      {
        if (Util.equal(paramT, paramArrayOfT[i]))
          bool1 = true;
      }
      else
        return bool1;
      i += 1;
    }
  }

  private static boolean nonEmptyIntersection(String[] paramArrayOfString1, String[] paramArrayOfString2)
  {
    if ((paramArrayOfString1 == null) || (paramArrayOfString2 == null) || (paramArrayOfString1.length == 0) || (paramArrayOfString2.length == 0));
    while (true)
    {
      return false;
      int j = paramArrayOfString1.length;
      int i = 0;
      while (i < j)
      {
        if (contains(paramArrayOfString2, paramArrayOfString1[i]))
          return true;
        i += 1;
      }
    }
  }

  private ConnectionSpec supportedSpec(SSLSocket paramSSLSocket, boolean paramBoolean)
  {
    String[] arrayOfString1 = null;
    if (this.cipherSuites != null)
    {
      arrayOfString1 = paramSSLSocket.getEnabledCipherSuites();
      arrayOfString1 = (String[])Util.intersect(String.class, this.cipherSuites, arrayOfString1);
    }
    String[] arrayOfString2 = arrayOfString1;
    if (paramBoolean)
    {
      arrayOfString2 = arrayOfString1;
      if (Arrays.asList(paramSSLSocket.getSupportedCipherSuites()).contains("TLS_FALLBACK_SCSV"))
        if (arrayOfString1 == null)
          break label128;
    }
    while (true)
    {
      arrayOfString2 = new String[arrayOfString1.length + 1];
      System.arraycopy(arrayOfString1, 0, arrayOfString2, 0, arrayOfString1.length);
      arrayOfString2[(arrayOfString2.length - 1)] = "TLS_FALLBACK_SCSV";
      paramSSLSocket = paramSSLSocket.getEnabledProtocols();
      paramSSLSocket = (String[])Util.intersect(String.class, this.tlsVersions, paramSSLSocket);
      return new Builder(this).cipherSuites(arrayOfString2).tlsVersions(paramSSLSocket).build();
      label128: arrayOfString1 = paramSSLSocket.getEnabledCipherSuites();
    }
  }

  void apply(SSLSocket paramSSLSocket, boolean paramBoolean)
  {
    Object localObject = supportedSpec(paramSSLSocket, paramBoolean);
    paramSSLSocket.setEnabledProtocols(((ConnectionSpec)localObject).tlsVersions);
    localObject = ((ConnectionSpec)localObject).cipherSuites;
    if (localObject != null)
      paramSSLSocket.setEnabledCipherSuites((String[])localObject);
  }

  public List<CipherSuite> cipherSuites()
  {
    if (this.cipherSuites == null)
      return null;
    CipherSuite[] arrayOfCipherSuite = new CipherSuite[this.cipherSuites.length];
    int i = 0;
    while (i < this.cipherSuites.length)
    {
      arrayOfCipherSuite[i] = CipherSuite.forJavaName(this.cipherSuites[i]);
      i += 1;
    }
    return Util.immutableList(arrayOfCipherSuite);
  }

  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof ConnectionSpec));
    do
    {
      return false;
      if (paramObject == this)
        return true;
      paramObject = (ConnectionSpec)paramObject;
    }
    while ((this.tls != paramObject.tls) || ((this.tls) && ((!Arrays.equals(this.cipherSuites, paramObject.cipherSuites)) || (!Arrays.equals(this.tlsVersions, paramObject.tlsVersions)) || (this.supportsTlsExtensions != paramObject.supportsTlsExtensions))));
    return true;
  }

  public int hashCode()
  {
    int i = 17;
    int j;
    int k;
    if (this.tls)
    {
      j = Arrays.hashCode(this.cipherSuites);
      k = Arrays.hashCode(this.tlsVersions);
      if (!this.supportsTlsExtensions)
        break label53;
    }
    label53: for (i = 0; ; i = 1)
    {
      i = ((j + 527) * 31 + k) * 31 + i;
      return i;
    }
  }

  public boolean isCompatible(SSLSocket paramSSLSocket)
  {
    boolean bool = false;
    if (!this.tls);
    String[] arrayOfString;
    do
    {
      return false;
      arrayOfString = paramSSLSocket.getEnabledProtocols();
    }
    while (!nonEmptyIntersection(this.tlsVersions, arrayOfString));
    if (this.cipherSuites == null)
    {
      if (paramSSLSocket.getEnabledCipherSuites().length > 0)
        bool = true;
      return bool;
    }
    paramSSLSocket = paramSSLSocket.getEnabledCipherSuites();
    return nonEmptyIntersection(this.cipherSuites, paramSSLSocket);
  }

  public boolean isTls()
  {
    return this.tls;
  }

  public boolean supportsTlsExtensions()
  {
    return this.supportsTlsExtensions;
  }

  public List<TlsVersion> tlsVersions()
  {
    TlsVersion[] arrayOfTlsVersion = new TlsVersion[this.tlsVersions.length];
    int i = 0;
    while (i < this.tlsVersions.length)
    {
      arrayOfTlsVersion[i] = TlsVersion.forJavaName(this.tlsVersions[i]);
      i += 1;
    }
    return Util.immutableList(arrayOfTlsVersion);
  }

  public String toString()
  {
    if (this.tls)
    {
      Object localObject = cipherSuites();
      if (localObject == null);
      for (localObject = "[use default]"; ; localObject = localObject.toString())
        return "ConnectionSpec(cipherSuites=" + (String)localObject + ", tlsVersions=" + tlsVersions() + ", supportsTlsExtensions=" + this.supportsTlsExtensions + ")";
    }
    return "ConnectionSpec()";
  }

  public static final class Builder
  {
    private String[] cipherSuites;
    private boolean supportsTlsExtensions;
    private boolean tls;
    private String[] tlsVersions;

    public Builder(ConnectionSpec paramConnectionSpec)
    {
      this.tls = paramConnectionSpec.tls;
      this.cipherSuites = paramConnectionSpec.cipherSuites;
      this.tlsVersions = paramConnectionSpec.tlsVersions;
      this.supportsTlsExtensions = paramConnectionSpec.supportsTlsExtensions;
    }

    Builder(boolean paramBoolean)
    {
      this.tls = paramBoolean;
    }

    public ConnectionSpec build()
    {
      return new ConnectionSpec(this, null);
    }

    public Builder cipherSuites(CipherSuite[] paramArrayOfCipherSuite)
    {
      if (!this.tls)
        throw new IllegalStateException("no cipher suites for cleartext connections");
      String[] arrayOfString = new String[paramArrayOfCipherSuite.length];
      int i = 0;
      while (i < paramArrayOfCipherSuite.length)
      {
        arrayOfString[i] = paramArrayOfCipherSuite[i].javaName;
        i += 1;
      }
      this.cipherSuites = arrayOfString;
      return this;
    }

    public Builder cipherSuites(String[] paramArrayOfString)
    {
      if (!this.tls)
        throw new IllegalStateException("no cipher suites for cleartext connections");
      if (paramArrayOfString == null)
      {
        this.cipherSuites = null;
        return this;
      }
      this.cipherSuites = ((String[])paramArrayOfString.clone());
      return this;
    }

    public Builder supportsTlsExtensions(boolean paramBoolean)
    {
      if (!this.tls)
        throw new IllegalStateException("no TLS extensions for cleartext connections");
      this.supportsTlsExtensions = paramBoolean;
      return this;
    }

    public Builder tlsVersions(TlsVersion[] paramArrayOfTlsVersion)
    {
      if (!this.tls)
        throw new IllegalStateException("no TLS versions for cleartext connections");
      if (paramArrayOfTlsVersion.length == 0)
        throw new IllegalArgumentException("At least one TlsVersion is required");
      String[] arrayOfString = new String[paramArrayOfTlsVersion.length];
      int i = 0;
      while (i < paramArrayOfTlsVersion.length)
      {
        arrayOfString[i] = paramArrayOfTlsVersion[i].javaName;
        i += 1;
      }
      this.tlsVersions = arrayOfString;
      return this;
    }

    public Builder tlsVersions(String[] paramArrayOfString)
    {
      if (!this.tls)
        throw new IllegalStateException("no TLS versions for cleartext connections");
      if (paramArrayOfString == null)
      {
        this.tlsVersions = null;
        return this;
      }
      this.tlsVersions = ((String[])paramArrayOfString.clone());
      return this;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.ConnectionSpec
 * JD-Core Version:    0.6.2
 */