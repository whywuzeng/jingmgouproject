package com.ismartgo.app.bean;

import java.io.Serializable;
import java.util.List;

public class Store
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private int beans;
  private String distance;
  private List<Goods> goods_list;
  private List<IBeacon> ibeacon_list;
  private String isSign;
  private int isibeacon;
  private double lat;
  private double lon;
  private int reatilId;
  private String reatilName;
  private int remainBean;
  private int saleCount;
  private String shopAddress;
  private int shopId;
  private String shopLogo;
  private String shopName;
  private int shopSign;
  private int shopTypeId;
  private String totalBean;
  private String userIsScan;
  private String userIsSign;

  public static long getSerialversionuid()
  {
    return 1L;
  }

  public int getBeans()
  {
    return this.beans;
  }

  public String getDistance()
  {
    return this.distance;
  }

  public List<Goods> getGoods_list()
  {
    return this.goods_list;
  }

  public List<IBeacon> getIbeacon_list()
  {
    return this.ibeacon_list;
  }

  public String getIsSign()
  {
    return this.isSign;
  }

  public int getIsibeacon()
  {
    return this.isibeacon;
  }

  public double getLat()
  {
    return this.lat;
  }

  public double getLon()
  {
    return this.lon;
  }

  public int getReatilId()
  {
    return this.reatilId;
  }

  public String getReatilName()
  {
    return this.reatilName;
  }

  public int getRemainBean()
  {
    return this.remainBean;
  }

  public int getSaleCount()
  {
    return this.saleCount;
  }

  public String getShopAddress()
  {
    return this.shopAddress;
  }

  public int getShopId()
  {
    return this.shopId;
  }

  public String getShopLogo()
  {
    return this.shopLogo;
  }

  public String getShopName()
  {
    return this.shopName;
  }

  public int getShopSign()
  {
    return this.shopSign;
  }

  public int getShopTypeId()
  {
    return this.shopTypeId;
  }

  public String getTotalBean()
  {
    return this.totalBean;
  }

  public String getUserIsScan()
  {
    return this.userIsScan;
  }

  public String getUserIsSign()
  {
    return this.userIsSign;
  }

  public void setBeans(int paramInt)
  {
    this.beans = paramInt;
  }

  public void setDistance(String paramString)
  {
    this.distance = paramString;
  }

  public void setGoods_list(List<Goods> paramList)
  {
    this.goods_list = paramList;
  }

  public void setIbeacon_list(List<IBeacon> paramList)
  {
    this.ibeacon_list = paramList;
  }

  public void setIsSign(String paramString)
  {
    this.isSign = paramString;
  }

  public void setIsibeacon(int paramInt)
  {
    this.isibeacon = paramInt;
  }

  public void setLat(double paramDouble)
  {
    this.lat = paramDouble;
  }

  public void setLon(double paramDouble)
  {
    this.lon = paramDouble;
  }

  public void setReatilId(int paramInt)
  {
    this.reatilId = paramInt;
  }

  public void setReatilName(String paramString)
  {
    this.reatilName = paramString;
  }

  public void setRemainBean(int paramInt)
  {
    this.remainBean = paramInt;
  }

  public void setSaleCount(int paramInt)
  {
    this.saleCount = paramInt;
  }

  public void setShopAddress(String paramString)
  {
    this.shopAddress = paramString;
  }

  public void setShopId(int paramInt)
  {
    this.shopId = paramInt;
  }

  public void setShopLogo(String paramString)
  {
    this.shopLogo = paramString;
  }

  public void setShopName(String paramString)
  {
    this.shopName = paramString;
  }

  public void setShopSign(int paramInt)
  {
    this.shopSign = paramInt;
  }

  public void setShopTypeId(int paramInt)
  {
    this.shopTypeId = paramInt;
  }

  public void setTotalBean(String paramString)
  {
    this.totalBean = paramString;
  }

  public void setUserIsScan(String paramString)
  {
    this.userIsScan = paramString;
  }

  public void setUserIsSign(String paramString)
  {
    this.userIsSign = paramString;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.bean.Store
 * JD-Core Version:    0.6.2
 */