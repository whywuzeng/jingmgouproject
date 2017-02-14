package cn.jpush.android;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.Bundle;
import cn.jpush.android.api.k;
import cn.jpush.android.util.ab;
import cn.jpush.android.util.ai;
import cn.jpush.android.util.x;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

public final class a
{
  public static int a;
  public static String b;
  public static String c;
  public static Context d;
  public static String e;
  public static String f;
  public static int g;
  public static String h;
  public static boolean i;
  public static boolean j;
  public static boolean k;
  public static boolean l;
  private static AtomicBoolean m;
  private static final String[] z;

  static
  {
    String[] arrayOfString = new String[19];
    int i1 = 0;
    Object localObject2 = "";
    int n = -1;
    Object localObject1 = arrayOfString;
    char[] arrayOfChar = ((String)localObject2).toCharArray();
    int i2 = arrayOfChar.length;
    int i5 = 0;
    int i3 = 0;
    int i7 = n;
    localObject2 = arrayOfChar;
    int i8 = i1;
    Object localObject3 = localObject1;
    int i4 = i2;
    Object localObject4;
    int i6;
    if (i2 <= 1)
    {
      localObject4 = localObject1;
      localObject1 = arrayOfChar;
      i6 = n;
      label68: i4 = i3;
      label71: localObject2 = localObject1;
      i5 = localObject2[i3];
      switch (i4 % 5)
      {
      default:
        n = 75;
      case 0:
      case 1:
      case 2:
      case 3:
      }
    }
    while (true)
    {
      localObject2[i3] = ((char)(n ^ i5));
      i4 += 1;
      if (i2 == 0)
      {
        i3 = i2;
        break label71;
      }
      i5 = i4;
      i4 = i2;
      localObject3 = localObject4;
      i8 = i1;
      localObject2 = localObject1;
      i7 = i6;
      i6 = i7;
      localObject1 = localObject2;
      i1 = i8;
      localObject4 = localObject3;
      i2 = i4;
      i3 = i5;
      if (i4 > i5)
        break label68;
      localObject1 = new String((char[])localObject2).intern();
      switch (i7)
      {
      default:
        localObject3[i8] = localObject1;
        i1 = 1;
        localObject2 = "";
        n = 0;
        localObject1 = arrayOfString;
        break;
      case 0:
        localObject3[i8] = localObject1;
        i1 = 2;
        localObject2 = "\fzuu.A)@9,E.\005`$U(\005X;P1@`kF(JtkP5Wm*L{";
        n = 1;
        localObject1 = arrayOfString;
        break;
      case 1:
        localObject3[i8] = localObject1;
        i1 = 3;
        localObject2 = "M?Qx/A.D#kA*UR.Yz\b9";
        n = 2;
        localObject1 = arrayOfString;
        break;
      case 2:
        localObject3[i8] = localObject1;
        i1 = 4;
        localObject2 = "s?Wo\"C?";
        n = 3;
        localObject1 = arrayOfString;
        break;
      case 3:
        localObject3[i8] = localObject1;
        i1 = 5;
        localObject2 = "J;SxeN?Q7;R?C|9i\nS-\030T;Fr";
        n = 4;
        localObject1 = arrayOfString;
        break;
      case 4:
        localObject3[i8] = localObject1;
        i1 = 6;
        localObject2 = "M?Qx/A.D#kc;K9%O.\005~.Tzdi;L3Fx?I5KP%F5";
        n = 5;
        localObject1 = arrayOfString;
        break;
      case 5:
        localObject3[i8] = localObject1;
        i1 = 7;
        localObject2 = "M?Qx/A.D#kC2Dw%E6\0054k";
        n = 6;
        localObject1 = arrayOfString;
        break;
      case 6:
        localObject3[i8] = localObject1;
        i1 = 8;
        localObject2 = "j\npJ\003\031mX\005n\037i";
        n = 7;
        localObject1 = arrayOfString;
        break;
      case 7:
        localObject3[i8] = localObject1;
        i1 = 9;
        localObject2 = "";
        n = 8;
        localObject1 = arrayOfString;
        break;
      case 8:
        localObject3[i8] = localObject1;
        i1 = 10;
        localObject2 = "i4Sx'I>\005x;P\021@`k\032z";
        n = 9;
        localObject1 = arrayOfString;
        break;
      case 9:
        localObject3[i8] = localObject1;
        i1 = 11;
        localObject2 = "";
        n = 10;
        localObject1 = arrayOfString;
        break;
      case 10:
        localObject3[i8] = localObject1;
        i1 = 12;
        localObject2 = "M?Qx/A.D#kC2Dw%E6\0054kN5Q9/E<Lw.DzLwkM;Kp-E)Q";
        n = 11;
        localObject1 = arrayOfString;
        break;
      case 11:
        localObject3[i8] = localObject1;
        i1 = 13;
        localObject2 = "F;Ij.";
        n = 12;
        localObject1 = arrayOfString;
        break;
      case 12:
        localObject3[i8] = localObject1;
        i1 = 14;
        localObject2 = "";
        n = 13;
        localObject1 = arrayOfString;
        break;
      case 13:
        localObject3[i8] = localObject1;
        i1 = 15;
        localObject2 = "";
        n = 14;
        localObject1 = arrayOfString;
        break;
      case 14:
        localObject3[i8] = localObject1;
        i1 = 16;
        localObject2 = "";
        n = 15;
        localObject1 = arrayOfString;
        break;
      case 15:
        localObject3[i8] = localObject1;
        i1 = 17;
        localObject2 = "T(P|";
        n = 16;
        localObject1 = arrayOfString;
        break;
      case 16:
        localObject3[i8] = localObject1;
        i1 = 18;
        localObject2 = "J;SxeN?Q7;R?C|9i\nS/\nD>W|8S?V";
        n = 17;
        localObject1 = arrayOfString;
        break;
      case 17:
        localObject3[i8] = localObject1;
        z = arrayOfString;
        m = new AtomicBoolean(false);
        i = false;
        j = false;
        k = false;
        l = false;
        return;
        n = 32;
        continue;
        n = 90;
        continue;
        n = 37;
        continue;
        n = 25;
      }
    }
  }

