package com.alibaba.sdk.android.oss.model;

public class ClientConfiguration
{
  private int connectTimeoutInMs = 30000;
  private boolean isSecurityTunnelRequired = false;
  private int maxConcurrentTaskNum = 6;
  private int maxConnections = 20;
  private String proxyHost;
  private int proxyPort;
  private int socketTimeoutInMs = 30000;

  public static ClientConfiguration defaultClientConfiguration()
  {
    return new ClientConfiguration();
  }

  public int getConnectTimeout()
  {
    return this.connectTimeoutInMs;
  }

  public int getMaxConcurrentTaskNum()
  {
    return this.maxConcurrentTaskNum;
  }

  public int getMaxConnections()
  {
    return this.maxConnections;
  }

  public String getProxyHost()
  {
    return this.proxyHost;
  }

  public int getProxyPort()
  {
    return this.proxyPort;
  }

  public int getSocketTimeout()
  {
    return this.socketTimeoutInMs;
  }

  public boolean isSecurityTunnelRequired()
  {
    return this.isSecurityTunnelRequired;
  }

  public void setConnectTimeout(int paramInt)
  {
    this.connectTimeoutInMs = paramInt;
  }

  public void setIsSecurityTunnelRequired(boolean paramBoolean)
  {
    this.isSecurityTunnelRequired = paramBoolean;
  }

  public void setMaxConcurrentTaskNum(int paramInt)
  {
    this.maxConcurrentTaskNum = paramInt;
  }

  @Deprecated
  public void setMaxConnections(int paramInt)
  {
    this.maxConnections = paramInt;
  }

  public void setProxy(String paramString, int paramInt)
  {
    this.proxyHost = paramString;
    this.proxyPort = paramInt;
  }

  public void setSocketTimeout(int paramInt)
  {
    this.socketTimeoutInMs = paramInt;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.sdk.android.oss.model.ClientConfiguration
 * JD-Core Version:    0.6.2
 */