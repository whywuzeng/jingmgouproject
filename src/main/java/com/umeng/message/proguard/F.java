package com.umeng.message.proguard;

import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class F
{
  private I a = null;
  private String[] b = null;
  private String[] c = null;
  private String[] d = null;
  private String[] e = null;
  private String[] f = null;

  public F(JSONObject paramJSONObject)
  {
    while (true)
    {
      int i;
      try
      {
        JSONArray localJSONArray = paramJSONObject.getJSONArray("ers");
        i = 0;
        if (i < localJSONArray.length())
        {
          JSONObject localJSONObject = localJSONArray.getJSONObject(i);
          int j = localJSONObject.optInt("e");
          int k = localJSONObject.optInt("cp");
          if ((j != 0) && (k >= 0) && (k <= 100))
            this.a.a(j, k);
          if (!localJSONObject.has("cp"))
            this.a.a(j, 100);
        }
        else
        {
          this.b = a("p", paramJSONObject);
          this.c = a("a1", paramJSONObject);
          this.d = a("a2", paramJSONObject);
          this.e = a("a3", paramJSONObject);
          this.f = a("as", paramJSONObject);
          return;
        }
      }
      catch (JSONException paramJSONObject)
      {
        paramJSONObject.printStackTrace();
        return;
      }
      i += 1;
    }
  }

  private boolean a(String paramString, String[] paramArrayOfString)
  {
    if ((paramArrayOfString == null) || (paramArrayOfString.length == 0))
      return true;
    while (true)
    {
      int i;
      try
      {
        if ((!at.a(paramString)) && (paramArrayOfString != null) && (paramArrayOfString.length > 0))
        {
          int j = paramArrayOfString.length;
          i = 0;
          if (i < j)
          {
            String str = paramArrayOfString[i];
            if ((str.startsWith("%")) && (str.endsWith("%")))
            {
              if (paramString.contains(str.substring(1, str.length() - 2)))
                break;
              break label109;
            }
            boolean bool = paramString.equals(str);
            if (!bool)
              break label109;
            return true;
          }
        }
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
      }
      return false;
      label109: i += 1;
    }
  }

  private String[] a(String paramString, JSONObject paramJSONObject)
  {
    try
    {
      JSONArray localJSONArray = paramJSONObject.getJSONArray(paramString);
      if ((localJSONArray != null) && (localJSONArray.length() > 0))
      {
        paramJSONObject = new String[localJSONArray.length()];
        int i = 0;
        while (true)
        {
          paramString = paramJSONObject;
          if (i >= localJSONArray.length())
            break;
          paramJSONObject[i] = localJSONArray.getString(i);
          i += 1;
        }
      }
    }
    catch (JSONException paramString)
    {
      paramString = null;
    }
    return paramString;
  }

  public J a(Map<String, String> paramMap)
  {
    if (paramMap != null)
      try
      {
        if (this.a != null)
        {
          int i = Integer.valueOf((String)paramMap.get(v.D.toString())).intValue();
          if (this.a != null)
          {
            I.b localb = this.a.a(i);
            if (localb.d())
            {
              String str1 = (String)paramMap.get(v.C.toString());
              String str2 = (String)paramMap.get(v.E.toString());
              String str3 = (String)paramMap.get(v.F.toString());
              String str4 = (String)paramMap.get(v.G.toString());
              paramMap = (String)paramMap.get(v.H.toString());
              if ((a(str1, this.b)) && (a(str2, this.c)) && (a(str3, this.d)) && (a(str4, this.e)) && (a(paramMap, this.f)))
              {
                paramMap = new J();
                if (localb.b())
                  paramMap.a();
                paramMap.a(localb.c());
                paramMap.a(true);
                return paramMap;
              }
            }
          }
        }
      }
      catch (Exception paramMap)
      {
        paramMap.printStackTrace();
      }
    paramMap = new J();
    paramMap.a(false);
    return paramMap;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.F
 * JD-Core Version:    0.6.2
 */