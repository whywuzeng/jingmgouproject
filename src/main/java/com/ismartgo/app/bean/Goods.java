package com.ismartgo.app.bean;

import java.io.Serializable;

public class Goods
  implements Serializable
{
  private String address;
  private int beannumber;
  private String distance;
  private String goodsDescribe;
  private String goodsEndDate;
  private int goodsId;
  private String goodsLogo;
  private String goodsName;
  private double goodsPrice;
  private int goodsScan;
  private String goodsStartDate;
  private int goodsTag;
  private String h5Url;
  private int height;
  private boolean isCollect = false;
  private boolean isScaned;
  private int shopId;
  private int width;

  public String getAddress()
  {
    return this.address;
  }

  public int getBeannumber()
  {
    return this.beannumber;
  }

  public String getDistance()
  {
    return this.distance;
  }

  public String getGoodsDescribe()
  {
    return this.goodsDescribe;
  }

  public String getGoodsEndDate()
  {
    return this.goodsEndDate;
  }

  public int getGoodsId()
  {
    return this.goodsId;
  }

  public String getGoodsLogo()
  {
    return this.goodsLogo;
  }

  public String getGoodsName()
  {
    return this.goodsName;
  }

  public double getGoodsPrice()
  {
    return this.goodsPrice;
  }

  public int getGoodsScan()
  {
    return this.goodsScan;
  }

  public String getGoodsStartDate()
  {
    return this.goodsStartDate;
  }

  public int getGoodsTag()
  {
    return this.goodsTag;
  }

  public String getH5Url()
  {
    return this.h5Url;
  }

  public int getHeight()
  {
    return this.height;
  }

  public int getShopId()
  {
    return this.shopId;
  }

  public int getWidth()
  {
    return this.width;
  }

  public boolean isCollect()
  {
    return this.isCollect;
  }

  public boolean isScaned()
  {
    return this.isScaned;
  }

  public void setAddress(String paramString)
  {
    this.address = paramString;
  }

  public void setBeannumber(int paramInt)
  {
    this.beannumber = paramInt;
  }

  public void setCollect(boolean paramBoolean)
  {
    this.isCollect = paramBoolean;
  }

  public void setDistance(String paramString)
  {
    this.distance = paramString;
  }

  public void setGoodsDescribe(String paramString)
  {
    this.goodsDescribe = paramString;
  }

  public void setGoodsEndDate(String paramString)
  {
    this.goodsEndDate = paramString;
  }

  public void setGoodsId(int paramInt)
  {
    this.goodsId = paramInt;
  }

  public void setGoodsLogo(String paramString)
  {
    this.goodsLogo = paramString;
  }

  public void setGoodsName(String paramString)
  {
    this.goodsName = paramString;
  }

  public void setGoodsPrice(double paramDouble)
  {
    this.goodsPrice = paramDouble;
  }

  public void setGoodsScan(int paramInt)
  {
    this.goodsScan = paramInt;
  }

  public void setGoodsStartDate(String paramString)
  {
    this.goodsStartDate = paramString;
  }

  public void setGoodsTag(int paramInt)
  {
    this.goodsTag = paramInt;
  }

  public void setH5Url(String paramString)
  {
    this.h5Url = paramString;
  }

  public void setHeight(int paramInt)
  {
    this.height = paramInt;
  }

  public void setScaned(boolean paramBoolean)
  {
    this.isScaned = paramBoolean;
  }

  public void setShopId(int paramInt)
  {
    this.shopId = paramInt;
  }

  public void setWidth(int paramInt)
  {
    this.width = paramInt;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.bean.Goods
 * JD-Core Version:    0.6.2
 */