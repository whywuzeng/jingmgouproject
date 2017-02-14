package com.ismartgo.beacon.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.telephony.TelephonyManager;

public class NetUtil
{
  private static final int NETWORK_CLASS_2_G = 1;
  private static final int NETWORK_CLASS_3_G = 2;
  private static final int NETWORK_CLASS_4_G = 3;
  private static final int NETWORK_CLASS_UNAVAILABLE = -1;
  private static final int NETWORK_CLASS_UNKNOWN = 0;
  private static final int NETWORK_CLASS_WIFI = -101;
  public static final int NETWORK_TYPE_1xRTT = 7;
  public static final int NETWORK_TYPE_CDMA = 4;
  public static final int NETWORK_TYPE_EDGE = 2;
  public static final int NETWORK_TYPE_EHRPD = 14;
  public static final int NETWORK_TYPE_EVDO_0 = 5;
  public static final int NETWORK_TYPE_EVDO_A = 6;
  public static final int NETWORK_TYPE_EVDO_B = 12;
  public static final int NETWORK_TYPE_GPRS = 1;
  public static final int NETWORK_TYPE_HSDPA = 8;
  public static final int NETWORK_TYPE_HSPA = 10;
  public static final int NETWORK_TYPE_HSPAP = 15;
  public static final int NETWORK_TYPE_HSUPA = 9;
  public static final int NETWORK_TYPE_IDEN = 11;
  public static final int NETWORK_TYPE_LTE = 13;
  public static final int NETWORK_TYPE_UMTS = 3;
  private static final int NETWORK_TYPE_UNAVAILABLE = -1;
  public static final int NETWORK_TYPE_UNKNOWN = 0;
  private static final int NETWORK_TYPE_WIFI = -101;

  public static boolean checkNetWork(Context paramContext)
  {
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if (paramContext == null);
    while (true)
    {
      return false;
      paramContext = paramContext.getAllNetworkInfo();
      if ((paramContext != null) && (paramContext.length > 0))
      {
        int i = 0;
        while (i < paramContext.length)
        {
          if (paramContext[i].getState() == NetworkInfo.State.CONNECTED)
            return true;
          i += 1;
        }
      }
    }
  }

  public static String getCurrentNetworkType(Context paramContext)
  {
    switch (getNetworkClass(paramContext))
    {
    default:
      return "未知";
    case -1:
      return "无";
    case -101:
      return "Wi-Fi";
    case 1:
      return "2G";
    case 2:
      return "3G";
    case 3:
      return "4G";
    case 0:
    }
    return "未知";
  }

  private static int getNetworkClass(Context paramContext)
  {
    int j = 0;
    try
    {
      NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
      int k;
      if ((localNetworkInfo != null) && (localNetworkInfo.isAvailable()) && (localNetworkInfo.isConnected()))
      {
        k = localNetworkInfo.getType();
        if (k == 1)
          i = -101;
      }
      while (true)
      {
        return getNetworkClassByType(i);
        i = j;
        if (k == 0)
        {
          i = ((TelephonyManager)paramContext.getApplicationContext().getSystemService("phone")).getNetworkType();
          continue;
          i = -1;
        }
      }
    }
    catch (Exception paramContext)
    {
      while (true)
      {
        paramContext.printStackTrace();
        int i = j;
      }
    }
  }

  private static int getNetworkClassByType(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return 0;
    case -1:
      return -1;
    case -101:
      return -101;
    case 1:
    case 2:
    case 4:
    case 7:
    case 11:
      return 1;
    case 3:
    case 5:
    case 6:
    case 8:
    case 9:
    case 10:
    case 12:
    case 14:
    case 15:
      return 2;
    case 13:
    }
    return 3;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.beacon.http.NetUtil
 * JD-Core Version:    0.6.2
 */