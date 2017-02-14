package com.ismartgo.app.bean;

import java.util.List;

public class Area
{
  private String areaName;
  private int districtId;
  List<DistrictArea> districtList;

  public String getAreaName()
  {
    return this.areaName;
  }

  public int getDistrictId()
  {
    return this.districtId;
  }

  public List<DistrictArea> getDistrictList()
  {
    return this.districtList;
  }

  public void setAreaName(String paramString)
  {
    this.areaName = paramString;
  }

  public void setDistrictId(int paramInt)
  {
    this.districtId = paramInt;
  }

  public void setDistrictList(List<DistrictArea> paramList)
  {
    this.districtList = paramList;
  }

  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("districtId: ").append(this.districtId).append("\n").append("areaName: ").append(this.areaName).append("\n").append("districtList: ").append(this.districtList).append("\n");
    return localStringBuffer.toString();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.bean.Area
 * JD-Core Version:    0.6.2
 */