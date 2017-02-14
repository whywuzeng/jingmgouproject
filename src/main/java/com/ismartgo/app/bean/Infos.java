package com.ismartgo.app.bean;

import java.util.List;

public class Infos
{
  private List<Advertise> adver_list;
  private int flag;
  private GoodsDetail goods_detail;
  private String msg;
  private List<Message> msg_list;
  private int status;
  private List<Store> store_list;
  private String time;

  public List<Advertise> getAdver_list()
  {
    return this.adver_list;
  }

  public int getFlag()
  {
    return this.flag;
  }

  public GoodsDetail getGoods_detail()
  {
    return this.goods_detail;
  }

  public String getMsg()
  {
    return this.msg;
  }

  public List<Message> getMsg_list()
  {
    return this.msg_list;
  }

  public int getStatus()
  {
    return this.status;
  }

  public List<Store> getStore_list()
  {
    return this.store_list;
  }

  public String getTime()
  {
    return this.time;
  }

  public void setAdver_list(List<Advertise> paramList)
  {
    this.adver_list = paramList;
  }

  public void setFlag(int paramInt)
  {
    this.flag = paramInt;
  }

  public void setGoods_detail(GoodsDetail paramGoodsDetail)
  {
    this.goods_detail = paramGoodsDetail;
  }

  public void setMsg(String paramString)
  {
    this.msg = paramString;
  }

  public void setMsg_list(List<Message> paramList)
  {
    this.msg_list = paramList;
  }

  public void setStatus(int paramInt)
  {
    this.status = paramInt;
  }

  public void setStore_list(List<Store> paramList)
  {
    this.store_list = paramList;
  }

  public void setTime(String paramString)
  {
    this.time = paramString;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.bean.Infos
 * JD-Core Version:    0.6.2
 */