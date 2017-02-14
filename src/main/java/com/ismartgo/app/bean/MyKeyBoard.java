package com.ismartgo.app.bean;

public class MyKeyBoard
{
  private int featurdResource;
  private int id;
  private boolean isFeatures;
  private String text;

  public int getFeaturdResource()
  {
    return this.featurdResource;
  }

  public int getId()
  {
    return this.id;
  }

  public String getText()
  {
    return this.text;
  }

  public boolean isFeatures()
  {
    return this.isFeatures;
  }

  public void setFeaturdResource(int paramInt)
  {
    this.featurdResource = paramInt;
  }

  public void setFeatures(boolean paramBoolean)
  {
    this.isFeatures = paramBoolean;
  }

  public void setId(int paramInt)
  {
    this.id = paramInt;
  }

  public void setText(String paramString)
  {
    this.text = paramString;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.bean.MyKeyBoard
 * JD-Core Version:    0.6.2
 */