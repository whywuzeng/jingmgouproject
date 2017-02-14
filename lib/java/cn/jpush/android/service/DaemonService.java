package cn.jpush.android.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import cn.jpush.android.util.x;

public class DaemonService extends Service
{
  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }

  public void onCreate()
  {
    x.c();
    ServiceInterface.c(getApplicationContext(), 30000);
    super.onCreate();
  }

  public void onDestroy()
  {
    super.onDestroy();
  }

  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    return super.onStartCommand(paramIntent, paramInt1, paramInt2);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.service.DaemonService
 * JD-Core Version:    0.6.2
 */