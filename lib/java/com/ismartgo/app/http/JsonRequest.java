package com.ismartgo.app.http;

import com.yolanda.nohttp.HeaderParser;
import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.RestRequestor;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonRequest extends RestRequestor<JSONObject>
{
  public JsonRequest(String paramString)
  {
    super(paramString);
  }

  public JsonRequest(String paramString, int paramInt)
  {
    super(paramString, paramInt);
  }

  public JSONObject parseResponse(String paramString1, String paramString2, byte[] paramArrayOfByte)
  {
    Object localObject = null;
    paramString1 = localObject;
    if (paramArrayOfByte != null)
    {
      paramString1 = localObject;
      if (paramArrayOfByte.length <= 0);
    }
    try
    {
      paramString1 = new String(paramArrayOfByte, HeaderParser.parseHeadValue(paramString2, "charset", ""));
    }
    catch (UnsupportedEncodingException paramString1)
    {
      try
      {
        while (true)
        {
          paramString1 = new JSONObject(paramString1);
          return paramString1;
          paramString1 = paramString1;
          Logger.w("Charset error in ContentType returned by the server：" + paramString2);
          paramString1 = new String(paramArrayOfByte);
        }
      }
      catch (JSONException paramString1)
      {
        paramString1.printStackTrace();
      }
    }
    return null;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.http.JsonRequest
 * JD-Core Version:    0.6.2
 */