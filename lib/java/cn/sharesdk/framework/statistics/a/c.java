package cn.sharesdk.framework.statistics.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class c
{
  private static c c;
  private Context a;
  private SharedPreferences b;

  private c(Context paramContext)
  {
    this.a = paramContext.getApplicationContext();
    this.b = this.a.getSharedPreferences("share_sdk_0", 0);
  }

  public static c a(Context paramContext)
  {
    if (c == null)
      c = new c(paramContext.getApplicationContext());
    return c;
  }

  public long a()
  {
    return this.b.getLong("service_time", 0L);
  }

  public String a(String paramString)
  {
    return this.b.getString(paramString, "");
  }

  public void a(long paramLong)
  {
    a("device_time", Long.valueOf(paramLong));
  }

  public void a(String paramString, int paramInt)
  {
    SharedPreferences.Editor localEditor = this.b.edit();
    localEditor.putInt(paramString, paramInt);
    localEditor.commit();
  }

  public void a(String paramString, Boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = this.b.edit();
    localEditor.putBoolean(paramString, paramBoolean.booleanValue());
    localEditor.commit();
  }

  public void a(String paramString, Long paramLong)
  {
    SharedPreferences.Editor localEditor = this.b.edit();
    localEditor.putLong(paramString, paramLong.longValue());
    localEditor.commit();
  }

  public void a(String paramString1, String paramString2)
  {
    SharedPreferences.Editor localEditor = this.b.edit();
    localEditor.putString(paramString1, paramString2);
    localEditor.commit();
  }

  public void a(boolean paramBoolean)
  {
    a("connect_server", Boolean.valueOf(paramBoolean));
  }

  public long b(String paramString)
  {
    return this.b.getLong(paramString, 0L);
  }

  public void b(long paramLong)
  {
    a("connect_server_time", Long.valueOf(paramLong));
  }

  public void b(String paramString1, String paramString2)
  {
    a("buffered_snsconf_" + paramString1, paramString2);
  }

  public boolean b()
  {
    return Boolean.parseBoolean(this.b.getString("upload_device_info", "true"));
  }

  public int c(String paramString)
  {
    return this.b.getInt(paramString, 0);
  }

  public boolean c()
  {
    return Boolean.parseBoolean(this.b.getString("upload_user_info", "true"));
  }

  public int d()
  {
    String str = this.b.getString("upload_share_content", "0");
    if ("true".equals(str))
      return 1;
    if ("false".equals(str))
      return -1;
    return 0;
  }

  public void d(String paramString)
  {
    a("upload_device_info", paramString);
  }

  public String e()
  {
    return a("device_data");
  }

  public void e(String paramString)
  {
    a("upload_user_info", paramString);
  }

  public String f()
  {
    return a("device_ext_data");
  }

  public void f(String paramString)
  {
    a("upload_share_content", paramString);
  }

  public Long g()
  {
    return Long.valueOf(b("device_time"));
  }

  public String g(String paramString)
  {
    return a("buffered_snsconf_" + paramString);
  }

  public void h(String paramString)
  {
    a("device_data", paramString);
  }

  public boolean h()
  {
    return this.b.getBoolean("connect_server", true);
  }

  public Long i()
  {
    return Long.valueOf(b("connect_server_time"));
  }

  public void i(String paramString)
  {
    a("device_ext_data", paramString);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.framework.statistics.a.c
 * JD-Core Version:    0.6.2
 */