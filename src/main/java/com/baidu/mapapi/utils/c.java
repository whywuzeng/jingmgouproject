package com.baidu.mapapi.utils;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.baidu.a.a.a.b;
import com.baidu.a.a.a.b.a;

final class c
  implements ServiceConnection
{
  public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
  {
    Log.d(a.b(), "onServiceConnected " + paramComponentName);
    try
    {
      if (a.d() != null)
        a.a(null);
      a.a(b.a.a(paramIBinder));
      a.d().a(new d(this));
      return;
    }
    catch (RemoteException paramComponentName)
    {
      do
        Log.d(a.b(), "getComOpenClient ", paramComponentName);
      while (a.d() == null);
      a.a(null);
    }
  }

  public void onServiceDisconnected(ComponentName paramComponentName)
  {
    Log.d(a.b(), "onServiceDisconnected " + paramComponentName);
    if (a.d() != null)
      a.a(null);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.utils.c
 * JD-Core Version:    0.6.2
 */