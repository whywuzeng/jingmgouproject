package com.ismartgo.beacon.http;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import com.ismartgo.beacon.util.DeviceInfoUtil;
import java.util.HashMap;
import java.util.Map;

public class HttpRequestParam
{
  public static Map<String, String> getBeaconInfoParam(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("appKey", paramString1);
    localHashMap.put("appPackName", paramString2);
    localHashMap.put("appType", paramString3);
    localHashMap.put("devCode", paramString5);
    localHashMap.put("beacons", paramString4);
    return localHashMap;
  }

  private static Map<String, String> getDeviceInfo(Context paramContext, Map<String, String> paramMap)
  {
    paramMap.put("devCode", DeviceInfoUtil.getUDID(paramContext));
    paramMap.put("devModel", Build.MANUFACTURER + " " + Build.MODEL);
    paramMap.put("internetType", NetUtil.getCurrentNetworkType(paramContext));
    paramMap.put("vs", Build.VERSION.RELEASE);
    paramMap.put("devRom", DeviceInfoUtil.getPhoneRAM(paramContext));
    return paramMap;
  }

  public static Map<String, String> getInitAppParam(String paramString1, String paramString2, String paramString3)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("appKey", paramString1);
    localHashMap.put("appPackName", paramString2);
    localHashMap.put("appType", paramString3);
    return localHashMap;
  }

  public static Map<String, String> getStatisticsInfoParam(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("appKey", paramString1);
    localHashMap.put("appPackName", paramString2);
    localHashMap.put("appType", paramString3);
    localHashMap.put("beaconInfos", paramString4);
    return getDeviceInfo(paramContext, localHashMap);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.beacon.http.HttpRequestParam
 * JD-Core Version:    0.6.2
 */