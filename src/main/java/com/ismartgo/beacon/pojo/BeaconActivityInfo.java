package com.ismartgo.beacon.pojo;

import java.io.Serializable;

public class BeaconActivityInfo
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private int activityId;
  private String activityName;
  private String activityUrl;
  private int id;
  private int major;
  private int minor;
  private String msg;
  private int status;
  private String time;
  private String uuid;

  public BeaconActivityInfo()
  {
  }

  public BeaconActivityInfo(String paramString1, String paramString2, int paramInt1, String paramString3, int paramInt2, int paramInt3)
  {
    this.activityName = paramString1;
    this.activityUrl = paramString2;
    this.id = paramInt1;
    this.uuid = paramString3;
    this.major = paramInt2;
    this.minor = paramInt3;
  }

  public int getActivityId()
  {
    return this.activityId;
  }

  public String getActivityName()
  {
    return this.activityName;
  }

  public String getActivityUrl()
  {
    return this.activityUrl;
  }

  public int getId()
  {
    return this.id;
  }

  public int getMajor()
  {
    return this.major;
  }

  public int getMinor()
  {
    return this.minor;
  }

  public String getMsg()
  {
    return this.msg;
  }

  public int getStatus()
  {
    return this.status;
  }

  public String getTime()
  {
    return this.time;
  }

  public String getUuid()
  {
    return this.uuid;
  }

  public void setActivityId(int paramInt)
  {
    this.activityId = paramInt;
  }

  public void setActivityName(String paramString)
  {
    this.activityName = paramString;
  }

  public void setActivityUrl(String paramString)
  {
    this.activityUrl = paramString;
  }

  public void setId(int paramInt)
  {
    this.id = paramInt;
  }

  public void setMajor(int paramInt)
  {
    this.major = paramInt;
  }

  public void setMinor(int paramInt)
  {
    this.minor = paramInt;
  }

  public void setMsg(String paramString)
  {
    this.msg = paramString;
  }

  public void setStatus(int paramInt)
  {
    this.status = paramInt;
  }

  public void setTime(String paramString)
  {
    this.time = paramString;
  }

  public void setUuid(String paramString)
  {
    this.uuid = paramString;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.beacon.pojo.BeaconActivityInfo
 * JD-Core Version:    0.6.2
 */