package org.android.agoo;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import android.util.Log;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.android.Config;

public final class a
{
  public static final String A = "app_device_token_temp";
  public static final String B = "app_key_temp";
  public static final String C = "app_sercet_temp";
  public static final String D = "app_tt_id_temp";
  private static final String E = "AppStore";
  private static final String F = "app_device_token";
  private static final String G = "app_version";
  private static final String H = "app_disable_version";
  private static final String I = "agoo_mode";
  private static final String J = "agoo_start_time";
  private static final String K = "agoo_end_time";
  private static final int L = 0;
  private static final String M = "backoff_count";
  private static final String N = "app_disable";
  private static final String O = "app_election_source";
  private static final String P = "app_sudo_pack_timeout";
  private static final String Q = "app_agoo_multiplex";
  private static final String R = "app_agoo_command_uptime_time";
  private static final String S = "app_last_register_time ";
  private static final String T = "app_retry_register";
  private static final String U = "Settings";

  @Deprecated
  private static final String V = "42.120.111.1";
  private static final String W = "42.120.80.36";
  private static final String X = "110.75.120.15";
  private static final String Y = "http://api.m.taobao.com/rest/api3.do";
  private static final String Z = "http://utop.umengcloud.com/rest/api3.do";
  public static final int a = 300000;
  private static final String aa = "http://api.wapa.taobao.com/rest/api3.do";
  private static final String ab = "http://api.waptest.taobao.com/rest/api3.do";
  private static final String ac = "http://apoll.m.taobao.com";
  private static final String ad = "http://upoll.umengcloud.com";
  private static final String ae = "http://apoll.m.taobao.com";
  private static final String af = "http://apoll.m.taobao.com";
  private static final long ag = 20150515L;
  public static final int b = 120;
  public static final int c = 1320;
  public static final int d = 1800000;
  public static final long e = 10000L;
  public static final long f = 600000L;
  public static final long g = 60000L;
  public static final long h = 300000L;
  public static final long i = 20000L;
  public static final long j = 120000L;
  public static final long k = 20000L;
  public static final long l = 60000L;
  public static final long m = 30000L;
  public static final long n = 2000L;
  public static final long o = 2000L;
  public static final long p = 1000L;
  public static final long q = 20000L;
  public static final long r = 10000L;
  public static final long s = 3000L;
  public static final long t = 5000L;
  public static final long u = 1800000L;
  public static final long v = 600000L;
  public static final long w = 15000L;
  public static final long x = 5000L;
  public static final long y = 5000L;
  public static final long z = 40000L;

  // ERROR //
  public static final boolean A(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 183	org/android/agoo/a:b	(Landroid/content/Context;)Landroid/content/SharedPreferences;
    //   4: astore 8
    //   6: aload 8
    //   8: ldc 70
    //   10: iconst_1
    //   11: invokeinterface 189 3 0
    //   16: istore_3
    //   17: aload 8
    //   19: ldc 73
    //   21: ldc2_w 190
    //   24: invokeinterface 195 4 0
    //   29: lstore 4
    //   31: aload 8
    //   33: ldc 44
    //   35: ldc 196
    //   37: invokeinterface 200 3 0
    //   42: istore_1
    //   43: aload_0
    //   44: invokestatic 206	org/android/Config:getAppVersion	(Landroid/content/Context;)I
    //   47: istore_2
    //   48: iload_1
    //   49: ldc 196
    //   51: if_icmpeq +18 -> 69
    //   54: iload_1
    //   55: iload_2
    //   56: if_icmpeq +13 -> 69
    //   59: aload_0
    //   60: iconst_1
    //   61: ldc2_w 190
    //   64: invokestatic 209	org/android/agoo/a:a	(Landroid/content/Context;ZJ)V
    //   67: iconst_1
    //   68: ireturn
    //   69: lload 4
    //   71: ldc2_w 190
    //   74: lcmp
    //   75: ifeq +16 -> 91
    //   78: invokestatic 215	java/lang/System:currentTimeMillis	()J
    //   81: lstore 6
    //   83: lload 4
    //   85: lload 6
    //   87: lcmp
    //   88: ifgt +9 -> 97
    //   91: iconst_1
    //   92: ireturn
    //   93: astore_0
    //   94: iconst_1
    //   95: ireturn
    //   96: astore_0
    //   97: iload_3
    //   98: ireturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	17	93	java/lang/Throwable
    //   17	48	96	java/lang/Throwable
    //   59	67	96	java/lang/Throwable
    //   78	83	96	java/lang/Throwable
  }

