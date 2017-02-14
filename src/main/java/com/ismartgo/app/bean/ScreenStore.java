package com.ismartgo.app.bean;

import java.util.List;

public class ScreenStore
{
  private String name;
  private List<SalesArea> salesAreas_list;
  private int shopTypeId;

  public String getName()
  {
    return this.name;
  }

  public List<SalesArea> getSalesAreas_list()
  {
    return this.salesAreas_list;
  }

  public int getShopTypeId()
  {
    return this.shopTypeId;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public void setSalesAreas_list(List<SalesArea> paramList)
  {
    this.salesAreas_list = paramList;
  }

  public void setShopTypeId(int paramInt)
  {
    this.shopTypeId = paramInt;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.bean.ScreenStore
 * JD-Core Version:    0.6.2
 */