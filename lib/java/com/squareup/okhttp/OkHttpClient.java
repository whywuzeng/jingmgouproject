package com.squareup.okhttp;

import com.squareup.okhttp.internal.Internal;
import com.squareup.okhttp.internal.InternalCache;
import com.squareup.okhttp.internal.Network;
import com.squareup.okhttp.internal.RouteDatabase;
import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.http.AuthenticatorAdapter;
import com.squareup.okhttp.internal.http.HttpEngine;
import com.squareup.okhttp.internal.http.RouteException;
import com.squareup.okhttp.internal.http.Transport;
import com.squareup.okhttp.internal.tls.OkHostnameVerifier;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.Proxy;
import java.net.ProxySelector;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import okio.BufferedSink;
import okio.BufferedSource;

public class OkHttpClient
  implements Cloneable
{
  private static final List<ConnectionSpec> DEFAULT_CONNECTION_SPECS;
  private static final List<Protocol> DEFAULT_PROTOCOLS = Util.immutableList(new Protocol[] { Protocol.HTTP_2, Protocol.SPDY_3, Protocol.HTTP_1_1 });
  private static SSLSocketFactory defaultSslSocketFactory;
  private Authenticator authenticator;
  private Cache cache;
  private CertificatePinner certificatePinner;
  private int connectTimeout = 10000;
  private ConnectionPool connectionPool;
  private List<ConnectionSpec> connectionSpecs;
  private CookieHandler cookieHandler;
  private Dispatcher dispatcher;
  private boolean followRedirects = true;
  private boolean followSslRedirects = true;
  private HostnameVerifier hostnameVerifier;
  private final List<Interceptor> interceptors = new ArrayList();
  private InternalCache internalCache;
  private Network network;
  private final List<Interceptor> networkInterceptors = new ArrayList();
  private List<Protocol> protocols;
  private Proxy proxy;
  private ProxySelector proxySelector;
  private int readTimeout = 10000;
  private boolean retryOnConnectionFailure = true;
  private final RouteDatabase routeDatabase;
  private SocketFactory socketFactory;
  private SSLSocketFactory sslSocketFactory;
  private int writeTimeout = 10000;

  static
  {
    DEFAULT_CONNECTION_SPECS = Util.immutableList(new ConnectionSpec[] { ConnectionSpec.MODERN_TLS, ConnectionSpec.COMPATIBLE_TLS, ConnectionSpec.CLEARTEXT });
    Internal.instance = new Internal()
    {
      public void addLenient(Headers.Builder paramAnonymousBuilder, String paramAnonymousString)
      {
        paramAnonymousBuilder.addLenient(paramAnonymousString);
      }

      public void addLenient(Headers.Builder paramAnonymousBuilder, String paramAnonymousString1, String paramAnonymousString2)
      {
        paramAnonymousBuilder.addLenient(paramAnonymousString1, paramAnonymousString2);
      }

      public void apply(ConnectionSpec paramAnonymousConnectionSpec, SSLSocket paramAnonymousSSLSocket, boolean paramAnonymousBoolean)
      {
        paramAnonymousConnectionSpec.apply(paramAnonymousSSLSocket, paramAnonymousBoolean);
      }

      public Connection callEngineGetConnection(Call paramAnonymousCall)
      {
        return paramAnonymousCall.engine.getConnection();
      }

      public void callEngineReleaseConnection(Call paramAnonymousCall)
        throws IOException
      {
        paramAnonymousCall.engine.releaseConnection();
      }

      public void callEnqueue(Call paramAnonymousCall, Callback paramAnonymousCallback, boolean paramAnonymousBoolean)
      {
        paramAnonymousCall.enqueue(paramAnonymousCallback, paramAnonymousBoolean);
      }

      public boolean clearOwner(Connection paramAnonymousConnection)
      {
        return paramAnonymousConnection.clearOwner();
      }

      public void closeIfOwnedBy(Connection paramAnonymousConnection, Object paramAnonymousObject)
        throws IOException
      {
        paramAnonymousConnection.closeIfOwnedBy(paramAnonymousObject);
      }

      public void connectAndSetOwner(OkHttpClient paramAnonymousOkHttpClient, Connection paramAnonymousConnection, HttpEngine paramAnonymousHttpEngine, Request paramAnonymousRequest)
        throws RouteException
      {
        paramAnonymousConnection.connectAndSetOwner(paramAnonymousOkHttpClient, paramAnonymousHttpEngine, paramAnonymousRequest);
      }

      public BufferedSink connectionRawSink(Connection paramAnonymousConnection)
      {
        return paramAnonymousConnection.rawSink();
      }

      public BufferedSource connectionRawSource(Connection paramAnonymousConnection)
      {
        return paramAnonymousConnection.rawSource();
      }

      public void connectionSetOwner(Connection paramAnonymousConnection, Object paramAnonymousObject)
      {
        paramAnonymousConnection.setOwner(paramAnonymousObject);
      }

      public InternalCache internalCache(OkHttpClient paramAnonymousOkHttpClient)
      {
        return paramAnonymousOkHttpClient.internalCache();
      }

      public boolean isReadable(Connection paramAnonymousConnection)
      {
        return paramAnonymousConnection.isReadable();
      }

      public Network network(OkHttpClient paramAnonymousOkHttpClient)
      {
        return paramAnonymousOkHttpClient.network;
      }

      public Transport newTransport(Connection paramAnonymousConnection, HttpEngine paramAnonymousHttpEngine)
        throws IOException
      {
        return paramAnonymousConnection.newTransport(paramAnonymousHttpEngine);
      }

      public void recycle(ConnectionPool paramAnonymousConnectionPool, Connection paramAnonymousConnection)
      {
        paramAnonymousConnectionPool.recycle(paramAnonymousConnection);
      }

      public int recycleCount(Connection paramAnonymousConnection)
      {
        return paramAnonymousConnection.recycleCount();
      }

      public RouteDatabase routeDatabase(OkHttpClient paramAnonymousOkHttpClient)
      {
        return paramAnonymousOkHttpClient.routeDatabase();
      }

      public void setCache(OkHttpClient paramAnonymousOkHttpClient, InternalCache paramAnonymousInternalCache)
      {
        paramAnonymousOkHttpClient.setInternalCache(paramAnonymousInternalCache);
      }

      public void setNetwork(OkHttpClient paramAnonymousOkHttpClient, Network paramAnonymousNetwork)
      {
        OkHttpClient.access$002(paramAnonymousOkHttpClient, paramAnonymousNetwork);
      }

      public void setOwner(Connection paramAnonymousConnection, HttpEngine paramAnonymousHttpEngine)
      {
        paramAnonymousConnection.setOwner(paramAnonymousHttpEngine);
      }

      public void setProtocol(Connection paramAnonymousConnection, Protocol paramAnonymousProtocol)
      {
        paramAnonymousConnection.setProtocol(paramAnonymousProtocol);
      }
    };
  }

  public OkHttpClient()
  {
    this.routeDatabase = new RouteDatabase();
    this.dispatcher = new Dispatcher();
  }

  private OkHttpClient(OkHttpClient paramOkHttpClient)
  {
    this.routeDatabase = paramOkHttpClient.routeDatabase;
    this.dispatcher = paramOkHttpClient.dispatcher;
    this.proxy = paramOkHttpClient.proxy;
    this.protocols = paramOkHttpClient.protocols;
    this.connectionSpecs = paramOkHttpClient.connectionSpecs;
    this.interceptors.addAll(paramOkHttpClient.interceptors);
    this.networkInterceptors.addAll(paramOkHttpClient.networkInterceptors);
    this.proxySelector = paramOkHttpClient.proxySelector;
    this.cookieHandler = paramOkHttpClient.cookieHandler;
    this.cache = paramOkHttpClient.cache;
    if (this.cache != null);
    for (InternalCache localInternalCache = this.cache.internalCache; ; localInternalCache = paramOkHttpClient.internalCache)
    {
      this.internalCache = localInternalCache;
      this.socketFactory = paramOkHttpClient.socketFactory;
      this.sslSocketFactory = paramOkHttpClient.sslSocketFactory;
      this.hostnameVerifier = paramOkHttpClient.hostnameVerifier;
      this.certificatePinner = paramOkHttpClient.certificatePinner;
      this.authenticator = paramOkHttpClient.authenticator;
      this.connectionPool = paramOkHttpClient.connectionPool;
      this.network = paramOkHttpClient.network;
      this.followSslRedirects = paramOkHttpClient.followSslRedirects;
      this.followRedirects = paramOkHttpClient.followRedirects;
      this.retryOnConnectionFailure = paramOkHttpClient.retryOnConnectionFailure;
      this.connectTimeout = paramOkHttpClient.connectTimeout;
      this.readTimeout = paramOkHttpClient.readTimeout;
      this.writeTimeout = paramOkHttpClient.writeTimeout;
      return;
    }
  }

  private SSLSocketFactory getDefaultSSLSocketFactory()
  {
    try
    {
      Object localObject1 = defaultSslSocketFactory;
      if (localObject1 == null);
      try
      {
        localObject1 = SSLContext.getInstance("TLS");
        ((SSLContext)localObject1).init(null, null, null);
        defaultSslSocketFactory = ((SSLContext)localObject1).getSocketFactory();
        localObject1 = defaultSslSocketFactory;
        return localObject1;
      }
      catch (GeneralSecurityException localGeneralSecurityException)
      {
        throw new AssertionError();
      }
    }
    finally
    {
    }
  }

  public OkHttpClient cancel(Object paramObject)
  {
    getDispatcher().cancel(paramObject);
    return this;
  }

  public OkHttpClient clone()
  {
    return new OkHttpClient(this);
  }

  OkHttpClient copyWithDefaults()
  {
    OkHttpClient localOkHttpClient = new OkHttpClient(this);
    if (localOkHttpClient.proxySelector == null)
      localOkHttpClient.proxySelector = ProxySelector.getDefault();
    if (localOkHttpClient.cookieHandler == null)
      localOkHttpClient.cookieHandler = CookieHandler.getDefault();
    if (localOkHttpClient.socketFactory == null)
      localOkHttpClient.socketFactory = SocketFactory.getDefault();
    if (localOkHttpClient.sslSocketFactory == null)
      localOkHttpClient.sslSocketFactory = getDefaultSSLSocketFactory();
    if (localOkHttpClient.hostnameVerifier == null)
      localOkHttpClient.hostnameVerifier = OkHostnameVerifier.INSTANCE;
    if (localOkHttpClient.certificatePinner == null)
      localOkHttpClient.certificatePinner = CertificatePinner.DEFAULT;
    if (localOkHttpClient.authenticator == null)
      localOkHttpClient.authenticator = AuthenticatorAdapter.INSTANCE;
    if (localOkHttpClient.connectionPool == null)
      localOkHttpClient.connectionPool = ConnectionPool.getDefault();
    if (localOkHttpClient.protocols == null)
      localOkHttpClient.protocols = DEFAULT_PROTOCOLS;
    if (localOkHttpClient.connectionSpecs == null)
      localOkHttpClient.connectionSpecs = DEFAULT_CONNECTION_SPECS;
    if (localOkHttpClient.network == null)
      localOkHttpClient.network = Network.DEFAULT;
    return localOkHttpClient;
  }

  public Authenticator getAuthenticator()
  {
    return this.authenticator;
  }

  public Cache getCache()
  {
    return this.cache;
  }

  public CertificatePinner getCertificatePinner()
  {
    return this.certificatePinner;
  }

  public int getConnectTimeout()
  {
    return this.connectTimeout;
  }

  public ConnectionPool getConnectionPool()
  {
    return this.connectionPool;
  }

  public List<ConnectionSpec> getConnectionSpecs()
  {
    return this.connectionSpecs;
  }

  public CookieHandler getCookieHandler()
  {
    return this.cookieHandler;
  }

  public Dispatcher getDispatcher()
  {
    return this.dispatcher;
  }

  public boolean getFollowRedirects()
  {
    return this.followRedirects;
  }

  public boolean getFollowSslRedirects()
  {
    return this.followSslRedirects;
  }

  public HostnameVerifier getHostnameVerifier()
  {
    return this.hostnameVerifier;
  }

  public List<Protocol> getProtocols()
  {
    return this.protocols;
  }

  public Proxy getProxy()
  {
    return this.proxy;
  }

  public ProxySelector getProxySelector()
  {
    return this.proxySelector;
  }

  public int getReadTimeout()
  {
    return this.readTimeout;
  }

  public boolean getRetryOnConnectionFailure()
  {
    return this.retryOnConnectionFailure;
  }

  public SocketFactory getSocketFactory()
  {
    return this.socketFactory;
  }

  public SSLSocketFactory getSslSocketFactory()
  {
    return this.sslSocketFactory;
  }

  public int getWriteTimeout()
  {
    return this.writeTimeout;
  }

  public List<Interceptor> interceptors()
  {
    return this.interceptors;
  }

  InternalCache internalCache()
  {
    return this.internalCache;
  }

  public List<Interceptor> networkInterceptors()
  {
    return this.networkInterceptors;
  }

  public Call newCall(Request paramRequest)
  {
    return new Call(this, paramRequest);
  }

  RouteDatabase routeDatabase()
  {
    return this.routeDatabase;
  }

  public OkHttpClient setAuthenticator(Authenticator paramAuthenticator)
  {
    this.authenticator = paramAuthenticator;
    return this;
  }

  public OkHttpClient setCache(Cache paramCache)
  {
    this.cache = paramCache;
    this.internalCache = null;
    return this;
  }

  public OkHttpClient setCertificatePinner(CertificatePinner paramCertificatePinner)
  {
    this.certificatePinner = paramCertificatePinner;
    return this;
  }

  public void setConnectTimeout(long paramLong, TimeUnit paramTimeUnit)
  {
    if (paramLong < 0L)
      throw new IllegalArgumentException("timeout < 0");
    if (paramTimeUnit == null)
      throw new IllegalArgumentException("unit == null");
    long l = paramTimeUnit.toMillis(paramLong);
    if (l > 2147483647L)
      throw new IllegalArgumentException("Timeout too large.");
    if ((l == 0L) && (paramLong > 0L))
      throw new IllegalArgumentException("Timeout too small.");
    this.connectTimeout = ((int)l);
  }

  public OkHttpClient setConnectionPool(ConnectionPool paramConnectionPool)
  {
    this.connectionPool = paramConnectionPool;
    return this;
  }

  public OkHttpClient setConnectionSpecs(List<ConnectionSpec> paramList)
  {
    this.connectionSpecs = Util.immutableList(paramList);
    return this;
  }

  public OkHttpClient setCookieHandler(CookieHandler paramCookieHandler)
  {
    this.cookieHandler = paramCookieHandler;
    return this;
  }

  public OkHttpClient setDispatcher(Dispatcher paramDispatcher)
  {
    if (paramDispatcher == null)
      throw new IllegalArgumentException("dispatcher == null");
    this.dispatcher = paramDispatcher;
    return this;
  }

  public void setFollowRedirects(boolean paramBoolean)
  {
    this.followRedirects = paramBoolean;
  }

  public OkHttpClient setFollowSslRedirects(boolean paramBoolean)
  {
    this.followSslRedirects = paramBoolean;
    return this;
  }

  public OkHttpClient setHostnameVerifier(HostnameVerifier paramHostnameVerifier)
  {
    this.hostnameVerifier = paramHostnameVerifier;
    return this;
  }

  void setInternalCache(InternalCache paramInternalCache)
  {
    this.internalCache = paramInternalCache;
    this.cache = null;
  }

  public OkHttpClient setProtocols(List<Protocol> paramList)
  {
    paramList = Util.immutableList(paramList);
    if (!paramList.contains(Protocol.HTTP_1_1))
      throw new IllegalArgumentException("protocols doesn't contain http/1.1: " + paramList);
    if (paramList.contains(Protocol.HTTP_1_0))
      throw new IllegalArgumentException("protocols must not contain http/1.0: " + paramList);
    if (paramList.contains(null))
      throw new IllegalArgumentException("protocols must not contain null");
    this.protocols = Util.immutableList(paramList);
    return this;
  }

  public OkHttpClient setProxy(Proxy paramProxy)
  {
    this.proxy = paramProxy;
    return this;
  }

  public OkHttpClient setProxySelector(ProxySelector paramProxySelector)
  {
    this.proxySelector = paramProxySelector;
    return this;
  }

  public void setReadTimeout(long paramLong, TimeUnit paramTimeUnit)
  {
    if (paramLong < 0L)
      throw new IllegalArgumentException("timeout < 0");
    if (paramTimeUnit == null)
      throw new IllegalArgumentException("unit == null");
    long l = paramTimeUnit.toMillis(paramLong);
    if (l > 2147483647L)
      throw new IllegalArgumentException("Timeout too large.");
    if ((l == 0L) && (paramLong > 0L))
      throw new IllegalArgumentException("Timeout too small.");
    this.readTimeout = ((int)l);
  }

  public void setRetryOnConnectionFailure(boolean paramBoolean)
  {
    this.retryOnConnectionFailure = paramBoolean;
  }

  public OkHttpClient setSocketFactory(SocketFactory paramSocketFactory)
  {
    this.socketFactory = paramSocketFactory;
    return this;
  }

  public OkHttpClient setSslSocketFactory(SSLSocketFactory paramSSLSocketFactory)
  {
    this.sslSocketFactory = paramSSLSocketFactory;
    return this;
  }

  public void setWriteTimeout(long paramLong, TimeUnit paramTimeUnit)
  {
    if (paramLong < 0L)
      throw new IllegalArgumentException("timeout < 0");
    if (paramTimeUnit == null)
      throw new IllegalArgumentException("unit == null");
    long l = paramTimeUnit.toMillis(paramLong);
    if (l > 2147483647L)
      throw new IllegalArgumentException("Timeout too large.");
    if ((l == 0L) && (paramLong > 0L))
      throw new IllegalArgumentException("Timeout too small.");
    this.writeTimeout = ((int)l);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.OkHttpClient
 * JD-Core Version:    0.6.2
 */