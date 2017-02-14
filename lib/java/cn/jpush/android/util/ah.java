package cn.jpush.android.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public final class ah
{
  private static SharedPreferences a;
  private static final String z;

  static
  {
    Object localObject1 = "Li17RLi9 H`~?-]Vz".toCharArray();
    int j = localObject1.length;
    int m = 0;
    int i = 0;
    Object localObject2 = localObject1;
    int k = j;
    label30: int n;
    if (j <= 1)
    {
      m = i;
      k = i;
      localObject2 = localObject1;
      n = localObject2[k];
      switch (m % 5)
      {
      default:
        i = 59;
      case 0:
      case 1:
      case 2:
      case 3:
      }
    }
    while (true)
    {
      localObject2[k] = ((char)(i ^ n));
      m += 1;
      if (j == 0)
      {
        k = j;
        break label30;
      }
      k = j;
      localObject2 = localObject1;
      localObject1 = localObject2;
      j = k;
      i = m;
      if (k > m)
        break;
      z = new String(localObject2).intern();
      a = null;
      return;
      i = 63;
      continue;
      i = 29;
      continue;
      i = 80;
      continue;
      i = 67;
    }
  }

  public static long a(Context paramContext, String paramString, long paramLong)
  {
    a(paramContext);
    return a.getLong(paramString, 0L);
  }

  private static SharedPreferences a(Context paramContext)
  {
    if (a == null)
      a = paramContext.getSharedPreferences(z, 0);
    return a;
  }

  public static void b(Context paramContext, String paramString, long paramLong)
  {
    paramContext = a(paramContext).edit();
    paramContext.putLong(paramString, paramLong);
    paramContext.commit();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.util.ah
 * JD-Core Version:    0.6.2
 */