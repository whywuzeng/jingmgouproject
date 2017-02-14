package com.alibaba.sdk.android.oss.model;

public class UploadPartInfo
{
  private String eTag;
  private String lastModified;
  private int partNumber;
  private long partSize;

  public String getLastModified()
  {
    return this.lastModified;
  }

  public int getPartNumber()
  {
    return this.partNumber;
  }

  public long getPartSize()
  {
    return this.partSize;
  }

  public String geteTag()
  {
    return this.eTag;
  }

  public void setLastModified(String paramString)
  {
    this.lastModified = paramString;
  }

  public void setPartNumber(int paramInt)
  {
    this.partNumber = paramInt;
  }

  public void setPartSize(long paramLong)
  {
    this.partSize = paramLong;
  }

  public void seteTag(String paramString)
  {
    this.eTag = paramString;
  }

  public String toString()
  {
    return "UploadPartInfo{partNumber=" + this.partNumber + ", eTag='" + this.eTag + '\'' + ", lastModified='" + this.lastModified + '\'' + ", partSize=" + this.partSize + '}';
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.sdk.android.oss.model.UploadPartInfo
 * JD-Core Version:    0.6.2
 */