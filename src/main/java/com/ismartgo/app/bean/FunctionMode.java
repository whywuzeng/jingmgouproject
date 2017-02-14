package com.ismartgo.app.bean;

public class FunctionMode
{
  private String clickurl;
  private int id;
  private String imgurl;
  private String title;

  public FunctionMode()
  {
  }

  public FunctionMode(String paramString1, int paramInt, String paramString2, String paramString3)
  {
    this.clickurl = paramString1;
    this.id = paramInt;
    this.imgurl = paramString2;
    this.title = paramString3;
  }

  public String getClickUrl()
  {
    return this.clickurl;
  }

  public int getId()
  {
    return this.id;
  }

  public String getImgUrl()
  {
    return this.imgurl;
  }

  public String getTitle()
  {
    return this.title;
  }

  public void setClickUrl(String paramString)
  {
    this.clickurl = paramString;
  }

  public void setId(int paramInt)
  {
    this.id = paramInt;
  }

  public void setImgUrl(String paramString)
  {
    this.imgurl = paramString;
  }

  public void setTitle(String paramString)
  {
    this.title = paramString;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.bean.FunctionMode
 * JD-Core Version:    0.6.2
 */