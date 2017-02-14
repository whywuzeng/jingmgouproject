package org.android.agoo;

import android.content.Context;
import android.content.Intent;
import org.android.agoo.callback.IControlCallBack;

public abstract interface IControlService
{
  public abstract void onHandleIntent(Context paramContext, Intent paramIntent, IControlCallBack paramIControlCallBack);
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.IControlService
 * JD-Core Version:    0.6.2
 */