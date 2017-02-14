package com.ismartgo.app.bean;

public class DistrictArea
{
  private int distance;
  private int districtId;
  private String name;
  private int townId;

  public int getDistance()
  {
    return this.distance;
  }

  public int getDistrictId()
  {
    return this.districtId;
  }

  public String getName()
  {
    return this.name;
  }

  public int getTownId()
  {
    return this.townId;
  }

  public void setDistance(int paramInt)
  {
    this.distance = paramInt;
  }

  public void setDistrictId(int paramInt)
  {
    this.districtId = paramInt;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public void setTownId(int paramInt)
  {
    this.townId = paramInt;
  }

  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("\n");
    localStringBuffer.append("------------------------------------------").append("\n");
    localStringBuffer.append("districtId: ").append(this.districtId).append("\n").append("townId: ").append(this.townId).append("\n").append("name: ").append(this.name).append("\n");
    localStringBuffer.append("------------------------------------------").append("\n").append("\n");
    return localStringBuffer.toString();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.bean.DistrictArea
 * JD-Core Version:    0.6.2
 */