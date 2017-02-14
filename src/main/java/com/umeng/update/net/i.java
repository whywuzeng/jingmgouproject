package com.umeng.update.net;

import org.json.JSONObject;

public class i extends u.upd.i
{
  public a a;

  public i(JSONObject paramJSONObject)
  {
    super(paramJSONObject);
    if (("ok".equalsIgnoreCase(paramJSONObject.optString("status"))) || ("ok".equalsIgnoreCase(paramJSONObject.optString("success"))))
    {
      this.a = a.a;
      return;
    }
    this.a = a.b;
  }

  public static enum a
  {
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.update.net.i
 * JD-Core Version:    0.6.2
 */