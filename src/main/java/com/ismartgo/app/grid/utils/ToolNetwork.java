package com.ismartgo.app.grid.utils;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class ToolNetwork
{
  public static final String NETWORK_CMNET = "CMNET";
  public static final String NETWORK_CMWAP = "CMWAP";
  public static final String NETWORK_WIFI = "WIFI";
  public static final String TAG = "NetWorkUtils";
  private static Context localContext = null;
  private static NetworkInfo networkInfo = null;

  public static ToolNetwork getInstance(Context paramContext)
  {
    localContext = paramContext;
    return SingletonHolder.instance;
  }

  public static String getNetworkInfo(Context paramContext)
  {
    String str = "";
    NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    if ((localNetworkInfo != null) && (localNetworkInfo.isConnected()))
    {
      int i = localNetworkInfo.getType();
      if (i == 1)
        paramContext = "WIFI";
      do
      {
        return paramContext;
        paramContext = str;
      }
      while (i != 0);
      i = localNetworkInfo.getType();
      paramContext = localNetworkInfo.getSubtypeName();
      switch (i)
      {
      default:
        if ((paramContext.equals("TD-SCDMA")) || (paramContext.equals("WCDMA")) || (paramContext.equals("CDMA2000")))
          return "3G";
        break;
      case 1:
      case 2:
      case 4:
      case 7:
      case 11:
        return "2G";
      case 3:
      case 5:
      case 6:
      case 8:
      case 9:
      case 10:
      case 12:
      case 14:
      case 15:
        return "3G";
      case 13:
        return "4G";
      }
      if (paramContext.equals("TDS_HSDPA"))
        return "3G";
      return paramContext;
    }
    return "未知网络";
  }

  public String getNetworkType()
  {
    if (isConnected())
    {
      int i = networkInfo.getType();
      if (i == 0)
      {
        Log.i("NetWorkUtils", "networkInfo.getExtraInfo()-->" + networkInfo.getExtraInfo());
        if ("CMWAP".equals(networkInfo.getExtraInfo().toLowerCase()))
          return "CMWAP";
        return "CMNET";
      }
      if (1 == i)
        return "WIFI";
    }
    return "";
  }

  public boolean isAvailable()
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)localContext.getApplicationContext().getSystemService("connectivity");
    if (localConnectivityManager == null);
    do
    {
      return false;
      networkInfo = localConnectivityManager.getActiveNetworkInfo();
    }
    while ((networkInfo == null) || (!networkInfo.isAvailable()));
    return true;
  }

  public boolean isConnected()
  {
    if (!isAvailable());
    while (!networkInfo.isConnected())
      return false;
    return true;
  }

  public void validateNetWork()
  {
    if (!isConnected())
    {
      AlertDialog.Builder localBuilder = new AlertDialog.Builder(localContext);
      localBuilder.setTitle("网络设置");
      localBuilder.setMessage("网络不可用，是否现在设置网络?");
      localBuilder.setPositiveButton(17039370, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          ((Activity)ToolNetwork.localContext).startActivityForResult(new Intent("android.settings.SETTINGS"), paramAnonymousInt);
        }
      });
      localBuilder.setNegativeButton(17039360, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramAnonymousDialogInterface.cancel();
        }
      });
      localBuilder.create();
      localBuilder.show();
    }
  }

  private static class SingletonHolder
  {
    private static ToolNetwork instance = new ToolNetwork(null);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.grid.utils.ToolNetwork
 * JD-Core Version:    0.6.2
 */