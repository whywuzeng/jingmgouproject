package com.ismartgo.app.bean;

public class ReceiptMonthInfo
{
  private int id;
  private double money;
  private String month;
  private int times;

  public ReceiptMonthInfo(int paramInt1, double paramDouble, String paramString, int paramInt2)
  {
    this.id = paramInt1;
    this.money = paramDouble;
    this.month = paramString;
    this.times = paramInt2;
  }

  public int getId()
  {
    return this.id;
  }

  public double getMoney()
  {
    return this.money;
  }

  public String getMonth()
  {
    return this.month;
  }

  public int getTimes()
  {
    return this.times;
  }

  public void setId(int paramInt)
  {
    this.id = paramInt;
  }

  public void setMoney(double paramDouble)
  {
    this.money = paramDouble;
  }

  public void setMonth(String paramString)
  {
    this.month = paramString;
  }

  public void setTimes(int paramInt)
  {
    this.times = paramInt;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.bean.ReceiptMonthInfo
 * JD-Core Version:    0.6.2
 */