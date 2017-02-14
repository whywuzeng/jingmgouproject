package com.ismartgo.app.oos;

public class FederationToken
{
  private String accessKeyId;
  private String accessKeySecret;
  private long expiration;
  private String securityToken;

  public FederationToken(String paramString1, String paramString2, String paramString3, long paramLong)
  {
    this.accessKeyId = paramString1;
    this.accessKeySecret = paramString2;
    this.securityToken = paramString3;
    this.expiration = paramLong;
  }

  public String getAccessKeyId()
  {
    return this.accessKeyId;
  }

  public String getAccessKeySecret()
  {
    return this.accessKeySecret;
  }

  public long getExpiration()
  {
    return this.expiration;
  }

  public String getSecurityToken()
  {
    return this.securityToken;
  }

  public void setAccessKeyId(String paramString)
  {
    this.accessKeyId = paramString;
  }

  public void setAccessKeySecret(String paramString)
  {
    this.accessKeySecret = paramString;
  }

  public void setExpiration(long paramLong)
  {
    this.expiration = paramLong;
  }

  public void setSecurityToken(String paramString)
  {
    this.securityToken = paramString;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.oos.FederationToken
 * JD-Core Version:    0.6.2
 */