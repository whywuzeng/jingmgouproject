package com.mrwujay.cascade.model;

public class DistrictModel
{
  private String name;

  public DistrictModel()
  {
  }

  public DistrictModel(String paramString)
  {
    this.name = paramString;
  }

  public String getName()
  {
    return this.name;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public String toString()
  {
    return "DistrictModel [name=" + this.name + "]";
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.mrwujay.cascade.model.DistrictModel
 * JD-Core Version:    0.6.2
 */