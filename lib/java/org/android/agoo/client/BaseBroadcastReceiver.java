package org.android.agoo.client;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public abstract class BaseBroadcastReceiver extends BroadcastReceiver
{
  protected abstract String getIntentServiceClassName(Context paramContext);

  public final void onReceive(Context paramContext, Intent paramIntent)
  {
    try
    {
      onUserReceive(paramContext, paramIntent);
      BaseIntentService.runIntentInService(paramContext, paramIntent, getIntentServiceClassName(paramContext));
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  protected void onUserReceive(Context paramContext, Intent paramIntent)
  {
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.client.BaseBroadcastReceiver
 * JD-Core Version:    0.6.2
 */