  public static final String B(Context paramContext)
  {
    return Config.getPushUserToken(paramContext);
  }

  public static final void C(Context paramContext)
  {
    Config.clearPushUserToken(paramContext);
  }

  public static final long D(Context paramContext)
  {
    try
    {
      paramContext = b(paramContext);
      int i1 = paramContext.getInt("agoo_start_time", -1);
      int i2 = paramContext.getInt("agoo_end_time", -1);
      if ((i1 != -1) && (i2 != -1))
      {
        paramContext = Calendar.getInstance();
        paramContext.setTimeInMillis(System.currentTimeMillis());
        int i3 = paramContext.get(11) * 60 * 60 + paramContext.get(12) * 60 + paramContext.get(13);
        if (i3 < i1)
          paramContext.add(13, i1 - i3);
        while (true)
        {
          return paramContext.getTimeInMillis();
          if (i3 <= i2)
            break;
          paramContext.add(13, i1 - i3 + 86400);
        }
      }
    }
    catch (Throwable paramContext)
    {
    }
    return -1L;
  }

  public static final boolean E(Context paramContext)
  {
    return Config.isAgooSoSecurityMode(paramContext);
  }

  public static final boolean F(Context paramContext)
  {
    boolean bool2 = false;
    try
    {
      int i1 = b(paramContext).getInt("agoo_mode", a.d.a());
      boolean bool1 = bool2;
      if (i1 != a.d.a())
      {
        int i2 = a.e.a();
        bool1 = bool2;
        if (i1 != i2)
          bool1 = true;
      }
      return bool1;
    }
    catch (Throwable paramContext)
    {
    }
    return false;
  }

  public static final String G(Context paramContext)
  {
    return H(paramContext).e();
  }

  public static final a H(Context paramContext)
  {
    while (true)
      try
      {
        switch (b(paramContext).getInt("agoo_mode", a.e.a()))
        {
        case 1:
          return a.e;
        case -2:
          return a.a;
        case -1:
          return a.b;
        case 0:
          return a.c;
        case 2:
          paramContext = a.d;
          return paramContext;
        }
      }
      catch (Throwable paramContext)
      {
        return a.e;
      }
  }

  public static final long a()
  {
    return 20150515L;
  }

  private static final String a(long paramLong)
  {
    try
    {
      String str = new SimpleDateFormat("yyyy-MM-dd").format(Long.valueOf(paramLong));
      return str;
    }
    catch (Throwable localThrowable)
    {
    }
    return "none";
  }

