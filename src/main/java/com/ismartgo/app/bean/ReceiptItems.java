package com.ismartgo.app.bean;

import java.io.Serializable;

public class ReceiptItems
  implements Serializable
{
  private double daymoney;
  private int id;
  private String imgurl;
  private double money;
  private String month;
  private String receiptDate;
  private String shopTypeName;
  private String shopTypeUrl;
  private int shoptype;

  public ReceiptItems(String paramString1, double paramDouble1, int paramInt1, String paramString2, double paramDouble2, String paramString3, int paramInt2, String paramString4, String paramString5)
  {
    this.receiptDate = paramString1;
    this.daymoney = paramDouble1;
    this.id = paramInt1;
    this.imgurl = paramString2;
    this.money = paramDouble2;
    this.month = paramString3;
    this.shoptype = paramInt2;
    this.shopTypeUrl = paramString4;
    this.shopTypeName = paramString5;
  }

  public String getDate()
  {
    return this.receiptDate;
  }

  public double getDaymoney()
  {
    return this.daymoney;
  }

  public int getId()
  {
    return this.id;
  }

  public String getImgurl()
  {
    return this.imgurl;
  }

  public double getMoney()
  {
    return this.money;
  }

  public String getMonth()
  {
    return this.month;
  }

  public String getReceiptDate()
  {
    return this.receiptDate;
  }

  public String getShopTypeName()
  {
    return this.shopTypeName;
  }

  public String getShopTypeUrl()
  {
    return this.shopTypeUrl;
  }

  public int getShoptype()
  {
    return this.shoptype;
  }

  public void setDate(String paramString)
  {
    this.receiptDate = paramString;
  }

  public void setDaymoney(double paramDouble)
  {
    this.daymoney = paramDouble;
  }

  public void setId(int paramInt)
  {
    this.id = paramInt;
  }

  public void setImgurl(String paramString)
  {
    this.imgurl = paramString;
  }

  public void setMoney(double paramDouble)
  {
    this.money = paramDouble;
  }

  public void setMonth(String paramString)
  {
    this.month = paramString;
  }

  public void setReceiptDate(String paramString)
  {
    this.receiptDate = paramString;
  }

  public void setShopTypeName(String paramString)
  {
    this.shopTypeName = paramString;
  }

  public void setShopTypeUrl(String paramString)
  {
    this.shopTypeUrl = paramString;
  }

  public void setShoptype(int paramInt)
  {
    this.shoptype = paramInt;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.bean.ReceiptItems
 * JD-Core Version:    0.6.2
 */