package com.ismartgo.beacon.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;
import com.ismartgo.beacon.SmartgoBeaconUtil;

public class BeaconReceiver extends BroadcastReceiver
{
  public void onReceive(final Context paramContext, Intent paramIntent)
  {
    if (paramIntent.getAction().equals("android.bluetooth.adapter.action.STATE_CHANGED"))
      new Thread()
      {
        public void run()
        {
          SystemClock.sleep(3000L);
          if (SmartgoBeaconUtil.getInstance(paramContext).isOpenBlutoothLe())
            Log.i("smartgo_beacon", "蓝牙已打开");
        }
      }
      .start();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.beacon.receiver.BeaconReceiver
 * JD-Core Version:    0.6.2
 */