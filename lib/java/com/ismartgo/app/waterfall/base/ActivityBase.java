package com.ismartgo.app.waterfall.base;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

public class ActivityBase extends Activity
{
  public boolean checkConnection()
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)getSystemService("connectivity")).getActiveNetworkInfo();
    if (localNetworkInfo != null)
      return localNetworkInfo.isAvailable();
    return false;
  }

  public boolean isWifi(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    return (paramContext != null) && (paramContext.getTypeName().equals("WIFI"));
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
  }

  protected void onPause()
  {
    super.onPause();
  }

  protected void onResume()
  {
    super.onResume();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.waterfall.base.ActivityBase
 * JD-Core Version:    0.6.2
 */