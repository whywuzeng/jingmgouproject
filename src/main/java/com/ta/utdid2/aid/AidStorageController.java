package com.ta.utdid2.aid;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.util.Base64;
import android.util.Log;
import com.ta.utdid2.android.utils.DebugUtils;
import com.ta.utdid2.android.utils.SharedPreferenceHelper;
import com.ta.utdid2.android.utils.StringUtils;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AidStorageController
{
  private static final String KEY_PREF_AID_GEN_TIME = "rKrMJgyAEbVtSQGi";
  private static final String KEY_PREF_AID_VALUE = "EvQwnbilKezpOJey";
  private static final String PREF_AID = "OfJbkLdFbPOMbGyP";
  private static final String TAG = AidStorageController.class.getName();
  private static Map<String, Long> sAidGenTimeMapInSP = new ConcurrentHashMap();
  private static Map<String, String> sAidMapInSP = new ConcurrentHashMap();

  public static long getAidGenTimeFromSP(Context paramContext, String paramString1, String paramString2)
  {
    if (paramContext == null)
    {
      Log.e(TAG, "no context!");
      return 0L;
    }
    String str = getEncodedAppName(paramString1, paramString2);
    if (sAidGenTimeMapInSP.containsKey(str));
    for (long l = ((Long)sAidGenTimeMapInSP.get(str)).longValue(); ; l = 0L)
    {
      paramString2 = Long.valueOf(l);
      if (DebugUtils.DBG)
        Log.d(TAG, "cache AIDGenTime:" + paramString2);
      paramString1 = paramString2;
      if (paramString2.longValue() == 0L)
      {
        paramString1 = Long.valueOf(paramContext.getSharedPreferences("OfJbkLdFbPOMbGyP", 0).getLong("rKrMJgyAEbVtSQGi".concat(str), 0L));
        sAidGenTimeMapInSP.put(str, paramString1);
      }
      return paramString1.longValue();
    }
  }

  public static String getAidValueFromSP(Context paramContext, String paramString1, String paramString2)
  {
    if (paramContext == null)
    {
      Log.e(TAG, "no context!");
      paramString1 = "";
    }
    String str;
    do
    {
      return paramString1;
      str = getEncodedAppName(paramString1, paramString2);
      paramString2 = (String)sAidMapInSP.get(str);
      if (DebugUtils.DBG)
        Log.d(TAG, "cache AID:" + paramString2);
      paramString1 = paramString2;
    }
    while (!StringUtils.isEmpty(paramString2));
    paramContext = paramContext.getSharedPreferences("OfJbkLdFbPOMbGyP", 0).getString("EvQwnbilKezpOJey".concat(str), "");
    sAidMapInSP.put(str, paramContext);
    return paramContext;
  }

  private static String getEncodedAppName(String paramString1, String paramString2)
  {
    paramString1 = Base64.encodeToString(paramString1.concat(paramString2).getBytes(Charset.forName("UTF-8")), 2);
    if (DebugUtils.DBG)
      Log.d(TAG, "encodedName:" + paramString1);
    return paramString1;
  }

  public static void setAidValueToSP(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    if (paramContext == null)
    {
      Log.e(TAG, "no context!");
      return;
    }
    paramString1 = getEncodedAppName(paramString1, paramString3);
    long l = System.currentTimeMillis();
    sAidMapInSP.put(paramString1, paramString2);
    sAidGenTimeMapInSP.put(paramString1, Long.valueOf(l));
    paramContext = paramContext.getSharedPreferences("OfJbkLdFbPOMbGyP", 0);
    if (Build.VERSION.SDK_INT >= 9)
    {
      SharedPreferenceHelper.apply(paramContext.edit().putString("EvQwnbilKezpOJey".concat(paramString1), paramString2));
      SharedPreferenceHelper.apply(paramContext.edit().putLong("rKrMJgyAEbVtSQGi".concat(paramString1), l));
      return;
    }
    paramContext.edit().putString("EvQwnbilKezpOJey".concat(paramString1), paramString2).commit();
    paramContext.edit().putLong("rKrMJgyAEbVtSQGi".concat(paramString1), l).commit();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ta.utdid2.aid.AidStorageController
 * JD-Core Version:    0.6.2
 */