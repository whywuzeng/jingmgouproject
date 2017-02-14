package com.ismartgo.app.net;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.ismartgo.app.activity.HomeActivity;
import com.ismartgo.app.bean.User;
import com.ismartgo.app.http.HttpCallback;
import com.ismartgo.app.http.HttpRequest;
import com.ismartgo.app.tools.SharedPreferenceUtil;
import com.ismartgo.app.tools.TelephoneUtils;
import com.ismartgo.app.url.ResultCode;
import com.ismartgo.app.url.Url;
import com.yolanda.nohttp.Response;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class TouristUserRequest
{
  Context context;
  TouristListener touristListener;

  public void TouristRegistRequest(final Context paramContext)
  {
    this.context = paramContext;
    HashMap localHashMap = new HashMap();
    localHashMap.put("devcode", TelephoneUtils.getIMEI(paramContext));
    String str2 = SharedPreferenceUtil.getLocationInfo(paramContext).getString("city", "");
    String str1 = str2;
    if ("".equals(str2))
      str1 = "广州";
    localHashMap.put("cityName", str1);
    HttpRequest.getInstance().executePostStringRequest(paramContext, Url.TOURIST_REGIST_URL, 3, localHashMap, new HttpCallback()
    {
      public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
      {
        TouristUserRequest.this.touristListener.rigistFail();
      }

      public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
      {
        User localUser = new User();
        while (true)
        {
          JSONObject localJSONObject2;
          try
          {
            paramAnonymousResponse = new JSONObject(((String)paramAnonymousResponse.get()).toString());
            JSONObject localJSONObject1 = paramAnonymousResponse.getJSONObject("data");
            if (localJSONObject1.isNull("user"))
              break;
            localJSONObject2 = localJSONObject1.getJSONObject("user");
            if (paramAnonymousResponse.getInt("status") != ResultCode.RESULT_OK)
              break;
            localUser.setId(localJSONObject2.getString("id"));
            localUser.setUsername(localJSONObject2.getString("username"));
            localUser.setNickname(localJSONObject2.getString("nickname"));
            try
            {
              localUser.setBeanNumber(localJSONObject1.getInt("userBeanNum"));
              localUser.setPassword(localJSONObject2.getString("password"));
              localUser.setByInviteCode(localJSONObject2.getString("byInviteCode"));
              localUser.setDevInvited(localJSONObject2.getInt("devInvited"));
              localUser.setEmail(localJSONObject2.getString("email"));
              if (localJSONObject2.getString("sex").equals(""))
              {
                paramAnonymousResponse = "0";
                localUser.setSex(paramAnonymousResponse);
                localUser.setAge(localJSONObject2.getString("age"));
                localUser.setLongitude(null);
                localUser.setLatitude(null);
                localUser.setBirthday(localJSONObject2.getString("username"));
                localUser.setThirdId(localJSONObject2.getString("thirdid"));
                localUser.setLoginType(6);
                SharedPreferenceUtil.setDevInvited(paramContext, localJSONObject2.optInt("devInvited"));
                HomeActivity.beanGet = localJSONObject1.getString("beanGet");
                if ((!"null".equals(localJSONObject1.getString("beanGet"))) && (!localJSONObject1.getString("beanGet").equals(null)) && (!"".equals(localJSONObject1.getString("beanGet"))))
                  break label375;
                com.ismartgo.app.application.AndroidApplication.isFirst = false;
                Log.i("AndroidApplication", "游客豆数： " + localUser.getBeanNumber() + "  第一次注册豆数： " + HomeActivity.beanGet);
                TouristUserRequest.this.touristListener.TouristInfo(localUser);
                return;
              }
            }
            catch (Exception paramAnonymousResponse)
            {
              localUser.setBeanNumber(0);
              continue;
            }
          }
          catch (JSONException paramAnonymousResponse)
          {
            paramAnonymousResponse.printStackTrace();
            return;
          }
          paramAnonymousResponse = localJSONObject2.getString("sex");
          continue;
          label375: com.ismartgo.app.application.AndroidApplication.isFirst = true;
        }
      }
    });
  }

  public void setTouristRegistListener(TouristListener paramTouristListener)
  {
    this.touristListener = paramTouristListener;
  }

  public static abstract interface TouristListener
  {
    public abstract void TouristInfo(User paramUser);

    public abstract void rigistFail();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.net.TouristUserRequest
 * JD-Core Version:    0.6.2
 */