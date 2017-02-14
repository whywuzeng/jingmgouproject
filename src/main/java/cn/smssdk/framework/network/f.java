package cn.smssdk.framework.network;

import java.net.Socket;
import java.security.KeyStore;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

public class f extends org.apache.http.conn.ssl.SSLSocketFactory
{
  SSLContext a = SSLContext.getInstance("TLS");

  public f(KeyStore paramKeyStore)
  {
    super(paramKeyStore);
    paramKeyStore = new g(this);
    this.a.init(null, new TrustManager[] { paramKeyStore }, null);
  }

  public Socket createSocket()
  {
    return this.a.getSocketFactory().createSocket();
  }

  public Socket createSocket(Socket paramSocket, String paramString, int paramInt, boolean paramBoolean)
  {
    return this.a.getSocketFactory().createSocket(paramSocket, paramString, paramInt, paramBoolean);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.smssdk.framework.network.f
 * JD-Core Version:    0.6.2
 */