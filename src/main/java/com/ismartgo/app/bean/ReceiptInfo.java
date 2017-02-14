package com.ismartgo.app.bean;

import java.io.Serializable;

public class ReceiptInfo
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private String begindate;
  private int clickcnt;
  private String detailurl;
  private String enddate;
  private int gameId;
  private int id;
  private String imgurl;
  private int joinno;
  private String prizename;
  private int status;
  private String title;

  public String getBegindate()
  {
    return this.begindate;
  }

  public int getClickcnt()
  {
    return this.clickcnt;
  }

  public String getDetailurl()
  {
    return this.detailurl;
  }

  public String getEnddate()
  {
    return this.enddate;
  }

  public int getGameId()
  {
    return this.gameId;
  }

  public int getId()
  {
    return this.id;
  }

  public String getImgurl()
  {
    return this.imgurl;
  }

  public int getJoinno()
  {
    return this.joinno;
  }

  public String getPrizename()
  {
    return this.prizename;
  }

  public int getStatus()
  {
    return this.status;
  }

  public String getTitle()
  {
    return this.title;
  }

  public void setBegindate(String paramString)
  {
    this.begindate = paramString;
  }

  public void setClickcnt(int paramInt)
  {
    this.clickcnt = paramInt;
  }

  public void setDetailurl(String paramString)
  {
    this.detailurl = paramString;
  }

  public void setEnddate(String paramString)
  {
    this.enddate = paramString;
  }

  public void setGameId(int paramInt)
  {
    this.gameId = paramInt;
  }

  public void setId(int paramInt)
  {
    this.id = paramInt;
  }

  public void setImgurl(String paramString)
  {
    this.imgurl = paramString;
  }

  public void setJoinno(int paramInt)
  {
    this.joinno = paramInt;
  }

  public void setPrizename(String paramString)
  {
    this.prizename = paramString;
  }

  public void setStatus(int paramInt)
  {
    this.status = paramInt;
  }

  public void setTitle(String paramString)
  {
    this.title = paramString;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.bean.ReceiptInfo
 * JD-Core Version:    0.6.2
 */