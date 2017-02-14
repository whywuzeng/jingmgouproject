package com.alibaba.sdk.android.oss.model;

import android.util.Pair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class XOSSMeta
{
  private Map<String, String> headers = new HashMap();

  public void addXOSSMetaDirectly(String paramString1, String paramString2)
  {
    this.headers.put(paramString1, paramString2);
  }

  public Map<String, String> getXOSSMetaHeader()
  {
    return this.headers;
  }

  public List<Pair<String, String>> getXOSSMetaHeaderList()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.headers.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str1 = (String)localIterator.next();
      String str2 = (String)this.headers.get(str1);
      localArrayList.add(new Pair(str1.toLowerCase(), str2));
    }
    return localArrayList;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.sdk.android.oss.model.XOSSMeta
 * JD-Core Version:    0.6.2
 */