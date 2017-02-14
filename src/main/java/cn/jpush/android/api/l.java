package cn.jpush.android.api;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.app.TabActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build.VERSION;
import android.os.Bundle;
import cn.jpush.android.util.a;
import cn.jpush.android.util.ai;
import cn.jpush.android.util.x;
import java.util.HashMap;

final class l
  implements Application.ActivityLifecycleCallbacks
{
  private static final String[] z;

  static
  {
    String[] arrayOfString = new String[4];
    Object localObject1 = "";
    int i = -1;
    int j = 0;
    Object localObject3 = arrayOfString;
    localObject1 = ((String)localObject1).toCharArray();
    int k = localObject1.length;
    int m;
    int n;
    int i1;
    label42: Object localObject2;
    int i2;
    label91: label113: Object localObject4;
    int i3;
    if (k <= 1)
    {
      m = 0;
      n = j;
      i1 = i;
      j = m;
      while (true)
      {
        localObject2 = localObject1;
        i2 = localObject2[m];
        switch (j % 5)
        {
        default:
          i = 29;
          localObject2[m] = ((char)(i ^ i2));
          j += 1;
          if (k != 0)
            break label113;
          m = k;
        case 0:
        case 1:
        case 2:
        case 3:
        }
      }
      i = k;
      localObject4 = localObject3;
      i3 = n;
      localObject2 = localObject1;
      i2 = i1;
    }
    while (true)
    {
      i1 = i2;
      localObject1 = localObject2;
      n = i3;
      localObject3 = localObject4;
      k = i;
      m = j;
      if (i > j)
        break label42;
      localObject1 = new String(localObject2).intern();
      switch (i2)
      {
      default:
        localObject4[i3] = localObject1;
        localObject1 = "";
        j = 1;
        i = 0;
        break;
      case 0:
        localObject4[i3] = localObject1;
        localObject1 = "^\007MR^k\rGR^s\002GU|q\005X";
        j = 2;
        i = 1;
        break;
      case 1:
        localObject4[i3] = localObject1;
        j = 3;
        localObject1 = "";
        i = 2;
        break;
      case 2:
        localObject4[i3] = localObject1;
        z = arrayOfString;
        return;
        i = 18;
        break label91;
        i = 110;
        break label91;
        i = 43;
        break label91;
        i = 55;
        break label91;
        m = 0;
        i2 = i;
        localObject2 = localObject1;
        i3 = j;
        localObject4 = localObject3;
        i = k;
        j = m;
      }
    }
  }

  public final void onActivityCreated(Activity paramActivity, Bundle paramBundle)
  {
  }

  public final void onActivityDestroyed(Activity paramActivity)
  {
  }

  public final void onActivityPaused(Activity paramActivity)
  {
    k.c(paramActivity.getClass().getName());
    if (ai.a(k.f()))
      k.a(paramActivity.getClass().getName());
    if ((Build.VERSION.SDK_INT >= 14) && (k.a))
    {
      if ((!e.a) && (e.b().a()))
      {
        k.c().put(k.f(), Integer.valueOf(0));
        if ((!ai.a(k.d())) && (k.d().equals(k.f())))
          a.b(paramActivity, k.e(), k.f(), 0);
      }
      if ((paramActivity instanceof TabActivity))
        x.e();
    }
    else
    {
      return;
    }
    e.a = false;
  }

  public final void onActivityResumed(Activity paramActivity)
  {
    k.a(paramActivity.getClass().getName());
    if ((Build.VERSION.SDK_INT >= 14) && (k.a))
    {
      if (!k.a())
        break label108;
      Intent localIntent = new Intent(z[1]);
      localIntent.setPackage(paramActivity.getPackageName());
      localIntent.addCategory(z[0]);
      paramActivity = paramActivity.getPackageManager().resolveActivity(localIntent, 0);
      if (paramActivity == null)
        x.d(z[2], z[3]);
    }
    else
    {
      return;
    }
    k.b(paramActivity.activityInfo.name);
    k.a(false);
    return;
    label108: if ((!e.b) && (e.b().a()) && (k.b() != null))
    {
      if (!k.c().containsKey(k.b()))
        break label198;
      k.c().put(k.b(), Integer.valueOf(2));
      if ((!ai.a(k.d())) && (k.d().equals(k.b())))
        a.b(paramActivity, k.e(), k.b(), 2);
    }
    while ((paramActivity instanceof TabActivity))
    {
      x.e();
      return;
      label198: k.c().put(k.b(), Integer.valueOf(1));
      if ((!ai.a(k.d())) && (k.d().equals(k.b())))
        a.b(paramActivity, k.e(), k.b(), 1);
    }
    e.b = false;
  }

  public final void onActivitySaveInstanceState(Activity paramActivity, Bundle paramBundle)
  {
  }

  public final void onActivityStarted(Activity paramActivity)
  {
  }

  public final void onActivityStopped(Activity paramActivity)
  {
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.api.l
 * JD-Core Version:    0.6.2
 */