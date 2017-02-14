package com.umeng.message.proguard;

import android.annotation.TargetApi;
import android.app.Application;

public class C
{
  @TargetApi(14)
  public static void a(Application paramApplication)
  {
    if (paramApplication != null)
      paramApplication.registerActivityLifecycleCallbacks(B.a());
  }

  @TargetApi(14)
  public static void a(A paramA)
  {
    if (paramA != null)
      B.a().a(paramA);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.C
 * JD-Core Version:    0.6.2
 */