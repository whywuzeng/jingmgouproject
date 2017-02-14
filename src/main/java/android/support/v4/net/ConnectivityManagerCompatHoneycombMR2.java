package android.support.v4.net;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

class ConnectivityManagerCompatHoneycombMR2
{
  public static boolean isActiveNetworkMetered(ConnectivityManager paramConnectivityManager)
  {
    paramConnectivityManager = paramConnectivityManager.getActiveNetworkInfo();
    if (paramConnectivityManager == null)
      return true;
    switch (paramConnectivityManager.getType())
    {
    case 0:
    case 2:
    case 3:
    case 4:
    case 5:
    case 6:
    case 8:
    default:
      return true;
    case 1:
    case 7:
    case 9:
    }
    return false;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     android.support.v4.net.ConnectivityManagerCompatHoneycombMR2
 * JD-Core Version:    0.6.2
 */