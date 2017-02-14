package com.mrwujay.cascade.model;

import java.util.List;

public class ProvinceModel
{
  private List<CityModel> cityList;
  private String name;

  public ProvinceModel()
  {
  }

  public ProvinceModel(String paramString, List<CityModel> paramList)
  {
    this.name = paramString;
    this.cityList = paramList;
  }

  public List<CityModel> getCityList()
  {
    return this.cityList;
  }

  public String getName()
  {
    return this.name;
  }

  public void setCityList(List<CityModel> paramList)
  {
    this.cityList = paramList;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public String toString()
  {
    return "ProvinceModel [name=" + this.name + ", cityList=" + this.cityList + "]";
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.mrwujay.cascade.model.ProvinceModel
 * JD-Core Version:    0.6.2
 */