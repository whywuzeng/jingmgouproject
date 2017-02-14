package com.alibaba.sdk.android.oss.model;

import com.alibaba.sdk.android.dpa.util.ToolKit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ResourceToQuery
{
  private String baseResource;
  private List<String> querys = new ArrayList();

  public String getBaseResource()
  {
    return this.baseResource;
  }

  public String getOnlyUriQuery()
  {
    String str1 = "";
    Collections.sort(this.querys);
    Iterator localIterator = this.querys.iterator();
    while (localIterator.hasNext())
    {
      String str2 = (String)localIterator.next();
      if (str1.equals(""))
        str1 = str1.concat(str2);
      else
        str1 = str1.concat("&" + str2);
    }
    return str1;
  }

  public List<String> getQuerys()
  {
    return this.querys;
  }

  public void setBaseResource(String paramString)
  {
    this.baseResource = paramString;
  }

  public void setQuery(String paramString)
  {
    this.querys.add(paramString);
  }

  public String toCanoResource()
  {
    ToolKit.checkNotNullArg(this.baseResource, "Must set base resource string before constructing canonicalizedResource");
    String str = getOnlyUriQuery();
    if (ToolKit.isEmptyOrNullString(str))
      return this.baseResource;
    return this.baseResource + "?" + str;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.sdk.android.oss.model.ResourceToQuery
 * JD-Core Version:    0.6.2
 */