package org.android.agoo.impl;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.umeng.message.proguard.aU;
import com.umeng.message.proguard.aV;
import com.umeng.message.proguard.aW;
import com.umeng.message.proguard.bG;
import com.umeng.message.proguard.bd;
import com.umeng.message.proguard.bh;
import com.umeng.message.proguard.bk;
import com.umeng.message.proguard.bq;
import com.umeng.message.proguard.bt;
import com.umeng.message.proguard.bw;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import org.android.Config;
import org.android.agoo.IPushService;
import org.android.agoo.callback.IServiceCallBack;
import org.android.agoo.intent.IntentUtil;
import org.android.agoo.net.async.c;
import org.android.agoo.service.IMessageService.Stub;
import org.android.agoo.service.SendMessage;
import org.android.agoo.service.SendMessage.Stub;

public class PushService
  implements Handler.Callback, bh, IPushService
{
  private static bq B;
  private static volatile boolean C = false;
  private static final String a = "PushService";
  private static final int b = 45613913;
  private static final String c = "agoo_action_re_election";
  private static final int d = 0;
  private static final String e = "cockroach";
  private static final String f = "cockroach-PPreotect";
  private static final String g = "pack";
  private static final int h = 0;
  private static final int i = 1;
  private static final int j = 2;
  private static final int k = 3;
  private static final int l = 4;
  private static final int m = 5;
  private final AtomicBoolean A = new AtomicBoolean(false);
  private volatile IServiceCallBack D = null;
  private final BroadcastReceiver E = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      try
      {
        if (TextUtils.equals("agoo_action_re_election", paramAnonymousIntent.getAction()))
          PushService.c(PushService.this).sendEmptyMessage(5);
        return;
      }
      catch (Throwable paramAnonymousContext)
      {
        bd.e("PushService", "onReceive", paramAnonymousContext);
      }
    }
  };
  private final IMessageService.Stub F = new IMessageService.Stub()
  {
    public boolean ping()
      throws RemoteException
    {
      return true;
    }

    public void probe()
      throws RemoteException
    {
      bd.c("PushService", "messageServiceBinder [probe]");
      bG.a(new Runnable()
      {
        public void run()
        {
          try
          {
            if (!org.android.agoo.a.m(PushService.a(PushService.this)))
            {
              bd.c("PushService", "messageServiceBinder [probe][deviceToken==null]");
              return;
            }
            String str = a.a(PushService.a(PushService.this));
            if ((TextUtils.isEmpty(str)) && (!TextUtils.equals(PushService.a(PushService.this).getPackageName(), str)))
            {
              PushService.this.onHandleError("ERROR_NEED_ELECTION");
              bd.c("PushService", "messageServiceBinder [probe][need_election]");
              return;
            }
            if ((PushService.d(PushService.this) == null) || (!PushService.d(PushService.this).f()))
            {
              PushService.this.onHandleCommand("command_restart_sudo");
              bd.c("PushService", "messageServiceBinder [probe][restart_sudo]");
              return;
            }
            bd.c("PushService", "messageServiceBinder [probe][successfully]");
            return;
          }
          catch (Throwable localThrowable)
          {
          }
        }
      });
    }
  };
  private Context n;
  private HandlerThread o = null;
  private Handler p = null;
  private boolean q = false;
  private PendingIntent r = null;
  private boolean s = false;
  private bk t;
  private long u;
  private AlarmManager v = null;
  private String w = null;
  private String x = null;
  private String y = null;
  private String z = null;

  private static final int a(int paramInt1, int paramInt2)
  {
    return paramInt1 & 0xFF | (0xFFFF & paramInt2) << 16 | 0x0;
  }

  private static final void a(Context paramContext)
  {
    try
    {
      if ((Config.getServiceProtect(paramContext)) && (paramContext != null))
      {
        paramContext.getPackageName();
        paramContext.getClass().getName();
        a(2, 60);
        paramContext = IntentUtil.getAgooCockroach(paramContext);
        bd.c("PushService", "registerNotKill--->[current-thread-name:" + Thread.currentThread().getName() + "][action:" + paramContext + "]");
      }
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  private void a(Intent paramIntent, int paramInt1, int paramInt2)
  {
    try
    {
      String str1 = paramIntent.getAction();
      String str2 = IntentUtil.getAgooStart(this.n);
      bd.c("PushService", "action [" + str1 + "]");
      if (TextUtils.equals(str1, str2))
      {
        paramIntent = paramIntent.getStringExtra("method");
        if ((!TextUtils.isEmpty(paramIntent)) && (TextUtils.equals(paramIntent, "stop")))
        {
          c();
          return;
        }
        if ((!TextUtils.isEmpty(paramIntent)) && (TextUtils.equals(paramIntent, "start")))
        {
          if (e())
          {
            b(null);
            return;
          }
          c();
        }
      }
      return;
    }
    catch (Throwable paramIntent)
    {
    }
  }

  private void a(String paramString)
  {
    while (true)
    {
      long l3;
      try
      {
        if (this.s)
          return;
        this.s = true;
        Intent localIntent = new Intent("agoo_action_re_election");
        localIntent.setPackage(this.n.getPackageName());
        localIntent.putExtra("eventId", paramString);
        int i1 = new Random().nextInt(120);
        l1 = System.currentTimeMillis();
        l3 = i1 + 1320;
        long l2 = org.android.agoo.a.q(this.n);
        if (l2 > System.currentTimeMillis() + 1800000L)
        {
          l1 = l2;
          this.v = ((AlarmManager)this.n.getSystemService("alarm"));
          if (this.r != null)
          {
            this.r.cancel();
            this.v.cancel(this.r);
          }
          this.r = PendingIntent.getBroadcast(this.n, 45613913, localIntent, 134217728);
          bd.c("PushService", "election next time[current-thread-name:" + Thread.currentThread().getName() + "][" + bw.a(l1) + "][timeout:" + l2 + "] ");
          this.v.set(1, l1, this.r);
          return;
        }
      }
      catch (Throwable paramString)
      {
        bd.e("PushService", "ReElection start", paramString);
        return;
      }
      long l1 = l3 * 60L * 1000L + l1;
    }
  }

  private static final void b(Context paramContext)
  {
    try
    {
      if (Config.getServiceProtect(paramContext))
      {
        B.b();
        bd.c("PushService", "unregisterNotKill--->[current-thread-name:" + Thread.currentThread().getName() + "]");
      }
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  private void b(String paramString)
  {
    try
    {
      if (this.t != null)
        this.t.e();
      a(paramString);
      return;
    }
    catch (Throwable paramString)
    {
    }
  }

  private void d()
  {
    try
    {
      this.u = System.currentTimeMillis();
      this.t = new bk(this.n, this);
      return;
    }
    catch (Throwable localThrowable)
    {
      bd.d("PushService", "initMessage", localThrowable);
    }
  }

  private boolean e()
  {
    try
    {
      if (this.n == null)
      {
        bd.c("PushService", "mContext == null");
        return false;
      }
      this.w = org.android.agoo.a.f(this.n);
      if (TextUtils.isEmpty(this.w))
      {
        onHandleError("ERROR_APPKEY_NULL");
        return false;
      }
      this.y = org.android.agoo.a.h(this.n);
      if (TextUtils.isEmpty(this.y))
      {
        onHandleError("ERROR_TTID_NULL");
        return false;
      }
      this.x = org.android.agoo.a.j(this.n);
      this.z = org.android.agoo.a.n(this.n);
      if (TextUtils.isEmpty(this.z))
      {
        onHandleError("ERROR_DEVICETOKEN_NULL");
        return false;
      }
      if (this.t == null)
        d();
      this.t.b(this.w);
      this.t.a(this.x);
      this.t.c(this.y);
      this.t.d(this.z);
      return true;
    }
    catch (Throwable localThrowable)
    {
    }
    return false;
  }

  private boolean f()
  {
    try
    {
      if (!org.android.agoo.a.m(this.n))
        return true;
      String str = a.a(this.n);
      if (TextUtils.isEmpty(str))
      {
        bd.c("PushService", "[currentSudoPack==null]");
        return true;
      }
      if (!TextUtils.equals(this.n.getPackageName(), str))
      {
        bd.c("PushService", "[currentSudoPack(" + str + ")!=appPackage(" + this.n.getPackageName() + ")]");
        return true;
      }
    }
    catch (Throwable localThrowable)
    {
    }
    return false;
  }

  private final void g()
  {
    try
    {
      if (this.n != null)
      {
        long l1 = 60000L + System.currentTimeMillis();
        String str = IntentUtil.getAgooStart(this.n);
        if (TextUtils.isEmpty(str))
        {
          bd.a("PushService", "action==null");
          return;
        }
        bd.a("PushService", "handleDestroyService [" + bw.a(l1) + "][" + str + ":restart]");
        AlarmManager localAlarmManager = (AlarmManager)this.n.getSystemService("alarm");
        Intent localIntent = new Intent();
        localIntent.setAction(str);
        localIntent.setPackage(this.n.getPackageName());
        localIntent.putExtra("method", "start");
        localIntent.putExtra("eventId", "handleDestroyService");
        localIntent.addFlags(32);
        localIntent.addFlags(268435456);
        localAlarmManager.set(1, l1, PendingIntent.getService(this.n, 0, localIntent, 134217728));
        return;
      }
    }
    catch (Throwable localThrowable)
    {
      bd.e("PushService", "handleDestroyService", localThrowable);
    }
  }

  protected void a()
  {
    try
    {
      aW.d(this.n);
      String str = a.a(this.n);
      new c(this.n, "androidSystem").a(Config.getConnectHeader(this.n));
      aW.c(this.n, str, "androidSystem");
      if (f())
      {
        onHandleError("ERROR_NEED_ELECTION");
        c();
        return;
      }
      if (!e())
      {
        c();
        return;
      }
      b("androidSystemSuccess");
      return;
    }
    catch (Throwable localThrowable)
    {
    }
  }

  protected void b()
  {
    try
    {
      String str = a.a(this.n);
      aW.d(this.n);
      c localc = new c(this.n, "hasComeFromCock");
      LinkedHashMap localLinkedHashMap = Config.getConnectHeader(this.n);
      localLinkedHashMap.put("currentSudoPack", str);
      localc.a(localLinkedHashMap);
      aW.c(this.n, str, "hasComeFromCock");
      if (f())
      {
        onHandleError("ERROR_NEED_ELECTION");
        c();
        return;
      }
      if (!e())
      {
        c();
        return;
      }
      b("hasComeFromCock");
      if (aU.a(this.n))
        new c(this.n, "hasComeFromCockSuccess").a(localLinkedHashMap);
      return;
    }
    catch (Throwable localThrowable)
    {
    }
  }

  public IBinder bind(Intent paramIntent)
  {
    Object localObject = null;
    String str = paramIntent.getAction();
    Set localSet = paramIntent.getCategories();
    StringBuilder localStringBuilder = new StringBuilder().append("onBind:[").append(str).append("],intent categries[");
    if ((localSet == null) || (localSet.isEmpty()));
    for (paramIntent = null; ; paramIntent = localSet.toString())
    {
      bd.c("PushService", paramIntent + "]");
      paramIntent = localObject;
      if (!TextUtils.isEmpty(str))
      {
        paramIntent = localObject;
        if (TextUtils.equals(str, "org.agoo.android.intent.action.PING_V4"))
        {
          paramIntent = localObject;
          if (localSet != null)
          {
            paramIntent = localObject;
            if (!localSet.isEmpty())
            {
              paramIntent = localObject;
              if (localSet.contains(Config.getAgooGroup(this.n)))
                paramIntent = this.F;
            }
          }
        }
      }
      return paramIntent;
    }
  }

  protected final void c()
  {
    try
    {
      this.q = false;
      if (this.p != null)
        this.p.sendEmptyMessage(1);
      return;
    }
    catch (Throwable localThrowable)
    {
    }
  }

  public void create(Context paramContext, IServiceCallBack paramIServiceCallBack)
  {
    try
    {
      Log.w("PushService", ">>> agoo system is creating >>>");
      bt.a(paramContext, "agoo.pid");
      B = bq.a(paramContext, 600, false);
      this.n = paramContext;
      bd.a(this.n);
      aW.a(this.n);
      this.q = true;
      this.o = new HandlerThread("se-service");
      this.o.start();
      this.p = new Handler(this.o.getLooper(), this);
      String str = paramContext.getPackageName();
      Log.w("PushService", "create currentPack=" + str);
      Log.w("PushService", "getCurrentSudo(context)" + a.a(paramContext));
      boolean bool = str.equals(a.a(paramContext));
      Log.w("PushService", str + ",  " + bool);
      if (bool)
        B.a();
      this.D = paramIServiceCallBack;
      this.v = ((AlarmManager)paramContext.getSystemService("alarm"));
      if (!this.A.get())
      {
        this.A.set(true);
        paramContext = new IntentFilter();
        paramContext.addAction("agoo_action_re_election");
        this.n.registerReceiver(this.E, paramContext);
      }
      d();
      return;
    }
    catch (Throwable paramContext)
    {
      bd.d("PushService", "create", paramContext);
    }
  }

  public void destroy(Context paramContext)
  {
    try
    {
      bd.c("PushService", "PushService destroying");
      if (this.A.get())
      {
        this.A.set(false);
        if (this.t != null)
          this.t.i();
        bG.a(new Runnable()
        {
          public void run()
          {
            aW.a(PushService.a(PushService.this), PushService.b(PushService.this));
            aW.b(PushService.a(PushService.this));
          }
        });
        this.n.unregisterReceiver(this.E);
        if (this.r != null)
          this.r.cancel();
        if (this.v != null)
          this.v.cancel(this.r);
      }
      bd.c("PushService", "PushService destroyed");
      bd.c("PushService", "PushService hasNeedNotKill[handleDestroyService]+hasNeedNotKill=" + this.q);
      if (this.q)
      {
        bd.c("PushService", "PushService hasNeedNotKill[handleDestroyService]");
        g();
      }
      while (true)
      {
        this.r = null;
        this.v = null;
        return;
        aV.b(paramContext);
      }
    }
    catch (Throwable localThrowable)
    {
      bd.d("PushService", "destroy", localThrowable);
      bd.c("PushService", "PushService hasNeedNotKill[handleDestroyService]+hasNeedNotKill=" + this.q);
      if (this.q)
      {
        bd.c("PushService", "PushService hasNeedNotKill[handleDestroyService]");
        g();
      }
      while (true)
      {
        this.r = null;
        this.v = null;
        return;
        aV.b(paramContext);
      }
    }
    finally
    {
      bd.c("PushService", "PushService hasNeedNotKill[handleDestroyService]+hasNeedNotKill=" + this.q);
      if (!this.q)
        break label302;
    }
    bd.c("PushService", "PushService hasNeedNotKill[handleDestroyService]");
    g();
    while (true)
    {
      this.r = null;
      this.v = null;
      throw localObject;
      label302: aV.b(paramContext);
    }
  }

  public boolean handleMessage(Message paramMessage)
  {
    try
    {
      switch (paramMessage.what)
      {
      case 0:
        a(this.n);
        break;
      case 1:
        b(this.n);
        this.D.stop();
        break;
      case 2:
        a(this.n);
        a((Intent)paramMessage.obj, paramMessage.arg1, paramMessage.arg2);
        break;
      case 3:
        bq.b(this.n);
        a(this.n);
        b();
        break;
      case 4:
        a(this.n);
        a();
        break;
      case 5:
        onHandleError("ERROR_NEED_ELECTION");
        this.s = false;
        break;
      }
      label152: return true;
    }
    catch (Throwable paramMessage)
    {
      break label152;
    }
  }

  public boolean hasComeFromCock(Intent paramIntent)
  {
    try
    {
      if (!this.n.getPackageName().equals(a.a(this.n)))
        c();
      if (paramIntent == null)
      {
        bd.c("PushService", "hasComeFromCock[intent==null]");
        return false;
      }
      String str1 = paramIntent.getAction();
      String str2 = IntentUtil.getAgooCockroach(this.n);
      if ((TextUtils.isEmpty(str1)) || (!TextUtils.equals(str1, str2)))
      {
        bd.c("PushService", "hasComeFromCock[action==null]or[action!=" + str2 + "]");
        return false;
      }
      str2 = paramIntent.getStringExtra("cockroach");
      if ((TextUtils.isEmpty(str2)) || (!TextUtils.equals(str2, "cockroach-PPreotect")))
      {
        bd.c("PushService", "hasComeFromCock[cockroach==null]or[cockroach!=" + str2 + "]");
        return false;
      }
      paramIntent = paramIntent.getStringExtra("pack");
      if ((TextUtils.isEmpty(paramIntent)) || (!TextUtils.equals(paramIntent, this.n.getPackageName())))
      {
        bd.c("PushService", "hasComeFromCock[pack==null] or [" + paramIntent + "!=" + this.n.getPackageName() + "]");
        return false;
      }
      bd.c("PushService", "hasComeFromCock[" + str1 + "] [" + paramIntent + "==" + this.n.getPackageName() + "]");
    }
    catch (Throwable paramIntent)
    {
    }
    return true;
  }

  public void onHandleCommand(String paramString)
  {
    try
    {
      paramString = IntentUtil.createComandIntent(this.n, paramString);
      paramString.setPackage(this.n.getPackageName());
      this.n.sendBroadcast(paramString);
      return;
    }
    catch (Throwable paramString)
    {
      bd.d("PushService", "handleError", paramString);
    }
  }

  public void onHandleError(String paramString)
  {
    try
    {
      Intent localIntent = IntentUtil.createComandIntent(this.n, "error");
      localIntent.setPackage(this.n.getPackageName());
      localIntent.putExtra("error", paramString);
      this.n.sendBroadcast(localIntent);
      return;
    }
    catch (Throwable paramString)
    {
      bd.d("PushService", "handleError", paramString);
    }
  }

  public void onHandleMessage(String paramString, Bundle paramBundle)
  {
    try
    {
      bd.c("PushService", "onHandleMessage current tid:" + Thread.currentThread().getId());
      this.p.post(new b(paramString, paramBundle, this.t));
      return;
    }
    catch (Throwable paramString)
    {
      bd.e("PushService", "handleMessage error >>", paramString);
    }
  }

  public int startCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    if (paramIntent == null);
    try
    {
      this.p.sendEmptyMessage(4);
      return 1;
      Message localMessage = Message.obtain();
      localMessage.arg1 = paramInt1;
      localMessage.arg2 = paramInt2;
      localMessage.obj = paramIntent;
      if (hasComeFromCock(paramIntent))
      {
        localMessage.what = 3;
        this.p.sendMessage(localMessage);
        return 1;
      }
      localMessage.what = 2;
      this.p.sendMessage(localMessage);
      if (aU.a(this.n))
      {
        paramIntent = paramIntent.getStringExtra("eventId");
        Log.d("PushService", "pushService startCommand,eventId=" + paramIntent + "_Success");
        if (!TextUtils.isEmpty(paramIntent))
          new c(this.n, paramIntent + "_Success").a(Config.getConnectHeader(this.n));
      }
      return 1;
    }
    catch (Throwable paramIntent)
    {
    }
    return 1;
  }

  public boolean unbind(Intent paramIntent)
  {
    return false;
  }

  class a
    implements ServiceConnection
  {
    private Intent b;
    private bk c;
    private String d;
    private SendMessage e;
    private ServiceConnection f;

    public a(String paramIntent, Intent parambk, bk arg4)
    {
      this.d = paramIntent;
      this.b = parambk;
      Object localObject;
      this.c = localObject;
      this.f = this;
    }

    public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
    {
      bd.c("PushService", "MessageConnection conneted:" + paramComponentName);
      this.e = SendMessage.Stub.asInterface(paramIBinder);
      bd.c("PushService", "onConnected current tid:" + Thread.currentThread().getId());
      bd.c("PushService", "MessageConnection sent:" + this.b);
      if (this.e != null)
        PushService.c(PushService.this).post(new Runnable()
        {
          public void run()
          {
            try
            {
              bd.c("PushService", "onConnected running tid:" + Thread.currentThread().getId());
              PushService.a.b(PushService.a.this).doSend(PushService.a.a(PushService.a.this));
              return;
            }
            catch (RemoteException localRemoteException)
            {
              bd.e("PushService", "send error", localRemoteException);
              bk localbk = PushService.a.d(PushService.a.this);
              String str = PushService.a.c(PushService.a.this);
              PushService.a.d(PushService.a.this);
              localbk.a(str, null, "14");
              return;
            }
            finally
            {
              bd.c("PushService", "send finish. close this connection");
              PushService.a.a(PushService.a.this, null);
              PushService.a(PushService.this).unbindService(PushService.a.e(PushService.a.this));
            }
          }
        });
    }

    public void onServiceDisconnected(ComponentName paramComponentName)
    {
      bd.c("PushService", "MessageConnection disConnected");
    }
  }

  class b
    implements Runnable
  {
    private String b;
    private Bundle c;
    private bk d;

    public b(String paramBundle, Bundle parambk, bk arg4)
    {
      this.b = paramBundle;
      this.c = parambk;
      Object localObject;
      this.d = localObject;
    }

    public void run()
    {
      bd.c("PushService", "running tid:" + Thread.currentThread().getId());
      Object localObject2 = new Intent();
      ((Intent)localObject2).setAction("org.agoo.android.intent.action.RECEIVE");
      ((Intent)localObject2).setPackage(this.b);
      ((Intent)localObject2).putExtras(this.c);
      ((Intent)localObject2).putExtra("type", "common-push");
      ((Intent)localObject2).putExtra("message_source", "apoll");
      ((Intent)localObject2).addFlags(32);
      bd.c("PushService", "cast intent:" + this.c);
      PushService.a(PushService.this).sendBroadcast((Intent)localObject2);
      Object localObject1 = new Intent("org.android.agoo.client.MessageReceiverService");
      ((Intent)localObject1).setPackage(this.b);
      bd.c("PushService", "this message pack:" + this.b);
      bd.c("PushService", "start to service...");
      try
      {
        localObject2 = new PushService.a(PushService.this, this.c.getString("i"), (Intent)localObject2, this.d);
        boolean bool = PushService.a(PushService.this).bindService((Intent)localObject1, (ServiceConnection)localObject2, 17);
        bd.c("PushService", "start service ret:" + bool);
        if (!bool)
        {
          localObject1 = this.d;
          localObject2 = this.c.getString("id");
          bk localbk = this.d;
          ((bk)localObject1).a((String)localObject2, null, "14");
        }
        return;
      }
      catch (Throwable localThrowable)
      {
        bd.c("PushService", "bindService error...e=" + localThrowable);
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.impl.PushService
 * JD-Core Version:    0.6.2
 */