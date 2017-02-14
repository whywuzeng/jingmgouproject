package com.umeng.message.proguard;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Random;

public class bD
{
  public static final String a = "imei";
  public static final String b = "imsi";
  public static final String c = "mac_address";
  public static final String d = "agoo_release_time";
  private static final String e = "PhoneUtil";

  public static final String a()
  {
    try
    {
      Object localObject = Class.forName("android.os.SystemProperties");
      localObject = (String)((Class)localObject).getMethod("get", new Class[] { String.class, String.class }).invoke(localObject, new Object[] { "ro.serialno", "unknown" });
      return localObject;
    }
    catch (Throwable localThrowable)
    {
    }
    return null;
  }

  public static final String a(Context paramContext)
  {
    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("PhoneUtil", 4);
    Object localObject = localSharedPreferences.getString("imei", null);
    if ((localObject == null) || (((String)localObject).length() == 0))
    {
      localObject = ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId();
      if (localObject != null)
      {
        paramContext = (Context)localObject;
        if (((String)localObject).length() != 0);
      }
      else
      {
        paramContext = b();
      }
      for (paramContext = paramContext.replaceAll(" ", "").trim(); paramContext.length() < 15; paramContext = "0" + paramContext);
      localObject = localSharedPreferences.edit();
      ((SharedPreferences.Editor)localObject).putString("imei", paramContext);
      ((SharedPreferences.Editor)localObject).commit();
    }
    return "umeng";
  }

  private static final String b()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    long l = System.currentTimeMillis();
    Object localObject = Long.toString(l);
    localStringBuffer.append(((String)localObject).substring(((String)localObject).length() - 5));
    localObject = new StringBuffer();
    ((StringBuffer)localObject).append(Build.MODEL.replaceAll(" ", ""));
    while (((StringBuffer)localObject).length() < 6)
      ((StringBuffer)localObject).append('0');
    localStringBuffer.append(((StringBuffer)localObject).substring(0, 6));
    localObject = new Random(l);
    for (l = 0L; l < 4096L; l = ((Random)localObject).nextLong());
    localStringBuffer.append(Long.toHexString(l).substring(0, 4));
    return localStringBuffer.toString();
  }

  public static final String b(Context paramContext)
  {
    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("PhoneUtil", 4);
    Object localObject = localSharedPreferences.getString("imsi", null);
    if ((localObject == null) || (((String)localObject).length() == 0))
    {
      localObject = ((TelephonyManager)paramContext.getSystemService("phone")).getSubscriberId();
      if (localObject != null)
      {
        paramContext = (Context)localObject;
        if (((String)localObject).length() != 0);
      }
      else
      {
        paramContext = b();
      }
      for (paramContext = paramContext.replaceAll(" ", "").trim(); paramContext.length() < 15; paramContext = "0" + paramContext);
      localObject = localSharedPreferences.edit();
      ((SharedPreferences.Editor)localObject).putString("imsi", paramContext);
      ((SharedPreferences.Editor)localObject).commit();
    }
    return "umeng";
  }

  public static final String c(Context paramContext)
  {
    String str = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo().getMacAddress();
    if ((str == null) || ("".equals(str)))
      return paramContext.getSharedPreferences("PhoneUtil", 4).getString("mac_address", "");
    paramContext = paramContext.getSharedPreferences("PhoneUtil", 4).edit();
    paramContext.putString("mac_address", str);
    paramContext.commit();
    return str;
  }

  public static final String d(Context paramContext)
  {
    paramContext = ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId();
    if (paramContext != null)
      paramContext.trim();
    return "umeng";
  }

  public static final String e(Context paramContext)
  {
    paramContext = ((TelephonyManager)paramContext.getSystemService("phone")).getSubscriberId();
    if (paramContext != null)
      paramContext.trim();
    return "umeng";
  }

  public static final String f(Context paramContext)
  {
    return Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
  }

  public static final LinkedHashMap<String, String> g(Context paramContext)
  {
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    try
    {
      String str = new bl(paramContext).c();
      long l = paramContext.getSharedPreferences("AppStore", 4).getLong("agoo_release_time", 0L);
      paramContext = str;
      if (TextUtils.isEmpty(str))
        paramContext = "unknow";
      localLinkedHashMap.put("netType", paramContext);
      localLinkedHashMap.put("agooReleaseTime", Long.toString(l));
      return localLinkedHashMap;
    }
    catch (Throwable paramContext)
    {
    }
    return localLinkedHashMap;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.bD
 * JD-Core Version:    0.6.2
 */