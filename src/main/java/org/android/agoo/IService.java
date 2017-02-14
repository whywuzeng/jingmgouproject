package org.android.agoo;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import org.android.agoo.callback.IServiceCallBack;

public abstract interface IService
{
  public abstract IBinder bind(Intent paramIntent);

  public abstract void create(Context paramContext, IServiceCallBack paramIServiceCallBack);

  public abstract void destroy(Context paramContext);

  public abstract int startCommand(Intent paramIntent, int paramInt1, int paramInt2);

  public abstract boolean unbind(Intent paramIntent);
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.IService
 * JD-Core Version:    0.6.2
 */