package org.android.agoo.service;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import org.android.agoo.IPushService;
import org.android.agoo.callback.IServiceCallBack;
import org.android.agoo.proxy.ProxyFactroy;

public abstract class AgooService extends Service
{
  private static final String TAG = "AgooService";
  private Context mContext = this;
  private volatile IPushService pushService = null;

  public final IBinder onBind(Intent paramIntent)
  {
    try
    {
      if (this.pushService != null)
        return this.pushService.bind(paramIntent);
      Log.d("AgooService", "onBind[pushService==null]");
      label26: return null;
    }
    catch (Throwable paramIntent)
    {
      break label26;
    }
  }

  public final void onCreate()
  {
    try
    {
      this.pushService = ((IPushService)ProxyFactroy.getInstance(this, "org.android.agoo.impl.PushService"));
      Log.d("AgooService", "pushService==[" + this.pushService.getClass().getName() + "]");
      if (this.pushService != null)
        this.pushService.create(this, new IServiceCallBack()
        {
          public void stop()
          {
            AgooService.this.stopSelf();
          }
        });
      if (Build.VERSION.SDK_INT < 18)
        startForeground(-1469, new Notification());
      new Handler(Looper.getMainLooper());
      return;
    }
    catch (Throwable localThrowable)
    {
      Log.e("AgooService", "onCreate", localThrowable);
    }
  }

  public final void onDestroy()
  {
    try
    {
      if (this.pushService != null)
        this.pushService.destroy(this.mContext);
      return;
    }
    catch (Throwable localThrowable)
    {
    }
  }

  public final int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    try
    {
      if (this.pushService != null)
        return this.pushService.startCommand(paramIntent, paramInt1, paramInt2);
      Log.d("AgooService", "onStartCommand[pushService==null]");
      return super.onStartCommand(paramIntent, paramInt1, paramInt2);
    }
    catch (Throwable localThrowable)
    {
      while (true)
        Log.e("AgooService", "onStartCommand", localThrowable);
    }
  }

  public final boolean onUnbind(Intent paramIntent)
  {
    try
    {
      if (this.pushService != null)
        return this.pushService.unbind(paramIntent);
      Log.d("AgooService", "onBind[pushService==null]");
      label26: return true;
    }
    catch (Throwable paramIntent)
    {
      break label26;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.service.AgooService
 * JD-Core Version:    0.6.2
 */