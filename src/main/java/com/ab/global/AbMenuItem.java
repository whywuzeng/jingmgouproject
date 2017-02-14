package com.ab.global;

public class AbMenuItem
{
  private int iconId;
  private int id;
  private String mark;
  private String text;

  public AbMenuItem()
  {
  }

  public AbMenuItem(int paramInt, String paramString)
  {
    this.id = paramInt;
    this.text = paramString;
  }

  public AbMenuItem(String paramString)
  {
    this.text = paramString;
  }

  public int getIconId()
  {
    return this.iconId;
  }

  public int getId()
  {
    return this.id;
  }

  public String getMark()
  {
    return this.mark;
  }

  public String getText()
  {
    return this.text;
  }

  public void setIconId(int paramInt)
  {
    this.iconId = paramInt;
  }

  public void setId(int paramInt)
  {
    this.id = paramInt;
  }

  public void setMark(String paramString)
  {
    this.mark = paramString;
  }

  public void setText(String paramString)
  {
    this.text = paramString;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.global.AbMenuItem
 * JD-Core Version:    0.6.2
 */