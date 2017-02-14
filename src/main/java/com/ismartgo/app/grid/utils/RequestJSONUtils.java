package com.ismartgo.app.grid.utils;

import android.content.Context;
import android.util.Log;
import com.ismartgo.app.http.HttpCallback;
import com.ismartgo.app.http.HttpRequest;
import com.ismartgo.app.url.Url;
import java.util.HashMap;
import java.util.Map;

public class RequestJSONUtils
{
  private static Map<String, String> params;

  private static void getInstance()
  {
    params = new HashMap();
  }

  public static void getUserBeanDetail(Context paramContext, String paramString1, String paramString2, HttpCallback<String> paramHttpCallback)
  {
    getInstance();
    params.put("uid", paramString1);
    params.put("month", paramString2);
    Log.i("json", params.toString());
    HttpRequest.getInstance().executePostStringRequest(paramContext, Url.GET_USER_BEAN_DETAIL, 49, params, paramHttpCallback);
  }

  public static void getUserBeanNew(Context paramContext, String paramString1, String paramString2, HttpCallback<String> paramHttpCallback)
  {
    getInstance();
    params.put("uid", paramString1);
    params.put("pages", paramString2);
    params.put("pageSize", "30");
    Log.i("json", params.toString());
    HttpRequest.getInstance().executePostStringRequest(paramContext, Url.GET_USER_BEAN_DETAIL, 49, params, paramHttpCallback);
  }

  public static void getUserInfoRequest(Context paramContext, String paramString1, String paramString2, HttpCallback<String> paramHttpCallback)
  {
    getInstance();
    params.put("uid", paramString1);
    params.put("devCode", paramString2);
    HttpRequest.getInstance().executePostStringRequest(paramContext, Url.GET_USER_INFO, 51, params, paramHttpCallback);
  }

  public static void getUserUsuallyAddress(Context paramContext, String paramString, HttpCallback<String> paramHttpCallback)
  {
    getInstance();
    params.put("uid", paramString);
    HttpRequest.getInstance().executePostStringRequest(paramContext, Url.GET_USER_CONTACT, 50, params, paramHttpCallback);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.grid.utils.RequestJSONUtils
 * JD-Core Version:    0.6.2
 */