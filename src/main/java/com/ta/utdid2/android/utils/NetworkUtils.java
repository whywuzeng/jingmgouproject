package com.ta.utdid2.android.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

public class NetworkUtils
{
  public static final String DEFAULT_WIFI_ADDRESS = "00-00-00-00-00-00";
  private static final String TAG = "NetworkUtils";
  private static final int[] WEAK_NETWORK_GROUP = { 4, 7, 2, 1 };
  public static final String WIFI = "Wi-Fi";
  private static ConnectivityManager sConnManager = null;

  private static String _convertIntToIp(int paramInt)
  {
    return (paramInt & 0xFF) + "." + (paramInt >> 8 & 0xFF) + "." + (paramInt >> 16 & 0xFF) + "." + (paramInt >> 24 & 0xFF);
  }

  public static ConnectivityManager getConnManager(Context paramContext)
  {
    if (paramContext == null)
    {
      Log.e("NetworkUtils", "context is null!");
      return null;
    }
    if (sConnManager == null)
      sConnManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
    return sConnManager;
  }

  public static String[] getNetworkState(Context paramContext)
  {
    String[] arrayOfString = new String[2];
    arrayOfString[0] = "Unknown";
    arrayOfString[1] = "Unknown";
    try
    {
      if (paramContext.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", paramContext.getPackageName()) != 0)
      {
        arrayOfString[0] = "Unknown";
        return arrayOfString;
      }
      paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
      if (paramContext == null)
      {
        arrayOfString[0] = "Unknown";
        return arrayOfString;
      }
      NetworkInfo localNetworkInfo = paramContext.getNetworkInfo(1);
      if ((localNetworkInfo != null) && (localNetworkInfo.getState() == NetworkInfo.State.CONNECTED))
      {
        arrayOfString[0] = "Wi-Fi";
        return arrayOfString;
      }
      paramContext = paramContext.getNetworkInfo(0);
      if ((paramContext != null) && (paramContext.getState() == NetworkInfo.State.CONNECTED))
      {
        arrayOfString[0] = "2G/3G";
        arrayOfString[1] = paramContext.getSubtypeName();
        return arrayOfString;
      }
    }
    catch (Exception paramContext)
    {
    }
    return arrayOfString;
  }

  public static String getWifiAddress(Context paramContext)
  {
    if (paramContext != null)
    {
      paramContext = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo();
      if (paramContext != null)
      {
        String str = paramContext.getMacAddress();
        paramContext = str;
        if (StringUtils.isEmpty(str))
          paramContext = "00-00-00-00-00-00";
        return paramContext;
      }
      return "00-00-00-00-00-00";
    }
    return "00-00-00-00-00-00";
  }

  public static String getWifiIpAddress(Context paramContext)
  {
    if (paramContext != null)
      try
      {
        paramContext = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo();
        if (paramContext != null)
        {
          paramContext = _convertIntToIp(paramContext.getIpAddress());
          return paramContext;
        }
        return null;
      }
      catch (Exception paramContext)
      {
      }
    return null;
  }

  public static boolean isConnected(Context paramContext)
  {
    paramContext = getConnManager(paramContext);
    if (paramContext != null)
      try
      {
        paramContext = paramContext.getActiveNetworkInfo();
        if (paramContext != null)
        {
          boolean bool = paramContext.isConnected();
          return bool;
        }
      }
      catch (Exception paramContext)
      {
        Log.e("NetworkUtils", paramContext.toString());
      }
    while (true)
    {
      return false;
      Log.e("NetworkUtils", "connManager is null!");
    }
  }

  public static boolean isConnectedToWeakNetwork(Context paramContext)
  {
    boolean bool2 = false;
    paramContext = getConnManager(paramContext);
    int j;
    int k;
    int i;
    if (paramContext != null)
    {
      try
      {
        paramContext = paramContext.getActiveNetworkInfo();
        if (paramContext != null)
        {
          j = paramContext.getSubtype();
          if (DebugUtils.DBG)
            Log.d("NetworkUtils", "subType:" + j + ": name:" + paramContext.getSubtypeName());
          paramContext = WEAK_NETWORK_GROUP;
          k = paramContext.length;
          i = 0;
          break label114;
        }
        Log.e("NetworkUtils", "networkInfo is null!");
        return false;
      }
      catch (Exception paramContext)
      {
        Log.e("NetworkUtils", paramContext.toString());
        return false;
      }
    }
    else
    {
      Log.e("NetworkUtils", "connManager is null!");
      return false;
    }
    while (true)
    {
      label114: boolean bool1 = bool2;
      if (i < k)
      {
        if (paramContext[i] == j)
          bool1 = true;
      }
      else
        return bool1;
      i += 1;
    }
  }

  public static boolean isWifi(Context paramContext)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramContext != null);
    try
    {
      boolean bool3 = getNetworkState(paramContext)[0].equals("Wi-Fi");
      bool1 = bool2;
      if (bool3)
        bool1 = true;
      return bool1;
    }
    catch (Exception paramContext)
    {
    }
    return false;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ta.utdid2.android.utils.NetworkUtils
 * JD-Core Version:    0.6.2
 */