package com.ismartgo.app.oos;

import android.content.Context;
import android.util.Log;
import com.ismartgo.app.http.HttpCallback;
import com.ismartgo.app.http.HttpRequest;
import com.ismartgo.app.interfaces.OnCompleteListener;
import com.ismartgo.app.url.Url;
import com.yolanda.nohttp.Response;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class FederationTokenGetter
{
  public static void getTokenFromServer(Context paramContext, String paramString1, String paramString2, OnCompleteListener paramOnCompleteListener)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("uid", paramString1);
    localHashMap.put("devcode", paramString2);
    localHashMap.put("operationTime", System.currentTimeMillis());
    Log.d("token", "开始获取token");
    HttpRequest.getInstance().executePostStringRequest(paramContext, Url.GET_OOS_TOKEN, 40, localHashMap, new HttpCallback()
    {
      public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
      {
        FederationTokenGetter.this.onFailure(null, paramAnonymousInt, paramAnonymousString);
      }

      public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
      {
        if (paramAnonymousResponse != null);
        try
        {
          paramAnonymousResponse = new JSONObject(((String)paramAnonymousResponse.get()).toString()).getJSONObject("data").getJSONObject("authorizeInfo");
          paramAnonymousResponse = new FederationToken(paramAnonymousResponse.getString("accessKeyId"), paramAnonymousResponse.getString("accessKeySecret"), paramAnonymousResponse.getString("securityToken"), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(paramAnonymousResponse.getString("expireTime")).getTime());
          FederationTokenGetter.this.onResult(paramAnonymousResponse, paramAnonymousInt);
          return;
        }
        catch (Exception paramAnonymousResponse)
        {
          while (true)
          {
            paramAnonymousResponse.printStackTrace();
            paramAnonymousResponse = null;
          }
        }
      }
    });
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.oos.FederationTokenGetter
 * JD-Core Version:    0.6.2
 */