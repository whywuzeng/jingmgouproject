package com.baidu.platform.comapi;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.baidu.mapapi.VersionInfo;
import com.baidu.platform.comapi.util.PermissionCheck;
import com.baidu.platform.comapi.util.PermissionCheck.b;
import com.baidu.platform.comapi.util.PermissionCheck.c;
import com.baidu.platform.comapi.util.c;
import com.baidu.platform.comapi.util.f;
import com.baidu.platform.comjni.engine.AppEngine;
import com.baidu.vi.VMsg;
import java.io.PrintStream;

public class a
  implements PermissionCheck.c
{
  private static final String a = a.class.getSimpleName();
  private static a f;
  private static int g = -100;
  private Context b;
  private Handler c;
  private d d;
  private int e;

  static
  {
    try
    {
      System.loadLibrary(VersionInfo.getKitName());
      AppEngine.InitClass();
      return;
    }
    catch (Error localError)
    {
      System.out.println(VersionInfo.getKitName() + " Failed to load.");
    }
  }

  public static a a()
  {
    if (f == null)
      f = new a();
    return f;
  }

  private void f()
  {
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
    localIntentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
    if ((this.b != null) && (this.d != null))
      this.b.registerReceiver(this.d, localIntentFilter);
  }

  private void g()
  {
    if ((this.d != null) && (this.b != null))
      this.b.unregisterReceiver(this.d);
  }

  public void a(Context paramContext)
  {
    this.b = paramContext;
  }

  public void a(Message paramMessage)
  {
    if (paramMessage.what == 2012)
      if (paramMessage.arg1 == 0)
      {
        paramMessage = new Intent("permission check ok");
        this.b.sendBroadcast(paramMessage);
      }
    do
    {
      return;
      Intent localIntent = new Intent("permission check error");
      localIntent.putExtra("error_code", paramMessage.arg1);
      paramMessage = localIntent;
      break;
      if (paramMessage.arg2 == 3)
      {
        localIntent = new Intent("network error");
        this.b.sendBroadcast(localIntent);
      }
    }
    while ((paramMessage.arg2 != 2) && (paramMessage.arg2 != 404) && (paramMessage.arg2 != 5) && (paramMessage.arg2 != 8));
    paramMessage = new Intent("network error");
    this.b.sendBroadcast(paramMessage);
  }

  public void a(PermissionCheck.b paramb)
  {
    if (paramb == null);
    while (true)
    {
      return;
      if (paramb.a == 0)
      {
        f.D = paramb.e;
        f.a(paramb.b, paramb.c);
      }
      while ((this.c != null) && (paramb.a != g))
      {
        g = paramb.a;
        Message.obtain(this.c, 2012, paramb.a, paramb.a, null).sendToTarget();
        return;
        Log.e("baidumapsdk", "Authentication Error " + paramb.toString());
      }
    }
  }

  public void b()
  {
    if (this.e == 0)
    {
      if (this.b == null)
        throw new IllegalStateException("you have not supplyed the global app context info from SDKInitializer.initialize(Context) function.");
      com.baidu.vi.b.a(this.b);
      VMsg.init();
      AppEngine.InitEngine(this.b, f.a());
      AppEngine.StartSocketProc();
      this.d = new d();
      f();
      c.a(this.b);
    }
    this.e += 1;
  }

  public boolean c()
  {
    if (this.b == null)
      throw new IllegalStateException("you have not supplyed the global app context info from SDKInitializer.initialize(Context) function.");
    com.baidu.platform.comjni.engine.a.a(2000, this.c);
    this.c = new b(this);
    f.b(this.b);
    f.b();
    f.d();
    PermissionCheck.init(this.b);
    PermissionCheck.setPermissionCheckResultListener(this);
    PermissionCheck.permissionCheck();
    return true;
  }

  public void d()
  {
    this.e -= 1;
    if (this.e == 0)
    {
      g();
      VMsg.destroy();
      com.baidu.platform.comjni.engine.a.a();
      AppEngine.UnInitEngine();
    }
  }

  public Context e()
  {
    if (this.b == null)
      throw new IllegalStateException("you have not supplyed the global app context info from SDKInitializer.initialize(Context) function.");
    return this.b;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.platform.comapi.a
 * JD-Core Version:    0.6.2
 */