  public static boolean a(Context paramContext)
  {
    if (m.get())
      return true;
    x.c();
    ab.a(paramContext.getApplicationContext());
    Object localObject1 = b(paramContext);
    if (localObject1 == null)
    {
      x.e(z[4], z[16]);
      return false;
    }
    try
    {
      Object localObject2 = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0);
      g = ((PackageInfo)localObject2).versionCode;
      localObject2 = ((PackageInfo)localObject2).versionName;
      h = (String)localObject2;
      if (((String)localObject2).length() > 30)
        h = h.substring(0, 30);
      d = paramContext.getApplicationContext();
      if ((Build.VERSION.SDK_INT >= 14) && ((paramContext instanceof Application)))
      {
        boolean bool = cn.jpush.android.util.a.h(paramContext);
        k.a = bool;
        if (bool)
          k.a((Application)paramContext.getApplicationContext());
      }
      b = paramContext.getPackageName();
      a = ((ApplicationInfo)localObject1).icon;
      if (a == 0)
        x.e(z[4], z[14]);
      c = paramContext.getPackageManager().getApplicationLabel((ApplicationInfo)localObject1).toString();
    }
    catch (Exception localException)
    {
      try
      {
        paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
        if (paramContext != null);
        for (paramContext = paramContext.metaData; ; paramContext = null)
        {
          if (paramContext == null)
            break label520;
          localObject1 = paramContext.getString(z[1]);
          f = (String)localObject1;
          if (!ai.a((String)localObject1))
            break label311;
          x.e(z[4], z[9]);
          return false;
          localException = localException;
          x.b(z[4], z[15]);
          break;
          x.b(z[4], z[6]);
        }
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        while (true)
        {
          x.a(z[4], z[0], paramContext);
          paramContext = null;
        }
        label311: if (f.length() != 24)
        {
          x.e(z[4], z[10] + f + z[2]);
          return false;
        }
        f = f.toLowerCase(Locale.getDefault());
        x.b(z[4], z[3] + f);
        paramContext = paramContext.getString(z[8]);
        e = paramContext;
        if (!ai.a(paramContext))
          break label487;
      }
    }
    x.b(z[4], z[12]);
    while (true)
    {
      if (Build.VERSION.SDK_INT == 8)
      {
        System.setProperty(z[5], z[17]);
        System.setProperty(z[18], z[13]);
      }
      m.set(true);
      return true;
      label487: x.b(z[4], z[7] + e);
      continue;
      label520: x.b(z[4], z[11]);
    }
  }

  private static ApplicationInfo b(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 0);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      x.b(z[4], z[0], paramContext);
    }
    return null;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.a
 * JD-Core Version:    0.6.2
 */