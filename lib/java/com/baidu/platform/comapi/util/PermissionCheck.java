package com.baidu.platform.comapi.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import com.baidu.lbsapi.auth.LBSAuthManager;
import com.baidu.lbsapi.auth.LBSAuthManagerListener;
import java.util.Hashtable;
import org.json.JSONException;
import org.json.JSONObject;

public class PermissionCheck
{
  private static final String a = PermissionCheck.class.getSimpleName();
  private static Context b;
  private static Hashtable<String, String> c;
  private static LBSAuthManager d = null;
  private static LBSAuthManagerListener e = null;
  private static c f = null;

  public static void destory()
  {
    f = null;
    b = null;
    e = null;
  }

  public static void init(Context paramContext)
  {
    b = paramContext;
    if (c == null)
      c = new Hashtable();
    if (d == null)
      d = LBSAuthManager.getInstance(b);
    if (e == null)
      e = new a(null);
    Object localObject = "";
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(b.getPackageName(), 0).applicationInfo.loadLabel(b.getPackageManager()).toString();
      Log.d("auth info", "mcode: " + a.a(b));
      localObject = f.a();
      c.put("mb", ((Bundle)localObject).getString("mb"));
      c.put("os", ((Bundle)localObject).getString("os"));
      c.put("sv", ((Bundle)localObject).getString("sv"));
      c.put("imt", "1");
      c.put("net", ((Bundle)localObject).getString("net"));
      c.put("cpu", ((Bundle)localObject).getString("cpu"));
      c.put("glr", ((Bundle)localObject).getString("glr"));
      c.put("glv", ((Bundle)localObject).getString("glv"));
      c.put("resid", ((Bundle)localObject).getString("resid"));
      c.put("appid", "-1");
      c.put("ver", "1");
      c.put("screen", String.format("(%d,%d)", new Object[] { Integer.valueOf(((Bundle)localObject).getInt("screen_x")), Integer.valueOf(((Bundle)localObject).getInt("screen_y")) }));
      c.put("dpi", String.format("(%d,%d)", new Object[] { Integer.valueOf(((Bundle)localObject).getInt("dpi_x")), Integer.valueOf(((Bundle)localObject).getInt("dpi_y")) }));
      c.put("pcn", ((Bundle)localObject).getString("pcn"));
      c.put("cuid", ((Bundle)localObject).getString("cuid"));
      c.put("name", paramContext);
      return;
    }
    catch (Exception paramContext)
    {
      while (true)
      {
        paramContext.printStackTrace();
        paramContext = (Context)localObject;
      }
    }
  }

  public static int permissionCheck()
  {
    int j = 0;
    int i = j;
    try
    {
      if (d != null)
      {
        i = j;
        if (e != null)
        {
          Context localContext = b;
          if (localContext != null)
            break label36;
        }
      }
      label36: for (i = j; ; i = d.authenticate(false, "lbs_androidsdk", c, e))
        return i;
    }
    finally
    {
    }
  }

  public static void setPermissionCheckResultListener(c paramc)
  {
    f = paramc;
  }

  private static class a
    implements LBSAuthManagerListener
  {
    public void onAuthResult(int paramInt, String paramString)
    {
      if (paramString == null);
      while (true)
      {
        return;
        PermissionCheck.b localb = new PermissionCheck.b();
        try
        {
          paramString = new JSONObject(paramString);
          if (paramString.has("status"))
            localb.a = paramString.optInt("status");
          if (paramString.has("appid"))
            localb.c = paramString.optString("appid");
          if (paramString.has("uid"))
            localb.b = paramString.optString("uid");
          if (paramString.has("message"))
            localb.d = paramString.optString("message");
          if (paramString.has("token"))
            localb.e = paramString.optString("token");
          if (PermissionCheck.a() == null)
            continue;
          PermissionCheck.a().a(localb);
          return;
        }
        catch (JSONException paramString)
        {
          while (true)
            paramString.printStackTrace();
        }
      }
    }
  }

  public static class b
  {
    public int a = 0;
    public String b = "-1";
    public String c = "-1";
    public String d = "";
    public String e;

    public String toString()
    {
      return String.format("errorcode: %d uid: %s appid %s msg: %s", new Object[] { Integer.valueOf(this.a), this.b, this.c, this.d });
    }
  }

  public static abstract interface c
  {
    public abstract void a(PermissionCheck.b paramb);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.platform.comapi.util.PermissionCheck
 * JD-Core Version:    0.6.2
 */