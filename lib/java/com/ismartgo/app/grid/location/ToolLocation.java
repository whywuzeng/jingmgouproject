package com.ismartgo.app.grid.location;

import android.content.Context;
import android.location.LocationManager;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.ismartgo.app.grid.utils.ToolNetwork;

public class ToolLocation
{
  private static LocationClient mLocClient;
  private static LocationClientOption options;

  private static LocationClientOption configOptions(Context paramContext)
  {
    LocationClientOption localLocationClientOption = new LocationClientOption();
    if (isGpsOPen(paramContext))
    {
      localLocationClientOption.setOpenGps(true);
      localLocationClientOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
    }
    while (true)
    {
      localLocationClientOption.setIsNeedAddress(true);
      localLocationClientOption.setIsNeedLocationDescribe(true);
      localLocationClientOption.setCoorType("bd09ll");
      localLocationClientOption.setNeedDeviceDirect(true);
      localLocationClientOption.setIgnoreKillProcess(false);
      localLocationClientOption.setScanSpan(300000);
      localLocationClientOption.setTimeOut(3600000);
      return localLocationClientOption;
      if (ToolNetwork.getInstance(paramContext).isConnected())
        localLocationClientOption.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);
    }
  }

  public static LocationClient getLocationClient()
  {
    return mLocClient;
  }

  public static boolean isGpsOPen(Context paramContext)
  {
    return ((LocationManager)paramContext.getSystemService("location")).isProviderEnabled("gps");
  }

  public static void requestLocation(Context paramContext, InterfaceBDLocation paramInterfaceBDLocation, final boolean paramBoolean)
  {
    if (mLocClient == null)
    {
      mLocClient = new LocationClient(paramContext);
      options = configOptions(paramContext);
      mLocClient.setLocOption(options);
      mLocClient.registerLocationListener(new BDLocationListener()
      {
        public void onReceiveLocation(BDLocation paramAnonymousBDLocation)
        {
          if ((paramAnonymousBDLocation == null) || (ToolLocation.this == null));
          do
          {
            return;
            ToolLocation.this.onLocationSuccess(paramAnonymousBDLocation);
          }
          while (!paramBoolean);
          ToolLocation.mLocClient.stop();
        }
      });
      mLocClient.start();
    }
  }

  public static void stopLocation()
  {
    try
    {
      if (mLocClient == null)
        return;
      if (mLocClient != null)
      {
        mLocClient.stop();
        mLocClient = null;
        return;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public static abstract interface InterfaceBDLocation
  {
    public abstract void onLocationSuccess(BDLocation paramBDLocation);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.grid.location.ToolLocation
 * JD-Core Version:    0.6.2
 */