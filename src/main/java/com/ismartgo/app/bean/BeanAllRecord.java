package com.ismartgo.app.bean;

public class BeanAllRecord
{
  private String date;
  private String explanation;
  private int num;
  private int type;

  public String getDate()
  {
    return this.date;
  }

  public String getExplanation()
  {
    return this.explanation;
  }

  public int getNum()
  {
    return this.num;
  }

  public int getType()
  {
    return this.type;
  }

  public void setDate(String paramString)
  {
    this.date = paramString;
  }

  public void setExplanation(String paramString)
  {
    this.explanation = paramString;
  }

  public void setNum(int paramInt)
  {
    this.num = paramInt;
  }

  public void setType(int paramInt)
  {
    this.type = paramInt;
  }

  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("date: ").append(this.date).append('\n').append("explanation: ").append(this.explanation).append('\n').append("num: ").append(this.num).append('\n').append("type: ").append(this.type).append('\n');
    return localStringBuffer.toString();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.bean.BeanAllRecord
 * JD-Core Version:    0.6.2
 */