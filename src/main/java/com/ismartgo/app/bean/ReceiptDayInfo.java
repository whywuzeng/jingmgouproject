package com.ismartgo.app.bean;

public class ReceiptDayInfo
{
  private double money;
  private String month;
  private String time;

  public ReceiptDayInfo()
  {
  }

  public ReceiptDayInfo(String paramString1, double paramDouble, String paramString2)
  {
    this.time = paramString1;
    this.money = paramDouble;
    this.month = paramString2;
  }

  public double getMoney()
  {
    return this.money;
  }

  public String getMonth()
  {
    return this.month;
  }

  public String getTime()
  {
    return this.time;
  }

  public void setMoney(double paramDouble)
  {
    this.money = paramDouble;
  }

  public void setMoney(long paramLong)
  {
    this.money = paramLong;
  }

  public void setMonth(String paramString)
  {
    this.month = paramString;
  }

  public void setTime(String paramString)
  {
    this.time = paramString;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.bean.ReceiptDayInfo
 * JD-Core Version:    0.6.2
 */