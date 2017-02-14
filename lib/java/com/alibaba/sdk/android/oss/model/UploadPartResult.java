package com.alibaba.sdk.android.oss.model;

public class UploadPartResult
{
  private String eTag;
  private int partNumber;

  public int getPartNumber()
  {
    return this.partNumber;
  }

  public String geteTag()
  {
    return this.eTag;
  }

  public void setPartNumber(int paramInt)
  {
    this.partNumber = paramInt;
  }

  public void seteTag(String paramString)
  {
    this.eTag = paramString;
  }

  public String toString()
  {
    return "UploadPartResult{partNumber=" + this.partNumber + ", eTag='" + this.eTag + '\'' + '}';
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.sdk.android.oss.model.UploadPartResult
 * JD-Core Version:    0.6.2
 */