package com.alibaba.sdk.android.oss.model;

public class ResumableTaskOption
{
  private int autoRetryTime;
  private String recordFileDirectory;
  private int threadNum;

  public int getAutoRetryTime()
  {
    return this.autoRetryTime;
  }

  public String getRecordFileDirectory()
  {
    return this.recordFileDirectory;
  }

  public int getThreadNum()
  {
    return this.threadNum;
  }

  public void setAutoRetryTime(int paramInt)
  {
    this.autoRetryTime = paramInt;
  }

  public void setRecordFileDirectory(String paramString)
  {
    this.recordFileDirectory = paramString;
  }

  public void setThreadNum(int paramInt)
  {
    this.threadNum = paramInt;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.sdk.android.oss.model.ResumableTaskOption
 * JD-Core Version:    0.6.2
 */