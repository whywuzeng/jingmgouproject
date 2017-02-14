package com.ismartgo.app.bean;

public class SmartHeadInfo
{
  private String clickurl;
  private int id;
  private String title;

  public SmartHeadInfo()
  {
  }

  public SmartHeadInfo(String paramString1, int paramInt, String paramString2)
  {
    this.clickurl = paramString1;
    this.id = paramInt;
    this.title = paramString2;
  }

  public String getClickurl()
  {
    return this.clickurl;
  }

  public int getId()
  {
    return this.id;
  }

  public String getTitle()
  {
    return this.title;
  }

  public void setClickurl(String paramString)
  {
    this.clickurl = paramString;
  }

  public void setId(int paramInt)
  {
    this.id = paramInt;
  }

  public void setTitle(String paramString)
  {
    this.title = paramString;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.bean.SmartHeadInfo
 * JD-Core Version:    0.6.2
 */