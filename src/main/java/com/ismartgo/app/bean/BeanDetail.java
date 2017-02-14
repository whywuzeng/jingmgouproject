package com.ismartgo.app.bean;

import java.util.List;

public class BeanDetail
{
  private List<BeanAllRecord> allRecordList;
  private int beanGetSum;
  private int beanUseSum;
  private List<BeanCostRecord> costRecordList;
  private List<BeanGetRecord> getRecordList;
  private int userBeanNum;

  public List<BeanAllRecord> getAllRecordList()
  {
    return this.allRecordList;
  }

  public int getBeanGetSum()
  {
    return this.beanGetSum;
  }

  public int getBeanUseSum()
  {
    return this.beanUseSum;
  }

  public List<BeanCostRecord> getCostRecordList()
  {
    return this.costRecordList;
  }

  public List<BeanGetRecord> getGetRecordList()
  {
    return this.getRecordList;
  }

  public int getUserBeanNum()
  {
    return this.userBeanNum;
  }

  public void setAllRecordList(List<BeanAllRecord> paramList)
  {
    this.allRecordList = paramList;
  }

  public void setBeanGetSum(int paramInt)
  {
    this.beanGetSum = paramInt;
  }

  public void setBeanUseSum(int paramInt)
  {
    this.beanUseSum = paramInt;
  }

  public void setCostRecordList(List<BeanCostRecord> paramList)
  {
    this.costRecordList = paramList;
  }

  public void setGetRecordList(List<BeanGetRecord> paramList)
  {
    this.getRecordList = paramList;
  }

  public void setUserBeanNum(int paramInt)
  {
    this.userBeanNum = paramInt;
  }

  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("userBeanNum: ").append(this.userBeanNum).append('\n').append("beanGetSum: ").append(this.beanGetSum).append('\n').append("beanUseSum: ").append(this.beanUseSum).append('\n').append("allRecordList: ").append(this.allRecordList).append('\n').append("getRecordList: ").append(this.getRecordList).append('\n').append("costRecordList: ").append(this.costRecordList).append('\n');
    return localStringBuffer.toString();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.bean.BeanDetail
 * JD-Core Version:    0.6.2
 */