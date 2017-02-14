package com.umeng.message;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class UmengIntentService extends UmengBaseIntentService
{
  private static final String a = UmengIntentService.class.getName();

  protected void onMessage(Context paramContext, Intent paramIntent)
  {
    super.onMessage(paramContext, paramIntent);
    try
    {
      String str1 = paramIntent.getStringExtra("body");
      String str2 = paramIntent.getStringExtra("id");
      paramIntent = paramIntent.getStringExtra("task_id");
      Intent localIntent = new Intent();
      localIntent.setPackage(paramContext.getPackageName());
      localIntent.setAction("com.umeng.message.message.handler.action");
      localIntent.putExtra("body", str1);
      localIntent.putExtra("id", str2);
      localIntent.putExtra("task_id", paramIntent);
      paramContext.startService(localIntent);
      return;
    }
    catch (Exception paramContext)
    {
      Log.e(a, paramContext.getMessage());
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.UmengIntentService
 * JD-Core Version:    0.6.2
 */