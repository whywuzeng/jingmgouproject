package com.alibaba.sdk.android.oss.model;

public class OSSFederationToken
{
  private long expiration;
  private String securityToken;
  private String tempAk;
  private String tempSk;

  public OSSFederationToken()
  {
  }

  public OSSFederationToken(String paramString1, String paramString2, String paramString3, long paramLong)
  {
    this.tempAk = paramString1;
    this.tempSk = paramString2;
    this.securityToken = paramString3;
    setExpiration(paramLong);
  }

  public long getExpiration()
  {
    return this.expiration;
  }

  public String getSecurityToken()
  {
    return this.securityToken;
  }

  public String getTempAK()
  {
    return this.tempAk;
  }

  public String getTempSK()
  {
    return this.tempSk;
  }

  public void setExpiration(long paramLong)
  {
    this.expiration = paramLong;
  }

  public void setSecurityToken(String paramString)
  {
    this.securityToken = paramString;
  }

  public void setTempAk(String paramString)
  {
    this.tempAk = paramString;
  }

  public void setTempSk(String paramString)
  {
    this.tempSk = paramString;
  }

  public String toString()
  {
    return "OSSFederationToken [tempAk=" + this.tempAk + ", tempSk=" + this.tempSk + ", securityToken=" + this.securityToken + ", expiration=" + this.expiration + "]";
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.sdk.android.oss.model.OSSFederationToken
 * JD-Core Version:    0.6.2
 */