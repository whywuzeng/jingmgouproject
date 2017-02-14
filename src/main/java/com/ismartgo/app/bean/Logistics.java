package com.ismartgo.app.bean;

import java.io.Serializable;
import java.util.List;

public class Logistics
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private DataEntity data;
  private String msg;
  private int status;
  private String time;

  public DataEntity getData()
  {
    return this.data;
  }

  public String getMsg()
  {
    return this.msg;
  }

  public int getStatus()
  {
    return this.status;
  }

  public String getTime()
  {
    return this.time;
  }

  public void setData(DataEntity paramDataEntity)
  {
    this.data = paramDataEntity;
  }

  public void setMsg(String paramString)
  {
    this.msg = paramString;
  }

  public void setStatus(int paramInt)
  {
    this.status = paramInt;
  }

  public void setTime(String paramString)
  {
    this.time = paramString;
  }

  public static class DataEntity
    implements Serializable
  {
    private String logisticsNum;
    private List<OrderTrackEntity> orderTrack;

    public String getLogisticsNum()
    {
      return this.logisticsNum;
    }

    public List<OrderTrackEntity> getOrderTrack()
    {
      return this.orderTrack;
    }

    public void setLogisticsNum(String paramString)
    {
      this.logisticsNum = paramString;
    }

    public void setOrderTrack(List<OrderTrackEntity> paramList)
    {
      this.orderTrack = paramList;
    }

    public static class OrderTrackEntity
      implements Serializable
    {
      private String content;
      private String msgTime;

      public String getContent()
      {
        return this.content;
      }

      public String getMsgTime()
      {
        return this.msgTime;
      }

      public void setContent(String paramString)
      {
        this.content = paramString;
      }

      public void setMsgTime(String paramString)
      {
        this.msgTime = paramString;
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.bean.Logistics
 * JD-Core Version:    0.6.2
 */