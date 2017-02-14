package com.baidu.lbsapi.auth;

import org.json.JSONException;
import org.json.JSONObject;

class ErrorMessage
{
  static String a(String paramString)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("status", -1);
      localJSONObject.put("message", paramString);
      label24: return localJSONObject.toString();
    }
    catch (JSONException paramString)
    {
      break label24;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.lbsapi.auth.ErrorMessage
 * JD-Core Version:    0.6.2
 */