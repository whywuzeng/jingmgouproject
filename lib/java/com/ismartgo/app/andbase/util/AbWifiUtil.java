package com.ismartgo.app.andbase.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import java.util.List;

public class AbWifiUtil
{
  public static WifiInfo getConnectionInfo(Context paramContext)
  {
    return ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo();
  }

  public static List<ScanResult> getScanResults(Context paramContext)
  {
    WifiManager localWifiManager = (WifiManager)paramContext.getSystemService("wifi");
    if (!localWifiManager.startScan())
    {
      getScanResults(paramContext);
      return null;
    }
    return localWifiManager.getScanResults();
  }

  public static ScanResult getScanResultsByBSSID(Context paramContext, String paramString)
  {
    Object localObject2 = (WifiManager)paramContext.getSystemService("wifi");
    Object localObject1 = null;
    ScanResult localScanResult = null;
    if (!((WifiManager)localObject2).startScan())
      getScanResultsByBSSID(paramContext, paramString);
    localObject2 = ((WifiManager)localObject2).getScanResults();
    paramContext = localObject1;
    int i;
    if (localObject2 != null)
      i = 0;
    for (paramContext = localScanResult; ; paramContext = localScanResult)
    {
      if (i >= ((List)localObject2).size());
      do
      {
        return paramContext;
        localScanResult = (ScanResult)((List)localObject2).get(i);
        paramContext = localScanResult;
      }
      while (localScanResult.BSSID.equals(paramString));
      i += 1;
    }
  }

  public static boolean isConnectivity(Context paramContext)
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
    paramContext = (TelephonyManager)paramContext.getSystemService("phone");
    return ((localConnectivityManager.getActiveNetworkInfo() != null) && (localConnectivityManager.getActiveNetworkInfo().getState() == NetworkInfo.State.CONNECTED)) || (paramContext.getNetworkType() == 3);
  }

  public static boolean isWifiConnectivity(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    return (paramContext != null) && (paramContext.getType() == 1);
  }

  public static void setWifiEnabled(Context paramContext, boolean paramBoolean)
  {
    paramContext = (WifiManager)paramContext.getSystemService("wifi");
    if (paramBoolean)
    {
      paramContext.setWifiEnabled(true);
      return;
    }
    paramContext.setWifiEnabled(false);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.andbase.util.AbWifiUtil
 * JD-Core Version:    0.6.2
 */