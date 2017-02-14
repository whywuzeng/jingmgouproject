package com.umeng.message.proguard;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;

public class aT
{
  private static String a = Build.BRAND;
  private static final String b = "agoo_xiaomi";
  private static final String c = "agoo_huawei";
  private static final String d = "com.xiaomi.xmsf";
  private static final String e = "com.huawei.android.pushagent";
  private static final String f = "Huawei".toLowerCase();
  private static final String g = "Xiaomi".toLowerCase();

  public static final void a(Context paramContext)
  {
    if ((paramContext == null) || (TextUtils.isEmpty(a)))
      return;
    bG.a(new Runnable()
    {
      public void run()
      {
        try
        {
          Object localObject = this.a.getPackageManager();
          if ((TextUtils.equals(aT.a(), aT.b().toLowerCase())) && (((PackageManager)localObject).getPackageInfo("com.huawei.android.pushagent", 4) != null))
            aW.i(this.a, "agoo_huawei");
          if (TextUtils.equals(aT.c(), aT.b().toLowerCase()))
          {
            localObject = ((PackageManager)localObject).getPackageInfo("com.xiaomi.xmsf", 4);
            if ((localObject != null) && (((PackageInfo)localObject).versionCode >= 105))
              aW.i(this.a, "agoo_xiaomi");
          }
          return;
        }
        catch (Throwable localThrowable)
        {
        }
      }
    });
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.aT
 * JD-Core Version:    0.6.2
 */