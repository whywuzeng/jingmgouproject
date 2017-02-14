package com.alimama.mobile.csdk.umupdate.b;

import com.alimama.mobile.csdk.umupdate.a.g;
import org.json.JSONException;
import org.json.JSONObject;
import u.upd.i;

public class f extends i
{
  public int a;
  public JSONObject b;

  public f(JSONObject paramJSONObject)
  {
    super(paramJSONObject);
    this.b = paramJSONObject;
    a(paramJSONObject);
  }

  private void a(JSONObject paramJSONObject)
  {
    if (paramJSONObject == null)
    {
      g.d("failed requesting", new Object[0]);
      return;
    }
    try
    {
      this.a = paramJSONObject.getInt("status");
      return;
    }
    catch (JSONException paramJSONObject)
    {
      g.d("Parse json error", new Object[] { paramJSONObject });
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.alimama.mobile.csdk.umupdate.b.f
 * JD-Core Version:    0.6.2
 */