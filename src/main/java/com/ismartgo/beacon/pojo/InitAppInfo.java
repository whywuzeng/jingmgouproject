package com.ismartgo.beacon.pojo;

public class InitAppInfo
{
  private String msg;
  private int status;
  private String time;
  String uuid;

  public InitAppInfo()
  {
  }

  public InitAppInfo(int paramInt, String paramString1, String paramString2, String paramString3)
  {
    this.status = paramInt;
    this.time = paramString1;
    this.msg = paramString2;
    this.uuid = paramString3;
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
 * Qualified Name:     com.ismartgo.beacon.pojo.InitAppInfo
 * JD-Core Version:    0.6.2
 */