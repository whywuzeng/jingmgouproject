package com.ismartgo.app.bean;

import java.io.Serializable;
import java.util.List;

public class IsNewMsg
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private boolean isNews;
  private List<Message> msg_list;

  public List<Message> getMsg_list()
  {
    return this.msg_list;
  }

  public boolean isNews()
  {
    return this.isNews;
  }

  public void setMsg_list(List<Message> paramList)
  {
    this.msg_list = paramList;
  }

  public void setNews(boolean paramBoolean)
  {
    this.isNews = paramBoolean;
  }

  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("isNews: ").append(this.isNews).append("\n");
    if (this.msg_list != null)
      localStringBuffer.append("msg: ").append(this.msg_list.toString());
    return localStringBuffer.toString();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.bean.IsNewMsg
 * JD-Core Version:    0.6.2
 */