package com.alibaba.sdk.android.oss.model;

import java.io.Serializable;

public class DownloadRecorder
  implements Serializable
{
  private static final long serialVersionUID = 201503161032L;
  private int breakPoint;
  private String lastModifiedTime = null;

  public DownloadRecorder(int paramInt, String paramString)
  {
    this.lastModifiedTime = paramString;
    this.breakPoint = paramInt;
  }

  public void clear()
  {
    this.breakPoint = 0;
    this.lastModifiedTime = null;
  }

  public int getBreakPoint()
  {
    return this.breakPoint;
  }

  public String getLastModifiedTime()
  {
    return this.lastModifiedTime;
  }

  public void setBreakPoint(int paramInt)
  {
    this.breakPoint = paramInt;
  }

  public void setLastModifiedTime(String paramString)
  {
    this.lastModifiedTime = paramString;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.sdk.android.oss.model.DownloadRecorder
 * JD-Core Version:    0.6.2
 */