package com.ismartgo.app.bean;

import java.util.ArrayList;
import java.util.List;

public class GoodsClazz
{
  private int clazzId;
  private List<GoodsClazz> goodsClazzList;
  private String name;
  private int shopTypeId;
  private String shopTypeName;

  public int getClazzId()
  {
    return this.clazzId;
  }

  public List<GoodsClazz> getGoodsClazzList()
  {
    return this.goodsClazzList;
  }

  public String getName()
  {
    return this.name;
  }

  public int getShopTypeId()
  {
    return this.shopTypeId;
  }

  public String getShopTypeName()
  {
    return this.shopTypeName;
  }

  public void setClazzId(int paramInt)
  {
    this.clazzId = paramInt;
  }

  public void setGoodsClazzList(List<GoodsClazz> paramList)
  {
    Object localObject = paramList;
    if (paramList == null)
      localObject = new ArrayList();
    this.goodsClazzList = ((List)localObject);
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public void setShopTypeId(int paramInt)
  {
    this.shopTypeId = paramInt;
  }

  public void setShopTypeName(String paramString)
  {
    this.shopTypeName = paramString;
  }

  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("shopTypeId:  ").append(this.shopTypeId).append("\n");
    localStringBuffer.append("clazzId:  ").append(this.clazzId).append("\n");
    localStringBuffer.append("name:  ").append(this.name).append("\n");
    localStringBuffer.append("goodsClazzList:  ").append(this.goodsClazzList).append("\n");
    return localStringBuffer.toString();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.bean.GoodsClazz
 * JD-Core Version:    0.6.2
 */