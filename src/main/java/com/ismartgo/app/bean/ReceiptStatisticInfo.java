package com.ismartgo.app.bean;

import java.util.ArrayList;
import java.util.List;

public class ReceiptStatisticInfo
{
  private List<Items> items = new ArrayList();
  private double totalmoney;
  private int totaltimes;

  public List<Items> getItems()
  {
    return this.items;
  }

  public double getTotalmoney()
  {
    return this.totalmoney;
  }

  public int getTotaltimes()
  {
    return this.totaltimes;
  }

  public void setItems(List<Items> paramList)
  {
    this.items = paramList;
  }

  public void setTotalmoney(double paramDouble)
  {
    this.totalmoney = paramDouble;
  }

  public void setTotaltimes(int paramInt)
  {
    this.totaltimes = paramInt;
  }

  public class Items
  {
    private int id;
    private double money;
    private int times;

    public Items()
    {
    }

    public int getId()
    {
      return this.id;
    }

    public double getMoney()
    {
      return this.money;
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

    public void setTimes(int paramInt)
    {
      this.times = paramInt;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.bean.ReceiptStatisticInfo
 * JD-Core Version:    0.6.2
 */