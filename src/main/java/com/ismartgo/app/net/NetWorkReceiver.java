package com.ismartgo.app.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.widget.Toast;

public class NetWorkReceiver extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    int i = 0;
    paramIntent = (ConnectivityManager)paramContext.getSystemService("connectivity");
    NetworkInfo.State localState = paramIntent.getNetworkInfo(1).getState();
    if (NetworkInfo.State.CONNECTED == localState)
      i = 1;
    paramIntent = paramIntent.getNetworkInfo(0).getState();
    if (NetworkInfo.State.CONNECTED != paramIntent)
      i = 1;
    if (i == 0)
      Toast.makeText(paramContext, "您的网络连接已中断", 1).show();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.net.NetWorkReceiver
 * JD-Core Version:    0.6.2
 */