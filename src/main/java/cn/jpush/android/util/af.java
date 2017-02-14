package cn.jpush.android.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class af
{
  private static af c;
  private static final String z;
  private final String a = z;
  private SharedPreferences b = null;

  static
  {
    Object localObject1 = "B#\t:U[2#\nRf\025\025.".toCharArray();
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
        i = 61;
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
      return;
      i = 8;
      continue;
      i = 115;
      continue;
      i = 124;
      continue;
      i = 73;
    }
  }

  private SharedPreferences a(Context paramContext)
  {
    if (this.b == null);
    try
    {
      this.b = paramContext.getSharedPreferences(z, 0);
      return this.b;
    }
    finally
    {
      paramContext = finally;
    }
    throw paramContext;
  }

  public static af a()
  {
    if (c == null)
      c = new af();
    return c;
  }

  public final long a(Context paramContext, String paramString, long paramLong)
  {
    return a(paramContext).getLong(paramString, paramLong);
  }

  public final String a(Context paramContext, String paramString1, String paramString2)
  {
    return a(paramContext).getString(paramString1, null);
  }

  public final void b(Context paramContext, String paramString, long paramLong)
  {
    a(paramContext).edit().putLong(paramString, paramLong).commit();
  }

  public final void b(Context paramContext, String paramString1, String paramString2)
  {
    a(paramContext).edit().putString(paramString1, paramString2).commit();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.util.af
 * JD-Core Version:    0.6.2
 */