  public static final String a(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
    }
    return null;
  }

  public static final void a(Context paramContext, int paramInt)
  {
    try
    {
      paramContext = b(paramContext).edit();
      paramContext.putInt("backoff_count", paramInt);
      paramContext.commit();
      return;
    }
    catch (Throwable paramContext)
    {
      Log.w("Settings", "setBackOffCount", paramContext);
    }
  }

  public static final void a(Context paramContext, long paramLong, String paramString)
  {
    try
    {
      paramContext = b(paramContext).edit();
      paramContext.putLong("app_sudo_pack_timeout", paramLong);
      paramContext.putString("app_election_source", paramString);
      paramContext.commit();
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static final void a(Context paramContext, String paramString)
  {
    Config.setXToken(paramContext, paramString);
  }

  public static final void a(Context paramContext, boolean paramBoolean, long paramLong)
  {
    try
    {
      paramContext = b(paramContext).edit();
      paramContext.putBoolean("app_agoo_multiplex", paramBoolean);
      paramContext.putLong("app_agoo_command_uptime_time", paramLong);
      paramContext.commit();
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static final void a(Context paramContext, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramContext != null);
    try
    {
      Config.setDebug(paramContext, paramBoolean1, paramBoolean2);
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  // ERROR //
  public static final boolean a(Context paramContext, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 183	org/android/agoo/a:b	(Landroid/content/Context;)Landroid/content/SharedPreferences;
    //   4: astore 5
    //   6: aload 5
    //   8: ldc 61
    //   10: iconst_0
    //   11: invokeinterface 189 3 0
    //   16: istore 4
    //   18: iload_1
    //   19: ifne +6 -> 25
    //   22: iload 4
    //   24: ireturn
    //   25: aload 5
    //   27: ldc 44
    //   29: ldc 196
    //   31: invokeinterface 200 3 0
    //   36: istore_2
    //   37: aload_0
    //   38: invokestatic 206	org/android/Config:getAppVersion	(Landroid/content/Context;)I
    //   41: istore_3
    //   42: iload_2
    //   43: ldc 196
    //   45: if_icmpeq -23 -> 22
    //   48: iload_2
    //   49: iload_3
    //   50: if_icmpeq -28 -> 22
    //   53: aload_0
    //   54: invokestatic 357	org/android/agoo/a:y	(Landroid/content/Context;)V
    //   57: aload_0
    //   58: invokestatic 359	org/android/agoo/a:e	(Landroid/content/Context;)V
    //   61: iconst_0
    //   62: ireturn
    //   63: astore_0
    //   64: iconst_0
    //   65: ireturn
    //   66: astore_0
    //   67: iload 4
    //   69: ireturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	18	63	java/lang/Throwable
    //   25	42	66	java/lang/Throwable
    //   53	61	66	java/lang/Throwable
  }

  public static final SharedPreferences b(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getSharedPreferences("AppStore", 4);
      return paramContext;
    }
    catch (Throwable paramContext)
    {
    }
    return null;
  }

  public static final void b(Context paramContext, String paramString)
  {
    try
    {
      SharedPreferences localSharedPreferences = b(paramContext);
      int i1 = Config.getAppVersion(paramContext);
      paramContext = localSharedPreferences.edit();
      paramContext.putString("app_device_token", paramString);
      paramContext.putString("app_device_token_temp", paramString);
      paramContext.putInt("app_version", i1);
      paramContext.putLong("app_last_register_time ", System.currentTimeMillis());
      paramContext.commit();
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static final void c(Context paramContext)
  {
    Config.clearXToken(paramContext);
  }

  public static void c(Context paramContext, String paramString)
  {
    Config.setPushUserToken(paramContext, paramString);
  }

  public static final String d(Context paramContext)
  {
    return Config.getXToken(paramContext);
  }

  public static final void d(Context paramContext, String paramString)
  {
    if (paramContext != null);
    try
    {
      Config.setUTClassName(paramContext, paramString);
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static final void e(Context paramContext)
  {
    Config.clear(paramContext);
  }

  public static void e(Context paramContext, String paramString)
  {
    if (paramContext != null);
    try
    {
      Config.setAgooGroup(paramContext, paramString);
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static final String f(Context paramContext)
  {
    return Config.getAppKey(paramContext);
  }

  public static final String g(Context paramContext)
  {
    try
    {
      paramContext = b(paramContext).getString("app_key_temp", "");
      return paramContext;
    }
    catch (Throwable paramContext)
    {
    }
    return "";
  }

  public static final String h(Context paramContext)
  {
    return Config.getTtId(paramContext);
  }

  public static final String i(Context paramContext)
  {
    try
    {
      paramContext = b(paramContext).getString("app_tt_id_temp", "");
      return paramContext;
    }
    catch (Throwable paramContext)
    {
    }
    return "";
  }

  public static final String j(Context paramContext)
  {
    return Config.getAppSecret(paramContext);
  }

  public static final String k(Context paramContext)
  {
    try
    {
      paramContext = b(paramContext).getString("app_sercet_temp", "");
      return paramContext;
    }
    catch (Throwable paramContext)
    {
    }
    return "";
  }

  public static final String l(Context paramContext)
  {
    return n(paramContext);
  }

  public static final boolean m(Context paramContext)
  {
    return !TextUtils.isEmpty(n(paramContext));
  }

  public static final String n(Context paramContext)
  {
    return Config.getDeviceToken(paramContext);
  }

  public static String o(Context paramContext)
  {
    try
    {
      paramContext = b(paramContext).getString("app_device_token_temp", "");
      return paramContext;
    }
    catch (Throwable paramContext)
    {
    }
    return null;
  }

  public static final String p(Context paramContext)
  {
    try
    {
      paramContext = b(paramContext).getString("app_election_source", "local");
      return paramContext;
    }
    catch (Throwable paramContext)
    {
    }
    return "local";
  }

  public static final long q(Context paramContext)
  {
    try
    {
      long l1 = b(paramContext).getLong("app_sudo_pack_timeout", -1L);
      return l1;
    }
    catch (Throwable paramContext)
    {
    }
    return -1L;
  }

  public static final int r(Context paramContext)
  {
    try
    {
      int i1 = b(paramContext).getInt("backoff_count", 0);
      return i1;
    }
    catch (Throwable paramContext)
    {
      Log.w("Settings", "getBackoffCount", paramContext);
    }
    return 0;
  }

  public static final void s(Context paramContext)
  {
    a(paramContext, 0);
  }

  public static final void t(Context paramContext)
  {
    try
    {
      paramContext = b(paramContext);
      int i1 = paramContext.getInt("app_retry_register", 0);
      paramContext = paramContext.edit();
      paramContext.putInt("app_retry_register", i1 + 1);
      paramContext.commit();
      return;
    }
    catch (Throwable paramContext)
    {
      Log.w("Settings", "setRetryRegisterCount", paramContext);
    }
  }

  public static final int u(Context paramContext)
  {
    try
    {
      int i1 = b(paramContext).getInt("app_retry_register", 0);
      return i1;
    }
    catch (Throwable paramContext)
    {
      Log.w("Settings", "getRetryRegisterCount", paramContext);
    }
    return 0;
  }

  public static final void v(Context paramContext)
  {
    try
    {
      paramContext = b(paramContext).edit();
      paramContext.putInt("app_retry_register", 0);
      paramContext.commit();
      return;
    }
    catch (Throwable paramContext)
    {
      Log.w("Settings", "RestRetryRegisterCount", paramContext);
    }
  }

  public static final boolean w(Context paramContext)
  {
    boolean bool2 = true;
    String str = "";
    try
    {
      long l1 = b(paramContext).getLong("app_last_register_time ", -1L);
      paramContext = str;
      if (l1 != -1L)
        paramContext = a(l1);
      str = a(System.currentTimeMillis());
      boolean bool1 = bool2;
      if (l1 != -1L)
      {
        boolean bool3 = str.equals(paramContext);
        bool1 = bool2;
        if (bool3)
          bool1 = false;
      }
      return bool1;
    }
    catch (Throwable paramContext)
    {
    }
    return true;
  }

  public static final void x(Context paramContext)
  {
    try
    {
      SharedPreferences.Editor localEditor = b(paramContext).edit();
      localEditor.putBoolean("app_disable", true);
      localEditor.putInt("app_disable_version", Config.getAppVersion(paramContext));
      localEditor.commit();
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static final void y(Context paramContext)
  {
    try
    {
      SharedPreferences.Editor localEditor = b(paramContext).edit();
      localEditor.putBoolean("app_disable", false);
      localEditor.putInt("app_disable_version", Config.getAppVersion(paramContext));
      localEditor.commit();
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static final boolean z(Context paramContext)
  {
    return a(paramContext, false);
  }

  public static abstract enum a
  {
    private int f;

    private a(int paramInt)
    {
      this.f = paramInt;
    }

    public final int a()
    {
      return this.f;
    }

    public abstract int b();

    public abstract String c();

    public abstract String d();

    public abstract String e();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.a
 * JD-Core Version:    0.6.2
 */