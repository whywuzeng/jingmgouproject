package com.umeng.analytics.onlineconfig;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.h;
import java.util.Iterator;
import org.json.JSONObject;
import u.aly.bq;
import u.aly.br;
import u.aly.bw;
import u.aly.bx;
import u.aly.by;
import u.aly.cd;

public class a
{
  public static final String a = "type";
  public static final String b = "package";
  public static final String c = "channel";
  public static final String d = "idmd5";
  public static final String e = "version_code";
  public static final String f = "appkey";
  public static final String g = "sdk_version";
  private static final long k = 600000L;
  private final String h = "last_config_time";
  private final String i = "report_policy";
  private final String j = "online_config";
  private UmengOnlineConfigureListener l = null;
  private c m = null;

  private void a(Context paramContext, b paramb)
  {
    if (paramb.c != -1)
      h.a(paramContext).a(paramb.c, paramb.d);
  }

  private void a(JSONObject paramJSONObject)
  {
    if (this.l != null)
      this.l.onDataReceived(paramJSONObject);
  }

  private long b(Context paramContext)
  {
    return h.a(paramContext).j().getLong("oc_mdf_told", 0L);
  }

  private void b(Context paramContext, b paramb)
  {
    if ((paramb.a == null) || (paramb.a.length() == 0))
      return;
    paramContext = h.a(paramContext).j().edit();
    try
    {
      paramb = paramb.a;
      Iterator localIterator = paramb.keys();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        paramContext.putString(str, paramb.getString(str));
      }
    }
    catch (Exception paramContext)
    {
      br.c("MobclickAgent", "save online config params", paramContext);
      return;
    }
    paramContext.commit();
    br.a("MobclickAgent", "get online setting params: " + paramb);
  }

  public void a()
  {
    this.l = null;
  }

  public void a(Context paramContext)
  {
    if (paramContext == null);
    try
    {
      br.b("MobclickAgent", "unexpected null context in updateOnlineConfig");
      return;
      new Thread(new b(paramContext.getApplicationContext())).start();
      return;
    }
    catch (Exception paramContext)
    {
      br.b("MobclickAgent", "exception in updateOnlineConfig");
    }
  }

  public void a(UmengOnlineConfigureListener paramUmengOnlineConfigureListener)
  {
    this.l = paramUmengOnlineConfigureListener;
  }

  public void a(c paramc)
  {
    this.m = paramc;
  }

  public void b()
  {
    this.m = null;
  }

  class a extends bx
  {
    private final String e = "http://oc.umeng.com/v2/check_config_update";
    private JSONObject f;

    public a(Context arg2)
    {
      super();
      this.d = "http://oc.umeng.com/v2/check_config_update";
      Context localContext;
      this.f = a(localContext);
    }

    private JSONObject a(Context paramContext)
    {
      JSONObject localJSONObject = new JSONObject();
      try
      {
        localJSONObject.put("type", "online_config");
        localJSONObject.put("appkey", AnalyticsConfig.getAppkey(paramContext));
        localJSONObject.put("version_code", bq.c(paramContext));
        localJSONObject.put("package", bq.u(paramContext));
        localJSONObject.put("sdk_version", AnalyticsConfig.getSDKVersion());
        localJSONObject.put("idmd5", cd.b(bq.f(paramContext)));
        localJSONObject.put("channel", AnalyticsConfig.getChannel(paramContext));
        localJSONObject.put("report_policy", h.a(paramContext).c()[0]);
        localJSONObject.put("last_config_time", a.a(a.this, paramContext));
        return localJSONObject;
      }
      catch (Exception paramContext)
      {
        br.b("MobclickAgent", "exception in onlineConfigInternal");
      }
      return null;
    }

    public JSONObject a()
    {
      return this.f;
    }

    public String b()
    {
      return this.d;
    }
  }

  public class b extends bw
    implements Runnable
  {
    Context a;

    public b(Context arg2)
    {
      Object localObject;
      this.a = localObject.getApplicationContext();
    }

    private void b()
    {
      b localb = (b)a(new a.a(a.this, this.a), b.class);
      if (localb == null)
      {
        a.a(a.this, null);
        return;
      }
      if (localb.b)
      {
        if (a.a(a.this) != null)
          a.a(a.this).a(localb.c, localb.d);
        a.a(a.this, this.a, localb);
        a.b(a.this, this.a, localb);
        a.a(a.this, localb.a);
        return;
      }
      a.a(a.this, null);
    }

    private boolean c()
    {
      boolean bool = true;
      if (TextUtils.isEmpty(AnalyticsConfig.getAppkey(this.a)))
        br.b("MobclickAgent", "Appkey is missing ,Please check AndroidManifest.xml");
      label289: label292: 
      while (true)
      {
        return false;
        int i;
        Object localObject;
        if ((br.a) && (bq.w(this.a)))
        {
          i = 1;
          if (i != 0)
            break label289;
          localObject = h.a(this.a).j();
          long l1 = ((SharedPreferences)localObject).getLong("last_test_t", 0L);
          long l2 = System.currentTimeMillis();
          if (l2 - l1 <= ((SharedPreferences)localObject).getLong("oc_req_i", 600000L))
            break label289;
          ((SharedPreferences)localObject).edit().putLong("last_test_t", l2).commit();
        }
        for (int j = 1; ; j = 0)
        {
          if ((i == 0) && (j == 0))
            break label292;
          localObject = (a.d)a(new a.c(a.this, this.a), a.d.class);
          if (localObject == null)
            break;
          SharedPreferences localSharedPreferences = h.a(this.a).j();
          if (((a.d)localObject).a > localSharedPreferences.getLong("oc_mdf_t", 0L));
          while (true)
          {
            SharedPreferences.Editor localEditor = localSharedPreferences.edit();
            if (((a.d)localObject).b >= 0L)
              localEditor.putLong("oc_req_i", ((a.d)localObject).b);
            if (((a.d)localObject).a >= 0L)
            {
              localEditor.putLong("oc_mdf_told", localSharedPreferences.getLong("oc_mdf_t", 0L));
              localEditor.putLong("oc_mdf_t", ((a.d)localObject).a);
            }
            localEditor.commit();
            return bool;
            i = 0;
            break;
            bool = false;
          }
        }
      }
    }

    public boolean a()
    {
      return true;
    }

    public void run()
    {
      try
      {
        if (c())
          b();
        return;
      }
      catch (Exception localException)
      {
        a.a(a.this, null);
        br.c("MobclickAgent", "request online config error", localException);
      }
    }
  }

  class c extends bx
  {
    private final String e = "http://oc.umeng.com/v2/get_update_time";
    private JSONObject f;

    public c(Context arg2)
    {
      super();
      this.d = "http://oc.umeng.com/v2/get_update_time";
      Context localContext;
      this.f = a(localContext);
    }

    private JSONObject a(Context paramContext)
    {
      JSONObject localJSONObject = new JSONObject();
      try
      {
        localJSONObject.put("appkey", AnalyticsConfig.getAppkey(paramContext));
        localJSONObject.put("version_code", bq.c(paramContext));
        return localJSONObject;
      }
      catch (Exception paramContext)
      {
        br.b("MobclickAgent", "exception in onlineConfigInternal");
      }
      return null;
    }

    public JSONObject a()
    {
      return this.f;
    }

    public String b()
    {
      return this.d;
    }
  }

  public static class d extends by
  {
    public long a = -1L;
    public long b = -1L;

    public d(JSONObject paramJSONObject)
    {
      super();
      a(paramJSONObject);
    }

    private void a(JSONObject paramJSONObject)
    {
      if (paramJSONObject == null)
        return;
      try
      {
        this.a = paramJSONObject.optLong("last_config_time", -1L);
        this.b = (paramJSONObject.optLong("oc_interval", -1L) * 60L * 1000L);
        return;
      }
      catch (Exception paramJSONObject)
      {
        br.e("MobclickAgent", "fail to parce online config response", paramJSONObject);
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.analytics.onlineconfig.a
 * JD-Core Version:    0.6.2
 */