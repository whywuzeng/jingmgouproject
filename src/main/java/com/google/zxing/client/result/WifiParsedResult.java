package com.google.zxing.client.result;

public final class WifiParsedResult extends ParsedResult
{
  private final String networkEncryption;
  private final String password;
  private final String ssid;

  public WifiParsedResult(String paramString1, String paramString2, String paramString3)
  {
    super(ParsedResultType.WIFI);
    this.ssid = paramString2;
    this.networkEncryption = paramString1;
    this.password = paramString3;
  }

  public String getDisplayResult()
  {
    StringBuffer localStringBuffer = new StringBuffer(80);
    maybeAppend(this.ssid, localStringBuffer);
    maybeAppend(this.networkEncryption, localStringBuffer);
    maybeAppend(this.password, localStringBuffer);
    return localStringBuffer.toString();
  }

  public String getNetworkEncryption()
  {
    return this.networkEncryption;
  }

  public String getPassword()
  {
    return this.password;
  }

  public String getSsid()
  {
    return this.ssid;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.WifiParsedResult
 * JD-Core Version:    0.6.2
 */