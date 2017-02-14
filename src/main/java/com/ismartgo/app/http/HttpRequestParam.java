package com.ismartgo.app.http;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.Log;
import com.ismartgo.app.activity.BaseActivity;
import com.ismartgo.app.application.AndroidApplication;
import com.ismartgo.app.bean.User;
import com.ismartgo.app.grid.utils.Helper;
import com.ismartgo.app.grid.utils.ToolNetwork;
import com.ismartgo.app.tools.TelephoneUtils;
import com.ismartgo.app.utils.DeviceInfoUtil;
import java.util.HashMap;
import java.util.Map;

public class HttpRequestParam
{
  public static Map<String, String> addDevInfoParams(Map<String, String> paramMap, Context paramContext)
  {
    paramMap.put("devType", "Android");
    paramMap.put("devModel", Build.MANUFACTURER + " " + Build.MODEL);
    paramMap.put("internetType", ToolNetwork.getNetworkInfo(paramContext));
    paramMap.put("vs", Build.VERSION.RELEASE);
    if (DeviceInfoUtil.getPhoneRAM(paramContext) == null);
    for (String str = "0G"; ; str = DeviceInfoUtil.getPhoneRAM(paramContext))
    {
      paramMap.put("devRom", str);
      paramMap.put("appVersion", DeviceInfoUtil.getAppVersion(paramContext));
      return paramMap;
    }
  }

  public static Map<String, String> getAreaParams(int paramInt)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("cityId", String.valueOf(paramInt));
    return localHashMap;
  }

  public static Map<String, String> getCityParams(int paramInt)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("provinceId", String.valueOf(paramInt));
    return localHashMap;
  }

  public static Map<String, String> getSessionParam(String paramString)
  {
    HashMap localHashMap = new HashMap();
    if (BaseActivity.loginUser == null)
    {
      str = "0";
      localHashMap.put("uid", str);
      localHashMap.put("devcode", TelephoneUtils.getIMEI(AndroidApplication.getInstance()));
      Log.d("BaseActivity.log", BaseActivity.log);
      Log.d("BaseActivity.log", BaseActivity.lat);
      if (BaseActivity.log != null)
        break label157;
      str = "0";
      label69: localHashMap.put("lon", str);
      if (BaseActivity.lat != null)
        break label164;
    }
    label157: label164: for (String str = "0"; ; str = BaseActivity.lat)
    {
      localHashMap.put("lat", str);
      localHashMap.put("client", "android");
      localHashMap.put("appversion", Helper.getVersion(AndroidApplication.getInstance()));
      localHashMap.put("reqtime", paramString);
      localHashMap.put("userkey", "0");
      return localHashMap;
      str = BaseActivity.loginUser.getId();
      break;
      str = BaseActivity.log;
      break label69;
    }
  }

  public static Map<String, String> getTownParams(int paramInt)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("countyId", String.valueOf(paramInt));
    return localHashMap;
  }

  public static Map<String, String> lookOverLogistics(String paramString1, String paramString2, String paramString3)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("giftnum", paramString1);
    localHashMap.put("logisticsnum", paramString2);
    localHashMap.put("logisticscompany", paramString3);
    return localHashMap;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.http.HttpRequestParam
 * JD-Core Version:    0.6.2
 */