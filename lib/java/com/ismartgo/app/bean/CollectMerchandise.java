package com.ismartgo.app.bean;

import java.util.List;

public class CollectMerchandise
{
  private int collectId;
  private List<Goods> merchandises;

  public int getCollectId()
  {
    return this.collectId;
  }

  public List<Goods> getMerchandises()
  {
    return this.merchandises;
  }

  public void setCollectId(int paramInt)
  {
    this.collectId = paramInt;
  }

  public void setMerchandises(List<Goods> paramList)
  {
    this.merchandises = paramList;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.bean.CollectMerchandise
 * JD-Core Version:    0.6.2
 */