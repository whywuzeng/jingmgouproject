package com.umeng.message.proguard;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class G
{
  private String a = null;
  private List<F> b = new Vector();

  public G(String paramString, JSONArray paramJSONArray)
  {
    this.a = paramString;
    if (paramJSONArray != null)
    {
      int i = 0;
      while (true)
        if (i < paramJSONArray.length())
          try
          {
            paramString = paramJSONArray.getJSONObject(i);
            if (paramString.has("ers"))
              this.b.add(new F(paramString));
            i += 1;
          }
          catch (JSONException paramString)
          {
            while (true)
              paramString.printStackTrace();
          }
    }
  }

  public J a(Map<String, String> paramMap)
  {
    Iterator localIterator = this.b.iterator();
    while (localIterator.hasNext())
    {
      J localJ = ((F)localIterator.next()).a(paramMap);
      if (localJ.c())
        return localJ;
    }
    paramMap = new J();
    paramMap.a(false);
    return paramMap;
  }

  public String a()
  {
    return this.a;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.G
 * JD-Core Version:    0.6.2
 */