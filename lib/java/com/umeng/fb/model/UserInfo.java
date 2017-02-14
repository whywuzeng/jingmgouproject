package com.umeng.fb.model;

import android.text.TextUtils;
import com.umeng.fb.util.Log;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class UserInfo
{
  private static final String e = UserInfo.class.getName();
  int a = -1;
  String b = "";
  Map<String, String> c;
  Map<String, String> d;

  public UserInfo()
  {
    this.c = new HashMap();
    this.d = new HashMap();
  }

  UserInfo(JSONObject paramJSONObject)
    throws JSONException
  {
    this.a = paramJSONObject.optInt("age_group", -1);
    this.b = paramJSONObject.optString("gender", "");
    this.c = new HashMap();
    this.d = new HashMap();
    Object localObject1 = paramJSONObject.optJSONObject("contact");
    Object localObject2;
    if (localObject1 != null)
    {
      localObject2 = ((JSONObject)localObject1).keys();
      while (((Iterator)localObject2).hasNext())
      {
        String str = (String)((Iterator)localObject2).next();
        this.c.put(str, ((JSONObject)localObject1).getString(str));
      }
    }
    paramJSONObject = paramJSONObject.optJSONObject("remark");
    Log.c(e, "" + paramJSONObject);
    if (paramJSONObject != null)
    {
      localObject1 = paramJSONObject.keys();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (String)((Iterator)localObject1).next();
        this.d.put(localObject2, paramJSONObject.getString((String)localObject2));
      }
    }
  }

  public int getAgeGroup()
  {
    return this.a;
  }

  public Map<String, String> getContact()
  {
    return this.c;
  }

  public String getGender()
  {
    return this.b;
  }

  public Map<String, String> getRemark()
  {
    return this.d;
  }

  public JSONObject getRemarkJson()
  {
    JSONObject localJSONObject = null;
    Object localObject3 = null;
    Object localObject1 = localJSONObject;
    if (this.d != null)
    {
      localObject1 = localJSONObject;
      if (this.d.size() <= 0);
    }
    try
    {
      localJSONObject = new JSONObject();
      try
      {
        localObject1 = this.d.entrySet().iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject3 = (Map.Entry)((Iterator)localObject1).next();
          localJSONObject.put((String)((Map.Entry)localObject3).getKey(), ((Map.Entry)localObject3).getValue());
        }
      }
      catch (JSONException localJSONException1)
      {
        localObject3 = localJSONException1;
        localObject2 = localJSONObject;
      }
      ((JSONException)localObject3).printStackTrace();
      return localObject2;
      return localJSONObject;
    }
    catch (JSONException localJSONException2)
    {
      while (true)
      {
        Object localObject2 = localObject3;
        localObject3 = localJSONException2;
      }
    }
  }

  public void setAgeGroup(int paramInt)
  {
    this.a = paramInt;
  }

  public void setContact(Map<String, String> paramMap)
  {
    this.c = paramMap;
  }

  public void setGender(String paramString)
  {
    this.b = paramString;
  }

  public void setRemark(Map<String, String> paramMap)
  {
    this.d = paramMap;
  }

  public JSONObject toJson()
  {
    JSONObject localJSONObject1 = new JSONObject();
    Map.Entry localEntry;
    do
    {
      try
      {
        if (this.a > -1)
          localJSONObject1.put("age_group", this.a);
        if (!TextUtils.isEmpty(this.b))
          localJSONObject1.put("gender", this.b);
        if ((this.c == null) || (this.c.size() <= 0))
          continue;
        JSONObject localJSONObject2 = new JSONObject();
        localIterator = this.c.entrySet().iterator();
        while (localIterator.hasNext())
        {
          localEntry = (Map.Entry)localIterator.next();
          localJSONObject2.put((String)localEntry.getKey(), localEntry.getValue());
        }
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
        return localJSONObject1;
      }
      localJSONObject1.put("contact", localJSONException);
    }
    while ((this.d == null) || (this.d.size() <= 0));
    JSONObject localJSONObject3 = new JSONObject();
    Iterator localIterator = this.d.entrySet().iterator();
    while (localIterator.hasNext())
    {
      localEntry = (Map.Entry)localIterator.next();
      localJSONObject3.put((String)localEntry.getKey(), localEntry.getValue());
    }
    localJSONObject1.put("remark", localJSONObject3);
    return localJSONObject1;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.fb.model.UserInfo
 * JD-Core Version:    0.6.2
 */