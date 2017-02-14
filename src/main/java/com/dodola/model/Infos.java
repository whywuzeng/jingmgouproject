package com.dodola.model;

import com.ismartgo.app.bean.Store;
import java.util.List;

public class Infos
{
  private List<Store> newsInfos;
  private String newsLast = "0";
  private int type = 0;

  public List<Store> getNewsInfos()
  {
    return this.newsInfos;
  }

  public String getNewsLast()
  {
    return this.newsLast;
  }

  public int getType()
  {
    return this.type;
  }

  public void setNewsInfos(List<Store> paramList)
  {
    this.newsInfos = paramList;
  }

  public void setNewsLast(String paramString)
  {
    this.newsLast = paramString;
  }

  public void setType(int paramInt)
  {
    this.type = paramInt;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.dodola.model.Infos
 * JD-Core Version:    0.6.2
 */