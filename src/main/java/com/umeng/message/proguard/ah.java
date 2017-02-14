package com.umeng.message.proguard;

import org.json.JSONException;
import org.json.JSONObject;

public class ah
{
  public static boolean a(String paramString)
  {
    try
    {
      paramString = new JSONObject(paramString);
      if (paramString.has("success"))
      {
        paramString = paramString.getString("success");
        if (!at.a(paramString))
        {
          boolean bool = paramString.equals("success");
          if (bool)
            return true;
        }
      }
    }
    catch (JSONException paramString)
    {
      paramString.printStackTrace();
    }
    return false;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.ah
 * JD-Core Version:    0.6.2
 */