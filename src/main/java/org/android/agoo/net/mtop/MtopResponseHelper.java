package org.android.agoo.net.mtop;

import android.text.TextUtils;
import com.umeng.message.proguard.bd;
import org.json.JSONArray;
import org.json.JSONObject;

public class MtopResponseHelper
{
  public static final String a = "SUCCESS";
  public static final String b = "FAIL";
  private static final String c = "MtopResponseHelper";

  public static Result parse(String paramString)
    throws Throwable
  {
    Result localResult = new Result();
    while (true)
    {
      int i;
      try
      {
        JSONObject localJSONObject = new JSONObject(paramString);
        JSONArray localJSONArray = localJSONObject.getJSONArray("ret");
        int j = localJSONArray.length();
        i = 0;
        if (i < j)
        {
          Object localObject = localJSONArray.getString(i);
          if (TextUtils.isEmpty((CharSequence)localObject))
            break label206;
          localObject = ((String)localObject).split("::");
          if ((localObject == null) || (2 != localObject.length))
            break label206;
          localResult.setRetCode(localObject[0]);
          localResult.setRetDesc(localObject[1]);
          if ("SUCCESS".equals(localObject[0]))
          {
            localResult.setData(localJSONObject.getString("data"));
            localResult.setSuccess(true);
          }
        }
        else
        {
          localResult.setHttpCode(200);
          bd.c("MtopResponseHelper", "MtopResponseHelper:[" + localResult.toString() + "]");
          return localResult;
        }
        localResult.setSuccess(false);
        localResult.setData(localJSONObject.getString("data"));
      }
      catch (Throwable localThrowable)
      {
        localResult.setSuccess(false);
        localResult.setData(paramString);
        localResult.setHttpCode(302);
        continue;
      }
      label206: i += 1;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.net.mtop.MtopResponseHelper
 * JD-Core Version:    0.6.2
 */