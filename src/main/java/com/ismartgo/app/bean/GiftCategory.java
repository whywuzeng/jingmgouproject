package com.ismartgo.app.bean;

import java.io.Serializable;

public class GiftCategory
  implements Serializable
{
  private String giftCategoryName;
  private int giftType;
  private int id;

  public String getGiftCategoryName()
  {
    return this.giftCategoryName;
  }

  public int getGiftType()
  {
    return this.giftType;
  }

  public int getId()
  {
    return this.id;
  }

  public void setGiftCategoryName(String paramString)
  {
    this.giftCategoryName = paramString;
  }

  public void setGiftType(int paramInt)
  {
    this.giftType = paramInt;
  }

  public void setId(int paramInt)
  {
    this.id = paramInt;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.bean.GiftCategory
 * JD-Core Version:    0.6.2
 */