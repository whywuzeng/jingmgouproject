package com.umeng.message.proguard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class H
{
  private Map<String, G> a = new HashMap();
  private List<G> b = new Vector();

  private List<G> b(Map<String, String> paramMap)
  {
    if (this.a.containsKey("drop"))
    {
      localObject1 = (G)this.a.get("drop");
      localObject2 = ((G)localObject1).a(paramMap);
      if ((((J)localObject2).c()) && (((J)localObject2).b()))
      {
        paramMap = new ArrayList();
        paramMap.add(localObject1);
        return paramMap;
      }
    }
    if (this.a.containsKey("delay"))
    {
      localObject1 = (G)this.a.get("delay");
      localObject2 = ((G)localObject1).a(paramMap);
      if ((((J)localObject2).c()) && (((J)localObject2).b()))
      {
        paramMap = new ArrayList();
        paramMap.add(localObject1);
        return paramMap;
      }
    }
    Object localObject1 = new ArrayList();
    Object localObject2 = this.b.iterator();
    int j = 1;
    int i = 0;
    J localJ;
    if (((Iterator)localObject2).hasNext())
    {
      G localG = (G)((Iterator)localObject2).next();
      localJ = localG.a(paramMap);
      if ((localJ.c()) && (localJ.b()))
      {
        ((List)localObject1).add(localG);
        i = j;
        j = 1;
      }
    }
    while (true)
    {
      int k = j;
      j = i;
      i = k;
      break;
      if ((localJ.c()) && (!localJ.b()))
      {
        k = 0;
        j = i;
        i = k;
        continue;
        k = j;
        int m = i;
        if (this.a.containsKey("ki_stm_d"))
        {
          localObject2 = ((G)this.a.get("ki_stm_d")).a(paramMap);
          if ((!((J)localObject2).c()) || (!((J)localObject2).b()))
            break label379;
          ((List)localObject1).add(new G("stm_d", null));
          m = 1;
          k = j;
        }
        while ((m == 0) && (k == 0))
        {
          ((List)localObject1).add(new G("drop", null));
          return localObject1;
          label379: k = j;
          m = i;
          if (((J)localObject2).c())
          {
            k = j;
            m = i;
            if (!((J)localObject2).b())
            {
              k = 0;
              m = i;
            }
          }
        }
        if (((List)localObject1).size() > 0)
          return localObject1;
        if (this.a.containsKey("stm_d"))
        {
          localObject2 = (G)this.a.get("stm_d");
          paramMap = ((G)localObject2).a(paramMap);
          if ((paramMap.c()) && (paramMap.b()))
          {
            ((List)localObject1).add(localObject2);
            return localObject1;
          }
          ((List)localObject1).add(new G("drop", null));
          return localObject1;
        }
        ((List)localObject1).add(new G("stm_d", null));
        return localObject1;
      }
      else
      {
        k = i;
        i = j;
        j = k;
      }
    }
  }

  private void b(JSONObject paramJSONObject)
  {
    if (paramJSONObject != null)
    {
      String str = null;
      try
      {
        if (paramJSONObject.has("stm"))
          str = paramJSONObject.getString("stm");
        if ((!at.a(str)) && (paramJSONObject.has("rs")))
        {
          paramJSONObject = new G(str, paramJSONObject.getJSONArray("rs"));
          if ((str.equals("stm_d")) || (str.equals("ki_stm_d")) || (str.equals("drop")) || (str.equals("delay")))
          {
            this.a.put(str, paramJSONObject);
            return;
          }
          this.b.add(paramJSONObject);
          return;
        }
      }
      catch (JSONException paramJSONObject)
      {
        paramJSONObject.printStackTrace();
      }
    }
  }

  public List<String> a(Map<String, String> paramMap)
  {
    Object localObject = b(paramMap);
    if (localObject == null)
      return null;
    paramMap = new LinkedList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      G localG = (G)((Iterator)localObject).next();
      if (!paramMap.contains(localG.a()))
        paramMap.add(localG.a());
    }
    return paramMap;
  }

  public void a(JSONObject paramJSONObject)
  {
    if ((paramJSONObject != null) && (paramJSONObject.has("stms")))
      try
      {
        paramJSONObject = paramJSONObject.getJSONArray("stms");
        int i = 0;
        while (i < paramJSONObject.length())
        {
          JSONObject localJSONObject = paramJSONObject.getJSONObject(i);
          if (localJSONObject != null)
            b(localJSONObject);
          i += 1;
        }
      }
      catch (JSONException paramJSONObject)
      {
        paramJSONObject.printStackTrace();
      }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.H
 * JD-Core Version:    0.6.2
 */