package com.baidu.location;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public abstract interface LLSInterface
{
  public abstract double getVersion();

  public abstract IBinder onBind(Intent paramIntent);

  public abstract void onCreate(Context paramContext);

  public abstract void onDestroy();

  public abstract int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2);

  public abstract boolean onUnBind(Intent paramIntent);
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.LLSInterface
 * JD-Core Version:    0.6.2
 */