package com.ismartgo.app.bean;

import java.io.Serializable;

public class BeanScope
  implements Serializable
{
  private int afterBean;
  private String beanScope;
  private int beforeBean;
  private int id;

  public int getAfterBean()
  {
    return this.afterBean;
  }

  public String getBeanScope()
  {
    return this.beanScope;
  }

  public int getBeforeBean()
  {
    return this.beforeBean;
  }

  public int getId()
  {
    return this.id;
  }

  public void setAfterBean(int paramInt)
  {
    this.afterBean = paramInt;
  }

  public void setBeanScope(String paramString)
  {
    this.beanScope = paramString;
  }

  public void setBeforeBean(int paramInt)
  {
    this.beforeBean = paramInt;
  }

  public void setId(int paramInt)
  {
    this.id = paramInt;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.bean.BeanScope
 * JD-Core Version:    0.6.2
 */