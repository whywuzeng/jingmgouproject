package com.alibaba.sdk.android.oss.model;

import org.w3c.dom.Document;

public class OSSResponseInfo
{
  private String code;
  private String hostId;
  private String message;
  private String requestId;
  private Document responseInfoDom;
  private int statusCode;

  public String getCode()
  {
    return this.code;
  }

  public String getHostId()
  {
    return this.hostId;
  }

  public String getMessage()
  {
    return this.message;
  }

  public String getRequestId()
  {
    return this.requestId;
  }

  public Document getResponseInfoDom()
  {
    return this.responseInfoDom;
  }

  public int getStatusCode()
  {
    return this.statusCode;
  }

  public void setCode(String paramString)
  {
    this.code = paramString;
  }

  public void setHostId(String paramString)
  {
    this.hostId = paramString;
  }

  public void setMessage(String paramString)
  {
    this.message = paramString;
  }

  public void setRequestId(String paramString)
  {
    this.requestId = paramString;
  }

  public void setResponseInfoDom(Document paramDocument)
  {
    this.responseInfoDom = paramDocument;
  }

  public void setStatusCode(int paramInt)
  {
    this.statusCode = paramInt;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.sdk.android.oss.model.OSSResponseInfo
 * JD-Core Version:    0.6.2
 */