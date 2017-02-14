package com.ismartgo.app.bean;

import java.util.List;

public class MyReceiptInfo
{
  private List<ReceiptItems> items;
  private List<ReceiptMonthInfo> monthinfos;
  private int type;

  public List<ReceiptItems> getItems()
  {
    return this.items;
  }

  public List<ReceiptMonthInfo> getMonthinfos()
  {
    return this.monthinfos;
  }

  public int getType()
  {
    return this.type;
  }

  public void setItems(List<ReceiptItems> paramList)
  {
    this.items = paramList;
  }

  public void setMonthinfos(List<ReceiptMonthInfo> paramList)
  {
    this.monthinfos = paramList;
  }

  public void setType(int paramInt)
  {
    this.type = paramInt;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.bean.MyReceiptInfo
 * JD-Core Version:    0.6.2
 */