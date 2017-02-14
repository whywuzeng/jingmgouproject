package cn.jpush.android.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import cn.jpush.android.api.TagAliasCallback;
import cn.jpush.android.api.b;
import cn.jpush.android.api.m;
import cn.jpush.android.data.d;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.ae;
import cn.jpush.android.util.ai;
import cn.jpush.android.util.x;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ServiceInterface
{
  public static long a;
  public static String b = (String)localObject1;
  public static ConcurrentHashMap<Integer, b> d = new ConcurrentHashMap();
  private static String e;
  private static int f;
  private static String g;
  private static int h;
  private static boolean i = false;
  private static final String[] z;
  WeakHashMap<Integer, TagAliasCallback> c = new WeakHashMap();

  static
  {
    Object localObject2 = new String[51];
    int k = 0;
    Object localObject3 = "P5Jm)a/Y$9a}F>zw)@=*a9\003m3p}X$6h}H$,a}Z=ze1Cm.l8\017,9p4@#)$(A93h}V\"/$>N!6$/J>/i88)l}B(.l2Km.k}]()q0Jm.l8\017>?v+F.?*";
    int j = -1;
    Object localObject1 = localObject2;
    int m;
    int i2;
    int n;
    int i5;
    Object localObject4;
    int i1;
    int i3;
    while (true)
    {
      char[] arrayOfChar = ((String)localObject3).toCharArray();
      m = arrayOfChar.length;
      i2 = 0;
      n = 0;
      int i4 = j;
      localObject3 = arrayOfChar;
      i5 = k;
      localObject4 = localObject1;
      i1 = m;
      Object localObject5;
      if (m <= 1)
      {
        localObject5 = localObject1;
        localObject1 = arrayOfChar;
        i3 = j;
      }
      label143: 
      do
      {
        i1 = n;
        while (true)
        {
          localObject3 = localObject1;
          i2 = localObject3[n];
          switch (i1 % 5)
          {
          default:
            j = 90;
            localObject3[n] = ((char)(j ^ i2));
            i1 += 1;
            if (m != 0)
              break label143;
            n = m;
          case 0:
          case 1:
          case 2:
          case 3:
          }
        }
        i2 = i1;
        i1 = m;
        localObject4 = localObject5;
        i5 = k;
        localObject3 = localObject1;
        i4 = i3;
        i3 = i4;
        localObject1 = localObject3;
        k = i5;
        localObject5 = localObject4;
        m = i1;
        n = i2;
      }
      while (i1 > i2);
      localObject1 = new String((char[])localObject3).intern();
      switch (i4)
      {
      default:
        localObject4[i5] = localObject1;
        k = 1;
        localObject3 = "W8];3g8f#.a/I,9a";
        j = 0;
        localObject1 = localObject2;
        break;
      case 0:
        localObject4[i5] = localObject1;
        k = 2;
        localObject3 = "(}L\">ag\017";
        j = 1;
        localObject1 = localObject2;
        break;
      case 1:
        localObject4[i5] = localObject1;
        k = 3;
        localObject3 = "i.H\022)p<[8)";
        j = 2;
        localObject1 = localObject2;
        break;
      case 2:
        localObject4[i5] = localObject1;
        k = 4;
        localObject3 = "v8\\86p";
        j = 3;
        localObject1 = localObject2;
        break;
      case 3:
        localObject4[i5] = localObject1;
        k = 5;
        localObject3 = "`<[,";
        j = 4;
        localObject1 = localObject2;
        break;
      case 4:
        localObject4[i5] = localObject1;
        k = 6;
        localObject3 = "m)F ?";
        j = 5;
        localObject1 = localObject2;
        break;
      case 5:
        localObject4[i5] = localObject1;
        k = 7;
        localObject3 = "$/J=5v)\017.5j)J#.>}";
        j = 6;
        localObject1 = localObject2;
        break;
      case 6:
        localObject4[i5] = localObject1;
        k = 8;
        localObject3 = "p$_(";
        j = 7;
        localObject1 = localObject2;
        break;
      case 7:
        localObject4[i5] = localObject1;
        k = 9;
        localObject3 = "i.H\0223`";
        j = 8;
        localObject1 = localObject2;
        break;
      case 8:
        localObject4[i5] = localObject1;
        k = 10;
        localObject3 = "e>[$5jg](*k/[\f9p4@#\ba.Z!.$p\017 ?w.N*?M9\025m";
        j = 9;
        localObject1 = localObject2;
        break;
      case 9:
        localObject4[i5] = localObject1;
        k = 11;
        localObject3 = "5s\027ch";
        j = 10;
        localObject1 = localObject2;
        break;
      case 10:
        localObject4[i5] = localObject1;
        k = 12;
        localObject3 = "w)N?.I4A>";
        j = 11;
        localObject1 = localObject2;
        break;
      case 11:
        localObject4[i5] = localObject1;
        k = 13;
        localObject3 = "w)N?.L2Z?";
        j = 12;
        localObject1 = localObject2;
        break;
      case 12:
        localObject4[i5] = localObject1;
        k = 14;
        localObject3 = "a3K9\027m3\\";
        j = 13;
        localObject1 = localObject2;
        break;
      case 13:
        localObject4[i5] = localObject1;
        k = 15;
        localObject3 = "a3K\0055q/";
        j = 14;
        localObject1 = localObject2;
        break;
      case 14:
        localObject4[i5] = localObject1;
        k = 16;
        localObject3 = "v8\\9;v)p?.g";
        j = 15;
        localObject1 = localObject2;
        break;
      case 15:
        localObject4[i5] = localObject1;
        k = 17;
        localObject3 = "b<C>?";
        j = 16;
        localObject1 = localObject2;
        break;
      case 16:
        localObject4[i5] = localObject1;
        k = 18;
        localObject3 = "m.}()p<]9\bp>";
        j = 17;
        localObject1 = localObject2;
        break;
      case 17:
        localObject4[i5] = localObject1;
        k = 19;
        localObject3 = "p/Z(";
        j = 18;
        localObject1 = localObject2;
        break;
      case 18:
        localObject4[i5] = localObject1;
        k = 20;
        localObject3 = "w8];3g8p>.k-J)";
        j = 19;
        localObject1 = localObject2;
        break;
      case 19:
        localObject4[i5] = localObject1;
        k = 21;
        localObject3 = "v8_\0223`";
        j = 20;
        localObject1 = localObject2;
        break;
      case 20:
        localObject4[i5] = localObject1;
        k = 22;
        localObject3 = "W8A)zi8\\>=a}I?5i}k\017`$";
        j = 21;
        localObject1 = localObject2;
        break;
      case 21:
        localObject4[i5] = localObject1;
        k = 23;
        localObject3 = "v8_\022*v8I$\"";
        j = 22;
        localObject1 = localObject2;
        break;
      case 22:
        localObject4[i5] = localObject1;
        k = 24;
        localObject3 = "v8_\022>e)N";
        j = 23;
        localObject1 = localObject2;
        break;
      case 23:
        localObject4[i5] = localObject1;
        k = 25;
        localObject3 = "C8[m7a.\\*?$;]\"7$\031mazp5Jm9k(A9zm.\025m";
        j = 24;
        localObject1 = localObject2;
        break;
      case 24:
        localObject4[i5] = localObject1;
        k = 26;
        localObject3 = "e-_";
        j = 25;
        localObject1 = localObject2;
        break;
      case 25:
        localObject4[i5] = localObject1;
        k = 27;
        localObject3 = "g3\001'*q.Gc;j9]\"3`sF#.a3[c\bA\016{\002\bA\rz\036\022";
        j = 26;
        localObject1 = localObject2;
        break;
      case 26:
        localObject4[i5] = localObject1;
        k = 28;
        localObject3 = "w8];3g8\017$)$/Z#4m3Hm;h/J,>}";
        j = 27;
        localObject1 = localObject2;
        break;
      case 27:
        localObject4[i5] = localObject1;
        k = 29;
        localObject3 = "f2K4";
        j = 28;
        localObject1 = localObject2;
        break;
      case 28:
        localObject4[i5] = localObject1;
        k = 30;
        localObject3 = "j2[$<e>[$5j\002A87";
        j = 29;
        localObject1 = localObject2;
        break;
      case 29:
        localObject4[i5] = localObject1;
        k = 31;
        localObject3 = "w8[m4k)F+3g<[$5j}B,\"$3Z `$";
        j = 30;
        localObject1 = localObject2;
        break;
      case 30:
        localObject4[i5] = localObject1;
        k = 32;
        localObject3 = "w)@=\005p5](;`";
        j = 31;
        localObject1 = localObject2;
        break;
      case 31:
        localObject4[i5] = localObject1;
        k = 33;
        localObject3 = "E-_!3w)\017wz";
        j = 32;
        localObject1 = localObject2;
        break;
      case 32:
        localObject4[i5] = localObject1;
        k = 34;
        localObject3 = "E\r!3w)\017!?j:[%`$";
        j = 33;
        localObject1 = localObject2;
        break;
      case 33:
        localObject4[i5] = localObject1;
        k = 35;
        localObject3 = "e-_\0226m.[";
        j = 34;
        localObject1 = localObject2;
        break;
      case 34:
        localObject4[i5] = localObject1;
        k = 36;
        localObject3 = "g3\001'*q.Gc;j9]\"3`sF#.a3[c\023J\024{";
        j = 35;
        localObject1 = localObject2;
        break;
      case 35:
        localObject4[i5] = localObject1;
        k = 37;
        localObject3 = "g3\001'*q.Gc;j9]\"3`sF#.a3[c\nQ\016g\022\016M\020j";
        j = 36;
        localObject1 = localObject2;
        break;
      case 36:
        localObject4[i5] = localObject1;
        k = 38;
        localObject3 = "t(\\%.m0J";
        j = 37;
        localObject1 = localObject2;
        break;
      case 37:
        localObject4[i5] = localObject1;
        k = 39;
        localObject3 = "g1@>?t(\\%";
        j = 38;
        localObject1 = localObject2;
        break;
      case 38:
        localObject4[i5] = localObject1;
        k = 40;
        localObject3 = "v)L";
        j = 39;
        localObject1 = localObject2;
        break;
      case 39:
        localObject4[i5] = localObject1;
        k = 41;
        localObject3 = "v)L\022>a1N4";
        j = 40;
        localObject1 = localObject2;
        break;
      case 40:
        localObject4[i5] = localObject1;
        k = 42;
        localObject3 = "w8];3g8\017,6v8N)#$.[\"*";
        j = 41;
        localObject1 = localObject2;
        break;
      case 41:
        localObject4[i5] = localObject1;
        k = 43;
        localObject3 = "g3\001'*q.Gc;j9]\"3`sF#.a3[c\tP\022\035\017W\025";
        j = 42;
        localObject1 = localObject2;
        break;
      case 42:
        localObject4[i5] = localObject1;
        k = 44;
        localObject3 = "p<H>";
        j = 43;
        localObject1 = localObject2;
        break;
      case 43:
        localObject4[i5] = localObject1;
        k = 45;
        localObject3 = "w8^\0223`";
        j = 44;
        localObject1 = localObject2;
        break;
      case 44:
        localObject4[i5] = localObject1;
        k = 46;
        localObject3 = "g3\001'*q.Gc;j9]\"3`sF#.a3[c\033H\024n\036\005P\034h\036";
        j = 45;
        localObject1 = localObject2;
        break;
      case 45:
        localObject4[i5] = localObject1;
        k = 47;
        localObject3 = "e1F,)";
        j = 46;
        localObject1 = localObject2;
        break;
      case 46:
        localObject4[i5] = localObject1;
        k = 48;
        localObject3 = "e>[$5jg](*k/[\002*a/N93k3\017`zg2A9?j)\025";
        j = 47;
        localObject1 = localObject2;
        break;
      case 47:
        localObject4[i5] = localObject1;
        k = 49;
        localObject3 = "J\bc\001zg2A9?|)";
        j = 48;
        localObject1 = localObject2;
        break;
      case 48:
        localObject4[i5] = localObject1;
        k = 50;
        localObject3 = "w4C(4g88)l\tF ?";
        j = 49;
        localObject1 = localObject2;
      case 49:
      }
    }
    localObject4[i5] = localObject1;
    z = (String[])localObject2;
    a = 0L;
    j = -1;
    localObject1 = "5l\030ck7h\001|l4s\032";
    while (true)
    {
      localObject1 = ((String)localObject1).toCharArray();
      k = localObject1.length;
      n = 0;
      i3 = 0;
      localObject2 = localObject1;
      m = k;
      i2 = j;
      if (k <= 1)
      {
        i1 = j;
        j = i3;
      }
      label1675: label1791: 
      do
      {
        n = j;
        m = j;
        localObject2 = localObject1;
        i2 = localObject2[m];
        switch (n % 5)
        {
        default:
          j = 90;
        case 0:
        case 1:
        case 2:
        case 3:
        }
        while (true)
        {
          localObject2[m] = ((char)(j ^ i2));
          n += 1;
          if (k != 0)
            break label1791;
          m = k;
          break label1675;
          j = 4;
          break;
          j = 93;
          break;
          j = 47;
          break;
          j = 77;
          break;
          j = 4;
          continue;
          j = 93;
          continue;
          j = 47;
          continue;
          j = 77;
        }
        i2 = i1;
        m = k;
        localObject2 = localObject1;
        localObject1 = localObject2;
        k = m;
        j = n;
        i1 = i2;
      }
      while (m > n);
      localObject1 = new String((char[])localObject2).intern();
      switch (i2)
      {
      default:
        e = (String)localObject1;
        f = 9001;
        j = 0;
        localObject1 = "5l\030ck7h\001|l4s\032";
        break;
      case 0:
        g = (String)localObject1;
        h = 9002;
        j = 1;
        localObject1 = "l)[=`+r\\9;p.\001'*q.Gc9j";
      case 1:
      }
    }
  }

  static b a(int paramInt)
  {
    return (b)d.get(Integer.valueOf(paramInt));
  }

  public static void a(Context paramContext)
  {
    if (j(paramContext));
    while (ae.a(paramContext, z[18], z[19]).equals(z[17]))
      return;
    x.c();
    Intent localIntent = new Intent(paramContext, PushService.class);
    if (cn.jpush.android.a.a(paramContext))
    {
      localIntent.setAction(z[36]);
      localIntent.putExtra(z[26], paramContext.getPackageName());
      paramContext.startService(localIntent);
      return;
    }
    x.e();
  }

  public static void a(Context paramContext, int paramInt)
  {
    paramInt = ae.a(paramContext, z[20], -1);
    if (1 == paramInt)
    {
      x.b(z[1], z[42]);
      return;
    }
    if ((1 < paramInt) && (paramInt > 0))
      boolean bool = i;
    ae.b(paramContext, z[20], 1);
    PushService.b(false);
    ae.b(paramContext, z[18], z[17]);
    Intent localIntent = new Intent(paramContext, PushService.class);
    localIntent.setAction(z[43]);
    localIntent.putExtra(z[26], paramContext.getPackageName());
    paramContext.startService(localIntent);
    a(paramContext, false);
  }

  public static void a(Context paramContext, d paramd)
  {
    x.b();
    Intent localIntent = new Intent(paramContext, DownloadService.class);
    localIntent.putExtra(z[29], paramd);
    paramContext.startService(localIntent);
  }

  public static void a(Context paramContext, String paramString)
  {
    if (ae.a(paramContext, z[18], z[19]).equals(z[17]))
      return;
    ae.b(paramContext, z[50], paramString);
  }

  public static void a(Context paramContext, String paramString1, String paramString2, b paramb)
  {
    if (j(paramContext));
    while (ae.a(paramContext, z[18], z[19]).equals(z[17]))
      return;
    int k = 0;
    int j = k;
    if (paramb != null)
    {
      j = k;
      if (paramb.c != null)
      {
        j = cn.jpush.android.util.a.c(paramContext);
        d.put(Integer.valueOf(j), paramb);
      }
    }
    paramb = new Intent(paramContext, PushService.class);
    paramb.setAction(z[46]);
    paramb.putExtra(z[47], paramString1);
    paramb.putExtra(z[44], paramString2);
    paramb.putExtra(z[45], j);
    paramContext.startService(paramb);
  }

  public static void a(Context paramContext, JSONObject paramJSONObject)
  {
    if (!cn.jpush.android.a.j);
    do
    {
      return;
      if (paramContext == null)
        throw new IllegalArgumentException(z[49]);
    }
    while ((paramJSONObject == null) || (paramJSONObject.length() <= 0));
    ac.a(paramContext, paramJSONObject);
    new StringBuilder(z[48]).append(paramJSONObject.toString()).toString();
    x.c();
  }

  private static void a(Context paramContext, boolean paramBoolean)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    ComponentName localComponentName = new ComponentName(paramContext.getApplicationContext(), PushReceiver.class);
    paramContext = new ComponentName(paramContext.getApplicationContext(), AlarmReceiver.class);
    if (!paramBoolean)
    {
      localPackageManager.setComponentEnabledSetting(paramContext, 2, 1);
      return;
    }
    localPackageManager.setComponentEnabledSetting(localComponentName, 1, 1);
    localPackageManager.setComponentEnabledSetting(paramContext, 1, 1);
  }

  public static void a(Context paramContext, boolean paramBoolean, String paramString)
  {
    if (ae.a(paramContext, z[18], z[19]).equals(z[17]))
      return;
    Intent localIntent = new Intent(paramContext, PushService.class);
    localIntent.setAction(z[37]);
    localIntent.putExtra(z[39], paramBoolean);
    localIntent.putExtra(z[38], paramString);
    paramContext.startService(localIntent);
  }

  public static void a(String paramString)
  {
    e = paramString;
  }

  public static void a(String paramString, int paramInt, Context paramContext)
  {
    a(paramString, paramInt, null, paramContext);
  }

  public static void a(String paramString1, int paramInt, String paramString2, Context paramContext)
  {
    if (!cn.jpush.android.a.j)
      return;
    if (paramContext == null)
    {
      x.c();
      return;
    }
    Object localObject = new StringBuffer();
    ((StringBuffer)localObject).append(z[10] + paramString1 + z[2] + paramInt + "-" + r.b(paramInt));
    if (!ai.a(paramString2))
      ((StringBuffer)localObject).append(z[7] + paramString2);
    ((StringBuffer)localObject).toString();
    x.c();
    localObject = new JSONObject();
    try
    {
      ((JSONObject)localObject).put(z[9], paramString1);
      ((JSONObject)localObject).put(z[4], paramInt);
      if (!ai.a(paramString2))
        ((JSONObject)localObject).put(z[5], paramString2);
      ((JSONObject)localObject).put(z[6], PushService.m + Math.abs(System.currentTimeMillis() / 1000L - PushService.n));
      ((JSONObject)localObject).put(z[8], z[3]);
      ac.a(paramContext, (JSONObject)localObject);
      return;
    }
    catch (JSONException paramString1)
    {
    }
  }

  public static boolean a()
  {
    return PushProtocol.GetSdkVersion() >= 160;
  }

  public static boolean a(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put(z[13], paramInt1);
      localJSONObject.put(z[12], paramInt2);
      localJSONObject.put(z[15], paramInt3);
      localJSONObject.put(z[14], paramInt4);
      a(paramContext, localJSONObject.toString());
      return true;
    }
    catch (JSONException paramContext)
    {
    }
    return false;
  }

  public static String b()
  {
    return z[11];
  }

  static void b(int paramInt)
  {
    d.remove(Integer.valueOf(paramInt));
  }

  public static void b(Context paramContext)
  {
    if (ae.a(paramContext, z[18], z[19]).equals(z[17]))
      return;
    Intent localIntent = new Intent(paramContext, PushService.class);
    localIntent.setAction(z[32]);
    localIntent.putExtra(z[26], paramContext.getPackageName());
    paramContext.startService(localIntent);
  }

  public static void b(Context paramContext, int paramInt)
  {
    paramInt = ae.a(paramContext, z[20], -1);
    if (paramInt == 0)
    {
      x.b(z[1], z[28]);
      return;
    }
    if ((1 < paramInt) && (paramInt > 0))
      boolean bool = i;
    a(paramContext, true);
    PushService.b(true);
    ae.b(paramContext, z[18], z[19]);
    ae.b(paramContext, z[20], 0);
    Intent localIntent = new Intent(paramContext, PushService.class);
    localIntent.setAction(z[27]);
    localIntent.putExtra(z[26], paramContext.getPackageName());
    paramContext.startService(localIntent);
  }

  public static void b(String paramString)
  {
    g = paramString;
  }

  public static void c(int paramInt)
  {
    f = paramInt;
  }

  public static void c(Context paramContext)
  {
    if (ae.a(paramContext, z[18], z[19]).equals(z[17]))
      return;
    Intent localIntent = new Intent(paramContext, PushService.class);
    Bundle localBundle = new Bundle();
    localBundle.putString(z[40], z[40]);
    localIntent.putExtras(localBundle);
    paramContext.startService(localIntent);
  }

  public static void c(Context paramContext, int paramInt)
  {
    if (ae.a(paramContext, z[18], z[19]).equals(z[17]))
      return;
    Intent localIntent = new Intent(paramContext, PushService.class);
    Bundle localBundle = new Bundle();
    localBundle.putString(z[40], z[40]);
    localBundle.putInt(z[41], paramInt);
    localIntent.putExtras(localBundle);
    paramContext.startService(localIntent);
  }

  public static void d(int paramInt)
  {
    h = paramInt;
  }

  public static void d(Context paramContext)
  {
    if (ae.a(paramContext, z[18], z[19]).equals(z[17]))
      return;
    x.b();
    if (PushService.a())
    {
      Intent localIntent = new Intent(paramContext, PushService.class);
      localIntent.setAction(z[16]);
      paramContext.startService(localIntent);
      return;
    }
    x.d();
  }

  public static void d(Context paramContext, int paramInt)
  {
    if (paramInt < ae.a(paramContext, z[30], -1))
      m.a(paramContext, paramInt);
    ae.b(paramContext, z[30], paramInt);
    new StringBuilder(z[31]).append(ae.a(paramContext, z[30], 5)).toString();
    x.b();
  }

  public static void e(Context paramContext)
  {
    if (!cn.jpush.android.a.j);
    while (true)
    {
      return;
      if (paramContext == null)
      {
        x.c();
        return;
      }
      JSONArray localJSONArray = cn.jpush.android.util.a.m(paramContext);
      if ((localJSONArray == null) || (localJSONArray.length() == 0))
        continue;
      Object localObject = new JSONArray();
      int k = localJSONArray.length();
      int j = 0;
      label50: if (j < k);
      try
      {
        ((JSONArray)localObject).put(localJSONArray.getJSONObject(j));
        label66: if ((((JSONArray)localObject).toString().length() > 15360) || (j == k - 1))
        {
          localObject = cn.jpush.android.util.a.a(z[35], localJSONArray);
          if ((localObject != null) && (((JSONObject)localObject).length() > 0))
            ac.a(paramContext, (JSONObject)localObject);
          localObject = new JSONArray();
        }
        j += 1;
        break label50;
        localObject = cn.jpush.android.util.a.a(z[35], localJSONArray);
        if ((localObject == null) || (((JSONObject)localObject).length() <= 0))
          continue;
        ac.a(paramContext, (JSONObject)localObject);
        new StringBuilder(z[34]).append(((JSONObject)localObject).toString().getBytes().length).toString();
        x.d();
        new StringBuilder(z[33]).append(localObject).toString();
        x.d();
        return;
      }
      catch (JSONException localJSONException)
      {
        break label66;
      }
    }
  }

  // ERROR //
  public static void f(Context paramContext)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aload_0
    //   3: invokestatic 459	cn/jpush/android/data/r:a	(Landroid/content/Context;)Landroid/database/Cursor;
    //   6: astore_2
    //   7: aload_2
    //   8: ifnull +213 -> 221
    //   11: aload_2
    //   12: astore_3
    //   13: aload_2
    //   14: invokeinterface 464 1 0
    //   19: ifle +202 -> 221
    //   22: aload_2
    //   23: astore_3
    //   24: new 299	java/lang/StringBuilder
    //   27: dup
    //   28: getstatic 143	cn/jpush/android/service/ServiceInterface:z	[Ljava/lang/String;
    //   31: bipush 25
    //   33: aaload
    //   34: invokespecial 300	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   37: aload_2
    //   38: invokeinterface 464 1 0
    //   43: invokevirtual 345	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   46: invokevirtual 308	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   49: pop
    //   50: aload_2
    //   51: astore_3
    //   52: invokestatic 422	cn/jpush/android/util/x:d	()V
    //   55: aload_2
    //   56: astore_3
    //   57: aload_2
    //   58: invokeinterface 467 1 0
    //   63: pop
    //   64: aload_2
    //   65: astore_3
    //   66: aload_2
    //   67: invokeinterface 470 1 0
    //   72: ifne +149 -> 221
    //   75: aload_2
    //   76: astore_3
    //   77: aload_2
    //   78: aload_2
    //   79: getstatic 143	cn/jpush/android/service/ServiceInterface:z	[Ljava/lang/String;
    //   82: bipush 21
    //   84: aaload
    //   85: invokeinterface 474 2 0
    //   90: invokeinterface 478 2 0
    //   95: istore_1
    //   96: aload_2
    //   97: astore_3
    //   98: aload_2
    //   99: aload_2
    //   100: getstatic 143	cn/jpush/android/service/ServiceInterface:z	[Ljava/lang/String;
    //   103: bipush 24
    //   105: aaload
    //   106: invokeinterface 474 2 0
    //   111: invokeinterface 481 2 0
    //   116: astore 4
    //   118: aload_2
    //   119: astore_3
    //   120: new 483	java/lang/Thread
    //   123: dup
    //   124: new 485	cn/jpush/android/service/q
    //   127: dup
    //   128: aload_2
    //   129: aload_2
    //   130: getstatic 143	cn/jpush/android/service/ServiceInterface:z	[Ljava/lang/String;
    //   133: bipush 23
    //   135: aaload
    //   136: invokeinterface 474 2 0
    //   141: invokeinterface 481 2 0
    //   146: aload 4
    //   148: invokevirtual 449	java/lang/String:getBytes	()[B
    //   151: invokespecial 488	cn/jpush/android/service/q:<init>	(Ljava/lang/String;[B)V
    //   154: invokespecial 491	java/lang/Thread:<init>	(Ljava/lang/Runnable;)V
    //   157: invokevirtual 494	java/lang/Thread:start	()V
    //   160: aload_2
    //   161: astore_3
    //   162: new 299	java/lang/StringBuilder
    //   165: dup
    //   166: getstatic 143	cn/jpush/android/service/ServiceInterface:z	[Ljava/lang/String;
    //   169: bipush 22
    //   171: aaload
    //   172: invokespecial 300	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   175: aload 4
    //   177: invokevirtual 307	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   180: invokevirtual 308	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   183: pop
    //   184: aload_2
    //   185: astore_3
    //   186: invokestatic 422	cn/jpush/android/util/x:d	()V
    //   189: aload_2
    //   190: astore_3
    //   191: aload_0
    //   192: iload_1
    //   193: invokestatic 497	cn/jpush/android/data/r:a	(Landroid/content/Context;I)Z
    //   196: pop
    //   197: aload_2
    //   198: astore_3
    //   199: aload_2
    //   200: invokeinterface 500 1 0
    //   205: pop
    //   206: goto -142 -> 64
    //   209: astore_0
    //   210: aload_3
    //   211: ifnull +9 -> 220
    //   214: aload_3
    //   215: invokeinterface 503 1 0
    //   220: return
    //   221: aload_2
    //   222: ifnull -2 -> 220
    //   225: aload_2
    //   226: invokeinterface 503 1 0
    //   231: return
    //   232: astore_0
    //   233: aconst_null
    //   234: astore_2
    //   235: aload_2
    //   236: ifnull +9 -> 245
    //   239: aload_2
    //   240: invokeinterface 503 1 0
    //   245: aload_0
    //   246: athrow
    //   247: astore_0
    //   248: goto -13 -> 235
    //
    // Exception table:
    //   from	to	target	type
    //   2	7	209	java/lang/Exception
    //   13	22	209	java/lang/Exception
    //   24	50	209	java/lang/Exception
    //   52	55	209	java/lang/Exception
    //   57	64	209	java/lang/Exception
    //   66	75	209	java/lang/Exception
    //   77	96	209	java/lang/Exception
    //   98	118	209	java/lang/Exception
    //   120	160	209	java/lang/Exception
    //   162	184	209	java/lang/Exception
    //   186	189	209	java/lang/Exception
    //   191	197	209	java/lang/Exception
    //   199	206	209	java/lang/Exception
    //   2	7	232	finally
    //   13	22	247	finally
    //   24	50	247	finally
    //   52	55	247	finally
    //   57	64	247	finally
    //   66	75	247	finally
    //   77	96	247	finally
    //   98	118	247	finally
    //   120	160	247	finally
    //   162	184	247	finally
    //   186	189	247	finally
    //   191	197	247	finally
    //   199	206	247	finally
  }

  public static void g(Context paramContext)
  {
    m.a(paramContext);
  }

  public static boolean h(Context paramContext)
  {
    boolean bool = false;
    if (ae.a(paramContext, z[20], 0) > 0)
      bool = true;
    return bool;
  }

  public static int i(Context paramContext)
  {
    return ae.a(paramContext, z[20], -1);
  }

  public static boolean j(Context paramContext)
  {
    boolean bool = h(paramContext);
    if (bool)
      x.d(z[1], z[0]);
    return bool;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.service.ServiceInterface
 * JD-Core Version:    0.6.2
 */