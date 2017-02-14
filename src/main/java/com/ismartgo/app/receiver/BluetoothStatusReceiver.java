package com.ismartgo.app.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.ismartgo.app.ibeacon.MyIbeacon;
import java.io.PrintStream;

public class BluetoothStatusReceiver extends BroadcastReceiver
{
  public static int closeBluetooth;

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (paramIntent.getAction().equals("android.bluetooth.adapter.action.STATE_CHANGED"))
    {
      paramIntent = MyIbeacon.getInstance(paramContext);
      if (paramIntent.isSuportBlutoohLe())
        break label25;
    }
    label25: 
    do
    {
      return;
      if (!paramIntent.isOpenBlutoothLe())
        break;
      if (!MyIbeacon.getInstance(paramContext).isFirstScan())
      {
        System.out.println("设置第一次扫描");
        MyIbeacon.getInstance(paramContext).setFirstScan(true);
      }
      System.out.println("closeBluetooth: " + closeBluetooth);
    }
    while (closeBluetooth != 1);
    paramIntent.startScan();
    System.out.println("开始扫描");
    closeBluetooth = 0;
    return;
    closeBluetooth = 1;
    paramIntent.stopScan();
    System.out.println("停止扫描: " + closeBluetooth);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.receiver.BluetoothStatusReceiver
 * JD-Core Version:    0.6.2
 */