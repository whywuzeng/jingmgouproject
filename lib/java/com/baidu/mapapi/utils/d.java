package com.baidu.mapapi.utils;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.baidu.a.a.a.a.a;
import com.baidu.a.a.a.c.a;

class d extends c.a
{
  d(c paramc)
  {
  }

  public void a(IBinder paramIBinder)
    throws RemoteException
  {
    Log.d(a.b(), "onClientReady");
    if (a.c() != null)
      a.a(null);
    a.a(a.a.a(paramIBinder));
    if (!a.e())
      a.a(a.a);
    a.a(true);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.utils.d
 * JD-Core Version:    0.6.2
 */