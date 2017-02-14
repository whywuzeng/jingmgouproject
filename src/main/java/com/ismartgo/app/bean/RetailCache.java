package com.ismartgo.app.bean;

import java.util.List;

public class RetailCache
{
  private int districtId;
  private List<SalesArea> salesAreasList;
  private int shopTypeId;
  private int townId;

  public int getDistrictId()
  {
    return this.districtId;
  }

  public List<SalesArea> getSalesAreasList()
  {
    return this.salesAreasList;
  }

  public int getShopTypeId()
  {
    return this.shopTypeId;
  }

  public int getTownId()
  {
    return this.townId;
  }

  public void setDistrictId(int paramInt)
  {
    this.districtId = paramInt;
  }

  public void setSalesAreasList(List<SalesArea> paramList)
  {
    this.salesAreasList = paramList;
  }

  public void setShopTypeId(int paramInt)
  {
    this.shopTypeId = paramInt;
  }

  public void setTownId(int paramInt)
  {
    this.townId = paramInt;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.bean.RetailCache
 * JD-Core Version:    0.6.2
 */