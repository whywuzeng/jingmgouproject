package com.baidu.vi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

final class a extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    VDeviceAPI.onNetworkStateChanged();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.vi.a
 * JD-Core Version:    0.6.2
 */