package com.alibaba.sdk.android.oss.model;

import java.util.ArrayList;
import java.util.List;

public class ListObjectResult
{
  private String bucketName;
  private List<String> commonPrefixList = new ArrayList();
  private String delimiter;
  private boolean isTruncated;
  private List<ObjectInfo> list = new ArrayList();
  private String marker;
  private int maxKeys;
  private String nextMarker;
  private String prefix;

  public String getBucketName()
  {
    return this.bucketName;
  }

  public List<String> getCommonPrefixList()
  {
    return this.commonPrefixList;
  }

  public String getDelimiter()
  {
    return this.delimiter;
  }

  public String getMarker()
  {
    return this.marker;
  }

  public int getMaxKeys()
  {
    return this.maxKeys;
  }

  public String getNextMarker()
  {
    return this.nextMarker;
  }

  public List<ObjectInfo> getObjectInfoList()
  {
    return this.list;
  }

  public String getPrefix()
  {
    return this.prefix;
  }

  public boolean isTruncated()
  {
    return this.isTruncated;
  }

  public void setBucketName(String paramString)
  {
    this.bucketName = paramString;
  }

  public void setDelimiter(String paramString)
  {
    this.delimiter = paramString;
  }

  public void setList(List<ObjectInfo> paramList)
  {
    this.list = paramList;
  }

  public void setMarker(String paramString)
  {
    this.marker = paramString;
  }

  public void setMaxKeys(int paramInt)
  {
    this.maxKeys = paramInt;
  }

  public void setNextMarker(String paramString)
  {
    this.nextMarker = paramString;
  }

  public void setPrefix(String paramString)
  {
    this.prefix = paramString;
  }

  public void setTruncated(boolean paramBoolean)
  {
    this.isTruncated = paramBoolean;
  }

  public static class ObjectInfo
  {
    private String Etag;
    private String lastModified;
    private String objectKey;
    private long size;
    private String type;

    public String getEtag()
    {
      return this.Etag;
    }

    public String getLastModified()
    {
      return this.lastModified;
    }

    public String getObjectKey()
    {
      return this.objectKey;
    }

    public long getSize()
    {
      return this.size;
    }

    public String getType()
    {
      return this.type;
    }

    public void setEtag(String paramString)
    {
      this.Etag = paramString;
    }

    public void setLastModified(String paramString)
    {
      this.lastModified = paramString;
    }

    public void setObjectKey(String paramString)
    {
      this.objectKey = paramString;
    }

    public void setSize(long paramLong)
    {
      this.size = paramLong;
    }

    public void setType(String paramString)
    {
      this.type = paramString;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.sdk.android.oss.model.ListObjectResult
 * JD-Core Version:    0.6.2
 */