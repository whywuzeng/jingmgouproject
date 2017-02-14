package cn.jpush.android.util;

import android.content.Context;
import android.util.Log;
import cn.jpush.android.data.f;
import cn.jpush.android.service.PushService;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JLogger
{
  private static final SimpleDateFormat a;
  private static r b;
  private static final String z;

  static
  {
    Object localObject1 = "\t\003^Z`=7\nG)P2o\031 \035@TP".toCharArray();
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
        i = 77;
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
      a = new SimpleDateFormat(z);
      b = new r();
      return;
      i = 112;
      continue;
      i = 122;
      continue;
      i = 39;
      continue;
      i = 35;
    }
  }

  private static void a(int paramInt, String paramString1, String paramString2)
  {
    String str = "V";
    switch (paramInt)
    {
    default:
      if ((b != null) && (b.b))
        break;
    case 1:
    case 2:
    case 4:
    case 8:
    case 16:
    }
    do
    {
      do
      {
        return;
        if (PushService.b)
          Log.v(paramString1, paramString2);
        str = "V";
        break;
        if (PushService.b)
          Log.d(paramString1, paramString2);
        str = "D";
        break;
        if (PushService.b)
          Log.i(paramString1, paramString2);
        str = "I";
        break;
        if (PushService.b)
          Log.w(paramString1, paramString2);
        str = "W";
        break;
        if (PushService.b)
          Log.e(paramString1, paramString2);
        str = "E";
        break;
      }
      while ((b.a & paramInt) == 0);
      paramString1 = new f(paramInt, str, paramString1, paramString2, a.format(new Date()));
    }
    while (b == null);
    b.a(paramString1);
  }

  public static void d(String paramString1, String paramString2)
  {
    a(2, paramString1, paramString2);
  }

  public static void e(String paramString1, String paramString2)
  {
    a(16, paramString1, paramString2);
  }

  public static void i(String paramString1, String paramString2)
  {
    a(4, paramString1, paramString2);
  }

  public static void parseModalJson(String paramString, Context paramContext)
  {
    if (paramContext == null);
    while (b == null)
      return;
    b.a(paramContext, paramString);
  }

  public static void reportByHeartbeats()
  {
    if (b != null)
      b.a();
  }

  public static void v(String paramString1, String paramString2)
  {
    a(1, paramString1, paramString2);
  }

  public static void w(String paramString1, String paramString2)
  {
    a(8, paramString1, paramString2);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.util.JLogger
 * JD-Core Version:    0.6.2
 */