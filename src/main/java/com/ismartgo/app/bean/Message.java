package com.ismartgo.app.bean;

public class Message
{
  private String content;
  private int id;
  private int isRead;
  private String time;
  private String title;

  public String getContent()
  {
    return this.content;
  }

  public int getId()
  {
    return this.id;
  }

  public int getIsRead()
  {
    return this.isRead;
  }

  public String getTime()
  {
    return this.time;
  }

  public String getTitle()
  {
    return this.title;
  }

  public void setContent(String paramString)
  {
    this.content = paramString;
  }

  public void setId(int paramInt)
  {
    this.id = paramInt;
  }

  public void setIsRead(int paramInt)
  {
    this.isRead = paramInt;
  }

  public void setTime(String paramString)
  {
    this.time = paramString;
  }

  public void setTitle(String paramString)
  {
    this.title = paramString;
  }

  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("title: ").append(this.title).append("\n").append("time: ").append(this.time).append("\n").append("content: ").append(this.content).append("\n").append("\n").append("isRead: ").append(this.isRead).append("\n").append("\n").append("id: ").append(this.id);
    return localStringBuffer.toString();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.bean.Message
 * JD-Core Version:    0.6.2
 */