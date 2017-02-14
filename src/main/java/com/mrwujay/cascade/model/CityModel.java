package com.mrwujay.cascade.model;

import java.util.List;

public class CityModel
{
  private List<DistrictModel> districtList;
  private String name;

  public CityModel()
  {
  }

  public CityModel(String paramString, List<DistrictModel> paramList)
  {
    this.name = paramString;
    this.districtList = paramList;
  }

  public List<DistrictModel> getDistrictList()
  {
    return this.districtList;
  }

  public String getName()
  {
    return this.name;
  }

  public void setDistrictList(List<DistrictModel> paramList)
  {
    this.districtList = paramList;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public String toString()
  {
    return "CityModel [name=" + this.name + ", districtList=" + this.districtList + "]";
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.mrwujay.cascade.model.CityModel
 * JD-Core Version:    0.6.2
 */