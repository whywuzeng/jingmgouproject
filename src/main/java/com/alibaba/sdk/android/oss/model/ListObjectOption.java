package com.alibaba.sdk.android.oss.model;

import com.alibaba.sdk.android.dpa.util.ToolKit;
import java.util.ArrayList;
import java.util.List;

public class ListObjectOption
{
  private String delimiter = "";
  private String marker = "";
  private int maxKeys = 0;
  private String prefix = "";

  public String genQueryString()
  {
    Object localObject = new ArrayList();
    if (!ToolKit.isEmptyOrNullString(this.delimiter))
      ((List)localObject).add("delimiter=" + this.delimiter.trim());
    if (!ToolKit.isEmptyOrNullString(this.marker))
      ((List)localObject).add("marker=" + this.marker.trim());
    if (!ToolKit.isEmptyOrNullString(this.prefix))
      ((List)localObject).add("prefix=" + this.prefix.trim());
    if (this.maxKeys != 0)
      ((List)localObject).add("max-keys=" + this.maxKeys);
    localObject = ToolKit.trimAndJoin((List)localObject, "&");
    if (localObject == "")
      return "";
    return "?" + (String)localObject;
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

  public String getPrefix()
  {
    return this.prefix;
  }

  public void setDelimiter(String paramString)
  {
    this.delimiter = paramString;
  }

  public void setMarker(String paramString)
  {
    this.marker = paramString;
  }

  public void setMaxKeys(int paramInt)
  {
    this.maxKeys = paramInt;
  }

  public void setPrefix(String paramString)
  {
    this.prefix = paramString;
  }

  public String toString()
  {
    return genQueryString();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.sdk.android.oss.model.ListObjectOption
 * JD-Core Version:    0.6.2
 */