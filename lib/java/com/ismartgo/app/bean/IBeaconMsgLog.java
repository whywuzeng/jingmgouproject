package com.ismartgo.app.bean;

import net.tsz.afinal.annotation.sqlite.Table;

@Table(name="IBeaconMsgLog")
public class IBeaconMsgLog
{
  private String date;
  private int id;
  private String major;
  private String minor;
  private String uuid;

  public String getDate()
  {
    return this.date;
  }

  public int getId()
  {
    return this.id;
  }

  public String getMajor()
  {
    return this.major;
  }

  public String getMinor()
  {
    return this.minor;
  }

  public String getUuid()
  {
    return this.uuid;
  }

  public void setDate(String paramString)
  {
    this.date = paramString;
  }

  public void setId(int paramInt)
  {
    this.id = paramInt;
  }

  public void setMajor(String paramString)
  {
    this.major = paramString;
  }

  public void setMinor(String paramString)
  {
    this.minor = paramString;
  }

  public void setUuid(String paramString)
  {
    this.uuid = paramString;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.bean.IBeaconMsgLog
 * JD-Core Version:    0.6.2
 */