package com.ismartgo.app.bean;

public class NewReceiptInfo
{
  public ReceiptDayInfo dayInfo;
  private int flag;
  private ReceiptItems items;
  private int type;

  public ReceiptDayInfo getDayInfo()
  {
    return this.dayInfo;
  }

  public int getFlag()
  {
    return this.flag;
  }

  public ReceiptItems getItems()
  {
    return this.items;
  }

  public int getType()
  {
    return this.type;
  }

  public void setDayInfo(ReceiptDayInfo paramReceiptDayInfo)
  {
    this.dayInfo = paramReceiptDayInfo;
  }

  public void setFlag(int paramInt)
  {
    this.flag = paramInt;
  }

  public void setItems(ReceiptItems paramReceiptItems)
  {
    this.items = paramReceiptItems;
  }

  public void setType(int paramInt)
  {
    this.type = paramInt;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.bean.NewReceiptInfo
 * JD-Core Version:    0.6.2
 */