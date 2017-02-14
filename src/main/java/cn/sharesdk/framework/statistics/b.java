package cn.sharesdk.framework.statistics;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.framework.statistics.b.e;
import cn.sharesdk.framework.statistics.b.g;
import com.mob.tools.SSDKHandlerThread;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Ln;
import java.io.PrintStream;
import java.util.Calendar;

public class b extends SSDKHandlerThread
{
  private static b a;
  private Context b;
  private DeviceHelper c;
  private a d;
  private String e;
  private Handler f;
  private boolean g;
  private int h;
  private boolean i;
  private long j;
  private boolean k;

  private b(Context paramContext)
  {
    super("Thread-" + Math.abs(4736));
    this.b = paramContext;
    this.c = DeviceHelper.getInstance(paramContext);
    this.d = a.a(paramContext);
  }

  public static b a(Context paramContext)
  {
    try
    {
      b localb = a;
      if (localb == null)
        if (paramContext != null);
      for (paramContext = null; ; paramContext = a)
      {
        return paramContext;
        a = new b(paramContext.getApplicationContext());
      }
    }
    finally
    {
    }
    throw paramContext;
  }

  private void a()
  {
    boolean bool = b();
    if (bool)
      if (!this.k)
      {
        this.k = bool;
        this.j = System.currentTimeMillis();
        a(new g());
      }
    while (!this.k)
      return;
    this.k = bool;
    long l1 = System.currentTimeMillis();
    long l2 = this.j;
    e locale = new e();
    locale.a = (l1 - l2);
    a(locale);
  }

  private void b(cn.sharesdk.framework.statistics.b.c paramc)
  {
    paramc.f = this.c.getDeviceKey();
    paramc.g = this.e;
    paramc.h = this.c.getPackageName();
    paramc.i = this.c.getAppVersion();
    paramc.j = String.valueOf(50000 + this.h);
    paramc.k = this.c.getPlatformCode();
    paramc.l = this.c.getDetailNetworkTypeForStatic();
    if ((!"cn.sharesdk.demo".equals(paramc.h)) && ("api20".equals(this.e)) && (ShareSDK.isDebug()))
      System.err.println("Your application is using the appkey of ShareSDK Demo, this will cause its data won't be count!");
    paramc.m = this.c.getDeviceData();
  }

  private boolean b()
  {
    Object localObject = DeviceHelper.getInstance(this.b);
    String str = ((DeviceHelper)localObject).getTopTaskPackageName();
    localObject = ((DeviceHelper)localObject).getPackageName();
    return (localObject != null) && (((String)localObject).equals(str));
  }

  private void c()
  {
    try
    {
      a.a(this.b).a();
      return;
    }
    catch (Throwable localThrowable)
    {
      Ln.e(localThrowable);
    }
  }

  private void c(cn.sharesdk.framework.statistics.b.c paramc)
  {
    try
    {
      this.d.a(paramc);
      paramc.b(this.b);
      return;
    }
    catch (Throwable localThrowable)
    {
      Ln.e(localThrowable);
      Ln.e(paramc.toString(), new Object[0]);
    }
  }

  public void a(int paramInt)
  {
    this.h = paramInt;
  }

  public void a(Handler paramHandler)
  {
    this.f = paramHandler;
  }

  public void a(cn.sharesdk.framework.statistics.b.c paramc)
  {
    Message localMessage;
    if (this.i)
    {
      b(paramc);
      if (paramc.a(this.b))
      {
        localMessage = new Message();
        localMessage.what = 3;
        localMessage.obj = paramc;
      }
    }
    else
    {
      try
      {
        this.handler.sendMessage(localMessage);
        return;
      }
      catch (Throwable paramc)
      {
        Ln.e(paramc);
        return;
      }
    }
    Ln.d("Drop event: " + paramc.toString(), new Object[0]);
  }

  public void a(String paramString)
  {
    this.e = paramString;
  }

  public void a(boolean paramBoolean)
  {
    this.g = paramBoolean;
  }

  protected void onMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default:
    case 3:
      do
        return;
      while (paramMessage.obj == null);
      c((cn.sharesdk.framework.statistics.b.c)paramMessage.obj);
      return;
    case 1:
      a();
      try
      {
        this.handler.sendEmptyMessageDelayed(1, 100L);
        return;
      }
      catch (Throwable paramMessage)
      {
        Ln.e(paramMessage);
        return;
      }
    case 2:
      c();
      try
      {
        this.handler.sendEmptyMessageDelayed(2, 10000L);
        return;
      }
      catch (Throwable paramMessage)
      {
        Ln.e(paramMessage);
        return;
      }
    case 4:
    }
    long l = cn.sharesdk.framework.statistics.a.c.a(this.b).g().longValue();
    paramMessage = Calendar.getInstance();
    paramMessage.setTimeInMillis(l);
    int m = paramMessage.get(1);
    int n = paramMessage.get(2);
    int i1 = paramMessage.get(5);
    paramMessage.setTimeInMillis(System.currentTimeMillis());
    int i2 = paramMessage.get(1);
    int i3 = paramMessage.get(2);
    int i4 = paramMessage.get(5);
    if ((m != i2) || (n != i3) || (i1 != i4))
      this.d.b(this.e);
    while (true)
    {
      this.handler.sendEmptyMessageDelayed(4, 3600000L);
      return;
      this.d.d(this.e);
    }
  }

  protected void onStart(Message paramMessage)
  {
    if (!this.i)
    {
      this.i = true;
      this.d.a(this.e);
      this.d.b(this.e);
      this.handler.sendEmptyMessageDelayed(4, 3600000L);
      this.d.a(this.g);
      this.handler.sendEmptyMessage(1);
      this.handler.sendEmptyMessage(2);
      NewAppReceiver.a(this.b);
    }
  }

  protected void onStop(Message paramMessage)
  {
    if (this.i)
    {
      long l1 = System.currentTimeMillis();
      long l2 = this.j;
      paramMessage = new e();
      paramMessage.a = (l1 - l2);
      a(paramMessage);
      this.i = false;
    }
    try
    {
      this.f.sendEmptyMessage(1);
      a = null;
      this.handler.getLooper().quit();
      return;
    }
    catch (Throwable paramMessage)
    {
      while (true)
        Ln.e(paramMessage);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.framework.statistics.b
 * JD-Core Version:    0.6.2
 */