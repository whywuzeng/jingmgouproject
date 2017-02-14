package com.ismartgo.app.bean;

import java.io.Serializable;

public class IBeacon
  implements Serializable
{
  public int major;
  public int minor;
  public String proximityUuid;

  public int getMajor()
  {
    return this.major;
  }

  public int getMinor()
  {
    return this.minor;
  }

  public String getProximityUuid()
  {
    return this.proximityUuid;
  }

  public void setMajor(int paramInt)
  {
    this.major = paramInt;
  }

  public void setMinor(int paramInt)
  {
    this.minor = paramInt;
  }

  public void setProximityUuid(String paramString)
  {
    this.proximityUuid = paramString;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.bean.IBeacon
 * JD-Core Version:    0.6.2
 */