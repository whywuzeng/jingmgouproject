package cn.jpush.android.util;

import android.util.Log;
import cn.jpush.android.service.PushService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public final class x
{
  private static final SimpleDateFormat a;
  private static ArrayList<String> b;
  private static boolean c;
  private static boolean d;
  private static final String[] z;

  static
  {
    String[] arrayOfString = new String[3];
    Object localObject2 = "\t:e8X";
    int i = -1;
    int j = 0;
    Object localObject1 = arrayOfString;
    localObject2 = ((String)localObject2).toCharArray();
    int k = localObject2.length;
    int m;
    Object localObject3;
    int n;
    int i1;
    label50: int i2;
    label99: label121: Object localObject4;
    int i3;
    if (k <= 1)
    {
      m = 0;
      localObject3 = localObject1;
      n = j;
      localObject1 = localObject2;
      i1 = i;
      j = m;
      while (true)
      {
        localObject2 = localObject1;
        i2 = localObject2[m];
        switch (j % 5)
        {
        default:
          i = 48;
          localObject2[m] = ((char)(i ^ i2));
          j += 1;
          if (k != 0)
            break label121;
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
        break label50;
      localObject1 = new String((char[])localObject2).intern();
      switch (i2)
      {
      default:
        localObject4[i3] = localObject1;
        localObject2 = "\036J";
        j = 1;
        i = 0;
        break;
      case 0:
        localObject4[i3] = localObject1;
        localObject2 = "\016'>/T\034\"Xq].Pc8o\0209C";
        j = 2;
        i = 1;
        break;
      case 1:
        localObject4[i3] = localObject1;
        z = arrayOfString;
        a = new SimpleDateFormat(z[2]);
        b = new ArrayList();
        c = false;
        d = false;
        return;
        i = 67;
        break label99;
        i = 106;
        break label99;
        i = 16;
        break label99;
        i = 75;
        break label99;
        m = 0;
        i2 = i;
        i3 = j;
        localObject4 = localObject1;
        i = k;
        j = m;
      }
    }
  }

  public static void a(String paramString1, String paramString2)
  {
    if ((PushService.b) && (a(2)))
      Log.v(z[0], "[" + paramString1 + z[1] + paramString2);
  }

  public static void a(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if ((PushService.b) && (a(5)))
      Log.w(z[0], "[" + paramString1 + z[1] + paramString2, paramThrowable);
  }

  public static boolean a()
  {
    return false;
  }

  private static boolean a(int paramInt)
  {
    return paramInt >= PushService.d;
  }

  public static void b()
  {
  }

  public static void b(String paramString1, String paramString2)
  {
    if ((PushService.b) && (a(3)))
      Log.d(z[0], "[" + paramString1 + z[1] + paramString2);
  }

  public static void b(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if ((PushService.b) && (a(6)))
      Log.e(z[0], "[" + paramString1 + z[1] + paramString2, paramThrowable);
  }

  public static void c()
  {
  }

  public static void c(String paramString1, String paramString2)
  {
    if ((PushService.b) && (a(4)))
      Log.i(z[0], "[" + paramString1 + z[1] + paramString2);
  }

  public static void d()
  {
  }

  public static void d(String paramString1, String paramString2)
  {
    if ((PushService.b) && (a(5)))
      Log.w(z[0], "[" + paramString1 + z[1] + paramString2);
  }

  public static void e()
  {
  }

  public static void e(String paramString1, String paramString2)
  {
    if ((PushService.b) && (a(6)))
      Log.e(z[0], "[" + paramString1 + z[1] + paramString2);
  }

  public static void f()
  {
  }

  public static void g()
  {
  }

  public static void h()
  {
  }

  public static void i()
  {
  }

  public static void j()
  {
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.util.x
 * JD-Core Version:    0.6.2
 */